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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPoliticaPublicaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddPoliticaPublicaRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad PddNivel
 * @author DANIEL
 * @version 1.0 03/03/2020
 */
@Service
public class PddPoliticaPublicaRepo extends OperacionesBasicasFacade<PddPoliticaPublica, Long> implements IPddPoliticaPublicaServiceRepo, Serializable {

	@Autowired
	IPddPoliticaPublicaRepo pddPoliticaPublicaRepository;

	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<PddPoliticaPublica, Long> getRepo() {
		return pddPoliticaPublicaRepository;
	}

	@Override
	public FiltroDTO obtenerTodosFiltrado(PddPoliticaPublicaDTO pddPoliticaPublicadDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddPoliticaPublica> cq = cb.createQuery(PddPoliticaPublica.class);
		Root<PddPoliticaPublica> pddPoliticaPublica = cq.from(PddPoliticaPublica.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (pddPoliticaPublicadDTO.getIdPolPub() != null) {
			predicates.add(
					cb.equal(pddPoliticaPublica.get("idPolPub").as(String.class), 
							pddPoliticaPublicadDTO.getIdPolPub() ));
		}
		if (pddPoliticaPublicadDTO.getCodPolitica() != null && !"".equals(pddPoliticaPublicadDTO.getCodPolitica())) {
			predicates.add(cb.like(cb.lower(pddPoliticaPublica.get("codPolitica").as(String.class)),
					"%" + pddPoliticaPublicadDTO.getCodPolitica().toLowerCase() + "%"));
		}
		if (pddPoliticaPublicadDTO.getPolitica() != null && !"".equals(pddPoliticaPublicadDTO.getPolitica())) {
			predicates.add(cb.like(cb.lower(pddPoliticaPublica.get("politica").as(String.class)),
					"%" + pddPoliticaPublicadDTO.getPolitica().toLowerCase() + "%"));
		}
		if (pddPoliticaPublicadDTO.getIdPlanDesarrollo() != null) {
			predicates.add(
					cb.equal(pddPoliticaPublica.get("idPlanDesarrollo").get("idPlanDesarrollo").as(String.class), 
							pddPoliticaPublicadDTO.getIdPlanDesarrollo() ));
		}
		if (pddPoliticaPublicadDTO.getStringPlanDesarrollo() != null) {
			predicates.add(
					cb.equal(pddPoliticaPublica.get("idPlanDesarrollo").get("nombrePlan").as(String.class), 
							pddPoliticaPublicadDTO.getIdPlanDesarrollo() ));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (pddPoliticaPublicadDTO.getColumnaOrdenar() != null) {
			if (pddPoliticaPublicadDTO.getTipoOrden().equals("asc")) {
				if (pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("idPolPub") ||
						pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("codPolitica") ||
						pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("politica")) {
					cq.orderBy(cb.asc(pddPoliticaPublica.get(pddPoliticaPublicadDTO.getColumnaOrdenar())));
				} else if (pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("idPlanDesarrollo")) {
					cq.orderBy(cb.asc(pddPoliticaPublica.get("idPlanDesarrollo").get("idPlanDesarrollo")));
				}
				else if(pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("stringPlanDesarrollo"))
				{
					cq.orderBy(cb.asc(pddPoliticaPublica.get("idPlanDesarrollo").get("nombrePlan")));
				}
			} else {
				if (pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("idPolPub") ||
						pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("codPolitica") ||
						pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("politica")) {
					cq.orderBy(cb.desc(pddPoliticaPublica.get(pddPoliticaPublicadDTO.getColumnaOrdenar())));
				} else if (pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("idPlanDesarrollo")) {
					cq.orderBy(cb.desc(pddPoliticaPublica.get("idPlanDesarrollo").get("idPlanDesarrollo")));
				}
				else if(pddPoliticaPublicadDTO.getColumnaOrdenar().equalsIgnoreCase("stringPlanDesarrollo"))
				{
					cq.orderBy(cb.desc(pddPoliticaPublica.get("idPlanDesarrollo").get("nombrePlan")));
				}
			}
		} else {
			cq.orderBy(cb.asc(pddPoliticaPublica.get("idPolPub")));
		}
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddPoliticaPublica.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<PddPoliticaPublica> obtenerTodosSinRelacionConProyectoInversion(Long idProyInversion) {
		return pddPoliticaPublicaRepository.obtenerTodosSinRelacionConProyectoInversion(idProyInversion);
	}

	@Override
	public List<PddPoliticaPublica> obtenerTodosOrdenadosPorNombrePolitica() {
		return pddPoliticaPublicaRepository.obtenerTodosOrdenadosPorNombrePolitica();
	}

}
