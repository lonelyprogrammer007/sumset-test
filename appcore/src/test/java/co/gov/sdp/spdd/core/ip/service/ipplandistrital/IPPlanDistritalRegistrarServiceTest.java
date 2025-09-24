package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
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
@SpringBootTest(classes = { IPPlanDistritalRegistrarService.class, IPPlanDistritalRegistrarService.class,
		SessionFacadeIP.class, SessionFacadeAFS.class })
public class IPPlanDistritalRegistrarServiceTest {

	@Autowired
	IPPlanDistritalRegistrarService iPPlanDistritalRegistrarService;

	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SessionFacadeAFS sessionFacadeAFS;

	@MockBean
	SPDDLogger spddLogger;

	@MockBean
	AuditoriaUtil auditoriaUtil;

	@MockBean
	ObjectMapper objectMapper;

	@Test
	public void testRegistrarPdd() throws SpddExceptions, JsonProcessingException {
		PddDTO peticion = new PddDTO();
		ArgumentoListaSimpleDTO  argumentoLsNiveles = new ArgumentoListaSimpleDTO();
		argumentoLsNiveles.setIdArgumento(1L);
		
		PddDTO respuesta = new PddDTO();
		respuesta.setIdPlanDesarrollo(1L);
		
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_NUMERO_NIVELES_2, NHSPDDConstantes.LS_NIVELES_PDD)).thenReturn(argumentoLsNiveles);
		when(sessionFacadeIP.guardarPdd(peticion)).thenReturn(respuesta);
		PddDTO res = iPPlanDistritalRegistrarService.registrarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		respuesta.setIdPlanDesarrollo(null);
		res = iPPlanDistritalRegistrarService.registrarPdd(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarPddNivel() throws SpddExceptions, JsonProcessingException {
		PddNivelDTO peticion = new PddNivelDTO();
		peticion.setIdPlanDesarrollo(1L);		
		peticion.setCodNivelConcatenados("2");
		peticion.setDescripcionConcatenados("des");
		peticion.setObligatorioPdlConcatenados("0");
		
		PddDTO pddDTOAuxiliar = new PddDTO();
		pddDTOAuxiliar.setIdLsEstadoPlan(1L);
		
		ArgumentoListaSimpleDTO argumentoVigente = new ArgumentoListaSimpleDTO();
		argumentoVigente.setIdArgumento(2L);
		
		ArgumentoListaSimpleDTO argumentoFinalizado = new ArgumentoListaSimpleDTO();
		argumentoFinalizado.setIdArgumento(1L);
		
		PddNivelDTO pddNivelDTO = new PddNivelDTO();
		pddNivelDTO.setIdPlanDesarrollo(2L);
		
		PddNivelDTO pddNivelDTOauxiliar = new PddNivelDTO();
		pddNivelDTOauxiliar.setIdPddNivel(5L);
		
		when(sessionFacadeIP.consultarPddPorID(peticion.getIdPlanDesarrollo())).thenReturn(pddDTOAuxiliar);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoVigente);
		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_FINALIZADO, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoFinalizado);
		GenericoDTO res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_NIVEL_ESTADO_FINALIZADO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		//when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultado(NHSPDDConstantes.PDD_ESTADO_FINALIZADO)).thenReturn(argumentoVigente);
		pddDTOAuxiliar.setIdLsEstadoPlan(2L);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_PDD_NIVEL_ESTADO_VIGENTE, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		peticion.setIdPddNivelConcatenados("1");
		pddDTOAuxiliar.setIdLsEstadoPlan(3L);
		when(sessionFacadeIP.consultarPddNivelPorID(1L)).thenReturn(pddNivelDTO);
		when(sessionFacadeIP.modificarPddNivel(new PddNivelDTO(1L, 2L, "des", 0L, peticion.getIdPlanDesarrollo()))).thenReturn(pddNivelDTOauxiliar);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVELES_CORRECTO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		when(sessionFacadeIP.modificarPddNivel(new PddNivelDTO(1L, 2L, "des", 0L, peticion.getIdPlanDesarrollo()))).thenReturn(null);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVELES_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		when(sessionFacadeIP.consultarPddNivelPorID(1L)).thenReturn(null);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PDD_NIVELES_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
	
		peticion.setIdPddNivelConcatenados(null);
		when(sessionFacadeIP.guardarPddNivel(new PddNivelDTO(null, 2L, "des", 0L, peticion.getIdPlanDesarrollo()))).thenReturn(pddNivelDTOauxiliar);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVELES_CORRECTO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		when(sessionFacadeIP.guardarPddNivel(new PddNivelDTO(null, 2L, "des", 0L, peticion.getIdPlanDesarrollo()))).thenReturn(null);
		res = iPPlanDistritalRegistrarService.registrarPddNivel(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_PDD_NIVELES_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		
		
	}

	@Test
	public void testRegistrarPddNivelAtributo() throws SpddExceptions, JsonProcessingException {
		PddNivelAtributoDTO peticion =  new PddNivelAtributoDTO();
		peticion.setDenominacion("den");
		peticion.setIdPddNivel(1L);		
		
		PddNivelAtributoDTO pddNivelAtributoDTOAux = new PddNivelAtributoDTO();		
		
		PddNivelAtributoDTO respuesta = new PddNivelAtributoDTO();
		respuesta.setIdAtributo(1L);
		
		when(sessionFacadeIP.consultarPddNivelAtributoPorDenominacionYIdPddNivel(peticion.getDenominacion(),peticion.getIdPddNivel())).thenReturn(pddNivelAtributoDTOAux);
		when(sessionFacadeIP.guardarPddNivelAtributo(peticion)).thenReturn(respuesta);
		PddNivelAtributoDTO res = iPPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdAtributo(null);
		res = iPPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		pddNivelAtributoDTOAux.setIdAtributo(1L);
		res = iPPlanDistritalRegistrarService.registrarPddNivelAtributo(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_YA_EXISTENTE, PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

	}

	@Test
	public void testRegistrarPdl() throws SpddExceptions {
		ArgumentoListaSimpleDTO argumentoListaSimpleDTO = new ArgumentoListaSimpleDTO();
		argumentoListaSimpleDTO.setIdArgumento(2L);
		argumentoListaSimpleDTO.setResultado("Vigente");

		PddDTO pddDTO = new PddDTO();
		pddDTO.setIdPlanDesarrollo(3L);
		
		PdlDTO peticion = new PdlDTO();
		peticion.setIdPlanLocal(1L);
		peticion.setIdLsEstadoPlan(argumentoListaSimpleDTO.getIdArgumento());
		peticion.setCodigoEntidad("Secretaria de movilidad");
		peticion.setIdPlanDesarrollo(pddDTO.getIdPlanDesarrollo());

		PdlDTO respuesta = new PdlDTO();
		respuesta.setIdPlanLocal(1L);
		respuesta.setIdLsEstadoPlan(argumentoListaSimpleDTO.getIdArgumento());
		respuesta.setCodigoEntidad("Secretaria de movilidad");
		respuesta.setIdPlanDesarrollo(pddDTO.getIdPlanDesarrollo());

		List<Object> lst = new ArrayList<>();
		lst.add(pddDTO);

		GenericoDTO genericoDTO = new GenericoDTO();
		genericoDTO.setLstObjectsDTO(lst);

		when(sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD)).thenReturn(argumentoListaSimpleDTO);
		
		when(sessionFacadeIP.consultarPddsPorEstado(argumentoListaSimpleDTO.getIdArgumento())).thenReturn(genericoDTO);
		
		when(sessionFacadeIP.guardarPdl(peticion)).thenReturn(respuesta);

		PdlDTO res = iPPlanDistritalRegistrarService.registrarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		respuesta.setIdPlanDesarrollo(null);
		res = iPPlanDistritalRegistrarService.registrarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		

		when(sessionFacadeIP.consultarPddsPorEstado(argumentoListaSimpleDTO.getIdArgumento())).thenReturn(new GenericoDTO());

		res = iPPlanDistritalRegistrarService.registrarPdl(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarIndicadorMetaResultado() throws SpddExceptions {
		PddIndicadorDTO peticion = new PddIndicadorDTO();
		peticion.setIdMetaResultado(1L);
		
		PddMRIndicadorDTO respuesta = new PddMRIndicadorDTO();
		
		PddIndicadorDTO indicador = new PddIndicadorDTO();
		indicador.setIdIndicador(1L);
		
		PddMRIndicadorDTO auxiliar = new PddMRIndicadorDTO();
		auxiliar.setIdMetaResultado(peticion.getIdMetaResultado());
		auxiliar.setIdIndicador(indicador.getIdIndicador());
		
		when(sessionFacadeIP.obtenerPddIndicadorMetaResultado(peticion)).thenReturn(indicador);
		when(sessionFacadeIP.validarIndicadorMetaResultado(auxiliar)).thenReturn(auxiliar);
		PddMRIndicadorDTO res = iPPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeIP.validarIndicadorMetaResultado(auxiliar)).thenReturn(null);
		when(sessionFacadeIP.guardarMetaIndicador(auxiliar)).thenReturn(new PddMRIndicadorDTO());
		res = iPPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		indicador.setIdIndicador(null);
		auxiliar = new PddMRIndicadorDTO();
		auxiliar.setIdMetaResultado(peticion.getIdMetaResultado());
		auxiliar.setIdIndicador(indicador.getIdIndicador());
		when(sessionFacadeIP.guardarPddIndicador(peticion)).thenReturn(indicador);
		when(sessionFacadeIP.guardarMetaIndicador(auxiliar)).thenReturn(new PddMRIndicadorDTO());
		res = iPPlanDistritalRegistrarService.registrarIndicadorMetaResultado(peticion);
		assertNotNull(res);
		
	}

	@Test
	public void testRegistrarMetaProducto() throws SpddExceptions {
		PddMetaProductoDTO peticion = new PddMetaProductoDTO();
		when(sessionFacadeIP.guardarMetaProducto(peticion)).thenReturn(new PddMetaProductoDTO());
		assertThat(iPPlanDistritalRegistrarService.registrarMetaProducto(peticion).getCodigoRespuesta()).isEqualTo(200);

	}

	@Test
	public void testRegistrarPdlNivelAtributo() throws SpddExceptions {
		
	}


	@Test
	public void testRegistrarMetaProductoIndicador() throws Exception {
//		PddIndicadorDTO peticion = new PddIndicadorDTO();
//		peticion.setIdMetaProducto(1L);
//		
//		PddMpIndicadorDTO respuesta = new PddMpIndicadorDTO();
//		
//		PddIndicadorDTO indicador = new PddIndicadorDTO();
//		indicador.setIdIndicador(1L);
//		
//		PddMpIndicadorDTO auxiliar = new PddMpIndicadorDTO();
//		auxiliar.setIdIndicador(indicador.getIdIndicador());
//		auxiliar.setIdMetaProducto(peticion.getIdMetaProducto());
//		auxiliar.setEsPdd(0L);
//		
//		PddMpIndicadorDTO res;
//		
//		when(sessionFacadeIP.obtenerPddIndicadorMetaResultado(peticion)).thenReturn(indicador);
//		when(sessionFacadeIP.validarMetaProductoIndicador(auxiliar)).thenReturn(auxiliar);		
//		res = iPPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
//		
//		
//		when(sessionFacadeIP.validarMetaProductoIndicador(auxiliar)).thenReturn(null);
//		when(sessionFacadeIP.guardarIndicadorMetaProducto(auxiliar)).thenReturn(new PddMpIndicadorDTO());
//		res = iPPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
//		
//		indicador.setIdIndicador(null);
//		auxiliar = new PddMpIndicadorDTO();
//		auxiliar.setIdIndicador(indicador.getIdIndicador());
//		auxiliar.setIdMetaProducto(peticion.getIdMetaProducto());
//		auxiliar.setEsPdd(0L);
//		when(sessionFacadeIP.guardarPddIndicador(peticion)).thenReturn(indicador);
//		when(sessionFacadeIP.guardarIndicadorMetaProducto(auxiliar)).thenReturn(new PddMpIndicadorDTO());
//		res = iPPlanDistritalRegistrarService.registrarMetaProductoIndicador(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200);		

	}


	@Test
	public void testCopiarEstructuraPddToPdl() throws Exception {
		/*
		PdlDTO peticion = new PdlDTO();
		peticion.setIdPlanDesarrollo(1L);
		
		GenericoDTO genericoNivelesPdd = new GenericoDTO();
		genericoNivelesPdd.setLstObjectsDTO(new ArrayList<Object>());
		
		TypeReference<List<PddNivelDTO>> type = new TypeReference<List<PddNivelDTO>>() {
		};
		
		List<PddNivelDTO> pddNivelesDTO = new ArrayList<PddNivelDTO>();
		
		when(sessionFacadeIP.consultarPddNivelPorIdPlanDesarrollo(peticion.getIdPlanDesarrollo())).thenReturn(genericoNivelesPdd);
		when(objectMapper.convertValue(genericoNivelesPdd.getLstObjectsDTO(), type)).thenReturn(pddNivelesDTO);
		
		GenericoDTO res = iPPlanDistritalRegistrarService.copiarEstructuraPddToPdl(peticion);
		*/
	
	}

	@Test
	public void testRegistrarMpEntidad() throws Exception {
		PddMpIndicadorEntidadDTO peticion = new PddMpIndicadorEntidadDTO();
		peticion.setCodigoEntidad("0141");
		peticion.setIdMetaProdInd(1L);
		
		PddMpIndicadorEntidadDTO auxiliar = new PddMpIndicadorEntidadDTO();
		
		when(sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),	peticion.getIdMetaProdInd())).thenReturn(auxiliar);
		PddMpIndicadorEntidadDTO res = iPPlanDistritalRegistrarService.registrarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		when(sessionFacadeIP.validarMpIndicadorEntidad(peticion.getCodigoEntidad(),	peticion.getIdMetaProdInd())).thenReturn(null);
		when(sessionFacadeIP.registrarMpIndicadorEntidad(peticion)).thenReturn(new PddMpIndicadorEntidadDTO());
		res = iPPlanDistritalRegistrarService.registrarMpEntidad(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
	}


}
