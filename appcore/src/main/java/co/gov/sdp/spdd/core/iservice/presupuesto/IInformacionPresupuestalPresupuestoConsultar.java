package co.gov.sdp.spdd.core.iservice.presupuesto;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IInformacionPresupuestalPresupuestoConsultar {

    /**
     * Metodo para obtener la lista de todos las informacion presupuestal
     *
     * @return Objeto Generico que contiene la lista de todas las informacion
     * presupuestal
     */
    public GenericoDTO obtenerInformacionPresupuestalTodos() throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws SpddExceptions
     * @throws JsonProcessingException 
     */
	public GenericoDTO obtenerPaginado(InformacionPresupuestalDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerInformacionPresupuestalPorPlanDesarrollo(Long id) throws SpddExceptions;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerInformacionPresupuestalPorEntidad(Long id) throws SpddExceptions;
	
	public GenericoDTO obtenerPorArchivoInfo(Long id);
}
