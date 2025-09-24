package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PARAMETRO_GENERAL")
public class ParametroGeneral implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PARAMETRO")
    private String codigoParametro;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "ARGUMENTO")
    private String argumento;
    
    @Column(name = "CODIGO_USUARIO_CREA")
    private String codigoUsuarioCrea;
    
    @Column(name = "CODIGO_USUARIO_MOD")
    private String codigoUsuarioMod;
    
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

}
