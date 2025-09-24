package co.gov.sdp.spdd.data.model.pa;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "SPDD_PA_ACT_UBI_DESAGREGACION")
public class PaActUbiDesagregacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESAGREGA")
    private Long idDesagrega;
    
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private short vigencia;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private BigDecimal magnitud;
    
    @Basic(optional = false)
    @Column(name = "RECURSOS")
    private BigDecimal recursos;
    
    @Basic(optional = false)
    @Column(name = "VIG_ORIGEN")
    private short vigOrigen;
    
    @JoinColumn(name = "ID_PA_UBICACION", referencedColumnName = "ID_PA_UBICACION")
    @ManyToOne(optional = false)
    private PaActUbicacion idPaUbicacion;    
}
