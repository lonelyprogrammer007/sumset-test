package co.gov.sdp.spdd.data.model.pa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.gov.sdp.spdd.data.model.ip.PddSubtemaTransversal;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_TRANSV")
public class PaActUbiTransv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UBI_TRANSV")
    private Long idUbiTransv;
    
    @JoinColumn(name = "ID_PA_UBICACION", referencedColumnName = "ID_PA_UBICACION")
    @ManyToOne(optional = false)
    private PaActUbicacion idPaUbicacion;
    
    @JoinColumn(name = "ID_SUBTEMA", referencedColumnName = "ID_SUBTEMA")
    @ManyToOne(optional = false)
    private PddSubtemaTransversal idSubtema;    
}
