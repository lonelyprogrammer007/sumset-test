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
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompromisoEspecificoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddCompromisoEspecificoRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion de Repository.
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Service
public class PddCompromisoEspecificoRepo extends OperacionesBasicasFacade<PddCompromisoEspecifico, Long> implements IPddCompromisoEspecificoServiceRepo, Serializable {

	/**
	 * Inyeccion del repo
	 */
	@Autowired
	IPddCompromisoEspecificoRepo pddComromisoEspecificoRepository;	
	
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<PddCompromisoEspecifico, Long> getRepo() {
		return pddComromisoEspecificoRepository;
	}

	@Override
	public PddCompromisoEspecifico obtenerPorIdCompromisoYDescripcion(Long idCompromiso, String descripcion) throws SpddExceptions {
		return pddComromisoEspecificoRepository.obtenerPorIdCompromisoYDescripcion(idCompromiso, descripcion);
	}

	@Override
	public List<PddCompromisoEspecifico> obtenerPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		return pddComromisoEspecificoRepository.obtenerPorIdCompromiso(idCompromiso);
	}

	@Override
	public FiltroDTO filtrarCompromisoPorCampo(PddCompromisoEspecificoDTO peticion,Long inicio,Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddCompromisoEspecifico> cq = cb.createQuery(PddCompromisoEspecifico.class);
		Root<PddCompromisoEspecifico> root = cq.from(PddCompromisoEspecifico.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if(peticion.getIdEspecifico() != null) {
			predicates.add(cb.equal(root.get("idEspecifico").as(String.class),
					peticion.getIdEspecifico()));
		}
		
		if(peticion.getIdCompromiso() != null) {
			predicates.add(cb.equal(root.get("idCompromiso").get("idCompromiso").as(String.class),
					peticion.getIdCompromiso()));
		}
		
		if(peticion.getDescripcion() !=null && !"".equals(peticion.getDescripcion())) {
			predicates.add(cb.like(cb.lower(root.get("descripcion").as(String.class)),
					"%" + peticion.getDescripcion() + "%"));
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (peticion.getColumnaOrdenar() != null) {
			if (peticion.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(root.get(peticion.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(root.get(peticion.getColumnaOrdenar())));
			}
		} else {
			cq.orderBy(cb.asc(root.get("idEspecifico")));
		}
				
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddCompromisoEspecifico.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
		
		
	}
	
	

}
