package co.gov.sdp.spdd.core.bp.controller.bpproyinv;

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
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.bp.icontroller.bpproyinv.IBPProyInvController;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvConsultarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvEliminarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvModificarService;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvRegistrarService;
import co.gov.sdp.spdd.core.ip.controller.ipformulacion.IPFormulacionController;
import co.gov.sdp.spdd.core.ip.controller.ippot.IPPotController;

/**
 * Controlador del modulo BP que se expone para ser consumido. Condensara los
 * Capítulos 8,9,10 y 11.
 * 
 * @author DANIEL, BRYAN, SEBASTIAN
 * @version 1.0 01/04/2020
 */
@RestController
public class BPProyInvController implements IBPProyInvController {

	/**
	 * Inyeccion del Service de registro
	 */
	@Autowired
	IBPProyInvRegistrarService bPProyInvRegistrarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@Autowired
	IBPProyInvConsultarService bPProyInvConsultarService;

	/**
	 * Inyeccion del Service de modificar
	 */
	@Autowired
	IBPProyInvModificarService bPProyInvModificarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@Autowired
	IBPProyInvEliminarService bPProyInvEliminarService;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_FILTRO_BP_PROYECTO_INVERSION)
	@Override
	public GenericoDTO consultarBpProyectoInversionPorFiltro(@RequestBody BpProyectoInversionDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarBpProyectoInversionPorFiltro(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROYECTO_INVERSION)
	@Override
	public GenericoDTO consultarProyectoInversionTodos(@RequestBody BpProyectoInversionDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarProyectoInversionTodos(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_SIN_RELACION_CON_PROYECTO_INVERSION)
	@Override
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(
			@PathVariable("id") Long idProyecto) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService
					.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION)
	@Override
	public BpProyectoInversionDTO registrarProyectoInversionTABIndentificacionProyecto(@RequestBody BpProyectoInversionDTO peticion) {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarProyectoInversionTABIndentificacionProyecto();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarProyectoInversionTABIndentificacionProyecto(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion);
			} else {

				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_BP_APORTE_CIUDADANO_CARGADOS_POR_ARCHIVO_CON_BP_PROYECTO_INVERSION)
	@Override
	public BpProyInvAporteDTO registrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion(
			@RequestBody BpProyInvAporteDTO peticion) {
		peticion.setIdProyAporte(null);
		BpProyInvAporteDTO respuesta = new BpProyInvAporteDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvAporte();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvAporte(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvAporte(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_BP_PROYECTO_INVERSION_POR_ID)
	@Override
	public BpProyectoInversionDTO consultarBpProyectoInversionPorId(@PathVariable("id") Long idProyecto)
			throws SpddExceptions {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarBpProyectoInversionPorId(idProyecto);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_APORTE_CIUDADANO)
	@Override
	public BpAporteCiudadanoDTO registrarBpAporteCiudadano(@RequestBody BpAporteCiudadanoDTO peticion)
			throws SpddExceptions {
		peticion.setIdAporte(null);
		peticion.setIdArchivo(peticion.getIdArchivo() == null ? -1 : peticion.getIdArchivo());
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpAporteCiudadano();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpAporteCiudadano(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_APORTE_POR_ID_PROYECTO_INVERSION)
	@Override
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(
			@RequestBody BpProyInvAporteDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_BP_APORTE_CIUDADANO_POR_ID)
	@Override
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(@PathVariable("id") Long idAporte) {
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarBpAporteCiudadanoPorId(idAporte);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_APORTE)
	@Override
	public BpProyInvAporteDTO eliminarBpProyInvAporte(@PathVariable("id") Long idProyAporte) {
		BpProyInvAporteDTO respuesta = new BpProyInvAporteDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarBpProyInvAporte(idProyAporte);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_APORTE_CIUDADANO)
	@Override
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(@RequestBody BpAporteCiudadanoDTO peticion) {
		peticion.setIdArchivo(peticion.getIdArchivo() == null ? -1 : peticion.getIdArchivo());
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarBpAporteCiudadano();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpAporteCiudadano(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpAporteCiudadano(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_CON_RELACION_CON_PORYECTO_INVERSION)
	@Override
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(
			@PathVariable("id") Long idProyecto) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService
					.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_CARGADO_POR_ARCHIVO)
	@Override
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpAportesCiudadanosCargadosPorArchivos();
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROYECTO_INVERSION)
	@Override
	public BpProyectoInversionDTO modificarBpProyectoInversion(@RequestBody BpProyectoInversionDTO peticion) {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		try {
			respuesta = bPProyInvModificarService.modificarProyectoInversion(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_FILTRO_BP_INICIATIVA_CIUDADANA_VIABLE)
	@Override
	public GenericoDTO consultarTodosBpIniciativaCiudadanaViablesFiltradas(
			@RequestBody BpIniciativaCiudadanaDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpIniciativaCiudadanaViablesFiltradas(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_INICIATIVA_CIUDADANA_CON_RELACION_CON_PORYECTO_INVERSION)
	@Override
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(
			@PathVariable("id") Long idProyecto) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService
					.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_BP_INICIATIVA_CIUDADANA_VIABLES_CON_BP_PROYECTO_INVERSION)
	@Override
	public BpProyInvIniciativaDTO registrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion(
			@RequestBody BpProyInvIniciativaDTO peticion) {
		peticion.setIdIniciativaInv(null);
		BpProyInvIniciativaDTO respuesta = new BpProyInvIniciativaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvIniciativa();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvIniciativa(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROYECTO_INVERSION)
	@Override
	public BpProyectoInversionDTO eliminarBpProyectoInversion(@PathVariable("id") Long idProyectoInversion)
			throws SpddExceptions {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarBpProyectoInversion(idProyectoInversion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_LOCALIZA)
	@Override
	public BpProyectoInversionDTO registrarBpProyectoInversionLocaliza(
			@RequestBody BpProyInvLocalizaYTerritorizacionDTO peticion) {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		try {
			respuesta = bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_ESPECIF)
	@Override
	public BpProyInvEspecifDTO registrarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions {
		BpProyInvEspecifDTO respuesta = new BpProyInvEspecifDTO();
		try {
			respuesta = bPProyInvRegistrarService.registrarBpProyInvEspecif(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_META_PLAN)
	@Override
	public BpProyInvMetaPlanDTO registrarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions {
		BpProyInvMetaPlanDTO respuesta = new BpProyInvMetaPlanDTO();
		try {
			respuesta = bPProyInvRegistrarService.registrarBpProyInvMetaPlan(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_PRODUCTO)
	@Override
	public BpProyInvProductoDTO registrarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions {
		BpProyInvProductoDTO respuesta = new BpProyInvProductoDTO();
		try {
			respuesta = bPProyInvRegistrarService.registrarBpProyInvProducto(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_ACTIVIDAD)
	@Override
	public BpProyInvActividadDTO registrarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions {
		BpProyInvActividadDTO respuesta = new BpProyInvActividadDTO();
		try {
			respuesta = bPProyInvRegistrarService.registrarBpProyInvActividad(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_OBTENER_TODAS_FUENTES_FINANCIACION_PYOYECT)
	@Override
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(
			@RequestBody BpProyInvFinanciaDTO peticion) throws SpddExceptions {

		GenericoDTO respuesta = new GenericoDTO();

		try {
			respuesta = bPProyInvConsultarService.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, IPFormulacionController.class);
		}

		return respuesta;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_REGISTRAR_FUENTE_FINANCIACION_PYOYECT)
	@Override
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(@RequestBody BpProyInvFinanciaDTO peticion)
			throws SpddExceptions {

		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvFinancia();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvFinancia(peticion,
				camposAValidar);

		try {

			if (resultadoValidacion.esValido()) {

				respuesta = bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion);

			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}

		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION)
	@Override
<<<<<<< HEAD
	public GenericoDTO consultarGruposEtarios(@RequestBody BpProyInvPoblacionDTO peticion) throws SpddExceptions {
=======
	public GenericoDTO consultarTodosProyInvPoblacionAsociadosAProyectoInversion(
			@RequestBody BpProyInvPoblacionDTO peticion) throws SpddExceptions {
>>>>>>> developer
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarGruposEtarios(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INV_POBLACION)
	@Override
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(@RequestBody BpProyInvEtnicoDTO peticion)
			throws SpddExceptions {

		GenericoDTO respuesta = new GenericoDTO();
<<<<<<< HEAD
		
		BpProyInvPoblacionDTO respuestaAuxValidacionCampos = new BpProyInvPoblacionDTO();
		respuestaAuxValidacionCampos.setIdPoblacion(peticion.getIdPoblacion());
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.listarTodosBpProyInvEtnicoAsociadosABpProyInvPoblacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPoblacion(respuestaAuxValidacionCampos, camposAValidar);
		
		if(resultadoValidacion.esValido()) {
			try {
				respuesta = bPProyInvConsultarService.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
			} catch (SpddExceptions e) {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO, NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
				mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL, IBPProyInvController.class, respuesta.getLenguaje());
			}
		}else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
					respuesta.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
			mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IPPotController.class,
					respuesta.getLenguaje());
=======
		try {
			respuesta = bPProyInvConsultarService.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
>>>>>>> developer
		}
		
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION)
	@Override
<<<<<<< HEAD
	public BpProyInvPoblacionDTO registrarBpProyInvPoblacionAsociadoAProyInversion(@RequestBody BpProyInvPoblacionDTO peticion)
			throws SpddExceptions {
		
=======
	public BpProyInvPoblacionDTO registrarVariosBpProyInvPoblacionAsociadoAProyInversion(
			@RequestBody BpProyInvPoblacionDTO peticion) throws SpddExceptions {

>>>>>>> developer
		peticion.setIdPoblacion(null);
		BpProyInvPoblacionDTO respuesta = new BpProyInvPoblacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarProyInvPoblacionAsociadoAProyInv();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPoblacion(peticion,
				camposAValidar);

		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvPoblacionAsociadoaProyInv(peticion);
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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_PROYECTO_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION)
	@Override
<<<<<<< HEAD
	public BpProyInvEtnicoDTO registrarBpProyInvEtnico(@RequestBody BpProyInvEtnicoDTO peticion)
			throws SpddExceptions {
		
		peticion.setIdEtnico(null);
		BpProyInvEtnicoDTO respuesta = new BpProyInvEtnicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarProyInvEtnico();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvEtnico(peticion, camposAValidar);
		
=======
	public BpProyInvEtnicoDTO registrarVariosBpProyInvEtnicoAsociadoAProyInversion(
			@RequestBody BpProyInvEtnicoDTO peticion) throws SpddExceptions {

		peticion.setIdEtnico(null);
		BpProyInvEtnicoDTO respuesta = new BpProyInvEtnicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarProyInvEtnicoAsociadoAProyInv();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvEtnico(peticion,
				camposAValidar);

>>>>>>> developer
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvEtnico(peticion);
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
	
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_POBLACION)
	@Override
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(@RequestBody BpProyInvPoblacionDTO peticion)
			throws SpddExceptions {
		
		BpProyInvPoblacionDTO respuesta = new BpProyInvPoblacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarProyInvPoblacionAsociadoAProyInv();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPoblacion(peticion, camposAValidar);
		
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpProyInvPoblacion(peticion);
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
	
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_ETNICO)
	@Override
	public BpProyInvEtnicoDTO modificarBpProyInvEtnico(@RequestBody BpProyInvEtnicoDTO peticion) throws SpddExceptions {
		
		BpProyInvEtnicoDTO respuesta = new BpProyInvEtnicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarProyInvEtnicoAsociadoABpProyInvPoblacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvEtnico(peticion, camposAValidar);
		
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpProyInvEtnico(peticion);
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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_POBLACION)
	@Override
	public BpProyInvPoblacionDTO eliminarBpProyInvPoblacion(@RequestBody BpProyInvPoblacionDTO peticion)
			throws SpddExceptions{
		
		BpProyInvPoblacionDTO respuesta = new BpProyInvPoblacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.eliminarProyInvPoblacionAsociadoAProyInv();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPoblacion(peticion, camposAValidar);
		
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvEliminarService.eliminarBpProyInvPoblacion(peticion);
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
	
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_POLITICA_FILTRADO)
	@Override
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(@RequestBody BpProyInvPoliticaDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpProyInvPoliticaFiltrado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PDD_POLITICA_PUBLICA_FILTRADO)
	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(@RequestBody PddPoliticaPublicaDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosPddPoliticaPublicaFiltrado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PDD_POLITICA_PUBLICA)
	@Override
	public GenericoDTO consultarTodosPddPoliticaPublica(@PathVariable("id") Long idProyInversion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosPddPoliticaPublica();
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPoliticaDTO registrarBpProyInvPolitica(@RequestBody BpProyInvPoliticaDTO peticion) {
		peticion.setIdProyPolitica(null);
		BpProyInvPoliticaDTO respuesta = new BpProyInvPoliticaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvPolitica();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPolitica(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvPolitica(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(@RequestBody BpProyInvPoliticaDTO peticion) {
		BpProyInvPoliticaDTO respuesta = new BpProyInvPoliticaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarBpProyInvPolitica();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPolitica(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpProyInvPolitica(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPoliticaDTO eliminarBpProyInvPolitica(@PathVariable("id") Long idProyPolitica) {
		BpProyInvPoliticaDTO respuesta = new BpProyInvPoliticaDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarBpProyInvPolitica(idProyPolitica);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}
	
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_LINEA)
	@Override
	public GenericoDTO consultarTodosBpProyInvLineaPorIdProyectoInversion(@PathVariable("id") Long idProyecto) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpProyInvLineaPorIdProyectoInversion(idProyecto);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvLineaDTO registrarBpProyInvLinea(@RequestBody BpProyInvLineaDTO peticion) {
		peticion.setIdProyLinea(null);
		BpProyInvLineaDTO respuesta = new BpProyInvLineaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvLinea();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvLinea(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvLinea(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvLineaDTO modificarBpProyInvLinea(@RequestBody BpProyInvLineaDTO peticion) {
		BpProyInvLineaDTO respuesta = new BpProyInvLineaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarBpProyInvLinea();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvLinea(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpProyInvLinea(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvLineaDTO eliminarBpProyInvLinea(@PathVariable("id") Long idProyLinea) {
		BpProyInvLineaDTO respuesta = new BpProyInvLineaDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarBpProyInvLinea(idProyLinea);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}
	
	

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_FINANCIA)
	@Override
	public BpProyInvFinanciaDTO eliminarProyInvFinanciaPorId(@RequestBody BpProyInvFinanciaDTO peticion) throws SpddExceptions {
		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarProyInvFinanciaPorId(peticion.getIdFuente());
		} catch (SpddExceptions e) {

			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FUENTE_FINANCIACION_NO_ENCONTRADA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_MODIFICAR_FUENTE_FINANCIACION)
	@Override
	public BpProyInvFinanciaDTO modificarFuenteFinanciacion(@RequestBody BpProyInvFinanciaDTO peticion) throws SpddExceptions {

		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();
		try {
			respuesta = bPProyInvModificarService.modificarFuenteFinanciacion(peticion);
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_FUENTE_FINANCIACION_NO_ENCONTRADA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}

		return respuesta;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION)
	@Override
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(@RequestBody BpProyInvPmrDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = bPProyInvConsultarService.consultarTodosBpProyInvPmrDTOFiltrado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPmrDTO registrarBpProyInvPmr(@RequestBody BpProyInvPmrDTO peticion) {
		peticion.setIdProyPmr(null);
		BpProyInvPmrDTO respuesta = new BpProyInvPmrDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarBpProyInvPmr();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPmr(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvRegistrarService.registrarBpProyInvPmr(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPmrDTO modificarBpProyInvPmr(@RequestBody BpProyInvPmrDTO peticion) {
		BpProyInvPmrDTO respuesta = new BpProyInvPmrDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarBpProyInvPmr();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvPmr(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvModificarService.modificarBpProyInvPmr(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), null);
				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR, IBPProyInvController.class,
						respuesta.getLenguaje());
			}
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION)
	@Override
	public BpProyInvPmrDTO eliminarBpProyInvPmr(@PathVariable("id") Long idProyPmr) {
		BpProyInvPmrDTO respuesta = new BpProyInvPmrDTO();
		try {
			respuesta = bPProyInvEliminarService.eliminarBpProyInvPmr(idProyPmr);
		} catch (JsonProcessingException e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);

			mensajeLogger(NHSPDDConstantes.MENSAJE_FATAL_JSON, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IBPProyInvController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_ETNICO)
	@Override
	public BpProyInvEtnicoDTO eliminarBpProyInvEtnico(@RequestBody BpProyInvEtnicoDTO peticion) throws SpddExceptions {
	
		BpProyInvEtnicoDTO respuesta = new BpProyInvEtnicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.eliminarProyInvEtnicoAsociadoAProyInvPoblacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarBpProyInvEtnico(peticion, camposAValidar);
		
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = bPProyInvEliminarService.eliminarBpProyInvEtnico(peticion);
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

	
	
}
