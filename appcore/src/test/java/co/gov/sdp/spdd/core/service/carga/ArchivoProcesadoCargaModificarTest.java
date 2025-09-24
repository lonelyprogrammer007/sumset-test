package co.gov.sdp.spdd.core.service.carga;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.carga.IArchivoProcesadoCargaModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ArchivoProcesadoMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IArchivoProcesadoCargaModificar.class,ArchivoProcesadoCargaModificar.class})
public class ArchivoProcesadoCargaModificarTest {

	@Autowired
	IArchivoProcesadoCargaModificar modificar;
	
	@MockBean
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	@MockBean
    ArchivoProcesadoMapper archivoProcesadoMapper;
	
	@Test
	public void testModificarArchivoProcesado() throws SpddExceptions {
		ArchivoProcesadoDTO peticion = new ArchivoProcesadoDTO();
		peticion.setIdArchivo(1L);
		
		ArchivoProcesado archivoProcesado = new ArchivoProcesado();
		archivoProcesado.setIdArchivo(null);
		
		ArchivoProcesadoDTO res;
		
		when(archivoProcesadoServiceRepo.obtener(peticion.getIdArchivo())).thenReturn(archivoProcesado);
		res = modificar.modificarArchivoProcesado(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
        
        archivoProcesado.setIdArchivo(1L);
		when(archivoProcesadoMapper.ArchivoProcesadoDTOToEntity(peticion)).thenReturn(archivoProcesado);
		when(archivoProcesadoServiceRepo.guardar(archivoProcesado)).thenReturn(archivoProcesado);
		when(archivoProcesadoMapper.ArchivoProcesadoEntityToDTO(archivoProcesado)).thenReturn(new ArchivoProcesadoDTO());
        res = modificar.modificarArchivoProcesado(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
	}

}
