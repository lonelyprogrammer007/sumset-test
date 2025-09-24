package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdmnistracionEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ISessionFacadeAFS.class, ProyectoInversionAdministracionEliminar.class,
		IProyectoInversionAdmnistracionEliminar.class })
class ProyectoInversionAdministracionEliminarTest {
	
	@Autowired
	IProyectoInversionAdmnistracionEliminar eliminar;
	
	// Repositorio de proyectos de inversion usuario
	@MockBean
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioRepo;

	// Mapper de proyecto inversion usuario
	@MockBean
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;

	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@MockBean
	SPDDLogger spddLogger;

	@Test
	void testRemoverProyectoInversionUsuario() throws SpddExceptions {
		ProyectosInversionUsuarioDTO peticion = new ProyectosInversionUsuarioDTO();
		peticion.setIdProyectoInversion(1L);
		peticion.setIdProyectoUsuario(1L);
		ProyectosInversionUsuario entidad = new ProyectosInversionUsuario();
		entidad.setIdProyectoUsuario(1L);
		ProyectosInversionUsuarioDTO res;
		when(proyectosInversionUsuarioRepo.obtener(peticion.getIdProyectoUsuario())).thenReturn(entidad);
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(entidad)).thenReturn(peticion);
		res = eliminar.removerProyectoInversionUsuario(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(proyectosInversionUsuarioRepo.obtener(peticion.getIdProyectoUsuario())).thenReturn(null);
		
		res = eliminar.removerProyectoInversionUsuario(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
