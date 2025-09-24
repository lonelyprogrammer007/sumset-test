package co.gov.sdp.spdd.web.bp.controller.versionamiento;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion" })
public class VersionamientoController {

	
	
	@PostMapping("/consultar-versionamiento")
	public String consultarVersionamiento(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {
		
		return "redirect:/versionamiento";
	}
	
	@GetMapping("/versionamiento")
	public String versionamiento() {
		return "bp/versionamiento/versionamiento-proyecto";
	}
	
	
	
}
