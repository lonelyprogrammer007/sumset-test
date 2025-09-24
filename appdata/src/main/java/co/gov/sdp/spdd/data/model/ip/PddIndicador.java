package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.math.BigDecimal;
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

import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_INDICADOR")
public class PddIndicador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INDICADOR")
	private Long idIndicador;

	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;

	@Basic(optional = false)
	@Column(name = "TIPO")
	private String tipo;

	@Basic(optional = false)
	@Column(name = "LINEA_BASE_DESC")
	private String lineaBaseDesc;

	@Basic(optional = false)
	@Column(name = "FUENTE")
	private String fuente;

	@Basic(optional = false)
	@Column(name = "YEAR_LINEA_BASE")
	private String yearLineaBase;

	@Basic(optional = false)
	@Column(name = "INFORMACION_SOPORTE")
	private String informacionSoporte;

	@Column(name = "LINEA_BASE")
	private Long lineaBase;

	@Column(name = "MAGNITUD_LB")
	private Long magnitudLb;

	@Column(name = "MAGNITUD")
	private Long magnitud;

	@Column(name = "PERIODICIDAD")
	private String periodicidad;

	@Column(name = "FUENTE_EXTERNA")
	private Long fuenteExterna;

	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO")
	@ManyToOne
	private ArchivoProcesado idArchivo;

	@JoinColumn(name = "ID_LS_ESTADO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne
	private ArgumentoListaSimple idLsEstado;
	@Column(name = "PONDERACION")
	private Long ponderacion;
	
	@JoinColumn(name = "ID_LS_AGREGACION", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne
	private ArgumentoListaSimple idLsAgregacion;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

}
