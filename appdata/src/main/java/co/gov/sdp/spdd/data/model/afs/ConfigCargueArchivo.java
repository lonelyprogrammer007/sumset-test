package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;

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

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_CONFIG_CARGUE_ARCHIVO")
public class ConfigCargueArchivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONFIG_CARGUE")
    private Long idConfigCargue;

    @Basic(optional = false)
    @Lob
    @Column(name = "CONFIGURACION")
    private String configuracion;
    
    @JoinColumn(name = "ID_LS_TEMA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsTema;
    
    @JoinColumn(name = "ID_LS_MODULO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = true)
    private ArgumentoListaSimple idLsModulo;

}
