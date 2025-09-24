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

import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;
import lombok.Data;

@Entity
@Table(name = "SPDD_BP_PROY_INV_META_PLAN")
@Data
public class BpProyInvMetaPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_PROY_META_PLAN")
	private Long idProyMetaPlan;
	
	@JoinColumn(name = "ID_PROY_OBJ_ESPECIF", referencedColumnName = "ID_PROY_OBJ_ESPECIF")
	@ManyToOne(optional = false)
	private BpProyInvEspecif idProyObjEspecif;
	
	@JoinColumn(name = "ID_META_PRODUCTO", referencedColumnName = "ID_META_PRODUCTO")
	@ManyToOne(optional = false)
	private PddMetaProducto idMetaProducto;

}