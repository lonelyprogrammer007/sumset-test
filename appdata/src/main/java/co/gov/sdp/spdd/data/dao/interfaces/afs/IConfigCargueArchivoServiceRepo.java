package co.gov.sdp.spdd.data.dao.interfaces.afs;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.afs.ConfigCargueArchivo;
import co.gov.sdp.spdd.data.operacionesbasicas.interfaces.IOperacionesBasicasFacade;

public interface IConfigCargueArchivoServiceRepo
		extends IOperacionesBasicasFacade<ConfigCargueArchivo, Long>, Serializable {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ConfigCargueArchivo obtenerConfigCarguePorArchivoProcesado(Long id);

	/**
	 * 
	 * @param idLsModulo
	 * @param idLsTema
	 * @return
	 */
	public ConfigCargueArchivo buscarPorIdLsModuloYIdLsTema(Long idLsModulo, Long idLsTema);
	
	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int guardarSql(String strQuery) throws SpddExceptions;
	
	/**
	 * 
	 * @param strQuery
	 * @return
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public int buscarSql(String strQuery) throws SpddExceptions; 
}
