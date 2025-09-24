package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IPotObraConsultar {
	
	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPotObra() throws SpddExceptions;

}
