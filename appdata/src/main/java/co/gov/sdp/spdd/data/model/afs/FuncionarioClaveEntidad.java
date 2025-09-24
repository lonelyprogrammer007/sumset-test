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
@Table(name = "SPDD_FUNCIONARIO_CLAVE_ENT")
public class FuncionarioClaveEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FUNCIONARIO_ENTIDAD")
    private Long idFuncionarioEntidad;
    
    @JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
    @ManyToOne(optional = false)
    private Entidad codigoEntidad;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @JoinColumn(name = "ID_LS_FUNCION", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsFuncion;
    
    @JoinColumn(name = "ID_LS_GENERO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne
    private ArgumentoListaSimple idLsGenero;
    
    @Column(name = "CARGO")
    private String cargo;
    
    @Column(name = "CORREO")
    private String correo;
    
}
