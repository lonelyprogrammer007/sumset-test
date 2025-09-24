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

import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_BP_INICIATIVA_UBICACION")
public class BpIniciativaUbicacion implements Serializable {

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UBICACION")
	private Long idUbicacion;

	@JoinColumn(name = "ID_TERRITORIALIZACION", referencedColumnName = "ID_TERRITORIALIZACION")
	@ManyToOne(optional = false)
	private Territorializacion idTerritorializacion;

	@JoinColumn(name = "ID_INICIATIVA", referencedColumnName = "ID_INICIATIVA")
	@ManyToOne(optional = false)
	private BpIniciativaCiudadana idIniciativa;
}
