package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the INFORMACION_PRESUPUESTAL database table.
 *
 */
@Data
public class InformacionPresupuestalDTO extends RespuestaDTO {

    private Long idInfoPresupuestal;

    private Long year;

    private Double apropiacionDefinitiva;

    private Double apropiacionReserva;

    private String codigoClasificacionPresupuestal;

    private String codigoDistrital;

    private Long codigoInterno;

    private Long codigoProyecto;

    private Double constitucionReserva;

    private Double ejecucionGiroReservas;

    private Double ejecucionVigencia;

    private Double girosVigencia;

    private Long mes;

    private String nombreProyecto;

    private Double recursosSuspendidos;

    private String origen;
    
    private Long codigoN3;

    private Long planDeDesarrollo;

    private Long idArchivo;

    private Long idPlanDesarrollo;
    
    private FilesDTO filesDTO;
    
    private int contadorErrores;
    
    private String codigoN1Aux;
    
    private Long idCodigoN1Aux;
    
    private String codigoN2Aux;
    
    private Long idCodigoN2Aux;
    
    private String codigoN3Aux;
    
    private Long idCodigoN3Aux;
    
    private String auxClasificacion;
}
