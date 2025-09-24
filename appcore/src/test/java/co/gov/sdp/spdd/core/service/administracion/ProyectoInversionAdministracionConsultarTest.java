package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectoInversionRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectoInversion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ISessionFacadeAFS.class, ProyectoInversionAdministracionConsultar.class, IProyectoInversionAdmnistracionConsultar.class})
class ProyectoInversionAdministracionConsultarTest {
	
		@Autowired
		IProyectoInversionAdmnistracionConsultar consultar;
	// Servicio repositorio de proyecto de inversion
		@MockBean
		IProyectoInversionServiceRepo proyectoInversionServiceRepo;

		// Servicio repositorio de proyecto de inversion
		@MockBean
		IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioServiceRepo;

		// Repositorio de proyecto de inversion
		@MockBean
		IProyectoInversionRepo proyectoInversionRepo;

		// Repositorio de proyecto de inversion
		@MockBean
		IProyectosInversionUsuarioRepo proyectosInversionUsuarioRepo;

		// Mapper de ProyectosInversionUsuario
		@MockBean
		ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;

		// Mapper de ProyectoInversion
		@MockBean
		ProyectoInversionMapper proyectoInversionMapper;

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
	void testObtenerProyectoInversionAsignados() throws SpddExceptions {
		UsuariosDTO peticion = new UsuariosDTO();
		peticion.setNombreUsuario("sumset1");
		GenericoDTO res;
		when(sessionFacadeAfs
				.consultarProyectosInversionUsuarioPorId(peticion.getNombreUsuario())).thenReturn(new ArrayList<ProyectosInversionUsuarioDTO>());
		res = consultar.obtenerProyectoInversionAsignados(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	void testObtenerProyectoInversionDisponibles() throws SpddExceptions {
		GenericoDTO res;
		List<ProyectoInversion> lista = new ArrayList<ProyectoInversion>();
		when(sessionFacadeAfs.proyectoInversionObtenerDisponibles()).thenReturn(lista);
		when(proyectoInversionMapper.proyectoInversionEntitiesToDTO(lista)).thenReturn(new ArrayList<ProyectoInversionDTO>());
		res = consultar.obtenerProyectoInversionDisponibles();
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	void testObtenerProyectoInversionTodos() throws SpddExceptions {
		GenericoDTO res;
		when(sessionFacadeAfs.consultarProyectoInversionTodos()).thenReturn(new GenericoDTO());
		res = consultar.obtenerProyectoInversionTodos();
		assertNotNull(res);
	}

}
