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

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, FuncionarioClaveEntidadRegistrar.class,
		IFuncionarioClaveEntidadRegistrar.class})
public class FuncionarioClaveEntidadRegistrarTest {

	@Autowired
	IFuncionarioClaveEntidadRegistrar registrar;
	
	@MockBean
	IFuncionarioClaveEntidadServiceRepo funcionarioClaveEntidadServiceRepo;

	
	@MockBean
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	
	@MockBean
	IEntidadServiceRepo entidadServiceRepo;

	
	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testCrearFuncionarioClaveEntidad() throws SpddExceptions, JsonProcessingException {
		
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		EntidadDTO validacion = new EntidadDTO();
		ArgumentoListaSimpleDTO validacionDos = new ArgumentoListaSimpleDTO();
		ArgumentoListaSimpleDTO validacionTres = new ArgumentoListaSimpleDTO();
		FuncionarioClaveEntidadDTO res;
		
		//if (validacion != null && validacionDos != null && validacionTres != null) {
		
		when(sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad())).thenReturn(validacion);
		when(sessionFacadeAFS
				.consultarArgumentoListaSimplePorId(peticion.getIdLsFuncion())).thenReturn(validacionDos).thenReturn(validacionTres);
		
		// if (validacion != null && validacionDos != null && validacionTres != null) {
		when(sessionFacadeAFS.guardarFuncionarioClaveEntidad(peticion)).thenReturn(respuesta);
		res = registrar.crearFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		
		when(sessionFacadeAFS.consultarEntidad(peticion.getCodigoEntidad())).thenReturn(null);
		res = registrar.crearFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}

}
