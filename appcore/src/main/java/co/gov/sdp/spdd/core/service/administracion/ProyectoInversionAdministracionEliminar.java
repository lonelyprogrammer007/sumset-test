package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de eliminacion para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ProyectoInversionAdministracionEliminar implements IProyectoInversionAdmnistracionEliminar {

	// Repositorio de proyectos de inversion usuario
	@Autowired
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioRepo;

	// Mapper de proyecto inversion usuario
	@Autowired
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;
	
	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearProyectoInversion
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionEliminar.removerProyectoInversionUsuario
	 */
	@Override
	public ProyectosInversionUsuarioDTO removerProyectoInversionUsuario(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions {
		
		ProyectosInversionUsuario entidad = proyectosInversionUsuarioRepo.obtener(peticion.getIdProyectoUsuario());
		ProyectosInversionUsuarioDTO respuesta = new ProyectosInversionUsuarioDTO();
		if (entidad != null && entidad.getIdProyectoUsuario() != null) {
			sessionFacadeAFS.eliminarProyectoInversionUsuario(peticion.getIdProyectoUsuario());
			respuesta = proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REMOVER_PROYECTOS_INVERSION_USUARIO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
}
