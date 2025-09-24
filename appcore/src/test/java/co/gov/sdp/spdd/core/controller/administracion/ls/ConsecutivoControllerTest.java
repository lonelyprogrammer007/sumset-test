package co.gov.sdp.spdd.core.controller.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IConsecutivoController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoConsultar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoModificar;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ConsecutivoController.class })
public class ConsecutivoControllerTest {
	
	@Autowired 
	IConsecutivoController consecutivo;
	
	@MockBean
	IConsecutivoConsultar consultar;

	
	@MockBean
	IConsecutivoModificar modificar;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testEditatConsecutivo() throws SpddExceptions, IOException {
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		peticion.setIdPlanDesarrollo(1L);
		peticion.setNombre("sumset");
		peticion.setDescripcion("descripcion");
		peticion.setSecuencia(1L);
		peticion.setVigencia("vigencia");
		peticion.setCodigoEntidad("1");
		peticion.setIdConsecutivo(1L);
		ConsecutivoDTO res;
		
		when(modificar.modificarConsecutivo(peticion)).thenReturn(peticion);
		res = consecutivo.editatConsecutivo(peticion);
		assertNotNull(res);
		
		peticion.setIdPlanDesarrollo(null);
		peticion.setIdConsecutivo(null);
		when(modificar.modificarConsecutivo(peticion)).thenReturn(peticion);
		res = consecutivo.editatConsecutivo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
		peticion.setIdPlanDesarrollo(1L);
		peticion.setIdConsecutivo(1L);
		when(modificar.modificarConsecutivo(peticion)).thenThrow(new SpddExceptions());
		res = consecutivo.editatConsecutivo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testObtenerTodos() throws SpddExceptions {
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = consecutivo.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = consecutivo.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
