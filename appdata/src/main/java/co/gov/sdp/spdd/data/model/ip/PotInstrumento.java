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
@Table(name = "SPDD_POT_INSTRUMENTO")
public class PotInstrumento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INSTRUMENTO_POT")
    private Long idInstrumentoPot;
    
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    
    @JoinColumn(name = "ID_LS_DENOMINACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple denominacion;
    
    @JoinColumn(name = "ID_LS_NIVEL_INST", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsNivelInst;
    
    @JoinColumn(name = "ID_POT", referencedColumnName = "ID_POT")
    @ManyToOne(optional = false)
    private Pot idPot;

}
