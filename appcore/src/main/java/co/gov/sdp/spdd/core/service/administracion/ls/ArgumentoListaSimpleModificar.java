package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa todos los metodos relacionados con modificar de un
 * argumento lista simple
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ArgumentoListaSimpleModificar implements IArgumentoListaSimpleModificar {

	/**
	 * 
	 */
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	/**
	 * 
	 */
	@Autowired
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

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
	 * Implementacion del metodo modificarArgumento
	 * @throws JsonProcessingException 
	 */
	@Override
	public ArgumentoListaSimpleDTO modificarArgumento(ArgumentoListaSimpleDTO argumentoListaSimpleDTO)
			throws SpddExceptions, JsonProcessingException {

		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();

		ArgumentoListaSimpleDTO entidad = sessionFacadeAFS
				.consultarArgumentoListaSimplePorId(argumentoListaSimpleDTO.getIdArgumento());

		ArgumentoListaSimpleDTO auxiliar = sessionFacadeAFS.buscarIdListaSimpleArgumento(argumentoListaSimpleDTO);

		if (entidad != null && entidad.getIdArgumento() > 0) {
			if (entidad.getIdArgumento() == auxiliar.getIdArgumento()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE,
								PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
					auditoria.crearAuditoria(argumentoListaSimpleDTO.toString(), argumentoListaSimpleDTO.toString(), "ACTUALIZAR_AFS", "ADMIN_CONTENIDO_LS");
			} else {
				if (auxiliar.getIdArgumento() != null) {

					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO,
							PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
				} else {
					respuesta = sessionFacadeAFS.guardarArgumentoListaSimple(argumentoListaSimpleDTO);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE,
									PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
					auditoria.crearAuditoria(argumentoListaSimpleDTO.toString(), argumentoListaSimpleDTO.toString(), "ACTUALIZAR_AFS", "ADMIN_CONTENIDO_LS");
				}

			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, argumentoListaSimpleDTO.getLenguaje()));
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarEstado
	 */
	@Override
	public ArgumentoListaSimpleDTO modificarEstado(Long id) throws SpddExceptions {
		ArgumentoListaSimple entidad = argumentoListaSimpleServiceRepo.obtener(id);
		ArgumentoListaSimpleDTO respuesta = new ArgumentoListaSimpleDTO();
		if (entidad != null) {
			if (entidad.getEstado() == 1) {
				entidad.setEstado((short) 0);
				argumentoListaSimpleServiceRepo.guardar(entidad);
				respuesta = argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(entidad);

				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CAMBIO_DE_ESTADO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			} else {
				entidad.setEstado((short) 1);
				argumentoListaSimpleServiceRepo.guardar(entidad);
				respuesta = argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(entidad);

				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CAMBIO_DE_ESTADO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}
}
