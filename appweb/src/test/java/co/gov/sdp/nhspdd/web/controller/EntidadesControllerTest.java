package co.gov.sdp.nhspdd.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EntidadesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConsultarEntidades() throws Exception {
//        mockMvc.perform(get("/consultar-ent")).andExpect(status().isOk())
//                .andExpect(view().name("entidades/consultar-ent-alc"));
    }

	@Test
	public void testCrearEntidades() throws Exception {
		mockMvc.perform(get("/crear-ent")).andExpect(status().isOk())
		.andExpect(view().name("entidades/crear-entidad"));
	}

}
