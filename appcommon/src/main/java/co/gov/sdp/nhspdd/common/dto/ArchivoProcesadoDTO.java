	package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * The persistent class for the ARCHIVO_PROCESADO database table.
 *
 */
@Data
public class ArchivoProcesadoDTO extends RespuestaDTO {
	    private Long idArchivo;
	    private String estado;
	    private String detalle;
	    private String fechaCargue;
	    private String codigoUsuario;
	    private Long idConfigCargue;
	    private String nombreModulo;
	    private String nombreTema;
	    private String nombreArchivo;
}
