package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.EstructuraMeta;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IEstructuraMetaServiceRepo extends IOperacionesBasicasFacade<EstructuraMeta, Long>, Serializable  {

	/**
	 * 
	 * @param peticion
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(EstructuraMetaDTO peticion, Long inicio, Integer limite) throws SpddExceptions;

	/**
	 * 
	 * @param unidadMedida
	 * @param verbo
	 * @return
	 */
	public EstructuraMeta validarUnidadMedidaYVerbo(Long unidadMedida, Long verbo) throws SpddExceptions;
}
