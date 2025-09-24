package co.gov.sdp.nhspdd.web.controller.administracion.lista.compuesta;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PotProyectoInstrumentoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<PotProyectoInstrumentoDTO> potProyectoInstrumento;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testConsultarPOT() throws Exception {
		when(listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/PotProyecto",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Instrumento",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-pot")).andExpect(status().isOk())
				.andExpect(view().name("listas/consultar-pot"));

	}

	@Test
	public void testRegistrarPOT() throws Exception {
		PotProyectoInstrumentoDTO proyecto = new PotProyectoInstrumentoDTO();
		proyecto.setIdProyectoInstrumento(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(proyecto);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(potProyectoInstrumento.post(parametro, new ParameterizedTypeReference<PotProyectoInstrumentoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_POT_PROYECTO_INSTRUMENTO,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(proyecto);
		mockMvc.perform(post("/crear_pot")).andExpect(view().name("redirect:/consultar-pot"));

	}

	@Test
	public void testEditarPOT() throws Exception {
		PotProyectoInstrumentoDTO potProyecto = new PotProyectoInstrumentoDTO();
		potProyecto.setIdProyectoInstrumento(1L);
		doNothing().when(potProyectoInstrumento).put(potProyecto, PotProyectoInstrumentoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_POT_PROYECTO_INSTRUMENTO,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/editar_ProyectosPOT")).andExpect(view().name("redirect:/consultar-pot"));

	}

	@Test
	public void testCambiarEstadoPOT() throws Exception {
		Long id = 1L;
		doNothing().when(potProyectoInstrumento).put(null, null,
				"/administracion_lista_compuesta/modificar_estado_proyecto_instrumento/" + id,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_pot/" + id)).andExpect(view().name("redirect:/consultar-pot"));

	}

}
