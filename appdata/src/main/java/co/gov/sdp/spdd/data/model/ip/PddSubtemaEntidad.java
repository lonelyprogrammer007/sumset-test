package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_SUBTEMA_ENTIDAD")
public class PddSubtemaEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SUBTEMA_ENTIDAD")
    private Long idSubtemaEntidad;
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne
    private Entidad codigoEntidad;
    @JoinColumn(name = "ID_SUBTEMA", referencedColumnName = "ID_SUBTEMA")
    @ManyToOne
    private PddSubtemaTransversal idSubtema;

}
