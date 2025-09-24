package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvIniciativa que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 14/05/2020
 */
public interface IBpProyInvIniciativaServiceRepo extends IOperacionesBasicasFacade<BpProyInvIniciativa, Long>, Serializable {

	/**
	 * Metodo que permite obtener todas las relaciones entre iniciativa ciudadana y proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return una lista de BpProyInvIniciativa
	 */
	public List<BpProyInvIniciativa> obtenerTodosPorIdProyInversion(Long idProyecto);
	
	/**
	 * Metodo que permite elimnar todas las relaciones entre iniciativa ciudadana y proyecto de inversion 
	 * pasadas como lista en el parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 */
	public void eliminarTodos(List<BpProyInvIniciativa> listaBpProyInvIniciativa);
	
	/**
	 * Metodo que permite obtener todas una relacione entre iniciativa ciudadana y proyecto de inversion segun
	 * el identificador del proyecto de inversion y el identificador de la iniciativa pasadas como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @param idInciativa Long que representa el identificador de la iniciativa ciudadana por el cual se va a filtar
	 * @return un objeto de tipo sBpProyInvIniciativa
	 */
	public BpProyInvIniciativa obtenerPorIdIniciativaYIdProyInversion(Long idIniciativa, Long idProyecto);

	/**
	 * Metodo que permite obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	public List<BpProyInvIniciativa> obtenerTodosPorIdIniciativa(Long idIniciativa);
}
