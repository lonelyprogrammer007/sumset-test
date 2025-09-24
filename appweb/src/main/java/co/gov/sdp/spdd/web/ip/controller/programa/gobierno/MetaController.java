package co.gov.sdp.spdd.web.ip.controller.programa.gobierno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.ip.icontroller.programa.gobierno.IMetasController;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class MetaController implements IMetasController {

	@Autowired
	private MetodosRest<PddMetaDTO> listaGenerico;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/agregar-metas")
	public String agregarMetas(PddMetaDTO meta, RedirectAttributes redirectAttributes) {

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(meta);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		PddMetaDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<PddMetaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_META_COMPROMISO, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageEspecifico", res.getMsgRespuesta());
		return "redirect:/ver-pdd";

	}

	@PostMapping("/editar-metas")
	public String editarObrasConcretas(PddMetaDTO meta, RedirectAttributes redirectAttributes) {

		PddMetaDTO res = listaGenerico.put(meta, PddMetaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_METAS, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageEspecifico", res.getMsgRespuesta());
		return "redirect:/ver-pdd";
	}

	@GetMapping("/eliminar-meta/{idMeta}")
	public String eliminarCompromisoEspecifico(@PathVariable(name = "idMeta") String id,
			RedirectAttributes redirectAttributes) {

		PddMetaDTO res = listaGenerico.delete("/ipformulacion/eliminar_meta/" + id,
				new ParameterizedTypeReference<PddMetaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("messageEspecifico", res.getMsgRespuesta());
		return "redirect:/ver-pdd";
	}

}
