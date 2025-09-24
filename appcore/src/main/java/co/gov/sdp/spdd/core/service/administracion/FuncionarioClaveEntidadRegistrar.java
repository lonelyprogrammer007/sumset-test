package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Esta clase implementa todos los metodos de registro de
 * FuncionarioClaveEntidadRegistrar
 *
 * @author Johan Sebastian Giraldo
 *
 */
@Service
public class FuncionarioClaveEntidadRegistrar implements IFuncionarioClaveEntidadRegistrar {

	/**
	 * Objeto que tiene los servicios de funcionarioClaveEntidadRepo
	 */
	@Autowired
	IFuncionarioClaveEntidadServiceRepo funcionarioClaveEntidadServiceRepo;

	/**
	 * Objeto que mapea las entidades a dto y viceversa
	 */
	@Autowired
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	/**
	 * Objeto que contiene metodos de consultas especificas de entidadServiceRepo
	 */
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

	/**
	 * Objeto que contiene metodos de consultas especificas de
	 * argumentoListaSimpleServiceRepo
	 */
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

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
	 * Valido la llave foranea, Mapear un dto a una entidad, Guardar en la base de
	 * datos, Mapear una entidad a un dto, Mensaje correcto, Mensaje incorrecto.
	 * @throws JsonProcessingException 
	 */
	@Override
	public FuncionarioClaveEntidadDTO crearFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		EntidadDTO validacion = sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad());
		ArgumentoListaSimpleDTO validacionDos = sessionFacadeAFS
				.consultarArgumentoListaSimplePorId(peticion.getIdLsFuncion());
		ArgumentoListaSimpleDTO validacionTres = sessionFacadeAFS
				.consultarArgumentoListaSimplePorId(peticion.getIdLsGenero());
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		FuncionarioClaveEntidadDTO auxiliar = sessionFacadeAFS
				.validarFuncionarioPorEntidadYFuncion(peticion.getCodigoEntidad(), peticion.getIdLsFuncion());
		if (validacion != null && validacionDos != null && validacionTres != null) {
			if (auxiliar == null) {
				respuesta = sessionFacadeAFS.guardarFuncionarioClaveEntidad(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CREAR_PROYECTO_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "ASOCIAR_FUNCIONARIOS");
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_FUNCIONARIO_CLAVE_ENTIDAD,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_CREAR_PROYECTO_FUNCIONARIO_CLAVE_ENTIDAD_INCORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

}
