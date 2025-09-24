package co.gov.sdp.nhspdd.web.controller.administracion.lista.compuesta;

import static org.junit.Assert.*;
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

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LineaDeInversionControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<LineaDeInversionDTO> lineaDeInversionRest;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testConsultarLineaInversion() throws Exception {

		when(listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Sectores",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());

		mockMvc.perform(get("/consultar-li")).andExpect(status().isOk()).andExpect(view().name("listas/consultar-li"));

	}

	@Test
	public void testCrearLineaDeInversion() throws Exception {
		LineaDeInversionDTO lineaDeInversion = new LineaDeInversionDTO();
		lineaDeInversion.setIdLineaInversion(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(lineaDeInversion);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(lineaDeInversionRest.post(parametro, new ParameterizedTypeReference<LineaDeInversionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_LINEA_INVERSION,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(lineaDeInversion);
		mockMvc.perform(post("/crear_linea_inversion")).andExpect(view().name("redirect:/consultar-li"));

	}

	@Test
	public void testModificarLineaDeInversion() throws Exception {
		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();
		lineaDeInversionDTO.setIdLineaInversion(1L);
		doNothing().when(lineaDeInversionRest).put(lineaDeInversionDTO, LineaDeInversionDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION,
				NHSPDDConstantes.TIPO_CORE);

		mockMvc.perform(post("/editar_linea_inversion")).andExpect(view().name("redirect:/consultar-li"));

	}

	@Test
	public void testCambiarEstadoLineaInversion() throws Exception {
		Long id = 1L;
		doNothing().when(lineaDeInversionRest).put(null, null,
				"/administracion_lista_compuesta/modificar_estado_linea_inversion/" + id, NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_linea/" + id)).andExpect(view().name("redirect:/consultar-li"));

	}

}
