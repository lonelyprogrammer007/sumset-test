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

import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParametroGeneralModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.mapper.ParametroGeneralMapper;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IParametroGeneralModificar.class, ParametroGeneralModificar.class})
public class ParametroGeneralModificarTest {
	
	@Autowired
	IParametroGeneralModificar modificar;
	
	@MockBean
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	@MockBean
	ParametroGeneralMapper mapper;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testEditarParametro() throws SpddExceptions, JsonProcessingException {
		String id = "100";
		
		ParametroGeneral parametro = new ParametroGeneral();
		parametro.setCodigoParametro(id);
		
		ParametroGeneralDTO peticion = new ParametroGeneralDTO();
		peticion.setCodigoParametro(id);
		
		ParametroGeneralDTO parametroDTO = new ParametroGeneralDTO();
		parametroDTO.setCodigoParametro(id);
		
		ParametroGeneralDTO res;
		
		when(parametroGeneralServiceRepo.obtener(id)).thenReturn(parametro);
		when(mapper.parametroDTOToEntity(peticion)).thenReturn(parametro);
		when(parametroGeneralServiceRepo.guardar(parametro)).thenReturn(parametro);
		when(mapper.parametroEntityToDTO(parametro)).thenReturn(parametroDTO);
		res = modificar.editarParametro(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        when(parametroGeneralServiceRepo.obtener(id)).thenReturn(null);
        res = modificar.editarParametro(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
	}

}
