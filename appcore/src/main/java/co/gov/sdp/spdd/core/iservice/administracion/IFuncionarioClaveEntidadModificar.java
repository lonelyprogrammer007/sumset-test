package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IFuncionarioClaveEntidadModificar {
	
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO modificarFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions;

}
