package co.gov.sdp.spdd.data.dao.impl.afs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IArgumentoListaSimpleRepo;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ArgumentoListaSimpleRepo extends OperacionesBasicasFacade<ArgumentoListaSimple, Long>
		implements IArgumentoListaSimpleServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	transient EntityManager em;
	
	/**
	 * 
	 */
	@Autowired
	IArgumentoListaSimpleRepo argumentoListaSimpleRepository;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ArgumentoListaSimple, Long> getRepo() {
		return argumentoListaSimpleRepository;
	}

    /**
     * 
     */
	@Override
	public List<ArgumentoListaSimple> obtenerArgumentoPorLista(Long id) throws SpddExceptions {
		return argumentoListaSimpleRepository.obtenerArgumentoPorLista(id);
	}

	/**
	 * 
	 */
	@Override
	public List<ArgumentoListaSimple> obtenerArgumentoPorNombre(String nombre) throws SpddExceptions {
		return argumentoListaSimpleRepository.obtenerArgumentoPorNombre(nombre);
	}
	
	/**
	 * 
	 */
	@Override
	public ArgumentoListaSimple buscarIdListaSimple(Long idListaSimple, String argumento) throws SpddExceptions {
		return argumentoListaSimpleRepository.findByIdListaSimple(idListaSimple, argumento.toLowerCase());
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ArgumentoListaSimpleDTO argumento, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ArgumentoListaSimple> cq = cb.createQuery(ArgumentoListaSimple.class);
		Root<ArgumentoListaSimple> componenteRoot = cq.from(ArgumentoListaSimple.class);
		List<Predicate> predicates = new ArrayList<>();
		if (argumento.getIdArgumento() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idArgumento").as(String.class)), "%" + argumento.getIdArgumento() + "%"));
		}
		if (argumento.getArgumento() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get(NHSPDDConstantes.ARGUMENTO)), "%" + argumento.getArgumento().toLowerCase() + "%"));
		}
		if (argumento.getIdListaSimple() != null) {
			predicates.add(cb.equal(componenteRoot.get("idListaSimple").get("idListaSimple").as(String.class), argumento.getIdListaSimple()));
		}
		if (argumento.getResultado() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("resultado")), "%" + argumento.getResultado().toLowerCase() + "%"));
		}
		if (argumento.getEstado() != null) {
			predicates.add(cb.like(componenteRoot.get("estado").as(String.class), "%" + argumento.getEstado() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		if(argumento.getColumnaOrdenar()!=null) {
			if(argumento.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(componenteRoot.get(argumento.getColumnaOrdenar())));
			}else {
				cq.orderBy(cb.desc(componenteRoot.get(argumento.getColumnaOrdenar())));
			}
		}else {
			cq.orderBy(cb.desc(componenteRoot.get("idArgumento")));
		}
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ArgumentoListaSimple.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<ArgumentoListaSimple> obtenerArgumentoPorIdPdd(Long idPlan) throws SpddExceptions {
		return argumentoListaSimpleRepository.obtenerArgumentoPorIdPdd(idPlan);
	}

	@Override
	public ArgumentoListaSimple obtenerPorId(Long idArgumento) throws SpddExceptions {
		return argumentoListaSimpleRepository.obtenerPorId(idArgumento);
	}

	@Override
	public ArgumentoListaSimple obtenerPorResultadoYNombreListaSimple(String resultado, String nombreListaSimple) throws SpddExceptions {
		return argumentoListaSimpleRepository.obtenerPorResultadoYNombreListaSimple(resultado, nombreListaSimple);
	}
}
