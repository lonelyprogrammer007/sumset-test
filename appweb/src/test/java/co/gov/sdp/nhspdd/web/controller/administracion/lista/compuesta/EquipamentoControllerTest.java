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
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EquipamentoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<EquipamientoDTO> equipamentoDTORest;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarEquipamento() throws Exception {

		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Sectores",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/Categorias",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_EQUIPAMIENTOS_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-eq")).andExpect(status().isOk()).andExpect(view().name("listas/consultar-eq"));
	}

	@Test
	public void testCrearEquipamento() throws Exception {
		EquipamientoDTO equipamiento = new EquipamientoDTO();
		equipamiento.setIdEquipamento(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(equipamiento);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(equipamentoDTORest.post(parametro, new ParameterizedTypeReference<EquipamientoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_EQUIPAMIENTO, NHSPDDConstantes.TIPO_CORE))
				.thenReturn(equipamiento);
		mockMvc.perform(post("/crear_equipamientos")).andExpect(view().name("redirect:/consultar-eq"));
	}

	@Test
	public void testEditarEquipamento() throws Exception {
		EquipamientoDTO equipamiento = new EquipamientoDTO();
		equipamiento.setIdEquipamento(1L);
		doNothing().when(equipamentoDTORest).put(equipamiento, EquipamientoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_EQUIPAMIENTO,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/editar_equipamientos")).andExpect(view().name("redirect:/consultar-eq"));

	}

	@Test
	public void testCambiarEstadoEquipamiento() throws Exception {
		Long id = 1L;
		doNothing().when(equipamentoDTORest).put(null, null,
				"/administracion/lista_compuesta/cambiar_estado_equipamiento/" + id, NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_equipamiento/" + id)).andExpect(view().name("redirect:/consultar-eq"));

	}

}
