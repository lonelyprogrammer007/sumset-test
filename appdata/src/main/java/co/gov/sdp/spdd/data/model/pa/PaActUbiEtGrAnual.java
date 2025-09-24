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

import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_ET_GR_ANUAL")
public class PaActUbiEtGrAnual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBI_GR_ANUAL")
    private Long idUbiGrAnual;
    
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private short vigencia;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private int magnitud;
    
    @JoinColumn(name = "ID_ET_GRUPO", referencedColumnName = "ID_ET_GRUPO")
    @ManyToOne
    private PaActUbiEtarioGrupo idEtGrupo;
    
}
