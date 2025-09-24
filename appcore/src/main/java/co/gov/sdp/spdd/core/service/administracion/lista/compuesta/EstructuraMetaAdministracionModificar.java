package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEstructuraMetaServiceRepo;
import co.gov.sdp.spdd.data.mapper.EstructuraMetaMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de modificacion para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EstructuraMetaAdministracionModificar implements IEstructuraMetaAdministracionModificar {

	// Servicio Repositorio de estructura meta
	@Autowired
	IEstructuraMetaServiceRepo estructuraMetaServiceRepo;

	// Servicio Repositorio de argumento lista simple
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de estructura meta
	@Autowired
	EstructuraMetaMapper estructuraMetaMapper;

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
	 * Implementacion del metodo modificarComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEstructuraMetaAdministracionModificar.modificarEstructuraMeta
	 */
	@Override
	public EstructuraMetaDTO modificarEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions {

		EstructuraMetaDTO respuesta = new EstructuraMetaDTO();

		EstructuraMetaDTO entidad = sessionFacadeAFS.consultarEstructuraMeta(peticion.getIdEstructuraMetas());

		EstructuraMetaDTO auxiliar = sessionFacadeAFS.buscarUnidadMedidaYVerbo(peticion.getIdLsUnidadMedida(),
				peticion.getIdLsVerbo());

		if (entidad != null && entidad.getIdEstructuraMetas() > 0) {
			if (entidad.getIdEstructuraMetas() == auxiliar.getIdEstructuraMetas()) {
				respuesta = sessionFacadeAFS.modificarEstructuraMeta(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			} else {
				respuesta = sessionFacadeAFS.guardarEstructuraMeta(peticion);
				if (respuesta.getIdEstructuraMetas() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
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
}