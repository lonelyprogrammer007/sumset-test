package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.Equipamento;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IEquipamientoServiceRepo extends IOperacionesBasicasFacade<Equipamento, Long>, Serializable  {

	/**
	 * 
	 * @param equipamientoDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(EquipamientoDTO equipamientoDTO, Long inicio, Integer limite)
			throws SpddExceptions;
	
	/**
	 * 
	 * @param sector
	 * @param categoria
	 * @return
	 */
	public Equipamento validarSectorYCategoria(Long sector, Long categoria);

}
