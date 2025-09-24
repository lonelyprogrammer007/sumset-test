package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGastoServiceRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGastoMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de modificacion para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ComponenteGastoAdministracionModificar implements IComponenteGastoAdministracionModificar {

	// Servicio Repositorio de componente de gasto
	@Autowired
	IComponenteGastoServiceRepo componenteGastoServiceRepo;

	// Servicio Repositorio de argumento lista simple
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

	// Mapper de componente gasto
	@Autowired
	ComponenteGastoMapper componenteGastoMapper;

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
	 * @see co.gov.sdp.nhspdd.core.iservice.administracion.listacompuesta.IComponenteGastoAdministracionModificar.modificarComponenteGasto
	 */
	@Override
	public ComponenteGastoDTO modificarComponenteGasto(ComponenteGastoDTO componenteGastoDTO) throws SpddExceptions {

		ComponenteGastoDTO respuesta = new ComponenteGastoDTO();

		ComponenteGastoDTO entidad = sessionFacadeAFS
				.consultarComponenteGastoPorId(componenteGastoDTO.getIdComponenteGasto());

		ComponenteGastoDTO auxiliar = sessionFacadeAFS.buscarCodigoYNombre(componenteGastoDTO.getCodigoComponente(),
				componenteGastoDTO.getNombreComponente());

		if (entidad != null && entidad.getIdComponenteGasto() > 0) {
			if (componenteGastoDTO.getIdComponenteGasto() == auxiliar.getIdComponenteGasto()) {
				// Existe para la misma entidad. Modificar.
				respuesta = sessionFacadeAFS.modificarComponenteGasto(componenteGastoDTO);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
								PaqueteMensajeEnum.MENSAJES, componenteGastoDTO.getLenguaje()));
			} else {
				// Existe la llave para otra entidad. Mensaje de error donde se indica que la
				// pareja ya existe para otra entidad.
				respuesta = sessionFacadeAFS.guardarComponenteGasto(componenteGastoDTO);
				if (respuesta.getIdComponenteGasto() != null) {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(
							NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ACTUALIZAR_LISTA_COMPUESTA,
									PaqueteMensajeEnum.MENSAJES, componenteGastoDTO.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ASOCIACION_EXISTENTE,
							PaqueteMensajeEnum.MENSAJES, componenteGastoDTO.getLenguaje()));
				}
			}
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, componenteGastoDTO.getLenguaje()));
		}
		return respuesta;
	}
}