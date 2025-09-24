package co.gov.sdp.spdd.core.iservice.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que tiene todos los metodos asociados con un modificar una lista
 * simple
 *
 * @author Bryan Munoz
 *
 */
public interface IArgumentoListaSimpleModificar {

    /**
     * Metodo que modifica un argumento lista simple
     *
     * @param argumentoListaSimpleDTO objeto con campos a modificar de la bd
     * @return un codigo y mensaje de respueta
     * @throws JsonProcessingException 
     */
    public ArgumentoListaSimpleDTO modificarArgumento(ArgumentoListaSimpleDTO argumentoListaSimpleDTO) throws SpddExceptions, JsonProcessingException;

    /**
     * Metodo que permite cambiar el estado de un argumento
     *
     * @param id tipo long que hace referencia al id que se busca cambiar el
     * estado
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ArgumentoListaSimpleDTO modificarEstado(Long id) throws SpddExceptions;
}
