package co.gov.sdp.spdd.data.irepositorio.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;

/**
 * Interface que contiene los metodos basicos de CrudRepository y otros para
 * hacer transacciones relacionadas con la tabla PddMetaResultado de la BD
 * 
 * @author DANIEL
 * @version 1.0 23/03/2020
 */
@Transactional
@Repository
public interface IPddMetaResultadoRepo extends CrudRepository<PddMetaResultado, Long>, Serializable {

	/**
	 * Metodo que permite consultar en BD la lista de PddMetaResultado
	 * correspondiente a un identificador de problematicaIndicador
	 * 
	 * @param idProblematicaIndicador Long que representa el idenficador de una
	 *                                relacion entre problematica e indicador.
	 * @return una lista de PddMetaResultado con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	@Query("SELECT mr FROM PddMetaResultado mr WHERE mr.idProbInd.idProbInd= :problematicaIndicador")
	public List<PddMetaResultado> obtenerPorIDProblematicaIndicador(
			@Param("problematicaIndicador") Long idProblematicaIndicador) throws SpddExceptions;

	/**
	 * Metodo que permite consutar en la BD la lista de PddMetaResultado que estan
	 * desbalanceadas
	 * 
	 * @return una lista de PddMetaResultado con la informacion correspondiente
	 */
	@Query("SELECT mr FROM PddMetaResultado mr WHERE mr.sumPond > 0 AND mr.sumPond <> 100 AND mr.idProyEstrategico.idPddNivel.idPlanDesarrollo.idPlanDesarrollo = :pdd")
	public List<PddMetaResultado> obtenerTodosDesbalanceados(@Param("pdd") Long idPlanDesarrollo);

	@Query("SELECT SUM(mr.ponderacion) FROM PddMetaResultado mr WHERE mr.idProyEstrategico.idAtributo= :idProy")
	public Long sumarPonderacionesMetaResultado(@Param("idProy") Long idProyEstrategico);
	
	@Query("SELECT mr FROM PddMetaResultado mr WHERE mr.idProyEstrategico.idAtributo= :proyecto ORDER BY mr.idMetaResultado ASC")
	public List<PddMetaResultado> obtenerTodosPorIdProyectoEstrategico(@Param("proyecto")Long idProyecto);
	
	@Query("SELECT mr FROM PddMetaResultado mr ORDER BY mr.idMetaResultado ASC")
	public List<PddMetaResultado> obtenerTodosOrdenadosASC();
}
