package co.gov.sdp.spdd.data.dao.interfaces.bp;

import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCiudadana;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
public interface IBpIniciativaCiudadanaServiceRepo extends IOperacionesBasicasFacade<BpIniciativaCiudadana, Long> {

	public FiltroDTO obtenerTodosIniciativaPorFiltro(BpIniciativaCiudadanaDTO peticion,Long inicio, Integer limite) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todas las BpIniciativasCiudadanas que estan relacionadas con el proyecto de inversion especificado
	 * @param idProyecto Long que representa el identificador del proyecto por el cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public List<BpIniciativaCiudadana> obtenerTodosRelacionadasConProyectoInversion(Long idProyecto);
}
