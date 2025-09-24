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

import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_MP_ENTIDAD")
public class PddMpEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MP_ENTIDAD")
    private Long idMpEntidad;
    
    @Column(name = "MAGNITUD")
    private Long magnitud;
    @Column(name = "ESTADO")
    private Short estado;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne
    private Entidad codigoEntidad;
    
    @JoinColumn(name = "ID_META_PRODUCTO", referencedColumnName = "ID_META_PRODUCTO")
    @ManyToOne
    private PddMetaProducto idMetaProducto;

}
