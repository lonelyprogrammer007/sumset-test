package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
<<<<<<< HEAD
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
=======
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
>>>>>>> developer
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvModificarService;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.util.AuditoriaUtil;

@Service
public class BPProyInvModificarService implements IBPProyInvModificarService {

	@Autowired
	ISessionFacadeBP sessionFacadeBP;

<<<<<<< HEAD
	@Autowired
	ObjectMapper objectMapper;

=======
>>>>>>> developer
	@Autowired
	AuditoriaUtil auditoria;

	@Override
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(BpAporteCiudadanoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		if (peticion.getIdNivelAtributoPddOpcion3() != null) {
			peticion.setIdNivelAtributoPdd(peticion.getIdNivelAtributoPddOpcion3());
		} else {
			peticion.setIdNivelAtributoPdd(peticion.getIdNivelAtributoPddOpcion2());
		}

		BpAporteCiudadanoDTO respuesta = new BpAporteCiudadanoDTO();
		BpAporteCiudadanoDTO bpAporteCiudadanoDTO = sessionFacadeBP
				.consultarBpAporteCiudadanoPorId(peticion.getIdAporte());
		BpAporteCiudadanoDTO bpAporteCiudadanoDTOAux = sessionFacadeBP
				.consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(peticion.getAccion(), peticion.getFuente(),
						peticion.getIdNivelAtributoPdd());
		if (bpAporteCiudadanoDTO != null && bpAporteCiudadanoDTO.getIdAporte() > 0) {
			if (bpAporteCiudadanoDTOAux != null
					&& peticion.getIdAporte().equals(bpAporteCiudadanoDTOAux.getIdAporte())) {

				respuesta = sessionFacadeBP.modificarBpAporteCiudadano(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_BP_APORTE_CIUDADANO_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
				// "CREAR_IP", "CREAR");

			} else {
				respuesta = sessionFacadeBP.guardarBPAporteCiudadano(peticion);
				if (respuesta != null && respuesta.getIdAporte() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_BP_APORTE_CIUDADANO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
					// "CREAR_IP", "CREAR");
				} else {
					respuesta = new BpAporteCiudadanoDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
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
	public BpProyectoInversionDTO modificarProyectoInversion(BpProyectoInversionDTO peticion) throws SpddExceptions {
		// Se actualiza la version
<<<<<<< HEAD
		peticion.setVersion(peticion.getVersion() + 1L);
		// peticion.setFechaVersion(fechaVersion);
=======

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		peticion.setVersion(peticion.getVersion()+1L);
		peticion.setFechaVersion(formatter.format(new Date()));
		
>>>>>>> developer

		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		BpProyectoInversionDTO auxiliar = sessionFacadeBP.consultarProyInvPorId(peticion.getIdProyInversion());
		if (auxiliar != null && auxiliar.getIdProyInversion() > 0) {
			if (auxiliar.getIdProyInversion() == peticion.getIdProyInversion()) {
				respuesta = sessionFacadeBP.modificarProyectoInversionProyecto(peticion);
				if (respuesta.getIdProyInversion() != null) {
					
					sessionFacadeBP.eliminarBpProyInvTiposDeIdProyectoInversion(respuesta.getIdProyInversion());

					String[] listaIdsTipos = peticion.getIdsTipoProy().split(";");
					for (String idTipoAux : listaIdsTipos) {
						BpProyInvTipoDTO bpProyInvTipoDTO = sessionFacadeBP.guardarProyInvTipo(new BpProyInvTipoDTO(null,
								Long.parseLong(idTipoAux.trim()), respuesta.getIdProyInversion()));
						
						if (bpProyInvTipoDTO != null && bpProyInvTipoDTO.getIdTipo() != null) {
							// auditoria.crearAuditoria(peticion.toString(), peticion.toString(),
							// "CREAR_IP", "CREAR");
						}

					}
					
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(), null);
				} else {
					respuesta = new BpProyectoInversionDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(), null);
				}
			} else {
				respuesta = sessionFacadeBP.guardarProyectoInversionIndentificacionProyecto(peticion);
				if (respuesta.getIdProyInversion() != null) {
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_CORRECTO, PaqueteMensajeEnum.MENSAJES,
							peticion.getLenguaje(), null);
				} else {
					respuesta = new BpProyectoInversionDTO();
					mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_CREAR_BP_PROYECTO_INVERSION_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(), null);
				}
			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje(),
					null);
		}
		return respuesta;
	}

	@Override
<<<<<<< HEAD
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		
		BpProyInvPoblacionDTO AuxObtencionRegistrosTablaSuperior = new BpProyInvPoblacionDTO();
		AuxObtencionRegistrosTablaSuperior.setIdProyInversion(peticion.getIdProyInversion());

		//Validacion existencia grupo etario
		BpProyInvPoblacionDTO auxDtoModificador = new BpProyInvPoblacionDTO();
		try {
			auxDtoModificador = sessionFacadeBP.consultarBpProyInvPoblacion(peticion);

			if (auxDtoModificador.getIdPoblacion() != null) {
				// el registro si existe
				auxDtoModificador.setNumero(peticion.getNumero());
			} else {
				mensajeRespuesta(peticion, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_NO_EXISTE, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);
				return peticion;
			}
			
			//Validacion registros asociados en la tabla inferior
			BpProyInvEtnicoDTO AuxObtencionRegistrosTablaInferior = new BpProyInvEtnicoDTO();
			AuxObtencionRegistrosTablaInferior.setIdPoblacion(peticion.getIdPoblacion());
			
			GenericoDTO listaTablaInferior = sessionFacadeBP
					.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(AuxObtencionRegistrosTablaInferior);	

			// transformar a lista
			TypeReference<List<BpProyInvEtnicoDTO>> type = new TypeReference<List<BpProyInvEtnicoDTO>>() {
			};
			
			List<BpProyInvEtnicoDTO> lista = objectMapper.convertValue(listaTablaInferior.getLstObjectsDTO(), type);
			
			for (BpProyInvEtnicoDTO item : lista) {
				if(item.getNumero() <= peticion.getNumero()) {
					//no se incumple la regla de negocio
				}else {
					//se rompe la regla de negocio
					mensajeRespuesta(auxDtoModificador, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
							NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_ERROR, PaqueteMensajeEnum.MENSAJES, auxDtoModificador.getLenguaje(),
							null);
					return auxDtoModificador;
				}
			}
			
			//La regla de negocio asociado al campo numero que se quiere modificar se cumple
			
			sessionFacadeBP.modificarBpProyInvPoblacion(auxDtoModificador);
			
			mensajeRespuesta(auxDtoModificador, NHSPDDConstantes.RESPUESTA_EXITOSA,
					NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_EXITOSO, PaqueteMensajeEnum.MENSAJES, auxDtoModificador.getLenguaje(),
					null);
		
		} catch (SpddExceptions e) {

			e.printStackTrace();
		}
		
		return auxDtoModificador;

	}

