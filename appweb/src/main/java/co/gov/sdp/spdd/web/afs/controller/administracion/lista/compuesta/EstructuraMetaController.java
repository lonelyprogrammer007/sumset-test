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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IEstructurasMetaController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que implementa los metodos del controlador y maneja las peticiones de
 * estructuraMeta
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class EstructuraMetaController implements IEstructurasMetaController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<EstructuraMetaDTO> estructuraMetaRest;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	private static final String REDIRECT = "redirect:/consultar-em";

	/**
	 * Implementacion del metodo consultarEstructura
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.IEstructurasMetaController.consultarEstructura
	 */
	@GetMapping("/consultar-em")
	@Override
	public String consultarEstructura(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {


		
		GenericoDTO verbo = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Verbo",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO unidadMedida = listaGenerico.get("/administracion_lista_simple/obtener_argumento/UnidadDeMedida",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);


		model.addAttribute("verbo", verbo.getLstObjectsDTO());
		model.addAttribute("unidadMedida", unidadMedida.getLstObjectsDTO());
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/listas/consultar-em";
			}

		}
		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo crearEstructura
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IEstructuraMetaController.crearEstructura
	 */
	@PostMapping("/crear_estructura_meta")
	@Override
	public String crearEstructura(EstructuraMetaDTO estructurasMeta, Model model, RedirectAttributes redirectAttribute) {

		model.addAttribute("estructuraMeta", new EstructuraMetaDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(estructurasMeta);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, estructurasMeta.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraMetaController.class);
		}
		EstructuraMetaDTO estructura = estructuraMetaRest.post(parametro, new ParameterizedTypeReference<EstructuraMetaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_ESTRUCTURA_METAS, NHSPDDConstantes.TIPO_CORE);
		redirectAttribute.addFlashAttribute("messageRespuesta", estructura.getMsgRespuesta());
		return REDIRECT;
	}

	/**
	 * Implementacion del metodo modificarEstructura
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta.IEstructuraMetaController.modificarEstructura
	 */
	@PostMapping("/editar_estructura_meta")
	@Override
	public String modificarEstructura(EstructuraMetaDTO estructurasMeta, Model model, RedirectAttributes redirectAttribute) {
		model.addAttribute("estructuraMeta", new EstructuraMetaDTO());
		EstructuraMetaDTO estructura = estructuraMetaRest.put(estructurasMeta, EstructuraMetaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_ESTRUCTURA_METAS,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttribute.addFlashAttribute("messageRespuesta", estructura.getMsgRespuesta());
		return REDIRECT;
	}

	@GetMapping("/cambiar_estado_estructura/{id}")
	@Override
	public String cambiarEstadoEstructura(@PathVariable("id") Long id) {
		estructuraMetaRest.put(null, null, "/administracion/lista_compuesta/cambiar_estado_estructura_meta/" + id,
				NHSPDDConstantes.TIPO_CORE);
		return REDIRECT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaEstructuraMetaByPage")
	public ResponseEntity listarEstructuraMeta(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		EstructuraMetaDTO estructuraMetaDTO = new EstructuraMetaDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
	
			if (!("".equals(search0))) {
				estructuraMetaDTO.setIdEstructuraMetas(Long.parseLong(search0));
				estructuraMetaDTO.setNombreVerbo(search1);
				estructuraMetaDTO.setNombreUnidadMedida(search2);
			}else {
				estructuraMetaDTO.setIdEstructuraMetas(null);
				estructuraMetaDTO.setNombreVerbo(search1);
				estructuraMetaDTO.setNombreUnidadMedida(search2);
			}
			
			if (!("".equals(search4))) {
				estructuraMetaDTO.setEstado(Integer.parseInt(search4));
			}else {
				estructuraMetaDTO.setEstado(null);
			}
			
			if (!("".equals(search3))) {
				estructuraMetaDTO.setTipo(Long.parseLong(search3));
			}else {
				estructuraMetaDTO.setTipo(null);
			}
			
	
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			estructuraMetaDTO.setTamanioPagina(Integer.MAX_VALUE);
			estructuraMetaDTO.setPagina(pageNo - 1);
		} else {
			estructuraMetaDTO.setTamanioPagina(length);
			estructuraMetaDTO.setPagina(pageNo - 1);
		}
		
		if (!("idEstructuraMetas".equals(name))){
			switch (name) {

			case "nombreVerbo":
				name = "idLsVerbo";
				break;
			case "nombreUnidadMedida":
				name ="idLsUnidadMedida";
			    break;
			case "stringTipo":
				name = "tipo";
				break;  
		    default:
		    	name = "estado";
		    	break;
		}
		}
		
		estructuraMetaDTO.setColumnaOrdenar(name);
		estructuraMetaDTO.setTipoOrden(sortDir);
		
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(estructuraMetaDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, estructuraMetaDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraMetaController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_ESTRUCTURA_METAS_TODOS,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<EstructuraMetaDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<EstructuraMetaDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
