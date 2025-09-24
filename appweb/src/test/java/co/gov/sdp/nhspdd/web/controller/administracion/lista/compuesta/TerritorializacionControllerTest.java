package co.gov.sdp.nhspdd.web.controller.administracion.lista.compuesta;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta.TerritorializacionController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TerritorializacionControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<TerritorializacionDTO> territorializacionRest;
	@Autowired
	TerritorializacionController controller;
	@MockBean
	Model model;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarTerritorializacion() throws Exception {
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Localidad",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Upz",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Upr",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Barrio",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Vereda",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());

		mockMvc.perform(get("/consultar-terr")).andExpect(status().isOk())
				.andExpect(view().name("listas/consultar-territorializacion"));

	}

	@Test
	public void testCrearTerritorializacion() throws Exception {
		TerritorializacionDTO territorializacion = new TerritorializacionDTO();
		territorializacion.setIdTerritorializacion(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(territorializacion);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(territorializacionRest.post(parametro, new ParameterizedTypeReference<TerritorializacionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_TERRITORIALIZACION,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(territorializacion);
		mockMvc.perform(post("/gestion_terr")).andExpect(view().name("redirect:/consultar-terr"));
		doNothing().when(territorializacionRest).put(territorializacion, TerritorializacionDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_TERRITORIALIZACION,
				NHSPDDConstantes.TIPO_CORE);
		String result = controller.crearTerritorializacion(territorializacion, model, null);
		assertEquals(result, "redirect:/consultar-terr");
	}

	@Test
	public void testCambiarEstadoTerritorializacion() throws Exception {
		Long id = 1L;
		doNothing().when(territorializacionRest).put(null, null,
				"/administracion_lista_compuesta/modificar_estado_territorializacion/" + id,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_terr/" + id)).andExpect(view().name("redirect:/consultar-terr"));

	}

}
