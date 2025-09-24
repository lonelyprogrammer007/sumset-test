package co.gov.sdp.spdd.core.service.administracion.lista.compuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta.IComponenteGastoAdministracionConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Implementacion de las funcionalidades de consulta para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
@Service
public class ComponenteGastoAdministracionConsultar implements IComponenteGastoAdministracionConsultar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAfs;

	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo obtenerComponenteGastoTodos
	 *
	 * @see co.gov.sdp.nhspdd.core..iservice.administracion.listacompuesta.IComponenteGastoAdmnistracionConsultar.obtenerComponenteGastoTodos
	 */
	@Override
	public GenericoDTO obtenerComponenteGastoTodos(ComponenteGastoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAfs.consultarComponenteGastoPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_LISTA_SIMPLE,
				PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
}
