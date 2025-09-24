package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BpProyInvActividadDTO
 * @author SEBASTIAN
 * @version 1.0 29/05/2020
 */
@Data
public class BpProyInvActividadDTO extends RespuestaDTO implements Serializable {

	private Long idActividad;

	private long consecutivo;

	private Long magnitud;

	private String descripcion;

	private String lineaBase;

	private String justificacion;

	private Long idLsProceso;

	private Long idLsEstado;

	private Long idLsUnidadMedida;

	private Long idProducto;

}
