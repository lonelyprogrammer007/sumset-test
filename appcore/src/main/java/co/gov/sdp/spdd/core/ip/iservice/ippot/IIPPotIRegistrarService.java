package co.gov.sdp.spdd.core.ip.iservice.ippot;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPotIRegistrarService {

	public PotDTO registrarPot(PotDTO peticion) throws SpddExceptions;
	

	public PotRamaDTO registrarRamaPot(PotRamaDTO peticion) throws SpddExceptions;
	
	public PotNivelDTO registrarPotNivel(PotNivelDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para registrar una PotObra
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a guardar
	 * @return un objeto de tipo PotObraDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraDTO registrarPotObra(PotObraDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar una potInstrumento
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion a guardar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO registrarPotInstrumento(PotInstrumentoDTO peticion) throws SpddExceptions, JsonProcessingException;


}
