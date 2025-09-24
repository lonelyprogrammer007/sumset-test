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

import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;
import lombok.Data;

@Entity
@Table(name = "SPDD_PDD_POLITICA_PUBLICA")
@Data
public class PddPoliticaPublica implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_POL_PUB")
    private Long idPolPub;
    
    @Basic(optional = false)
    @Column(name = "COD_POLITICA")
    private String codPolitica;
    
    @Basic(optional = false)
    @Column(name = "POLITICA")
    private String politica;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne(optional = false)
    private Pdd idPlanDesarrollo;

}
