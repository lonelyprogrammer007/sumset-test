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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.bp.BpProyInvActividad;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACTIVIDAD_ASOCIADA")
public class PaActividadAsociada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PA_ACTIVIDAD")
    private Long idPaActividad;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_TIPO_PROGR")
    private int idLsTipoProgr;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_TIPO_ANUA")
    private int idLsTipoAnua;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_POBLACION")
    private int idLsPoblacion;
    
    @Basic(optional = false)
    @Column(name = "PONDERACION")
    private BigDecimal ponderacion;
    
    @Basic(optional = false)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    @JoinColumn(name = "ID_LS_ESTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEstado;
    
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @OneToOne(optional = false)
    private BpProyInvActividad idActividad;
    
    @JoinColumn(name = "ID_PROY_LINEA", referencedColumnName = "ID_PROY_LINEA")
    @ManyToOne(optional = false)
    private BpProyInvLinea idProyLinea;
    
    @JoinColumn(name = "ID_PROY_PMR", referencedColumnName = "ID_PROY_PMR")
    @ManyToOne(optional = false)
    private BpProyInvPmr idProyPmr;   
}
