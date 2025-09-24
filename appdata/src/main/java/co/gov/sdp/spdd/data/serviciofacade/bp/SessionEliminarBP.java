package co.gov.sdp.spdd.data.serviciofacade.bp;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
<<<<<<< HEAD
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
=======
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
>>>>>>> developer
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLocaliza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;

/**
 * Clase que representa el Facade para el servicio Eliminar del modulo BP
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Component
public class SessionEliminarBP extends SessionBP implements Serializable {

	/**
	 * Metodo que permite eliminar de la BD todos los BpProyInvTipo que estan
	 * relacionados a un proyecto de inversion
	 * 
	 * @param idBpProyectoInversion Long que representa el identificador del
	 *                              proyecto de inversion del cual se quiere
	 *                              eliminar los BpProyInvTipo
	 */
	public void eliminarBpProyInvTiposDeIdProyectoInversion(Long idBpProyectoInversion) throws SpddExceptions {
		List<BpProyInvTipo> listaTipos = bpProyInvTipoServiceRepo.obtenerPorIdProyectoInversion(idBpProyectoInversion);

		if (!listaTipos.isEmpty()) {
			bpProyInvTipoServiceRepo.eliminar(listaTipos);
		}
	}

	public void eliminarGruposEtarios(List<BpIniciativaEtarioDTO> listaEtarios) throws SpddExceptions {
		bpIniciativaEtarioServiceRepo.eliminarTodos(bpIniciativaEtarioMapper.etarioDTOToEntities(listaEtarios));
	}

	public void eliminarUbicaciones(List<BpIniciativaUbicacionDTO> listaUbicaciones) throws SpddExceptions {
		bpIniciativaUbicacionServiceRepo.eliminarTodosUbicaciones(
				bpIniciativaUbicacionMapper.iniciativaUbicacionDTOToEntities(listaUbicaciones));
	}

	public void eliminatIniciativaCiudadana(Long idIniciativa) {
		iniciativaCiudadanaServiceRepo.eliminar(idIniciativa);
	}

	/**
	 * Metod que permite eliminar un BpProyInvAporte por medio de su identificador
	 * 
	 * @param idProyAporte Long que representa el identificador del BpProyInvAporte
	 *                     a eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvAporte(Long idProyAporte) throws SpddExceptions {
		bpProyInvAporteServiceRepo.eliminar(idProyAporte);
	}

	/**
	 * Metod que permite eliminar un BpAporteCiudadano por medio de su identificador
	 * 
	 * @param idAporte Long que representa el identificador del BpAporteCiudadano a
	 *                 eliminar
	 * @throws SpddExceptions
	 */
	public void eliminarBpAporteCiudadano(Long idAporte) throws SpddExceptions {
		bpAporteCiudadanoServiceRepo.eliminar(idAporte);
	}

	/**
	 * Metodo que permite eliminar todos los Aportes Ciudadanos que hayan sido
	 * cargados desde un archivo plano y que esten relacionados con el proyecto de
	 * inversion pasado como parametro. Es decir los registros en la tabla
	 * intermedia BpProyInvAporte
	 * 
	 * @param idProyecto Long que represente el identificado del proyecto al cual se
	 *                   le eliminaran todos lo apotes ciudadanos cargados desde un
	 *                   archivo que esten relacionados.
	 * @throws SpddExceptions
	 */
	public void eliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto(Long idProyInversion) throws SpddExceptions {
		List<BpProyInvAporte> listaBpProyInvAporte = bpProyInvAporteServiceRepo
				.obtenerTodosCargadoArchivoPorIdProyInversion(idProyInversion);

		if (!listaBpProyInvAporte.isEmpty()) {
			bpProyInvAporteServiceRepo.eliminarTodos(listaBpProyInvAporte);
		}
	}

	public void eliminarIniciativaCondiciones(List<BpIniciativaCondicionDTO> listaCondiciones) throws SpddExceptions {
		bpIniciativaCondicionServiceRepo.eliminarTodasLasCondicionesInciativas(
				bpIniciativaCondicionMapper.iniciativaCondicionDTOToEntities(listaCondiciones));
	}

	/**
	 * Metodo que permite elimnar todas las relaciones entre iniciativa ciudadana y
	 * proyecto de inversion correspondientes al proyecto de inversion especificado
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion al cual se le va a elimnar los registros
	 */
	public void eliminarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions {
		List<BpProyInvIniciativa> listaBpProyInvIniciativa = bpProyInvIniciativaServiceRepo
				.obtenerTodosPorIdProyInversion(idProyecto);

		if (!listaBpProyInvIniciativa.isEmpty()) {
			bpProyInvIniciativaServiceRepo.eliminarTodos(listaBpProyInvIniciativa);
		}
	}

	/**
	 * Metodo que permite elimnar todas las relaciones entre territorializacion y
	 * proyecto de inversion correspondientes al proyecto de inversion especificado
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion al cual se le va a elimnar los registros
	 */
	public void eliminarTodosBpProyInvLocalizaPorIdProyInversion(Long idProyecto) throws SpddExceptions {
		List<BpProyInvLocaliza> lstBpProyInvLocaliza = bpProyInvLocalizaServiceRepo
				.obtenerTodosPorIdProyInversion(idProyecto);
		if (!lstBpProyInvLocaliza.isEmpty()) {
			bpProyInvLocalizaServiceRepo.eliminarTodos(lstBpProyInvLocaliza);
		}
	}
