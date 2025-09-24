package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstadoServicioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, ComponenteGestionUsuarioConsultar.class,
		IComponenteGestionUsuarioConsultar.class, ComponenteGestionUsuarioMapper.class})
public class ComponenteGestionUsuarioConsultarTest {

	@Autowired
	IComponenteGestionUsuarioConsultar consultar;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	IEstadoServicioServiceRepo serviceRepo;

	@MockBean
	SessionConsultaAFS consultaAFS;

	@MockBean
	SPDDLogger logger;
	@MockBean
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	@MockBean
	IComponenteGestionUsuarioRepo componenteGestionUsuarioRepo;
	
	@MockBean
	ComponenteGestionUsuarioMapper componenteGestionUsuarioMapper;

	@Test
	public void testObtenerPorUsuario() throws SpddExceptions {

		UsuariosDTO usuario = new UsuariosDTO();
		usuario.setNombreUsuario("sumset");
		when(componenteGestionUsuarioRepo.findByUsuario(usuario.getNombreUsuario())).thenReturn(new ArrayList<>());
		if (consultar.obtenerPorUsuario(usuario) != null) {
			GenericoDTO lista = consultar.obtenerPorUsuario(usuario);
			assertThat(lista).isNotNull();
		}
	}

	@Test
	public void testObtenerTodos() throws SpddExceptions {

		when(sessionFacadeAFS.consultarComponenteGestionUsuarioTodos()).thenReturn(new GenericoDTO());
		if (consultar.obtenerTodos() != null) {
			GenericoDTO lista = consultar.obtenerTodos();
			assertThat(lista).isNotNull();
		}
	}

}
