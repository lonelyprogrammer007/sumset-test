package co.gov.sdp.spdd.data.irepositorio.afs;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;

/**
 * Esta es la interface de argumento lista simple que cumple como repo
 *
 * @author Bryan Munoz
 *
 */
@Transactional
@Repository
public interface IArgumentoListaSimpleRepo extends CrudRepository<ArgumentoListaSimple, Long>, Serializable  {

	/**
	 * Consulta que obtiene los argumentos lista simples dada una lista simple
	 *
	 * @param id lista simple
	 * @return lista de argumentos simples pertenecientes a una lista simple
	 */
	@Query("SELECT c FROM ArgumentoListaSimple c WHERE c.idListaSimple.idListaSimple = :id ORDER BY c.resultado ASC")
	public List<ArgumentoListaSimple> obtenerArgumentoPorLista(@Param("id") Long id) throws SpddExceptions;

	/**
	 * Consulta que obtiene los argumentos por el nombre de la lista simple
	 *
	 * @param nombre nombre de la lista simple
	 * @return una lista de argumentos dado el nombre de una lista simple
	 */
	@Query("SELECT c FROM ArgumentoListaSimple c JOIN ListaSimple ls on ls.idListaSimple=c.idListaSimple.idListaSimple  WHERE ls.nombre = :nombre AND c.estado=1 ORDER BY c.resultado ASC")
	public List<ArgumentoListaSimple> obtenerArgumentoPorNombre(@Param("nombre") String nombre) throws SpddExceptions;
	
	/**
	 * 
	 * @param idListaSimple
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT als FROM ArgumentoListaSimple als WHERE als.idListaSimple.idListaSimple = :idListaSimple AND lower(als.argumento) = :argumento")
	public ArgumentoListaSimple findByIdListaSimple(@Param("idListaSimple") Long idListaSimple,@Param("argumento") String argumento) throws SpddExceptions;
	
	/**
	 * Consulta que obtiene los agumentos correspondientes a las tematicas que estan relacionadas con un plan de desarrollo
	 * @param idPlan Long que representa el identificador del plan de desarrollo del cual se necesitan las tematicas
	 * @return una lista de ArgumentoListaSimple que representa la lista de tematicas correspondiente al plan de desarrollo
	 * @throws SpddExceptions
	 */
	@Query("SELECT DISTINCT als FROM ArgumentoListaSimple als JOIN CompromisoEstrategico comp_estra on als.idArgumento = comp_estra.idLsTematica.idArgumento JOIN PddCompromiso pdd_comp ON comp_estra.idCompromiso = pdd_comp.idEstrategico.idCompromiso WHERE pdd_comp.idPlanDesarrollo.idPlanDesarrollo = :idPlan")
	public List<ArgumentoListaSimple> obtenerArgumentoPorIdPdd(@Param("idPlan") Long idPlan) throws SpddExceptions;
	
	/**
	 * Consulta que obtiene un argumento lista simple por id.
	 * 
	 * @param idArgumento
	 * @return
	 * @throws SpddExceptions
	 */
	@Query("SELECT als FROM ArgumentoListaSimple als WHERE als.idArgumento = :idArgumento")
	public ArgumentoListaSimple obtenerPorId(@Param("idArgumento") Long idArgumento) throws SpddExceptions;
	
	/**
	 * Metodo que sirve para obtener el argumento de lista simple por el campo de resultado y nombre de la lista simple
	 * @param resultado string que corresponde al valor por el cual se piensa buscar
	 * @return un objeto de tipo ArgumentoistaSimple con la informacion correspondiente
	 */
	@Query("SELECT als FROM ArgumentoListaSimple als WHERE lower(als.resultado) = lower(:resultado) AND lower(als.idListaSimple.nombre) = lower(:nombreLs)")
	public ArgumentoListaSimple obtenerPorResultadoYNombreListaSimple(@Param("resultado") String resultado,@Param("nombreLs") String nombreListaSimple);
}
