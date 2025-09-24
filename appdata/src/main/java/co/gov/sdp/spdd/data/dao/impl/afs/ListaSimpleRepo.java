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

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaSimpleRepo;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class ListaSimpleRepo extends OperacionesBasicasFacade<ListaSimple, Long> implements IListaSimpleServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IListaSimpleRepo listaSimpleRepository;
	
	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<ListaSimple, Long> getRepo() {
		return listaSimpleRepository;
	}
	
	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ListaSimpleDTO listaSimpleDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ListaSimple> cq = cb.createQuery(ListaSimple.class);
		Root<ListaSimple> listaSimple = cq.from(ListaSimple.class);
		List<Predicate> predicates = new ArrayList<>();
		if (listaSimpleDTO.getNombre() != null) {
			predicates.add(cb.like(cb.lower(listaSimple.get("nombre")), "%" + listaSimpleDTO.getNombre().toLowerCase() + "%"));
		}
		if (listaSimpleDTO.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(listaSimple.get("descripcion")), "%" + listaSimpleDTO.getDescripcion().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		if(listaSimpleDTO.getColumnaOrdenar()!=null) {
			if(listaSimpleDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(listaSimple.get(listaSimpleDTO.getColumnaOrdenar())));
			}else {
				cq.orderBy(cb.desc(listaSimple.get(listaSimpleDTO.getColumnaOrdenar())));
			}
		}else {
			cq.orderBy(cb.desc(listaSimple.get("idListaSimple")));
			
		}
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ListaSimple.class)));
		
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}
}
