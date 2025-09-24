package co.gov.sdp.spdd.core.ip.iservice.ipplandistrital;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPlanDistritalConsultarService {

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener la
	 * paginacion de los objetos de planes de desarrollo distrital
	 * 
	 * @param peticion objeto de tipo PddDTO:RespuestaDTO
	 * @return un objeto de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO pddObtenerPaginado(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la cominicacion entre appdata y appcore para obtener los
	 * niveles de un plan de desarrollo distrital indicado por el identificador
	 * 
	 * @param idPlan long que representa el identificador del plan de desarrollo
	 *               distrital del cual se van a consultar los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion consultada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPddNivelPorIdPlanDesarrolloDistrital(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener un
	 * pdd por el identificador
	 * 
	 * @param id Long que representa el identificador del pdd que se desea buscar
	 * @return un objeto PddDTO con la indormacion del plan encontrado o null en
	 *         caso contrario
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PddNivelAtributo de un nivel en especifico en orden ascendente
	 * 
	 * @param idPddNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener la
	 * paginacion de los objetos de planes de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO:RespuestaDTO
	 * @return un objeto de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO pdlObtenerPaginado(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar un plan de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO que contiene la informacion
	 * @return un objeto de tipo GenericoDTO con la informacion del plan de
	 *         desarrollo local.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarTodosPlanDesarrolloLocal() throws SpddExceptions;

	/**
	 * Metodo que permite consultar un Pdl por medio del identificador
	 * 
	 * @param idPlanLocal Long que representa el identificador del plan de
	 *                    desarrollo local por el que se desea buscar
	 * @return un objeto de tipo PdlDTO con la informacion correspondiente.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public PdlDTO consultarPlanDesarrolloLocalPorId(Long idPlanLocal) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los niveles correspondientes a un plan de
	 * desarrollo
	 * 
	 * @param idPlanDesarrollo Long que representa el identificador del plan de
	 *                         desarrollo del cual se quieren obtener los niveles
	 * @return una lista de PddNivel que contiene todos los niveles correspondientes
	 *         al plan de desarrollo.
	 */
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions;

	/**
	 * Metodo que permite consultar las metas resultados por proyecto estrategico
	 * 
	 * @param peticion dto el cual trae si se apunta a un pdd o un pdl
	 * @return un genericoDto con la lista de pdd meta resultado
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddMetaResultadoProyectoEstrategico(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PddNivelAtributo que corresponden al nivel atributo padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PddNivelAtributo
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(PddNivelAtributoDTO peticion)
			throws SpddExceptions;

	/**
	 * Consultar pddMetaIndicador
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetaProductoPorMR(PddMetaProductoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadoresMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden
	 * ascendente
	 * 
	 * @param idPdlNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO peticion)
			throws SpddExceptions;

	/**
	 * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo
	 * padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PdlNivelAtributo
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para construir el
	 * arbor con toda la informacion de los componentes de niveles que estan
	 * desbalanceados.
	 * 
	 * @param idPlanDesarrollo Long que representa el identificador al cual se le
	 *                         van a buscar todos los componentes de niveles
	 *                         desbalanceados
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo) throws SpddExceptions;

	/**
	 * Metodo que consulta todas las meta entidades relacionadas al meta producto y
	 * con paginacion señalada
	 * 
	 * @param peticion viene con los campos idMetaProducto, tamanioPagina y pagina
	 * @return retorna un genericoDTO con la lista
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarTodosRangoPonderacion() throws SpddExceptions;

	/**
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarRangoPonderacionPorIdPdd(Long idRango) throws SpddExceptions;

	public GenericoDTO consultarTodosIndicadoresMetaProducto() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener el Plan de desarrollo local que esta vigente
	 * 
	 * @return un objeto de tipo PdlDTO con la info del pdlDTO vigente
	 */
	public PdlDTO consultarPdlVigente(String codigoEntidad) throws SpddExceptions;
}
