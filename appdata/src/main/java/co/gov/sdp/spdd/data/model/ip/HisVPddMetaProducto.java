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
@Table(name = "SPDD_HIS_V_PDD_META_PRODUCTO")
public class HisVPddMetaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_META_PRODUCTO")
    private Long idMetaProducto;
    @Basic(optional = false)
    @Column(name = "ID_LS_VERBO")
    private int idLsVerbo;
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private BigDecimal magnitud;
    @Basic(optional = false)
    @Column(name = "ID_LS_UNIDAD_MEDIDA")
    private int idLsUnidadMedida;
    @Basic(optional = false)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "ID_LS_TIPO_ANUALIZACION")
    private int idLsTipoAnualizacion;
    @Column(name = "META_PDD")
    private Short metaPdd;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "ES_FORMULACION")
    private Short esFormulacion;
    @Column(name = "PONDERACION")
    private Integer ponderacion;
    @Column(name = "BLOQUEA_PPI")
    private Short bloqueaPpi;
    @Column(name = "ID_LS_ESTADO")
    private Integer idLsEstado;
    @JoinColumn(name = "ID_META_RESULTADO", referencedColumnName = "ID_META_RESULTADO")
    @ManyToOne(optional = false)
    private HisVPddMetaResultado idMetaResultado;

}
