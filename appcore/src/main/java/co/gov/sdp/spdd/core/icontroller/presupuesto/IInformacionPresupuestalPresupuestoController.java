package co.gov.sdp.spdd.core.icontroller.presupuesto;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de las informacion presupuestal para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IInformacionPresupuestalPresupuestoController {

    /**
     * Metodo para crear una informacion presupuestal
     *
     * @param peticion Objeto DTO que contiene la informacion de la informacion
     * presupuestal
     * @return Objeto DTO que contiene la informacion de la informacion
     * presupuestal creada si la peticion se realiza satisfactoriamente, sino,
     * se enviara el objeto vacio con el respectivo mensaje de error
     * @throws JsonProcessingException 
     */
    public InformacionPresupuestalDTO crearInformacionPresupuestal(InformacionPresupuestalDTO peticion) throws SpddExceptions, JsonProcessingException;

    /**
     * Metodo para modificar informacion presupuestal
     *
     * @param peticion Objeto DTO que contiene la informacion de la informacion
     * presupuestal a modificar
     * @return Objeto DTO que contiene la informacion de la informacion
     * presupuestal si la peticion se realiza satisfactoriamente, sino, se
     * enviara el objeto vacio con el respectivo mensaje de error
     */
    public InformacionPresupuestalDTO modificarInformacionPresupuestal(InformacionPresupuestalDTO peticion) throws SpddExceptions;
    
    /**
     * Metodo para eliminar informacion presupuestal
     *
     * @param idInformacionPresupuestal Long que representa el identificador de la informacion presupuestal que se desea eliminar
     * @return Objeto DTO que contiene la informacion de la informacion
     * presupuestal si la peticion se realiza satisfactoriamente, sino, se
     * enviara el objeto vacio con el respectivo mensaje de error
     */
    public InformacionPresupuestalDTO eliminarInformacionPresupuestal(Long idInformacionPresupuestal);

    
    /**
     * Metodo que trae la lista completa de informacion presupuestal que el
     * usuario puede ver
     *
     * @return Objeto DTO generico que contiene la lista de informacion
     * presupuestal que el usuario puede ver
     */
//    public GenericoDTO obtenerInformacionPresupuestalTodos() throws SpddExceptions;

    /**
     * 
     * @param peticion
     * @return
     * @throws JsonProcessingException 
     */
	public GenericoDTO obtenerTodos(InformacionPresupuestalDTO peticion) throws JsonProcessingException;

	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerInformacionPresupuestalPorEntidad(Long codigo) throws SpddExceptions;

	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerInformacionPresupuestalPorPlanDesarrollo(Long codigo) throws SpddExceptions;
	
	
	public GenericoDTO obtenerPorArchivoInfo(Long id);
}
