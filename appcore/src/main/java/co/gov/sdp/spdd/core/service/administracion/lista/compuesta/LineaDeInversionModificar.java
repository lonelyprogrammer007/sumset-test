package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos asociados con modificar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class LineaDeInversionModificar implements ILineaDeInversionModificar {

	/**
	 * Objeto que permite realizar las operaciones basicas de bd
	 */
	@Autowired
	ILineaDeInversionServiceRepo lineaDeInversionServiceRepo;
	
	/**
	 * Objeto que permite realizar los mapeos de dto a entidad y viceversa
	 */
	@Autowired
	LineaDeInversionMapper lineaDeInversionMapper;
	
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
	 * Implementacion del metodo modificarLineaInversion
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar.modificarLineaInversion
	 */
	@Override
	public LineaDeInversionDTO modificarLineaInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions {

		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();

		LineaDeInversionDTO entidad = sessionFacadeAFS.consultarLineaDeInversionPorId(lineaDeInversionDTO.getIdLineaInversion());

		LineaDeInversionDTO auxiliar = sessionFacadeAFS.buscarLineaInversionPorConceptoYSector(lineaDeInversionDTO.getConcepto(), lineaDeInversionDTO.getIdLsSector());

		if (entidad != null && entidad.getIdLineaInversion() > 0) {
			if (entidad.getIdLineaInversion() == auxiliar.getIdLineaInversion()) {
				respuesta = sessionFacadeAFS.modificarLineaDeInversion(lineaDeInversionDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, lineaDeInversionDTO.getLenguaje()));
			} else {
				respuesta = sessionFacadeAFS.guardarLineaDeInversion(lineaDeInversionDTO);
				if (respuesta.getIdLineaInversion() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, lineaDeInversionDTO.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
							PaqueteMensajeEnum.MENSAJES, lineaDeInversionDTO.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, lineaDeInversionDTO.getLenguaje()));
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo modificarEstadoLineaInversion
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionModificar.modificarEstadoLineaInversion
	 */
	@Override
	public LineaDeInversionDTO modificarEstadoLineaInversion(Long id) throws SpddExceptions {
		LineaDeInversion entidad = lineaDeInversionServiceRepo.obtener(id);
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		if (entidad != null) {
			if (entidad.getEstado() == 0) {
				entidad.setEstado((short) 1);
				lineaDeInversionServiceRepo.guardar(entidad);
				respuesta = lineaDeInversionMapper.lineaDeInversionEntityToDTO(entidad);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_CAMBIO_DE_ESTADO_CORRECTO, PaqueteMensajeEnum.MENSAJES, null));
			} else {
				entidad.setEstado((short) 0);
				lineaDeInversionServiceRepo.guardar(entidad);
				respuesta = lineaDeInversionMapper.lineaDeInversionEntityToDTO(entidad);
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
