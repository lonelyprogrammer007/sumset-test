package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author sumset
 *
 */
public interface IPotObraServiceRepo extends IOperacionesBasicasFacade<PotObra, Long>, Serializable {

	/**
	 * Metodo que permite obtener todos los PotObra correspondientes a un nivel pot
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public Page<PotObra> obtenerTodosPaginadoPorIdNivelPot(Long idNivelPot, Pageable paginador);

	/**
	 * MEtodo que permite obtener un PotObra por medio de su codigo y id nivel
	 * 
	 * @param codigo     string que reprsenta el codigo pro el cual se va a buscar
	 * @param idNivelPot Long que representa el identificador del nivel por el cual
	 *                   se va a buscar
	 * @return un objeto de tipo PotObra con la informacion correspondiente.
	 */
	public PotObra obtenerPorCodigoYIdNivelPot(Long codigo, Long idNivelPot);

	/**
	 * 
	 * @param pot
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public Page<PotObra> obtenerPotObraPorPot(Long pot, Pageable pageable) throws SpddExceptions;

}
