/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.sdp.nhspdd.common.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *
 * @author USER
 */
@Data
public class PermisoRolEventoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoUsuario;
	private String usuario;
	private String correo;
	private String identificacion;
	private String nombreUsuario;
	private String codigoRol;
	private String nombreRol;
	private String codigoAplicacion;
	private String nombreAplicacion;
	private List<PermisoDTO> listaPermisos;

}
