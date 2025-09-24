package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEquipamientoAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionRegistrar;

/**
 * Clase para manejar las peticiones relacionadas con equipamiento para el
 * modulo administracion
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class EquipamientoAdministracionController implements IEquipamientoAdministracionController {

	// Servicio de consulta para equipamiento
	@Autowired
	IEquipamientoAdministracionConsultar consultar;


	// Servicio de modificacion para equipamiento
	@Autowired
	IEquipamientoAdministracionModificar modificar;

	// Servicio de registro para equipamiento
	@Autowired
	IEquipamientoAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearEquipamiento
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.crearEquipamiento
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_EQUIPAMIENTO)
	@Override
	public EquipamientoDTO crearEquipamiento(@RequestBody EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException {
		peticion.setEstado(1);
		peticion.setIdEquipamento(null);
		EquipamientoDTO respuesta = new EquipamientoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.crearEquipamiento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarEquipamiento(peticion,
				camposAValidar);
		if (resultadoValidacion.esValido()) {
			respuesta = registrar.crearEquipamiento(peticion);
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
		}
		return respuesta;
	}


	/**
	 * Implementacion del metodo modificarEquipamiento
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.modificarEquipamiento
	 */
	@PutMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_EQUIPAMIENTO)
	@Override
	public EquipamientoDTO modificarEquipamiento(@RequestBody EquipamientoDTO peticion) throws SpddExceptions {
		EquipamientoDTO respuesta = new EquipamientoDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarEquipamiento();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion.validarEquipamiento(peticion,
				camposAValidar);
		if (resultadoValidacion.esValido()) {
			respuesta = modificar.modificarEquipamiento(peticion);
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEquipamientoTodos
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.administracion.lista.compuesta.ArchivoProcesadoCargaController.obtenerEquipamientoTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_EQUIPAMIENTOS_TODOS)
	@Override
	public GenericoDTO obtenerEquipamientoTodos(@RequestBody EquipamientoDTO peticion) throws JsonProcessingException {
		GenericoDTO respuesta= new GenericoDTO();
		try {
			respuesta=consultar.obtenerPaginado(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}
		
		return respuesta;
	}
}
