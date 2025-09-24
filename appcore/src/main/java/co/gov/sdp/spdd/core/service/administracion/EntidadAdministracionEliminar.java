package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de eliminacion para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EntidadAdministracionEliminar implements IEntidadAdministracionEliminar {

    // Repositorio de usuario entidad
    @Autowired
    IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

    // Mapper de usuario entidad
    @Autowired
    UsuarioEntidadMapper usuarioEntidadMapper;
    
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

    /**
     * Implementacion del metodo removerUsuarioEntidad
     * @throws JsonProcessingException 
     *
     * @see
     * co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionEliminar.removerUsuarioEntidad
     */
    @Override
    public UsuarioEntidadDTO removerUsuarioEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException {
        UsuarioEntidad usuarioEntidad = usuarioEntidadServiceRepo.obtener(peticion.getIdUsuarioEntidad());
        UsuarioEntidadDTO respuesta = new UsuarioEntidadDTO();
        if (usuarioEntidad == null || usuarioEntidad.getIdUsuarioEntidad() == null) {
            respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
            respuesta.setMsgRespuesta(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_USUARIO_ENTIDAD_NO_ENCONTRADO,
                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
            return respuesta;
        }
        UsuarioEntidad entidad = usuarioEntidadMapper.usuarioEntidadDTOToEntity(peticion);
        usuarioEntidadServiceRepo.eliminar(entidad.getIdUsuarioEntidad());
        respuesta = usuarioEntidadMapper.usuarioEntidadEntityToDTO(entidad);
        respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        respuesta.setMsgRespuesta(
                NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REMOVER_PROYECTOS_INVERSION_USUARIO_CORRECTO,
                        PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
        //auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "ELIMINAR_AFS", "");
        return respuesta;
    }
}
