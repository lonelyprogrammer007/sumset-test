package co.gov.sdp.spdd.web.afs.controller.presupuesto;

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

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class PresupuestoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	public static final String REDIRECT_CONSULTA_PRE = "redirect:/consultar-info-pre";
	public static final String EXPRESION_REGULAR_NUMERO = "[0-9]*";
	public static final String MESSAGE= "message";
	public static final String ID_ENTIDAD= "idEntidad";
	public static final String ID_DESARROLLO= "idDesarrollo";
			
	@Autowired
	private MetodosRest<InformacionPresupuestalDTO> presupuestoDTORest;

	@GetMapping("/consultar-info-pre")
	public String consultarPresupuesto(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		RespuestaApiDTO<EntidadSeguridadDTO> prueba = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);

		GenericoDTO entidades = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("entidad", prueba.getObjetos());
		model.addAttribute("entidades", entidades.getLstObjectsDTO());

		GenericoDTO planDesarrollo = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_PDD_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		
		model.addAttribute("planDesarrollo", planDesarrollo.getLstObjectsDTO());

		listaGenerico.getHeaders().add("Authorization", NHSPDDConstantes.TOKEN_PREFIX + tokenSesionSeguridad);
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/presupuesto/consultar-info-pres";
			}

		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";

	}

	@PostMapping("/filtro-presupuestal")
	public String filtroInformacionPresupuestal(RedirectAttributes redirectAttributes,
			InformacionPresupuestalDTO filtro) {
		redirectAttributes.addFlashAttribute("filtro", filtro);
		return REDIRECT_CONSULTA_PRE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listPresupuestoByPage/{idplan}/{entidad}")
	public ResponseEntity listarInformacionPresupuestal(@RequestParam("draw") int draw,
			@RequestParam("start") int start, @RequestParam("length") int length, HttpServletRequest req,
		  @PathVariable("idplan") String idPlan, @PathVariable("entidad") String entidad) {
		
		InformacionPresupuestalDTO presupuesto = new InformacionPresupuestalDTO();
	    System.out.println(idPlan+"ab"+entidad);
		if ("-1".equals(idPlan) && "-1".equals(entidad)) {
			presupuesto.setIdPlanDesarrollo(-1L);
			presupuesto.setCodigoDistrital("");
		}else {
			if("-1".equals(entidad)) {
				presupuesto.setCodigoDistrital(null);
				 presupuesto.setIdPlanDesarrollo(Long.parseLong(idPlan));
			}else {
				  if("-1".equals(idPlan)) {
					  presupuesto.setCodigoDistrital(entidad);  
					  presupuesto.setIdPlanDesarrollo(null);
				  }
				  else {
				  presupuesto.setCodigoDistrital(entidad);
			      presupuesto.setIdPlanDesarrollo(Long.parseLong(idPlan));
				  }
			}
		}

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
		String search10 = req.getParameter("columns[10][search][value]");
		String search11 = req.getParameter("columns[11][search][value]");
		String search12 = req.getParameter("columns[12][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		
		
	   if ("".equals(search0)) {
		 presupuesto.setYear(null); 

		}else 
			if (search0.matches(EXPRESION_REGULAR_NUMERO)) {
			presupuesto.setYear(Long.parseLong(search0));
			}
	 	
	  if ("".equals(search1)) {
		 presupuesto.setMes(null);
	  }else 
		  if (search1.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setMes(Long.parseLong(search1));
		  }   
	      
          presupuesto.setCodigoClasificacionPresupuestal(search2);
          presupuesto.setNombreProyecto(search5);
          
       if("".equals(search3)) {
    	   presupuesto.setCodigoProyecto(null);

       } else 
    	   if (search3.matches(EXPRESION_REGULAR_NUMERO)) {
    		   presupuesto.setCodigoProyecto(Long.parseLong(search3));  
       }
         
          
	  if ("".equals(search4)) {
		  presupuesto.setCodigoInterno(null);
		  
	  }else  if (search4.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setCodigoInterno(Long.parseLong(search4));
	  }
	 
	  if("".equals(search6)) {
		  presupuesto.setApropiacionDefinitiva(null);
	  }else if (search6.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setApropiacionDefinitiva(Double.parseDouble(search6));
	  }

	  if("".equals(search7)) {
		  presupuesto.setEjecucionVigencia(null);
	  }else if (search7.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setEjecucionVigencia(Double.parseDouble(search7));
	  }

	  if ("".equals(search8)) {
		  presupuesto.setGirosVigencia(null);
	  }else if (search8.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setGirosVigencia(Double.parseDouble(search8));
	  }
	  if("".equals(search9)) {
		  presupuesto.setRecursosSuspendidos(null);
	  }else if (search9.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setRecursosSuspendidos(Double.parseDouble(search9));
	  }
	  if("".equals(search10)) {
		  presupuesto.setConstitucionReserva(null);
	  }else if (search10.matches(EXPRESION_REGULAR_NUMERO)) {
		  presupuesto.setConstitucionReserva(Double.parseDouble(search10));
	  }
	  if("".equals(search11)) {
		  presupuesto.setApropiacionReserva(null);
	  }else if (search11.matches(EXPRESION_REGULAR_NUMERO)){
		  presupuesto.setApropiacionReserva(Double.parseDouble(search11));
	  }
	  if("".equals(search12)) {
		  presupuesto.setEjecucionGiroReservas(null);
	  }else if (search12.matches(EXPRESION_REGULAR_NUMERO)){
		  presupuesto.setEjecucionGiroReservas(Double.parseDouble(search12));
	  }
	  
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			presupuesto.setTamanioPagina(Integer.MAX_VALUE);
			presupuesto.setPagina(pageNo - 1);
		} else {
			presupuesto.setTamanioPagina(length);
			presupuesto.setPagina(pageNo - 1);
		}
		if ("codigoClasificacionPresupuestal".equals(name)) {
			name = "codigoClasifPresupuestal";
		}
		presupuesto.setColumnaOrdenar(name);
		presupuesto.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(presupuesto);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    e.getMessage(),
                    NHSPDDConstantes.SEVERIDAD_FATAL, PresupuestoController.class);
			
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_TODOS,
				NHSPDDConstantes.TIPO_CORE);

	 
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<InformacionPresupuestalDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest,
				res.getTotal());

		DataTable<InformacionPresupuestalDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/crear_info_presupuestal")
	public String crearInfoPresupuestal(Model model, InformacionPresupuestalDTO presupuestal,
			RedirectAttributes redirectAttributes) {
        System.out.println("info presu"+presupuestal.toString());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(presupuestal);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, presupuestal.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, PresupuestoController.class);
		}
		InformacionPresupuestalDTO pres = presupuestoDTORest.post(parametro,
				new ParameterizedTypeReference<InformacionPresupuestalDTO>() {
				}, NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_POST_CREAR_INFORMACION_PRESUPUESTAL,
				NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute(MESSAGE, pres.getMsgRespuesta());
		redirectAttributes.addFlashAttribute(ID_ENTIDAD, presupuestal.getCodigoDistrital());
		System.out.println(presupuestal.getCodigoDistrital()+presupuestal.getIdPlanDesarrollo());
		redirectAttributes.addFlashAttribute(ID_DESARROLLO, presupuestal.getIdPlanDesarrollo());
		return REDIRECT_CONSULTA_PRE;
	}

	@PostMapping("/editar_info_presupuestal")
	public String editarInfoPresupuestal(Model model, InformacionPresupuestalDTO presupuesto,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("infoPresupuestal", new InformacionPresupuestalDTO());

		InformacionPresupuestalDTO pres = presupuestoDTORest.put(presupuesto, InformacionPresupuestalDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_PUT_MODIFICAR_INFORMACION_PRESUPUESTAL,
				NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute(MESSAGE, pres.getMsgRespuesta());
		redirectAttributes.addFlashAttribute(ID_ENTIDAD, presupuesto.getCodigoDistrital());
		redirectAttributes.addFlashAttribute(ID_DESARROLLO, presupuesto.getIdPlanDesarrollo());
		return REDIRECT_CONSULTA_PRE;
	}

	@PostMapping("/eliminar-presupuesto")
	public String eliminarInformacionPresupuestal(InformacionPresupuestalDTO eliminar,
			RedirectAttributes redirectAttributes) {

		InformacionPresupuestalDTO res = presupuestoDTORest.delete(
				"/presupuesto/eliminar_informacion_presupuestal/" + eliminar.getIdInfoPresupuestal(),
				new ParameterizedTypeReference<InformacionPresupuestalDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute(MESSAGE, res.getMsgRespuesta());
		redirectAttributes.addFlashAttribute(ID_ENTIDAD, eliminar.getCodigoDistrital());
		redirectAttributes.addFlashAttribute(ID_DESARROLLO, eliminar.getIdPlanDesarrollo());
		return REDIRECT_CONSULTA_PRE;
	}

}
