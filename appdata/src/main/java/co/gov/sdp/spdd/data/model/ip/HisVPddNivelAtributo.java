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
@Table(name = "SPDD_HIS_V_PDD_NIVEL_ATRIBUTO")
public class HisVPddNivelAtributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ATRIBUTO")
    private Long idAtributo;
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @Column(name = "DENOMINACION")
    private String denominacion;
    @Basic(optional = false)
    @Column(name = "PONDERACION")
    private BigDecimal ponderacion;
    @Column(name = "NOMBRE_GERENTE")
    private String nombreGerente;
    @Column(name = "CORREO_GERENTE")
    private String correoGerente;
    @Column(name = "ID_LS_GENERO_GERENTE")
    private Integer idLsGeneroGerente;
    @Column(name = "PROYECTO_ESTRATEGICO")
    private Short proyectoEstrategico;
    @JoinColumn(name = "ID_PDD_NIVEL", referencedColumnName = "ID_PDD_NIVEL")
    @ManyToOne
    private HisVPddNivel idPddNivel;
    @JoinColumn(name = "ID_ATRIBUTO_PADRE", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne
    private HisVPddNivelAtributo idAtributoPadre;

}
