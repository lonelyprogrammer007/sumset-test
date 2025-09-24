package co.gov.sdp.spdd.data.model.ip;

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

@Data
@Entity
@Table(name = "SPDD_COMPROMISO_ESTRATEGICO")
public class CompromisoEstrategico {

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPROMISO")
	private Long idCompromiso;
	
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private short estado;
	@JoinColumn(name = "ID_LS_ESTRATEGICO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsEstrategico;
	
	@JoinColumn(name = "ID_LS_TEMATICA", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsTematica;
}
