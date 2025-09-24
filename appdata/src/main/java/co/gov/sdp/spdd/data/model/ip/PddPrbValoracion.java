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
@Table(name = "SPDD_PDD_PRB_VALORACION")
public class PddPrbValoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VALORACION")
    private Long idValoracion;
    
    @Basic(optional = false)
    @Column(name = "GRAVEDAD")
    private Long gravedad;
    
    @Basic(optional = false)
    @Column(name = "DURACION")
    private Long duracion;
    
    @Basic(optional = false)
    @Column(name = "IMPACTO")
    private Long impacto;
    
    @Basic(optional = false)
    @Column(name = "DEBILIDAD")
    private Long debilidad;
    
    @Basic(optional = false)
    @Column(name = "BALANCE_INICIAL")
    private String balanceInicial;
    
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    @Basic(optional = false)
    @Column(name = "MOMENTO")
    private Long momento;
    
    @JoinColumn(name = "ID_LS_SECTOR", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsSector;
    
    @JoinColumn(name = "ID_LS_DIMENSION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsDimension;
    
    @JoinColumn(name = "ID_LS_COMPETENCIA_1", referencedColumnName = "ID_COMPETENCIA")
    @ManyToOne
    private PddCompetenciaAsociada idLsCompetencia1;
    
    @JoinColumn(name = "ID_LS_COMPETENCIA_2", referencedColumnName = "ID_COMPETENCIA")
    @ManyToOne
    private PddCompetenciaAsociada idLsCompetencia2;
    
    @JoinColumn(name = "ID_PROBLEMATICA", referencedColumnName = "ID_PROBLEMATICA")
    @ManyToOne
    private PddProblematica idProblematica;
    
    @Column(name = "AGRAVA")
    private Long agrava;
    
    @Column(name = "CONTRARRESTA")
    private Long contrarresta;
    

}
