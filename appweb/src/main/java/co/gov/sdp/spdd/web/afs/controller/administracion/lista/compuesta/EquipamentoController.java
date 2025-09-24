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

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IEquipamentoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que maneja los metodos get,post,put y delete del controlador de
 * equipamiento
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class EquipamentoController implements IEquipamentoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<EquipamientoDTO> equipamentoDTORest;
	
	@Autowired 
    SPDDLogger spddLogger;

	@Autowired
	ObjectMapper mapper;

	private static final String REDIRECT = "redirect:/consultar-eq";

	/**
	 * Implementaicon del metodo consultar equipamiento
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IEquipamientoController.consultarEquipamiento
	 */
	@GetMapping("/consultar-eq")
	@Override
	public String consultarEquipamento(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {

		GenericoDTO listaSectores = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Sectores",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO listaCategorias = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Categorias",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("sectores", listaSectores.getLstObjectsDTO());
		model.addAttribute("categorias", listaCategorias.getLstObjectsDTO());
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/listas/consultar-eq";
			}

		}
		
		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo crearEquipamiento
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IEquipamientoController.crearEquipamietno
	 */
	@PostMapping("/crear_equipamientos")
	@Override
	public String crearEquipamento(EquipamientoDTO equipamiento, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("equipamiento", new EquipamientoDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(equipamiento);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, equipamiento.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, EquipamentoController.class);
		}
		EquipamientoDTO equi = equipamentoDTORest.post(parametro, new ParameterizedTypeReference<EquipamientoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_EQUIPAMIENTO, NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageRespuesta",equi.getMsgRespuesta());
		return REDIRECT;
	}

	/**
	 * Implementacion del metodo editarEquipamiento
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IEquipamientoController.editarEquipamiento
	 */
	@PostMapping("/editar_equipamientos")
	@Override
	public String editarEquipamento(EquipamientoDTO equipamiento, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("equipam", new EquipamientoDTO());
		EquipamientoDTO equip = equipamentoDTORest.put(equipamiento, EquipamientoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_EQUIPAMIENTO,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta",equip.getMsgRespuesta());
		return REDIRECT;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listaEquipamientoByPage")
	public ResponseEntity listarEquipamiento(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		EquipamientoDTO equipamientoDTO = new EquipamientoDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
	


		if ("".equals(search5)) {
			equipamientoDTO.setEstado(null);

		} else {
			equipamientoDTO.setEstado(Integer.parseInt(search5));
		}
		if (!("".equals(search0))) {
			equipamientoDTO.setIdEquipamento(Long.parseLong(search0));
			equipamientoDTO.setNombre(search1);
			equipamientoDTO.setDescripcion(search2);
			equipamientoDTO.setNombreSector(search3);
			equipamientoDTO.setNombreCategoria(search4);
		}
		else {
			equipamientoDTO.setIdEquipamento(null);
			equipamientoDTO.setNombre(search1);
			equipamientoDTO.setDescripcion(search2);
			equipamientoDTO.setNombreSector(search3);
			equipamientoDTO.setNombreCategoria(search4);
		}
		

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			equipamientoDTO.setTamanioPagina(Integer.MAX_VALUE);
			equipamientoDTO.setPagina(pageNo - 1);
		} else {
			equipamientoDTO.setTamanioPagina(length);
			equipamientoDTO.setPagina(pageNo - 1);
		}
		
		if (!("idArchivo".equals(name)) || !("fechaCargue".equals(name))  ){
			switch (name) {
			case "nombreSector":
				name = "idLsSector";
				break;
			case "nombreCategoria":
				name ="idLsCategoria";
			    break;
		    default:
		    	name = "estado";
		    	break;
		}
		}
		equipamientoDTO.setColumnaOrdenar(name);
		equipamientoDTO.setTipoOrden(sortDir);
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(equipamientoDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, equipamientoDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, EquipamentoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_EQUIPAMIENTOS_TODOS,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<EquipamientoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<EquipamientoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
