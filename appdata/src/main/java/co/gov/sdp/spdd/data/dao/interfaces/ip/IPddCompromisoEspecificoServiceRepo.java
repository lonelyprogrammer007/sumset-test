package co.gov.sdp.spdd.data.dao.interfaces.ip;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * Interface de service repo que esta relacionado a un compromiso especifico de un plan de desarrollo que contiene la declaracion 
 * de metodos para ser implementados y por consiguiente ser utilizados en el facade
 * @author DANIEL
 * @version 1.0 10/03/2020
 */
public interface IPddCompromisoEspecificoServiceRepo extends IOperacionesBasicasFacade<PddCompromisoEspecifico, Long>, Serializable {
	
	/**
	 * Metodo que permite buscar un pdd compromiso especifico por el id del compromiso al cual esta relacionado y la descripcion
	 * @param idCompromiso Long que representa el identificador del comrpromiso al cual esta relacionado
	 * @param descripcion Long que representa la descripcon del pdd compromiso especifico
	 * @return un objeto de tipo PddCompromisoEspecifico con la informacion correspondiente o null en caso de no existir.
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecifico obtenerPorIdCompromisoYDescripcion(Long idCompromiso, String descripcion) throws SpddExceptions;

	/**
	 * Metodo que permite buscar los pddcompromisoEspecificos relacionados a un pddCompromiso especifico
	 * @param idCompromiso Long que representa el identificador del pddCompromiso por el cual se piensa buscar o filtrar
	 * @return una lista de PddCompromisoEspecifico con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public List<PddCompromisoEspecifico> obtenerPorIdCompromiso(Long idCompromiso) throws SpddExceptions;
	
	/**
	 * metodo que permite filtrar y ordenar los campos de un pddCompromisoDTO
	 * @param peticion pddCompromisoDTO con los campos a filtrar
	 * @return un objeto de tipo FiltroDTO
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarCompromisoPorCampo(PddCompromisoEspecificoDTO peticion,Long inicio,Integer limite) throws SpddExceptions;
}
