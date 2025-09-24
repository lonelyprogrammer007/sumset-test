package co.gov.sdp.spdd.core.service.buzon.notificacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.mapper.BuzonNotificacionesMapper;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IBuzonNotificacionConsultar.class, BuzonNotificacionConsultar.class})
public class BuzonNotificacionConsultarTest {
	
	@Autowired
	IBuzonNotificacionConsultar consultar;
	
	@MockBean
	BuzonNotificacionesMapper mapper;

	@MockBean
	IBuzonNotificacioneServiceRepo buzonNotificacionesServiceRepo;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@Test
	public void testObtenerTodosAdmin() throws SpddExceptions {
		
		List<BuzonNotificaciones> entidad = new ArrayList<>();
		List<BuzonNotificacionesDTO> listaRespuesta = new ArrayList<>();
		
		GenericoDTO res;
		
		when(buzonNotificacionesServiceRepo.obtenerTodos()).thenReturn(entidad);
		when(mapper.buzonNotificacionesEntitiesToDTO(entidad)).thenReturn(listaRespuesta);		
		res = consultar.obtenerTodosAdmin();
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testObtenerTodosPorUsuario() throws SpddExceptions {
		String usuario = "usuario";
		
		GenericoDTO respuesta = new GenericoDTO();
		
		GenericoDTO res;
		
		when(sessionFacadeAFS.obtenerBuzonPorUsuario(usuario)).thenReturn(respuesta);
		res = consultar.obtenerTodosPorUsuario(usuario);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testNotificacionesPorUsuario() throws SpddExceptions {
		Long res = 1L;
		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
		when(sessionFacadeAFS.notificacionesPorUsuario(peticion)).thenReturn(res);
		res = consultar.notificacionesPorUsuario(peticion);
		assertNotNull(res);
	}

	@Test
	public void testObtenerPaginado() throws SpddExceptions {
		
		BuzonNotificacionesDTO peticion =  new BuzonNotificacionesDTO();
		GenericoDTO res;
		
		when(sessionFacadeAFS.consultarBuzonNotificacionesPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
