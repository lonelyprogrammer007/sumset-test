package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvActividadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvMetaPlanServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvActividadRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvActividad;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvActividad
 * @author SEBASTIAN
 * @version 1.0 29/05/2020
 */
@Service
public class BpProyInvActividadRepo extends OperacionesBasicasFacade<BpProyInvActividad, Long> implements IBpProyInvActividadServiceRepo, Serializable {

	@Autowired
	IBpProyInvActividadRepo bpProyInvActividadRepository;
	
	@Override
	public CrudRepository<BpProyInvActividad, Long> getRepo() {
		return bpProyInvActividadRepository;
	}

	@Override
	public BpProyInvActividad obtenerPorIdProducto(Long IdProducto) {
		return bpProyInvActividadRepository.obtenerPorIdProducto(IdProducto);
	}

}
