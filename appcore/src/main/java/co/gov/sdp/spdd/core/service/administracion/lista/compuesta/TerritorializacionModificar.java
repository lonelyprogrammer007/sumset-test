package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ITerritorializacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ITerritorializacionServiceRepo;
import co.gov.sdp.spdd.data.mapper.TerritorializacionMapper;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos asociados con modificar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class TerritorializacionModificar implements ITerritorializacionModificar {

	/**
	 * 
	 */
	@Autowired
	TerritorializacionMapper mapper;
	
	/**
	 * 
	 */
	@Autowired
	ITerritorializacionServiceRepo territorializacionServiceRepo;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo modificarTerritorializacion
	 */
	@Override
	public TerritorializacionDTO modificarTerritorializacion(TerritorializacionDTO territorializacionDTO)
			throws SpddExceptions {

		TerritorializacionDTO respuesta = new TerritorializacionDTO();

		TerritorializacionDTO entidad = sessionFacadeAFS
				.consultarTerritorializacionPorId(territorializacionDTO.getIdTerritorializacion());
		
		TerritorializacionDTO auxiliar = territorializacionDTO.getIdLsUpr() != null
				? sessionFacadeAFS.buscarPorLsVeredaYLsUpr(territorializacionDTO)
				: sessionFacadeAFS.buscarPorLsBarrioYLsUpzYLsLocalidad(territorializacionDTO); 
		
		if (entidad != null && entidad.getIdTerritorializacion() > 0) {
			if (territorializacionDTO.getIdTerritorializacion() == auxiliar.getIdTerritorializacion()) {
			 if(territorializacionDTO.getIdLsUpr()!=null) {
				 territorializacionDTO.setIdLsBarrio(-1L);
				 territorializacionDTO.setIdLsUpz(-1L);
			 }else {
				 territorializacionDTO.setIdLsUpr(-1L);
				 territorializacionDTO.setIdLsVereda(-1L);
			 }
				respuesta = sessionFacadeAFS.modificarTerritorializacion(territorializacionDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeAFS.guardarTerritorializacion(territorializacionDTO);
				if (respuesta.getIdTerritorializacion() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
							PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, territorializacionDTO.getLenguaje()));
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarEstadoTerritorializacion
	 */
	@Override
	public TerritorializacionDTO modificarEstadoTerritorializacion(Long id) throws SpddExceptions {
		Territorializacion entidad = territorializacionServiceRepo.obtener(id);
		TerritorializacionDTO respuesta = new TerritorializacionDTO();
		if (entidad != null) {
			if (entidad.getEstado() == 0) {
				entidad.setEstado((short) 1);

				territorializacionServiceRepo.guardar(entidad);
				respuesta = mapper.territorializacionEntityToDTO(entidad);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CAMBIO_DE_ESTADO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			} else {
				entidad.setEstado((short) 0);

				territorializacionServiceRepo.guardar(entidad);
				respuesta = mapper.territorializacionEntityToDTO(entidad);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CAMBIO_DE_ESTADO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}
}
