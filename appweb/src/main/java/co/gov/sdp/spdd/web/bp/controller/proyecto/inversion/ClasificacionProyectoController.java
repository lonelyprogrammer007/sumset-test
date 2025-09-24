package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "proyecto" })
public class ClasificacionProyectoController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<BpProyInvPoliticaDTO> listaPolitica;

	@Autowired
	private MetodosRest<BpProyInvLineaDTO> listaLinea;

	@Autowired
	SPDDLogger spddLogger;

	@Autowired
	private ObjectMapper mapper;

	@GetMapping("listaPoliticaPublica/{idProyecto}")
	@ResponseBody
	public GenericoDTO listaPoliticaPublica(@PathVariable("idProyecto") String id) {
		return listaGenerico.get(
				"/bpproyinv/obtener_todos_pdd_politica_publica_sin_relacion_con_proyecto_inversion/" + id,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	}

	@PostMapping("agregarPoliticaPublica")
	@ResponseBody
	public GenericoDTO agregarPoliticaPublica(BpProyInvPoliticaDTO politica) {
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(politica);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, politica.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ClasificacionProyectoController.class);
		}
		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION,
				NHSPDDConstantes.TIPO_CORE);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listaPoliticaPublicaByPage/{idProyectoInversion}")
	public ResponseEntity listarPoliticasPublicas(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req,
			@PathVariable("idProyectoInversion") Long idProyecto) {

		BpProyInvPoliticaDTO politica = new BpProyInvPoliticaDTO();
		politica.setIdProyInversion(idProyecto);
		String search0 = req.getParameter("columns[0][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		politica.setStringPolPub(search0);

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			politica.setTamanioPagina(Integer.MAX_VALUE);
			politica.setPagina(pageNo - 1);
		} else {
			politica.setTamanioPagina(length);
			politica.setPagina(pageNo - 1);
		}

		politica.setColumnaOrdenar(name);
		politica.setTipoOrden(sortDir);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(politica);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, politica.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ClasificacionProyectoController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_POLITICA_FILTRADO,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<BpProyInvPoliticaDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<BpProyInvPoliticaDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	@PostMapping("/editarPoliticaPublica")
	@ResponseBody
	public BpProyInvPoliticaDTO editarPoliticaPublica(BpProyInvPoliticaDTO politica) {
		return listaPolitica.put(politica, BpProyInvPoliticaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION,
				NHSPDDConstantes.TIPO_CORE);

	}

	@GetMapping("/eliminarPoliticaPublica/{idPolitica}")
	@ResponseBody
	public BpProyInvPoliticaDTO eliminarPoliticaPublica(@PathVariable("idPolitica") String id) {
		return listaPolitica.delete("/bpproyinv/eliminar_relacion_pddpoliticapublic_con_bpproyectoInversion/" + id,
				new ParameterizedTypeReference<BpProyInvPoliticaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	}

	@GetMapping("/listLineaInversioByPage/{idProyecto}")
	@ResponseBody
	public GenericoDTO listLineaInversion(@PathVariable("idProyecto") String id) {
		return listaGenerico.get("/bpproyinv/obtener_todos_bp_proy_inv_linea/" + id,
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
	}

	@PostMapping("/agregarLineaInversion")
	@ResponseBody
	public BpProyInvLineaDTO agregarLineaInversion(BpProyInvLineaDTO linea) {
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(linea);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, linea.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ClasificacionProyectoController.class);
		}
		return listaLinea.post(parametro, new ParameterizedTypeReference<BpProyInvLineaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION,
				NHSPDDConstantes.TIPO_CORE);

	}

	@PostMapping("/modificarLineaInversion")
	@ResponseBody
	public BpProyInvLineaDTO modificarLineaInversion(BpProyInvLineaDTO linea) {
		return listaLinea.put(linea, BpProyInvLineaDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION,
				NHSPDDConstantes.TIPO_CORE);

	}

	@GetMapping("/eliminarLineaInversion/{idLinea}")
	 @ResponseBody
	 public BpProyInvLineaDTO eliminarLineaInversion(@PathVariable("idLinea") String idLinea) {
		 
		return listaLinea.delete("/bpproyinv/eliminar_relacion_lineainversion_con_bpproyectoinversion/"+ idLinea,
				new ParameterizedTypeReference<BpProyInvLineaDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

	 }
}
