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
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPdlRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad Pdl
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
@Transactional
@Service
public class PdlRepo extends OperacionesBasicasFacade<Pdl, Long> implements IPdlServiceRepo, Serializable{

	/**
	 * Inyeccion del repo de pdd
	 */
	@Autowired
	IPdlRepo pdlRepo;
	
	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<Pdl, Long> getRepo() {
		return pdlRepo;
	}
	
	@Override
	public FiltroDTO filtrarPorCampo(PdlDTO pdlDTO, Long inicio, Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pdl> cq = cb.createQuery(Pdl.class);
		Root<Pdl> pdl = cq.from(Pdl.class);
		List<Predicate> predicates = new ArrayList<>();
		if (pdlDTO.getIdPlanLocal() != null) {
			predicates.add(
					cb.like(pdl.get("idPlanLocal").as(String.class), "%" + pdlDTO.getIdPlanLocal() + "%"));
		}
		if(!pdlDTO.getNombrePlan().equals("") && pdlDTO.getNombrePlan() != null){
            predicates.add(cb.like(cb.lower(pdl.get("nombrePlan").as(String.class)),
                    "%" + pdlDTO.getNombrePlan().toLowerCase() + "%"));
        }
		if (!pdlDTO.getNombreAlcaldeLocal().equals("") && pdlDTO.getNombreAlcaldeLocal() != null) {
			predicates.add(cb.like(cb.lower(pdl.get("nombreAlcaldeLocal").as(String.class)),
					"%" + pdlDTO.getNombreAlcaldeLocal().toLowerCase() + "%"));
		}
		if (!pdlDTO.getVigencia().equals("") && pdlDTO.getVigencia() != null) {
			String[] anios = pdlDTO.getVigencia().split("-");

			if (anios.length == 2) {
				predicates.add(cb.like(cb.lower(pdl.get("yearInicio").as(String.class)), anios[0].trim()));

				predicates.add(cb.like(cb.lower(pdl.get("yearFinal").as(String.class)), anios[1].trim()));
			} else {
				predicates.add(cb.like(cb.lower(pdl.get("yearInicio").as(String.class)), "%" + anios[0].trim() + "%"));
			}
		}
		if (pdlDTO.getYearInicio() != null) {
			predicates.add(cb.like(cb.lower(pdl.get("yearInicio").as(String.class)),
					"%" + pdlDTO.getYearInicio().toLowerCase() + "%"));
		}
		if (pdlDTO.getYearFinal() != null) {
			predicates.add(cb.like(cb.lower(pdl.get("yearFinal").as(String.class)),
					"%" + pdlDTO.getYearFinal().toLowerCase() + "%"));
		}
		if (!pdlDTO.getEstadoPlan().equals("") && pdlDTO.getEstadoPlan() != null) {
			predicates.add(cb.like(cb.lower(pdl.get("idLsEstadoPlan").get("resultado").as(String.class)),
					"%" + pdlDTO.getEstadoPlan().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		if (pdlDTO.getColumnaOrdenar() != null) {
			if (pdlDTO.getTipoOrden().equals("asc")) {
				if (!pdlDTO.getColumnaOrdenar().equalsIgnoreCase("vigencia")) {
					if (!pdlDTO.getColumnaOrdenar().equalsIgnoreCase("idLsEstadoPlan"))
						cq.orderBy(cb.asc(pdl.get(pdlDTO.getColumnaOrdenar())));
					else
						cq.orderBy(cb.asc(pdl.get(pdlDTO.getColumnaOrdenar()).get("resultado")));

				} else {
					cq.orderBy(cb.asc(pdl.get("yearInicio")), cb.asc(pdl.get("yearFinal")));
				}
			} else {
				if (!pdlDTO.getColumnaOrdenar().equalsIgnoreCase("vigencia")) {
					if (!pdlDTO.getColumnaOrdenar().equalsIgnoreCase("idLsEstadoPlan"))
						cq.orderBy(cb.desc(pdl.get(pdlDTO.getColumnaOrdenar())));
					else
						cq.orderBy(cb.desc(pdl.get(pdlDTO.getColumnaOrdenar()).get("resultado")));
				} else {
					cq.orderBy(cb.desc(pdl.get("yearInicio")), cb.desc(pdl.get("yearFinal")));
				}
			}
		} else {
			cq.orderBy(cb.desc(pdl.get("idPlanLocal")));
		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Pdl.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<Pdl> obtenerPdlsPorEstado(Long idEstado) throws SpddExceptions {
		return pdlRepo.obtenerPorEstado(idEstado);
	}

	@Override
	public List<Pdl> obtenerPorLsEstadoPlanYCodigoEntidad(String resultado, String codigoEntidad) {
		return pdlRepo.obtenerPorLsEstadoPlanYCodigoEntidad(resultado, codigoEntidad);
	}

}
