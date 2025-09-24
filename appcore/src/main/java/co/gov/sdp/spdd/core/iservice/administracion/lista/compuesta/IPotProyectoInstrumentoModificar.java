package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos modificar asociadoas a un
 * potProyectoInstrumento
 *
 * @author Bryan Munoz
 *
 */
public interface IPotProyectoInstrumentoModificar {

    /**
     * Metodo que permite modificar un potProyectoInstrumento
     *
     * @param potProyectoInstrumentoDTO objeto con los campos a modificar
     * @return un objeto dto con un codigo y mensaje de respuesta
     */
    public PotProyectoInstrumentoDTO modificarProyectoInstrumento(PotProyectoInstrumentoDTO potProyectoInstrumentoDTO) throws SpddExceptions;

    
}
