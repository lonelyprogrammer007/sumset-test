package co.gov.sdp.spdd.data.model.pa;

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
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_ETARIO_ANUAL")
public class PaActUbiEtarioAnual implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ET_ANUAL")
    private Long idEtAnual;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private int magnitud;
    
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private short vigencia;
    
    @JoinColumn(name = "ID_LS_TIPOLOGIA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsTipologia;
    
    @JoinColumn(name = "ID_UBI_ETARIO", referencedColumnName = "ID_UBI_ETARIO")
    @ManyToOne(optional = false)
    private PaActUbiEtario idUbiEtario;    
}
