package co.gov.sdp.spdd.core.bp.iservice.bpiniciativa;

/**
 * 
 * @author Bryan Muñoz
 *
 */

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBPIniciativaConsultarService {

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodasLaIniciativasCiudadanas(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO consultaDetallaIniciativaCiudadana(Long idIniciativa) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa) throws SpddExceptions;

}
