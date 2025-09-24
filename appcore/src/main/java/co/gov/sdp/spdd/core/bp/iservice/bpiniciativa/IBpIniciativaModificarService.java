package co.gov.sdp.spdd.core.bp.iservice.bpiniciativa;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaModificarService {

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO modificarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion)
			throws SpddExceptions;

}
