package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionRegistrar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Implementacion de las funcionalidades de registro para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ComponenteGastoAdministracionRegistrar implements IComponenteGastoAdministracionRegistrar {

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
	 * Implementacion del metodo crearComponenteGasto
	 * @throws JsonProcessingException 
	 *
	 * @see co.gov.sdp.nhspdd.core.iservice.administracion.listacompuesta.IComponenteGastoAdministracionRegistrar.crearComponenteGasto
	 */
	@Override
	public ComponenteGastoDTO crearComponenteGasto(ComponenteGastoDTO peticion) throws SpddExceptions, JsonProcessingException {
		ComponenteGastoDTO respuesta = sessionFacadeAFS.guardarComponenteGasto(peticion);
		if (respuesta.getIdComponenteGasto() != null) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "CREAR_COMPONENTE_GASTO");
		} else {
			respuesta = new ComponenteGastoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}
		return respuesta;
	}
}
