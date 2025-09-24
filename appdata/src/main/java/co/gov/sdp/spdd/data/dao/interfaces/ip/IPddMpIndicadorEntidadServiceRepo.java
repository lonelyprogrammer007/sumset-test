package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicadorEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpIndicadorEntidadServiceRepo extends IOperacionesBasicasFacade<PddMpIndicadorEntidad, Long> {

	/**
	 * 
	 * @param idIndProd
	 * @param pageable
	 * @return
	 */
	public Page<PddMpIndicadorEntidad> obtenerTodosIndicadoresMetaEntidad(Long idIndProd, Pageable pageable)
			throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidad validarIndicadorEntidad(String codigoEntidad, Long idIndProd) throws SpddExceptions;

	/**
	 * 
	 * @param idMetaProdInd
	 * @return
	 * @throws SpddExceptions
	 */
	public Long sumarPonderacionesEntidadesMetaProducto(Long idMetaProdInd) throws SpddExceptions;
}
