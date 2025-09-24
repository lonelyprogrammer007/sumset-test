package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IConsecutivoRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IConsecutivoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ConsecutivoMapper;
import co.gov.sdp.spdd.data.model.afs.Consecutivo;

/**
 * Clase que implementa los metodos de registrar asociados a consecutivo
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ConsecutivoRegistrar implements IConsecutivoRegistrar {

	// Servicio que permite utilizar las operaciones basicas de la bd
	@Autowired
	IConsecutivoServiceRepo consecutivoServiceRepo;
	
	// Mapper que permite convertir un dto a entidad y viceversa
	@Autowired
	ConsecutivoMapper consecutivoMapper;

	/**
	 * 
	 */
	@Override
	public ConsecutivoDTO registrarConsecutivo(ConsecutivoDTO peticion) throws SpddExceptions {
		Consecutivo entidad = consecutivoMapper.consecutivoDTOTOEntity(peticion);
		consecutivoServiceRepo.guardar(entidad);
		ConsecutivoDTO respuesta = consecutivoMapper.consecutivoEntityToDTO(entidad);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRAR_CONSECUTIVO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}
}
