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
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaResultadoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMetaResultadoRepo;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad PddMetaResultado
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
@Service
public class PddMetaResultadoRepo extends OperacionesBasicasFacade<PddMetaResultado, Long>
		implements IPddMetaResultadoServiceRepo, Serializable {

	/**
	 * Inyeccion de IPddMetaResultadoRepo
	 */
	@Autowired
	IPddMetaResultadoRepo pddMetaResultadoRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * Metodo que permite retornar el Reposity correspondiente a la entidad
	 * PddMetaRepository
	 * 
	 * @return retorna un objeto de tipo CrudRepository relacionado a la entidad
	 *         PddMetaRepository
	 */
	@Override
	public CrudRepository<PddMetaResultado, Long> getRepo() {
		return pddMetaResultadoRepository;
	}

	@Override
	public List<PddMetaResultado> consultarPorIDProblematicaIndicador(Long idProblematicaIndicador)
			throws SpddExceptions {
		return pddMetaResultadoRepository.obtenerPorIDProblematicaIndicador(idProblematicaIndicador);
	}

	@Override
	public FiltroDTO consultarPddPorProyectoEstrategico(PddMetaResultadoDTO peticion, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddMetaResultado> cq = cb.createQuery(PddMetaResultado.class);
		Root<PddMetaResultado> componenteRoot = cq.from(PddMetaResultado.class);
		List<Predicate> predicates = new ArrayList<>();
		if (peticion.getIdProyEstrategico() != null) {
			predicates.add((cb.equal(componenteRoot.get("idProyEstrategico").get("idAtributo"),
					peticion.getIdProyEstrategico())));
		}
		if (peticion.getIdProyEstrategicoLocal() != null) {
			predicates.add((cb.equal(componenteRoot.get("idProyEstrategicoLocal").get("idAtributo"),
					peticion.getIdProyEstrategicoLocal())));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(PddMetaResultado.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<PddMetaResultado> obtenerTodosDesbalanceados(Long idPlanDesarrollo) {
		return pddMetaResultadoRepository.obtenerTodosDesbalanceados(idPlanDesarrollo);
	}

	@Override
	public Long sumarPonderacionesMetaResultado(Long idProy) {
		return pddMetaResultadoRepository.sumarPonderacionesMetaResultado(idProy);
	}

	@Override
	public List<PddMetaResultado> obtenerTodosPorIdProyectoEstrategico(Long idProyecto) {
		return pddMetaResultadoRepository.obtenerTodosPorIdProyectoEstrategico(idProyecto);
	}

	@Override
	public List<PddMetaResultado> obtenerTodosOrdenadosASC() {
		return pddMetaResultadoRepository.obtenerTodosOrdenadosASC();
	}

}
