package co.gov.sdp.spdd.core.service.carga;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.ErroresPorCampoDTO;
import co.gov.sdp.nhspdd.common.dto.FilesDTO;
import co.gov.sdp.nhspdd.common.dto.TablasDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.GestionarArchivos;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionRegistroAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IArchivoProcesadoCargaRegistrar.class,ArchivoProcesadoCargaRegistrar.class})
public class ArchivoProcesadoCargaRegistrarTest {
	
	@Autowired
	ArchivoProcesadoCargaRegistrar regAutowired;
	
	@Autowired
	IArchivoProcesadoCargaRegistrar registrar;
	
//	@MockBean
//	ArchivoProcesadoCargaRegistrar regAutowired;
	
	@MockBean
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	@MockBean
	IInformacionPresupuestalServiceRepo informacionPresupuestalRepo;

	@MockBean
	SessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	SessionRegistroAFS sessionRegistroAFS;

	@MockBean
	ArchivoProcesadoMapper archivoProcesadoMapper;

	@MockBean
	GestionarArchivos gestionarArchivos;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testCargarArchivoProcesado() throws SpddExceptions, IOException {
		
		CargaAchivoDTO peticion =  new CargaAchivoDTO();
		peticion.setCodigo(1);
		peticion.setTema(1L);
		peticion.setModulo(2L);
		peticion.setUsuario("Usuario1");
		peticion.setNombreArchivo("arvhico1");
		
		InputStream archivo = new InputStream() {
			
			@Override
			public int read() throws IOException {
				return 0;
			}
		};
		
		ConfiguracionCargueArchivoDTO resArchivoDTO = new ConfiguracionCargueArchivoDTO();
		resArchivoDTO.setIdConfigCargue(2);
		
		ConfiguracionCargueArchivoDTO configCargueArchivoDTO = new ConfiguracionCargueArchivoDTO();
		configCargueArchivoDTO.setIdLsTema(peticion.getTema());
		configCargueArchivoDTO.setIdLsModulo(peticion.getModulo());
		
		List<FilesDTO> archivoLeido = new ArrayList<>();
		
		TablasDTO tablaDTO = new TablasDTO();
		tablaDTO.setNombreTabla("Tabla1");
		
		List<ErroresPorCampoDTO> lstCampos = new ArrayList<>();
		
		FilesDTO file =  new FilesDTO();
		file.setErroresPorCampoDTO(lstCampos);
		file.setTieneError(false);
		file.setNumLinea(1);
		file.setMapCampos(new HashMap<String,String>());
		file.setDescripcionErrorFila("Descripcion1");
		List<FilesDTO> result = new ArrayList<>();
		result.add(file);
		
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		archivoProcesadoDTO.setDetalle(NHSPDDConstantes.DESCRIPCION_NA);
		archivoProcesadoDTO.setCodigoUsuario(peticion.getUsuario());
		archivoProcesadoDTO.setNombreArchivo(peticion.getNombreArchivo());
		archivoProcesadoDTO.setEstado("1");
		archivoProcesadoDTO.setIdConfigCargue(resArchivoDTO.getIdConfigCargue().longValue());
		
		ArchivoProcesadoDTO archivoDTO = new ArchivoProcesadoDTO();
		archivoDTO.setDetalle(NHSPDDConstantes.DESCRIPCION_NA);
		archivoDTO.setCodigoUsuario(peticion.getUsuario());
		archivoDTO.setNombreArchivo(peticion.getNombreArchivo());
		archivoDTO.setEstado("1");
		archivoDTO.setIdConfigCargue(resArchivoDTO.getIdConfigCargue().longValue());
		archivoDTO.setIdArchivo(1L);
		archivoDTO.setIdArchivo(2L);
		archivoDTO.setDetalle(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO, PaqueteMensajeEnum.MENSAJES, null));
		
		CarguePlanoDTO res;
		
		//----------------------------------------------------------------------///
		
