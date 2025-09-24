package co.gov.sdp.spdd.core.ip.iservice.ippot;

import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPotIModificarService {

	/**
	 * <<<<<<< HEAD Metodo que permite la comunicación entre el appcore y el data
	 * para modificar un plan de ordenamiento territorial
	 * 
	 * @param peticion objeto de potDto:RespuestaDTO que contiene la información
	 *                 para modificar
	 * @return un objeto de tipo potDto:RespuestaDTO con la información modificada
	 * @throws SpddExceptions
	 */
	public PotDTO modificarPot(PotDTO peticion) throws SpddExceptions;

	/*
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar una
	 * PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a modificar
	 * 
	 * @return un objeto de tipo PotObraDTO con la informacion modificada
	 * 
	 * @throws SpddExceptions
	 */
	public PotObraDTO modificarPotObra(PotObraDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar una
	 * potInstrumento
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a modificar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO modificarPotInstrumento(PotInstrumentoDTO peticion)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PotNivelDTO modificarPotNivel(PotNivelDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param idNivel
	 * @param cerrado
	 * @return
	 * @throws SpddExceptions
	 */
	public PotNivelDTO cerrarNIvel(Long idNivel, Long cerrado) throws SpddExceptions;

	/**
	 * 
	 * @param idRama
	 * @param cerrado
	 * @return
	 * @throws SpddExceptions
	 */
	public PotRamaDTO cerrarRama(Long idRama, Long cerrado) throws SpddExceptions;

}
