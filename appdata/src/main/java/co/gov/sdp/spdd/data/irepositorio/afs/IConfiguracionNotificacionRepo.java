package co.gov.sdp.spdd.data.irepositorio.afs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

/**
 * Interface repositorio de la clase Configuracion notificacion
 *
 * @author Bryan Munoz
 *
 */
public interface IConfiguracionNotificacionRepo extends CrudRepository<ConfiguracionNotificacion, Long> {

    /**
     * Consulta que extrae una lista de notificaciones que se crearon de manera
     * automatica
     *
     * @return una lista de tipo configuracionNotificacion
     */
    @Query("SELECT DISTINCT c FROM ConfiguracionNotificacion c JOIN BuzonNotificaciones bn ON c.idConfigNotificacion=bn.idConfigNotificacion WHERE bn.tipoMensaje=0")
    public List<ConfiguracionNotificacion> obtenerAutomaticos();
}
