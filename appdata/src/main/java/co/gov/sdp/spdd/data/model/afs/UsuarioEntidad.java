package co.gov.sdp.spdd.data.model.afs;

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

@Data
@Entity
@Table(name = "SPDD_USUARIO_ENTIDAD")
public class UsuarioEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO_ENTIDAD")
    private Long idUsuarioEntidad;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne(optional = false)
    private Entidad codigoEntidad;
    
    @Basic(optional = false)
    @Column(name = "MODIFICAR_DATOS")
    private Long modificarDatos;

}
