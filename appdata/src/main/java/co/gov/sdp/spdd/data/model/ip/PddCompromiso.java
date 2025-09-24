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
@Table(name = "SPDD_PDD_COMPROMISO")
public class PddCompromiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPROMISO")
    private Long idCompromiso;
    
    /*
    @JoinColumn(name = "ID_LS_TEMATICA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsTematica;
    */
    
    @JoinColumn(name = "ID_ESTRATEGICO_LC", referencedColumnName = "ID_COMPROMISO")
    @ManyToOne(optional = false)
    private CompromisoEstrategico idEstrategico;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne(optional = false)
    private Pdd idPlanDesarrollo;

}
