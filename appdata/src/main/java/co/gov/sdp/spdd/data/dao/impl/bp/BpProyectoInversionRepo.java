package co.gov.sdp.spdd.data.dao.impl.bp;

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
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyectoInversionRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyectoInversion
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Service
public class BpProyectoInversionRepo extends OperacionesBasicasFacade<BpProyectoInversion, Long> implements IBpProyectoInversionServiceRepo, Serializable {

	/**
	 * Inyeccion de IBpProyectoInversionRepo
	 */
	@Autowired
	IBpProyectoInversionRepo bpProyectoInversionRepository;

	/**
	 * 
	 */
	@PersistenceContext
	transient EntityManager em;
	
	@Override
	public CrudRepository<BpProyectoInversion, Long> getRepo() {
		return bpProyectoInversionRepository;
	}
	
	@Override
	public FiltroDTO filtrarPorCampo(BpProyectoInversionDTO bpProyectoInversionDTO, Long inicio, Integer limite) throws SpddExceptions {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BpProyectoInversion> cq = cb.createQuery(BpProyectoInversion.class);
		Root<BpProyectoInversion> proyecto = cq.from(BpProyectoInversion.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (bpProyectoInversionDTO.getIdProyInversion() != null) {
			predicates.add(
					cb.equal(proyecto.get("idProyInversion").as(String.class), bpProyectoInversionDTO.getIdProyInversion() ));
		}
		
		if (bpProyectoInversionDTO.getCodigoProyecto() != null) {
			predicates.add(
					cb.like(proyecto.get("codigo").as(String.class), "%" + bpProyectoInversionDTO.getCodigoProyecto() + "%"));
		}
		
		if (bpProyectoInversionDTO.getNombreProyecto() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("nombre").as(String.class)), "%" + bpProyectoInversionDTO.getNombreProyecto().toLowerCase() + "%"));
		}
		
		if (bpProyectoInversionDTO.getCodigoBpin() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("codigoBpin").as(String.class)), "%" + bpProyectoInversionDTO.getCodigoBpin().toLowerCase() + "%"));
		}
		
		if (bpProyectoInversionDTO.getNombreBpin() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("nombreBpin").as(String.class)), "%" + bpProyectoInversionDTO.getNombreBpin().toLowerCase() + "%"));
		}
				
		if (bpProyectoInversionDTO.getNombrePoai() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("nombrePoai").as(String.class)), "%" + bpProyectoInversionDTO.getNombrePoai().toLowerCase() + "%"));
		}
				
		if (bpProyectoInversionDTO.getVersion() != null) {
			predicates.add(
					cb.like(proyecto.get("version").as(String.class), "%" + bpProyectoInversionDTO.getVersion() + "%"));
		}
		
		if (bpProyectoInversionDTO.getFechaVersion() != null) {
			predicates.add(
					cb.like(proyecto.get("fechaVersion").as(String.class), "%" + bpProyectoInversionDTO.getFechaVersion() + "%"));
		}		
		
		if (bpProyectoInversionDTO.getFechaEstado() != null) {
			predicates.add(
					cb.like(proyecto.get("fechaEstado").as(String.class), "%" + bpProyectoInversionDTO.getFechaEstado() + "%"));
		}
		
		//TIPO
		
		
		//SECTOR
		
		
		//LsEstado
		if (bpProyectoInversionDTO.getIdLsEstado() != null) {
			predicates.add(
					cb.equal(proyecto.get("idLsEstado").get("idArgumento").as(String.class), bpProyectoInversionDTO.getIdLsEstado()));
		}
		
		if (bpProyectoInversionDTO.getStringLsEstado() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("idLsEstado").get("resultado").as(String.class)), "%" + bpProyectoInversionDTO.getStringLsEstado() + "%"));
		}
		
		//LsEtapa
		if (bpProyectoInversionDTO.getIdLsEtapa() != null) {
			predicates.add(
					cb.like(proyecto.get("idLsEtapa").get("idArgumento").as(String.class), "%" + bpProyectoInversionDTO.getIdLsEtapa() + "%"));
		}
		
		if (bpProyectoInversionDTO.getStringLsEtapa() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("idLsEtapa").get("resultado").as(String.class)), "%" + bpProyectoInversionDTO.getStringLsEtapa() + "%"));
		}
		
		//PDD
		if (bpProyectoInversionDTO.getIdAtributoPdd() != null) {
			predicates.add(
					cb.equal(proyecto.get("idAtributoPdd").as(String.class),  bpProyectoInversionDTO.getIdAtributoPdd() ));
		}
		
		if (bpProyectoInversionDTO.getIdPlanDesarrolloDistrital() != null) {
			predicates.add(
					cb.equal(proyecto.get("idAtributoPdd").get("idPddNivel").get("idPlanDesarrollo").get("idPlanDesarrollo").as(String.class), "%" + bpProyectoInversionDTO.getIdPlanDesarrolloDistrital() + "%"));
		}
		
		if (bpProyectoInversionDTO.getStringPlanDesarrolloDistrital() != null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("idAtributoPdd").get("idPddNivel").get("idPlanDesarrollo").get("nombrePlan").as(String.class)), "%" + bpProyectoInversionDTO.getStringPlanDesarrolloDistrital().toLowerCase() + "%"));
		}
		
		//PDL
		if (bpProyectoInversionDTO.getIdAtributoPdl() != null) {
			predicates.add(
					cb.equal(proyecto.get("idAtributoPdl").as(String.class), bpProyectoInversionDTO.getIdAtributoPdl() ));
		}
		
		if (bpProyectoInversionDTO.getIdPlanDesarrolloLocal() != null) {
			predicates.add(
					cb.equal(proyecto.get("idAtributoPdl").get("idPdlNivel").get("idPlanLocal").get("idPlanLocal").as(String.class), "%" + bpProyectoInversionDTO.getIdPlanDesarrolloLocal() + "%"));
		}
		
		if (bpProyectoInversionDTO.getStringPlanDesarrolloLocal()!= null) {
			predicates.add(
					cb.like(cb.lower(proyecto.get("idAtributoPdl").get("idPdlNivel").get("idPlanLocal").get("nombrePlan").as(String.class)), "%" + bpProyectoInversionDTO.getStringPlanDesarrolloLocal().toLowerCase() + "%"));
		}
		
		//Entidad
		if (bpProyectoInversionDTO.getCodigoEntidad() != null) {
			predicates.add(cb.like(cb.lower(proyecto.get("codigoEntidad").get("codigoEntidad").as(String.class)), "%" + bpProyectoInversionDTO.getCodigoEntidad().toLowerCase() + "%"));
		}
		
		if (bpProyectoInversionDTO.getStringEntidad() != null) {
			predicates.add(cb.like(cb.lower(proyecto.get("codigoEntidad").get("idLsClasificacion").get("resultado").as(String.class)), "%" + bpProyectoInversionDTO.getStringEntidad().toLowerCase() + "%"));
		}
		
		cq.where(predicates.toArray(new Predicate[0]));

		if (bpProyectoInversionDTO.getColumnaOrdenar() != null) {
			if (bpProyectoInversionDTO.getTipoOrden().equals("asc")) {
				
				//ordenar por columnas que no hacen parte de la tabla
				if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Tipo de Proyecto")
						|| bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Sector"))
				{
					// TODO: falta determinar como filtrara por estos campos ya que no hacen parte de la tabla en BD
				}
				else
				{
					if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Plan de Desarrollo"))
					{
						cq.orderBy(cb.asc(proyecto.get("idAtributoPdd").get("idPddNivel").get("idPlanDesarrollo").get("nombrePlan")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Alcaldia Local/Entidad"))
					{
						cq.orderBy(cb.asc(proyecto.get("codigoEntidad").get("idLsClasificacion").get("resultado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Codigo Proyecto"))
					{
						cq.orderBy(cb.asc(proyecto.get("codigo")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Nombre Proyecto"))
					{
						cq.orderBy(cb.asc(proyecto.get("nombre")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Estado"))
					{
						cq.orderBy(cb.asc(proyecto.get("idLsEstado").get("resultado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Fecha de Estado"))
					{
						cq.orderBy(cb.asc(proyecto.get("fecha_estado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Etapa Actual"))
					{
						cq.orderBy(cb.asc(proyecto.get("idLsEtapa").get("resultado")));
					}
					else
						cq.orderBy(cb.asc(proyecto.get(bpProyectoInversionDTO.getColumnaOrdenar())));
				}				
			} else {
				if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Tipo de Proyecto")
						|| bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Sector"))
				{
					// TODO: falta determinar como filtrara por estos campos ya que no hacen parte de la tabla en BD
				}
				else
				{
					if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Plan de Desarrollo"))
					{
						cq.orderBy(cb.desc(proyecto.get("idAtributoPdd").get("idPddNivel").get("idPlanDesarrollo").get("nombrePlan")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Alcaldia Local/Entidad"))
					{
						cq.orderBy(cb.desc(proyecto.get("codigoEntidad").get("idLsClasificacion").get("resultado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Codigo Proyecto"))
					{
						cq.orderBy(cb.desc(proyecto.get("codigo")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Nombre Proyecto"))
					{
						cq.orderBy(cb.desc(proyecto.get("nombre")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Estado"))
					{
						cq.orderBy(cb.desc(proyecto.get("idLsEstado").get("resultado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Fecha de Estado"))
					{
						cq.orderBy(cb.desc(proyecto.get("fecha_estado")));
					}
					else if(bpProyectoInversionDTO.getColumnaOrdenar().equalsIgnoreCase("Etapa Actual"))
					{
						cq.orderBy(cb.desc(proyecto.get("idLsEtapa").get("resultado")));
					}
					else
						cq.orderBy(cb.desc(proyecto.get(bpProyectoInversionDTO.getColumnaOrdenar())));
				}				
			}
		} else {
			cq.orderBy(cb.desc(proyecto.get("idProyInversion")));
		}

		FiltroDTO res = new FiltroDTO();
		res.setResultadoConsulta(
				em.createQuery(cq).setFirstResult(inicio.intValue()).setMaxResults(limite.intValue()).getResultList());
		CriteriaQuery<Long> cq2 = cb.createQuery(Long.class);
		cq2.select(cb.count(cq2.from(BpProyectoInversion.class)));
		cq2.where(predicates.toArray(new Predicate[0]));
		res.setContador(em.createQuery(cq2).getSingleResult());
		return res;
		
	}

	

}
