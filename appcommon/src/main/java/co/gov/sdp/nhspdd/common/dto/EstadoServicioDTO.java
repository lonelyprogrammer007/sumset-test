package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the ESTADO_SERVICIO database table.
 *
 */
@Data
public class EstadoServicioDTO extends RespuestaDTO {

    private Long idEstadoServicio;

    private String estadoSolicitud;

    private String fechaRespuesta;

    private String fechaSolicitud;

    private String nombreServicio;

    private String tarea;
    
	public EstadoServicioDTO() {
		super();
	}

	public EstadoServicioDTO(Long idEstadoServicio, String estadoSolicitud, String fechaRespuesta, String fechaSolicitud, String nombreServicio, String tarea) {
		super();
		this.idEstadoServicio = idEstadoServicio;
		this.estadoSolicitud = estadoSolicitud;
		this.fechaRespuesta = fechaRespuesta;
		this.fechaSolicitud = fechaSolicitud;
		this.nombreServicio = nombreServicio;
		this.tarea = tarea;
	}


	
	
    
    

}
