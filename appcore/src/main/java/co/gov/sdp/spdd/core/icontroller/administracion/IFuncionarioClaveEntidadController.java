package co.gov.sdp.spdd.core.icontroller.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IFuncionarioClaveEntidadController {

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public GenericoDTO obtenerFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion);

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 * @throws JsonProcessingException 
	 */
	public FuncionarioClaveEntidadDTO crearFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions, JsonProcessingException;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public FuncionarioClaveEntidadDTO modificarFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion)
			throws SpddExceptions;

}
