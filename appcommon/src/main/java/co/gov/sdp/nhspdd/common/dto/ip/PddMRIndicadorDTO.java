package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddMRIndicadorDTO extends RespuestaDTO {

	private Long idIndProxy;

	private Long idIndicador;

	private Long idMetaResultado;

	private Long indicadorMetaResultado;

	private String denominacion;

	private Long magnitud;

	private String periodicidad;

	private String fuente;

	private Long fuenteExterna;

	private String estado;

	private String lineaBase;

	private Long magnitudLb;

	private Long idArchivo;

	private String lineaBaseDesc;

	private String fechaCreacion;
}
