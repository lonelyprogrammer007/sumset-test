package co.gov.sdp.nhspdd.web.controller;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ParametrosControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MetodosRest<GenericoDTO> dto;
	@MockBean
	private MetodosRest<ParametroGeneralDTO> param;

	@Test
	public void testConsultarParametros() throws Exception {
		when(dto.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODO_PARAMETRO_GENERAL,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE)).thenReturn(new GenericoDTO());
		mockMvc.perform(get("/consultar-parametro")).andExpect(status().isOk())
				.andExpect(view().name("parametros-consecutivos/consultar-parametros"));
	}

	@Test
	public void testEditarParametro() throws Exception {
		ParametroGeneralDTO parametro = new ParametroGeneralDTO();
		parametro.setCodigoParametro("1");
		doNothing().when(param).put(parametro, ParametroGeneralDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_PARAMETRO_GENERAL,
				NHSPDDConstantes.TIPO_CORE);
		mockMvc.perform(post("/editarParametro")).andExpect(redirectedUrl("/consultar-parametro"));
	}

}
