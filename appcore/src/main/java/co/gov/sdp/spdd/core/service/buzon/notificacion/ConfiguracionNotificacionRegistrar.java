package co.gov.sdp.spdd.core.service.buzon.notificacion;

import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionRegistrar;

@Service
public class ConfiguracionNotificacionRegistrar implements IConfiguracionNotificacionRegistrar {

	/**
	 * 
	 */
    @Override
    public ConfiguracionNotificacionDTO crearNotificacionManual(ConfiguracionNotificacionDTO peticion) {
        return null;
    }

    /**
     * 
     */
    @Override
    public ConfiguracionNotificacionDTO crearNotificacionAutomatica(ConfiguracionNotificacionDTO peticion) {
        return null;
    }

}
