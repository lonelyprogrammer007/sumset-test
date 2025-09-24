package co.gov.sdp.spdd.core.iservice.autenticacion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IAutenticacionModificar {

    /**
     * Metodo para cambiar la clave de un usuario existente por una nueva
     *
     * @param peticion Objeto DTO con los datos para identificar el usuario y
     * cambiar la contraseña
     * @return Objeto DTO informando del cambio satisfactorio en la clave, sino
     * un objeto vacio con el mensaje de error correspondiente
     * @throws SpddExceptions
     * @throws JsonProcessingException 
     */
    public UsuariosDTO cambiarClave(UsuariosDTO peticion) throws SpddExceptions, JsonProcessingException;
    
    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
    public UsuariosDTO reestablecerContrasenia(UsuariosDTO peticion) throws SpddExceptions,JsonProcessingException;
}
