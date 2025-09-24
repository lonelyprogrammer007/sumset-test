package co.gov.sdp.spdd.web.afs.icontroller.administracion.usuario;

import org.springframework.ui.Model;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;


public interface IAdministracionUsuariosController {

	public String gestionUsuarios(Model model, UsuariosSeguridadDTO user);

	public String editarGestionUsuario(Model model, UsuariosSeguridadDTO usuarioSesion);


	public String asignarProyectoInversion(Model model, ProyectosInversionUsuarioDTO inversion,
			UsuariosSeguridadDTO usuarioSesion);


	public String asignarComponentesGestion(Model model, ComponenteGestionUsuarioDTO componente);


	public String asignarEntidades(Model model, UsuarioEntidadDTO user);
		
	
}
