package co.gov.sdp.spdd.data.sesionfacade.impl;

import java.nio.channels.SeekableByteChannel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaEtarioDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaUbicacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvActividadDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionConsultaBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionEliminarBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionModificarBP;
import co.gov.sdp.spdd.data.serviciofacade.bp.SessionRegistroBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;

/**
 * Clase del SessionFacade del modulo BP que permite la comunicacion entre
 * appdata y appcore
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Service
public class SessionFacadeBP implements ISessionFacadeBP {

	/**
	 * Inyeccion del servicio Consultar del modulo IP
	 */
	@Autowired
	SessionConsultaBP sessionConsultaBP;

	/**
	 * Inyeccion del servicio Eliminar del modulo IP
	 */
	@Autowired
	SessionEliminarBP sessionEliminarBP;

	/**
	 * Inyeccion del servicio Registrar del modulo IP
	 */
	@Autowired
	SessionRegistroBP sessionRegistroBP;

	/**
	 * Inyeccion del servicio Modificar del modulo IP
	 */
	@Autowired
	SessionModificarBP sessionModificarBP;

	@Override
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyectoInversionPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarBpProyectoInversionTodos() throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyectoInversionTodos();
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvLineaPorIdProyectoInversion(Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvLineaPorIdProyectoInversion(idProyecto);
	}

	@Override
	public GenericoDTO consultarBpProyInvTipoPorIdProyectoInversion(Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvTipoPorIdProyectoInversion(idProyecto);
	}

	@Override
	public BpProyInvTipoDTO consultarBpProyInvTipoPorIdLsTipoYIdProyInv(Long idLsTipo, Long idProyInversion)
			throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(idLsTipo, idProyInversion);
	}

	@Override
	public GenericoDTO consultarBpAporteCiudadanoTodos() throws SpddExceptions {
		return sessionConsultaBP.consultarBpAporteCiudadanoTodos();
	}

	@Override
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto)
			throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
	}

	@Override
	public GenericoDTO filtrarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions {
		return sessionConsultaBP.filtroInciativaCiudadana(peticion);
	}

	@Override
	public BpProyectoInversionDTO guardarProyectoInversionIndentificacionProyecto(
			BpProyectoInversionDTO bpProyectoInversionDTO) throws SpddExceptions {
		return sessionRegistroBP.guardarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO);
	}

	@Override
	public BpProyInvTipoDTO guardarProyInvTipo(BpProyInvTipoDTO bpProyInvTipoDTO) throws SpddExceptions {
		BpProyInvTipoDTO bpProyInvTipoDTOAux = sessionConsultaBP.consultarBpProyInvTipoPorIdLsTipoYIdProyInv(
				bpProyInvTipoDTO.getIdLsTipo(), bpProyInvTipoDTO.getIdProyInversion());

		return bpProyInvTipoDTOAux.getIdTipo() == null ? sessionRegistroBP.guardarProyInvTipo(bpProyInvTipoDTO)
				: new BpProyInvTipoDTO();
	}

	@Override
	public BpProyInvAporteDTO guardarBpProyInvAporte(BpProyInvAporteDTO bpProyInvAporteDTO) throws SpddExceptions {
		BpProyInvAporteDTO bpProyInvAporteDTOAux = sessionConsultaBP
				.consultarBpProyInvAportePorIdAporteYIdProyInversion(bpProyInvAporteDTO.getIdAporte(),
						bpProyInvAporteDTO.getIdProyInversion());

		return bpProyInvAporteDTOAux.getIdProyAporte() == null
				? sessionRegistroBP.guardarBpProyInvAporte(bpProyInvAporteDTO)
				: new BpProyInvAporteDTO();
	}

	@Override
	public void eliminarBpProyInvTiposDeIdProyectoInversion(Long idBpProyectoInversion) throws SpddExceptions {
		sessionEliminarBP.eliminarBpProyInvTiposDeIdProyectoInversion(idBpProyectoInversion);
	}

	@Override
	public BpProyectoInversionDTO consultarProyInvPorId(Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP.consultarProyInvPorId(idProyecto);
	}

	@Override
	public BpAporteCiudadanoDTO guardarBPAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO)
			throws SpddExceptions {
		BpAporteCiudadanoDTO bpAporteCiudadanoDTOAux = sessionConsultaBP
				.consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(bpAporteCiudadanoDTO.getAccion(),
						bpAporteCiudadanoDTO.getFuente(), bpAporteCiudadanoDTO.getIdNivelAtributoPdd());

		return bpAporteCiudadanoDTOAux.getIdAporte() == null
				? sessionRegistroBP.guardarBPAporteCiudadano(bpAporteCiudadanoDTO)
				: new BpAporteCiudadanoDTO();
	}

	@Override
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion)
			throws SpddExceptions {

		return sessionRegistroBP.guardarIniciativaCiudadana(peticion);
	}

	@Override
	public void eliminarGruposEtarios(Long idIniciativa) throws SpddExceptions {

		sessionEliminarBP.eliminarGruposEtarios(sessionConsultaBP.obtenerGruposEtariosPorIniciativa(idIniciativa));
	}

	@Override
	public void eliminarIniciativaCiudadana(Long idIniciativa) throws SpddExceptions {
		sessionEliminarBP.eliminatIniciativaCiudadana(idIniciativa);
	}

	@Override
	public void eliminarUbicaciones(Long idIniciativa) throws SpddExceptions {
		sessionEliminarBP.eliminarUbicaciones(sessionConsultaBP.obtenerUbicacionPorIniciativa(idIniciativa));
	}

	@Override
	public BpIniciativaEtarioDTO guardarGruposEtarios(BpIniciativaEtarioDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarGrupoEtario(peticion);
	}

	@Override
	public BpIniciativaUbicacionDTO guardarUbicacionIniciativaCiudadana(BpIniciativaUbicacionDTO peticion)
			throws SpddExceptions {
		return sessionRegistroBP.guardarUbicacion(peticion);
	}

	@Override
	public BpProyectoInversionDTO modificarProyectoInversionProyecto(BpProyectoInversionDTO bpProyectoInversionDTO)
			throws SpddExceptions {
		return sessionRegistroBP.guardarProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO);
	}

	@Override
	public List<BpIniciativaCondicionDTO> obtenerCondicionesPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return sessionConsultaBP.obtenerCondicionesPorIniciativa(idIniciativa);
	}

	@Override
	public BpIniciativaCondicionDTO guardarIniciativaCondicion(BpIniciativaCondicionDTO peticion)
			throws SpddExceptions {
		return sessionRegistroBP.guardarCondicionIniciativa(peticion);
	}

	@Override
	public void eliminarTodasCondicionIniciativa(Long idIniciativa) throws SpddExceptions {
		sessionEliminarBP
				.eliminarIniciativaCondiciones(sessionConsultaBP.obtenerCondicionesPorIniciativa(idIniciativa));
	}

	@Override
	public BpIniciativaCiudadanaDTO consultarBpIniciativaCiudadanaPorId(Long idIniciativa) throws SpddExceptions {
		return sessionConsultaBP.consultarBpIniciativaCiudadanaPorId(idIniciativa);
	}

	@Override
	public List<BpIniciativaEtarioDTO> consultarGruposEtariosPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return sessionConsultaBP.obtenerGruposEtariosPorIniciativa(idIniciativa);
	}

	@Override
	public List<BpIniciativaUbicacionDTO> consultarUbicacionesGruposEtariosPorIniciativa(Long idIniciativa)
			throws SpddExceptions {
		return sessionConsultaBP.obtenerUbicacionPorIniciativa(idIniciativa);
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO bpProyInvAporteDTO)
			throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpProyInvAportePorIdProyInversionPaginado(bpProyInvAporteDTO);
	}

	@Override
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte) throws SpddExceptions {
		return sessionConsultaBP.consultarBpAporteCiudadanoPorId(idAporte);
	}

	@Override
	public void eliminarBpProyInvAporte(Long idProyAporte) throws SpddExceptions {
		sessionEliminarBP.eliminarBpProyInvAporte(idProyAporte);
	}

	@Override
	public void eliminarBpAporteCiudadano(Long idAporte) throws SpddExceptions {
		sessionEliminarBP.eliminarBpAporteCiudadano(idAporte);
	}

	@Override
	public BpProyInvAporteDTO consultarProyInvAportePorId(Long idProyInvAporte) throws SpddExceptions {
		return sessionConsultaBP.consultarProyInvAportePorId(idProyInvAporte);
	}

	@Override
	public BpAporteCiudadanoDTO modificarBpAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO)
			throws SpddExceptions {
		return sessionRegistroBP.guardarBPAporteCiudadano(bpAporteCiudadanoDTO);
	}

	@Override
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(String accion, String fuente,
			Long idPddNivelAtributo) throws SpddExceptions {
		return sessionConsultaBP.consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(accion, fuente,
				idPddNivelAtributo);

	}

	@Override
	public void eliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto(Long idProyInversion) throws SpddExceptions {
		sessionEliminarBP.eliminarTodosBpProyInvAporteCargadosArchivoPorIdProyecto(idProyInversion);
	}

	@Override
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpAportesCiudadanosCargadosPorArchivos();
	}

	@Override
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(
			Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP
				.obtenerTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
	}

	@Override
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto)
			throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpProyInvIniciativaPorIdProyInversion(idProyecto);
	}

	@Override
	public void eliminarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions {
		sessionEliminarBP.eliminarTodosBpProyInvIniciativaPorIdProyInversion(idProyecto);
	}

	@Override
	public BpProyInvIniciativaDTO guardarBpProyInvIniciativa(BpProyInvIniciativaDTO bpProyInvIniciativaDTO)
			throws SpddExceptions {
		BpProyInvIniciativaDTO bpProyInvIniciativaDTOAux = sessionConsultaBP
				.consultarBpProyInvIniciativaPorIdIniciativaYIdProyInversion(bpProyInvIniciativaDTO.getIdIniciativa(),
						bpProyInvIniciativaDTO.getIdProyInversion());

		return bpProyInvIniciativaDTOAux.getIdIniciativaInv() == null
				? sessionRegistroBP.guardarBpProyInvIniciativa(bpProyInvIniciativaDTO)
				: new BpProyInvIniciativaDTO();
	}

	@Override
	public BpProyInvLocalizaDTO guardarBPProyectoInvLocaliza(BpProyInvLocalizaDTO BpProyInvLocalizaDTO)
			throws SpddExceptions {
		return sessionRegistroBP.guardarBPProyectoInvLocaliza(BpProyInvLocalizaDTO);
	}

	@Override
	public void eliminarTodosBpProyInvLocalizaPorIdProyecto(Long idProyInversion) throws SpddExceptions {
		sessionEliminarBP.eliminarTodosBpProyInvLocalizaPorIdProyInversion(idProyInversion);
	}

	@Override
	public BpProyInvEspecifDTO consultarBpProyInvEspecifPorId(Long idProyObjEspecif) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvEspecifPorId(idProyObjEspecif);
	}

	@Override
	public BpProyInvEspecifDTO guardarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvEspecif(peticion);
	}

	@Override
	public BpProyInvMetaPlanDTO guardarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvMetaPlan(peticion);
	}

	@Override
	public BpProyInvProductoDTO consultarBpProyInvProductoPorId(Long idProducto) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvProductoPorId(idProducto);
	}

	@Override
	public BpProyInvProductoDTO guardarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvProducto(peticion);
	}

	@Override
	public BpProyInvActividadDTO consultarBpProyInvActividadPorId(Long idProducto) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvActividadPorId(idProducto);
	}

	@Override
	public BpProyInvActividadDTO guardarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvActividad(peticion);
	}

	@Override
	public BpProyInvMetaPlanDTO consultarBpProyInvMetaPlanPorIdProyInvEspecif(Long idProyObjEspecif) {
		return sessionConsultaBP.obtenerPorIdProyInvEspecif(idProyObjEspecif);
	}

	@Override
	public BpProyInvProductoDTO consultarBpProyInvProductoPorIdProyInvMetaPlan(Long idProyMetaPlan) {
		return sessionConsultaBP.consultarBpProyInvProductoPorIdProyInvMetaPlan(idProyMetaPlan);
	}

	@Override
