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

import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_COMP_GESTION_USUARIO")
public class ComponenteGestionUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GESTION_USUARIO")
    private Long idGestionUsuario;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;
    
    @JoinColumn(name = "ID_COMPONENTE_GESTION", referencedColumnName = "ID_ATRIBUTO")
    @ManyToOne(optional = false)
    private PddNivelAtributo idComponenteGestion;

}
