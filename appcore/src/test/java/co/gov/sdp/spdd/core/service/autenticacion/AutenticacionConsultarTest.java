package co.gov.sdp.spdd.core.service.autenticacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionConsultar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IAutenticacionConsultar.class, AutenticacionConsultar.class})
public class AutenticacionConsultarTest {

	@Autowired
	IAutenticacionConsultar consultar;
	
	@Test
	public void testIniciarSesion() throws SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		
		UsuariosDTO res = consultar.iniciarSesion(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testVerificarCorreo() throws SpddExceptions {
		UsuariosDTO res = consultar.verificarCorreo(new UsuariosDTO());
		assertThat(res).isEqualTo(null);
	}

}
