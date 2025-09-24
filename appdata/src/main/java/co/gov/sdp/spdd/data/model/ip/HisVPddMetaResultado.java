package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_HIS_V_PDD_META_RESULTADO")
public class HisVPddMetaResultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_META_RESULTADO")
    private Long idMetaResultado;
    @Basic(optional = false)
    @Column(name = "ID_LS_VERBO")
    private int idLsVerbo;
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private String magnitud;
    @Basic(optional = false)
    @Column(name = "ID_LS_UNIDAD_MEDIDA")
    private int idLsUnidadMedida;
    @Basic(optional = false)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "ID_LS_TIPO_ANUALIZACION")
    private int idLsTipoAnualizacion;
    @Basic(optional = false)
    @Column(name = "ES_FORMULACION")
    private short esFormulacion;
    @Column(name = "PONDERACION")
    private BigDecimal ponderacion;
    @Column(name = "ID_LS_ESTADO")
    private Integer idLsEstado;
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
    @ManyToOne
    private HisVPddIndicador idIndicador;
    @JoinColumn(name = "ID_PROY_ESTRATEGICO", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne
    private HisVPddNivelAtributo idProyEstrategico;
    @JoinColumn(name = "ID_PROB_IND", referencedColumnName = "ID_PROB_IND")
    @ManyToOne
    private HisVPddPrbIndicador idProbInd;

}
