package co.gov.sdp.spdd.core.ip.iservice.ipformulacion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Iterface que contiene la declaracion de metodos de registrar del servicio de
 * IP
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
public interface IIPFormulacionRegistrarService {

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para registrar
	 * un compromiso estrategico
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO que contiene la
	 *                 informacion para registrar o guardar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion
	 *         registrada o guardada
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO registrarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre el appdata y appcore para registrar un
	 * Competencia Asociada
	 * 
	 * @param peticion objeto de tipo PddCompetenciaAsociadaDTO que contiene la
	 *                         informacion para guardar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO registrarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion) throws SpddExceptions;


	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para registrar
	 * un pdd compromiso especifico
	 * 
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO que contiene la
	 *                 informacion para registrar o guardar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion
	 *         registrada o guardada
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO registrarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para registrar
	 * un pddCompromiso
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para registrar o guardar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion registrada o
	 *         guardada.
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO registrarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite que permite la comunicacion entre appdata y appcore para guarda una PddMetaResultado
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la inforamcion para guardar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO registrarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;

	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para registrar un PddPrbValoracion
	 * @param peticion objeto de tipo PddPrbValoracionDTO que contiene la informacion para guardar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO registrarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions;
	
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException
	 */
	public PddMetaDTO registrarMetaPorCompromiso(PddMetaDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO registrarObraConcretaPorMeta(PddObraConcretaDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO registrarProblematica(PddProblematicaDTO peticion) throws SpddExceptions;
	
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO registrarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO registrarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions, JsonProcessingException;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para guardar un PddIndicador.
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion para guardar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO registrarPddIndicadorYPddPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions, JsonProcessingException;
}
