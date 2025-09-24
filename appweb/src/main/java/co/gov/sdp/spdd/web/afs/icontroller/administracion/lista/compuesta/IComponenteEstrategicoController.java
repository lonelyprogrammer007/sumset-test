package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;

public interface IComponenteEstrategicoController {

	public String consultarCompromisoEstrategico(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);
	

	public String crearCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategico,Model model, RedirectAttributes redirectAttributes);
	
	public String editarCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategico,Model model, RedirectAttributes redirectAttributes);
}
