package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author sumset
 *
 */
public interface IPotInstrumentoServiceRepo extends IOperacionesBasicasFacade<PotInstrumento, Long>, Serializable  {
	
	/**
	 * Metodo que pemite obtener todos los PotIntrumento filtrados y paginados
	 * @param potInstrumentoDTO Objeto de tipo PotInstrumentoDTO con la informacion para filtar
	 * @param inicio Long que representa la pagina de inicio para el paginado
	 * @param limite Long que representa el liminte de registros para el paginado
	 * @return un objeto de tipo FiltroDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public FiltroDTO obtenerTodosFiltrado(PotInstrumentoDTO potInstrumentoDTO, Long inicio, Integer limite) throws SpddExceptions;

	/**
	 * Metodo que permite obtener un PotInstrumento por el codigo y por identificador del pot
	 * @param codigo String que representa el codigo del PotInstrumento
	 * @param idPot Long que representa el identificador del pot
	 * @return un objeot de tipo PotInstrumento con la informacion correspondiente
	 */
	public PotInstrumento obtenerPorCodigoYIdPot(Long codigo,Long idPot);

}
