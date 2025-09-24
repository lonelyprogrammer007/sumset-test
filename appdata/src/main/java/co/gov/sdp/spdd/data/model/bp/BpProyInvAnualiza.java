package co.gov.sdp.spdd.data.model.bp;

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

@Entity
@Table(name = "SPDD_BP_PROY_INV_ANUALIZA")
@Data
public class BpProyInvAnualiza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRY_ANUALIZA")
    private Long idPryAnualiza;
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private Long vigencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private Double monto;
    @JoinColumn(name = "ID_FUENTE", referencedColumnName = "ID_FUENTE")
    @ManyToOne(optional = false)
    private BpProyInvFinancia idFuente;
}