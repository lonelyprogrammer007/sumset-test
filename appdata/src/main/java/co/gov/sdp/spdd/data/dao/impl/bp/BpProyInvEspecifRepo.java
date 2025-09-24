package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAporteServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEspecifServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvEspecifRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvEspecif
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Service
public class BpProyInvEspecifRepo extends OperacionesBasicasFacade<BpProyInvEspecif, Long> implements IBpProyInvEspecifServiceRepo, Serializable {

	@Autowired
	IBpProyInvEspecifRepo bpProyInvEspecifRepository;
	
	@Override
	public CrudRepository<BpProyInvEspecif, Long> getRepo() {
		return bpProyInvEspecifRepository;
	}

}
