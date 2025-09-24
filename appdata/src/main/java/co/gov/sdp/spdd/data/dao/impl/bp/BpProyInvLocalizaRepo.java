package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLineaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLocalizaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvLineaRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvLocalizaRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvLocaliza
 * 
 * @author SEBASTIAN
 * @version 1.0 17/05/2020
 */
@Service
public class BpProyInvLocalizaRepo extends OperacionesBasicasFacade<BpProyInvLocaliza, Long> implements IBpProyInvLocalizaServiceRepo, Serializable {

	/**
	 * Inyeccion de IBpProyInvLocalizaRepo
	 */
	@Autowired
	IBpProyInvLocalizaRepo bpProyInvLocalizaRepository;
	
	@Override
	public CrudRepository<BpProyInvLocaliza, Long> getRepo() {
		return bpProyInvLocalizaRepository;
	}
	
	@Override
	public List<BpProyInvLocaliza> obtenerTodosPorIdProyInversion(Long idProyecto) {
		return bpProyInvLocalizaRepository.obtenerTodosPorIdProyInversion(idProyecto);
	}
	
	@Override
	public void eliminarTodos(List<BpProyInvLocaliza> lstBpProyInvLocaliza) {
		bpProyInvLocalizaRepository.deleteAll(lstBpProyInvLocaliza);
	}


}
