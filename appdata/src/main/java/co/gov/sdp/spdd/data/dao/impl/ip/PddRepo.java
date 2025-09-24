package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class PddRepo extends OperacionesBasicasFacade<Pdd, Long> implements IPddServiceRepo, Serializable {

	/**
	 * Inyeccion del repo de pdd
	 */
	@Autowired
	IPddRepo pddRepo;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<Pdd, Long> getRepo() {
		return pddRepo;
	}

	/**
	 * 
	 * @return
	 */
	public List<Pdd> obtenerTodosPdd() {
		return pddRepo.obtenerTodosPdd();
	}

	@Override
	public FiltroDTO filtrarPorCampo(PddDTO pddDTO, Long inicio, Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pdd> cq = cb.createQuery(Pdd.class);
		Root<Pdd> pdd = cq.from(Pdd.class);
		List<Predicate> predicates = new ArrayList<>();
		if (pddDTO.getIdPlanDesarrollo() != null) {
			predicates.add(
					cb.like(pdd.get("idPlanDesarrollo").as(String.class), "%" + pddDTO.getIdPlanDesarrollo() + "%"));
		}
		if(pddDTO.getNombrePlan() != null && !"".equals(pddDTO.getNombrePlan())){
            predicates.add(cb.like(cb.lower(pdd.get("nombrePlan").as(String.class)),
                    "%" + pddDTO.getNombrePlan().toLowerCase() + "%"));
        }
		if (pddDTO.getProgramaGobierno() != null && !"".equals(pddDTO.getProgramaGobierno())) {
			predicates.add(cb.like(cb.lower(pdd.get("programaGobierno").as(String.class)),
					"%" + pddDTO.getProgramaGobierno().toLowerCase() + "%"));
		}
		if (pddDTO.getNombreAlcalde() != null && !"".equals(pddDTO.getNombreAlcalde())) {
			predicates.add(cb.like(cb.lower(pdd.get("nombreAlcalde").as(String.class)),
					"%" + pddDTO.getNombreAlcalde().toLowerCase() + "%"));
		}
		if (pddDTO.getVigencia() != null && !"".equals(pddDTO.getVigencia())) {
			String[] anios = pddDTO.getVigencia().split("-");

			if (anios.length == 2) {
				predicates.add(cb.like(cb.lower(pdd.get("yearInicio").as(String.class)), anios[0].trim()));

				predicates.add(cb.like(cb.lower(pdd.get("yearFinal").as(String.class)), anios[1].trim()));
			} else {
				predicates.add(cb.like(cb.lower(pdd.get("yearInicio").as(String.class)), "%" + anios[0].trim() + "%"));
			}
		}
		if (pddDTO.getYearInicio() != null && !"".equals(pddDTO.getYearInicio())) {
			predicates.add(cb.like(cb.lower(pdd.get("yearInicio").as(String.class)),
					"%" + pddDTO.getYearInicio().toLowerCase() + "%"));
		}
		if (pddDTO.getYearFinal() != null && !"".equals(pddDTO.getYearFinal())) {
			predicates.add(cb.like(cb.lower(pdd.get("yearFinal").as(String.class)),
					"%" + pddDTO.getYearFinal().toLowerCase() + "%"));
		}
		if (pddDTO.getEstadoPlan() != null && !"".equals(pddDTO.getEstadoPlan())) {
			predicates.add(cb.like(cb.lower(pdd.get("idLsEstadoPlan").get("resultado").as(String.class)),
					"%" + pddDTO.getEstadoPlan().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		if (pddDTO.getColumnaOrdenar() != null) {
			if (pddDTO.getTipoOrden().equals("asc")) {
				if (!pddDTO.getColumnaOrdenar().equalsIgnoreCase("vigencia")) {
					if (!pddDTO.getColumnaOrdenar().equalsIgnoreCase("idLsEstadoPlan"))
						cq.orderBy(cb.asc(pdd.get(pddDTO.getColumnaOrdenar())));
					else
						cq.orderBy(cb.asc(pdd.get(pddDTO.getColumnaOrdenar()).get("resultado")));

				} else {
					cq.orderBy(cb.asc(pdd.get("yearInicio")), cb.asc(pdd.get("yearFinal")));
				}
			} else {
				if (!pddDTO.getColumnaOrdenar().equalsIgnoreCase("vigencia")) {
					if (!pddDTO.getColumnaOrdenar().equalsIgnoreCase("idLsEstadoPlan"))
						cq.orderBy(cb.desc(pdd.get(pddDTO.getColumnaOrdenar())));
					else
						cq.orderBy(cb.desc(pdd.get(pddDTO.getColumnaOrdenar()).get("resultado")));
				} else {
					cq.orderBy(cb.desc(pdd.get("yearInicio")), cb.desc(pdd.get("yearFinal")));
				}
			}
		} else {
			cq.orderBy(cb.desc(pdd.get("idPlanDesarrollo")));
		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Pdd.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<Pdd> obtenerPddsPorEstado(Long idEstado) throws SpddExceptions {
		return pddRepo.obtenerPorEstado(idEstado);
	}

	@Override
	public Pdd obtenerPorId(Long idPlan) {
		return pddRepo.obtenerPorId(idPlan);
	}

	@Override
	public List<Pdd> obtenerTodosOrdenadosASC() {
		return pddRepo.obtenerTodosOrdenadosASC();
	}


}
