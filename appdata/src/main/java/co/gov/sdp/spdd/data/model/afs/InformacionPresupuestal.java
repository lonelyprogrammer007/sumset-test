package co.gov.sdp.spdd.data.model.afs;

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
import javax.validation.constraints.Size;

import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_INFORMACION_PRESUPUESTAL")
public class InformacionPresupuestal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_INFO_PRESUPUESTAL")
	private Long idInfoPresupuestal;

	@Column(name = "YEAR")
	//@Size(max = 4)
	private Long year;

	@Column(name = "MES")
	private Long mes;

	@JoinColumn(name = "CODIGO_DISTRITAL", referencedColumnName = "CODIGO_ENTIDAD")
	@ManyToOne(optional = true)
	private Entidad codigoDistrital;

	@Column(name = "CODIGO_PROYECTO")
	private Long codigoProyecto;

	@Column(name = "NOMBRE_PROYECTO")
	private String nombreProyecto;

	@Column(name = "EJECUCION_VIGENCIA")
	private Double ejecucionVigencia;

	@Column(name = "GIROS_VIGENCIA")
	private Double girosVigencia;

	@Column(name = "RECURSOS_SUSPENDIDOS")
	private Double recursosSuspendidos;

	@Column(name = "CONSTITUCION_RESERVA")
	private Double constitucionReserva;

	@Column(name = "APROPIACION_RESERVA")
	private Double apropiacionReserva;

	@Column(name = "APROPIACION_DEFINITIVA")
	private Double apropiacionDefinitiva;

	@Column(name = "EJECUCION_GIRO_RESERVAS")
	private Double ejecucionGiroReservas;

	@Column(name = "CODIGO_CLASIF_PRESUPUESTAL")
	private String codigoClasifPresupuestal;

	@Column(name = "CODIGO_INTERNO")
	private Long codigoInterno;

	@Column(name = "ORIGEN")
	private String origen;

	@JoinColumn(name = "CODIGO_N3", referencedColumnName = "ID_ATRIBUTO")
	@ManyToOne(optional = true)
	private PddNivelAtributo codigoN3;

	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO")
	@ManyToOne(optional = true)
	private ArchivoProcesado idArchivo;

	@JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
	@ManyToOne(optional = false)
	private Pdd idPlanDesarrollo;

}
