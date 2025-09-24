package co.gov.sdp.spdd.core.service.carga;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.ErroresPorCampoDTO;
import co.gov.sdp.nhspdd.common.dto.FilesDTO;
import co.gov.sdp.nhspdd.common.dto.RegistroArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.TablasDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.GestionarArchivos;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.controller.carga.ArchivoProcesadoCargaController;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Esta clase tiene todos los metodos de registrar de la entidad
 * archivoProcesado
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ArchivoProcesadoCargaRegistrar implements IArchivoProcesadoCargaRegistrar {

	/**
	 * Objeto para realizar las operaciones CRUD de archivos procesados
	 */
	@Autowired
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	/**
	 * Objeto para realizar las operaciones CRUD de informacion presupuestal
	 */
	@Autowired
	IInformacionPresupuestalServiceRepo informacionPresupuestalRepo;

	@Autowired
	SessionFacadeAFS sessionFacadeAFS;

	/**
	 * Objeto que permite el mapeo de componente de gestion
	 */
	@Autowired
	ArchivoProcesadoMapper archivoProcesadoMapper;

	@Autowired
	GestionarArchivos gestionarArchivos;

	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * 
	 */
	@Autowired
	AuditoriaUtil auditoria;

	@Override
	public CarguePlanoDTO cargarArchivoProcesado(InputStream archivo, CargaAchivoDTO peticion)
			throws IOException, SpddExceptions, DataAccessException {
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		CarguePlanoDTO respuesta = new CarguePlanoDTO();
		List<RegistroArchivoDTO> listaRegistroArchivoDTO = new ArrayList<>();
		HashMap<String, String> camposAdicionales = new HashMap<>();
		String detalleArchivo = "";
		ConfiguracionCargueArchivoDTO configCargueArchivoDTO = new ConfiguracionCargueArchivoDTO();
		configCargueArchivoDTO.setIdLsTema(peticion.getTema());
		configCargueArchivoDTO.setIdLsModulo(peticion.getModulo());
		boolean archivoConErrores = false;
		String error = NHSPDDConstantes.DESCRIPCION_NA;

		ConfiguracionCargueArchivoDTO resArchivoDTO = sessionFacadeAFS
				.buscarIdLsModuloYIdLsTema(configCargueArchivoDTO);

		int totalRegistros = 0;
		int totalRegistrosConError = 0;
		int totalRegistrosActualizados = 0;
		int totalRegistrosInsertados = 0;

		if (resArchivoDTO != null && resArchivoDTO.getIdConfigCargue() != null) {
			List<FilesDTO> archivoLeido = gestionarArchivos.leerArchivo(archivo,
					NHSPDDConstantes.CARACTER_SPLIT_PUNTOYCOMA);

			totalRegistros = archivoLeido.size();
			TablasDTO tablaDTO = gestionarArchivos.xmlStringToDTO(resArchivoDTO.getConfiguracion());
			List<FilesDTO> result = gestionarArchivos.comparaArchivosXML(archivoLeido, tablaDTO.getColumnas());

			int resultQuery = 0;
			int resultInsert = 0;

			archivoProcesadoDTO.setDetalle(NHSPDDConstantes.DESCRIPCION_NA);
			archivoProcesadoDTO.setCodigoUsuario(peticion.getUsuario());
			archivoProcesadoDTO.setNombreArchivo(peticion.getNombreArchivo());
			archivoProcesadoDTO = guardarArchivoProcesado(archivoProcesadoDTO,
					resArchivoDTO.getIdConfigCargue().longValue());

			if (archivoProcesadoDTO != null && archivoProcesadoDTO.getIdArchivo() > 0) {
				for (FilesDTO filesDTO : result) {
					RegistroArchivoDTO registroArchivoDTO = new RegistroArchivoDTO();
					error = NHSPDDConstantes.DESCRIPCION_NA;
					if (!filesDTO.isTieneError()) {
						resultQuery = sessionFacadeAFS.buscarSql(
								armarSqlBuscarValidacion(filesDTO.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()));

						String strSql = resultQuery > 0
								? armarUpdateSql(filesDTO.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(),
										archivoProcesadoDTO.getIdArchivo())
								: armarInsertSQL(filesDTO.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(),
										archivoProcesadoDTO.getIdArchivo());
								
						resultInsert = EjecutarJDBCTemplateSQL(strSql);

						if (resultInsert > 0) {
							if (resultQuery > 0) {
								totalRegistrosActualizados += 1;
							} else {
								totalRegistrosInsertados += 1;
							}
							camposAdicionales = filesDTO.getMapCampos();
							spddLogger.mensajeLogger(
									NHSPDDMensajes.obtenerMensaje(
											NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_LINEA_ARCHIVO_PLANO,
											PaqueteMensajeEnum.MENSAJES, null),
									NHSPDDConstantes.SEVERIDAD_INFO, ArchivoProcesadoCargaRegistrar.class);
						} else {
							totalRegistrosConError += 1;
							archivoConErrores = true;
							camposAdicionales = filesDTO.getMapCampos();
							error = NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA_SQL,
									PaqueteMensajeEnum.ERRORES, null);

							spddLogger.mensajeLogger(
									NHSPDDMensajes.obtenerMensaje(
											NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_LINEA_ARCHIVO,
											PaqueteMensajeEnum.ERRORES, null),
									NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaRegistrar.class);
						}
					} else {
						totalRegistrosConError += 1;
						archivoConErrores = true;

						if (filesDTO.getMapCampos() != null) {
							camposAdicionales = filesDTO.getMapCampos();
						}
						spddLogger.mensajeLogger(
								NHSPDDMensajes.obtenerMensaje(
										NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_LINEA_ARCHIVO_PLANO,
										PaqueteMensajeEnum.ERRORES, null),
								NHSPDDConstantes.SEVERIDAD_ERROR, ArchivoProcesadoCargaRegistrar.class);
					}
					camposAdicionales.put(NHSPDDConstantes.CARGUE_ARCHIVO_NUMLINEA,
							String.valueOf(filesDTO.getNumLinea()));

					error = error.equals(NHSPDDConstantes.DESCRIPCION_NA) ? filesDTO.getDescripcionErrorFila() : error;

					camposAdicionales.put(NHSPDDConstantes.CARGUE_ARCHIVO_ERROR, error);

					String msgError = "";

					if (filesDTO.isTieneError() || resultInsert < 0) {
						msgError = NHSPDDConstantes.CARGUE_ARCHIVO_CON_ERROR;
					} else if (resultQuery > 0) {
						msgError = NHSPDDConstantes.CARGUE_ARCHIVO_ACTUALIZADO;
					} else {
						msgError = NHSPDDConstantes.CARGUE_ARCHIVO_INSERTADO;
					}

					camposAdicionales.put(NHSPDDConstantes.CARGUE_ARCHIVO_RESULTADO_CARGUE, msgError.toUpperCase());

					registroArchivoDTO.setMapCampos(filesDTO.getMapCampos());
					listaRegistroArchivoDTO.add(registroArchivoDTO);

					if (!filesDTO.getDescripcionErrorFila().equals("")) {
						detalleArchivo += filesDTO.getDescripcionErrorFila() + "\n";
					}
				}

				if (detalleArchivo.equals("")) {
					detalleArchivo = NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO,
							PaqueteMensajeEnum.MENSAJES, null);
				}

				archivoProcesadoDTO.setDetalle(detalleArchivo);
				guardarArchivoProcesado(archivoProcesadoDTO, resArchivoDTO.getIdConfigCargue().longValue());

				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "CARGAR_ARCHIVOS_PLANOS");

				if (archivoConErrores) {
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,
							PaqueteMensajeEnum.MENSAJES, null));

					spddLogger.mensajeLogger(
							NHSPDDMensajes.obtenerMensaje(
									NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,
									PaqueteMensajeEnum.MENSAJES, null),
							NHSPDDConstantes.SEVERIDAD_INFO, ArchivoProcesadoCargaRegistrar.class);
				} else {
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_LEER_ARCHIVO_PLANO,
							PaqueteMensajeEnum.MENSAJES, null));

					spddLogger.mensajeLogger(
							NHSPDDMensajes.obtenerMensaje(
									NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_LEER_ARCHIVO_PLANO,
									PaqueteMensajeEnum.MENSAJES, null),
							NHSPDDConstantes.SEVERIDAD_INFO, ArchivoProcesadoCargaRegistrar.class);
				}
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_ARCHIVO_DB,
								PaqueteMensajeEnum.ERRORES, null));

				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_ARCHIVO_DB,
								PaqueteMensajeEnum.ERRORES, null),
						NHSPDDConstantes.SEVERIDAD_ERROR, ArchivoProcesadoCargaRegistrar.class);
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_NO_EXISTE_XML, PaqueteMensajeEnum.ERRORES, null));

			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_NO_EXISTE_XML,
							PaqueteMensajeEnum.ERRORES, null),
					NHSPDDConstantes.SEVERIDAD_ERROR, ArchivoProcesadoCargaRegistrar.class);
		}
		respuesta.setTotalRegistros(totalRegistros);
		respuesta.setTotalRegistrosActualizados(totalRegistrosActualizados);
		respuesta.setTotalRegistrosConError(totalRegistrosConError);
		respuesta.setTotalRegistrosInsertados(totalRegistrosInsertados);
		respuesta.setListaRegistroArchivoDTO(listaRegistroArchivoDTO);
		return respuesta;
	}

	/**
	 * 
	 * @param lstCampos
	 * @param nombreTabla
	 * @return
	 */
	public String armarSqlBuscarValidacion(List<ErroresPorCampoDTO> lstCampos, String nombreTabla) {
		String result = "SELECT * FROM " + nombreTabla + " WHERE ";
		String camposValidarSQL = "";
		String valorCampo = "";

		List<ErroresPorCampoDTO> camposValidar = lstCampos.stream().filter(item -> item.isEsValidacion())
				.collect(Collectors.toList());

		if (camposValidar.size() > 0) {

			for (int i = 0; i < camposValidar.size(); i++) {
				valorCampo = camposValidar.get(i).getTipoDato().equals("CHAR")
						? "'" + camposValidar.get(i).getValorCampo() + "'"
						: camposValidar.get(i).getValorCampo();

				camposValidarSQL += i == camposValidar.size() - 1
						? camposValidar.get(i).getNombreCampo() + " = " + valorCampo
						: camposValidar.get(i).getNombreCampo() + " = " + valorCampo + " AND ";
			}
		}
		return result + camposValidarSQL;
	}

	/**
	 * 
	 * @param lstCampos
	 * @param nombreTabla
	 * @param idArchivo
	 * @return
	 */
	public String armarInsertSQL(List<ErroresPorCampoDTO> lstCampos, String nombreTabla, Long idArchivo) {
		String result = "INSERT INTO " + nombreTabla;
		String fields = "(";
		String values = "VALUES(";

		for (int i = 0; i < lstCampos.size(); i++) {
			if (lstCampos.get(i).getTipoDato().equals("CHAR")) {
				values += "'" + lstCampos.get(i).getValorCampo() + "'";
			} else {
				values += lstCampos.get(i).getValorCampo();
			}

			if (i == lstCampos.size() - 1) {
				fields += lstCampos.get(i).getNombreCampo() + ", ID_ARCHIVO)";
				values += ", " + String.valueOf(idArchivo) + ")";
			} else {
				fields += lstCampos.get(i).getNombreCampo() + ", ";
				values += ", ";
			}
		}
		result = result + " " + fields + " " + values;
		return result;
	}

	/**
	 * 
	 * @param lstCampos
	 * @param nombreTabla
	 * @param idArchivo
	 * @return
	 */
	public String armarUpdateSql(List<ErroresPorCampoDTO> lstCampos, String nombreTabla, Long idArchivo) {
		String result = "UPDATE " + nombreTabla + " SET ";
		String valorCampo = "";
		String camposValidarSQL = " WHERE ";
		String values = "";

		List<ErroresPorCampoDTO> camposValidar = lstCampos.stream().filter(item -> item.isEsValidacion())
				.collect(Collectors.toList());

		if (camposValidar.size() > 0) {
			for (int i = 0; i < camposValidar.size(); i++) {
				valorCampo = camposValidar.get(i).getTipoDato().equals("CHAR")
						? "'" + camposValidar.get(i).getValorCampo() + "'"
						: camposValidar.get(i).getValorCampo();

				camposValidarSQL += i == camposValidar.size() - 1
						? camposValidar.get(i).getNombreCampo() + " = " + valorCampo
						: camposValidar.get(i).getNombreCampo() + " = " + valorCampo + " AND ";
			}

			valorCampo = "";
			for (int i = 0; i < lstCampos.size(); i++) {
				values = "";
				values += lstCampos.get(i).getTipoDato().equals("CHAR") ? "'" + lstCampos.get(i).getValorCampo() + "'"
						: lstCampos.get(i).getValorCampo();

				valorCampo += (i == lstCampos.size() - 1) ? lstCampos.get(i).getNombreCampo() + " = " + values
						+ ", ID_ARCHIVO = " + String.valueOf(idArchivo)
						: lstCampos.get(i).getNombreCampo() + " = " + values + ", ";
			}
		} else {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_ARMAR_UPDATE_VALIDACION_ARCHIVO_PLANO,
							PaqueteMensajeEnum.ERRORES, null),
					NHSPDDConstantes.SEVERIDAD_ERROR, ArchivoProcesadoCargaRegistrar.class);
		}
		return result + valorCampo + camposValidarSQL;
	}

	/**
	 * 
	 * @param archivoProcesadoDTO
	 * @param idConfigCargue
	 * @return
	 */
	public ArchivoProcesadoDTO guardarArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO, Long idConfigCargue) {
		try {

			archivoProcesadoDTO.setEstado("1");
			archivoProcesadoDTO.setIdConfigCargue(idConfigCargue);
		

			archivoProcesadoDTO = sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO);

		} catch (SpddExceptions e) {
			archivoProcesadoDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			archivoProcesadoDTO.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, ""));

			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, ""),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivoProcesadoCargaController.class);
		}
		return archivoProcesadoDTO;
	}

	/**
	 * 
	 * @param strSql
	 * @return
	 * @throws SpddExceptions
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public int EjecutarJDBCTemplateSQL(String strSql) throws SpddExceptions, DataAccessException {
		try {
			return sessionFacadeAFS.guardarSql(strSql);
		} catch (DataAccessException e) {
			
			return -1;
		} catch (SpddExceptions e) {
			
			return -1;
		}
	}

}