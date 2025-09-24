package co.gov.sdp.spdd.core.iservice.administracion.ls;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos de registrar asociados con consecutivo
 *
 * @author Bryan Munoz
 *
 */
public interface IConsecutivoRegistrar {

    /**
     * Metodo registra un consecutivo en la bd
     *
     * @param peticion la cual contiene los campos para registrar en la bd
     * @return un objeto tipo dto con un codigo y mensaje de respuesta
     */
    public ConsecutivoDTO registrarConsecutivo(ConsecutivoDTO peticion) throws SpddExceptions;
}
