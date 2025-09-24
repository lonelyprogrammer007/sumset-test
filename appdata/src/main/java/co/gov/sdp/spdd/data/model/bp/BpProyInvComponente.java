package co.gov.sdp.spdd.data.model.bp;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_COMPONENTE")
@Data
public class BpProyInvComponente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_COMPONENTE_INV")
	private Long idComponenteInv;
	@Column(name = "PLAZO_INICIAL")
	private Short plazoInicial;
	@Column(name = "CAPEX")
	private Long capex;
	@Column(name = "OPEX")
	private Long opex;
	@Column(name = "ID_COMPONENTE")
	private String idComponente;
	@JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
	@ManyToOne(optional = false)
	private BpProyInvTipo idTipo;
}