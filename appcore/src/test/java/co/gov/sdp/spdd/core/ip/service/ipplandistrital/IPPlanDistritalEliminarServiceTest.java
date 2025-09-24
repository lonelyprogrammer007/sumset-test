package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IIPPlanDistritalEliminarService.class, IPPlanDistritalEliminarService.class,
		SessionFacadeIP.class })
public class IPPlanDistritalEliminarServiceTest {

	@Autowired
	IPPlanDistritalEliminarService eliminar;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	AuditoriaUtil auditoria;

	@MockBean
	SPDDLogger spddLogger;

	@Test
	public void testEliminarMetaResultado() throws SpddExceptions {
		PddMetaResultadoDTO respuesta = new PddMetaResultadoDTO();
		PddMRIndicadorDTO peticion = new PddMRIndicadorDTO();
		peticion.setIdMetaResultado(1L);
		
		when(sessionFacadeIP.consultarIndicadoresMetaResultado(peticion)).thenReturn(new GenericoDTO());
		assertThat(eliminar.eliminarMetaResultado(1L).getCodigoRespuesta()).isEqualTo(400);
		
		respuesta.setEsFormulacion(0L);
		when(sessionFacadeIP.consultarPddMetaResultadoPorId(1L)).thenReturn(respuesta);
		assertThat(eliminar.eliminarMetaResultado(1L).getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setEsFormulacion(1L);
		doNothing().when(sessionFacadeIP).eliminarMetaResultado(1L);
		when(sessionFacadeIP.consultarPddMetaResultadoPorId(1L)).thenReturn(respuesta);
		assertThat(eliminar.eliminarMetaResultado(1L).getCodigoRespuesta()).isEqualTo(400);
		
		GenericoDTO indicadoresResultado = new GenericoDTO();
		indicadoresResultado.getLstObjectsDTO().add(new Object());
		assertThat(eliminar.eliminarMetaResultado(1L).getCodigoRespuesta()).isEqualTo(400);

	}

	@Test
	public void testEliminarIndicadorMetaResultado() throws Exception {
		Long indMetaResultado = 1L;
		
		when(sessionFacadeIP.obtenerPddMRIndicadorPorId(indMetaResultado)).thenReturn(new PddMRIndicadorDTO());
		PddMRIndicadorDTO res = eliminar.eliminarIndicadorMetaResultado(indMetaResultado);
		assertNotNull(res);
		
		when(sessionFacadeIP.obtenerPddMRIndicadorPorId(indMetaResultado)).thenReturn(null);
		res = eliminar.eliminarIndicadorMetaResultado(indMetaResultado);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}


	@Test
	public void testEliminarPddNivelAtributo() throws Exception {
//		Long idAtributo = 1L;
//		PddNivelAtributoDTO peticionAux = new PddNivelAtributoDTO();
//		peticionAux.setIdAtributoPadre(idAtributo);
//		
//		
//		GenericoDTO pddNivelAtributosHijos = new GenericoDTO();
//		pddNivelAtributosHijos.setLstObjectsDTO(new ArrayList<Object>());
//				
//		when(sessionFacadeIP.consultarPddNivelAtributoPorId(idAtributo)).thenReturn(new PddNivelAtributoDTO());
//		when(sessionFacadeIP.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticionAux)).thenReturn(pddNivelAtributosHijos);
//		PddNivelAtributoDTO res = eliminar.eliminarPddNivelAtributo(idAtributo);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
//		
//		pddNivelAtributosHijos.getLstObjectsDTO().add(new Object());
//		res = eliminar.eliminarPddNivelAtributo(idAtributo);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
//		
//		when(sessionFacadeIP.consultarPddNivelAtributoPorId(idAtributo)).thenReturn(null);
//		res = eliminar.eliminarPddNivelAtributo(idAtributo);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	
	}

	
	@Test
	public void testEliminarMetaProducto() throws SpddExceptions {
		doNothing().when(sessionFacadeIP).eliminarMetaProducto(1L);
		assertThat(eliminar.eliminarMetaProducto(1L).getCodigoRespuesta()).isEqualTo(400);
		when(sessionFacadeIP.obtenerMetaProductoPorId(1L)).thenReturn(new PddMetaProductoDTO());
		assertThat(eliminar.eliminarMetaProducto(1L).getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testEliminarPddRangoPonderacion() throws SpddExceptions {
		doNothing().when(sessionFacadeIP).eliminarPddRangoPonderacion(1L);
		assertThat(eliminar.eliminarPddRangoPonderacion(1L).getCodigoRespuesta()).isEqualTo(400);
		when(sessionFacadeIP.obtenerPddRangoPonderacionPorId(1L)).thenReturn(new PddRangoPonderacionDTO());
		assertThat(eliminar.eliminarPddRangoPonderacion(1L).getCodigoRespuesta()).isEqualTo(200);
	}

	@Test
	public void testEliminarMpIndicador() throws Exception {
		doNothing().when(sessionFacadeIP).eliminarIndicadorMetaProducto(1L);
		assertThat(eliminar.eliminarMpIndicador(1L).getCodigoRespuesta()).isEqualTo(400);
	}

	
}
