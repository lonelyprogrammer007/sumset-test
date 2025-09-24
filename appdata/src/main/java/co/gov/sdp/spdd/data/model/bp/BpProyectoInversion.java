package co.gov.sdp.spdd.data.model.bp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import co.gov.sdp.spdd.data.model.afs.Entidad;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROYECTO_INVERSION")
@Data
public class BpProyectoInversion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROY_INVERSION")
    private Long idProyInversion;
    
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CODIGO_BPIN")
    private String codigoBpin;
    
    @Column(name = "NOMBRE_BPIN")
    private String nombreBpin;
    
    @Column(name = "NOMBRE_POAI")
    private String nombrePoai;
    
    @Column(name = "ANTECEDENTE")
    private String antecedente;
    
    @Column(name = "SITUACION")
    private String situacion;
    
    @Column(name = "DESCRIPCION_UNIVERSO")
    private String descripcionUniverso;
    
    @Column(name = "CUANTIFICACION")
    private Long cuantificacion;
    
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "OBJETIVO")
    private String objetivo;
    
    @Column(name = "OBSERVACION")
    private String observacion;
    
    @Column(name = "NOMBRE_GERENTE")
    private String nombreGerente;
    
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Column(name = "CARGO")
    private String cargo;
    
    @Column(name = "AREA")
    private String area;
    
    @Column(name = "FECHA_RESPONSABLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResponsable;
    
    @Column(name = "OBSERACION_REG")
    private String obseracionReg;
    
    @Column(name = "VERSION")
    private Long version;
    
    @Column(name = "APORTE_CIUDADANO")
    private Long aporteCiudadano;
    
    @Basic(optional = false)
    @Column(name = "BLOQUEO")
    private Long bloqueo;    
    
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    
    @Column(name = "FECHA_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVersion;
    
    @Column(name = "ID_ARCH_UCM")
    private String idArchUcm;
    
    @Column(name = "PESO")
    private Long peso;
    
    @Column(name = "ENFOQUE_GENERO")
    private String enfoqueGenero;
    
    @JoinColumn(name = "ID_LS_ESTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEstado;
    
    @JoinColumn(name = "ID_LS_ETAPA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEtapa;
    
    @JoinColumn(name = "ID_LS_UNIDAD", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsUnidad;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne(optional = false)
    private Entidad codigoEntidad;
    
    @JoinColumn(name = "ID_ATRIBUTO_PDD", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne
    private PddNivelAtributo idAtributoPdd;
    
    @JoinColumn(name = "ID_ATRIBUTO_PDL", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne
    private PdlNivelAtributo idAtributoPdl;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne
    private Pdd idPlanDesarrollo;
    
    @JoinColumn(name = "ID_LS_SECTOR_AL", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsSectorAl;
}