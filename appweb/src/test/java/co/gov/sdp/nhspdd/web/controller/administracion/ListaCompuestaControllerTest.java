package co.gov.sdp.nhspdd.web.controller.administracion;

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
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ListaCompuestaControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> rest;
	@MockBean
	private MetodosRest<ListaCompuestaDTO> listaCompuesta;

	@Test
	public void testConsultarListaCompuestaTodos() throws Exception {
		when(rest.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_LISTAS_COMPUESTAS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get(NHSPDDConstantes.WEB_CONTROLLER_GET_CONSULTAR_LISTA_COMPUESTA)).andExpect(status().isOk())
				.andExpect(view().name(NHSPDDConstantes.WEB_RESOURCE_LISTA_CONSULTAR_CONTENIDO_LISTA_COMPUESTA));
	}

	@Test
	public void testConsultarTablaListaCompuesta() throws Exception {
		ListaCompuestaDTO eq = new ListaCompuestaDTO();
		eq.setIdListaCompuesta(1L);
		eq.setTabla("Equipamiento");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + eq.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(eq);
		mockMvc.perform(get("/lista_compuesta/" + eq.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-eq"));

		ListaCompuestaDTO pot = new ListaCompuestaDTO();
		pot.setIdListaCompuesta(2L);
		pot.setTabla("PotProyectoInstrumento");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + pot.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(pot);
		mockMvc.perform(get("/lista_compuesta/" + pot.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-pot"));

		ListaCompuestaDTO terr = new ListaCompuestaDTO();
		terr.setIdListaCompuesta(3L);
		terr.setTabla("Territorializacion");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + terr.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(terr);
		mockMvc.perform(get("/lista_compuesta/" + terr.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-terr"));

		ListaCompuestaDTO li = new ListaCompuestaDTO();
		li.setIdListaCompuesta(4L);
		li.setTabla("LineaDeInversion");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + li.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(li);
		mockMvc.perform(get("/lista_compuesta/" + li.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-li"));

		ListaCompuestaDTO em = new ListaCompuestaDTO();
		em.setIdListaCompuesta(5L);
		em.setTabla("EstructuraMeta");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + em.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(em);
		mockMvc.perform(get("/lista_compuesta/" + em.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-em"));

		ListaCompuestaDTO cg = new ListaCompuestaDTO();
		cg.setIdListaCompuesta(5L);
		cg.setTabla("ComponenteGasto");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + cg.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(cg);
		mockMvc.perform(get("/lista_compuesta/" + cg.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar-cg"));

		ListaCompuestaDTO sin = new ListaCompuestaDTO();
		sin.setIdListaCompuesta(6L);
		sin.setTabla("sin nombre");
		when(listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + sin.getIdListaCompuesta(),
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(sin);
		mockMvc.perform(get("/lista_compuesta/" + sin.getIdListaCompuesta()))
				.andExpect(view().name("redirect:/consultar_lista_compuesta"));

	}

}
