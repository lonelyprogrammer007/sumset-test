/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@XmlRootElement
@Data
public class TablasDTO {
	private String nombreTabla;
    private List<ColumnasDTO> columnas;
}
