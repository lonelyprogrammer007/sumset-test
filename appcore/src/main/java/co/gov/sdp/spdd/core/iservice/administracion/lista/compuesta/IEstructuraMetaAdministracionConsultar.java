package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de admnistracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEstructuraMetaAdministracionConsultar {

    /**
     * Metodo para obtener la lista de todas las estructuras de metas
     *
     * @return Objeto Generico que contiene la lista de todas las estructuras de
     * metas
     */
    public GenericoDTO obtenerEstructuraMetaTodos() throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     * @throws JsonProcessingException 
     */
	public GenericoDTO obtenerPaginado(EstructuraMetaDTO peticion) throws SpddExceptions, JsonProcessingException;

}
