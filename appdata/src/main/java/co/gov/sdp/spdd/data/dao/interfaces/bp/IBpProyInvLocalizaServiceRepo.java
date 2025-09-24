package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvLocaliza que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author SEBASTIAN
 * @version 1.0 17/05/2020
 */
public interface IBpProyInvLocalizaServiceRepo extends IOperacionesBasicasFacade<BpProyInvLocaliza, Long>, Serializable{

	/**
	 * Metodo que permite obtener todas las relaciones entre territorializacion y proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return una lista de BpProyInvLocaliza
	 */
	public List<BpProyInvLocaliza> obtenerTodosPorIdProyInversion(Long idProyecto);
	
	/**
	 * Metodo que permite eliminar todas las relaciones entre territorializacion y proyecto de inversion 
	 * pasadas como lista en el parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 */
	public void eliminarTodos(List<BpProyInvLocaliza> lstBpProyInvLocaliza);
	

}
