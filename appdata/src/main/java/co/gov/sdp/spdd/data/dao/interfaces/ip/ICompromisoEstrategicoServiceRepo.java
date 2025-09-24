package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a compromiso estrategico que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 25/02/2020
 */
public interface ICompromisoEstrategicoServiceRepo extends IOperacionesBasicasFacade<CompromisoEstrategico, Long>, Serializable {
	
	/**
	 * Metodo que permite filtrar por algun campo en especifico de CompromisoEstrategico
	 * @param CompromisoEstrategicoDTO objeto DTO de tipo CompromisoEstrategicoDTO que contiene la informacion para filtrar.
	 * @param inicio long que indica la pagina
	 * @param limite Integer que indica la cantidad de elementos a filtrar
	 * @return un objeto de tipo FiltroDTO con la informacion filtrada
	 * @throws SpddExceptions Excepcion propia que puede ser lanzada
	 */
	public FiltroDTO filtrarPorCampo(CompromisoEstrategicoDTO compromisoEstrategicoDTO, Long inicio, Integer limite) throws SpddExceptions;
	
	/**
	 * Metodo que permite buscar un compromiso estratetico por los identifiadores de tematica y compromisoEstrategico
	 * @param idTematica Long que representa el identificador de tematica
	 * @param idCompromisoEstrategico Long que representa el identificador de compromiso estratetico
	 * @return un objeto de tipo CompromisoEstrategico con la informacion correspondiente o null en caso de no existir.
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategico buscarPorIdTematicaYIdCompromisoEstrategico(Long idTematica, Long idCompromisoEstrategico) throws SpddExceptions;
}
