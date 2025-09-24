
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

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IComponenteGastoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Metodo que tiene los metodos de peticiones post,get,put y delete de
 * componente gasto
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "ordenLista" })
public class ComponenteGastoController implements IComponenteGastoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<ComponenteGastoDTO> componenteGastoRest;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	

	private static final String REDIRECT = "redirect:/consultar-cg";

	/**
	 * Implementacion del metodo consultar gasto
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IComponenteGastoController.consultarComponenteGasto
	 */
	@GetMapping("/consultar-cg")
	@Override
	public String consultarComponenteGasto(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {

		GenericoDTO tipoProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TipoProyecto",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("tipoProyecto", tipoProyecto.getLstObjectsDTO());

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/listas/consultar-cg";
			}

		}
		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo crearComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IComponenteGastoController.crearComponenteGasto
	 */
	@PostMapping("/crear_componente_gasto")
	@Override
	public String crearComponenteGasto(ComponenteGastoDTO componenteGasto, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("componenteGasto", new ComponenteGastoDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(componenteGasto);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, componenteGasto.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		ComponenteGastoDTO componente = componenteGastoRest.post(parametro, new ParameterizedTypeReference<ComponenteGastoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_COMPONENTE_GASTO, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
		return REDIRECT;
	}

	/**
	 * Implementacion del metodo editarComponenteGasto
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IComponenteGastoController.editarComponenteGasto
	 */
	@PostMapping("/editar_componente_gasto")
	@Override
	public String editarComponenteGasto(ComponenteGastoDTO componenteGasto, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("componenteGasto", new ComponenteGastoDTO());
		ComponenteGastoDTO componente = componenteGastoRest.put(componenteGasto, ComponenteGastoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_COMPONENTE_GASTO,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta",componente.getMsgRespuesta());
		return REDIRECT;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaComponenteGastoByPage")
	public ResponseEntity listarComponenteGasto(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		ComponenteGastoDTO componenteGastoDTO = new ComponenteGastoDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if ("".equals(search3)) {
			componenteGastoDTO.setEstado(null);
		} else {
			componenteGastoDTO.setEstado(Integer.parseInt(search3));
		}

		if(!"".equals(search0)) {
		componenteGastoDTO.setCodigoComponente(Long.parseLong(search0));
		}
		componenteGastoDTO.setNombreComponente(search1);
		componenteGastoDTO.setTipoProyecto(search2);
		
		

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			componenteGastoDTO.setTamanioPagina(Integer.MAX_VALUE);
			componenteGastoDTO.setPagina(pageNo - 1);
		} else {
			componenteGastoDTO.setTamanioPagina(length);
			componenteGastoDTO.setPagina(pageNo - 1);
		}
		
		if ("tipoProyecto".equals(name)) {
			name = "idLsTipoProyecto";
		}
		if ("stringEstado".equals(name)) {
			name = "estado";
		}
		componenteGastoDTO.setColumnaOrdenar(name);
		componenteGastoDTO.setTipoOrden(sortDir);
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(componenteGastoDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, componenteGastoDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_COMPONENTE_GASTO_TODOS,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<ComponenteGastoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<ComponenteGastoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
