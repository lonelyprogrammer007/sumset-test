package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEspecif;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvEspecif que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author SEBASTIAN
 * @version 1.0 28/05/2020
 */
public interface IBpProyInvEspecifServiceRepo  extends IOperacionesBasicasFacade<BpProyInvEspecif, Long>, Serializable {

}
