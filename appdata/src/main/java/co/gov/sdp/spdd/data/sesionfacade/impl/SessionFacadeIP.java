package co.gov.sdp.spdd.data.sesionfacade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCondicionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.ArbolCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.HisVPddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMRIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.serviciofacade.afs.SessionConsultaAFS;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionConsultaIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionEliminarIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionModificarIP;
import co.gov.sdp.spdd.data.serviciofacade.ip.SessionRegistroIP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;

/**
 * Clase del SessionFacade del modulo IP que permite la comunicacion entre
 * appdata y appcore
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Service
public class SessionFacadeIP implements ISessionFacadeIP {

	/**
	 * Inyeccion del servicio Consultar del modulo IP
	 */
	@Autowired
	SessionConsultaIP sessionConsultaIP;

	/**
	 * Inyeccion del servicio Eliminar del modulo IP
	 */
	@Autowired
	SessionEliminarIP sessionEliminarIP;

	/**
	 * Inyeccion del servicio Registrar del modulo IP
	 */
	@Autowired
	SessionRegistroIP sessionRegistroIP;

	/**
	 * Inyeccion del servicio Modificar del modulo IP
	 */
	@Autowired
	SessionModificarIP sessionModificarIP;

	@Autowired
	SessionConsultaAFS sessionConsultarAFS;

	@Override
	public PddCompromisoDTO consultarCompromisoPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoPorId(id);
	}

	// METODOS DE CONSULTAR_COMPROMISO
	@Override
	public GenericoDTO consultarCompromisoPorFiltro(HisVPddCompromisoDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarCompromisoEstrategicoPorFiltro(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoEstrategicoPorFiltro(peticion);
	}

	@Override
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorID(Long idCompromisoEstrategico)
			throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoEstrategicoPorId(idCompromisoEstrategico);
	}

	@Override
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(Long idTematica,
			Long idCompromisoEstrategico) throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(idTematica,
				idCompromisoEstrategico);
	}

	// METODOS DE METAS_COMPROMISO
	@Override
	public GenericoDTO consultarMetasCompromisoEstrategico(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarMetasCompromisoEstrategico(id);
	}

	@Override
	public PddObraConcretaDTO consultarObraConcretaPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarObraConcretaPorId(id);
	}

	@Override
	public GenericoDTO consultarObrasConcretasPorMetas(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarObrasConcretasPorMetas(id);
	}

	@Override
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompetenciaAsociadaPorId(id);
	}

	@Override
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(Long idSector) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSector(idSector);
	}

	@Override
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(Long idSector,
			Long idLsCompetencia) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(idSector, idLsCompetencia);
	}

	@Override
	public GenericoDTO consultarPddCompromisosPorFiltro(PddCompromisoDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisosPorFiltro(peticion);
	}

	@Override
	public PddCompromisoDTO consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(Long idEstrategico, Long idPlan)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(idEstrategico, idPlan);
	}

	@Override
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisoPorIdPlanDesarrollo(idPlan);
	}

	@Override
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorID(Long idPddCompromisoEspecifico)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisoEspecificoPorId(idPddCompromisoEspecifico);
	}

	@Override

	public ArbolCompromisoDTO consultarPddCompromisosEspecificosPorIdCompromiso(Long idCompromiso)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisosEspecificosPorIdCompromiso(idCompromiso);
	}

	@Override
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(Long idCompromiso,
			String decripcion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(idCompromiso, decripcion);
	}

	@Override
	public PddIndicadorDTO consultarPddIndicadorPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPddIndicadorPorId(id);
	}

	@Override
	public GenericoDTO consultarPddsPorEstado(Long idestado) throws SpddExceptions {
		return sessionConsultaIP.consultarPddsPorEstado(idestado);
	}

	// METODOS DE PDD
	@Override
	public PddDTO consultarPddVigente() throws SpddExceptions {
		ArgumentoListaSimpleDTO argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
		GenericoDTO genericoPDDVigenteDTO = sessionConsultaIP.consultarPddsPorEstado(idEstadoVigente);

		return !genericoPDDVigenteDTO.getLstObjectsDTO().isEmpty()
				? (PddDTO) genericoPDDVigenteDTO.getLstObjectsDTO().get(0)
				: new PddDTO();
	}

	@Override
	public GenericoDTO consultarPddPorFiltro(PddDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPorFiltro(peticion);
	}

	@Override
	public PddDTO consultarPddPorID(Long idPdd) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPorId(idPdd);
	}

	@Override
	public PddMetaDTO consultarPddMetaPorId(Long id) throws SpddExceptions {

		return sessionConsultaIP.consultarPddMetaPorId(id);
	}

	@Override
	public PddMetaResultadoDTO consultarPddMetaResultadoPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPddMetaResultadoPorId(id);
	}

	// METODOS DE PDD_NIVEL
	@Override
	public PddNivelDTO consultarPddNivelPorID(Long idPddNivel) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelPorId(idPddNivel);
	}

	@Override
	public GenericoDTO consultarPddNivelPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelesPorIdPlanDesarrollo(idPlan);
	}

	@Override
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYIdPddNivel(Long numero, Long idPddNivel,
			Long idAtributoPadre) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelAtributoPorNumeroYIdPddNivel(numero, idPddNivel, idAtributoPadre);
	}

	@Override
	public PddNivelAtributoDTO consultarPddNivelAtributoPorDenominacionYIdPddNivel(String denominacion, Long idPddNivel)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelAtributoPorDenominacionYIdPddNivel(denominacion, idPddNivel);
	}

	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddNivelAtributoPorIdPddNivelPaginado(pddNivelAtributoDTO);
	}

	@Override
	public PddPrbValoracionDTO consultarPddPrbValoracionPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPrbValoracionPorId(id);
	}

	@Override
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(Long idProblematica, Long momento)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddPrbValoracionPorIdProblematicaYMomento(idProblematica, momento);
	}

	@Override
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPddProblematicaPorId(id);
	}

	@Override
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddProblematicaPorCompromiso(peticion);
	}

	// METODOS DE PDL
	@Override
	public GenericoDTO consultarPdlPorFiltro(PdlDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPdlPorFiltro(peticion);
	}

	@Override
	public GenericoDTO consultarTodosPlanDesarrolloLocal() throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPlanDesarrolloLocal();
	}

	@Override
	public PdlDTO consultarPlanDesarrolloLocalPorId(Long idPlanLocal) throws SpddExceptions {
		return sessionConsultaIP.consultarPlanDesarrolloLocalPorId(idPlanLocal);
	}

	@Override
	public PdlNivelDTO consultarPdlNivelPorId(Long idPdlNivel) throws SpddExceptions {
		return sessionConsultaIP.consultarPdlNivelPorId(idPdlNivel);
	}

	@Override
	public GenericoDTO consultarPdlNivelPorIdPlanLocal(Long idPlanLocal) throws SpddExceptions {
		return sessionConsultaIP.consultarPdlNivelesPorIdPlanLocal(idPlanLocal);
	}

	@Override
	public PdlNivelDTO consultarPdlNivelPorNivelYIdPlanLocal(Long nivel, Long idPlanLocal) throws SpddExceptions {
		return sessionConsultaIP.consultarPdlNivelPorNivelYIdPlanLocal(nivel, idPlanLocal);
	}

	@Override
	public GenericoDTO consultarPdlPorEntidad(String resultado, String codigoEntidad) {
		return sessionConsultaIP.consultarPdlPorLsEstadoPlanYCodigoEntidad(resultado, codigoEntidad);

	}

	@Override
	public PddPrbPoblacionDTO consultarPrbPoblacionPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarPrbPolacionPorId(id);
	}

	@Override
	public PddPrbIndicadorDTO consultarPrbIndicadorPorId(Long idProbInd) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPrbIndicadorPorId(idProbInd);
	}

	@Override
	public GenericoDTO consultarPddIndicadorTodos() throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddIndicador();
	}

	@Override
	public GenericoDTO consultarPrbIndicadorPorProblematica(Long idProblematica) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPrbIndicadorPorProblematica(idProblematica);
	}

	@Override
	public void eliminarPrbIndicador(Long idProbInd) throws SpddExceptions {
		sessionEliminarIP.eliminarPrbIndicador(idProbInd);
	}

	@Override
	public void elimanrPDDMeta(Long id) throws SpddExceptions {
		sessionEliminarIP.eliminarPddMeta(id);

	}

	@Override
	public void eliminarMetaResultado(Long idMetaResultado) throws SpddExceptions {
		sessionEliminarIP.eliminarPddMetaResultado(idMetaResultado);
	}

	@Override
	public GenericoDTO consultarPddMetaResultadoPorIDProblematicaIndicador(Long idProblematicaIndicador)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPddMetaResultadoPorIDProblematicaIndicador(idProblematicaIndicador);
	}

	@Override
	public void eliminarPddObraConcreta(Long id) throws SpddExceptions {
		sessionEliminarIP.eliminarPddObraConcreta(id);

	}

	@Override
	public void eliminarPddCompromisoEspecifico(Long idPddCompromisoEspecifico) throws SpddExceptions {
		sessionEliminarIP.eliminarPddCompromisoEspecifico(idPddCompromisoEspecifico);
	}

	@Override
	public void eliminarPrbPolacion(Long idPoblacion) throws SpddExceptions {
		sessionEliminarIP.eliminarPrbPoblacion(idPoblacion);
	}

	@Override
	public CompromisoEstrategicoDTO guardarCompromisoEstrategico(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		// Se valida que no exista otr registro en BD con la combinacion entre los
		// identificadores de tematica y compromiso que se piensan ingresar.
		CompromisoEstrategicoDTO respuesta = sessionConsultaIP
				.consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(peticion.getIdTematica(),
						peticion.getIdCompromisoEstrategico());

		return respuesta.getIdCompromiso() == null ? sessionRegistroIP.guardarCompromisoEstrategico(peticion)
				: new CompromisoEstrategicoDTO();
	}

	@Override
	public PddDTO guardarPdd(PddDTO peticion) throws SpddExceptions {
		ArgumentoListaSimpleDTO argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
		argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_FORMULACION, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoFormulacion = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;

		GenericoDTO respuestaFormulacion = sessionConsultaIP.consultarPddsPorEstado(idEstadoFormulacion);
		GenericoDTO respuestaVigente = sessionConsultaIP.consultarPddsPorEstado(idEstadoVigente);

		if ((peticion.getIdLsEstadoPlan().equals(idEstadoFormulacion)
				&& respuestaFormulacion.getLstObjectsDTO().isEmpty())
				|| (peticion.getIdLsEstadoPlan().equals(idEstadoVigente)
						&& respuestaVigente.getLstObjectsDTO().isEmpty())) {
			return sessionRegistroIP.guardarPdd(peticion);
		}
		return new PddDTO();
	}

	@Override
	public PddCompetenciaAsociadaDTO guardarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion)
			throws SpddExceptions {
		// validacion de la unicidad
		PddCompetenciaAsociadaDTO pddCompetenciaDTO = sessionConsultaIP
				.consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(peticion.getIdLsSector(),
						peticion.getIdLsCompetencia());

		return pddCompetenciaDTO.getIdCompetencia() == null ? sessionRegistroIP.guardarPddCompetenciaAsociada(peticion)
				: new PddCompetenciaAsociadaDTO();
	}

	@Override
	public PddCompromisoDTO guardarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions {
		PddCompromisoDTO pddCompromisoDTO = sessionConsultaIP.consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(
				peticion.getIdEstrategico(), peticion.getIdPlanDesarrollo());

		return pddCompromisoDTO.getIdCompromiso() == null ? sessionRegistroIP.guardadPddCompromiso(peticion)
				: new PddCompromisoDTO();
	}

	@Override
	public PddMetaDTO guardarPddMeta(PddMetaDTO pddMetaDTO) throws SpddExceptions {
		PddMetaDTO respuesta = sessionConsultaIP.validarMeta(pddMetaDTO.getMeta(), pddMetaDTO.getIdEspecifico(),
				pddMetaDTO.getIdTipoMetaLs());

		return respuesta.getIdMeta() == null ? sessionRegistroIP.guardarPddMeta(pddMetaDTO) : new PddMetaDTO();
	}

	@Override
	public PddMetaResultadoDTO guardarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddMetaResultado(peticion);
	}

	@Override
	public PddObraConcretaDTO guardarPddObraConcreta(PddObraConcretaDTO pddObraConcretaDTO) throws SpddExceptions {
		PddObraConcretaDTO respuesta = sessionConsultaIP.validarObra(pddObraConcretaDTO.getIdMeta(),
				pddObraConcretaDTO.getObraConcreta());

		return respuesta.getIdConcreta() == null ? sessionRegistroIP.guardarObraConcreta(pddObraConcretaDTO)
				: new PddObraConcretaDTO();
	}

	@Override
	public PddNivelDTO guardarPddNivel(PddNivelDTO peticion) throws SpddExceptions {
		PddNivelDTO pddNivelDTO = sessionConsultaIP.consultarPddNivelPorNivelYIdPlanDesarrollo(peticion.getCodNivel(),
				peticion.getIdPlanDesarrollo());

		return pddNivelDTO.getIdPddNivel() == null ? sessionRegistroIP.guardarPddNivel(peticion) : new PddNivelDTO();
	}

	@Override
	public PddNivelAtributoDTO guardarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO) throws SpddExceptions {
		PddNivelAtributoDTO pddNivelAtributoDTOAux = sessionConsultaIP.consultarPddNivelAtributoPorNumeroYIdPddNivel(
				pddNivelAtributoDTO.getNumero(), pddNivelAtributoDTO.getIdPddNivel(),
				pddNivelAtributoDTO.getIdAtributoPadre());

		return pddNivelAtributoDTOAux.getIdAtributo() == null
				? sessionRegistroIP.guardarPddNivelAtributo(pddNivelAtributoDTO)
				: new PddNivelAtributoDTO();
	}

	@Override
	public PddProblematicaDTO guardarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions {
		PddProblematicaDTO respuesta = sessionConsultaIP.validarProblematicaPorLlaveDeNegocio(peticion);
		return respuesta == null ? sessionRegistroIP.guardarPddProblematica(peticion) : new PddProblematicaDTO();
	}

	@Override
	public PddPrbPoblacionDTO guardarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		PddPrbPoblacionDTO res = sessionConsultaIP.validarPrbPoblacion(peticion.getDescripcion(),
				peticion.getIdProblematica());
		return res.getIdPoblacion() == null ? sessionRegistroIP.guardarPrbPoblacion(peticion)
				: new PddPrbPoblacionDTO();
	}

	@Override
	public PdlDTO guardarPdl(PdlDTO peticion) throws SpddExceptions {
		ArgumentoListaSimpleDTO argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
		GenericoDTO respuestaVigente = sessionConsultaIP.consultarPdlPorLsEstadoPlanYCodigoEntidad(argumento.getResultado(),
				peticion.getCodigoEntidad());
		if (peticion.getIdLsEstadoPlan().equals(idEstadoVigente) && respuestaVigente.getLstObjectsDTO().isEmpty()) {
			return sessionRegistroIP.guardarPdl(peticion);
		}
		return new PdlDTO();
	}

	@Override
	public PdlNivelDTO guardarPdlNivel(PdlNivelDTO peticion) throws SpddExceptions {
		PdlNivelDTO pdlNivelDTO = sessionConsultaIP.consultarPdlNivelPorNivelYIdPlanLocal(peticion.getCodNivel(),
				peticion.getIdPlanLocal());

		return pdlNivelDTO.getIdPdlNivel() == null ? sessionRegistroIP.guardarPdlNivel(peticion) : new PdlNivelDTO();
	}

	@Override
	public PddNivelDTO modificarPddNivel(PddNivelDTO peticion) throws SpddExceptions {
		PddNivelDTO pddNivelDTO = sessionConsultaIP.consultarPddNivelPorNivelYIdPlanDesarrollo(peticion.getCodNivel(),
				peticion.getIdPlanDesarrollo());

		return pddNivelDTO.getIdPddNivel() == null
				|| (pddNivelDTO.getIdPddNivel() != null && pddNivelDTO.getIdPddNivel().equals(peticion.getIdPddNivel()))
						? sessionRegistroIP.guardarPddNivel(peticion)
						: new PddNivelDTO();
	}

	@Override
	public PddCompromisoEspecificoDTO guardarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO = sessionConsultaIP
				.consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(peticion.getIdCompromiso(),
						peticion.getDescripcion());

		return pddCompromisoEspecificoDTO.getIdEspecifico() == null
				? sessionRegistroIP.guardarPddCompromisoEspecifico(peticion)
				: new PddCompromisoEspecificoDTO();
	}

	@Override
	public PddPrbValoracionDTO guardarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions {
		PddPrbValoracionDTO pddPrbValoracionDTO = sessionConsultaIP.consultarPddPrbValoracionPorIdProblematicaYMomento(
				peticion.getIdProblematica(), peticion.getMomento());

		return pddPrbValoracionDTO.getIdValoracion() == null ? sessionRegistroIP.guardarPddPrbValoracion(peticion)
				: new PddPrbValoracionDTO();
	}

	@Override
	public PddPrbIndicadorDTO guardarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions {
		PddPrbIndicadorDTO pddIndicador = sessionConsultaIP.validarPrbIndicador(peticion.getIdIndicador(),
				peticion.getIdProblematica());
		return pddIndicador.getIdProbInd() == null ? sessionRegistroIP.guardarPddPrbIndicador(peticion)
				: new PddPrbIndicadorDTO();
	}

	@Override
	public CompromisoEstrategicoDTO modificarCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategicoDTO)
			throws SpddExceptions {
		return sessionRegistroIP.guardarCompromisoEstrategico(compromisoEstrategicoDTO);
	}

	@Override
	public PddMetaDTO modificarMetaDeCompromiso(PddMetaDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddMeta(peticion);
	}

	@Override
	public PddObraConcretaDTO modificarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarObraConcreta(peticion);
	}

	@Override
	public PddDTO modificarPdd(PddDTO pddDTO) throws SpddExceptions {
		return sessionRegistroIP.guardarPdd(pddDTO);
	}

	@Override
	public PddCompetenciaAsociadaDTO modificarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO peticion)
			throws SpddExceptions {
		return sessionRegistroIP.guardarPddCompetenciaAsociada(peticion);
	}

	@Override
	public PddCompromisoDTO modificarPddCompromiso(PddCompromisoDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardadPddCompromiso(peticion);
	}

	@Override
	public PddCompromisoEspecificoDTO modificarPddCompromisoEspecifico(PddCompromisoEspecificoDTO peticion)
			throws SpddExceptions {
		return sessionRegistroIP.guardarPddCompromisoEspecifico(peticion);
	}

	@Override
	public PddMetaResultadoDTO modificarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddMetaResultado(peticion);
	}

	@Override
	public PddPrbValoracionDTO modificarPddPrbValoracion(PddPrbValoracionDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddPrbValoracion(peticion);
	}

	@Override
	public PddPrbPoblacionDTO modificarPrbPoblacion(PddPrbPoblacionDTO peticion) {

		return sessionRegistroIP.guardarPrbPoblacion(peticion);
	}

	@Override
	public PddPrbIndicadorDTO modificarPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddPrbIndicador(peticion);
	}

	@Override
	public PddMetaDTO validarMeta(PddMetaDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.validarMeta(peticion.getMeta(), peticion.getIdEspecifico(),
				peticion.getIdTipoMetaLs());
	}

	@Override
	public PddObraConcretaDTO validarPddObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.validarObra(peticion.getIdMeta(), peticion.getObraConcreta());
	}

	@Override
	public PddPrbPoblacionDTO validarPrbPoblacion(PddPrbPoblacionDTO peticion) throws SpddExceptions {

		return sessionConsultaIP.validarPrbPoblacion(peticion.getDescripcion(), peticion.getIdProblematica());
	}

	@Override
	public PddPrbIndicadorDTO validarPddPrbIndicador(Long idIndicador, Long idProblematica) throws SpddExceptions {
		return sessionConsultaIP.validarPrbIndicador(idIndicador, idProblematica);
	}

	@Override
	public PddIndicadorDTO guardarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddIndicador(peticion);
	}

	@Override
	public PddIndicadorDTO modificarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddIndicador(peticion);
	}

	@Override
	public GenericoDTO consultarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddMetaResultadoPorProyecto(peticion);
	}

	public boolean validarPddVigente(Long idPlanDesarrollo) throws SpddExceptions {
		ArgumentoListaSimpleDTO argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		Long idEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;

		GenericoDTO genericoPDDVigenteDTO = sessionConsultaIP.consultarPddsPorEstado(idEstadoVigente);

		return !genericoPDDVigenteDTO.getLstObjectsDTO().isEmpty()
				&& (((PddDTO) genericoPDDVigenteDTO.getLstObjectsDTO()).getIdPlanDesarrollo().equals(idPlanDesarrollo));
	}

	@Override
	public PdlDTO modificarPdl(PdlDTO pdlDTO) throws SpddExceptions {
		PdlDTO aux = sessionConsultaIP.consultarPlanDesarrolloLocalPorId(pdlDTO.getIdPlanLocal());

		ArgumentoListaSimpleDTO argFinalizado = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_FINALIZADO, NHSPDDConstantes.LS_ESTADOS_PDD);
		ArgumentoListaSimpleDTO argVigente = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDD_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDD);
		if (pdlDTO.getIdLsEstadoPlan().equals(argFinalizado.getIdArgumento())
				&& aux.getEstadoPlan().equals(NHSPDDConstantes.PDD_ESTADO_VIGENTE)) {
			Long idEstado = argFinalizado.getIdArgumento() != null ? argFinalizado.getIdArgumento() : 0;
			pdlDTO.setIdLsEstadoPlan(idEstado);
			return sessionRegistroIP.guardarPdl(pdlDTO);
		} else if (pdlDTO.getIdLsEstadoPlan().equals(argVigente.getIdArgumento())
				&& aux.getEstadoPlan().equals(NHSPDDConstantes.PDD_ESTADO_VIGENTE)) {
			Long idEstado = argVigente.getIdArgumento() != null ? argVigente.getIdArgumento() : 0;
			pdlDTO.setIdLsEstadoPlan(idEstado);
			return sessionRegistroIP.guardarPdl(pdlDTO);
		}
		return new PdlDTO();
	}

	@Override
	public PdlNivelDTO modificarPdlNivel(PdlNivelDTO peticion) throws SpddExceptions {
		PdlNivelDTO pdlNivelDTO = sessionConsultaIP.consultarPdlNivelPorNivelYIdPlanLocal(peticion.getCodNivel(),
				peticion.getIdPlanLocal());

		return pdlNivelDTO.getIdPdlNivel() == null
				|| (pdlNivelDTO.getIdPdlNivel() != null && pdlNivelDTO.getIdPdlNivel().equals(peticion.getIdPdlNivel()))
						? sessionRegistroIP.guardarPdlNivel(peticion)
						: new PdlNivelDTO();
	}

	@Override
	public PddMRIndicadorDTO guardarMetaIndicador(PddMRIndicadorDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarIndicadorMetaResultado(peticion);
	}

	@Override
	public PddMRIndicadorDTO validarIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.validarIndicadorMetaResultado(peticion);
	}

	@Override
	public PddIndicadorDTO obtenerPddIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.obtenerPddIndicadorMetaResultado(peticion);
	}

	@Override
	public GenericoDTO consultarIndicadoresMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarMetaResultadoIndicador(peticion);
	}

	@Override
	public void eliminarIndicadorMetaResultado(Long id) throws SpddExceptions {
		sessionEliminarIP.eliminarIndicadorMetaResultado(id);
	}

	@Override
	public PddMRIndicadorDTO obtenerPddMRIndicadorPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.obtenerPddMRIndicadorPorId(id);
	}

	@Override
	public GenericoDTO consultarPotPorFiltro(PotDTO peticion) throws SpddExceptions {

		return sessionConsultaIP.consultarPotPorFiltro(peticion);
	}

	@Override
	public PotDTO guardarPot(PotDTO peticion) throws SpddExceptions {

		return sessionRegistroIP.guardarPot(peticion);
	}

	@Override
	public PotDTO obtenerPotCodigo(String codigoPot) throws SpddExceptions {

		return sessionConsultaIP.obtenerPorCodigo(codigoPot);
	}

	@Override
	public PotDTO obtenerPotPorId(Long idCodigo) throws SpddExceptions {

		return sessionConsultaIP.obtenerPorId(idCodigo);
	}

	@Override
	public GenericoDTO consultarPotRamaPorIdPot(Long idPot) throws SpddExceptions {

		return sessionConsultaIP.consultarRamaPotPorIdPot(idPot);
	}

	@Override
	public GenericoDTO consultarMetaProductoPorMR(PddMetaProductoDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosMetaProductoPorMR(peticion);
	}

	@Override
	public PddMetaProductoDTO guardarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarMetaProducto(peticion);
	}

	@Override
	public void eliminarMetaProducto(Long idMetaProducto) throws SpddExceptions {
		sessionEliminarIP.eliminarMetaProducto(idMetaProducto);
	}

	@Override
	public PddMetaProductoDTO obtenerMetaProductoPorId(Long idMetaProducto) throws SpddExceptions {
		return sessionConsultaIP.obtenerMetaProductoPorId(idMetaProducto);
	}

	@Override
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadrePaginado(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddNivelAtributoPorIdAtributoPadre(pddNivelAtributoDTO);
	}

	@Override
	public PotRamaDTO guardarRamaPot(PotRamaDTO peticion) throws SpddExceptions {

		return sessionRegistroIP.guardarRamaPot(peticion);
	}

	@Override
	public GenericoDTO obtenerPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions {

		return sessionConsultaIP.obtenerPotRamaPorIdPotNumeroRamaDesc(idPot);
	}

	@Override
	public void eliminarRamaPot(Long idRamaPot) throws SpddExceptions {
		sessionEliminarIP.eliminarRamaPot(idRamaPot);

	}

	@Override
	public PotRamaDTO obtenerRamaPotPorid(Long idRamaPot) throws SpddExceptions {
		return sessionConsultaIP.obtenerRamaPotPorId(idRamaPot);
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosNivelPotPorIdRamaPot(idRamaPot);
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosNivelPotPorIdRamaPotDesc(idRamaPot);
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosNivelPotPorIdNivel(idNivelPot);
	}

	@Override
	public GenericoDTO obtenerTodosNivelPotPorIdNivelDesc(Long idNivelPot) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosNivelPotPorIdNivelDesc(idNivelPot);
	}

	@Override
	public PotNivelDTO guardarNivelPot(PotNivelDTO peticion) throws SpddExceptions {

		return sessionRegistroIP.guardarNivelDTO(peticion);
	}

	@Override
	public void eliminarNivelPot(Long idNivelPot) throws SpddExceptions {
		sessionEliminarIP.eliminarNivelPot(idNivelPot);

	}

	@Override
	public PotNivelDTO obtenerNivelPotPorId(Long idNivelPot) throws SpddExceptions {

		return sessionConsultaIP.obtenerPotNivelPorId(idNivelPot);
	}

	@Override
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPotObraPorIdNivelPotPaginado(peticion);

	}

	@Override
	public PddCompromisoDTO consultarPddCompromisoPorId(Long id) throws SpddExceptions {
		return sessionConsultaIP.consultarCompromisoPorId(id);
	}

	@Override
	public PddNivelAtributoDTO modificarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions {
		return sessionRegistroIP.guardarPddNivelAtributo(pddNivelAtributoDTO);
	}

	@Override
	public PddNivelAtributoDTO consultarPddNivelAtributoPorId(Long idAtributo) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelAtributoPorId(idAtributo);
	}

	@Override
	public void eliminarPddNivelAtributo(Long idAtributo) throws SpddExceptions {
		sessionEliminarIP.eliminarPddNivelAtributo(idAtributo);
	}

	@Override
	public PotObraDTO guardarPotObra(PotObraDTO potObraDTO) throws SpddExceptions {
		PotObraDTO potObraDTOAux = sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(potObraDTO.getCodigoPotObra(),
				potObraDTO.getIdNivelPot());
		return potObraDTOAux.getIdObraProyPot() == null ? sessionRegistroIP.guardarPotObra(potObraDTO)
				: new PotObraDTO();
	}

	@Override
	public PotObraDTO consultarPotObraPorId(Long idPotObra) throws SpddExceptions {
		return sessionConsultaIP.consultarPotObraPorId(idPotObra);
	}

	@Override
	public PotObraDTO modificarPotObra(PotObraDTO potObraDTO) throws SpddExceptions {
		return sessionRegistroIP.guardarPotObra(potObraDTO);
	}

	@Override
	public void eliminarPotObra(Long idPotObra) throws SpddExceptions {
		sessionEliminarIP.eliminarPotObra(idPotObra);
	}

	@Override
	public PotObraDTO consultarPotObraPorCodigoYIdNivelPot(Long codigo, Long idNivelPot) throws SpddExceptions {
		return sessionConsultaIP.consultarPotObraPorCodigoYIdNivelPot(codigo, idNivelPot);
	}

	@Override
	public GenericoDTO consultarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPotObraEntidadPorIdPotObra(idPotObra);
	}

	@Override
	public void eliminarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions {
		sessionEliminarIP.eliminarTodosPotObraEntidadPorIdPotObra(idPotObra);
	}

	@Override
	public GenericoDTO consultarIndicadorMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarIndicadoresMetaProducto(peticion);
	}

	@Override
	public PddMpIndicadorDTO validarMetaProductoIndicador(PddMpIndicadorDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.validarMetaProductoIndicador(peticion);
	}

	@Override
	public PddMpIndicadorDTO guardarIndicadorMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarIndicadorMetaProducto(peticion);
	}

	@Override
	public void eliminarIndicadorMetaProducto(Long idIndMetaProducto) throws SpddExceptions {
		sessionEliminarIP.eliminarIndicadorMetaProducto(idIndMetaProducto);
	}

	@Override
	public PddMpIndicadorDTO obtenerIndicadorMp(Long idIndicadorMp) throws SpddExceptions {
		return sessionConsultaIP.obtenerIndicadorMetaProductoPorId(idIndicadorMp);
	}

	@Override
	public PotObraEntidadDTO guardarPotObraEntidad(PotObraEntidadDTO potObraEntidadDTO) throws SpddExceptions {
		PotObraEntidadDTO potObraEntidadDTOAux = sessionConsultaIP.obtenerPotObraEntidadPorCodigoEntidadYIdPotObra(
				potObraEntidadDTO.getCodigoEntidad(), potObraEntidadDTO.getIdObraProyPot());

		return potObraEntidadDTOAux.getIdObraEntidad() == null
				? sessionRegistroIP.guardarPotObraEntidad(potObraEntidadDTO)
				: new PotObraEntidadDTO();
	}

	@Override
	public GenericoDTO consultarTodosPotInstrumentoFiltrado(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPotInstrumentoFiltrado(potInstrumentoDTO);
	}

	@Override
	public PotInstrumentoDTO consultarPotInstrumentoPorId(Long idPotInstrumento) throws SpddExceptions {
		return sessionConsultaIP.consultarPotInstrumentoPorId(idPotInstrumento);
	}

	@Override
	public PotInstrumentoDTO consultarPotInstrumentoPorCodigoYIdPot(Long codigo, Long idPot) throws SpddExceptions {
		return sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(codigo, idPot);
	}

	@Override
	public PotInstrumentoDTO guardarPotInstrumento(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions {
		PotInstrumentoDTO potInstrumentoDTOAux = sessionConsultaIP.consultarPotInstrumentoPorCodigoYIdPot(
				potInstrumentoDTO.getCodigoPotInstrumento(), potInstrumentoDTO.getIdPot());
		return potInstrumentoDTOAux.getIdInstrumentoPot() == null
				? sessionRegistroIP.guardarPotInstrumento(potInstrumentoDTO)
				: new PotInstrumentoDTO();
	}

	@Override
	public PotInstrumentoDTO modificarPotInstrumento(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions {
		return sessionRegistroIP.guardarPotInstrumento(potInstrumentoDTO);
	}

	@Override
	public void eliminarPotInstrumento(Long idPotInstrumento) throws SpddExceptions {
		sessionEliminarIP.eliminarPotInstrumento(idPotInstrumento);
	}

	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(pdlNivelAtributoDTO);
	}

	@Override
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPdlNivelAtributoPorIdAtributoPadre(pdlNivelAtributoDTO);
	}

	@Override
	public PdlNivelAtributoDTO guardarPdlNivelAtributo(PdlNivelAtributoDTO pdlNivelAtributoDTO) throws SpddExceptions {
		return sessionRegistroIP.guardarPdlNivelAtributo(pdlNivelAtributoDTO);
	}

	@Override
	public PdlNivelAtributoDTO consultarPdlNivelAtributoPorDenominacionYIdPdlNivel(String denominacion, Long idPdlNivel)
			throws SpddExceptions {
		return sessionConsultaIP.consultarPdlNivelAtributoPorDenominacionYIdPdlNivel(denominacion, idPdlNivel);
	}

	@Override
	public GenericoDTO consultarTodosMpEntidades(PddMpEntidadDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.obtenerTodasMpEntidades(peticion);
	}

	@Override
	public PddMpEntidadDTO obtenerMpEntidadPorId(Long idMpEntidad) throws SpddExceptions {
		return sessionConsultaIP.obtenerMpEntidadPorId(idMpEntidad);
	}

	@Override
	public PddMpEntidadDTO validarMpEntidadPorMetaProductoYEntidad(String codigoEntidad, Long idMetaProducto)
			throws SpddExceptions {
		return sessionConsultaIP.validarMpEntidadPorMetaProductoYEntidad(codigoEntidad, idMetaProducto);
	}

	@Override
	public PddMpEntidadDTO guardarMpEntidad(PddMpEntidadDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarMpEntidad(peticion);
	}

	@Override
	public void eliminarMpEntidad(Long idMpEntidad) throws SpddExceptions {
		sessionEliminarIP.eliminarMpEntidad(idMpEntidad);

	}

	@Override
	public GenericoDTO obtenerTodosRangoPonderacion() throws SpddExceptions {
		return sessionConsultaIP.obtenerTodosRangoPonderacion();
	}

	@Override
	public PddRangoPonderacionDTO obtenerPddRangoPonderacionPorId(Long idRango) throws SpddExceptions {
		return sessionConsultaIP.obtenerPddRangoPonderacionPorId(idRango);

	}

	@Override
	public void eliminarPddRangoPonderacion(Long idRango) throws SpddExceptions {
		sessionEliminarIP.eliminarPddRangoPonderacion(idRango);
	}

	@Override
	public PddRangoPonderacionDTO guardarPddRangoPonderacion(PddRangoPonderacionDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddRangoPonderacion(peticion);
	}

	@Override
	public PddRangoPonderacionDTO modificarPddRangoPonderacion(PddRangoPonderacionDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.guardarPddRangoPonderacion(peticion);
	}

	@Override
	public GenericoDTO obtenerPddRangoPonderacionPorIdPdd(Long idPdd) throws SpddExceptions {
		return sessionConsultaIP.obtenerPddRangoPonderacionPorIdPdd(idPdd);
	}

	@Override
	public GenericoDTO consultarTodosPotObra(PotObraDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPotObraPorPot(peticion);
	}

	@Override
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo) throws SpddExceptions {
		return sessionConsultaIP.consultarNivelesComponentesDesbalanceados(idPlanDesarrollo);
	}

	@Override
	public GenericoDTO consultarIndicadorEntidadMetaProducto(PddMpIndicadorEntidadDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarIndicadoresEntidadesMetaProducto(peticion);
	}

	@Override
	public PddMpIndicadorEntidadDTO registrarMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion)
			throws SpddExceptions {
		return sessionRegistroIP.registrarIndicadorEntidadMetaProducto(peticion);
	}

	@Override
	public void eliminarMpIndicadorEntidad(Long idMpEntidad) throws SpddExceptions {
		sessionEliminarIP.eliminarMpIndicadorEntidadProducto(idMpEntidad);
	}

	@Override
	public PddMpIndicadorEntidadDTO validarMpIndicadorEntidad(String codigoEntidad, Long idIndProd)
			throws SpddExceptions {
		return sessionConsultaIP.validarIndicadorEntidadMetaProducto(codigoEntidad, idIndProd);
	}

	@Override
	public PddMpIndicadorEntidadDTO consultarIndicadorEntidadPorId(Long idIndMetaProd) throws SpddExceptions {
		return sessionConsultaIP.obtenerPddMpIndicadorEntidadPorId(idIndMetaProd);
	}

	@Override
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(Long numero, Long idPlanDesarrollo) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(numero, idPlanDesarrollo);
	}

	@Override
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(Long numero,
			Long codigoNumero, Long idAtributoPadre) throws SpddExceptions {
		return sessionConsultaIP.consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(numero, codigoNumero, idAtributoPadre);
	}
	
	@Override
	public GenericoDTO consultarTodosMetaProductosEntidades() {
		return sessionConsultaIP.consultarIndicadorMetaPorductoTodos();
	}

	@Override
	public GenericoDTO consultarProblematicasPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		return sessionConsultaIP.consultarProblematicaPorIdCompromiso(idCompromiso);
	}

	@Override
	public GenericoDTO consultarTodosPOTPorEstado(String estado) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPOTPorEstado(estado);
	}

	@Override
	public GenericoDTO consultarTodosPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddPrbPoblacionPorIdProblematica(peticion);
	}

	@Override
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions {
		return sessionConsultaIP.consultarTodasPddCompetenciaAsociada();
	}
	
	@Override
	public PddProblematicaDTO modificarPddProblematica(PddProblematicaDTO peticion) throws SpddExceptions {
		return sessionRegistroIP.modificarPddProblematica(peticion);
	}

	@Override
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPrbIndicadorFiltrado(pddPrbIndicadorDTO);
	}

	@Override
	public GenericoDTO obtenerTodosPddProblematica() throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPrbProblematica();
	}

	@Override
	public GenericoDTO consultarPddCompromisoEspecificoFiltrado(PddCompromisoEspecificoDTO peticion) throws SpddExceptions {
		return sessionConsultaIP.consultarPddCompromisoEspecificoFiltrado(peticion);
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO pddPoliticaPublicadDTO) throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddPoliticaPublicaFiltrado(pddPoliticaPublicadDTO);
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(Long idProyInversion)
			throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(idProyInversion);
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaOrdenadosPorNombrePolitica() throws SpddExceptions {
		return sessionConsultaIP.consultarTodosPddPoliticaPublicaOrdenadosPorNombrePolitica();
	}

	@Override
	public PdlDTO consultarPdlVigente(String codigoEntidad) throws SpddExceptions {
		ArgumentoListaSimpleDTO argumento = sessionConsultarAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(
				NHSPDDConstantes.PDL_ESTADO_VIGENTE, NHSPDDConstantes.LS_ESTADOS_PDL);
		Long idPdlEstadoVigente = argumento.getIdArgumento() != null ? argumento.getIdArgumento() : 0;
		GenericoDTO genericoPDDVigenteDTO = sessionConsultaIP.consultarPdlPorLsEstadoPlanYCodigoEntidad(argumento.getResultado(), codigoEntidad);

		return !genericoPDDVigenteDTO.getLstObjectsDTO().isEmpty()
				? (PdlDTO) genericoPDDVigenteDTO.getLstObjectsDTO().get(0)
				: new PdlDTO();
	}
	
	
}
