package co.gov.sdp.spdd.core.bp.icontroller.bpiniciativa;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que contiene los metodos asociados a las iniciativas ciudadanas
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBPIniciativaController {

	/**
	 * Metodo que consulta todas las iniciativas ciudadanas
	 * 
	 * @param peticion un dto conn los filtros que se desean aplicar a la consulta
	 * @return un genericDto con una lista de iniciativas ciudadanas
	 */
	public GenericoDTO consultarTodasLasIniCiudadana(BpIniciativaCiudadanaDTO peticion);

	/**
	 * Metodoo que guarda una iniciativa ciudadana
	 * 
	 * @param peticion dto que se va a almacenar
	 * @return un dto con una respuesta de exito o fracaso
	 */
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public BpIniciativaCiudadanaDTO modificarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion);

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 */
	public BpIniciativaCiudadanaDTO consultaDetalladaIniciativaCiudadana(Long idIniciativa);

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 */
	public BpIniciativaCiudadanaDTO eliminarIniciativaCiudadana(Long idIniciativa);
	
	/**
	 * Metodo que permite obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa);

}
