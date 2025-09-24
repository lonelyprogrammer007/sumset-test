package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.spdd.data.model.ip.PddMpAnualizar;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPddMpAnualizarServiceRepo extends IOperacionesBasicasFacade<PddMpAnualizar, Long> {

	public List<PddMpAnualizar> obtenerMpAnualizarPorMetaProducto(Long idMetaProducto);
}
