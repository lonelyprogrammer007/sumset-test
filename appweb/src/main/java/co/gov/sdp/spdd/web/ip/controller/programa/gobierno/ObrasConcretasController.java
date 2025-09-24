package co.gov.sdp.spdd.web.ip.controller.programa.gobierno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion"})
public class ObrasConcretasController {

	@Autowired
	private MetodosRest<PddObraConcretaDTO> listaGenerico;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/agregar-obras")
	public String agregarMetas(PddObraConcretaDTO obra, RedirectAttributes redirectAttributes) {
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(obra);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		PddObraConcretaDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<PddObraConcretaDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_OBRA_CONCRETA, NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageEspecifico",res.getMsgRespuesta());
		return "redirect:/ver-pdd";
		
	}
	
	@PostMapping("/editar-obras")
	public String editarObrasConcretas(PddObraConcretaDTO obra, RedirectAttributes redirectAttributes) {
		listaGenerico.put(obra, PddObraConcretaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_OBRA_CONCRETA , NHSPDDConstantes.TIPO_CORE);
				
		redirectAttributes.addFlashAttribute("messageEspecifico","La obra se ha actualizado exitosamente");
		return "redirect:/ver-pdd";
	}
	
	@GetMapping("/eliminar-obra/{idObra}")
	public String eliminarCompromisoEspecifico(@PathVariable(name="idObra") String id,
			RedirectAttributes redirectAttributes) {
	    System.out.println("idobra"+id);
		PddObraConcretaDTO res = listaGenerico.delete("/ipformulacion/eliminar_obra_concreta/"+id,
				new ParameterizedTypeReference<PddObraConcretaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageEspecifico",res.getMsgRespuesta());
		return "redirect:/ver-pdd";
	}
}
