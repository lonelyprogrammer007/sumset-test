package co.gov.sdp.spdd.core.service.administracion.ls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IConsecutivoModificar.class, ConsecutivoModificar.class})
public class ConsecutivoModificarTest {
	
	@Autowired
	IConsecutivoModificar modificar;
	
	@MockBean
	IConsecutivoServiceRepo consecutivoServiceRepo;

	@MockBean
	ConsecutivoMapper consecutivoMapper;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	public void testModificarConsecutivo() throws SpddExceptions, IOException {
		Long id = 1L;
		
		ConsecutivoDTO peticion = new ConsecutivoDTO();
		peticion.setIdConsecutivo(id);
		
		Consecutivo consecutivo = new Consecutivo();
		consecutivo.setIdConsecutivo(id);
		
		ConsecutivoDTO res;
		
		when(consecutivoServiceRepo.obtener(id)).thenReturn(consecutivo);
		when(consecutivoMapper.consecutivoDTOTOEntity(peticion)).thenReturn(consecutivo);
		when(consecutivoServiceRepo.guardar(consecutivo)).thenReturn(consecutivo);
		when(consecutivoMapper.consecutivoEntityToDTO(consecutivo)).thenReturn(peticion);
		res = modificar.modificarConsecutivo(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
        when(consecutivoServiceRepo.obtener(id)).thenReturn(null);
        res = modificar.modificarConsecutivo(peticion);
        assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
		
	}

}
