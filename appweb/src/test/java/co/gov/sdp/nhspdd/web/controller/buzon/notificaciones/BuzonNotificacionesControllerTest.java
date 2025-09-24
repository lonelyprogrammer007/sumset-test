package co.gov.sdp.nhspdd.web.controller.buzon.notificaciones;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BuzonNotificacionesControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<BuzonNotificacionesDTO> buzonRest;

	@Test
	public void testBuzonTodos() throws Exception {

		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_ADMIN,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar_buzon_todos")).andExpect(status().isOk())
				.andExpect(view().name("buzon-notificaciones/buzon-todos"));

	}

	@Test
	public void testBuzonAdmin() throws Exception {

		mockMvc.perform(get("/consultar_buzon_admin")).andExpect(status().isOk())
				.andExpect(view().name("buzon-notificaciones/buzon-admin"));
	}

	@Test
	public void testPlantillaNotificacion() throws Exception {
		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_AUTOMATICOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar_plantilla_notificacion")).andExpect(status().isOk())
				.andExpect(view().name("buzon-notificaciones/plantilla"));

	}

	@Test
	public void testResponderMensaje() throws Exception {
		BuzonNotificacionesDTO buzonNotificaciones = new BuzonNotificacionesDTO();
		buzonNotificaciones.setIdNotificacion(1L);
		buzonNotificaciones.setRespuesta("atendido");
		doNothing().when(buzonRest).put(buzonNotificaciones, BuzonNotificacionesDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_DAR_RESPUESTA, NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/responder_mensaje")).andExpect(view().name("redirect:/consultar_buzon_todos"));
	}

}
