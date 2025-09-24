package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionConsultar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IComponenteGastoAdministracionConsultar.class, ComponenteGastoAdministracionConsultar.class})
public class ComponenteGastoAdministracionConsultarTest {

	@Autowired
	IComponenteGastoAdministracionConsultar consultar; 
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAfs;
	
	@MockBean
	SPDDLogger spddLogger;
	
	@Test
	public void testObtenerComponenteGastoTodos() throws SpddExceptions {
	    ComponenteGastoDTO generico = new ComponenteGastoDTO();
	    GenericoDTO generico2 = new GenericoDTO();
		
	    GenericoDTO res;	
	    
	    when(sessionFacadeAfs.consultarComponenteGastoPorFiltro(generico)).thenReturn(generico2);
	    
	    res = consultar.obtenerComponenteGastoTodos(generico);
	    
	    assertNotNull(res);
	    assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	   
	}

}
