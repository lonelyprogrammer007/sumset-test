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
@Table(name = "SPDD_PDD_MP_ENTIDAD_POT")
public class PddMpEntidadPot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MP_ENT_POT")
    private Long idMpEntPot;
    
    @JoinColumn(name = "ID_MP_ENTIDAD", referencedColumnName = "ID_MP_ENTIDAD")
    @ManyToOne
    private PddMpEntidad idMpEntidad;
    
    @JoinColumn(name = "ID_OBRA_PROY_POT", referencedColumnName = "ID_OBRA_PROY_POT")
    @ManyToOne
    private PotObra idObraProyPot;

}
