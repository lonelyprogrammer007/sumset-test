package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

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
@Table(name = "SPDD_HIS_V_PDD_META")
public class HisVPddMeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_META")
    private Long idMeta;
    @Basic(optional = false)
    @Column(name = "META")
    private String meta;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_META_LS")
    private int idTipoMetaLs;
    @Column(name = "MAGNITUD")
    private String magnitud;
    @JoinColumn(name = "ID_ESPECIFICO", referencedColumnName = "ID_ESPECIFICO")
    @ManyToOne(optional = false)
    private HisVPddCompromisoEspecifico idEspecifico;

}
