package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IBancoDeProyectoConsultar {

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodos() throws SpddExceptions;
}
