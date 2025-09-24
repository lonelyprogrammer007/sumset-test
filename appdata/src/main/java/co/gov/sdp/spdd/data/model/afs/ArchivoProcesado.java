package co.gov.sdp.spdd.data.model.afs;

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

@Data
@Entity
@Table(name = "SPDD_ARCHIVO_PROCESADO")
public class ArchivoProcesado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARCHIVO")
    private Long idArchivo;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    
    @Basic(optional = false)
    @Column(name = "DETALLE")
    private String detalle;
    
    @Basic(optional = false)
    @Column(name = "FECHA_CARGUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCargue;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;    
    
    @JoinColumn(name = "ID_CONFIG_CARGUE", referencedColumnName = "ID_CONFIG_CARGUE")
    @ManyToOne(optional = false)
    private ConfigCargueArchivo idConfigCargue;

}
