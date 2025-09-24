package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the EQUIPAMENTO database table.
 *
 */
@Data
public class EquipamientoDTO extends RespuestaDTO {

    private Long idEquipamento;

    private String descripcion;

    private Integer estado;

    private String nombre;

    private Long idLsSector;

    private Long idLsCategoria;

    private String nombreSector;

    private String nombreCategoria;
    
    private String stringEstado;

}
