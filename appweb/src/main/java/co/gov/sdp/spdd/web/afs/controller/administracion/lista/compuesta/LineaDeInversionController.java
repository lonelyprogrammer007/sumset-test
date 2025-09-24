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
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.ILineaDeInversionController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * Clase que implementa y direcciona los metodos de la aplicacion por medio de
 * peticiones get,post y put
 *
 * @author Bryan Munoz
 *
 */
@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion" })
public class LineaDeInversionController implements ILineaDeInversionController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<LineaDeInversionDTO> lineaDeInversionRest;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	private static final String REDIRECT = "redirect:/consultar-li";

	/**
	 * Implementacion del metodo connsultarLineaInversion
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.ILineaDeInversionController.consultarLineaInversion
	 */
	@GetMapping("/consultar-li")
	@Override
	public String consultarLineaInversion(Model model,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request) {

		GenericoDTO listaSectores = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Sectores",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("sectores", listaSectores.getLstObjectsDTO());

		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {
			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null &&
				request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {
					return "afs/listas/consultar-li";
			}

		}
		return "redirect:/home";

	}

	/**
	 * Implementacion del metodo crearLineaDeInversion
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.ILineaDeInversionController.crearLineaDeInversion
	 */
	@PostMapping("/crear_linea_inversion")
	@Override
	public String crearLineaDeInversion(Model model, LineaDeInversionDTO lineaDeInversion, 
			RedirectAttributes redirectAttribute) {    
        String fecha = lineaDeInversion.getFecha();
        String formato = "";
        String[] varia = fecha.split("-");
        String anio = varia[0];
        String mes = varia[1];
        String dia = varia[2];
        
        formato = dia+"/"+mes+"/"+anio;
        lineaDeInversion.setFecha(formato);
        
		model.addAttribute("lineaInversion", new LineaDeInversionDTO());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(lineaDeInversion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, lineaDeInversion.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, LineaDeInversionController.class);
		}
		LineaDeInversionDTO linea = lineaDeInversionRest.post(parametro, new ParameterizedTypeReference<LineaDeInversionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_LINEA_INVERSION,
				NHSPDDConstantes.TIPO_CORE);
		
		redirectAttribute.addFlashAttribute("messageRespuesta", linea.getMsgRespuesta());
		return REDIRECT;
	}

	/**
	 * Implementacion del metodo modificarLineaDeInversion
	 *
	 * @see co.gov.sdp.spdd.web.afs.icontroller.administracion.lista.compuesta.ILineaDeInversionController.modificarLineaDeInversion
	 */
	@PostMapping("/editar_linea_inversion")
	@Override
	public String modificarLineaDeInversion(Model model, LineaDeInversionDTO lineaDeInversionDTO, 
			RedirectAttributes redirectAttribute) {
		 String fecha = lineaDeInversionDTO.getFecha();
	        String formato = "";
	        String[] varia = fecha.split("-");
	        String anio = varia[0];
	        String mes = varia[1];
	        String dia = varia[2];
	        
	        formato = dia+"/"+mes+"/"+anio;
	        lineaDeInversionDTO.setFecha(formato);
		model.addAttribute("lineaInversion", new LineaDeInversionDTO());
		LineaDeInversionDTO linea = lineaDeInversionRest.put(lineaDeInversionDTO, LineaDeInversionDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION,
				NHSPDDConstantes.TIPO_CORE);
		redirectAttribute.addFlashAttribute("messageRespuesta", linea.getMsgRespuesta());
		return REDIRECT;
	}

	@GetMapping("/cambiar_estado_linea/{id}")
	@Override
	public String cambiarEstadoLineaInversion(@PathVariable(name = "id") Long id) {
		lineaDeInversionRest.put(null, null, "/administracion_lista_compuesta/modificar_estado_linea_inversion/" + id,
				NHSPDDConstantes.TIPO_CORE);
		return REDIRECT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaLineaDeInversionByPage")
	public ResponseEntity listarLineaDeInversion(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		LineaDeInversionDTO lineaDeInversionDTO = new LineaDeInversionDTO();

		
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
			lineaDeInversionDTO.setIdLineaInversion(null);
			
		} else {
			if (search0.matches("[0-9]*")) {
				lineaDeInversionDTO.setIdLineaInversion(Long.parseLong(search0));
			}
			else {
				lineaDeInversionDTO.setIdLineaInversion(-100L);
			}
			
		}

		if (!("".equals(search6))) {
			lineaDeInversionDTO.setEstado(Integer.parseInt(search6));
		} else {
			lineaDeInversionDTO.setEstado(null);
		}

		
		lineaDeInversionDTO.setDescripcion(search1);
		lineaDeInversionDTO.setConcepto(search2);
		lineaDeInversionDTO.setEstablecido(search3);
		lineaDeInversionDTO.setFecha(search4);
		lineaDeInversionDTO.setNombreSector(search5);

		int pageNo = (start) / length + 1;

		if (length == -1) {
			length=	Integer.MAX_VALUE;
			pageNo=1;
			lineaDeInversionDTO.setTamanioPagina(Integer.MAX_VALUE);
			lineaDeInversionDTO.setPagina(pageNo - 1);
		} else {
			lineaDeInversionDTO.setTamanioPagina(length);
			lineaDeInversionDTO
			.setPagina(pageNo - 1);
		}
		
		if ("nombreSector".equals(name)) {
			name = "idLsSector";
		}
		if ("stringEstado".equals(name)) {
			name = "estado";
		}
		
		lineaDeInversionDTO.setColumnaOrdenar(name);
		lineaDeInversionDTO.setTipoOrden(sortDir);
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(lineaDeInversionDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, lineaDeInversionDTO.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, LineaDeInversionController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION,
				NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<LineaDeInversionDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<LineaDeInversionDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
