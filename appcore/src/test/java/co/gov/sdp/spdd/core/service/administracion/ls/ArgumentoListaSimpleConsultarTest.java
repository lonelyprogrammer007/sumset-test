package co.gov.sdp.spdd.core.service.administracion.ls;

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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IArgumentoListaSimpleConsultar;
import co.gov.sdp.spdd.data.irepositorio.afs.IArgumentoListaSimpleRepo;
import co.gov.sdp.spdd.data.mapper.ArgumentoListaSimpleMapper;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IArgumentoListaSimpleConsultar.class, ArgumentoListaSimpleConsultar.class})
public class ArgumentoListaSimpleConsultarTest {
	
	@Autowired
	IArgumentoListaSimpleConsultar consultar;
	
	@MockBean
	IArgumentoListaSimpleRepo argumentoListaSimpleRepo;

	@MockBean
	ArgumentoListaSimpleMapper argumentoListaSimpleMapper;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;

	@Test
	public void testObtenerTodos() throws SpddExceptions {
		Long id=1L;
		
		ArgumentoListaSimple argumento = new ArgumentoListaSimple();
		argumento.setIdArgumento(id);
		
        List<ArgumentoListaSimple> listaArgumentos = new ArrayList<>();
        listaArgumentos.add(argumento);
        
        ArgumentoListaSimpleDTO argumentoDTO = new ArgumentoListaSimpleDTO();
        argumentoDTO.setIdArgumento(id);
        List<ArgumentoListaSimpleDTO> listaArgumentosDTO = new ArrayList<>();
        listaArgumentosDTO.add(argumentoDTO);
        
        when(argumentoListaSimpleRepo.obtenerArgumentoPorLista(id)).thenReturn(listaArgumentos);
        when(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(listaArgumentos)).thenReturn(listaArgumentosDTO);        
        GenericoDTO res=consultar.obtenerTodos(id);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testObtenerPorNombre() throws SpddExceptions {
		Long id=1L;
		String nombre = "nombreLista";
		
		ArgumentoListaSimple argumento = new ArgumentoListaSimple();
		argumento.setIdArgumento(id);        
        List<ArgumentoListaSimple> listaArgumentos = new ArrayList<>();
        listaArgumentos.add(argumento);
        
        ArgumentoListaSimpleDTO argumentoDTO = new ArgumentoListaSimpleDTO();
        argumentoDTO.setIdArgumento(id);
        List<ArgumentoListaSimpleDTO> listaArgumentosDTO = new ArrayList<>();
        listaArgumentosDTO.add(argumentoDTO);
        
        when(argumentoListaSimpleRepo.obtenerArgumentoPorNombre(nombre)).thenReturn(listaArgumentos);
        when(argumentoListaSimpleMapper.argumentoListaSimpleEntitiesToDTO(listaArgumentos)).thenReturn(listaArgumentosDTO);        
        GenericoDTO res=consultar.obtenerPorNombre(nombre);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testObtenerPorFiltro() throws SpddExceptions {
		Long id = 1L;
		ArgumentoListaSimpleDTO peticion = new ArgumentoListaSimpleDTO();
		peticion.setIdListaSimple(id);
        when(sessionFacadeAfs.consultarArgumentoListaSimplePorFiltro(peticion)).thenReturn(new GenericoDTO());
        GenericoDTO res = consultar.obtenerPorFiltro(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

	@Test
	public void testConsultarArgumentoPorIdPlanDesarrollo() throws SpddExceptions {
		Long id = 1L;
        when(sessionFacadeAfs.consultarArgumentoPorIdPdd(id)).thenReturn(new GenericoDTO());
        GenericoDTO res = consultar.consultarArgumentoPorIdPlanDesarrollo(id);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

}
