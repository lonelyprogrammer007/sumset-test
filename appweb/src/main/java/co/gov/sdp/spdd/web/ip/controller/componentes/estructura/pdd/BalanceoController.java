package co.gov.sdp.spdd.web.ip.controller.componentes.estructura.pdd;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
	"nombreUsuarioSesion", "pddDTO", "tipoPdd"})
public class BalanceoController {

	@Autowired
	private MetodosRest<ArbolCompromisoDTO> arbolRest;
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	ObjectMapper mapper;

	@Autowired
	SPDDLogger spddLogger;
	
	@PostMapping("/obtener-balanceo-pdd")
	public String obtenerBalanceoPDD(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion,Model model, PddDTO pddDTO,RedirectAttributes redirectAttributes) {
	
		ArbolCompromisoDTO arbol = arbolRest.get("/ipplandistrital/obtener_componentes_desbalanceados_niveles_por_id_plan_desarrollo/"+pddDTO.getIdPlanDesarrollo(), new ParameterizedTypeReference<ArbolCompromisoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("pddDTO", pddDTO);
		System.out.println(arbol.toString());
		model.addAttribute("arbol", arbol.getMapObjetos());
		return "ip/componentes-estructura-pdd/balanceo/ver-balanceo-pdd.html";
	}
	
	@GetMapping("/ver-balanceo-pdd")
	public String verBalanceoPDD(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion) {
		
		return "ip/componentes-estructura-pdd/balanceo/ver-balanceo-pdd.html";
	}
	
	@GetMapping("/consultar-entidad-mp/{id}")
	@ResponseBody
	public GenericoDTO consultarEntidadPorIndicadorMetaProducto(@PathVariable("id") Long id) {
		PddMpIndicadorEntidadDTO pddMpEntidadDTO = new PddMpIndicadorEntidadDTO();
		pddMpEntidadDTO.setTamanioPagina(Integer.MAX_VALUE);
		pddMpEntidadDTO.setPagina(0);
		pddMpEntidadDTO.setIdMetaProdInd(id);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddMpEntidadDTO);
		} catch (JsonProcessingException e ) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddMpEntidadDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		
		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_ENTIDADES,NHSPDDConstantes.TIPO_CORE );
	}
	
	@GetMapping("/consultar-indicador-mr/{id}")
	@ResponseBody
	public GenericoDTO consultarIndicadorMetaProducto(@PathVariable("id") Long id) {
		PddMpIndicadorDTO pddMpIndicadorDTO = new PddMpIndicadorDTO();
		pddMpIndicadorDTO.setPagina(0);
		pddMpIndicadorDTO.setTamanioPagina(Integer.MAX_VALUE);
		pddMpIndicadorDTO.setIdMetaProducto(id);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddMpIndicadorDTO);
		} catch (JsonProcessingException e ) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddMpIndicadorDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_INDICADOR, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/consultar-pdd-nivel/{id}")
	@ResponseBody
	public GenericoDTO consultarPddNiveles(@PathVariable("id") Long id) {
		PddNivelAtributoDTO pddNivelAtributoDTO = new PddNivelAtributoDTO();
		pddNivelAtributoDTO.setIdPddNivel(id);
		pddNivelAtributoDTO.setPagina(0);
		pddNivelAtributoDTO.setTamanioPagina(Integer.MAX_VALUE);
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(pddNivelAtributoDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pddNivelAtributoDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ComponentesController.class);
		}
		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL, NHSPDDConstantes.TIPO_CORE);
	}
}
