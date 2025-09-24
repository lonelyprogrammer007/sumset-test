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
@Table(name = "SPDD_PA_ACT_UBICACION")
public class PaActUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PA_UBICACION")
    private Long idPaUbicacion;
    
    @Basic(optional = false)
    @Column(name = "COD_TEMATICA")
    private int codTematica;
    
    @Basic(optional = false)
    @Column(name = "COD_LIMIT")
    private int codLimit;
    
    @Basic(optional = false)
    @Column(name = "DENOMINACION")
    private String denominacion;
    
    @Basic(optional = false)
    @Column(name = "META_PLAN")
    private String metaPlan;
    
    @JoinColumn(name = "ID_PA_ACTIVIDAD", referencedColumnName = "ID_PA_ACTIVIDAD")
    @ManyToOne(optional = false)
    private PaActividadAsociada idPaActividad;
    
}
