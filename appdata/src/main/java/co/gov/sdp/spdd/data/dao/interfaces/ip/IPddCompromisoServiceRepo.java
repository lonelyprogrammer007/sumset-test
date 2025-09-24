package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a pdd compromiso que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 11/03/2020
 */
public interface IPddCompromisoServiceRepo extends IOperacionesBasicasFacade<PddCompromiso, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar todos los PddCompromiso aplicando un filtro
	 * @param argumento objeto de tipo PddCompromisoDTO con la informacion necesaria para el filtrado
	 * @return un objeto tipo FiltroDTO con los PddCompromisos conrrespondientes al filtro
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(PddCompromisoDTO argumento) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar los pddCompromiso relacionados a un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo por el cual se va a buscar
	 * @return una lista de tipo PddCompromiso correspondiente a la busqueda o null
	 * @throws SpddExceptions
	 */
	public List<PddCompromiso> obtenerTodosPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar un PddCompromiso relacionado a un compromisoEstrategico y a un plan de desarrollo
	 * @param idEstrategico Long que represnta el identificador del compromisoEstrategico por el cual se va a buscar
	 * @param idPlan Long que representa el identiricador del plan de desarrollo por el cual se va a buscar
	 * @return un objeto de tipo PddCompromiso correspondiente a la informacion o null

	 * @throws SpddExceptions
	 */
	public PddCompromiso obtenerPorIdEstrategicoYIdPlanDesarrollo(Long idEstrategico, Long idPlan) throws SpddExceptions;
	
	/**
	 * Método que permite consultar un PddCompromiso por Id Compromiso.
	 * 
	 * @param idCompromiso
	 * @return
	 * @throws SpddExceptions
	 */
	public PddCompromiso obtenerPorID(Long idCompromiso) throws SpddExceptions;
}
