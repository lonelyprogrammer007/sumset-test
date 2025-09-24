package co.gov.sdp.spdd.core.controller.buzon.notificacion;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.nhspdd.common.validador.NHSPDDCamposValidar;
import co.gov.sdp.nhspdd.common.validador.NHSPDDReglasValidacion;
import co.gov.sdp.nhspdd.common.validador.NHSPDDResultadoValidacion;
import co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IConfiguracionNotificacionController;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionConsultar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionModificar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionRegistrar;

/**
 * Clase que direcciona y permite ejecutar los metodos por medio de peticiones
 * rest
 *
 * @author Bryan Munoz
 *
 */
@RestController
public class ConfiguracionNotificacionController implements IConfiguracionNotificacionController {

	// Servicio que permite consultar una configuracionNotificacion
	@Autowired
	IConfiguracionNotificacionConsultar consultar;
	
	// Servicio que permite registrar una configuracionNotificacion
	@Autowired
	IConfiguracionNotificacionRegistrar registrar;
	
	// Servicio que permite modificar una configuracionNotificacion
	@Autowired
	IConfiguracionNotificacionModificar modificar;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearNotificacionManual
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.buzon.notificacion.IConfiguracionNotificacionController.crearNotificacionManual
	 */
	@Override
	public ConfiguracionNotificacionDTO crearNotificacionManual(ConfiguracionNotificacionDTO peticion) {

		return null;
	}

	/**
	 * Implementacion del metodo crearNotificacionAutomatica
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.buzon.notificacion.IConfiguracionNotificacionController.crearNotificacionAutomatica
	 */
	@Override
	public ConfiguracionNotificacionDTO crearNotificacionAutomatica(ConfiguracionNotificacionDTO peticion) {

		return null;
	}

	/**
	 * Implementacion del metodo obtenerAutomaticos
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.buzon.notificacion.IConfiguracionNotificacionController.obtenerAutomaticos
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_AUTOMATICOS)
	@Override
	public GenericoDTO obtenerAutomaticos(ConfiguracionNotificacionDTO peticion) {
		return consultar.obtenerAutomaticos();
	}

	/**
	 * Implementacion del metodo modificarConfiguracion
	 *
	 * @see co.gov.sdp.nhspdd.core.icontroller.buzon.notificacion.IConfiguracionNotificacionController.modificarConfiguracion
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_CONFIGURACION_NOTIFICACION_MODIFICAR_CONFIGURACION)
	@Override
	public ConfiguracionNotificacionDTO modificarConfiguracion(@RequestBody ConfiguracionNotificacionDTO peticion) {
		ConfiguracionNotificacionDTO respuesta = new ConfiguracionNotificacionDTO();
		Map<String, Boolean> camposAValidar = NHSPDDCamposValidar.modificarMensaje();
		NHSPDDResultadoValidacion resultadoValidacion = NHSPDDReglasValidacion
				.validarConfiguracionNotificacion(peticion, camposAValidar);
		if (resultadoValidacion.esValido()) {
			respuesta = modificar.modificarNotificacion(peticion);
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));
			respuesta.setLstCampoValidacion(resultadoValidacion.getMsgCampoValidacion());
		}
		return respuesta;
	}
}
