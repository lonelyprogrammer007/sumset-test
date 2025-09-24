package co.gov.sdp.spdd.core.service.administracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IFuncionarioClaveEntidadRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class, IFuncionarioClaveEntidadConsultar.class,
		FuncionarioClaveEntidadConsultar.class})
public class FuncionarioClaveEntidadConsultarTest {

	@Autowired
	IFuncionarioClaveEntidadConsultar consultar;

	@MockBean
	IFuncionarioClaveEntidadRepo funcionarioClaveEntidadRepo;

	
	@MockBean
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	
	@MockBean
	IEntidadServiceRepo entidadServiceRepo;

	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	
	@MockBean
	SPDDLogger spddLogger;

	
	@Test
	public void testObtenerFuncionarioClaveEntidad() throws SpddExceptions {
		EntidadDTO entidad = new EntidadDTO();
		EntidadDTO auxiliar = new EntidadDTO();
		entidad.setCodigoEntidad("sumset");
		GenericoDTO respuesta = new GenericoDTO();
		when(sessionFacadeAFS.consultarEntidad(entidad.getCodigoEntidad())).thenReturn(auxiliar);
		
		//if (entidadDTO != null) {
		when(sessionFacadeAFS.obtenerFuncionarioPorEntidad(entidad.getCodigoEntidad())).thenReturn(respuesta);
		GenericoDTO res = consultar.obtenerFuncionarioClaveEntidad(entidad.getCodigoEntidad());
	    assertNotNull(res);
	    System.out.println(res.getCodigoRespuesta());
	    assertThat(res.getCodigoRespuesta()).isEqualTo(200);
	    
	    //else {
	    entidad.setCodigoEntidad(null);
	    GenericoDTO res2 = consultar.obtenerFuncionarioClaveEntidad(entidad.getCodigoEntidad());
	    assertNotNull(res2);
	    System.out.println(res2.getCodigoRespuesta());
	    assertThat(res2.getCodigoRespuesta()).isEqualTo(400);
	    
	    
	}
	@Test
	public void testObtenerPaginado() throws SpddExceptions {
		
		Long id = 1L;
		FuncionarioClaveEntidadDTO peticion = new FuncionarioClaveEntidadDTO();
		peticion.setIdFuncionarioEntidad(id);
        when(sessionFacadeAFS.consultarFuncionarioClaveEntidadPorFiltro(peticion)).thenReturn(new GenericoDTO());
        GenericoDTO res = consultar.obtenerPaginado(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

}
