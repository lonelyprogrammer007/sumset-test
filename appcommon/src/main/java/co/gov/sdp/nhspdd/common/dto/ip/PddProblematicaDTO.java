/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * @author Jose Alvaro
 *
 */
@Data
public class PddProblematicaDTO extends RespuestaDTO {
	private Long idProblematica;
    private String problematica;
    private String descripcion;
    private String causas;
    private String consecuencias;
    private String objetivo;
    private Long idLsLocalizacion;
    private String localizacion;
    private Long idLsSublocalizacion;
    private String subLocalizacion;
    private Long idLzUpzUpr;
    private String upzUpr;
    private Long idCompromiso;
}
