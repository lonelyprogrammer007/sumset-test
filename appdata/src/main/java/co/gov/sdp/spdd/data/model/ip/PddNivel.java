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
@Table(name = "SPDD_PDD_NIVEL")
public class PddNivel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PDD_NIVEL")
    private Long idPddNivel;
    
    @Basic(optional = false)
    @Column(name = "COD_NIVEL")
    private Long codNivel;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "OBLIGATORIO_PDL")
    private Long obligatorioPdl;
    
    @Column(name="SUM_POND")
    private Long sumPond;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne(optional = false)
    private Pdd idPlanDesarrollo;

}
