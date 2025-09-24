package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entidad que representa la tabla SPDD_HIS_V_PDD_COMPROMISO de la BD
 */
@Data
@Entity
@Table(name = "SPDD_HIS_V_PDD_COMPROMISO")
public class HisVPddCompromiso implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID_COMPROMISO")
	private Long idCompromiso;
	@Basic(optional = false)
	@Column(name = "ID_ESTRATEGICO_LC")
	private Long idEstrategicoLc;
	@JoinColumn(name = "ID_HIS_PLAN_DESARROLLO", referencedColumnName = "ID_HIS_PLAN_DESARROLLO")
	@ManyToOne(optional = false)
	private HisVPdd idHisPlanDesarrollo;


}
