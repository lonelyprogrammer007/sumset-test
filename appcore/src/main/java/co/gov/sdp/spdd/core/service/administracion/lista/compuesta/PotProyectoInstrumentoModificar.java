package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IPotProyectoInstrumentoModificar;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotProyectoInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.mapper.PotProyectoInstrumentoMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Esta clase implementa los metodos relacionados con modificar de
 * potProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
@Service
public class PotProyectoInstrumentoModificar implements IPotProyectoInstrumentoModificar {

	/**
	 * Objeto con el cual se traen los servicios de bd
	 */
	@Autowired
	IPotProyectoInstrumentoServiceRepo potProyectoInstrumentoServiceRepo;

	/**
	 * Objeto que permite mapear de dto a entidad y viceversa
	 */
	@Autowired
	PotProyectoInstrumentoMapper potProyectoInstrumentoMapper;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Implementacion del metodo modificarProyectoInstrumento
	 *
	 * @see
	 */
	@Override
	public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO)
			throws SpddExceptions {

		PotProyectoInstrumentoDTO respuesta = new PotProyectoInstrumentoDTO();		
		
		PotProyectoInstrumentoDTO entidad = sessionFacadeAFS.consultarPotProyectoInstrumentoPorId(potProyectoInstrumentoDTO.getIdProyectoInstrumento());

		PotProyectoInstrumentoDTO auxiliar = sessionFacadeAFS.buscarPorIdLsPotObraYIdLsPotInstrumento(potProyectoInstrumentoDTO);
		
		if (entidad != null && entidad.getIdProyectoInstrumento() > 0) {
			if (entidad.getIdProyectoInstrumento().equals(auxiliar.getIdProyectoInstrumento())) {
				respuesta = sessionFacadeAFS.modificarProyectoInstrumento(potProyectoInstrumentoDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
			} else {
				respuesta = sessionFacadeAFS.guardarPotProyectoInstrumento(potProyectoInstrumentoDTO);
				if (respuesta.getIdProyectoInstrumento() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
							PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, potProyectoInstrumentoDTO.getLenguaje()));
		}
		return respuesta;
	}

	/**
	 * Implementacion del metodo cambiarEstadoProyectoInstrumento
	 */
	
}
