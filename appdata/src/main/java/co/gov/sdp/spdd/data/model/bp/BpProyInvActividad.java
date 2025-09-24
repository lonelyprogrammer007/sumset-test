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
@Table(name = "SPDD_BP_PROY_INV_ACTIVIDAD")
@Data
public class BpProyInvActividad implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private Long idActividad;
    
    @Basic(optional = false)
    @Column(name = "CONSECUTIVO")
    private long consecutivo;
    
    @Basic(optional = false)
    @Column(name = "MAGNITUD")
    private Long magnitud;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "LINEA_BASE")
    private String lineaBase;
    
    @Column(name = "JUSTIFICACION")
    private String justificacion;
    
    @JoinColumn(name = "ID_LS_PROCESO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsProceso;
    
    @JoinColumn(name = "ID_LS_ESTADO", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsEstado;
    
    @JoinColumn(name = "ID_LS_UNIDAD_MEDIDA", referencedColumnName = "ID_ARGUMENTO")
    @ManyToOne(optional = false)
    private ArgumentoListaSimple idLsUnidadMedida;
    
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
    private BpProyInvProducto idProducto;
}
