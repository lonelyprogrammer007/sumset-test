package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IProyectoInversionAdministracionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IProyectosInversionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IProyectosInversionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.ProyectosInversionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ProyectosInversionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ISessionFacadeAFS.class, ProyectoInversionAdministracionRegistrar.class,
		IProyectoInversionAdministracionRegistrar.class })
class ProyectoInversionAdministracionRegistrarTest {
	
	@Autowired
	ProyectoInversionAdministracionRegistrar registrar;
	
	@MockBean
	IProyectosInversionUsuarioServiceRepo proyectosInversionUsuarioRepo;

	@MockBean
	IProyectosInversionUsuarioRepo repo;

	// Repositorio de proyecto de inversion
	@MockBean
	IProyectoInversionServiceRepo proyectosInversionRepo;

	// Mapper de proyecto inversion
	@MockBean
	ProyectoInversionMapper proyectoInversionMapper;

	// Mapper de proyecto inversion usuario
	@MockBean
	ProyectosInversionUsuarioMapper proyectosInversionUsuarioMapper;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	void testCrearProyectoInversion() throws SpddExceptions, JsonProcessingException {
		ProyectoInversionDTO peticion = new ProyectoInversionDTO();
		peticion.setIdProyectoInversion(1L);
		ProyectoInversionDTO res;
		when(sessionFacadeAFS.guardarProyectoInversion(peticion)).thenReturn(peticion);
		res = registrar.crearProyectoInversion(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	void testAsignarProyectosInversionUsuario() throws SpddExceptions {
		ProyectosInversionUsuarioDTO peticion = new ProyectosInversionUsuarioDTO();
		peticion.setCodigoUsuario("sumset1");
		peticion.setStringConcatenado("3;5;6");
		ProyectosInversionUsuario entidad = new ProyectosInversionUsuario();
		List<ProyectosInversionUsuario> lista = new ArrayList<ProyectosInversionUsuario>();
		ProyectosInversionUsuarioDTO res;
		when(proyectosInversionUsuarioRepo
				.obtenerPorUsuario(peticion.getCodigoUsuario())).thenReturn(lista);
		when(proyectosInversionUsuarioMapper
						.proyectosInversionUsuarioDTOToEntity(peticion)).thenReturn(entidad);
		when(proyectosInversionUsuarioMapper.proyectosInversionUsuarioEntityToDTO(entidad)).thenReturn(new ProyectosInversionUsuarioDTO());
		res = registrar.asignarProyectosInversionUsuario(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		
	}

}
