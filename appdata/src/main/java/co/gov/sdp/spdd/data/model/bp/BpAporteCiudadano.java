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

import co.gov.sdp.spdd.data.model.afs.ArchivoProcesado;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_APORTE_CIUDADANO")
@Data
public class BpAporteCiudadano implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_APORTE")
	private Long idAporte;
	
	@Basic(optional = false)
	@Column(name = "CONSECUTIVO")
	private Long consecutivo;
	
	@Basic(optional = false)
	@Column(name = "ACCION")
	private String accion;
	
	@Basic(optional = false)
	@Column(name = "FUENTE")
	private String fuente;
	
	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO")
	@ManyToOne(optional = false)
	private ArchivoProcesado idArchivo;
	
	@JoinColumn(name = "ID_NIVEL_PDD", referencedColumnName = "ID_ATRIBUTO")
	@ManyToOne(optional = false)
	private PddNivelAtributo idNivelAtributoPdd;
}
