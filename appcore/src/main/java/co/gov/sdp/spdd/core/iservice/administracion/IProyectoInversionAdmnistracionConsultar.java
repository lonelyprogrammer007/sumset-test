package co.gov.sdp.spdd.core.iservice.administracion;

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
public interface IProyectoInversionAdmnistracionConsultar {

    /**
     * Metodo para obtener la lista de proyectos de inversion asignados a un
     * usuario
     *
     * @param peticion Objeto DTO con los datos del usuario a buscar
     * @return Objeto Generico que contiene la lista de los proyectos asignados
     */
    public GenericoDTO obtenerProyectoInversionAsignados(UsuariosDTO peticion) throws SpddExceptions;

    /**
     * Metodo para obtener la lista de proyectos de inversion que se pueden
     * asignar
     *
     * @return Objeto Generico que contiene la lista de los proyectos
     * disponibles
     */
    public GenericoDTO obtenerProyectoInversionDisponibles() throws SpddExceptions;

    /**
     * Metodo para obtener la lista de todos los proyectos de inversion
     *
     * @return Objeto Generico que contiene la lista de todos los proyectos de
     * inversion
     */
    public GenericoDTO obtenerProyectoInversionTodos() throws SpddExceptions;

}
