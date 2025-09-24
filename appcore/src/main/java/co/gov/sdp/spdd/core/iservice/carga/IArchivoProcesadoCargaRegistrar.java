package co.gov.sdp.spdd.core.iservice.carga;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.dao.DataAccessException;

import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interfaz para implementar todos las funcionalidades de modificicacion para el
 * modulo de presupuesto
 *
 * @author Raul Londono Murillo
 *
 */
public interface IArchivoProcesadoCargaRegistrar {

	/**
	 * 
	 * @param archivo
	 * @param peticion
	 * @return
	 * @throws IOException
	 * @throws SpddExceptions
	 * @throws DataAccessException
	 */
	public CarguePlanoDTO cargarArchivoProcesado(InputStream archivo, CargaAchivoDTO peticion)
			throws IOException, SpddExceptions, DataAccessException;
}
