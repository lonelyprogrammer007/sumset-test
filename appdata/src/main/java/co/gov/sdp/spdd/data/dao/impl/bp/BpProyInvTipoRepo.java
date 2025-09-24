package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvTipoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvTipoRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvTipo
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Service
public class BpProyInvTipoRepo extends OperacionesBasicasFacade<BpProyInvTipo, Long> implements IBpProyInvTipoServiceRepo, Serializable {

	/**
	 * Inyeccion de IBpProyInvTipoRepo
	 */
	@Autowired
	IBpProyInvTipoRepo bpProyInvTipoRepository;

	
	@Override
	public CrudRepository<BpProyInvTipo, Long> getRepo() {
		return bpProyInvTipoRepository;
	}
	
	@Override
	public List<BpProyInvTipo> obtenerPorIdProyectoInversion(Long idProyecto) {
		return bpProyInvTipoRepository.obtenerPorIdProyectoInversion(idProyecto);
	}

	@Override
	public BpProyInvTipo obtenerPorIdLsTipoYIdProyInversion(Long idLsTipo, Long idProyInversion) {
		return bpProyInvTipoRepository.obtenerPorIdLsTipoYIdProyInversion(idLsTipo, idProyInversion);
	}
	
	@Override
	public void eliminar(List<BpProyInvTipo> listaBpProyInvTipo)
	{
		bpProyInvTipoRepository.deleteAll(listaBpProyInvTipo);
	}
}
