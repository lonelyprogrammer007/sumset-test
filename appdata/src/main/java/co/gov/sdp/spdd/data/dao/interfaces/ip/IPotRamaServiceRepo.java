package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotRama;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPotRamaServiceRepo extends IOperacionesBasicasFacade<PotRama, Long>{

	/**
	 * Metodo que permite obtener las ramas de un pot por el identificador del pot
	 * @param idPot
	 * @return
	 * @throws SpddExceptions
	 */
	public List<PotRama> obtenerRamaPotPorIdPot(Long idPot) throws SpddExceptions;
	
	/**
	 * metodo que permite obtener las ramas ordenadas por el numero de rama de un pot
	 * por el identificador del pot
	 * @param idPot Long identificador del pot
	 * @return lista de ramas del pot ordenadas de forma descendiente por el numero de rama 
	 * @throws SpddExceptions
	 */
	public List<PotRama> obtenerPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions;
}
