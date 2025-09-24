package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IComponenteGestionUsuarioRegistrar.class,
		ComponenteGestionUsuarioRegistrar.class })
public class ComponenteGestionUsuarioRegistrarTest {

	@Autowired
	IComponenteGestionUsuarioRegistrar registrar;

	@MockBean
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	/**
	 * Este objeto maneja el traslado de dto a entidad y viceversa de
	 * componenteGestionUsuario
	 */
	@MockBean
	ComponenteGestionUsuarioMapper componenteGesionUsuarioMapperImpl;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SessionConsultaAFS consultaAFS;

	@MockBean
	SPDDLogger logger;
	
	@MockBean
	IComponenteGestionUsuarioRepo repo;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testCrearGestionComponenteUsuario() throws SpddExceptions, JsonProcessingException {

		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO = new ComponenteGestionUsuarioDTO();
		componenteGestionUsuarioDTO.setCodigoUsuario("Sumset");
		componenteGestionUsuarioDTO.setStringConcatenado("1;2");
		List<ComponenteGestionUsuario> lista = new ArrayList<>();
		when(repo.findByUsuario(componenteGestionUsuarioDTO.getCodigoUsuario())).thenReturn(lista);
		doNothing().when(repo).deleteAll(lista);
		componenteGestionUsuarioDTO.setIdComponenteGestion(1L);
		when(sessionFacadeAFS.guardarComponenteGestionusuario(componenteGestionUsuarioDTO))
				.thenReturn(componenteGestionUsuarioDTO);
		ComponenteGestionUsuarioDTO res = registrar.crearGestionComponenteUsuario(componenteGestionUsuarioDTO);
		assertThat(res.getCodigoUsuario()).isEqualTo(componenteGestionUsuarioDTO.getCodigoUsuario());
	}

}
