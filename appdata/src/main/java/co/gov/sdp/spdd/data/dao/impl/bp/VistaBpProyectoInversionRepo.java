package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IVistaBpProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IVistaBpProyectoInversionRepo;
import co.gov.sdp.spdd.data.model.view.VistaBpProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad VistaBpProyectoInversion
 * 
 * @author DANIEL
 * @version 1.0 15/04/2020
 */
@Service
public class VistaBpProyectoInversionRepo extends OperacionesBasicasFacade<VistaBpProyectoInversion, Long> implements IVistaBpProyectoInversionServiceRepo, Serializable {
	
	@Autowired
	IVistaBpProyectoInversionRepo vistaBpProyectoInversionRepository;
	
	@Override
	public CrudRepository<VistaBpProyectoInversion, Long> getRepo() {
		return vistaBpProyectoInversionRepository;
	}

}
