package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IEquipamientoAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEquipamientoServiceRepo;
import co.gov.sdp.spdd.data.mapper.EquipamientoMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de modificacion para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class EquipamientoAdministracionModificar implements IEquipamientoAdministracionModificar {

	// Servicio Repositorio de eEquipamiento
	@Autowired
	IEquipamientoServiceRepo equipamientoServiceRepo;

	// Servicio Repositorio de argumento lista simple
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de estructura meta
	@Autowired
	EquipamientoMapper equipamientoMapper;

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
	public EquipamientoDTO modificarEquipamiento(EquipamientoDTO equipamientoDTO) throws SpddExceptions {

		EquipamientoDTO respuesta = new EquipamientoDTO();

		EquipamientoDTO entidad = sessionFacadeAFS.consultarEquipamiento(equipamientoDTO.getIdEquipamento());

		EquipamientoDTO auxiliar = sessionFacadeAFS.buscarSectorYCategoria(equipamientoDTO.getIdLsSector(),
				equipamientoDTO.getIdLsCategoria());

		if (entidad != null && entidad.getIdEquipamento() > 0) {
			if (entidad.getIdEquipamento() == auxiliar.getIdEquipamento()) {
				respuesta = sessionFacadeAFS.modificarEquipamiento(equipamientoDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, equipamientoDTO.getLenguaje()));
			} else {
				respuesta = sessionFacadeAFS.guardarEquipamiento(equipamientoDTO);
				if (respuesta.getIdEquipamento() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, equipamientoDTO.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
							PaqueteMensajeEnum.MENSAJES, equipamientoDTO.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, equipamientoDTO.getLenguaje()));
		}
		return respuesta;
	}
}
