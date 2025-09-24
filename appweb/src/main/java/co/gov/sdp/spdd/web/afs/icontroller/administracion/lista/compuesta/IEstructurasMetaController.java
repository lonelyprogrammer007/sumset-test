package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que tiene los metodos del controlador
 *
 * @author Bryan Munoz
 *
 */
public interface IEstructurasMetaController {

	/**
	 * Metodo que consulta una estructura por medio de una peticion get
	 *
	 * @param model objeto que permite enviar al consulta a la pagina
	 * @return la lista donde se observara la consulta
	 */
	public String consultarEstructura(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);

	/**
	 * Metodo que crea un estructura por medio de una peticion post
	 *
	 * @param estructurasMeta la estructura meta a crear
	 * @param model           permite extraer los campos del html
	 * @return la vista donde se consultan las estructuras metas
	 */
	public String crearEstructura(EstructuraMetaDTO estructurasMeta, Model model, RedirectAttributes redirectAttribute);

	/**
	 * Metodo que modifica un estructura por medio de una peticion post
	 *
	 * @param estructurasMeta la estructura meta a modificar
	 * @param model           permite extraer los campos del html
	 * @return la vista donde se consultan las estructuras metas
	 */
	public String modificarEstructura(EstructuraMetaDTO estructurasMeta, Model model, RedirectAttributes redirectAttribute);

	public String cambiarEstadoEstructura(Long id);
}
