package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.util.List;

import org.springframework.data.repository.query.Param;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PotNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IPotNivelServiceRepo extends IOperacionesBasicasFacade<PotNivel, Long>{
	
	/**
	 * Metodo que permite obtener todos los niveles de una rama pot
	 * @param idRamaPot Long identificador de la rama
	 * @return Lista de niveles de la rama de un pot
	 * @throws SpddExceptions
	 */
	public List<PotNivel> obtenerPotNivelPorIdRamaPot(Long idRamaPot) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener todos los niveles de una rama pot 
	 * ordenados de forma descendiente por el numero del nivel
	 * @param idRamaPot Long identificador de la rama pot
	 * @return lista de niveles de la rama de un pot
	 * @throws SpddExceptions
	 */
	public List<PotNivel> obtenerPotNivelPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener los sub niveles de un nivel de un nivel pot
	 * @param idNivelPot Long identificador del nivel padre
	 * @return lista de niveles de un nivel padre de un pot
	 * @throws SpddExceptions
	 */
	public List<PotNivel> obtenerPotNivelPorIdNivelPadre(Long idNivelPot) throws SpddExceptions;
	
	/**
	 * Metodo que permite obtener los sub niveles de un nivel de un nivel pot ordenados
	 * de forma descendiente por el numero de nive
	 * @param idNivelPot Long identificador del nivel padre
	 * @return lista de niveles de un nivel padre de un pot
	 * @throws SpddExceptions
	 */
	public List<PotNivel> obtenerPotNivelPorIdNivelPadreDesc(Long idNivelPot) throws SpddExceptions;
}
