package co.gov.sdp.spdd.core.controller.carga;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.carga.IArchivoProcesadoCargaController;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaConsultar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaEliminar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaModificar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaRegistrar;
import co.gov.sdp.spdd.core.iservice.carga.IConfiguracionCargueArchivoConsultar;

/**
 * Clase para manejar las peticiones relacionadas con archivos procesados para
 * el modulo carga
 *
 * @author Raul Londono Murillo
 *
 */
@RestController
public class ArchivoProcesadoCargaController implements IArchivoProcesadoCargaController {

	// Servicio de consulta para archivo procesado
	@Autowired
	IArchivoProcesadoCargaConsultar consultar;

	// Servicio de eliminacion para archivo procesado
	@Autowired
	IArchivoProcesadoCargaEliminar eliminar;

	// Servicio de modificacion para archivo procesado
	@Autowired
	IArchivoProcesadoCargaModificar modificar;

	// Servicio de registro para archivo procesado
	@Autowired
	IArchivoProcesadoCargaRegistrar registrar;

	/**
	 * 
	 */
	@Autowired
	IConfiguracionCargueArchivoConsultar consultarConfiguracionCargue;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo crearComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.carga.IArchivoProcesadoCargaController.crearArchivoProcesado
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_CARGA_POST_CARGAR_ARCHIVO_PLANO)
	@Override
	public CarguePlanoDTO crearArchivoProcesado(@RequestParam("file2") MultipartFile archivo,
			@RequestParam("tema") Long tema, @RequestParam("usuario") String usuario,
			@RequestParam("modulo") Long modulo) {

		CarguePlanoDTO respuesta = new CarguePlanoDTO();
		CargaAchivoDTO peticion = new CargaAchivoDTO();
		peticion.setUsuario(usuario);
		peticion.setTema(tema);
		peticion.setModulo(modulo);
		peticion.setNombreArchivo(archivo.getOriginalFilename());
		
		try {
			respuesta = registrar.cargarArchivoProcesado(archivo.getInputStream(), peticion);
		} catch (IOException e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA_IO_FILE,
							PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		} catch (DataAccessException E) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA_SQL,
							PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		} catch (SpddExceptions E) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo crearComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.core.icontroller.carga.IArchivoProcesadoCargaController.obtenerArchivoProcesadoTodos
	 */
	@PostMapping(NHSPDDConstantes.CORE_CONTROLLER_CARGA_GET_OBTENER_ARCHIVOS_PLANOS_TODOS)
	@Override
	public GenericoDTO obtenerArchivoProcesadoTodos(@RequestBody ArchivoProcesadoDTO peticion) {

		GenericoDTO respuesta = new GenericoDTO();

		try {

			respuesta = consultar.obtenerPaginado(peticion);

		} catch (SpddExceptions e) {

			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		}		

		return respuesta;
	}

	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_CARGA_GET_OBTENER_CONFIGURACION_CARGA)
	@Override
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(@PathVariable("id") Long id) {
		ConfiguracionCargueArchivoDTO respuesta = new ConfiguracionCargueArchivoDTO();
		try {
			respuesta = consultarConfiguracionCargue.consultarConfigCargueArchivoProcesado(id);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_CONFIGURACION_CARGUE_ARCHIVO, PaqueteMensajeEnum.MENSAJES, null));
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		}
		return respuesta;
	}

}
