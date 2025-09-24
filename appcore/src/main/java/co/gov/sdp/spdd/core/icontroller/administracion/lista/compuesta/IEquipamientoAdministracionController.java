package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de los equipamientos para el modulo de
 * administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEquipamientoAdministracionController {

    /**
     * Metodo para crear un equipamento
     *
     * @param peticion Objeto DTO que contiene la informacion del componente de
     * gasto
     * @return Objeto DTO que contiene la informacion del equipamiento creado si
     * la peticion se realiza satisfactoriamente, sino, se enviara el objeto
     * vacio con el respectivo mensaje de error
     */
    public EquipamientoDTO crearEquipamiento(EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException;

   
    /**
     * Metodo para modificar equipamiento
     *
     * @param peticion Objeto DTO que contiene la informacion de la equipamiento
     * a modificar
     * @return Objeto DTO que contiene la informacion del equipamiento si la
     * peticion se realiza satisfactoriamente, sino, se enviara el objeto vacio
     * con el respectivo mensaje de error
     */
    public EquipamientoDTO modificarEquipamiento(EquipamientoDTO peticion) throws SpddExceptions;

    /**
     * Metodo que trae la lista completa de equipamiento que el usuario puede
     * ver
     *
     * @return Objeto DTO generico que contiene la lista de equipamiento que el
     * usuario puede ver
     * @throws JsonProcessingException 
     */
    public GenericoDTO obtenerEquipamientoTodos(EquipamientoDTO peticion) throws JsonProcessingException ;

}
