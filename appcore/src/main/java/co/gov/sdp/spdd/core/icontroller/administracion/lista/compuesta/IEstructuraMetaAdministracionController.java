package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de las estructuras metas para el modulo
 * de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEstructuraMetaAdministracionController {

    /**
     * Metodo para crear una estructura meta
     *
     * @param peticion Objeto DTO que contiene la informacion del componente de
     * gasto
     * @return Objeto DTO que contiene la informacion de la estructura meta
     * creado si la peticion se realiza satisfactoriamente, sino, se enviara el
     * objeto vacio con el respectivo mensaje de error
     */
    public EstructuraMetaDTO crearEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException;

   

    /**
     * Metodo para modificar estructura meta
     *
     * @param peticion Objeto DTO que contiene la informacion de la estructura
     * meta a modificar
     * @return Objeto DTO que contiene la informacion de la estructura meta si
     * la peticion se realiza satisfactoriamente, sino, se enviara el objeto
     * vacio con el respectivo mensaje de error
     */
    public EstructuraMetaDTO modificarEstructuraMeta(EstructuraMetaDTO peticion) throws SpddExceptions;

    /**
     * Metodo que trae la lista completa de estructura meta que el usuario puede
     * ver
     *
     * @return Objeto DTO generico que contiene la lista de estructura meta que
     * el usuario puede ver
     * @throws JsonProcessingException 
     */
    public GenericoDTO obtenerEstructuraMetaTodos(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException;

}
