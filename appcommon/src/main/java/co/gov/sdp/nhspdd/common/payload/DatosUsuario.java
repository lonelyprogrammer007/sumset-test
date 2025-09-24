package co.gov.sdp.nhspdd.common.payload;

import java.io.Serializable;

import lombok.Data;

/**
 * Est clase es encargada de recibir los datos del servicio rest de usuarios
 *
 * @author mtovar
 */
@Data
public class DatosUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String usuario;
	private String nombre;
	private String tipoUsuario;
	private Integer estado;
	private String correoElectronico;
	private String telefono;
	private String codigoAplicacion;
	private String codigoEntidad;
	private String[] roles;

	public DatosUsuario() {
		super();
	}

}
