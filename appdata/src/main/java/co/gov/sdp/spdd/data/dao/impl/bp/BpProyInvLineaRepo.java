package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLineaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvLineaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvLinea
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Service
public class BpProyInvLineaRepo extends OperacionesBasicasFacade<BpProyInvLinea, Long> implements IBpProyInvLineaServiceRepo, Serializable {

	/**
	 * Inyeccion de IBpProyectoInversionRepo
	 */
	@Autowired
	IBpProyInvLineaRepo bpProyInvLineaRepository;


	
	
	@Override
	public CrudRepository<BpProyInvLinea, Long> getRepo() {
		return bpProyInvLineaRepository;
	}
	
	@Override
	public List<BpProyInvLinea> obtenerPorIdProyectoInversion(Long idProyecto) {
		return bpProyInvLineaRepository.obtenerPorIdProyectoInversion(idProyecto);
	}

	@Override
	public BpProyInvLinea obtenerPorIdLineaInversionYIdProyInversion(Long idLineaInversion, Long idProyecto) {
		return bpProyInvLineaRepository.obtenerPorIdLineaInversionYIdProyInversion(idLineaInversion, idProyecto);
	}

	

	

}
