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

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IBuzonNotificacionesRepo;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;
import co.gov.sdp.spdd.data.utils.QueryUtils;

/**
 * Clase que implementa el facade
 *
 * @author Bryan Munoz
 *
 */
@Transactional
@Service
public class BuzonNotificacioneRepo extends OperacionesBasicasFacade<BuzonNotificaciones, Long>
		implements IBuzonNotificacioneServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IBuzonNotificacionesRepo buzonNotificacioneRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<BuzonNotificaciones, Long> getRepo() {
		return buzonNotificacioneRepository;
	}

	/**
	 * 
	 */
	@Override
	public List<BuzonNotificaciones> obtenerBuzonPorUsuario(String usuario) throws SpddExceptions {
		return buzonNotificacioneRepository.obtenerPorUsuario(usuario);
	}

	/**
	 * 
	 */
	@Override
	public Long notificacionesPorUsuario(String usuario, int estado) throws SpddExceptions {
		return buzonNotificacioneRepository.notificacionesPorUsuario(usuario, estado);
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(BuzonNotificacionesDTO buzonNotificacionesDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BuzonNotificaciones> cq = cb.createQuery(BuzonNotificaciones.class);
		Root<BuzonNotificaciones> buzonNotificaciones = cq.from(BuzonNotificaciones.class);
		List<Predicate> predicates = new ArrayList<>();
		if (buzonNotificacionesDTO.getIdNotificacion() != null) {
			predicates.add(cb.like(buzonNotificaciones.get("idNotificacion").as(String.class),
					"%" + buzonNotificacionesDTO.getIdNotificacion() + "%"));
		}
		if (buzonNotificacionesDTO.getMensaje() != null && !buzonNotificacionesDTO.getMensaje().equals("")) {
			predicates.add(cb.like(cb.lower(buzonNotificaciones.get("mensaje")),
					"%" + buzonNotificacionesDTO.getMensaje().toLowerCase() + "%"));
		}
		if (buzonNotificacionesDTO.getAsunto() != null && !buzonNotificacionesDTO.getAsunto().equals("")) {
			predicates.add(cb.like(cb.lower(buzonNotificaciones.get("asunto")),
					"%" + buzonNotificacionesDTO.getAsunto().toLowerCase() + "%"));
		}
		if (buzonNotificacionesDTO.getAdmin() == 1) {
			if (buzonNotificacionesDTO.getCodigoUsuarioDestino() != null
					&& !buzonNotificacionesDTO.getCodigoUsuarioDestino().equals("")) {
				predicates.add(cb.like(cb.lower(buzonNotificaciones.get("codigoUsuarioDestino")),
						"%" + buzonNotificacionesDTO.getCodigoUsuarioDestino().toLowerCase() + "%"));
			}
		} else {
			if (buzonNotificacionesDTO.getCodigoUsuarioDestino() != null
					&& !buzonNotificacionesDTO.getCodigoUsuarioDestino().equals("")) {
				predicates.add(cb.equal(cb.lower(buzonNotificaciones.get("codigoUsuarioDestino")),
						buzonNotificacionesDTO.getCodigoUsuarioDestino().toLowerCase()));
			}
		}

		if (buzonNotificacionesDTO.getCodigoUsuarioOrigina() != null
				&& !buzonNotificacionesDTO.getCodigoUsuarioOrigina().equals("")) {
			predicates.add(cb.like(cb.lower(buzonNotificaciones.get("codigoUsuarioOrigina")),
					"%" + buzonNotificacionesDTO.getCodigoUsuarioOrigina().toLowerCase() + "%"));
		}
		if (buzonNotificacionesDTO.getRespuesta() != null && !buzonNotificacionesDTO.getRespuesta().equals("")) {
			predicates.add(cb.like(cb.lower(buzonNotificaciones.get("respuesta")),
					"%" + buzonNotificacionesDTO.getRespuesta().toLowerCase() + "%"));
		}
		if (buzonNotificacionesDTO.getTipoMensaje() != null) {
			predicates.add(cb.like(buzonNotificaciones.get("tipoMensaje"),
					"%" + buzonNotificacionesDTO.getTipoMensaje() + "%"));
		}
		if (buzonNotificacionesDTO.getFechaEscritura() != null
				&& !buzonNotificacionesDTO.getFechaEscritura().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fechaEscritura", buzonNotificacionesDTO.getFechaEscritura(),
					NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, buzonNotificaciones);
			predicates.add(p);

		}
		if (buzonNotificacionesDTO.getFechaRespuesta() != null
				&& !buzonNotificacionesDTO.getFechaRespuesta().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fechaRespuesta", buzonNotificacionesDTO.getFechaRespuesta(),
					NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, buzonNotificaciones);
			predicates.add(p);

		}
		if (buzonNotificacionesDTO.getEstado() != null) {
			predicates.add(cb.like(buzonNotificaciones.get("estado").as(String.class),
					"%" + buzonNotificacionesDTO.getEstado() + "%"));
		}
		if (buzonNotificacionesDTO.getFechaLectura() != null && !buzonNotificacionesDTO.getFechaLectura().equals("")) {
			Predicate p = QueryUtils.filtroFechas("fechaLectura", buzonNotificacionesDTO.getFechaLectura(),
					NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, buzonNotificaciones);
			predicates.add(p);

		}

		cq.where(predicates.toArray(new Predicate[0]));

		if (buzonNotificacionesDTO.getTipoOrden() != null) {
			if (buzonNotificacionesDTO.getTipoOrden().equals("asc")) {
				cq.orderBy(cb.asc(buzonNotificaciones.get(buzonNotificacionesDTO.getColumnaOrdenar())));
			} else {
				cq.orderBy(cb.desc(buzonNotificaciones.get(buzonNotificacionesDTO.getColumnaOrdenar())));
			}
		} else {
			cq.orderBy(cb.desc(buzonNotificaciones.get("idNotificacion")));

		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(BuzonNotificaciones.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<BuzonNotificaciones> leerInformativos(String usuario) throws SpddExceptions {
		return buzonNotificacioneRepository.leerNotificacionesInformativas(usuario);
	}
}
