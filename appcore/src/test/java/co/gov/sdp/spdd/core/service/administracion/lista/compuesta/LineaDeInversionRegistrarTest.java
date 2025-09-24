package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;

import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, LineaDeInversionRegistrar.class, ILineaDeInversionRegistrar.class})

class LineaDeInversionRegistrarTest {
	
	@Autowired
	ILineaDeInversionRegistrar registrar;
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
	
	/**
	 * 
	 */
	@MockBean
	AuditoriaUtil auditoria;
	
	@Test
	void testRegistrarLineaDeInversion() throws SpddExceptions, JsonProcessingException {
		LineaDeInversionDTO respuesta;
		LineaDeInversionDTO linea = new LineaDeInversionDTO();
		linea.setIdLineaInversion(1L);
		linea.setSectorMultiple("1;2;3");
		LineaDeInversionDTO peticion = new LineaDeInversionDTO();
		peticion.setIdLineaInversion(1L);
		
		when(sessionFacadeAFS.guardarLineaDeInversion(linea)).thenReturn(linea);
		respuesta = registrar.registrarLineaDeInversion(linea);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeAFS.guardarLineaDeInversion(linea)).thenReturn(linea);
		linea.setIdLineaInversion(null);
		respuesta = registrar.registrarLineaDeInversion(linea);
		assertNotNull(respuesta);
		assertThat(respuesta.getCodigoRespuesta()).isEqualTo(400L);
	}

}
