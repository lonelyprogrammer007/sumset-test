package co.gov.sdp.spdd.data.serviciofacade.ip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMeta;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

@RunWith(SpringRunner.class)
public class SessionRegistroIPTest extends SessionIPTest {

	@TestConfiguration
	static class SessionRegistroIPTestContextConfiguration {
		@Bean
		public SessionRegistroIP SessionRegistroIP() {
			return new SessionRegistroIP();
		}
	}

	@Autowired
	SessionRegistroIP sessionRegistroIP;


	@Test
	public void testGuardarCompromisoEstrategico() throws SpddExceptions  {
		when(compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(new CompromisoEstrategico())).thenReturn(new CompromisoEstrategicoDTO());
		when(compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(compromisoEstrategicoServiceRepo.guardar(new CompromisoEstrategico()))).thenReturn(new CompromisoEstrategicoDTO());
		assertThat(sessionRegistroIP.guardarCompromisoEstrategico(new CompromisoEstrategicoDTO())).isNotNull();
	}

	@Test
	public void testGuardarPdd() throws SpddExceptions {
		when(pddMapper.pddEntityToDTO(new Pdd())).thenReturn(new PddDTO());
		when(pddMapper.pddEntityToDTO(pddServiceRepo.guardar(new Pdd()))).thenReturn(new PddDTO());
		assertThat(sessionRegistroIP.guardarPdd(new PddDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddNivel() throws SpddExceptions {
		when(pddNivelMapper.pddNivelEntityToDTO(new PddNivel())).thenReturn(new PddNivelDTO());
		when(pddNivelMapper.pddNivelEntityToDTO(pddNivelServiceRepo.guardar(new PddNivel()))).thenReturn(new PddNivelDTO());
		assertThat(sessionRegistroIP.guardarPddNivel(new PddNivelDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddCompetenciaAsociada() throws SpddExceptions {
		when(pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(new PddCompetenciaAsociada())).thenReturn(new PddCompetenciaAsociadaDTO());
		when(pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(pddCompetenciaAsociadaServiceRepo.guardar(new PddCompetenciaAsociada()))).thenReturn(new PddCompetenciaAsociadaDTO());
		assertThat(sessionRegistroIP.guardarPddCompetenciaAsociada(new PddCompetenciaAsociadaDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddCompromisoEspecifico() throws SpddExceptions {
		when(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(new PddCompromisoEspecifico())).thenReturn(new PddCompromisoEspecificoDTO());
		when(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(pddCompromisoEspecificoServiceRepo.guardar(new PddCompromisoEspecifico()))).thenReturn(new PddCompromisoEspecificoDTO());
		assertThat(sessionRegistroIP.guardarPddCompromisoEspecifico(new PddCompromisoEspecificoDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddMeta() throws SpddExceptions {
		when(pddMetaMapper.pddMetaEntityToDTO(new PddMeta())).thenReturn(new PddMetaDTO());
		when(pddMetaMapper.pddMetaEntityToDTO(pddMetaServiceRepo.guardar(new PddMeta()))).thenReturn(new PddMetaDTO());
		assertThat(sessionRegistroIP.guardarPddMeta(new PddMetaDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddMetaResultado() throws SpddExceptions {
		when(pddMetaResultadoMapper.pddMetaResultadoEntityToDTO(new PddMetaResultado())).thenReturn(new PddMetaResultadoDTO());
		when(pddMetaResultadoMapper.pddMetaResultadoEntityToDTO(pddMetaResultadoServiceRepo.guardar(new PddMetaResultado()))).thenReturn(new PddMetaResultadoDTO());
		assertThat(sessionRegistroIP.guardarPddMetaResultado(new PddMetaResultadoDTO())).isNotNull();
	}

	@Test
	public void testGuardarPrbPoblacion() throws SpddExceptions {
		when(poblacionMapper.poblacionEntityToDTO(new PddPrbPoblacion())).thenReturn(new PddPrbPoblacionDTO());
		when(poblacionMapper.poblacionEntityToDTO(poblacionServiceRepo.guardar(new PddPrbPoblacion()))).thenReturn(new PddPrbPoblacionDTO());
		assertThat(sessionRegistroIP.guardarPrbPoblacion(new PddPrbPoblacionDTO())).isNotNull();
	}

	@Test
	public void testGuardarObraConcreta() throws SpddExceptions {
		when(pddObraConcretamapper.pddObraConcretaEntityToDTO(new PddObraConcreta())).thenReturn(new PddObraConcretaDTO());
		when(pddObraConcretamapper.pddObraConcretaEntityToDTO(pddObraConcretaServiceRepo.guardar(new PddObraConcreta()))).thenReturn(new PddObraConcretaDTO());
		assertThat(sessionRegistroIP.guardarObraConcreta(new PddObraConcretaDTO())).isNotNull();
	}

	@Test
	public void testGuardadPddCompromiso() throws SpddExceptions {
		when(pddCompromisoMapper.pddCompromisoEntityToDTO(new PddCompromiso())).thenReturn(new PddCompromisoDTO());
		when(pddCompromisoMapper.pddCompromisoEntityToDTO(pddCompromisoServiceRepo.guardar(new PddCompromiso()))).thenReturn(new PddCompromisoDTO());
		assertThat(sessionRegistroIP.guardadPddCompromiso(new PddCompromisoDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddProblematica() throws SpddExceptions {
		when(pddProblematicaMapper.pddProblematicaEntityToDTO(new PddProblematica())).thenReturn(new PddProblematicaDTO());
		when(pddProblematicaMapper.pddProblematicaEntityToDTO(pddProblematicaServiceRepo.guardar(new PddProblematica()))).thenReturn(new PddProblematicaDTO());
		assertThat(sessionRegistroIP.guardarPddProblematica(new PddProblematicaDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddPrbValoracion() throws SpddExceptions {
		when(pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(new PddPrbValoracion())).thenReturn(new PddPrbValoracionDTO());
		when(pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(pddPrbValoracionServiceRepo.guardar(new PddPrbValoracion()))).thenReturn(new PddPrbValoracionDTO());
		assertThat(sessionRegistroIP.guardarPddPrbValoracion(new PddPrbValoracionDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddPrbIndicador() throws SpddExceptions {
		when(pddPrbIndicadorMapper.prbIndicadorEntityToDTO(new PddPrbIndicador())).thenReturn(new PddPrbIndicadorDTO());
		when(pddPrbIndicadorMapper.prbIndicadorEntityToDTO(pddPrbIndicadorServiceRepo.guardar(new PddPrbIndicador()))).thenReturn(new PddPrbIndicadorDTO());
		assertThat(sessionRegistroIP.guardarPddPrbIndicador(new PddPrbIndicadorDTO())).isNotNull();
	}

	@Test
	public void testGuardarPddIndicador() throws SpddExceptions {
		when(pddIndicadorMapper.pddIndicadorEntityToDTO(new PddIndicador())).thenReturn(new PddIndicadorDTO());
		when(pddIndicadorMapper.pddIndicadorEntityToDTO(pddIndicadorServiceRepo.guardar(new PddIndicador()))).thenReturn(new PddIndicadorDTO());
		assertThat(sessionRegistroIP.guardarPddIndicador(new PddIndicadorDTO())).isNotNull();
	}

	@Test
	public void testGuardarPdl() throws SpddExceptions {
		Pdl entidad = new Pdl();
		PdlDTO pdlDTO = new PdlDTO();
		when(pdlMapper.pdlEntityToDTO(entidad)).thenReturn(pdlDTO);
		when(pdlMapper.pdlEntityToDTO(pdlServiceRepo.guardar(entidad))).thenReturn(pdlDTO);
		assertThat(sessionRegistroIP.guardarPdl(pdlDTO)).isNotNull();
	}

	@Test
	public void testGuardarPdlNivel() throws SpddExceptions {
		PdlNivel entidad = new PdlNivel();
		PdlNivelDTO pdlNivelDTO = new PdlNivelDTO();
		when(pdlNivelMapper.pdlNivelEntityToDTO(entidad)).thenReturn(pdlNivelDTO);
		when(pdlNivelMapper.pdlNivelEntityToDTO(pdlNivelServiceRepo.guardar(entidad))).thenReturn(pdlNivelDTO);
		assertThat(sessionRegistroIP.guardarPdlNivel(pdlNivelDTO)).isNotNull();
	}

	@Test
	public void testGuardarPddNivelAtributo() {
		when(pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(new PddNivelAtributo())).thenReturn(new PddNivelAtributoDTO());
		when(pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributoServiceRepo.guardar(new PddNivelAtributo()))).thenReturn(new PddNivelAtributoDTO());
		assertThat(sessionRegistroIP.guardarPddNivelAtributo(new PddNivelAtributoDTO()));
	}
	
	@Test
	public void testGuardaPot() {
		Pot entidad = new Pot();
		PotDTO potDTO = new PotDTO();
		when(potMapper.potEntityToDTO(entidad)).thenReturn(potDTO);
		when(potMapper.potEntityToDTO(potServiceRepo.guardar(entidad))).thenReturn(potDTO);
		assertThat(sessionRegistroIP.guardarPot(potDTO)).isNotNull();
	}

	@Test
	public void testGuardarPotObraEntidad() throws Exception {
		PotObraEntidadDTO potObraEntidadDTO = new PotObraEntidadDTO();
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		when(potObraEntidadMapper.potObraEntidadDTOToEntity(potObraEntidadDTO)).thenReturn(potObraEntidad);
		when(potObraEntidadServiceRepo.guardar(potObraEntidad)).thenReturn(potObraEntidad);
		when(potObraEntidadMapper.potObraEntidadEntityToDTO(potObraEntidad)).thenReturn(potObraEntidadDTO);
		PotObraEntidadDTO res = sessionRegistroIP.guardarPotObraEntidad(potObraEntidadDTO);
		assertNotNull(res);
	}

	@Test
	public void testGuardarPotObra() throws Exception {
		PotObraDTO potObraDTO = new PotObraDTO();
		PotObra potObra = new PotObra();
		when(potObraMapper.potObraDTOToEntity(potObraDTO)).thenReturn(potObra);
		when(potObraServiceRepo.guardar(potObra)).thenReturn(potObra);
		when(potObraMapper.potObraEntityToDTO(potObraServiceRepo.guardar(potObra))).thenReturn(potObraDTO);
		PotObraDTO res = sessionRegistroIP.guardarPotObra(potObraDTO);
		assertNotNull(res);
	}

	@Test
	public void testGuardarPotInstrumento() throws Exception {
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		PotInstrumento potInstrumento = new PotInstrumento();
		when(potInstrumentoMapper.potInstrumentoDTOToEntity(potInstrumentoDTO)).thenReturn(potInstrumento);
		when(potInstrumentoServiceRepo.guardar(potInstrumento)).thenReturn(potInstrumento);
		when(potInstrumentoMapper.potInstrumentoEntityToDTO(potInstrumento)).thenReturn(potInstrumentoDTO);
		PotInstrumentoDTO res = sessionRegistroIP.guardarPotInstrumento(potInstrumentoDTO);
		assertNotNull(res);
	}
	
	@Test
	public void testGuardarPdlNivelAtributo() throws SpddExceptions {
		PdlNivelAtributo entidad = new PdlNivelAtributo();
		PdlNivelAtributoDTO pdlNivelAtributoDTO = new PdlNivelAtributoDTO();
		when(pdlNivelAtributoMapper.pdlNivelAtributoEntityToDTO(entidad)).thenReturn(pdlNivelAtributoDTO);
		when(pdlNivelAtributoMapper.pdlNivelAtributoEntityToDTO(pdlNivelAtributoServiceRepo.guardar(entidad))).thenReturn(pdlNivelAtributoDTO);
		assertThat(sessionRegistroIP.guardarPdlNivelAtributo(pdlNivelAtributoDTO)).isNotNull();
	}

	@Test
	public void testGuardarPddRangoPonderacion() throws SpddExceptions {
		when(pddRangoPonderacionMapper.ppdRangoPonderacionEntityToDTO(new PddRangoPonderacion())).thenReturn(new PddRangoPonderacionDTO());
		when(pddRangoPonderacionMapper.ppdRangoPonderacionEntityToDTO(pddRangoPonderacionServiceRepo.guardar(new PddRangoPonderacion()))).thenReturn(new PddRangoPonderacionDTO());
		assertThat(sessionRegistroIP.guardarPddRangoPonderacion(new PddRangoPonderacionDTO())).isNotNull();
	}
}
