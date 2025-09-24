package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface ILineaDeInversionServiceRepo extends IOperacionesBasicasFacade<LineaDeInversion, Long>, Serializable {

	/**
	 * 
	 * @param lineaDeInversionDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(LineaDeInversionDTO lineaDeInversionDTO, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param concepto
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public LineaDeInversion buscarConceptoYSector(String concepto, Long sector) throws SpddExceptions;

	/**
	 * 
	 * @param sector
	 * @return
	 * @throws SpddExceptions
	 */
	public List<LineaDeInversion> buscarPorSector(String sector) throws SpddExceptions;

}
