package co.gov.sdp.spdd.core.controller.estado.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.estado.servicio.IEstadoServicioController;
import co.gov.sdp.spdd.core.iservice.estado.servicio.IEstadoServicioConsultar;

@SpringBootTest(classes = { EstadoServicioController.class })
@RunWith(SpringRunner.class)
class EstadoServicioControllerTest {
	
	@Autowired
	IEstadoServicioController servicio;
	
	@MockBean
    IEstadoServicioConsultar consultar;
    
    /**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	
	@Test
	void testObtenerTodos() throws SpddExceptions {
		EstadoServicioDTO peticion = new EstadoServicioDTO();
		GenericoDTO res;
		when(consultar.obtenerTodos(peticion)).thenReturn(new GenericoDTO());
		res = servicio.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerTodos(peticion)).thenThrow(new SpddExceptions());
		res = servicio.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
