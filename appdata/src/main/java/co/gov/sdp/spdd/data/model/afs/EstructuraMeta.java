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
@Table(name = "SPDD_ESTRUCTURA_META")
public class EstructuraMeta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTRUCTURA_METAS")
    private Long idEstructuraMetas;

    @Basic(optional = false)
    @Column(name = "TIPO")
    private short tipo;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @JoinColumn(name = "ID_LS_UNIDAD_MEDIDA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsUnidadMedida;
    
    @JoinColumn(name = "ID_LS_VERBO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsVerbo;

}
