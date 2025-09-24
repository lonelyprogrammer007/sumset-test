package co.gov.sdp.spdd.data.serviciofacade.ip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

@RunWith(SpringRunner.class)
public class SessionEliminarIPTest extends SessionIPTest {
	
	@TestConfiguration
	static class SessionEliminarIPTestContextConfiguration {
		@Bean
		public SessionEliminarIP sessionEliminarIP() {
			return new SessionEliminarIP();
		}
	}

	@Autowired
	SessionEliminarIP sessionEliminarIP;

	@Test
	public void testEliminarPddMeta() throws Exception {
		Long idPddMeta = 1L;
		PddMetaDTO pddMetaDTO = new PddMetaDTO();
		pddMetaDTO.setIdMeta(idPddMeta);
		List<PddMetaDTO> listaDTO = new ArrayList<PddMetaDTO>();
		listaDTO.add(pddMetaDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddMetaServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddMeta(idPddMeta);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPddObraConcreta() throws Exception {
		Long idPddObraConcreta = 1L;
		PddObraConcretaDTO pddObraConcretaDTO = new PddObraConcretaDTO();
		pddObraConcretaDTO.setIdConcreta(idPddObraConcreta);
		List<PddObraConcretaDTO> listaDTO = new ArrayList<PddObraConcretaDTO>();
		listaDTO.add(pddObraConcretaDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddObraConcretaServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddObraConcreta(idPddObraConcreta);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPddCompromisoEspecifico() throws Exception {
		Long idPddCompromisoEspecifico = 1L;
		PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO = new PddCompromisoEspecificoDTO();
		pddCompromisoEspecificoDTO.setIdEspecifico(idPddCompromisoEspecifico);
		List<PddCompromisoEspecificoDTO> listaDTO = new ArrayList<PddCompromisoEspecificoDTO>();
		listaDTO.add(pddCompromisoEspecificoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddCompromisoEspecificoServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddCompromisoEspecifico(idPddCompromisoEspecifico);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPrbPoblacion() throws Exception {
		Long idPrbPoblacion = 1L;
		PddPrbPoblacionDTO prbPoblacionDTO = new PddPrbPoblacionDTO();
		prbPoblacionDTO.setIdPoblacion(idPrbPoblacion);
		List<PddPrbPoblacionDTO> listaDTO = new ArrayList<PddPrbPoblacionDTO>();
		listaDTO.add(prbPoblacionDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(poblacionServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPrbPoblacion(idPrbPoblacion);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPrbIndicador() throws Exception {
		Long idPrbIndicador = 1L;
		PddPrbIndicadorDTO prbIndicadorDTO = new PddPrbIndicadorDTO(); 
		prbIndicadorDTO.setIdIndicador(idPrbIndicador);
		List<PddPrbIndicadorDTO> listaDTO = new ArrayList<PddPrbIndicadorDTO>();
		listaDTO.add(prbIndicadorDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddPrbIndicadorServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPrbIndicador(idPrbIndicador);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPddMetaResultado() throws Exception {
		Long idPddMetaResultado = 1L;
		PddMetaResultadoDTO pddMetaResultadoDTO = new PddMetaResultadoDTO();
		pddMetaResultadoDTO.setIdMetaResultado(idPddMetaResultado);
		List<PddMetaResultadoDTO> listaDTO = new ArrayList<PddMetaResultadoDTO>();
		listaDTO.add(pddMetaResultadoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddMetaResultadoServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddMetaResultado(idPddMetaResultado);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarIndicadorMetaResultado() throws Exception {
		Long idPddMRIndicador = 1L;
		PddMRIndicadorDTO pddMRIndicadorDTO = new PddMRIndicadorDTO();
		pddMRIndicadorDTO.setIdMetaResultado(idPddMRIndicador);
		List<PddMRIndicadorDTO> listaDTO = new ArrayList<PddMRIndicadorDTO>();
		listaDTO.add(pddMRIndicadorDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddMRIndicadorServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarIndicadorMetaResultado(idPddMRIndicador);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarMetaProducto() throws Exception {
		Long idPddMetaProducto = 1L;
		PddMetaProductoDTO pddMetaProductoDTO = new PddMetaProductoDTO();
		pddMetaProductoDTO.setIdMetaProducto(idPddMetaProducto);
		List<PddMetaProductoDTO> listaDTO = new ArrayList<PddMetaProductoDTO>();
		listaDTO.add(pddMetaProductoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(potRamaServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarRamaPot(idPddMetaProducto);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarRamaPot() throws Exception {
		Long idRamaPot = 1L;
		PotRamaDTO potRamaDTO = new PotRamaDTO();
		potRamaDTO.setIdPotRama(idRamaPot);
		List<PotRamaDTO> listaDTO = new ArrayList<PotRamaDTO>();
		listaDTO.add(potRamaDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(potRamaServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarRamaPot(idRamaPot);
		assertEquals(0, listaDTO.size());
		
	}

	@Test
	public void testEliminarNivelPot() throws Exception {
		Long idNivelPot = 1L;
		PotNivelDTO potNivelDTO = new PotNivelDTO();
		potNivelDTO.setIdNivelPot(idNivelPot);
		List<PotNivelDTO> listaDTO = new ArrayList<PotNivelDTO>();
		listaDTO.add(potNivelDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(potNivelServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarNivelPot(idNivelPot);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarIndicadorMetaProducto() throws Exception {
		Long idIndicadorMetaProducto = 1L;
		PddIndicadorDTO pddIndicadorDTO = new PddIndicadorDTO();
		pddIndicadorDTO.setIdIndicador(idIndicadorMetaProducto);
		List<PddIndicadorDTO> listaDTO = new ArrayList<PddIndicadorDTO>();
		listaDTO.add(pddIndicadorDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddMpIndicadorServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarIndicadorMetaProducto(idIndicadorMetaProducto);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void eliminarPddNivelAtributo() throws Exception {
		Long idPddNivelAtributo = 1L;
		PddNivelAtributoDTO pddNivelAtributoDTO = new PddNivelAtributoDTO();
		pddNivelAtributoDTO.setIdAtributo(idPddNivelAtributo);
		List<PddNivelAtributoDTO> listaDTO = new ArrayList<PddNivelAtributoDTO>();
		listaDTO.add(pddNivelAtributoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddNivelAtributoServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddNivelAtributo(idPddNivelAtributo);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPotObra() throws Exception {
		Long idPotObra = 1L;
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		potObraEntidad.setIdObraEntidad(idPotObra);
		List<PotObraEntidad> listaDTO = new ArrayList<PotObraEntidad>();
		listaDTO.add(potObraEntidad);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(potInstrumentoServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPotInstrumento(idPotObra);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarTodosPotObraEntidadPorIdPotObra() throws Exception {
		Long idPotObra = 1L;
		PotObraEntidad potObraEntidad = new PotObraEntidad();
		List<PotObraEntidad> listaPotObraEntidad = new ArrayList<PotObraEntidad>();
		listaPotObraEntidad.add(potObraEntidad);
		when(potObraEntidadServiceRepo.obtenerTodosPorIdPotObra(idPotObra)).thenReturn(listaPotObraEntidad);
		doAnswer((argumentos) -> {
	        listaPotObraEntidad.clear();
	        return null;
	    }).when(potObraEntidadServiceRepo).eliminarTodos(listaPotObraEntidad);
		sessionEliminarIP.eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
		assertThat(listaPotObraEntidad.isEmpty()).isTrue();
	}

	@Test
	public void testEliminarPotInstrumento() throws Exception {
		Long idPotInstrumento = 1L;
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		potInstrumentoDTO.setIdInstrumentoPot(idPotInstrumento);
		List<PotInstrumentoDTO> listaDTO = new ArrayList<PotInstrumentoDTO>();
		listaDTO.add(potInstrumentoDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(potInstrumentoServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPotInstrumento(idPotInstrumento);
		assertEquals(0, listaDTO.size());
	}

	@Test
	public void testEliminarPddRangoPonderacion() throws Exception {
		Long idRango = 1L;
		PddRangoPonderacionDTO pddRangoPonderacionDTO = new PddRangoPonderacionDTO();
		pddRangoPonderacionDTO.setIdRango(idRango);
		List<PddRangoPonderacionDTO> listaDTO = new ArrayList<PddRangoPonderacionDTO>();
		listaDTO.add(pddRangoPonderacionDTO);
		doAnswer((argumentos) -> {
	        listaDTO.remove(0);
	        return null;
	    }).when(pddRangoPonderacionServiceRepo).eliminar(1L);
		sessionEliminarIP.eliminarPddRangoPonderacion(idRango);;
		assertEquals(0, listaDTO.size());
	}

}
