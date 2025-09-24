package co.gov.sdp.spdd.core.controller.autenticacion;

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

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.autenticacion.IUsuarioAutenticacionController;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionConsultar;
import co.gov.sdp.spdd.core.iservice.autenticacion.IAutenticacionModificar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UsuarioAutenticacionController.class })
public class UsuarioAutenticacionControllerTest {

	@Autowired
	IUsuarioAutenticacionController autenticacion;
	
	@MockBean
	IAutenticacionConsultar consultar;


	@MockBean
	IAutenticacionModificar modificar;


	@MockBean
	SPDDLogger spddLogger;

	
	@Test
	public void testCambiarClave() throws JsonProcessingException, SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		UsuariosDTO res;
		when(modificar.cambiarClave(peticion)).thenReturn(peticion);
		res = autenticacion.cambiarClave(peticion);
		assertNotNull(res);
		
		peticion.setCorreo(null);
		peticion.setNombreUsuario(null);
		peticion.setClave(null);
		peticion.setContraseniaActual(null);
		peticion.setContraseniaNueva(null);
		peticion.setTipo(null);
		
		when(modificar.cambiarClave(peticion)).thenReturn(peticion);
		res = autenticacion.cambiarClave(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		
		when(modificar.cambiarClave(peticion)).thenThrow(new SpddExceptions());
		res = autenticacion.cambiarClave(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	public void testIniciarSesion() throws SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		UsuariosDTO res;
		when(consultar.iniciarSesion(peticion)).thenReturn(peticion);
		res = autenticacion.iniciarSesion(peticion);
		assertNotNull(res);
		
		peticion.setCorreo(null);
		peticion.setNombreUsuario(null);
		peticion.setClave(null);
		peticion.setContraseniaActual(null);
		peticion.setContraseniaNueva(null);
		peticion.setTipo(null);
		when(consultar.iniciarSesion(peticion)).thenReturn(peticion);
		res = autenticacion.iniciarSesion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		
		when(consultar.iniciarSesion(peticion)).thenThrow(new SpddExceptions());
		res = autenticacion.iniciarSesion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRestablecerClave() throws JsonProcessingException, SpddExceptions {
		
		UsuariosDTO peticion = new UsuariosDTO();
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		UsuariosDTO res;
		when(modificar.reestablecerContrasenia(peticion)).thenReturn(peticion);
		res = autenticacion.restablecerClave(peticion);
		assertNotNull(res);
		
		peticion.setCorreo(null);
		peticion.setNombreUsuario(null);
		peticion.setClave(null);
		peticion.setContraseniaActual(null);
		peticion.setContraseniaNueva(null);
		peticion.setTipo(null);
		
		when(modificar.reestablecerContrasenia(peticion)).thenReturn(peticion);
		res = autenticacion.restablecerClave(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setCorreo("correo@");
		peticion.setNombreUsuario("alex");
		peticion.setClave("123");
		peticion.setContraseniaActual("123");
		peticion.setContraseniaNueva("1234");
		peticion.setTipo("uni");
		when(modificar.reestablecerContrasenia(peticion)).thenThrow(new SpddExceptions());
		res = autenticacion.restablecerClave(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
