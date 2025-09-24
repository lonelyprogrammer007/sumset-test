package co.gov.sdp.nhspdd.common.dto;

import java.util.List;

import lombok.Data;
/**
 * clase dto que permite recibir la respuesta del sistema de seguridad
 * 
 * @author Bryan Munoz
 *
 */
@Data
public class RespuestaApiDTO<T>  {
    private Boolean exito;
    private String mensaje;
    private int codigo;
    private List<T> objetos;
}