		when(sessionFacadeAFS.buscarIdLsModuloYIdLsTema(configCargueArchivoDTO)).thenReturn(resArchivoDTO);
		when(gestionarArchivos.leerArchivo(archivo,NHSPDDConstantes.CARACTER_SPLIT_PUNTOYCOMA)).thenReturn(archivoLeido);
		when(gestionarArchivos.xmlStringToDTO(resArchivoDTO.getConfiguracion())).thenReturn(tablaDTO);
		when(gestionarArchivos.comparaArchivosXML(archivoLeido, tablaDTO.getColumnas())).thenReturn(result);
		when(sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO)).thenReturn(archivoDTO);
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(1); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarUpdateSql(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(2);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_LEER_ARCHIVO_PLANO,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
	
		//----------------------------------------------------------------------///
		
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(0); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarInsertSQL(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(3);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_LEER_ARCHIVO_PLANO,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

		//----------------------------------------------------------------------///
		
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(1); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarUpdateSql(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(0);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		//----------------------------------------------------------------------///
		
		
		
		result.get(0).setTieneError(true);
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(1); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarUpdateSql(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(0);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		//----------------------------------------------------------------------///
		
		result.get(0).setDescripcionErrorFila("");
		result.get(0).setTieneError(true);
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(1); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarUpdateSql(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(0);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		//----------------------------------------------------------------------///
		
		result.get(0).setTieneError(true);
		result.get(0).setMapCampos(null);
		when(sessionFacadeAFS.buscarSql(regAutowired.armarSqlBuscarValidacion(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla()))).thenReturn(1); 	
		when(sessionFacadeAFS.guardarSql(regAutowired.armarUpdateSql(file.getErroresPorCampoDTO(), tablaDTO.getNombreTabla(), archivoDTO.getIdArchivo()))).thenReturn(0);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS,PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		//----------------------------------------------------------------------///
		
		when(sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO)).thenReturn(null);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_ARCHIVO_DB,PaqueteMensajeEnum.ERRORES, null), res.getMsgRespuesta());

		//----------------------------------------------------------------------///
		
		when(sessionFacadeAFS.buscarIdLsModuloYIdLsTema(configCargueArchivoDTO)).thenReturn(null);
		res = registrar.cargarArchivoProcesado(archivo, peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals((NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CARGUE_ARCHIVO_ERROR_NO_EXISTE_XML, PaqueteMensajeEnum.ERRORES, null)), res.getMsgRespuesta());
		

	
	}

	@Test
	public void testArmarSqlBuscarValidacion() {
		
		ErroresPorCampoDTO error1 = new ErroresPorCampoDTO();
		error1.setTipoDato("CHAR");
		error1.setValorCampo("valor1");
		error1.setNombreCampo("nombre1");
		error1.setEsValidacion(true);
		
		ErroresPorCampoDTO error2 = new ErroresPorCampoDTO();
		error2.setTipoDato("INT");
		error2.setValorCampo("valor2");
		error2.setNombreCampo("nombre2");
		error2.setEsValidacion(true);


		List<ErroresPorCampoDTO> lstCampos = new ArrayList<>();
		lstCampos.add(error1);
		lstCampos.add(error2);
		
		String nombreTabla = "talba";
		
		String res;
		
		res = regAutowired.armarSqlBuscarValidacion(lstCampos, nombreTabla);
		assertNotNull(res);
		assertThat(res.contains("SELECT")).isEqualTo(true);
		
		
	}

	@Test
	public void testArmarInsertSQL() {
		ErroresPorCampoDTO error1 = new ErroresPorCampoDTO();
		error1.setTipoDato("CHAR");
		error1.setValorCampo("valor1");	
		
		ErroresPorCampoDTO error2 = new ErroresPorCampoDTO();
		error2.setTipoDato("INT");
		error2.setValorCampo("valor2");
		List<ErroresPorCampoDTO> lstCampos = new ArrayList<>();
		lstCampos.add(error1);
		lstCampos.add(error2);

		
		String nombreTabla = "tabla";
		Long idArchivo = 1L;
		
		String res = regAutowired.armarInsertSQL(lstCampos, nombreTabla, idArchivo);
		assertNotNull(res);
		assertThat(res.contains("INSERT")).isEqualTo(true);
	}

	@Test
	public void testArmarUpdateSql() {
		ErroresPorCampoDTO error1 = new ErroresPorCampoDTO();
		error1.setTipoDato("CHAR");
		error1.setValorCampo("valor1");
		error1.setNombreCampo("nombre1");
		error1.setEsValidacion(true);
		
		ErroresPorCampoDTO error2 = new ErroresPorCampoDTO();
		error2.setTipoDato("INT");
		error2.setValorCampo("valor2");
		error2.setNombreCampo("nombre2");
		error2.setEsValidacion(true);


		List<ErroresPorCampoDTO> lstCampos = new ArrayList<>();
		lstCampos.add(error1);
		lstCampos.add(error2);
		
		String nombreTabla = "talba";
		Long idArchivo = 1L;
		
		String res;
		
		res = regAutowired.armarUpdateSql(lstCampos, nombreTabla, idArchivo);
		assertNotNull(res);
		assertThat(res.contains("UPDATE")).isEqualTo(true);
		
		error1.setEsValidacion(false);
		error2.setEsValidacion(false);
		res = regAutowired.armarUpdateSql(lstCampos, nombreTabla, idArchivo);
		assertNotNull(res);
		assertThat(res.contains("UPDATE")).isEqualTo(true);
	}

	@Test
	public void testGuardarArchivoProcesado() throws SpddExceptions {
		ArchivoProcesadoDTO archivoProcesadoDTO = new ArchivoProcesadoDTO();
		Long idConfigCargue = 1L;
		
		ArchivoProcesadoDTO res;
		
		when(sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO)).thenReturn(archivoProcesadoDTO);
		res =  regAutowired.guardarArchivoProcesado(archivoProcesadoDTO, idConfigCargue);
		assertNotNull(res);
		
		when(sessionFacadeAFS.guardarArchivoProcesado(archivoProcesadoDTO)).thenThrow(new SpddExceptions());
		res =  regAutowired.guardarArchivoProcesado(archivoProcesadoDTO, idConfigCargue);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testEjecutarJDBCTemplateSQL() throws DataAccessException, SpddExceptions {
		String strSql = "sql";
		
		int res;
		when(sessionFacadeAFS.guardarSql(strSql)).thenReturn(1);
		res = regAutowired.EjecutarJDBCTemplateSQL(strSql);
		assertNotNull(res);
		
		when(sessionFacadeAFS.guardarSql(strSql)).thenThrow(new SpddExceptions());
		res = regAutowired.EjecutarJDBCTemplateSQL(strSql);
		assertNotNull(res);
		assertEquals(-1, res);
	}

}
