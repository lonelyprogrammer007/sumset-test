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
@Table(name = "SPDD_ARGUMENTO_LISTA_SIMPLE")
public class ArgumentoListaSimple implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "ARGUMENTO")
    private String argumento;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARGUMENTO")
    private Long idArgumento;
    
    @Basic(optional = false)
    @Column(name = "RESULTADO")
    private String resultado;
    
    @JoinColumn(name = "ID_LISTA_SIMPLE", referencedColumnName = "ID_LISTA_SIMPLE")
    @ManyToOne(optional = false)
    private ListaSimple idListaSimple;

}
