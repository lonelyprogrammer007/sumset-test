package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvProductoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvProductoRepo;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Clase que contiene la implemenatcion de los metodos a partir de la inyeccion
 * de Repository de la entidad BpProyInvProducto
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
@Service
public class BpProyInvProductoRepo extends OperacionesBasicasFacade<BpProyInvProducto, Long> implements IBpProyInvProductoServiceRepo, Serializable {

	@Autowired
	IBpProyInvProductoRepo bpProyInvProductoRepository;
	
	@Override
	public CrudRepository<BpProyInvProducto, Long> getRepo() {
		return bpProyInvProductoRepository;
	}

	@Override
	public BpProyInvProducto obtenerPorIdProyInvMetaPlan(Long idProyMetaPlan) {
		return bpProyInvProductoRepository.obtenerPorIdProyInvMetaPlan(idProyMetaPlan);
	}

}
