package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.bp.controller.iniciativa.ciudadana.IniciativaCiudadanaController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class ParticipacionCiudadanaController {
	
	@Autowired
	MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MetodosRest<BpAporteCiudadanoDTO> aporteRest;
	
	@Autowired
	private MetodosRest<BpProyInvIniciativaDTO> iniciativaProyRest;
	
	@GetMapping("/obtener-niveles-participacion/{id}")
	@ResponseBody
	public GenericoDTO obtenerPddNivelesPorPdd(@PathVariable("id") Long idPdd) {
		
		return  listaGenerico.get("/ipplandistrital/obtener_todos_pdd_nivel/"+idPdd,
                new ParameterizedTypeReference<GenericoDTO>() {
                }, NHSPDDConstantes.TIPO_CORE);
	}
	
	
	
	
	
	@PostMapping("/registrar-aporte")
	@ResponseBody
	public BpAporteCiudadanoDTO registrarAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO) {
		String parametro = "";
		if(bpAporteCiudadanoDTO.getIdAporte()==null) {
		try {
			parametro = mapper.writeValueAsString(bpAporteCiudadanoDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, bpAporteCiudadanoDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		return aporteRest.post(parametro, new ParameterizedTypeReference<BpAporteCiudadanoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_APORTE_CIUDADANO, NHSPDDConstantes.TIPO_CORE);
		}else {
			return aporteRest.put(bpAporteCiudadanoDTO, BpAporteCiudadanoDTO.class, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_APORTE_CIUDADANO, NHSPDDConstantes.TIPO_CORE);
		}
	}
	
	@GetMapping("/obtenerAportesPorIdProyInv/{id}")
	@ResponseBody
	public GenericoDTO obtenerAportesPorIdProyInv(@PathVariable("id") Long idProyInv) {
		BpAporteCiudadanoDTO peticion = new BpAporteCiudadanoDTO();
		peticion.setIdProyInversion(idProyInv);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, peticion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_APORTE_POR_ID_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/obtenerAportePorId/{id}")
	@ResponseBody
	public BpAporteCiudadanoDTO obtenerAportePorId(@PathVariable("id") Long idAporte) {
		
		return aporteRest.get("/bpproyinv/obtener_bp_aporte_ciudadano_por_id/"+idAporte, new ParameterizedTypeReference<BpAporteCiudadanoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@GetMapping("/eliminar-aporte/{id}")
	@ResponseBody
	public BpAporteCiudadanoDTO eliminarAporte(@PathVariable("id") Long idAporte) {
		return aporteRest.delete("/bpproyinv/eliminar_bp_proy_inv_aporte/"+idAporte, new ParameterizedTypeReference<BpAporteCiudadanoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/agregar-iniciativas")
	@ResponseBody
	public BpProyInvIniciativaDTO agregarIniciativasProyInv(BpProyInvIniciativaDTO bpProyInvIniciativaDTO) {
		String parametro = "";
		
		try {
			parametro = mapper.writeValueAsString(bpProyInvIniciativaDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, bpProyInvIniciativaDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		return iniciativaProyRest.post(parametro, new ParameterizedTypeReference<BpProyInvIniciativaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_BP_INICIATIVA_CIUDADANA_VIABLES_CON_BP_PROYECTO_INVERSION, NHSPDDConstantes.TIPO_CORE);
		
	}
	@GetMapping("/obtenerIniciativasAsociadas/{id}")
	@ResponseBody
	public GenericoDTO obtenerIniciativasAsociadas(@PathVariable("id") Long idProyInv) {
		return listaGenerico.get("/bpproyinv/obtener_todos_bp_iniativa_ciudadana_con_relacion_con_proyecto_inversion/"+idProyInv, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
	}
}
