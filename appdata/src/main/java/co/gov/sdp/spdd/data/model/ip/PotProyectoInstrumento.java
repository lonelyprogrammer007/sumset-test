package co.gov.sdp.spdd.data.model.ip;

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
@Table(name = "SPDD_POT_PROYECTO_INSTRUMENTO")
public class PotProyectoInstrumento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROYECTO_INSTRUMENTO")
    private Long idProyectoInstrumento;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    
    @JoinColumn(name = "ID_POT_INSTRUMENTO", referencedColumnName = "ID_INSTRUMENTO_POT")
    @ManyToOne(optional = false)
    private PotInstrumento idPotInstrumento;
    
    @JoinColumn(name = "ID_POT_PROYECTO", referencedColumnName = "ID_OBRA_PROY_POT")
    @ManyToOne(optional = false)
    private PotObra idPotProyecto;

    
}
