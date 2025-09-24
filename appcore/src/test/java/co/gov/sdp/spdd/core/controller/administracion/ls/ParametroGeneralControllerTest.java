package co.gov.sdp.spdd.core.controller.administracion.ls;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.administracion.ls.IParametroGeneralController;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParametroGeneralModificar;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParanetroGeneralConsultar;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParametroGeneralController.class })
public class ParametroGeneralControllerTest {

	@Autowired
	IParametroGeneralController parametro;
	
	
	@MockBean
	IParanetroGeneralConsultar consultar;

	
	@MockBean
	IParametroGeneralModificar modificar;

	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	public void testEditarParametro() throws SpddExceptions, JsonProcessingException {
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		peticion.setCodigoParametro("1");
		peticion.setNombre("alaa");
		peticion.setArgumento("arg");
		peticion.setDescripcion("aaa");
		peticion.setFechaCreacion("20/05/2020");
		peticion.setFechaModificacion("20/05/2020");
		peticion.setUsuarioCreacion("alex");
		peticion.setUsuarioModificacion("alex");
		
		ParametroGeneralDTO res;
				
		when(modificar.editarParametro(peticion)).thenReturn(peticion);
		res = parametro.editarParametro(peticion);
		assertNotNull(res);
		
		peticion.setCodigoParametro(null);
		peticion.setNombre(null);
		peticion.setArgumento(null);
		peticion.setDescripcion(null);
		peticion.setFechaCreacion(null);
		peticion.setFechaModificacion(null);
		peticion.setUsuarioCreacion(null);
		peticion.setUsuarioModificacion(null);
		
		when(modificar.editarParametro(peticion)).thenReturn(peticion);
		res = parametro.editarParametro(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setCodigoParametro("1");
		peticion.setNombre("alaa");
		peticion.setArgumento("arg");
		peticion.setDescripcion("aaa");
		peticion.setFechaCreacion("20/05/2020");
		peticion.setFechaModificacion("20/05/2020");
		peticion.setUsuarioCreacion("alex");
		peticion.setUsuarioModificacion("alex");
		
		when(modificar.editarParametro(peticion)).thenThrow(new SpddExceptions());
		res = parametro.editarParametro(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testObtenerTodos() throws SpddExceptions {
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		GenericoDTO res;
		when(consultar.obtenerPaginado(peticion)).thenReturn(new GenericoDTO());
		res = parametro.obtenerTodos(peticion);
		assertNotNull(res);
		
		when(consultar.obtenerPaginado(peticion)).thenThrow(new SpddExceptions());
		res = parametro.obtenerTodos(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

}
