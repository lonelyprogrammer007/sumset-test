package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpProyInvAporte de la BD
 * 
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
@Transactional
@Repository
public interface IBpProyInvAporteRepo extends CrudRepository<BpProyInvAporte, Long> {
	
	/**
	 * Metodo que permite obtener un BpProyInvAporte por el indentificador del aporte y el identificador del proyecto
	 * @param idAporte Long que representa el identificador del aporte
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	@Query("SELECT pia FROM BpProyInvAporte pia WHERE pia.idAporte.idAporte=:aporte AND pia.idProyInversion.idProyInversion=:proyecto")
	public BpProyInvAporte obtenerPorIdAporteYIdProyInversion(@Param("aporte")Long idAporte,@Param("proyecto") Long idProyInversion);
	
	/**
	 * Metodo que permite obtener todos BpProyInvAporte por el identificador del proyecto paginados
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador objeto de tipo pageable que contiene la informacion para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	@Query("SELECT pia FROM BpProyInvAporte pia WHERE pia.idProyInversion.idProyInversion=:proyecto")
	public Page<BpProyInvAporte> obtenerTodosPorIdProyInversionPaginado(@Param("proyecto") Long idProyInversion, Pageable paginador);
	
	/**
	 * Metodo que permite eliminar todos los Aportes Ciudadanos que hayan sido cargados desde
	 * un archivo plano y que esten relacionados con el proyecto de inversion pasado como parametro.
	 * Es decir los registros en la tabla intermedia BpProyInvAporte
	 * @param idProyecto Long que represente el identificado del proyecto al cual se le eliminaran todos lo
	 * apotes ciudadanos cargados desde un archivo que esten relacionados.
	 * @throws SpddExceptions
	 */
	@Query("SELECT pia FROM BpProyInvAporte pia WHERE pia.idProyInversion.idProyInversion=:proyecto AND pia.idAporte.idArchivo <>  -1")
	public List<BpProyInvAporte> obtenerTodosCargadoArchivoPorIdProyInversion(@Param("proyecto") Long idProyInversion);


}
