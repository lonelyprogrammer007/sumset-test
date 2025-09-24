package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_HIS_V_PDD_INDICADOR")
public class HisVPddIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_INDICADOR")
    private Long idIndicador;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "LINEA_BASE_DESC")
    private String lineaBaseDesc;
    @Basic(optional = false)
    @Column(name = "FUENTE")
    private String fuente;
    @Basic(optional = false)
    @Column(name = "YEAR_LINEA_BASE")
    private String yearLineaBase;
    @Basic(optional = false)
    @Column(name = "INFORMACION_SOPORTE")
    private String informacionSoporte;
    @Column(name = "LINEA_BASE")
    private Short lineaBase;
    @Column(name = "MAGNITUD_LB")
    private BigDecimal magnitudLb;
    @Column(name = "MAGNITUD")
    private BigDecimal magnitud;
    @Column(name = "PERIODICIDAD")
    private String periodicidad;
    @Column(name = "ID_LS_ESTADO")
    private Integer idLsEstado;
    @Column(name = "FUENTE_EXTERNA")
    private Short fuenteExterna;
    @Column(name = "ID_ARCHIVO")
    private Long idArchivo;

}
