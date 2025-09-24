package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos registrar asociados con
 * potProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
public interface IPotProyectoInstrumentoRegistrar {

    /**
     * Este metodo registra los potProyectoInstrumento en la bd
     *
     * @param potProyectoInstrumentoDTO objeto que trae los campos para realizar
     * el registro
     * @return un objeto dto con un codigo y un mensaje de respuesta
     * @throws JsonProcessingException 
     */
    public PotProyectoInstrumentoDTO registrarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws SpddExceptions, JsonProcessingException;
}
