package co.gov.sdp.spdd.core.controller.carga;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.carga.IArchivoProcesadoCargaController;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaConsultar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaEliminar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaModificar;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaRegistrar;
import co.gov.sdp.spdd.core.iservice.carga.IConfiguracionCargueArchivoConsultar;

@SpringBootTest(classes = { ArchivoProcesadoCargaController.class })
@RunWith(SpringRunner.class)
class ArchivoProcesadoCargaControllerTest {
	
	@Autowired
	IArchivoProcesadoCargaController archivoPlano;
	
	// Servicio de consulta para archivo procesado
	@MockBean
	IArchivoProcesadoCargaConsultar consultar;

	// Servicio de eliminacion para archivo procesado
	@MockBean
	IArchivoProcesadoCargaEliminar eliminar;

	// Servicio de modificacion para archivo procesado
	@MockBean
	IArchivoProcesadoCargaModificar modificar;

	// Servicio de registro para archivo procesado
	@MockBean
	IArchivoProcesadoCargaRegistrar registrar;

	/**
	 * 
	 */
	@MockBean
	IConfiguracionCargueArchivoConsultar consultarConfiguracionCargue;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	MultipartFile archivo;

	@Test
	void testCrearArchivoProcesado() throws SpddExceptions, DataAccessException, IOException {
		Long tema = 1L;
		String usuario = "usuario";
		Long modulo = 2L;
		
		CargaAchivoDTO peticion = new CargaAchivoDTO();
		peticion.setUsuario(usuario);
		peticion.setTema(tema);
		peticion.setModulo(modulo);
		peticion.setNombreArchivo(archivo.getOriginalFilename());
		
		CarguePlanoDTO respuesta = new CarguePlanoDTO();
		respuesta.setCodigo(123);
		
		CarguePlanoDTO res;
		
		when(registrar.cargarArchivoProcesado(archivo.getInputStream(), peticion)).thenReturn(respuesta);
		res = archivoPlano.crearArchivoProcesado(archivo, tema, usuario, modulo);
		assertNotNull(res);
		
		
		when(registrar.cargarArchivoProcesado(archivo.getInputStream(), peticion)).thenThrow(new SpddExceptions());
		res = archivoPlano.crearArchivoProcesado(archivo, tema, usuario, modulo);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerArchivoProcesadoTodos() throws SpddExceptions {
		
		ArchivoProcesadoDTO peticion = new ArchivoProcesadoDTO();
		
		GenericoDTO res;
		
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = archivoPlano.obtenerArchivoProcesadoTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = archivoPlano.obtenerArchivoProcesadoTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testConsultarConfigCargueArchivoProcesado() throws SpddExceptions {
		
		Long id = 1L;
		
		ConfiguracionCargueArchivoDTO res;
		
		when(consultarConfiguracionCargue.consultarConfigCargueArchivoProcesado(id)).thenReturn(new ConfiguracionCargueArchivoDTO());
		res = archivoPlano.consultarConfigCargueArchivoProcesado(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(consultarConfiguracionCargue.consultarConfigCargueArchivoProcesado(id)).thenThrow(new SpddExceptions());
		res = archivoPlano.consultarConfigCargueArchivoProcesado(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
