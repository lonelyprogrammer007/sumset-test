package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import co.gov.sdp.spdd.data.model.view.VistaBpProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a VistaBpProyectoInversion que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface IVistaBpProyectoInversionServiceRepo extends IOperacionesBasicasFacade<VistaBpProyectoInversion, Long>, Serializable {

}
