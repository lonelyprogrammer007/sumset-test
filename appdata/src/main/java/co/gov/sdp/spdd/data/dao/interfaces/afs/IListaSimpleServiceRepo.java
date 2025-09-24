package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ListaSimple;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IListaSimpleServiceRepo extends IOperacionesBasicasFacade<ListaSimple, Long>, Serializable  {
	
	/**
	 * 
	 * @param listaSimpleDTO
	 * @param inicio
	 * @param limite
	 * @return
	 */
   public FiltroDTO filtrarPorCampo(ListaSimpleDTO listaSimpleDTO,Long inicio, Integer limite) throws SpddExceptions;
}
