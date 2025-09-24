package co.gov.sdp.spdd.web.ip.controller.analisis.politica;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta.ComponenteGastoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion","pddDTO", "idCompromiso" })
public class AnalisisPoliticaController {
	
	
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<PddProblematicaDTO> problematicaRest;
	
	@Autowired
	private MetodosRest<PddPrbPoblacionDTO> poblacionRest;
	
	@Autowired
	private MetodosRest<PddPrbValoracionDTO> valoracionRest;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;

	@GetMapping("/consultar-politica")
	public String consultarAnalisisPolitica(@ModelAttribute("tokenSesionSeguridad") String token, Model model) {
		
		listaGenerico.agregarToken(token);
		return "ip/analisis-politica/consultar-politica.html";
	}
	
	@PostMapping("/obtener-politica")
	public String obtenerAnalisisPolitica(Model model,PddDTO pddDTO) {
	
		model.addAttribute("pddDTO",pddDTO);
		model.addAttribute("idCompromiso", "");
		return "redirect:/ver-politica";
	}
	
	@GetMapping("/ver-politica")
	public String verPolitica(Model model,@ModelAttribute("pddDTO") PddDTO pddDTO, @ModelAttribute("idCompromiso") String idCompromiso) {
		
		GenericoDTO tematicasCompromiso = listaGenerico.get("/administracion_lista_simple/obtener_tematicas_por_id_plan/"+pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO compro = listaGenerico.get("/ipformulacion/obtener_pdd_compromiso_por_id_plan/"+pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO localizacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Localizacion", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO localidades = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Localidad", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO listaTerritorializacion = listaGenerico.get("/administracion_lista_compuesta/obtener_localidad/", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO listaCompetencias = listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_TODAS_PDD_COMPETENCIAS_ASOCIADAS, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO sectores = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Sectores", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO dimensiones = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Dimension", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO problematicas = listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICA_TODOS, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO indicadorProblematica = listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADORES_META_PRODUCTO,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		

		model.addAttribute("compromisoDTO", compro.getLstObjectsDTO());
		model.addAttribute("tematicaCompromiso", tematicasCompromiso.getLstObjectsDTO());
		model.addAttribute("localizacion",localizacion.getLstObjectsDTO());
		model.addAttribute("localidades", localidades.getLstObjectsDTO());
		model.addAttribute("listaTerritorializacion", listaTerritorializacion.getLstObjectsDTO());
		model.addAttribute("problematicas", problematicas.getLstObjectsDTO());
		model.addAttribute("competencias", listaCompetencias.getLstObjectsDTO());
		model.addAttribute("sectores", sectores.getLstObjectsDTO());
		model.addAttribute("dimensiones", dimensiones.getLstObjectsDTO());

		model.addAttribute("listaIndicadorProblematica", indicadorProblematica.getLstObjectsDTO());

		return "ip/analisis-politica/ver-compromisos-politica.html";
	}
	
	@PostMapping("/agregar-problematica")
	@ResponseBody
	public PddProblematicaDTO agregarProblematica(PddProblematicaDTO pddProblematicaDTO)  {
		String parametro="";
		try {
			parametro = mapper.writeValueAsString(pddProblematicaDTO);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
		}
		PddProblematicaDTO respuesta = problematicaRest.post(parametro, new ParameterizedTypeReference<PddProblematicaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PROBLEMATICA, NHSPDDConstantes.TIPO_CORE);
		return respuesta;
	}
	
	@GetMapping("/consultar-problematicas/{id}")
	@ResponseBody
	public GenericoDTO consultarProblematicas(@PathVariable("id") Long idCompromiso) {
		
		return listaGenerico.get("/ipformulacion/obtener_problematica_por_id_compromiso/"+idCompromiso, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-problematica")
	@ResponseBody
	public PddProblematicaDTO modificarProblematica(PddProblematicaDTO pddProblematicaDTO) {
		System.out.println(pddProblematicaDTO.toString());
		return problematicaRest.put(pddProblematicaDTO, PddProblematicaDTO.class, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_PROBLEMATICA,
				NHSPDDConstantes.TIPO_CORE);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listarPoblacionPorId/{id}")
	public ResponseEntity listarPoblacionPorIdProblematica(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("id") Long idProblematica) {
		
		PddPrbPoblacionDTO pddPrbPoblacionDTO = new PddPrbPoblacionDTO();
		GenericoDTO res = new GenericoDTO();
		pddPrbPoblacionDTO.setIdProblematica(idProblematica);
		String search0 = req.getParameter("columns[0][search][value]");
		
		pddPrbPoblacionDTO.setDescripcion(search0);
		int pageNo = (start) / length + 1;
		
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			pddPrbPoblacionDTO.setTamanioPagina(Integer.MAX_VALUE);
			pddPrbPoblacionDTO.setPagina(pageNo - 1);
		} else {
			pddPrbPoblacionDTO.setTamanioPagina(length);
			pddPrbPoblacionDTO.setPagina(pageNo - 1);
		}
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddPrbPoblacionDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddPrbPoblacionDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POBLACION_POR_ID_PROBLEMATICA, NHSPDDConstantes.TIPO_CORE);
		
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<PddPrbPoblacionDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<PddPrbPoblacionDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);
		
		return ResponseEntity.ok(dataTable);
		
	}
	
	@GetMapping("/consultarPoblacionYactores/{idProblematica}")
	@ResponseBody
	public GenericoDTO consultarPoblacionActores(@PathVariable("idProblematica") Long idProblematica) {
		PddPrbPoblacionDTO problematica = new PddPrbPoblacionDTO();
		GenericoDTO res;
		problematica.setIdProblematica(idProblematica);
		problematica.setPagina(0);
		problematica.setTamanioPagina(Integer.MAX_VALUE);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(problematica);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, problematica.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, AnalisisPoliticaController.class);
		}
		res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POBLACION_POR_ID_PROBLEMATICA, NHSPDDConstantes.TIPO_CORE);
         
		return res;
	}
	
