package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "SPDD_HIS_V_PDD_MP_ANUALIZAR")
public class HisVPddMpAnualizar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ANUALIZAR")
    private Long idAnualizar;
    @Column(name = "VIGENCIA")
    private String vigencia;
    @Column(name = "MAGNITUD")
    private BigDecimal magnitud;
    @Column(name = "RECURSOS")
    private BigDecimal recursos;
    @JoinColumn(name = "ID_META_PRODUCTO", referencedColumnName = "ID_META_PRODUCTO")
    @ManyToOne(optional = false)
    private HisVPddMetaProducto idMetaProducto;

}
