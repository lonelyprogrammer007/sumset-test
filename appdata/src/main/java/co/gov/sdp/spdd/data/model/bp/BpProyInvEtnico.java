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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_ETNICO")
@Data
public class BpProyInvEtnico implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ETNICO")
    private Long idEtnico;
	@Basic(optional = false)
    @Column(name = "NUMERO")
    private Long numero;
	@Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
//	@JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
//    @ManyToOne(optional = false)
//    private BpProyectoInversion idProyInversion;
	@JoinColumn(name = "ID_LS_ETNICO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEtnico;
	@JoinColumn(name = "ID_POBLACION", referencedColumnName = "ID_POBLACION")
    @ManyToOne(optional = false)
    private BpProyInvPoblacion idPoblacion;
}
