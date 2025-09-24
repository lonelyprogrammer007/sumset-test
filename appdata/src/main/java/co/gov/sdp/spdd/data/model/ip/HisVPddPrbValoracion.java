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
@Table(name = "SPDD_HIS_V_PDD_PRB_VALORACION")
public class HisVPddPrbValoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VALORACION")
    private Long idValoracion;
    @Basic(optional = false)
    @Column(name = "GRAVEDAD")
    private short gravedad;
    @Basic(optional = false)
    @Column(name = "DURACION")
    private short duracion;
    @Basic(optional = false)
    @Column(name = "IMPACTO")
    private short impacto;
    @Basic(optional = false)
    @Column(name = "DEBILIDAD")
    private short debilidad;
    @Basic(optional = false)
    @Column(name = "BALANCE_INICIAL")
    private String balanceInicial;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ID_LS_SECTOR")
    private int idLsSector;
    @Column(name = "ID_LS_COMPETENCIA_1")
    private Integer idLsCompetencia1;
    @Column(name = "ID_LS_COMPETENCIA_2")
    private Integer idLsCompetencia2;
    @Basic(optional = false)
    @Column(name = "ID_LS_DIMENSION")
    private BigDecimal idLsDimension;
    @Basic(optional = false)
    @Column(name = "MOMENTO")
    private short momento;
    @JoinColumn(name = "ID_PROBLEMATICA", referencedColumnName = "ID_PROBLEMATICA")
    @ManyToOne
    private HisVPddProblematica idProblematica;

}
