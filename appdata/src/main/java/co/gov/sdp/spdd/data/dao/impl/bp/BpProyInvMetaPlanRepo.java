package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvMetaPlanServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvMetaPlanRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvMetaPlan
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Service
public class BpProyInvMetaPlanRepo extends OperacionesBasicasFacade<BpProyInvMetaPlan, Long> implements IBpProyInvMetaPlanServiceRepo, Serializable {

	@Autowired
	IBpProyInvMetaPlanRepo bpProyInvMetaPlanRepository;
	
	@Override
	public CrudRepository<BpProyInvMetaPlan, Long> getRepo() {
		return bpProyInvMetaPlanRepository;
	}

	@Override
	public BpProyInvMetaPlan obtenerPorIdProyInvEspecif(Long idProyObjEspecif) {
		return bpProyInvMetaPlanRepository.obtenerPorIdProyInvEspecif(idProyObjEspecif);
	}


}
