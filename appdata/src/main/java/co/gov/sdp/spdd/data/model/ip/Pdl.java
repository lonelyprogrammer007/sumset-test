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
import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDL")
public class Pdl implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLAN_LOCAL")
    private Long idPlanLocal;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_PLAN")
    private String nombrePlan;
    
    @Basic(optional = false)
    @Column(name = "URL_PLAN")
    private String urlPlan;
    
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
    @Column(name = "NOMBRE_ALCALDE_LOCAL")
    private String nombreAlcaldeLocal;
    
    @Basic(optional = false)
    @Column(name = "CORREO_ALCALDE_LOCAL")
    private String correoAlcaldeLocal;
    
    @Basic(optional = false)
    @Column(name = "YEAR_FINAL")
    private String yearFinal;
    
    @JoinColumn(name = "ID_LS_ADOPTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsAdoptado;
    
    @JoinColumn(name = "ID_LS_ESTADO_PLAN", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEstadoPlan;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne
    private Pdd idPlanDesarrollo;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne(optional = false)
    private Entidad codigoEntidad;
    
    @Transient
    private String vigencia;
    
    public String getVigencia() {
    	return String.format("%s - %s", yearInicio, yearFinal);
    }

}
