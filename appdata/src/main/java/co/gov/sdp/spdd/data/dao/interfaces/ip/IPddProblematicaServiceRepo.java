/**
 * 
 */
package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * @author Jose Alvaro
 *
 */
public interface IPddProblematicaServiceRepo extends IOperacionesBasicasFacade<PddProblematica, Long>, Serializable {

	/**
	 * 
	 * @param argumento
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(PddProblematicaDTO argumento) throws SpddExceptions;
	
	/**
	 * 
	 * @param idCompromiso
	 * @param problematica
	 * @return
	 * @throws SpddExceptions
	 */
	public PddProblematica validarLlaveDeNegocio (Long idCompromiso, String problematica) throws SpddExceptions;
	
	public List<PddProblematica> consultarPorIdCompromiso(Long idCompromiso) throws SpddExceptions;
}
