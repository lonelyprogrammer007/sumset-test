package co.gov.sdp.spdd.core.service.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IArgumentoListaSimpleRegistrar.class, ArgumentoListaSimpleRegistrar.class})
public class ArgumentoListaSimpleRegistrarTest {
	
	@Autowired
	IArgumentoListaSimpleRegistrar registrar;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testRegistrarArgumentoListaSimple() throws SpddExceptions, JsonProcessingException {
		Long id=1L;
		
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setIdArgumento(id);
		
		ArgumentoListaSimpleDTO argumentoDTO = new ArgumentoListaSimpleDTO();
        argumentoDTO.setIdArgumento(id);
		
        ArgumentoListaSimpleDTO res;
		when(sessionFacadeAFS.buscarIdListaSimpleArgumento(peticion)).thenReturn(argumentoDTO);
		res = registrar.registrarArgumentoListaSimple(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
        
        argumentoDTO.setIdArgumento(null);
        when(sessionFacadeAFS.buscarIdListaSimpleArgumento(peticion)).thenReturn(argumentoDTO);
        when(sessionFacadeAFS.guardarArgumentoListaSimple(peticion)).thenReturn(argumentoDTO);
        res = registrar.registrarArgumentoListaSimple(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

}
