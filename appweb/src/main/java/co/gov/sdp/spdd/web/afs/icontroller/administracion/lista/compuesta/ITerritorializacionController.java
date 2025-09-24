package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;

/**
 * Interface que manjea los metodos del controlador Territorializacion
 *
 * @author Bryan Munoz, Alex Leal
 *
 */
public interface ITerritorializacionController {

	


	/**
	 * Metodo que crea una territorializacion realizando una peticion http
	 *
	 * @param territorializacion objeto a crear
	 * @param model              objeto que permite extraer y crear el objeto que se
	 *                           enviara a la bd
	 * @return la pagina en la que se creo el objeto
	 */
	public String crearTerritorializacion(TerritorializacionDTO territorializacion, Model model,  RedirectAttributes redirectAttributes);

	/**
	 * Metodo que modifica una territorializacion realizando una peticion http
	 *
	 * @param territorializacion objeto a modficiar
	 * @param model              objeto que permite extraer y crear el objeto que se
	 *                           enviara a la bd
	 * @return la pagina en la que se modifico el objeto
	 */
	public String cambiarEstadoTerritorializacion(Long id);

}
