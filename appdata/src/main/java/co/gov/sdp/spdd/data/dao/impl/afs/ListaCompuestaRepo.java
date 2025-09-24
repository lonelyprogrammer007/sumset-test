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
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaCompuestaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaCompuestaRepo;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class ListaCompuestaRepo extends OperacionesBasicasFacade<ListaCompuesta, Long>
        implements IListaCompuestaServiceRepo, Serializable {

	/**
	 * 
	 */
    @Autowired
    IListaCompuestaRepo listaCompuestaRepository;
    
	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

    /**
     * 
     */
    @Override
    public CrudRepository<ListaCompuesta, Long> getRepo() {
        return listaCompuestaRepository;
    }
    
	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(ListaCompuestaDTO listaCompuestaDTO, Long inicio, Integer limite) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ListaCompuesta> cq = cb.createQuery(ListaCompuesta.class);
		Root<ListaCompuesta> listaCompuesta = cq.from(ListaCompuesta.class);
		List<Predicate> predicates = new ArrayList<>();
		if (listaCompuestaDTO.getIdListaCompuesta() != null) {
			predicates.add(cb.like(listaCompuesta.get("idListaCompuesta").as(String.class), "%" + listaCompuestaDTO.getIdListaCompuesta() + "%"));
		}
		if (listaCompuestaDTO.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(listaCompuesta.get("descripcion")), "%" + listaCompuestaDTO.getDescripcion().toLowerCase() + "%"));
		}
		if (listaCompuestaDTO.getNombre() != null) {
			predicates.add(cb.like(cb.lower(listaCompuesta.get("nombre")), "%" + listaCompuestaDTO.getNombre().toLowerCase() + "%"));
		}
		if (listaCompuestaDTO.getTabla() != null) {
			predicates.add(cb.like(cb.lower(listaCompuesta.get("tabla")), "%" + listaCompuestaDTO.getTabla().toLowerCase() + "%"));
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		if(listaCompuestaDTO.getColumnaOrdenar()!=null) {
			if(listaCompuestaDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(listaCompuesta.get(listaCompuestaDTO.getColumnaOrdenar())));
			}else {
				cq.orderBy(cb.desc(listaCompuesta.get(listaCompuestaDTO.getColumnaOrdenar())));
			}
		}
		
		
		
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(ListaCompuesta.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

}
