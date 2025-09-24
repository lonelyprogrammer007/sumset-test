package co.gov.sdp.spdd.core.service.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParanetroGeneralConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.mapper.ParametroGeneralMapper;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IParanetroGeneralConsultar.class, ParametroGeneralConsultar.class})
public class ParametroGeneralConsultarTest {

	@Autowired
	IParanetroGeneralConsultar consultar;
	
	@MockBean
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	@MockBean
	ParametroGeneralMapper mapper;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;
	
	
	@Test
	public void testObtenerTodos() throws SpddExceptions {
		String id = "100";
		
		ParametroGeneral parametro = new ParametroGeneral();
		parametro.setCodigoParametro(id);
		List<ParametroGeneral> listaParametros = new ArrayList<>();
		listaParametros.add(parametro);
		
		ParametroGeneralDTO parametroDTO = new ParametroGeneralDTO();
		parametroDTO.setCodigoParametro(id);
		List<ParametroGeneralDTO> listaParametrosDTO = new ArrayList<>();
		listaParametrosDTO.add(parametroDTO);
		
		GenericoDTO res;
		
		when(parametroGeneralServiceRepo.obtenerTodos()).thenReturn(listaParametros);
		when(mapper.parametroGeneralEntitiesToDTO(listaParametros)).thenReturn(listaParametrosDTO);
		res = consultar.obtenerTodos();
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        assertThat(res.getLstObjectsDTO().isEmpty()).isEqualTo(false);
	}

	@Test
	public void testObtenerPaginado() throws SpddExceptions {
		String id = "100";
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		peticion.setCodigoParametro(id);
		
		GenericoDTO res;
		
		when(sessionFacadeAFS.consultarParametroGeneralPorFiltro(peticion)).thenReturn(new GenericoDTO());
		res = consultar.obtenerPaginado(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
