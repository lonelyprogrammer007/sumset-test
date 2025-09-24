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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.ip.icontroller.programa.gobierno.ICompromisosPDDController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
"nombreUsuarioSesion","pddDTO", "idCompromiso" })
public class CompromisosPDDController implements ICompromisosPDDController{

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;
	
	@Autowired
	private MetodosRest<PddCompromisoDTO> listPddCompromiso;
	
	@Autowired
	private MetodosRest<ArbolCompromisoDTO> arboldto;
	
	@Autowired
	private MetodosRest<PddCompromisoDTO> listCompromiso;
	
	@GetMapping("/consultar-compromisos")
	@Override
	public String verProgramas(@ModelAttribute("tokenSesionSeguridad") String token, Model model) {
		
		listaGenerico.agregarToken(token);

		return "ip/programa-gobierno/consultar-programas.html";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listaProgramaCompromisosByPage", method = RequestMethod.POST)
     public ResponseEntity listarProgramaCompromisos(@RequestParam("draw") int draw, @RequestParam("start") int start,
		@RequestParam("length") int length, HttpServletRequest req) {
			
		PddDTO pddDTO = new PddDTO();
		
		String sSearch_0= req.getParameter("columns[0][search][value]");
		String sSearch_1= req.getParameter("columns[1][search][value]");
		String sSearch_2= req.getParameter("columns[2][search][value]");
		String sSearch_3= req.getParameter("columns[3][search][value]");
		String sSearch_4= req.getParameter("columns[4][search][value]");
		String sSearch_5= req.getParameter("columns[5][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");
		
		if(sSearch_0.equals("")) {
			pddDTO.setProgramaGobierno(null);
		}else {
			pddDTO.setProgramaGobierno(sSearch_0);
		}
		if(sSearch_1.equals("")) {
			pddDTO.setNombreAlcalde(null);
		}else {
			pddDTO.setNombreAlcalde(sSearch_1);
		}
		if(sSearch_2.equals("")) {
			pddDTO.setVigencia(null);
		}else {
			pddDTO.setVigencia(sSearch_2);
		}
		if(sSearch_3.equals("")) {
			pddDTO.setYearInicio(null);
		}else {
			pddDTO.setYearInicio(sSearch_3);
		}
		if(sSearch_4.equals("")) {
			pddDTO.setYearFinal(null);
		}else {
			pddDTO.setYearFinal(sSearch_4);
		}
		if(sSearch_5.equals("")) {
			pddDTO.setEstadoPlan(null);
		}else {
			pddDTO.setEstadoPlan(sSearch_5);
		}
		
		
		int pageNo = (start) / length + 1;

		if (length == -1) {
			length=	Integer.MAX_VALUE;
			pageNo=1;
			pddDTO.setTamanioPagina(Integer.MAX_VALUE);
			pddDTO.setPagina(pageNo - 1);
		} else {
			pddDTO.setTamanioPagina(length);
			pddDTO
			.setPagina(pageNo - 1);
		}
		
		pddDTO.setColumnaOrdenar(name);
		pddDTO.setTipoOrden(sortDir);
		String parametro= "";
		try {
			parametro = mapper.writeValueAsString(pddDTO); 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(name.equals("estadoPlan")) {
			name="estado";
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD, 
		NHSPDDConstantes.TIPO_CORE);
		
		Pageable pageRequest = new PageRequest(length, pageNo);
		
		Page<PddDTO> responseData = new PageImpl(res.getLstObjectsDTO(),pageRequest,res.getTotal());
		
		DataTable<PddDTO> dataTable = new DataTable<PddDTO>();
		
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);
		
		return ResponseEntity.ok(dataTable);
		
	}
	@PostMapping("/obtener-programa")
	@Override
	public String obtenerPrograma(Model model,PddDTO pddDTO) {
	
		model.addAttribute("pddDTO",pddDTO);
		model.addAttribute("idCompromiso", "");
		return "redirect:/ver-pdd";
	}
	
	@GetMapping("/ver-pdd")
	@Override
	public String verPDD(Model model,@ModelAttribute("pddDTO") PddDTO pddDTO, @ModelAttribute("idCompromiso") String idCompromiso) {
		
		GenericoDTO tematica = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Tematica",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		model.addAttribute("tematicas", tematica.getLstObjectsDTO());

		GenericoDTO compromiso = listaGenerico.get("/administracion_lista_simple/obtener_argumento/CompromisoEstrategico",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO tipoMeta = listaGenerico.get("/administracion_lista_simple/obtener_argumento/TipoMeta",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		model.addAttribute("compro", compromiso.getLstObjectsDTO());
		
		GenericoDTO compro = listaGenerico.get("/ipformulacion/obtener_pdd_compromiso_por_id_plan/"+pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		GenericoDTO tematicasCompromiso = listaGenerico.get("/administracion_lista_simple/obtener_tematicas_por_id_plan/"+pddDTO.getIdPlanDesarrollo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		
		
		if (!idCompromiso.equals("")) {
		ArbolCompromisoDTO compromisoEspecifico = arboldto.get("/ipformulacion/obtener_pdd_compromiso_especifico_por_id_compromiso/"+idCompromiso,
				new ParameterizedTypeReference<ArbolCompromisoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		System.out.println("esto es id"+idCompromiso);
		PddCompromisoDTO compromisoAuxiliar = listPddCompromiso.get("/ipformulacion/obtener_pdd_compromiso_por_id/"+idCompromiso,
				new ParameterizedTypeReference<PddCompromisoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		
		model.addAttribute("compromisoEspecifico",compromisoEspecifico.getMapObjetos().get(NHSPDDConstantes.MAPA_LLAVE_COMPROMISO));
		model.addAttribute("metas",compromisoEspecifico.getMapObjetos().get(NHSPDDConstantes.MAPA_LLAVE_META));
		model.addAttribute("obras",compromisoEspecifico.getMapObjetos().get(NHSPDDConstantes.MAPA_LLAVE_OBRA));
		model.addAttribute("tipoMeta", tipoMeta.getLstObjectsDTO());
		model.addAttribute("radioSeleccionado", idCompromiso);
		model.addAttribute("compromisoAuxiliar", compromisoAuxiliar);
		
		}
		
		model.addAttribute("compromisoDTO", compro.getLstObjectsDTO());
		model.addAttribute("tematicaCompromiso", tematicasCompromiso.getLstObjectsDTO());
		return "ip/programa-gobierno/ver-compromisos-programas.html";
	}
	
	@PostMapping("/agregar-compromiso")
	@Override
	public String crearCompromiso(PddCompromisoDTO compromiso, RedirectAttributes redirectAttributes) {
		
		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(compromiso);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 PddCompromisoDTO compro = listCompromiso.post(parametro, new ParameterizedTypeReference<PddCompromisoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO, NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageCompromiso", compro.getMsgRespuesta());
		return "redirect:/ver-pdd";
		
	}
	@PostMapping("/editar-compromiso")
	public String editarCompromiso(PddCompromisoDTO compromiso, RedirectAttributes redirectAttributes) {
		
	
		 PddCompromisoDTO compro = listCompromiso.put(compromiso, PddCompromisoDTO.class,
				 NHSPDDConstantes.CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO, NHSPDDConstantes.TIPO_CORE);
		
		redirectAttributes.addFlashAttribute("messageCompromiso",compro.getMsgRespuesta());
		return "redirect:/ver-pdd";
		
	}
	@PostMapping("/consultar-especifico")
	public String consultarCompromisosEspecificos(PddCompromisoDTO compromiso,  Model model) {
		
		model.addAttribute("idCompromiso", compromiso.getIdCompromiso());
		
		return "redirect:/ver-pdd";
	}
	
}
