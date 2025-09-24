package co.gov.sdp.spdd.web.afs.controller.administracion.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.UsuariosSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.usuario.IAdministracionUsuariosController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "usuario" })
public class AdministracionUsuariosController implements IAdministracionUsuariosController {

	@Autowired
	private MetodosRest<GenericoDTO> dto;

	@Autowired
	private MetodosRest<UsuarioEntidadDTO> usuarioRest;

	@Autowired
	private MetodosRest<EntidadDTO> entidadRest;

	@Autowired
	private MetodosRest<ProyectosInversionUsuarioDTO> proyectoRest;

	@Autowired
	private MetodosRest<ComponenteGestionUsuarioDTO> componenteRest;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;

	public static final String REDIRECT_EDITAR_GESTION = "redirect:/editar-gestion";

	@PostMapping("/gestion-usuario")
	@Override
	public String gestionUsuarios(Model model, UsuariosSeguridadDTO user) {

		EntidadDTO entidad = entidadRest.get(
				"/administracion/obtener_por_id/" + user.getSegEntidad().getCodigoDistrital(),
				new ParameterizedTypeReference<EntidadDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		user.getSegEntidad().setDelegar(entidad.getGestionProyectos());

		model.addAttribute("usuario", user);

		return REDIRECT_EDITAR_GESTION;

	}

	@GetMapping("/editar-gestion")
	@Override
	public String editarGestionUsuario(Model model, @ModelAttribute("usuario") UsuariosSeguridadDTO usuarioSesion) {

		RespuestaApiDTO<EntidadSeguridadDTO> res = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);
		String parametro = "";
		UsuariosDTO usuario = new UsuariosDTO();

		usuario.setNombreUsuario(usuarioSesion.getUsuario());
		GenericoDTO res1 = dto.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_DISPONIBLES,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO res2 = dto.get(NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTE_LIBRE,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		try {
			parametro = mapper.writeValueAsString(usuario);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, usuario.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AdministracionUsuariosController.class);
		}

		GenericoDTO entidadAsignada = dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_ASIGNADOS, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("entidadAsignada", entidadAsignada.getLstObjectsDTO());

		GenericoDTO res3 = dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTES_USUARIO, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO res4 = dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_ASIGNADOS,
				NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("proyectoInversionAct", res4.getLstObjectsDTO());
		model.addAttribute("componenteGestionDisponible", res2.getLstObjectsDTO());
		model.addAttribute("componenteGestionAsignado", res3.getLstObjectsDTO());
		model.addAttribute("proyectoInversion", res1.getLstObjectsDTO());
		model.addAttribute("entidades", res.getObjetos());
		return "afs/admin-usuarios/gestion-usuarios";

	}

	@GetMapping("/admin-usuario")
	public String administracionUsuarios(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String parametro = "";
		UsuariosDTO usuario = new UsuariosDTO();
		String retorno = "redirect:/home";

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null) {

				if (request.getRequestURI().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {

					usuario.setToken(tokenSesionSeguridad);

					usuario.setNombreUsuario(usuarioSesion);

					dto.agregarToken(tokenSesionSeguridad);
					api.agregarToken(tokenSesionSeguridad);
					entidadRest.agregarToken(tokenSesionSeguridad);
					usuarioRest.agregarToken(tokenSesionSeguridad);
					componenteRest.agregarToken(tokenSesionSeguridad);
					proyectoRest.agregarToken(tokenSesionSeguridad);

					try {
						parametro = mapper.writeValueAsString(usuario);

					} catch (JsonProcessingException e) {

						spddLogger.mensajeLogger(
								NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
										usuario.getLenguaje()),
								NHSPDDConstantes.SEVERIDAD_FATAL, AdministracionUsuariosController.class);

					}

					GenericoDTO res = dto.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
					}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CONSULTAR_USUARIOS,
							NHSPDDConstantes.TIPO_CORE);

					model.addAttribute("list3", res.getLstObjectsDTO());
					retorno = "afs/admin-usuarios/adm-usuarios";
				}
				if ("/asignarComponentes".equals(respuestaAutenticacion.getListaPermisos().get(i).getUrl())) {

					redirectAttributes.addFlashAttribute("tabComponente", 1);
				} else {
					redirectAttributes.addFlashAttribute("tabComponente", "0");
				}

			}
		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return retorno;
	}

	@PostMapping("/asignarProyectos")
	@Override
	public String asignarProyectoInversion(Model model, ProyectosInversionUsuarioDTO inversion,
			@ModelAttribute("usuario") UsuariosSeguridadDTO usuarioSesion) {

		String parametro = "";
		inversion.setCodigoUsuario(usuarioSesion.getUsuario());
		inversion.setIdProyectoInversion(-1L);
		try {
			parametro = mapper.writeValueAsString(inversion);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, inversion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AdministracionUsuariosController.class);
		}

		proyectoRest.post(parametro, new ParameterizedTypeReference<ProyectosInversionUsuarioDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE);

		return REDIRECT_EDITAR_GESTION;
	}

	@PostMapping("/asignarComponentes")
	@Override
	public String asignarComponentesGestion(Model model, ComponenteGestionUsuarioDTO componente) {

		String parametro = "";
		componente.setIdComponenteGestion(-1L);
		try {
			parametro = mapper.writeValueAsString(componente);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
							componente.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AdministracionUsuariosController.class);
		}
		componenteRest.post(parametro, new ParameterizedTypeReference<ComponenteGestionUsuarioDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_POST_ASIGNAR_COMPONENTE_GESTION_USUARIO, NHSPDDConstantes.TIPO_CORE);

		return REDIRECT_EDITAR_GESTION;
	}

	@PostMapping("/asignarEntidades")
	@Override
	public String asignarEntidades(Model model, UsuarioEntidadDTO user) {

		try {
			String parametro = mapper.writeValueAsString(user);

			usuarioRest.post(parametro, new ParameterizedTypeReference<UsuarioEntidadDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_ENTIDAD, NHSPDDConstantes.TIPO_CORE);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, user.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AdministracionUsuariosController.class);
		}

		return REDIRECT_EDITAR_GESTION;
	}
}
