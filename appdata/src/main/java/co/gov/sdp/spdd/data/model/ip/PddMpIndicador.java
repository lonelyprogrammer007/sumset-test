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
@Table(name = "SPDD_PDD_MP_INDICADOR")
public class PddMpIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_META_PROD_IND")
    private Long idMetaProdInd;
    
    @Column(name = "ES_PDD")
    private Short esPdd;
    
    @Column(name="SUM_POND")
    private Long sumPond;
    
    @JoinColumn(name = "ID_INDICADOR", referencedColumnName = "ID_INDICADOR")
    @ManyToOne(optional = false)
    private PddIndicador idIndicador;
    
    @JoinColumn(name = "ID_META_PRODUCTO", referencedColumnName = "ID_META_PRODUCTO")
    @ManyToOne(optional = false)
    private PddMetaProducto idMetaProducto;

}
