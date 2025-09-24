package co.gov.sdp.spdd.core.iservice.administracion;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de registro para el
 * modulo de administracion
 *
 * @author Johan Sebastian Giraldo
 *
 */
public interface IFuncionarioClaveEntidadRegistrar {

	/**
	 * Metodo para crear funcionarios clave por entidad
	 *
	 * @param peticion Objeto DTO con los datos para crear el funcionarios clave
	 * @return Objeto DTO informando la creacion exitosa del funcionarios clave,
	 *         sino un objeto vacio con el mensaje de error correspondiente
	 * @throws JsonProcessingException 
	 */
	public FuncionarioClaveEntidadDTO crearFuncionarioClaveEntidad(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions, JsonProcessingException;

}
