package co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IComponenteEstrategicoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class CompromisoEstrategicoController implements IComponenteEstrategicoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<CompromisoEstrategicoDTO> compromisoEstrategicoRest;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;

	private static final String REDIRECT = "redirect:/consultar-ce";

	@GetMapping("/consultar-ce")
	@Override
	public String consultarCompromisoEstrategico(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {
		GenericoDTO tematica = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Tematica",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("tematicas", tematica.getLstObjectsDTO());

		GenericoDTO compromiso = listaGenerico.get(
				"/administracion_lista_simple/obtener_argumento/CompromisoEstrategico",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("compromisos", compromiso.getLstObjectsDTO());

		return "afs/listas/consultar-ce";
	}

	@PostMapping("/crear_compromiso_estrategico")
	@Override
	public String crearCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategico, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("compromisoEstrategico", new CompromisoEstrategicoDTO());

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(compromisoEstrategico);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, compromisoEstrategico.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, CompromisoEstrategicoController.class);

		}
		CompromisoEstrategicoDTO componente = compromisoEstrategicoRest.post(parametro,
				new ParameterizedTypeReference<CompromisoEstrategicoDTO>() {
				}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_COMPROMISO_ESTRATEGICO,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
		return REDIRECT;
	}

	@PostMapping("/editar_compromiso_estrategico")
	@Override
	public String editarCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategico, Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("compromisoEstrategico", new CompromisoEstrategicoDTO());

		CompromisoEstrategicoDTO componente = compromisoEstrategicoRest.put(compromisoEstrategico,
				CompromisoEstrategicoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_COMPROMISO_ESTRATEGICO,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
		return REDIRECT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaCompromisoEstrategicoByPage")
	public ResponseEntity listarCompromisoEstrategico(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		CompromisoEstrategicoDTO compromiso = new CompromisoEstrategicoDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");

		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if (!"".equals(search0)) {
			compromiso.setIdCompromiso(Long.parseLong(search0));
		}
		compromiso.setNombreTematica(search1);
		compromiso.setNombreCompromisoEstrategico(search2);
		if (!"idCompromiso".equals(name)) {
			switch (name) {
			case "nombreTematica":
				name = "idLsTematica";
				break;
			case "nombreCompromisoEstrategico":
				name = "idLsEstrategico";
				break;
			default:
				name = "estado";
				break;
			}
		}
		compromiso.setColumnaOrdenar(name);
		compromiso.setTipoOrden(sortDir);

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			compromiso.setTamanioPagina(Integer.MAX_VALUE);
			compromiso.setPagina(pageNo - 1);
		} else {
			compromiso.setTamanioPagina(length);
			compromiso.setPagina(pageNo - 1);
		}

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(compromiso);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, compromiso.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, CompromisoEstrategicoController.class);

		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_COMPROMISO_ESTRATEGICO,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<CompromisoEstrategicoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<CompromisoEstrategicoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
}
