package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que maneja los metodos del controlador pot proyecto instrumento
 *
 * @author Bryan Munoz, Alex Leal
 *
 */
public interface IPotProyectoInstrummentoController {

	/**
	 * Metodo que realiza una peticion get para traer una consulta
	 *
	 * @param model objeto que permite agregar la consulta a la pagina
	 * @return la pagina donde se observara la consulta
	 */
	public String consultarPOT(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);

	/**
	 * Metodo que permite registrar un POT por medio de una peticion post
	 *
	 * @param potInstrumento objeto el cual se envia para su creacion
	 * @param model          objeto que permite extraer los campos y crear el dto
	 * @return la pagina en donde se visualiza la creacion
	 */
	public String registrarPOT(PotProyectoInstrumentoDTO potInstrumento, Model model, RedirectAttributes redirectAttributes);

	/**
	 * Metodo que permite editar un POT por medio de una peticion put
	 *
	 * @param potProyectoInstrumento objeto el cual se envia para editar
	 * @param model                  objeto que permite extraer los campos a
	 *                               modificar
	 * @return la pagina en donde se visualiza la modificacion
	 */
	public String editarPOT(PotProyectoInstrumentoDTO potProyectoInstrumento, Model model, RedirectAttributes redirectAttributes);

}
