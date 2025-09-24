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

import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_INICIATIVA_CONDICION")
@Data
public class BpIniciativaCondicion implements Serializable {

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONDICION")
	private Long idCondicion;
	@JoinColumn(name = "ID_LS_CONDICION", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsCondicion;
	@JoinColumn(name = "ID_INICIATIVA", referencedColumnName = "ID_INICIATIVA")
	@ManyToOne
	private BpIniciativaCiudadana idIniciativa;
}
