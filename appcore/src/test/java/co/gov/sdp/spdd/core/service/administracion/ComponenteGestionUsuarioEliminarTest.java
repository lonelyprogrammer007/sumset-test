package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IComponenteGestionUsuarioEliminar.class,
		ComponenteGestionUsuarioEliminar.class })
public class ComponenteGestionUsuarioEliminarTest {

	@Autowired
	IComponenteGestionUsuarioEliminar eliminar;

	@MockBean
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

	/**
	 * Este objeto maneja el traslado de dto a entidad y viceversa de
	 * componenteGestionUsuario
	 */
	@MockBean
	ComponenteGestionUsuarioMapper componenteGesionUsuarioMapperImpl;

	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * 
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testEliminarComponenteUsuario() throws SpddExceptions {
		ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO=new ComponenteGestionUsuarioDTO();
		componenteGestionUsuarioDTO.setIdComponenteGestion(1L);
		doNothing().when(sessionFacadeAfs).eliminarComponenteGestionUsuario(componenteGestionUsuarioDTO.getIdComponenteGestion());
		ComponenteGestionUsuarioDTO res=eliminar.eliminarComponenteUsuario(componenteGestionUsuarioDTO);
		assertThat(componenteGestionUsuarioDTO.getIdComponenteGestion()).isEqualTo((res.getIdComponenteGestion()));
	}

}
