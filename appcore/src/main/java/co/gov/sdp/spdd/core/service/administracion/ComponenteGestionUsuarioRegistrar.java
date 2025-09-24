package co.gov.sdp.spdd.core.service.administracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioRegistrar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.afs.IComponenteGestionUsuarioRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.model.afs.ComponenteGestionUsuario;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.util.AuditoriaUtil;

/**
 * Esta clase tiene todos los metodos de registrar de la entidad
 * componenteGestionUsuario
 *
 * @author Bryan Munoz
 *
 */
@Service
public class ComponenteGestionUsuarioRegistrar implements IComponenteGestionUsuarioRegistrar {

	/**
	 * Este objeto maneja los metodos de bases de dato de componenteGestionUsuario
	 */
	@Autowired
	IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;
	
	/**
	 * 
	 */
	@Autowired
	IComponenteGestionUsuarioRepo repo;

	/**
	 * Este objeto maneja el traslado de dto a entidad y viceversa de
	 * componenteGestionUsuario
	 */
	@Autowired
	ComponenteGestionUsuarioMapper componenteGesionUsuarioMapperImpl;

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * Auditoria
	 */
	@Autowired
	AuditoriaUtil auditoria;
	

	/**
	 * @throws JsonProcessingException 
	 * 
	 */
	@Override
	public ComponenteGestionUsuarioDTO crearGestionComponenteUsuario(ComponenteGestionUsuarioDTO peticion)
			throws SpddExceptions, JsonProcessingException {
        List<ComponenteGestionUsuario> lista=repo.findByUsuario(peticion.getCodigoUsuario());
        if(!lista.isEmpty() && lista!=null) {
        repo.deleteAll(lista);
        }
		ComponenteGestionUsuarioDTO respuesta = new ComponenteGestionUsuarioDTO();
		String[] split = peticion.getStringConcatenado().split(";");
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals("")) {
				Long idComponenteGestion = Long.parseLong(split[i]);
				peticion.setIdComponenteGestion(idComponenteGestion);
				respuesta = sessionFacadeAFS.guardarComponenteGestionusuario(peticion);
				auditoria.crearAuditoria(peticion.toString(), peticion.toString(), "CREAR_AFS", "ASIGNAR_COMPONENTES_GESTION");
				respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
				respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(
						NHSPDDConstantes.MENSAJE_ASIGNAR_COMPONENTE_GESTION_USUARIO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
			}
		}
		return respuesta;
	}
}
