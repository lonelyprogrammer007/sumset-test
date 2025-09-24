package co.gov.sdp.spdd.web.bp.controller.iniciativa.ciudadana;

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

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;


@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion"})
public class IniciativaCiudadanaController {
	

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<BpIniciativaCiudadanaDTO> iniciativaRest;
	
	@Autowired
	private MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidadRest;
	
	@Autowired
	private MetodosRest<RespuestaApiDTO<HistorialSectorialDTO>> listaHistorial;
	
	@Autowired
	private MetodosRest<PddDTO> pddRest;
	
	@Autowired
	private MetodosRest<ConsecutivoDTO> consecutivoRest;
	
	@GetMapping("/consultar-iniciativa-ciudadana")
	public String verIniciativa(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		return "bp/iniciativas-ciudadanas/consultar-iniciativa-ciudadana.html";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaIniciativaCiudadanaByPage")
	public ResponseEntity listaIniciativaCiudadna(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {
		
		BpIniciativaCiudadanaDTO iniciativa = new BpIniciativaCiudadanaDTO();
	
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		String search6 = req.getParameter("columns[6][search][value]");
		String search7 = req.getParameter("columns[7][search][value]");
		String search8 = req.getParameter("columns[8][search][value]");
		String search9 = req.getParameter("columns[9][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		
		
		iniciativa.setCodigoEntidad(search0);
		
		if (!("".equals(search1)) && search1.matches("[0-9]*")) {
			iniciativa.setCodigoIn(Long.parseLong(search1));
		}
		
		iniciativa.setNombrePlanDesarrollo(search2); // error
		
		iniciativa.setFechaCodigo(search3);
		if (!("".equals(search4))) {
			iniciativa.setNumeroRad(Long.parseLong(search4));
		}
	
		iniciativa.setFechaRad(search5); // error
		iniciativa.setNombreSector(search6);
		iniciativa.setNombreLineaInversion(search7); // error
		iniciativa.setNombreEstado(search8); // error
    	iniciativa.setNombreCodigoProyecto(search9); // error
//		
	
		
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			iniciativa.setTamanioPagina(Integer.MAX_VALUE);
			iniciativa.setPagina(pageNo - 1);
		} else {
			iniciativa.setTamanioPagina(length);
			iniciativa.setPagina(pageNo - 1);
		}
		
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(iniciativa);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, iniciativa.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, IniciativaCiudadanaController.class);
		}
		
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BP_INCIATIVA_CIUDADANA_OBTENER_TODOS,
				NHSPDDConstantes.TIPO_CORE);
		System.out.println(res.getLstObjectsDTO());
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<BpIniciativaCiudadanaDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<BpIniciativaCiudadanaDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);
	}

	
	
	@GetMapping("/crear-iniciativa")
	public String crearIniciativa(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			
			Model model) {
		RespuestaApiDTO<HistorialSectorialDTO> lista;
		RespuestaApiDTO<EntidadSeguridadDTO> entidad;
		System.out.println(codigoEntidadUsuario);
		if(codigoEntidadUsuario.equals("Interno")) {
			entidad= entidadRest.get("/api/entidad/Entidad 1", new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
			lista = listaHistorial.get("/api/historial_sectorial/consultar_por_codigo_entidad/"+"Entidad 1", new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
		}else {
			entidad= entidadRest.get("/api/entidad/"+codigoEntidadUsuario, new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
			lista = listaHistorial.get("/api/historial_sectorial/consultar_por_codigo_entidad/"+codigoEntidadUsuario, new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
		}
		PddDTO pddDTO = pddRest.get(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_VIGENTE, new ParameterizedTypeReference<PddDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO listaLineas = listaGenerico.get("/administracion_lista_compuesta/obtener_por_sector/"+lista.getObjetos().get(0).getDescripcion().toLowerCase(),new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO listaTerritorializacion = listaGenerico.get("/administracion_lista_compuesta/obtener_id_localidad/"+codigoEntidadUsuario, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO condiciones = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Condicion poblacional", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO gruposEtarios = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Grupos etarios", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO origen = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Origen", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estado iniciativa", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		ConsecutivoDTO consecutivoDTO = new ConsecutivoDTO();
		consecutivoDTO.setCodigoEntidad(entidad.getObjetos().get(0).getCodigoDistrital());
		consecutivoDTO.setIdPlanDesarrollo(pddDTO.getIdPlanDesarrollo());
		consecutivoDTO.setNombre("Iniciativa Ciudadana");
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(consecutivoDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConsecutivoDTO respuesta = consecutivoRest.post(parametro, new ParameterizedTypeReference<ConsecutivoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_CONSECUTIVO_POR_PLAN, NHSPDDConstantes.TIPO_CORE);
		System.out.println(respuesta.toString());
		model.addAttribute("condiciones", condiciones.getLstObjectsDTO());
		model.addAttribute("gruposEtarios", gruposEtarios.getLstObjectsDTO());
		model.addAttribute("origen", origen.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("listaTerritorios",listaTerritorializacion.getLstObjectsDTO());
		model.addAttribute("listaLineas", listaLineas.getLstObjectsDTO());
		model.addAttribute("entidad",entidad.getObjetos().get(0));
		model.addAttribute("pddDTO", pddDTO);
		model.addAttribute("consecutivo", respuesta);
		return "bp/iniciativas-ciudadanas/crear-iniciativa-ciudadana.html";
	}
	
	@GetMapping("/consulta-detallada-iniciativa")
	public String consultaDetalladaIniciativa(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			
			Model model) {
		
		return "bp/iniciativas-ciudadanas/consulta-detallada-iniciativa.html";
	}
	
	@PostMapping("/registar-iniciativa")
	public String registrarIniciativa(BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO,RedirectAttributes redirectAttributes) {
		
		
		System.out.println(bpIniciativaCiudadanaDTO.toString());
		if(bpIniciativaCiudadanaDTO.getIdIniciativa()==null) {
				
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(bpIniciativaCiudadanaDTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		BpIniciativaCiudadanaDTO respuesta = iniciativaRest.post(parametro, new ParameterizedTypeReference<BpIniciativaCiudadanaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_REGISTRAR, NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("messageRespuesta", respuesta.getMsgRespuesta());
		return "redirect:/crear-iniciativa";
		}else {
			
			BpIniciativaCiudadanaDTO respuesta = iniciativaRest.put(bpIniciativaCiudadanaDTO, BpIniciativaCiudadanaDTO.class, NHSPDDConstantes.CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_MODIFICAR, 
					NHSPDDConstantes.TIPO_CORE);
			redirectAttributes.addFlashAttribute("messageRespuesta", respuesta.getMsgRespuesta());
			return "redirect:/crear-iniciativa";
		}
	}
	
	@PostMapping("/modificar-iniciativa")
	public String modificarIniciativa(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO,RedirectAttributes redirectAttributes) {
		
		BpIniciativaCiudadanaDTO componente = iniciativaRest.get("/iniciativa_ciudadana/obtener/"+bpIniciativaCiudadanaDTO.getIdIniciativa(), new ParameterizedTypeReference<BpIniciativaCiudadanaDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		RespuestaApiDTO<HistorialSectorialDTO> lista;
		RespuestaApiDTO<EntidadSeguridadDTO> entidad;
		
		if(codigoEntidadUsuario.equals("Interno")) {
			entidad= entidadRest.get("/api/entidad/Entidad 1", new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
			lista = listaHistorial.get("/api/historial_sectorial/consultar_por_codigo_entidad/"+"Entidad 1", new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
		}else {
			entidad= entidadRest.get("/api/entidad/"+codigoEntidadUsuario, new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
			lista = listaHistorial.get("/api/historial_sectorial/consultar_por_codigo_entidad/"+codigoEntidadUsuario, new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
			}, NHSPDDConstantes.TIPO_SEGURIDAD);
		}
		PddDTO pddDTO = pddRest.get(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_VIGENTE, new ParameterizedTypeReference<PddDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO listaLineas = listaGenerico.get("/administracion_lista_compuesta/obtener_por_sector/"+lista.getObjetos().get(0).getDescripcion().toLowerCase(),new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO listaTerritorializacion = listaGenerico.get("/administracion_lista_compuesta/obtener_localidad/", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO condiciones = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Condicion poblacional", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO gruposEtarios = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Grupos etarios", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO origen = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Origen", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estado iniciativa", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("condiciones", condiciones.getLstObjectsDTO());
		model.addAttribute("gruposEtarios", gruposEtarios.getLstObjectsDTO());
		model.addAttribute("origen", origen.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("listaTerritorios",listaTerritorializacion.getLstObjectsDTO());
		model.addAttribute("listaLineas", listaLineas.getLstObjectsDTO());
		model.addAttribute("entidad",entidad.getObjetos().get(0));
		model.addAttribute("pddDTO", pddDTO);
		model.addAttribute("editar", 1);
		model.addAttribute("iniciativaDTO", componente);
		return "bp/iniciativas-ciudadanas/crear-iniciativa-ciudadana.html";
	}
	
	@PostMapping("/eliminar-iniciativa")
	public String eliminarIniciativa(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO,RedirectAttributes redirectAttributes) {
		
			BpIniciativaCiudadanaDTO respuesta = iniciativaRest.delete("/iniciativa_ciudadana/eliminar/"+bpIniciativaCiudadanaDTO.getIdIniciativa(), new ParameterizedTypeReference<BpIniciativaCiudadanaDTO>() {
			}, NHSPDDConstantes.TIPO_CORE);
			redirectAttributes.addFlashAttribute("messageRespuesta", respuesta.getMsgRespuesta());
		return "redirect:/consultar-iniciativa-ciudadana";
	}
	
	@PostMapping("/ver-consultar-detallada-iniciativa")
	public String verConsultaDetalladaIniciativa (Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO,RedirectAttributes redirectAttributes) {
		BpIniciativaCiudadanaDTO componente = iniciativaRest.get("/iniciativa_ciudadana/obtener/"+bpIniciativaCiudadanaDTO.getIdIniciativa(), new ParameterizedTypeReference<BpIniciativaCiudadanaDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO proyectosAsociados = listaGenerico.get("/bpiniciativaciudadana/obtener_todos_bp_proy_inv_iniciativa_por_id_iniciativa/"+componente.getIdIniciativa(), new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("iniciativaDTO", componente);
		model.addAttribute("proyectos", proyectosAsociados.getLstObjectsDTO());
		return "bp/iniciativas-ciudadanas/consulta-detallada-iniciativa.html";
	}
}
