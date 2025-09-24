package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
<<<<<<< HEAD
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
=======
>>>>>>> developer

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
<<<<<<< HEAD
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
=======
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
>>>>>>> developer
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvEliminarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

@Service
public class BPProyInvEliminarService implements IBPProyInvEliminarService {

	@Autowired
	ISessionFacadeBP sessionFacadeBP;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public BpProyInvAporteDTO eliminarBpProyInvAporte(Long idProyAporte) throws SpddExceptions {
		BpProyInvAporteDTO respuesta = sessionFacadeBP.consultarProyInvAportePorId(idProyAporte);
		if (respuesta != null) {

			// Elimino BpProyInvAporte (relacion entre Proyecto Inversion y Aporte
			// Ciudadano)
			sessionFacadeBP.eliminarBpProyInvAporte(idProyAporte);

			// Elimino el Aporte Ciudadano que se encontraba en la relacion anterior si no
			// es un aporte cargado por archivo
			BpAporteCiudadanoDTO bpAporteAux = sessionFacadeBP.consultarBpAporteCiudadanoPorId(respuesta.getIdAporte());
			if (bpAporteAux.getIdArchivo().equals(-1L)) {
				sessionFacadeBP.eliminarBpAporteCiudadano(respuesta.getIdAporte());
			}

			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_OBRA_CONCRETA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new BpProyInvAporteDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public BpProyectoInversionDTO eliminarBpProyectoInversion(Long idProyectoInversion) throws SpddExceptions {
		BpProyectoInversionDTO respuesta = new BpProyectoInversionDTO();
		BpProyectoInversionDTO bpProyectoInversionDTO = sessionFacadeBP.consultarProyInvPorId(idProyectoInversion);

		
		if(bpProyectoInversionDTO != null && bpProyectoInversionDTO.getIdProyInversion() != null)
		{
			ArgumentoListaSimpleDTO argumentoEstadoSinInscripcion = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.BP_PROYECTO_INV_ESTADO_SIN_INSCRIPCION, NHSPDDConstantes.LS_ESTADOS_PROYECTO_INVERSION);

			if(bpProyectoInversionDTO.getIdLsEstado().equals(argumentoEstadoSinInscripcion.getIdArgumento()))
			{
				ArgumentoListaSimpleDTO argumentoEstadoEliminado = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.BP_PROYECTO_INV_ESTADO_ELIMINADO, NHSPDDConstantes.LS_ESTADOS_PROYECTO_INVERSION);

				bpProyectoInversionDTO.setIdLsEstado(argumentoEstadoEliminado.getIdArgumento());
				respuesta = sessionFacadeBP.modificarProyectoInversionProyecto(bpProyectoInversionDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_CORRECTO,
								PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_INCORRECTO, PaqueteMensajeEnum.MENSAJES,
						null));

			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;

	}

	@Override
	public BpProyInvFinanciaDTO eliminarProyInvFinanciaPorId(Long idFuente) throws SpddExceptions {

		BpProyInvFinanciaDTO respuesta = new BpProyInvFinanciaDTO();

		BpProyInvFinanciaDTO bpProyInvFinanciaDTO = sessionFacadeBP.consultarProyInvFinanciaPorId(idFuente);

		if (bpProyInvFinanciaDTO.getIdFuente() != null) {

			sessionFacadeBP.eliminarTodosProyInvAnualizaPorIdFuente(idFuente);
			sessionFacadeBP.eliminarProyInvFinanciaPorId(idFuente);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_FUENTE_FINANCIACION_ELIMINADA, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_FUENTE_FINANCIACION_NO_ENCONTRADA, PaqueteMensajeEnum.MENSAJES, null));

		}

		return respuesta;

	}

	@Override
	public BpProyInvPoliticaDTO eliminarBpProyInvPolitica(Long idProyPolitica) throws SpddExceptions {
		BpProyInvPoliticaDTO respuesta = sessionFacadeBP.consultarBpProyInvPoliticaPorId(idProyPolitica);
		if (respuesta != null) {			
			sessionFacadeBP.eliminarBpProyInvPolitica(idProyPolitica);				
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new BpProyInvPoliticaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public BpProyInvLineaDTO eliminarBpProyInvLinea(Long idProyLinea) throws SpddExceptions {
		BpProyInvLineaDTO respuesta = sessionFacadeBP.consultarBpProyInvLineaPorId(idProyLinea);
		if (respuesta != null) {			
			sessionFacadeBP.eliminarBpProyInvLinea(idProyLinea);			
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new BpProyInvLineaDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public BpProyInvPmrDTO eliminarBpProyInvPmr(Long idProyPmr) throws SpddExceptions, JsonProcessingException {
		BpProyInvPmrDTO respuesta = sessionFacadeBP.consultarBpProyInvPmrPorId(idProyPmr);
		if (respuesta != null) {			
			sessionFacadeBP.eliminarBpProyInvPmr(idProyPmr);		
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		} else {
			respuesta = new BpProyInvPmrDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}

	@Override
	public BpProyInvPoblacionDTO eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions {
		
		BpProyInvPoblacionDTO respuesta = new BpProyInvPoblacionDTO();
		
		
		BpProyInvEtnicoDTO consultaExistenciaAsociaciones = new BpProyInvEtnicoDTO();
		consultaExistenciaAsociaciones.setIdPoblacion(peticion.getIdPoblacion());
		
		BpProyInvPoblacionDTO consultaExistencia = new BpProyInvPoblacionDTO();
		consultaExistencia = sessionFacadeBP.consultarBpProyInvPoblacion(peticion);
		
		if(consultaExistencia.getIdPoblacion() != null) {
			//se puede eliminar
			//validar existencia de registros asociados

			GenericoDTO listaConsulta = sessionFacadeBP
					.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(consultaExistenciaAsociaciones);

			// transformar a lista
			TypeReference<List<BpProyInvEtnicoDTO>> type = new TypeReference<List<BpProyInvEtnicoDTO>>() {
			};
			List<BpProyInvEtnicoDTO> lista = objectMapper.convertValue(listaConsulta.getLstObjectsDTO(), type);
			
			if(lista.size() > 0) {
				//no se puede eliminar ya que tiene registros asociados
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_REGISTROS_ASOCIADOS,
						PaqueteMensajeEnum.MENSAJES, null));
				return respuesta;
			}else {
				//se puede eliminar
				sessionFacadeBP.eliminarBpProyInvPoblacion(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_EXITOSO,
						PaqueteMensajeEnum.MENSAJES, null));
			}
		}else {
			//ya no existe
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_NO_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, null));
			return respuesta;
		}
		
		
		return respuesta;
	}

	@Override
	public BpProyInvEtnicoDTO eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion)
			throws SpddExceptions, JsonProcessingException {
		
		//Validar si existe el registro a eliminar
		BpProyInvEtnicoDTO respuesta = sessionFacadeBP.consultarBpProyInvEtnico(peticion);
		if(respuesta.getIdEtnico() == null) {
			//el registro ya no existe
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_ETNICO_NO_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		
		//eliminar registro
		
		sessionFacadeBP.eliminarBpProyInvEtnico(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_ETNICO_EXITOSO,
				PaqueteMensajeEnum.MENSAJES, null));
		
		return respuesta;
	}

}
