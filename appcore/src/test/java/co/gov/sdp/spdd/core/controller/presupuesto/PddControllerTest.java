package co.gov.sdp.spdd.core.controller.presupuesto;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.icontroller.presupuesto.IPddController;
import co.gov.sdp.spdd.core.iservice.presupuesto.IPddConsultar;
@SpringBootTest(classes = { PddController.class })
@RunWith(SpringRunner.class)
class PddControllerTest {
	
	@Autowired
	IPddController pdd;
	
	@MockBean
	IPddConsultar consultar;
	
	@Test
	void testConsultarPdd() throws SpddExceptions {
		GenericoDTO res;
		when(consultar.consultarPdd()).thenReturn(new GenericoDTO());
		res = pdd.consultarPdd();
		assertNotNull(res);
	}

}
