package co.gov.sdp.spdd.web.afs.controller.archivos.planos;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.CargaAchivoDTO;
import co.gov.sdp.nhspdd.common.dto.CarguePlanoDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PermisoRolEventoDto;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "archivoDTO" })
public class ArchivosPlanosController {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	SPDDLogger spddLogger;

	@Value("${com.direccion.url.appcore}")
	private String urlCore;

	private RestTemplate rest = new RestTemplate();

	private HttpHeaders headers = new HttpHeaders();

	@GetMapping("/consultar-archivos")
	public String consultarArchivosPlanos(@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		headers.clear();
		for (int i = 0; i < respuestaAutenticacion.getListaPermisos().size(); i++) {

			if (respuestaAutenticacion.getListaPermisos().get(i).getUrl() != null
					&& request.toString().indexOf(respuestaAutenticacion.getListaPermisos().get(i).getUrl()) >= 0) {

				return "afs/archivos-planos/consultar-archivos";
			}

		}
		redirectAttributes.addFlashAttribute("messagePermisos", "No tiene permisos para ingresar a esta dirección");
		return "redirect:/home";
	}

	@PostMapping("/archivoPlanoCargar")
	public String cargarArchivo(@ModelAttribute CargaAchivoDTO archivoPlano, @RequestParam("file1") MultipartFile file,
			@ModelAttribute("tokenSesionSeguridad") String tokenSesionSeguridad,
			@ModelAttribute("usuarioSesion") String usuarioSesion,
			@ModelAttribute("codigoEntidadUsuario") String codigoEntidadUsuario,
			@ModelAttribute("respuestaAutenticacion") PermisoRolEventoDto respuestaAutenticacion,
			@ModelAttribute("nombreUsuarioSesion") String nombreUsuarioSesion, RedirectAttributes redirectAttributes) {

		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		archivoPlano.setUsuario(usuarioSesion);
		String tempFileName = new File(".").getAbsolutePath() + file.getOriginalFilename();

		try (

				FileOutputStream fo = new FileOutputStream(tempFileName);)

		{

			fo.write(file.getBytes());
			fo.close();
			map.add("file2", new FileSystemResource(tempFileName));
			map.add("tema", archivoPlano.getTema());
			map.add("modulo", archivoPlano.getModulo());
			map.add("usuario", usuarioSesion);
		} catch (Exception e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA,
							archivoPlano.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivosPlanosController.class);
		}

		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", NHSPDDConstantes.TOKEN_PREFIX + tokenSesionSeguridad);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
		CarguePlanoDTO cargue = rest.postForObject(
				urlCore + NHSPDDConstantes.CORE_CONTROLLER_CARGA_POST_CARGAR_ARCHIVO_PLANO, requestEntity,
				CarguePlanoDTO.class);
		File f = new File(tempFileName);
		f.delete();
		if (!cargue.getListaRegistroArchivoDTO().isEmpty()) {
			redirectAttributes.addFlashAttribute("arch", archivoPlano);
			redirectAttributes.addFlashAttribute("cargue", cargue);
			System.out.println("soy un archivo" + cargue.getListaRegistroArchivoDTO());
			redirectAttributes.addFlashAttribute("cargueTabla", cargue.getListaRegistroArchivoDTO());
			redirectAttributes.addFlashAttribute("msgRespuesta", cargue.getMsgRespuesta());
		} else {
			redirectAttributes.addFlashAttribute("msgRespuesta", cargue.getMsgRespuesta());
		}
		return "redirect:/cargar-archivos";
	}

	@PostMapping("/ver-archivo")
	public String verArchivo(Model model, ArchivoProcesadoDTO archivo) {
		model.addAttribute("archivoDTO", archivo);
		return "redirect:/cargar-archivos-planos";
	}

	@GetMapping("/cargar-archivos-planos")
	public String cargarArchivosPlanos1(@ModelAttribute("archivoDTO") ArchivoProcesadoDTO archivo, Model model) {

		GenericoDTO respuesta = listaGenerico.get("/obtener_por_archivo/" + archivo.getIdArchivo(),
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("presupuestoConsulta", respuesta.getLstObjectsDTO());

		return "afs/archivos-planos/ver-archivos";
	}

	@GetMapping("/cargar-archivos")
	public String obtenerDatosArchivoPlano(Model model) {

		GenericoDTO tema = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Tema",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		GenericoDTO modulo = listaGenerico.get("/administracion_lista_simple/obtener_argumento/Modulo",
				new ParameterizedTypeReference<GenericoDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);

		model.addAttribute("modulo", modulo.getLstObjectsDTO());
		model.addAttribute("tema", tema.getLstObjectsDTO());

		return "afs/archivos-planos/carga-archivos";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/listaArchivosByPage")
	public ResponseEntity listarInformacionArchivos(@RequestParam("draw") int draw, @RequestParam("start") int start,
			@RequestParam("length") int length, HttpServletRequest req) {

		ArchivoProcesadoDTO archivo = new ArchivoProcesadoDTO();

		String search0 = req.getParameter("columns[0][search][value]");
		String search1 = req.getParameter("columns[1][search][value]");
		String search2 = req.getParameter("columns[2][search][value]");
		String search3 = req.getParameter("columns[3][search][value]");
		String search4 = req.getParameter("columns[4][search][value]");
		int sortableCol = Integer.parseInt(req.getParameter("order[0][column]"));
		String sortDir = req.getParameter("order[0][dir]");
		String name = req.getParameter("columns[" + sortableCol + "][data]");

		if ("".equals(search0)) {
			archivo.setIdArchivo(null);
			archivo.setNombreModulo(search1);
			archivo.setNombreTema(search2);
		} else {
			archivo.setIdArchivo(Long.parseLong(search0));
			archivo.setNombreModulo(search1);
			archivo.setNombreTema(search2);
		}
		archivo.setNombreArchivo(search3);

		int pageNo = (start) / length + 1;
		if (length == -1) {
			length = Integer.MAX_VALUE;
			pageNo = 1;
			archivo.setTamanioPagina(Integer.MAX_VALUE);
			archivo.setPagina(pageNo - 1);
		} else {
			archivo.setTamanioPagina(length);
			archivo.setPagina(pageNo - 1);
		}

		if ("nombreModulo".equals(name)) {
			name = "idLsModulo";
		} else if ("nombreTema".equals(name)) {
			name = "idLsTema";
		}
		archivo.setColumnaOrdenar(name);
		archivo.setTipoOrden(sortDir);
		archivo.setFechaCargue(search4);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(archivo);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, archivo.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ArchivosPlanosController.class);
		}
		GenericoDTO res = listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_CARGA_GET_OBTENER_ARCHIVOS_PLANOS_TODOS, NHSPDDConstantes.TIPO_CORE);

		Pageable pageRequest = new PageRequest(length, pageNo);

		Page<ArchivoProcesadoDTO> responseData = new PageImpl(res.getLstObjectsDTO(), pageRequest, res.getTotal());

		DataTable<ArchivoProcesadoDTO> dataTable = new DataTable<>();
		dataTable.setData(responseData.getContent());
		dataTable.setRecordsTotal(responseData.getTotalElements());
		dataTable.setRecordsFiltered(responseData.getTotalElements());
		dataTable.setDraw(draw);
		dataTable.setStart(start);

		return ResponseEntity.ok(dataTable);

	}

}
