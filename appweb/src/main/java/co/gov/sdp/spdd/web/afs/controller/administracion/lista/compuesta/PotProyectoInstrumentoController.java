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
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IPotProyectoInstrummentoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que maneja los metodos de peticiones post,get,put y delete de
 * potProyectoInstrumento
 *
 * @author Bryan Munoz, Alex leal
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class PotProyectoInstrumentoController implements IPotProyectoInstrummentoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<PotProyectoInstrumentoDTO> potProyectoInstrumento;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;

	private static final String REDIRECT = "redirect:/consultar-pot";

	/**
	 * Implementacion del metodo consultarPOT
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.consultarPOT
	 */
	@GetMapping("/consultar-pot")
	@Override
	public String consultarPOT(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {

		GenericoDTO potProyecto = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_OBRA,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO instrumento = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_INSTRUMENTO,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("potProyecto", potProyecto.getLstObjectsDTO());
		model.addAttribute("instrumentos", instrumento.getLstObjectsDTO());

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/listas/consultar-pot";
			}

		}
		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo registrarPOT
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.registrarPOT
	 */
	@PostMapping("/crear_pot")
	@Override
	public String registrarPOT(PotProyectoInstrumentoDTO proyecto, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("proyecto", new PotProyectoInstrumentoDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(proyecto);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, proyecto.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}
		PotProyectoInstrumentoDTO pot = potProyectoInstrumento.post(parametro, new ParameterizedTypeReference<PotProyectoInstrumentoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_POT_PROYECTO_INSTRUMENTO,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", pot.getMsgRespuesta());
		return REDIRECT;
	}

	/**
	 * Implementacion del metodo editarPOT
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IPotProyectoInstrumentoController.editarPOT
	 */
	@PostMapping("/editar_ProyectosPOT")
	@Override
	public String editarPOT(PotProyectoInstrumentoDTO potProyecto, Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("proyecto", new PotProyectoInstrumentoDTO());
		PotProyectoInstrumentoDTO pot= potProyectoInstrumento.put(potProyecto, PotProyectoInstrumentoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_POT_PROYECTO_INSTRUMENTO,
				NHSPDDConstantes.TIPO_CORE);
	
		redirectAttributes.addFlashAttribute("messageRespuesta", pot.getMsgRespuesta());
		return REDIRECT;
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaPotProyectoInstrumentoByPage")
	public ResponseEntity listarPotProyectoInstrumento(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		PotProyectoInstrumentoDTO potProyectoInstrumentoDTO = new PotProyectoInstrumentoDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if ("".equals(search3)) {
			potProyectoInstrumentoDTO.setEstado(null);
		} else {
			potProyectoInstrumentoDTO.setEstado(Integer.parseInt(search3));
		}
		if ("".equals(search0)) {
			potProyectoInstrumentoDTO.setIdProyectoInstrumento(null);
			potProyectoInstrumentoDTO.setNombrePotProyecto(search1);
			potProyectoInstrumentoDTO.setNombreInstrumento(search2);
		} else {
			potProyectoInstrumentoDTO.setIdProyectoInstrumento(Long.parseLong(search0));
			potProyectoInstrumentoDTO.setNombrePotProyecto(search1);
			potProyectoInstrumentoDTO.setNombreInstrumento(search2);
		}

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			potProyectoInstrumentoDTO.setTamanioPagina(Integer.MAX_VALUE);
			potProyectoInstrumentoDTO.setPagina(pageNo - 1);
		} else {
			potProyectoInstrumentoDTO.setTamanioPagina(length);
			potProyectoInstrumentoDTO.setPagina(pageNo - 1);
		}
		
		if ("nombrePotProyecto".equals(name)) {
			name = "idPotProyecto";
		}
		if ("nombreInstrumento".equals(name)) {
			name = "idPotInstrumento";
		}
		if ("stringEstado".equals(name)) {
			name = "estado";
		}
		
		potProyectoInstrumentoDTO.setColumnaOrdenar(name);
		potProyectoInstrumentoDTO.setTipoOrden(sortDir);
		
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potProyectoInstrumentoDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potProyectoInstrumentoDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, PotProyectoInstrumentoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PotProyectoInstrumentoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest,
				res.getTotal());

		DataTable<PotProyectoInstrumentoDTO> dataTable = new DataTable<>();

		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
