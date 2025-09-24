/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import java.util.List;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class CarguePlanoDTO extends RespuestaDTO {
	private Integer totalRegistros;
	private Integer totalRegistrosConError;
	private Integer totalRegistrosActualizados;
	private Integer totalRegistrosInsertados;
	private List<RegistroArchivoDTO> listaRegistroArchivoDTO;
}
