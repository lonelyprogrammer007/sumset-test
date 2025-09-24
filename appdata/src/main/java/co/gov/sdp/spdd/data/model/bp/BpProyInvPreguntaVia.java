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
@Table(name = "SPDD_BP_PROY_INV_PREGUNTA_VIA")
@Data
public class BpProyInvPreguntaVia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PREGUNTA_ESTADO")
    private Long idPreguntaEstado;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    @JoinColumn(name = "ID_PREGUNTA_VIAB", referencedColumnName = "ID_PREGUNTA_VIAB")
    @ManyToOne
    private BpPreguntasViabilidad idPreguntaViab;
    @JoinColumn(name = "ID_PROY_INVERSION", referencedColumnName = "ID_PROY_INVERSION")
    @ManyToOne
    private BpProyectoInversion idProyInversion;
  
}