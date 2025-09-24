package co.gov.sdp.spdd.core.iservice.carga;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author sumset
 *
 */
public interface IConfiguracionCargueArchivoConsultar {
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id) throws SpddExceptions;

}
