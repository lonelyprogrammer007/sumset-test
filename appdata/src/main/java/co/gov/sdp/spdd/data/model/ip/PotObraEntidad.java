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

import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_POT_OBRA_ENTIDAD")
public class PotObraEntidad implements Serializable {

	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBRA_ENT")
    private Long idObraEntidad;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne
    private Entidad codigoEntidad;
    
    @JoinColumn(name = "ID_OBRA_PROY_POT", referencedColumnName = "ID_OBRA_PROY_POT")
    @ManyToOne
    private PotObra idObraProyPot;
}
