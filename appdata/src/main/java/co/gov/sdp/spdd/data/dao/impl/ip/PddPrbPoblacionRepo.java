package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbPoblacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddPrbPoblacionRepo;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddPrbPoblacionRepo extends OperacionesBasicasFacade<PddPrbPoblacion, Long>
		implements IPddPrbPoblacionServiceRepo, Serializable {

	@Autowired
	IPddPrbPoblacionRepo poblacionRepositorio;

	@Autowired
	transient EntityManager em;
	
	@Override
	public CrudRepository<PddPrbPoblacion, Long> getRepo() {

		return poblacionRepositorio;
	}

	@Override
	public PddPrbPoblacion validarReglasPoblacion(String descripcion, Long idProblematica) throws SpddExceptions {
	
		return poblacionRepositorio.buscarPorDescripcionYProblematica(descripcion, idProblematica);
	}

	@Override
	public FiltroDTO obtenerPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddPrbPoblacion> cq = cb.createQuery(PddPrbPoblacion.class);
		Root<PddPrbPoblacion> componenteRoot = cq.from(PddPrbPoblacion.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (peticion.getIdProblematica() != null) {
			predicates.add(cb.equal(componenteRoot.get("idProblematica").get("idProblematica").as(String.class),
					  peticion.getIdProblematica()));
		}
		if (peticion.getDescripcion() != null && !peticion.getDescripcion().equals("")) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("descripcion").as(String.class)),
					"%" + peticion.getDescripcion() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		        res.setResultadoConsulta(
		                em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		        CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		        cq2.select(cb.count(cq2.from(PddPrbPoblacion.class)));
		        cq2.where(predicates.toArray(new Predicate[0]));
		        res.setContador(em.createQuery(cq2).getSingleResult());
		        return res;

	
	}

	

	

	

}
