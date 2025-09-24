package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de los proyectos de inversion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IListaCompuestaAdministracionController {

    /**
     * Metodo que trae la lista completa de lista compuesta que el usuario puede
     * ver
     *
     * @return Objeto DTO generico que contiene la lista de listas compuestas
     * que el usuario puede ver
     */
    public GenericoDTO obtenerListaCompuestaTodos(ListaCompuestaDTO peticion) throws SpddExceptions;

    /**
     * 
     * @param id
     * @return
     * @throws SpddExceptions
     */
    public ListaCompuestaDTO obtenerPorId(Long id) throws SpddExceptions;

}
