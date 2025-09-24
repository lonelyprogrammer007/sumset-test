package co.gov.sdp.nhspdd.common.dto.ip;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * The persistent class for the SPDD_PDL database table.
 *
 */
@Data
public class PdlDTO extends RespuestaDTO {

	private Long idPlanLocal;

	private String nombrePlan;

	private String urlPlan;

	private String actoAdministrativo;

	private String fechaActo;

	private String yearInicio;

	private String nombreAlcaldeLocal;

	private String correoAlcaldeLocal;

	private String yearFinal;

	private Long idLsAdoptado;
	
	private String estadoPlan;

	private Long idLsEstadoPlan;

	private Long idPlanDesarrollo;
	
	private String codigoEntidad;
	
	private String vigencia;

}