package co.gov.sdp.spdd.core.controller.administracion;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.icontroller.administracion.IUsuarioAdministracionController;
import co.gov.sdp.spdd.core.iservice.administracion.IUsuarioAdmnistracionConsultar;
@SpringBootTest(classes = {UsuarioAdministracionController.class})
@RunWith(SpringRunner.class)
class UsuarioAdministracionControllerTest {
	
	@MockBean
	IUsuarioAdmnistracionConsultar consultar;
	
	@Autowired
	IUsuarioAdministracionController usuario;
	
	@Test
	void testConsultarUsuario() throws JsonProcessingException, SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		when(consultar.consultarUsuario(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO res = usuario.consultarUsuario(peticion);
	}

}
