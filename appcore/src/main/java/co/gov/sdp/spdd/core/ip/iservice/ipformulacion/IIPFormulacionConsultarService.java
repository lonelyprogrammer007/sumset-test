package co.gov.sdp.spdd.core.ip.iservice.ipformulacion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;

public interface IIPFormulacionConsultarService {

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener los
	 * objetos HisVPddCompromisoDTO paginados
	 * 
	 * @param peticion objeto de tipo HisVPddCompromisoDTO
	 * @return un objeto de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPaginado(HisVPddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener la
	 * paginacion de los objetos CompromisoEstrategicoDTO
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO:RespuestaDTO
	 * @return un objeto de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO compromisoEstrategicoObtenerPaginado(CompromisoEstrategicoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idEspecifico
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetasCompromistoEstrategico(Long idEspecifico) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar la
	 * lista de PddMetaResultado correspondiente a un identificador de
	 * problematicaIndicador
	 * 
	 * @param idProblematicaIndicador Long que representa el idenficador de una
	 *                                relacion entre problematica e indicador.
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddMetaResultadoPorIDProblematicaIndicador(Long idProblematicaIndicador)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para buscar las
	 * competencias asociadas a un sector en especifico
	 * 
	 * @param idSector Long que representa el identificador del sector por el cual
	 *                 se esta buscando
	 * @return un objeto de tipo GenericoDTO con la informacion de los
	 *         pddCompetenciasAsociadas
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(Long idSector) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para obtener
	 * los PddCompromisos y retornarlo segun el filtro aplicado para este
	 * 
	 * @param peticion Objeto de tipo PddCompromiso que tiene la informacion
	 *                 necesaria para filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion de los
	 *         PddCompromisos filtrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisosPorFiltro(PddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appData y appCore para obtener
	 * todos los pddCompromiso relacionados a un plan de desarrollo distrital
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo
	 *               distrital por el cual se desea buscar o filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appData y appCore para obtener
	 * todos los pddCompromisosEspecificos relacionados a un pddCompromiso, tambien
	 * se consultan las metas relacionadas a este compromiso y las obras
	 * relacionadas a las metas.
	 * 
	 * @param idPlan Long que representa el identificador del pddCompromiso por el
	 *               cual se quiere buscar o filtar
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion
	 *         correspondiente o null en caso contrario
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarPddCompromisoEspecificoPorIdCompromiso(Long idCompromiso) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appData y appCore para consultar
	 * un PddPrbValoracion por medio del identificador de la problematica y el valor
	 * de momento
	 * 
	 * @param idProblematica Long que representa el identificador de la problematica
	 *                       pro la que se desea filtrar o buscar
	 * @param momento        Long que representa el valor del momento (1-antes,
	 *                       2-Durante, 3-Despues)
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(PddPrbValoracionDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarObrasConcretasPorMetas(Long id) throws SpddExceptions;

	/**
	 * Metodo que retorna la problematica por Id
	 * 
	 * @param id
	 * @return Problematica que coincida con el Id
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions;

	/**
	 * Método que retorna las problematicas por compromiso.
	 * 
	 * @param peticion
	 * @return Objeto de tipo GenericoDTO con el listado de problematicas que
	 *         coincidan con el filtro
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idProblematica
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPrbIndicadorPorProblematica(Long idProblematica) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddIndicadorTodos() throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appData y appCore para consultar
	 * un PddIndicador por medio del identificador
	 * 
	 * @param id Long que representa el identificador del PddIndicador
	 * @return un objeto de tipo PddIndicadorDTO con la informacion correspondiente.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO consultarPddIndicadorPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para buscar un
	 * pddCompromisos por el identificador
	 * 
	 * @param id Long que representa el identificador del compromiso
	 * @return un objeto tipo PddCompromisoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarPddCompromisoPorId(Long id) throws SpddExceptions;

	/**
	 * Consultar pdd vigente del sistema
	 * 
	 * @return el pdd vigente
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddVigente() throws SpddExceptions;
	
	/**
	 * metodo que permite la comunicación entre appdata  
	 * @param idCompromiso
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProblematicaPorIdCompromiso(Long idCompromiso) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener las PddPrbPoblaciones
	 * asociadas a una problematica con filtrado y paginado
	 * @param peticion 
	 * @return un Generico DTO que contiene una lista con las poblaciones asociadas a una problematica
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener todas las competencias asociadas
	 * @return un generditoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener la lista de PrbIndicador filtrado y paginados
	 * @param pddPrbIndicadorDTO objeto que contiene la informacion para filtrar y paginar
	 * @return un filtroDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions;
	
	public GenericoDTO consultarTodosPddProbleatica() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener la lista de pddCompromisoEspecifico
	 * filtrado y paginado
	 * @param peticion objeto que contiene la información para filtrar y paginar
	 * @return un GenericoDTO con la información filtrada y paginada
	 * @throws SpddExceptions 
	 */
	public GenericoDTO consultarPddCompromisoEspecificoFiltrado(PddCompromisoEspecificoDTO peticion) throws SpddExceptions;
}
