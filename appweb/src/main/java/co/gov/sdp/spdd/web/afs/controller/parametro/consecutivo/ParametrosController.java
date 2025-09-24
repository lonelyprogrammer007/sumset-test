package co.gov.sdp.spdd.web.afs.controller.parametro.consecutivo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class ParametrosController {

	@Autowired
	private MetodosRest<ParametroGeneralDTO> param;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@GetMapping("/consultar-parametro")
	public String consultarParametros(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/parametros-consecutivos/consultar-parametros";
			}
		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaParametrosByPage")
	public ResponseEntity listarParametros(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {
		ParametroGeneralDTO parametro = new ParametroGeneralDTO();
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		parametro.setCodigoParametro(search0);
		parametro.setNombre(search1);
		parametro.setDescripcion(search2);
		parametro.setArgumento(search3);
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			parametro.setTamanioPagina(Integer.MAX_VALUE);
			parametro.setPagina(pageNo - 1);
		} else {
			parametro.setTamanioPagina(length);
			parametro.setPagina(pageNo - 1);
		}
		
		parametro.setColumnaOrdenar(name);
	    parametro.setTipoOrden(sortDir);
		
		
		String para = "";
		try {
			para = mapper.writeValueAsString(parametro);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, parametro.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL ,ParametrosController.class);
		}
		GenericoDTO res = listaGenerico.post(para, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODO_PARAMETRO_GENERAL,
				NHSPDDConstantes.TIPO_CORE);
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<ParametroGeneralDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<ParametroGeneralDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/editarParametro")
	public String editarParametro(ParametroGeneralDTO parametro, RedirectAttributes redirectAttributes) {
		
		ParametroGeneralDTO parame = param.put(parametro, ParametroGeneralDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_PARAMETRO_GENERAL,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttributes.addFlashAttribute("mensaje", parame.getMsgRespuesta());
		return "redirect:/consultar-parametro";
	}

}
