package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

@Controller
@SessionAttributes({ "tokenSesionSeguridad", "usuarioSesion", "codigoEntidadUsuario", "respuestaAutenticacion",
		"nombreUsuarioSesion", "proyecto" })
public class DescripcionProyectoController {

	@Autowired
	private MetodosRest<BpProyectoInversionDTO> listaProyecto;
	
	@PostMapping("/agregarEditarDescripcion")
	@ResponseBody
    public BpProyectoInversionDTO editarIdentificacionProyecto(BpProyectoInversionDTO proyecto) {
		System.out.println("esto es lo que esta llegando edit"+proyecto.toString());
		
		BpProyectoInversionDTO proyectoDto =  listaProyecto.get("/bpproyinv/obtener_bp_proyecto_inversion_por_id/"+proyecto.getIdProyInversion(),
				new ParameterizedTypeReference<BpProyectoInversionDTO>() {
				}, NHSPDDConstantes.TIPO_CORE);
		
		proyectoDto.setDescripcion(proyecto.getDescripcion());
		
		return listaProyecto.put(proyectoDto, BpProyectoInversionDTO.class,
			"/bpproyinv/modificar_bp_proyecto_inversion", NHSPDDConstantes.TIPO_CORE);	
		
	}
	
}
