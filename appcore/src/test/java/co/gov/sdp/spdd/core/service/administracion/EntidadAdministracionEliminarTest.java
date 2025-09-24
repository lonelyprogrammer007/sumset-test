package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEntidadAdministracionEliminar.class,
		EntidadAdministracionEliminar.class })
public class EntidadAdministracionEliminarTest {

	@Autowired
	IEntidadAdministracionEliminar eliminar;

	@MockBean
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;

	@MockBean
	SPDDLogger spddLogger;

	// Mapper de usuario entidad
	@MockBean
	UsuarioEntidadMapper usuarioEntidadMapper;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testRemoverUsuarioEntidad() throws SpddExceptions, JsonProcessingException {

		UsuarioEntidadDTO peticion = new UsuarioEntidadDTO();
		peticion.setIdUsuarioEntidad(1L);
		UsuarioEntidad auxiliar = new UsuarioEntidad();
		peticion.setCodigoEntidad("0141");
		Entidad entidad = new Entidad();
		entidad.setCodigoEntidad("0141");
		auxiliar.setCodigoEntidad(entidad);
		when(usuarioEntidadServiceRepo.obtener(peticion.getIdUsuarioEntidad())).thenReturn(new UsuarioEntidad());
		UsuarioEntidadDTO res = eliminar.removerUsuarioEntidad(peticion);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		when(usuarioEntidadServiceRepo.obtener(peticion.getIdUsuarioEntidad())).thenReturn(auxiliar);
		when(usuarioEntidadMapper.usuarioEntidadDTOToEntity(peticion)).thenReturn(auxiliar);
		auxiliar.setIdUsuarioEntidad(1L);
		doNothing().when(usuarioEntidadServiceRepo).eliminar(auxiliar.getIdUsuarioEntidad());
		when(usuarioEntidadMapper.usuarioEntidadEntityToDTO(auxiliar)).thenReturn(peticion);
		res = eliminar.removerUsuarioEntidad(peticion);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
