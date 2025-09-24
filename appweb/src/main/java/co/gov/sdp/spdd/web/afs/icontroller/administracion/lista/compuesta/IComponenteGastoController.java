package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que maneja los metodos del controlador componente gasto
 *
 * @author Bryan Munoz
 *
 */
public interface IComponenteGastoController {

	/**
	 * Metodo que realiza una peticion get para traer la lista de componentes de
	 * gasto
	 *
	 * @param model objeto que permite insertar en la pagina la lista
	 * @return la direccion donde se mostrara la lista
	 */
	public String consultarComponenteGasto(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);

	/**
	 * Metodo que realiza una peticion post para crear un componente de gasto
	 *
	 * @param componenteGasto componente de gasto a crear tipo dto
	 * @param model           objeto que permite crear el dto a enviar
	 * @return la direccion donde se ve los componentes registrados
	 */
	public String crearComponenteGasto(ComponenteGastoDTO componenteGasto, Model model, RedirectAttributes redirectAttributes);

	/**
	 * Metodo que realiza una peticion put para modificar un componente de gasto
	 *
	 * @param componenteGasto componente de gasto a modificar tipo dto
	 * @param model           objeto que permite crear el dto a enviar
	 * @return la direccion donde se ve los componentes registrados
	 */
	public String editarComponenteGasto(ComponenteGastoDTO componenteGasto, Model model, RedirectAttributes redirectAttributes);

}
