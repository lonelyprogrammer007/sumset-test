package co.gov.sdp.spdd.core.ip.service.ippot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.impl.SessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SessionFacadeIP.class, IPPotRegistrarService.class,
		IIPPotIRegistrarService.class })
class IPPotRegistrarServiceTest {

	@Autowired
	IIPPotIRegistrarService registrarService;
	
	@MockBean
	SessionFacadeIP sessionFacadeIP;

	@MockBean
	SPDDLogger spddLogger;
	
	@MockBean
	AuditoriaUtil auditoria;
	

	@Test
	void testRegistrarPot() throws SpddExceptions {

//		PotDTO peticion = new PotDTO();
//		PotDTO res;
//		peticion.setCodigoPot("pot1");
//		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(new PotDTO());
//		res = registrarService.registrarPot(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
//		
//		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(null);
//		when(sessionFacadeIP.guardarPot(peticion)).thenReturn(peticion);
//		res = registrarService.registrarPot(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
//		
//		peticion.setIdPot(1L);
//		when(sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot())).thenReturn(null);
//		when(sessionFacadeIP.guardarPot(peticion)).thenReturn(peticion);
//		res = registrarService.registrarPot(peticion);
//		assertNotNull(res);
//		assertThat(res.getCodigoRespuesta()).isEqualTo(200);

	}


	@Test
	public void testRegistrarPotObra() throws Exception {
		PotObraDTO peticion = new PotObraDTO();
		peticion.setCodigoEntidadConcatenados("1");
		
		PotObraDTO respuesta = new PotObraDTO();
		respuesta.setIdObraProyPot(1L);
		
		PotObraEntidadDTO potObraEntidadDTO = new PotObraEntidadDTO();
		potObraEntidadDTO.setIdObraEntidad(1L);
		
		when(sessionFacadeIP.guardarPotObra(peticion)).thenReturn(respuesta);
		when(sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, "1", respuesta.getIdObraProyPot()))).thenReturn(new PotObraEntidadDTO());
		PotObraDTO res = registrarService.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		respuesta.setCodigoRespuesta(null);
		when(sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, "1", respuesta.getIdObraProyPot()))).thenReturn(potObraEntidadDTO);
		res = registrarService.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
		
		when(sessionFacadeIP.guardarPotObra(peticion)).thenReturn(new PotObraDTO());
		res = registrarService.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
		respuesta.setCodigoRespuesta(null);
		when(sessionFacadeIP.guardarPotObra(peticion)).thenReturn(respuesta);
		when(sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, "1", respuesta.getIdObraProyPot()))).thenThrow(new SpddExceptions());
		res = registrarService.registrarPotObra(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()), res.getMsgRespuesta());
	
	}

	@Test
	public void testRegistrarPotInstrumento() throws Exception {
		PotInstrumentoDTO peticion = new PotInstrumentoDTO();
		
		PotInstrumentoDTO respuesta = new PotInstrumentoDTO();
		respuesta.setIdInstrumentoPot(1L);
		
		when(sessionFacadeIP.guardarPotInstrumento(peticion)).thenReturn(respuesta);
		PotInstrumentoDTO res = registrarService.registrarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		respuesta.setIdInstrumentoPot(null);
		res = registrarService.registrarPotInstrumento(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400);
		
	}

}
