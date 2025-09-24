package co.gov.sdp.spdd.data.model.ip;

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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_POT_OBRA")
public class PotObra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBRA_PROY_POT")
    private Long idObraProyPot;
    
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    
    @Basic(optional = false)
    @Column(name = "OBRA")
    private String obra;
    
    @JoinColumn(name = "ID_LS_PLAZO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsPlazo;
    
    @JoinColumn(name = "ID_NIVEL_POT", referencedColumnName = "ID_NIVEL_POT")
    @ManyToOne(optional = false)
    private PotNivel idNivelPot;

}
