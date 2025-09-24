package co.gov.sdp.spdd.web.afs.controller.entidades;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialAdministrativoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "entidad" })
public class EntidadesController {

	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;

	@Autowired
	MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	MetodosRest<FuncionarioClaveEntidadDTO> funcionarioRest;

	@Autowired
	MetodosRest<EntidadDTO> entidadRest;

	@Autowired
	MetodosRest<RespuestaApiDTO<HistorialAdministrativoDTO>> hisAdminRest;

	@Autowired
	MetodosRest<RespuestaApiDTO<FuncionarioClaveEntidadDTO>> funRest;

	@Autowired
	SPDDLogger spddLogger;

	public static final String REDIRECT_EDITAR_ENTIDAD = "redirect:/entidad-editar";

	@GetMapping("/consultar-ent")
	public String consultarEntidades(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		System.out.println(tokenSesionSeguridad);
		RespuestaApiDTO<EntidadSeguridadDTO> prueba = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);

		model.addAttribute("entidades", prueba.getObjetos());

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
					&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
				return "afs/entidades/consultar-ent-alc";
			}
		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	@PostMapping("/crear-ent")
	public String crearEntidades(Model model, EntidadSeguridadDTO entidad) {

		model.addAttribute("entidad", entidad);
		return REDIRECT_EDITAR_ENTIDAD;
	}

	@PostMapping("editar-ent")
	public String editarEntidad(Model model, EntidadDTO entidadDTO,
			@ModelAttribute("entidad") EntidadSeguridadDTO entidad, RedirectAttributes redirectAttributes) {

		String dto = "";

		if (entidadDTO.getGestionProyectos() == null) {
			entidadDTO.setGestionProyectos(0);
		}

		entidadDTO.setCodigoEntidad(entidad.getCodigoDistrital());

		try {
			dto = mapper.writeValueAsString(entidadDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					entidadDTO.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, EntidadesController.class);
		}
		EntidadDTO res = entidadRest.post(dto, new ParameterizedTypeReference<EntidadDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_ENTIDAD, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageConfirma", res.getMsgRespuesta());
		return REDIRECT_EDITAR_ENTIDAD;
	}

	@GetMapping("/entidad-editar")
	public String paginaEntidades(Model model, @ModelAttribute("entidad") EntidadSeguridadDTO entidad) {
		GenericoDTO funcion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Funcion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO genero = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Genero",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO clasificacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Clasificacion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO asociacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Asociacion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO banco = listaGenerico.get("/consultar_banco", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);

		EntidadDTO entAct = entidadRest.get("/administracion/validaryregistrarEntidad/" + entidad.getCodigoDistrital(),
				new ParameterizedTypeReference<EntidadDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		System.out.println("dfb"+entAct.toString());
		RespuestaApiDTO<HistorialAdministrativoDTO> acto = hisAdminRest.get(
				"/api/historial_administrativo/consultar_acto_administrativo/" + entidad.getCodigoEntidad(),
				new ParameterizedTypeReference<RespuestaApiDTO<HistorialAdministrativoDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);

		if (!acto.getObjetos().isEmpty()) {
			model.addAttribute("acto", acto.getObjetos().get(0));
		} else {
			HistorialAdministrativoDTO auxiliar = new HistorialAdministrativoDTO();
			auxiliar.setActoAdministrativo("no tiene");
			model.addAttribute("acto", auxiliar);
		}

		model.addAttribute("funcion", funcion.getLstObjectsDTO());
		model.addAttribute("genero", genero.getLstObjectsDTO());
		model.addAttribute("asociacion", asociacion.getLstObjectsDTO());
		model.addAttribute("clasificacion", clasificacion.getLstObjectsDTO());
		model.addAttribute("banco", banco.getLstObjectsDTO());
		model.addAttribute("entAct", entAct);

		return "afs/entidades/crear-entidad";
	}

	/**
	 * @param model
	 * @param funcionario
	 * @param entidad
	 * @return
	 */
	@PostMapping("/crear-funcionario")
	public String crearFuncionario(Model model, FuncionarioClaveEntidadDTO funcionario,
			@ModelAttribute("entidad") EntidadSeguridadDTO entidad, RedirectAttributes redirectAttributes) {
		String parametro = "";
		funcionario.setCodigoEntidad(entidad.getCodigoDistrital());
		try {
			parametro = mapper.writeValueAsString(funcionario);
		} catch (JsonProcessingException e) {

			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					funcionario.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, EntidadesController.class);
		}
		FuncionarioClaveEntidadDTO res= funcionarioRest.post(parametro, new ParameterizedTypeReference<FuncionarioClaveEntidadDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_REGISTRAR_FUNCIONARIO_CLAVE_ENTIDAD,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageFuncionario", res.getMsgRespuesta());

		return REDIRECT_EDITAR_ENTIDAD;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/consultarfuncionarios/{ent}")
	public ResponseEntity listAllTable(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("ent") String entidad) {
		FuncionarioClaveEntidadDTO funcionario = new FuncionarioClaveEntidadDTO();
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		funcionario.setNombreIdLsFuncion(search0);
		funcionario.setNombre(search1);
		funcionario.setCargo(search2);
		funcionario.setCorreo(search3);
		funcionario.setNombreIdLsGenero(search4);
		funcionario.setCodigoEntidad(entidad);

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			funcionario.setTamanioPagina(Integer.MAX_VALUE);
			funcionario.setPagina(pageNo - 1);
		} else {
			funcionario.setTamanioPagina(length);
			funcionario.setPagina(pageNo - 1);
		}
		if ("nombreIdLsFuncion".equals(name)) {
			name = "idLsFuncion";
		}
		if ("nombreIdLsGenero".equals(name)) {
			name = "idLsGenero";
		}
		funcionario.setColumnaOrdenar(name);
		funcionario.setTipoOrden(sortDir);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(funcionario);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					funcionario.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, EntidadesController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_FUNCIONARIO_CLAVE_ENTIDAD,
				NHSPDDConstantes.TIPO_CORE);
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<FuncionarioClaveEntidadDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest,
				res.getTotal());
		DataTable<FuncionarioClaveEntidadDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/editar_funcionarios_claves")
	public String editarFuncionario(Model model, FuncionarioClaveEntidadDTO funcionario,
			@ModelAttribute("entidad") EntidadSeguridadDTO entidad, RedirectAttributes redirectAttributes) {
		funcionario.setCodigoEntidad(entidad.getCodigoDistrital());

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(funcionario);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					funcionario.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, EntidadesController.class);
		}

		FuncionarioClaveEntidadDTO res = funcionarioRest.post(parametro,
				new ParameterizedTypeReference<FuncionarioClaveEntidadDTO>() {
				}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_POST_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD,
				NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageFuncionario", res.getMsgRespuesta());
		return REDIRECT_EDITAR_ENTIDAD;
	}

}
