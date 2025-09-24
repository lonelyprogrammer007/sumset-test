package co.gov.sdp.spdd.core.ip.icontroller.ipformulacion;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface del controlador que expone los servicios al cliente. Esta interface
 * condensara los Capítulos 5, 6, 7, 8 y 9.
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
public interface IIPFormulacionController {

	/**
	 * Metodo que permite obtener los Compromisos
	 * 
	 * @param peticion objeto que contiene la informacion del paginado (pagina y
	 *                 tamanioPagina)
	 * @return un objetoDTO de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerHisVPddCompromisoTodos(HisVPddCompromisoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar las competencias asociadas a un sector en especifico
	 * @param idSector Long que representa el identificador del sector por el cual se esta buscando
	 * @return un objeto de tipo GenericoDTO con la informacion de los pddCompetenciasAsociadas 
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(Long idSector) throws SpddExceptions;
	
	/**
	 * Metodo que permite registrar o modificar una competencia asociada
	 * @param peticion objeto de tipo PddCompetenciaAsociadaDTO con la informacion a guardar o modificar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion guardada o modificada.
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO registrarModificarCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todos los compromisos estrategicos
	 * 
	 * @param peticion objeto de tipo RespuestaDTO que contiene la informacion del
	 *                 paginado (pagina y tamanioPagina)
	 * @return un objetoDTO de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerCompromisoEstrategicoTodos(CompromisoEstrategicoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite registrar o guardar un compromiso estrategico
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion
	 *         guardada, en caso contrario null
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO registrarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que modifica un compromisoEstrategico
	 *
	 * @param CompromisoEstrategicoDTO campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite consultar todos los pddCompromisosEspecificos relacionados
	 * a un pddCompromiso
	 * 
	 * @param idCompromiso Long que representa el identificador del pddCompromiso
	 *                     por el cual se piensa buscar o filtrar
	 * @return un objeto tipo GenericoDTO con la informacion de los
	 *         PddCompromisosEspecificos o null en caso contrario
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarPddCompromisoEspecificoPorIdCompromiso(Long idCompromiso) throws SpddExceptions;

	/**
	 * Metodo que permite registrar o guardar un pdd compromiso espedifico
	 * 
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO con la informacion
	 *                 a guardar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion
	 *         guardada, en caso contrario null
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO registrarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que modifica un pdd compromiso especifico
	 *
	 * @param PddCompromisoEspecificoDTO campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que elimina un pddCompromisoEspecifico
	 * 
	 * @param pddCompromisoEspecifico
	 * @return
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico)
			throws SpddExceptions;

	/**
	 * 
	 * @param idEspecifico
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetasCompromistoEstrategico(Long idEspecifico) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los PddCompromisos y retornarlo segun el filtro
	 * aplicado para este
	 * 
	 * @param peticion Objeto de tipo PddCompromiso que tiene la informacion
	 *                 necesaria para filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion de los
	 *         PddCompromisos filtrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisosPorFiltro(PddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar todos los compromisos relacionados a un plan de
	 * desarrollo distrital.
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo al
	 *               cual se le quieren consultar los compromisos
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que permite registrar o guardar un pddCompromiso
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para registrar o guardar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion registrada o
	 *         guardada.
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO registrarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite modificar un pddCompromiso
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para modificar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO modificarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar un PddPrbValoracion por medio del identificador de la problematica y el valor de momento
	 * @param idProblematica Long que representa el identificador de la problematica pro la que se desea filtrar o buscar
	 * @param momento Long que representa el valor del momento (1-antes, 2-Durante, 3-Despues) 
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(PddPrbValoracionDTO peticion) throws SpddExceptions;


	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMetaDTO registrarMetaPorCompromiso(PddMetaDTO peticion);

	/**
	 * 
	 * @param idMeta
	 * @return
	 */
	public GenericoDTO consultarObrasPorMeta(Long idMeta);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO eliminarPddObraConcreta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddObraConcretaDTO registrarObraConcretaPorMeta(PddObraConcretaDTO peticion);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO eliminarPddMeta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO modificarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMetaDTO modificarPddMeta(PddMetaDTO peticion);

