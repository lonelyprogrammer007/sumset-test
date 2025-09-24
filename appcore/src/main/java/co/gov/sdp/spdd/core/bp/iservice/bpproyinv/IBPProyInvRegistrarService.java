package co.gov.sdp.spdd.core.bp.iservice.bpproyinv;

import org.hibernate.HibernateException;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IBPProyInvRegistrarService {
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion basica de un proyecto de inversion.
	 * Este guardar corresponde a la informacion que existe en el TAB-Identificacion del proyecto.
	 * 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que
	 *                                 contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO registrarProyectoInversionIndentificacionProyecto(BpProyectoInversionDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para registrar la informacion de un BpProyInvAporte la cual indica la realacion entre un BpProyectoInversion y BpAporteCiudadano
	 * @param bpProyInvAporte Objeto de tipo BpProyInvAporteDTO que contiene la informacion para guardar
	 * @return un objeto de tipo BpProyInvAporteDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO registrarBpProyInvAporte(BpProyInvAporteDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para registrar la informacion de un BpAporteCiudadano
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la informacion para guardar
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la iformacion guardada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO registrarBPAporteCiudadano(BpAporteCiudadanoDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvIniciativa en BD
	 * @param bpProyInvIniciativaDTO objeto de tipo BpProyInvIniciativaDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvIniciativaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvIniciativaDTO registrarBpProyInvIniciativa(BpProyInvIniciativaDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvLocaliza en BD
	 * @param bpProyInvLocalizaYTerritorizacionDTO objeto de tipo BpProyInvLocalizaYTerritorizacionDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvLocalizaYTerritorizacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO registrarBPProyectoInvLocaliza(BpProyInvLocalizaYTerritorizacionDTO bpProyInvLocalizaYTerritorizacionDTO) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar la informacion de un BpProyInvEspecif en BD
	 * @param peticion objeto de tipo BpProyInvEspecifDTO con la informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO registrarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvMetaPlan en BD
	 * @param peticion objeto de tipo BpProyInvMetaPlanDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO registrarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvProducto en BD
	 * @param peticion objeto de tipo BpProyInvProductoDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO registrarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite guardar una BpProyInvActividad en BD
	 * @param peticion objeto de tipo BpProyInvActividadDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO registrarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite guardar una BpProyInvPoblacion en BD
	 * @param peticion objeto de tipo BpProyInvPoblacionDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoblacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO registrarBpProyInvPoblacionAsociadoaProyInv(BpProyInvPoblacionDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite guardar una BpProyInvEtnico en BD
	 * @param peticion objeto de tipo BpProyInvPoblacionEtnicoDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvEtnicoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO registrarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite guardar una BpProyInvFinancia en BD
	 * @param peticion objeto de tipo BpProyInvActividadDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO registrarBpProyInvPolitica(BpProyInvPoliticaDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvLinea
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO registrarBpProyInvLinea(BpProyInvLineaDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y el appcore para guardar un BpProyInvPrm en la BD
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO registrarBpProyInvPmr(BpProyInvPmrDTO peticion) throws SpddExceptions, JsonProcessingException;
}
