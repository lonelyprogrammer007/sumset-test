package co.gov.sdp.spdd.core.ip.service.ippot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIModificarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeIP.class, IPPotModificarService.class,
		IIPPotIModificarService.class })
class IPPotModificarServiceTest {
	
	@Autowired
	IIPPotIModificarService modificarService;
	
	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;
	
	
	@Test
	void testModificarPot() throws SpddExceptions {
		PotDTO peticion = new PotDTO();
		PotDTO res;
		peticion.setIdPot(1L);
		peticion.setCodigoPot("pot1");
		PotDTO auxiliar = new PotDTO();
		auxiliar.setIdPot(1L);
		auxiliar.setCodigoPot("pot1");
		PotDTO entidad = new PotDTO();
		entidad.setIdPot(1L);
		entidad.setCodigoPot("pot1");
		when(sessionFacadeIP.obtenerPotPorId(peticion.getIdPot())).thenReturn(entidad);
		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(auxiliar);
		when(sessionFacadeIP.guardarPot(peticion)).thenReturn(peticion);
		res = modificarService.modificarPot(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		auxiliar.setCodigoPot("pot2");
		when(sessionFacadeIP.obtenerPotPorId(peticion.getIdPot())).thenReturn(entidad);
		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(auxiliar);
		when(sessionFacadeIP.guardarPot(peticion)).thenReturn(peticion);
		res = modificarService.modificarPot(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		peticion.setIdPot(null);
		when(sessionFacadeIP.obtenerPotPorId(peticion.getIdPot())).thenReturn(entidad);
		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(auxiliar);
		when(sessionFacadeIP.guardarPot(peticion)).thenReturn(peticion);
		res = modificarService.modificarPot(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		when(sessionFacadeIP.obtenerPotPorId(peticion.getIdPot())).thenReturn(entidad);
		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(null);
		res = modificarService.modificarPot(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}


	@Test
	public void testModificarPotObra() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		peticion.setIdObraProyPot(1L);
		peticion.setCodigoEntidadConcatenados("123");
		peticion.setIdNivelPot(1L);
		peticion.setCodigoEntidadConcatenados("1");
		
		PotObraDTO respuesta = new PotObraDTO();
		
		PotObraDTO potObraDTO = new PotObraDTO();
		potObraDTO.setIdObraProyPot(1L);
		
		PotObraDTO  potObraDTOAux= new PotObraDTO();
		potObraDTOAux.setIdObraProyPot(1L);
		
		PotObraEntidadDTO potObraEntidadDTO = new PotObraEntidadDTO();
		
		when(sessionFacadeIP.consultarPotObraPorId(peticion.getIdObraProyPot())).thenReturn(potObraDTO);
		when(sessionFacadeIP.consultarPotObraPorCodigoYIdNivelPot(peticion.getCodigoPotObra(), peticion.getIdNivelPot())).thenReturn(potObraDTOAux);
		doNothing().when(sessionFacadeIP).eliminarTodosPotObraEntidadPorIdPotObra(peticion.getIdObraProyPot());
		when(sessionFacadeIP.modificarPotObra(peticion)).thenReturn(respuesta);
		when(sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, "1", respuesta.getIdObraProyPot()))).thenReturn(potObraEntidadDTO);
		PotObraDTO res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());
		
		potObraEntidadDTO.setIdObraEntidad(1L);
		respuesta.setCodigoRespuesta(null);
		res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_OBRA_EXITOSO,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());

		potObraDTOAux.setIdObraProyPot(2L);
		respuesta.setIdObraProyPot(1L);
		potObraEntidadDTO.setIdObraEntidad(null);
		when(sessionFacadeIP.guardarPotObra(peticion)).thenReturn(respuesta);
		when(sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, "1", respuesta.getIdObraProyPot()))).thenReturn(potObraEntidadDTO);
		res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());
		
		potObraEntidadDTO.setIdObraEntidad(1L);
		respuesta.setCodigoRespuesta(null);
		res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_POT_OBRA_EXITOSO,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());

		respuesta.setIdObraProyPot(null);
		res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());
		
		when(sessionFacadeIP.consultarPotObraPorId(peticion.getIdObraProyPot())).thenReturn(null);
		res = modificarService.modificarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
	}


	@Test
	public void testModificarPotInstrumento() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
		peticion.setIdInstrumentoPot(1L);
		peticion.setCodigoPotInstrumento(1L);
		peticion.setIdPot(1L);
		
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		
		PotInstrumentoDTO potInstrumentoDTO = new PotInstrumentoDTO();
		potInstrumentoDTO.setIdInstrumentoPot(1L);
		
		PotInstrumentoDTO  potInstrumentoDTOAux= new PotInstrumentoDTO();
		potInstrumentoDTOAux.setIdInstrumentoPot(1L);
		
		when(sessionFacadeIP.consultarPotInstrumentoPorId(peticion.getIdInstrumentoPot())).thenReturn(potInstrumentoDTO);
		when(sessionFacadeIP.consultarPotInstrumentoPorCodigoYIdPot(peticion.getCodigoPotInstrumento(), peticion.getIdPot())).thenReturn(potInstrumentoDTOAux);
		when(sessionFacadeIP.modificarPotInstrumento(peticion)).thenReturn(respuesta);
		PotInstrumentoDTO res = modificarService.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		potInstrumentoDTOAux.setIdInstrumentoPot(2L);
		when(sessionFacadeIP.guardarPotInstrumento(peticion)).thenReturn(respuesta);
		res = modificarService.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());
		
		respuesta.setIdInstrumentoPot(1L);
		res = modificarService.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(sessionFacadeIP.consultarPotInstrumentoPorId(peticion.getIdInstrumentoPot())).thenReturn(null);
		res = modificarService.modificarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
				PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()), res.getMsgRespuesta());
	}

}
