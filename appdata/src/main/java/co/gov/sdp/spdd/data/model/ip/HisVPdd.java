package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_HIS_V_PDD")
public class HisVPdd implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "ID_PLAN_DESARROLLO")
    private int idPlanDesarrollo;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_PLAN")
    private String nombrePlan;
    
    @Basic(optional = false)
    @Column(name = "SIGLA_PLAN")
    private String siglaPlan;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_ADOPTADO")
    private int idLsAdoptado;
    
    @Basic(optional = false)
    @Column(name = "ACTO_ADMINISTRATIVO")
    private String actoAdministrativo;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ACTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActo;
    
    @Basic(optional = false)
    @Column(name = "YEAR_INICIO")
    private String yearInicio;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_NIVELES")
    private int idLsNiveles;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_ESTADO_PLAN")
    private int idLsEstadoPlan;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ALCALDE")
    private String nombreAlcalde;
    
    @Basic(optional = false)
    @Column(name = "CORREO_ALCALDE")
    private String correoAlcalde;
    
    @Basic(optional = false)
    @Column(name = "PROGRAMA_GOBIERNO")
    private String programaGobierno;
    
    @Basic(optional = false)
    @Column(name = "ID_LS_AVANCE_SGR")
    private int idLsAvanceSgr;
    
    @Basic(optional = false)
    @Column(name = "YEAR_FINAL")
    private String yearFinal;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_HIS_PLAN_DESARROLLO")
    private Long idHisPlanDesarrollo;
    
    @Basic(optional = false)
    @Column(name = "VERSION")
    private String version;
}
