package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the ENTIDAD database table.
 *
 */
@Data
public class EntidadDTO extends RespuestaDTO {

    private String codigoEntidad;

    private Integer gestionProyectos;

    private Integer gestionUsuarios;

    private Long idLsClasificacion;
    
    private String stringClasificacion;

    private Long idLsAsociacion;
    
    private String stringAsociacion;

    private Long idBancoProyecto;
    
    private String stringBancoProyecto;
    
    private String sector;
    
    private String nombreEntidad;
    
    

}
