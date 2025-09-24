package co.gov.sdp.spdd.core.bp.controller.bpiniciativa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.bp.icontroller.bpiniciativa.IBPIniciativaController;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaConsultarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaEliminarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaRegistrarService;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBpIniciativaModificarService;
import co.gov.sdp.spdd.core.ip.controller.ipformulacion.IPFormulacionController;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@RestController
public class BPIniciativaController implements IBPIniciativaController {

	@Autowired
	IBPIniciativaConsultarService consultar;

	@Autowired
	IBPIniciativaEliminarService eliminar;

	@Autowired
	IBPIniciativaRegistrarService registrar;

	@Autowired
	IBpIniciativaModificarService modificar;

	@Autowired
	SPDDLogger spddLogger;

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INCIATIVA_CIUDADANA_OBTENER_TODOS)
	@Override
	public GenericoDTO consultarTodasLasIniCiudadana(@RequestBody BpIniciativaCiudadanaDTO peticion) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.obtenerTodasLaIniciativasCiudadanas(peticion);
		} catch (SpddExceptions e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BPIniciativaController.class);
		}
		return respuesta;
	}

	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_REGISTRAR)
	@Override
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(@RequestBody BpIniciativaCiudadanaDTO peticion) {
		BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.registrarIniciativaCiudadana();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarIniciativaCiudadana(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = registrar.guardarIniciativaCiudadana(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));

				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, IPFormulacionController.class);
			}
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

	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_MODIFICAR)
	@Override
	public BpIniciativaCiudadanaDTO modificarIniciativaCiudadana(@RequestBody BpIniciativaCiudadanaDTO peticion) {
		BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarIniciativaCiudadana();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarIniciativaCiudadana(peticion,
				camposAValidar);
		try {
			if (resultadoValidacion.esValido()) {
				respuesta = modificar.modificarIniciativaCiudadana(peticion);
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_OBLIGATORIEDAD,
						PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(respuesta.getMsgRespuesta(), respuesta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_ERROR, IPFormulacionController.class);
			}
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

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INICIAITIVA_CIUDADANA_OBTENER_POR_ID)
	@Override
	public BpIniciativaCiudadanaDTO consultaDetalladaIniciativaCiudadana(@PathVariable("id") Long idIniciativa) {
		BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		try {
			respuesta = consultar.consultaDetallaIniciativaCiudadana(idIniciativa);
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

	@DeleteMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_ELIMINAR)
	@Override
	public BpIniciativaCiudadanaDTO eliminarIniciativaCiudadana(@PathVariable("id") Long idIniciativa) {
		BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		try {
			respuesta = eliminar.eliminarIniciativaCiudadana(idIniciativa);
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
	
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BP_INICIAITIVA_CIUDADANA_OBTENER_TODOS_BP_PROY_INV_INICIATIVA_POR_ID_INICIATIVA)
	@Override
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(@PathVariable("id") Long idIniciativa) {
		GenericoDTO respuesta = new GenericoDTO();
		try {
			respuesta = consultar.consultarTodosBpProyInvIniciativaPorIdIniciativa(idIniciativa);
		} catch (SpddExceptions e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BPIniciativaController.class);
		}
		return respuesta;
	}

}
