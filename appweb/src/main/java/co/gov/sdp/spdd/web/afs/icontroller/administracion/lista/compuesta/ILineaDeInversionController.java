package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que contiene los metodos del controlador de linea de inversion
 *
 * @author Bryan Munoz
 *
 */
public interface ILineaDeInversionController {

	/**
	 * Metodo que permite consultar las lineasd de inversion y plasmarla en el la
	 * pagina
	 *
	 * @param model esto nos permite agregarle objetos a la pagina
	 * @return la pagina en la que se observa las lineas de inversion
	 */
	public String consultarLineaInversion(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);

	/**
	 * Metodo que permite crear una linea de inversion desde la pagina
	 *
	 * @param model               objeto que nos permite extraer los campos del
	 *                            formulario y transformarlo en una
	 *                            lineaDeInversionDTO
	 * @param lineaDeInversionDTO objeto el cual se agregara en la base de datos
	 * @return la pagina en la que se consulta las lineas de inversion
	 */
	public String crearLineaDeInversion(Model model, LineaDeInversionDTO lineaDeInversionDTO, RedirectAttributes redirectAttribute);

	/**
	 * Metodo que permite modificar una linea de inversion desde la pagina
	 *
	 * @param model               objeto que nos permite extraer los campos del
	 *                            formulario y transformarlo en una
	 *                            lineaDeInversionDTO
	 * @param lineaDeInversionDTO objeto el cual se modificara en la base de datos
	 * @return la pagina en la que se consulta las lineas de inversion
	 */
	public String modificarLineaDeInversion(Model model, LineaDeInversionDTO lineaDeInversionDTO, RedirectAttributes redirectAttribute);

	public String cambiarEstadoLineaInversion(Long id);
}
