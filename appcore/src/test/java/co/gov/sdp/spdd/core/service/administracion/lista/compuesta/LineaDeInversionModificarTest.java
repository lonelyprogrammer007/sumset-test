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

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, LineaDeInversionModificar.class, ILineaDeInversionModificar.class})
class LineaDeInversionModificarTest {
	
	@Autowired
	ILineaDeInversionModificar modificar;
	/**
	 * Objeto que permite realizar las operaciones basicas de bd
	 */
	@MockBean
	ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;
	
	/**
	 * Objeto que permite realizar los mapeos de dto a entidad y viceversa
	 */
	@MockBean
	LineaDeInversionMapper lineaDeInversionMapper;
	
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
	void testModificarLineaInversion() throws SpddExceptions {
		LineaDeInversionDTO linea = new LineaDeInversionDTO();
		linea.setIdLineaInversion(1L);
		linea.setConcepto("concepto1");
		linea.setIdLsSector(1L);
		LineaDeInversionDTO auxiliar = new LineaDeInversionDTO();
		auxiliar.setIdLineaInversion(1L);
		auxiliar.setConcepto("concepto1");
		auxiliar.setIdLsSector(1L);
		LineaDeInversionDTO res;
		
		//inicio del if
		when(sessionFacadeAFS.consultarLineaDeInversionPorId(linea.getIdLineaInversion())).thenReturn(linea);
		when(sessionFacadeAFS.buscarLineaInversionPorConceptoYSector(linea.getConcepto(),linea.getIdLsSector())).thenReturn(linea);
		when(sessionFacadeAFS.guardarLineaDeInversion(auxiliar)).thenReturn(new LineaDeInversionDTO());
		when(sessionFacadeAFS.modificarLineaDeInversion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarLineaInversion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//inicio del else
		
		//inicio del if
		
		linea.setIdLineaInversion(2L);
		when(sessionFacadeAFS.consultarLineaDeInversionPorId(auxiliar.getIdLineaInversion())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarLineaInversionPorConceptoYSector(linea.getConcepto(),linea.getIdLsSector())).thenReturn(linea);
		when(sessionFacadeAFS.guardarLineaDeInversion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarLineaInversion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//inicio del else
		linea.setIdLineaInversion(2L);
		when(sessionFacadeAFS.consultarLineaDeInversionPorId(auxiliar.getIdLineaInversion())).thenReturn(auxiliar);
		when(sessionFacadeAFS.buscarLineaInversionPorConceptoYSector(linea.getConcepto(),linea.getIdLsSector())).thenReturn(linea);
		when(sessionFacadeAFS.guardarLineaDeInversion(auxiliar)).thenReturn(auxiliar);
		auxiliar.setIdLineaInversion(null);
		res = modificar.modificarLineaInversion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeAFS.consultarLineaDeInversionPorId(linea.getIdLineaInversion())).thenReturn(null);
		when(sessionFacadeAFS.buscarLineaInversionPorConceptoYSector(linea.getConcepto(),linea.getIdLsSector())).thenReturn(linea);
		when(sessionFacadeAFS.guardarLineaDeInversion(auxiliar)).thenReturn(new LineaDeInversionDTO());
		when(sessionFacadeAFS.modificarLineaDeInversion(auxiliar)).thenReturn(auxiliar);
		res = modificar.modificarLineaInversion(auxiliar);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	void testModificarEstadoLineaInversion() throws SpddExceptions {
		Long id = 1L;
		LineaDeInversionDTO peticion = new LineaDeInversionDTO();
		peticion.setIdLineaInversion(1L);
		
		LineaDeInversion linea = new LineaDeInversion();
		linea.setIdLineaInversion(1L);
		linea.setEstado((short)1);
		
		LineaDeInversionDTO lineaDTO = new LineaDeInversionDTO();
		lineaDTO.setIdLineaInversion(1L);
		
		LineaDeInversionDTO res;
		
		when(lineaDeInversionServiceRepo.obtener(id)).thenReturn(linea);
		when(lineaDeInversionServiceRepo.guardar(linea)).thenReturn(linea);
		when(lineaDeInversionMapper.lineaDeInversionEntityToDTO(linea)).thenReturn(lineaDTO);
		
		res=modificar.modificarEstadoLineaInversion(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		linea.setEstado((short)0);
		res=modificar.modificarEstadoLineaInversion(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(lineaDeInversionServiceRepo.obtener(id)).thenReturn(null);
		res=modificar.modificarEstadoLineaInversion(id);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
		
	}

}
