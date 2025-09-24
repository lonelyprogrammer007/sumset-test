package co.gov.sdp.spdd.data.model.pa;

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

import co.gov.sdp.spdd.data.model.afs.Equipamento;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_EQUIPAMENTO")
public class PaActUbiEquipamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UBI_EQUIP")
    private Long idUbiEquip;
    
    @JoinColumn(name = "ID_EQUIPAMENTO", referencedColumnName = "ID_EQUIPAMENTO")
    @ManyToOne(optional = false)
    private Equipamento idEquipamento;
    
    @JoinColumn(name = "ID_PA_UBICACION", referencedColumnName = "ID_PA_UBICACION")
    @ManyToOne
    private PaActUbicacion idPaUbicacion;
    
}
