package co.gov.sdp.spdd.core.service.buzon.notificacion;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionRegistrar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IConfiguracionNotificacionRegistrar.class,ConfiguracionNotificacionRegistrar.class})
public class ConfiguracionNotificacionRegistrarTest {
	
	@Autowired
	IConfiguracionNotificacionRegistrar registrar;

	@Test
	public void testCrearNotificacionManual() {
		ConfiguracionNotificacionDTO res = registrar.crearNotificacionManual(new ConfiguracionNotificacionDTO());
		assertNull(res);
	}

	@Test
	public void testCrearNotificacionAutomatica() {
		ConfiguracionNotificacionDTO res = registrar.crearNotificacionAutomatica(new ConfiguracionNotificacionDTO());
		assertNull(res);
	}

}
