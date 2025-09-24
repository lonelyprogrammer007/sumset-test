package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP
 * Capítulos 10, 11, 14, 15, 16 y 17. Permite que el controlador pueda utilizar
 * los servicios de eliminar
 * 
 * @author DANIEL
 * @version 1.0 02/03/2020
 */
@Service
public class IPPlanDistritalEliminarService implements IIPPlanDistritalEliminarService {

	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	AuditoriaUtil auditoria;

	@Override
	public PddMetaResultadoDTO eliminarMetaResultado(Long idMetaResultado) throws SpddExceptions {
		PddMetaResultadoDTO respuesta = sessionFacadeIP.consultarPddMetaResultadoPorId(idMetaResultado);
		PddMRIndicadorDTO peticion = new PddMRIndicadorDTO();
		peticion.setIdMetaResultado(idMetaResultado);
		GenericoDTO indicadoresResultado = sessionFacadeIP.consultarIndicadoresMetaResultado(peticion);
		if (respuesta != null) {
			if (indicadoresResultado.getLstObjectsDTO().isEmpty()) {
				if (respuesta.getEsFormulacion() == 1) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_RESULTADO_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
				} else {
					sessionFacadeIP.eliminarMetaResultado(idMetaResultado);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_RESULTADO_CORRECTO,
									PaqueteMensajeEnum.MENSAJES, null));
				}
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_RESULTADO_CON_ASOCIACION_INCORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta = new PddMetaResultadoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddMRIndicadorDTO eliminarIndicadorMetaResultado(Long indMetaResultado) throws SpddExceptions {
		PddMRIndicadorDTO respuesta = sessionFacadeIP.obtenerPddMRIndicadorPorId(indMetaResultado);
		if (respuesta != null) {
			sessionFacadeIP.eliminarIndicadorMetaResultado(indMetaResultado);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_RESULTADO_INDICADOR_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddMRIndicadorDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public PddMetaProductoDTO eliminarMetaProducto(Long idMetaProducto) throws SpddExceptions {
		PddMetaProductoDTO respuesta = sessionFacadeIP.obtenerMetaProductoPorId(idMetaProducto);
		if (respuesta != null) {
			sessionFacadeIP.eliminarMetaProducto(idMetaProducto);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_META_PRODUCTO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddMetaProductoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddMpIndicadorDTO eliminarMpIndicador(Long idMpIndicador) throws SpddExceptions {
		PddMpIndicadorDTO respuesta = sessionFacadeIP.obtenerIndicadorMp(idMpIndicador);
		if (respuesta != null) {
			sessionFacadeIP.eliminarIndicadorMetaProducto(idMpIndicador);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_INDICADOR_META_PRODUCTO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
					null));

		} else {
			respuesta = new PddMpIndicadorDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	public PddNivelAtributoDTO eliminarPddNivelAtributo(Long idAtributo)
			throws SpddExceptions, JsonProcessingException {
		PddNivelAtributoDTO respuesta = sessionFacadeIP.consultarPddNivelAtributoPorId(idAtributo);
		if (respuesta != null) {
			
			GenericoDTO pddComponentesHijos;
			if(respuesta.getProyectoEstrategico().equals(0L))
			{
				PddNivelAtributoDTO peticionAux = new PddNivelAtributoDTO();
				peticionAux.setIdAtributoPadre(idAtributo);
				pddComponentesHijos = sessionFacadeIP.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticionAux);
			}
			else
			{
				PddMetaResultadoDTO peticionMRAux = new PddMetaResultadoDTO();
				peticionMRAux.setIdProyEstrategico(idAtributo);
				peticionMRAux.setPagina(0);
				peticionMRAux.setTamanioPagina(Integer.MAX_VALUE);
				pddComponentesHijos = sessionFacadeIP.consultarPddMetaResultado(peticionMRAux);
			}
			 
			if (pddComponentesHijos.getLstObjectsDTO().isEmpty()) {
				sessionFacadeIP.eliminarPddNivelAtributo(idAtributo);
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_NIVEL_ATRIBUTO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
						respuesta.getLenguaje(), null);
				// TODO: Habilitar Auditoria
				// auditoria.crearAuditoria("", "", "MODIFICAR_IP", "MODIFICAR");

			} else {
				respuesta = new PddNivelAtributoDTO();
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_REGISTROS_ASOCIADOS, PaqueteMensajeEnum.MENSAJES,
						respuesta.getLenguaje(), null);
			}
		} else {
			respuesta = new PddNivelAtributoDTO();
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES,
					respuesta.getLenguaje(), null);
		}

		return respuesta;
	}

	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el
	 * mensaje correspondiente en cada uno de los casos para la respuesta de las
	 * peticiones
	 * 
	 * @param respuesta             objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta       objeto de tipo Integer que representa el codigo
	 *                              de la respuesta
	 * @param msgRespuesta          String que representa el mensaje de respuesta
	 *                              que se va a retornar
	 * @param paqueteMensaje        objeto de tipo PaqueteMensajeEnum que representa
	 *                              el paquete donde se encuentra el mensaje
	 * @param lenguaje
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta,
			PaqueteMensajeEnum paqueteMensaje, String lenguaje, List<CampoValidacionDTO> getMsgCampoValidacion) {
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta, paqueteMensaje, lenguaje));

		respuesta.setLstCampoValidacion(getMsgCampoValidacion);

	}

	@Override
	public PddMpIndicadorEntidadDTO eliminarMpEntidad(Long idMpEntidad) throws SpddExceptions {

		PddMpIndicadorEntidadDTO respuesta = sessionFacadeIP.consultarIndicadorEntidadPorId(idMpEntidad);
		if (respuesta != null) {
			sessionFacadeIP.eliminarMpIndicadorEntidad(idMpEntidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_MP_ENTIDAD_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddMpIndicadorEntidadDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public PddRangoPonderacionDTO eliminarPddRangoPonderacion(Long idRango) throws SpddExceptions {
		PddRangoPonderacionDTO respuesta = sessionFacadeIP.obtenerPddRangoPonderacionPorId(idRango);
		if (respuesta != null) {
			sessionFacadeIP.eliminarPddRangoPonderacion(idRango);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_PDD_RANGO_PONDERACION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new PddRangoPonderacionDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

}
