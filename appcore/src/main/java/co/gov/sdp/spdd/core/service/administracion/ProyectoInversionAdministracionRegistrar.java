package co.gov.sdp.spdd.core.service.administracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de registro para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ProyectoInversionAdministracionRegistrar implements IProyectoInversionAdministracionRegistrar {

	// Repositorio de proyectos de inversion usuario
	@Autowired
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioRepo;

	@Autowired
	IProyectosInversionUsuarioRepo repo;

	// Repositorio de proyecto de inversion
	@Autowired
	IProyectoInversionServiceRepo proyectosInversionRepo;

	// Mapper de proyecto inversion
	@Autowired
	ProyectoInversionMapper proyectoInversionMapper;

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
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo crearProyectoInversion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdministracionRegistrar.crearProyectoInversion
	 */
	@Override
	public ProyectoInversionDTO crearProyectoInversion(ProyectoInversionDTO peticion) throws SpddExceptions, JsonProcessingException {
		ProyectoInversionDTO respuesta = sessionFacadeAFS.guardarProyectoInversion(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PROYECTO_INVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "ASIGNAR_PROYECTOS_INVERSION");
		return respuesta;
	}
	
	/**
	 * Implementacion del metodo crearProyectoInversion
	 *
	 * @see co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionRegistrar.asignarProyectosInversionUsuario
	 */
	@Override
	public ProyectosInversionUsuarioDTO asignarProyectosInversionUsuario(ProyectosInversionUsuarioDTO peticion)
			throws SpddExceptions {
		List<ProyectosInversionUsuario> lista = proyectosInversionUsuarioRepo
				.obtenerPorUsuario(peticion.getCodigoUsuario());
		if (lista != null && !lista.isEmpty()) {
			repo.deleteAll(lista);
		}
		ProyectosInversionUsuarioDTO respuesta = new ProyectosInversionUsuarioDTO();
		String[] split = peticion.getStringConcatenado().split(";");
		for (int i = 0; i < split.length; i++) {
			if (split.length > 0 && !split[i].equals("")) {
				Long auxiliar = Long.parseLong(split[i]);
				peticion.setIdProyectoInversion(auxiliar);
				ProyectosInversionUsuario entidad = proyectosInversionUsuarioMapper
						.proyectosInversionUsuarioDTOToEntity(peticion);
				proyectosInversionUsuarioRepo.guardar(entidad);
				respuesta = proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(entidad);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ASIGNAR_PROYECTOS_INVERSION_USUARIO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		}

		return respuesta;
	}

}
