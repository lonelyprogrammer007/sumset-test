package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * Clase dto de la entidad PlanDeDesarrolloDistrital
 *
 * @author Bryan Munoz
 *
 */
@Data
public class PddDTO extends RespuestaDTO {

    private Long idPlanDesarrollo;

    private String nombrePlan;

    private String siglaPlan;

    private String actoAdministrativo;

    private String fechaActo;

    private String yearInicio;

    private String nombreAlcalde;

    private String correoAlcalde;

    private String programaGobierno;

    private String yearFinal;

    private Long idLsAdoptado;
    
    private String stringAdoptado;

    private Long idLsAvanceSgr;
    
    private String stringAvanceSgr;

    private Long idLsEstadoPlan;
    
    private String estadoPlan;

    private Long idLsNiveles;
    
    private String stringNiveles;
    
    private String version;
    
    private String vigencia;

}
