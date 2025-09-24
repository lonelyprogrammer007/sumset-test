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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_PRODUCTO")
@Data
public class BpProyInvProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private Long magnitud;
    
    @JoinColumn(name = "ID_LS_DENOMINACION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsDenominacion;
    
    @JoinColumn(name = "ID_PROY_META_PLAN", referencedColumnName = "ID_PROY_META_PLAN")
    @ManyToOne(optional = false)
    private BpProyInvMetaPlan idProyMetaPlan;

}