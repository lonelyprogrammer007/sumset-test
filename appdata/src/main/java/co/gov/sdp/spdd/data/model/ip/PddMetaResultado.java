package co.gov.sdp.spdd.data.model.ip;

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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_META_RESULTADO")
public class PddMetaResultado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_META_RESULTADO")
	private Long idMetaResultado;

	@Basic(optional = false)
	@Column(name = "MAGNITUD")
	private String magnitud;

	@Basic(optional = false)
	@Column(name = "COMPLEMENTO")
	private String complemento;

	@Basic(optional = false)
	@Column(name = "ES_FORMULACION")
	private Long esFormulacion;

	@Column(name = "PONDERACION")
	private Long ponderacion;
	
	@Column(name="SUM_POND")
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

	@JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
	@ManyToOne
	private PddIndicador idIndicador;

	@JoinColumn(name = "ID_PROY_ESTRATEGICO", referencedColumnName = "ID_ATRIBUTO")
	@ManyToOne
	private PddNivelAtributo idProyEstrategico;

	@JoinColumn(name = "ID_PROB_IND", referencedColumnName = "ID_PROB_IND")
	@ManyToOne
	private PddPrbIndicador idProbInd;

	@JoinColumn(name = "ID_PROY_ESTRATEGICO_LOCAL", referencedColumnName = "ID_ATRIBUTO")
	@ManyToOne
	private PdlNivelAtributo idProyEstrategicoLocal;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

}
