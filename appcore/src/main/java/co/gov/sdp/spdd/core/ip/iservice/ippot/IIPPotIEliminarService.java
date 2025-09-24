package co.gov.sdp.spdd.core.ip.iservice.ippot;


import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPotIEliminarService {


	
	/**
	 * metodo que elimina una rama de un pot
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PotRamaDTO eliminarPotRama(Long id) throws SpddExceptions;
	
	/**
	 * metodo que elimina el nivel de un pot
	 * @param id Long que reprsenta el identificador del nivel que se quiere eliminar
	 * @return
	 * @throws SpddExceptions
	 */
	public PotNivelDTO eliminarNivelDTO(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para eliminar una PotObra
	 * @param idPotObra Long que representa el identificador del PotObra que se quiere eliminar
	 * @return un objeto de tipo PotObraDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public PotObraDTO eliminarPotObra(Long idPotObra) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un potInstrumento
	 * @param idPotInstrumento Long que representa el identificador del potInstrumento a eliminar
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO eliminarPotInstrumento(Long idPotInstrumento) throws SpddExceptions, JsonProcessingException;

	

}
