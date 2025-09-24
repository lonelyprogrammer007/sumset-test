package co.gov.sdp.spdd.core.bp.iservice.bpproyinv;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author DANIEL, SEBASTIAN
 *
 */
public interface IBPProyInvConsultarService {
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para consultar un BpProyectoInversion segun el filtro
	 * aplicado para este
	 * 
	 * @param peticion objeto de tipo BpProyectoInversionDTO que contiene la informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para consultar todos los BpProyectosInversion que esten en la vista de VistaBpProyectoInversion
	 * @return un objeto genericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarProyectoInversionTodos(BpProyectoInversionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que se encarga de la comunicacion entre el appdata y el appcore para consultar todos los registros que hay en BD de la tabla BpAporteCiudadano pero que aun no tienen una
	 * relacion con el proyecto BpProyectoInversion indicado en el parametro 
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Un objeto GenericoDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para consutar un BpProyInv por medio del identificador del proyecto de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se quiere buscar
	 * @return un objeto de tipo BpProyInversionDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO consultarBpProyectoInversionPorId(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos BpProyInvAporte por el identificador del proyecto paginados
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador objeto de tipo pageable que contiene la informacion para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para buscar un BpAporteCiudadano por medio del identificador
	 * @param idAporte Long que representa el identificador del Aporte ciudadano
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la informacion  correspondiente
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano y que estan relacionados con el proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las BpInciativasCiudadanas viables y filtradas
	 * @param bpIniciativaCiudadanaDTO objeto de tipo BpIniciativaCiudadana con la informacion para filtrar
	 * @return un objeto de tipo GenericoDTO con la ifnormacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaViablesFiltradas(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las BpIniciativasCiudadanas que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar los grupos etarios, que hacen referencia al modelo
	 * BpProyInvPoblacion
	 * @param bpProyInvPoblacionDTO
	 * @return GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarGruposEtarios(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar los "ProyInvEtnico" asociados a un proyecto de inversion
	 * @param BpProyInvEtnicoDTO peticion, peticion con el DTO requerido para la consuta,
	 * con el id del proyecto de inversion, informacion de paginacion
	 * @return GenericoDTO, que contiene la lista
	 */
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO peticion) throws SpddExceptions;

	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las ProyInvFiancia que estan relacionadas con el proyecto de inversion especificado
	 * @param BpProyInvFinanciaDTO  que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las relaciones entre proyecto de inversion y politica publica filtrados por sus campos
	 * @param peticion objeto de tipo BpIniciativaCiudadanaDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtenre todos las policitcas publicas filtradas por sus campos
	 * @param pddPoliticaPublicadDTO objeto de tipo PddPoliticaPublicaDTO que contiene la informacion para filtar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para consultar las politicas publicas que no estan relacionadas con el proyecto de inversion
	 * @param idProyInversion Long que representa el proyecto de inversion que esta relacionado con las politicas publicas
	 * @return un objeto GenericoDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(Long idProyInversion) throws SpddExceptions;
	
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
	public GenericoDTO consultarTodosBpProyInvLineaPorIdProyectoInversion(Long idProyecto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para consultar todas las politicas publicas ordenadas por el campo Politca ascendentemente
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublica() throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener todas las relaciones entre proyecto de inversion y MRIndicador filtrados por sus campos
	 * @param BpProyInvPmrDTO objeto de tipo BpProyInvPmrDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO peticion) throws SpddExceptions;
}
