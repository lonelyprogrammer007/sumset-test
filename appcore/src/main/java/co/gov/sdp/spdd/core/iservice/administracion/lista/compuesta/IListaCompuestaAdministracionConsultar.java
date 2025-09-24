package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de admnistracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IListaCompuestaAdministracionConsultar {

    /**
     * Metodo para obtener la lista de todos los proyectos de inversion
     *
     * @return Objeto Generico que contiene la lista de todos los proyectos de
     * inversion
     */
    public GenericoDTO obtenerListaCompuestaTodos() throws SpddExceptions;

    /**
     * Metodo para obtener una lista por id
     *
     * @param idListaCompuesta id de la lista compuesta que se desea obtener
     * @return un objeto tipo dto con un codgio y mensaje de respuesta
     */
    public ListaCompuestaDTO obtenerLista(Long idListaCompuesta) throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     */
	public GenericoDTO obtenerPaginado(ListaCompuestaDTO peticion) throws SpddExceptions;
}
