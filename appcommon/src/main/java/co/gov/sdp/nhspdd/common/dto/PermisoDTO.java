package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

@Data
public class PermisoDTO extends RespuestaDTO {

    private String codigoPermiso;
    private String nombre;
    private Character estado;
    private Integer nivel;
    private String padre;
    private String url;
    private String metodo;
    private Character tipo;
    private String codigoAplicacion;

    public PermisoDTO() {
        super();
    }

}
