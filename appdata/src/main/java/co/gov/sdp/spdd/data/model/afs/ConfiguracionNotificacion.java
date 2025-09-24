package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Raul Londono Murillo
 */
@Data
@Entity
@Table(name = "SPDD_CONFIG_NOTIFICACION")
public class ConfiguracionNotificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONFIG_NOTIFICACION")
    private Long idConfigNotificacion;
    @Basic(optional = false)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Basic(optional = false)
    @Column(name = "ASUNTO")
    private String asunto;
    @Basic(optional = false)
    @Column(name = "REQUIERE_ACCION")
    private short requiereAccion;
    @Basic(optional = false)
    @Column(name = "OPERACION_ORIGEN")
    private String operacionOrigen;
    @Basic(optional = false)
    @Column(name = "ENVIAR_CORREO")
    private short enviarCorreo;
    @Basic(optional = false)
    @Column(name = "MODULO_ORIGEN")
    private String moduloOrigen;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Long estado;
    

  
}
