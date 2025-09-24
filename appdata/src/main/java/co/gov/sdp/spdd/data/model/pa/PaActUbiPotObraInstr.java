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

import co.gov.sdp.spdd.data.model.ip.PotProyectoInstrumento;
import lombok.Data;

/**
 *
 * @author DANIEL
 */
@Data
@Entity
@Table(name = "SPDD_PA_ACT_UBI_POT_OBRA_INSTR")
public class PaActUbiPotObraInstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_POT_INSTR")
    private Long idPotInstr;
    
    @JoinColumn(name = "ID_UBI_OBRA", referencedColumnName = "ID_UBI_OBRA")
    @ManyToOne(optional = false)
    private PaActUbiPotObra idUbiObra;
    
    @JoinColumn(name = "ID_PROYECTO_INSTRUMENTO", referencedColumnName = "ID_PROYECTO_INSTRUMENTO")
    @ManyToOne(optional = false)
    private PotProyectoInstrumento idProyectoInstrumento;
}
