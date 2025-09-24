package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de admnistracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEntidadAdministracionConsultar {

    /**
     * Metodo para obtener la lista de entidades asignadas a un usuario
     *
     * @param peticion Objeto DTO con los datos del usuario a buscar
     * @return Objeto Generico que contiene la lista de las entidades asignadas
     */
    public GenericoDTO obtenerEntidadAsignadas(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * Metodo para obtener la lista de todas las entidades
     *
     * @return Objeto Generico que contiene la lista de todas las entidades
     */
    public GenericoDTO obtenerEntidadTodos() throws SpddExceptions;
    
    /**
     * Metodo para la entidad por su codigo
     *
     * @return Objeto Generico que contiene la lista de todas las entidades
     */
    public EntidadDTO obtenerPorId(String codigo) throws SpddExceptions;
    
    /**
     * Metodo para obtener la entidad por su Nombre de entidad 
     * @param nombre
     * @return
     * @throws SpddExceptions
     */
    public EntidadDTO obtenerPorNombre(String nombre) throws SpddExceptions;
    
    /**
     * Peticion que permite la comunicacion entre el appdata y appcore para validar si la entidad 
     * existe en la BD de SegPlan con respecto a la base de datos de seguridad por medio del codigo, 
     * Si la entidad existe en la BD de seguridad y no en SegPlan entonces la registrara en la BD de 
     * segplan.
     * @param codigo string que representa el codigo de la entidad
     * @return una EntidadDTO con la informacion correspondiente
     */
    public EntidadDTO validarYRegistrarEntidadNoExistente(String codigo) throws SpddExceptions;

}
