package co.gov.sdp.spdd.data.model.ip;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_HIS_V_PDD_ENT_POT_ANUAL")
public class HisVPddEntidadPotAnualiza {

	@Id
    @Basic(optional = false)
    @Column(name = "ID_ANUALIZA_POT")
    private Long idAnualizaPot;
	@Basic(optional = false)
    @Column(name = "VIGENCIA")
    private String Vigencia;
	@Basic(optional = false)
    @Column(name = "RECURSOS")
    private Long recursos;
    @JoinColumn(name = "ID_MP_ENT_POT", referencedColumnName = "ID_MP_ENT_POT")
    @ManyToOne
    private PddMpEntidadPot idObraProyPot;
}
