package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCondicion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaCondicionServiceRepo extends IOperacionesBasicasFacade<BpIniciativaCondicion, Long> {

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BpIniciativaCondicion> obtenerCondicionesPorIniciativa(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param listaCondiciones
	 */
	public void eliminarTodasLasCondicionesInciativas(List<BpIniciativaCondicion> listaCondiciones);
}
