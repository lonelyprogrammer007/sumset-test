package co.gov.sdp.spdd.core.controller.autenticacion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.autenticacion.IUsuarioAutenticacionController;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionConsultar;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar;

/**
 * Clase para manejar las peticiones relacionadas con usuarios para el modulo
 * autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8", path = "/core/autenticacion/")
public class UsuarioAutenticacionController implements IUsuarioAutenticacionController {

	// Servicio de consulta para autenticacion
	@Autowired
	IAutenticacionConsultar consultar;

	// Servicio de modificacion para autenticacion
	@Autowired
	IAutenticacionModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo cambiarClave
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.autenticacion.IUsuarioAutenticacionController.cambiarClave
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_AUTENTICACION_POST_CAMBIAR_CLAVE)
	@Override
	public UsuariosDTO cambiarClave(@RequestBody UsuariosDTO peticion) {
		UsuariosDTO respuesta = new UsuariosDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.cambiarClaveObtenerCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarUsuario(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.cambiarClave(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, UsuarioAutenticacionController.class, respuesta.getLenguaje());
			}
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, UsuarioAutenticacionController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo iniciarSesion
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.autenticacion.IUsuarioAutenticacionController.iniciarSesion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_AUTENTICACION_POST_INICIAR_SESION)
	@Override
	public UsuariosDTO iniciarSesion(@RequestBody UsuariosDTO peticion) {
		UsuariosDTO respuesta = new UsuariosDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.iniciarSesionObtenerCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarUsuario(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = consultar.iniciarSesion(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, UsuarioAutenticacionController.class, respuesta.getLenguaje());
			}
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, UsuarioAutenticacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo restablecer clave
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.autenticacion.IUsuarioAutenticacionController.restablecerClave
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_GET_ENVIAR_CORREO)
	@Override
	public UsuariosDTO restablecerClave(@RequestBody UsuariosDTO peticion) {
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.restablcerCorreoCamposValidacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarUsuario(peticion, camposAValidar);
		UsuariosDTO respuesta = new UsuariosDTO();
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.reestablecerContrasenia(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, UsuarioAutenticacionController.class, respuesta.getLenguaje());
			}
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, UsuarioAutenticacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el mensaje correspondiente en cada 
	 * uno de los casos para la respuesta de las peticiones
	 * @param respuesta objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta objeto de tipo Integer que representa el codigo de la respuesta
	 * @param msgRespuesta String que representa el mensaje de respuesta que se va a retornar
	 * @param paqueteMensaje objeto de tipo PaqueteMensajeEnum que representa el paquete donde se encuentra el mensaje
	 * @param lenguaje 
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta, PaqueteMensajeEnum paqueteMensaje, String lenguaje,List<CampoValidacionDTO> getMsgCampoValidacion)
	{
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta,
				paqueteMensaje, lenguaje));
		
		respuesta.setLstCampoValidacion(getMsgCampoValidacion);
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para el log.
	 * @param msgLogger string que represente el mensaje que tendra el log
	 * @param severidadLoger String que representa la severidad del log
	 * @param nombreClase String que representa el nombre de la clase donde se presenta el log
	 * @param lenguaje
	 */
	private void mensajeLogger(String msgLogger, String severidadLoger, Class<?> nombreClase, String lenguaje)
	{
		spddLogger.mensajeLogger(
				NHSPDDMensajes.obtenerMensaje(msgLogger, lenguaje),
				severidadLoger, nombreClase);
	}
}
