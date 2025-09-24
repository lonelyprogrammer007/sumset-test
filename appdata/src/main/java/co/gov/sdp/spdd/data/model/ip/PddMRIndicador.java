package co.gov.sdp.spdd.data.model.ip;

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

@Data
@Entity
@Table(name = "SPDD_PDD_MR_INDICADOR")
public class PddMRIndicador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IND_MR")
	private Long idIndProxy;
	
	@JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
	@ManyToOne
	private PddIndicador idIndicador;
	
	@JoinColumn(name = "ID_META_RESULTADO", referencedColumnName = "ID_META_RESULTADO")
	@ManyToOne
	private PddMetaResultado idMetaResultado;

}
