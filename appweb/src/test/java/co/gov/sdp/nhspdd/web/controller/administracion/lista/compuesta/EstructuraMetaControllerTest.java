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

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EstructuraMetaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<EstructuraMetaDTO> estructuraMetaRest;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarEstructura() throws Exception {
		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_ESTRUCTURA_METAS_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Verbo",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/UnidadDeMedida",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-em")).andExpect(status().isOk()).andExpect(view().name("listas/consultar-em"));
	}

	@Test
	public void testCrearEstructura() throws Exception {
		EstructuraMetaDTO estructurasMeta = new EstructuraMetaDTO();
		estructurasMeta.setIdEstructuraMetas(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(estructurasMeta);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(estructuraMetaRest.post(parametro, new ParameterizedTypeReference<EstructuraMetaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_ESTRUCTURA_METAS, NHSPDDConstantes.TIPO_CORE))
				.thenReturn(estructurasMeta);
		mockMvc.perform(post("/crear_estructura_meta")).andExpect(view().name("redirect:/consultar-em"));

	}

	@Test
	public void testModificarEstructura() throws Exception {
		EstructuraMetaDTO estructurasMeta = new EstructuraMetaDTO();
		estructurasMeta.setIdEstructuraMetas(1L);
		doNothing().when(estructuraMetaRest).put(estructurasMeta, EstructuraMetaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_ESTRUCTURA_METAS,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/editar_estructura_meta")).andExpect(view().name("redirect:/consultar-em"));

	}

	@Test
	public void testCambiarEstadoEstructura() throws Exception {
		Long id = 1L;
		doNothing().when(estructuraMetaRest).put(null, null,
				"/administracion/lista_compuesta/cambiar_estado_estructura_meta/" + id, NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_estructura/" + id)).andExpect(view().name("redirect:/consultar-em"));

	}

}
