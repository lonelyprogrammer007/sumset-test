package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.controller.administracion.ComponenteGestionUsuarioController;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEstructuraMetaAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con estructura meta para el
 * modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class EstructuraMetaAdministracionController implements IEstructuraMetaAdministracionController {

	// Servicio de consulta para estructura meta
	@Autowired
	IEstructuraMetaAdministracionConsultar consultar;


	// Servicio de modificacion para estructura meta
	@Autowired
	IEstructuraMetaAdministracionModificar modificar;

	// Servicio de registro para estructura meta
	@Autowired
	IEstructuraMetaAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearEstructuraMeta
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEstructuraMetaAdministracionController.crearEstructuraMeta
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_ESTRUCTURA_METAS)
	@Override
	public EstructuraMetaDTO crearEstructuraMeta(@RequestBody EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException {
		peticion.setEstado(NHSPDDConstantes.ESTADO_ACTIVO);
		EstructuraMetaDTO respuesta = new EstructuraMetaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearEstructuraMeta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarEstrucuraMeta(peticion,
				camposAValidar);
		if (resultadoValidacion.esValido()) {
			respuesta = registrar.crearEstructuraMeta(peticion);
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
		}
		return respuesta;
	}


	/**
	 * Implementacion del metodo modificarEstructuraMeta
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEstructuraMetaAdministracionController.modificarEstructuraMeta
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_ESTRUCTURA_METAS)
	@Override
	public EstructuraMetaDTO modificarEstructuraMeta(@RequestBody EstructuraMetaDTO peticion) throws SpddExceptions {
		EstructuraMetaDTO respuesta = new EstructuraMetaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarEstructuraMeta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarEstrucuraMeta(peticion,
				camposAValidar);
		if (resultadoValidacion.esValido()) {
			respuesta = modificar.modificarEstructuraMeta(peticion);
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEstructuraMetaTodos
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEstructuraMetaAdministracionController.obtenerEstructuraMetaTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_ESTRUCTURA_METAS_TODOS)
	@Override
	public GenericoDTO obtenerEstructuraMetaTodos(@RequestBody EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class);
		}
		return respuesta;
	}

}
