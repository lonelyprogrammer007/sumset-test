package co.gov.sdp.spdd.core.bp.service.bpiniciativa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.spdd.core.bp.iservice.bpiniciativa.IBpIniciativaModificarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

@RunWith(PowerMockRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class, IBpIniciativaModificarService.class,
		BpIniciativaModificarService.class, ObjectMapper.class })
class BpIniciativaModificarServiceTest {

	@Autowired
	IBpIniciativaModificarService modificar;

	@MockBean
	ISessionFacadeBP sessionFacadeBP;

	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;

	@Test
	public void testModificarIniciativaCiudadana() throws Exception {

		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		peticion.setIdIniciativa(1L);
		peticion.setGruposEtarios("1");
		peticion.setCodicionesPoblacionales("1");
		peticion.setIdLsUpz(1L);
		peticion.setIdLsUpr(1L);
		peticion.setNombreSector("hola");

		final BpIniciativaCiudadanaDTO respuesta = new BpIniciativaCiudadanaDTO();
		respuesta.setIdIniciativa(1L);

		when(sessionFacadeBP.consultarBpIniciativaCiudadanaPorId(peticion.getIdIniciativa())).thenReturn(respuesta);
		doNothing().when(sessionFacadeBP).eliminarGruposEtarios(peticion.getIdIniciativa());
		doNothing().when(sessionFacadeBP).eliminarUbicaciones(peticion.getIdIniciativa());
		doNothing().when(sessionFacadeBP).eliminarTodasCondicionIniciativa(peticion.getIdIniciativa());
		GenericoDTO generico = new GenericoDTO();
		TerritorializacionDTO te = new TerritorializacionDTO();
		te.setIdTerritorializacion(1L);
		te.setIdLsLocalidad(1L);
		te.setIdLsBarrio(1L);
		te.setIdLsUpr(1L);
		te.setIdLsUpz(1L);
		te.setIdLsVereda(1L);

		TerritorializacionDTO aux = new TerritorializacionDTO();
		aux.setIdLsUpz(1L);
		aux.setIdLsUpr(1L);
		aux.setIdLsLocalidad(1L);

		List<TerritorializacionDTO> terr = new ArrayList<>();
		terr.add(te);
		generico.setLstObjectsDTO(new ArrayList<>(terr));
		when(sessionFacadeAFS.buscarPorLocalidadTerritorializacion()).thenReturn(generico);
		TypeReference<List<TerritorializacionDTO>> type = new TypeReference<List<TerritorializacionDTO>>() {
		};
		PowerMockito.whenNew(TypeReference.class).withAnyArguments().thenReturn(type);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(aux)).thenReturn(te);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsVeredaYLsUpr(aux)).thenReturn(te);
		BpIniciativaUbicacionDTO ubicacion = new BpIniciativaUbicacionDTO();
		ubicacion.setIdTerritorializacion(aux.getIdTerritorializacion());
		ubicacion.setIdIniciativa(respuesta.getIdIniciativa());
		when(sessionFacadeBP.guardarUbicacionIniciativaCiudadana(ubicacion)).thenReturn(new BpIniciativaUbicacionDTO());
		assertThat(modificar.modificarIniciativaCiudadana(peticion)).isNotNull();

	}

}
