package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPotServiceRepo extends IOperacionesBasicasFacade<Pot, Long> {

	
	public FiltroDTO filtrarPorCampo(PotDTO peticion,Long inicio, Integer limite);
	
	public Pot obtenerPorCodigo(String codigoPot);
	
	/**
	 * Metodo que permite obtener el POT por estado
	 * @param estado estado del por por el cual se quiere filtar (Vigente | Finalizado)
	 * @return una lista con la informacion correspondiente
	 */
	public List<Pot> obtenerTodosPorEstado(String estado);
}
