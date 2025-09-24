package co.gov.sdp.spdd.web.afs.controller.buzon.notificaciones;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.RolSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.buzon.notificaciones.IBuzonNotificacionesController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que implementa y controla las vistas y metodos asociados a un buzon
 * notificaciones
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "admin" })
public class BuzonNotificacionesController implements IBuzonNotificacionesController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<BuzonNotificacionesDTO> buzonRest;

	@Autowired
	MetodosRest<RespuestaApiDTO<RolSeguridadDTO>> roles;

	@Autowired
	MetodosRest<ConfiguracionNotificacionDTO> configNotificacion;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	SPDDLogger spddLogger;

	public static final String REDIRECT_CONSULTAR_BUZON = "redirect:/consultar_buzon_todos";

	/**
	 * Implementacion del metodo buzonTodos
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.buzon.notificaciones.IBuzonNotificacionesController,buzonTodos
	 */
	@GetMapping("/consultar_buzon_todos")
	public String buzonTodos(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		RespuestaApiDTO<RolSeguridadDTO> rol = roles.get(
				"/api/sincronizacion/traerrolesporaplicacion?Aplicacion=SPDD&tipoUsuario=I",
				new ParameterizedTypeReference<RespuestaApiDTO<RolSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);

		buzonRest.agregarToken(tokenSesionSeguridad);
		configNotificacion.agregarToken(tokenSesionSeguridad);
		model.addAttribute("roles", rol.getObjetos());

		String retorno = "redirect:/home";
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null) {
				if (request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					model.addAttribute("admin", "");
					buzonRest.get("/leer_informativo/" + usuarioSesion,
							new ParameterizedTypeReference<BuzonNotificacionesDTO>() {
							}, NHSPDDConstantes.TIPO_CORE);
					retorno = "afs/buzon-notificaciones/buzon-todos";
				}
				if ("/crear_mensaje".equals(respuestaAutenticacion.getListaPermisos().get(i).getUrl())) {

					model.addAttribute("boton", 1);
					model.addAttribute("boton2", 1);

				}
			}
		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return retorno;

	}

	/**
	 * Implementacion del metodo buzonAdmin
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.buzon.notificaciones.IBuzonNotificacionesController.buzonAdmin
	 */
	@GetMapping("/consultar_buzon_admin")
	@Override
	public String buzonAdmin(Model model,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			HttpServletRequest request) {

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
					&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
				model.addAttribute("admin", "activo");
				model.addAttribute("boton", 1);
				model.addAttribute("boton2", 0);
				return "afs/buzon-notificaciones/buzon-todos";
			}

		}
		return REDIRECT_CONSULTAR_BUZON;

	}

	/**
	 * Implementacion del metodo plantillaNotificacion
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.buzon.notificaciones.IBuzonNotificacionesController.plantillaNotificacion
	 */
	@GetMapping("/consultar_plantilla_notificacion")
	@Override
	public String plantillaNotificacion(Model model,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		GenericoDTO listaNotificaciones = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_AUTOMATICOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
					&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
				model.addAttribute("listaNotificaciones", listaNotificaciones.getLstObjectsDTO());
				return "afs/buzon-notificaciones/plantilla";

			}
		}

		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	@PostMapping("/editar-plantilla")
	public String editarPlantillaNotificacion(Model model, ConfiguracionNotificacionDTO config,
			RedirectAttributes redirectAttributes) {

		try {
			String parametro = mapper.writeValueAsString(config);
			configNotificacion.post(parametro, new ParameterizedTypeReference<ConfiguracionNotificacionDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_CONFIGURACION_NOTIFICACION_MODIFICAR_CONFIGURACION,
					NHSPDDConstantes.TIPO_CORE);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, config.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionesController.class);
		}
		redirectAttributes.addFlashAttribute("messagePlantilla", "Se ha actualizado la notificaion exitosamente");
		return "redirect:/consultar_plantilla_notificacion";
	}

	/**
	 * Implementacion del metodo responderMensaje
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.buzon.notificaciones.IBuzonNotificacionesController.
	 */
	@PostMapping("/responder_mensaje")
	@Override
	public String responderMensaje(BuzonNotificacionesDTO buzonNotificaciones, Model model) {
		model.addAttribute("buzonNotificaciones", new BuzonNotificacionesDTO());
		buzonRest.put(buzonNotificaciones, BuzonNotificacionesDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_DAR_RESPUESTA, NHSPDDConstantes.TIPO_CORE);
		return REDIRECT_CONSULTAR_BUZON;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaBuzonNotificacionesByPage")
	public ResponseEntity listarBuzonNotificaciones(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req,
			@ModelAttribute("usuarioSesion") String usuarioSesion, @ModelAttribute("admin") String admin) {

		BuzonNotificacionesDTO buzonNotificacionesDTO = new BuzonNotificacionesDTO();

		String search0 = req.getParameter("columns[0][search][value]");

		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");

		String search6 = req.getParameter("columns[6][search][value]");
		String search7 = req.getParameter("columns[7][search][value]");
		String search8 = req.getParameter("columns[8][search][value]");
		String search9 = req.getParameter("columns[9][search][value]");
		String search10 = req.getParameter("columns[10][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		String parametro = "";

		if ("".equals(search0)) {
			buzonNotificacionesDTO.setIdNotificacion(null);
		} else if (search0.matches("[0-9]*")) {
			buzonNotificacionesDTO.setIdNotificacion(Long.parseLong(search0));
		}

		buzonNotificacionesDTO.setAsunto(search3);
		buzonNotificacionesDTO.setMensaje(search4);
		buzonNotificacionesDTO.setRespuesta(search6);
		buzonNotificacionesDTO.setFechaEscritura(search7);
		buzonNotificacionesDTO.setFechaRespuesta(search8);
		buzonNotificacionesDTO.setFechaLectura(search9);

		if ("".equals(search10)) {
			buzonNotificacionesDTO.setEstado(null);
		} else {
			buzonNotificacionesDTO.setEstado(Integer.parseInt(search10));
		}

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			buzonNotificacionesDTO.setTamanioPagina(Integer.MAX_VALUE);
			buzonNotificacionesDTO.setPagina(pageNo - 1);
		} else {
			buzonNotificacionesDTO.setTamanioPagina(length);
			buzonNotificacionesDTO.setPagina(pageNo - 1);
		}

		if ("stringEstado".equals(name)) {
			name = "estado";
		}
		if ("nombreEntidad".equals(name) || "nombreOperacionOrigen".equals(name)) {
			name = "idNotificacion";
		}

		buzonNotificacionesDTO.setColumnaOrdenar(name);
		buzonNotificacionesDTO.setTipoOrden(sortDir);
		buzonNotificacionesDTO.setAdmin(0L);

		buzonNotificacionesDTO.setCodigoUsuarioDestino(usuarioSesion);
		if ("activo".equals(admin)) {
			buzonNotificacionesDTO.setCodigoUsuarioDestino(search2);
			buzonNotificacionesDTO.setAdmin(1L);
		}

		try {
			parametro = mapper.writeValueAsString(buzonNotificacionesDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
							buzonNotificacionesDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionesDTO.class);
		}

		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_ADMIN, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<BuzonNotificacionesDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<BuzonNotificacionesDTO> dataTable = new DataTable<>();

		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/crear_mensaje")
	public String crear(Model model, BuzonNotificacionesDTO buz,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion, RedirectAttributes redirectAttributes) {
		String parametro = "";

		buz.setToken(tokenSesionSeguridad);
		buz.setCodigoUsuarioOrigina(usuarioSesion);
		model.addAttribute("buzonMensaje", new BuzonNotificacionesDTO());
		try {
			parametro = mapper.writeValueAsString(buz);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, buz.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, BuzonNotificacionesController.class);
		}

		buzonRest.post(parametro, new ParameterizedTypeReference<BuzonNotificacionesDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BUZON_NOTIFICACION_CREAR_BUZON, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageExito", "Se ha creado el mensaje con exito");

		return REDIRECT_CONSULTAR_BUZON;

	}

}
