package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGastoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGastoMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IComponenteGastoAdministracionModificar.class, ComponenteGastoAdministracionModificar.class})
public class ComponenteGastoAdministracionModificarTest {

	@Autowired
	IComponenteGastoAdministracionModificar modificar; 
	
	@MockBean
	IComponenteGastoServiceRepo componenteGastoServiceRepo;
	

	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	@MockBean
	ComponenteGastoMapper componenteGastoMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;


	@Test
	public void testModificarComponenteGasto() throws SpddExceptions {
		
		ComponenteGastoDTO entidad = new ComponenteGastoDTO(), auxiliar = new ComponenteGastoDTO(), componenteGastoDTO = new ComponenteGastoDTO(), respuesta = new ComponenteGastoDTO();
		entidad.setIdComponenteGasto(1L);
		auxiliar.setIdComponenteGasto(1L);
		componenteGastoDTO.setIdComponenteGasto(1L);
		respuesta.setIdComponenteGasto(1L);
		
		ComponenteGastoDTO res;
		
		// if (componenteGastoDTO.getIdComponenteGasto() == auxiliar.getIdComponenteGasto())
		when(sessionFacadeAFS.consultarComponenteGastoPorId(componenteGastoDTO.getIdComponenteGasto())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarCodigoYNombre(componenteGastoDTO.getCodigoComponente(),	componenteGastoDTO.getNombreComponente())).thenReturn(auxiliar);
		when(sessionFacadeAFS.modificarComponenteGasto(componenteGastoDTO)).thenReturn(auxiliar);
		
		res = modificar.modificarComponenteGasto(componenteGastoDTO);
		
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);

	        // if (respuesta.getIdComponenteGasto() != null)
			auxiliar.setIdComponenteGasto(2L);
			when(sessionFacadeAFS.guardarComponenteGasto(componenteGastoDTO)).thenReturn(respuesta);
			
			res = modificar.modificarComponenteGasto(componenteGastoDTO);
			assertNotNull(res);
	        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	        
	        // else
	        respuesta.setIdComponenteGasto(null);
	        res = modificar.modificarComponenteGasto(componenteGastoDTO);
			assertNotNull(res);
	        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	        assertEquals("La asociación creada ya se encuentra registrada", res.getMsgRespuesta());
        
	    // else
        when(sessionFacadeAFS.consultarComponenteGastoPorId(componenteGastoDTO.getIdComponenteGasto())).thenReturn(null);
        res = modificar.modificarComponenteGasto(componenteGastoDTO);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
        assertEquals("No se encontro la entidad seleccionada", res.getMsgRespuesta());

		
	}

}
