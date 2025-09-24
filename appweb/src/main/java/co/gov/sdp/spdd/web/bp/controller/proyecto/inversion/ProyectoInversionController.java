


package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.ip.controller.componentes.estructura.pdd.ComponentesController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "proyecto" })
public class ProyectoInversionController {

	public static final String TIPO_CONSULTA = "tipoConsulta";
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<EntidadDTO> listaEntidad;
	
	@Autowired
	private MetodosRest<BpProyectoInversionDTO> listaProyecto;
	
	@Autowired
	private MetodosRest<PddDTO> pddRest;
	
	@Autowired
	private MetodosRest<ConsecutivoDTO> consecutivoRest;
	
	@Autowired
	private MetodosRest<PdlDTO> pdlRest;
	
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private ObjectMapper mapper;

	@GetMapping("/consultar-proyecto-entidad/{id}")
	public String consultarProyecto(@PathVariable("id") String parametro, Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			RedirectAttributes redirectAttributes) {

		switch (parametro) {
		case "1":
			
			redirectAttributes.addFlashAttribute(TIPO_CONSULTA, "1");
			break;
		case "2":
			redirectAttributes.addFlashAttribute(TIPO_CONSULTA, "2");
			break;
		case "3":
			redirectAttributes.addFlashAttribute(TIPO_CONSULTA, "3");
			break;
		case "4":
			redirectAttributes.addFlashAttribute(TIPO_CONSULTA, "4");
			break;
		}

		return "redirect:/tablaConsultaProyecto";

	}

