/**
 * 
 */
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
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddProblematicaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddProblematicaRepo;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * @author Jose Alvaro
 *
 */
@Transactional
@Service
public class PddProblematicaServiceRepo extends OperacionesBasicasFacade<PddProblematica, Long>
		implements IPddProblematicaServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPddProblematicaRepo problematicaRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/*
	 * 
	 */
	@Override
	public CrudRepository<PddProblematica, Long> getRepo() {
		return problematicaRepository;
	}

	/*
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(PddProblematicaDTO argumento)
			throws SpddExceptions {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PddProblematica> cq = cb.createQuery(PddProblematica.class);
		Root<PddProblematica> pddProblematica = cq.from(PddProblematica.class);
		List<Predicate> predicates = new ArrayList<>();

		if (argumento.getIdProblematica() != null) {
			predicates.add(cb.like(pddProblematica.get("idProblematica").as(String.class),
					"%" + argumento.getIdProblematica() + "%"));
		}

		if (argumento.getIdCompromiso() != null) {
			predicates.add(cb.like(pddProblematica.get("idCompromiso").get("idCompromiso").as(String.class),
					"%" + argumento.getIdCompromiso() + "%"));
		}

		if (argumento.getIdLsLocalizacion() != null) {
			predicates.add(cb.like(pddProblematica.get("idLsLocalizacion").get("idArgumento").as(String.class),
					"%" + argumento.getIdLsLocalizacion() + "%"));
		}
		
		if (argumento.getLocalizacion() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("idLsLocalizacion").get("resultado")),
					"%" + argumento.getLocalizacion().toLowerCase() + "%"));
		}
		
		if (argumento.getIdLsSublocalizacion() != null) {
			predicates.add(cb.like(pddProblematica.get("idLsSublocalizacion").get("idArgumento").as(String.class),
					"%" + argumento.getIdLsSublocalizacion() + "%"));
		}
		
		if (argumento.getSubLocalizacion() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("idLsSublocalizacion").get("resultado")),
					"%" + argumento.getSubLocalizacion().toLowerCase() + "%"));
		}
		
		if (argumento.getIdLzUpzUpr() != null) {
			predicates.add(cb.like(pddProblematica.get("idLzUpzUpr").get("idArgumento").as(String.class),
					"%" + argumento.getIdLzUpzUpr() + "%"));
		}
		
		if (argumento.getUpzUpr() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("idLzUpzUpr").get("resultado")),
					"%" + argumento.getUpzUpr().toLowerCase() + "%"));
		}
		
		if (argumento.getCausas() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("causas")),
					"%" + argumento.getCausas().toLowerCase() + "%"));
		}
		
		if (argumento.getConsecuencias() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("consecuencias")),
					"%" + argumento.getConsecuencias().toLowerCase() + "%"));
		}
		
		if (argumento.getDescripcion() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("descripcion")),
					"%" + argumento.getDescripcion().toLowerCase() + "%"));
		}
		
		if (argumento.getObjetivo() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("objetivo")),
					"%" + argumento.getObjetivo().toLowerCase() + "%"));
		}
		
		if (argumento.getProblematica() != null) {
			predicates.add(cb.like(cb.lower(pddProblematica.get("problematica")),
					"%" + argumento.getProblematica().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(em.createQuery(cq).getResultList());
		return res;
	}

	@Override
	public PddProblematica validarLlaveDeNegocio(Long idCompromiso, String problematica) throws SpddExceptions {
		return problematicaRepository.validarIdCompromisoYProblematica(idCompromiso, problematica);
	}

	@Override
	public List<PddProblematica> consultarPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		
		return problematicaRepository.consultarPorIdCompromiso(idCompromiso);
	}

}
