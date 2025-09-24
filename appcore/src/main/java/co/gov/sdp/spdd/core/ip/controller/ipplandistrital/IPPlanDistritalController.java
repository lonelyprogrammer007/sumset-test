package co.gov.sdp.spdd.core.ip.controller.ipplandistrital;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.ip.controller.ipformulacion.IPFormulacionController;
import co.gov.sdp.spdd.core.ip.icontroller.ipplandistrital.IIPPlanDistritalController;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalConsultarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalEliminarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalModificarService;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalRegistrarService;

/**
 * Controlador del modulo IP que se expone para ser consumido. Condensara los
 * Capítulos 10, 11, 14, 15, 16 y 17.
 * 
 * @author DANIEL, SEBASTIAN
 * @version 1.0 02/03/2020
 */
@RestController
public class IPPlanDistritalController implements IIPPlanDistritalController {

	/**
	 * Inyeccion del Service de registro
	 */
	@Autowired
	IIPPlanDistritalRegistrarService ipPlanDistritalRegistrarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@Autowired
	IIPPlanDistritalConsultarService ipPlanDistritalConsultarService;

	/**
	 * Inyeccion del Service de modificar
	 */
	@Autowired
	IIPPlanDistritalModificarService ipPlanDistritalModificarService;

	/**
	 * Inyeccion del Service de eliminar
	 */
	@Autowired
	IIPPlanDistritalEliminarService ipPlanDistritalEliminarService;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD)
	@Override
	public GenericoDTO consultarTodosPddPorFiltro(@RequestBody PddDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.pddObtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD)
	@Override
	public PddDTO registrarPdd(@RequestBody PddDTO peticion) throws SpddExceptions {
		peticion.setIdPlanDesarrollo(null);
		peticion.setVersion(NHSPDDConstantes.VERSION_INICIAL);
		PddDTO respuesta = new PddDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPdd();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdd(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarPdd(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD)
	@Override
	public PddDTO modificarPdd(@RequestBody PddDTO peticion) throws SpddExceptions {

		PddDTO respuesta = new PddDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPdd();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdd(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalModificarService.modificarPdd(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_POR_ID_PLAN_DESARROLLO)
	@Override
	public GenericoDTO consultarTodosPddNivelPorIdPlanDesarrolloDistrital(@PathVariable("id") Long idPlan)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosPddNivelPorIdPlanDesarrolloDistrital(idPlan);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL)
	@Override
	public GenericoDTO registrarPddNivel(@RequestBody PddNivelDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddNivel();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddNivel(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido() && validarPddNivelCamposConcatenados(peticion)) {
				respuesta = ipPlanDistritalRegistrarService.registrarPddNivel(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}

		return respuesta;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL_ATRIBUTO)
	@Override
	public PddNivelAtributoDTO registrarPddNivelAtributo(@RequestBody PddNivelAtributoDTO peticion)
			throws SpddExceptions {
		peticion.setIdAtributo(null);

		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddNivelAtributo();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddNivelAtributo(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_META_RESULTADO_POR_PROYECTO)
	@Override
	public GenericoDTO consultarPddMetaResultadoPorProyecto(@RequestBody PddMetaResultadoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPddMetaResultadoProyectoEstrategico(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL)
	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(@RequestBody PddNivelAtributoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_META_RESULTADO)
	@Override
	public PddMetaResultadoDTO eliminarMetaResultadoPorId(@PathVariable("id") Long idMetaResultado)
			throws SpddExceptions {

		PddMetaResultadoDTO respuesta = new PddMetaResultadoDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarMetaResultado(idMetaResultado);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
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

	/**
	 * Metodo que perite validar que en las concatenaciones no venga ningun null
	 * para asegurar que todos los niveles fueron llenados
	 * 
	 * @param peticion objeto de tipo PddNivelDTO que contiene la informacion a
	 *                 validar
	 * @return true si contiene toda la informacion y false de lo contrario
	 */
	private boolean validarPddNivelCamposConcatenados(PddNivelDTO peticion) {
		boolean validador = !peticion.getCodNivelConcatenados().contains("null")
				&& !peticion.getDescripcionConcatenados().contains("null")
				&& !peticion.getObligatorioPdlConcatenados().contains("null");

		if (peticion.getIdPddNivelConcatenados() != null && !peticion.getIdPddNivelConcatenados().equals("")) {
			validador = validador && !peticion.getIdPddNivelConcatenados().contains("null");
		}

		return validador;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_POR_FILTRO_PDL)
	@Override
	public GenericoDTO consultarTodosPdlPorFiltro(@RequestBody PdlDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.pdlObtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL)
	@Override
	public GenericoDTO consultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosPlanDesarrolloLocal();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_POR_ID)
	@Override
	public PdlDTO consultarPlanDesarrolloLocalPorId(@PathVariable("id") Long idPlanLocal) throws SpddExceptions {
		PdlDTO respuesta = new PdlDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPlanDesarrolloLocalPorId(idPlanLocal);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDL)
	@Override
	public PdlDTO registrarPdl(@RequestBody PdlDTO peticion) throws SpddExceptions {
		peticion.setIdPlanLocal(null);
		PdlDTO respuesta = new PdlDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPdl();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdl(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarPdl(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDL)
	@Override
	public PdlDTO modificarPdl(@RequestBody PdlDTO peticion) throws SpddExceptions {
		PdlDTO respuesta = new PdlDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPdl();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdl(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalModificarService.modificarPdl(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_NIVEL_POR_ID_PLAN_LOCAL)
	@Override
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(@PathVariable("id") Long idPlanLocal) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPdlNivelPorIdPlanLocal(idPlanLocal);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE)
	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(
			@RequestBody PddNivelAtributoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService
					.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion);

		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_RESULTADO_INDICADOR)
	@Override
	public PddMRIndicadorDTO registrarIndicadorMetaResultado(@RequestBody PddIndicadorDTO peticion)
			throws SpddExceptions {
		PddMRIndicadorDTO respuesta = new PddMRIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddIndicador(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class);
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;

	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_INDICADOR_META_RESULTADO)
	@Override
	public PddMRIndicadorDTO eliminarMetaResultadoIndicador(@PathVariable("id") Long id) {
		PddMRIndicadorDTO respuesta = new PddMRIndicadorDTO();

		try {
			respuesta = ipPlanDistritalEliminarService.eliminarIndicadorMetaResultado(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADOR_META_RESULTADO)
	@Override
	public GenericoDTO consultarIndicadorMetaResultado(@RequestBody PddMRIndicadorDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPddIndicadorMetaResultado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_POR_MR)
	@Override
	public GenericoDTO consultarMetaProductoPorMR(@RequestBody PddMetaProductoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarMetaProductoPorMR(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_META_PRODUCTO)
	@Override
	public PddMetaProductoDTO eliminarMetaProducto(@PathVariable("id") Long id) {
		PddMetaProductoDTO respuesta = new PddMetaProductoDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarMetaProducto(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_META_PRODUCTO)
	@Override
	public PddMetaProductoDTO modificarMetaProducto(@RequestBody PddMetaProductoDTO peticion) {
		PddMetaProductoDTO respuesta = new PddMetaProductoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarMetaProducto();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarpddMetaProducto(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalModificarService.modificarMetaProducto(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class);
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO)
	@Override
	public PddMetaProductoDTO registrarMetaProducto(@RequestBody PddMetaProductoDTO peticion) {
		PddMetaProductoDTO respuesta = new PddMetaProductoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarMetaProducto();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarpddMetaProducto(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarMetaProducto(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class);
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_INDICADOR)
	@Override
	public GenericoDTO consultarIndicadoresMetaProducto(@RequestBody PddMpIndicadorDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarIndicadoresMetaProducto(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO_INDICADOR)
	@Override
	public PddMpIndicadorDTO registrarIndicadorMetaProducto(@RequestBody PddIndicadorDTO peticion) {
		PddMpIndicadorDTO respuesta = new PddMpIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddIndicador(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class);
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_META_PRODUCTO_INDICADOR)
	@Override
	public PddMpIndicadorDTO eliminarIndicadorMp(@PathVariable("id") Long idIndicadorMp) {
		PddMpIndicadorDTO respuesta = new PddMpIndicadorDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarMpIndicador(idIndicadorMp);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_NIVEL_ATRIBUTO)
	@Override
	public PddNivelAtributoDTO modificarPddNivelAtributo(@RequestBody PddNivelAtributoDTO peticion)
			throws SpddExceptions {
		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddNivelAtributo();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddNivelAtributo(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalModificarService.modificarPddNivelAtributo(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_NIVEL_ATRIBUTO)
	@Override
	public PddNivelAtributoDTO eliminarPddNivelAtributo(@PathVariable("id") Long idAtributo) {
		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarPddNivelAtributo(idAtributo);
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_COPIAR_ESTRUCTURA_DE_PDD_A_PDL)
	@Override
	public GenericoDTO copiarEstructuraPddToPdl(@RequestBody PdlDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.copiarEstructuraPddToPdl();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdl(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.copiarEstructuraPddToPdl(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class, respuesta.getLenguaje());
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
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL_NIVEL_ATRIBUTO_POR_ID_NIVEL)
	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(@RequestBody PdlNivelAtributoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE)
	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(@RequestBody PdlNivelAtributoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDL_NIVEL_ATRIBUTO)
	@Override
	public PdlNivelAtributoDTO registrarPdlNivelAtributo(@RequestBody PdlNivelAtributoDTO peticion)
			throws SpddExceptions {
		peticion.setIdAtributo(null);
		PdlNivelAtributoDTO respuesta = new PdlNivelAtributoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPdlNivelAtributo();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPdlNivelAtributo(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarPdlNivelAtributo(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_ENTIDADES)
	@Override
	public GenericoDTO consultarTodosMpEntidades(@RequestBody PddMpIndicadorEntidadDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosMpEntidad(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_COMPONENTES_DESBALANCEADOS_NIVELES_POR_ID_PLAN_DESARROLLO)
	@Override
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(@PathVariable("id") Long idPlanDesarrollo) {
		ArbolCompromisoDTO respuesta = new ArbolCompromisoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_RANGO_PONDERACION)
	@Override
	public GenericoDTO consultarTodosPddRangoPonderacion() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosRangoPonderacion();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_RANGO_PONDERACION_POR_ID_PDD)
	@Override
	public GenericoDTO consultarRangoPonderacionPorIdPdd(@PathVariable("id") Long id) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarRangoPonderacionPorIdPdd(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_RANGO_PONDERACION)
	@Override
	public PddRangoPonderacionDTO eliminarPddRangoPonderacion(@PathVariable("id") Long id) throws SpddExceptions {
		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarPddRangoPonderacion(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_RANGO_PONDERACION)
	@Override
	public PddRangoPonderacionDTO registrarPddRangoPonderacion(@RequestParam("logo") MultipartFile logo,
			@RequestParam("rango") String rango, @RequestParam("descripcion") String descripcion,
			@RequestParam("idPlanDesarrollo") Long idPlanDesarrollo) throws SpddExceptions {
		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
		System.out.println(logo.getOriginalFilename());
		System.out.println(descripcion);
		try {
			respuesta = ipPlanDistritalRegistrarService.registrarPddRangoPonderacion(null, logo, rango, descripcion,
					idPlanDesarrollo);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_RANGO_PONDERACION)
	@Override
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(@RequestParam("idRango") Long idRango,
			@RequestParam("logo") MultipartFile logo, @RequestParam("rango") String rango,
			@RequestParam("descripcion") String descripcion, @RequestParam("idPlanDesarrollo") Long idPlanDesarrollo)
			throws SpddExceptions, IOException {
		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
		try {
			respuesta = ipPlanDistritalModificarService.modificarPddRangoPonderacion(idRango, logo, rango, descripcion,
					idPlanDesarrollo);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_MP_INDICADOR_ENTIDAD)
	@Override
	public PddMpIndicadorEntidadDTO registrarMpIndicadorEntidad(@RequestBody PddMpIndicadorEntidadDTO peticion) {
		PddMpIndicadorEntidadDTO respuesta = new PddMpIndicadorEntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarMpIndicadorEntidad();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMpIndicadorEntidad(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalRegistrarService.registrarMpEntidad(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_MP_INDICADOR_ENTIDAD)
	@Override
	public PddMpIndicadorEntidadDTO modificarMpIndicadorEntidad(@RequestBody PddMpIndicadorEntidadDTO peticion) {
		PddMpIndicadorEntidadDTO respuesta = new PddMpIndicadorEntidadDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarMpIndicadorEntidad();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMpIndicadorEntidad(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipPlanDistritalModificarService.modificarMpEntidad(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPPlanDistritalController.class, respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPPlanDistritalController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_MP_INDICADOR_ENTIDAD)
	@Override
	public PddMpIndicadorEntidadDTO eliminarMpIndicadorEntidad(@PathVariable("id") Long idMpEntidad) {
		PddMpIndicadorEntidadDTO respuesta = new PddMpIndicadorEntidadDTO();
		try {
			respuesta = ipPlanDistritalEliminarService.eliminarMpEntidad(idMpEntidad);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADORES_META_PRODUCTO)
	@Override
	public GenericoDTO consultarIndicadoresMetaProducto() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarTodosIndicadoresMetaProducto();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_POR_ID)
	@Override
	public PddDTO consultarPddPorId(@PathVariable("id") Long id) {
		PddDTO respuesta = new PddDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPddPorId(id);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_VIGENTE)
	@Override
	public PdlDTO consultarPdlVigente(@PathVariable("codigoEntidad")String codigoEntidad) {
		PdlDTO respuesta = new PdlDTO();
		try {
			respuesta = ipPlanDistritalConsultarService.consultarPdlVigente(codigoEntidad);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPPlanDistritalController.class);
		}
		return respuesta;
	}

}
