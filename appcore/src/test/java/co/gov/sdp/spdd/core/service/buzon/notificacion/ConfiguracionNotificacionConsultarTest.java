package co.gov.sdp.spdd.core.service.buzon.notificacion;

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

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IConfiguracionNotificacionRepo;
import co.gov.sdp.spdd.data.mapper.ConfiguracionNotificacionMapper;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IConfiguracionNotificacionConsultar.class,ConfiguracionNotificacionConsultar.class})
public class ConfiguracionNotificacionConsultarTest {
	
	@Autowired
	IConfiguracionNotificacionConsultar consultar;
	
	@MockBean
    IConfiguracionNotificacionRepo configuracionNotificacionRepo;
    
	@MockBean
    ConfiguracionNotificacionMapper mapper;
	
	@MockBean
    IConfiguracionNotificacionServiceRepo serviceRepo;

	@Test
	public void testObtenerAutomaticos() {
		 List<ConfiguracionNotificacion> entidad = new ArrayList<>();
		 List<ConfiguracionNotificacionDTO> listaRespuesta = new ArrayList<>();

		 GenericoDTO res;
		 
		 when(serviceRepo.obtenerTodos()).thenReturn(entidad);
		 when(mapper.configuracionNotificacionEntitiesToDTO(entidad)).thenReturn(listaRespuesta);		
		 res = consultar.obtenerAutomaticos();
		 assertNotNull(res);
	     assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	}

}
