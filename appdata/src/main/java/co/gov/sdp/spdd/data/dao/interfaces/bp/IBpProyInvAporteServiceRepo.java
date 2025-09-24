package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a BpProyInvAporte que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
public interface IBpProyInvAporteServiceRepo extends IOperacionesBasicasFacade<BpProyInvAporte, Long>, Serializable {
	
	/**
	 * Metodo que permite obtener un BpProyInvAporte por el indentificador del aporte y el identificador del proyecto
	 * @param idAporte Long que representa el identificador del aporte
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	BpProyInvAporte obtenerPorIdAporteYIdProyInversion(Long idAporte,Long idProyInversion);
	
	/**
	 * Metodo que permite obtener todos BpProyInvAporte por el identificador del proyecto paginados
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador objeto de tipo pageable que contiene la informacion para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public Page<BpProyInvAporte> obtenerTodosPorIdProyInversionPaginado(Long idProyInversion, Pageable paginador);

	/**
	 * Metodo que permite eliminar todos los Aportes Ciudadanos que hayan sido cargados desde
	 * un archivo plano y que esten relacionados con el proyecto de inversion pasado como parametro.
	 * Es decir los registros en la tabla intermedia BpProyInvAporte
	 * @param idProyecto Long que represente el identificado del proyecto al cual se le eliminaran todos lo
	 * apotes ciudadanos cargados desde un archivo que esten relacionados.
	 * @throws SpddExceptions
	 */
	public List<BpProyInvAporte> obtenerTodosCargadoArchivoPorIdProyInversion(Long idProyInversion);
	
	/**
	 * Metodo que permite eliminar de la BD todos los BpProyInvAporte pasados en la lista como parametro
	 * @param listaBpProyInvAporte lista de BpProyInvAporte que se desea elimimar
	 */
	public void eliminarTodos(List<BpProyInvAporte> listaBpProyInvAporte);
}
