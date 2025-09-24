package co.gov.sdp.spdd.data.dao.impl.bp;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.bp.IVistaBpProyectoInversionRepo;

@RunWith(SpringRunner.class)
public class VistaBpProyectoInversionRepoTest {
	
	@TestConfiguration
	static class VistaBpProyectoInversionRepoTestContextConfiguration {
		@Bean
		public VistaBpProyectoInversionRepo vistaBpProyectoInversionRepo() {
			return new VistaBpProyectoInversionRepo();
		}
	}
	
	@Autowired
	VistaBpProyectoInversionRepo vistaBpProyectoInversionRepo;
	
	@MockBean
	IVistaBpProyectoInversionRepo vistaBpProyectoInversionRepository;

	@Test
	public void testGetRepo() {
		assertNotNull(vistaBpProyectoInversionRepo.getRepo());
	}

}
