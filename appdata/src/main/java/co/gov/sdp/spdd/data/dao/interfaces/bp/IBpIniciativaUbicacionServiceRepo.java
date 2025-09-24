package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaUbicacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaUbicacionServiceRepo extends IOperacionesBasicasFacade<BpIniciativaUbicacion, Long> {

	public List<BpIniciativaUbicacion> obtenerUbicacionPorIniciativa(Long idIniciativa) throws SpddExceptions;

	public void eliminarTodosUbicaciones(List<BpIniciativaUbicacion> entities) throws SpddExceptions;
}
