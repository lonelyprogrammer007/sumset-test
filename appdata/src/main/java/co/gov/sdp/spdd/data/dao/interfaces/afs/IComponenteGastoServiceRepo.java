package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ComponenteGasto;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IComponenteGastoServiceRepo extends IOperacionesBasicasFacade<ComponenteGasto, Long>, Serializable  {
	
	/**
	 * 
	 * @param componente
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(ComponenteGastoDTO componente, Long inicio, Integer limite) throws SpddExceptions;
	
	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @return
	 */
	public ComponenteGasto buscarPorCodigoYNombre(Long codigo, String nombre);
}
