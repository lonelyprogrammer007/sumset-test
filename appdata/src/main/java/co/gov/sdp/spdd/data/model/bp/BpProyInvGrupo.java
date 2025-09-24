package co.gov.sdp.spdd.data.model.bp;

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

@Entity
@Table(name = "SPDD_BP_PROY_INV_GRUPO")
@Data
public class BpProyInvGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_PROY")
    private Long idGrupoProy;
    @Basic(optional = false)
    @Column(name = "ID_LS_GRUPO")
    private int idLsGrupo;
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private int numero;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne
    private BpProyectoInversion idProyInversion;
}