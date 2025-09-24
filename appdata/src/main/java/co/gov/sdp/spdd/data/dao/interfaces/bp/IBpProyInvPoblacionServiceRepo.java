package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvPoblacion que
 * contiene la declaracion de metodos para ser implementados y por consiguiente
 * ser utilizados en el facade
 * 
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
public interface IBpProyInvPoblacionServiceRepo
		extends IOperacionesBasicasFacade<BpProyInvPoblacion, Long>, Serializable {

	/**
	 * Metodo que permite obtener los grupos etarios paginados
	 * @param idProyectoInversion
	 * @param paginador
	 * @return
	 * @throws SpddExceptions
	 */
	public Page<BpProyInvPoblacion> obtenerTodosGruposEtarios(Long idProyectoInversion,
			Pageable paginador) throws SpddExceptions;

	/**
	 * Metodo que permite validar unicidad con las llaves ingresadas como parametro
	 * 
	 * @param idLsEtario
	 * @param idProyInv
	 * @return
	 */
	public BpProyInvPoblacion obtenerPorIdLsEtarioYIdProyInv(Long idLsEtario, Long idProyInv);

}
