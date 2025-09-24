package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IConsecutivoServiceRepo extends IOperacionesBasicasFacade<Consecutivo, Long>, Serializable {

	/**
	 * 
	 * @param consecutivoDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(ConsecutivoDTO consecutivoDTO, Long inicio, Integer limite) throws SpddExceptions;

	/**
	 * 
	 * @param idPlan
	 * @param entidad
	 * @param nombre
	 * @return
	 * @throws SpddExceptions
	 */
	public Consecutivo obtenerConsecutivos(Long idPlan, String entidad, String nombre) throws SpddExceptions;
}
