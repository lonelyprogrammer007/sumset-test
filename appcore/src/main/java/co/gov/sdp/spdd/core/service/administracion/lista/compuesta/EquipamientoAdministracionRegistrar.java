package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
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
public class EquipamientoAdministracionRegistrar implements IEquipamientoAdministracionRegistrar {

	// Servicio Repositorio de estructura meta
	@Autowired
	IEquipamientoServiceRepo equipamientoServiceRepo;

	// Servicio Repositorio de estructura meta
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de estructura meta
	@Autowired
	EquipamientoMapper equipamientoMapper;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo crearEquipamiento
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.administracion.IArchivoProcesadoCargaRegistrar.IEquipamientoAdministracionRegistrar.crearEquipamiento
	 */
	@Override
	public EquipamientoDTO crearEquipamiento(EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO idLsCategoria = sessionFacadeAFS.consultarArgumentoListaSimplePorId(peticion.getIdLsCategoria());
		ArgumentoListaSimpleDTO idLsSector = sessionFacadeAFS.consultarArgumentoListaSimplePorId(peticion.getIdLsSector());
		EquipamientoDTO respuesta = new EquipamientoDTO();
		if (idLsCategoria == null || idLsCategoria.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_CATEGORIA_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		if (idLsSector == null || idLsSector.getIdArgumento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_LISTA_SECTOR_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			return respuesta;
		}
		respuesta = sessionFacadeAFS.guardarEquipamiento(peticion);
		if (respuesta.getIdEquipamento() == null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "CREAR_EQUIPAMIENTOS");
		}
		return respuesta;
	}
}