package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, LineaDeInversionConsultar.class, ILineaDeInversionConsultar.class})
class LineaDeInversionConsultarTest {
	
	@Autowired
	ILineaDeInversionConsultar consultar;
	
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;
	
	 /**
     * Objeto que permite realizar las operaciones basicas de bd
     */
    @MockBean
    ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;
    /**
     * Objeto que permite realizar el mapeo de dto a entidad y viceversa
     */
    @MockBean
    LineaDeInversionMapper lineaDeInversionMapper;
    
    
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
		List<LineaDeInversion> entidad = new ArrayList<>();
		when(lineaDeInversionServiceRepo.obtenerTodos()).thenReturn(entidad);
		when(lineaDeInversionMapper.lineaDeInversionEntitiesTODTO(entidad)).thenReturn(new ArrayList<LineaDeInversionDTO>());
		GenericoDTO res = consultar.obtenerTodos();
		assertNotNull(res);
	}

	@Test
	void testObtenerPaginado() throws SpddExceptions, JsonProcessingException {
		Long id = 1L;
		LineaDeInversionDTO peticion = new LineaDeInversionDTO();
		peticion.setIdLineaInversion(id);
		when(sessionFacadeAfs.consultarLineaDeInversionPorFiltro(peticion)).thenReturn(new GenericoDTO());
		GenericoDTO respuesta = consultar.obtenerPaginado(peticion);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(200L);
	}

}
