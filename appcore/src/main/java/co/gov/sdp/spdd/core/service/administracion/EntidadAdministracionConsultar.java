package co.gov.sdp.spdd.core.service.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IUsuarioEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IUsuarioEntidadRepo;
import co.gov.sdp.spdd.data.mapper.EntidadMapper;
import co.gov.sdp.spdd.data.mapper.UsuarioEntidadMapper;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.afs.UsuarioEntidad;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.MetodosRest;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EntidadAdministracionConsultar implements IEntidadAdministracionConsultar {

	// Repositorio de usuario entidad
	@Autowired
	IUsuarioEntidadServiceRepo usuarioEntidadServiceRepo;

	// Repositorio de entidad
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

	// Repositorio de usuario entidad
	@Autowired
	IUsuarioEntidadRepo usuarioEntidadRepo;

	// Repositorio de Entidad
	@Autowired
	IEntidadServiceRepo entidadRepo;

	// Mapper de usuario entidad
	@Autowired
	UsuarioEntidadMapper usuarioEntidadMapper;

	// Mapper de entidad
	@Autowired
	EntidadMapper entidadMapper;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<HistorialSectorialDTO>> historialSectorial;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidad;

	@Autowired
	HttpServletRequest request;

	// Logger
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerEntidadAsignadas
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionConsultar.obtenerEntidadAsignadas
	 */
	@Override
	public GenericoDTO obtenerEntidadAsignadas(UsuariosDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<UsuarioEntidad> lista = usuarioEntidadRepo.obtenerPorUsuario(peticion.getNombreUsuario());
		List<UsuarioEntidadDTO> listaRespuesta = usuarioEntidadMapper.usuarioEntidadDTOEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_ENTIDAD_TODOS_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		return respuesta;
	}

	/**
	 * Implementacion del metodo obtenerEntidadTodos
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.IEntidadAdministracionConsultar.obtenerEntidadTodos
	 */
	@Override
	public GenericoDTO obtenerEntidadTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<Entidad> lista = entidadRepo.obtenerTodos();
		List<EntidadDTO> listaRespuesta = entidadMapper.entidadEntitiesToDTO(lista);
		respuesta.setLstObjectsDTO(new ArrayList<>(listaRespuesta));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_ENTIDAD_TODOS_CORRECTO,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public EntidadDTO obtenerPorId(String codigo) throws SpddExceptions {
		EntidadDTO respuesta = sessionFacadeAFS.consultarEntidad(codigo);		
		
		if(respuesta != null)
		{
			String token = request.getHeader("Authorization");
			historialSectorial.agregarToken(token.substring(6, token.length()));			
				
			RespuestaApiDTO<HistorialSectorialDTO> entidades = historialSectorial.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/historial_sectorial/consultar_por_codigo_distrital/"+codigo,
					new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
					});
			
			if(!entidades.getObjetos().isEmpty())
			{
				respuesta.setNombreEntidad(entidades.getObjetos().get(0).getCodigoEntidad());
				respuesta.setSector(entidades.getObjetos().get(0).getDescripcion());
			}
			else
			{
				respuesta.setNombreEntidad(null);
				respuesta.setSector(null);
				
			}
			
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_ENTIDAD_TODOS_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		else
		{
			respuesta= new EntidadDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}		
		
		return respuesta;
	}

	@Override
	public EntidadDTO obtenerPorNombre(String nombre) throws SpddExceptions {
		EntidadDTO respuesta = new EntidadDTO();
		
		String token = request.getHeader("Authorization");
		historialSectorial.agregarToken(token.substring(6, token.length()));			
			
		RespuestaApiDTO<HistorialSectorialDTO> entidades = historialSectorial.get(
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/historial_sectorial/consultar_por_codigo_entidad/"+nombre,
				new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
				});
		
		if(!entidades.getObjetos().isEmpty())
		{
			respuesta = sessionFacadeAFS.consultarEntidad(entidades.getObjetos().get(0).getCodigoDistrital());
			
			respuesta.setNombreEntidad(entidades.getObjetos().get(0).getCodigoEntidad());
			respuesta.setSector(entidades.getObjetos().get(0).getDescripcion());
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_ENTIDAD_TODOS_CORRECTO,
					PaqueteMensajeEnum.MENSAJES, null));
			
		}
		else
		{
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		
		return respuesta;
		
	}

	@Override
	public EntidadDTO validarYRegistrarEntidadNoExistente(String codigo) throws SpddExceptions {
		EntidadDTO respuesta = sessionFacadeAFS.consultarEntidad(codigo);
		
		if(respuesta == null)
		{
			String token = request.getHeader("Authorization");
			entidad.agregarToken(token.substring(6, token.length()));
			
			RespuestaApiDTO<EntidadSeguridadDTO> entidades = entidad.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/entidad/consultarentidad/"
							+ codigo,
					new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
					});
			
			String codigoEntidad = entidades.getObjetos().get(0).getCodigoDistrital();
			
			if(codigoEntidad != null)
			{
				EntidadDTO entidadDTOAux = new EntidadDTO();
				entidadDTOAux.setCodigoEntidad(entidades.getObjetos().get(0).getCodigoDistrital());
				entidadDTOAux.setGestionUsuarios(0);
				entidadDTOAux.setGestionProyectos(0);
				
				respuesta = sessionFacadeAFS.guardarEntidad(entidadDTOAux);
				respuesta.setCodigo(NHSPDDConstantes.RESPUESTA_EXITOSA);
			}
			else
			{
				respuesta = new EntidadDTO();
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_EXISTENTE_BD_SEGURIDAD,
						PaqueteMensajeEnum.MENSAJES, null));
			}						
		}
		else
		{
			respuesta.setCodigo(NHSPDDConstantes.RESPUESTA_EXITOSA);
		}
		return respuesta;
	}
	
	
}
