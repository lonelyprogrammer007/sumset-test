package co.gov.sdp.spdd.core.ip.iservice.ippot;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPotIConsultarService {

	/**
	 * metodo que permite la comunicación entre el appdata y el appcore para obtener
	 * la paginación de los objetos del pot
	 * 
	 * @param peticion objeto de tipo potDTO:RespuestaDTO
	 * @return un objeto de tipo GenericoDTO
	 * @throws SpddExceptions
	 */
	public GenericoDTO potObtenerPaginado(PotDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * metodo que permite la comunicación entre el appdata y el appcore para obtener
	 * las ramas de un pot
	 * 
	 * @param idPot Long que representa el identificador del pot
	 * @return un objeto de tipo GenericoDTO con la informacion consultada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosRamaPotPorIdPot(Long idPot) throws SpddExceptions;

	/**
	 * metodo que permite la comunicación entre el appData y el appcore para obtener
	 * las ramas de un pot ordenadas de forma desdecendiente por el numero de rama
	 * 
	 * @param idPot Long que representa el identificador del pot
	 * @return un objeto de tipo GenericoDTO con la información consultada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * todos los niveles de una rama pot
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appcore para obtener
	 * todos los niveles de una rama pot ordenados de forma desciendiente
	 * 
	 * @param idRamaPot Long que representa el identificador de la rama
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener
	 * todos los sub-niveles de un nivel de un pot
	 * 
	 * @param idNivelPot Long que represetna el identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicación entre el appData y el appCore para obtener
	 * todos los sub-niveles de un nivel de un pot de forma descediente
	 * 
	 * @param idNivelPot Long que represetna el identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivelDesc(Long idNivelPot) throws SpddExceptions;

	/*
	 * Metodo que permite la comunicacion entre appdata y appcore para obtener todos
	 * los PotObra correspondientes a un nivel pot paginado
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * 
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que pemite la comunicacion entre appdata y appcore para consultar
	 * todos los PotIntrumento filtrados y paginados
	 * 
	 * @param potInstrumentoDTO Objeto de tipo PotInstrumentoDTO con la informacion
	 *                          para filtar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotInstrumentoFiltrado(PotInstrumentoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotObraPorPot(PotObraDTO peticion) throws SpddExceptions;

}
