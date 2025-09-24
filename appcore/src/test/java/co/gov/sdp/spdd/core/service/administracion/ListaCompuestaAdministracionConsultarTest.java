package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IListaCompuestaAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IListaCompuestaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IListaCompuestaRepo;
import co.gov.sdp.spdd.data.mapper.ListaCompuestaMapper;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, ListaCompuestaAdministracionConsultar.class,
		IListaCompuestaAdministracionConsultar.class})
public class ListaCompuestaAdministracionConsultarTest {

	@Autowired
	IListaCompuestaAdministracionConsultar consultar;
	
	@MockBean
	IListaCompuestaServiceRepo listaCompuestaServiceRepo;

	@MockBean
	IListaCompuestaRepo listaCompuestaRepo;

	@MockBean
	ListaCompuestaMapper listaCompuestaMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	
	@Test
	public void testObtenerListaCompuestaTodos() throws SpddExceptions {
		List<ListaCompuesta> lista=new ArrayList<>();		
		when(listaCompuestaServiceRepo.obtenerTodos()).thenReturn(lista);
        when(listaCompuestaMapper.listaCompuestaEntitiesToDTO(lista)).thenReturn(new ArrayList<ListaCompuestaDTO>());
        GenericoDTO res = consultar.obtenerListaCompuestaTodos();
        assertNotNull(res);
	}

	@Test
	public void testObtenerLista() throws SpddExceptions {
		Long id = 1L;
		ListaCompuestaDTO peticion = new ListaCompuestaDTO();
		ListaCompuesta entidad = new ListaCompuesta();
		ListaCompuestaDTO respuesta = new ListaCompuestaDTO();
		ListaCompuestaDTO res;
		peticion.setIdListaCompuesta(id);
		when(listaCompuestaServiceRepo.obtener(peticion.getIdListaCompuesta())).thenReturn(entidad);
		when(listaCompuestaMapper.listaCompuestaEntityToDTO(entidad)).thenReturn(respuesta);
		
		res = consultar.obtenerLista(peticion.getIdListaCompuesta());
	    assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//else
		when(listaCompuestaServiceRepo.obtener(peticion.getIdListaCompuesta())).thenReturn(null);
		res = consultar.obtenerLista(peticion.getIdListaCompuesta());
	    assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
	}

	@Test
	public void testObtenerPaginado() throws SpddExceptions {
		Long id = 1L;
		ListaCompuestaDTO peticion = new ListaCompuestaDTO();
		peticion.setIdListaCompuesta(id);
        when(sessionFacadeAFS.consultarListaCompuestaPorFiltro(peticion)).thenReturn(new GenericoDTO());
        GenericoDTO res = consultar.obtenerPaginado(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
