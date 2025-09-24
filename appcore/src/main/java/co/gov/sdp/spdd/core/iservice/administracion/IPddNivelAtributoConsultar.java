package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IPddNivelAtributoConsultar {

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO pddNivelAtributoObtenerLibres() throws SpddExceptions;
	
}
