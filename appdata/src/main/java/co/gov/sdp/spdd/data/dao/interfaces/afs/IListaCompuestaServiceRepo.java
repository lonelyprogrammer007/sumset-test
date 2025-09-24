package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.spdd.data.model.afs.ListaCompuesta;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IListaCompuestaServiceRepo extends IOperacionesBasicasFacade<ListaCompuesta, Long>, Serializable  {

	/**
	 * 
	 * @param listaCompuestaDTO
	 * @param inicio
	 * @param limite
	 * @return
	 */
	public FiltroDTO filtrarPorCampo(ListaCompuestaDTO listaCompuestaDTO, Long inicio, Integer limite);

}
