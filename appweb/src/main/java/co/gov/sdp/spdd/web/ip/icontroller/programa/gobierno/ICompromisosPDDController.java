package co.gov.sdp.spdd.web.ip.icontroller.programa.gobierno;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;

/**
 * Metodo que maneja los métodos del controlador
 * @author Nicolas Zuluaga
 *
 */
public interface ICompromisosPDDController {

	
	public String verProgramas(String token, Model model);
	
	public String obtenerPrograma(Model model,PddDTO pddDTO);
	
	public String verPDD(Model model, PddDTO pddDTO, String idCompromiso);
	
	public String crearCompromiso(PddCompromisoDTO compromiso, RedirectAttributes redirectAttributes);
}
