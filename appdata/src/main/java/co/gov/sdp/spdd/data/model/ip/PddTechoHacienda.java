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

import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_TECHO_HACIENDA")
public class PddTechoHacienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TECHO")
    private Long idTecho;
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private String vigencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RECURSOS")
    private BigDecimal recursos;
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne
    private Entidad codigoEntidad;
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne
    private Pdd idPlanDesarrollo;

}
