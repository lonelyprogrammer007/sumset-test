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
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaProductoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMetaProductoRepo;
import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMetaProductoRepo extends OperacionesBasicasFacade<PddMetaProducto, Long>
		implements IPddMetaProductoServiceRepo, Serializable {
	@Autowired
	IPddMetaProductoRepo metaProductoRepositorio;

	@PersistenceContext
	transient EntityManager em;

	@Override
	public CrudRepository<PddMetaProducto, Long> getRepo() {
		return metaProductoRepositorio;
	}

	@Override
	public List<PddMetaProducto> obtenerPorMetaResultado(Long idMetaResultado) throws SpddExceptions {
		return metaProductoRepositorio.obtenerPorMetaResultado(idMetaResultado);
	}

	@Override
	public FiltroDTO obtenerTodosMetaProductos(Long idMetaResultado, Long inicio, Integer limite)
			throws SpddExceptions {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddMetaProducto> cq = cb.createQuery(PddMetaProducto.class);
		Root<PddMetaProducto> componenteRoot = cq.from(PddMetaProducto.class);
		List<Predicate> predicates = new ArrayList<>();
		if (idMetaResultado != null) {
			predicates.add((cb.equal(componenteRoot.get("idMetaResultado").get("idMetaResultado"), idMetaResultado)));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddMetaProducto.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<PddMetaProducto> obtenerTodosDesbalanceados(Long idPlanDesarrollo) {
		return metaProductoRepositorio.obtenerTodosDesbalanceados(idPlanDesarrollo);
	}

	@Override
	public Long sumarPonderacionesMetaProducto(Long idMetaResultado) throws SpddExceptions {
		return metaProductoRepositorio.sumarPonderacionesMetaProducto(idMetaResultado);
	}

	@Override
	public List<PddMetaProducto> obtenerTodosPorIdMetaResultado(Long idMetaResultado) {
		return metaProductoRepositorio.obtenerPorMetaResultado(idMetaResultado);
	}

	@Override
	public List<PddMetaProducto> obtenerTodosOrdenadosASC() {
		return metaProductoRepositorio.obtenerTodosOrdenadosASC();
	}

}
