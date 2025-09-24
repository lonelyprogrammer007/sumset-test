package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que persiste la tabla HIS_V_PDD_Compromiso de la BD
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Data
public class HisVPddCompromisoDTO extends RespuestaDTO {

	private Long idCompromiso;

	private Long idEstrategicoLc;

	private Long idHisPlanDesarrollo;

}