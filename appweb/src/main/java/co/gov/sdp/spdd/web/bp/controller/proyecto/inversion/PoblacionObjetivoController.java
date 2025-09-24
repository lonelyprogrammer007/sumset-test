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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.spdd.web.afs.controller.administracion.lista.compuesta.LineaDeInversionController;
import co.gov.sdp.spdd.web.util.DataTable;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
public class PoblacionObjetivoController {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<BpProyInvPoblacionDTO> restGrupoEtario;

	@Autowired
	private MetodosRest<BpProyInvEtnicoDTO> restBpProyInvEtnico;

	@GetMapping("/obtenerPoblacion/{id}")
	@ResponseBody
	public GenericoDTO obtenerPoblacionPorIdProyectoInversion(@PathVariable("id") Long idProyInv) {

		BpProyInvPoblacionDTO bpProyInvPoblacionDTO = new BpProyInvPoblacionDTO();
		bpProyInvPoblacionDTO.setIdProyInversion(idProyInv);
		bpProyInvPoblacionDTO.setPagina(0);
		bpProyInvPoblacionDTO.setTamanioPagina(null);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(bpProyInvPoblacionDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION,
				NHSPDDConstantes.TIPO_CORE);
	}

	@GetMapping("/obtenerSectores/{id}")
	@ResponseBody
	public GenericoDTO obtenerSectoresPorIdPoblacion(@PathVariable("id") Long idPoblacion) {

		BpProyInvEtnicoDTO bpProyInvEtnicoDTO = new BpProyInvEtnicoDTO();
		bpProyInvEtnicoDTO.setIdPoblacion(idPoblacion);
		bpProyInvEtnicoDTO.setPagina(0);
		bpProyInvEtnicoDTO.setTamanioPagina(null);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(bpProyInvEtnicoDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INV_POBLACION,
				NHSPDDConstantes.TIPO_CORE);
	}

	@PostMapping("/registrarGruposEtarios/{id}")
	@ResponseBody
	public BpProyInvPoblacionDTO registrarGrupoEtario(@PathVariable("id") Long idProyInversion,
			BpProyInvPoblacionDTO peticion) {

		if (peticion.getIdPoblacion() == null) {
			// guardar
			peticion.setIdProyInversion(idProyInversion);

			// validacion si es no aplica grupo etario
			if (peticion.getNumero() == null) {
				peticion.setNumero(0l);
			}

			String parametro = "";
			try {
				parametro = mapper.writeValueAsString(peticion);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return restGrupoEtario.post(parametro, new ParameterizedTypeReference<BpProyInvPoblacionDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION,
					NHSPDDConstantes.TIPO_CORE);
		}

		// editar

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return restGrupoEtario.put(peticion, BpProyInvPoblacionDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_POBLACION, NHSPDDConstantes.TIPO_CORE);

	}

	@PostMapping("/registrarSectorCondicion/{id}")
	@ResponseBody
	public BpProyInvEtnicoDTO registrarSectorCondicion(@PathVariable("id") Long idPoblacion,
			BpProyInvEtnicoDTO peticion) {

		if (peticion.getIdEtnico() == null) {
			// guardar
			peticion.setIdPoblacion(idPoblacion);
			String parametro = "";
			try {
				parametro = mapper.writeValueAsString(peticion);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return restBpProyInvEtnico.post(parametro, new ParameterizedTypeReference<BpProyInvEtnicoDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_PROYECTO_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION,
					NHSPDDConstantes.TIPO_CORE);
		}

		// editar

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return restBpProyInvEtnico.put(peticion, BpProyInvEtnicoDTO.class,
				NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_ETNICO, NHSPDDConstantes.TIPO_CORE);

	}

	@PostMapping("/eliminarGrupoEtario/{id}")
	@ResponseBody
	public BpProyInvPoblacionDTO eliminarGrupoEtario(BpProyInvPoblacionDTO peticion,
			@PathVariable("id") Long idPoblacion) {

		peticion.setIdPoblacion(idPoblacion);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return restGrupoEtario.post(parametro, new ParameterizedTypeReference<BpProyInvPoblacionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_POBLACION, NHSPDDConstantes.TIPO_CORE);
	}
	
	@PostMapping("/eliminarSector/{id}")
	@ResponseBody
	public BpProyInvEtnicoDTO eliminarSector(BpProyInvEtnicoDTO peticion,
			@PathVariable("id") Long idEtnico) {

		peticion.setIdEtnico(idEtnico);

		String parametro = "";
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return restBpProyInvEtnico.post(parametro, new ParameterizedTypeReference<BpProyInvEtnicoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_ETNICO, NHSPDDConstantes.TIPO_CORE);
	}

}
