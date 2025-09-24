package co.gov.sdp.spdd.web.afs.controller.estado.servicio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.estado.servicio.IEstadoServicioController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que maneja las peticiones e implementa los metodos del controlador
 * estado de servicio
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class EstadoServicioController implements IEstadoServicioController {



	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;

	/**
	 * Implementacion del metodo consultarServicio
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.estado.servicio.IEstadoServicioController.consultarServicio
	 */
	@GetMapping("/consultar_estado_servicio")
	public String consultarServicio(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
	
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/servicios-web/consultar-sw";
			}

		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaEstadoServicioByPage")
	public ResponseEntity listarEstadoServicio(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		EstadoServicioDTO estadoServicioDTO = new EstadoServicioDTO();
		String parametro = "";
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		

	
		estadoServicioDTO.setEstadoSolicitud(search3);
		estadoServicioDTO.setFechaRespuesta(search4);
		estadoServicioDTO.setFechaSolicitud(search2);
		estadoServicioDTO.setNombreServicio(search0);
		estadoServicioDTO.setTarea(search1);

		int pageNo = (start) / length + 1;
	
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			estadoServicioDTO.setTamanioPagina(Integer.MAX_VALUE);
			estadoServicioDTO.setPagina(pageNo - 1);
		} else {
			estadoServicioDTO.setTamanioPagina(length);
			estadoServicioDTO.setPagina(pageNo - 1);
		}
		try {
			parametro = mapper.writeValueAsString(estadoServicioDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, estadoServicioDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, EstadoServicioController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ESTADO_DE_SERVICIO_OBTENER_TODOS, NHSPDDConstantes.TIPO_CORE);

		
		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<EstadoServicioDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<EstadoServicioDTO> dataTable = new DataTable<>();

		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}
}
