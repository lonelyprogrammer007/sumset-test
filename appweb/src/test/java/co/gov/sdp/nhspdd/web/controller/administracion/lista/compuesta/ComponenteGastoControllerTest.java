package co.gov.sdp.nhspdd.web.controller.administracion.lista.compuesta;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ComponenteGastoControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<ComponenteGastoDTO> componenteGastoRest;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarComponenteGasto() throws Exception {
		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_COMPONENTE_GASTO_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		when(listaGenerico.get("/administracion_lista_simple/obtener_argumento/TipoProyecto",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-cg")).andExpect(status().isOk()).andExpect(view().name("listas/consultar-cg"));
	}

	@Test
	public void testCrearComponenteGasto() throws Exception {
		ComponenteGastoDTO componenteGasto = new ComponenteGastoDTO();
		componenteGasto.setIdComponenteGasto(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(componenteGasto);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(componenteGastoRest.post(parametro, new ParameterizedTypeReference<ComponenteGastoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_COMPONENTE_GASTO, NHSPDDConstantes.TIPO_CORE))
				.thenReturn(componenteGasto);

		mockMvc.perform(post("/crear_componente_gasto")).andExpect(view().name("redirect:/consultar-cg"));
	}

	@Test
	public void testEditarComponenteGasto() throws Exception {
		ComponenteGastoDTO componenteGasto = new ComponenteGastoDTO();
		componenteGasto.setIdComponenteGasto(1L);
		doNothing().when(componenteGastoRest).put(componenteGasto, ComponenteGastoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_COMPONENTE_GASTO,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/editar_componente_gasto")).andExpect(view().name("redirect:/consultar-cg"));
	}

	@Test
	public void testCambiarEstadoComponenteGasto() throws Exception {
		Long id = 1L;
		doNothing().when(componenteGastoRest).put(null, null,
				"/administracion/lista_compuesta/cambiar_estado_componente_gasto/" + id, NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(get("/cambiar_estado_componente/" + id)).andExpect(redirectedUrl("/consultar-cg"));

	}

}
