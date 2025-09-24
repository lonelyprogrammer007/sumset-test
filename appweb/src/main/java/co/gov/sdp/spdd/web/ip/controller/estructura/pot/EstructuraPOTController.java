package co.gov.sdp.spdd.web.ip.controller.estructura.pot;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta.ComponenteGastoController;
import co.gov.sdp.spdd.web.ip.controller.estructura.pd.EstructuraPDDController;
import co.gov.sdp.spdd.web.ip.icontroller.estructura.pot.IEstructuraPOTController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "ordenLista","potDTO" })
public class EstructuraPOTController implements IEstructuraPOTController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MetodosRest<PotDTO> potRest;
	
	@Autowired
	private MetodosRest<PotRamaDTO> potRamaRest;
	
	@Autowired
	private MetodosRest<PotNivelDTO> potNivelRest;
	
	@Autowired
    MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> api;
	
	@Autowired
	MetodosRest<PotObraDTO> potObraRest;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@GetMapping("/consultar-pot-ip")
	public String obtenerPOT(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {
		
		listaGenerico.agregarToken(tokenSesionSeguridad);
		return "ip/estructura-pot/consultar-pot";
	}
	

	
	@GetMapping("/agregar-pot")
	public String agregarPOT(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {
		
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Adopcion PDD", 
				new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		
		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());
		
		return "ip/estructura-pot/agregar-pot";
	}
	
	
	@PostMapping("/obtener-niveles-pot")
	public String obtenerNivelesPot(Model model, PotDTO pot) {
		
		model.addAttribute("potDTO", pot);
		return "redirect:/ver-niveles-pot";
	}

	@GetMapping("/ver-niveles-pot")
	public String verNivelesPot(Model model, @ModelAttribute("potDTO") PotDTO potDTO) {
		model.addAttribute("potDTOGet", potDTO);
		GenericoDTO plazos = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Plazos", 
				new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		RespuestaApiDTO<EntidadSeguridadDTO> entidad = api.get("/api/entidad/consultartodos",
                new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
                }, NHSPDDConstantes.TIPO_SEGURIDAD);
		model.addAttribute("entidades", entidad.getObjetos());
		
		model.addAttribute("plazos", plazos.getLstObjectsDTO());
		
		return "ip/estructura-pot/niveles-pot.html";
	}
	
	@GetMapping("/lista-ramas-pot/{id}")
	@ResponseBody
	public GenericoDTO listarRamas(@PathVariable("id") Long id) {
		
		return listaGenerico.get("/ippot/obtener_todos_pot_rama/"+id,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/lista-nivel-pot-rama/{id}")
	@ResponseBody
	public GenericoDTO listarNivelesPorRama(@PathVariable("id") Long id) {
		
		return listaGenerico.get("/ippot/obtener_todos_nivel_pot_rama/"+id, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/crear-pot-obra")
	@ResponseBody
	public PotObraDTO crearPotObra(@RequestBody PotObraDTO potObraDTO) {
		
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potObraDTO);	
		} catch (Exception e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potObraDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
				}
		PotObraDTO componente =potObraRest.post(parametro, new ParameterizedTypeReference<PotObraDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBRA_REGISTRAR_POT_OBRA, NHSPDDConstantes.TIPO_CORE);
		return componente;
		
		
		
	}
	
	@PostMapping("/modificar-estado-rama")
	@ResponseBody
	public PotRamaDTO modificarEstadoRama(@RequestBody PotRamaDTO potRamaDTO) {
		return potRamaRest.put(potRamaDTO, PotRamaDTO.class, "/ippot/modificar_rama/"+potRamaDTO.getIdPotRama()+"/"+potRamaDTO.getCerrada(), NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-estado-nivel")
	@ResponseBody
	public PotNivelDTO modificarEstadoNivel(@RequestBody PotNivelDTO potNivelDTO) {
		
		return potNivelRest.put(potNivelDTO, PotNivelDTO.class, "/ippot/modificar_niveles/"+potNivelDTO.getIdNivelPot()+"/"+potNivelDTO.getCerrado(), NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/eliminar-pot-obra")
	@ResponseBody
	public PotObraDTO eliminarPotObra(@RequestBody PotObraDTO potObraDTO) {
		return potObraRest.delete("/ippot/eliminar_pot_obra/"+potObraDTO.getIdObraProyPot(), new ParameterizedTypeReference<PotObraDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-pot-obra")
	@ResponseBody
	public PotObraDTO modificarPotObra(@RequestBody PotObraDTO potObraDTO) {
		return potObraRest.put(potObraDTO, PotObraDTO.class, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBRA_MODIFICAR_POT_OBRA
				, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/listar-nivel-pot-nivel/{id}")
	@ResponseBody
	public GenericoDTO listarNivelesPorNivel(@PathVariable("id") Long id) {
		return listaGenerico.get("/ippot/obtener_todos_nivel_pot_nivel/"+id, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/editar-nivel")
	@ResponseBody
	public PotNivelDTO editarNivel(@RequestBody PotNivelDTO potNivelDTO) {
		
		return potNivelRest.put(potNivelDTO, PotNivelDTO.class, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT_NIVEL, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/crear-rama")
	@ResponseBody
	public PotRamaDTO crearRama(@RequestBody PotRamaDTO potRamaDTO) {
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potRamaDTO);
		} catch (Exception e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potRamaDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		PotRamaDTO componente = potRamaRest.post(parametro, new ParameterizedTypeReference<PotRamaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_RAMA_REGISTRAR_RAMA, NHSPDDConstantes.TIPO_CORE);
		
		return componente;
	}
	
	@PostMapping("/crear-nivel")
	@ResponseBody
	public PotNivelDTO crearNivel(@RequestBody PotNivelDTO potNivelDTO) {
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potNivelDTO);
		} catch (Exception e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potNivelDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		return potNivelRest.post(parametro, new ParameterizedTypeReference<PotNivelDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_NIVEL_REGISTRAR_NIVEL, NHSPDDConstantes.TIPO_CORE);
	}
	

	@PostMapping("/listaPotObraByPage")
	public ResponseEntity listarPotObraPorIdNivel(int draw, int start, int length,HttpServletRequest req) {
		PotObraDTO potObraDTO= new PotObraDTO();
		potObraDTO.setIdNivelPot(Long.parseLong(req.getParameter("obra")));
		
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			potObraDTO.setTamanioPagina(Integer.MAX_VALUE);
			potObraDTO.setPagina(pageNo - 1);
		} else {
			potObraDTO.setTamanioPagina(length);
			potObraDTO.setPagina(pageNo - 1);
		}
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potObraDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potObraDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_OBRA_POR_ID_NIVEL_POT,
				NHSPDDConstantes.TIPO_CORE);
		Pageable pageRequest = new PageRequest(length, pageNo);
		
		Page<PotDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<PotDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaPotByPage")
	@Override
	public ResponseEntity listarPot(int draw, int start, int length, HttpServletRequest req) {
		PotDTO potDTO = new PotDTO();
		
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		String search6 = req.getParameter("columns[6][search][value]");
	
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		int pageNo = (start) / length + 1;
		
		potDTO.setCodigoPot(search0);
		potDTO.setNombreAdoptado(search1);
		potDTO.setActoAdministrativo(search2);
		potDTO.setFecha(search3);
		potDTO.setYearInicio(search4);
		potDTO.setYearFin(search5);
		potDTO.setEstado(search6);
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			potDTO.setTamanioPagina(Integer.MAX_VALUE);
			potDTO.setPagina(pageNo - 1);
		} else {
			potDTO.setTamanioPagina(length);
			potDTO.setPagina(pageNo - 1);
		}
		potDTO.setColumnaOrdenar(name);
		potDTO.setTipoOrden(sortDir);
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS,
				NHSPDDConstantes.TIPO_CORE);
		
		Pageable pageRequest = new PageRequest(length, pageNo);
		
		Page<PotDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<PotDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);
		
	}
	
	
	@PostMapping("/crear-editar-pot")
	public String crearPOT(PotDTO pot, RedirectAttributes redirectAttributes) {
		String parametro = "";
		
		if(pot.getIdPot()==null) {
			try {
				parametro = mapper.writeValueAsString(pot);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
						NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pot.getLenguaje()),
						NHSPDDConstantes.SEVERIDAD_FATAL, EstructuraPDDController.class);
			}
			PotDTO componente = potRest.post(parametro, new ParameterizedTypeReference<PotDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_AGREGAR_POT, NHSPDDConstantes.TIPO_CORE);
		
			redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
			return "redirect:/agregar-pot";
		}else {
			
			PotDTO componente= potRest.put(pot, PotDTO.class,NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT, NHSPDDConstantes.TIPO_CORE);
			redirectAttributes.addFlashAttribute("messageRespuesta", componente.getMsgRespuesta());
			
			return "redirect:/agregar-pot";
		}
	}
	
	@PostMapping("/eliminar-rama/{id}")
	@ResponseBody
	public PotRamaDTO eliminarRama(@PathVariable("id") Long id) {
		return potRamaRest.delete("/ippot/eliminar_rama_pot/"+id, new ParameterizedTypeReference<PotRamaDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/eliminar-nivel/{id}")
	@ResponseBody
	public PotNivelDTO eliminarNivel(@PathVariable("id") Long id) {
		return potNivelRest.delete("/ippot/eliminar_nivel_pot/"+id, new ParameterizedTypeReference<PotNivelDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/modificar-pot")
	public String editarPot(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,
			@ModelAttribute("tipoPdd") String idPdd,PotDTO potDTO,RedirectAttributes redirectAttributes) {
		
		GenericoDTO adoptado = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Adopcion PDD", 
				new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("editar", 1);
		model.addAttribute("adoptados", adoptado.getLstObjectsDTO());
		redirectAttributes.addFlashAttribute("potDTO", potDTO);
		
		return "ip/estructura-pot/agregar-pot";
	}
	
	
	
}
