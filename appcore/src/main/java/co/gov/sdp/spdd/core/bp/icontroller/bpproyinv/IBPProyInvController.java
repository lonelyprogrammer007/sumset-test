package co.gov.sdp.spdd.core.bp.icontroller.bpproyinv;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface del controlador que expone los servicios al cliente. Esta interface
 * condensara los Capítulos 8,9,10 y 11 del modulo de BP.
 * 
 * @author DANIE, Bryan
 * @version 1.0 01/04/2020
 */
public interface IBPProyInvController {
	
	/**
	 * Metodo que permite consultar un BpProyectoInversion segun el filtro
	 * aplicado para este
	 * 
	 * @param peticion objeto de tipo BpProyectoInversionDTO que contiene la informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion);

	/**
	 * Metodo que permite consultar todos los BpProyectosInversion que esten en la vista de VistaBpProyectoInversion
	 * @return un objeto genericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionTodos(BpProyectoInversionDTO peticion);
	
	/**
	 * Metodo que permite registrar la informacion basica de un proyecto de inversion.
	 * Este guardar corresponde a la informacion que existe en el TAB-Identificacion del proyecto.
	 * 
	 * @param peticion objeto de tipo BpProyectoInversionDTO que
	 *                                 contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO registrarProyectoInversionTABIndentificacionProyecto(BpProyectoInversionDTO peticion);
	
	/**
	 * Metodo que se encarga de consultar todos los registros que hay en BD de la tabla BpAporteCiudadano pero que aun no tienen una
	 * relacion con el proyecto BpProyectoInversion indicado en el parametro 
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Un objeto GenericoDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto);

	/**
	 * Metodo que permite registrar la informacion de un BpProyInvAporte la cual indica la realacion entre un BpProyectoInversion y BpAporteCiudadano
	 * En este caso registra la relacion entre un proyecto de inversion y varios aportes ciudadanos cargados por archivo que fueron seleccionados
	 * @param bpProyInvAporte Objeto de tipo BpProyInvAporteDTO que contiene la informacion para guardar
	 * @return un objeto de tipo BpProyInvAporteDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO registrarVariosBpAportesCiudadanosCargadosPorArchivoConBpProyectoInversion(BpProyInvAporteDTO peticion);

	/**
	 * Metodo que permite buscar un BpProyInv por medio del identificador del proyecto de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se quiere buscar
	 * @return un objeto de tipo BpProyInversionDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO consultarBpProyectoInversionPorId(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite registrar la informacion de un BpAporteCiudadano
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la informacion para guardar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion guardada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO registrarBpAporteCiudadano(BpAporteCiudadanoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todos BpProyInvAporte por el identificador del proyecto paginados
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador objeto de tipo pageable que contiene la informacion para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO peticion);
	
	/**
	 * Metodo que permite buscar un BpAporteCiudadano por medio del identificador
	 * @param idAporte Long que representa el identificador del Aporte ciudadano
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la informacion  correspondiente
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte);
	
	/**
	 * Metod que permite eliminar un 
	 * BpProyInvAporte por medio de su identificador y tambien el BpAporteCiudadano que esta
	 * relacionado.
	 * @param idProyAporte Long que representa el identificador del BpProyInvAporte a eliminar
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO eliminarBpProyInvAporte(Long idProyAporte);
	
	/**
	 * Metodo que permite modificar la informacion de un BpAporteCiudadano
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la informacion para modificar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion modificada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(BpAporteCiudadanoDTO peticion);
	
	/**
	 * Metodo que permite obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano y que estan relacionados con el proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos();

	/**
	 * Metodo que permite modificar la informacion basica de un proyecto de inversion. 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO modificarBpProyectoInversion(BpProyectoInversionDTO peticion);
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las BpInciativasCiudadanas viables y filtradas
	 * @param bpIniciativaCiudadanaDTO objeto de tipo BpIniciativaCiudadana con la informacion para filtrar
	 * @return un objeto de tipo GenericoDTO con la ifnormacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaViablesFiltradas(BpIniciativaCiudadanaDTO peticion);
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las BpIniciativasCiudadanas que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvIniciativa en BD
	 * @param bpProyInvIniciativaDTO objeto de tipo BpProyInvIniciativaDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvIniciativaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvIniciativaDTO registrarVariasBpIniciativasCiudadanasViablesConBpProyectoInversion(BpProyInvIniciativaDTO peticion);

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyectoInversionDTO en BD
	 * @param peticion objeto de tipo BpProyectoInversionDTO con la informacion para guardar en BD
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO registrarBpProyectoInversionLocaliza(BpProyInvLocalizaYTerritorizacionDTO peticion);
	
	/**
	 * Metodo que permite eliminar un BpProyectoInvresionDTO por medio del identificador
	 * @param idProyectoInversion Long que representa el indentificador del BpProyectoInversion
	 * @return un objeto de BpProyectoInversionDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO eliminarBpProyectoInversion(Long idProyectoInversion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvEspecif en BD
	 * @param peticion objeto de tipo BpProyInvEspecifDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO registrarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvMetaPlan en BD
	 * @param peticion objeto de tipo BpProyInvMetaPlanDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvMetaPlanDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO registrarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvProducto en BD
	 * @param peticion objeto de tipo BpProyInvProductoDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO registrarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvActividad en BD
	 * @param peticion objeto de tipo BpProyInvActividadDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO registrarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions;


	/**
	 * Metodo que permite la comunicacion entre el appdata y el BackSegplan para obtener todas las ProyInvFiancia que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar sus respectivos financiaciones
	 * @return unas lista de objetos ProyInvFiancia con sus respectivos montos
	 */
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO peticion) throws SpddExceptions;

	
	/**
	 * Metodo que permite consultar los grupos etarios, que hacen referencia al modelo
	 * BpProyInvPoblacion
	 * @param bpProyInvPoblacionDTO
	 * @return GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarGruposEtarios(BpProyInvPoblacionDTO bpProyInvPoblacionDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar los "ProyInvEtnico" asociados a un proyecto de inversion
	 * @param BpProyInvEtnicoDTO peticion, peticion con el DTO requerido para la consuta,
	 * con el id del proyecto de inversion, informacion de paginacion
	 * @return GenericoDTO, que contiene la lista
	 */
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO bpProyInvEtnicoDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore 
	 * para guardar la informacion de varios BpProyInvPoblacion asociados a un proyecto de inversion
	 * Incluye en el DTO un id asociado a un ArgumentoListaSimple de la tabla en BD
	 *  en BD
	 * @param peticion objeto de tipo BpProyInvPoblacionDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvPoblacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO registrarBpProyInvPoblacionAsociadoAProyInversion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite registrar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO registrarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite modificar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
