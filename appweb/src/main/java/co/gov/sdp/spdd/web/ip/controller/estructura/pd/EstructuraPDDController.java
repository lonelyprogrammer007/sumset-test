package co.gov.sdp.spdd.web.ip.controller.estructura.pd;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * 
 * @author Alexander Leal
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "pddDTO", "tipoPdd", "idPddAgregar" })
public class EstructuraPDDController {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<PddDTO> pddRest;

	@Autowired
	private MetodosRest<PddNivelDTO> pddNivelRest;

	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;

	@Autowired
	SPDDLogger spddLogger;

	@GetMapping("/estructura-pdd/{idPdd}")
	public String verEstructurasPDD(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, @PathVariable("idPdd") String idPdd,
			Model model) {

		switch (idPdd) {
		case "1":
			model.addAttribute("tipoPdd", "1");
			break;
		case "2":
			model.addAttribute("tipoPdd", "2");
			break;
		case "3":
			model.addAttribute("tipoPdd", "3");
			break;
		default:
			break;
		}

		return "redirect:/consultar-PDD";
	}

	@GetMapping("consultar-PDD")
	public String consultarPdd(@ModelAttribute("tipoPdd") String idPdd) {
		return "ip/general/consultar-pdd.html";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaProgramaEstructuraByPage")
	public ResponseEntity listarProgramaCompromisos(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		PddDTO pddDTO = new PddDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		pddDTO.setNombrePlan(search0);
		pddDTO.setNombreAlcalde(search1);
		pddDTO.setVigencia(search2);
		pddDTO.setYearInicio(search3);
		pddDTO.setYearFinal(search4);
		pddDTO.setEstadoPlan(search5);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			pddDTO.setTamanioPagina(Integer.MAX_VALUE);
			pddDTO.setPagina(pageNo - 1);
		} else {
			pddDTO.setTamanioPagina(length);
			pddDTO.setPagina(pageNo - 1);
		}

		pddDTO.setColumnaOrdenar(name);
		pddDTO.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDDController.class);
		}

		if (name.equals("estadoPlan")) {
			name = "estado";
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PddDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddDTO> dataTable = new DataTable<>();

		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/obtener-niveles")
	public String obtenerNiveles(Model model, PddDTO pddDTO) {
		model.addAttribute("pddDTO", pddDTO);
		return "redirect:/ver-niveles";

	}

	@GetMapping("/ver-niveles")
	public String verNiveles(Model model, @ModelAttribute("pddDTO") PddDTO pddDTO,
			@ModelAttribute("tipoPdd") String idPdd, @ModelAttribute("codigoEntidadUsuario") String entidadLog) {

		GenericoDTO niveles = listaGenerico.get(
				"/ipplandistrital/obtener_todos_pdd_nivel/" + pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		RespuestaApiDTO<EntidadSeguridadDTO> entidad = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);
		model.addAttribute("pddDTOGet", pddDTO);
		model.addAttribute("nivelConsulta", niveles.getLstObjectsDTO());
		model.addAttribute("tipoPdd", idPdd);
		model.addAttribute("entidad", entidad.getObjetos());
		model.addAttribute("entidadLog", entidadLog);
		return "ip/estructura-pdd/ver-niveles.html";
	}

	@GetMapping("/agregar-pdd/{idPddAgregar}")
	public String agregarPDD(Model model, @PathVariable("idPddAgregar") String idPddAgregar) {
		model.addAttribute("idPddAgregar", idPddAgregar);
		return "redirect:/registrar-pdd";

	}

	@GetMapping("/registrar-pdd")
	public String registrarPddGet(@ModelAttribute("idPddAgregar") String idPddAgregar, Model model) {
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/adoptados",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());

		GenericoDTO niveles = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Niveles PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("niveles", niveles.getLstObjectsDTO());

		GenericoDTO avanceSgr = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Opcion Avance SGR",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estados PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("avanceSgr", avanceSgr.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("pddDTO", new PddDTO());
		model.addAttribute("idPddAgregar", idPddAgregar);

		return "ip/estructura-pdd/agregar-pdd";
	}

	@PostMapping("/crearPddPost")
	public String crearPDD(PddDTO pdd, Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute("tipoPdd") String idPdd) {

		System.out.println("imprimo todo" + pdd.toString());
		String parametro = "";
		if (pdd.getFechaActo().equals("")) {
			pdd.setFechaActo(null);
		}
		try {
			parametro = mapper.writeValueAsString(pdd);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pdd.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDDController.class);
		}

		PddDTO componente = pddRest.post(parametro, new ParameterizedTypeReference<PddDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
		return "redirect:/registrar-pdd";
	}

	@PostMapping("/modificarPddPost")
	public String modificarPddPost(PddDTO pdd, RedirectAttributes redirectAttributes) {
		System.out.println("entro a editarr");
		PddDTO componente = pddRest.put(pdd, PddDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
	

		return "redirect:/modificar-pdd";

	}

	@PostMapping("/modificar-pdd-post")
	public String editarPDD(Model model, PddDTO pdd) {
        model.addAttribute("pddDTO", pdd);
		return "redirect:/modificar-pdd";
	}

	@GetMapping("/modificar-pdd")
	public String getModificarPdd(Model model, @ModelAttribute("pddDTO") PddDTO pdd ) {
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/adoptados",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO niveles = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Niveles PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO avanceSgr = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Opcion Avance SGR",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estados PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());
		model.addAttribute("niveles", niveles.getLstObjectsDTO());
		model.addAttribute("avanceSgr", avanceSgr.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("pddDTO", pdd);

		return "ip/estructura-pdd/editar-pdd";
	}

	@PostMapping("/agregar-editar-PddNiveles")
	public String agregarEditarPddNiveles(PddNivelDTO pddNivel, RedirectAttributes redirectAttributes) {

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDDController.class);
		}
		PddNivelDTO niveles = pddNivelRest.post(parametro, new ParameterizedTypeReference<PddNivelDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageRespuesta", niveles.getMsgRespuesta());

		return "redirect:/ver-niveles";
	}

	@PostMapping("/copiar-estructura")
	public String copiarEstructuraPddaPdl(PdlDTO pdl, RedirectAttributes redirectAttributes,
			@ModelAttribute("codigoEntidadUsuario") String entidad) {
		System.out.println("pdl estructura" + pdl.toString());

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pdl);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pdl.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDDController.class);
		}
		GenericoDTO copia = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_COPIAR_ESTRUCTURA_DE_PDD_A_PDL, NHSPDDConstantes.TIPO_CORE);

		System.out.println("mensae" + copia.getMsgRespuesta());
		redirectAttributes.addFlashAttribute("messageRespuesta", copia.getMsgRespuesta());
		return "redirect:/ver-niveles";
	}
}
