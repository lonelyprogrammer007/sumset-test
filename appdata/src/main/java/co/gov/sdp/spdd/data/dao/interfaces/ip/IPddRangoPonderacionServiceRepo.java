package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPddRangoPonderacionServiceRepo extends IOperacionesBasicasFacade<PddRangoPonderacion, Long>, Serializable {

	/**
     * Metodo que permite consultar los PddRangoPonderacion de un plan de desarrollo en especifico
     * @param idPdd Long que representa el identificador del plan de desarrollo por el cual se quiere hacer la busqueda
     * @return una lista de PddRangoPonderacion correspondiente a la busqueda o null en caso contrario
     */
	public List<PddRangoPonderacion> obtenerPddRangoPonderacionPorIdPdd(Long idPdd);
}
