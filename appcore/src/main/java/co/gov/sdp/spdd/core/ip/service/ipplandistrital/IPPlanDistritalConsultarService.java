package co.gov.sdp.spdd.core.ip.service.ipplandistrital;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.ip.iservice.ipplandistrital.IIPPlanDistritalConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;

/**
 * Clase que permite la comunicacion entre el appdata y appcore del modulo IP
 * Capítulos 10, 11, 14, 15, 16 y 17. Permite que el controlador pueda utilizar
 * los servicios de consultar
 * 
 * @author DANIEL, SEBASTIAN, BRYAN
 * @version 1.0 02/03/2020
 */
@Service
public class IPPlanDistritalConsultarService implements IIPPlanDistritalConsultarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeIP sessionFacadeIP;

	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	HttpServletRequest request;
	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	@Override
	public GenericoDTO pddObtenerPaginado(PddDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public PddDTO consultarPddPorId(Long id) throws SpddExceptions {
		PddDTO respuesta = sessionFacadeIP.consultarPddPorID(id);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;

	}

	@Override
	public GenericoDTO consultarTodosPddNivelPorIdPlanDesarrolloDistrital(Long idPlan) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddNivelPorIdPlanDesarrollo(idPlan);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_NIVEL_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public GenericoDTO pdlObtenerPaginado(PdlDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPdlPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDL_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPlanDesarrolloLocal();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDL_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public PdlDTO consultarPlanDesarrolloLocalPorId(Long idPlanLocal) throws SpddExceptions {
		PdlDTO respuesta = sessionFacadeIP.consultarPlanDesarrolloLocalPorId(idPlanLocal);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDL_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPdlNivelPorIdPlanLocal(idPlanLocal);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDL_NIVEL_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddMetaResultadoProyectoEstrategico(PddMetaResultadoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarPddMetaResultado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_PDD_META_RESULTADO_POR_PROYECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(PddNivelAtributoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarPddIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarIndicadoresMetaResultado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_META_RESULTADO_INDICADOR_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarMetaProductoPorMR(PddMetaProductoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarMetaProductoPorMR(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDD_META_PRODUCTO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDL_NIVEL_ATRIBUTO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarIndicadoresMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarIndicadorMetaProducto(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_PDD_META_PRODUCTO_INDICADOR_CORRECTO, peticion.getLenguaje()));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPdlNivelAtributoPorIdAtributoPadre(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDL_NIVEL_ATRIBUTO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarIndicadorEntidadMetaProducto(peticion);
		String token = request.getHeader("Authorization");
		entidad.agregarToken(token.substring(6, token.length()));
		TypeReference<List<PddMpIndicadorEntidadDTO>> type = new TypeReference<List<PddMpIndicadorEntidadDTO>>() {
		};
		List<PddMpIndicadorEntidadDTO> resultado = new ArrayList<>();
		List<PddMpIndicadorEntidadDTO> lista = mapper.convertValue(respuesta.getLstObjectsDTO(), type);
		for (PddMpIndicadorEntidadDTO indicadorEntidad : lista) {
			RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
							+ indicadorEntidad.getCodigoEntidad(),
					new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
					});
			if (entidades.getObjetos().get(0).getCodigoEntidad() != null
					&& !entidades.getObjetos().get(0).getCodigoEntidad().equals("")) {
				indicadorEntidad.setNombreEntidad(entidades.getObjetos().get(0).getCodigoEntidad());
			} else {
				indicadorEntidad.setNombreEntidad(NHSPDDConstantes.CORE_SEGURIDAD_ENTIDAD_PLANEACION);
			}
			resultado.add(indicadorEntidad);
		}

		respuesta.setLstObjectsDTO(new ArrayList<>(resultado));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_MP_ENTIDADES_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosRangoPonderacion() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerTodosRangoPonderacion();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_RANGO_PONDERACION, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarRangoPonderacionPorIdPdd(Long idRango) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.obtenerPddRangoPonderacionPorIdPdd(idRango);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_RANGO_PONDERACION, PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo) throws SpddExceptions {
		ArbolCompromisoDTO respuesta = sessionFacadeIP.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_COMPONENTES_DESBALANCEADOS_NIVELES_POR_ID_PLAN_DESARROLLO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosIndicadoresMetaProducto() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosMetaProductosEntidades();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
				NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PDD_MP_ENTIDADES_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}

	@Override
	public PdlDTO consultarPdlVigente(String codigoEntidad) throws SpddExceptions {
		PdlDTO respuesta=sessionFacadeIP.consultarPdlVigente(codigoEntidad);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_PDL_VIGENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

}
