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
@Table(name = "SPDD_BP_PREGUNTAS_VIABILIDAD")
@Data
public class BpPreguntasViabilidad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_PREGUNTA_VIAB")
	private Integer idPreguntaViab;
	@Basic(optional = false)
	@Column(name = "PREGUNTA")
	private String pregunta;
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private short estado;
	@JoinColumn(name = "ID_LS_DIRIGIDO", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne(optional = false)
	private ArgumentoListaSimple idLsDirigido;
}
