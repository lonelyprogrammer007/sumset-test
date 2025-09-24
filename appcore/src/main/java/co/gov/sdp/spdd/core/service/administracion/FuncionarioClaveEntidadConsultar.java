package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadConsultar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IFuncionarioClaveEntidadRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Esta clase implementa todos los metodos de consulta de
 * FuncionarioClaveEntidadConsultar
 *
 * @author Johan Sebastian Giraldo
 *
 */
@Service
public class FuncionarioClaveEntidadConsultar implements IFuncionarioClaveEntidadConsultar {

	/**
	 * Objeto que tiene los servicios de funcionarioClaveEntidadRepo
	 */
	@Autowired
	IFuncionarioClaveEntidadRepo funcionarioClaveEntidadRepo;

	/**
	 * Objeto que mapea las entidades a dto y viceversa
	 */
	@Autowired
	FuncionarioClaveEntidadMapper funcionarioClaveEntidadMapper;

	/**
	 * Objeto que contiene metodos de consultas especificas de entidadServiceRepo
	 */
	@Autowired
	IEntidadServiceRepo entidadServiceRepo;

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
	 * 
	 */
	@Override
	public GenericoDTO obtenerFuncionarioClaveEntidad(String codigoEntidad) throws SpddExceptions {
		EntidadDTO entidadDTO = sessionFacadeAFS.consultarEntidad(codigoEntidad);
		GenericoDTO respuesta = new GenericoDTO();
		if (entidadDTO != null) {
			respuesta = sessionFacadeAFS.obtenerFuncionarioPorEntidad(codigoEntidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_FUNCIONARIO_CLAVE_ENTIDAD_INCORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

	/**
	 * 
	 */
	@Override
	public GenericoDTO obtenerPaginado(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeAFS.consultarFuncionarioClaveEntidadPorFiltro(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));

		return respuesta;
	}
}
