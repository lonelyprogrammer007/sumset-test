package co.gov.sdp.spdd.core.icontroller.autenticacion;

import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de los usuarios para el modulo de
 * autenticacion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IUsuarioAutenticacionController {

    /**
     * Metodo que permite cambiar la clave del usuario
     *
     * @param usuariosDTO Objeto UsuarioDTO que contiene el id, la clave
     * anterior y la clave nueva
     * @return El objeto UsuarioDTO con codigo 200 y token si el cambio de clave
     * fue exitoso, en caso contrario el codigo de respuesta sera con base al
     * error generado
     * @throws SpddExceptions
     */
    public UsuariosDTO cambiarClave(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * Metodo que permite iniciar sesion otorgando el token de autorizacion para
     * las demas peticiones
     *
     * @param usuariosDTO Objeto UsuarioDTO que contiene el correo, la clave y
     * el tipo de usuario
     * @return El objeto UsuarioDTO con codigo 200 y token si la autenticacion
     * fue exitosa, en caso contrario el codigo de respuesta sera con base al
     * error generado
     * @throws SpddExceptions
     */
    public UsuariosDTO iniciarSesion(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * Direccionamiento que se recibe del front para enviar el correo
     *
     * @param correo tipo String este es el correo del usuario que desea
     * restablecer la contraseña
     * @return tipo UsuarioDTO que retorna el usuario que desea restablecer
     * contraseña
     * @throws SpddExceptions
     */
    public UsuariosDTO restablecerClave(UsuariosDTO usuariosDTO) throws SpddExceptions;

}
