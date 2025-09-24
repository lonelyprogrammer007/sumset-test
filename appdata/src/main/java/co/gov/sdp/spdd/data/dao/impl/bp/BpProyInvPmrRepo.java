package co.gov.sdp.spdd.data.dao.impl.bp;

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
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPmrServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvPmrRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvPmr
 * 
 * @author DANIEL
 * @version 1.0 06/06/2020
 */
@Service
public class BpProyInvPmrRepo extends OperacionesBasicasFacade<BpProyInvPmr, Long> implements IBpProyInvPmrServiceRepo, Serializable {


	@Autowired
	IBpProyInvPmrRepo bpProyInvPmrRepository;
	
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<BpProyInvPmr, Long> getRepo() {
		return bpProyInvPmrRepository;
	}

	@Override
	public FiltroDTO obtenerTodosFiltrado(BpProyInvPmrDTO bpProyInvPmrDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BpProyInvPmr> cq = cb.createQuery(BpProyInvPmr.class);
		Root<BpProyInvPmr> bpProyInvPmr = cq.from(BpProyInvPmr.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (bpProyInvPmrDTO.getIdProyPmr() != null) {
			predicates.add(cb.equal(bpProyInvPmr.get("idProyPmr").as(String.class), 
					bpProyInvPmrDTO.getIdProyPmr()));
		}
		
		if (bpProyInvPmrDTO.getIdIndMr() != null) {
			predicates.add(cb.equal(bpProyInvPmr.get("idIndMr").get("idIndProxy").as(String.class), 
					bpProyInvPmrDTO.getIdIndMr()));
		}
		
		if (bpProyInvPmrDTO.getIdIndMrIdIndicador() != null) {
			predicates.add(cb.equal(bpProyInvPmr.get("idIndMr").get("idIndicador").get("idIndicador").as(String.class), 
					bpProyInvPmrDTO.getIdIndMrIdIndicador()));
		}
		
		if (bpProyInvPmrDTO.getIdIndMrStringIndicador() != null && !"".equals(bpProyInvPmrDTO.getIdIndMrStringIndicador())) {
			predicates.add(cb.like(cb.lower(bpProyInvPmr.get("idIndMr").get("idIndicador").get("nombre").as(String.class)),
					"%" + bpProyInvPmrDTO.getIdIndMrStringIndicador().toLowerCase() + "%"));
		}
		
		if (bpProyInvPmrDTO.getIdIndMrIdMetaResultadol() != null) {
			predicates.add(cb.equal(bpProyInvPmr.get("idIndMr").get("idMetaResultado").get("idMetaResultado").as(String.class), 
					bpProyInvPmrDTO.getIdIndMrIdMetaResultadol()));
		}
		
		if (bpProyInvPmrDTO.getIdProyInversion() != null) {
			predicates.add(cb.equal(bpProyInvPmr.get("idProyInversion").get("idProyInversion").as(String.class), 
					bpProyInvPmrDTO.getIdProyInversion()));
		}		
		
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (bpProyInvPmrDTO.getColumnaOrdenar() != null) {
			if (bpProyInvPmrDTO.getTipoOrden().equals("asc")) {
				if(bpProyInvPmrDTO.getColumnaOrdenar().equals("idProyPmr"))
				{
					cq.orderBy(cb.asc(bpProyInvPmr.get("idProyPmr")));
				}
				else if(bpProyInvPmrDTO.getColumnaOrdenar().equals("idIndMrStringIndicador"))
				{
					cq.orderBy(cb.asc(bpProyInvPmr.get("idIndMr").get("idIndicador").get("nombre")));
				}
							
			} else {
				if(bpProyInvPmrDTO.getColumnaOrdenar().equals("idProyPmr"))
				{
					cq.orderBy(cb.desc(bpProyInvPmr.get("idProyPmr")));
				}
				else if(bpProyInvPmrDTO.getColumnaOrdenar().equals("idIndMrStringIndicador"))
				{
					cq.orderBy(cb.desc(bpProyInvPmr.get("idIndMr").get("idIndicador").get("nombre")));
				}
			}
		} else {
			cq.orderBy(cb.asc(bpProyInvPmr.get("idProyPmr")));
		}	
		
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(BpProyInvPmr.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;	
	}

	@Override
	public BpProyInvPmr obtenerPorIdIndMrYIdProyInversion(Long idIndMr, Long idProyInversion) {
		return bpProyInvPmrRepository.obtenerPorIdIndMrYIdProyInversion(idIndMr, idProyInversion);
	}

}