	/**
	 * Metodo que permite registrar o modificar un PddPrbValoracion
	 * @param peticion objeto de tipo PddPrbValoracionDTO que contiene la informacion para guardar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO registrarModificarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions;
	
	
	/**
	 * Metodo que permite consultar la lista de PddMetaResultado correspondiente a un identificador de problematicaIndicador
	 * @param idProblematicaIndicador Long que representa el idenficador de una relacion entre problematica e indicador.
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO colsultarPddMetaResultadoPorIDProblematicaIndicador(Long idProblematicaIndicador) throws SpddExceptions;
	
	/**
	 * Metodo que permite registrar una meta resultado
	 * @param peticion objeto de tipo PddMetaResultadoDTO con toda la informacion para guardar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO registrarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite modificar una PddMetaResultado
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la inforamcion para modificar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO modificarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	
	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddPrbPoblacionDTO eliminarPrbPoblacion(Long idPoblacion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddPrbPoblacionDTO registrarPrbPoblacion(PddPrbPoblacionDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddPrbPoblacionDTO modificarPrbPoblacion(PddPrbPoblacionDTO peticion);

	/**
	 * 
	 * 
	 * @return
	 */
	public GenericoDTO consultarPddIndicadorTodos();

	/**
	 * 
	 * @param idProblematica
	 * @return
	 */
	public GenericoDTO consultarPrbIndicadorPorProblematica(Long idProblematica);


	/**
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite consultar problematicas por compromisos.
	 * 
	 * @param peticion un objeto de tipo PddProblematicaDTO con los criterios de busqueda.
	 * @return un objeto de tipo GenericoDTO que contiene el listado de problematicas que 
	 * 					coinciden con los criterios de la consulta.
	 * @param peticion
	 * @return
	 */
	public PddPrbIndicadorDTO guardarPrbIndicador(PddPrbIndicadorDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddPrbIndicadorDTO modificarPrbIndicador(PddPrbIndicadorDTO peticion);

	/**
	 * 
	 * @param idProbInd
	 * @return
	 */
	public PddPrbIndicadorDTO eliminarPrbIndicador(Long idProbInd);

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite registrar una problematica.
	 * 
	 * @param peticion son los datos que se van a registrar.
	 * @return un objeto de tipo PddProblematicaDTO con los datos guardados.
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO registrarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que consultar un PddIndicador por medio del identificador
	 * @param id Long que representa el identificador del PddIndicador
	 * @return un objeto de tipo PddIndicadorDTO con la informacion correspondiente.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO consultarPddIndicadorPorId(Long id);
	
	/**
	 * Metodo que permite guardar un PddIndicador.
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion para guardar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO registrarPddIndicadorYPddPrbIndicador(PddPrbIndicadorDTO peticion);

	
	/**
	 * Metodo que permite modificar un PddIndicador.
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion para modificar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO modificarPddIndicador(PddIndicadorDTO peticion);
	
	/**
	 * Metodo que permite buscar un pddCompromisos por el identificador
	 * 
	 * @param id Long que representa el identificador del compromiso
	 * @return un objeto tipo PddCompromisoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarPddCompromisoPorId(Long id) throws SpddExceptions;
	
	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddVigente() throws SpddExceptions;
	
	public GenericoDTO consultarProblematicaPorIdCompromiso(Long idCompromiso);
	
	public PddProblematicaDTO modificarProblematica(PddProblematicaDTO peticion) throws SpddExceptions;
	
	public GenericoDTO consultarTodosPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todas las competencias asociadas
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

	
	public GenericoDTO obtenerTodosPddProblematica() throws SpddExceptions;
	
	/**
	 * metodo que permite obtener todos compromisos especificos asociados a un compromiso estrategico filtrado 
	 * y paginado
	 * @param peticion objeto de tipo PddCompromiso con la información necesaria para filtrar y paginar
	 * @return GenericoDTO con la lista de los compromisos filtrados y paginados
	 * @throws SpddExceptions 
	 */
	public GenericoDTO consultarPddCompromisoEspecificoFitlrado(PddCompromisoEspecificoDTO peticion);
}
