package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a PdlNivel que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author SEBASTIAN
 * @version 1.0 22/04/2020
 */
public interface IPdlNivelServiceRepo extends IOperacionesBasicasFacade<PdlNivel, Long>, Serializable {
	
	/**
	 * Metodo que permite consultar los niveles correspondientes a un plan de desarrollo local
	 * @param idPlanLocal Long que representa el identificador del plan de desarrollo local del cual se quieren
	 * obtener los niveles
	 * @return una lista de PdlNivel que contiene todos los niveles correspondientes al plan de desarrollo local.
	 * @throws SpddExceptions 
	 */
	public List<PdlNivel> obtenerPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar el nivel correspondiente a un codigo de nivel y a un plan de desarrollo local en especifico
	 * @param nivel Long que representa el nivel que se desea buscar (1,2,3...)
	 * @param idPlanLocal Long que representa el identificador del plan que se desea buscar
	 * @return un objeto de tipo PdlNivel con la informacion del nivel o null en caso contrario.
	 * @throws SpddExceptions 
	 */
	public PdlNivel obtenerPdlNivelPorNivelYIdPlanLocal(Long nivel, Long idPlanLocal) throws SpddExceptions;

}
