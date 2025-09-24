package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvPmr que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 06/06/2020
 */
public interface IBpProyInvPmrServiceRepo extends IOperacionesBasicasFacade<BpProyInvPmr, Long>, Serializable {
	
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y MRIndicador filtrados por sus campos
	 * @param BpProyInvPmrDTO objeto de tipo BpProyInvPmrDTO que contiene la informacion para filtrar
	 * @param inicio
	 * @param limite
	 * @return 
	 */
	public FiltroDTO obtenerTodosFiltrado(BpProyInvPmrDTO bpProyInvPmrDTO, Long inicio, Integer limite);
	
	/**
	 * Metodo que permite obtener un BpProyInvPmr por medio de los campos de unicidad de idIndMr e idProyInversion
	 * @param idIndMr Long que representa el identificador de MrIndicador
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	public BpProyInvPmr obtenerPorIdIndMrYIdProyInversion(Long idIndMr, Long idProyInversion);
}
