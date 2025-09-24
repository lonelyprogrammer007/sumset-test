/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class ConfigFileDTO {
	private  String fieldName;
    private  int position;
    private  int longitude;
    private  String dataType;
}
