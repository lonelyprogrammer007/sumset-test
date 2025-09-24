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

import co.gov.sdp.spdd.data.model.afs.ArgumentoListaSimple;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_META")
public class PddMeta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_META")
	private Long idMeta;
	@Basic(optional = false)
	@Column(name = "META")
	private String meta;

	@JoinColumn(name = "ID_TIPO_META_LS", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne
	private ArgumentoListaSimple idTipoMetaLs;
	@Column(name = "MAGNITUD")
	private Long magnitud;
	@JoinColumn(name = "ID_ESPECIFICO", referencedColumnName = "ID_ESPECIFICO")
	@ManyToOne(optional = false)
	private PddCompromisoEspecifico idEspecifico;

}
