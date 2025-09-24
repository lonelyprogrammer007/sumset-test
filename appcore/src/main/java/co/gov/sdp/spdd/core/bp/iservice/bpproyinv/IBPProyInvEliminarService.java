package co.gov.sdp.spdd.core.bp.iservice.bpproyinv;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
<<<<<<< HEAD
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
=======
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
>>>>>>> developer
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;


public interface IBPProyInvEliminarService {
	
	/**
	 * Metod que permite la comunicacion entre el appdata y el appcore para eliminar un 
	 * BpProyInvAporte por medio de su identificador y tambien el BpAporteCiudadano que esta
	 * relacionado.
	 * @param idProyAporte Long que representa el identificador del BpProyInvAporte a eliminar
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO eliminarBpProyInvAporte(Long idProyAporte) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyectoInvresionDTO
	 * por medio del identificador
	 * @param idProyectoInversion Long que representa el indentificador del BpProyectoInversion
	 * @return un objeto de BpProyectoInversionDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO eliminarBpProyectoInversion(Long idProyectoInversion) throws SpddExceptions;
	
	/**
<<<<<<< HEAD
	 * Metodo que permite eliminar un BpProyInvPoblacion
	 * @param idProyectoInversion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite eliminar un BpProyInvEtnico
	 * @param idEtnico
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	
=======
	 * Metodo que permite eliminar el ProyInvFinancia por ID y sus ProyInvAnualiza asociados
	 * @param idFuente id de la fuente a eliminar
	 * @return BpProyInvFinanciaDTO con la informacion eliminada
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO eliminarProyInvFinanciaPorId(Long idFuente) throws SpddExceptions;
>>>>>>> developer

	 /** Metodo que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyInvPolitica de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y politica publica
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO eliminarBpProyInvPolitica(Long idProyPolitica) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore eliminar un BpProyInvLinea de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y linea de inversion
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO eliminarBpProyInvLinea(Long idProyLinea) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para eliminar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a eliminar
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO eliminarBpProyInvPmr(Long idProyPmr) throws SpddExceptions, JsonProcessingException;
}
