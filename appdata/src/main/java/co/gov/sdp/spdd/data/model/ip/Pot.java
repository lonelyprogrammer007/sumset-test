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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_POT")
public class Pot implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_POT")
    private Long idPot;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_POT")
    private String codigoPot;

    @Column(name = "ACTO_ADMINISTRATIVO")
    private String actoAdministrativo;
    
    @Basic(optional = false)
    @Column(name = "YEAR_INICIO")
    private String yearInicio;
    
    @Basic(optional = false)
    @Column(name = "YEAR_FIN")
    private String yearFin;
    
    @JoinColumn(name = "ID_LS_ADOPTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsAdoptado;
    
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "URL")
    private String url;

}
