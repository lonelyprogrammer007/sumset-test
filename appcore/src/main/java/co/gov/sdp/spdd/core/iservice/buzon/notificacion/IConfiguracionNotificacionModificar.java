package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;

/**
 * Interface que maneja los metodos asociados a modificar
 *
 * @author Bryan Munoz
 *
 */
public interface IConfiguracionNotificacionModificar {

    /**
     * Metodo que permite modificar una configuracion
     *
     * @param peticion camposa modificar de la configuracion
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO modificarNotificacion(ConfiguracionNotificacionDTO peticion);
}
