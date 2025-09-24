package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Munoz
 *
 */
public interface IObraConcretaServiceRepo extends IOperacionesBasicasFacade<PddObraConcreta, Long> {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public List<PddObraConcreta> buscarPorMeta(Long id) ;

	/**
	 * 
	 * @param idMeta
	 * @param obraConcreta
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcreta validarCampos(Long idMeta, String obraConcreta) throws SpddExceptions;

}
