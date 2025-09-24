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
@Table(name = "SPDD_PDD_PRB_POBLACION")
public class PddPrbPoblacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_POBLACION")
    private Long idPoblacion;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_PROBLEMATICA", referencedColumnName = "ID_PROBLEMATICA")
    @ManyToOne(optional = false)
    private PddProblematica idProblematica;

}
