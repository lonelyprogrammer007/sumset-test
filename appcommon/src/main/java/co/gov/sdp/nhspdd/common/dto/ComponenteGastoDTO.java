package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the COMPONENTE_GASTO database table.
 *
 */
@Data
public class ComponenteGastoDTO extends RespuestaDTO {

    private Long idComponenteGasto;

    private Long codigoComponente;

    private Integer estado;

    private String nombreComponente;

    private Long idLsTipoProyecto;

    private String tipoProyecto;
    
    private String stringEstado;

}
