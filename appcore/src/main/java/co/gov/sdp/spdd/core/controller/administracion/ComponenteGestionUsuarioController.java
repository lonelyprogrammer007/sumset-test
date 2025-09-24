package co.gov.sdp.spdd.core.controller.administracion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioRegistrar;
import co.gov.sdp.spdd.core.iservice.administracion.IPddNivelAtributoConsultar;

/**
 * Clase que maneja los componentes de gestion usuario
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ComponenteGestionUsuarioController implements IComponenteGestionUsuarioController {

	/**
	 * Objeto que maneja los metodos de registrar de componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioRegistrar registrar;

	/**
	 * Objeto que maneja los metodos de eliminar de componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioEliminar eliminar;

	/**
	 * Objeto que maneja los metodos de consulta de componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioConsultar consultar;

	/**
	 * Objeto que maneja los metodos de consulta de pddNivelAtributoConsultar
	 */
	@Autowired
	IPddNivelAtributoConsultar consultarNivelAtributo;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo asignarComponente
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController.asignarComponente
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_POST_ASIGNAR_COMPONENTE_GESTION_USUARIO)
	@Override
	public ComponenteGestionUsuarioDTO asignarComponente(
			@RequestBody ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws JsonProcessingException {
		ComponenteGestionUsuarioDTO respuesta = new ComponenteGestionUsuarioDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.asignarComponenteUsuario();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarComponenteGestionUsuario(componenteGestionUsuarioDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.crearGestionComponenteUsuario(componenteGestionUsuarioDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, componenteGestionUsuarioDTO.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGestionUsuarioController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo remover componenteUsuario
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController.removerComponenteUsuario
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_REMOVER_COMPONENTE_GESTION_USUARIO)
	@Override
	public ComponenteGestionUsuarioDTO removerComponenteUsuario(
			@RequestBody ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) {
		ComponenteGestionUsuarioDTO respuesta = new ComponenteGestionUsuarioDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.removerComponenteUsuario();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarComponenteGestionUsuario(componenteGestionUsuarioDTO, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = eliminar.eliminarComponenteUsuario(componenteGestionUsuarioDTO);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, componenteGestionUsuarioDTO.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGestionUsuarioController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class, respuesta.getLenguaje());

		}

		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerComponenteGestionUsuario
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController.obtenerComponenteGestionUsuario
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTES_USUARIO)
	@Override
	public GenericoDTO obtenerComponenteGestionUsuario(@RequestBody UsuariosDTO usuariosDTO) {
		GenericoDTO respuesta = new GenericoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.obtenerUsuarioComponente();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarUsuario(usuariosDTO,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = consultar.obtenerPorUsuario(usuariosDTO);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
						PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje()));
				respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());

				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, ComponenteGestionUsuarioController.class);
			}
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerListaComponentes
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController.obtenerListaComponentes
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO)
	@Override
	public GenericoDTO obtenerListaComponentesUsuario() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTodos();
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_INFO, ComponenteGestionUsuarioController.class);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerComponenteLibre
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.administracion.IComponenteGestionUsuarioController.obtenerComponenteLibre
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTE_LIBRE)
	@Override
	public GenericoDTO obtenerComponenteLibre() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultarNivelAtributo.pddNivelAtributoObtenerLibres();
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_INFO, ComponenteGestionUsuarioController.class);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGestionUsuarioController.class);
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
