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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IArgumentoListaSimpleModificar.class, ArgumentoListaSimpleModificar.class})
public class ArgumentoListaSimpleModificarTest {
	
	@Autowired
	IArgumentoListaSimpleModificar modificar;
	
	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;
	
	@MockBean
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testModificarArgumento() throws SpddExceptions {
		/*
		Long id=1L;
		
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setIdArgumento(id);
		
		ArgumentoListaSimple argumento = new ArgumentoListaSimple();
		argumento.setIdArgumento(id);
		
		ArgumentoListaSimpleDTO argumentoDTO = new ArgumentoListaSimpleDTO();
	    argumentoDTO.setIdArgumento(id);
	    
	    ArgumentoListaSimpleDTO res;
		
        when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(id)).thenReturn(argumentoDTO);
        when(sessionFacadeAFS.buscarIdListaSimpleArgumento(peticion)).thenReturn(argumentoDTO);
        when(sessionFacadeAFS.guardarArgumentoListaSimple(peticion)).thenReturn(new ArgumentoListaSimpleDTO());
        res = modificar.modificarArgumento(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        when(sessionFacadeAFS.buscarIdListaSimpleArgumento(peticion)).thenReturn(new ArgumentoListaSimpleDTO());
        res = modificar.modificarArgumento(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        assertThat(res.getMsgRespuesta()).isEqualTo("La asociación creada ya se encuentra registrada");
        
        when(sessionFacadeAFS.consultarArgumentoListaSimplePorId(id)).thenReturn(null);
        res = modificar.modificarArgumento(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
        assertThat(res.getMsgRespuesta()).isEqualTo("No se encontro la entidad seleccionada");
        */
	}

	@Test
	public void testModificarEstado() throws SpddExceptions {
		Long id=1L;
		
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setIdArgumento(id);		
		
		ArgumentoListaSimple argumento = new ArgumentoListaSimple();
		argumento.setIdArgumento(id);
		argumento.setEstado((short)1);
		
		ArgumentoListaSimpleDTO argumentoDTO = new ArgumentoListaSimpleDTO();
	    argumentoDTO.setIdArgumento(id);
	    
	    ArgumentoListaSimpleDTO res;
	    
        when(argumentoListaSimpleServiceRepo.obtener(id)).thenReturn(argumento);
        when(argumentoListaSimpleServiceRepo.guardar(argumento)).thenReturn(argumento);
        when(argumentoListaSimpleMapper.argumentoListaSimpleEntityToDTO(argumento)).thenReturn(argumentoDTO);
        res = modificar.modificarEstado(id);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        argumento.setEstado((short)0);
        res = modificar.modificarEstado(id);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        when(argumentoListaSimpleServiceRepo.obtener(id)).thenReturn(null);
        res = modificar.modificarEstado(id);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
	}

}
