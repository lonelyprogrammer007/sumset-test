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
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMRIndicadorServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMRIndicadorRepo;
import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMRIndicadorRepo extends OperacionesBasicasFacade<PddMRIndicador, Long>
		implements IPddMRIndicadorServiceRepo, Serializable {

	@Autowired
	IPddMRIndicadorRepo indicadorRepositorio;

	@PersistenceContext
	transient EntityManager em;

	@Override
	public CrudRepository<PddMRIndicador, Long> getRepo() {
		return indicadorRepositorio;
	}

	@Override
	public FiltroDTO obtenerTodosIndicadoresPorMetaResultado(Long idMetaResultado, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddMRIndicador> cq = cb.createQuery(PddMRIndicador.class);
		Root<PddMRIndicador> componenteRoot = cq.from(PddMRIndicador.class);
		List<Predicate> predicates = new ArrayList<>();
		if (idMetaResultado != null) {
			predicates.add((cb.equal(componenteRoot.get("idMetaResultado").get("idMetaResultado"), idMetaResultado)));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddMRIndicador.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public PddMRIndicador validarIndicadorMetaResultado(Long idIndicador, Long idMetaResultado) throws SpddExceptions {
		return indicadorRepositorio.validarIndicadorMetaResultado(idIndicador, idMetaResultado);
	}

	@Override
	public List<PddMRIndicador> obtenerTodosPorIdMetaResultado(Long idMetaResultado) {
		return indicadorRepositorio.obtenerTodosPorIdMetaResultado(idMetaResultado);
	}

	@Override
	public List<PddMRIndicador> obtenerTodosOrdenadosASC() {
		return indicadorRepositorio.obtenerTodosOrdenadosASC();
	}
	
	

}
