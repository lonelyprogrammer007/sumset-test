package co.gov.sdp.spdd.core.iservice.administracion;

import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Esta es la interfaz en la que se crean los metodos de consulta
 *
 * @author Johan Sebastian Giraldo
 *
 */
public interface IFuncionarioClaveEntidadConsultar {

	/**
     * Metodo que obtiene la lista de funcionarios por entidad
     *
     * @param codigoEntidad el codigo de la entidad
     * @return una lista con los funcionarios por entidad
     */
	public GenericoDTO obtenerFuncionarioClaveEntidad(String codigoEntidad) throws SpddExceptions;

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPaginado(FuncionarioClaveEntidadDTO peticion) throws SpddExceptions;
}
