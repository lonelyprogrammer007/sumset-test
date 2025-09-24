package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPDD_HIS_V_PDD_MP_ENTIDAD_POT")
public class HisVPddMpEntidadPot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MP_ENT_POT")
    private Long idMpEntPot;
    @JoinColumn(name = "ID_MP_ENTIDAD", referencedColumnName = "ID_MP_ENTIDAD")
    @ManyToOne(optional = false)
    private HisVPddMpEntidad idMpEntidad;
    @JoinColumn(name = "ID_OBRA_PROY_POT", referencedColumnName = "ID_OBRA_PROY_POT")
    @ManyToOne(optional = false)
    private HisVPotObra idObraProyPot;

    
    
}