	@Override
	public BpProyInvEtnicoDTO modificarBpProyInvEtnico(BpProyInvEtnicoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		
		//validar si existe
		
		BpProyInvEtnicoDTO modificado = sessionFacadeBP.consultarBpProyInvEtnico(peticion);
		
		if(modificado.getIdEtnico() == null) {
			mensajeRespuesta(modificado, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_NO_EXISTE, PaqueteMensajeEnum.MENSAJES, modificado.getLenguaje(),
					null);
			return modificado;
		}
		
		//validar si existe el BpProyInvPoblacion asociado
		
		BpProyInvPoblacionDTO auxBpProyInvPoblacion = new BpProyInvPoblacionDTO();
		auxBpProyInvPoblacion.setIdPoblacion(modificado.getIdPoblacion());
		auxBpProyInvPoblacion = sessionFacadeBP.consultarBpProyInvPoblacion(auxBpProyInvPoblacion);
		
		if(auxBpProyInvPoblacion.getIdPoblacion() == null) {
			mensajeRespuesta(modificado, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_BP_PROY_INV_POBLACION_NO_EXISTE, PaqueteMensajeEnum.MENSAJES, modificado.getLenguaje(),
					null);
			return modificado;
		}
		
		//validar regla de negocio con el numero
		
		modificado.setNumero(peticion.getNumero());
		if(modificado.getNumero() <= auxBpProyInvPoblacion.getNumero()) {
			//no se rompe la regla de negocio
			
		}else {
			//se rompe la regla de negocio
			mensajeRespuesta(modificado, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_NUMERO_NO_VALIDO, PaqueteMensajeEnum.MENSAJES, modificado.getLenguaje(),
					null);
			return modificado;
		}
		
		//modificar registro
		
		modificado = sessionFacadeBP.guardarBpProyInvEtnico(modificado);
		mensajeRespuesta(modificado, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
				NHSPDDConstantes.MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_EXITOSO, PaqueteMensajeEnum.MENSAJES, modificado.getLenguaje(),
				null);
		
		return modificado;
	}
=======
	public BpProyInvFinanciaDTO modificarFuenteFinanciacion(BpProyInvFinanciaDTO peticion) throws SpddExceptions, JsonProcessingException {

		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();

		if (peticion.getIdFuente() != null) {
			BpProyInvFinanciaDTO bpProyInvFinanciaDTO = sessionFacadeBP
					.consultarProyInvFinanciaPorId(peticion.getIdFuente());

			if (bpProyInvFinanciaDTO != null && bpProyInvFinanciaDTO.getIdFuente() != null) {

				respuesta = sessionFacadeBP.modificarProyInvFinancia(peticion);
				
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_EXITOSA,
						NHSPDDConstantes.MENSAJE_MODIFICAR_CORRECTO_BP_PROY_INV_ANUALIZA, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);

			} else {
				mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
						NHSPDDConstantes.MENSAJE_MODIFICAR_ERROR_BP_PROY_INV_ANUALIZA, PaqueteMensajeEnum.MENSAJES,
						peticion.getLenguaje(), null);
				// si el idFuente esta vacio

				// si no encuentra el ProyInfFinancia

			}
		} else {
			mensajeRespuesta(respuesta, NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDConstantes.MENSAJE_MODIFICAR_ERROR_BP_PROY_INV_ANUALIZA, PaqueteMensajeEnum.MENSAJES,
					peticion.getLenguaje(), null);
			// si el idFuente esta vacio
		}

		return respuesta;

	}

	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(BpProyInvPoliticaDTO peticion) throws SpddExceptions, JsonProcessingException {		
		BpProyInvPoliticaDTO respuesta = new BpProyInvPoliticaDTO();
		BpProyInvPoliticaDTO bpProyInvPoliticaDTO = sessionFacadeBP.consultarBpProyInvPoliticaPorId(peticion.getIdProyPolitica());
		BpProyInvPoliticaDTO bpProyInvPoliticaDTOAux = sessionFacadeBP.consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(peticion.getIdPolPub(), peticion.getIdProyInversion());
		if (bpProyInvPoliticaDTO != null && bpProyInvPoliticaDTO.getIdProyPolitica() > 0) {			
			if (bpProyInvPoliticaDTOAux != null && peticion.getIdProyPolitica().equals(bpProyInvPoliticaDTOAux.getIdProyPolitica())) {
				
				respuesta = sessionFacadeBP.modificarBpProyInvPolitica(peticion);			
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");

			} else {
				respuesta = sessionFacadeBP.guardarBpProyInvPolitica(peticion);
				if (respuesta != null && respuesta.getIdProyPolitica() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");
				} else {
					respuesta = new BpProyInvPoliticaDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
			
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public BpProyInvLineaDTO modificarBpProyInvLinea(BpProyInvLineaDTO peticion) throws SpddExceptions, JsonProcessingException {
		BpProyInvLineaDTO respuesta = new BpProyInvLineaDTO();
		BpProyInvLineaDTO bpProyInvLineaDTO = sessionFacadeBP.consultarBpProyInvLineaPorId(peticion.getIdProyLinea());
		BpProyInvLineaDTO bpProyInvLineaDTOAux = sessionFacadeBP.consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(peticion.getIdLineaInversion(), peticion.getIdProyInversion());
		if (bpProyInvLineaDTO != null && bpProyInvLineaDTO.getIdProyLinea() > 0) {			
			if (bpProyInvLineaDTOAux != null && peticion.getIdProyLinea().equals(bpProyInvLineaDTOAux.getIdProyLinea())) {
				
				respuesta = sessionFacadeBP.modificarBpProyInvLinea(peticion);			
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");

			} else {
				respuesta = sessionFacadeBP.guardarBpProyInvLinea(peticion);
				if (respuesta != null && respuesta.getIdProyLinea() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");
				} else {
					respuesta = new BpProyInvLineaDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
			
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}

	@Override
	public BpProyInvPmrDTO modificarBpProyInvPmr(BpProyInvPmrDTO peticion) throws SpddExceptions, JsonProcessingException {
		BpProyInvPmrDTO respuesta = new BpProyInvPmrDTO();
		BpProyInvPmrDTO bpProyInvPmrDTO = sessionFacadeBP.consultarBpProyInvPmrPorId(peticion.getIdProyPmr());
		BpProyInvPmrDTO bpProyInvPmrDTOAux = sessionFacadeBP.consultarBpProyInvPmrPorIdIndMrYIdProyInversion(peticion.getIdIndMr(), peticion.getIdProyInversion());
		if (bpProyInvPmrDTO != null && bpProyInvPmrDTO.getIdProyPmr() > 0) {			
			if (bpProyInvPmrDTOAux != null && peticion.getIdProyPmr().equals(bpProyInvPmrDTOAux.getIdProyPmr())) {
				
				respuesta = sessionFacadeBP.modificarBpProyInvPmr(peticion);			
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");

			} else {
				respuesta = sessionFacadeBP.guardarBpProyInvPmr(peticion);
				if (respuesta != null && respuesta.getIdProyPmr() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					//auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_IP", "CREAR");
				} else {
					respuesta = new BpProyInvPmrDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
			
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
	

>>>>>>> developer

}
