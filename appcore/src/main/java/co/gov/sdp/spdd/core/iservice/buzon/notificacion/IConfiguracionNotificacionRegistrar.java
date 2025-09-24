package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;

/**
 * Interface que maneja los metodos asociados a registrar de una configuracion
 * de notificacion
 *
 * @author Bryan Munoz
 *
 */
public interface IConfiguracionNotificacionRegistrar {

    /**
     * Metodo que crea una notificacion manual
     *
     * @param peticion campos con los que se creara la notificacion
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO crearNotificacionManual(ConfiguracionNotificacionDTO peticion);

    /**
     * Metodo que crea una notificacion automatica
     *
     * @param peticion campos con los que se creara la notificacion
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO crearNotificacionAutomatica(ConfiguracionNotificacionDTO peticion);
}
