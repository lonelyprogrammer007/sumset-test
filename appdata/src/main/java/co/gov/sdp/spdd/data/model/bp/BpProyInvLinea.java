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

import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_LINEA")
@Data
public class BpProyInvLinea implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_PROY_LINEA")
	private Long idProyLinea;
	
	@JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
	@ManyToOne(optional = false)
	private BpProyectoInversion idProyInversion;
	
	@JoinColumn(name = "ID_LINEA_INVERSION", referencedColumnName = "ID_LINEA_INVERSION")
	@ManyToOne(optional = false)
	private LineaDeInversion idLineaInversion;
}