package co.gov.sdp.spdd.data.sesionfacade.interfaces;

import java.util.ArrayList;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

/**
 * Interface del SessionFacade del modulo IP que permite la comunicacion entre
 * appdata y appcore
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
public interface ISessionFacadeIP {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarCompromisoPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * compromiso y retornarlo segun el filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo HisVPddCompromisoDTO:RespuestaDTO que contiene
	 *                 la informacion necesaria para filtrar.
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso estrategico
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarCompromisoPorFiltro(HisVPddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * compromiso estrategico y retornarlo segun el filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO:RespuestaDTO que
	 *                 contiene la informacion necesaria para filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso estrategico
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarCompromisoEstrategicoPorFiltro(CompromisoEstrategicoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * compromiso estrategico por el identificador en la BD
	 * 
	 * @param idCompromisoEstrategico objeto de tipo Long que representa el
	 *                                identificador del compromiso estrategico que
	 *                                se quiere buscar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion del
	 *         compromiso estrateco encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorID(Long idCompromisoEstrategico)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * compromiso estrategico por el identificador de la tematica y el de compromiso
	 * estrategico en la BD
	 * 
	 * @param idTematica              Long que representa el identificador de la
	 *                                tematica por la cual se desea filtrar
	 * @param idCompromisoEstrategico Long que representa el identificador del
	 *                                compromiso estrategico por el cual se desea
	 *                                filtrar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion del
	 *         compromiso estrateco encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(Long idTematica,
			Long idCompromisoEstrategico) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetasCompromisoEstrategico(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO consultarObraConcretaPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que trae todas las obras concretas por un id metas
	 * 
	 * @param id de la meta la cual se asocia con la obra
	 * @return un generico dto con la lista de obras concretas
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarObrasConcretasPorMetas(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para consultar
	 * los Pdl y retornarlo segun el filtro aplicado para este
	 * 
	 * @param peticion Objeto de tipo Pdl que tiene la informacion necesaria para
	 *                 filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion de los Pdl filtrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdlPorFiltro(PdlDTO peticion) throws SpddExceptions;

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
	 * Metodo que permite obtener el Plan de desarrollo distrital que esta vigente
	 * 
	 * @return un objeto de tipo PddDTO con la info del pddDTO vigente
	 */
	public PddDTO consultarPddVigente() throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar los
	 * planes de desarrollo distrital (Pdd) y retornarlo segun el filtro aplicado
	 * para este
	 * 
	 * @param peticion objeto de tipo PddDTO:RespuestaDTO que contiene la
	 *                 informacion necesaria para filtrar.
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso estrategico
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarPddPorFiltro(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * plan de desarrollo distrital por el identificador en la BD
	 * 
	 * @param idPdd objeto de tipo Long que representa el identificador del plan de
	 *              desarrollo distrital que se quiere buscar
	 * @return un objeto de tipo PddDTO con la informacion del plan de desarrollo
	 *         distrital encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddPorID(Long idPdd) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar los
	 * pdds relacionados a un estado en BD
	 * 
	 * @param idestado Long que representa el estado por el cual se va a buscar.
	 * @return un objeto de tipo GenericoDTO con la informacion de los pdds
	 *         encontrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddsPorEstado(Long idestado) throws SpddExceptions;

	/**
	 * Metodo que permite a comunicacion entre el appdata y appcore para buscar la
	 * competencias asociadas a su identificador
	 * 
	 * @param id Long que representa el identificador de la competencia que se desea
	 *           buscar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorId(Long id) throws SpddExceptions;

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
	 * Metodo que permite la comunicacion entre el appdata y appcore para buscar un
	 * PddCompetenciaAsociada que corresponda a los identificadores del secto y
	 * LsCompetencia que se pasan como parametro
	 * 
	 * @param idSector        Long que representa el identificador del sector por el
	 *                        cual se va a buscar o filtrar
	 * @param idLsCompetencia Long que representa el identificador de LsCompetencia
	 *                        por el cual se va a buscar o filtrar
	 * @return un objeto de tipo PddCompetenciaAsociada con la informacion
	 *         correspodiente
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(Long idSector,
			Long idLsCompetencia) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para consultar
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
	 * Metodo que permite la comunicacion entre el appdata y appcore para consultar
	 * todos los pddCompromisos de un plan de desarrollo distrital
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo por
	 *               el cuals se va a buscar o filtrar.
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para buscar un
	 * PddCompromiso relacionado a un compromisoEstrategico y un plan de desarrollo
	 * 
	 * @param idEstrategico Long que representa el identificador del
	 *                      compromisoEstrategico por el cual se piens buscar o
	 *                      filtrar
	 * @param idPlan        Long que representa el identificador del plan de
	 *                      desarrollo por el cual se piensa buscar o filtrar
	 * @return un objeto de tipo PddCompromisoDTO con la inforacion correspondiente.
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(Long idEstrategico, Long idPlan)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para buscar los
	 * pddCompromisosEspecificos y sus metas y obras correspondientes relacionados a
	 * un pddCompromiso
	 * 
	 * @param idCompromiso Long que representa el identificador del pddCompromiso
	 *                     por el cual se quiere buscar o filtrar
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion de los
	 *         pddCompromisosEspecificos
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarPddCompromisosEspecificosPorIdCompromiso(Long idCompromiso)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * pdd compromiso especifico por el id del compromiso al cual esta relacionado y
	 * la descripcion en la BD
	 * 
	 * @param idCompromiso objeto de tipo Long que representa el identificador del
	 *                     compromiso al cual esta relacionado el pdd compromiso
	 *                     especifico
	 * @param decripcion   objeto de tipo string que representa la descripcion del
	 *                     compromiso especifico
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion del
	 *         pdd compromiso especifico encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(Long idCompromiso,
			String decripcion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * pdd compromiso especifico por su identificador en la BD
	 * 
	 * @param idPddCompromisoEspecifico objeto de tipo Long que representa el
	 *                                  identificador del pdd compromiso especifico
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion del
	 *         pdd compromiso especifico encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorID(Long idPddCompromisoEspecifico)
			throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public PddMetaDTO consultarPddMetaPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar una
	 * PddMetaResultado por medio de su indicador
	 * 
	 * @param id Long que representa el identificador de la meta resultado.
	 * @return objeto de tipo PddMetaResultadoDTO con la inforamcion correspondiente
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO consultarPddMetaResultadoPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * nivel de plan de desarrollo distrital por el identificador en la BD
	 * 
	 * @param idPddNivel objeto de tipo Long que representa el identificador del
	 *                   nivel de plan de desarrollo distrital que se quiere buscar
	 * @return un objeto de tipo PddNivelDTO con la informacion del nivel de plan de
	 *         desarrollo distrital encontrado o null en caso contrario
	 * @throws SpddExceptions
	 */
	public PddNivelDTO consultarPddNivelPorID(Long idPddNivel) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar los
	 * niveles del pdd relacionados a un identificador de plan de desarrollo en BD
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo al
	 *               cual se le quieren consultar los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion de los niveles
	 *         encontrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddNivelPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que retorna la problematica por Id
	 * 
	 * @param id
	 * @return Problematica que coincida con el Id
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * PddPrbValoracion por medio de su identificador
	 * 
	 * @param id Long que representa el identificador de la valoracion
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * PddPrbValoracion por medio del identificador de la problematica y el valor de
	 * momento
	 * 
	 * @param idProblematica Long que representa el identificador de la problematica
	 *                       pro la que se desea filtrar o buscar
	 * @param momento        Long que representa el valor del momento (1-antes,
	 *                       2-Durante, 3-Despues)
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(Long idProblematica, Long momento)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * pddCompromisoEspecifico por medio de su identificador
	 * 
	 * @param idPddCompromisoEspecifico Long que representa el identificador del
	 *                                  pddCompromisoEspecifico que se desea
	 *                                  eliminar ======= Método que retorna las
	 *                                  problematicas por compromiso.
	 * 
	 * @param peticion
	 * @return Objeto de tipo GenericoDTO con el listado de problematicas que
	 *         coincidan con el filtro
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para consultar un
	 * PddIndicador por su identificador
	 * 
	 * @param id Long que representa el identificador del PddIndicador
	 * @return objeto de tipo PddIndicadorDTO con la inforamcion correspondiente
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO consultarPddIndicadorPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO consultarPrbPoblacionPorId(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param idProbInd
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO consultarPrbIndicadorPorId(Long idProbInd) throws SpddExceptions;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddIndicadorTodos() throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idProblematica
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPrbIndicadorPorProblematica(Long idProblematica) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * pddCompromisoEspecifico por medio de su identificador
	 * 
	 * @param idPddCompromisoEspecifico Long que representa el identificador del
	 *                                  pddCompromisoEspecifico que se desea
	 *                                  eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico) throws SpddExceptions;

	/**
	 * Metodo que elimina una meta resultado
	 * 
	 * @param idMetaResultado id de la meta resultado que se desea eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarMetaResultado(Long idMetaResultado) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void elimanrPDDMeta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarPddObraConcreta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param idPoblacion
	 * @throws SpddExceptions
	 */
	public void eliminarPrbPolacion(Long idPoblacion) throws SpddExceptions;

	/**
	 * 
	 * @param idProbInd
	 * @throws SpddExceptions
	 */
	public void eliminarPrbIndicador(Long idProbInd) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * compromisoEstrategico en BD
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO que contiene la
	 *                 informacion para guardar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO guardarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * pdd en BD
	 * 
	 * @param peticion objeto de tipo PddDTO que contiene la informacion para
	 *                 guardar
	 * @return un objeto de tipo PddDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddDTO guardarPdd(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para guardar un
	 * PddCompetenciaAsociada en BD
	 * 
	 * @param peticion objeto de tipo PddCompetenciaAsociadaDTO que contiene la
	 *                 informacion para guardar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO guardarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para guardar un
	 * PddCompromiso en BD
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para guardar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO guardarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * PddCompromisoEspecifico en BD
	 * 
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO que contiene la
	 *                 informacion para guardar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion que
	 *         se guardo
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO guardarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param pddMetaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO guardarPddMeta(PddMetaDTO pddMetaDTO) throws SpddExceptions;

	/**
	 * Metodo que permite que permite la comunicacion entre appdata y appcore para
	 * guarda una PddMetaResultado
	 * 
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la
	 *                 inforamcion para guardar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO guardarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * pddNivel en BD
	 * 
	 * @param peticion objeto de tipo PddNivelDTO que contiene la informacion para
	 *                 guardar
	 * @return un objeto de tipo PddNivelDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddNivelDTO guardarPddNivel(PddNivelDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param pddObraConcretaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO guardarPddObraConcreta(PddObraConcretaDTO pddObraConcretaDTO) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO guardarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * PddPrbValoracion
	 * 
	 * @param pddPrbValoracion objeto de tipo PddPrbValoracionDTO que contiene la
	 *                         informacion para guardar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO guardarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO guardarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO guardarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * pdl en BD
	 * 
	 * @param peticion objeto de tipo PdlDTO que contiene la informacion para
	 *                 guardar
	 * @return un objeto de tipo PdlDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PdlDTO guardarPdl(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * compromiso en BD
	 * 
	 * @param compromisoEstrategicoDTO objeto de tipo CompromisoEstrategicoDTO que
	 *                                 contiene la informacion para modificar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategicoDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO modificarMetaDeCompromiso(PddMetaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO modificarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * plan de desarrollo distrital en BD
	 * 
	 * @param pddDTO objeto de tipo PddDTO que contiene la informacion para
	 *               modificar
	 * @return un objeto de tipo PddDTO con la informacion que se modifico
	 * @throws SpddExceptions
	 */
	public PddDTO modificarPdd(PddDTO pddDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para modificar
	 * un PddCompromiso en BD
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para modificar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion que se modifico
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO modificarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para modificar
	 * un PddCompetenciaAsociada en BD
	 * 
	 * @param peticion objeto de tipo PddCompetenciaAsociadaDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO modificarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PddCompromisoEspecifico en BD
	 * 
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion que
	 *         se modifico
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite que permite la comunicacion entre appdata y appcore para
	 * modifica una PddMetaResultado
	 * 
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la
	 *                 inforamcion para modificar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO modificarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * pddNivel en BD
	 * 
	 * @param peticion objeto de tipo PddNivelDTO que contiene la informacion para
	 *                 modificar
	 * @return un objeto de tipo PddNivelDTO con la informacion que se modifico
	 * @throws SpddExceptions
	 */
	public PddNivelDTO modificarPddNivel(PddNivelDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PddPrbValoracion
	 * 
	 * @param pddPrbValoracion objeto de tipo PddPrbValoracionDTO que contiene la
	 *                         informacion para modificar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se
	 *         modifico
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO modificarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite modificar una poblacion de una problematica especifica
	 * 
	 * @param peticion dto tipo PddPrbPoblacionDTO
	 * @return retorna un dto PddPrbPoblacionDTO com las modificaciones realizadas
	 */
	public PddPrbPoblacionDTO modificarPrbPoblacion(PddPrbPoblacionDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO validarMeta(PddMetaDTO peticion) throws SpddExceptions;

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
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO validarPddObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO validarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO validarPddPrbIndicador(Long idIndicador, Long idProblematica) throws SpddExceptions;

	/**
	 * Metodo que permite validar si el identificador del plan de desarrollo pasado
	 * como parametro corresponde al identificador del pland de desarrollo que esta
	 * vigente
	 * 
	 * @param idPlanDesarrollo Long que representa el identficador del pdd que se
	 *                         quiere verificar o comprobar si es el pdd vigente
	 * @return un boolean. True si el identificador pasado como parametro
	 *         corresponde al identificador del pdd vigente y false en caso
	 *         contrario
	 * @throws SpddExceptions
	 */
	public boolean validarPddVigente(Long idPlanDesarrollo) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO modificarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * PddIndicador.
	 * 
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion
	 *                 para guardar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO guardarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PddIndicador.
	 * 
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion
	 *                 para modificar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO modificarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotPorFiltro(PotDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para registra un
	 * pot
	 * 
	 * @param peticion objeto de tipo PotDTO que contiene la información a registrar
	 * @return un objeto de tipo PotDTO con la información que se registro
	 * @throws SpddExceptions
	 */
	public PotDTO guardarPot(PotDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre appdata y appcore para buscar un pot
	 * por el codigo
	 * 
	 * @param codigoPot codigo por el cual se busca un pot
	 * @return un PotDto con toda la información
	 * @throws SpddExceptions
	 */
	public PotDTO obtenerPotCodigo(String codigoPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obetner un
	 * PddNivelAtributo por medio del numeoro y del identificador del nivel al que
	 * esta relacionado
	 * 
	 * @param numero     string que representa el numero del nivel atributo
	 * @param idPddNivel Long que reprsenta el identificador del pddNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PddNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYIdPddNivel(Long numero, Long idPddNivel,
			Long idAtributoPadre) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PddNivelAtributo de un nivel en especifico en orden ascendente
	 * 
	 * @param idPddNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obetner un
	 * PddNivelAtributo por medio de la denominacion y del identificador del nivel
	 * al que esta relacionado
	 * 
	 * @param numero     string que representa el numero del nivel atributo
	 * @param idPddNivel Long que reprsenta el identificador del pddNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PddNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorDenominacionYIdPddNivel(String denominacion, Long idPddNivel)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a guardar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         guardo
	 */
	public PddNivelAtributoDTO guardarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PdlDTO
	 * 
	 * @param pdlDTO objeto de tipo PdlDTO que contiene la informacion a modificar
	 * @return un objeto de tipo PdlDTO con la informacion que se modificar
	 */
	public PdlDTO modificarPdl(PdlDTO pdlDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PdlNivelDTO
	 * 
	 * @param pdlNivelDTO objeto de tipo PdlNivelDTO que contiene la informacion a
	 *                    modificar
	 * @return un objeto de tipo PdlNivelDTO con la informacion que se modificar
	 */
	public PdlNivelDTO modificarPdlNivel(PdlNivelDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite buscar los niveles de pdl por el identificador del pdl
	 * 
	 * @param idPlan Long que representa el identificador del pdl del cual se quiere
	 *               consultar los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion encontrada.
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions;

	/**
	 * Metodo que premite buscar un nivel de un pdl por su codigo de nivel
	 * (1,2,3...) y su identificador de plan
	 * 
	 * @param nivel  Long que representa el codigo del nivel (1,2,3...)
	 * @param idPlan Long que representa el identificado del plan de desarrollo
	 *               local
	 * @return un objeto de tipo PdlNivelDTO con la informacion correspondiente o un
	 *         objeto vacio
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO consultarPdlNivelPorNivelYIdPlanLocal(Long nivel, Long idPlanLocal) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un nivel de plan de desarrollo local (pdlNivel) por
	 * el identificador
	 * 
	 * @param id Long que representa el identificador del pdlNivel
	 * @return un objeto de tipo PdlNivelDTO con la informacion del nivel de plan de
	 *         desarrollo local, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO consultarPdlNivelPorId(Long idPdlNivel) throws SpddExceptions;

	/**
	 * Metodo que permite guarda un pdl nivel en BD
	 * 
	 * @param pdlNivelDTO objeto de tipo PdlNivelDTO que contiene la informacion
	 *                    para guardar
	 * @return un objeto de tipo PdlNivelDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO guardarPdlNivel(PdlNivelDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y appcore para obtener un
	 * pot por el identificador
	 * 
	 * @param idPot Long representa en identificador del Pot
	 * @return un objeto de tipo PotDto con la información del pot o null en caso
	 *         contrario
	 * @throws SpddExceptions
	 */
	public PotDTO obtenerPotPorId(Long idPot) throws SpddExceptions;

	/**
	 * 
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * las ramas de un pot por el identificador
	 * 
	 * @param idPot Long que representa el identificador del Pot
	 * @return un objeto de tipo GenericoDTO con la información de las ramas del pot
	 *         o null en caso contraio
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPotRamaPorIdPot(Long idPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para guardar
	 * una rama Pot en la bd
	 * 
	 * @param peticion objeto que contiene la información para guardar una rama pot
	 * @return objeto de tipo DTO que contiene la información que se guardo
	 * @throws SpddExceptions
	 */
	public PotRamaDTO guardarRamaPot(PotRamaDTO peticion) throws SpddExceptions;

	/**
	 * metodo que permite la comunicación entre el appData y el appcore para guardar
	 * un nivel pot en la bf
	 * 
	 * @param peticion objeto que contiene la información para guadar un nivel pot
	 * @return objeto de tipo dto que contiene la información que se guardo
	 * @throws SpddExceptions
	 */
	public PotNivelDTO guardarNivelPot(PotNivelDTO peticion) throws SpddExceptions;

	/**
	 * Meteodo que permite la comunicación entre el appData y el appCore para
	 * obtener las ramas de un pot por el identificador, ordenadas por de forma
	 * descediente por el numero de rama
	 * 
	 * @param idPot Long que representa el identificador del Pot
	 * @return un objeto de tipo GenericoDTO con la información de las ramas del pot
	 *         o null en caso contrario
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO guardarMetaIndicador(PddMRIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que valida un indicador meta resultado por el id meta resultado y el
	 * id indicador
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO validarIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO obtenerPddIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadoresMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @throws SpddExceptions
	 */
	public void eliminarIndicadorMetaResultado(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO obtenerPddMRIndicadorPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los PDL de una entidad
	 * 
	 * @param codigoEntidad Long que representa el codigo de la entidad por el cual
	 *                      se quiere hacer la busqueda
	 * @return una lista de entidades PDL correspondientes a la busqueda o null en
	 *         caso contrario
	 */

	public GenericoDTO consultarPdlPorEntidad(String resultado, String codigoEntidad);

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PddNivelAtributo que corresponden al nivel atributo padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PddNivelAtributo
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PotObra correspondientes a un nivel pot paginado
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO peticion) throws SpddExceptions;

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
	 * Metodo de consultar meta producto por meta resultado
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetaProductoPorMR(PddMetaProductoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que guarda una meta producto
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO guardarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions;

	/**
	 * eliminar meta producto por id
	 * 
	 * @param idMetaProducto
	 * @throws SpddExceptions
	 */
	public void eliminarMetaProducto(Long idMetaProducto) throws SpddExceptions;

	/**
	 * Obtener meta producto por id
	 * 
	 * @param idMetaProducto
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO obtenerMetaProductoPorId(Long idMetaProducto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para
	 * eliminar una rama de un pot
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama que se desea
	 *                  eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarRamaPot(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para
	 * eliminar un nivel de un pot
	 * 
	 * @param idNivelPot Long que representa el identificador del nivel pot que se
	 *                   desea eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarNivelPot(Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * una rama de un pot por su identificador
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama que se desea
	 *                  obtener
	 * @return un objeto de tipo RamaPotDTO con la información de la rama
	 * @throws SpddExceptions
	 */
	public PotRamaDTO obtenerRamaPotPorid(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener
	 * un nivel de un pot por su identificador
	 * 
	 * @param idNivelPot Long que representa el identificador del nivel pot que se
	 *                   desea obtener
	 * @return un objeto de tipo dto con la información del nivel
	 * @throws SpddExceptions
	 */
	public PotNivelDTO obtenerNivelPotPorId(Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * todos los niveles de una rama pot
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * todos los niveles de una rama pot ordenados de forma desciendiente
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener
	 * todos los sub-niveles de un nivel de un pot
	 * 
	 * @param idNivelPot Long que represetna el identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener
	 * todos los sub-niveles de un nivel de un pot de forma descediente
	 * 
	 * @param idNivelPot Long que represetna el identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivelDesc(Long idNivelPot) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadorMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO validarMetaProductoIndicador(PddMpIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO guardarIndicadorMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idIndMetaProducto
	 * @throws SpddExceptions
	 */
	public void eliminarIndicadorMetaProducto(Long idIndMetaProducto) throws SpddExceptions;

	/**
	 * 
	 * @param idIndicadorMp
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO obtenerIndicadorMp(Long idIndicadorMp) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener un
	 * PddNivelAtributoDTO por el identificador
	 * 
	 * @param idAtributo Long que representa el identificador del
	 *                   PddNivelAtributoDTO
	 * @return un ojbeto de tipo PddNivelAtributoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorId(Long idAtributo) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a modificar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         modifico
	 */
	public PddNivelAtributoDTO modificarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * PddNivelAtriguto
	 * 
	 * @param idAtributo Long que representa el identificador PddNivelAtriguto que
	 *                   se quiere eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPddNivelAtributo(Long idAtributo) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar una
	 * PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a guardar
	 * @return un objeto de tipo PotObraDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraDTO guardarPotObra(PotObraDTO potObraDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar una
	 * PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a modificar
	 * @return un objeto de tipo PotObraDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PotObraDTO modificarPotObra(PotObraDTO potObraDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar una
	 * PotObra
	 * 
	 * @param potObraDTO Long que representa el identificador de la PotObra que se
	 *                   desea eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPotObra(Long idPotObra) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener una
	 * PotObra por medio del identificador
	 * 
	 * @param idPotObra Long que representa el identificador de la PotObra
	 * @return un objeto de tipo PotObraDTO
	 * @throws SpddExceptions
	 */
	public PotObraDTO consultarPotObraPorId(Long idPotObra) throws SpddExceptions;

	/**
	 * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden
	 * ascendente
	 * 
	 * @param idPdlNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions;

	/**
	 * MEtodo que permite la comunicacion entre appdata y appcore para obtener un
	 * PotObra por medio de su codigo y id nivel
	 * 
	 * @param codigo     string que reprsenta el codigo pro el cual se va a buscar
	 * @param idNivelPot Long que representa el identificador del nivel por el cual
	 *                   se va a buscar
	 * @return un objeto de tipo PotObra con la informacion correspondiente.
	 */
	public PotObraDTO consultarPotObraPorCodigoYIdNivelPot(Long codigo, Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que pemite la comunicacion entre appdata y appcore para obtener todos
	 * los PotObraEntidad por medio del potObra
	 * 
	 * @param idPotObra Long que representa el identificador del PotObra por el cual
	 *                  se va a buscar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar
	 * todos los PotObraEntidad que esten relacionados a un PotObra
	 * 
	 * @param idPotObra Long que representa el identificador del PotObra al que se
	 *                  le quiere eliminar todas las PotObraEntidad relacionadas
	 */
	public void eliminarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar una
	 * PotObraEntidad
	 * 
	 * @param potObraEntidadDTO objetio de tipo PotObraEntidadDTO con la informacion
	 *                          a guardar.
	 * @return un objeto de tipo PotOBraEntidadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraEntidadDTO guardarPotObraEntidad(PotObraEntidadDTO potObraEntidadDTO) throws SpddExceptions;

	/**
	 * Metodo que pemite la comunicacion entre appdata y appcore para consultar
	 * todos los PotIntrumento filtrados y paginados
	 * 
	 * @param potInstrumentoDTO Objeto de tipo PotInstrumentoDTO con la informacion
	 *                          para filtar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotInstrumentoFiltrado(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener el
	 * PotInstrumento por el identificador
	 * 
	 * @param idPotInstrumento Long que representa el identificador del
	 *                         potInstrumento
	 * @return un objeto de tipo PotInstrumnetoDTO con la informacin correspondiente
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO consultarPotInstrumentoPorId(Long idPotInstrumento) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener un
	 * PotInstrumento por el codigo y por identificador del pot
	 * 
	 * @param codigo String que representa el codigo del PotInstrumento
	 * @param idPot  Long que representa el identificador del pot
	 * @return un objeot de tipo PotInstrumento con la informacion correspondiente
	 */
	public PotInstrumentoDTO consultarPotInstrumentoPorCodigoYIdPot(Long codigo, Long idPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar una
	 * potInstrumento
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a guardar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO guardarPotInstrumento(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar una
	 * potInstrumento
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a modificar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO modificarPotInstrumento(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * potInstrumento
	 * 
	 * @param idPotInstrumento Long que representa el identificador del
	 *                         potInstrumento a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarPotInstrumento(Long idPotInstrumento) throws SpddExceptions;

	/**
	 * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo
	 * padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PdlNivelAtributo
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions;

	/**
	 * Metodo que permite guardar una PdlNivelAtributo en BD
	 * 
	 * @param potObraDTO objeto de tipo PdlNivelAtributoDTO con la informacion a
	 *                   guardar
	 * @return un objeto de tipo PdlNivelAtributoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PdlNivelAtributoDTO guardarPdlNivelAtributo(PdlNivelAtributoDTO pdlNivelAtributoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite consultar todas las entidades meta productos relacionadas
	 * con un meta producto
	 * 
	 * @param peticion datos en los que viene el id del meta producto y la
	 *                 paginacion de la tabla
	 * @return retorna un generico dto con la lista de entidades
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosMpEntidades(PddMpEntidadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite obetner un PdlNivelAtributo por medio de la denominacion y
	 * del identificador del nivel al que esta relacionado
	 * 
	 * @param String     que representa la denominacion del nivel atributo
	 * @param idPdlNivel Long que representa el identificador del pdlNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PdlNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PdlNivelAtributoDTO consultarPdlNivelAtributoPorDenominacionYIdPdlNivel(String denominacion, Long idPdlNivel)
			throws SpddExceptions;

	/**
	 * Obtener entidades meta producto por id
	 * 
	 * @param idMpEntidad identificador de la entidad meta producto
	 * @return una entidad meta producto
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO obtenerMpEntidadPorId(Long idMpEntidad) throws SpddExceptions;

	/**
	 * Metodo que valida una entidad meta producto por su idMetaProducto y
	 * codigoEntidad
	 * 
	 * @param codigoEntidad  codigo correspondiente a la entidad asociada
	 * @param idMetaProducto codigo correspondiente al meta producot asociado
	 * @return una entidad meta producto DTO
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO validarMpEntidadPorMetaProductoYEntidad(String codigoEntidad, Long idMetaProducto)
			throws SpddExceptions;

	/**
	 * Metodo que guarda una entidad meta producto en la BD
	 * 
	 * @param peticion dto en el que llegan los campos a almacenar
	 * @return retorna le entidad guardada con el id auto generado
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO guardarMpEntidad(PddMpEntidadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que elimina una entidad por su ID
	 * 
	 * @param idMpEntidad id de la entidad a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarMpEntidad(Long idMpEntidad) throws SpddExceptions;

	/**
	 * <<<<<<< HEAD
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotObra(PotObraDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para construir el arbor con toda la informacion de los componentes de niveles que estan desbalanceados.
	 * @param idPlanDesarrollo Long que representa el identificador al cual se le van a buscar todos los componentes de niveles desbalanceados
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion correspondiente.
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo) throws SpddExceptions;


	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadorEntidadMetaProducto(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO registrarMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param idMpEntidad
	 * @throws SpddExceptions
	 */
	public void eliminarMpIndicadorEntidad(Long idMpEntidad) throws SpddExceptions;

	/**
	 * 
	 * @param codigoEntidad
	 * @param idIndProd
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO validarMpIndicadorEntidad(String codigoEntidad, Long idIndProd)
			throws SpddExceptions;

	/**
	 * 
	 * @param idIndMetaProd
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO consultarIndicadorEntidadPorId(Long idIndMetaProd) throws SpddExceptions;

	/**
	 * 
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO obtenerTodosRangoPonderacion() throws SpddExceptions;

	/**
	 * Metodo que permite un objeto de tipo rango ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public PddRangoPonderacionDTO obtenerPddRangoPonderacionPorId(Long idRango) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar una
	 * PddRangoPonderacion
	 * 
	 * @param potObraDTO Long que representa el identificador de la
	 *                   PddRangoPonderacion que se desea eliminar
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public void eliminarPddRangoPonderacion(Long idRango) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una PddRangoPonderacion en BD
	 * 
	 * @param peticion objeto de tipo PddRangoPonderacionDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddRangoPonderacionDTO guardarPddRangoPonderacion(PddRangoPonderacionDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite modificar una PddRangoPonderacion en BD
	 * 
	 * @param peticion objeto de tipo PddRangoPonderacionDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(PddRangoPonderacionDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los PddRangoPonderacion de un plan de desarrollo
	 * en especifico
	 * 
	 * @param idPdd Long que representa el identificador del plan de desarrollo por
	 *              el cual se quiere hacer la busqueda
	 * @return una lista de PddRangoPonderacion correspondiente a la busqueda o null
	 *         en caso contrario
	 */
	public GenericoDTO obtenerPddRangoPonderacionPorIdPdd(Long idPdd) throws SpddExceptions;
	
	/**
     * Permite la comunicacion entre appdata y appcore para obtener un PddNivelAtributo por el numero del PddNivelAtributo del codigo de nivel 1 del PddNivel
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(Long numero, Long idPlanDesarrollo) throws SpddExceptions;    
   
    /**
     * Metodo que permite la comunicacion entre appdata y appcore para obtener un PddNivelAtributo por el numero del PddNivelAtributo, el codigo_numero del PddNivel y el identificador del pddnivelAtributo
     * @param numero Long que respresenta el numero del pddNivelAtributo de primer nivel que se quiere buscar
     * @param codigoNumero Long que represnta el codigoNumero del PddNivel al que esta asociado el PddNivelAtributo
     * @param idAtributoPadre Long que representa el identificador del atributopadre al que esta relacionado
     * @return un objeto PddNivelAtributo con la informacion correspondiente
     */
    public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre( Long numero, Long codigoNumero, Long idAtributoPadre) throws SpddExceptions;

    /**
	 * 
	 * @return
	 */
	public GenericoDTO consultarTodosMetaProductosEntidades();
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener una lista de PddProblematica por el idCompromiso
	 * @param idCompromiso numero Long que representa el numero del id compromiso por el que se quiere buscar
	 * @return un objeto de tipo GenericoDTO con la lista de las problematicas
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProblematicasPorIdCompromiso(Long idCompromiso) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener el POT por estado
	 * @param estado estado del por por el cual se quiere filtar (Vigente | Finalizado)
	 * @return una lista con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPOTPorEstado(String estado) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener las PddPrbPoblaciones
	 * asociadas a una problematica con filtrado y paginado
	 * @param peticion 
	 * @return un Generico DTO que contiene una lista con las poblaciones asociadas a una problematica
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener todas las competencias asociadas
	 * @return un generditoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions;
	
	public PddProblematicaDTO modificarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener la lista de PrbIndicador filtrado y paginados
	 * @param pddPrbIndicadorDTO objeto que contiene la informacion para filtrar y paginar
	 * @return un filtroDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions;
	
	public GenericoDTO obtenerTodosPddProblematica() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtenter la lista de pddCompromisoEspecificoDTO filtrado y 
	 * paginado
	 * @param peticion un objeto de tipo PddCompromisoEspecificoDTo con la informacion para filtrar y paginar
	 * @return un GenericoDTO con los datos filtrados y paginados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisoEspecificoFiltrado(PddCompromisoEspecificoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtenre todos las policitcas publicas filtradas por sus campos
	 * @param pddPoliticaPublicadDTO objeto de tipo PddPoliticaPublicaDTO que contiene la informacion para filtar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO pddPoliticaPublicadDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para consultar las politicas publicas que no estan relacionadas con el proyecto de inversion
	 * @param idProyInversion Long que representa el proyecto de inversion que esta relacionado con las politicas publicas
	 * @return un objeto GenericoDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(Long idProyInversion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para consultar todas las politicas publicas ordenadas por el campo Politca ascendentemente
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaOrdenadosPorNombrePolitica() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener el Plan de desarrollo local que esta vigente
	 * 
	 * @return un objeto de tipo PdlDTO con la info del pdlDTO vigente
	 */
	public PdlDTO consultarPdlVigente(String codigoEntidad) throws SpddExceptions;
}
