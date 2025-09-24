package co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta;

import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Interface que tiene los metodos del controlador de equipamiento
 *
 * @author Bryan Munoz
 *
 */
public interface IEquipamentoController {

    /**
     * Metodo que consulta un equipamiento por medio de una peticion get
     *
     * @param model permite agregarla a la pagina
     * @return la pagina en donde se ve la consulta realizada
     */
    public String consultarEquipamento(Model model, String tokenSesionSeguridad, String usuarioSesion,
			String codigoEntidadUsuario, PermisoRolEventoDto respuestaAutenticacion, String nombreUsuarioSesion,
			HttpServletRequest request);

    /**
     * Metodo que crea un equipamiento por medio de una peticion post
     *
     * @param equipamento objeto que se desea crear
     * @param model objeto que extrae los campos y permite crear el objeto
     * @return la pagina en donde se observa el equipamiento creado
     */
    public String crearEquipamento(EquipamientoDTO equipamento, Model model, RedirectAttributes redirectAttributes);

    /**
     * Metodo que edita un equipamiento pot medio de una peticion put
     *
     * @param equipamento objeto que se esta editando
     * @param model objeto que permite extraer los campos a editar
     * @return la pagina en donde se ve el objeto editado
     */
    public String editarEquipamento(EquipamientoDTO equipamento, Model model, RedirectAttributes redirectAttributes);

   
}
