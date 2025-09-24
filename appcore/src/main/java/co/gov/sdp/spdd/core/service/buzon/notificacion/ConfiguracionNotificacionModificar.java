package co.gov.sdp.spdd.core.service.buzon.notificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConfiguracionNotificacionMapper;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

/**
 * Clase que implementa los metodos modificar asociados a una
 * configuracionNotificacion
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ConfiguracionNotificacionModificar implements IConfiguracionNotificacionModificar {

    // Servicio que permite realizar operaciones basicas de bd
    @Autowired
    IConfiguracionNotificacionServiceRepo configuracionNotificacionServiceRepo;
    
    // Servicio que permite convertir un dto a una entidad y viceversa
    @Autowired
    ConfiguracionNotificacionMapper mapper;

    /**
     * Implementacion del metodo modificarNotificacion
     *
     * @see
     * co.gov.sdp.nhspdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionModificar.modificarNotificacion
     */
    @Override
    public ConfiguracionNotificacionDTO modificarNotificacion(ConfiguracionNotificacionDTO peticion) {
        ConfiguracionNotificacion entidad = configuracionNotificacionServiceRepo
                .obtener(peticion.getIdConfigNotificacion());
        ConfiguracionNotificacionDTO respuesta = new ConfiguracionNotificacionDTO();
        if (entidad != null) {
            entidad = mapper.configuracionNotificacionDTOToEntity(peticion);
            configuracionNotificacionServiceRepo.guardar(entidad);

            respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
            respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_CONFIGURACION_NOTIFICACION,
                    PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
        } else {
            respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
            respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
                    PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
        }
        return respuesta;
    }

}
