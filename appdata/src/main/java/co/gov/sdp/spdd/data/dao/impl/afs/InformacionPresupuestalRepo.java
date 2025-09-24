package co.gov.sdp.spdd.data.dao.impl.afs;

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
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IInformacionPresupuestalRepo;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
@Transactional
@Service
public class InformacionPresupuestalRepo extends OperacionesBasicasFacade<InformacionPresupuestal, Long>
		implements IInformacionPresupuestalServiceRepo {

	/**
	 * 
	 */
	@Autowired
	IInformacionPresupuestalRepo informacionPresupuestalRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;

	/**
	 * 
	 */
	@Override
	public CrudRepository<InformacionPresupuestal, Long> getRepo() {
		return informacionPresupuestalRepository;
	}

	/**
	 * 
	 */
	@Override
	public FiltroDTO filtrarPorCampo(InformacionPresupuestalDTO informacionPresupuestalDTO, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<InformacionPresupuestal> cq = cb.createQuery(InformacionPresupuestal.class);
		Root<InformacionPresupuestal> informacionPresupuestal = cq.from(InformacionPresupuestal.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (informacionPresupuestalDTO.getIdInfoPresupuestal() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("idInfoPresupuestal").as(String.class),
					"%" + informacionPresupuestalDTO.getIdInfoPresupuestal() + "%"));
		}
		if (informacionPresupuestalDTO.getYear() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("year").as(String.class),
					"%" + informacionPresupuestalDTO.getYear() + "%"));
		}
		if (informacionPresupuestalDTO.getApropiacionDefinitiva() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("apropiacionDefinitiva").as(String.class),
					"%" + informacionPresupuestalDTO.getApropiacionDefinitiva() + "%"));
		}
		if (informacionPresupuestalDTO.getApropiacionReserva() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("apropiacionReserva").as(String.class),
					"%" + informacionPresupuestalDTO.getApropiacionReserva() + "%"));
		}
		if (informacionPresupuestalDTO.getCodigoClasificacionPresupuestal() != null) {
			predicates.add(cb.like(cb.lower(informacionPresupuestal.get("codigoClasifPresupuestal")).as(String.class),
					"%" + informacionPresupuestalDTO.getCodigoClasificacionPresupuestal().toLowerCase() + "%"));
		}
		if (informacionPresupuestalDTO.getCodigoDistrital() != null) {
			predicates.add(cb.equal(informacionPresupuestal.get("codigoDistrital").get("codigoEntidad").as(String.class),
					informacionPresupuestalDTO.getCodigoDistrital()));
		}
		if (informacionPresupuestalDTO.getCodigoInterno() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("codigoInterno").as(String.class),
					"%" + informacionPresupuestalDTO.getCodigoInterno() + "%"));
		}
		if (informacionPresupuestalDTO.getCodigoProyecto() != null) {
			predicates.add(cb.like(cb.lower(informacionPresupuestal.get("codigoProyecto").as(String.class)),
					"%" + informacionPresupuestalDTO.getCodigoProyecto() + "%"));
		}
		if (informacionPresupuestalDTO.getConstitucionReserva() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("constitucionReserva").as(String.class),
					"%" + informacionPresupuestalDTO.getConstitucionReserva() + "%"));
		}
		if (informacionPresupuestalDTO.getEjecucionGiroReservas() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("ejecucionGiroReservas").as(String.class),
					"%" + informacionPresupuestalDTO.getEjecucionGiroReservas() + "%"));
		}
		if (informacionPresupuestalDTO.getEjecucionVigencia() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("ejecucionVigencia").as(String.class),
					"%" + informacionPresupuestalDTO.getEjecucionVigencia() + "%"));
		}
		if (informacionPresupuestalDTO.getGirosVigencia() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("girosVigencia").as(String.class),
					"%" + informacionPresupuestalDTO.getGirosVigencia() + "%"));
		}
		if (informacionPresupuestalDTO.getMes() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("mes").as(String.class),
					"%" + informacionPresupuestalDTO.getMes() + "%"));
		}
		if (informacionPresupuestalDTO.getNombreProyecto() != null) {
			predicates.add(cb.like(cb.lower(informacionPresupuestal.get("nombreProyecto")),
					"%" + informacionPresupuestalDTO.getNombreProyecto().toLowerCase() + "%"));
		}
		if (informacionPresupuestalDTO.getRecursosSuspendidos() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("recursosSuspendidos").as(String.class),
					"%" + informacionPresupuestalDTO.getRecursosSuspendidos() + "%"));
		}
		if (informacionPresupuestalDTO.getOrigen() != null) {
			predicates.add(
					cb.like(cb.lower(informacionPresupuestal.get("origen")), "%" + informacionPresupuestalDTO.getOrigen().toLowerCase() + "%"));
		}
		if (informacionPresupuestalDTO.getIdArchivo() != null) {
			predicates.add(cb.like(informacionPresupuestal.get("idArchivo").get("idArchivo").as(String.class),
					"%" + informacionPresupuestalDTO.getIdArchivo() + "%"));
		}
		if (informacionPresupuestalDTO.getIdPlanDesarrollo() != null) {
			predicates.add(
					cb.equal(informacionPresupuestal.get("idPlanDesarrollo").get("idPlanDesarrollo").as(String.class),
							informacionPresupuestalDTO.getIdPlanDesarrollo()));
		}
		
		if(informacionPresupuestalDTO.getColumnaOrdenar()!=null)
		{
			if (informacionPresupuestalDTO.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(informacionPresupuestal.get(informacionPresupuestalDTO.getColumnaOrdenar())));
				} else {
					cq.orderBy(cb.desc(informacionPresupuestal.get(informacionPresupuestalDTO.getColumnaOrdenar())));
				}

		}
	
		
		cq.where(predicates.toArray(new Predicate[0]));
		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(InformacionPresupuestal.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	/**
	 * 
	 */
	@Override
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorPlanDesarrollo(Long id)
			throws SpddExceptions {
		return informacionPresupuestalRepository.obtenerInformacionPresupuestalPorPlanDesarrollo(id);

	}

	/**
	 * 
	 */
	@Override
	public List<InformacionPresupuestal> obtenerInformacionPresupuestalPorEntidad(Long id) throws SpddExceptions {
		return informacionPresupuestalRepository.obtenerInformacionPresupuestalPorEntidad(id);
	
	}

	@Override
	public List<InformacionPresupuestal> obtenerPorArchivo(Long idArchivo) {
		return informacionPresupuestalRepository.obtenerArchivoInfo(idArchivo);
	}

	@Override
	public InformacionPresupuestal obtenerPorCodigoEntidadYMesYYear(String codigoEntidad, Long mes, Long year) {
		return informacionPresupuestalRepository.obtenerPorCodigoEntidadYMesYYear(codigoEntidad, mes, year);
	}
}
