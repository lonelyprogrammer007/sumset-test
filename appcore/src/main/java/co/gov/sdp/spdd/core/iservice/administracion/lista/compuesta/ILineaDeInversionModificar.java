package co.gov.sdp.spdd.core.iservice.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Interface que maneja todos los metodos de modificar asociados con linea de
 * inversion
 *
 * @author Bryan Munoz
 *
 */
public interface ILineaDeInversionModificar {

    /**
     * Metodo que modifica una linea de inversion de la bd
     *
     * @param id tipo Long del objeto que se desea modificar el estado
     * @return un objeto tipo dto con un codigo y mnesaje de respuesta
     */
    public LineaDeInversionDTO modificarLineaInversion(LineaDeInversionDTO lineaDeInversionDTO) throws SpddExceptions;

    /**
     * Metodo que modifica estado de linea de inversion en la bd
     *
     * @param lineaDeInversionDTO estado a modificar
     * @return un objeto tipo dto con un codigo y mnesaje de respuesta
     */
    public LineaDeInversionDTO modificarEstadoLineaInversion(Long id) throws SpddExceptions;
}
