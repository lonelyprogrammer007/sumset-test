package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeBP.class,IBPProyInvRegistrarService.class, BPProyInvRegistrarService.class})
class BPProyInvRegistrarServiceTest {
	
	@Autowired
	BPProyInvRegistrarService bPProyInvRegistrarService;
	
	@MockBean
	ISessionFacadeIP sessionFacadeIP;
	
	@MockBean
	ISessionFacadeBP sessionFacadeBP;
	
	@MockBean
	ISessionFacadeAFS sessionFacadeAFS;
	
	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;

	@Test
	void testRegistrarProyectoInversionIndentificacionProyecto() throws SpddExceptions, JsonProcessingException {
		BpProyectoInversionDTO peticion = new BpProyectoInversionDTO();
		peticion.setIdProyInversion(1L);
		peticion.setIdsTipoProy("1");
		
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		respuesta.setIdProyInversion(1L);
		
		BpProyInvTipoDTO bpProyInvTipoDTO = new BpProyInvTipoDTO();
		bpProyInvTipoDTO.setIdTipo(1L);
		
		PddDTO pddVigente = new PddDTO();
		pddVigente.setIdPlanDesarrollo(1L);
		
		when(sessionFacadeIP.consultarPddVigente()).thenReturn(pddVigente);
		when(sessionFacadeBP.guardarProyectoInversionIndentificacionProyecto(peticion)).thenReturn(respuesta);		
		when(sessionFacadeBP.guardarProyInvTipo(new BpProyInvTipoDTO(null, 1L, respuesta.getIdProyInversion()))).thenReturn(bpProyInvTipoDTO);
		doNothing().when(sessionFacadeBP).eliminarBpProyInvTiposDeIdProyectoInversion(respuesta.getIdProyInversion());
		BpProyectoInversionDTO res = bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		
		when(sessionFacadeBP.guardarProyectoInversionIndentificacionProyecto(peticion)).thenReturn(null);
		res = bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		pddVigente.setIdPlanDesarrollo(null);
		when(sessionFacadeIP.consultarPddVigente()).thenReturn(pddVigente);
		res = bPProyInvRegistrarService.registrarProyectoInversionIndentificacionProyecto(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}
	
	@Test
	void testRegistrarBpProyInvAporte() throws SpddExceptions, JsonProcessingException
	{
		BpProyInvAporteDTO peticion =  new BpProyInvAporteDTO();
		peticion.setIdProyAporte(1L);
		peticion.setIdsAportes("1; 1");
		
		BpProyInvAporteDTO respuestaAux = new BpProyInvAporteDTO();
		respuestaAux.setIdProyAporte(2L);
		
		BpProyInvAporteDTO respuestaAux2 = new BpProyInvAporteDTO();
		respuestaAux2.setIdProyAporte(null);
		
		when(sessionFacadeBP.guardarBpProyInvAporte(new BpProyInvAporteDTO(null, 1L, peticion.getIdProyInversion()))).thenReturn(respuestaAux);
		BpProyInvAporteDTO res = bPProyInvRegistrarService.registrarBpProyInvAporte(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		when(sessionFacadeBP.guardarBpProyInvAporte(new BpProyInvAporteDTO(null, 1L, peticion.getIdProyInversion()))).thenReturn(respuestaAux).thenReturn(respuestaAux2);
		res = bPProyInvRegistrarService.registrarBpProyInvAporte(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO_INCORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		when(sessionFacadeBP.guardarBpProyInvAporte(new BpProyInvAporteDTO(null, 1L, peticion.getIdProyInversion()))).thenReturn(respuestaAux2);
		res = bPProyInvRegistrarService.registrarBpProyInvAporte(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		peticion.setIdsAportes(null);
		res = bPProyInvRegistrarService.registrarBpProyInvAporte(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
	}

	@Test
	public void testRegistrarBPProyectoInvLocaliza() throws SpddExceptions {
		BpProyInvLocalizaYTerritorizacionDTO bpProyInvLocalizaYTerritorizacionDTO = new BpProyInvLocalizaYTerritorizacionDTO();
		BpProyInvLocalizaDTO bpProyInvLocalizaDTO = new BpProyInvLocalizaDTO();
		BpProyectoInversionDTO res = new BpProyectoInversionDTO();
		BpProyectoInversionDTO auxiliar = new BpProyectoInversionDTO();
		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();
		
		when(sessionFacadeBP.consultarProyInvPorId(1L)).thenReturn(null);
		res = bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(bpProyInvLocalizaYTerritorizacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeBP.consultarProyInvPorId(1L)).thenReturn(auxiliar);
		when(sessionFacadeBP.modificarProyectoInversionProyecto(auxiliar)).thenReturn(auxiliar);
		res = bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(bpProyInvLocalizaYTerritorizacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeBP.consultarProyInvPorId(1L)).thenReturn(auxiliar);
		when(sessionFacadeBP.modificarProyectoInversionProyecto(auxiliar)).thenReturn(auxiliar);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsVeredaYLsUpr(new TerritorializacionDTO())).thenReturn(null);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(new TerritorializacionDTO())).thenReturn(null);
		res = bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(bpProyInvLocalizaYTerritorizacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		
		when(sessionFacadeBP.consultarProyInvPorId(1L)).thenReturn(auxiliar);
		when(sessionFacadeBP.modificarProyectoInversionProyecto(auxiliar)).thenReturn(auxiliar);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsVeredaYLsUpr(new TerritorializacionDTO())).thenReturn(territorializacionDTO);
		when(sessionFacadeAFS.consultarTerritorializacionPorLsBarrioYLsUpzYLsLocalidad(new TerritorializacionDTO())).thenReturn(territorializacionDTO);
		when(sessionFacadeBP.guardarBPProyectoInvLocaliza(new BpProyInvLocalizaDTO(null, auxiliar.getIdProyInversion(), territorializacionDTO.getIdTerritorializacion()))).thenReturn(bpProyInvLocalizaDTO);
		res = bPProyInvRegistrarService.registrarBPProyectoInvLocaliza(bpProyInvLocalizaYTerritorizacionDTO);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
	}

	@Test
	public void testRegistrarBPAporteCiudadano() throws Exception {
		BpAporteCiudadanoDTO peticion = new BpAporteCiudadanoDTO();
		peticion.setIdNivelAtributoPddOpcion3(1L);
		peticion.setIdProyInversion(1L);
		
		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		respuesta.setIdAporte(1L);
		
		when(sessionFacadeBP.guardarBPAporteCiudadano(peticion)).thenReturn(respuesta);
		//doNothing().when(sessionFacadeBP).guardarBpProyInvAporte(new BpProyInvAporteDTO(null, respuesta.getIdAporte(), peticion.getIdProyInversion()));
		BpAporteCiudadanoDTO res = bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		peticion.setIdNivelAtributoPddOpcion3(null);
		peticion.setIdNivelAtributoPddOpcion2(1L);
		when(sessionFacadeBP.guardarBPAporteCiudadano(peticion)).thenReturn(null);
		res = bPProyInvRegistrarService.registrarBPAporteCiudadano(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);

	}

	@Test
	public void testRegistrarBpProyInvIniciativa() throws Exception {
		BpProyInvIniciativaDTO peticion = new BpProyInvIniciativaDTO();
		peticion.setIdsIniciativasConcatenadas("1;1");
		peticion.setIdProyInversion(1L);
		
		BpProyInvIniciativaDTO respuestaAux = new BpProyInvIniciativaDTO();
		respuestaAux.setIdIniciativaInv(1L);
		
		BpProyInvIniciativaDTO respuestaAux2 = new BpProyInvIniciativaDTO();
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		
		when(sessionFacadeBP.guardarBpProyInvIniciativa(new BpProyInvIniciativaDTO(null, Long.parseLong("1") , peticion.getIdProyInversion(),formateador.format(new Date())))).thenReturn(respuestaAux);
		BpProyInvIniciativaDTO res = bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		
		when(sessionFacadeBP.guardarBpProyInvIniciativa(new BpProyInvIniciativaDTO(null, Long.parseLong("1") , peticion.getIdProyInversion(),formateador.format(new Date())))).thenReturn(respuestaAux,respuestaAux2);
		res = bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA_INCORRECTA,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		when(sessionFacadeBP.guardarBpProyInvIniciativa(new BpProyInvIniciativaDTO(null, Long.parseLong("1") , peticion.getIdProyInversion(),formateador.format(new Date())))).thenReturn(respuestaAux2);
		res = bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_INCORRECTA,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		peticion.setIdsIniciativasConcatenadas(null);
		res = bPProyInvRegistrarService.registrarBpProyInvIniciativa(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_INICIATIVA_IDS_INICIATIVAS_CONCATENADAS_VACIO,
				PaqueteMensajeEnum.ERRORES, peticion.getLenguaje()), res.getMsgRespuesta());
	}

	@Test
	public void testRegistrarBpProyInvEspecif() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvMetaPlan() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvProducto() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvActividad() throws Exception {
	}

	@Test
	public void testRegistrarBpProyInvFinancia() throws Exception {
		
		BpProyInvFinanciaDTO peticion = new BpProyInvFinanciaDTO();
		peticion.setIdProyInversion(2L);
		peticion.setIdsLsfuentes("1;2;3");
		peticion.setMontoAnio1(2.2);
		peticion.setMontoAnio2(2.2);
		peticion.setMontoAnio3(2.2);
		peticion.setMontoAnio4(2.2);
		peticion.setMontoAnio5(2.2);
		peticion.setVigencia1(2010l);
		peticion.setVigencia2(2011l);
		peticion.setVigencia3(2012l);
		peticion.setVigencia4(2013l);
		peticion.setVigencia4(2014l);
		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		BpProyectoInversionDTO auxBpProyectoInversionDTO = new BpProyectoInversionDTO();
		auxBpProyectoInversionDTO.setIdProyInversion(1L);
		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO2 = new BpProyInvFinanciaDTO();
		auxBpProyInvFinanciaDTO2.setIdFuente(2l);
		
		when(sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion())).thenReturn(auxBpProyectoInversionDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(1L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(2L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(3L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.registrarBpProyInvFinancia(peticion)).thenReturn(auxBpProyInvFinanciaDTO2);
		BpProyInvFinanciaDTO res = bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		
		when(sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion())).thenReturn(auxBpProyectoInversionDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(1L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(2L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(3L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO2);
		//when(sessionFacadeBP.registrarBpProyInvFinancia(peticion)).thenReturn(auxBpProyInvFinanciaDTO2);
		res = bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		
		when(sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion())).thenReturn(auxBpProyectoInversionDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(1L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(2L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		when(sessionFacadeBP.consultarProyInvFinanciaPorIdLSFuYIdProy(new Long(3L), peticion.getIdProyInversion())).thenReturn(auxBpProyInvFinanciaDTO);
		auxBpProyInvFinanciaDTO2.setIdFuente(null);
		when(sessionFacadeBP.registrarBpProyInvFinancia(peticion)).thenReturn(auxBpProyInvFinanciaDTO2);
		res = bPProyInvRegistrarService.registrarBpProyInvFinancia(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		
	}
		

	public void testRegistrarBpProyInvPoblacionAsociadoaProyInv() throws Exception {
		
		when(sessionFacadeBP.consultarBpProyInvPoblacionUnicidad(new BpProyInvPoblacionDTO())).thenReturn(new BpProyInvPoblacionDTO());

	}

}
