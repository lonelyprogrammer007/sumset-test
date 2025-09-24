package co.gov.sdp.spdd.data.dao.impl.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaUbicacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpIniciativaUbicacionRepo;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaUbicacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class BpIniciativaUbicacionRepo extends OperacionesBasicasFacade<BpIniciativaUbicacion, Long>
		implements IBpIniciativaUbicacionServiceRepo, Serializable {

	@Autowired
	IBpIniciativaUbicacionRepo iniciativaUbicacionRepositorio;

	@Override
	public CrudRepository<BpIniciativaUbicacion, Long> getRepo() {
		return iniciativaUbicacionRepositorio;
	}

	@Override
	public List<BpIniciativaUbicacion> obtenerUbicacionPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return iniciativaUbicacionRepositorio.obtenerUbicacionesPorIniciativas(idIniciativa);
	}

	@Override
	public void eliminarTodosUbicaciones(List<BpIniciativaUbicacion> entities) throws SpddExceptions {
		iniciativaUbicacionRepositorio.deleteAll(entities);
	}

}
