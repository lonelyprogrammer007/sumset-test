package co.gov.sdp.spdd.data.serviciofacade.bp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SessionEliminarBPTest extends SessionBPTest {
	
	@TestConfiguration
	static class SessionEliminarBPTestContextConfiguration {
		@Bean
		public SessionEliminarBP sessionEliminarBP() {
			return new SessionEliminarBP();
		}
	}
	
	@Autowired
	SessionEliminarBP sessionEliminarBP;

	@Test
	public void testEliminarBpProyInvTiposDeIdProyectoInversion() throws Exception {
	}

	@Test
	public void testEliminarGruposEtarios() throws Exception {
	}

	@Test
	public void testEliminarUbicaciones() throws Exception {
	}

	@Test
	public void testEliminatIniciativaCiudadana() throws Exception {
	}

	@Test
	public void testEliminarBpProyInvAporte() throws Exception {
	}

	@Test
	public void testEliminarBpAporteCiudadano() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto() throws Exception {
	}

	@Test
	public void testEliminarIniciativaCondiciones() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvIniciativaPorIdProyInversion() throws Exception {
	}

	@Test
	public void testEliminarTodosBpProyInvLocalizaPorIdProyInversion() throws Exception {
	}

}
