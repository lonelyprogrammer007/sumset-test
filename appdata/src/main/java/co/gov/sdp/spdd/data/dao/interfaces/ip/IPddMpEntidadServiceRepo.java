package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMpEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IPddMpEntidadServiceRepo extends IOperacionesBasicasFacade<PddMpEntidad, Long> {

	public List<PddMpEntidad> obtenerTodasMpEntidadPorMetaProducto(Long idMetaProducto, Pageable pageable)
			throws SpddExceptions;

	public PddMpEntidad validarMpEntidadPorMetaProductoYEntidad(String codigoEntidad, Long idMetaProducto)
			throws SpddExceptions;
}
