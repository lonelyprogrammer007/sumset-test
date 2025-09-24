package co.gov.sdp.spdd.core.ip.controller.ipformulacion;

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
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.ip.icontroller.ipformulacion.IIPFormulacionController;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionConsultarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionEliminarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionModificarService;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionRegistrarService;

/**
 * Controlador del modulo IP que se expone para ser consumido. Condensara los
 * Capítulos 5, 6, 7, 8 y 9.
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@RestController
public class IPFormulacionController implements IIPFormulacionController {

	/**
	 * Inyeccion del Service de registro
	 */
	@Autowired
	IIPFormulacionRegistrarService ipFormulacionRegistrarService;

	/**
	 * Inyeccion del Service de consulta
	 */
	@Autowired
	IIPFormulacionConsultarService ipFormulacionConsultarService;

	/**
	 * Inyeccion del Service de modificar
	 */
	@Autowired
	IIPFormulacionModificarService ipFormulacionModificarService;

	/**
	 * Inyeccion del Service de eliminar
	 */
	@Autowired
	IIPFormulacionEliminarService ipFormulacionEliminarService;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_HISVPDDCOMPROMISO)
	@Override
	public GenericoDTO obtenerHisVPddCompromisoTodos(@RequestBody HisVPddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje(),
					null);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_COMPETENCIA_ASOCIADA)
	@Override
	public PddCompetenciaAsociadaDTO registrarModificarCompetenciaAsociada(
			@RequestBody PddCompetenciaAsociadaDTO peticion) throws SpddExceptions {

		PddCompetenciaAsociadaDTO respuesta = new PddCompetenciaAsociadaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddCompetenciaAsociada();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddCompetenciaAsociada(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				if (peticion.getIdCompetencia() == null)
					respuesta = ipFormulacionRegistrarService.registrarPddCompetenciaAsociada(peticion);
				else
					respuesta = ipFormulacionModificarService.modificarPddCompetenciaAsociada(peticion);
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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_COMPROMISO_ESTRATEGICO)
	@Override
	public GenericoDTO obtenerCompromisoEstrategicoTodos(@RequestBody CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.compromisoEstrategicoObtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_METAS_COMPROMISO_ESTRATEGICO)
	@Override
	public GenericoDTO consultarMetasCompromistoEstrategico(@PathVariable("id") Long idEspecifico)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarMetasCompromistoEstrategico(idEspecifico);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPETENCIAS_ASOCIADAS_POR_ID_SECTOR)
	@Override
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(@PathVariable("id") Long idSector)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompetenciaAsociadaPorIdSector(idSector);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODAS_PDD_COMPETENCIAS_ASOCIADAS)
	@Override
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarTodasPddCompetenciaAsociada();
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_COMPROMISO_ESTRATEGICO)
	@Override
	public CompromisoEstrategicoDTO registrarCompromisoEstrategico(@RequestBody CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		peticion.setEstado(NHSPDDConstantes.ESTADO_ACTIVO);
		peticion.setIdCompromiso(null);
		CompromisoEstrategicoDTO respuesta = new CompromisoEstrategicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarCompromisoEstrategico();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarCompromisoEstrategico(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarCompromisoEstrategico(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION_DILIGENCIAR, PaqueteMensajeEnum.ERRORES,
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_COMPROMISO_ESTRATEGICO)
	@Override
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(@RequestBody CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		CompromisoEstrategicoDTO respuesta = new CompromisoEstrategicoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarCompromisoEstrategico();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarCompromisoEstrategico(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionModificarService.modificarCompromisoEstrategico(peticion);
			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_VALIDACION_DILIGENCIAR, PaqueteMensajeEnum.ERRORES,
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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_ESPECIFICO_POR_ID_COMPROMISO)
	@Override
	public ArbolCompromisoDTO consultarPddCompromisoEspecificoPorIdCompromiso(@PathVariable("id") Long idCompromiso)
			throws SpddExceptions {
		ArbolCompromisoDTO respuesta = new ArbolCompromisoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompromisoEspecificoPorIdCompromiso(idCompromiso);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;

	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO_ESPECIFICO)
	@Override
	public PddCompromisoEspecificoDTO registrarPddCompromisoEspecifico(@RequestBody PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		peticion.setIdEspecifico(null);
		PddCompromisoEspecificoDTO respuesta = new PddCompromisoEspecificoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddCompromisoEspecifico();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddCompromisoEspecifico(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarPddCompromisoEspecifico(peticion);
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO_ESPECIFICO)
	@Override
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(@RequestBody PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		PddCompromisoEspecificoDTO respuesta = new PddCompromisoEspecificoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddCompromisoEspecifico();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddCompromisoEspecifico(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionModificarService.modificarPddCompromisoEspecifico(peticion);
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

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PDD_COMPROMISO_ESPECIFICO)
	@Override
	public PddCompromisoEspecificoDTO eliminarPddCompromisoEspecifico(
			@PathVariable("id") Long idPddCompromisoEspecifico) throws SpddExceptions {
		PddCompromisoEspecificoDTO respuesta = new PddCompromisoEspecificoDTO();
		try {
			respuesta = ipFormulacionEliminarService.eliminarPddCompromisoEspecifico(idPddCompromisoEspecifico);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_PDD_COMPROMISO)
	@Override
	public GenericoDTO consultarPddCompromisosPorFiltro(@RequestBody PddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompromisosPorFiltro(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_POR_ID_PLAN_DESARROLLO)
	@Override
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(@PathVariable("id") Long idPlan)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompromisoPorIdPlanDesarrollo(idPlan);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO)
	@Override
	public PddCompromisoDTO registrarPddCompromiso(@RequestBody PddCompromisoDTO peticion) throws SpddExceptions {
		peticion.setIdCompromiso(null);
		PddCompromisoDTO respuesta = new PddCompromisoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddCompromiso();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddCompromiso(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarPddCompromiso(peticion);
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO)
	@Override
	public PddCompromisoDTO modificarPddCompromiso(@RequestBody PddCompromisoDTO peticion) throws SpddExceptions {
		PddCompromisoDTO respuesta = new PddCompromisoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddCompromiso();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddCompromiso(peticion,
				camposAValidar);
		try {
			if (peticion.getIdEstrategico() == null) {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_ERROR_SELECCION_COMPROMISO, PaqueteMensajeEnum.ERRORES,
						peticion.getLenguaje(), resultadoValidacion.getMsgCampoValidacion());
				return respuesta;
			}

			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionModificarService.modificarPddCompromiso(peticion);
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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_PR_VALORACION_POR_ID_PROBLEMATICA_Y_MOMENTO)
	@Override
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(
			@RequestBody PddPrbValoracionDTO peticion) throws SpddExceptions {
		PddPrbValoracionDTO respuesta = new PddPrbValoracionDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_META_COMPROMISO)
	@Override
	public PddMetaDTO registrarMetaPorCompromiso(@RequestBody PddMetaDTO peticion) {

		PddMetaDTO respuesta = new PddMetaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddMeta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMeta(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionRegistrarService.registrarMetaPorCompromiso(peticion);

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
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_OBRA_CONCRETA)
	@Override
	public PddObraConcretaDTO registrarObraConcretaPorMeta(@RequestBody PddObraConcretaDTO peticion) {
		PddObraConcretaDTO respuesta = new PddObraConcretaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddObraConcreta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddObraConcreta(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarObraConcretaPorMeta(peticion);
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

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_ELIMINAR_OBRA_CONCRETA)
	@Override
	public PddObraConcretaDTO eliminarPddObraConcreta(@PathVariable("id") Long id) {
		PddObraConcretaDTO respuesta = new PddObraConcretaDTO();
		try {
			respuesta = ipFormulacionEliminarService.eliminarPddObraConcreta(id);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_ELIMINAR_META)
	@Override
	public PddMetaDTO eliminarPddMeta(@PathVariable("id") Long id) {
		PddMetaDTO respuesta = new PddMetaDTO();
		try {
			respuesta = ipFormulacionEliminarService.eliminarPddMeta(id);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_OBRA_CONCRETA)
	@Override
	public PddObraConcretaDTO modificarObraConcreta(@RequestBody PddObraConcretaDTO peticion) throws SpddExceptions {
		PddObraConcretaDTO respuesta = new PddObraConcretaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarObraConcreta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddObraConcreta(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionModificarService.modificarObraConcreta(peticion);
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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_OBRAS_POR_METAS)
	@Override
	public GenericoDTO consultarObrasPorMeta(@PathVariable("id") Long idMeta) {

		GenericoDTO respuesta = new GenericoDTO();

		try {
			respuesta = ipFormulacionConsultarService.consultarObrasConcretasPorMetas(idMeta);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_METAS)
	@Override
	public PddMetaDTO modificarPddMeta(@RequestBody PddMetaDTO peticion) {
		PddMetaDTO respuesta = new PddMetaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddMeta();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMeta(peticion, camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionModificarService.modificarPddMeta(peticion);

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

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_PRB_VALORACION)
	@Override
	public PddPrbValoracionDTO registrarModificarPddPrbValoracion(@RequestBody PddPrbValoracionDTO peticion)
			throws SpddExceptions {

		PddPrbValoracionDTO respuesta = new PddPrbValoracionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddPrbValoracion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddPrbValoracion(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				if (peticion.getIdValoracion() == null)
					respuesta = ipFormulacionRegistrarService.registrarPddPrbValoracion(peticion);
				else
					respuesta = ipFormulacionModificarService.modificarPddPrbValoracion(peticion);
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

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PBR_POBLACION)
	@Override
	public PddPrbPoblacionDTO eliminarPrbPoblacion(@PathVariable("id") Long idPoblacion) {
		PddPrbPoblacionDTO respuesta = new PddPrbPoblacionDTO();
		try {
			respuesta = ipFormulacionEliminarService.eliminarPrbPoblacion(idPoblacion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PBR_POBLACION)
	@Override
	public PddPrbPoblacionDTO registrarPrbPoblacion(@RequestBody PddPrbPoblacionDTO peticion) {
		PddPrbPoblacionDTO respuesta = new PddPrbPoblacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddPbrPoblacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddPbrPoblacion(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionRegistrarService.registrarPrbPoblacion(peticion);

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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_META_RESULTADO_POR_ID_PRB_INDICADOR)
	@Override
	public GenericoDTO colsultarPddMetaResultadoPorIDProblematicaIndicador(
			@PathVariable("id") Long idProblematicaIndicador) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService
					.consultarPddMetaResultadoPorIDProblematicaIndicador(idProblematicaIndicador);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_META_RESULTADO)
	@Override
	public PddMetaResultadoDTO registrarPddMetaResultado(@RequestBody PddMetaResultadoDTO peticion)
			throws SpddExceptions {
		peticion.setIdMetaResultado(null);
		PddMetaResultadoDTO respuesta = new PddMetaResultadoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddMetaResultado();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMetaResultado(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarPddMetaResultado(peticion);
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_META_RESULTADO)
	@Override
	public PddMetaResultadoDTO modificarPddMetaResultado(@RequestBody PddMetaResultadoDTO peticion)
			throws SpddExceptions {
		PddMetaResultadoDTO respuesta = new PddMetaResultadoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddMetaResultado();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddMetaResultado(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionModificarService.modificarPddMetaResultado(peticion);
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PBR_POBLACION)
	@Override
	public PddPrbPoblacionDTO modificarPrbPoblacion(@RequestBody PddPrbPoblacionDTO peticion) {
		PddPrbPoblacionDTO respuesta = new PddPrbPoblacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddPbrPoblacion();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddPbrPoblacion(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionModificarService.modificarPrbPoblacion(peticion);

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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_INDICADORES)
	@Override
	public GenericoDTO consultarPddIndicadorTodos() {
		GenericoDTO respuesta = new GenericoDTO();

		try {
			respuesta = ipFormulacionConsultarService.consultarPddIndicadorTodos();
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICA_POR_ID)
	@Override
	public PddProblematicaDTO consultarPddProblematicaPorId(@PathVariable("id") Long idProblematica)
			throws SpddExceptions {
		PddProblematicaDTO respuesta = new PddProblematicaDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddProblematicaPorId(idProblematica);

		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_INDICADORES_DE_PROBLEMATICA)
	@Override
	public GenericoDTO consultarPrbIndicadorPorProblematica(@PathVariable("id") Long idProblematica) {
		GenericoDTO respuesta = new GenericoDTO();

		try {
			respuesta = ipFormulacionConsultarService.consultarPrbIndicadorPorProblematica(idProblematica);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POR_COMPROMISO)
	@Override
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();

		try {
			respuesta = ipFormulacionConsultarService.consultarPddProblematicaPorCompromiso(peticion);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_SERVICIO_INTERNO, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PROBLEMATICA)
	@Override
	public PddProblematicaDTO registrarPddProblematica(@RequestBody PddProblematicaDTO peticion) throws SpddExceptions {
		PddProblematicaDTO respuesta = new PddProblematicaDTO();
		

		try {
			
				respuesta = ipFormulacionRegistrarService.registrarProblematica(peticion);

				mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_INFO,
						IPFormulacionController.class, respuesta.getLenguaje());

			
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PRB_INDICADOR)
	@Override
	public PddPrbIndicadorDTO guardarPrbIndicador(@RequestBody PddPrbIndicadorDTO peticion) {
		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddPrbIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddPrbIndicador(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionRegistrarService.registrarPrbIndicador(peticion);

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
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PRB_INDICADOR)
	@Override
	public PddPrbIndicadorDTO modificarPrbIndicador(@RequestBody PddPrbIndicadorDTO peticion) {
		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddPrbIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddPrbIndicador(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionModificarService.modificarPrbIndicador(peticion);
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

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PDD_PRB_INDICADOR)
	@Override
	public PddPrbIndicadorDTO eliminarPrbIndicador(@PathVariable("id") Long idProbInd) {

		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		try {
			respuesta = ipFormulacionEliminarService.eliminarPrbIndicador(idProbInd);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());

		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_INDICADOR_POR_ID)
	@Override
	public PddIndicadorDTO consultarPddIndicadorPorId(@PathVariable("id") Long id) {
		PddIndicadorDTO respuesta = new PddIndicadorDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddIndicadorPorId(id);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_INDICADOR_Y_PRB_INDICADOR)
	@Override
	public PddPrbIndicadorDTO registrarPddIndicadorYPddPrbIndicador(@RequestBody PddPrbIndicadorDTO peticion) {
		PddPrbIndicadorDTO respuesta = new PddPrbIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarPddIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarPddIndicadorYPrbIndicador(peticion, camposAValidar);

		try {
			if (resultadoValidacion.esValido()) {
				respuesta = ipFormulacionRegistrarService.registrarPddIndicadorYPddPrbIndicador(peticion);

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
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_INDICADOR_Y_PRB_INDICADOR)
	@Override
	public PddIndicadorDTO modificarPddIndicador(@RequestBody PddIndicadorDTO peticion) {
		PddIndicadorDTO respuesta = new PddIndicadorDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarPddIndicador();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarPddIndicador(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {

				respuesta = ipFormulacionModificarService.modificarPddIndicador(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(respuesta.getMsgRespuesta(), NHSPDDConstantes.SEVERIDAD_ERROR,
						IPFormulacionController.class);
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
					IPFormulacionController.class, respuesta.getLenguaje());
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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_POR_ID)
	@Override
	public PddCompromisoDTO consultarPddCompromisoPorId(@PathVariable("id") Long id) throws SpddExceptions {
		PddCompromisoDTO respuesta = new PddCompromisoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompromisoPorId(id);
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_VIGENTE)
	@Override
	public PddDTO consultarPddVigente() throws SpddExceptions {
		PddDTO respuesta = new PddDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddVigente();
		} catch (SpddExceptions e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;

	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POR_ID_COMPROMISO)
	@Override
	public GenericoDTO consultarProblematicaPorIdCompromiso(@PathVariable("id")Long idCompromiso) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarProblematicaPorIdCompromiso(idCompromiso);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_PROBLEMATICA)
	@Override
	public PddProblematicaDTO modificarProblematica(@RequestBody PddProblematicaDTO peticion) throws SpddExceptions {
		PddProblematicaDTO respuesta = new PddProblematicaDTO();
		try {
			respuesta = ipFormulacionModificarService.modificarPddProblematica(peticion);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POBLACION_POR_ID_PROBLEMATICA)
	@Override
	public GenericoDTO consultarTodosPddPrbPoblacionPorIdProblematica(@RequestBody PddPrbPoblacionDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddPrbPoblacionPorIdProblematica(peticion);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_INDICADORES_DE_PROBLEMATICA_FILTRADO)
	@Override
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(@RequestBody PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarTodosPrbIndicadorFiltrado(pddPrbIndicadorDTO);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICA_TODOS)
	@Override
	public GenericoDTO obtenerTodosPddProblematica() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarTodosPddProbleatica();
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_COMPROMISOS_ESPECIFICOS_FILTRADO)
	@Override
	public GenericoDTO consultarPddCompromisoEspecificoFitlrado(@RequestBody PddCompromisoEspecificoDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = ipFormulacionConsultarService.consultarPddCompromisoEspecificoFiltrado(peticion);
		} catch (Exception e) {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ERROR_VALIDACION, PaqueteMensajeEnum.ERRORES, null, null);
			mensajeLogger(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, NHSPDDConstantes.SEVERIDAD_FATAL,
					IPFormulacionController.class, respuesta.getLenguaje());
		}	
		return respuesta;
	}
}
