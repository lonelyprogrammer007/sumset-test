package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;
import java.util.List;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.FuncionarioClaveEntidad;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

/**
 * 
 * @author Sumset
 *
 */
public interface IFuncionarioClaveEntidadServiceRepo
		extends IOperacionesBasicasFacade<FuncionarioClaveEntidad, Long>, Serializable {

	/**
	 * 
	 * @param funcionarioClaveEntidadDTO
	 * @param inicio
	 * @param limite
	 * @return
	 * @throws SpddExceptions
	 */
	public FiltroDTO filtrarPorCampo(FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO, Long inicio, Integer limite)
			throws SpddExceptions;

	/**
	 * 
	 * @param codigoEntidad
	 * @return
	 */
	public List<FuncionarioClaveEntidad> obtenerFuncionarioPorEntidad(String codigoEntidad);

	/**
	 * 
	 * @param codigoEntidad
	 * @param idLsFuncion
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidad validarFuncionarioPorIdLsFuncionYEntidad(String codigoEntidad, Long idLsFuncion)
			throws SpddExceptions;
}
