package co.gov.sdp.spdd.data.model.ip;

import java.io.Serializable;
import java.math.BigDecimal;

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
import co.gov.sdp.spdd.data.model.afs.Entidad;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_MP_INDICADOR_ENTIDAD")
public class PddMpIndicadorEntidad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MP_IND_ENTIDAD")
	private Long idMpIndEntidad;
	@Column(name = "MAGNITUD")
	private BigDecimal magnitud;
	@Column(name = "PONDERACION")
	private BigDecimal ponderacion;
	@JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
	@ManyToOne
	private Entidad codigoEntidad;
	@JoinColumn(name = "ID_META_PROD_IND", referencedColumnName = "ID_META_PROD_IND")
	@ManyToOne
	private PddMpIndicador idMetaProdInd;

	@JoinColumn(name = "ID_LS_TIPO_ANUAL", referencedColumnName = "ID_ARGUMENTO")
	@ManyToOne
	private ArgumentoListaSimple idLsTipoAnualizacion;
}
