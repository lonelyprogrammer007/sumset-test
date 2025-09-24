package co.gov.sdp.spdd.data.model.bp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_VERSION")
@Data
public class BpProyInvVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VERSION")
    private Long idVersion;
    @Basic(optional = false)
    @Column(name = "VERSION")
    private int version;
    @Basic(optional = false)
    @Column(name = "FECHA_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVersion;
    @Basic(optional = false)
    @Lob
    @Column(name = "FICHA_EBI")
    private String fichaEbi;
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne
    private BpProyectoInversion idProyInversion;
    
}