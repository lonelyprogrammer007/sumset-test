package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the CONFIGURACION_CARGUE_ARCHIVO database table.
 *
 */
@Data
public class ConfiguracionCargueArchivoDTO extends RespuestaDTO {

	private Integer idConfigCargue;

	private String configuracion;

	private Long idLsTema;

	private Long idLsModulo;
	
	private String nombreTema;

}	
