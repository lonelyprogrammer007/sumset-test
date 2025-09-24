package co.gov.sdp.spdd.core.iservice.buzon.notificacion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;

/**
 * Interface que maneja los metodos consultar asociados a configuracion
 * notificacion
 *
 * @author Bryan Munoz
 *
 */
public interface IConfiguracionNotificacionConsultar {

    /**
     * Metodo que obtiene las notificaciones creadas automaticamente
     *
     * @return una lista,codigo y mensaje de respuesta
     */
    public GenericoDTO obtenerAutomaticos();
}
