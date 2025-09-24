package co.gov.sdp.spdd.data.model.afs;

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
@Table(name = "SPDD_COMPONENTE_GASTO")
public class ComponenteGasto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPONENTE_GASTO")
    private Long idComponenteGasto;

    @Basic(optional = false)
    @Column(name = "CODIGO_COMPONENTE")
    private Long codigoComponente;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_COMPONENTE")
    private String nombreComponente;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @JoinColumn(name = "ID_LS_TIPO_PROYECTO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsTipoProyecto;

}
