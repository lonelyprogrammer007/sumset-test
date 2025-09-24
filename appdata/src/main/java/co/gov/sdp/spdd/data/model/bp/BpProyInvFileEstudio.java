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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_FILE_ESTUDIO")
@Data
public class BpProyInvFileEstudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIO")
    private Long idEstudio;
    @Basic(optional = false)
    @Column(name = "CONSECUTIVO")
    private long consecutivo;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Basic(optional = false)
    @Column(name = "REALIZADO")
    private String realizado;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "ID_ARCH_UCM")
    private String idArchUcm;
    @Basic(optional = false)
    @Column(name = "PESO")
    private Long peso;
    @Basic(optional = false)
    @Column(name = "RUTA_TEMPORAL")
    private String rutaTemporal;
    @Basic(optional = false)
    @Column(name = "VERSION")
    private int version;
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne(optional = false)
    private BpProyectoInversion idProyInversion;
 
}