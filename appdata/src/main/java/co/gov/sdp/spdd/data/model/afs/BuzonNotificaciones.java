package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_BUZON_NOTIFICACIONES")
public class BuzonNotificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTIFICACION")
    private Long idNotificacion;
    
    @Basic(optional = false)
    @Column(name = "MENSAJE")
    private String mensaje;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO_DESTINO")
    private String codigoUsuarioDestino;
    
    @Basic(optional = false)
    @Column(name = "CODIGO_USUARIO_ORIGINA")
    private String codigoUsuarioOrigina;
    
    @Column(name = "RESPUESTA")
    private String respuesta;
    
    @Basic(optional = false)
    @Column(name = "TIPO_MENSAJE")
    private short tipoMensaje;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ESCRITURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEscritura;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private int estado;
    
    @Basic(optional = false)
    @Column(name = "ASUNTO")
    private String asunto;
    
    @Column(name = "FECHA_LECTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLectura;
    
    @Column(name = "FECHA_RESPUESTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    
    @JoinColumn(name = "ID_CONFIG_NOTIFICACION", referencedColumnName = "ID_CONFIG_NOTIFICACION")
    @ManyToOne
    private ConfiguracionNotificacion idConfigNotificacion;

}
