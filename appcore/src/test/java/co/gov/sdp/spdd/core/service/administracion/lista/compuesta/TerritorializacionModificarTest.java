package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.TerritorializacionMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, TerritorializacionModificar.class,ITerritorializacionModificar.class})
class TerritorializacionModificarTest {
	
	@Autowired
	ITerritorializacionModificar modificar;
	
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

	@Test
	void testModificarTerritorializacion() throws SpddExceptions {
		TerritorializacionDTO entidad = new TerritorializacionDTO();
		entidad.setIdTerritorializacion(1L);
		entidad.setIdLsUpr(1L);
		TerritorializacionDTO auxiliar= new TerritorializacionDTO();
		auxiliar.setIdTerritorializacion(1L);
		auxiliar.setIdLsUpr(1L);
		
		TerritorializacionDTO res;
		when(sessionFacadeAFS.consultarTerritorializacionPorId(entidad.getIdTerritorializacion())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarPorLsVeredaYLsUpr(entidad)).thenReturn(auxiliar);
		when(sessionFacadeAFS.guardarTerritorializacion(auxiliar)).thenReturn(auxiliar);
		when(sessionFacadeAFS.modificarTerritorializacion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarTerritorializacion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		auxiliar.setIdLsUpr(null);
		auxiliar.setIdLsUpz(1L);
		auxiliar.setIdLsBarrio(1L);
		auxiliar.setIdLsLocalidad(1L);
		
		when(sessionFacadeAFS.consultarTerritorializacionPorId(entidad.getIdTerritorializacion())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(auxiliar)).thenReturn(auxiliar);
		when(sessionFacadeAFS.guardarTerritorializacion(auxiliar)).thenReturn(auxiliar);
		when(sessionFacadeAFS.modificarTerritorializacion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarTerritorializacion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		auxiliar.setIdLsUpr(null);
		auxiliar.setIdLsUpz(1L);
		auxiliar.setIdLsBarrio(1L);
		auxiliar.setIdLsLocalidad(1L);
		entidad.setIdTerritorializacion(2L);
		when(sessionFacadeAFS.consultarTerritorializacionPorId(auxiliar.getIdTerritorializacion())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarTerritorializacion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarTerritorializacion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		entidad.setIdLsUpr(null);
		entidad.setIdLsUpz(1L);
		entidad.setIdLsBarrio(1L);
		entidad.setIdLsLocalidad(1L);
		entidad.setIdTerritorializacion(2L);
		entidad.setIdTerritorializacion(2L);
		when(sessionFacadeAFS.consultarTerritorializacionPorId(auxiliar.getIdTerritorializacion())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarTerritorializacion(auxiliar)).thenReturn(new TerritorializacionDTO());
		
		res = modificar.modificarTerritorializacion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		auxiliar.setIdLsUpr(null);
		auxiliar.setIdLsUpz(1L);
		auxiliar.setIdLsBarrio(1L);
		auxiliar.setIdLsLocalidad(1L);
		when(sessionFacadeAFS.consultarTerritorializacionPorId(auxiliar.getIdTerritorializacion())).thenReturn(null);
		when(sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(auxiliar)).thenReturn(entidad);
		when(sessionFacadeAFS.guardarTerritorializacion(auxiliar)).thenReturn(new TerritorializacionDTO());
		res = modificar.modificarTerritorializacion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

}
