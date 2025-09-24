package co.gov.sdp.spdd.data.operacionesbasicas.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bryan Munnoz
 *
 * @param <T>
 * @param <ID>
 */
public interface IOperacionesBasicasFacade<T, I extends Serializable> {

    T guardar(T entity);

    /**
     * Metodo que elimna de la bd
     *
     * @param id del objeto que se desea eliminar
     */
    void eliminar(I id);

    /**
     * Metodo que retorna un solo objeto de una lista por el id
     *
     * @param id el objeto que se desea buscar en la lista
     * @return el objeto encontradod e la lista
     */
    T obtener(I id);

    /**
     * Metodo que retorna un resultList
     *
     * @return un resulList de un objeto
     */
    List<T> obtenerTodos();

}