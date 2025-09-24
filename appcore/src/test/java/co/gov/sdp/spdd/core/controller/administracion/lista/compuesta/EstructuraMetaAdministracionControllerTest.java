package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IEstructuraMetaAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionRegistrar;
@SpringBootTest(classes = { EstructuraMetaAdministracionController.class })
@RunWith(SpringRunner.class)
class EstructuraMetaAdministracionControllerTest {

	@Autowired
	IEstructuraMetaAdministracionController meta;
	
	@MockBean
	IEstructuraMetaAdministracionConsultar consultar;


	// Servicio de modificacion para estructura meta
	@MockBean
	IEstructuraMetaAdministracionModificar modificar;

	// Servicio de registro para estructura meta
	@MockBean
	IEstructuraMetaAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testCrearEstructuraMeta() throws SpddExceptions, JsonProcessingException {
		EstructuraMetaDTO peticion = new EstructuraMetaDTO();
		peticion.setIdEstructuraMetas(1L);
		peticion.setIdLsUnidadMedida(1L);
		peticion.setIdLsVerbo(1L);
		EstructuraMetaDTO res;
		when(registrar.crearEstructuraMeta(peticion)).thenReturn(peticion);
		res = meta.crearEstructuraMeta(peticion);
		assertNotNull(res);
		
		peticion.setIdEstructuraMetas(null);
		peticion.setIdLsUnidadMedida(null);
		peticion.setIdLsVerbo(null);
		when(registrar.crearEstructuraMeta(peticion)).thenReturn(peticion);
		res = meta.crearEstructuraMeta(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarEstructuraMeta() throws SpddExceptions {
		EstructuraMetaDTO peticion = new EstructuraMetaDTO();
		peticion.setIdEstructuraMetas(1L);
		peticion.setIdLsUnidadMedida(1L);
		peticion.setIdLsVerbo(1L);
		peticion.setEstado(1);
		EstructuraMetaDTO res;
		when(modificar.modificarEstructuraMeta(peticion)).thenReturn(peticion);
		res = meta.modificarEstructuraMeta(peticion);
		assertNotNull(res);
		
		peticion.setIdEstructuraMetas(null);
		peticion.setIdLsUnidadMedida(null);
		peticion.setIdLsVerbo(null);
		
		res = meta.modificarEstructuraMeta(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerEstructuraMetaTodos() throws SpddExceptions, JsonProcessingException {
		EstructuraMetaDTO peticion = new EstructuraMetaDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = meta.obtenerEstructuraMetaTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = meta.obtenerEstructuraMetaTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
