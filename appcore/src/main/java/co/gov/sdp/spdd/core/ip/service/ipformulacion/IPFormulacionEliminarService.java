package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP.
 * Permite que el controlador pueda utilizar los servicios de eliminar
 * 
 * @author DANIEL
 * @version 1.0 27/02/2020
 */
@Service
public class IPFormulacionEliminarService implements IIPFormulacionEliminarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Override
	public PddMetaDTO eliminarPddMeta(Long id) throws SpddExceptions {
		PddMetaDTO respuesta = sessionFacadeIP.consultarPddMetaPorId(id);
		GenericoDTO resultado = sessionFacadeIP.consultarObrasConcretasPorMetas(id);
		if (respuesta != null) {
			if (resultado.getLstObjectsDTO().isEmpty()) {
				sessionFacadeIP.elimanrPDDMeta(id);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_INCORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta = new PddMetaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public PddObraConcretaDTO eliminarPddObraConcreta(Long id) throws SpddExceptions {

		PddObraConcretaDTO respuesta = sessionFacadeIP.consultarObraConcretaPorId(id);
		if (respuesta != null) {

			sessionFacadeIP.eliminarPddObraConcreta(id);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_OBRA_CONCRETA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new PddObraConcretaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public PddCompromisoEspecificoDTO eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico)
			throws SpddExceptions {
		PddCompromisoEspecificoDTO respuesta = sessionFacadeIP
				.consultarPddCompromisoEspecificoPorID(idPddCompromisoEspecifico);
		if (respuesta != null) {
			GenericoDTO metasDTO = sessionFacadeIP.consultarMetasCompromisoEstrategico(idPddCompromisoEspecifico);
			if (metasDTO.getLstObjectsDTO().isEmpty()) {
				sessionFacadeIP.eliminarPddCompromisoEspecifico(idPddCompromisoEspecifico);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_COMPROMISO_ESPECIFICO_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta = new PddCompromisoEspecificoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddPrbPoblacionDTO eliminarPrbPoblacion(Long idPoblacion) throws SpddExceptions {
		PddPrbPoblacionDTO respuesta = sessionFacadeIP.consultarPrbPoblacionPorId(idPoblacion);
		if (respuesta != null) {
			sessionFacadeIP.eliminarPrbPolacion(idPoblacion);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_PBR_POBLACION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddPrbPoblacionDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddPrbIndicadorDTO eliminarPrbIndicador(Long idProbInd) throws SpddExceptions {
		PddPrbIndicadorDTO respuesta = sessionFacadeIP.consultarPrbIndicadorPorId(idProbInd);
		if (respuesta != null) {
			sessionFacadeIP.eliminarPrbIndicador(idProbInd);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELMINAR_PDD_PRB_INDICADOR_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddPrbIndicadorDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

}
