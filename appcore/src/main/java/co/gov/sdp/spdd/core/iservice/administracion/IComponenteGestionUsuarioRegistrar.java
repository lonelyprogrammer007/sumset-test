package co.gov.sdp.spdd.core.iservice.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene los metodos de registrar componentes de gestion usuario
 *
 * @author Bryan Munoz
 *
 */
public interface IComponenteGestionUsuarioRegistrar {

    /**
     * Metodoq ue crea un componente de gestion con un usuario asociado
     *
     * @param componenteGestionUsuarioDTO objeto que se desea crear
     * @return el objeto creado con los mensajes de respuesta
     * @throws SpddExceptions
     */
    public ComponenteGestionUsuarioDTO crearGestionComponenteUsuario(
            ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws SpddExceptions, JsonProcessingException;

}
