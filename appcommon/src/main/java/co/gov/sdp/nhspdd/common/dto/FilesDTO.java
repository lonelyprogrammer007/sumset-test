/**
 * 
 */
package co.gov.sdp.nhspdd.common.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

/**
 * @author Sumset
 *
 */
@Data
public class FilesDTO {
	private  List<String> linea;
    private  int numLinea;
    private  boolean tieneError;
    private  String descripcionErrorFila;
    private  List<ErroresPorCampoDTO> erroresPorCampoDTO;
    private HashMap<String, String> mapCampos;
}
