package co.gov.sdp.spdd.data.model.pa;

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

import co.gov.sdp.spdd.data.model.bp.BpProyInvGrupo;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_ETARIO_GRUPO")
public class PaActUbiEtarioGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ET_GRUPO")
    private Long idEtGrupo;
    
    @JoinColumn(name = "ID_GRUPO_PROY", referencedColumnName = "ID_GRUPO_PROY")
    @ManyToOne(optional = false)
    private BpProyInvGrupo idGrupoProy;
    
    @JoinColumn(name = "ID_UBI_ETARIO", referencedColumnName = "ID_UBI_ETARIO")
    @ManyToOne(optional = false)
    private PaActUbiEtario idUbiEtario;    
}
