package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

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
@Table(name = "SPDD_HIS_V_PDD_NIVEL")
public class HisVPddNivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PDD_NIVEL")
    private Long idPddNivel;
    @Basic(optional = false)
    @Column(name = "COD_NIVEL")
    private short codNivel;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "OBLIGATORIO_PDL")
    private short obligatorioPdl;
    @JoinColumn(name = "ID_HIS_PLAN_DESARROLLO", referencedColumnName = "ID_HIS_PLAN_DESARROLLO")
    @ManyToOne(optional = false)
    private HisVPdd idHisPlanDesarrollo;

}
