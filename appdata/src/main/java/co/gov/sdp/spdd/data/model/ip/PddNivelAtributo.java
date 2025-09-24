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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_NIVEL_ATRIBUTO")
public class PddNivelAtributo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATRIBUTO")
    private Long idAtributo;
    
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private Long numero;
    
    @Basic(optional = false)
    @Column(name = "DENOMINACION")
    private String denominacion;
    
    @Basic(optional = false)
    @Column(name = "PONDERACION")
    private String ponderacion;
    
    @Column(name = "NOMBRE_GERENTE")
    private String nombreGerente;
    
    @Column(name = "CORREO_GERENTE")
    private String correoGerente;
    
    @Column(name = "PROYECTO_ESTRATEGICO")
    private Long proyectoEstrategico;
    
    @Column(name="SUM_POND")
    private Long sumPond;
    
    @JoinColumn(name = "ID_LS_GENERO_GERENTE", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsGeneroGerente;
    
    @JoinColumn(name = "ID_PDD_NIVEL", referencedColumnName = "ID_PDD_NIVEL")
    @ManyToOne
    private PddNivel idPddNivel;
    
    @JoinColumn(name = "ID_ATRIBUTO_PADRE", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne
    private PddNivelAtributo idAtributoPadre;

}
