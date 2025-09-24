package co.gov.sdp.spdd.web.ip.controller.componentes.estructura.pdd;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
	"nombreUsuarioSesion", "pddDTO" })
@Controller
public class ComponentesController {
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	ObjectMapper mapper;

	@Autowired
	SPDDLogger spddLogger;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	@Autowired
	private MetodosRest<PddNivelAtributoDTO> pddRest;
	
	@Autowired
	private MetodosRest<PddMetaResultadoDTO> metaRest;
	
	@Autowired
	private MetodosRest<PddIndicadorDTO> indicadorRest;
	
	@Autowired
	private MetodosRest<PddMRIndicadorDTO> indicador2Rest;
	
	@Autowired
	private MetodosRest<PddMpIndicadorDTO> indicadorProductoRest;
	
	@Autowired
	private MetodosRest<PddMetaProductoDTO> productoRest;
	
	@Autowired
	private MetodosRest<PddMpIndicadorEntidadDTO> entidadRest;
	
	@PostMapping("/obtener-componentes-pdd")
	public String obtenerComponentesPDD(@ModelAttribute("tokenSesionSeguridad") String token,
			Model model, PddDTO pddDTO) {
	
		listaGenerico.agregarToken(token);
		pddRest.agregarToken(token);
		model.addAttribute("pddDTO", pddDTO);
		return "redirect:/ver-componentes-pdd";
	}
	
	@GetMapping("/ver-componentes-pdd")
	public String verComponentesPDD(Model model, @ModelAttribute("pddDTO") PddDTO pddDTO){
		GenericoDTO numNiveles = listaGenerico.get("/ipplandistrital/obtener_todos_pdd_nivel/"+pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO genero = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Genero",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO verbo = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Verbo",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO unidadMedida = listaGenerico.get("/administracion_lista_simple/obtener_argumento/UnidadDeMedida",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO tipoAnualizacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TipoAnualizacion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO estado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estado iniciativa",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO estadoIndicador = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Estado indicador",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO agregacion = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Agregacion",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO indicadorProblematica = listaGenerico.get(NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADORES_META_PRODUCTO,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		RespuestaApiDTO<EntidadSeguridadDTO> entidades = api.get("/api/entidad/consultartodos",
				new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
				}, NHSPDDConstantes.TIPO_SEGURIDAD);
		
		model.addAttribute("entidades", entidades.getObjetos());
		model.addAttribute("genero", genero.getLstObjectsDTO());
		model.addAttribute("verbo", verbo.getLstObjectsDTO());
		model.addAttribute("unidadMedida", unidadMedida.getLstObjectsDTO());
		model.addAttribute("anualizacion", tipoAnualizacion.getLstObjectsDTO());
		model.addAttribute("estado", estado.getLstObjectsDTO());
		model.addAttribute("estadoIndicador", estadoIndicador.getLstObjectsDTO());
		model.addAttribute("agregacion", agregacion.getLstObjectsDTO());
		model.addAttribute("indicadorProblematica", indicadorProblematica.getLstObjectsDTO());
		

		if (numNiveles.getLstObjectsDTO().size()>0) {
			
		PddNivelAtributoDTO pddNivel = new PddNivelAtributoDTO();	
			
	    TypeReference<PddNivelDTO> type = new TypeReference<PddNivelDTO>() {
		};
		PddNivelDTO list = mapper.convertValue(numNiveles.getLstObjectsDTO().get(0), type);
		
		pddNivel.setIdPddNivel(list.getIdPddNivel());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}

		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);


