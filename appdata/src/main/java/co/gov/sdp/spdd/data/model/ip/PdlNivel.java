package co.gov.sdp.spdd.data.model.ip;

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

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDL_NIVEL")
public class PdlNivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PDL_NIVEL")
    private Long idPdlNivel;
    
    @Basic(optional = false)
    @Column(name = "COD_NIVEL")
    private Long codNivel;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JoinColumn(name = "ID_PLAN_LOCAL", referencedColumnName = "ID_PLAN_LOCAL")
    @ManyToOne(optional = false)
    private Pdl idPlanLocal;

}
