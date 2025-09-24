package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * @author SumSet 2019
 *
 */
public interface ITerritorializacionServiceRepo
		extends IOperacionesBasicasFacade<Territorializacion, Long>, Serializable {

	/**
	 * 
	 * @param territorializacionDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(TerritorializacionDTO territorializacionDTO, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param idLsBarrio
	 * @param idLsUpz
	 * @param idLsLocalidad
	 * @return
	 * @throws SpddExceptions
	 */
	public Territorializacion buscarPorLsBarrioYLsUpzYLsLocalidad(Long idLsBarrio, Long idLsUpz, Long idLsLocalidad)
			throws SpddExceptions;

	/**
	 * 
	 * @param idLsVereda
	 * @param idLsUpr
	 * @return
	 * @throws SpddExceptions
	 */
	public Territorializacion buscarPorLsVeredaYLsUpr(Long idLsVereda, Long idLsUpr, Long idLsLocalidad)
			throws SpddExceptions;

	/**
	 * @param localidad
	 * @return
	 * @throws SpddExceptions
	 */
	public List<Territorializacion> buscarPorLocalidad(String localidad) throws SpddExceptions;
	
	public List<Territorializacion> obtenerPorIdLocalidad(Long idLocalidad) throws SpddExceptions;
	
	

}
