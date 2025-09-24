package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Esta es la interfaz en la que se crean los meotods de consultar
 *
 * @author SumSet 2019
 *
 */
public interface IComponenteGestionUsuarioConsultar {

 	/**
	 * Metodo que obtiene componente por usuario
	 * 
	 * @param usuario el usuario que se desea obtener los componentes
	 * @return una lista con los componentes de los usuarios
	 * @throws SpddExceptions
	 */
    public GenericoDTO obtenerPorUsuario(UsuariosDTO usuario) throws SpddExceptions;

    /**
     * Metodo que obtiene todos los componentes
     * 
     * @return una lista con todos los componentes
     * @throws SpddExceptions
     */
    public GenericoDTO obtenerTodos() throws SpddExceptions;
    
}
