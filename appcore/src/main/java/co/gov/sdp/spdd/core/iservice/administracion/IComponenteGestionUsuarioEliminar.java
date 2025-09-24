package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos de eliminar de componete gestion usuario
 *
 * @author Bryan Munoz
 *
 */
public interface IComponenteGestionUsuarioEliminar {

    /**
     * Metodo que elimine un componenteGestionUsuario
     *
     * @param componenteGestionUsuarioDTO el objeto que se desea eliminar
     * @return el objeto con un mensaje de eliminacion
     * @throws SpddExceptions
     */
    public ComponenteGestionUsuarioDTO eliminarComponenteUsuario(
            ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO) throws SpddExceptions;
}
