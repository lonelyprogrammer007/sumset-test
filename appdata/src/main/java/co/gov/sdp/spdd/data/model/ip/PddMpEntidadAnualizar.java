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

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PDD_MP_ENTIDAD_ANUALIZAR")
public class PddMpEntidadAnualizar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MP_ENT_ANUALIZA")
	private Long idMpEntAnualiza;
	@Basic(optional = false)
	@Column(name = "VIGENCIA")
	private String vigencia;
	@Column(name = "MAGNITUD")
	private BigDecimal magnitud;
	@Column(name = "VALOR")
	private BigDecimal valor;
	@JoinColumn(name = "ID_MP_ENTIDAD", referencedColumnName = "ID_MP_ENTIDAD")
	@ManyToOne(optional = false)
	private PddMpEntidad idMpEntidad;

}
