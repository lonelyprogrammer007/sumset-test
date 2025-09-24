package co.gov.sdp.spdd.web.ip.controller.estructura.pot;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "potDTO" })
public class InstrumentosPOTController {

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<PotInstrumentoDTO> listaInstrumento;
	
	@Autowired 
    SPDDLogger spddLogger;
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/ver-instrumentos")
	public String verInstrumentosPOT(PotDTO pot, Model model) {
		model.addAttribute("potDTO", pot);
		return "redirect:/consultar-instrumentos-pot";
	}

	@GetMapping("/consultar-instrumentos-pot")
	public String consultarInstrumentosPOT(Model model, @ModelAttribute("potDTO") PotDTO potDTO) {
		model.addAttribute("potDTOGet", potDTO);

		GenericoDTO primerNivel = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Primer nivel",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO segundoNivel = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Segundo nivel",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO tercerNivel = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Tercer nivel",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO nivel = listaGenerico.get("/administracion_lista_simple/obtener_argumento/NivelInstrumentos",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("primerNivel", primerNivel.getLstObjectsDTO());
		model.addAttribute("segundoNivel", segundoNivel.getLstObjectsDTO());
		model.addAttribute("tercerNivel", tercerNivel.getLstObjectsDTO());
		model.addAttribute("nivelInstrumento", nivel.getLstObjectsDTO());

		return "ip/estructura-pot/instrumentos-pot";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/listaPotInstrumentoByPage/{idPot}")
	public ResponseEntity listarPotInstrumento(int draw, int start, int length, HttpServletRequest req,
			@PathVariable("idPot") Long idPot) {
		PotInstrumentoDTO potDTO = new PotInstrumentoDTO();
		potDTO.setIdPot(idPot);
		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");

		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		int pageNo = (start) / length + 1;

		
		if (!search0.equals("")) {
			potDTO.setCodigoPotInstrumento(Long.parseLong(search0));
		}
		
		if (!search1.equals("")) {
			potDTO.setStringLsNivelInst(search1);
		}
		if (!search2.equals("")) {
			potDTO.setStringLsDenominacion(search2);
		}
	
		
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			potDTO.setTamanioPagina(Integer.MAX_VALUE);
			potDTO.setPagina(pageNo - 1);
		} else {
			potDTO.setTamanioPagina(length);
			potDTO.setPagina(pageNo - 1);
		}
		potDTO.setColumnaOrdenar(name);
		potDTO.setTipoOrden(sortDir);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(potDTO);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, potDTO.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, InstrumentosPOTController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_INSTUMENTO_POR_ID_POT, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<PotInstrumentoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());
		DataTable<PotInstrumentoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

	
	@PostMapping("/agregarEditarPotInstrumento")
	public String agregarPotInstrumento(PotInstrumentoDTO pot, RedirectAttributes redirectAttributes) {
		System.out.println("instrumento"+pot.toString());
		String parametro = "";
		
		if (pot.getIdInstrumentoPot() == null) {
			
			try {
				parametro = mapper.writeValueAsString(pot);
			} catch (JsonProcessingException e) {
				spddLogger.mensajeLogger(
	                    NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, pot.getLenguaje()),
	                    NHSPDDConstantes.SEVERIDAD_FATAL, InstrumentosPOTController.class);
			}
			GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
			},NHSPDDConstantes.CORE_CONTROLLER_IP_POT_REGISTRAR_POT_INSTUMENTO,
					NHSPDDConstantes.TIPO_CORE);
			
			redirectAttributes.addFlashAttribute("messageInstrumento", res.getMsgRespuesta());
			return "redirect:/consultar-instrumentos-pot";
			
		}else {
			PotInstrumentoDTO res = listaInstrumento.put(pot, PotInstrumentoDTO.class,
					NHSPDDConstantes.CORE_CONTROLLER_IP_POT_MODIFICAR_POT_INSTUMENTO , NHSPDDConstantes.TIPO_CORE);
			
			redirectAttributes.addFlashAttribute("messageInstrumento", res.getMsgRespuesta());

			return "redirect:/consultar-instrumentos-pot";
		}
	
		
	}
	
	
	@GetMapping("/eliminarPot/{idInstrumento}")
	public String eliminarPotInstrumnto(@PathVariable("idInstrumento") String id,RedirectAttributes redirectAttributes) {
		
		 PotInstrumentoDTO res = listaInstrumento.delete("/ippot/eliminar_pot_instrumento/"+id,
					new ParameterizedTypeReference<PotInstrumentoDTO>() {
					}, NHSPDDConstantes.TIPO_CORE);
	
		 redirectAttributes.addFlashAttribute("messageInstrumento", res.getMsgRespuesta());
		 
		 return "redirect:/consultar-instrumentos-pot";
		
	}
	
}
