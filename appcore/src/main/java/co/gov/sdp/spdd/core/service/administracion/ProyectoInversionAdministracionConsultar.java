package co.gov.sdp.spdd.core.service.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectoInversionRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ProyectoInversionAdministracionConsultar implements IProyectoInversionAdmnistracionConsultar {

	// Servicio repositorio de proyecto de inversion
	@Autowired
	IProyectoInversionServiceRepo proyectoInversionServiceRepo;

	// Servicio repositorio de proyecto de inversion
	@Autowired
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioServiceRepo;

	// Repositorio de proyecto de inversion
	@Autowired
	IProyectoInversionRepo proyectoInversionRepo;

	// Repositorio de proyecto de inversion
	@Autowired
	IProyectosInversionUsuarioRepo proyectosInversionUsuarioRepo;

	// Mapper de ProyectosInversionUsuario
	@Autowired
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;

	// Mapper de ProyectoInversion
	@Autowired
	ProyectoInversionMapper proyectoInversionMapper;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerProyectoInversionAsignados
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.autenticacion.obtenerProyectoInversionTodos.obtenerProyectoInversionAsignados
	 */
	@Override
	public GenericoDTO obtenerProyectoInversionAsignados(UsuariosDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		sessionFacadeAfs.consultarProyectosInversionUsuarioPorId(peticion.getNombreUsuario());
		List<ProyectosInversionUsuarioDTO> listaRespuesta = sessionFacadeAfs
				.consultarProyectosInversionUsuarioPorId(peticion.getNombreUsuario());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PROYECTO_INVERSION_ASIGNADOS_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerProyectoInversionDisponibles
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.autenticacion.obtenerProyectoInversionTodos.obtenerProyectoInversionDisponibles
	 */
	@Override
	public GenericoDTO obtenerProyectoInversionDisponibles() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<ProyectoInversion> lista = sessionFacadeAfs.proyectoInversionObtenerDisponibles();
		List<ProyectoInversionDTO> listaRespuesta = proyectoInversionMapper.proyectoInversionEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PROYECTO_INVERSION_DISPONIBLES_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerProyectoInversionTodos
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.adminitracion.obtenerProyectoInversionTodos.obtenerProyectoInversionTodos
	 */
	@Override
	public GenericoDTO obtenerProyectoInversionTodos() throws SpddExceptions {
		return sessionFacadeAfs.consultarProyectoInversionTodos();
	}

}
