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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_INICIATIVA_ETARIO")
@Data
public class BpIniciativaEtario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ETARIO")
	private Long idEtario;
	@JoinColumn(name = "ID_LS_ETARIO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsEtario;
	@JoinColumn(name = "ID_INICIATIVA", referencedColumnName = "ID_INICIATIVA")
	@ManyToOne(optional = false)
	private BpIniciativaCiudadana idIniciativa;
}
