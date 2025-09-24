package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvPolitica que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 06/06/2020
 */
public interface IBpProyInvPoliticaServiceRepo extends IOperacionesBasicasFacade<BpProyInvPolitica, Long>, Serializable {
	
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y politica publica filtrados por sus campos
	 * @param bpProyInvPoliticaDTO objeto de tipo BpIniciativaCiudadanaDTO que contiene la informacion para filtrar
	 * @param inicio
	 * @param limite
	 * @return 
	 */
	public FiltroDTO obtenerTodosFiltrado(BpProyInvPoliticaDTO bpProyInvPoliticaDTO,Long inicio, Integer limite);
	
	/**
	 * Metodo que permite obtener un BpProyInvPolitica por medio de la llave de unicidad entre el identificador de la politica
	 * y el identificador del proyecto de inversion
	 * @param idPolPub Long que representa el identificador de la politica publica porl a que se va filtrar
	 * @param idProyInversion Long que represneta el identiricador del proyecto de inversion por el cual se va filtrar
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	public BpProyInvPolitica obtenerPorIdPolPubYIdProyInversion(Long idPolPub, Long idProyInversion);

}
