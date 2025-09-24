package co.gov.sdp.spdd.core.controller.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IArgumentoListaSimpleController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleRegistrar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ArgumentoListaSimpleController.class })
public class ArgumentoListaSimpleControllerTest {

	@Autowired
	IArgumentoListaSimpleController argumento;

	@MockBean
	IArgumentoListaSimpleRegistrar registrar;

	@MockBean
	IArgumentoListaSimpleConsultar consultar;

	@MockBean
	IArgumentoListaSimpleModificar modificar;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testRegistrarArgumentoListaSimple() throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();

		peticion.setEstado(1);
		peticion.setIdArgumento(1L);
		peticion.setArgumento("arg1");
		peticion.setResultado("res1");
		peticion.setIdListaSimple(1L);
		ArgumentoListaSimpleDTO res;

		when(registrar.registrarArgumentoListaSimple(peticion)).thenReturn(peticion);
		res = argumento.registrarArgumentoListaSimple(peticion);
		assertNotNull(res);

		peticion.setIdArgumento(null);
		peticion.setIdListaSimple(null);
		when(registrar.registrarArgumentoListaSimple(peticion)).thenReturn(peticion);
		res = argumento.registrarArgumentoListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		peticion.setIdArgumento(2L);
		peticion.setIdListaSimple(3L);
		when(registrar.registrarArgumentoListaSimple(peticion)).thenThrow(new SpddExceptions());
		res = argumento.registrarArgumentoListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarArgumentoListaSimple() throws SpddExceptions, JsonProcessingException {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setEstado(1);
		peticion.setIdArgumento(1L);
		peticion.setArgumento("arg1");
		peticion.setResultado("res1");
		peticion.setIdListaSimple(1L);

		ArgumentoListaSimpleDTO res;

		when(modificar.modificarArgumento(peticion)).thenReturn(peticion);
		res = argumento.modificarArgumentoListaSimple(peticion);
		assertNotNull(res);

		peticion.setIdArgumento(null);
		peticion.setIdListaSimple(null);
		when(modificar.modificarArgumento(peticion)).thenReturn(peticion);
		res = argumento.modificarArgumentoListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

		peticion.setIdArgumento(2L);
		peticion.setIdListaSimple(3L);
		when(modificar.modificarArgumento(peticion)).thenThrow(new SpddExceptions());
		res = argumento.modificarArgumentoListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);

	}

	@Test
	public void testObtenerArgumentoPorNombre() throws SpddExceptions {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setArgumento("sumset");

		GenericoDTO res;
		when(consultar.obtenerPorNombre(peticion.getArgumento())).thenReturn(new GenericoDTO());
		res = argumento.obtenerArgumentoPorNombre(peticion.getArgumento());

		assertNotNull(res);

		when(consultar.obtenerPorNombre(peticion.getArgumento())).thenThrow(new SpddExceptions());
		res = argumento.obtenerArgumentoPorNombre(peticion.getArgumento());
		assertNotNull(res);

		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testObtenerTodosPorFiltro() throws SpddExceptions {
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		GenericoDTO res;
		when(consultar.obtenerPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = argumento.obtenerTodosPorFiltro(peticion);

		assertNotNull(res);

		when(consultar.obtenerPorFiltro(peticion)).thenThrow(new SpddExceptions());
		res = argumento.obtenerTodosPorFiltro(peticion);

		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testConsultarArgumentoPorIdPlanDesarrollo() throws SpddExceptions {

		Long id= 1L;
		GenericoDTO res;
		when(consultar.consultarArgumentoPorIdPlanDesarrollo(id)).thenReturn(new GenericoDTO());
		res = argumento.consultarArgumentoPorIdPlanDesarrollo(id);
		assertNotNull(res);
		
		when(consultar.consultarArgumentoPorIdPlanDesarrollo(id)).thenThrow(new SpddExceptions());
		res = argumento.consultarArgumentoPorIdPlanDesarrollo(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
