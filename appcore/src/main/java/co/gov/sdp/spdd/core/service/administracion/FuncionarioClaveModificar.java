package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IFuncionarioClaveEntidadModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IArgumentoListaSimpleServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IFuncionarioClaveEntidadServiceRepo;
import co.gov.sdp.spdd.data.mapper.FuncionarioClaveEntidadMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@Service
public class FuncionarioClaveModificar implements IFuncionarioClaveEntidadModificar {

	/**
	 * Objeto que tiene los servicios de funcionarioClaveEntidadRepo
	 */
	@Autowired
	IFuncionarioClaveEntidadServiceRepo funcionarioClaveEntidadServiceRepo;

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
	 * Objeto que contiene metodos de consultas especificas de
	 * argumentoListaSimpleServiceRepo
	 */
	@Autowired
	IArgumentoListaSimpleServiceRepo argumentoListaSimpleServiceRepo;

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
	public FuncionarioClaveEntidadDTO modificarFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions {

		FuncionarioClaveEntidadDTO respuesta = sessionFacadeAFS
				.consultarFuncionarioClaveEntidad(peticion.getIdFuncionarioEntidad());
		FuncionarioClaveEntidadDTO auxiliar = sessionFacadeAFS
				.validarFuncionarioPorEntidadYFuncion(peticion.getCodigoEntidad(), peticion.getIdLsFuncion());
		if (respuesta != null) {
			if (auxiliar != null) {
				if (auxiliar.getIdFuncionarioEntidad() == respuesta.getIdFuncionarioEntidad()) {
					respuesta = sessionFacadeAFS.guardarFuncionarioClaveEntidad(peticion);
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				} else {
					respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
					respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_REGISTRO_REPETIDO_FUNCIONARIO_CLAVE_ENTIDAD,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
				}
			} else {
				respuesta = sessionFacadeAFS.guardarFuncionarioClaveEntidad(peticion);
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}

		} else {
			respuesta = new FuncionarioClaveEntidadDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
					PaqueteMensajeEnum.MENSAJES, null));
		}
		return respuesta;
	}

}
