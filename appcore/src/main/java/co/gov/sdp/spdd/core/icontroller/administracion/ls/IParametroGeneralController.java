package co.gov.sdp.spdd.core.icontroller.administracion.ls;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que implementa los metodos del controlador de parametro general
 *
 * @author Bryan Munoz
 *
 */
public interface IParametroGeneralController {

    /**
     * Metodo post para editar un parametro
     *
     * @param peticion campos a editar
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     * @throws JsonProcessingException 
     */
    public ParametroGeneralDTO editarParametro(ParametroGeneralDTO peticion) throws SpddExceptions, JsonProcessingException;

    /**
     * Metodo get para traer todos los parametros
     *
     * @return una lista tipo genericoDTO con la lista de parametros, un codgio
     * y mensaje de respuesta
     */
    public GenericoDTO obtenerTodos(ParametroGeneralDTO peticion) ;
}
