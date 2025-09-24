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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_PROBLEMATICA")
public class PddProblematica implements Serializable {

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
    
    @Column(name = "CAUSAS")
    private String causas;
    
    @Column(name = "CONSECUENCIAS")
    private String consecuencias;
    
    @Column(name = "OBJETIVO")
    private String objetivo;
    
    @JoinColumn(name = "ID_LS_LOCALIZACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsLocalizacion;
    
    @JoinColumn(name = "ID_LS_SUBLOCALIZACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsSublocalizacion;
    
    @JoinColumn(name = "ID_LZ_UPZ_UPR", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLzUpzUpr;
    
    @JoinColumn(name = "ID_COMPROMISO", referencedColumnName = "ID_COMPROMISO")
    @ManyToOne(optional = false)
    private PddCompromiso idCompromiso;
}
