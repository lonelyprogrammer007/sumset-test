package co.gov.sdp.nhspdd.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.simple.ListaSimpleController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ListasControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> listaGenerico;
	@MockBean
	private MetodosRest<ListaSimpleDTO> listaSimpleRest;
	@MockBean
	private MetodosRest<ArgumentoListaSimpleDTO> argumentoRest;
	@MockBean
	private Model model;
	@Autowired
	private ListaSimpleController controller;
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testConsultarLs() throws Exception {
		when(listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-ls")).andExpect(status().isOk()).andExpect(view().name("listas/consultar-ls"));
	}

	@Test
	public void testEditarListaSimple() throws Exception {
		ListaSimpleDTO listaSimple = new ListaSimpleDTO();
		listaSimple.setIdListaSimple(1L);
		when(listaSimpleRest.get("/administracion_lista_simple/obtener_por_id/" + listaSimple.getIdListaSimple(),
				new ParameterizedTypeReference<ListaSimpleDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(listaSimple);
		when(listaGenerico.get(
				"/administracion_lista_simple/obtener_argumento_lista_simple/" + listaSimple.getIdListaSimple(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/editar-ls/" + listaSimple.getIdListaSimple())).andExpect(status().isOk())
				.andExpect(view().name("listas/editar-ls"));
	}

	@Test
	public void testCrearArgumento() throws Exception {
		ArgumentoListaSimpleDTO arg = new ArgumentoListaSimpleDTO();
		arg.setIdArgumento(1L);
		arg.setArgumento("argumento");
		arg.setResultado("resultado");
		arg.setIdListaSimple(1L);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(arg);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		when(argumentoRest.post(parametro, new ParameterizedTypeReference<ArgumentoListaSimpleDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE,
				NHSPDDConstantes.TIPO_CORE)).thenReturn(arg);

		String result = controller.crearArgumento(arg, model, null);
		assertEquals(result, "redirect:/editar-ls/" + arg.getIdArgumento());

	}

	@Test
	public void testPostEditarListaSimple() {
		ArgumentoListaSimpleDTO arg = new ArgumentoListaSimpleDTO();
		arg.setIdArgumento(1L);
		arg.setArgumento("argumento");
		arg.setResultado("resultado");
		arg.setIdListaSimple(1L);
		doNothing().when(argumentoRest).put(arg, ArgumentoListaSimpleDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE,
				NHSPDDConstantes.TIPO_CORE);
//		String result = controller.postEditarListaSimple(arg, model, );
//		assertEquals(result, "redirect:/editar-ls/" + arg.getIdArgumento());
	}

	
}
