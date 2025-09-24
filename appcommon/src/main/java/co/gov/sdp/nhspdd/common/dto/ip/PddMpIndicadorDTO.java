package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddMpIndicadorDTO extends RespuestaDTO {

	private Long idMetaProdInd;
	private Long esPdd;
	private Long idIndicador;
	private Long idMetaProducto;
	private Long ponderacion;
	private String nombreIndicador;
	private Long idLsEstado;
	private Long idArchivo;
	private Long idLsAgregacion;
	private Long magnitud;
	private String fuente;
	private String periodicidad;
	private String nombreEstado;
	private String fuenteExterna;
	private Long longFuenteExterna;
	private String lineaBase;
	private Long longLineaBase;
	private Long magnitudLb;
	private String lineaBaseDesc;
	private String stringEsPdd;
	private String fechaCreacion;
	private String nombreAgregacion;
	private Long sumPond;
}
