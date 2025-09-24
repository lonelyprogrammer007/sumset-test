
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class ConsecutivosController {

	@Autowired
	private MetodosRest<ConsecutivoDTO> restconse;

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<RespuestaApiDTO<EntidadSeguridadDTO>> entidadRest;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	ObjectMapper mapper;

	@GetMapping("/consultar-consecutivo")
	public String consultarConsecutivo(Model model, @ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		GenericoDTO entidades = listaGenerico.get(
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_TODOS,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		RespuestaApiDTO<EntidadSeguridadDTO> entidad= entidadRest.get("/api/entidad/consultartodos", new ParameterizedTypeReference<RespuestaApiDTO<EntidadSeguridadDTO>>() {
		}, NHSPDDConstantes.TIPO_SEGURIDAD);
		
		model.addAttribute("entidades", entidad.getObjetos());

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {

           if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null && 
        		   request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0){
					return "afs/parametros-consecutivos/consultar-consecutivo";

			}
		}
		
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaConsecutivosByPage")
	public ResponseEntity listarConsecutivos(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {
		ConsecutivoDTO consecutivo = new ConsecutivoDTO();
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if (!("".equals(search3))) {
			consecutivo.setSecuencia(Long.parseLong(search3));
		} else {
			consecutivo.setSecuencia(null);
		}

		if (!("".equals(search0))) {
			consecutivo.setStringPlanDeDesarrollo(search0);
			consecutivo.setNombre(search1);
			consecutivo.setDescripcion(search2);

			consecutivo.setVigencia(search4);
			consecutivo.setCodigoEntidad(search5);
		} else {
			consecutivo.setIdPlanDesarrollo(null);
			consecutivo.setNombre(search1);
			consecutivo.setDescripcion(search2);

			consecutivo.setVigencia(search4);
			consecutivo.setCodigoEntidad(search5);
		}

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			consecutivo.setTamanioPagina(Integer.MAX_VALUE);
			consecutivo.setPagina(pageNo - 1);
		} else {
			consecutivo.setTamanioPagina(length);
			consecutivo.setPagina(pageNo - 1);
		}
		System.out.println("name"+name);
		if ("codigoEntidad".equals(name)) {
			name = "";
		}

		consecutivo.setColumnaOrdenar(name);
		consecutivo.setTipoOrden(sortDir);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(consecutivo);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
					consecutivo.getLenguaje()), NHSPDDConstantes.SEVERIDAD_FATAL, ConsecutivosController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS_CONSECUTIVO,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<ConsecutivoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<ConsecutivoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/editarConsecutivo")
	public String editarConsecutivo(ConsecutivoDTO con, RedirectAttributes redirectAttributes) {

		ConsecutivoDTO consecutivo = restconse.put(con, ConsecutivoDTO.class,

				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_CONSECUTIVO,
				NHSPDDConstantes.TIPO_CORE);

		redirectAttributes.addFlashAttribute("mensaje", consecutivo.getMsgRespuesta());
		return "redirect:/consultar-consecutivo";
	}

}
