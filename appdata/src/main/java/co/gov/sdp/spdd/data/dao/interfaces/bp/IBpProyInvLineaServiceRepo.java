package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvLinea que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface IBpProyInvLineaServiceRepo extends IOperacionesBasicasFacade<BpProyInvLinea, Long>, Serializable {
	
	/**
	 * Metodo que permite obtener una lista de BpProyInvTipo relacionado a un proyecto de inversion
	 * @param idProyecto Long que representa el identificador por le cual se va a buscar los BpProyInvTipo 
	 * @return una lista BpProyInvTipo con la informacion correspondiente
	 */
	public List<BpProyInvLinea> obtenerPorIdProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por medio de la unicidad entre el identificador de la linea de inversion y 
	 * el identificador del proyecto de inversion
	 * @param idLineaInversion Long que representa el identificador de la linea de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	public BpProyInvLinea obtenerPorIdLineaInversionYIdProyInversion(Long idLineaInversion, Long idProyecto);
	
}
