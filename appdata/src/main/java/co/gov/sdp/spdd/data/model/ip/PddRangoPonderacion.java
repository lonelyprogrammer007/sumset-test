package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_RANGO_PONDERACION")
public class PddRangoPonderacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RANGO")
    private Long idRango;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "LOGO")
    private String logo;
    
    @Basic(optional = false)
    @Column(name = "RANGO")
    private String rango;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne
    private Pdd idPlanDesarrollo;

}
