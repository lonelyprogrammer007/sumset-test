package co.gov.sdp.spdd.core.ip.service.ippot;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ippot.IIPPotIRegistrarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@Service
public class IPPotRegistrarService implements IIPPotIRegistrarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	AuditoriaUtil auditoria;
	
	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;	
	
	@Override
	public PotDTO registrarPot(PotDTO peticion) throws SpddExceptions {
		PotDTO respuesta = sessionFacadeIP.obtenerPotCodigo(peticion.getCodigoPot());
		GenericoDTO listaPOTVigente = sessionFacadeIP.consultarTodosPOTPorEstado(NHSPDDConstantes.POT_ESTADO_VIGENTE);
		if(respuesta!=null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_INCORRECTO, 
					PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
		}		
		else {
			
			Calendar fechaActual = Calendar.getInstance();
			if(Integer.parseInt(peticion.getYearFin()) < fechaActual.get(Calendar.YEAR))
			{
				peticion.setEstado(NHSPDDConstantes.POT_ESTADO_FINALIZADO);
			}
			
			if (!listaPOTVigente.getLstObjectsDTO().isEmpty() && peticion.getEstado().equals(NHSPDDConstantes.POT_ESTADO_VIGENTE)) {
				respuesta = new PotDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_INCORRECTO_VIGENTE, 
						PaqueteMensajeEnum.ERRORES, respuesta.getLenguaje()));
			}
			else
			{
				respuesta = sessionFacadeIP.guardarPot(peticion);
				if(respuesta.getIdPot()!=null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_EXITOSO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}else {
					respuesta = new PotDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}
			}			
		}
		
		
		return respuesta;
	}

	@Override

	public PotRamaDTO registrarRamaPot(PotRamaDTO peticion) throws SpddExceptions {
		GenericoDTO lista = sessionFacadeIP.obtenerPotRamaPorIdPotNumeroRamaDesc(peticion.getIdPot());
		PotRamaDTO respuesta = new PotRamaDTO();
		if(lista.getLstObjectsDTO().isEmpty()) {
			peticion.setNumeroRama(1L);
			respuesta = sessionFacadeIP.guardarRamaPot(peticion);
			if(respuesta.getIdPotRama() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_RAMA_CORRECTO, 
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
			}else {
				respuesta = new PotRamaDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
			}
		}else {
			PotRamaDTO aux = (PotRamaDTO) lista.getLstObjectsDTO().get(0);
			Long numeroRama = aux.getNumeroRama()+1;
			
			peticion.setNumeroRama(numeroRama);
			respuesta = sessionFacadeIP.guardarRamaPot(peticion);
			if(respuesta.getIdPotRama() != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_RAMA_CORRECTO, 
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
			}else {
				respuesta = new PotRamaDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
						PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
			}
		}
		return respuesta;
	}

	@Override
	public PotNivelDTO registrarPotNivel(PotNivelDTO peticion) throws SpddExceptions {
		GenericoDTO lista;
		PotNivelDTO respuesta = new PotNivelDTO();
		if(peticion.getIdNivelPadre()==null) {
			lista = sessionFacadeIP.obtenerTodosNivelPotPorIdRamaPotDesc(peticion.getIdRamaPot());
			if(lista.getLstObjectsDTO().isEmpty()) {
				peticion.setNumeroNivel(1L);
				respuesta = sessionFacadeIP.guardarNivelPot(peticion);
				if(respuesta.getIdNivelPot()!=null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}else {
					respuesta = new PotNivelDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}
				
			}else {
				PotNivelDTO aux = (PotNivelDTO) lista.getLstObjectsDTO().get(0);
				Long numeroNivel = aux.getNumeroNivel()+1;
				peticion.setNumeroNivel(numeroNivel);
				respuesta = sessionFacadeIP.guardarNivelPot(peticion);
				if(respuesta.getIdNivelPot()!=null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}else {
					respuesta = new PotNivelDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}
			}
		}else {
			lista = sessionFacadeIP.obtenerTodosNivelPotPorIdNivelDesc(peticion.getIdNivelPadre());
			if(lista.getLstObjectsDTO().isEmpty()) {
				peticion.setNumeroNivel(1L);
				respuesta = sessionFacadeIP.guardarNivelPot(peticion);
				if(respuesta.getIdNivelPot()!=null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}else {
					respuesta = new PotNivelDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}
				
			}else {
				PotNivelDTO aux = (PotNivelDTO) lista.getLstObjectsDTO().get(0);
				Long numeroNivel =aux.getNumeroNivel()+1;
				peticion.setNumeroNivel(numeroNivel);
				respuesta = sessionFacadeIP.guardarNivelPot(peticion);
				if(respuesta.getIdNivelPot()!=null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_POT_NIVEL_CORRECTO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}else {
					respuesta = new PotNivelDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO, 
							PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje()));
				}
			}
		}
		return respuesta;
	}

	public PotObraDTO registrarPotObra(PotObraDTO peticion) throws SpddExceptions, JsonProcessingException {
		PotObraDTO respuesta = sessionFacadeIP.guardarPotObra(peticion);
		if(respuesta.getIdObraProyPot() != null)
		{
			String[] codigosEntidades = peticion.getCodigoEntidadConcatenados().split(";");
			List<String> listaCodigosEntidades = Arrays.asList(codigosEntidades);
			listaCodigosEntidades.stream().forEach(codigoEntidad -> {
				try {
					PotObraEntidadDTO potObraEntidadDTO = sessionFacadeIP.guardarPotObraEntidad(new PotObraEntidadDTO(null, codigoEntidad.trim(), respuesta.getIdObraProyPot()));
					if(potObraEntidadDTO.getIdObraEntidad() == null)
					{
						mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
								NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
					}
					
				} catch (SpddExceptions e) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);					
				}
			});
			
			if(respuesta.getCodigoRespuesta() != null && respuesta.getCodigoRespuesta().equals(NHSPDDConstantes.RESPUESTA_EXITOSA))
			{
				return respuesta;
			}
			
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_CREAR_POT_OBRA_EXITOSO, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
			// TODO: Habilitar Auditoria
			//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "MODIFICAR_IP", "MODIFICAR");
		}
		else
		{
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
		}
		return respuesta;
	}
	
	@Override
	public PotInstrumentoDTO registrarPotInstrumento(PotInstrumentoDTO peticion) throws SpddExceptions, JsonProcessingException {
		PotInstrumentoDTO respuesta = sessionFacadeIP.guardarPotInstrumento(peticion);
		if(respuesta.getIdInstrumentoPot() != null)
		{
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_CREAR_POT_INSTRUMENTO_EXITOSO, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
			// TODO: Habilitar Auditoria
			//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "MODIFICAR_IP", "MODIFICAR");
		}
		else
		{
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE, PaqueteMensajeEnum.MENSAJES, respuesta.getLenguaje(),null);
		}
		return respuesta;
	}
	
	/**
	 * Metodo privado que permite setiar los valores necesarios para mostrar el mensaje correspondiente en cada 
	 * uno de los casos para la respuesta de las peticiones
	 * @param respuesta objeto al cual se le va a setiar el mensaje
	 * @param codigoRespuesta objeto de tipo Integer que representa el codigo de la respuesta
	 * @param msgRespuesta String que representa el mensaje de respuesta que se va a retornar
	 * @param paqueteMensaje objeto de tipo PaqueteMensajeEnum que representa el paquete donde se encuentra el mensaje
	 * @param lenguaje 
	 * @param getMsgCampoValidacion lista de mensaje de las validaciones
	 */
	private void mensajeRespuesta(RespuestaDTO respuesta, Integer codigoRespuesta, String msgRespuesta, PaqueteMensajeEnum paqueteMensaje, String lenguaje,List<CampoValidacionDTO> getMsgCampoValidacion)
	{
		respuesta.setCodigoRespuesta(codigoRespuesta);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(msgRespuesta,
				paqueteMensaje, lenguaje));
		
		respuesta.setLstCampoValidacion(getMsgCampoValidacion);
	}




}
