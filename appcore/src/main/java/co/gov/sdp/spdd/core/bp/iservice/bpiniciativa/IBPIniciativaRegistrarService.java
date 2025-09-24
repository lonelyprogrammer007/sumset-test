package co.gov.sdp.spdd.core.bp.iservice.bpiniciativa;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que contiene todos los metodos de registro asociados a una
 * iniciativa
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBPIniciativaRegistrarService {

	/**
	 * Metodo que registra una iniciativa ciudadana con sus tablas intermedias
	 * asociadas: grupos estarios y ubicaciones
	 * 
	 * @param peticion contiene informacion de la iniciativa ciudadana y sus tablas
	 *                 intermedias
	 * @return un dto con una respuesta de exito o fracaso
	 * @throws SpddExceptions excepcion que se puede dar si el metodo falla a nivel
	 *                        funcional
	 */
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions;
}
