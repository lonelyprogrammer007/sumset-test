package co.gov.sdp.spdd.core.service.buzon.notificacion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.MetodosRest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeAFS.class,IBuzonNotificacionRegistrar.class, BuzonNotificacionRegistrar.class})
public class BuzonNotificacionRegistrarTest {
	
	@Autowired
	IBuzonNotificacionRegistrar registrar;
	
	@MockBean
	MetodosRest<RespuestaApiDTO<UsuariosSeguridadDTO>> metodoRest;

	@MockBean
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidadRest;

	@MockBean
	ObjectMapper objectMapper;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testRegistarBuzonNotificacion() throws JsonProcessingException, SpddExceptions {
	}

}
