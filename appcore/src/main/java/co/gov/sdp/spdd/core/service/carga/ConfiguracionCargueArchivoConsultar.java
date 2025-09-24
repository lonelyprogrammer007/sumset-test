
package co.gov.sdp.spdd.core.service.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.carga.IConfiguracionCargueArchivoConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@Service
public class ConfiguracionCargueArchivoConsultar implements IConfiguracionCargueArchivoConsultar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * 
	 */
	@Override
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id) throws SpddExceptions {
		return sessionFacadeAFS.consultarConfigCargueArchivoProcesado(id);
	}

}
