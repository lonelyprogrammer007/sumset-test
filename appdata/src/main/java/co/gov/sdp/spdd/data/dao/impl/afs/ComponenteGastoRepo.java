package co.gov.sdp.spdd.data.dao.impl.afs;

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

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGastoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGastoRepo;
import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ComponenteGastoRepo extends OperacionesBasicasFacade<ComponenteGasto, Long>
		implements IComponenteGastoServiceRepo, Serializable {
	/**
	 * 
	 */
	@Autowired
	IComponenteGastoRepo componenteGastoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ComponenteGasto, Long> getRepo() {
		return componenteGastoRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ComponenteGastoDTO componente, Long inicio, Integer limite) throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ComponenteGasto> cq = cb.createQuery(ComponenteGasto.class);
		Root<ComponenteGasto> componenteRoot = cq.from(ComponenteGasto.class);
		List<Predicate> predicates = new ArrayList<>();
		if (componente.getCodigoComponente() != null) {
			predicates
					.add(cb.like((componenteRoot.get("codigoComponente").as(String.class)), "%" + componente.getCodigoComponente() + "%"));
		}
		if (componente.getNombreComponente() != null) {
			predicates
					.add(cb.like(cb.lower(componenteRoot.get("nombreComponente")), "%" + componente.getNombreComponente().toLowerCase() + "%"));
		
		}
		if (componente.getEstado() != null) {
			predicates.add(cb.like(componenteRoot.get("estado").as(String.class), "%" + componente.getEstado() + "%"));
		}
		if (componente.getTipoProyecto() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLsTipoProyecto").get(NHSPDDConstantes.RESULTADO).as(String.class)), "%" + componente.getTipoProyecto().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		
		if (componente.getColumnaOrdenar().equals("estado") || 
				componente.getColumnaOrdenar().equals("codigoComponente") || 
				componente.getColumnaOrdenar().equals("nombreComponente")) {
			if (componente.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(componenteRoot.get(componente.getColumnaOrdenar())));
			}
			else {
				cq.orderBy(cb.desc(componenteRoot.get(componente.getColumnaOrdenar())));
			}
		} else {
			if (componente.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(componenteRoot.get(componente.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
			 else {
					cq.orderBy(cb.desc(componenteRoot.get(componente.getColumnaOrdenar())
							.get(NHSPDDConstantes.RESULTADO)));
				}
	}
		
	
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ComponenteGasto.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public ComponenteGasto buscarPorCodigoYNombre(Long codigo, String nombre) {	
		return componenteGastoRepository.obtenerCodigoYNombre(codigo, nombre.toLowerCase());
	}

}