		model.addAttribute("numNiveles", numNiveles.getLstObjectsDTO());
		model.addAttribute("ejesDTO", res.getLstObjectsDTO());
		}
		
		return "ip/componentes-estructura-pdd/componentes/componentes-niveles";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaNivelActualByPage/{idPddNivel}/{nivel}")
	public ResponseEntity listarProgramaCompromisos(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idPddNivel") Long idActual,
			@PathVariable("nivel") Long nivel) {
 
		PddNivelAtributoDTO pddDTO = new PddNivelAtributoDTO();
		GenericoDTO res = new GenericoDTO();
		pddDTO.setIdPddNivel(idActual);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			pddDTO.setTamanioPagina(Integer.MAX_VALUE);
			pddDTO.setPagina(pageNo - 1);
		} else {
			pddDTO.setTamanioPagina(length);
			pddDTO.setPagina(pageNo - 1);
		}

	
		String parametro = "";
		
		if (nivel == 1) {
			try {
				parametro = mapper.writeValueAsString(pddDTO);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddDTO.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			
			res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);

		} else if (nivel >= 2) {
			pddDTO.setIdPddNivel(null);
			pddDTO.setIdAtributoPadre(idActual);
			try {
				parametro = mapper.writeValueAsString(pddDTO);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddDTO.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			res =  listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE, NHSPDDConstantes.TIPO_CORE);	
		}	
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PddNivelAtributoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddNivelAtributoDTO> dataTable = new DataTable<>();
		if (res.getTotalPonderacion() != null) {
        dataTable.setTotalPonderacion(res.getTotalPonderacion());
		} else if (res.getTotalPonderacion() == null) {
			dataTable.setTotalPonderacion(0L);
		}
		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
    
	
	@GetMapping("/agregarArbolSeguir/{idPddNivel}/{nivel}")
	@ResponseBody
	public GenericoDTO agregarNivelActualArbol(@PathVariable("idPddNivel") Long idNivel,
			@PathVariable("nivel") Long nivel, Model model) {
		PddNivelAtributoDTO pddDTO = new PddNivelAtributoDTO();
		GenericoDTO res = new GenericoDTO();
		String parametro = "";
		if (nivel == 1) {
			pddDTO.setIdPddNivel(idNivel);
			try {
				parametro = mapper.writeValueAsString(pddDTO);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddDTO.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}

			res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);
		
		}else if (nivel >=2) {
			pddDTO.setIdPddNivel(null);
			pddDTO.setIdAtributoPadre(idNivel);
			try {
				parametro = mapper.writeValueAsString(pddDTO);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddDTO.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			 res =  listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE, NHSPDDConstantes.TIPO_CORE);	
			
		}
		
	  return res;
		
		
	}
	@GetMapping("/consultarHijos/{idNivelPdd}")
	@ResponseBody
	public GenericoDTO consultarHijos(@PathVariable("idNivelPdd") Long idNivel) {
		PddNivelAtributoDTO pdd = new PddNivelAtributoDTO();
		pdd.setIdAtributoPadre(idNivel);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pdd);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pdd.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
	
		return  listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE, NHSPDDConstantes.TIPO_CORE);

		
	}
	
	
	
	@PostMapping("/agregarNivel1")
	@ResponseBody
	public PddNivelAtributoDTO agregarNivel1(PddNivelAtributoDTO pddNivel) {
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivel);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivel.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		return pddRest.post(parametro, new ParameterizedTypeReference<PddNivelAtributoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL_ATRIBUTO, NHSPDDConstantes.TIPO_CORE);

	
	}

	
	@PostMapping("/editarNivel1")
	@ResponseBody
	public PddNivelAtributoDTO editarNivel1(PddNivelAtributoDTO pddNivel, Model model) {
		
		return pddRest.put(pddNivel, PddNivelAtributoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_NIVEL_ATRIBUTO, NHSPDDConstantes.TIPO_CORE);
			
	}
	
	@GetMapping("/eliminar-nivel/{idRegistro}")
	@ResponseBody
	public PddNivelAtributoDTO eliminarNivel1(@PathVariable("idRegistro") String id) {
	     return  pddRest.delete("/ipplandistrital/eliminar_pdd_nivel_atributo/"+id,
				new ParameterizedTypeReference<PddNivelAtributoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
	}
	
	@GetMapping("/consultarMetaResultado/{idAtributo}")
	@ResponseBody
	public GenericoDTO consultaMetaResultado(@PathVariable("idAtributo") Long idAtributo) {
		PddMetaResultadoDTO meta = new PddMetaResultadoDTO();
		GenericoDTO res;
		meta.setIdProyEstrategico(idAtributo);
		meta.setPagina(0);
		meta.setTamanioPagina(Integer.MAX_VALUE);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(meta);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, meta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_META_RESULTADO_POR_PROYECTO, NHSPDDConstantes.TIPO_CORE);
         
		return res;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaMetaResultadoByPage/{idAtributo}")
	public ResponseEntity listarMetaResultados(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idAtributo") Long idAtributo
			) {
    
		PddMetaResultadoDTO meta = new PddMetaResultadoDTO();
		
		meta.setIdProyEstrategico(idAtributo);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			meta.setTamanioPagina(Integer.MAX_VALUE);
			meta.setPagina(pageNo - 1);
		} else {
			meta.setTamanioPagina(length);
			meta.setPagina(pageNo - 1);
		}

		String parametro = "";
	
			try {
				parametro = mapper.writeValueAsString(meta);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, meta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			
			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_META_RESULTADO_POR_PROYECTO, NHSPDDConstantes.TIPO_CORE);

		
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PddMetaResultadoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddMetaResultadoDTO> dataTable = new DataTable<>();
		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
		
        dataTable.setTotalPonderacion(res.getTotalPonderacion());
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
	
	@PostMapping("/agregarMetaResultado")
	@ResponseBody
	public GenericoDTO agregarMetaResultad(PddMetaResultadoDTO meta) {
		 
		 String parametro = "";
			try {
				parametro = mapper.writeValueAsString(meta);

			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, meta.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_META_RESULTADO, NHSPDDConstantes.TIPO_CORE);
	         
			
	}
	
	@PostMapping("/editarMetaResultado")
	@ResponseBody
	public PddMetaResultadoDTO editarMetaResultad(PddMetaResultadoDTO meta) {
		 
			return metaRest.put(meta, PddMetaResultadoDTO.class,
			NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_META_RESULTADO, NHSPDDConstantes.TIPO_CORE);	
	}
	
	@GetMapping("/eliminarMetaResultado/{idMetaResultado}")
	@ResponseBody
	public PddMetaResultadoDTO eliminarMetaResultado(@PathVariable("idMetaResultado") Long id) {
	     return  metaRest.delete("/ipplandistrital/eliminar_meta_resultado/"+id,
				new ParameterizedTypeReference<PddMetaResultadoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaIndicadoresByPage/{idMetaResultado}")
	public ResponseEntity listarIndicadores(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idMetaResultado") Long idMetaResultado
			) {

		PddMRIndicadorDTO  indicador = new PddMRIndicadorDTO();
		GenericoDTO res = new GenericoDTO();
		indicador.setIdMetaResultado(idMetaResultado);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			indicador.setTamanioPagina(Integer.MAX_VALUE);
			indicador.setPagina(pageNo - 1);
		} else {
			indicador.setTamanioPagina(length);
			indicador.setPagina(pageNo - 1);
		}

		String parametro = "";
	
			try {
				parametro = mapper.writeValueAsString(indicador);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
		  if (idMetaResultado != null) {	
			res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADOR_META_RESULTADO, NHSPDDConstantes.TIPO_CORE);

		  }
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<PddMRIndicadorDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddMRIndicadorDTO> dataTable = new DataTable<>();
        System.out.println("iiii"+res.getNumeroConsecutivo());
		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
	
	@PostMapping("/agregarIndicadorMetaResultado")
	@ResponseBody
	public PddIndicadorDTO agregarIndicadorMetaResultado(PddIndicadorDTO indicador) {
		 String parametro = "";
			try {
				parametro = mapper.writeValueAsString(indicador);

			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			return indicadorRest.post(parametro, new ParameterizedTypeReference<PddIndicadorDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_RESULTADO_INDICADOR, NHSPDDConstantes.TIPO_CORE);
	        	
	}
	
	@PostMapping("/editarIndicadorMetaResultado")
	@ResponseBody
	public PddIndicadorDTO editarIndicadorMetaResultad(PddIndicadorDTO indicador) {
		 System.out.println(indicador.toString());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(indicador);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		return indicadorRest.post(parametro, new ParameterizedTypeReference<PddIndicadorDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_INDICADOR_Y_PRB_INDICADOR, NHSPDDConstantes.TIPO_CORE);
        	
	}
	
	@GetMapping("/eliminarIndicadorMetaResultado/{idIndicador}")
	@ResponseBody
	public PddMRIndicadorDTO eliminarIndicadorMetaResultado(@PathVariable("idIndicador") Long id) {
		
	     return  indicador2Rest.delete("/ipplandistrital/eliminar_meta_resultado_indicador/"+id,
				new ParameterizedTypeReference<PddMRIndicadorDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaMetaProductoByPage/{idMetaResultado}")
	public ResponseEntity listarMetaProducto(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idMetaResultado") Long idAtributo
			) {
    
		PddMetaProductoDTO producto = new PddMetaProductoDTO();
		
		producto.setIdMetaResultado(idAtributo);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			producto.setTamanioPagina(Integer.MAX_VALUE);
			producto.setPagina(pageNo - 1);
		} else {
			producto.setTamanioPagina(length);
			producto.setPagina(pageNo - 1);
		}

		String parametro = "";
	
			try {
				parametro = mapper.writeValueAsString(producto);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			
			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_POR_MR, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PddMetaProductoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddMetaProductoDTO> dataTable = new DataTable<>();
        System.out.println("esto es un total prod"+res.getTotalPonderacion());
        System.out.println("id pro"+res.getNumeroConsecutivo());
		dataTable.setTotalPonderacion(res.getTotalPonderacion());
		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
	

    @GetMapping("/consultarMetaProductos/{idMetaResultado}")
    @ResponseBody
    public GenericoDTO consultarMetaProductos(@PathVariable("idMetaResultado")Long idMeta) {
    	PddMetaProductoDTO producto = new PddMetaProductoDTO();
		GenericoDTO res;
		producto.setIdMetaResultado(idMeta);
		producto.setPagina(0);
		producto.setTamanioPagina(Integer.MAX_VALUE);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(producto);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_POR_MR, NHSPDDConstantes.TIPO_CORE);
         
		return res;
    }
    
    @PostMapping("/agregarMetaProducto")
	@ResponseBody
	public PddMetaProductoDTO agregarMetaProducto(PddMetaProductoDTO producto) {
		System.out.println("producto"+producto.toString());
		 String parametro = "";
			try {
				parametro = mapper.writeValueAsString(producto);

			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			return productoRest.post(parametro, new ParameterizedTypeReference<PddMetaProductoDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO, NHSPDDConstantes.TIPO_CORE);	
	}
    
    @PostMapping("/editarMetaProducto")
	@ResponseBody
	public PddMetaProductoDTO editarMetaProducto(PddMetaProductoDTO producto) {
		 
			return productoRest.put(producto, PddMetaProductoDTO.class,
			NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_META_PRODUCTO, NHSPDDConstantes.TIPO_CORE);	
	}
    
    @GetMapping("/eliminarMetaProducto/{idProducto}")
	@ResponseBody
	public PddMetaProductoDTO eliminarMetaProducto(@PathVariable("idProducto") Long id) {
		
	     return  productoRest.delete("/ipplandistrital/eliminar_meta_producto/"+id,
				new ParameterizedTypeReference<PddMetaProductoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaAnualizacionEntidadesByPage/{idMetaProducto}")
	public ResponseEntity listarAnualizacion(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idMetaProducto") Long idAtributo
			) {
        System.out.println("eentro a anualidades");
		PddMetaProductoDTO producto = new PddMetaProductoDTO();
		
		producto.setIdMetaResultado(idAtributo);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			producto.setTamanioPagina(Integer.MAX_VALUE);
			producto.setPagina(pageNo - 1);
		} else {
			producto.setTamanioPagina(length);
			producto.setPagina(pageNo - 1);
		}

		String parametro = "";
	
			try {
				parametro = mapper.writeValueAsString(producto);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
			}
			
			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_POR_MR, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PddMetaProductoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<PddMetaProductoDTO> dataTable = new DataTable<>();

		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
    
       
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	@PostMapping("/listaIndicadoresProductosByPage/{idMetaProducto}")
   	public ResponseEntity listarIndicadoresMetaProducto(@RequestParam("draw") int draw, @RequestParam("start") int start,
   			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idMetaProducto") Long idMetaProducto
   			) {
           System.out.println("eentro a indicador producto");
   		PddMpIndicadorDTO producto = new PddMpIndicadorDTO();
   		
   		producto.setIdMetaProducto(idMetaProducto);

   		int pageNo = (start) / length + 1;

   		if (length == -1) {
   			length = Integer.MAX_VALUE;
   			pageNo = 1;
   			producto.setTamanioPagina(Integer.MAX_VALUE);
   			producto.setPagina(pageNo - 1);
   		} else {
   			producto.setTamanioPagina(length);
   			producto.setPagina(pageNo - 1);
   		}

   		String parametro = "";
   	
   			try {
   				parametro = mapper.writeValueAsString(producto);
   			} catch (JsonProcessingException e) {
   				spddLogger.mensajeLogger(
   						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
   						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
   			}
   			
   			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
   			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_INDICADOR, NHSPDDConstantes.TIPO_CORE);

   		Pageable pageRequest = new PageRequest(length, pageNo);

   		Page<PddMpIndicadorDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

   		DataTable<PddMpIndicadorDTO> dataTable = new DataTable<>();
        System.out.println("esto es un ind prod"+res.getTotalPonderacion());
   		dataTable.setTotalPonderacion(res.getTotalPonderacion());
   		System.out.println("esto es un id meta prod"+res.getNumeroConsecutivo());
   		dataTable.setNumConsecutivo(res.getNumeroConsecutivo());
   		dataTable.setData(responseData.getContent());
   		dataTable.setRecordsTotal(responseData.getTotalElements());
   		dataTable.setRecordsFiltered(responseData.getTotalElements());
   		dataTable.setDraw(draw);
   		dataTable.setStart(start);

   		return ResponseEntity.ok(dataTable);

   	}
    
    @GetMapping("/consultarMetaProductosIndicadores/{idMetaProducto}")
    @ResponseBody
    public GenericoDTO consultarMetaProductosIndicadores(@PathVariable("idMetaProducto")Long idProducto) {
    	PddMpIndicadorDTO producto = new PddMpIndicadorDTO();
		GenericoDTO res;
		producto.setIdMetaProducto(idProducto);
		producto.setPagina(0);
		producto.setTamanioPagina(Integer.MAX_VALUE);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(producto);

		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_INDICADOR, NHSPDDConstantes.TIPO_CORE);
         
		return res;
    }
    
    @PostMapping("/agregarIndicadorMetaProducto")
   	@ResponseBody
   	public PddMpIndicadorDTO agregarIndicadorMetaProducto(PddIndicadorDTO producto) {
   		System.out.println("indicaodrorrproducto"+producto.toString());
   		 String parametro = "";
   			try {
   				parametro = mapper.writeValueAsString(producto);

   			} catch (JsonProcessingException e) {
   				spddLogger.mensajeLogger(
   						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
   						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
   			}
   			return indicadorProductoRest.post(parametro, new ParameterizedTypeReference<PddMpIndicadorDTO>() {
   			}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO_INDICADOR, NHSPDDConstantes.TIPO_CORE);	
   	}
    
    @PostMapping("/editarIndicadorMetaProducto")
   	@ResponseBody
   	public PddIndicadorDTO editarIndicadorMetaProducto(PddIndicadorDTO indicador) {
    	System.out.println("leallll"+indicador.toString());
 		String parametro = "";
 		try {
 			parametro = mapper.writeValueAsString(indicador);

 		} catch (JsonProcessingException e) {
 			spddLogger.mensajeLogger(
 					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
 					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
 		}
 		return indicadorRest.post(parametro, new ParameterizedTypeReference<PddIndicadorDTO>() {
 		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_INDICADOR_Y_PRB_INDICADOR, NHSPDDConstantes.TIPO_CORE);
         	
   	}
       
    @GetMapping("/eliminarMetaProductoIndicador/{idIndicador}")
   	@ResponseBody
   	public PddMpIndicadorDTO eliminarMetaProductoIndicador(@PathVariable("idIndicador") Long id) {
   		
   	     return  indicadorProductoRest.delete("/ipplandistrital/eliminar_meta_producto_indicador/"+id,
   				new ParameterizedTypeReference<PddMpIndicadorDTO>() {
   				}, NHSPDDConstantes.TIPO_CORE);

   	}
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	@PostMapping("/listaEntidadesByPage/{idIndicador}")
   	public ResponseEntity listarEntidades(@RequestParam("draw") int draw, @RequestParam("start") int start,
   			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("idIndicador") Long idMetaProducto
   			) {
       
    	PddMpIndicadorEntidadDTO entidad = new PddMpIndicadorEntidadDTO();
   		
   		entidad.setIdMetaProdInd(idMetaProducto);

   		int pageNo = (start) / length + 1;

   		if (length == -1) {
   			length = Integer.MAX_VALUE;
   			pageNo = 1;
   			entidad.setTamanioPagina(Integer.MAX_VALUE);
   			entidad.setPagina(pageNo - 1);
   		} else {
   			entidad.setTamanioPagina(length);
   			entidad.setPagina(pageNo - 1);
   		}

   		String parametro = "";
   	
   			try {
   				parametro = mapper.writeValueAsString(entidad);
   			} catch (JsonProcessingException e) {
   				spddLogger.mensajeLogger(
   						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, entidad.getLenguaje()),
   						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
   			}
   			
   			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
   			},NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_ENTIDADES, NHSPDDConstantes.TIPO_CORE);

   		Pageable pageRequest = new PageRequest(length, pageNo);

   		Page<PddMpIndicadorEntidadDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

   		DataTable<PddMpIndicadorEntidadDTO> dataTable = new DataTable<>();
   		if (res.getTotalPonderacion() == null) {
   		dataTable.setTotalPonderacion(0L);
   		}else {
   		 dataTable.setTotalPonderacion(res.getTotalPonderacion());
   		}
   		System.out.println("esto es una entidadad"+res.getTotalPonderacion());
       
   		dataTable.setData(responseData.getContent());
   		dataTable.setRecordsTotal(responseData.getTotalElements());
   		dataTable.setRecordsFiltered(responseData.getTotalElements());
   		dataTable.setDraw(draw);
   		dataTable.setStart(start);

   		return ResponseEntity.ok(dataTable);

   	}
    
    @PostMapping("/agregarEntidad")
   	@ResponseBody
   	public PddMpIndicadorEntidadDTO agregarEntidad(PddMpIndicadorEntidadDTO producto) {
   		System.out.println("entidad"+producto.toString());
   		 String parametro = "";
   			try {
   				parametro = mapper.writeValueAsString(producto);

   			} catch (JsonProcessingException e) {
   				spddLogger.mensajeLogger(
   						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, producto.getLenguaje()),
   						NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
   			}

   			return entidadRest.post(parametro, new ParameterizedTypeReference<PddMpIndicadorEntidadDTO>() {
   			}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_MP_INDICADOR_ENTIDAD, NHSPDDConstantes.TIPO_CORE);	
   	}
    
    @PostMapping("/editarEntidad")
   	@ResponseBody
   	public PddMpIndicadorEntidadDTO editarEntidad(PddMpIndicadorEntidadDTO producto) {
   		System.out.println("entidad editar"+producto.toString());
   		
   		return entidadRest.put(producto, PddMpIndicadorEntidadDTO.class,
   		NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_MP_INDICADOR_ENTIDAD, NHSPDDConstantes.TIPO_CORE);	
   	}
    
    @GetMapping("/eliminarEntidad/{idIndicador}")
   	@ResponseBody
   	public PddMpIndicadorEntidadDTO eliminarEntidad(@PathVariable("idIndicador") Long id) {
   		
   	     return  entidadRest.delete("/ipplandistrital/eliminar_mp_indicador_entidad/"+id,
   				new ParameterizedTypeReference<PddMpIndicadorEntidadDTO>() {
   				}, NHSPDDConstantes.TIPO_CORE);

   	}
        
}
