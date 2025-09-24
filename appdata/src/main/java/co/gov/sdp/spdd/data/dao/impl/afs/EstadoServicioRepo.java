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

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstadoServicioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IEstadoServicioRepo;
import co.gov.sdp.spdd.data.model.afs.EstadoServicio;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;
import co.gov.sdp.spdd.data.utils.QueryUtils;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class EstadoServicioRepo extends OperacionesBasicasFacade<EstadoServicio, Long>
		implements IEstadoServicioServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IEstadoServicioRepo estadoServcioRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<EstadoServicio, Long> getRepo() {
		return estadoServcioRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(EstadoServicioDTO estadoServicioDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EstadoServicio> cq = cb.createQuery(EstadoServicio.class);
		Root<EstadoServicio> estadoServicio = cq.from(EstadoServicio.class);
		List<Predicate> predicates = new ArrayList<>();
		if (estadoServicioDTO.getIdEstadoServicio() != null) {
			predicates.add(cb.like(estadoServicio.get("idEstadoServicio").as(String.class),
					"%" + estadoServicioDTO.getIdEstadoServicio() + "%"));
		}
		if (estadoServicioDTO.getEstadoSolicitud() != null) {
			predicates.add(
					cb.like(estadoServicio.get("estadoSolicitud"), "%" + estadoServicioDTO.getEstadoSolicitud() + "%"));
		}
		if (estadoServicioDTO.getFechaRespuesta() != null && !estadoServicioDTO.getFechaRespuesta().equals("")) {
			Predicate p=  QueryUtils.filtroFechas("fechaRespuesta",estadoServicioDTO.getFechaRespuesta(), NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, estadoServicio);
			predicates.add(p);
			
		}
		if (estadoServicioDTO.getFechaSolicitud() != null && !estadoServicioDTO.getFechaSolicitud().equals("")) {
			Predicate p=  QueryUtils.filtroFechas("fechaSolicitud",estadoServicioDTO.getFechaSolicitud(), NHSPDDConstantes.FORMATO_FECHA_FILTRO, cb, estadoServicio);
			predicates.add(p);
			
		}
		if (estadoServicioDTO.getNombreServicio() != null) {
			predicates.add(
					cb.like(cb.lower(estadoServicio.get("nombreServicio")), "%" + estadoServicioDTO.getNombreServicio().toLowerCase() + "%"));
		}
		if (estadoServicioDTO.getTarea() != null) {
			predicates.add(cb.like(cb.lower(estadoServicio.get("tarea")), "%" + estadoServicioDTO.getTarea().toLowerCase() + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(EstadoServicio.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}
}
