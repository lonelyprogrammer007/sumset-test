package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class PddIndicadorDTO extends RespuestaDTO {

	private Long idIndicador;

	private String nombre;

	private String tipo;

	private String lineaBaseDesc;

	private String fuente;

	private String yearLineaBase;

	private String informacionSoporte;

	private Long lineaBase;

	private Long magnitudLb;

	private Long magnitud;

	private String periodicidad;

	private Long fuenteExterna;

	private Long idArchivo;

	private Long idLsEstado;

	private String nombreEstado;

	private String fechaCreacion;

	private String stringEstado;

	private Long idMetaResultado;

	private Long idMetaProducto;

	private Long ponderacion;

	private Long idLsAgregacion;

	private String nombreAgregacion;

	public PddIndicadorDTO(Long idIndicador, String nombre, String tipo, String lineaBaseDesc, String fuente,
			String yearLineaBase, String informacionSoporte, Long lineaBase, Long magnitudLb, Long magnitud,
			String periodicidad, Long fuenteExterna, Long idArchivo, Long idLsEstado) {
		this.idIndicador = idIndicador;
		this.nombre = nombre;
		this.tipo = tipo;
		this.lineaBaseDesc = lineaBaseDesc;
		this.fuente = fuente;
		this.yearLineaBase = yearLineaBase;
		this.informacionSoporte = informacionSoporte;
		this.lineaBase = lineaBase;
		this.magnitudLb = magnitudLb;
		this.magnitud = magnitud;
		this.periodicidad = periodicidad;
		this.fuenteExterna = fuenteExterna;
		this.idArchivo = idArchivo;
		this.idLsEstado = idLsEstado;
	}

	public PddIndicadorDTO() {
	}
}
