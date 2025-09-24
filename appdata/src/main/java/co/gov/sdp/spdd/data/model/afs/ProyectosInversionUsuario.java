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
@Table(name = "SPDD_PROYECTO_INV_USUARIO")
public class ProyectosInversionUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROYECTO_USUARIO")
    private Long idProyectoUsuario;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;
    
    @JoinColumn(name = "ID_PROYECTO_INVERSION", referencedColumnName = "ID_PROYECTO_INVERSION")
    @ManyToOne(optional = false)
    private ProyectoInversion idProyectoInversion;

}
