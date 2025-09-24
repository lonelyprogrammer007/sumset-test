package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja los metodos de registrar relaciones con linea de
 * inversion
 *
 * @author Bryan Munoz
 *
 */
public interface ILineaDeInversionRegistrar {

    /**
     * Metodo que registra una linea de inversion en la bd
     *
     * @param lineaDeInversionDTO objeto con los campos para realizar el
     * registro
     * @return un dto con un codigo y mensaje de respuesta
     */
    public LineaDeInversionDTO registrarLineaDeInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions, JsonProcessingException;
}
