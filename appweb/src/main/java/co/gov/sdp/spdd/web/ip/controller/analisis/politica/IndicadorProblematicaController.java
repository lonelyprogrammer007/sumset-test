package co.gov.sdp.spdd.web.ip.controller.analisis.politica;


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
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion","pddDTO", "idCompromiso" })
public class IndicadorProblematicaController {
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<PddPrbIndicadorDTO> listaIndicador;
	
	
	@PostMapping("/agregarIndicadorProblematica")
	@ResponseBody
	public PddPrbIndicadorDTO agregarIndicador(PddIndicadorDTO indicador) {
	    System.out.println("indProblee"+indicador.toString());
		PddPrbIndicadorDTO indicadorAux = new PddPrbIndicadorDTO();
		indicadorAux.setIdProblematica(indicador.getIdMetaProducto());
		indicadorAux.setIdIndicador(indicador.getIdIndicador());
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(indicadorAux);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, IndicadorProblematicaController.class);
		}
		return  listaIndicador.post(parametro, new ParameterizedTypeReference<PddPrbIndicadorDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PRB_INDICADOR, NHSPDDConstantes.TIPO_CORE);
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listarIndicadorProblematica/{id}")
	public ResponseEntity listarIndicadorProblematica(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req, @PathVariable("id") Long idProblematica) {
		
		
		PddPrbIndicadorDTO indicador = new PddPrbIndicadorDTO();
		indicador.setIdProblematica(idProblematica);
		
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		String search5 = req.getParameter("columns[5][search][value]");
		String search6 = req.getParameter("columns[6][search][value]");
		
		if (search0.matches("[0-9]*") && !search0.equals("")) {
		indicador.setIdIndicador(Long.parseLong(search0));
		}
		indicador.setNombre(search1);
		indicador.setTipo(search2);
		
		if (search3.matches("[0-9]*")  && !search3.equals("")) {
		indicador.setLineaBase(Long.parseLong(search3));
		}
		indicador.setFuente(search4);
		indicador.setYearLineaBase(search5);
		indicador.setInformacionSoporte(search6);
		
		int pageNo = (start) / length + 1;
		
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			indicador.setTamanioPagina(Integer.MAX_VALUE);
			indicador.setPagina(pageNo - 1);
		} else {
			indicador.setTamanioPagina(length);
			indicador.setPagina(pageNo - 1);
		}
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(indicador);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, indicador.getLenguaje()),
                    NHSPDDConstantes.SEVERIDAD_FATAL, IndicadorProblematicaController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_OBTENER_INDICADORES_DE_PROBLEMATICA_FILTRADO, NHSPDDConstantes.TIPO_CORE);
		
		Pageable pageRequest = new PageRequest(length, pageNo);
		Page<PddPrbIndicadorDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<PddPrbIndicadorDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);
		
		return ResponseEntity.ok(dataTable);
		
	}
	
	@GetMapping("/eliminarIndicadorPolitica/{idIndicador}")
	@ResponseBody
	public PddPrbIndicadorDTO eliminarIndicadorPolitica(@PathVariable("idIndicador") String id) {
	     return  listaIndicador.delete("/ipformulacion/eliminar_indicador/"+id,
				new ParameterizedTypeReference<PddPrbIndicadorDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
	}
	
	@PostMapping("/editarIndicadorPolitica")
	@ResponseBody
	public PddPrbIndicadorDTO editarIndicadorPolitica(PddPrbIndicadorDTO indicador) {
		 System.out.println(indicador.toString());
		 return listaIndicador.put(indicador, PddPrbIndicadorDTO.class,
		   NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PRB_INDICADOR, NHSPDDConstantes.TIPO_CORE);
			
	}
}
