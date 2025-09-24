package co.gov.sdp.spdd.core.ip.service.ippot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;

@Service
public class IPPotConsultarService implements IIPPotIConsultarService {

	/**
	 * inyeccion del Session Facade del modulo ip
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;

	@Autowired
	HttpServletRequest request;

	@Autowired
	SPDDLogger spddLogger;

	@Override
	public GenericoDTO potObtenerPaginado(PotDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPotPorFiltro(peticion);
		mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_CORRECTO, PaqueteMensajeEnum.MENSAJES, null, null);
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion);
		String token = request.getHeader("Authorization");
		entidad.agregarToken(token.substring(6, token.length()));
		TypeReference<List<PotObraDTO>> typeListaPotObraDTO = new TypeReference<List<PotObraDTO>>() {
		};
		List<PotObraDTO> listaPotObraDTO = objectMapper.convertValue(respuesta.getLstObjectsDTO(), typeListaPotObraDTO);

		if (listaPotObraDTO != null && !listaPotObraDTO.isEmpty()) {
			listaPotObraDTO.stream().forEach(potObraDTOAux -> {
				StringBuilder codigosEntidadesConcatenado = new StringBuilder("");
				StringBuilder nombresEntidadesConcatenado = new StringBuilder("");
				String separado = "";

				GenericoDTO genericoPotObraEntidad;
				try {
					genericoPotObraEntidad = sessionFacadeIP
							.consultarTodosPotObraEntidadPorIdPotObra(potObraDTOAux.getIdObraProyPot());

					TypeReference<List<PotObraEntidadDTO>> typeListaPotObraEntidadDTO = new TypeReference<List<PotObraEntidadDTO>>() {
					};
					List<PotObraEntidadDTO> listaPotObraEntidadDTO = objectMapper
							.convertValue(genericoPotObraEntidad.getLstObjectsDTO(), typeListaPotObraEntidadDTO);
					for (PotObraEntidadDTO potObraEntidadDTOAux : listaPotObraEntidadDTO) {
						codigosEntidadesConcatenado
								.append(String.format("%s%s", separado, potObraEntidadDTOAux.getCodigoEntidad()));
						// TODO: Aqui se le haria la peticion para traer el nombre de la entidad por el
						// codigo y se la setiaria a la variable nombresEntidadesConcatenado

						RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
								NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
										+ potObraEntidadDTOAux.getCodigoEntidad(),
								new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
								});
						nombresEntidadesConcatenado.append(
								String.format("%s%s", separado, entidades.getObjetos().get(0).getCodigoEntidad()));
						separado = ";";
					}
					potObraDTOAux.setCodigoEntidadConcatenados(codigosEntidadesConcatenado.toString());
					potObraDTOAux.setNombreEntidadConcatenados(nombresEntidadesConcatenado.toString());
				} catch (SpddExceptions e) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_OBRA_INCORRECTO, PaqueteMensajeEnum.MENSAJES,
							null, null);
				}
			});

			if (respuesta.getCodigoRespuesta() != null
					&& respuesta.getCodigoRespuesta().equals(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO)) {
				return respuesta;
			}

			respuesta.setLstObjectsDTO(new ArrayList<>(listaPotObraDTO));
		}
		mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_OBRA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null, null);
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPotInstrumentoFiltrado(PotInstrumentoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPotInstrumentoFiltrado(peticion);
		mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_INTRUMENTO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null,
				null);
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
	public GenericoDTO consultarTodosRamaPotPorIdPot(Long idPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPotRamaPorIdPot(idPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_RAMA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerPotRamaPorIdPotNumeroRamaDesc(idPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_RAMA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosNivelPotPorIdRamaPot(idRamaPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosNivelPotPorIdRamaPotDesc(idRamaPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosNivelPotPorIdNivel(idNivelPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdNivelDesc(Long idNivelPot) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosNivelPotPorIdNivelDesc(idNivelPot);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPotObraPorPot(PotObraDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPotObra(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

}
