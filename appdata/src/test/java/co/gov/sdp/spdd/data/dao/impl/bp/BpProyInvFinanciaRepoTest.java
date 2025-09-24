package co.gov.sdp.spdd.data.dao.impl.bp;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.spdd.data.irepositorio.bp.IBpProyInvFinanciaRepo;

@RunWith(SpringRunner.class)
public class BpProyInvFinanciaRepoTest {
	
	@TestConfiguration
	static class BpProyInvFinanciaTestContextConfiguration {
		@Bean
		public BpProyInvFinanciaRepo bpProyInvFinanciaRepo() {
			return new BpProyInvFinanciaRepo();
		}
	}
	
	
	@Autowired
	private BpProyInvFinanciaRepo bpProyInvFinanciaRepo;
	
	@MockBean
	private IBpProyInvFinanciaRepo iBpProyInvFinanciaRepo;

}
