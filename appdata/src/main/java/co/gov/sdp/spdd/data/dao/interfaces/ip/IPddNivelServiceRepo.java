package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a PddNivel que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 03/03/2020
 */
public interface IPddNivelServiceRepo extends IOperacionesBasicasFacade<PddNivel, Long>, Serializable {

	/**
	 * Metodo que permite obtener los niveles de un pdd por el identificador del pdd
	 * @param idPlan Long que representa el identificador del pdd
	 * @return una lista de PddNivel correspondiente a los niveles del pdd
	 * @throws SpddExceptions
	 */
	public List<PddNivel> obtenerPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener un nivel de un pdd por su codigo de nivel (1,2,3...) y por su plan de desarrollo
	 * @param nivel Long que representa el codigo del nivel (1,2,3...)
	 * @param idPlan Long que representa el idenficador del plan de desarrollo
	 * @return un objeto PddNivel con la informacion correspondiente o null en caso contrario
	 * @throws SpddExceptions
	 */
	public PddNivel obtenerPorNivelYIdPlanDesarrollo(Long nivel, Long idPlan) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar el nivel correspondiente a un codigo de nivel y a un plan de desarrollo en especifico que esten desbalanceados
	 * @param nivel Long que representa el nivel que se desea buscar (1,2,3...)
	 * @param idPlanDesarrollo Long que representa el identificador del plan que se desea buscar
	 * @return un objeto de tipo PddNivel con la informacion del nivel o null en caso contrario.
	 */
	public List<PddNivel> obtenerPddNivelDesbalanceadosPorNivelYIdPlanDesarrollo(Long nivel, Long idPlanDesarrollo);

	
}
