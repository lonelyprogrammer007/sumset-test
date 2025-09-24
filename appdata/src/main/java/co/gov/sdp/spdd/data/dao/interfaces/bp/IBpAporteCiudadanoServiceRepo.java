package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpAporteCiudadano que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 16/04/2020
 */
public interface IBpAporteCiudadanoServiceRepo extends IOperacionesBasicasFacade<BpAporteCiudadano, Long>, Serializable {
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano pero que aun no tienen una
	 * relacion con el proyecto BpProyectoInversion indicado en el parametro 
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public List<BpAporteCiudadano> obtenerTodosSinRelacionConProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite obtener un BpAporteCiudadano mediante la combinacion de campos pasados como parametros
	 * @param accion String que representa la accion por la cual se va a buscar
	 * @param fuente String que representa la fuente por la cual se va a buscar
	 * @param idPddNivelAtributo Long que representa el identificador del nivels atributo por el cual se va a buscar
	 * @return Un objeto BpAporteCiudadano con la informacion correspondientes.
	 */
	public BpAporteCiudadano obtenrePorAccionYFuenteYIdNivelPdd(String accion, String fuente, Long idPddNivelAtributo);
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivos();
	
	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la tabla BpAporteCiudadano correspondiente
	 * a los aportes cargados desde un archivo plano y que estan relacionados con el proyecto de inversion pasado como parametro
	 * @param idProyecto Long que representa el identificador del BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes
	 */
	public List<BpAporteCiudadano> obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto);
	
	/**
	 * Metodo que permite ordenar los aportes ciudadanos ordenados ascendentemente por el consecutivo
	 * @return Una lista de BpAporteCiudadano con todos los registros correspondientes 
	 */
	public List<BpAporteCiudadano> obtenerTodosOrdenadorPorConsecutivoASC();

	
}
