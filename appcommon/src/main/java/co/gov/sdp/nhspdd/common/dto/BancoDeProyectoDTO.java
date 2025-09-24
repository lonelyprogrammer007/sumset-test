package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the BANCO_DE_PROYECTOS database table.
 *
 */
@Data
public class BancoDeProyectoDTO extends RespuestaDTO {

    private Long idBancoProyecto;
    private String nombre;

    public BancoDeProyectoDTO() {
        super();
    }

}