<<<<<<< HEAD
	
	/**
	 * metoto que permite eliminar un BpProyInvPoblacion
	 * @param peticion
	 * @return
	 */
	public void eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) {
		
		bpProyInvPoblacionServiceRepo.eliminar(peticion.getIdPoblacion());;

	}
	/**
	 * metoto que permite eliminar un BpProyInvEtnico
	 * @param peticion
	 * @return
	 */
	public void eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) {
		
		bpProyInvEtnicoServiceRepo.eliminar(peticion.getIdEtnico());

	}
	
=======


	/**
	 * Metodo que permite eliminar todos los ProyInvAnualiza por el Id de la fuente
	 * 
	 * @param idFuente id de la fuente en cuestion
	 */
	public void eliminarTodosProyInvAnualizaPorIdFuente(Long idFuente) throws SpddExceptions {

		List<BpProyInvAnualiza> listAnualiza = bpProyInvAnualizaServiceRepo
				.obtenerPorIdTodosProyInvAnualizacion(idFuente);

		if (!listAnualiza.isEmpty()) {

			bpProyInvAnualizaServiceRepo.eliminarTodosPorIdFUente(listAnualiza);
		}
	}

	/**
	 * Metoso que permite eliminar Fuente de financición por ID
	 * 
	 * @param idFuente id de la fuente de financiacion
	 */
	public void eliminarProyInvFinanciaPorId(Long idFuente) throws SpddExceptions {

		bpProyInvFinanciaServiceRepo.eliminar(idFuente);
	}


	/**
	 * Metodo que permite eliminar un BpProyInvPolitica de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y politica publica
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvPolitica(Long idProyPolitica) throws SpddExceptions{
		bpProyInvPoliticaServiceRepo.eliminar(idProyPolitica);
	}
	
	/**
	 * Metodo que permite eliminar un BpProyInvLinea de la BD
	 * @param idProyPolitica Long que representa el identificador de la relacion entre proyecto de inversion y linea de inversion
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvLinea(Long idProyLinea) throws SpddExceptions{
		bpProyInvLineaServiceRepo.eliminar(idProyLinea);
	}
	
	/**
	 * Metodo que permite eliminar un bpProyInvPmr de la BD
	 * @param idProyPmr Long que representa el identificador de bpProyInvPmr
	 * @throws SpddExceptions
	 */
	public void eliminarBpProyInvPmr(Long idProyPmr) throws SpddExceptions{
		bpProyInvPmrServiceRepo.eliminar(idProyPmr);
	}

>>>>>>> developer
}
