package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvProducto;
import co.gov.sdp.spdd.data.model.ip.PotObra;

/**
 * Interface que contiene los metodos basicos de PagingAndSortingRepository y
 * otros para hacer transacciones relacionadas con la tabla BpProyInvPoblacion
 * de la BD
 * 
 * @author LUIS FIGUEROA
 * @version 1.0 02/06/2020
 */
@Transactional
@Repository
public interface IBpProyInvPoblacionRepo extends PagingAndSortingRepository<BpProyInvPoblacion, Long> {

	/**
	 * Metodo que permite obtener la lista de entidades del tipo
	 * "BpProyInvPoblacion" asociados a una entidad del tipo "BpProyectoInversion"
	 * 
	 * @param idProyectoInversion Long que representa el identificador del proyecto
	 *                            de inversion asociado
	 * @return Una lista de BpProyInvPoblacion con todos los registros
	 *         correspondientes
	 */
	@Query("SELECT pi FROM BpProyInvPoblacion pi WHERE pi.idProyInversion.idProyInversion = :idProyectoInversion")
	public Page<BpProyInvPoblacion> obtenerTodosPorIdProyectoInversion(
			@Param("idProyectoInversion") Long idProyectoInversion, Pageable paginador) throws SpddExceptions;

	/**
	 * Metodo que permite obtener un BpProyInvPoblacion con la comprobacion de clave
	 * de unicidad conpuesta por los parametros recibidos
	 * 
	 * @param idLsEtario      parte de la clave de unicidad
	 * @param idProyInversion parte de la clave de unicidad
	 * @return
	 */
	@Query("SELECT pi FROM BpProyInvPoblacion pi WHERE pi.idLsEtario.idArgumento=:idLsEtario and pi.idProyInversion.idProyInversion=:idProyInversion")
	public BpProyInvPoblacion obtenerPorIdLsEtarioYIdProyInv(@Param("idLsEtario") Long idLsEtario,
			@Param("idProyInversion") Long idProyInversion);

}
