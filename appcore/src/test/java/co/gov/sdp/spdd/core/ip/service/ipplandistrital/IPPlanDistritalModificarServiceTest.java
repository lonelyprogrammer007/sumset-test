package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IPPlanDistritalModificarService.class, IPPlanDistritalModificarService.class,
		SessionFacadeIP.class, SessionFacadeAFS.class })
public class IPPlanDistritalModificarServiceTest {

	@Autowired
	IPPlanDistritalModificarService iPPlanDistritalModificarService;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoriaUtil;

	@Test
	void testModificarPdd() throws SpddExceptions, JsonProcessingException {
		PddDTO peticion = new PddDTO();
		peticion.setIdPlanDesarrollo(1L);
		
		PddDTO pddDTO = new PddDTO();
		pddDTO.setIdPlanDesarrollo(1L);
		pddDTO.setVersion("0");
		
		ArgumentoListaSimpleDTO argumentoFormlacion = new ArgumentoListaSimpleDTO();
		argumentoFormlacion.setIdArgumento(1L);
		
		ArgumentoListaSimpleDTO argumentoVigente = new ArgumentoListaSimpleDTO();
		argumentoVigente.setIdArgumento(2L);
		
		ArgumentoListaSimpleDTO argumentoFinalizado = new ArgumentoListaSimpleDTO();
		argumentoFinalizado.setIdArgumento(3L);
		
		when(sessionFacadeIP.consultarPddPorID(peticion.getIdPlanDesarrollo())).thenReturn(pddDTO);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FORMULACION, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoFormlacion);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoVigente);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FINALIZADO, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoFinalizado);
				
		pddDTO.setIdLsEstadoPlan(argumentoFinalizado.getIdArgumento());
		PddDTO res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_ESTADO_FINALIZADO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		pddDTO.setIdLsEstadoPlan(argumentoVigente.getIdArgumento());
		peticion.setIdLsEstadoPlan(argumentoFormlacion.getIdArgumento());
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_DEVOLVER_ESTADO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

		pddDTO.setIdLsEstadoPlan(argumentoFormlacion.getIdArgumento());
		peticion.setIdLsEstadoPlan(argumentoVigente.getIdArgumento());
		GenericoDTO respuestaPddVigente = new GenericoDTO();
		respuestaPddVigente.setLstObjectsDTO(new ArrayList<Object>());
		respuestaPddVigente.getLstObjectsDTO().add(new Object());
		when(sessionFacadeIP.consultarPddsPorEstado(argumentoVigente.getIdArgumento())).thenReturn(respuestaPddVigente);
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_ESTADO_VIGENTE, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		GenericoDTO genericoPddNiveles = new GenericoDTO();
		genericoPddNiveles.setLstObjectsDTO(new ArrayList<Object>());		
		peticion.setIdLsEstadoPlan(argumentoFinalizado.getIdArgumento());
		when(sessionFacadeIP.consultarPddNivelPorIdPlanDesarrollo(peticion.getIdPlanDesarrollo())).thenReturn(genericoPddNiveles);
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_MODIFICAR_SIN_NIVELES, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

		peticion.setIdLsEstadoPlan(argumentoFormlacion.getIdArgumento());		
		when(sessionFacadeIP.modificarPdd(peticion)).thenReturn(new PddDTO());
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		pddDTO.setIdPlanDesarrollo(5L);
		PddDTO respuesta = new PddDTO();
		respuesta.setIdPlanDesarrollo(1L);
		when(sessionFacadeIP.guardarPdd(peticion)).thenReturn(respuesta);
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdPlanDesarrollo(null);
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_ESTADO_FORMULACION, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		when(sessionFacadeIP.consultarPddPorID(peticion.getIdPlanDesarrollo())).thenReturn(null);
		res = iPPlanDistritalModificarService.modificarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
	}

	@Test
	public void testModificarPdl() throws SpddExceptions {
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		argumentoListaSimpleDTO.setIdArgumento(1L);
		argumentoListaSimpleDTO.setResultado(NHSPDDConstantes.PDD_ESTADO_VIGENTE);

		PdlDTO peticion = new PdlDTO();
		peticion.setIdPlanLocal(1L);
		peticion.setIdLsEstadoPlan(argumentoListaSimpleDTO.getIdArgumento());

		PdlDTO respuesta = new PdlDTO();
		respuesta.setIdPlanLocal(1L);
		respuesta.setIdLsEstadoPlan(argumentoListaSimpleDTO.getIdArgumento());
		
		PdlDTO auxiliar = new PdlDTO();
		auxiliar.setIdPlanLocal(1L);
		

		when(sessionFacadeIP.consultarPlanDesarrolloLocalPorId(peticion.getIdPlanLocal())).thenReturn(auxiliar);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoListaSimpleDTO);
		when(sessionFacadeIP.modificarPdl(peticion)).thenReturn(respuesta);

		PdlDTO res = iPPlanDistritalModificarService.modificarPdl(peticion);

		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		respuesta.setIdPlanLocal(null);
		res = iPPlanDistritalModificarService.modificarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		auxiliar.setIdPlanLocal(3L);
		respuesta.setIdPlanLocal(1L);
		when(sessionFacadeIP.guardarPdl(peticion)).thenReturn(respuesta);
		res = iPPlanDistritalModificarService.modificarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdPlanLocal(null);
		res = iPPlanDistritalModificarService.modificarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);		

		when(sessionFacadeIP.consultarPlanDesarrolloLocalPorId(peticion.getIdPlanLocal())).thenReturn(null);

		res = iPPlanDistritalModificarService.modificarPdl(peticion);

		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarMetaProducto() throws SpddExceptions {
		PddMetaProductoDTO peticion = new PddMetaProductoDTO();
		peticion.setIdMetaProducto(1L);
		
		PddMetaProductoDTO auxiliar = new PddMetaProductoDTO();
		
		when(sessionFacadeIP.obtenerMetaProductoPorId(peticion.getIdMetaProducto())).thenReturn(auxiliar);
		when(sessionFacadeIP.guardarMetaProducto(peticion)).thenReturn(new PddMetaProductoDTO());
		PddMetaProductoDTO res = iPPlanDistritalModificarService.modificarMetaProducto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(sessionFacadeIP.obtenerMetaProductoPorId(peticion.getIdMetaProducto())).thenReturn(null);
		res = iPPlanDistritalModificarService.modificarMetaProducto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	
	
	}

	@Test
	public void testModificarPddRangoPonderacion() throws Exception {
//		PddRangoPonderacionDTO peticion = new PddRangoPonderacionDTO();
//		peticion.setIdRango(1L);
//		PddRangoPonderacionDTO respuesta = new PddRangoPonderacionDTO();
//		respuesta.setIdRango(1L);
//		when(sessionFacadeIP.obtenerPddRangoPonderacionPorId(1L)).thenReturn(peticion);
//		when(sessionFacadeIP.modificarPddRangoPonderacion(peticion)).thenReturn(respuesta);
//		PddRangoPonderacionDTO res = iPPlanDistritalModificarService.modificarPddRangoPonderacion(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
//		when(sessionFacadeIP.modificarPddRangoPonderacion(new PddRangoPonderacionDTO())).thenThrow(new SpddExceptions());
//		res = iPPlanDistritalModificarService.modificarPddRangoPonderacion(new PddRangoPonderacionDTO());
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testModificarPddNivelAtributo() throws Exception {
		PddNivelAtributoDTO peticion = new PddNivelAtributoDTO();
		peticion.setIdAtributo(1L);
		peticion.setNumero(1L);
		peticion.setIdPddNivel(1L);
		peticion.setIdAtributoPadre(1L);
		
		PddNivelAtributoDTO pddNivelAtributoDTO = new PddNivelAtributoDTO();
		pddNivelAtributoDTO.setIdAtributo(1L);
		
		PddNivelAtributoDTO pddNivelAtributoDTOAux = new PddNivelAtributoDTO();
		pddNivelAtributoDTOAux.setIdAtributo(1L);
		
		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO(); 
		
		when(sessionFacadeIP.consultarPddNivelAtributoPorId(peticion.getIdAtributo())).thenReturn(pddNivelAtributoDTO);
		when(sessionFacadeIP.modificarPddNivelAtributo(peticion)).thenReturn(respuesta);
		when(sessionFacadeIP.consultarPddNivelAtributoPorNumeroYIdPddNivel(peticion.getNumero(), peticion.getIdPddNivel(), peticion.getIdAtributoPadre())).thenReturn(pddNivelAtributoDTOAux);
		PddNivelAtributoDTO res = iPPlanDistritalModificarService.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		pddNivelAtributoDTOAux.setIdAtributo(2L);
		respuesta.setIdAtributo(1L);
		when(sessionFacadeIP.guardarPddNivelAtributo(peticion)).thenReturn(respuesta);
		res = iPPlanDistritalModificarService.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdAtributo(null);
		res = iPPlanDistritalModificarService.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		when(sessionFacadeIP.consultarPddNivelAtributoPorId(peticion.getIdAtributo())).thenReturn(null);
		res = iPPlanDistritalModificarService.modificarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

	
	}

	@Test
	public void testModificarMpEntidad() throws Exception {
		PddMpIndicadorEntidadDTO peticion = new PddMpIndicadorEntidadDTO();
		peticion.setIdMpIndEntidad(1L);
		peticion.setCodigoEntidad("0141");
		peticion.setIdMetaProdInd(1L);
		
		PddMpIndicadorEntidadDTO respuesta = new PddMpIndicadorEntidadDTO();
		respuesta.setIdMpIndEntidad(1L);
		
		PddMpIndicadorEntidadDTO auxiliar = new PddMpIndicadorEntidadDTO();
		auxiliar.setIdMpIndEntidad(1L);;
		
		when(sessionFacadeIP.consultarIndicadorEntidadPorId(peticion.getIdMpIndEntidad())).thenReturn(respuesta);
		when(sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),	peticion.getIdMetaProdInd())).thenReturn(auxiliar);
		when(sessionFacadeIP.registrarMpIndicadorEntidad(peticion)).thenReturn(new PddMpIndicadorEntidadDTO());
		PddMpIndicadorEntidadDTO res = iPPlanDistritalModificarService.modificarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdMpIndEntidad(2L);
		res = iPPlanDistritalModificarService.modificarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		when(sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),	peticion.getIdMetaProdInd())).thenReturn(null);
		res = iPPlanDistritalModificarService.modificarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(sessionFacadeIP.consultarIndicadorEntidadPorId(peticion.getIdMpIndEntidad())).thenReturn(null);
		res = iPPlanDistritalModificarService.modificarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}

}
