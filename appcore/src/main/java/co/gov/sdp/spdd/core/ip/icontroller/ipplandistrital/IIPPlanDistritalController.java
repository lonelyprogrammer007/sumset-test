package co.gov.sdp.spdd.core.ip.icontroller.ipplandistrital;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface del controlador que expone los servicios al cliente. Esta interface
 * condensara los Capítulos 10, 11, 14, 15, 16 y 17.
 * 
 * @author DANIEL, SEBASTIAN
 * @version 1.0 02/03/2020
 */
public interface IIPPlanDistritalController {

	/**
	 * Metodo que permite obtener todos los planes de desarrollo distrital
	 * 
	 * @param peticion objeto de tipo PddDTO:RespuestaDTO que contiene la
	 *                 informacion del paginado (pagina y tamanioPagina)
	 * @return un objetoDTO de tipo GenericoDTO con la informacion filtrada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPddPorFiltro(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite registrar o guardar un plan de desarrollo distrital
	 * 
	 * @param peticion objeto de tipo PddDTO con la informacion a guardar
	 * @return un objeto de tipo PddDTO con la informacion guardada, en caso
	 *         contrario null
	 * @throws SpddExceptions
	 */
	public PddDTO registrarPdd(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que modifica un plan de desarrollo distrital pdd
	 *
	 * @param PddDTO campos a modificar
	 * @return un objeto tipo dto con un codigo y mensaje de respuesta
	 */
	public PddDTO modificarPdd(PddDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite obtener los niveles de un plan de desarrollo distrital por
	 * medio del identificador del plan
	 * 
	 * @param idPlan Long que representa el identificador del plan de desarrollo del
	 *               cual se quieren obtener los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion consultada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPddNivelPorIdPlanDesarrolloDistrital(Long idPlan) throws SpddExceptions;

	/**
	 * Metodo que permite registra y modificar un nivel de un plan de desarrollo
	 * distrital.
	 * 
	 * @param pddNivel objeto de tipo PddNivelDTO:RespuestaDTO que contiene la
	 *                 informacion a agregar.
	 * @return un objeto de tipo PddNivelDTO:RespuestaDTO con la informacion
	 *         guardada, en caso contrario null
	 * @throws SpddExceptions
	 */
	public GenericoDTO registrarPddNivel(PddNivelDTO peticion) throws SpddExceptions;

	/**
	 *
	 * Metodo que permite guardar un PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a guardar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         guardo
	 */
	public PddNivelAtributoDTO registrarPddNivelAtributo(PddNivelAtributoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los planes de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO:RespuestaDTO que contiene la
	 *                 informacion del paginado (pagina y tamanioPagina)
	 * @return un objetoDTO de tipo GenericoDTO con la informacion filtrada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPdlPorFiltro(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los planes de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO:RespuestaDTO que contiene la
	 *                 informacion
	 * @return un objetoDTO de tipo GenericoDTO con la informacion
	 * @throws SpddExceptions
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
	 * Metodo que permite registrar un nivel de un plan de desarrollo local.
	 * 
	 * @param pdlNivel objeto de tipo PdlNivelDTO:RespuestaDTO que contiene la
	 *                 informacion a agregar.
	 * @return un objeto de tipo PdlNivelDTO:RespuestaDTO con la informacion
	 *         guardada, en caso contrario null
	 * @throws SpddExceptions
	 */
	public PdlDTO registrarPdl(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los pddMetaResultado por proyectos estrategicos
	 * o proyectos locales
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddMetaResultadoPorProyecto(PddMetaResultadoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar todos los PddNivelAtributo de un nivel en
	 * especifico en orden ascendente
	 * 
	 * @param idPddNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO peticion);

	/**
	 * Metodo que permite modificar un nivel de un plan de desarrollo local.
	 * 
	 * @param pddNivel objeto de tipo PdlNivelDTO:RespuestaDTO que contiene la
	 *                 informacion a modificar.
	 * @return un objeto de tipo PdlNivelDTO:RespuestaDTO con la informacion
	 *         modificada , en caso contrario null
	 * @throws SpddExceptions
	 */
	public PdlDTO modificarPdl(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite eliminar una meta resultado por su id
	 * 
	 * @param idMetaResultado id de la meta resultado que se desea elminar
	 * @return un dto con una respuesta correcta o incorrecta
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO eliminarMetaResultadoPorId(Long idMetaResultado) throws SpddExceptions;

	/**
	 * Metodo que permite consultar todos los PddNivelAtributo paginados que
	 * corresponden al nivel atributo padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PddNivelAtributo
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(PddNivelAtributoDTO peticion)
			throws SpddExceptions;

	/**
	 * Registrar indicadores metas resultados
	 * 
	 * @param peticion peticion que se desea registrar tipo indicador que trae el
	 *                 idmetaresultado
	 * @return un pddMRIndicador con un codigo de respuesta
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO registrarIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Esto permite eliminar un indicador meta resultado
	 * 
	 * @param id id del indicador meta resultado que se desea eliminar
	 * @return un respuesta de exito o de fracaso
	 */
	public PddMRIndicadorDTO eliminarMetaResultadoIndicador(Long id);

	/**
	 * metodo con consulta los indicadores de un meta resultadoS
	 * 
	 * @param peticion
	 * @return
	 */
	public GenericoDTO consultarIndicadorMetaResultado(PddMRIndicadorDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public GenericoDTO consultarMetaProductoPorMR(PddMetaProductoDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMetaProductoDTO eliminarMetaProducto(Long id);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMetaProductoDTO modificarMetaProducto(PddMetaProductoDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMetaProductoDTO registrarMetaProducto(PddMetaProductoDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public GenericoDTO consultarIndicadoresMetaProducto(PddMpIndicadorDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMpIndicadorDTO registrarIndicadorMetaProducto(PddIndicadorDTO peticion);

	/**
	 * 
	 * @param idIndicadorMp
	 * @return
	 */
	public PddMpIndicadorDTO eliminarIndicadorMp(Long idIndicadorMp);

	/**
	 * Metodo que permite modificar un PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a modificar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         modifico
	 */
	public PddNivelAtributoDTO modificarPddNivelAtributo(PddNivelAtributoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * PddNivelAtriguto
	 * 
	 * @param idAtributo Long que representa el identificador PddNivelAtriguto que
	 *                   se quiere eliminar
	 * @throws SpddExceptions
	 */
	public PddNivelAtributoDTO eliminarPddNivelAtributo(Long idAtributo);

	/**
	 * MEtodo que permite copiar la estructara del pdd al pdl vigente
	 * 
	 * @param idPdd Long que representa el identificador del pdd del cual se va a
	 *              copiar la estrutura de niveles
	 * @return un GenerticoDTO con la respuesta correspondiente
	 */
	public GenericoDTO copiarEstructuraPddToPdl(PdlDTO peticion);

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
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions;

	/**
	 * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden
	 * ascendente
	 * 
	 * @param idPdlNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO peticion);

	/**
	 * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo
	 * padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PdlNivelAtributo
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO peticion);

	/**
	 * Metodo que permite guardar una PdlNivelAtributo en BD
	 * 
	 * @param potObraDTO objeto de tipo PdlNivelAtributoDTO con la informacion a
	 *                   guardar
	 * @return un objeto de tipo PdlNivelAtributoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PdlNivelAtributoDTO registrarPdlNivelAtributo(PdlNivelAtributoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite construir el arbor con toda la informacion de los
	 * componentes de niveles que estan desbalanceados.
	 * 
	 * @param idPlanDesarrollo Long que representa el identificador al cual se le
	 *                         van a buscar todos los componentes de niveles
	 *                         desbalanceados
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo);

	/**
	 * Endpoint expuesto que consulta todas las entidades relacionadas a un meta
	 * producto
	 * 
	 * @param peticion con los campos idMetaProducto,pagina y tamanioPagina
	 * @return un genericoDTO con la lista y un codigo de respuesta
	 */
	public GenericoDTO consultarTodosMpEntidades(PddMpIndicadorEntidadDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMpIndicadorEntidadDTO registrarMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddMpIndicadorEntidadDTO modificarMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion);

	/**
	 * 
	 * @param idMpEntidad
	 * @return
	 */
	public PddMpIndicadorEntidadDTO eliminarMpIndicadorEntidad(Long idMpEntidad);

	/**
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarTodosPddRangoPonderacion() throws SpddExceptions;

	/**
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarRangoPonderacionPorIdPdd(Long id) throws SpddExceptions;

	/**
	 * Esto permite eliminar un rango ponderacion
	 * 
	 * @param id id del indicador rango ponderacion que se desea eliminar
	 * @return un respuesta de exito o de fracaso
	 */
	public PddRangoPonderacionDTO eliminarPddRangoPonderacion(Long id) throws SpddExceptions;

	/**
	 *
	 * Metodo que permite guardar un PddRangoPonderacion
	 * 
	 * @param peticion objeto de tipo PddRangoPonderacionDTO que contiene la
	 *                 informacion a guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion que se
	 *         guardo
	 */
	public PddRangoPonderacionDTO registrarPddRangoPonderacion(MultipartFile logo, String rango, String descripcion,
			Long idPlanDesarrollo) throws SpddExceptions;

	/**
	 *
	 * Metodo que permite modificar un PddRangoPonderacion
	 * 
	 * @param peticion objeto de tipo PddRangoPonderacionDTO que contiene la
	 *                 informacion a guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion que se
	 *         guardo
	 * @throws IOException 
	 */
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(Long idRango, MultipartFile logo, String rango,
			String descripcion, Long idPlanDesarrollo) throws SpddExceptions, IOException;

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadoresMetaProducto() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener un
	 * pdd por el identificador
	 * 
	 * @param id Long que representa el identificador del pdd que se desea buscar
	 * @return un objeto PddDTO con la indormacion del plan encontrado o null en
	 *         caso contrario
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddPorId(Long id);
	
	/**
	 * Metodo que permite obtener el Plan de desarrollo local que esta vigente para la alcaldia local
	 * @param codigoEntidad String que representa el codigo de la entidad de la alcaldia local
	 * @return un objeto de tipo PdlDTO con la info del pdlDTO vigente
	 */
	public PdlDTO consultarPdlVigente(String codigoEntidad);
}
