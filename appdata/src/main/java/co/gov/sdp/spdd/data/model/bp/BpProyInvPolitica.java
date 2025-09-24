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

import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_POLITICA")
@Data
public class BpProyInvPolitica implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROY_POLITICA")
    private Long idProyPolitica;
    
    @JoinColumn(name = "ID_POL_PUB", referencedColumnName = "ID_POL_PUB")
    @ManyToOne(optional = false)
    private PddPoliticaPublica idPolPub;
    
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne(optional = false)
    private BpProyectoInversion idProyInversion;

    
    
}