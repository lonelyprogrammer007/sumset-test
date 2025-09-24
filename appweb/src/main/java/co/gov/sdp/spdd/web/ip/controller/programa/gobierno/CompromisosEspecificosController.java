package co.gov.sdp.spdd.web.ip.controller.programa.gobierno;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta.ComponenteGastoController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion"})
public class CompromisosEspecificosController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MetodosRest<PddCompromisoEspecificoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<GenericoDTO> genericoRest;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@PostMapping("/agregar-compro-especifico")
	public String agregarCompromisoEspecifico(PddCompromisoEspecificoDTO especifico,
			 RedirectAttributes redirectAttributes) {
		
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(especifico);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		PddCompromisoEspecificoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<PddCompromisoEspecificoDTO>() {
		},NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO_ESPECIFICO , NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageEspecifico",res.getMsgRespuesta());
		redirectAttributes.addFlashAttribute("radioSeleccionado", especifico.getIdCompromiso());
		return "redirect:/ver-pdd";
		
	}
	
	@PostMapping("/editar-compro-especifico")
	public String editarsCompromisoEspecifico(PddCompromisoEspecificoDTO especifico,
			 RedirectAttributes redirectAttributes) {
		
		PddCompromisoEspecificoDTO res = listaGenerico.put(especifico, PddCompromisoEspecificoDTO.class,
		NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO_ESPECIFICO , NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageEspecifico", res.getMsgRespuesta());
		return "redirect:/ver-pdd";
		
	}
	
	@GetMapping("/eliminar-especifico/{idEspecifico}")
	public String eliminarCompromisoEspecifico(@PathVariable(name="idEspecifico") String id,
			RedirectAttributes redirectAttributes) {
	
		PddCompromisoEspecificoDTO res = listaGenerico.delete("/ipformulacion/eliminar_pdd_compromiso_especifico/"+id,
				new ParameterizedTypeReference<PddCompromisoEspecificoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		System.out.println("eliminó");
		redirectAttributes.addFlashAttribute("messageEspecifico", res.getMsgRespuesta());
		return "redirect:/ver-pdd";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listarCompromisoEspecificoPorId/{id}")
	public ResponseEntity listarCompromisoEspecificoPorId(@PathVariable("id") Long id,@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {
		PddCompromisoEspecificoDTO peticion = new PddCompromisoEspecificoDTO();
		peticion.setIdCompromiso(id);
		
		String search0 = req.getParameter("columns[0][search][value]");
		peticion.setDescripcion(search0);
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			peticion.setTamanioPagina(Integer.MAX_VALUE);
			peticion.setPagina(pageNo - 1);
		} else {
			peticion.setTamanioPagina(length);
			peticion.setPagina(pageNo - 1);
		}
		
		peticion.setColumnaOrdenar(name);
		peticion.setTipoOrden(sortDir);
		System.out.println(peticion.toString());
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, peticion.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, ComponenteGastoController.class);
		}
		
		GenericoDTO respuesta = genericoRest.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_COMPROMISOS_ESPECIFICOS_FILTRADO, NHSPDDConstantes.TIPO_CORE);
		


		Pageable pageRequest = new PageRequest(length, pageNo);
		
		Page<PddCompromisoEspecificoDTO> responseData = new PageImpl(respuesta.getLstObjectsDTO(), pageRequest, respuesta.getTotal());
		DataTable<PddCompromisoEspecificoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);
		
		
	}
}
