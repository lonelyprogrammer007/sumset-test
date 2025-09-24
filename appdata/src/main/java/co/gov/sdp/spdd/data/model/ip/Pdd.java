package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD")
public class Pdd implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLAN_DESARROLLO")
    private Long idPlanDesarrollo;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_PLAN")
    private String nombrePlan;
    
    @Basic(optional = false)
    @Column(name = "SIGLA_PLAN")
    private String siglaPlan;
    
    @Basic(optional = true)
    @Column(name = "ACTO_ADMINISTRATIVO")
    private String actoAdministrativo;
    
    @Basic(optional = true)
    @Column(name = "FECHA_ACTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActo;
    
    @Basic(optional = false)
    @Column(name = "YEAR_INICIO")
    private String yearInicio;
    
    @Basic(optional = true)
    @Column(name = "NOMBRE_ALCALDE")
    private String nombreAlcalde;
    
    @Basic(optional = true)
    @Column(name = "CORREO_ALCALDE")
    private String correoAlcalde;
    
    @Basic(optional = false)
    @Column(name = "PROGRAMA_GOBIERNO")
    private String programaGobierno;
    
    @Basic(optional = false)
    @Column(name = "YEAR_FINAL")
    private String yearFinal;
    
    @Basic(optional = false)
    @Column(name = "VERSION")
    private String version;
    
    @JoinColumn(name = "ID_LS_ADOPTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsAdoptado;
    
    @JoinColumn(name = "ID_LS_AVANCE_SGR", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsAvanceSgr;
    
    @JoinColumn(name = "ID_LS_ESTADO_PLAN", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsEstadoPlan;
    
    @JoinColumn(name = "ID_LS_NIVELES", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsNiveles;
    
    @Transient
    private String vigencia;
    
    public String getVigencia() {
    	return String.format("%s - %s", yearInicio, yearFinal);
    }
   

}
