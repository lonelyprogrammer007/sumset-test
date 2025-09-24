package co.gov.sdp.spdd.core.iservice.administracion.ls;

import org.hibernate.exception.GenericJDBCException;

import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja todos los metodos de modificar asosciados con una lista
 * simple
 *
 * @author Bryan Munoz
 *
 */
public interface IAdministracionListaSimpleModificar {

    /**
     * Metodo que permite modificar una lista simple
     *
     * @param listaSimpleDTO objeto dto a modificar tipo listaSimpleDTO
     * @return el objeto dto con un codigo y mensaje de respuesta
     */
    public ListaSimpleDTO modificarListaSimple(ListaSimpleDTO listaSimpleDTO) throws SpddExceptions,GenericJDBCException;
}
