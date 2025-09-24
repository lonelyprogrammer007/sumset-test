package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.model.bp.BpProyInvMetaPlan;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvMetaPlan que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
public interface IBpProyInvMetaPlanServiceRepo extends IOperacionesBasicasFacade<BpProyInvMetaPlan, Long>, Serializable {

	/**
	 * Metodo que permite la meta por objetivo especifico el identificador del proyecto de inversion pasado como parametro
	 * @param idProyObjEspecif Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un objeto BpProyInvMetaPlan
	 */
	public BpProyInvMetaPlan obtenerPorIdProyInvEspecif(Long idProyObjEspecif);
	
}
