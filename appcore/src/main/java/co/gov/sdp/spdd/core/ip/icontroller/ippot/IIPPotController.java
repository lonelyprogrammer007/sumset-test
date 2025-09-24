package co.gov.sdp.spdd.core.ip.icontroller.ippot;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPotController {

	/**
	 * Metodo que permite obtener todos los planes de ordenamiento territorial
	 * 
	 * @param peticion objeto de tipo PotDTO:RespuestaDTO que contiene la
	 *                 informacion del paginado (pagina y tamanioPagina)
	 * @return un objetoDTO de tipo GenericoDTO con la informacion filtrada
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotPorFiltro(PotDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite almacenar un plan de ordenamiento territorial
	 * 
	 * @param peticion objeto de Tipo PotDTO que contiene la informacion del pot que
	 *                 se va almacenar
	 * @return un PotDTO con la información almacenada
	 */
	public PotDTO registrarPot(PotDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * Metodo que permite modificar almacenar un plan de ordenamiento territorial
	 * 
	 * @param peticion objeto de tipo PotDTO que contiene la información del pot que
	 *                 se va modificar
	 * @return un PotDTO con la información modificada
	 * @throws SpddExceptions
	 */
	public PotDTO modificarPot(PotDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todas las ramas de un plan de ordenamiento
	 * territorial
	 * 
	 * @param idPot Long identificación del pot
	 * @return un GenericoDTO con la información de la ramas del pot
	 */
	public GenericoDTO consultarTodosPotRamaPorIdPot(Long idPot) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todas las ramas de un pot ordenado de forma
	 * descendiente por el numero de rama
	 * 
	 * @param idPot Long identificación del pot
	 * @return un GenericoDTO con la información de las
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions;

	/**
	 * Metodo que permite almacenar una rama de un pot
	 * 
	 * @param peticion objeto de tipo PotRamaDTO con la información de la rama
	 * @return objeto de tipo PotRamaDTO con la información de la rama que se
	 *         almaceno
	 * @throws SpddExceptions
	 */
	public PotRamaDTO registrarPotRama(PotRamaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite eliminar una rama de un pot
	 * 
	 * @param idPotRama Long identificador de la rama
	 * @return objeto de tipo PotRamaDTO de la rama que se desea eliminar
	 * @throws SpddExceptions
	 */
	public PotRamaDTO eliminarPotRama(Long idPotRama) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los niveles de una rama de un pot
	 * 
	 * @param idRamaPot Long identificador de la rama
	 * @return objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los sub-niveles de un nivel de un pot
	 * 
	 * @param idNivelPot Long identificador de un pot
	 * @return objeto de tipo GenericoDTO con la información de los niveles
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions;

	/**
	 * metodo que permite guardar la información de un nivel de un pot
	 * 
	 * @param peticion Dto que contiene la información del nivel
	 * @return dto con la información a guardar
	 * @throws SpddExceptions
	 */
	public PotNivelDTO guardarNivelPotDTO(PotNivelDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite eliminar un nivel de un pot
	 * 
	 * @param idNivelPot Long que representa el identificador del nivel
	 * @return objeto de tipo PotNivelDTO del nivel que se desea eliminar
	 * @throws SpddExceptions
	 */
	public PotNivelDTO eliminarPotNivel(Long idNivelPot) throws SpddExceptions;

	/**
	 * Metodo que permite obtener todos los PotObra correspondientes a un nivel pot
	 * paginado
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite registrar una PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a guardar
	 * @return un objeto de tipo PotObraDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraDTO registrarPotObra(PotObraDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite modificar una PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a modificar
	 * @return un objeto de tipo PotObraDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PotObraDTO modificarPotObra(PotObraDTO peticion);

	/**
	 * Metodo que permite eliminar una PotObra
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a eliminar
	 * @return un objeto de tipo PotObraDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public PotObraDTO eliminarPotObra(Long idPotObra);

	/**
	 * Metodo que pemite consultar todos los PotIntrumento filtrados y paginados pot
	 * idPot
	 * 
	 * @param potInstrumentoDTO Objeto de tipo PotInstrumentoDTO con la informacion
	 *                          para filtar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotInstrumentoPorIdPotFiltrado(PotInstrumentoDTO peticion);

	/**
	 * Metodo que permite guardar una potInstrumento
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a guardar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO registrarPotInstrumento(PotInstrumentoDTO peticion);

	/**
	 * Metodo que permite modificar una potInstrumento
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a modificar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO modificarPotInstrumento(PotInstrumentoDTO peticion);

	/**
	 * Metodo que permite eliminar un potInstrumento
	 * 
	 * @param idPotInstrumento Long que representa el identificador del
	 *                         potInstrumento a eliminar
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO eliminarPotInstrumento(Long idPotInstrumento);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PotNivelDTO modificarPotNivel(PotNivelDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public GenericoDTO consultarTodosPotObraPorPot(PotObraDTO peticion);

	/**
	 * 
	 * @param idNivel
	 * @param cerrado
	 * @return
	 */
	public PotNivelDTO modificarEstadoNivel(Long idNivel, Long cerrado);

	/**
	 * 
	 * @param idRama
	 * @param cerrado
	 * @return
	 */
	public PotRamaDTO modificarEstadoRama(Long idRama, Long cerrado);
}
