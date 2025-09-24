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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IListaSimpleController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleEliminar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IAdministracionListaSimpleRegistrar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ListaSimpleController.class })
public class ListaSimpleControllerTest {

	@Autowired
	IListaSimpleController listaSimple;
	
	@MockBean
	IAdministracionListaSimpleConsultar consultar;

	// Servicio que permite consultar una lista simple
	@MockBean
	IAdministracionListaSimpleEliminar eliminar;

	// Servicio que permite consultar una lista simple
	@MockBean
	IAdministracionListaSimpleRegistrar registrar;

	// Servicio que permite consultar una lista simple
	@MockBean
	IAdministracionListaSimpleModificar modificar;

	
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	public void testCrearListSimple() throws SpddExceptions {
		
		ListaSimpleDTO peticion = new ListaSimpleDTO();
		peticion.setIdListaSimple(1L);
		peticion.setNombre("sumset");
		peticion.setDescripcion("descripcion");
		ListaSimpleDTO res;
		
		when(registrar.guardarListaSimple(peticion)).thenReturn(peticion);
		res = listaSimple.crearListSimple(peticion);
		assertNotNull(res);
		
		peticion.setIdListaSimple(null);
		peticion.setNombre(null);
		peticion.setDescripcion(null);
		when(registrar.guardarListaSimple(peticion)).thenReturn(peticion);
		res = listaSimple.crearListSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdListaSimple(1L);
		peticion.setNombre("aumse");
		peticion.setDescripcion("sjsk");
		when(registrar.guardarListaSimple(peticion)).thenThrow(new SpddExceptions());
		res = listaSimple.crearListSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarListaSimple() throws SpddExceptions {
		ListaSimpleDTO peticion = new ListaSimpleDTO();
		peticion.setIdListaSimple(1L);
		peticion.setNombre("sumset");
		peticion.setDescripcion("descripcion");
		ListaSimpleDTO res;
		
		when(modificar.modificarListaSimple(peticion)).thenReturn(peticion);
		res = listaSimple.modificarListaSimple(peticion);
		assertNotNull(res);
		
		peticion.setIdListaSimple(null);
		peticion.setNombre(null);
		peticion.setDescripcion(null);
		when(modificar.modificarListaSimple(peticion)).thenReturn(peticion);
		res = listaSimple.modificarListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdListaSimple(1L);
		peticion.setNombre("hhj");
		peticion.setDescripcion("ajsa");
		when(modificar.modificarListaSimple(peticion)).thenThrow(new SpddExceptions());
		res = listaSimple.modificarListaSimple(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testObtenerPorId() throws SpddExceptions {
		Long id= 2L;
		ListaSimpleDTO respuesta = new ListaSimpleDTO();
		ListaSimpleDTO res;
		when(consultar.obtenerPorId(id)).thenReturn(respuesta);
		res = listaSimple.obtenerPorId(id);
		assertNotNull(res);
		
		when(consultar.obtenerPorId(id)).thenThrow(new SpddExceptions());
		res = listaSimple.obtenerPorId(id);
		assertNotNull(res);
	}

	@Test
	public void testPaginado() throws SpddExceptions {
		ListaSimpleDTO peticion = new ListaSimpleDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = listaSimple.paginado(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = listaSimple.paginado(peticion);
		assertNotNull(res);
	}

}
