package co.gov.sdp.spdd.core.icontroller.administracion;

import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de entidades para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEntidadAdministracionController {

    /**
     * Metodo para asignar una entidad a un usuario
     *
     * @param peticion Objeto DTO que contiene la informacion del usuario a
     * asignarle la entidad y la entidad
     * @return Objeto DTO que contiene la informacion de la entidad y el usuario
     * asignado si la peticion se realiza satisfactoriamente, sino, se enviara
     * el objeto vacio con el respectivo mensaje de error
     * @throws JsonProcessingException 
     */
    public UsuarioEntidadDTO asignarEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException;

    /**
     * Metodo para crear una entidad
     *
     * @param peticion Objeto DTO que contiene la informacion de la entidad
     * @return Objeto DTO que contiene la informacion de la entidad creado si la
     * peticion se realiza satisfactoriamente, sino, se enviara el objeto vacio
     * con el respectivo mensaje de error
     */
    public EntidadDTO crearEntidad(EntidadDTO peticion) throws SpddExceptions;

    /**
     * Metodo para remover una signacion de una entidad para un usuario
     *
     * @param peticion Objeto DTO que contiene la informacion del usuario al
     * cual removerle la asignacion la entidad y la entidad de inversion a
     * remover
     * @return Objeto DTO que contiene la informacion del usuario y la entidad
     * removida si la peticion se realiza satisfactoriamente, sino, se enviara
     * el objeto vacio con el respectivo mensaje de error
     */
    public UsuarioEntidadDTO removerAsignacionEntidad(UsuarioEntidadDTO peticion) throws SpddExceptions, JsonProcessingException;

    /**
     * Metodo que trae la lista completa de entidades que el usuario tiene
     * asignado
     *
     * @param usuariosDTO usuario para consultar
     * @return Objeto DTO generico que contiene la lista de las entidades que el
     * usuario tiene asignado
     */
    public GenericoDTO obtenerEntidadesAsignados(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * Metodo que trae la lista de las entidades que el usuario puede ver
     *
     * @return Objeto DTO generico que contiene la lista de las entidades que el
     * usuario puede ver
     */
    public GenericoDTO obtenerEntidadesDisponibles() throws SpddExceptions;

    /**
     * Metodo que trae la lista completa de entidades que el usuario puede ver
     *
     * @return Objeto DTO generico que contiene la lista de las entidades que el
     * usuario puede ver
     */
    public GenericoDTO obtenerEntidadesTodas() throws SpddExceptions;
    
    /**
     * 
     * @return
     */
    public GenericoDTO obtenerTodosBancoProyectos();
    
    /**
     * 
     * @param codigo
     * @return
     */
    public EntidadDTO obtenerPorId(String codigo);
    
    /**
     * Peticion que permite obtener la entidad por el nombre de la entidad (codigoEntidad en seguridad)
     * con la informacion que hay en la base de datos de la SDP y seguridad.
     * @param nombre String que representa el nombre de la entidad (codigoEntidad en seguridad)
     * @return una entidad correspondiente a al nombre de la entidad
     */
    public EntidadDTO obtenerPorNombreEntidad(String nombre);
    
    
    /**
     * Peticion que permite validar si la entidad existe en la BD de SegPlan con respecto
     * a la base de datos de seguridad por medio del codigo, Si la entidad existe en la BD
     * de seguridad y no en SegPlan entonces la registrara en la BD de segplan
     * @param codigo string que representa el codigo de la entidad
     * @return una EntidadDTO con la informacion correspondiente
     */
    public EntidadDTO validarYRegistrarEntidadNoExistente(String codigo);
}
