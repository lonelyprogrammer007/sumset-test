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

import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_ETARIO")
public class PaActUbiEtario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBI_ETARIO")
    private Long idUbiEtario;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private int magnitud;
    
    @JoinColumn(name = "ID_POBLACION", referencedColumnName = "ID_POBLACION")
    @ManyToOne(optional = false)
    private BpProyInvPoblacion idPoblacion;
    
    @JoinColumn(name = "ID_PA_UBICACION", referencedColumnName = "ID_PA_UBICACION")
    @ManyToOne(optional = false)
    private PaActUbicacion idPaUbicacion;    
}