<<<<<<< HEAD
	public GenericoDTO consultarGruposEtarios(
=======
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa) throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpProyInvIniciativaPorIdIniciativa(idIniciativa);
	}

	@Override
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO bpProyInvFinanciaDTO)
			throws SpddExceptions {
		return sessionConsultaBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(bpProyInvFinanciaDTO);

	}

	@Override
	public GenericoDTO consultarTodosProyInvAnualizaPorIdFuentePaginado(BpProyInvAnualizaDTO bpProyInvAnualizaDTO)
			throws SpddExceptions {

		return sessionConsultaBP.consultarTodosProyInvAnualizaPorIdFuentePaginado(bpProyInvAnualizaDTO);

	}

	@Override
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions {

		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = sessionConsultaBP
				.consultarProyInvFinanciaPorIdLSFuYIdProy(peticion.getIdLsFuente(), peticion.getIdProyInversion());

		return auxBpProyInvFinanciaDTO.getIdFuente() == null ? sessionRegistroBP.registrarBpProyInvFinancia(peticion)
				: new BpProyInvFinanciaDTO();

	}

	public GenericoDTO consultarTodosProyInvPoblacionAsociadosAProyectoInversion(
>>>>>>> developer
			BpProyInvPoblacionDTO bpProyInvPoblacionDTO) throws SpddExceptions {
		return sessionConsultaBP.consultarGruposEtarios(bpProyInvPoblacionDTO);
	}

	@Override
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO bpProyInvEtnicoDTO)
			throws SpddExceptions {

		return sessionConsultaBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(bpProyInvEtnicoDTO);
	}

	@Override
	public BpProyInvPoblacionDTO guardarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions {
<<<<<<< HEAD
		
=======

//		BpProyInvPoblacionDTO bpProyInvPoblacionDTOAux = sessionConsultaBP.consultarBpProyInvPoblacionPorIdLsEtarioYIdProyInv(peticion.getIdLsEtario(), peticion.getIdProyInversion());
//	
//		return bpProyInvPoblacionDTOAux.getIdPoblacion() == null ? sessionRegistroBP.guardarBpProyInvPoblacion(peticion) : new BpProyInvPoblacionDTO();
>>>>>>> developer
		return sessionRegistroBP.guardarBpProyInvPoblacion(peticion);
	}

	@Override
	public BpProyInvEtnicoDTO guardarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvEtnico(peticion);
	}

	@Override
	public BpProyInvPoblacionDTO consultarBpProyInvPoblacionUnicidad(BpProyInvPoblacionDTO peticion)
			throws SpddExceptions {

		BpProyInvPoblacionDTO bpProyInvPoblacionDTOAux = sessionConsultaBP
				.consultarBpProyInvPoblacionPorIdLsEtarioYIdProyInv(peticion.getIdLsEtario(),
						peticion.getIdProyInversion());

		if (bpProyInvPoblacionDTOAux.getIdPoblacion() == null) {
			bpProyInvPoblacionDTOAux.setValidacionUnicidad(true);
		} else {
			bpProyInvPoblacionDTOAux.setValidacionUnicidad(false);
		}

		return bpProyInvPoblacionDTOAux;
	}

	@Override
	public BpProyInvEtnicoDTO consultarBpProyInvEtnicoUnicidad(BpProyInvEtnicoDTO peticion) throws SpddExceptions {
<<<<<<< HEAD
		
		BpProyInvEtnicoDTO bpProyInvEtnicoDTOAux = sessionConsultaBP.consultarBpProyInvEtnicoPorIdLsEtnicoYIdPoblacion(peticion.getIdLsEtnico(), peticion.getIdPoblacion());
		
		if(bpProyInvEtnicoDTOAux.getIdEtnico() == null) {
=======

		BpProyInvEtnicoDTO bpProyInvEtnicoDTOAux = sessionConsultaBP.consultarBpProyInvEtnicoPorIdLsEtnicoYIdProyInv(
				peticion.getIdLsEtnico(), peticion.getIdProyInversion());

		if (bpProyInvEtnicoDTOAux.getIdEtnico() == null) {
>>>>>>> developer
			bpProyInvEtnicoDTOAux.setValidacionUnicidad(true);
		} else {
			bpProyInvEtnicoDTOAux.setValidacionUnicidad(false);
		}

		return bpProyInvEtnicoDTOAux;
	}

	@Override
<<<<<<< HEAD
	public BpProyInvPoblacionDTO modificarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions {
		return sessionModificarBP.modificarBpProyInvPoblacion(peticion);
	}

	@Override
	public BpProyInvPoblacionDTO consultarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions {
		
		return sessionConsultaBP.consultarBpProyInvPoblacionPorIdPoblacion(peticion.getIdPoblacion());
	}

	@Override
	public void eliminarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions {
		
		sessionEliminarBP.eliminarBpProyInvPoblacion(peticion);
	}

	@Override
	public BpProyectoInversionDTO consultarBpProyectoInversion(BpProyectoInversionDTO peticion) throws SpddExceptions {
		
		return sessionConsultaBP.consultarBpProyectoInversion(peticion.getIdProyInversion());
	}

	@Override
	public BpProyInvEtnicoDTO consultarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions {
		
		return sessionConsultaBP.consultarBpProyInvEtnicoPorId(peticion.getIdEtnico());
	}

	@Override
	public void eliminarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions {
		
		sessionEliminarBP.eliminarBpProyInvEtnico(peticion);
=======
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorIdLSFuYIdProy(Long idLsFUente, Long idProyectoInv) {

		BpProyInvFinanciaDTO auxBpProyInvFinanciaDTO = sessionConsultaBP
				.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente, idProyectoInv);

		return auxBpProyInvFinanciaDTO.getIdFuente() == null ? new BpProyInvFinanciaDTO() : auxBpProyInvFinanciaDTO;

	}

	@Override
	public BpProyInvAnualizaDTO registrarBpProyInvAnualiza(BpProyInvAnualizaDTO peticion) throws SpddExceptions {
		return sessionRegistroBP.registrarBpProyInvAnualiza(peticion);
	}

	@Override

	public void eliminarTodosProyInvAnualizaPorIdFuente(Long idFuente) throws SpddExceptions {
		sessionEliminarBP.eliminarTodosProyInvAnualizaPorIdFuente(idFuente);

	}

	@Override
	public void eliminarProyInvFinanciaPorId(Long idFuente) throws SpddExceptions {
		sessionEliminarBP.eliminarProyInvFinanciaPorId(idFuente);

	}

	@Override
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorId(Long idFuente) {

		return sessionConsultaBP.consultarProyInvFinanciaPorId(idFuente);
	}

	@Override
	public BpProyInvFinanciaDTO modificarProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions {

		return sessionModificarBP.modificarTodosProyInvAnualizaDelProyInvFinancia(peticion);
	}

	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpProyInvPoliticaFiltrado(bpProyInvPoliticaDTO);
	}

	@Override
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(Long idPolPub, Long idProyInversion) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(idPolPub, idProyInversion);
	}

	@Override
	public BpProyInvPoliticaDTO guardarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO)	throws SpddExceptions {
		BpProyInvPoliticaDTO bpProyInvPoliticaDTOAux = sessionConsultaBP.consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(bpProyInvPoliticaDTO.getIdPolPub(), bpProyInvPoliticaDTO.getIdProyInversion());
		
		return bpProyInvPoliticaDTOAux.getIdProyPolitica() == null ? sessionRegistroBP.guardarBpProyInvPolitica(bpProyInvPoliticaDTO) : new BpProyInvPoliticaDTO();
	}

	@Override
	public BpProyInvPoliticaDTO modificarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvPolitica(bpProyInvPoliticaDTO);
	}

	@Override
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorId(Long idProyPol) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvPoliticaPorId(idProyPol);
	}

	@Override
	public void eliminarBpProyInvPolitica(Long idProyPolitica) throws SpddExceptions {
		sessionEliminarBP.eliminarBpProyInvPolitica(idProyPolitica);
	}

	@Override
	public BpProyInvLineaDTO consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(Long idLineaInversion, Long idProyecto) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(idLineaInversion, idProyecto);
	}

	@Override
	public BpProyInvLineaDTO consultarBpProyInvLineaPorId(Long idProyLinea) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvLineaPorId(idProyLinea);
	}
	
	@Override
	public BpProyInvLineaDTO guardarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO) throws SpddExceptions {
		BpProyInvLineaDTO bpProyInvLineaDTOAux = sessionConsultaBP.consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(bpProyInvLineaDTO.getIdLineaInversion(), bpProyInvLineaDTO.getIdProyInversion());
		
		return bpProyInvLineaDTOAux.getIdProyLinea() == null ? sessionRegistroBP.guardarBpProyInvLinea(bpProyInvLineaDTO) : new BpProyInvLineaDTO();
	}

	@Override
	public BpProyInvLineaDTO modificarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvLinea(bpProyInvLineaDTO);
	}

	@Override
	public void eliminarBpProyInvLinea(Long idProyLinea) throws SpddExceptions {
		sessionEliminarBP.eliminarBpProyInvLinea(idProyLinea);
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO bpProyInvPmrDTO) throws SpddExceptions {
		return sessionConsultaBP.consultarTodosBpProyInvPmrDTOFiltrado(bpProyInvPmrDTO);
	}

	@Override
	public BpProyInvPmrDTO guardarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPrmDTO) throws SpddExceptions {
		BpProyInvPmrDTO bpProyInvPmrDTOAux = sessionConsultaBP.consultarBpProyInvPmrPorIdIndMrYIdProyInversion(bpProyInvPrmDTO.getIdIndMr(), bpProyInvPrmDTO.getIdProyInversion());
		
		return bpProyInvPmrDTOAux.getIdProyPmr() == null ? sessionRegistroBP.guardarBpProyInvPmr(bpProyInvPrmDTO) : new BpProyInvPmrDTO();
	}

	@Override
	public BpProyInvPmrDTO consultarBpProyInvPmrPorIdIndMrYIdProyInversion(Long idIndMr, Long idProyInversion)	throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvPmrPorIdIndMrYIdProyInversion(idIndMr, idProyInversion);
	}

	@Override
	public BpProyInvPmrDTO consultarBpProyInvPmrPorId(Long idProyPmr) throws SpddExceptions {
		return sessionConsultaBP.consultarBpProyInvPmrPorId(idProyPmr);
	}

	@Override
	public BpProyInvPmrDTO modificarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPrmDTO) throws SpddExceptions {
		return sessionRegistroBP.guardarBpProyInvPmr(bpProyInvPrmDTO);
	}

	@Override
	public void eliminarBpProyInvPmr(Long idProyPmr) throws SpddExceptions {
		sessionEliminarBP.eliminarBpProyInvPmr(idProyPmr);	
>>>>>>> developer
	}

	

	


}
