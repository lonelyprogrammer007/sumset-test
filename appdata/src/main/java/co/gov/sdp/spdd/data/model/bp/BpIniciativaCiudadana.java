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

import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.afs.LineaDeInversion;
import co.gov.sdp.spdd.data.model.afs.Territorializacion;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_INICIATIVA_CIUDADANA")
@Data
public class BpIniciativaCiudadana implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_INICIATIVA")
	private Long idIniciativa;
	
	@Basic(optional = false)
	@Column(name = "CODIGO")
	private Long codigo;
	
	@Basic(optional = false)
	@Column(name = "FECHA_CODIGO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCodigo;
	
	@Basic(optional = false)
	@Column(name = "RADICADO")
	private short radicado;
	
	@Column(name = "NUMERO_RAD")
	private Long numeroRad;
	
	@Column(name = "FECHA_RAD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRad;
	
	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Basic(optional = false)
	@Column(name = "APLICA_LINEA")
	private short aplicaLinea;
	
	@JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
	@ManyToOne
	private Pdd idPlanDesarrollo;
	
	@Basic(optional = false)
	@Column(name = "PROBLEMATICA")
	private String problematica;
	
	@Basic(optional = false)
	@Column(name = "TOTAL_POBLACION")
	private long totalPoblacion;
	
	@Basic(optional = false)
	@Column(name = "ALTERNATIVA_SOLUCION")
	private String alternativaSolucion;
	
	@Basic(optional = false)
	@Column(name = "NOMBRE_PROP")
	private String nombreProp;
	
	@Column(name = "TELEFONO_PROP")
	private String telefonoProp;
	
	@Column(name = "IDENTIFICACION_PROP")
	private String identificacionProp;
	
	@Column(name = "EMAIL_PROP")
	private String emailProp;
	@Column(name = "OBSERVACION")
	private String observacion;
	
	@JoinColumn(name = "ID_LS_ORIGEN", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsOrigen;
	
	@JoinColumn(name = "ID_LS_ESTADO_INICIA", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsEstadoInicia;
	
	@JoinColumn(name = "ID_LC_LINEA_INV", referencedColumnName = "ID_LINEA_INVERSION")
	@ManyToOne
	private LineaDeInversion idLcLineaInv;

	@JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
	@ManyToOne(optional = false)
	private Entidad codigoEntidad;
}
