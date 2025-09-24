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

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IEstructuraMetaAdministracionModificar.class,
		EstructuraMetaAdministracionModificar.class })
public class EstructuraMetaAdministracionModificarTest {

	@Autowired
	IEstructuraMetaAdministracionModificar modificar;

	@MockBean
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	@MockBean
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	@MockBean
	EstructuraMetaMapper estructuraMetaMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testModificarEstructuraMeta() throws SpddExceptions {

		EstructuraMetaDTO entidad = new EstructuraMetaDTO(), auxiliar = new EstructuraMetaDTO(),
				 estructura = new EstructuraMetaDTO(), respuesta = new EstructuraMetaDTO();
		
		auxiliar.setIdEstructuraMetas(1L);
		entidad.setIdEstructuraMetas(1L);
		estructura.setIdEstructuraMetas(1L);
		estructura.setIdLsUnidadMedida(1L);
		estructura.setIdLsVerbo(1L);
		respuesta.setIdEstructuraMetas(1L);
		
		EstructuraMetaDTO res;
		
		//if (entidad != null && entidad.getIdEstructuraMetas() > 0) {
		
		when(sessionFacadeAFS.consultarEstructuraMeta(estructura.getIdEstructuraMetas())).thenReturn(entidad);
		when(sessionFacadeAFS.buscarUnidadMedidaYVerbo(estructura.getIdLsUnidadMedida(), estructura.getIdLsVerbo())).thenReturn(auxiliar);
		when(sessionFacadeAFS.modificarEstructuraMeta(estructura)).thenReturn(auxiliar);
		
		res = modificar.modificarEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		//if (respuesta.getIdEstructuraMetas() != null) {
		auxiliar.setIdEstructuraMetas(2L);
		when(sessionFacadeAFS.guardarEstructuraMeta(estructura)).thenReturn(respuesta);
		res = modificar.modificarEstructuraMeta(estructura);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		// else
		 respuesta.setIdEstructuraMetas(null);
		 res = modificar.modificarEstructuraMeta(estructura);
		 assertNotNull(res);
	     assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	     assertEquals("La asociación creada ya se encuentra registrada", res.getMsgRespuesta());
	     
	    // else
	     
	        when(sessionFacadeAFS.consultarEstructuraMeta(estructura.getIdEstructuraMetas())).thenReturn(null);
	        res = modificar.modificarEstructuraMeta(estructura);
			assertNotNull(res);
	        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	        assertEquals("No se encontro la entidad seleccionada", res.getMsgRespuesta());  
	}

}
