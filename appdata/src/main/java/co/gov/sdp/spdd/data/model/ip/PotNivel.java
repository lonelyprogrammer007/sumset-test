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
@Table(name = "SPDD_POT_NIVEL")
public class PotNivel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NIVEL_POT")
	private Long idNivelPot;

	@Basic(optional = false)
	@Column(name = "NUMERO_NIVEL")
	private Long numeroNivel;

	@Basic(optional = false)
	@Column(name = "ES_OBRA")
	private Long esObra;

	@Basic(optional = false)
	@Column(name = "CERRADO")
	private Long cerrado;

	@Basic(optional = false)
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@JoinColumn(name = "ID_NIVEL_PADRE", referencedColumnName = "ID_NIVEL_POT")
	@ManyToOne
	private PotNivel idNivelPadre;

	@JoinColumn(name = "ID_RAMA_POT", referencedColumnName = "ID_RAMA_POT")
	@ManyToOne(optional = false)
	private PotRama idRamaPot;

}
