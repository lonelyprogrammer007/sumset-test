package co.gov.sdp.spdd.core.ip.iservice.ipformulacion;

import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * 
 * @author Bryan Munoz
 *
 */
public interface IIPFormulacionEliminarService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO eliminarPddMeta(Long id) throws SpddExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO eliminarPddObraConcreta(Long id) throws SpddExceptions;

	/**
	 * Metodo que permite la comunicacion entre appdata y appcore para eliminar un
	 * pddCompromisoEspecifico por medio de su identificador
	 * 
	 * @param idPddCompromisoEspecifico Long que representa el identificador del
	 *                                  pddCompromisoEspecifico que se desea
	 *                                  eliminar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion
	 *         correspondiente de exito o fallo en la eliminacion
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico)
			throws SpddExceptions;

	/**
	 * 
	 * @param idPoblacion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO eliminarPrbPoblacion(Long idPoblacion) throws SpddExceptions;

	/**
	 * 
	 * @param idProbInd
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO eliminarPrbIndicador(Long idProbInd) throws SpddExceptions;

}
