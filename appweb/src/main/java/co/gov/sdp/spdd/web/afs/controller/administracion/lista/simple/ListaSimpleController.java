package co.gov.sdp.spdd.web.afs.controller.administracion.lista.simple;

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

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.simple.IListaSimpleController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que maneja los metodos de peticiones get,post,put y delete de listas
 * simples y argumentos
 *
 * @author Bryan Munoz, Alex Leal
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "listaSimple" })
public class ListaSimpleController implements IListaSimpleController {
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<ArgumentoListaSimpleDTO> argumentoRest;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	private ObjectMapper mapper;

	public static final String REDIRECT_EDITAR_LS = "redirect:/argumento-editar";

	@GetMapping("/consultar-ls")
	public String consultarLs(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		listaGenerico.agregarToken(tokenSesionSeguridad);
		argumentoRest.agregarToken(tokenSesionSeguridad);
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
					&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
				return "afs/listas/consultar-ls";
			}
		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	@PostMapping("/argumento-ls")
	@Override
	public String editarListaSimple(Model model, ListaSimpleDTO list) {

		model.addAttribute("listaSimple", list);
		return REDIRECT_EDITAR_LS;

	}

	@GetMapping("/argumento-editar")
	@Override
	public String paginaArgumentos(Model model, @ModelAttribute("listaSimple") ListaSimpleDTO idListaSimple) {

		return "afs/listas/editar-ls";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listaArgumentosByPage")
	public ResponseEntity listarArgumentos(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req,
			@ModelAttribute("listaSimple") ListaSimpleDTO idListaSimple) {
		ArgumentoListaSimpleDTO listaSimple = new ArgumentoListaSimpleDTO();
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		listaSimple.setIdListaSimple(idListaSimple.getIdListaSimple());

		if ("".equals(search3)) {
			listaSimple.setEstado(null);

		} else {
			listaSimple.setEstado(Integer.parseInt(search3));
		}
		if (!("".equals(search0))) {
			listaSimple.setIdArgumento(Long.parseLong(search0));
			listaSimple.setArgumento(search1);
			listaSimple.setResultado(search2);

		} else {
			listaSimple.setArgumento(search1);
			listaSimple.setResultado(search2);

		}
		listaSimple.setColumnaOrdenar(name);

		listaSimple.setTipoOrden(sortDir);

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			listaSimple.setTamanioPagina(Integer.MAX_VALUE);
			listaSimple.setPagina(pageNo - 1);
		} else {
			listaSimple.setTamanioPagina(length);
			listaSimple.setPagina(pageNo - 1);
		}
		if ("stringEstado".equals(name)) {
			name = "estado";
		}
		listaSimple.setColumnaOrdenar(name);

		listaSimple.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(listaSimple);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					listaSimple.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_ARGUMENTO_LISTA_SIMPLE,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<ArgumentoListaSimpleDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<ArgumentoListaSimpleDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/crearArgumento")
	@Override
	public String crearArgumento(ArgumentoListaSimpleDTO arg, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("arg", new ArgumentoListaSimpleDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(arg);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, arg.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class);
		}
		ArgumentoListaSimpleDTO argu = argumentoRest.post(parametro,
				new ParameterizedTypeReference<ArgumentoListaSimpleDTO>() {
				}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE,
				NHSPDDConstantes.TIPO_CORE);
		System.out.println(argu.getMsgRespuesta());
		redirectAttributes.addFlashAttribute("messageRespuesta", argu.getMsgRespuesta());
		return REDIRECT_EDITAR_LS;
	}

	@PostMapping("/editar-argumento")
	public String postEditarListaSimple(ArgumentoListaSimpleDTO argumento, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("argumento", new ArgumentoListaSimpleDTO());
		ArgumentoListaSimpleDTO argu = argumentoRest.put(argumento, ArgumentoListaSimpleDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", argu.getMsgRespuesta());
		return REDIRECT_EDITAR_LS;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/queryByPage")
	public ResponseEntity listAllTable(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		ListaSimpleDTO listaSimple = new ListaSimpleDTO();
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");

		String name = req.getParameter("columns[" + sortableCol + "][data]");

		listaSimple.setNombre(search0);
		listaSimple.setDescripcion(search1);
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			listaSimple.setTamanioPagina(Integer.MAX_VALUE);
			listaSimple.setPagina(pageNo - 1);
		} else {
			listaSimple.setTamanioPagina(length);
			listaSimple.setPagina(pageNo - 1);
		}
		listaSimple.setColumnaOrdenar(name);

		listaSimple.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(listaSimple);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					listaSimple.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, ListaSimpleController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS, NHSPDDConstantes.TIPO_CORE);
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<ListaSimpleDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<ListaSimpleDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}