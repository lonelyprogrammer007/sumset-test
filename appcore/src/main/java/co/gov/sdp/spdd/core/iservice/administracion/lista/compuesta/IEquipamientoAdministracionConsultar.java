package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de consulta para el
 * modulo de admnistracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IEquipamientoAdministracionConsultar {

    /**
     * Metodo para obtener la lista de todos los equipamientos
     *
     * @return Objeto Generico que contiene la lista de todos los equimientos
     */
    public GenericoDTO obtenerEquipamientoTodos() throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     * @throws JsonProcessingException 
     */
	public GenericoDTO obtenerPaginado(EquipamientoDTO peticion) throws SpddExceptions, JsonProcessingException;

}
