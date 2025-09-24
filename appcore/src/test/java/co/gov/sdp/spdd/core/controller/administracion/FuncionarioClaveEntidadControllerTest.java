package co.gov.sdp.spdd.core.controller.administracion;

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

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.IFuncionarioClaveEntidadController;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadModificar;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadRegistrar;

@SpringBootTest(classes = FuncionarioClaveEntidadController.class)
@RunWith(SpringRunner.class)
class FuncionarioClaveEntidadControllerTest {
	
	@Autowired
	IFuncionarioClaveEntidadController funcionario;
	
	// Servicio de consulta
	@MockBean
	IFuncionarioClaveEntidadConsultar consultar;

	// Servicio de registro
	@MockBean
	IFuncionarioClaveEntidadRegistrar registrar;

	// Servicio de registro
	@MockBean
	IFuncionarioClaveEntidadModificar modificar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testObtenerFuncionarioClaveEntidad() throws SpddExceptions {
		GenericoDTO res;
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		peticion.setIdFuncionarioEntidad(1L);
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = funcionario.obtenerFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = funcionario.obtenerFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testCrearFuncionarioClaveEntidad() throws SpddExceptions, JsonProcessingException {
		FuncionarioClaveEntidadDTO res;
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		peticion.setIdFuncionarioEntidad(1L);
		peticion.setCodigoEntidad("entidad1");
		peticion.setNombre("sumset");
		peticion.setIdLsFuncion(1L);
		peticion.setIdLsGenero(1L);
		peticion.setCargo("cargo1");
		peticion.setCorreo("sumset1@sumset.com");
		
		when(registrar.crearFuncionarioClaveEntidad(peticion)).thenReturn(peticion);
		res = funcionario.crearFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		
		when(registrar.crearFuncionarioClaveEntidad(peticion)).thenThrow(new SpddExceptions());
		res = funcionario.crearFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
		peticion.setIdLsFuncion(null);
		peticion.setIdLsGenero(null);
		
		res = funcionario.crearFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	void testModificarFuncionarioClaveEntidad() throws SpddExceptions {
		FuncionarioClaveEntidadDTO res;
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		peticion.setIdFuncionarioEntidad(1L);
		peticion.setCodigoEntidad("entidad1");
		peticion.setNombre("sumset");
		peticion.setIdLsFuncion(1L);
		peticion.setIdLsGenero(1L);
		peticion.setCargo("cargo1");
		peticion.setCorreo("sumset1@sumset.com");
		
		when(modificar.modificarFuncionarioClaveEntidad(peticion)).thenReturn(peticion);
		res = funcionario.modificarFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		
		when(modificar.modificarFuncionarioClaveEntidad(peticion)).thenThrow(new SpddExceptions());
		res = funcionario.modificarFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
		peticion.setIdLsFuncion(null);
		peticion.setIdLsGenero(null);
		
		res = funcionario.modificarFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
