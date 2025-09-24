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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_META_PRODUCTO")
public class PddMetaProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_META_PRODUCTO")
	private Long idMetaProducto;

	@Basic(optional = false)
	@Column(name = "MAGNITUD")
	private BigDecimal magnitud;

	@Basic(optional = false)
	@Column(name = "COMPLEMENTO")
	private String complemento;

	@Column(name = "META_PDD")
	private Short metaPdd;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@Column(name = "ES_FORMULACION")
	private Short esFormulacion;

	@Column(name = "BLOQUEA_PPI")
	private Short bloqueaPpi;

	@Column(name = "PONDERACION")
	private BigDecimal ponderacion;

	@Column(name = "SUM_POND")
	private Long sumPond;

	@JoinColumn(name = "ID_LS_VERBO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsVerbo;

	@JoinColumn(name = "ID_LS_UNIDAD_MEDIDA", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsUnidadMedida;

	@JoinColumn(name = "ID_LS_TIPO_ANUALIZACION", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsTipoAnualizacion;

	@JoinColumn(name = "ID_LS_ESTADO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne
	private ArgumentoListaSimple idLsEstado;

	@JoinColumn(name = "ID_META_RESULTADO", referencedColumnName = "ID_META_RESULTADO")
	@ManyToOne(optional = false)
	private PddMetaResultado idMetaResultado;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

}
