package co.gov.sdp.spdd.web.ip.controller.componentes.estructura.pdd;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.controller.archivos.planos.ArchivosPlanosController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "pddDTO", "tipoPdd" })
public class MedicionController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	private MetodosRest<PddRangoPonderacionDTO> rangoRest;
	
	@Autowired
	ObjectMapper obj;
	
	private RestTemplate rest = new RestTemplate();

	private HttpHeaders headers = new HttpHeaders();

	@Value("${com.direccion.url.appcore}")
	private String urlCore;

	@PostMapping("/obtener-medicion-pdd")
	public String obtenerMedicionPDD(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, Model model, PddDTO pddDTO) {
		
		
		model.addAttribute("pddDTO", pddDTO);
		
		
		
	
		return "redirect:/ver-medicion-pdd";
	}

	@GetMapping("/ver-medicion-pdd")
	public String verMedicionPdd(Model model, @ModelAttribute("pddDTO") PddDTO pddDTO) {
		GenericoDTO rangos = listaGenerico.get("/ipplandistrital/obtener_rango_ponderacion_por_id_pdd/"+pddDTO.getIdPlanDesarrollo(), new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("rangos", rangos.getLstObjectsDTO());
		return "ip/componentes-estructura-pdd/rangos/ver-medicion-pdd.html";
	}

	@PostMapping("/agregar-rango")
	public String agregarRango(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, @RequestParam("file") MultipartFile file,
			PddRangoPonderacionDTO pddRangoPonderacionDTO, RedirectAttributes redirectAttributes) {
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		String tempFileName = new File(".").getAbsolutePath() + file.getOriginalFilename();
	
	
		try (

				FileOutputStream fo = new FileOutputStream(tempFileName);)

		{
			fo.write(file.getBytes());
			fo.close();
			map.add("logo", new FileSystemResource(tempFileName));
			map.add("rango", pddRangoPonderacionDTO.getRango());
			map.add("descripcion", pddRangoPonderacionDTO.getDescripcion());
			map.add("idPlanDesarrollo", pddRangoPonderacionDTO.getIdPlanDesarrollo());
			if(pddRangoPonderacionDTO.getIdRango()!=null){
				map.add("idRango", pddRangoPonderacionDTO.getIdRango());
			}
			
			
		} catch (Exception e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
							pddRangoPonderacionDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivosPlanosController.class);
		}
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", NHSPDDConstantes.TOKEN_PREFIX + tokenSesionSeguridad);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
		if(pddRangoPonderacionDTO.getIdRango()==null) {
			PddRangoPonderacionDTO res = rest.postForObject(
					urlCore + NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_RANGO_PONDERACION,
					requestEntity, PddRangoPonderacionDTO.class);
			redirectAttributes.addFlashAttribute("respuesta", res.getMsgRespuesta());
		}else {
			PddRangoPonderacionDTO res = rest.postForObject(
					urlCore +NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_RANGO_PONDERACION,
					requestEntity, PddRangoPonderacionDTO.class);
			redirectAttributes.addFlashAttribute("respuesta", res.getMsgRespuesta());
		}
		
		
		File f = new File(tempFileName);
		f.delete();
		return "redirect:/ver-medicion-pdd";
	}
	
	@PostMapping("/eliminar-rango")
	public String eliminarRango(PddRangoPonderacionDTO pddRangoPonderacionDTO,RedirectAttributes model) {
		PddRangoPonderacionDTO respuesta = rangoRest.delete("/ipplandistrital/eliminar_rango_ponderacion/"+pddRangoPonderacionDTO.getIdRango(), new ParameterizedTypeReference<PddRangoPonderacionDTO>() {
		}, NHSPDDConstantes.TIPO_CORE);
		model.addFlashAttribute("respuesta", respuesta.getMsgRespuesta());
		return "redirect:/ver-medicion-pdd";
	}

}
