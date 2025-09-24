package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvEtnico que contiene
 * la declaracion de metodos para ser implementados y por consiguiente ser
 * utilizados en el facade
 * 
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
public interface IBpProyInvEtnicoServiceRepo extends IOperacionesBasicasFacade<BpProyInvEtnico, Long>, Serializable {

	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la
	 * tabla BpProyInvEtnico. Estos registros ya tienene una relación con el
	 * proyecto de inversión.
	 * 
	 * @param idProyecto Long que representa el identificador del
	 *                   BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpProyInvEtnico con todos los registros correspondientes
	 */
	public Page<BpProyInvEtnico> obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(Long idPoblacion,
			Pageable paginador) throws SpddExceptions;

	/**
	 * Metodo que permite validar unicidad con las llaves ingresadas como parametro
	 * 
	 * @param idLsEtnico
	 * @param idPoblacion
	 * @return
	 */
	public BpProyInvEtnico obtenerPorIdLsEtnicoYIdPoblacion(Long idLsEtnico, Long idPoblacion);
}
