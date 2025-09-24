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
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompromisoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddCompromisoRepo;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository de la entidad pddCompromiso
 * @author DANIEL
 * @version 1.0 11/03/2020
 */
@Transactional
@Service
public class PddCompromisoRepo extends OperacionesBasicasFacade<PddCompromiso, Long> implements IPddCompromisoServiceRepo, Serializable {
	
	/**
	 * Inyecccion del repo correspondiente
	 */
	@Autowired
	IPddCompromisoRepo pddCompromisoRepository;
	
	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	/**
	 * Metodo que permite retornar el Reposity correspondiente a la entidad pddCompromiso
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad pddCompromiso
	 */
	@Override
	public CrudRepository<PddCompromiso, Long> getRepo() {
		return pddCompromisoRepository;
	}
	
	@Override
	public FiltroDTO filtrarPorCampo(PddCompromisoDTO argumento)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddCompromiso> cq = cb.createQuery(PddCompromiso.class);
		Root<PddCompromiso> componenteRoot = cq.from(PddCompromiso.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (argumento.getIdCompromiso() != null) {
			predicates.add(cb.equal(componenteRoot.get("idCompromiso").as(String.class), argumento.getIdCompromiso().toString() ));
		}
		
		if(argumento.getIdEstrategico() != null)
		{
			predicates.add(cb.equal(componenteRoot.get("idEstrategico").get("idCompromiso").as(String.class), argumento.getIdEstrategico().toString() ));
		}
		
		if(argumento.getIdPlanDesarrollo() != null)
		{
			predicates.add(cb.equal(componenteRoot.get("idPlanDesarrollo").get("idPlanDesarrollo").as(String.class), argumento.getIdPlanDesarrollo().toString() ));
		}
		
		if(argumento.getIdTematica() != null)
		{
			predicates.add(cb.equal(componenteRoot.get("idEstrategico").get("idLsTematica").get("idArgumento").as(String.class), argumento.getIdTematica()));
		}
		
		if(argumento.getNombreTematica() != null)
		{
			predicates.add(cb.like(cb.lower(componenteRoot.get("idEstrategico").get("idLsTematica").get("resultado").as(String.class)), "%"+ argumento.getNombreTematica().toLowerCase() + "%"));
		}
		
		if(argumento.getIdLsEstrategico() != null)
		{
			predicates.add(cb.equal(componenteRoot.get("idEstrategico").get("idLsEstrategico").get("idArgumento").as(String.class), argumento.getIdTematica()));
		}
		
		if (argumento.getNombreCompromisoEstrategico() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idEstrategico").get("idLsEstrategico").get("resultado").as(String.class)),  "%"+ argumento.getNombreCompromisoEstrategico().toLowerCase() + "%"));
		}		
		
		if(argumento.getNombrePlan() != null)
		{
			predicates.add(cb.equal(cb.lower(componenteRoot.get("idPlanDesarrollo").get("nombrePlan").as(String.class)), argumento.getNombrePlan().toLowerCase() ));
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		
		//Ordenamiento
		/* 
		if(argumento.getColumnaOrdenar()!=null) {
			if(argumento.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(componenteRoot.get(argumento.getColumnaOrdenar())));
			}else {
				cq.orderBy(cb.desc(componenteRoot.get(argumento.getColumnaOrdenar())));
			}
		}else {
			cq.orderBy(cb.desc(componenteRoot.get("idArgumento")));
		}
		*/
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(em.createQuery(cq).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddCompromiso.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}
	
	@Override
	public List<PddCompromiso> obtenerTodosPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		return pddCompromisoRepository.obtenerPorIdPlanDesarrollo(idPlan);
	}

	@Override
	public PddCompromiso obtenerPorIdEstrategicoYIdPlanDesarrollo(Long idEstrategico, Long idPlan) throws SpddExceptions {
		return pddCompromisoRepository.obtenerPorIdEstrategicoYIdPlanDesarrollo(idEstrategico, idPlan);
	}

	@Override
	public PddCompromiso obtenerPorID(Long idCompromiso) throws SpddExceptions {
		return pddCompromisoRepository.obtenerPorId(idCompromiso);
	}

}
