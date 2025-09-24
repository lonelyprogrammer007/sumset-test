package co.gov.sdp.spdd.web.ip.controller.proyecto.formulacion.pdd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
	"nombreUsuarioSesion"})
public class ProyectoFormulacionPddController {

	
	@GetMapping("/ver-programas-formulacion")
	public String verProgramasFormulacion(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {
		
		
		return "ip/proyecto-formulacion-pdd/consultar-proyectos-formulacion.html";
	}
	
	@PostMapping("/obtener-formulacion-pdd")
	public String obtenerFormulacionPdd(Model model,@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,PddDTO pddDTO) {
		model.addAttribute("pddDTO",pddDTO);
		
		return "redirect:/ver-formulacion-pdd";
	}
	
	@GetMapping("ver-formulacion-pdd")
	public String verFormulacionPdd(@ModelAttribute("pddDTO") PddDTO pddDTO) {
		return "ip/proyecto-formulacion-pdd/ver-proyecto-formulacion.html";
	}
}
