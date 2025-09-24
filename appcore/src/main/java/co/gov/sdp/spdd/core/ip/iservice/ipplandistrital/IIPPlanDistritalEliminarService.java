package co.gov.sdp.spdd.core.ip.iservice.ipplandistrital;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que contiene los metodos eliminar del Plan distrital de IP
 * 
 * @author Bryan Muñoz
 *
 */
public interface IIPPlanDistritalEliminarService {

	/**
	 * Metodo que elimina los meta resultados por id
	 * 
	 * @param idMetaResultado id que permite eliminar el meta resultado
	 * @return un dto con una respuesta de exito o fracaso
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO eliminarMetaResultado(Long idMetaResultado) throws SpddExceptions;

	/**
	 * Metodo que elimina un indicador meta resultado
	 * 
	 * @param indMetaResultado
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO eliminarIndicadorMetaResultado(Long indMetaResultado) throws SpddExceptions;

	/**
	 * 
	 * @param idMetaProducto
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO eliminarMetaProducto(Long idMetaProducto) throws SpddExceptions;

	/**
	 * 
	 * @param idMpIndicador
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO eliminarMpIndicador(Long idMpIndicador) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un PddNivelAtriguto
	 * @param idAtributo Long que representa el identificador PddNivelAtriguto que se quiere eliminar
	 * @throws SpddExceptions
	 */
	public PddNivelAtributoDTO eliminarPddNivelAtributo(Long idAtributo) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que elimina la entidad meta producto
	 * @param idMpEntidad id de la entidad a eliminar
	 * @return retorna un dto con una respuesta exitosa o de fracaso
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO eliminarMpEntidad(Long idMpEntidad) throws SpddExceptions;


	/**
	 * Metodo que elimina la entidad Rango ponderacion
	 * @param idRango id de la entidad a eliminar
	 * @return retorna un dto con una respuesta exitosa o de fracaso
	 * @throws SpddExceptions
	 */
	public PddRangoPonderacionDTO eliminarPddRangoPonderacion(Long idRango) throws SpddExceptions;

}
