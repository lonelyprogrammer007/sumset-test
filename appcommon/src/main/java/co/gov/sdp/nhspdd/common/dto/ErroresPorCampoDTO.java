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
public class ErroresPorCampoDTO {
	private  String nombreCampo;
	private  String valorCampo; 
	private  String tipoDato; 
    private  boolean tieneError;
    private  String descripcionError;
    private boolean esLlaveForanea;
    private boolean esValidacion;
}
