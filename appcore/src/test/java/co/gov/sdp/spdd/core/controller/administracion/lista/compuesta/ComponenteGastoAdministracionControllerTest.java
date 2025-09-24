package co.gov.sdp.spdd.core.controller.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IComponenteGastoAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionModificar;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionRegistrar;

@SpringBootTest(classes = {ComponenteGastoAdministracionController.class})
@RunWith(SpringRunner.class)
class ComponenteGastoAdministracionControllerTest {
	
	@Autowired
	IComponenteGastoAdministracionController componente;
	
	@MockBean
	IComponenteGastoAdministracionConsultar consultar;


	// Servicio de modificacion para componente gasto
	@MockBean
	IComponenteGastoAdministracionModificar modificar;

	// Servicio de registro para componente gasto
	@MockBean
	IComponenteGastoAdministracionRegistrar registrar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testCrearComponenteGasto() throws SpddExceptions, JsonProcessingException {
		ComponenteGastoDTO peticion = new ComponenteGastoDTO();
		peticion.setIdComponenteGasto(1L);
		peticion.setCodigoComponente(1L);
		peticion.setNombreComponente("componente1");
		peticion.setIdLsTipoProyecto(1L);
		peticion.setEstado(1);
		ComponenteGastoDTO res;
		when(registrar.crearComponenteGasto(peticion)).thenReturn(peticion);
		res = componente.crearComponenteGasto(peticion);
		assertNotNull(res);
		
		
		when(registrar.crearComponenteGasto(peticion)).thenThrow(new SpddExceptions());
		res = componente.crearComponenteGasto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdComponenteGasto(null);
		peticion.setCodigoComponente(null);
		peticion.setNombreComponente(null);
		peticion.setIdLsTipoProyecto(null);
		peticion.setEstado(null);
		res = componente.crearComponenteGasto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarComponenteGasto() throws SpddExceptions {
		ComponenteGastoDTO peticion = new ComponenteGastoDTO();
		peticion.setIdComponenteGasto(1L);
		peticion.setCodigoComponente(1L);
		peticion.setNombreComponente("componente1");
		peticion.setIdLsTipoProyecto(1L);
		peticion.setEstado(1);
		ComponenteGastoDTO res;
		when(modificar.modificarComponenteGasto(peticion)).thenReturn(peticion);
		res = componente.modificarComponenteGasto(peticion);
		assertNotNull(res);
		
		
		when(modificar.modificarComponenteGasto(peticion)).thenThrow(new SpddExceptions());
		res = componente.modificarComponenteGasto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdComponenteGasto(null);
		peticion.setCodigoComponente(null);
		peticion.setNombreComponente(null);
		peticion.setIdLsTipoProyecto(null);
		peticion.setEstado(null);
		res = componente.modificarComponenteGasto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerComponenteGastoTodos() throws SpddExceptions {
		ComponenteGastoDTO peticion = new ComponenteGastoDTO();
		GenericoDTO respuesta;
		when(consultar.obtenerComponenteGastoTodos(peticion)).thenReturn(new GenericoDTO());
		respuesta = componente.obtenerComponenteGastoTodos(peticion);
		assertNotNull(respuesta);
		
		when(consultar.obtenerComponenteGastoTodos(peticion)).thenThrow(new SpddExceptions());
		respuesta = componente.obtenerComponenteGastoTodos(peticion);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
