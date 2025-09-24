package co.gov.sdp.spdd.core.ip.iservice.ipformulacion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IIPFormulacionModificarService {

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un compromiso estrategico
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion
	 *         modificada
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un pdd compromiso especifico
	 * 
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO que contiene la
	 *                 informacion para modificar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion
	 *         modificada
	 * Metodo que permite la comunicacion entre el appdata y appcore para modificar un PddCompetenciaAsociada
	 * 
	 * @param peticion objeto de tipo PddCompetenciaAsociadaDTO que contiene la
	 *                         informacion para modificar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO modificarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion) throws SpddExceptions;

	
	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar un pdd compromiso especifico
	 * @param peticion objeto de tipo PddCompromisoEspecificoDTO que contiene la informacion para modificar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion modificada
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO modificarPddMeta(PddMetaDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite que permite la comunicacion entre appdata y appcore para modifica una PddMetaResultado
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la inforamcion para modificar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO modificarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions;


	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO modificarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un PddPrbValoracion
	 * @param pddPrbValoracion objeto de tipo PddPrbValoracionDTO que contiene la informacion para modificar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se modifico
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO modificarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions;
	
	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO modificarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO modificarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions;
	
	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para modificar un PddIndicador.
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion para modificar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO modificarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions, JsonProcessingException;
	
	/**
	 * Metodo que permite la comunicacion entre el appcore y appdata para modificar
	 * un pddCompromiso
	 * 
	 * @param peticion objeto de tipo PddCompromisoDTO que contiene la informacion
	 *                 para modificar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion registrada o
	 *         modificar.
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO modificarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions;
	
	public PddProblematicaDTO modificarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions;

}
