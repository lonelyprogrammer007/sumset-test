package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IComponenteGastoAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con componentes de gasto para
 * el modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class ComponenteGastoAdministracionController implements IComponenteGastoAdministracionController {

	// Servicio de consulta para componente gasto
	@Autowired
	IComponenteGastoAdministracionConsultar consultar;

	// Servicio de eliminacion para componente gasto

	// Servicio de modificacion para componente gasto
	@Autowired
	IComponenteGastoAdministracionModificar modificar;

	// Servicio de registro para componente gasto
	@Autowired
	IComponenteGastoAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearComponenteGasto
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IComponenteGastoAdministracionController.crearComponenteGasto
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_COMPONENTE_GASTO)
	@Override
	public ComponenteGastoDTO crearComponenteGasto(@RequestBody ComponenteGastoDTO peticion) throws JsonProcessingException {
		peticion.setEstado(NHSPDDConstantes.ESTADO_ACTIVO);
		peticion.setIdComponenteGasto(null);
		ComponenteGastoDTO respuesta = new ComponenteGastoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearComponenteGasto();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarComponenteGasto(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearComponenteGasto(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGastoAdministracionController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoAdministracionController.class);
		}
		return respuesta;
	}


	/**
	 * Implementacion del metodo modificarComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IComponenteGastoAdministracionController.modificarComponenteGasto
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_COMPONENTE_GASTO)
	@Override
	public ComponenteGastoDTO modificarComponenteGasto(@RequestBody ComponenteGastoDTO peticion) {
		ComponenteGastoDTO respuesta = new ComponenteGastoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarComponenteGasto();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarComponenteGasto(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarComponenteGasto(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGastoAdministracionController.class);
			}
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoAdministracionController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerComponenteGastoTodos
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IComponenteGastoAdministracionController.obtenerComponenteGastoTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_COMPONENTE_GASTO_TODOS)
	@Override
	public GenericoDTO obtenerComponenteGastoTodos(@RequestBody ComponenteGastoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerComponenteGastoTodos(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoAdministracionController.class);
		}
		return respuesta;
	}
}