	@PostMapping("/registrar-poblacion")
	@ResponseBody
	public PddPrbPoblacionDTO registrarPddPrbPoblacion(PddPrbPoblacionDTO pddPrbPoblacionDTO) {
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddPrbPoblacionDTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return poblacionRest.post(parametro, new ParameterizedTypeReference<PddPrbPoblacionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PBR_POBLACION, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-poblacion")
	@ResponseBody
	public PddPrbPoblacionDTO modificarPddPrbPoblacion(PddPrbPoblacionDTO pddPrbPoblacionDTO) {
		
		return poblacionRest.put(pddPrbPoblacionDTO, PddPrbPoblacionDTO.class, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PBR_POBLACION, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/eliminar-poblacion/{id}")
	@ResponseBody
	public PddPrbPoblacionDTO eliminarPddPrbPoblacion(@PathVariable("id") Long idPoblacion) {
		return poblacionRest.delete("/ipformulacion/eliminar_pbr_poblacion/"+idPoblacion, new ParameterizedTypeReference<PddPrbPoblacionDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/obtener-problematica/{id}")
	@ResponseBody
	public PddProblematicaDTO obtenerTerritoriosPorIdLocalidad(@PathVariable("id") Long idProblematica) {
		return problematicaRest.get("/ipformulacion/obtener_problematica/"+idProblematica, new ParameterizedTypeReference<PddProblematicaDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/crear-prb-valoracion")
	@ResponseBody
	public PddPrbValoracionDTO registrarValoracion(PddPrbValoracionDTO pddPrbValoracionDTO) {
		String parametro = "";
		System.out.println(pddPrbValoracionDTO.toString());
		try {
			parametro = mapper.writeValueAsString(pddPrbValoracionDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valoracionRest.post(parametro, new ParameterizedTypeReference<PddPrbValoracionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_PRB_VALORACION, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/obtener-valoracion/{idProblematica}/{momento}")
	@ResponseBody
	public PddPrbValoracionDTO obtenerValoracionPorIdYMomento(@PathVariable("idProblematica") Long idProblematica,@PathVariable("momento") Long momento) {
		PddPrbValoracionDTO pddPrbValoracionDTO = new PddPrbValoracionDTO();
		pddPrbValoracionDTO.setIdProblematica(idProblematica);
		pddPrbValoracionDTO.setMomento(momento);
		String parametro = "";
		
		try {
			parametro = mapper.writeValueAsString(pddPrbValoracionDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valoracionRest.post(parametro, new ParameterizedTypeReference<PddPrbValoracionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_PR_VALORACION_POR_ID_PROBLEMATICA_Y_MOMENTO, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-valoracion")
	@ResponseBody
	public PddPrbValoracionDTO modificarPddPrbValoracion(PddPrbValoracionDTO pddPrbValoracionDTO) {
		String parametro = "";
		
		try {
			parametro = mapper.writeValueAsString(pddPrbValoracionDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return valoracionRest.post(parametro, new ParameterizedTypeReference<PddPrbValoracionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_PRB_VALORACION, NHSPDDConstantes.TIPO_CORE);
	}
}
