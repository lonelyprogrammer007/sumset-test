package co.gov.sdp.spdd.core.ip.service.ipformulacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipformulacion.IIPFormulacionConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP.
 * Permite que el controlador pueda utilizar los servicios de consultar
 * 
 * @author DANIEL
 * @version 1.0 27/02/2020
 */
@Service
public class IPFormulacionConsultarService implements IIPFormulacionConsultarService {

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
	public GenericoDTO obtenerPaginado(HisVPddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarCompromisoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_HISVPDDCOMPROMISO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO compromisoEstrategicoObtenerPaginado(CompromisoEstrategicoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarCompromisoEstrategicoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_COMPROMISO_ESTRATEGICO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarMetasCompromistoEstrategico(Long idEspecifico) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarMetasCompromisoEstrategico(idEspecifico);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_METAS_COMPROMISO_ESTRATEGICO_CORRECTO, PaqueteMensajeEnum.MENSAJES,
					null));
		} else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_METAS_COMPROMISO_ESTRATEGICO_INCORRECTO,
					PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddMetaResultadoPorIDProblematicaIndicador(Long idProblematicaIndicador) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddMetaResultadoPorIDProblematicaIndicador(idProblematicaIndicador);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_METAS_RESULTADO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		else {
			respuesta=new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_METAS_RESULTADO_INCORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}
	
	@Override
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(Long idSector) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddCompetenciaAsociadaPorIdSector(idSector);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPETENCIA_ASOCIADA_POR_ID_SECTOR_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	@Override
	public GenericoDTO consultarPddCompromisosPorFiltro(PddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddCompromisosPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddCompromisoPorIdPlanDesarrollo(idPlan);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_POR_ID_PLAN_DESARROLLO_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;

	}

	@Override
	public ArbolCompromisoDTO consultarPddCompromisoEspecificoPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		ArbolCompromisoDTO respuesta = sessionFacadeIP.consultarPddCompromisosEspecificosPorIdCompromiso(idCompromiso);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_ESPECIFICO_POR_ID_COMPROMISO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	@Override
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(PddPrbValoracionDTO peticion) throws SpddExceptions {
		PddPrbValoracionDTO respuesta = sessionFacadeIP.consultarPddPrbValoracionPorIdProblematicaYMomento(peticion.getIdProblematica(), peticion.getMomento());
		
		if(respuesta != null && respuesta.getIdValoracion() != null)
		{
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_PDD_PRB_VALORACION_POR_ID_PROBLEMATICA_Y_MOMENTO, PaqueteMensajeEnum.MENSAJES, null));
		}
		else
		{
			respuesta = new PddPrbValoracionDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO, PaqueteMensajeEnum.MENSAJES, null));
		}

		return respuesta;
	}


	@Override
	public GenericoDTO consultarObrasConcretasPorMetas(Long id) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarObrasConcretasPorMetas(id);

		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_OBRAS_CONCRETAS_POR_METAS_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_OBRAS_CONCRETAS_POR_METAS_INCORRECTO,
							PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPrbIndicadorPorProblematica(Long idProblematica) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPrbIndicadorPorProblematica(idProblematica);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENENER_TODOS_PRB_INDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENENER_TODOS_PRB_INDICADOR_INCORRECTO,
							PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}

	@Override
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions {
		PddProblematicaDTO respuesta = new PddProblematicaDTO();
		if (id != null) {
			respuesta = sessionFacadeIP.consultarPddProblematicaPorId(id);

			if (respuesta != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_ID_EXITO,
								PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta = new PddProblematicaDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_ID_SIN_RESGISTROS,
						PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_PROBLEMATICA,
							PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}


	@Override
	public GenericoDTO consultarPddIndicadorTodos() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddIndicadorTodos();
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_INDICADOR_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_INDICADOR_INCORRECTO, PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}


	@Override
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		if (peticion != null) {
			respuesta = sessionFacadeIP.consultarPddProblematicaPorCompromiso(peticion);

			if (respuesta != null) {
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_EXITO,
						PaqueteMensajeEnum.MENSAJES, null));
			} else {
				respuesta = new GenericoDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_SIN_RESGISTROS,
						PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_COMPROMISO,
							PaqueteMensajeEnum.ERRORES, null));
		}
		return respuesta;
	}

	@Override
	public PddIndicadorDTO consultarPddIndicadorPorId(Long id) throws SpddExceptions {
		PddIndicadorDTO respuesta = sessionFacadeIP.consultarPddIndicadorPorId(id);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_INDICADOR_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		else {
			respuesta=new PddIndicadorDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_INDICADOR_INCORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddCompromisoDTO consultarPddCompromisoPorId(Long id) throws SpddExceptions {
		PddCompromisoDTO respuesta = sessionFacadeIP.consultarPddCompromisoPorId(id);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_COMPROMISO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		else {
			respuesta=new PddCompromisoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_COMPROMISO_INCORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public PddDTO consultarPddVigente() throws SpddExceptions {

		PddDTO respuesta=sessionFacadeIP.consultarPddVigente();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_VIGENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarProblematicaPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarProblematicasPorIdCompromiso(idCompromiso);
		if (respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_EXITO,
					PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_SIN_RESGISTROS,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddPrbPoblacionPorIdProblematica(peticion);
		if(respuesta != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POBLACION_EXITO,
					PaqueteMensajeEnum.MENSAJES, null));
		}else {
			respuesta = new GenericoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
					NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_SIN_RESGISTROS,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodasPddCompetenciaAsociada();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPETENCIA_ASOCIADA_POR_ID_SECTOR_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPrbIndicadorFiltrado(pddPrbIndicadorDTO);		
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENENER_TODOS_PRB_INDICADOR_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));		
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddProbleatica() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosPddProblematica();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_EXITO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddCompromisoEspecificoFiltrado(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		
		GenericoDTO respuesta =  sessionFacadeIP.consultarPddCompromisoEspecificoFiltrado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_ESPECIFICO_POR_ID_COMPROMISO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
		
	}
	
	
}
