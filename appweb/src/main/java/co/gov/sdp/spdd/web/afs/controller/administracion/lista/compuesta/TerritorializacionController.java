package co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.ITerritorializacionController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que tiene los metodos de peticiones post,put,get y delete de
 * teritorializacion
 *
 * @author Bryan Munoz, Alex Leal
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class TerritorializacionController implements ITerritorializacionController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<TerritorializacionDTO> territorializacionRest;
	
	@Autowired 
    SPDDLogger spddLogger;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * Implementacion del metodo consultarTerritorializacion
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta,ITerritorializacionController.consultarTerritorializacion
	 */
	@GetMapping("/consultar-terr")
	public String consultarTerritorializacion(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request, RedirectAttributes red) {

		GenericoDTO localidad = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Localidad",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO upz = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Upz",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO upr = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Upr",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO barrio = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Barrio",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		GenericoDTO vereda = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Vereda",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("localidad", localidad.getLstObjectsDTO());
		model.addAttribute("upz", upz.getLstObjectsDTO());
		model.addAttribute("upr", upr.getLstObjectsDTO());
		model.addAttribute("vereda", vereda.getLstObjectsDTO());
		model.addAttribute("barrio", barrio.getLstObjectsDTO());
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {

				model.addAttribute("orden", "desc");
				return "afs/listas/consultar-territorializacion";
			}
		}

		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo crearTerritorializacion
	 *
	 * @see co.gov.sdp.nhspdd.web.icontroller.administracion.lista.compuesta,ITerritorializacionController.crearTerritorializacion
	 */
	@PostMapping("/gestion_terr")
	@Override
	public String crearTerritorializacion(TerritorializacionDTO territorializacion, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("territorializacion", new TerritorializacionDTO());

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(territorializacion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, territorializacion.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}

		if (territorializacion.getIdTerritorializacion() == null) {
			TerritorializacionDTO terr = territorializacionRest.post(parametro,
					new ParameterizedTypeReference<TerritorializacionDTO>() {
					}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_TERRITORIALIZACION,
					NHSPDDConstantes.TIPO_CORE);
			redirectAttributes.addFlashAttribute("messageRespuesta", terr.getMsgRespuesta());

		} else {
			TerritorializacionDTO terr = territorializacionRest.put(territorializacion, TerritorializacionDTO.class,
					NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_TERRITORIALIZACION,
					NHSPDDConstantes.TIPO_CORE);
			redirectAttributes.addFlashAttribute("messageRespuesta", terr.getMsgRespuesta());

		}

		return "redirect:/consultar-terr";
	}

	@GetMapping("/cambiar_estado_terr/{id}")
	@Override
	public String cambiarEstadoTerritorializacion(@PathVariable(name = "id") Long id) {
		territorializacionRest.put(null, null,
				"/administracion_lista_compuesta/modificar_estado_territorializacion/" + id,
				NHSPDDConstantes.TIPO_CORE);
		return "redirect:/consultar-terr";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/listaTerritorializacionByPage")
	public ResponseEntity listarTerritorizacion(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		TerritorializacionDTO territorializacionDTO = new TerritorializacionDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		String search6 = req.getParameter("columns[6][search][value]");

		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if ("".equals(search0)) {
			territorializacionDTO.setIdTerritorializacion(null);
		} else {
			if (search0.matches("[0-9]*")){
			territorializacionDTO.setIdTerritorializacion(Long.parseLong(search0));
			}

		}
		territorializacionDTO.setNombreLocalidad(search1);
		territorializacionDTO.setNombreUpz(search2);
		territorializacionDTO.setNombreUpr(search3);
		territorializacionDTO.setNombreBarrio(search4);
		territorializacionDTO.setNombreVereda(search5);
		if ("".equals(search6)) {
			territorializacionDTO.setEstado(null);
		} else {
			territorializacionDTO.setEstado(Integer.parseInt(search6));
		}
		int pageNo = (start) / length + 1;

		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			territorializacionDTO.setTamanioPagina(Integer.MAX_VALUE);
			territorializacionDTO.setPagina(pageNo - 1);
		} else {
			territorializacionDTO.setTamanioPagina(length);
			territorializacionDTO.setPagina(pageNo - 1);
		}
		if(!("idTerritorializacion".equals(name))) {
		switch (name) {

		case "nombreLocalidad":
			name = "idLsLocalidad";
			break;
		case "nombreUpr":
			name = "idLsUpr";
			break;
		case "nombreUpz":
			name= "idLsUpz";
			break;
		case "nombreBarrio":
			name= "idLsBarrio";
			break;
		case "nombreVereda":
			name = "idLsVereda";
			break;
		default:
			name="estado";
			break;

		}
		}
		territorializacionDTO.setColumnaOrdenar(name);
		territorializacionDTO.setTipoOrden(sortDir);
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(territorializacionDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, territorializacionDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, TerritorializacionController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<TerritorializacionDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<TerritorializacionDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
