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
@Table(name = "SPDD_HIS_V_PDD_TECHO_HACIENDA")
public class HisVPddTechoHacienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TECHO")
    private Long idTecho;
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private String vigencia;
    @Column(name = "RECURSOS")
    private BigDecimal recursos;
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @JoinColumn(name = "ID_HIS_PLAN_DESARROLLO", referencedColumnName = "ID_HIS_PLAN_DESARROLLO")
    @ManyToOne(optional = false)
    private HisVPdd idHisPlanDesarrollo;

}
