package co.gov.sdp.spdd.core.ip.iservice.ipplandistrital;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPlanDistritalModificarService {

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un plan de desarrollo distrital
	 * 
	 * @param peticion objeto de tipo PddDTO:RespuestaDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PddDTO:RespuestaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PddDTO modificarPdd(PddDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un nivel de un plan de desarrollo distrital (PddNivel)
	 * 
	 * @param peticion objeto de tipo PddNivelDTO:RespuestaDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PddDTO:RespuestaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	// public PddNivelDTO modificarPddNivel(PddNivelDTO peticion) throws
	// SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un plan de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO:RespuestaDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PdlDTO:RespuestaDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PdlDTO modificarPdl(PdlDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un
	 * PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a modificar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         modifico
	 */
	public PddNivelAtributoDTO modificarPddNivelAtributo(PddNivelAtributoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO modificarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo modificar entidad meta producto
	 * @param peticion datos a modificar 
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO modificarMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite modificar una PddRangoPonderacion en BD
	 * @param peticion objeto de tipo PddRangoPonderacionDTO con la informacion a guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 * @throws IOException 
	 */
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(Long idRango, MultipartFile logo, String rango, String descripcion, Long idPlanDesarrollo) throws SpddExceptions, IOException;



}
