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
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.ITerritorializacionRepo;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * @author Bryan Munoz
 *
 */
@Transactional
@Service
public class TerritorializacionRepo extends OperacionesBasicasFacade<Territorializacion, Long>
		implements ITerritorializacionServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	ITerritorializacionRepo territorializacionRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<Territorializacion, Long> getRepo() {
		return territorializacionRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(TerritorializacionDTO territorializacionDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Territorializacion> cq = cb.createQuery(Territorializacion.class);
		Root<Territorializacion> territorializacion = cq.from(Territorializacion.class);
		List<Predicate> predicates = new ArrayList<>();
		if (territorializacionDTO.getIdTerritorializacion() != null) {
			predicates.add(cb.like(territorializacion.get("idTerritorializacion").as(String.class),
					"%" + territorializacionDTO.getIdTerritorializacion() + "%"));
		}
		if (territorializacionDTO.getEstado() != null) {
			predicates.add(cb.like(territorializacion.get("estado").as(String.class),
					"%" + territorializacionDTO.getEstado() + "%"));
		}
		if (territorializacionDTO.getNombreLocalidad() != null) {
			predicates.add(cb.like(
					cb.lower(territorializacion.get("idLsLocalidad").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + territorializacionDTO.getNombreLocalidad().toLowerCase() + "%"));
		}
		if (territorializacionDTO.getNombreUpz() != null) {
			predicates.add(cb.like(
					cb.lower(territorializacion.get("idLsUpz").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + territorializacionDTO.getNombreUpz().toLowerCase() + "%"));
		}
		if (territorializacionDTO.getNombreVereda() != null) {
			predicates.add(cb.like(
					cb.lower(territorializacion.get("idLsVereda").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + territorializacionDTO.getNombreVereda().toLowerCase() + "%"));
		}
		if (territorializacionDTO.getNombreBarrio() != null) {
			predicates.add(cb.like(
					cb.lower(territorializacion.get("idLsBarrio").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + territorializacionDTO.getNombreBarrio().toLowerCase() + "%"));
		}
		if (territorializacionDTO.getNombreUpr() != null) {
			predicates.add(cb.like(
					cb.lower(territorializacion.get("idLsUpr").get(NHSPDDConstantes.RESULTADO).as(String.class)),
					"%" + territorializacionDTO.getNombreUpr().toLowerCase() + "%"));
		}
		if (territorializacionDTO.getColumnaOrdenar().equals("estado")
				|| territorializacionDTO.getColumnaOrdenar().equals("idTerritorializacion")) {
			if (territorializacionDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(territorializacion.get(territorializacionDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(territorializacion.get(territorializacionDTO.getColumnaOrdenar())));
			}

		} else {
			if (territorializacionDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(territorializacion.get(territorializacionDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			} else {
				cq.orderBy(cb.desc(territorializacion.get(territorializacionDTO.getColumnaOrdenar())
						.get(NHSPDDConstantes.RESULTADO)));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(Territorializacion.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public Territorializacion buscarPorLsBarrioYLsUpzYLsLocalidad(Long idLsBarrio, Long idLsUpz, Long idLsLocalidad)
			throws SpddExceptions {
		return territorializacionRepository.findByIdLsBarrioAndIdLsUpzAndIdLsLocalidad(idLsBarrio, idLsUpz,
				idLsLocalidad);
	}

	/**
	 * 
	 */
	@Override
	public Territorializacion buscarPorLsVeredaYLsUpr(Long idLsVereda, Long idLsUpr, Long idLsLocalidad)
			throws SpddExceptions {
		return territorializacionRepository.findByIdLsVeredaAndIdLsUpr(idLsVereda, idLsUpr, idLsLocalidad);
	}

	/**
	 * 
	 */
	@Override
	public List<Territorializacion> buscarPorLocalidad(String localidad) throws SpddExceptions {
		return territorializacionRepository.obtenerPorLocalidad(localidad);
	}

	@Override
	public List<Territorializacion> obtenerPorIdLocalidad(Long idLocalidad) throws SpddExceptions {
		
		return territorializacionRepository.obtenerPorIdLocalidad(idLocalidad);
	}
}
