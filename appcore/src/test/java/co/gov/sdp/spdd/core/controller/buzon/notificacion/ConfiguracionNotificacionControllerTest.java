package co.gov.sdp.spdd.core.controller.buzon.notificacion;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionConsultar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionModificar;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionRegistrar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ConfiguracionNotificacionController.class })
public class ConfiguracionNotificacionControllerTest {

	@Autowired
	ConfiguracionNotificacionController configuracion;
	
	@MockBean
	IConfiguracionNotificacionConsultar consultar;
	
	@MockBean
	IConfiguracionNotificacionRegistrar registrar;

	@MockBean
	IConfiguracionNotificacionModificar modificar;

	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	public void testCrearNotificacionManual() {
		ConfiguracionNotificacionDTO res;
		ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
		res = configuracion.crearNotificacionManual(peticion);
		assertNull(res);
		
		
	}

	@Test
	public void testCrearNotificacionAutomatica() {
		ConfiguracionNotificacionDTO res;
		ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
		res = configuracion.crearNotificacionAutomatica(peticion);
		assertNull(res);
	}

	@Test
	public void testObtenerAutomaticos() {
		ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
		GenericoDTO retorno = new GenericoDTO();
		GenericoDTO res;
		when(consultar.obtenerAutomaticos()).thenReturn(retorno);
		res = configuracion.obtenerAutomaticos(peticion);
		assertNotNull(res);
	}

	@Test
	public void testModificarConfiguracion() {
	 ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
	 ConfiguracionNotificacionDTO respuesta = new ConfiguracionNotificacionDTO();
	 peticion.setIdConfigNotificacion(1L);
	 peticion.setAsunto("Mensaje");
	 peticion.setMensaje("prueba mensaje");
	 peticion.setOperacionOrigen("Ejemplo");
	 peticion.setRequiereAccion(1L);
	 ConfiguracionNotificacionDTO res;
	 when(modificar.modificarNotificacion(peticion)).thenReturn(respuesta);
	 res = configuracion.modificarConfiguracion(peticion);
	 assertNotNull(res);
	 
	 peticion.setIdConfigNotificacion(null);
	 peticion.setAsunto(null);
	 peticion.setMensaje(null);
	 peticion.setOperacionOrigen(null);
	 peticion.setRequiereAccion(null);
	 
	 when(modificar.modificarNotificacion(peticion)).thenReturn(respuesta);
	 res = configuracion.modificarConfiguracion(peticion);
	 assertNotNull(res);
	 assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
