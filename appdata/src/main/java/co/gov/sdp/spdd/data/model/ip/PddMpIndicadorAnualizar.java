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
@Table(name = "SPDD_PDD_MP_INDICADOR_ANUALIZAR")
public class PddMpIndicadorAnualizar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MP_IND_ANUALIZA")
    private Long idMpIndAnualiza;
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private String vigencia;
    @Column(name = "MAGNITUD")
    private BigDecimal magnitud;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "ID_META_PROD_IND", referencedColumnName = "ID_META_PROD_IND")
    @ManyToOne
    private PddMpIndicador idMetaProdInd;

}
