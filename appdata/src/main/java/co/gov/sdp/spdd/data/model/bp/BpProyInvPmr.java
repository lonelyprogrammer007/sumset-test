package co.gov.sdp.spdd.data.model.bp;

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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_PMR")
@Data
public class BpProyInvPmr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROY_PMR")
    private Long idProyPmr;
    
    @JoinColumn(name = "ID_IND_MR", referencedColumnName = "ID_IND_MR")
    @ManyToOne(optional = false)
    private PddMRIndicador idIndMr;
    
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne(optional = false)
    private BpProyectoInversion idProyInversion;
    
}