package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos de modificar asociados a consecutivo
 *
 * @author SumSet 2019
 *
 */
@Service
public class ConsecutivoModificar implements IConsecutivoModificar {

	// Servicio que permite utilizar las operaciones basicas de la bd
	@Autowired
	IConsecutivoServiceRepo consecutivoServiceRepo;

	// Mapper que permite convertir un dto a entidad y viceversa
	@Autowired
	ConsecutivoMapper consecutivoMapper;
	
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion de modificarConsecutivo
	 * @throws JsonProcessingException 
	 */
	@Override
	public ConsecutivoDTO modificarConsecutivo(ConsecutivoDTO peticion) throws SpddExceptions, JsonProcessingException {
		if(peticion.getCodigoEntidad().equals(NHSPDDConstantes.CODIGO_ENTIDAD_DE_ENTIDAD_NO_APLICA))
		{
			peticion.setCodigoEntidad(null);
		}
		
		Consecutivo consecutivo = consecutivoServiceRepo.obtener(peticion.getIdConsecutivo());
		Consecutivo consecutivoAux = consecutivoServiceRepo.obtenerConsecutivos(peticion.getIdPlanDesarrollo(), peticion.getCodigoEntidad(), peticion.getNombre());
		ConsecutivoDTO respuesta = new ConsecutivoDTO();
		if (consecutivo != null) {
			if(consecutivoAux != null && consecutivoAux.getIdConsecutivo().equals(consecutivo.getIdConsecutivo()))
			{
				consecutivo = consecutivoMapper.consecutivoDTOTOEntity(peticion);
				consecutivoServiceRepo.guardar(consecutivo);
				respuesta = consecutivoMapper.consecutivoEntityToDTO(consecutivo);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_CONSECUTIVO_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "ACTUALIZAR_AFS", "CONSULTAR_EDITAR_CONSECUTIVOS");
			}
			else
			{
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

}
