package co.gov.sdp.spdd.web.afs.icontroller.buzon.notificaciones;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que maneja el controlador buzon notificacion
 *
 * @author Bryan Munoz
 *
 */
public interface IBuzonNotificacionesController {




	/**
	 * Metodo que realiza un consulta por medio de una peticion get
	 *
	 * @param model objeto que permite agregar la consulta a la pagina
	 * @return la pagina donde se visualiza la consulta
	 */
	public String buzonAdmin(Model model, PermisoRolEventoDto permiso,HttpServletRequest req);

	/**
	 * Metodo que realiza una peticion put para responder el mensaje
	 *
	 * @param buzonNotificaciones objeto tipo dto que sera enviado para responder el
	 *                            mensaje
	 * @param model               objeto que permite extraer los campos y crear el
	 *                            dto a modificar
	 * @return
	 */
	public String responderMensaje(BuzonNotificacionesDTO buzonNotificaciones, Model model);

	/**
	 * Metodo que realiza un consulta get para traer las notificaciones automaticas
	 *
	 * @param model objeto que permite agregar la cosulta a la pagina
	 * @return la pagina donde se observan las notificaciones
	 */
	public String plantillaNotificacion(Model model, PermisoRolEventoDto respuestaAutenticacion,HttpServletRequest request,RedirectAttributes redirectAttributes);
}
