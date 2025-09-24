package co.gov.sdp.spdd.core.icontroller.carga;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionCargueArchivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface para definir los endpoints de las informacion presupuestal para el
 * modulo de administracion
 *
 * @author Raul Londono Murillo
 *
 */
public interface IArchivoProcesadoCargaController {

	/**
	 * Metodo para cargar un archivo procesado
	 *
	 * @param archivo Archivo a cargar
	 * @param modulo  modulo seleccionado para carga
	 * @param tema    tema seleccionado para carga
	 * @param detalle detalle del archivo a cargar
	 * @param usuario usuario que cargo el archivo
	 * @return Objeto DTO que contiene la informacion del archivo procesado creado
	 *         si la peticion se realiza satisfactoriamente, sino, se enviara el
	 *         objeto vacio con el respectivo mensaje de error
	 */
	public CarguePlanoDTO crearArchivoProcesado(MultipartFile archivo, Long tema, String usuario, Long modulo)
			throws SpddExceptions;

	/**
	 * Metodo que trae la lista completa de archivos procesados que el usuario puede
	 * ver
	 *
	 * @return Objeto DTO generico que contiene la lista de archivos procesados que
	 *         el usuario puede ver
	 */
	public GenericoDTO obtenerArchivoProcesadoTodos(ArchivoProcesadoDTO peticion)
			throws SpddExceptions, DataAccessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public ConfiguracionCargueArchivoDTO consultarConfigCargueArchivoProcesado(Long id)
			throws SpddExceptions, DataAccessException;
}
