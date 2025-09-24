package co.gov.sdp.spdd.core.ip.iservice.ipplandistrital;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPPlanDistritalRegistrarService {

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para registrar
	 * un plan de desarrollo distrital
	 * 
	 * @param peticion objeto de tipo PddDTO:RespuestaDTO que contiene la
	 *                 informacion para registrar o guardar
	 * @return un objeto de tipo PddDTO:RespuestaDTO con la informacion registrada o
	 *         guardada
	 * @throws SpddExceptions
	 */
	public PddDTO registrarPdd(PddDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre app core y appdata para registra y
	 * modificar un nivel de plan de desarrollo distrital (PddNivel)
	 * 
	 * @param peticion peticion objeto de tipo PddDTO:RespuestaDTO que contiene la
	 *                 informacion para registrar o guardar
	 * @return un objeto de tipo PddNivelDTO:RespuestaDTO con la informacion
	 *         registrada o guardada
	 * @throws SpddExceptions
	 */
	public GenericoDTO registrarPddNivel(PddNivelDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un
	 * PddNivelAtributo
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a guardar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         guardo
	 */
	public PddNivelAtributoDTO registrarPddNivelAtributo(PddNivelAtributoDTO peticion)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para registrar
	 * un plan de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO:RespuestaDTO que contiene la
	 *                 informacion para registrar o guardar
	 * @return un objeto de tipo PdlDTO:RespuestaDTO con la informacion registrada o
	 *         guardada
	 * @throws SpddExceptions
	 */
	public PdlDTO registrarPdl(PdlDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite registrar un indicador meta resultado y en caso de que no
	 * exista crear el indicador
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO registrarIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para copiar la
	 * estructara del pdd al pdl vigente
	 * 
	 * @param idPdd Long que representa el identificador del pdd del cual se va a
	 *              copiar la estrutura de niveles
	 * @return un GenerticoDTO con la respuesta correspondiente
	 */
	public GenericoDTO copiarEstructuraPddToPdl(PdlDTO pdlDTO) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite obetner un PdlNivelAtributo por medio de la denominacion y
	 * del identificador del nivel al que esta relacionado
	 * 
	 * @param String     que representa la denominacion del nivel atributo
	 * @param idPdlNivel Long que representa el identificador del pdlNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PdlNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PdlNivelAtributoDTO registrarPdlNivelAtributo(PdlNivelAtributoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite registrar los meta productos asociados a un meta resultado
	 * 
	 * @param peticion el meta producto que se desea registrar
	 * @return retorna un mensaje de exito o fracaso
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO registrarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO registrarMetaProductoIndicador(PddIndicadorDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que registra una entidad meta producto
	 * 
	 * @param peticion entidad a registrar
	 * @return retorna un dto con el codigo y mensaje de respuesta
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO registrarMpEntidad(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que registrar un rango ponderacion
	 * @param peticion entidad a registrar
	 * @return retorna un dto con el codigo y mensaje de respuesta
	 * @throws SpddExceptions
	 * @throws IOException 
	 */
	public PddRangoPonderacionDTO registrarPddRangoPonderacion(Long idRango, MultipartFile logo, String rango, String descripcion, Long idPlanDesarrollo) throws SpddExceptions, IOException;
}
