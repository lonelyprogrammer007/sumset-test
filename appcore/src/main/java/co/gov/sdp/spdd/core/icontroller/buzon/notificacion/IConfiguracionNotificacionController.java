package co.gov.sdp.spdd.core.icontroller.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;

/**
 * Interface que maneja los metodos asociados al controlador de
 * configuracionNotificacionController
 *
 * @author Bryan Munoz
 *
 */
public interface IConfiguracionNotificacionController {

    /**
     * Metodo que crea una notificacion manual a partir de una peticion post
     *
     * @param peticion campos a crear
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO crearNotificacionManual(ConfiguracionNotificacionDTO peticion);

    /**
     * Metodo que crea una notificacion automatica a partir de una peticion post
     *
     * @param peticion campos a crear
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO crearNotificacionAutomatica(ConfiguracionNotificacionDTO peticion);

    /**
     * Metodo que obtiene una lista de configuraciones creadas automaticamente
     *
     * @return una lista,codigo y mensaje de respuesta
     */
    public GenericoDTO obtenerAutomaticos(ConfiguracionNotificacionDTO peticion);

    /**
     * Metodo que modifica una configuracion
     *
     * @param peticion campos a modificar
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConfiguracionNotificacionDTO modificarConfiguracion(ConfiguracionNotificacionDTO peticion);
}
