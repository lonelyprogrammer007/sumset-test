package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddPrbPoblacionServiceRepo extends IOperacionesBasicasFacade<PddPrbPoblacion, Long> {

	/**
	 * 
	 * @param descripcion
	 * @param idProblematica
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacion validarReglasPoblacion(String descripcion, Long idProblematica) throws SpddExceptions;
	
	public FiltroDTO obtenerPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion,Long inicio, Integer limite) throws SpddExceptions;

}
