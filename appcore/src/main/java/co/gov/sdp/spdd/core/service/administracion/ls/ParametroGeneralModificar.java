package co.gov.sdp.spdd.core.service.administracion.ls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.ls.IParametroGeneralModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IParametroGeneralServiceRepo;
import co.gov.sdp.spdd.data.mapper.ParametroGeneralMapper;
import co.gov.sdp.spdd.data.model.afs.ParametroGeneral;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos asociados a modificar de parametro general
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ParametroGeneralModificar implements IParametroGeneralModificar {

	// Servicio que proporciona metodos de bd
	@Autowired
	IParametroGeneralServiceRepo parametroGeneralServiceRepo;

	// Servicio que permite convertir un dto a entidad y viceversa
	@Autowired
	ParametroGeneralMapper mapper;

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
	 * Implementacion del metodo editarParametro
	 * @throws JsonProcessingException 
	 *
	 * @see
	 */
	@Override
	public ParametroGeneralDTO editarParametro(ParametroGeneralDTO peticion) throws SpddExceptions, JsonProcessingException {
		ParametroGeneral entidad = parametroGeneralServiceRepo.obtener(peticion.getCodigoParametro());
		ParametroGeneralDTO respuesta = new ParametroGeneralDTO();
		if (entidad != null) {
			entidad = mapper.parametroDTOToEntity(peticion);
			parametroGeneralServiceRepo.guardar(entidad);
			respuesta = mapper.parametroEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_PARAMETRO_GENERAL_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "ACTUALIZAR_AFS", "CONSULTAR_EDITAR_PARAMETROS");
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}

		return respuesta;
	}

}
