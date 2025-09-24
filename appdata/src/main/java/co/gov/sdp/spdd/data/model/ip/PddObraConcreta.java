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
@Table(name = "SPDD_PDD_OBRA_CONCRETA")
public class PddObraConcreta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONCRETA")
    private Long idConcreta;
    @Basic(optional = false)
    @Column(name = "OBRA_CONCRETA")
    private String obraConcreta;
    @JoinColumn(name = "ID_META", referencedColumnName = "ID_META")
    @ManyToOne(optional = false)
    private PddMeta idMeta;

}
