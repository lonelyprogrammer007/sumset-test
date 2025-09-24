package co.gov.sdp.spdd.web.afs.icontroller.autenticacion;


import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.payload.DatosLogin;


public interface IAutenticacionController {

	public String index(Model model);

	public String restablecer();

	public String home(Model model, String tokenSesionSeguridad, String usuarioSesion, String codigoEntidadUsuario,
			PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion);

	public String ingreso(Model model, DatosLogin datos, HttpServletRequest request,
			RedirectAttributes redirectAttributes);

	public String restablecerContrasenia(Model model, UsuariosDTO user, RedirectAttributes redirectAttributes);

	public String cambiarContrasenia(Model model, UsuariosDTO user,
			RedirectAttributes redirectAttributes);

	public String login(Model model);

	public String cerrarSesion(Model model, SessionStatus session);
}
