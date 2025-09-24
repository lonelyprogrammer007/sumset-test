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
@Table(name = "SPDD_BP_PROY_INV_FLUJO")
@Data
public class BpProyInvFlujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FLUJO_PROY_INV")
    private Long idFlujoProyInv;
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private short vigencia;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private Long valor;
    @JoinColumn(name = "ID_COMPONENTE_INV", referencedColumnName = "ID_COMPONENTE_INV")
    @ManyToOne(optional = false)
    private BpProyInvComponente idComponenteInv;

   
}