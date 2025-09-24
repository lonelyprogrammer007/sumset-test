package co.gov.sdp.spdd.core.icontroller.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;

/**
 * Interface que tiene todos los metodos del controlador PotProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
public interface IPotProyectoInstrumentoController {

    /**
     * Metodo que obtiene todos los potProyectoInstrumento que hay en la bd
     *
     * @return una lista con todos los potProyectoInstrumento
     */
    public GenericoDTO obtenerTodos(PotProyectoInstrumentoDTO peticion);

    /**
     * Metodo que registra un proyecto instrumento en la bd
     *
     * @param potProyectoInstrumentoDTO objeto con campos a registrar en la bd
     * @return un objeto dto con un codigo y mensaje de respuesta
     * @throws JsonProcessingException 
     */
    public PotProyectoInstrumentoDTO registrarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws JsonProcessingException;

    /**
     * Metodo que modifica un proyecto instrumento de la bd
     *
     * @param potProyectoInstrumentoDTO objeto con campos a modificar de la bd
     * @return un objeto dto con un codigo y mensaje de respuesta
     */
    public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO);

    /**
     * Metodo que cambia un estado de un proyecto instrumento de la bd
     *
     * @param id tipo Long del objeto que se desea activar o desactivar
     * @return un objeto dto con un codigo y mensaje de respuesta
     */
   

    /**
     * 
     * @return
     */
	public GenericoDTO obtenerPotInstrumento();

	/**
	 * 
	 * @return
	 */
	public GenericoDTO obtenerPotObra();

}
