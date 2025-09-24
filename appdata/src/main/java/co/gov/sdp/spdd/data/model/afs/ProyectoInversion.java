package co.gov.sdp.spdd.data.model.afs;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SPDD_PROYECTO_INVERSION")
public class ProyectoInversion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    
    @Column(name = "ID_PROYECTO_INVERSION")
    private Long idProyectoInversion;
    
    @Column(name = "NOMBRE")
    private String nombre;

}
