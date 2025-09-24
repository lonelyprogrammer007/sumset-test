package co.gov.sdp.spdd.core.service.administracion;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBancoDeProyectoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IUsuarioEntidadRepo;
import co.gov.sdp.spdd.data.mapper.EntidadMapper;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.BancoDeProyectos;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de registro para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EntidadAdministracionRegistrar implements IEntidadAdministracionRegistrar {

	// Repositorio de usuario entidad
	@Autowired
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

	@Autowired
	IUsuarioEntidadRepo repo;

	// Repositorio de entidad
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

	// Repositorio de banco
	@Autowired
	IBancoDeProyectoServiceRepo bancoDeProyectoServiceRepo;

	// Repositorio de banco
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de entidad
	@Autowired
	EntidadMapper entidadMapper;

	// Mapper de usuario entidad
	@Autowired
	UsuarioEntidadMapper usuarioEntidadMapper;

	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * 
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo crearEntidad
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar.crearEntidad
	 */
	@Override
	public EntidadDTO crearEntidad(EntidadDTO peticion) throws SpddExceptions {
		BancoDeProyectos bancoDeProyectos = bancoDeProyectoServiceRepo.obtener(peticion.getIdBancoProyecto());
		ArgumentoListaSimple idLsAsociacion = argumentoListaSimpleServiceRepo.obtener(peticion.getIdLsAsociacion());
		ArgumentoListaSimple idLsClasificacion = argumentoListaSimpleServiceRepo
				.obtener(peticion.getIdLsClasificacion());
		EntidadDTO respuesta = new EntidadDTO();
		if (bancoDeProyectos == null || bancoDeProyectos.getIdBancoProyecto() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_BANCO_DE_PROYECTOS_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		if (idLsAsociacion == null || idLsAsociacion.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_ASOCIACION_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		if (idLsClasificacion == null || idLsClasificacion.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_CLASIFICACION_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		Entidad entidad = entidadMapper.entidadDTOToEntity(peticion);
		entidadServiceRepo.guardar(entidad);
		respuesta = entidadMapper.entidadEntityToDTO(entidad);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_ENTIDAD_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;

	}

	/**
	 * Implementacion del metodo crearProyectoInversion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionRegistrar.asignarUsuarioEntidad
	 */
	@Override
	public UsuarioEntidadDTO asignarUsuarioEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException {

		UsuarioEntidadDTO respuesta = new UsuarioEntidadDTO();
        System.out.println("peticion"+peticion.toString());
		List<UsuarioEntidad> lista = usuarioEntidadServiceRepo.obtenerPorUsuario(peticion.getCodigoUsuario());

		if (lista != null && !lista.isEmpty()) {
			repo.deleteAll(lista);
		}
		String[] split = peticion.getStringConcatenado().split(";");
		String[] modificar = peticion.getStringModificar().split(";");

		List<String> splitList = Arrays.asList(split);
		List<String> modificarList = Arrays.asList(modificar);
		splitList.stream().forEach(s -> {
			peticion.setCodigoEntidad(s);
			UsuarioEntidad usuarioEntidad = usuarioEntidadMapper.usuarioEntidadDTOToEntity(peticion);
			usuarioEntidad.setModificarDatos(0L);
			if (!s.isEmpty() && !s.equals("")) {
				if (modificarList.contains(s)) {
					usuarioEntidad.setModificarDatos(1L);
				}

				usuarioEntidadServiceRepo.guardar(usuarioEntidad);
			}
		});

		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASIGNAR_USUARIO_ENTIDAD_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "ASIGNAR_ENTIDADES");
		return respuesta;
	}
}
