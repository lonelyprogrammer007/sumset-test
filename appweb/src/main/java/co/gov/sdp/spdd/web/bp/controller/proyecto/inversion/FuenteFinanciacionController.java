package co.gov.sdp.spdd.web.bp.controller.proyecto.inversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.web.util.MetodosRest;

/**
 * 
 * Clase FuenteFinanciacionController que permite controlar las peticiones que
 * se necesiten para el tab-fuentes
 * 
 * @author Jefferson Arenas
 * @version 10/06/2020
 *
 */
@Controller
public class FuenteFinanciacionController {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MetodosRest<GenericoDTO> listaGenerico;

	@Autowired
	private MetodosRest<BpProyInvFinanciaDTO> fuenteFinancia;

	/**
	 * metodo que permite obtener las fuentes de financiacion y sus respectivas
	 * anualizaciones de un proyecto de inversion
	 * 
	 * @param idProyInv el id del proyecto de inversion al cual se le va a obtener
	 *                  la lista respectiva
	 * @return un DTO con los datos pertinentes
	 */
	@GetMapping("/obtenerFuentesFinanciacion/{id}")
	@ResponseBody
	public GenericoDTO obtenerTodosProyInvFianciaPorIdProyInversion(@PathVariable("id") Long idProyInv) {

		BpProyInvFinanciaDTO bpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		bpProyInvFinanciaDTO.setIdProyInversion(idProyInv);

		String parametro = "";

		try {
			parametro = mapper.writeValueAsString(bpProyInvFinanciaDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaGenerico.post(parametro, new ParameterizedTypeReference<GenericoDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BP_OBTENER_TODAS_FUENTES_FINANCIACION_PYOYECT, NHSPDDConstantes.TIPO_CORE);

	}

	@PostMapping("/registrarBpProyInvFinancia")
	@ResponseBody
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO proyectoInvFinancia) {

		if (proyectoInvFinancia.getIdFuente() == null) {
			/// CAMBIAR EL ID YA DEBERIA DE LLEGAR DE LA PAGINA
			proyectoInvFinancia.setIdProyInversion(2L);

			String parametro = "";

			try {
				parametro = mapper.writeValueAsString(proyectoInvFinancia);
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			return fuenteFinancia.post(parametro, new ParameterizedTypeReference<BpProyInvFinanciaDTO>() {
			}, NHSPDDConstantes.CORE_CONTROLLER_BP_REGISTRAR_FUENTE_FINANCIACION_PYOYECT, NHSPDDConstantes.TIPO_CORE);
		} else {
			return fuenteFinancia.put(proyectoInvFinancia, BpProyInvFinanciaDTO.class , NHSPDDConstantes.CORE_CONTROLLER_BP_MODIFICAR_FUENTE_FINANCIACION, NHSPDDConstantes.TIPO_CORE);
			
		}
	}

	@GetMapping("/eliminarBpProyInvFinancia/{idFuente}")
	@ResponseBody
	public BpProyInvFinanciaDTO eliminarProyInvFinanciaPorId(@PathVariable("idFuente") Long idFuente) {

		String parametro = "";
		BpProyInvFinanciaDTO bpProyInvFinanciaDTO = new BpProyInvFinanciaDTO();
		bpProyInvFinanciaDTO.setIdFuente(idFuente);

		try {
			parametro = mapper.writeValueAsString(bpProyInvFinanciaDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fuenteFinancia.post(parametro, new ParameterizedTypeReference<BpProyInvFinanciaDTO>() {
		}, NHSPDDConstantes.CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_FINANCIA, NHSPDDConstantes.TIPO_CORE);

	}

}