	@GetMapping("/tablaConsultaProyecto")
	public String consultarProyectoVer(Model model) {
		BpProyectoInversionDTO bp = new BpProyectoInversionDTO();
		String parametro = "";
		try {
	    parametro = mapper.writeValueAsString(bp);
		} catch (JsonProcessingException e) {
		spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, bp.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("listBp", res.getLstObjectsDTO());
		return "bp/general/consultar-proyecto-entidad";

	}
	
	@PostMapping("/modificar-proyecto-inscripcion")
    public String modificarProyectoInversion(BpProyectoInversionDTO proyecto, Model model) {
		model.addAttribute("proyecto", proyecto);
		return "redirect:/editar-proyecto-inversion";
		
	}
	
	@GetMapping("/editar-proyecto-inversion")
	     public String getProyectoInversion(@ModelAttribute("proyecto") BpProyectoInversionDTO proyecto, 
	    		 Model model, @ModelAttribute("codigoEntidadUsuario")String entidad) {

		model.addAttribute("proyecto", proyecto);
		EntidadDTO enti = new EntidadDTO();
		
		
		GenericoDTO tipoProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TiposProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO etapaProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/EtapasProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO lineaInversionLocal = listaGenerico.get("/administracion_lista_simple/obtener_argumento/LineaInversionLocal",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		if (entidad.equals("Interno")) {
			enti = listaEntidad.get("/administracion/obtener_por_id/"+NHSPDDConstantes.CODIGO_ENTIDAD_INTERNO,
					new ParameterizedTypeReference<EntidadDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
			
		}else {
			enti = listaEntidad.get("/administracion/obtener_por_nombre/"+entidad,
					new ParameterizedTypeReference<EntidadDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
		}
		PddDTO pdd = pddRest.get("/ipplandistrital/obtener_pdd_por_id/"+proyecto.getIdPlanDesarrolloDistrital(),
				new ParameterizedTypeReference<PddDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		System.out.println("dto pdd"+pdd.toString());
		
		String clasific = enti.getStringClasificacion().toLowerCase();
		if (clasific.equals("alcaldía")) {
			System.out.println("esta entrando a una alcaldia");
			
			
		 PdlDTO	pdl = pdlRest.get("/ipplandistrital/obtener_pdl/"+proyecto.getIdPlanDesarrolloDistrital(),
					new ParameterizedTypeReference<PdlDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
		 
//		 model.addAttribute("pddVigente", pdl); 
		} else {
//		 model.addAttribute("pddVigente", pdd);	
		}
		
		LineaDeInversionDTO lineaInversion = new LineaDeInversionDTO();
        lineaInversion.setPagina(0);
	    lineaInversion.setTamanioPagina(Integer.MAX_VALUE);
	    String parametro = "";
		try {
			parametro = mapper.writeValueAsString(lineaInversion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, lineaInversion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		
		GenericoDTO lineaInve = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION, NHSPDDConstantes.TIPO_CORE);
		 System.out.println(lineaInve.getMsgRespuesta());
		
		model.addAttribute("listLineaInversion", lineaInve.getLstObjectsDTO());
		model.addAttribute("listaEntidadProy", enti);
		model.addAttribute("listTipoProyecto", tipoProyecto.getLstObjectsDTO());
		model.addAttribute("listLineaLocal", lineaInversionLocal.getLstObjectsDTO());
		model.addAttribute("listEtapaProyecto", etapaProyecto.getLstObjectsDTO());
		model.addAttribute("editar", "1");
		
		return "bp/inscripcion-proyectos/crear-proyecto-entidad.html";
	}
	
	
	
	
    @GetMapping("registrar-proyecto")
    public String registrarProyectoInversion(Model model) {
  
    	return "redirect:/crear-proyecto-entidad";
    }
	
	@GetMapping("/crear-proyecto-entidad")
	public String crearProyecto(@ModelAttribute("codigoEntidadUsuario")String entidad, Model model) {
		
		EntidadDTO enti = new EntidadDTO();
	

		GenericoDTO tipoProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TiposProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO etapaProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/EtapasProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO lineaInversionLocal = listaGenerico.get("/administracion_lista_simple/obtener_argumento/LineaInversionLocal",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
<<<<<<< HEAD
		GenericoDTO gruposEtarios = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Grupos etarios",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO listaSectoresCondiciones = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Grupo Etnicos",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
=======
		GenericoDTO fuenteFinanciacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/FuentesFinanciacion", 
				new ParameterizedTypeReference<GenericoDTO>() {}, NHSPDDConstantes.TIPO_CORE);
>>>>>>> developer
		
//		RespuestaApiDTO<EntidadSeguridadDTO> entidadSeguridad = api.get("/api/entidad/consultartodos",
//				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
//				}, NHSPDDConstantes.TIPO_SEGURIDAD);
		
		
		if (entidad.equals("Interno")) {
			enti = listaEntidad.get("/administracion/obtener_por_id/"+NHSPDDConstantes.CODIGO_ENTIDAD_INTERNO,
					new ParameterizedTypeReference<EntidadDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
			
		}else {
			enti = listaEntidad.get("/administracion/obtener_por_nombre/"+entidad,
					new ParameterizedTypeReference<EntidadDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
		}
		
		PddDTO pddVigente = pddRest.get(NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_VIGENTE,
				new ParameterizedTypeReference<PddDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
			
		ConsecutivoDTO consecutivo = new ConsecutivoDTO();
		consecutivo.setIdPlanDesarrollo(pddVigente.getIdPlanDesarrollo());
		consecutivo.setCodigoEntidad(enti.getCodigoEntidad());
		consecutivo.setNombre(NHSPDDConstantes.CORE_CONSECUTIVO_TABLA_BP_PROYECTO_INVERSION);
		
		String parametro = "";
		try {
	    parametro = mapper.writeValueAsString(consecutivo);
		} catch (JsonProcessingException e) {
		spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, consecutivo.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		ConsecutivoDTO numConse = consecutivoRest.post(parametro, new ParameterizedTypeReference<ConsecutivoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_CONSECUTIVO_POR_PLAN, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO niveles= listaGenerico.get("/ipplandistrital/obtener_todos_pdd_nivel/"+pddVigente.getIdPlanDesarrollo(),
                new ParameterizedTypeReference<GenericoDTO>() {
                }, NHSPDDConstantes.TIPO_CORE);
		PddNivelAtributoDTO pddNivel = new PddNivelAtributoDTO();	
		
	    TypeReference<PddNivelDTO> type = new TypeReference<PddNivelDTO>() {
		};
		
			
		PddNivelDTO	 list1 = mapper.convertValue(niveles.getLstObjectsDTO().get(0), type);
		PddNivelDTO	 list2 = mapper.convertValue(niveles.getLstObjectsDTO().get(1), type);
		PddNivelDTO	 list3 = mapper.convertValue(niveles.getLstObjectsDTO().get(2), type);
		pddNivel.setIdPddNivel(list1.getIdPddNivel());
		
		parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(

					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}

		GenericoDTO atributosNivel1 = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);
		
		pddNivel.setIdPddNivel(list2.getIdPddNivel());

		
		parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}

		GenericoDTO atributosNivel2 = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);
		
		pddNivel.setIdPddNivel(list3.getIdPddNivel());
		
		parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}

		GenericoDTO atributosNivel3 = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);
		BpIniciativaCiudadanaDTO peticion = new BpIniciativaCiudadanaDTO();
		peticion.setCodigoEntidad(enti.getCodigoEntidad());
		peticion.setPagina(0);
		peticion.setTamanioPagina(Integer.MAX_VALUE);
		 parametro = "";
		
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, peticion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		
		GenericoDTO iniciativas = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_FILTRO_BP_INICIATIVA_CIUDADANA_VIABLE, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO localizacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Localizacion", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO unidadDeMedida = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Unidades", new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		

        LineaDeInversionDTO lineaInversion = new LineaDeInversionDTO();
        lineaInversion.setPagina(0);
	    lineaInversion.setTamanioPagina(Integer.MAX_VALUE);
		try {
			parametro = mapper.writeValueAsString(lineaInversion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, lineaInversion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		
		GenericoDTO lineaInve = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION, NHSPDDConstantes.TIPO_CORE);
		
		
<<<<<<< HEAD
			
		System.out.println(enti.toString());
=======
		model.addAttribute("listLineaInversion", lineaInve.getLstObjectsDTO());
		model.addAttribute("numConsecutivo", numConse.getSecuencia());
>>>>>>> developer
		model.addAttribute("listaEntidadProy", enti);
		model.addAttribute("listTipoProyecto", tipoProyecto.getLstObjectsDTO());
		model.addAttribute("listEtapaProyecto", etapaProyecto.getLstObjectsDTO());
		model.addAttribute("listLineaLocal", lineaInversionLocal.getLstObjectsDTO());
		model.addAttribute("listfuenteFinanciacion",fuenteFinanciacion.getLstObjectsDTO());
		model.addAttribute("pddVigente", pddVigente);
<<<<<<< HEAD
		model.addAttribute("numConsecutivo", res.getSecuencia());
		System.err.println(gruposEtarios.getLstObjectsDTO());
		model.addAttribute("listaGruposEtarios",gruposEtarios.getLstObjectsDTO());
		model.addAttribute("listaSectoresCondiciones",listaSectoresCondiciones.getLstObjectsDTO());
=======
		model.addAttribute("agregar", "1");
		model.addAttribute("atributosNivel1", atributosNivel1.getLstObjectsDTO());
		model.addAttribute("atributosNivel2", atributosNivel2.getLstObjectsDTO());
		model.addAttribute("atributosNivel3", atributosNivel3.getLstObjectsDTO());
		model.addAttribute("iniciativas", iniciativas.getLstObjectsDTO());
		model.addAttribute("localizacion", localizacion.getLstObjectsDTO());
		model.addAttribute("unidades",unidadDeMedida.getLstObjectsDTO());
		
>>>>>>> developer
		
		
		return "bp/inscripcion-proyectos/crear-proyecto-entidad.html";
	}
	
	@PostMapping("/agregarIdentificacionProyecto")
	@ResponseBody
    public BpProyectoInversionDTO agregarIdentificacionProyecto(BpProyectoInversionDTO proyecto) {
		System.out.println("esto es lo que esta llegando"+proyecto.toString());
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(proyecto);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, proyecto.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		return listaProyecto.post(parametro, new ParameterizedTypeReference<BpProyectoInversionDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE);

	
	}
    
	@PostMapping("/editarIdentificacionProyecto")
	@ResponseBody
    public BpProyectoInversionDTO editarIdentificacionProyecto(BpProyectoInversionDTO proyecto) {
		System.out.println("esto es lo que esta llegando edit"+proyecto.toString());
		
		
		BpProyectoInversionDTO proyectoDto =  listaProyecto.get("/bpproyinv/obtener_bp_proyecto_inversion_por_id/"+proyecto.getIdProyInversion(),
				new ParameterizedTypeReference<BpProyectoInversionDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		proyectoDto.setNombreProyecto(proyecto.getNombreProyecto());
		proyectoDto.setCodigoBpin(proyecto.getCodigoBpin());
		proyectoDto.setNombreBpin(proyecto.getNombreBpin());
		proyectoDto.setIdsTipoProy(proyecto.getIdsTipoProy());
		proyectoDto.setIdLsEstado(proyecto.getIdLsEtapa());
		proyectoDto.setNombrePoai(proyecto.getNombrePoai());
		proyectoDto.setIdLsSectorAl(proyecto.getIdLsSectorAl());
		
		return listaProyecto.put(proyectoDto, BpProyectoInversionDTO.class,
			"/bpproyinv/modificar_bp_proyecto_inversion", NHSPDDConstantes.TIPO_CORE);	
		
	}
	
	@GetMapping("eliminarProyecto/{idProyecto}")
	public String eliminarProyecto(@PathVariable("idProyecto") String id) {
		 listaProyecto.delete("/bpproyinv/eliminar_bp_proyecto_inversion/"+id,
					new ParameterizedTypeReference<BpProyectoInversionDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
		
		 return  "redirect:/tablaConsultaProyecto";
	}
    
	@PostMapping("consultar-proyecto")
	public String consultarProyecto(BpProyectoInversionDTO proyecto, Model model) {
		model.addAttribute("proyecto", proyecto);
		return "redirect:/consultar-inscripcion-proyecto";
	}
	
	@GetMapping("/consultar-inscripcion-proyecto")
	public String consultarInsProyecto(@ModelAttribute("proyecto") BpProyectoInversionDTO proyecto, Model model) {
		GenericoDTO tipoProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TiposProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO etapaProyecto = listaGenerico.get("/administracion_lista_simple/obtener_argumento/EtapasProyectoInversion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("listTipoProyecto", tipoProyecto.getLstObjectsDTO());
		model.addAttribute("listEtapaProyecto", etapaProyecto.getLstObjectsDTO());
		return "bp/inscripcion-proyectos/consulta-proyecto-entidad";
	}

	@GetMapping("/asociar-aportes-ciudadanos")
	public String asociarAportesCiudadanos() {
		return "bp/proyectos-entidad/asociar-aportes-ciudadanos.html";
	}

	@GetMapping("/versionamiento-proyecto")
	public String versionProyecto() {
		return "bp/proyectos-entidad/versionamiento-proyecto.html";
	}
	

	
}
