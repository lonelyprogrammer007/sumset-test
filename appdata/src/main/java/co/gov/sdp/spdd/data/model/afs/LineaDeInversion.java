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
@Table(name = "SPDD_LINEA_DE_INVERSION")
public class LineaDeInversion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LINEA_INVERSION")
    private Long idLineaInversion;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "ESTABLECIDO")
    private String establecido;
    
    @Basic(optional = false)
    @Column(name = "CONCEPTO")
    private String concepto;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @JoinColumn(name = "ID_LS_SECTOR", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsSector;

}
