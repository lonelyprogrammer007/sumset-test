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

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.mapper.BuzonNotificacionesMapper;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IBuzonNotificacionModificar.class, BuzonNotificacionModificar.class})
public class BuzonNotificacionModificarTest {
	
	@Autowired
	IBuzonNotificacionModificar modificar;
	
	@MockBean
    IBuzonNotificacioneServiceRepo buzonNotificacionServiceRepo;
    
	@MockBean
    BuzonNotificacionesMapper mapper;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@Test
	public void testDarRespuesta() throws SpddExceptions {
		BuzonNotificacionesDTO peticion = new BuzonNotificacionesDTO(), respuesta = new BuzonNotificacionesDTO();
		BuzonNotificaciones entidad = new BuzonNotificaciones();
		peticion.setIdNotificacion(1L);
		entidad.setIdNotificacion(1L);
		
		BuzonNotificacionesDTO res;
		
		when(buzonNotificacionServiceRepo.obtener(peticion.getIdNotificacion())).thenReturn(entidad);
		when(buzonNotificacionServiceRepo.guardar(entidad)).thenReturn(entidad);
		when(mapper.buzonNotificacionesEntityToDTO(entidad)).thenReturn(respuesta);

		res = modificar.darRespuesta(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
        
		when(buzonNotificacionServiceRepo.obtener(peticion.getIdNotificacion())).thenReturn(null);
		res = modificar.darRespuesta(peticion);
		assertNotNull(res);
        assertThat(res.getCodigoRespuesta()).isEqualTo(408L);
	}

}
