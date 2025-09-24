package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.TerritorializacionMapper;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, TerritorializacionConsultar.class,  ITerritorializacionConsultar.class})
class TerritorializacionConsultarTest {
	
	@Autowired
	ITerritorializacionConsultar consultar;
	/**
	 * 
	 */
	@MockBean
	TerritorializacionMapper mapper;

	/**
	 * 
	 */
	@MockBean
	ITerritorializacionServiceRepo territorializacionServiceRepo;
	
	/**
	 * 
	 */
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Logger
	 */
	@MockBean
	SPDDLogger spddLogger;
	
	/**
	 * 
	 */
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	void testObtenerTodos() throws SpddExceptions {
		GenericoDTO res;
		List<Territorializacion> entidad = new ArrayList<>();
		
		when(territorializacionServiceRepo.obtenerTodos()).thenReturn(entidad);
		when(mapper.territorializacionEntitiesToDTO(entidad)).thenReturn(new ArrayList<TerritorializacionDTO>());
		res = consultar.obtenerTodos();
		assertNotNull(res);
		
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions, JsonProcessingException {
		GenericoDTO res;
		TerritorializacionDTO peticion = new TerritorializacionDTO();
		peticion.setIdTerritorializacion(1L);
		when(sessionFacadeAFS.consultarTerritorializacionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
	}

}
