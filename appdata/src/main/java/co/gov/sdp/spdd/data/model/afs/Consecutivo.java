package co.gov.sdp.spdd.data.model.afs;

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

import co.gov.sdp.spdd.data.model.ip.Pdd;
import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_CONSECUTIVO")
public class Consecutivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSECUTIVO")
    private Long idConsecutivo;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "SECUENCIA")
    private Long secuencia;
    
    @Basic(optional = false)
    @Column(name = "VIGENCIA")
    private String vigencia;
    
	@JoinColumn(name = "CODIGO_ENTIDAD", referencedColumnName = "CODIGO_ENTIDAD")
	@ManyToOne(optional = false)
    private Entidad codigoEntidad;
    
    @JoinColumn(name = "ID_PLAN_DESARROLLO", referencedColumnName = "ID_PLAN_DESARROLLO")
    @ManyToOne
    private Pdd idPlanDesarrollo;

}
