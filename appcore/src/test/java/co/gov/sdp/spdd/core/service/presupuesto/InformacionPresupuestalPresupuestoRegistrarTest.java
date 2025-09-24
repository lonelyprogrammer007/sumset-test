package co.gov.sdp.spdd.core.service.presupuesto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.presupuesto.IInformacionPresupuestalPresupuestoRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArchivoProcesadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IInformacionPresupuestalServiceRepo;
import co.gov.sdp.spdd.data.mapper.InformacionPresupuestalMapper;
import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.InformacionPresupuestal;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InformacionPresupuestalPresupuestoRegistrar.class,
		IInformacionPresupuestalPresupuestoRegistrar.class })
class InformacionPresupuestalPresupuestoRegistrarTest {
	
	@Autowired
	IInformacionPresupuestalPresupuestoRegistrar registrar;
	
	@MockBean
	IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

	// Servicio Repositorio de archivo procesado
	@MockBean
	IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

	// Mapper de informacion presupuestal
	@MockBean
	InformacionPresupuestalMapper informacionPresupuestalMapper;
	
	@MockBean
    ISessionFacadeIP sessionFacadeIP;

	@MockBean
	AuditoriaUtil auditoria;

	@Test
	void testCrearInformacionPresupuestal() throws SpddExceptions, JsonProcessingException {
		
		InformacionPresupuestalDTO peticion = new InformacionPresupuestalDTO();
		peticion.setIdArchivo(1L);
		peticion.setCodigoN3Aux("0001");
		InformacionPresupuestalDTO res;
		ArchivoProcesado archivoProcesado = new ArchivoProcesado();
		archivoProcesado.setIdArchivo(1L);
		
		when(informacionPresupuestalServiceRepo.obtenerPorCodigoEntidadYMesYYear(peticion.getCodigoDistrital(), peticion.getMes(), peticion.getYear())).thenReturn(null);
		when(archivoProcesadoServiceRepo.obtener(peticion.getIdArchivo())).thenReturn(null);
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ARCHIVO_PROCESADO_NO_ENCONTRADO,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		
		when(archivoProcesadoServiceRepo.obtener(peticion.getIdArchivo())).thenReturn(archivoProcesado);
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELES,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		InformacionPresupuestal info = new InformacionPresupuestal();
		info.setIdInfoPresupuestal(1L);
		PddDTO pddVigente = new PddDTO();		
		peticion.setCodigoN1Aux("01");
		peticion.setCodigoN2Aux("01");
		peticion.setCodigoN3Aux("01");
		when(sessionFacadeIP.consultarPddVigente()).thenReturn(pddVigente);
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_VIGENTE_INEXISTENTE,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		PddNivelAtributoDTO pddNivelAtributo1 = new PddNivelAtributoDTO();
		pddVigente.setIdPlanDesarrollo(1L);
		Long codigoNivel1 = Long.parseLong(peticion.getCodigoN1Aux());
		when(sessionFacadeIP.consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(codigoNivel1,pddVigente.getIdPlanDesarrollo())).thenReturn(pddNivelAtributo1);
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_1,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		pddNivelAtributo1.setIdAtributo(1L);
		Long codigoNivel2 = Long.parseLong(peticion.getCodigoN2Aux());
    	PddNivelAtributoDTO pddNivelAtributo2 = new PddNivelAtributoDTO();
    	when(sessionFacadeIP.consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(codigoNivel2, 2L, pddNivelAtributo1.getIdAtributo())).thenReturn(pddNivelAtributo2);
    	res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_2,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
		pddNivelAtributo2.setIdAtributo(1L);
		Long codigoNivel3 = Long.parseLong(peticion.getCodigoN2Aux());
    	PddNivelAtributoDTO pddNivelAtributo3 = new PddNivelAtributoDTO();
    	when(sessionFacadeIP.consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(codigoNivel3, 3L, pddNivelAtributo2.getIdAtributo())).thenReturn(pddNivelAtributo3);
    	res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_3,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());

		pddNivelAtributo3.setIdAtributo(1L);
		peticion.setCodigoN3(pddNivelAtributo3.getIdAtributo());
		when(informacionPresupuestalMapper.informacionPresupuestalDTOToEntity(peticion)).thenReturn(info);
		when(informacionPresupuestalMapper.informacionPresupuestalEntityToDTO(info)).thenReturn(new InformacionPresupuestalDTO());
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(200);
		
		when(informacionPresupuestalServiceRepo.obtenerPorCodigoEntidadYMesYYear(peticion.getCodigoDistrital(), peticion.getMes(), peticion.getYear())).thenReturn(new InformacionPresupuestal());
		res = registrar.crearInformacionPresupuestal(peticion);
		assertNotNull(res);
		assertThat(res.getCodigoRespuesta()).isEqualTo(400L);
		assertEquals(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
				PaqueteMensajeEnum.MENSAJES, null), res.getMsgRespuesta());
		
	}

}
