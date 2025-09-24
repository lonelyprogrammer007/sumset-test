package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPDD_HIS_V_POT_OBRA")
public class HisVPotObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OBRA_PROY_POT")
    private Integer idObraProyPot;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "OBRA")
    private String obra;
    @Basic(optional = false)
    @Column(name = "ID_LS_PLAZO")
    private int idLsPlazo;
    @Basic(optional = false)
    @Column(name = "ID_NIVEL_POT")
    private int idNivelPot;
  
    
}