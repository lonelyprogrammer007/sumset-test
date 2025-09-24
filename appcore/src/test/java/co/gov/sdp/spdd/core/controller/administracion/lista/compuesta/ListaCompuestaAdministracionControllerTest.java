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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta.IListaCompuestaAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar;
@SpringBootTest(classes = { ListaCompuestaAdministracionController.class})
@RunWith(SpringRunner.class)
class ListaCompuestaAdministracionControllerTest {

	@Autowired
	IListaCompuestaAdministracionController lista;
	
	@MockBean
	IListaCompuestaAdministracionConsultar consultar;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	void testObtenerListaCompuestaTodos() throws SpddExceptions {
		ListaCompuestaDTO peticion = new ListaCompuestaDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = lista.obtenerListaCompuestaTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = lista.obtenerListaCompuestaTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testObtenerPorId() throws SpddExceptions {
		Long id = 1L;
		ListaCompuestaDTO res;
		when(consultar.obtenerLista(id)).thenReturn(new ListaCompuestaDTO());
		res = lista.obtenerPorId(id);
		assertNotNull(res);
		
		when(consultar.obtenerLista(id)).thenThrow(new SpddExceptions());
		res = lista.obtenerPorId(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
