package co.gov.sdp.spdd.core.controller.buzon.notificacion;

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

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.buzon.notificacion.IBuzonNotificacionController;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionConsultar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionModificar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionRegistrar;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BuzonNotificacionController.class })
public class BuzonNotificacionControllerTest {

	@Autowired
	IBuzonNotificacionController buzon;
	
	@MockBean
	IBuzonNotificacionConsultar consultar;


	@MockBean
	IBuzonNotificacionModificar modificar;

	
	@MockBean
	IBuzonNotificacionRegistrar registrar;


	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	public void testObtenerTodosAdmin() throws SpddExceptions {
	BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
	GenericoDTO res;
	
	when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
	res = buzon.obtenerTodosAdmin(peticion);
	assertNotNull(res);
	
	when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
	res = buzon.obtenerTodosAdmin(peticion);
	assertNotNull(res);
	assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testObtenerPorUsuario() throws SpddExceptions {
		String usuario = "alex";
		GenericoDTO res;
		when(consultar.obtenerTodosPorUsuario(usuario)).thenReturn(new GenericoDTO());
		res = buzon.obtenerPorUsuario(usuario);
		assertNotNull(res);
		
		when(consultar.obtenerTodosPorUsuario(usuario)).thenThrow(new SpddExceptions());
		res = buzon.obtenerPorUsuario(usuario);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testDarRespuesta() throws SpddExceptions {
		
		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
		BuzonNotificacionesDTO res;
		when(modificar.darRespuesta(peticion)).thenReturn(peticion);
		res = buzon.darRespuesta(peticion);
		assertNotNull(res);
		
		when(modificar.darRespuesta(peticion)).thenThrow(new SpddExceptions());
		res = buzon.darRespuesta(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	public void testRegistrarBuzonNotificacion() throws JsonProcessingException, SpddExceptions {
		
		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO();
		BuzonNotificacionesDTO res;
		when(registrar.registarBuzonNotificacion(peticion)).thenReturn(peticion);
		res = buzon.registrarBuzonNotificacion(peticion);
		assertNotNull(res);
		
//		when(registrar.registarBuzonNotificacion(peticion)).thenThrow(json);
//		res = buzon.registrarBuzonNotificacion(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(500L);
		
		when(registrar.registarBuzonNotificacion(peticion)).thenThrow(new SpddExceptions());
		res = buzon.registrarBuzonNotificacion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(500L);
		
	}

}
