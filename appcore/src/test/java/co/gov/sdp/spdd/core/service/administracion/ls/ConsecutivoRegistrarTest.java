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

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IConsecutivoRegistrar.class, ConsecutivoRegistrar.class})
public class ConsecutivoRegistrarTest {
	
	@Autowired
	IConsecutivoRegistrar registrar;
	
	@MockBean
	IConsecutivoServiceRepo consecutivoServiceRepo;
	
	@MockBean
	ConsecutivoMapper consecutivoMapper;

	@Test
	public void testRegistrarConsecutivo() throws SpddExceptions {
		Long id = 1L;
		
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		peticion.setIdConsecutivo(id);
		
		Consecutivo consecutivo = new Consecutivo();
		consecutivo.setIdConsecutivo(id);
		
		ConsecutivoDTO res;		
		when(consecutivoMapper.consecutivoDTOTOEntity(peticion)).thenReturn(consecutivo);
		when(consecutivoServiceRepo.guardar(consecutivo)).thenReturn(consecutivo);
		when(consecutivoMapper.consecutivoEntityToDTO(consecutivo)).thenReturn(peticion);
		res = registrar.registrarConsecutivo(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
