package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.HisVPddCompromiso;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface que contiene la declaracion de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
public interface IHisVPddCompromisoServiceRepo extends IOperacionesBasicasFacade<HisVPddCompromiso, Long>, Serializable {
	
	/**
	 * Metodo que permite filtrar por algun campo en especifico de HisVPddCompromisoDTO
	 * @param hisVPddCompromisoDTO objeto DTO de tipo HisVPddCompromisoDTO que contiene la informacion para filtrar.
	 * @param inicio
	 * @param limite
	 * @return un objeto de tipo FiltroDTO con tal la informacion filtrada
	 * @throws SpddExceptions Excepcion propia que puede ser lanzada
	 */
	public FiltroDTO filtrarPorCampo(HisVPddCompromisoDTO hisVPddCompromisoDTO, Long inicio, Integer limite) throws SpddExceptions;

}
