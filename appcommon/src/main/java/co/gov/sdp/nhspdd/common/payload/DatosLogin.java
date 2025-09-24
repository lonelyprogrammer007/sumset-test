package co.gov.sdp.nhspdd.common.payload;

import lombok.Data;

/**
 * Clase que recibe las credenciales de acceso para autenticación
 *
 * @author mtovar
 *
 */
@Data
public class DatosLogin {

	private String nombreUsuario;
	private String contrasenia;
	private String aplicacion;
	private String tipoUsuario;

	public DatosLogin() {
		super();
	}

}
