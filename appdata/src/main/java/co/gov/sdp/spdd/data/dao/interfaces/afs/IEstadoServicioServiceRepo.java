package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.EstadoServicio;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IEstadoServicioServiceRepo extends IOperacionesBasicasFacade<EstadoServicio, Long>, Serializable  {

	/**
	 * 
	 * @param estadoServicioDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(EstadoServicioDTO estadoServicioDTO, Long inicio, Integer limite) throws SpddExceptions;
	
}
