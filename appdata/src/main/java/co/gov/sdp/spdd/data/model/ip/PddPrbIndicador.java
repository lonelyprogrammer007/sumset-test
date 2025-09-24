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
@Table(name = "SPDD_PDD_PRB_INDICADOR")
public class PddPrbIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROB_IND")
    private Long idProbInd;
    
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
    @ManyToOne(optional = false)
    private PddIndicador idIndicador;
    
    @JoinColumn(name = "ID_PROBLEMATICA", referencedColumnName = "ID_PROBLEMATICA")
    @ManyToOne(optional = false)
    private PddProblematica idProblematica;

}
