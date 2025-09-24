package co.gov.sdp.spdd.core.service.administracion;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, FuncionarioClaveModificar.class,
		IFuncionarioClaveEntidadModificar.class})
public class FuncionarioClaveModificarTest {

	
	@Autowired
	IFuncionarioClaveEntidadModificar modificar;
	
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
	
	
	@Test
	public void testModificarFuncionarioClaveEntidad() throws SpddExceptions {
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		FuncionarioClaveEntidadDTO respuesta = new FuncionarioClaveEntidadDTO();
		FuncionarioClaveEntidadDTO res;
		
		when(sessionFacadeAFS.guardarFuncionarioClaveEntidad(peticion)).thenReturn(respuesta);
		res = modificar.modificarFuncionarioClaveEntidad(peticion);
		assertNotNull(res);
		
	}

}
