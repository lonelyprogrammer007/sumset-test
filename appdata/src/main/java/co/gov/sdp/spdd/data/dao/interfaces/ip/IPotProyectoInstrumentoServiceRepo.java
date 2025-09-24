package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author sumset
 *
 */
public interface IPotProyectoInstrumentoServiceRepo
		extends IOperacionesBasicasFacade<PotProyectoInstrumento, Long>, Serializable {

	/**
	 * 
	 * @param potProyectoInstrumentoDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param idPotInstrumento
	 * @param idPotProyecto
	 * @return
	 * @throws SpddExceptions
	 */
	public PotProyectoInstrumento buscarPorIdLsPotObraAndIdLsPotInstrumento(Long idPotInstrumento, Long idPotProyecto)
			throws SpddExceptions;

}