<<<<<<< HEAD
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite modificar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO modificarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite eliminar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite eliminar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
=======
	public BpProyInvEtnicoDTO registrarVariosBpProyInvEtnicoAsociadoAProyInversion(BpProyInvEtnicoDTO peticion) throws SpddExceptions;

	
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvFinancia en BD
	 * @param peticion objeto de tipo BpProyInvFinanciaDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvFinanciaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public  BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite eliminar ProyInvFinancia y sus ProyInvAnualiza asociados
	 * @param idFuente id de la fuente a eliminar
	 * @param idLsFUente id de la ls asociada
	 * @param idProyectoInv id del proyecto de inversion
	 * @return un objeto de tipo BpProyInvFinanciaDTO con la respuesta respectiva
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO eliminarProyInvFinanciaPorId(BpProyInvFinanciaDTO peticion) throws SpddExceptions;
	
	
	/**
	 * Metodo que permite modificar un ProyInvFinancia y sus ProyInvAnualiza asociados
	 * @param peticion objeto BpProyInvFinanciaDTO a modificar
	 * @return objeto BpProyInvFinanciaDTO con la informacion pertinente
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO modificarFuenteFinanciacion(BpProyInvFinanciaDTO peticion)throws SpddExceptions;
	
	

	 /* Metodo que permite obtener todas las relaciones entre proyecto de inversion y politica publica filtrados por sus campos
	 * @param peticion objeto de tipo BpIniciativaCiudadanaDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO peticion);
	
	/**
	 * Metodo que permite obtenre todos las policitcas publicas filtradas por sus campos
	 * @param pddPoliticaPublicadDTO objeto de tipo PddPoliticaPublicaDTO que contiene la informacion para filtar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO peticion);
	
	/**
	 * Metodo que permite consultar todas las politicas publicas 
	 * @param idProyInversion 
	 * @return un objeto GenericoDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublica(Long idProyInversion);
	
	/**
	 * Metodo que permite guardar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO registrarBpProyInvPolitica(BpProyInvPoliticaDTO peticion);
	
	/**
	 * Metodo que permite modificar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(BpProyInvPoliticaDTO peticion);
	
	/**
	 * Metodo que permite eliminar un BpProyInvPolitica de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y politica publica
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO eliminarBpProyInvPolitica(Long idProyPolitica);
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consutar una lista de BpProyInvLine por medio del identificador del proyecto
	 * de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosBpProyInvLineaPorIdProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite guardar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO registrarBpProyInvLinea(BpProyInvLineaDTO peticion);
	
	/**
	 * Metodo que permite modificar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO modificarBpProyInvLinea(BpProyInvLineaDTO peticion);
	
	/**
	 * Metodo que permite eliminar un BpProyInvLinea de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y linea de inversion
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO eliminarBpProyInvLinea(Long idProyLinea);
	
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y MRIndicador filtrados por sus campos
	 * @param BpProyInvPmrDTO objeto de tipo BpProyInvPmrDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO peticion);
	
	/**
	 * Metodo que permite guardar un BpProyInvPrm en la BD
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO registrarBpProyInvPmr(BpProyInvPmrDTO peticion);
	
	/**
	 * Metodo que permite modificar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO modificarBpProyInvPmr(BpProyInvPmrDTO peticion);
	
	/**
	 * Metodo que permite eliminar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a eliminar
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO eliminarBpProyInvPmr(Long idProyPmr);



>>>>>>> developer
}
