package co.gov.sdp.spdd.web.bp.controller.reportes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportesController {

	
	@GetMapping("/reporte-proyecto-especifico")
	public String consultarProyectoEspecifico() {
		return "bp/reporte/reporte-proyecto-especifico";
	}
	
	@GetMapping("/reporte-ficha-ebi")
	public String consultarFichaEBI() {
		return "bp/reporte/reporte-ficha-ebi";
	}
	
	@GetMapping("/reporte-proyecto-registrado")
	public String consultarProyectoRegistrado() {
		
		return "bp/reporte/reporte-proyecto-registrado";
	}
	
	@GetMapping("/reporte-iniciativas-ciudadanas")
	public String consultarIniciativasCiudadanas() {
		return "bp/reporte/reporte-iniciativas-ciudadanas";
	}
}
