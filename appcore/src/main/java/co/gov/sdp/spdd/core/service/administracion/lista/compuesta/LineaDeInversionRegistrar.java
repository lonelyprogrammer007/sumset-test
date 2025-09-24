package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.ILineaDeInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.LineaDeInversionMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Clase que implementa los metodos asociados con registrar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class LineaDeInversionRegistrar implements ILineaDeInversionRegistrar {

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
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;

	/**
	 * Implementacion del metodo registrarLineaDeInversion
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.ILineaDeInversionRegistrar.registrarLineaDeInversion
	 */
	@Override
	public LineaDeInversionDTO registrarLineaDeInversion(LineaDeInversionDTO peticion) throws SpddExceptions, JsonProcessingException {
		LineaDeInversionDTO respuesta = new LineaDeInversionDTO();
		String[] split = peticion.getSectorMultiple().split(";");
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals("")) {
				peticion.setIdLsSector(Long.parseLong(split[i]));
				respuesta = sessionFacadeAFS.guardarLineaDeInversion(peticion);
				if (respuesta.getIdLineaInversion() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
					auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "CREAR_LINEA_INVERSION");
				} else {
					respuesta = new LineaDeInversionDTO();
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			}
		}
		return respuesta;
	}

}