package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyectoInversion que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface IBpProyectoInversionServiceRepo extends IOperacionesBasicasFacade<BpProyectoInversion, Long>, Serializable {
	
	/**
	 * Metodo que permite filtrar por algun campo en especifico y paginar los registros de BpProyectoInversion
	 * @param bpProyectoInversionDTO objeto DTO de tipo BpProyectoInversionDTO que contiene la informacion para filtrar y paginar
	 * @param inicio long que indica la pagina
	 * @param limite Integer que indica la cantidad de elementos a filtrar
	 * @return un objeto de tipo FiltroDTO con la informacion filtrada
	 * @throws SpddExceptions Excepcion propia que puede ser lanzada
	 */
	public FiltroDTO filtrarPorCampo(BpProyectoInversionDTO bpProyectoInversionDTO, Long inicio, Integer limite) throws SpddExceptions;

}
