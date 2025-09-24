package co.gov.sdp.spdd.core.iservice.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEntidadAdministracionRegistrar {

    /**
     * Metodo para crear entidades
     *
     * @param peticion Objeto DTO con los datos para crear la entidad
     * @return Objeto DTO informando la creacion exitosa de la entidad, sino un
     * objeto vacio con el mensaje de error correspondiente
     */
    public EntidadDTO crearEntidad(EntidadDTO peticion) throws SpddExceptions;

    /**
     * Metodo para asignar entidades a un usuario
     *
     * @param peticion Objeto DTO con los datos para asignar la entidad al
     * usuario correspondiente
     * @return Objeto DTO informando la asignacion exitosa de la entidad al
     * usuario, sino un objeto vacio con el mensaje de error correspondiente
     * @throws JsonProcessingException 
     */
    public UsuarioEntidadDTO asignarUsuarioEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException;
}
