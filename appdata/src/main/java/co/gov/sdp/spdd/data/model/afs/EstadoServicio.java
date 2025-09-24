package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_ESTADO_SERVICIO")
public class EstadoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_SERVICIO")
    private Long idEstadoServicio;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_SERVICIO")
    private String nombreServicio;
    
    @Basic(optional = false)
    @Column(name = "TAREA")
    private String tarea;
    
    @Basic(optional = false)
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    
    @Column(name = "ESTADO_SOLICITUD")
    private String estadoSolicitud;
    
    @Column(name = "FECHA_RESPUESTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

}
