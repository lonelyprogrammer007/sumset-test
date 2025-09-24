package co.gov.sdp.spdd.core.icontroller.presupuesto;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IPddController {

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdd() throws SpddExceptions;
}

