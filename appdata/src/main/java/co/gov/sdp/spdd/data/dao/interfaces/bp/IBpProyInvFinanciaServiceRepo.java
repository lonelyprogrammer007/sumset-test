package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvFinancia que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author Jefferson Arenas
 * @version 1.0 02/06/2020
 */
public interface IBpProyInvFinanciaServiceRepo extends IOperacionesBasicasFacade<BpProyInvFinancia, Long>, Serializable {

	/**
	 * Metodo que permite obtener todas las relaciones entre  proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idFuente Long que representa el identificador dela fuente por el cual se va a filtra
	 * @return una lista de BpProyInvIniciativa
	 */
	public Page<BpProyInvFinancia> obtenerPorIdTodosProyInvFiancia(Long idProyInversion, Pageable paginador);
	
	
	/**
	 * Metodo que permite obtener los ProyInvFinancia por el Id de proyecto de inversion y por idLsFuente
	 * @param idLsFUente id del argumento ls asociado
	 * @param idProyectoInv id del proyecto de inversion
	 * @return BpProyInvFinanciaDTO con la informacion determinada
	 */
	public BpProyInvFinancia consultarProyInvFinanciaPorIdLSFuYIdProy(Long idLsFUente, Long idProyectoInv);
	
	/**
	 * Metodo que permite consultar los ProyInvFinancia por el Idfuente
	 * @param idFUente id del argumento ls asociado
	 * @return BpProyInvFinanciaDTO con la informacion determinada
	 */
	public BpProyInvFinancia consultarProyInvFinanciaPorId(Long idFuente);
}
