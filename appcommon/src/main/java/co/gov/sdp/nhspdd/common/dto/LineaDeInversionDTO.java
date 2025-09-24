package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the LINEA_DE_INVERSION database table.
 *
 */
@Data
public class LineaDeInversionDTO extends RespuestaDTO {

    private Long idLineaInversion;
    private String concepto;
    private String establecido;
    private String descripcion;
    private Integer estado;
    private String fecha;
    private Long idLsSector;
    private String nombreSector;
    private String stringEstado;
    private String sectorMultiple;

}
