package co.gov.sdp.spdd.core.iservice.autenticacion;

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IAutenticacionConsultar {

    /**
     * Metodo que verifica la existencia del usuario y autoriza el inicio de
     * sesion
     *
     * @param peticion Objeto DTO con los datos de sesion del usuario
     * @return Objeto DTO con la informacion del usuario si la validacion es
     * correcta, sino un objeto vacio con el mensaje de error correspondiente
     * @throws SpddExceptions
     */
    public UsuariosDTO iniciarSesion(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * 
     * @param usuariosDTO
     * @return
     * @throws SpddExceptions
     */
    public UsuariosDTO verificarCorreo(UsuariosDTO usuariosDTO) throws SpddExceptions; 
}
