package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvAnualiza que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author Jefferson Arenas
 * @version 1.0 10/06/2020
 */
public interface IBpProyInvAnualizaServiceRepo extends IOperacionesBasicasFacade<BpProyInvAnualiza, Long>, Serializable {

	
	/**
	 * Metodo que permite obtener todos los BpProyInvAnualiza  segun
	 * el identificador fuente de inversion pasado como parametro
	 * @param idFuente Long que representa el identificador dela fuente por el cual se va a filtra
	 * @return una lista de BpProyInvIniciativa
	 */
	public Page<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualiza(Long idFuente, Pageable paginador);
	
	/**
	 * Metodo que permite eliminar todos los ProyInvAnualiza por
	 * Id de la fuente asociada
	 * @param idFuente el id de la fuente a eliminar
	 */
	public void eliminarTodosPorIdFUente(List<BpProyInvAnualiza> data);
	
	
	public List<BpProyInvAnualiza> obtenerPorIdTodosProyInvAnualizacion (Long idFuente);
}
