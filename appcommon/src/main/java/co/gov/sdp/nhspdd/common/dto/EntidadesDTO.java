package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class EntidadesDTO extends RespuestaDTO {

    private String codigoEntidad;
    private String nombre;
    private Character estado;
    private String descripcion;
    private String codigoSector;

}
