package co.gov.sdp.spdd.core.service.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
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

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class InformacionPresupuestalPresupuestoRegistrar implements IInformacionPresupuestalPresupuestoRegistrar {

    // Servicio Repositorio de informacion presupuestal
    @Autowired
    IInformacionPresupuestalServiceRepo informacionPresupuestalServiceRepo;

    // Servicio Repositorio de archivo procesado
    @Autowired
    IArchivoProcesadoServiceRepo archivoProcesadoServiceRepo;

    // Mapper de informacion presupuestal
    @Autowired
    InformacionPresupuestalMapper informacionPresupuestalMapper;
    
    @Autowired
    ISessionFacadeIP sessionFacadeIP;
    
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

    /**
     * Implementacion del metodo crearInformacionPresupuestal
     * @throws JsonProcessingException 
     *
     * @see
     * co.gov.sdp.nhspdd.core.iservice.presupuesto.IArchivoProcesadoCargaRegistrar.crearInformacionPresupuestal
     */
    @Override
    public InformacionPresupuestalDTO crearInformacionPresupuestal(InformacionPresupuestalDTO peticion) throws SpddExceptions, JsonProcessingException {
    	InformacionPresupuestalDTO respuesta = new InformacionPresupuestalDTO();
    	
    	InformacionPresupuestal informacionPresupuestalAux = informacionPresupuestalServiceRepo.obtenerPorCodigoEntidadYMesYYear(peticion.getCodigoDistrital(), peticion.getMes(), peticion.getYear());
        
    	if(informacionPresupuestalAux == null)
    	{
    		ArchivoProcesado archivoProcesado;
	    	if(peticion.getIdArchivo() != null)
	    	{
	    		archivoProcesado = archivoProcesadoServiceRepo.obtener(peticion.getIdArchivo());
	    	}
	    	else
	    	{
	    		archivoProcesado = new ArchivoProcesado();
	    	}    	
	        
	    	if (peticion.getIdArchivo() != null && (archivoProcesado == null || archivoProcesado.getIdArchivo() == null)) {
	           
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ARCHIVO_PROCESADO_NO_ENCONTRADO,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	        }
	    	
	    	
	    	
	    	if(peticion.getCodigoN1Aux() == null || peticion.getCodigoN2Aux() == null || peticion.getCodigoN3Aux() == null ||
        			"".equals(peticion.getCodigoN1Aux()) || "".equals(peticion.getCodigoN2Aux()) || "".equals(peticion.getCodigoN3Aux()))
	    	{
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELES,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	    	}
	    	
	    	PddDTO pddVigente = sessionFacadeIP.consultarPddVigente();
	    	
	    	if(pddVigente.getIdPlanDesarrollo() == null)
	    	{
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_PDD_VIGENTE_INEXISTENTE,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	    	}
	    	
        	Long codigoNivel1 = Long.parseLong(peticion.getCodigoN1Aux());
	    	PddNivelAtributoDTO pddNivelAtributo1 = sessionFacadeIP.consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(codigoNivel1,pddVigente.getIdPlanDesarrollo());
	    	if(pddNivelAtributo1.getIdAtributo() == null)
	    	{
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_1,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	    	}
	    	
	    	Long codigoNivel2 = Long.parseLong(peticion.getCodigoN2Aux());
	    	PddNivelAtributoDTO pddNivelAtributo2 = sessionFacadeIP.consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(codigoNivel2, 2L, pddNivelAtributo1.getIdAtributo());
	    	if(pddNivelAtributo2.getIdAtributo() == null)
	    	{
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_2,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	    	}
	    	
	    	Long codigoNivel3 = Long.parseLong(peticion.getCodigoN3Aux());
	    	PddNivelAtributoDTO pddNivelAtributo3 = sessionFacadeIP.consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(codigoNivel3, 3L, pddNivelAtributo2.getIdAtributo());
	    	if(pddNivelAtributo3.getIdAtributo() == null)
	    	{
	    		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
	            respuesta.setMsgRespuesta(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_3,
	                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
	            return respuesta;
	    	}
	    	
	    	peticion.setCodigoN3(pddNivelAtributo3.getIdAtributo());
    	
    		InformacionPresupuestal informacionPresupuestal = informacionPresupuestalMapper
                    .informacionPresupuestalDTOToEntity(peticion);
           
        	informacionPresupuestalServiceRepo.guardar(informacionPresupuestal);
            
        	respuesta = informacionPresupuestalMapper.informacionPresupuestalEntityToDTO(informacionPresupuestal);
            
        	respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
            respuesta.setMsgRespuesta(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_CORRECTO,
                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
            
            auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "AGREGAR_INFO_PRESUPUESTAL");
    	}
    	else
    	{           
            respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
            respuesta.setMsgRespuesta(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
                            PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
    	}   	
        
        return respuesta;
    }

}
