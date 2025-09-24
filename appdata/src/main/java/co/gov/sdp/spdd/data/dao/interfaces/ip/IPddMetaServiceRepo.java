package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMeta;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPddMetaServiceRepo extends IOperacionesBasicasFacade<PddMeta, Long>, Serializable {

	/**
	 * 
	 * @param idEspecifico
	 * @return
	 * @throws SpddExceptions
	 */
	public List<PddMeta> consultarMetasCompromistoEstrategico(Long idEspecifico) throws SpddExceptions;

	/**
	 * 
	 * @param meta
	 * @param especifico
	 * @param tipo
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMeta validarMetaTipoYEspecifico(String meta, Long especifico, Long tipo) throws SpddExceptions;

	/**
	 * 
	 * @param idCompromiso
	 * @throws SpddExceptions
	 */
	public void eliminarTodasLasMetasDeCompromisos(Long idCompromiso) throws SpddExceptions;

}
