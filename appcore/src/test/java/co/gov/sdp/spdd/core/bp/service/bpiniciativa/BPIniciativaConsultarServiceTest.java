package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBPIniciativaConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBPIniciativaConsultarService.class,
		BPIniciativaConsultarService.class })
public class BPIniciativaConsultarServiceTest {

	@Autowired
	BPIniciativaConsultarService consultar;

	@MockBean
	SessionFacadeBP sessionFacadeBP;
	
	@MockBean
	SessionFacadeAFS sessionFacadeAFS;

	@Test
	void testObtenerTodasLaIniciativasCiudadanas() throws SpddExceptions {

		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		when(sessionFacadeBP.filtrarIniciativaCiudadana(peticion)).thenReturn(new GenericoDTO());
		assertThat(consultar.obtenerTodasLaIniciativasCiudadanas(peticion).getCodigoRespuesta()).isEqualTo(200);

	}

	@Test
	public void testConsultaDetallaIniciativaCiudadana() throws Exception {
		Long idIniciativa = 1L;
		
		BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		
		BpIniciativaEtarioDTO bpIniciativaEtarioDTO = new BpIniciativaEtarioDTO();
		bpIniciativaEtarioDTO.setIdLsEtario(1L);
		bpIniciativaEtarioDTO.setNombreEtario("nombre");
		List<BpIniciativaEtarioDTO> gruposEtarios = new ArrayList<BpIniciativaEtarioDTO>();
		
		BpIniciativaUbicacionDTO ubicacion1 = new BpIniciativaUbicacionDTO();
		ubicacion1.setNombreUpr("NA");
		BpIniciativaUbicacionDTO ubicacion2 = new BpIniciativaUbicacionDTO();
		ubicacion2.setNombreUpr("upr");
		List<BpIniciativaUbicacionDTO> ubicaciones = new ArrayList<BpIniciativaUbicacionDTO>();
		ubicaciones.add(ubicacion1);
		ubicaciones.add(ubicacion2);
		
		BpIniciativaCondicionDTO cond1 = new BpIniciativaCondicionDTO();
		cond1.setIdLsCondicion(1L);
		cond1.setNombreCondicion("cond1");
		List<BpIniciativaCondicionDTO> condicion = new ArrayList<BpIniciativaCondicionDTO>();
		
		when(sessionFacadeBP.consultarBpIniciativaCiudadanaPorId(idIniciativa)).thenReturn(respuesta);
		when(sessionFacadeBP.consultarGruposEtariosPorIniciativa(idIniciativa)).thenReturn(gruposEtarios);
		when(sessionFacadeBP.consultarUbicacionesGruposEtariosPorIniciativa(idIniciativa)).thenReturn(ubicaciones);
		when(sessionFacadeBP.obtenerCondicionesPorIniciativa(idIniciativa)).thenReturn(condicion);
		
		BpIniciativaCiudadanaDTO res = consultar.consultaDetallaIniciativaCiudadana(idIniciativa);
		assertNotNull(res);
		assertThat(res.getNombrePoblaciones()).isNotNull();
	}

}
