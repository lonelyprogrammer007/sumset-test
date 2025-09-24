package co.gov.sdp.spdd.core.service.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.iservice.administracion.IComponenteGestionUsuarioEliminar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IComponenteGestionUsuarioServiceRepo;
import co.gov.sdp.spdd.data.mapper.ComponenteGestionUsuarioMapper;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que tiene los metodos de eliminar relacionado con componente gestion
 * usuario
 *
 * @author Bryan Munoz 2019
 *
 */
@Service
public class ComponenteGestionUsuarioEliminar implements IComponenteGestionUsuarioEliminar {

    /**
     * Este objeto tiene las operaciones basicas de la bd
     */
    @Autowired
    IComponenteGestionUsuarioServiceRepo componenteGestionUsuarioServiceRepo;

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
	ISessionFacadeAFS sessionFacadeAfs;
    
    /**
     * 
     */
    @Autowired
    SPDDLogger spddLogger;

    /**
     * 
     */
    @Override
    public ComponenteGestionUsuarioDTO eliminarComponenteUsuario(
            ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws SpddExceptions {
    	sessionFacadeAfs.eliminarComponenteGestionUsuario(componenteGestionUsuarioDTO.getIdComponenteGestion());
        componenteGestionUsuarioDTO.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
        componenteGestionUsuarioDTO.setMsgRespuesta(
                NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_REMOVER_COMPONENTE_GESTION_USUARIO_CORRECTO,
                        PaqueteMensajeEnum.MENSAJES, componenteGestionUsuarioDTO.getLenguaje()));
        
        return componenteGestionUsuarioDTO;
    }
}
