package co.gov.sdp.spdd.core.service.buzon.notificacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IConfiguracionNotificacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConfiguracionNotificacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConfiguracionNotificacionMapper;
import co.gov.sdp.spdd.data.model.afs.ConfiguracionNotificacion;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IConfiguracionNotificacionModificar.class,ConfiguracionNotificacionModificar.class})
public class ConfiguracionNotificacionModificarTest {
	
	@Autowired
	IConfiguracionNotificacionModificar modificar;
	
	@MockBean
    IConfiguracionNotificacionServiceRepo configuracionNotificacionServiceRepo;
    
	@MockBean
    ConfiguracionNotificacionMapper mapper;

	@Test
	public void testModificarNotificacion() {
		ConfiguracionNotificacionDTO peticion = new ConfiguracionNotificacionDTO();
		peticion.setIdConfigNotificacion(1L);
		ConfiguracionNotificacion entidad =  new ConfiguracionNotificacion();
		
		ConfiguracionNotificacionDTO res;
		
		 when(configuracionNotificacionServiceRepo.obtener(peticion.getIdConfigNotificacion())).thenReturn(entidad);
		 when(mapper.configuracionNotificacionDTOToEntity(peticion)).thenReturn(entidad);
		 when(configuracionNotificacionServiceRepo.guardar(entidad)).thenReturn(entidad);
		 
		 res = modificar.modificarNotificacion(peticion);
		 assertNotNull(res);
	     assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
	     
		 when(configuracionNotificacionServiceRepo.obtener(peticion.getIdConfigNotificacion())).thenReturn(null);
		 res = modificar.modificarNotificacion(peticion);
		 assertNotNull(res);
	     assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
	}

}
