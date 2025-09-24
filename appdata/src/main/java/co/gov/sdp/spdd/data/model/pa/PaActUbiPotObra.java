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

import co.gov.sdp.spdd.data.model.bp.BpProyInvObraPot;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_POT_OBRA")
public class PaActUbiPotObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBI_OBRA")
    private Long idUbiObra;
    
    @JoinColumn(name = "ID_PI_OBRA_POT", referencedColumnName = "ID_PI_OBRA_POT")
    @ManyToOne
    private BpProyInvObraPot idPiObraPot;
    
    @JoinColumn(name = "ID_PA_UBICACION", referencedColumnName = "ID_PA_UBICACION")
    @ManyToOne(optional = false)
    private PaActUbicacion idPaUbicacion;  
}
