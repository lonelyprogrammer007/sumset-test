package co.gov.sdp.spdd.core.ip.controller.ippot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.ip.controller.ipformulacion.IPFormulacionController;
import co.gov.sdp.spdd.core.ip.icontroller.ippot.IIPPotController;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIConsultarService;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIEliminarService;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIModificarService;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIRegistrarService;

@RestController
public class IPPotController implements IIPPotController {

	@Autowired
	IIPPotIRegistrarService iPPotRegistrarService;

	@Autowired
	IIPPotIConsultarService iPPotConsultarService;

	@Autowired
	IIPPotIModificarService iPPotModificarService;

	@Autowired
	IIPPotIEliminarService iPPotEliminarService;

	@Autowired
	SPDDLogger spddLogger;

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS)
	@Override
	public GenericoDTO consultarTodosPotPorFiltro(@RequestBody PotDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.potObtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_AGREGAR_POT)
	@Override
	public PotDTO registrarPot(@RequestBody PotDTO peticion) throws SpddExceptions {
		peticion.setIdPot(null);
		peticion.setEstado(NHSPDDConstantes.POT_ESTADO_VIGENTE);
		PotDTO respuesta = new PotDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarIpPot();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarIpPot(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotRegistrarService.registrarPot(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el
	 * mensaje correspondiente en cada uno de los casos para la respuesta de las
	 * peticiones
	 * 
	 * @param respuesta             objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta       objeto de tipo Integer que representa el codigo
	 *                              de la respuesta
	 * @param msgRespuesta          String que representa el mensaje de respuesta
	 *                              que se va a retornar
	 * @param paqueteMensaje        objeto de tipo PaqueteMensajeEnum que representa
	 *                              el paquete donde se encuentra el mensaje
	 * @param lenguaje
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta,
			PaqueteMensajeEnum paqueteMensaje, String lenguaje, List<CampoValidacionDTO> getMsgCampoValidacion) {
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta, paqueteMensaje, lenguaje));

		respuesta.setLstCampoValidacion(getMsgCampoValidacion);

	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para el log.
	 * 
	 * @param msgLogger      string que represente el mensaje que tendra el log
	 * @param severidadLoger String que representa la severidad del log
	 * @param nombreClase    String que representa el nombre de la clase donde se
	 *                       presenta el log
	 * @param lenguaje
	 */
	private void mensajeLogger(String msgLogger, String severidadLoger, Class<?> nombreClase, String lenguaje) {
		spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(msgLogger, lenguaje), severidadLoger, nombreClase);
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT)
	@Override
	public PotDTO modificarPot(@RequestBody PotDTO peticion) throws SpddExceptions {

		PotDTO respuesta = new PotDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarIpPot();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarIpPot(peticion, camposAValidar);

		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotModificarService.modificarPot(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_RAMA_OBTENER_TODOS)
	@Override
	public GenericoDTO consultarTodosPotRamaPorIdPot(@PathVariable("id") Long idPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.consultarTodosRamaPotPorIdPot(idPot);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_RAMA_OBTENER_TODOS_DESC)
	@Override
	public GenericoDTO consultarTodosPotRamaPorIdPotNumeroRamaDesc(@PathVariable("id") Long idPot)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.consultarTodosPotRamaPorIdPotNumeroRamaDesc(idPot);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_RAMA_REGISTRAR_RAMA)
	@Override
	public PotRamaDTO registrarPotRama(@RequestBody PotRamaDTO peticion) throws SpddExceptions {
		PotRamaDTO respuesta = new PotRamaDTO();
		try {
			respuesta = iPPotRegistrarService.registrarRamaPot(peticion);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_RAMA_ELIMINAR_RAMA)
	@Override
	public PotRamaDTO eliminarPotRama(@PathVariable("id") Long idPotRama) throws SpddExceptions {
		PotRamaDTO respuesta = new PotRamaDTO();
		try {
			respuesta = iPPotEliminarService.eliminarPotRama(idPotRama);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_NIVEL_POT_RAMA_OBTENER_TODOS)
	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(@PathVariable("id") Long idRamaPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.obtenerTodosNivelPotPorIdRamaPot(idRamaPot);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_NIVEL_POT_NIVEL_OBTENER_TODOS)
	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(@PathVariable("id") Long idNivelPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.obtenerTodosNivelPotPorIdNivel(idNivelPot);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_NIVEL_REGISTRAR_NIVEL)
	@Override
	public PotNivelDTO guardarNivelPotDTO(@RequestBody PotNivelDTO peticion) throws SpddExceptions {

		PotNivelDTO respuesta = new PotNivelDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPotNivel();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotNivel(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotRegistrarService.registrarPotNivel(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;

	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_NIVEL_ELIMINAR_NIVEL)
	@Override
	public PotNivelDTO eliminarPotNivel(@PathVariable("id") Long idNivelPot) throws SpddExceptions {
		PotNivelDTO respuesta = new PotNivelDTO();
		try {
			respuesta = iPPotEliminarService.eliminarNivelDTO(idNivelPot);
		} catch (Exception e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_OBRA_POR_ID_NIVEL_POT)
	@Override
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(@RequestBody PotObraDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBRA_REGISTRAR_POT_OBRA)
	@Override
	public PotObraDTO registrarPotObra(@RequestBody PotObraDTO peticion) throws SpddExceptions {
		peticion.setIdObraProyPot(null);
		PotObraDTO respuesta = new PotObraDTO();

		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPotObra();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotObra(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotRegistrarService.registrarPotObra(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBRA_MODIFICAR_POT_OBRA)
	@Override
	public PotObraDTO modificarPotObra(@RequestBody PotObraDTO peticion) {
		PotObraDTO respuesta = new PotObraDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPotObra();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotObra(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotModificarService.modificarPotObra(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class,
					respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBRA_ELIMINAR_POT_OBRA)
	@Override
	public PotObraDTO eliminarPotObra(@PathVariable("id") Long idPotObra) {
		PotObraDTO respuesta = new PotObraDTO();
		try {
			respuesta = iPPotEliminarService.eliminarPotObra(idPotObra);
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class,
					respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_INSTUMENTO_POR_ID_POT)
	@Override
	public GenericoDTO consultarTodosPotInstrumentoPorIdPotFiltrado(@RequestBody PotInstrumentoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.obtenerPotIntrumentoPorIdPot();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotIntrumento(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotConsultarService.consultarTodosPotInstrumentoFiltrado(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_REGISTRAR_POT_INSTUMENTO)
	@Override
	public PotInstrumentoDTO registrarPotInstrumento(@RequestBody PotInstrumentoDTO peticion) {
		peticion.setIdInstrumentoPot(null);
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();

		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registraPotInstrumento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotIntrumento(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotRegistrarService.registrarPotInstrumento(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT_INSTUMENTO)
	@Override
	public PotInstrumentoDTO modificarPotInstrumento(@RequestBody PotInstrumentoDTO peticion) {
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();

		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPotInstrumento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotIntrumento(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotModificarService.modificarPotInstrumento(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_ELIMINAR_POT_INSTUMENTO)
	@Override
	public PotInstrumentoDTO eliminarPotInstrumento(@PathVariable("id") Long idPotInstrumento) {
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		try {
			respuesta = iPPotEliminarService.eliminarPotInstrumento(idPotInstrumento);
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL, IPPotController.class,
					respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}


	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT_NIVEL)
	@Override
	public PotNivelDTO modificarPotNivel(@RequestBody PotNivelDTO peticion) {
		PotNivelDTO respuesta = new PotNivelDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPotNivel();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPotNivel(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = iPPotModificarService.modificarPotNivel(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_POT_NIVEL)
	@Override
	public GenericoDTO consultarTodosPotObraPorPot(@RequestBody PotObraDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = iPPotConsultarService.consultarTodosPotObraPorPot(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_ESTADO_POT_NIVEL)
	@Override
	public PotNivelDTO modificarEstadoNivel(@PathVariable("idnivel") Long idNivel,
			@PathVariable("cerrado") Long cerrado) {
		PotNivelDTO respuesta = new PotNivelDTO();
		try {
			respuesta = iPPotModificarService.cerrarNIvel(idNivel, cerrado);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_ESTADO_RAMA)
	@Override
	public PotRamaDTO modificarEstadoRama(@PathVariable("idrama") Long idRama, @PathVariable("cerrado") Long cerrado) {
		PotRamaDTO respuesta = new PotRamaDTO();
		try {
			respuesta = iPPotModificarService.cerrarRama(idRama, cerrado);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPotController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

}
