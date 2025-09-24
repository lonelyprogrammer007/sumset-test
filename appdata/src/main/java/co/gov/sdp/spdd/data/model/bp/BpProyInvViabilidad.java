package co.gov.sdp.spdd.data.model.bp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_VIABILIDAD")
@Data
public class BpProyInvViabilidad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_VIABILIDAD")
	private Long idViabilidad;
	@Basic(optional = false)
	@Column(name = "SUSTENTACION")
	private String sustentacion;
	@Basic(optional = false)
	@Column(name = "OBSERVACION")
	private String observacion;
	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;
	@Basic(optional = false)
	@Column(name = "CARGO")
	private String cargo;
	@Basic(optional = false)
	@Column(name = "TELEFONO")
	private String telefono;
	@Basic(optional = false)
	@Column(name = "CORREO")
	private String correo;
	@Basic(optional = false)
	@Column(name = "AREA")
	private String area;
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
	@ManyToOne(optional = false)
	private BpProyectoInversion idProyInversion;
}