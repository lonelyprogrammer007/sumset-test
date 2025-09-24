package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaYTerritorizacionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
public class IdentificacionProblemaController {
	
	@Autowired
	MetodosRest<BpProyInvLocalizaYTerritorizacionDTO> identificacionRest;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	SPDDLogger spddLogger;
	
	@PostMapping("/registrasIdentificacion")
	@ResponseBody
	public BpProyInvLocalizaYTerritorizacionDTO registrarIdentficacion(BpProyInvLocalizaYTerritorizacionDTO peticion) {
		
		String parametro = "";
		
		try {
			parametro = mapper.writeValueAsString(peticion);
		} catch (JsonProcessingException e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, peticion.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, ProyectoInversionController.class);
		}
		
		return identificacionRest.post(parametro, new ParameterizedTypeReference<BpProyInvLocalizaYTerritorizacionDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_LOCALIZA, NHSPDDConstantes.TIPO_CORE);
		
		
		
	}

}
