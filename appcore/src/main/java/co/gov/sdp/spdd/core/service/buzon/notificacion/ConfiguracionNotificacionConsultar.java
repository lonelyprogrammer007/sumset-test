package co.gov.sdp.spdd.core.service.buzon.notificacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IConfiguracionNotificacionRepo;
import co.gov.sdp.spdd.data.mapper.ConfiguracionNotificacionMapper;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

/**
 * Clase que implementa los metodos consultar asociados a una configuracion
 * notificacion
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ConfiguracionNotificacionConsultar implements IConfiguracionNotificacionConsultar {
    
	// Servicio que permite realizar una query para consultar
    @Autowired
    IConfiguracionNotificacionRepo configuracionNotificacionRepo;
    
    // Servicio que permite convertir un dto a una entidad y viceversa
    @Autowired
    ConfiguracionNotificacionMapper mapper;
    
    /**
     * 
     */
    @Autowired
    IConfiguracionNotificacionServiceRepo serviceRepo;

    /**
     * Implementacion del metodo obtenerAutomatico
     *
     * @see
     * co.gov.sdp.nhspdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionConsultar.obtenerAutomaticos
     */
    @Override
    public GenericoDTO obtenerAutomaticos() {
        GenericoDTO respuesta = new GenericoDTO();
        List<ConfiguracionNotificacion> entidad = serviceRepo.obtenerTodos();
        List<ConfiguracionNotificacionDTO> listaRespuesta = mapper.configuracionNotificacionEntitiesToDTO(entidad);
        respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
        respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
                NHSPDDConstantes.MENSAJE_OBTENER_NOTIFICACIONES_AUTOMATICO, PaqueteMensajeEnum.MENSAJES, null));

        return respuesta;
    }
}
