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
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoliticaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvPoliticaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvPolitica
 * 
 * @author DANIEL
 * @version 1.0 06/06/2020
 */
@Service
public class BpProyInvPoliticaRepo extends OperacionesBasicasFacade<BpProyInvPolitica, Long> implements IBpProyInvPoliticaServiceRepo, Serializable {

	@Autowired
	IBpProyInvPoliticaRepo bpProyInvPoliticaRepository;
	
	@PersistenceContext
	transient EntityManager em;
	
	
	@Override
	public CrudRepository<BpProyInvPolitica, Long> getRepo() {
		return bpProyInvPoliticaRepository;
	}
	
	
	@Override
	public FiltroDTO obtenerTodosFiltrado(BpProyInvPoliticaDTO bpProyInvPoliticaDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BpProyInvPolitica> cq = cb.createQuery(BpProyInvPolitica.class);
		Root<BpProyInvPolitica> bpProyInvPolitica = cq.from(BpProyInvPolitica.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (bpProyInvPoliticaDTO.getIdProyPolitica() != null) {
			predicates.add(cb.equal(bpProyInvPolitica.get("idProyPolitica").as(String.class), 
					bpProyInvPoliticaDTO.getIdProyPolitica()));
		}
		
		if (bpProyInvPoliticaDTO.getIdPolPub() != null) {
			predicates.add(cb.equal(bpProyInvPolitica.get("idPolPub").get("idPolPub").as(String.class), 
					bpProyInvPoliticaDTO.getIdPolPub()));
		}
		
		if (bpProyInvPoliticaDTO.getStringPolPub() != null && !"".equals(bpProyInvPoliticaDTO.getStringPolPub())) {
			predicates.add(cb.like(cb.lower(bpProyInvPolitica.get("idPolPub").get("politica").as(String.class)),
					"%" + bpProyInvPoliticaDTO.getStringPolPub().toLowerCase() + "%"));
		}
		
		if (bpProyInvPoliticaDTO.getIdProyInversion() != null) {
			predicates.add(cb.equal(bpProyInvPolitica.get("idProyInversion").get("idProyInversion").as(String.class), 
					bpProyInvPoliticaDTO.getIdProyInversion()));
		}		
		
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (bpProyInvPoliticaDTO.getColumnaOrdenar() != null) {
			if (bpProyInvPoliticaDTO.getTipoOrden().equals("asc")) {
				if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idProyPolitica"))
				{
					cq.orderBy(cb.asc(bpProyInvPolitica.get("idProyPolitica")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idPolPub"))
				{
					cq.orderBy(cb.asc(bpProyInvPolitica.get("idPolPub").get("idPolPub")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("stringPolPub"))
				{
					cq.orderBy(cb.asc(bpProyInvPolitica.get("idPolPub").get("politica")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idProyInversion"))
				{
					cq.orderBy(cb.asc(bpProyInvPolitica.get("idProyInversion").get("idProyInversion")));
				}				
			} else {
				if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idProyPolitica"))
				{
					cq.orderBy(cb.desc(bpProyInvPolitica.get("idProyPolitica")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idPolPub"))
				{
					cq.orderBy(cb.desc(bpProyInvPolitica.get("idPolPub").get("idPolPub")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("stringPolPub"))
				{
					cq.orderBy(cb.desc(bpProyInvPolitica.get("idPolPub").get("politica")));
				}
				else if(bpProyInvPoliticaDTO.getColumnaOrdenar().equals("idProyInversion"))
				{
					cq.orderBy(cb.desc(bpProyInvPolitica.get("idProyInversion").get("idProyInversion")));
				}
			}
		} else {
			cq.orderBy(cb.asc(bpProyInvPolitica.get("idProyPolitica")));
		}		
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(BpProyInvPolitica.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;		
	}


	@Override
	public BpProyInvPolitica obtenerPorIdPolPubYIdProyInversion(Long idPolPub, Long idProyInversion) {
		return bpProyInvPoliticaRepository.obtenerPorIdPolPubYIdProyInversion(idPolPub, idProyInversion);
	}

	

}
