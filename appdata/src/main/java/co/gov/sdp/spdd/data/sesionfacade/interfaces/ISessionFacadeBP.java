package co.gov.sdp.spdd.data.sesionfacade.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;

/**
 * Interface del SessionFacade del modulo BP que permite la comunicacion entre
 * appdata y appcore
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface ISessionFacadeBP {

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consultar un BpProyectoInversion segun el filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo BpProyectoInversionDTO que contiene la
	 *                 informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consutar una lista de BpProyInvLine por medio del identificador del proyecto
	 * de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 */
	public GenericoDTO consultarBpProyectoInversionTodos() throws SpddExceptions;

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
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consutar una lista de BpProyInvTipo por medio del identificador del proyecto
	 * de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBpProyInvTipoPorIdProyectoInversion(Long idProyecto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para buscar
	 * un BpProyInvTipo que corresponda a los identificadores del tipo y proyecto
	 * que se pasan como parametro
	 * 
	 * @param idLsTipo        Long que representa el identificador del argumento de
	 *                        lista simple que representa el tipo
	 * @param idProyInversion Long que respresenta el idnetificador del proyecto de
	 *                        inversion
	 * @return un objeto de tipo BpProyInvTipoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvTipoDTO consultarBpProyInvTipoPorIdLsTipoYIdProyInv(Long idLsTipo, Long idProyInversion)
			throws SpddExceptions;

	/**
	 * Metodo que se encarga de la comunicacion entre el appdata y el appcore para
	 * consultar todos los registros que hay en BD de la tabla BpAporteCiudadano.
	 * 
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO consultarBpAporteCiudadanoTodos() throws SpddExceptions;

	/**
	 * Metodo que se encarga de la comunicacion entre el appdata y el appcore para
	 * consultar todos los registros que hay en BD de la tabla BpAporteCiudadano
	 * pero que aun no tienen una relacion con el proyecto BpProyectoInversion
	 * indicado en el parametro
	 * 
	 * @param idProyecto Long que representa el identificador del
	 *                   BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto)
			throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO filtrarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar
	 * la informacion basica de un proyecto de inversion. Este guardar corresponde a
	 * la informacion que existe en el TAB-Identificacion del proyecto.
	 * 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que
	 *                               contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO guardarProyectoInversionIndentificacionProyecto(
			BpProyectoInversionDTO bpProyectoInversionDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar
	 * la informacion basica de un tipo de proyecto de inversion. Este guardar
	 * corresponde a la informacion que existe en el TAB-Identificacion del
	 * proyecto.
	 * 
	 * @param bpProyInvTipoDTO objeto de tipo BpProyInvTipoDTO que contiene la
	 *                         informacion para guardar
	 * @return un objeto de tipo BpProyInvTipoDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyInvTipoDTO guardarProyInvTipo(BpProyInvTipoDTO bpProyInvTipoDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consutar un BpProyInv por medio del identificador del proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo BpProyInversionDTO con la informacion
	 *         correspondiente
	 */
	public BpProyInvAporteDTO guardarBpProyInvAporte(BpProyInvAporteDTO bpProyInvAporteDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * eliminar de la BD todos los BpProyInvTipo que estan relacionados a un
	 * proyecto de inversion
	 * 
	 * @param idBpProyectoInversion Long que representa el identificador del
	 *                              proyecto de inversion del cual se quiere
	 *                              eliminar los BpProyInvTipo
	 */
	public void eliminarBpProyInvTiposDeIdProyectoInversion(Long idBpProyectoInversion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para
	 * consutar un BpProyInv por medio del identificador del proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo BpProyInversionDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO consultarProyInvPorId(Long idProyecto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar
	 * la informacion de un BpAporteCiudadano
	 * 
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la
	 *                             informacion para guardar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion guardada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO guardarBPAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO)
			throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @throws SpddExceptions
	 */
	public void eliminarGruposEtarios(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @throws SpddExceptions
	 */
	public void eliminarIniciativaCiudadana(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @throws SpddExceptions
	 */
	public void eliminarUbicaciones(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaEtarioDTO guardarGruposEtarios(BpIniciativaEtarioDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaUbicacionDTO guardarUbicacionIniciativaCiudadana(BpIniciativaUbicacionDTO peticion)
			throws SpddExceptions;
	
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos BpProyInvAporte por el identificador del proyecto paginados
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador objeto de tipo pageable que contiene la informacion para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO bpProyInvAporteDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para buscar un BpAporteCiudadano por medio del identificador
	 * @param idAporte Long que representa el identificador del Aporte ciudadano
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la informacion  correspondiente
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte) throws SpddExceptions;

	/**
	 * Metod que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyInvAporte por medio de su identificador
	 * @param idProyAporte Long que representa el identificador del BpProyInvAporte a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvAporte(Long idProyAporte) throws SpddExceptions;
	
	/**
	 * Metod que permite la comunicacion entre el appdata y el appcore para eliminar un BpAporteCiudadano por medio de su identificador
	 * @param idAporte Long que representa el identificador del BpAporteCiudadano a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarBpAporteCiudadano(Long idAporte) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para buscar un BpProyInvAporte por medio del identificador
	 * @param idProyInvAporte Long que representa el identificador BpProyInvAporte
	 * @return un objeto de tipo BpProyInvAporteDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO consultarProyInvAportePorId(Long idProyInvAporte) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar la informacion de un BpAporteCiudadano
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la informacion para modificar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion modificada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener un BpAporteCiudadano mediante la combinacion de campos pasados como parametros
	 * @param accion String que representa la accion por la cual se va a buscar
	 * @param fuente String que representa la fuente por la cual se va a buscar
	 * @param idPddNivelAtributo Long que representa el identificador del nivels atributo por el cual se va a buscar
	 * @return Un objeto BpAporteCiudadano con la informacion correspondientes.
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(String accion, String fuente, Long idPddNivelAtributo) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para  eliminar todos los Aportes Ciudadanos que hayan sido cargados desde
	 * un archivo plano y que esten relacionados con el proyecto de inversion pasado como parametro.
	 * Es decir los registros en la tabla intermedia BpProyInvAporte
	 * @param idProyecto Long que represente el identificado del proyecto al cual se le eliminaran todos lo
	 * apotes ciudadanos cargados desde un archivo que esten relacionados.
	 * @throws SpddExceptions
	 */
	public void eliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto(Long idProyInversion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() throws SpddExceptions;
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano y que estan relacionados con el proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto) throws SpddExceptions;


	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar
	 * la informacion basica de un proyecto de inversion. 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO modificarProyectoInversionProyecto(BpProyectoInversionDTO bpProyectoInversionDTO) throws SpddExceptions;
	
	/**
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BpIniciativaCondicionDTO> obtenerCondicionesPorIniciativa(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCondicionDTO guardarIniciativaCondicion(BpIniciativaCondicionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param listaCondiciones
	 * @throws SpddExceptions
	 */
	public void eliminarTodasCondicionIniciativa(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO consultarBpIniciativaCiudadanaPorId(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions 
	 */
	public List<BpIniciativaEtarioDTO> consultarGruposEtariosPorIniciativa(Long idIniciativa) throws SpddExceptions;

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions 
	 */
	public List<BpIniciativaUbicacionDTO> consultarUbicacionesGruposEtariosPorIniciativa(Long idIniciativa) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las BpIniciativasCiudadanas que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las relaciones entre iniciativa ciudadana y proyecto de inversion segun
	 * el identificador del proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un GenericoDTO con la lista de BpProyInvIniciativa
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para elimnar todas las relaciones entre iniciativa ciudadana y proyecto de inversion correspondientes
	 * al proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto de inversion al cual se le va a elimnar los registros
	 */
	public void eliminarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvIniciativa en BD
	 * @param bpProyInvIniciativaDTO objeto de tipo BpProyInvIniciativaDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvIniciativaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvIniciativaDTO guardarBpProyInvIniciativa(BpProyInvIniciativaDTO bpProyInvIniciativaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite guardar la informacion de un BpProyInvLocaliza en BD
	 * @param BpProyInvLocalizaDTO objeto de tipo BpProyInvLocalizaDTO con la informacion para guardar en BD
	 * @return un objeto de tipo BpProyInvLocalizaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLocalizaDTO guardarBPProyectoInvLocaliza(BpProyInvLocalizaDTO BpProyInvLocalizaDTO) throws SpddExceptions;

	/**
	 * Metodo que permite elimnar todas las relaciones entre territorializacion y proyecto de inversion correspondientes
	 * al proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto de inversion al cual se le va a elimnar los registros
	 */
	public void eliminarTodosBpProyInvLocalizaPorIdProyecto(Long idProyInversion) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvEspecif en BD
	 * @param peticion objeto de tipo BpProyInvEspecifDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO guardarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvMetaPlan en BD
	 * @param peticion objeto de tipo BpProyInvMetaPlanDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO guardarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un BpProyInvEspecif por medio del identificador
	 * @param idProyObjEspecif Long que representa el identificador BpProyInvEspecif
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO consultarBpProyInvEspecifPorId(Long idProyObjEspecif) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvProducto en BD
	 * @param peticion objeto de tipo BpProyInvProductoDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO guardarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un BpProyInvProducto por medio del identificador
	 * @param idProducto Long que representa el identificador BpProyInvProducto
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO consultarBpProyInvProductoPorId(Long idProducto) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un BpProyInvActividad por medio del identificador
	 * @param idProducto Long que representa el identificador BpProyInvActividad
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO consultarBpProyInvActividadPorId(Long idProducto) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un BpProyInvActividad por medio del identificador
	 * @param idProducto Long que representa el identificador BpProyInvActividad
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO guardarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite buscar un BpProyInvMetaPlan por medio del identificador
	 * @param idProyObjEspecif Long que representa el identificador BpProyInvMetaPlan
	 * @return un objeto de tipo BpProyInvMetaPlanDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO consultarBpProyInvMetaPlanPorIdProyInvEspecif(Long idProyObjEspecif);

	/**
	 * Metodo que permite la meta por objetivo especifico el identificador del proyecto de inversion pasado como parametro
	 * @param idProyMetaPlan Long que representa el identificador del proyecto de inversion por el cual se va a filtra
	 * @return un objeto BpProyInvProducto
	 */
	public BpProyInvProductoDTO consultarBpProyInvProductoPorIdProyInvMetaPlan(Long idProyMetaPlan);
	
	/**
<<<<<<< HEAD
	 * Metodo que permite consultar los grupos etarios, que hacen referencia al modelo
	 * BpProyInvPoblacion
	 * @param bpProyInvPoblacionDTO
	 * @return GenericoDTO
	 * @throws SpddExceptions
=======
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa) throws SpddExceptions;

	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos BpProyInvFinancia por el identificador del proyecto paginados
	 * @param BpProyInvFinanciaDTO dto de la entidad
	 * @return un objeto de tipo BpProyInvFinancia con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO bpProyInvFinanciaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todos BpProyInvAnualiza por el identificador de la fuente paginados
	 * @param BpProyInvAnualizaDTO dto de la entidad
	 * @return un objeto de tipo BpProyInvAnualiza con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosProyInvAnualizaPorIdFuentePaginado(BpProyInvAnualizaDTO bpProyInvAnualizaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite guardar un BpProyInvFinancia por medio del identificador
	 * @param peticion objeto de tipo BpProyInvFinanciaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvFinanciaDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions;
	
	
	/**
	 * metodo que permite obtener un proyecto de inversion finania por idLsfuente y por idProyInv
	 * @param idLsFUente id de la lista fuente
	 * @param idProyectoInv id del proyecto de inversion
	 * @return un objeto de tipo BpProyInvFinanciaDTO
	 */
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorIdLSFuYIdProy(Long idLsFUente,  Long idProyectoInv );

	/**
	 * Metodo que permite consultar un proyecto de inversion por idFuente
	 * @param idFuente idFuente de la fuente a consultar
	 * @return
	 */
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorId(Long idFuente);
	
	/**
	 * Metodo que permite guardar un BpProyInvAnualiza por medio del identificador
	 * @param peticion objeto de tipo BpProyInvAnualizaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvAnualizaDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvAnualizaDTO registrarBpProyInvAnualiza(BpProyInvAnualizaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite consultar los proyectos de tipo "BpProyInvPoblacion" asociados a un proyecto de inversion
	 * @param bpProyInvPoblacionDTO BpProyInvPoblacionDTO, representa el dto requerido con la informacion de consulta, id del proyecto  deinversion, datos de paginacion.
	 * @return un objeto de tipo GenericoDTO con la lista
>>>>>>> developer
	 */
	public GenericoDTO consultarGruposEtarios(BpProyInvPoblacionDTO bpProyInvPoblacionDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar los proyectos de tipo "BpProyInvEtnico" asociados a un proyecto de inversion
	 * @param peticion BpProyInvEtnicoDTO, representa el dto requerido con la informacion de consulta, id del proyecto  deinversion, datos de paginacion.
	 * @return un objeto de tipo GenericoDTO con la lista
	 */
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO peticion) throws SpddExceptions;

	
	/**
	 *
	 * Metodo que permite guardad un BpProyInvPoblacion
	 * @param peticion BpProyInvPoblacionDTO con la informacion requerida
	 * @return un objeto de tipo BpProyInvPoblacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO guardarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 *
	 * Metodo que permite guardad un BpProyInvEtnico
	 * @param peticion BpProyInvEtnicoDTO con la informacion requerida
	 * @return un objeto de tipo BpProyInvEtnicoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO guardarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
	
	/**
	 *
	 * Metodo que permite validar la unicidad de un BpProyInvPoblacion antes de ser guardado
	 * @param peticion BpProyInvPoblacionDTO con la informacion requerida
	 * @return un objeto de tipo BpProyInvPoblacionDTO con un valor booleano que contiene la validacion de unicidad,
	 * NOTA: si el valor el true, no viola la unicidad y se puede guardad,
	 * 		 si el valor es false, viola la unicidad y no se puede guardar.
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO consultarBpProyInvPoblacionUnicidad(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 *
	 * Metodo que permite validar la unicidad de un BpProyInvEtnico antes de ser guardado
	 * @param peticion BpProyInvEtnicoDTO con la informacion requerida
	 * @return un objeto de tipo BpProyInvEtnicoDTO con un valor booleano que contiene la validacion de unicidad,
	 * NOTA: si el valor el true, no viola la unicidad y se puede guardad,
	 * 		 si el valor es false, viola la unicidad y no se puede guardar.
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO consultarBpProyInvEtnicoUnicidad(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
	
	/**
<<<<<<< HEAD
	 * Metodo que permite modificar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO consultarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite eliminar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite eliminar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite consultar la existencia de un proyecto de inversion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO consultarBpProyectoInversion(BpProyectoInversionDTO peticion) throws SpddExceptions;
	
	
	/**
	 * Metodo que permite consultar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO consultarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions;
=======
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener todas las relaciones entre proyecto de inversion y politica publica filtrados por sus campos
	 * @param bpProyInvPoliticaDTO objeto de tipo BpIniciativaCiudadanaDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions;
>>>>>>> developer

	/**

	 * Metodo que permite eliminar todos los ProyInvAnualiza por el ID de la fuente
	 * @param idFuente id de la fuente a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarTodosProyInvAnualizaPorIdFuente(Long idFuente) throws SpddExceptions;
	
	/**
	 * Metodo que permite eliminar un ProyInvFinancia por su ID
	 * @param idFuente id de la fuente en cuestion
	 * @throws SpddExceptions
	 */
	public void eliminarProyInvFinanciaPorId(Long idFuente) throws SpddExceptions;
	
	
	/**
	 * Metodo que permite modificar un BpProyInvAnualiza 
	 * @param peticion objeto de tipo BpProyInvAnualizaDTO con el id y los campos a modificar
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO modificarProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions;

	 /* Metodo que permite obtener un BpProyInvPolitica por medio de la llave de unicidad entre el identificador de la politica
	 * y el identificador del proyecto de inversion
	 * @param idPolPub Long que representa el identificador de la politica publica porl a que se va filtrar
	 * @param idProyInversion Long que represneta el identiricador del proyecto de inversion por el cual se va filtrar
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(Long idPolPub, Long idProyInversion) throws SpddExceptions;


	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO guardarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener un BpProyInvPolitica por medio de su identificador
	 * @param idProyPol Long que representa el identificador del BpProyInvPolitica
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorId(Long idProyPol) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyInvPolitica de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y politica publica
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvPolitica(Long idProyPolitica) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por medio de la unicidad entre el identificador de la linea de inversion y 
	 * el identificador del proyecto de inversion
	 * @param idLineaInversion Long que representa el identificador de la linea de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	public BpProyInvLineaDTO consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(Long idLineaInversion, Long idProyecto)  throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por el identificador
	 * @param idProyLinea Long que representa el identificador de la relacion entre proyecto de inversion y la linea de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	public BpProyInvLineaDTO consultarBpProyInvLineaPorId(Long idProyLinea) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO guardarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO modificarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore eliminar un BpProyInvLinea de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y linea de inversion
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvLinea(Long idProyLinea) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y MRIndicador filtrados por sus campos
	 * @param BpProyInvPmrDTO objeto de tipo BpProyInvPmrDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO bpProyInvPmrDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvPrm en la BD
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO guardarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPrmDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO modificarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPrmDTO) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvPmr(Long idProyPmr) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener  un BpProyInvPmr por medio de los campos de unicidad de idIndMr e idProyInversion
	 * @param idIndMr Long que representa el identificador de MrIndicador
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	public BpProyInvPmrDTO consultarBpProyInvPmrPorIdIndMrYIdProyInversion(Long idIndMr, Long idProyInversion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para obtener un BpProyInvPmr por medio del identificador
	 * @param idProyPmr Long que representa el identificador de BpProyInvPmr
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	public BpProyInvPmrDTO consultarBpProyInvPmrPorId(Long idProyPmr) throws SpddExceptions;
}
