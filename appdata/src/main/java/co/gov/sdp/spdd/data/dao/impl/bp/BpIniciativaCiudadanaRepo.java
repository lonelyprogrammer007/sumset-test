package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EjemploDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCiudadanaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpIniciativaCiudadanaRepo;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCiudadana;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.model.view.VistaBpIniciativaCiudadana;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class BpIniciativaCiudadanaRepo extends OperacionesBasicasFacade<BpIniciativaCiudadana, Long>
		implements IBpIniciativaCiudadanaServiceRepo, Serializable {

	@Autowired
	transient EntityManager em;
	
	@Autowired
	IBpIniciativaCiudadanaRepo iniciativaRepositorio;

	@Override
	public CrudRepository<BpIniciativaCiudadana, Long> getRepo() {
		return iniciativaRepositorio;
	}

	@Override
	public FiltroDTO obtenerTodosIniciativaPorFiltro(BpIniciativaCiudadanaDTO peticion, Long inicio, Integer limite)
			throws SpddExceptions {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VistaBpIniciativaCiudadana> cq = cb.createQuery(VistaBpIniciativaCiudadana.class);
		Root<VistaBpIniciativaCiudadana> componenteRoot = cq.from(VistaBpIniciativaCiudadana.class);
		List<Predicate> predicates = new ArrayList<>();
		if (peticion.getIdIniciativa() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idIniciativa").as(String.class)),
					"%" + peticion.getIdIniciativa() + "%"));
		}
		if (peticion.getCodigoEntidad() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("codigoEntidad").get("codigoEntidad").as(String.class)),
					"%" + peticion.getCodigoEntidad() + "%"));
		}
		if (peticion.getCodigoIn() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("codigo").as(String.class)),
					"%" + peticion.getCodigoIn() + "%"));
		}
		if (peticion.getNombrePlanDesarrollo() != null) {
			predicates.add(
					cb.like(cb.lower(componenteRoot.get("idPlanDesarrollo").get("idPlanDesarrollo").as(String.class)),
							"%" + peticion.getNombrePlanDesarrollo().toLowerCase() + "%"));
		}
		if (peticion.getFechaCodigo() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("fechaCodigo").as(String.class)),
					"%" + peticion.getFechaCodigo() + "%"));
		}
		if (peticion.getNumeroRad() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("numeroRad").as(String.class)),
					"%" + peticion.getNumeroRad() + "%"));
		}
		if (peticion.getFechaRad() != null) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("fechaRad").as(String.class)),
					"%" + peticion.getFechaRad() + "%"));
		}
		if (peticion.getNombreSector() != null && !peticion.getNombreSector().equals("")) {
			predicates.add(cb.like(
					cb.lower(componenteRoot.get("idLcLineaInv").get("idLsSector").get("resultado").as(String.class)),
					"%" + peticion.getNombreSector() + "%"));
		}
		if (peticion.getNombreLineaInversion() != null && !peticion.getNombreLineaInversion().equals("")) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLcLineaInv").get("descripcion").as(String.class)),
					"%" + peticion.getNombreLineaInversion() + "%"));
		}
		if (peticion.getNombreEstado() != null && !peticion.getNombreEstado().equals("")) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLsEstadoInicia").get("resultado").as(String.class)),
					"%" + peticion.getNombreEstado().toLowerCase() + "%"));
		}
		if (peticion.getNombreCodigoProyecto() != null && !peticion.getNombreCodigoProyecto().equals("")) {
			predicates.add(cb.like(cb.lower(componenteRoot.get("codigoPi").as(String.class)),
					"%" + peticion.getNombreCodigoProyecto().toLowerCase() + "%"));
		}
		
		
		if(peticion.getIdLsEstadoInicia() != null)
		{
			predicates.add(cb.equal(componenteRoot.get("idLsEstadoInicia").get("idArgumento").as(String.class),
					peticion.getIdLsEstadoInicia()));
		}
		if(peticion.getNombreEstado() != null && !"".equals(peticion.getNombreEstado()))
		{
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLsEstadoInicia").get("resultado").as(String.class)),
					"%" + peticion.getNombreEstado().toLowerCase() + "%"));
		}
		if(peticion.getIdLsOrigen() != null)
		{
			predicates.add(cb.like(componenteRoot.get("idLsOrigen").get("idArgumento").as(String.class),
					"%" + peticion.getIdLsOrigen() + "%"));
		}
		if(peticion.getNombreOrigen() != null && !"".equals(peticion.getNombreOrigen()))
		{
			predicates.add(cb.like(cb.lower(componenteRoot.get("idLsOrigen").get("resultado").as(String.class)),
					"%" + peticion.getNombreOrigen().toLowerCase() + "%"));
		}
		if(peticion.getProblematica() != null && !"".equals(peticion.getProblematica()))
		{
			predicates.add(cb.like(cb.lower(componenteRoot.get("problematica").as(String.class)),
					"%" + peticion.getProblematica().toLowerCase() + "%"));
		}
		if(peticion.getAlternativaSolucion() != null && !"".equals(peticion.getAlternativaSolucion()))
		{
			predicates.add(cb.like(cb.lower(componenteRoot.get("alternativaSolucion").as(String.class)),
					"%" + peticion.getAlternativaSolucion().toLowerCase() + "%"));
		}
		if(peticion.getCodigoIn()!= null)
		{
			predicates.add(cb.like(componenteRoot.get("codigo").as(String.class),
					"%" + peticion.getCodigoIn() + "%"));
		}
		
		
		cq.where(predicates.toArray(new Predicate[0]));

		if (peticion.getColumnaOrdenar() != null) {
			if (peticion.getColumnaOrdenar().equals("idIniciativa") || peticion.getColumnaOrdenar().equals("codigo")
					|| peticion.getColumnaOrdenar().indexOf("fecha") >= 0
					|| peticion.getColumnaOrdenar().equals("codigoPi")
					|| peticion.getColumnaOrdenar().equals("numeroRad")) {
				if (peticion.getTipoOrden().equals("asc")) {
					cq.orderBy(cb.asc(componenteRoot.get(peticion.getColumnaOrdenar())));
				} else {
					cq.orderBy(cb.desc(componenteRoot.get(peticion.getColumnaOrdenar())));
				}
			} else {

			}
		} else {
			cq.orderBy(cb.desc(componenteRoot.get("idIniciativa")));
		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(VistaBpIniciativaCiudadana.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
	}

	@Override
	public List<BpIniciativaCiudadana> obtenerTodosRelacionadasConProyectoInversion(Long idProyecto) {
		return iniciativaRepositorio.obtenerTodosRelacionadasConProyectoInversion(idProyecto);
	}

}
