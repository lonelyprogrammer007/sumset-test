package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IArchivoProcesadoServiceRepo extends IOperacionesBasicasFacade<ArchivoProcesado, Long>, Serializable {

	/**
	 * 
	 * @param archivoProcesadoDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(ArchivoProcesadoDTO archivoProcesadoDTO, Long inicio, Integer limite)
			throws SpddExceptions;

}
