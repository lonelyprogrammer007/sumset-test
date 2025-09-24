package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a la meta resultado que
 * contiene la declaracion de metodos para ser implementados y por consiguiente
 * ser utilizados en el facade
 * 
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
public interface IPddMetaResultadoServiceRepo extends IOperacionesBasicasFacade<PddMetaResultado, Long>, Serializable {

	/**
	 * Metodo que permite consultar la lista de PddMetaResultado correspondiente a
	 * un identificador de problematicaIndicador
	 * 
	 * @param idProblematicaIndicador Long que representa el idenficador de una
	 *                                relacion entre problematica e indicador.
	 * @return una lista de PddMetaResultado con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public List<PddMetaResultado> consultarPorIDProblematicaIndicador(Long idProblematicaIndicador)
			throws SpddExceptions;

	/**
	 * Metodo que consulta los pddMeta resultado correspondiente a un proyecto
	 * estrategico o un proyecto estrategico local
	 * 
	 * @param peticion dto que viene por lo uqe se desea hacer la busqueda
	 * @param inicio   pagina en la que inicia la consulta
	 * @param limite   limite de registros por pagina
	 * @return un filtroDto que contiene el total y la lista paginada
	 * @throws SpddExceptions
	 */
	public FiltroDTO consultarPddPorProyectoEstrategico(PddMetaResultadoDTO peticion, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * MEtodo que permite consutar en la BD la lista de PddMetaResultado que estan
	 * desbalanceadas
	 * 
	 * @return una lista de PddMetaResultado con la informacion correspondiente
	 */
	public List<PddMetaResultado> obtenerTodosDesbalanceados(Long idPlanDesarrollo);

	/**
	 * 
	 * @param idProy
	 * @return
	 */
	public Long sumarPonderacionesMetaResultado(Long idProy);
	
	/**
	 * 
	 * @param idProyecto
	 * @return
	 */
	public List<PddMetaResultado> obtenerTodosPorIdProyectoEstrategico(Long idProyecto);
	
	/**
	 * 
	 * @param tipoOrden string que representa el orden ASC=ascendente y DESC=descendente
	 * @return
	 */
	public List<PddMetaResultado> obtenerTodosOrdenadosASC();
}
