package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a una competencia asociada de un plan de desarrollo que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 19/03/2020
 */
public interface IPddCompetenciaAsociadaServiceRepo extends IOperacionesBasicasFacade<PddCompetenciaAsociada, Long>, Serializable {

	/**
	 * Metodo que permite buscar las PddCompetenciaAsociada relacionados a un sector
	 * @param idSector Long que representa el identificador del sector por el cual se va a buscar
	 * @return una lista de tipo PddCompetenciaAsociada correspondiente a la busqueda o null
	 * @throws SpddExceptions
	 */
	public List<PddCompetenciaAsociada> obtenerPorIdSector(Long idSector)throws SpddExceptions;
	
	/**
	 * Metodo que permite buscar un PddCompetenciaAsociada que corresponda a los identificadores del secto y LsCompetencia que se pasan como parametro
	 * @param idSector Long que representa el identificador del sector por el cual se va a buscar o filtrar
	 * @param idLsCompetencia Long que representa el identificador de LsCompetencia por el cual se va a buscar o filtrar
	 * @return un objeto de tipo PddCompetenciaAsociada con la informacion correspodiente
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociada obtenerPorIdSectorYIdCompetencia(Long idSector, Long idLsCompetencia) throws SpddExceptions;
	
}
