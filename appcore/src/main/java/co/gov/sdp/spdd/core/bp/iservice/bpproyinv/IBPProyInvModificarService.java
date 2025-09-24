package co.gov.sdp.spdd.core.bp.iservice.bpproyinv;

import com.fasterxml.jackson.core.JsonProcessingException;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
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

public interface IBPProyInvModificarService {
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar la informacion de un BpAporteCiudadano
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la informacion para modificar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion modificada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(BpAporteCiudadanoDTO peticion) throws SpddExceptions, JsonProcessingException;


	/**
	 * Metodo que permite modificar la informacion basica de un proyecto de inversion. 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO modificarProyectoInversion(BpProyectoInversionDTO peticion) throws SpddExceptions;
	
	/**
<<<<<<< HEAD
	 * Metodo que permite modificar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions,JsonProcessingException;
	
	/**
	 * Metodo que permite modificar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO modificarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions,JsonProcessingException;
=======
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(BpProyInvPoliticaDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO modificarBpProyInvLinea(BpProyInvLineaDTO peticion) throws SpddExceptions, JsonProcessingException;

	
	/**
	 * metodo que permite modificar la informacion de las anualizaciones vinculadas a un ProyInvFinancia
	 * @param peticion objeto de tipo BpProyInvFinanciaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvFinanciaDTO con la informacion respectiva
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO modificarFuenteFinanciacion (BpProyInvFinanciaDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para modificar un BpProyInvPrm
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a modificar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO modificarBpProyInvPmr(BpProyInvPmrDTO peticion) throws SpddExceptions, JsonProcessingException;
>>>>>>> developer

}
