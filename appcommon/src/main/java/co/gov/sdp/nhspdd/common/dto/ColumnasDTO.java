/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@XmlRootElement
@Data
public class ColumnasDTO {
	private String nombreCampo;
    private Integer posicion;
    private Integer longitud;
    private String tipoDato;
    private Boolean esLlaveForanea;
    private Boolean esValidacion;
}
