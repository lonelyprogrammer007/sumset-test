package co.gov.sdp.spdd.web.afs.controller.administracion;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import co.gov.sdp.nhspdd.common.dto.ListaCompuestaDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.IListaCompuestaController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;


/**
 * Clase que maneja los metodos de una lista compuesta por medio de peticiones
 * post,get,put y delete
 *
 * @author Raul londono, Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class ListaCompuestaController implements IListaCompuestaController {

	@Autowired
	private MetodosRest<GenericoDTO> rest;
	
	@Autowired
	private MetodosRest<ListaCompuestaDTO> listaCompuesta;

	
	@Autowired 
    SPDDLogger spddLogger;


	@Autowired
	ObjectMapper mapper;
	/**
	 * Implementacion del metodo consultarListaCompuestaTodos
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.IListaCompuestaController.consultarListaCompuestaTodos
	 */
	@GetMapping(NHSPDDConstantes.WEB_CONTROLLER_GET_CONSULTAR_LISTA_COMPUESTA)
	public String consultarListaCompuestaTodos(
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {


		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {

			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return NHSPDDConstantes.WEB_RESOURCE_LISTA_CONSULTAR_CONTENIDO_LISTA_COMPUESTA;
			}

		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	/**
	 * Implementacion del metood consultarTablaListaCompuesta
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.IListaCompuestaController.consultarTablaListaCompuesta
	 */
	@GetMapping("/lista_compuesta/{id}")
	@Override
	public String consultarTablaListaCompuesta(@PathVariable(name = "id") Long id) {

		ListaCompuestaDTO tabla = listaCompuesta.get("/administracion/listacompuesta/obtener_lista/" + id,
				new ParameterizedTypeReference<ListaCompuestaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		if ("Equipamiento".equals(tabla.getTabla())) {
			return "redirect:/consultar-eq";
		}
		if ("PotProyectoInstrumento".equals(tabla.getTabla())) {
			return "redirect:/consultar-pot";
		}
		if ("Territorializacion".equals(tabla.getTabla())) {
			return "redirect:/consultar-terr";
		}
		if ("LineaDeInversion".equals(tabla.getTabla())) {
			return "redirect:/consultar-li";
		}

		if ("EstructuraMeta".equals(tabla.getTabla())) {
			return "redirect:/consultar-em";
		}
		if ("ComponenteGasto".equals(tabla.getTabla())) {
			return "redirect:/consultar-cg";
		}
		if("CompromisoEstrategico".equals(tabla.getTabla())) {
			return "redirect:/consultar-ce";
		}

		return "redirect:/consultar_lista_compuesta";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listaCompuestaByPage")
	public ResponseEntity listarListasCompuestas(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		ListaCompuestaDTO listCompuestaDTO = new ListaCompuestaDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
	
		listCompuestaDTO.setNombre(search0);
		listCompuestaDTO.setDescripcion(search1);


		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			listCompuestaDTO.setTamanioPagina(Integer.MAX_VALUE);
			listCompuestaDTO.setPagina(pageNo - 1);
		} else {
			listCompuestaDTO.setTamanioPagina(length);
			listCompuestaDTO.setPagina(pageNo - 1);
			
		}
		
		listCompuestaDTO.setColumnaOrdenar(name);
		listCompuestaDTO.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(listCompuestaDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, listCompuestaDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ListaCompuestaController.class);
		}
		GenericoDTO res = rest.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, 	NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_LISTAS_COMPUESTAS,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<ListaCompuestaDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<ListaCompuestaDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
