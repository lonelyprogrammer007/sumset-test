package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * Clase DTO para manejar los mensajes de validacion
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
@Data
public class CampoValidacionDTO {

    private Integer codigoRespuesta;
    private String nombreEntidad;
    private String nombreCampo;
    private String mensaje;

    /**
     * Metodo constructor
     *
     * @param codigoRespuesta Codigo de respuesta de la validacion
     * @param mensaje Mensaje de validacion
     */
    public CampoValidacionDTO(Integer codigoRespuesta, String mensaje) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
    }
}
