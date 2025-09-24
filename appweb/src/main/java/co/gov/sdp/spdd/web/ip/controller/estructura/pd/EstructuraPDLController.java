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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
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
		"nombreUsuarioSesion" })
public class EstructuraPDLController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	@Autowired
	private MetodosRest<PdlDTO> pdlRest;

	@Autowired
	SPDDLogger spddLogger;

	@GetMapping("/estructura-pdl")
	public String verEstructurasPDL(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {

		return "ip/estructura-pdl/consultar-pdl";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaPdlByPage")
	public ResponseEntity listarPDL(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		PdlDTO pdlDTO = new PdlDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		pdlDTO.setNombrePlan(search0);
		pdlDTO.setNombreAlcaldeLocal(search1);
		pdlDTO.setVigencia(search2);
		pdlDTO.setYearInicio(search3);
		pdlDTO.setYearFinal(search4);
		pdlDTO.setEstadoPlan(search5);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			pdlDTO.setTamanioPagina(Integer.MAX_VALUE);
			pdlDTO.setPagina(pageNo - 1);
		} else {
			pdlDTO.setTamanioPagina(length);
			pdlDTO.setPagina(pageNo - 1);
		}

		pdlDTO.setColumnaOrdenar(name);
		pdlDTO.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pdlDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pdlDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDLController.class);
		}

		if (name.equals("estadoPlan")) {
			name = "estado";
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_POR_FILTRO_PDL, NHSPDDConstantes.TIPO_CORE);

		System.out.println("consulta pdl"+res.toString());
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PdlDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PdlDTO> dataTable = new DataTable<>();

		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@GetMapping("/agregar-pdl")
	public String agregarPDL(Model model,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion) {

		RespuestaApiDTO<EntidadSeguridadDTO> entidad = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);
     
		
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/adoptados",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	

		GenericoDTO niveles = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Niveles PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		

		GenericoDTO avanceSgr = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Opcion Avance SGR",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estados PDL",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("entidades", entidad.getObjetos());
		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());
		model.addAttribute("niveles", niveles.getLstObjectsDTO());
		model.addAttribute("avanceSgr", avanceSgr.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("agregar", "agregar");

		return "ip/estructura-pdl/agregar-editar-pdl";

	}
	
	@PostMapping("/crear-editar-pdl")
	public String crearEditrPDL(PdlDTO pdl, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("psss"+pdl.toString());
		
		if (pdl.getIdPlanLocal() == null) {
			System.out.println("entro a registrar");
		String parametro = "";
			try {
				parametro = mapper.writeValueAsString(pdl);

			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pdl.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDLController.class);
			}
			PdlDTO componente = pdlRest.post(parametro, new ParameterizedTypeReference<PdlDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDL, NHSPDDConstantes.TIPO_CORE);
			System.out.println("RES"+componente.getMsgRespuesta());
			
			redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
           
			return "redirect:/agregar-pdl";
		} else {
			
		    System.out.println("antes");
			PdlDTO componente = pdlRest.put(pdl, PdlDTO.class,
					NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDL, NHSPDDConstantes.TIPO_CORE);
			System.out.println("depues"+componente.getMsgRespuesta());
			redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
			return "redirect:/agregar-pdl";
		}
}
	
	@PostMapping("/modificar-pdl")
	public String modificarPdl(PdlDTO pdl, Model model) {
		System.out.println("entro a pdl"+pdl.toString());
		RespuestaApiDTO<EntidadSeguridadDTO> entidad = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);
     
		
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/adoptados",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	

		GenericoDTO niveles = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Niveles PDD",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		

		GenericoDTO avanceSgr = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Opcion Avance SGR",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estados PDL",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		model.addAttribute("entidades", entidad.getObjetos());
		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());
		model.addAttribute("niveles", niveles.getLstObjectsDTO());
		model.addAttribute("avanceSgr", avanceSgr.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("pdlDTO", pdl);
		model.addAttribute("agregar", "editar");
		
		
		return "ip/estructura-pdl/agregar-editar-pdl";
	}
}
