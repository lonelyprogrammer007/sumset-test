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
@Table(name = "SPDD_TERRITORIALIZACION")
public class Territorializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TERRITORIALIZACION")
    private Long idTerritorializacion;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @JoinColumn(name = "ID_LS_LOCALIDAD", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsLocalidad;
    
    @JoinColumn(name = "ID_LS_UPZ", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsUpz;
    
    @JoinColumn(name = "ID_LS_VEREDA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsVereda;
    
    @JoinColumn(name = "ID_LS_BARRIO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsBarrio;
    
    @JoinColumn(name = "ID_LS_UPR", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsUpr;

}
