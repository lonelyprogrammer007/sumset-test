package co.gov.sdp.spdd.data.irepositorio.bp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla BpAporteCiudadano de la BD
 * 
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
@Transactional
@Repository
public interface IBpAporteCiudadanoRepo extends CrudRepository<BpAporteCiudadano, Long> {
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano pero que aun no tienen una
	 * relacion con el proyecto BpProyectoInversion indicado en el parametro 
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	@Query("SELECT ac FROM BpAporteCiudadano ac WHERE ac.idAporte NOT IN (SELECT pia.idAporte.idAporte FROM BpProyInvAporte pia WHERE pia.idProyInversion.idProyInversion=:idProyecto)")
	public List<BpAporteCiudadano> obtenerTodosSinRelacionConProyectoInversion(@Param("idProyecto") Long idProyecto);
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	@Query("SELECT ac FROM BpAporteCiudadano ac WHERE ac.idArchivo.idArchivo <> -1")
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivos();
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano y que estan relacionados con el proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	@Query("SELECT ac FROM BpAporteCiudadano ac WHERE ac.idArchivo.idArchivo <> -1 AND ac.idAporte IN (SELECT pia.idAporte.idAporte FROM BpProyInvAporte pia WHERE pia.idAporte.idArchivo.idArchivo <> -1 AND pia.idProyInversion.idProyInversion=:idProyecto)")
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(@Param("idProyecto") Long idProyecto);
	
	/**
	 * Metodo que permite obtener un BpAporteCiudadano mediante la combinacion de campos pasados como parametros
	 * @param accion String que representa la accion por la cual se va a buscar
	 * @param fuente String que representa la fuente por la cual se va a buscar
	 * @param idPddNivelAtributo Long que representa el identificador del nivels atributo por el cual se va a buscar
	 * @return Un objeto BpAporteCiudadano con la informacion correspondientes.
	 */
	@Query("SELECT ac FROM BpAporteCiudadano ac WHERE LOWER(ac.accion) = LOWER(:accion) AND LOWER(ac.fuente) = LOWER(:fuente) AND ac.idNivelAtributoPdd.idAtributo = :nivelPdd ")
	public BpAporteCiudadano obtenrePorAccionYFuenteYIdNivelPdd(@Param("accion") String accion, @Param("fuente") String fuente, @Param("nivelPdd") Long idPddNivelAtributo);
	
	/**
	 * Metodo que permite ordenar los aportes ciudadanos ordenados ascendentemente por el consecutivo
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes 
	 */
	@Query("SELECT ac FROM BpAporteCiudadano ac ORDER BY ac.consecutivo ASC")
	public List<BpAporteCiudadano> obtenerTodosOrdenadorPorConsecutivoASC();
}
