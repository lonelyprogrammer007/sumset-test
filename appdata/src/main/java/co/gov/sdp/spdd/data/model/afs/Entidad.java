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
@Table(name = "SPDD_ENTIDAD")
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    
    @Basic(optional = false)
    @Column(name = "GESTION_USUARIOS")
    private short gestionUsuarios;
    
    @Basic(optional = false)
    @Column(name = "GESTION_PROYECTOS")
    private short gestionProyectos;
    
    @JoinColumn(name = "ID_LS_CLASIFICACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsClasificacion;
    
    @JoinColumn(name = "ID_LS_ASOCIACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsAsociacion;
    
    @JoinColumn(name = "ID_BANCO_PROYECTO", referencedColumnName = "ID_BANCO_PROYECTO")
    @ManyToOne
    private BancoDeProyectos idBancoProyecto;

}
