package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class RolesDTO extends RespuestaDTO {

    private String codigoRol;
    private String nombre;
    private Character estado;
    private String entidad;

    public RolesDTO() {
        super();
    }

}
