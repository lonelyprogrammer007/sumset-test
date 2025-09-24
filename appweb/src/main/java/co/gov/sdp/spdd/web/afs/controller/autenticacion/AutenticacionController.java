package co.gov.sdp.spdd.web.afs.controller.autenticacion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.DatosToken;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.payload.DatosLogin;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.autenticacion.IAutenticacionController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class AutenticacionController implements IAutenticacionController {

	@Autowired
	MetodosRest<RespuestaApiDTO<DatosToken>> api;

	@Autowired
	MetodosRest<UsuariosDTO> apiUsuarios;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	SPDDLogger spddLogger;

	@GetMapping(NHSPDDConstantes.WEB_CONTROLLER_REQUEST_INDEX)
	@Override
	public String index(Model model) {

		return NHSPDDConstantes.WEB_CONTROLLER_RETURN_INDEX;
	}

	@GetMapping(NHSPDDConstantes.WEB_CONTROLLER_GET_RESTABLECER_CONTRASENA)
	@Override
	public String restablecer() {
		return "afs/autenticacion/restablecer-contrasena";
	}

	@GetMapping(NHSPDDConstantes.WEB_CONTROLLER_GET_HOME)
	@Override
	public String home(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {

		model.addAttribute("prohibido", 0);

		apiUsuarios.agregarToken(tokenSesionSeguridad);

		return NHSPDDConstantes.WEB_CONTROLLER_RETURN_HOME;
	}

	@PostMapping("/iniciar-sesion")
	@Override
	public String ingreso(Model model, DatosLogin datos, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String retorno = "index";
		model.addAttribute("datos", new DatosLogin());
		datos.setAplicacion("SPDD");
		String parametros = "";
		try {
			parametros = mapper.writeValueAsString(datos);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, datos.getAplicacion()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AutenticacionController.class);
		}
		RespuestaApiDTO<DatosToken> respuesta = api.post(parametros,
				new ParameterizedTypeReference<RespuestaApiDTO<DatosToken>>() {
				}, "/api/autenticacion/autenticar", NHSPDDConstantes.TIPO_SEGURIDAD);

		if (Boolean.FALSE.equals(respuesta.getExito())) {

			model.addAttribute("mensaje", respuesta.getMensaje());
			model.addAttribute("modal", 1);
			return retorno;
		} else {
			List<DatosToken> dt = respuesta.getObjetos();
			PermisoRolEventoDto respuestaAutenticacion = dt.get(0).getPermisos();

			for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
				if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
						&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					if ("I".equals(datos.getTipoUsuario())) {
						model.addAttribute("codigoEntidadUsuario", "Interno");
					} else {
						model.addAttribute("codigoEntidadUsuario", dt.get(0).getCodigoEntidad());
					}

					model.addAttribute("nombreUsuarioSesion", dt.get(0).getNombreUsuario());
					model.addAttribute("tokenSesionSeguridad", dt.get(0).getToken());

					model.addAttribute("usuarioSesion", dt.get(0).getPermisos().getUsuario());

					model.addAttribute("respuestaAutenticacion", dt.get(0).getPermisos());
					retorno = "redirect:/home";

				}

			}
		}
		return retorno;

	}

	@PostMapping("/restablecer_contrasenia")
	@Override
	public String restablecerContrasenia(Model model, UsuariosDTO user, RedirectAttributes redirectAttributes) {

		String parametros = "";
		try {
			parametros = mapper.writeValueAsString(user);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, user.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AutenticacionController.class);

		}
		UsuariosDTO respuesta = apiUsuarios.post(parametros, new ParameterizedTypeReference<UsuariosDTO>() {
		}, "/core/autenticacion/" + NHSPDDConstantes.CORE_CONTROLLER_GET_ENVIAR_CORREO, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageRestablecer", respuesta.getMsgRespuesta());
		return "redirect:/";
	}

	@PostMapping("/cambiar_contrasenia")
	@Override
	public String cambiarContrasenia(Model model, UsuariosDTO user, RedirectAttributes redirectAttributes) {
		user.setAplicacion("SPDD");
		String parametros = "";
		try {
			parametros = mapper.writeValueAsString(user);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, user.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AutenticacionController.class);
		}
		UsuariosDTO respuesta = apiUsuarios.post(parametros, new ParameterizedTypeReference<UsuariosDTO>() {
		}, "/core/autenticacion/" + NHSPDDConstantes.CORE_CONTROLLER_AUTENTICACION_POST_CAMBIAR_CLAVE,
				NHSPDDConstantes.TIPO_CORE);

		if (respuesta.getCodigo() != 0) {
			System.out.println("enttro");
			redirectAttributes.addFlashAttribute("mensaje", respuesta.getMsgRespuesta());
			redirectAttributes.addFlashAttribute("modal", 1);

			return "redirect:/home";

		}
		return "redirect:/cerrar-sesion";
	}

	@PostMapping("/cambiar_contrasenia_primera")
	public String cambiarContraseniaPrimeraVez(Model model, UsuariosDTO user, RedirectAttributes redirectAttributes) {
		user.setAplicacion("SPDD");
		String parametros = "";
		try {
			parametros = mapper.writeValueAsString(user);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, user.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AutenticacionController.class);
		}
		UsuariosDTO respuesta = apiUsuarios.post(parametros, new ParameterizedTypeReference<UsuariosDTO>() {
		}, "/core/autenticacion/" + NHSPDDConstantes.CORE_CONTROLLER_AUTENTICACION_POST_CAMBIAR_CLAVE,
				NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("mensaje", respuesta.getMsgRespuesta());
		redirectAttributes.addFlashAttribute("modal", 1);

		return "redirect:/";
	}

	@GetMapping("/iniciar-sesion")
	@Override
	public String login(Model model) {
		model.addAttribute("mensaje", null);
		return "index";

	}

	@GetMapping("/cerrar-sesion")
	@Override
	public String cerrarSesion(Model model, SessionStatus session) {
		session.setComplete();
		return "redirect:/";
	}

}
