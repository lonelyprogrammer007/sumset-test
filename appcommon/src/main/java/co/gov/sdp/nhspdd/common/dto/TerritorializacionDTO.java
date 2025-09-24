package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the TERRITORIALIZACION database table.
 *
 */
@Data
public class TerritorializacionDTO extends RespuestaDTO {

	private Long idTerritorializacion;

	private Integer estado;

	private Long idLsLocalidad;

	private Long idLsUpz;

	private Long idLsVereda;

	private Long idLsBarrio;

	private Long idLsUpr;

	private String nombreLocalidad;

	private String nombreUpz;

	private String nombreVereda;

	private String nombreBarrio;

	private String nombreUpr;

	private String stringEstado;

	public TerritorializacionDTO() {

	}

	public TerritorializacionDTO(Long idLsLocalidad, Long idLsBarrio, Long idLsUpz, Long idLsUpr, Long idLsVereda) {
		this.idLsLocalidad = idLsLocalidad;
		this.idLsBarrio = idLsBarrio;
		this.idLsUpz = idLsUpz;
		this.idLsUpr = idLsUpr;
		this.idLsVereda = idLsVereda;
	}

}
