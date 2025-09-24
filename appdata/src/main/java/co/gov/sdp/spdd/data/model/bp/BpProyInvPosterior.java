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
@Table(name = "SPDD_BP_PROY_INV_POSTERIOR")
@Data
public class BpProyInvPosterior implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_POSTERIOR")
	private Long idPosterior;
	@Basic(optional = false)
	@Column(name = "YEAR")
	private short year;
	@Basic(optional = false)
	@Column(name = "VALOR")
	private Long valor;
	@JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
	@ManyToOne(optional = false)
	private BpProyectoInversion idProyInversion;
}