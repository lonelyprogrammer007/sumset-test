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

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_POT_RAMA")
public class PotRama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RAMA_POT")
    private Long idRamaPot;
    
    @Basic(optional = false)
    @Column(name = "NUMERO_RAMA")
    private Long numeroRama;
    
    @Basic(optional = false)
	@Column(name = "CERRADA")
	private Long cerrada;
    
    @JoinColumn(name = "ID_POT", referencedColumnName = "ID_POT")
    @ManyToOne
    private Pot idPot;

}
