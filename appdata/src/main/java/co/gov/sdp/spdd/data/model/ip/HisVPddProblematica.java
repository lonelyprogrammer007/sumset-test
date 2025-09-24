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
@Table(name = "SPDD_HIS_V_PDD_PROBLEMATICA")
public class HisVPddProblematica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROBLEMATICA")
    private Long idProblematica;
    @Basic(optional = false)
    @Column(name = "PROBLEMATICA")
    private String problematica;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_LS_LOCALIZACION")
    private Integer idLsLocalizacion;
    @Column(name = "ID_LS_SUBLOCALIZACION")
    private Integer idLsSublocalizacion;
    @Column(name = "ID_LZ_UPZ_UPR")
    private Integer idLzUpzUpr;
    @Column(name = "CAUSAS")
    private String causas;
    @Column(name = "CONSECUENCIAS")
    private String consecuencias;
    @Column(name = "OBJETIVO")
    private String objetivo;
    @JoinColumn(name = "ID_COMPROMISO", referencedColumnName = "ID_COMPROMISO")
    @ManyToOne(optional = false)
    private HisVPddCompromiso idCompromiso;

}
