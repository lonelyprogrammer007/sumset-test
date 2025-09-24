package co.gov.sdp.spdd.data.serviciofacade.ip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
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
import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.data.model.ip.CompromisoEstrategico;
import co.gov.sdp.spdd.data.model.ip.HisVPddCompromiso;
import co.gov.sdp.spdd.data.model.ip.Pdd;
import co.gov.sdp.spdd.data.model.ip.PddCompetenciaAsociada;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddCompromisoEspecifico;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMRIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMeta;
import co.gov.sdp.spdd.data.model.ip.PddMetaProducto;
import co.gov.sdp.spdd.data.model.ip.PddMetaResultado;
import co.gov.sdp.spdd.data.model.ip.PddMpAnualizar;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicadorEntidad;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.model.ip.PddPoliticaPublica;
import co.gov.sdp.spdd.data.model.ip.PddPrbIndicador;
import co.gov.sdp.spdd.data.model.ip.PddPrbPoblacion;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.Pdl;
import co.gov.sdp.spdd.data.model.ip.PdlNivel;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.Pot;
import co.gov.sdp.spdd.data.model.ip.PotInstrumento;
import co.gov.sdp.spdd.data.model.ip.PotObra;
import co.gov.sdp.spdd.data.model.ip.PotObraEntidad;

/**
 * Clase que representa el Facade para el servicio Consultar del modulo IP
 * 
 * @author DANIEL
 * @version 1.0 24/01/2020
 */
@Component
public class SessionConsultaIP extends SessionIP implements Serializable {

	/**
	 * Constructor
	 */
	public SessionConsultaIP() {
		super();
	}

	/**
	 * Metodo que permite consultar un compromiso y retornarlo segun el filtro
	 * aplicado para este
	 * 
	 * @param peticion objeto de tipo HisVPddCompromisoDTO que contiene la
	 *                 informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarCompromisoPorFiltro(HisVPddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = hisVPddCompromisoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<HisVPddCompromiso> listaPage = new PageImpl<>((List<HisVPddCompromiso>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(hisVPddCompromisoMapper.hisVPddCompromisoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	public GenericoDTO consultarPddMetaResultadoPorProyecto(PddMetaResultadoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = pddMetaResultadoServiceRepo.consultarPddPorProyectoEstrategico(peticion,
				pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddMetaResultado> listaPage = new PageImpl<>((List<PddMetaResultado>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());

		List<PddMetaResultado> listaPddMetaResultado = pddMetaResultadoServiceRepo.obtenerTodosOrdenadosASC();
		if (listaPddMetaResultado != null && !listaPddMetaResultado.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPddMetaResultado.get(listaPddMetaResultado.size() - 1)
					.getIdMetaResultado() + 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}

		respuesta.setLstObjectsDTO(
				new ArrayList<>(pddMetaResultadoMapper.pddMetaResultadoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		
		Long sumatoriaPonderacion = pddMetaResultadoServiceRepo.sumarPonderacionesMetaResultado(peticion.getIdProyEstrategico());
		respuesta.setTotalPonderacion(sumatoriaPonderacion != null ? sumatoriaPonderacion : 0L);
		return respuesta;
	}

	/**
	 * Metodo que permite consultar un compromiso estrategico y retornarlo segun el
	 * filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo CompromisoEstrategicoDTO que contiene la
	 *                 informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarCompromisoEstrategicoPorFiltro(CompromisoEstrategicoDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = compromisoEstrategicoServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<CompromisoEstrategico> listaPage = new PageImpl<>(
				(List<CompromisoEstrategico>) filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				compromisoEstrategicoMapper.compromisoEstrategicoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite buscar un compromiso estrategico por el identificador
	 * 
	 * @param id Long que representa el identificador del compromiso estrategico
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion del
	 *         compromiso estrategico, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorId(Long id) {
		return compromisoEstrategicoMapper
				.compromisoEstrategicoEntityToDTO(compromisoEstrategicoServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite buscar un compromiso estrategico por los identificadores
	 * de tematica y compromisoEstrategico
	 * 
	 * @param idTematica              Long que representa el identificador de la
	 *                                tematica
	 * @param idCompromisoEstrategico Long que representa el identificador del
	 *                                compromiso estratetico
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion del
	 *         objeto encontrado, de lo contrario null.
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO consultarCompromisoEstrategicoPorIdTematicaYIdCompromisoEstrategico(Long idTematica,
			Long idCompromisoEstrategico) throws SpddExceptions {
		CompromisoEstrategico compromisoEstrategico = compromisoEstrategicoServiceRepo
				.buscarPorIdTematicaYIdCompromisoEstrategico(idTematica, idCompromisoEstrategico);
		if (compromisoEstrategico != null) {
			return compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(compromisoEstrategico);
		}
		return new CompromisoEstrategicoDTO();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetasCompromisoEstrategico(Long id) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddMetaDTO> listaDTO = pddMetaMapper
				.pddMetaEntitiesToDTO(pddMetaServiceRepo.consultarMetasCompromistoEstrategico(id));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param idMeta
	 * @return
	 */
	public GenericoDTO consultarObrasConcretasPorMetas(Long idMeta) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddObraConcretaDTO> lista = pddObraConcretamapper
				.pddObraConcretaEntitiesToDTO(pddObraConcretaServiceRepo.buscarPorMeta(idMeta));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * Metodo que permite consultar un plan de desarrollo distrital y retornarlo
	 * segun el filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo PddDTO que contiene la informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada del
	 *         compromiso.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarPddPorFiltro(PddDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = pddServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<Pdd> listaPage = new PageImpl<>((List<Pdd>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		
		List<Pdd> listaPdd = pddServiceRepo.obtenerTodosOrdenadosASC();
		if (listaPdd != null && !listaPdd.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPdd.get(listaPdd.size() - 1).getIdPlanDesarrollo() + 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}		
		
		respuesta.setLstObjectsDTO(new ArrayList<>(pddMapper.pddEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite buscar un plan de desarrollo distrital (pdd) por el
	 * identificador
	 * 
	 * @param id Long que representa el identificador del pdd
	 * @return un objeto de tipo PddDTO con la informacion del plan de desarrollo
	 *         distrital, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PddDTO consultarPddPorId(Long id) {
		return pddMapper.pddEntityToDTO(pddServiceRepo.obtenerPorId(id));
	}

	/**
	 * Metodo que permite buscar los pdds por el identificador del estado
	 * 
	 * @param idEstado Long que representa el identificador del estado
	 * @return un objeto de tipo GenericoDTO con la informacion encontrada.
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddsPorEstado(Long idEstado) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddDTO> listaPddDTO = pddMapper.pddEntitiesToDTO(pddServiceRepo.obtenerPddsPorEstado(idEstado));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPddDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite buscar un nivel de plan de desarrollo distrital (pddNivel)
	 * por el identificador
	 * 
	 * @param id Long que representa el identificador del pddNivel
	 * @return un objeto de tipo PddNivelDTO con la informacion del nivel de plan de
	 *         desarrollo distrital, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PddNivelDTO consultarPddNivelPorId(Long idPddNivel) {
		return pddNivelMapper.pddNivelEntityToDTO(pddNivelServiceRepo.obtener(idPddNivel));
	}

	/**
	 * Metodo que permite buscar los niveles de pdd por el identificador del pdd
	 * 
	 * @param idPlan Long que representa el identificador del pdd del cual se quiere
	 *               consultar los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion encontrada.
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddNivelesPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddNivelDTO> listaPddNivelDTO = pddNivelMapper
				.pddNivelEntitiesToDTO(pddNivelServiceRepo.obtenerPorIdPlanDesarrollo(idPlan));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPddNivelDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que premite buscar un nivel de un pdd por su codigo de nivel
	 * (1,2,3...) y su identificador de plan
	 * 
	 * @param nivel  Long que representa el codigo del nivel (1,2,3...)
	 * @param idPlan Long que representa el identificado del plan de desarrollo
	 * @return un objeto de tipo PddNivelDTO con la informacion correspondiente o un
	 *         objeto vacio
	 * @throws SpddExceptions
	 */
	public PddNivelDTO consultarPddNivelPorNivelYIdPlanDesarrollo(Long nivel, Long idPlan) throws SpddExceptions {
		PddNivel pddNivel = pddNivelServiceRepo.obtenerPorNivelYIdPlanDesarrollo(nivel, idPlan);
		return pddNivel != null ? pddNivelMapper.pddNivelEntityToDTO(pddNivel) : new PddNivelDTO();
	}

	/**
	 * Metodo que permite buscar la competencias asociadas a su identificador
	 * 
	 * @param id Long que representa el identificador de la competencia que se desea
	 *           buscar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorId(Long id) throws SpddExceptions {
		return pddCompetenciaAsociadaMapper
				.pddCompetenciaAsociadaEntityToDTO(pddCompetenciaAsociadaServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite buscar las competencias asociadas a un sector en
	 * especifico
	 * 
	 * @param idSector Long que representa el identificador del sector por el cual
	 *                 se esta buscando
	 * @return un objeto de tipo GenericoDTO con la informacion de los
	 *         pddCompetenciasAsociadas
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompetenciaAsociadaPorIdSector(Long idSector) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddCompetenciaAsociadaDTO> listaCompetenciasDTO = pddCompetenciaAsociadaMapper
				.pddCompetenciasAsociadasEntitiesToDTO(pddCompetenciaAsociadaServiceRepo.obtenerPorIdSector(idSector));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaCompetenciasDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite buscar un PddCompetenciaAsociada que corresponda a los
	 * identificadores del secto y LsCompetencia que se pasan como parametro
	 * 
	 * @param idSector        Long que representa el identificador del sector por el
	 *                        cual se va a buscar o filtrar
	 * @param idLsCompetencia Long que representa el identificador de LsCompetencia
	 *                        por el cual se va a buscar o filtrar
	 * @return un objeto de tipo PddCompetenciaAsociada con la informacion
	 *         correspodiente
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO consultarPddCompetenciaAsociadaPorIdSectorYIdCompetencia(Long idSector,
			Long idLsCompetencia) throws SpddExceptions {
		PddCompetenciaAsociada competenciaAsociada = pddCompetenciaAsociadaServiceRepo
				.obtenerPorIdSectorYIdCompetencia(idSector, idLsCompetencia);

		return competenciaAsociada != null
				? pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(competenciaAsociada)
				: new PddCompetenciaAsociadaDTO();
	}

	/**
	 * Metodo que permite buscar un pdd compromiso especifico por el identificador
	 * 
	 * @param id Long que representa el identificador del pdd comrpromiso especifico
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion del
	 *         pdd compromiso especifico, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorId(Long id) {
		return pddCompromisoEspecifocMapper
				.pddCompromisoEspecificoEntityToDTO(pddCompromisoEspecificoServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite buscar un pdd compromiso especifico por el identificador
	 * del compromiso al cual esta relacionado y la descripcion
	 * 
	 * @param idCompromiso Long que representa el identificador del pdd comrpromiso
	 *                     especifico
	 * @param decripcion   objeto de tipo string que representa la descripcion del
	 *                     compromiso especifico
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion del
	 *         pdd compromiso especifico, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO consultarPddCompromisoEspecificoPorIdCompromisoYDescripcion(Long idCompromiso,
			String decripcion) throws SpddExceptions {
		PddCompromisoEspecifico compromisoEspecifico = pddCompromisoEspecificoServiceRepo
				.obtenerPorIdCompromisoYDescripcion(idCompromiso, decripcion);

		return compromisoEspecifico != null
				? pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(compromisoEspecifico)
				: new PddCompromisoEspecificoDTO();
	}

	/**
	 * Metodo que permite buscar los pddCompromisosEspecificos y las metas y obras
	 * correspondientes relacionados a un pddCompromiso
	 * 
	 * @param idCompromiso Long que representa el identificador del pddCompromiso
	 *                     por el cual se quiere buscar o filtrar
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion de los
	 *         pddCompromisosEspecificos
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarPddCompromisosEspecificosPorIdCompromiso(Long idCompromiso)
			throws SpddExceptions {
		Map<String, List<Object>> mapa = new HashMap<>();
		ArbolCompromisoDTO respuesta = new ArbolCompromisoDTO();
		List<PddCompromisoEspecificoDTO> listaPddCompromisosEspecificosDTO = pddCompromisoEspecifocMapper
				.pddCompromisoEspecificoEntitiesToDTO(
						pddCompromisoEspecificoServiceRepo.obtenerPorIdCompromiso(idCompromiso));
		mapa.put(NHSPDDConstantes.MAPA_LLAVE_COMPROMISO, new ArrayList<>(listaPddCompromisosEspecificosDTO));
		List<PddMetaDTO> metas = new ArrayList<>();
		List<PddObraConcretaDTO> obra = new ArrayList<>();
		listaPddCompromisosEspecificosDTO.stream().forEach(esp -> {
			try {
				List<PddMetaDTO> listMeta = pddMetaMapper.pddMetaEntitiesToDTO(
						pddMetaServiceRepo.consultarMetasCompromistoEstrategico(esp.getIdEspecifico()));
				metas.addAll(listMeta);

				listMeta.stream().forEach(meta -> {
					obra.addAll(pddObraConcretamapper
							.pddObraConcretaEntitiesToDTO(pddObraConcretaServiceRepo.buscarPorMeta(meta.getIdMeta())));

				});

			} catch (SpddExceptions e) {

			}
		});
		mapa.put(NHSPDDConstantes.MAPA_LLAVE_META, new ArrayList<>(metas));
		mapa.put(NHSPDDConstantes.MAPA_LLAVE_OBRA, new ArrayList<>(obra));
		respuesta.setMapObjetos(mapa);
		return respuesta;

	}

	/**
	 * Metodo que permite consultar un plan de desarrollo local y retornarlo segun
	 * el filtro aplicado para este
	 * 
	 * @param peticion objeto de tipo PdlDTO que contiene la informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarPdlPorFiltro(PdlDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = pdlServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<Pdl> listaPage = new PageImpl<>((List<Pdl>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(pdlMapper.pdlEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite consultar un plan de desarrollo local
	 * 
	 * @param peticion objeto de tipo PdlDTO que contiene la informacion
	 * @return un objeto de tipo GenericoDTO con la informacion del plan de
	 *         desarrollo local.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO consultarTodosPlanDesarrolloLocal() {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PdlDTO> listaDTO = pdlMapper.pdlEntitiesToDTO(pdlServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite consultar un Pdl por medio del identificador
	 * 
	 * @param idPlanLocal Long que representa el identificador del plan de
	 *                    desarrollo local por el que se desea buscar
	 * @return un objeto de tipo PdlDTO con la informacion correspondiente.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public PdlDTO consultarPlanDesarrolloLocalPorId(Long idPlanLocal) {
		return pdlMapper.pdlEntityToDTO(pdlServiceRepo.obtener(idPlanLocal));
	}

	/**
	 * Metodo que permite buscar los pdls por el identificador del estado
	 * 
	 * @param idEstado Long que representa el identificador del estado
	 * @return un objeto de tipo GenericoDTO con la informacion encontrada.
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdlsPorEstado(Long idEstado) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PdlDTO> listaPddDTO = pdlMapper.pdlEntitiesToDTO(pdlServiceRepo.obtenerPdlsPorEstado(idEstado));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPddDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite buscar un nivel de plan de desarrollo local (pdlNivel) por
	 * el identificador
	 * 
	 * @param id Long que representa el identificador del pdlNivel
	 * @return un objeto de tipo PdlNivelDTO con la informacion del nivel de plan de
	 *         desarrollo local, o null en caso contrario.
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO consultarPdlNivelPorId(Long idPdlNivel) {
		return pdlNivelMapper.pdlNivelEntityToDTO(pdlNivelServiceRepo.obtener(idPdlNivel));
	}

	/**
	 * Metodo que permite buscar los niveles de pdl por el identificador del pdl
	 * 
	 * @param idPlan Long que representa el identificador del pdl del cual se quiere
	 *               consultar los niveles
	 * @return un objeto de tipo GenericoDTO con la informacion encontrada.
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPdlNivelesPorIdPlanLocal(Long idPlan) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PdlNivelDTO> listaPdlNivelDTO = pdlNivelMapper
				.pdlNivelEntitiesToDTO(pdlNivelServiceRepo.obtenerPdlNivelPorIdPlanLocal(idPlan));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPdlNivelDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que premite buscar un nivel de un pdl por su codigo de nivel
	 * (1,2,3...) y su identificador de plan
	 * 
	 * @param nivel  Long que representa el codigo del nivel (1,2,3...)
	 * @param idPlan Long que representa el identificado del plan de desarrollo
	 *               local
	 * @return un objeto de tipo PdlNivelDTO con la informacion correspondiente o un
	 *         objeto vacio
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO consultarPdlNivelPorNivelYIdPlanLocal(Long nivel, Long idPlan) throws SpddExceptions {
		PdlNivel pdlNivel = pdlNivelServiceRepo.obtenerPdlNivelPorNivelYIdPlanLocal(nivel, idPlan);
		return pdlNivel != null ? pdlNivelMapper.pdlNivelEntityToDTO(pdlNivel) : new PdlNivelDTO();
	}

	/**
	 * Metodo que permite consultar los PDL de una entidad
	 * 
	 * @param codigoEntidad Long que representa el codigo de la entidad por el cual
	 *                      se quiere hacer la busqueda
	 * @return una lista de entidades PDL correspondientes a la busqueda o null en
	 *         caso contrario
	 */
	public GenericoDTO consultarPdlPorLsEstadoPlanYCodigoEntidad(String resultado, String codigoEntidad) {
		GenericoDTO respuesa = new GenericoDTO();
		List<PdlDTO> lstPdlDTO = pdlMapper.pdlEntitiesToDTO(pdlServiceRepo.obtenerPorLsEstadoPlanYCodigoEntidad(resultado, codigoEntidad));
		respuesa.setLstObjectsDTO(new ArrayList<>(lstPdlDTO));
		return respuesa;
	}

	/**
	 * Metodo que permite consultar los PddCompromisos y retornarlo segun el filtro
	 * aplicado para este
	 * 
	 * @param peticion Objeto de tipo PddCompromiso que tiene la informacion
	 *                 necesaria para filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion de los
	 *         PddCompromisos filtrados
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisosPorFiltro(PddCompromisoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		FiltroDTO filtro = pddCompromisoServiceRepo.filtrarPorCampo(peticion);
		respuesta.setLstObjectsDTO(new ArrayList<>(
				pddCompromisoMapper.pddCompromisosEntitiesToDTO((List<PddCompromiso>) filtro.getResultadoConsulta())));
		return respuesta;
	}

	/**
	 * Metodo que permite buscar los pddCompromisos relacionados a un plan de
	 * desarrollo distrital
	 * 
	 * @param idPlan Long que representa el identificador del plan por cual se
	 *               piensa buscar o filtrar
	 * @return un objeto tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddCompromisoPorIdPlanDesarrollo(Long idPlan) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddCompromisoDTO> listaPddCompromisoDTO = pddCompromisoMapper
				.pddCompromisosEntitiesToDTO(pddCompromisoServiceRepo.obtenerTodosPorIdPlanDesarrollo(idPlan));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPddCompromisoDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite buscar un PddCompromiso relacionado a un
	 * compromisoEstrategico y un plan de desarrollo
	 * 
	 * @param idEstrategico Long que representa el identificador del
	 *                      compromisoEstrategico por el cual se piens buscar o
	 *                      filtrar
	 * @param idPlan        Long que representa el identificador del plan de
	 *                      desarrollo por el cual se piensa buscar o filtrar
	 * @return un objeto de tipo PddCompromisoDTO con la inforacion correspondiente.
	 * @throws SpddExceptions
	 */

	public PddCompromisoDTO consultarPddCompromisoPorIdEstrategicoYIdPlanDesarrollo(Long idEstrategico, Long idPlan)
			throws SpddExceptions {
		PddCompromiso pddCompromiso = pddCompromisoServiceRepo.obtenerPorIdEstrategicoYIdPlanDesarrollo(idEstrategico,
				idPlan);

		return pddCompromiso != null ? pddCompromisoMapper.pddCompromisoEntityToDTO(pddCompromiso)
				: new PddCompromisoDTO();

	}

	public PddIndicadorDTO consultarPddIndicadorPorId(Long id) {
		return pddIndicadorMapper.pddIndicadorEntityToDTO(pddIndicadorServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite consultar un PddPrbValoracion por medio del identificador
	 * 
	 * @param idProblematica Long que representa el identificador de la problematica
	 *                       pro la que se desea filtrar o buscar
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorId(Long id) throws SpddExceptions {
		return pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(pddPrbValoracionServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite consultar un PddPrbValoracion por medio del identificador
	 * de la problematica y el valor de momento
	 * 
	 * @param idProblematica Long que representa el identificador de la problematica
	 *                       pro la que se desea filtrar o buscar
	 * @param momento        Long que representa el valor del momento (1-antes,
	 *                       2-Durante, 3-Despues)
	 * @return un objeto de tipo PddPrbValoracionDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO consultarPddPrbValoracionPorIdProblematicaYMomento(Long idProblematica, Long momento)
			throws SpddExceptions {
		PddPrbValoracion pddPrbvaloracion = pddPrbValoracionServiceRepo.obtenerPorIdProblematicaYMomento(idProblematica,
				momento);

		return pddPrbvaloracion != null ? pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(pddPrbvaloracion)
				: new PddPrbValoracionDTO();
	}

	/**
	 * Metodo que consulta las problematicas por Id
	 * 
	 * @param id
	 * @return problematica que coincida con el id.
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO consultarPddProblematicaPorId(Long id) throws SpddExceptions {
		return pddProblematicaMapper.pddProblematicaEntityToDTO(pddProblematicaServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO consultarPddMetaPorId(Long id) throws SpddExceptions {
		return pddMetaMapper.pddMetaEntityToDTO(pddMetaServiceRepo.obtener(id));
	}

	/**
	 * MEtodo que permite consultar una MetaResultado por medio de su identificador
	 * 
	 * @param id Long que reprsent el identificador de la metaResultado
	 * @return objeto de tipo PddMetaResultadoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO consultarPddMetaResultadoPorId(Long id) throws SpddExceptions {
		return pddMetaResultadoMapper.pddMetaResultadoEntityToDTO(pddMetaResultadoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public PddObraConcretaDTO consultarObraConcretaPorId(Long id) {
		return pddObraConcretamapper.pddObraConcretaEntityToDTO(pddObraConcretaServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param idPoblacion
	 * @return
	 */
	public PddPrbPoblacionDTO consultarPrbPolacionPorId(Long idPoblacion) {
		return poblacionMapper.poblacionEntityToDTO(poblacionServiceRepo.obtener(idPoblacion));
	}

	/**
	 * 
	 * @param idProblematica
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddPrbIndicadorPorProblematica(Long idProblematica) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddPrbIndicadorDTO> lista = pddPrbIndicadorMapper.prbIndicadorEntititesToDTO(
				pddPrbIndicadorServiceRepo.obtenerPrbIndicadorPorProblematica(idProblematica));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(lista));
		return genericoDTO;
	}

	/**
	 * 
	 * @param idProbInd
	 * @return
	 */
	public PddPrbIndicadorDTO consultarPddPrbIndicadorPorId(Long idProbInd) {
		return pddPrbIndicadorMapper.prbIndicadorEntityToDTO(pddPrbIndicadorServiceRepo.obtener(idProbInd));
	}

	/**
	 * 
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPddIndicador() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddIndicadorDTO> lista = pddIndicadorMapper
				.pddIndicadorEntitiesToDTO(pddIndicadorServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(lista));
		return genericoDTO;

	}

	/**
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddProblematicaPorCompromiso(PddProblematicaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		FiltroDTO filtro = pddProblematicaServiceRepo.filtrarPorCampo(peticion);
		respuesta.setLstObjectsDTO(new ArrayList<>(pddProblematicaMapper
				.pddProblematicasEntitiesToDTO((List<PddProblematica>) filtro.getResultadoConsulta())));
		return respuesta;
	}

	/**
	 * 
	 * @param meta
	 * @param especifico
	 * @param tipo
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO validarMeta(String meta, Long especifico, Long tipo) throws SpddExceptions {
		PddMeta metaEntidad = pddMetaServiceRepo.validarMetaTipoYEspecifico(meta, especifico, tipo);

		return metaEntidad != null ? pddMetaMapper.pddMetaEntityToDTO(metaEntidad) : new PddMetaDTO();
	}

	/**
	 * 
	 * @param idMeta
	 * @param obraConcreta
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO validarObra(Long idMeta, String obraConcreta) throws SpddExceptions {
		PddObraConcreta respuesta = pddObraConcretaServiceRepo.validarCampos(idMeta, obraConcreta);
		return respuesta != null ? pddObraConcretamapper.pddObraConcretaEntityToDTO(respuesta)
				: new PddObraConcretaDTO();
	}

	/**
	 * Metodo que valida las reglas de negocio a nivel de bd de una poblacion
	 * 
	 * @param descripcion    campos a validar
	 * @param idProblematica campos a validar
	 * @return retorna si el objeto existe o no por esa combinacion de campos
	 * @throws SpddExceptions
	 */
	public PddPrbPoblacionDTO validarPrbPoblacion(String descripcion, Long idProblematica) throws SpddExceptions {
		PddPrbPoblacion respuesta = poblacionServiceRepo.validarReglasPoblacion(descripcion, idProblematica);
		return respuesta != null ? poblacionMapper.poblacionEntityToDTO(respuesta) : new PddPrbPoblacionDTO();
	}

	/**
	 * Metodo que permite consultar la lista de PddMetaResultado correspondiente a
	 * un identificador de problematicaIndicador
	 * 
	 * @param idProblematicaIndicador Long que representa el idenficador de una
	 *                                relacion entre problematica e indicador.
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarPddMetaResultadoPorIDProblematicaIndicador(Long idProblematicaIndicador)
			throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddMetaResultadoDTO> listaPddMetaResultadoDTO = pddMetaResultadoMapper.pddMetaResultadoEntitiesToDTO(
				pddMetaResultadoServiceRepo.consultarPorIDProblematicaIndicador(idProblematicaIndicador));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaPddMetaResultadoDTO));
		return genericoDTO;
	}

	public GenericoDTO consultarMpAnualizarPorIdMetaResultado(Long idMetaResultado) {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddMpAnualizar> lista;
		genericoDTO.setLstObjectsDTO(new ArrayList<>());
		return genericoDTO;
	}

	/**
	 * 
	 * @param idIndicador
	 * @param idProblematica
	 * @return
	 */
	public PddPrbIndicadorDTO validarPrbIndicador(Long idIndicador, Long idProblematica) {
		PddPrbIndicador respuesta = pddPrbIndicadorServiceRepo.consultarPorIndicidicadorYProblematica(idIndicador,
				idProblematica);
		return respuesta != null ? pddPrbIndicadorMapper.prbIndicadorEntityToDTO(respuesta) : new PddPrbIndicadorDTO();
	}

	/**
	 * 
	 * @param pddCompromisoDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarCompromisoPorId(Long id) throws SpddExceptions {
		return pddCompromisoMapper.pddCompromisoEntityToDTO(pddCompromisoServiceRepo.obtener(id));
	}

	/**
	 * Método que valida la llave de negocio de la tabla PDD Problematica
	 * 
	 * @param pddProblematicaDTO
	 * @return
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO validarProblematicaPorLlaveDeNegocio(PddProblematicaDTO pddProblematicaDTO)
			throws SpddExceptions {
		return pddProblematicaMapper.pddProblematicaEntityToDTO(pddProblematicaServiceRepo
				.validarLlaveDeNegocio(pddProblematicaDTO.getIdCompromiso(), pddProblematicaDTO.getProblematica()));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarPotPorFiltro(PotDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = potServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<Pot> listaPage = new PageImpl<>((List<Pot>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(potMapper.potEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param codigoPot
	 * @return
	 */
	public PotDTO obtenerPorCodigo(String codigoPot) {
		return potMapper.potEntityToDTO(potServiceRepo.obtenerPorCodigo(codigoPot));
	}

	/**
	 * metodo que permite obtener un nivel por medio del identificador
	 * 
	 * @param idNivel Long identificador del nivel
	 * @return un objeto de tipo dto con la información del nivel
	 */
	public PotNivelDTO obtenerPotNivelPorId(Long idNivel) {
		return potNivelMapper.PotNivelEntityToDTO(potNivelServiceRepo.obtener(idNivel));
	}

	/**
	 * Metodo que permite obetner un PddNivelAtributo por medio del numeoro y del
	 * identificador del nivel al que esta relacionado
	 * 
	 * @param numero     string que representa el numero del nivel atributo
	 * @param idPddNivel Long que reprsenta el identificador del pddNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PddNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYIdPddNivel(Long numero, Long idPddNivel,
			Long idAtributoPadre) throws SpddExceptions {
		PddNivelAtributo pddNivelAtributoAux = pddNivelAtributoServiceRepo.obtenerPorNumeroYIdPddNivel(numero,
				idPddNivel, idAtributoPadre);
		return pddNivelAtributoAux != null ? pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributoAux)
				: new PddNivelAtributoDTO();
	}

	/**
	 * Permite obtener todos los PddNivelAtributo de un nivel en especifico en orden
	 * ascendente
	 * 
	 * @param idPddNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdPddNivelPaginado(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions {
		if (pddNivelAtributoDTO.getTamanioPagina() == null || pddNivelAtributoDTO.getPagina() == null) {
			pddNivelAtributoDTO.setPagina(0);
			pddNivelAtributoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(pddNivelAtributoDTO.getPagina(), pddNivelAtributoDTO.getTamanioPagina(),
				Sort.by("numero").ascending());
		Page<PddNivelAtributo> pagePddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerTodosPorIdPddNivelPaginado(pddNivelAtributoDTO.getIdPddNivel(), pageRequest);

		List<PddNivelAtributo> listaPddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerTodosPorIdPddNivel(pddNivelAtributoDTO.getIdPddNivel());

		GenericoDTO respuesta = new GenericoDTO();

		if (listaPddNivelAtributo != null && !listaPddNivelAtributo.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPddNivelAtributo.get(listaPddNivelAtributo.size() - 1).getNumero()
					+ 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}
		
		Long ponderacionTotal = pddNivelAtributoServiceRepo	.obtenerPonderacionTotalDeTodosPorIdPddNivel(pddNivelAtributoDTO.getIdPddNivel());
		respuesta.setTotalPonderacion(ponderacionTotal != null ? ponderacionTotal : 0L);

		List<PddNivelAtributoDTO> listaPddNivelAtributoDTO = pddNivelAtributoMapper
				.nivelAtributoEntitiesToDTO(pagePddNivelAtributo.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPddNivelAtributoDTO));
		respuesta.setTotal(pagePddNivelAtributo.getTotalElements());

		return respuesta;
	}

	/**
	 * Metodo que permite obetner un PddNivelAtributo por medio de la denominacion y
	 * del identificador del nivel al que esta relacionado
	 * 
	 * @param numero     string que representa el numero del nivel atributo
	 * @param idPddNivel Long que reprsenta el identificador del pddNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PddNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorDenominacionYIdPddNivel(String denominacion, Long idPddNivel)
			throws SpddExceptions {
		PddNivelAtributo pddNivelAtributoAux = pddNivelAtributoServiceRepo
				.obtenerPorDenominacionYIdPddNivel(denominacion, idPddNivel);
		return pddNivelAtributoAux != null ? pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributoAux)
				: new PddNivelAtributoDTO();
	}

	/**
	 * Permite obtener todos los PddNivelAtributo que corresponden al nivel atributo
	 * padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PddNivelAtributo
	 */
	public GenericoDTO consultarTodosPddNivelAtributoPorIdAtributoPadre(PddNivelAtributoDTO pddNivelAtributoDTO)
			throws SpddExceptions {
		if (pddNivelAtributoDTO.getTamanioPagina() == null || pddNivelAtributoDTO.getPagina() == null) {
			pddNivelAtributoDTO.setPagina(0);
			pddNivelAtributoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(pddNivelAtributoDTO.getPagina(), pddNivelAtributoDTO.getTamanioPagina(),
				Sort.by("numero").ascending());
		Page<PddNivelAtributo> pagePddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerTodosPorIAtributoPadrePaginado(pddNivelAtributoDTO.getIdAtributoPadre(), pageRequest);

		List<PddNivelAtributo> listaPddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerTodosPorIAtributoPadre(pddNivelAtributoDTO.getIdAtributoPadre());

		GenericoDTO respuesta = new GenericoDTO();

		if (listaPddNivelAtributo != null && !listaPddNivelAtributo.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPddNivelAtributo.get(listaPddNivelAtributo.size() - 1).getNumero()
					+ 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}
		
		Long ponderacionTotal = pddNivelAtributoServiceRepo.obtenerPonderacionTotalDeTodosPorIAtributoPadre(pddNivelAtributoDTO.getIdAtributoPadre());
		respuesta.setTotalPonderacion(ponderacionTotal != null ? ponderacionTotal : 0L);
		List<PddNivelAtributoDTO> listaPddNivelAtributoDTO = pddNivelAtributoMapper
				.nivelAtributoEntitiesToDTO(pagePddNivelAtributo.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPddNivelAtributoDTO));
		respuesta.setTotal(pagePddNivelAtributo.getTotalElements());
		return respuesta;
	}

	public PotDTO obtenerPorId(Long idCodigo) throws SpddExceptions {
		return potMapper.potEntityToDTO(potServiceRepo.obtener(idCodigo));
	}

	/**
	 * permite obtener todas las ramas de un pot por medio de un identificado del
	 * pot
	 * 
	 * @param idPot
	 * @return un objeto de tipo GenericoDTO con la lista de todas las ramas de un
	 *         pot
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarRamaPotPorIdPot(Long idPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotRamaDTO> lista = potRamaMapper.potRamaEntitiesToDTO(potRamaServiceRepo.obtenerRamaPotPorIdPot(idPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * Metodo que permite obtener todas las ramas de un pot ordenadas de forma
	 * descendiente por el numero de rama
	 * 
	 * @param idPot Long identificador del pot
	 * @return objeto de tipo GenericoDTO que obtiene todas las ramas de un pot
	 *         ordenadas de forma descendiente por su número de rama
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotRamaDTO> lista = potRamaMapper
				.potRamaEntitiesToDTO(potRamaServiceRepo.obtenerPotRamaPorIdPotNumeroRamaDesc(idPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarMetaResultadoIndicador(PddMRIndicadorDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		if (peticion.getPagina() == null && peticion.getTamanioPagina() == null) {
			peticion.setPagina(0);
			peticion.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = pddMRIndicadorServiceRepo.obtenerTodosIndicadoresPorMetaResultado(
				peticion.getIdMetaResultado(), pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddMRIndicador> listaPage = new PageImpl<>((List<PddMRIndicador>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		
		List<PddMRIndicador> listaPddMRIndicador = pddMRIndicadorServiceRepo.obtenerTodosOrdenadosASC();
		if(listaPddMRIndicador != null && !listaPddMRIndicador.isEmpty())
		{
			Long numeroConsecutivoSiguiente = listaPddMRIndicador.get(listaPddMRIndicador.size()-1).getIdIndProxy() + 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		}
		else
		{
			respuesta.setNumeroConsecutivo(1L);
		}
		
		respuesta.setLstObjectsDTO(
				new ArrayList<Object>(pddMRIndicadorMapper.mRIndicadorEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO validarIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions {
		return pddMRIndicadorMapper.mRIndicadorEntityToDTO(pddMRIndicadorServiceRepo
				.validarIndicadorMetaResultado(peticion.getIdIndicador(), peticion.getIdMetaResultado()));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO obtenerPddIndicadorMetaResultado(PddIndicadorDTO peticion) throws SpddExceptions {
		List<PddIndicador> lista = pddIndicadorServiceRepo.obtenerPddIndicadorMetaResultado(peticion.getNombre(),
				peticion.getMagnitud(), peticion.getPeriodicidad(), peticion.getFuenteExterna(), peticion.getFuente(),
				peticion.getLineaBase(), peticion.getMagnitudLb(), peticion.getLineaBaseDesc());
		if (!lista.isEmpty()) {
			return pddIndicadorMapper.pddIndicadorEntityToDTO(lista.get(0));
		}

		return new PddIndicadorDTO();
	}

	public PddMRIndicadorDTO obtenerPddMRIndicadorPorId(Long id) throws SpddExceptions {
		return pddMRIndicadorMapper.mRIndicadorEntityToDTO(pddMRIndicadorServiceRepo.obtener(id));
	}

	/**
	 * Metodo que permite obtener todos los PotObra correspondientes a un nivel pot
	 * paginado
	 * 
	 * @param idNivelPot identificador del NivelPot por el cual se quiere filtrar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraPorIdNivelPotPaginado(PotObraDTO potObraDTO) throws SpddExceptions {
		if (potObraDTO.getTamanioPagina() == null || potObraDTO.getPagina() == null) {
			potObraDTO.setPagina(0);
			potObraDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(potObraDTO.getPagina(), potObraDTO.getTamanioPagina(),
				Sort.by("codigo").ascending());
		Page<PotObra> pagePotObra = potObraServiceRepo.obtenerTodosPaginadoPorIdNivelPot(potObraDTO.getIdNivelPot(),
				pageRequest);

		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setLstObjectsDTO(new ArrayList<>(potObraMapper.potObraEntitiesToDTO(pagePotObra.getContent())));
		respuesta.setTotal(pagePotObra.getTotalElements());
		return respuesta;
	}

	public GenericoDTO consultarTodosPotObraPorPot(PotObraDTO potObraDTO) throws SpddExceptions {
		Pageable pageRequest = PageRequest.of(potObraDTO.getPagina(), potObraDTO.getTamanioPagina());
		Page<PotObra> pagePotObra = potObraServiceRepo.obtenerPotObraPorPot(potObraDTO.getIdPot(), pageRequest);
		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setLstObjectsDTO(new ArrayList<>(potObraMapper.potObraEntitiesToDTO(pagePotObra.getContent())));
		respuesta.setTotal(pagePotObra.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite buscar un pddCompromisos por el identificador
	 * 
	 * @param id Long que representa el identificador del compromiso
	 * @return un objeto tipo PddCompromisoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO consultarPddCompromisoPorId(Long id) throws SpddExceptions {
		return pddCompromisoMapper.pddCompromisoEntityToDTO(pddCompromisoServiceRepo.obtener(id));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosMetaProductoPorMR(PddMetaProductoDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = pddMetaProductoServiceRepo.obtenerTodosMetaProductos(peticion.getIdMetaResultado(),
				pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddMetaProducto> listaPage = new PageImpl<>((List<PddMetaProducto>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());

		List<PddMetaProducto> listaPddMetaProducto = pddMetaProductoServiceRepo.obtenerTodosOrdenadosASC();
		if (listaPddMetaProducto != null && !listaPddMetaProducto.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPddMetaProducto.get(listaPddMetaProducto.size() - 1)
					.getIdMetaProducto() + 1;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}

		respuesta.setLstObjectsDTO(
				new ArrayList<Object>(pddMetaProductoMapper.metaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		
		Long sumatoriaPonderacion = pddMetaProductoServiceRepo.sumarPonderacionesMetaProducto(peticion.getIdMetaResultado());
		respuesta.setTotalPonderacion(sumatoriaPonderacion != null ? sumatoriaPonderacion : 0L);
		return respuesta;
	}

	/**
	 * 
	 * @param idMetaProducto
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO obtenerMetaProductoPorId(Long idMetaProducto) throws SpddExceptions {
		return pddMetaProductoMapper.metaProductoEntityToDTO(pddMetaProductoServiceRepo.obtener(idMetaProducto));
	}

	/**
	 * Metodo que permite obtener una rama de un pot por medio de un id
	 * 
	 * @param idRamaPot
	 * @return
	 * @throws SpddExceptions
	 */
	public PotRamaDTO obtenerRamaPotPorId(Long idRamaPot) throws SpddExceptions {
		return potRamaMapper.potRamaEntityToDTO(potRamaServiceRepo.obtener(idRamaPot));
	}

	/**
	 * metodo que permite obtener los niveles de una rama de un pot
	 * 
	 * @param idRamaPot Long identificador de la rama pot
	 * @return un objeto de tipo GenericoDTO con la lista de todas los niveles de
	 *         una rama de un pot
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPot(Long idRamaPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotNivelDTO> lista = potNivelMapper
				.potNivelEntitiesToDTO(potNivelServiceRepo.obtenerPotNivelPorIdRamaPot(idRamaPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * metodo que permite obtener los niveles de una rama de un pot ordenados de
	 * forma descendiente
	 * 
	 * @param idRamaPot Long identificador de la rama Pot
	 * @return un objeto de tipo GenericoDTO con la lista de todas los niveles de
	 *         una rama de un pot
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotNivelDTO> lista = potNivelMapper
				.potNivelEntitiesToDTO(potNivelServiceRepo.obtenerPotNivelPorIdRamaPotDesc(idRamaPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * metodo que permite obtener los sub-niveles de una nivel de un pot ordenados
	 * de forma descendiente
	 * 
	 * @param idNivelPot Long identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la lista de todos los sub-niveles
	 *         de un nivel de un pot
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivel(Long idNivelPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotNivelDTO> lista = potNivelMapper
				.potNivelEntitiesToDTO(potNivelServiceRepo.obtenerPotNivelPorIdNivelPadre(idNivelPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * metodo que permite obtener los sub-niveles de una nivel de un pot ordenados
	 * de forma descendiente
	 * 
	 * @param idNivelPot Long identificador del nivel
	 * @return un objeto de tipo GenericoDTO con la lista de todos los sub-niveles
	 *         de un nivel de un pot
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodosNivelPotPorIdNivelDesc(Long idNivelPot) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotNivelDTO> lista = potNivelMapper
				.potNivelEntitiesToDTO(potNivelServiceRepo.obtenerPotNivelPorIdNivelPadreDesc(idNivelPot));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadoresMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageable = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		Page<PddMpIndicador> page = pddMpIndicadorServiceRepo
				.obtenerMpIndicadorPorMetaProducto(peticion.getIdMetaProducto(), pageable);
		
		List<PddMpIndicador> listaPddMpIndicador = pddMpIndicadorServiceRepo.obtenerTodosOrdenadosASC();
		if (listaPddMpIndicador != null && !listaPddMpIndicador.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaPddMpIndicador.get(listaPddMpIndicador.size() - 1).getIdMetaProdInd() + 1L;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}		
		
		respuesta.setLstObjectsDTO(new ArrayList<>(pddMpIndicadorMapper.mpIndicadorEntitiesToDTO(page.getContent())));
		respuesta.setTotal(page.getTotalElements());
		
		Long pondetacionTotal = pddMpIndicadorServiceRepo.sumarPonderacionesMpIndicador(peticion.getIdMetaProducto());
		respuesta.setTotalPonderacion(pondetacionTotal != null ? pondetacionTotal : 0L);
		return respuesta;

	}

	public PddMpIndicadorDTO validarMetaProductoIndicador(PddMpIndicadorDTO peticion) throws SpddExceptions {

		return pddMpIndicadorMapper.mpIndicadorEntityToDTO(pddMpIndicadorServiceRepo
				.validarMetaProductoIndicador(peticion.getIdMetaProducto(), peticion.getIdIndicador()));
	}

	public PddMpIndicadorDTO obtenerIndicadorMetaProductoPorId(Long idIndicadorMp) throws SpddExceptions {
		return pddMpIndicadorMapper.mpIndicadorEntityToDTO(pddMpIndicadorServiceRepo.obtener(idIndicadorMp));

	}

	/**
	 * Metodo que permite obtener un PddNivelAtributoDTO por el identificador
	 * 
	 * @param idAtributo Long que representa el identificador del
	 *                   PddNivelAtributoDTO
	 * @return un ojbeto de tipo PddNivelAtributoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorId(Long idAtributo) throws SpddExceptions {
		return pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributoServiceRepo.obtener(idAtributo));
	}

	/**
	 * MEtodo que permite obtener un PotObra por medio de su codigo y id nivel
	 * 
	 * @param codigo     string que reprsenta el codigo pro el cual se va a buscar
	 * @param idNivelPot Long que representa el identificador del nivel por el cual
	 *                   se va a buscar
	 * @return un objeto de tipo PotObra con la informacion correspondiente.
	 */
	public PotObraDTO consultarPotObraPorCodigoYIdNivelPot(Long codigo, Long idNivelPot) throws SpddExceptions {
		PotObra potObra = potObraServiceRepo.obtenerPorCodigoYIdNivelPot(codigo, idNivelPot);
		return potObra != null ? potObraMapper.potObraEntityToDTO(potObra) : new PotObraDTO();
	}

	/**
	 * Metodo que permite obtener una PotObra por medio del identificador
	 * 
	 * @param idPotObra Long que representa el identificador de la PotObra
	 * @return un objeto de tipo PotObraDTO
	 * @throws SpddExceptions
	 */
	public PotObraDTO consultarPotObraPorId(Long idPotObra) throws SpddExceptions {
		return potObraMapper.potObraEntityToDTO(potObraServiceRepo.obtener(idPotObra));
	}

	/**
	 * Metodo que pemite obtener todos los PotObraEntidad por medio del potObra
	 * 
	 * @param idPotObra Long que representa el identificador del PotObra por el cual
	 *                  se va a buscar
	 * @return una lista de PotObra con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPotObraEntidadPorIdPotObra(Long idPotObra) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PotObraEntidadDTO> listaPotObraEntidadDTO = potObraEntidadMapper
				.potObraEntidadEntitiesToDTO(potObraEntidadServiceRepo.obtenerTodosPorIdPotObra(idPotObra));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPotObraEntidadDTO));
		return respuesta;
	}

	/**
	 * Metodo que permite obtener el PotObraEntidad correspondiente a la entidad y
	 * PotObra indicados
	 * 
	 * @param codigoEntidad String que representa el codigo de la entidad
	 * @param idPotObra     Long que representa el identificador de la potObra
	 * @return un objeto de tipo PotObraEntidad con la informacion correspondiente
	 */
	public PotObraEntidadDTO obtenerPotObraEntidadPorCodigoEntidadYIdPotObra(String codigoEntidad, Long idPotObra)
			throws SpddExceptions {
		PotObraEntidad potObraEntidad = potObraEntidadServiceRepo.obtenerPotCodigoEntidadYIdPotObra(codigoEntidad,
				idPotObra);
		return potObraEntidad != null ? potObraEntidadMapper.potObraEntidadEntityToDTO(potObraEntidad)
				: new PotObraEntidadDTO();
	}

	/**
	 * Metodo que pemite obtener todos los PotIntrumento filtrados y paginados
	 * 
	 * @param potInstrumentoDTO Objeto de tipo PotInstrumentoDTO con la informacion
	 *                          para filtar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodosPotInstrumentoFiltrado(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		if (potInstrumentoDTO.getPagina() == null || potInstrumentoDTO.getTamanioPagina() == null) {
			potInstrumentoDTO.setPagina(0);
			potInstrumentoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(potInstrumentoDTO.getPagina(), potInstrumentoDTO.getTamanioPagina());
		FiltroDTO filtro = potInstrumentoServiceRepo.obtenerTodosFiltrado(potInstrumentoDTO, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<PotInstrumento> listaPage = new PageImpl<>((List<PotInstrumento>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(potInstrumentoMapper.potInstrumentoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite obtener el PotInstrumento por el identificador
	 * 
	 * @param idPotInstrumento Long que representa el identificador del
	 *                         potInstrumento
	 * @return un objeto de tipo PotInstrumnetoDTO con la informacin correspondiente
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO consultarPotInstrumentoPorId(Long idPotInstrumento) throws SpddExceptions {
		return potInstrumentoMapper.potInstrumentoEntityToDTO(potInstrumentoServiceRepo.obtener(idPotInstrumento));
	}

	/**
	 * Metodo que permite obtener un PotInstrumento por el codigo y por
	 * identificador del pot
	 * 
	 * @param codigo String que representa el codigo del PotInstrumento
	 * @param idPot  Long que representa el identificador del pot
	 * @return un objeot de tipo PotInstrumento con la informacion correspondiente
	 */
	public PotInstrumentoDTO consultarPotInstrumentoPorCodigoYIdPot(Long codigo, Long idPot) throws SpddExceptions {
		PotInstrumento potInstrumento = potInstrumentoServiceRepo.obtenerPorCodigoYIdPot(codigo, idPot);
		return potInstrumento != null ? potInstrumentoMapper.potInstrumentoEntityToDTO(potInstrumento)
				: new PotInstrumentoDTO();
	}

	/**
	 * Permite obtener todos los PdlNivelAtributo de un nivel en especifico en orden
	 * ascendente
	 * 
	 * @param idPdlNivel Long que representa el identificador del nivel que se
	 *                   quieren obtener los atributos
	 * @return una genericoDTO con la informaion correspondiente
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdPdlNivelPaginado(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions {
		if (pdlNivelAtributoDTO.getTamanioPagina() == null || pdlNivelAtributoDTO.getPagina() == null) {
			pdlNivelAtributoDTO.setPagina(0);
			pdlNivelAtributoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(pdlNivelAtributoDTO.getPagina(), pdlNivelAtributoDTO.getTamanioPagina(),
				Sort.by("numero").ascending());
		Page<PdlNivelAtributo> pagePdlNivelAtributo = pdlNivelAtributoServiceRepo
				.obtenerTodosPorIdPdlNivelAtributoPaginado(pdlNivelAtributoDTO.getIdPdlNivel(), pageRequest);

		GenericoDTO respuesta = new GenericoDTO();
		List<PdlNivelAtributoDTO> listaPdlNivelAtributoDTO = pdlNivelAtributoMapper
				.nivelAtributoEntitiesToDTO(pagePdlNivelAtributo.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPdlNivelAtributoDTO));
		respuesta.setTotal(pagePdlNivelAtributo.getTotalElements());

		return respuesta;
	}

	/**
	 * Permite obtener todos los PdlNivelAtributo que corresponden al nivel atributo
	 * padre
	 * 
	 * @param idAtributoPadre Long que representa el identificador del nivel
	 *                        atributo padre por el cual se va a buscar
	 * @return una lista de tipo PdlNivelAtributo
	 */
	public GenericoDTO consultarTodosPdlNivelAtributoPorIdAtributoPadre(PdlNivelAtributoDTO pdlNivelAtributoDTO)
			throws SpddExceptions {
		if (pdlNivelAtributoDTO.getTamanioPagina() == null || pdlNivelAtributoDTO.getPagina() == null) {
			pdlNivelAtributoDTO.setPagina(0);
			pdlNivelAtributoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(pdlNivelAtributoDTO.getPagina(), pdlNivelAtributoDTO.getTamanioPagina(),
				Sort.by("numero").ascending());
		Page<PdlNivelAtributo> pagePdlNivelAtributo = pdlNivelAtributoServiceRepo
				.obtenerTodosPorIdAtributoPadrePaginado(pdlNivelAtributoDTO.getIdAtributoPadre(), pageRequest);

		GenericoDTO respuesta = new GenericoDTO();
		List<PdlNivelAtributoDTO> listaPdlNivelAtributoDTO = pdlNivelAtributoMapper
				.nivelAtributoEntitiesToDTO(pagePdlNivelAtributo.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPdlNivelAtributoDTO));
		respuesta.setTotal(pagePdlNivelAtributo.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite obetner un PdlNivelAtributo por medio de la denominacion y
	 * del identificador del nivel al que esta relacionado
	 * 
	 * @param String     que representa la denominacion del nivel atributo
	 * @param idPdlNivel Long que representa el identificador del pdlNivel al que
	 *                   esta asociado el atributo nivel
	 * @return un objeto de tipo PdlNivelAtributo con la informacion
	 *         correspondiente.
	 */
	public PdlNivelAtributoDTO consultarPdlNivelAtributoPorDenominacionYIdPdlNivel(String denominacion, Long idPdlNivel)
			throws SpddExceptions {
		PdlNivelAtributo pdlNivelAtributoAux = pdlNivelAtributoServiceRepo
				.obtenerPorDenominacionYIdPdlNivel(denominacion, idPdlNivel);
		return pdlNivelAtributoAux != null ? pdlNivelAtributoMapper.pdlNivelAtributoEntityToDTO(pdlNivelAtributoAux)
				: new PdlNivelAtributoDTO();
	}

	/**
	 * 
	 * @param idMpEntidad
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO obtenerMpEntidadPorId(Long idMpEntidad) throws SpddExceptions {
		return pddMpEntidadMapper.mPEntidadEntityToDTO(pddMpEntidadServiceRepo.obtener(idMpEntidad));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO obtenerTodasMpEntidades(PddMpEntidadDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable page = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		respuesta.setLstObjectsDTO(new ArrayList<>(
				pddMpEntidadServiceRepo.obtenerTodasMpEntidadPorMetaProducto(peticion.getIdMetaProducto(), page)));
		respuesta.setTotal((long) pddMpEntidadServiceRepo.obtenerTodos().size());
		return respuesta;
	}

	/**
	 * 
	 * @param codigoEntidad
	 * @param idMetaProducto
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO validarMpEntidadPorMetaProductoYEntidad(String codigoEntidad, Long idMetaProducto)
			throws SpddExceptions {
		return pddMpEntidadMapper.mPEntidadEntityToDTO(
				pddMpEntidadServiceRepo.validarMpEntidadPorMetaProductoYEntidad(codigoEntidad, idMetaProducto));
	}

	/**
	 * Metodo que permite construir el arbor con toda la informacion de los
	 * componentes de niveles que estan desbalanceados.
	 * 
	 * @param idPlanDesarrollo Long que representa el identificador al cual se le
	 *                         van a buscar todos los componentes de niveles
	 *                         desbalanceados
	 * @return un objeto de tipo ArbolCompromisoDTO con la informacion
	 *         correspondiente.
	 * @throws SpddExceptions
	 */
	public ArbolCompromisoDTO consultarNivelesComponentesDesbalanceados(Long idPlanDesarrollo) throws SpddExceptions {
		Map<String, List<Object>> mapa = new HashMap<>();
		ArbolCompromisoDTO respuesta = new ArbolCompromisoDTO();

		StringBuilder cadenaAux = new StringBuilder("");
		String separador = "";
		List<PddNivelDTO> listaPddNivelDTOAux = pddNivelMapper
				.pddNivelEntitiesToDTO(pddNivelServiceRepo.obtenerPorIdPlanDesarrollo(idPlanDesarrollo));
		for (int i = 1; i <= listaPddNivelDTOAux.size(); i++) {
			cadenaAux.append(String.format("%s%d", separador, i));
			separador = ",";
		}
		String sinNivelesGeneral = cadenaAux.toString();

		List<PddNivelDTO> listaPddNivelDTODesbalanceados = new ArrayList<PddNivelDTO>();
		List<PddNivelDTO> listaPddNivelDTO = pddNivelMapper.pddNivelEntitiesToDTO(
				pddNivelServiceRepo.obtenerPddNivelDesbalanceadosPorNivelYIdPlanDesarrollo(1L, idPlanDesarrollo));
		listaPddNivelDTO.stream().forEach(pddNivelDTOTemp -> {
			List<PddNivelAtributo> h = pddNivelAtributoServiceRepo
					.obtenerTodosPorIdPddNivel(pddNivelDTOTemp.getIdPddNivel());
			List<PddNivelAtributoDTO> hijos = pddNivelAtributoMapper.nivelAtributoEntitiesToDTO(h);
			if (hijos.isEmpty()) {
				pddNivelDTOTemp.setSinNiveles(String.format("Sin nivel %s", sinNivelesGeneral
						.substring(sinNivelesGeneral.indexOf(String.format("%d", pddNivelDTOTemp.getCodNivel())))));
			} else {
				pddNivelDTOTemp.setSinNiveles("");
			}
		});

		listaPddNivelDTODesbalanceados.addAll(listaPddNivelDTO);

		List<PddNivelAtributoDTO> listaPddNivelAtributoDTODesbalanceados = new ArrayList<PddNivelAtributoDTO>();
		List<PddNivelAtributoDTO> listaPddNivelAtributoDTO = pddNivelAtributoMapper
				.nivelAtributoEntitiesToDTO(pddNivelAtributoServiceRepo.obtenerTodosDesbalanceados(idPlanDesarrollo));
		listaPddNivelAtributoDTO.stream().forEach(pddNivelAtributoDTOTemp -> {
			if (pddNivelAtributoDTOTemp.getProyectoEstrategico() == null
					|| pddNivelAtributoDTOTemp.getProyectoEstrategico().equals(0L)) {
				List<PddNivelAtributo> h = pddNivelAtributoServiceRepo
						.obtenerTodosPorIAtributoPadre(pddNivelAtributoDTOTemp.getIdAtributo());
				List<PddNivelAtributoDTO> hijos = pddNivelAtributoMapper.nivelAtributoEntitiesToDTO(h);

				if (pddNivelAtributoDTOTemp.getCodigoPddNivel() < listaPddNivelDTOAux.size() && hijos.isEmpty()) {
					pddNivelAtributoDTOTemp
							.setSinNiveles(String.format("Sin nivel %s", sinNivelesGeneral.substring(sinNivelesGeneral
									.indexOf(String.format("%d", pddNivelAtributoDTOTemp.getCodigoPddNivel() + 1L)))));
				} else {
					pddNivelAtributoDTOTemp.setSinNiveles("");
				}
			} else {
				// peticion para cuando es un proyecto estrategico: haria la peticion a meta
				// resultado
			}
		});
		listaPddNivelAtributoDTODesbalanceados.addAll(listaPddNivelAtributoDTO);

		List<PddMetaResultadoDTO> listaPddMetaResultadoDTODesbalanceados = new ArrayList<PddMetaResultadoDTO>();
		List<PddMetaResultadoDTO> listaPddMetaResultadoDTO = pddMetaResultadoMapper.pddMetaResultadoEntitiesToDTO(
				pddMetaResultadoServiceRepo.obtenerTodosDesbalanceados(idPlanDesarrollo));
		listaPddMetaResultadoDTODesbalanceados.addAll(listaPddMetaResultadoDTO);

		List<PddMetaProductoDTO> listaPddMetaProductoDTODesbalanceados = new ArrayList<PddMetaProductoDTO>();
		List<PddMetaProductoDTO> listaPddMetaProductoDTO = pddMetaProductoMapper
				.metaEntitiesToDTO(pddMetaProductoServiceRepo.obtenerTodosDesbalanceados(idPlanDesarrollo));
		listaPddMetaProductoDTODesbalanceados.addAll(listaPddMetaProductoDTO);

		List<PddMpIndicadorDTO> listaPddMpIndicadorDTODesbalanceados = new ArrayList<PddMpIndicadorDTO>();
		List<PddMpIndicadorDTO> listaPddMpIndicadorDTO = pddMpIndicadorMapper
				.mpIndicadorEntitiesToDTO(pddMpIndicadorServiceRepo.obtenerTodosDesbalanceados(idPlanDesarrollo));
		listaPddMpIndicadorDTODesbalanceados.addAll(listaPddMpIndicadorDTO);

		mapa.put(PddNivel.class.getSimpleName(), new ArrayList<>(listaPddNivelDTODesbalanceados));
		mapa.put(PddNivelAtributo.class.getSimpleName(), new ArrayList<>(listaPddNivelAtributoDTODesbalanceados));
		mapa.put(PddMetaResultado.class.getSimpleName(), new ArrayList<>(listaPddMetaResultadoDTODesbalanceados));
		mapa.put(PddMetaProducto.class.getSimpleName(), new ArrayList<>(listaPddMetaProductoDTODesbalanceados));
		mapa.put(PddMpIndicador.class.getSimpleName(), new ArrayList<>(listaPddMpIndicadorDTODesbalanceados));

		respuesta.setMapObjetos(mapa);
		return respuesta;
	}

	/**
	 * Metodo que permite consultar los rangos de ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public GenericoDTO obtenerTodosRangoPonderacion() throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddRangoPonderacionDTO> lstDTO = pddRangoPonderacionMapper
				.pddRangoPonderacionEntitiesToDTO(pddRangoPonderacionServiceRepo.obtenerTodos());
		genericoDTO.setLstObjectsDTO(new ArrayList<>(lstDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite un objeto de tipo rango ponderacion
	 * 
	 * @return un objeto de tipo GenericoDTO con la informacion de rango
	 *         ponderacion.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	public PddRangoPonderacionDTO obtenerPddRangoPonderacionPorId(Long idRango) throws SpddExceptions {
		return pddRangoPonderacionMapper
				.ppdRangoPonderacionEntityToDTO(pddRangoPonderacionServiceRepo.obtener(idRango));
	}

	/**
	 * Metodo que permite consultar los PddRangoPonderacion de un plan de desarrollo
	 * en especifico
	 * 
	 * @param idPdd Long que representa el identificador del plan de desarrollo por
	 *              el cual se quiere hacer la busqueda
	 * @return una lista de PddRangoPonderacion correspondiente a la busqueda o null
	 *         en caso contrario
	 */
	public GenericoDTO obtenerPddRangoPonderacionPorIdPdd(Long idPdd) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<PddRangoPonderacionDTO> lstDTO = pddRangoPonderacionMapper.pddRangoPonderacionEntitiesToDTO(
				pddRangoPonderacionServiceRepo.obtenerPddRangoPonderacionPorIdPdd(idPdd));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(lstDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarIndicadoresEntidadesMetaProducto(PddMpIndicadorEntidadDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageable = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		Page<PddMpIndicadorEntidad> page = pddMpIndicadorEntidadServiceRepo
				.obtenerTodosIndicadoresMetaEntidad(peticion.getIdMetaProdInd(), pageable);
		respuesta.setLstObjectsDTO(
				new ArrayList<>(pddMpIndicadorEntidadMapper.indicadorEntidadEntitiesToDTO(page.getContent())));
		respuesta.setTotal(page.getTotalElements());
		respuesta.setTotalPonderacion(
				pddMpIndicadorEntidadServiceRepo.sumarPonderacionesEntidadesMetaProducto(peticion.getIdMetaProdInd()));
		return respuesta;
	}

	/**
	 * 
	 * @param idMpIndEntidad
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO obtenerPddMpIndicadorEntidadPorId(Long idMpIndEntidad) throws SpddExceptions {

		return pddMpIndicadorEntidadMapper
				.indicadorEntidadEntityToDTO(pddMpIndicadorEntidadServiceRepo.obtener(idMpIndEntidad));
	}

	/**
	 * 
	 * @param codigoEntidad
	 * @param idMetaProdInd
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO validarIndicadorEntidadMetaProducto(String codigoEntidad, Long idMetaProdInd)
			throws SpddExceptions {

		return pddMpIndicadorEntidadMapper.indicadorEntidadEntityToDTO(
				pddMpIndicadorEntidadServiceRepo.validarIndicadorEntidad(codigoEntidad, idMetaProdInd));

	}

	/**
	 * Permite obtener un PddNivelAtributo por el numero del PddNivelAtributo del
	 * codigo de nivel 1 del PddNivel
	 * 
	 * @param numero Long que respresenta el numero del pddNivelAtributo de primer
	 *               nivel que se quiere buscar
	 * @return un objeto PddNivelAtributo con la informacion correspondiente
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroDePrimerNivelDeIdPlanDesarrollo(Long numero,
			Long idPlanDesarrollo) throws SpddExceptions {
		PddNivelAtributo pddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerPorNumeroDePrimerNivelDeIdPlanDesarrollo(numero, idPlanDesarrollo);

		return pddNivelAtributo != null ? pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributo)
				: new PddNivelAtributoDTO();
	}

	/**
	 * Metodo que permite obtener un PddNivelAtributo por el numero del
	 * PddNivelAtributo, el codigo_numero del PddNivel y el identificador del
	 * pddnivelAtributo
	 * 
	 * @param numero          Long que respresenta el numero del pddNivelAtributo de
	 *                        primer nivel que se quiere buscar
	 * @param codigoNumero    Long que represnta el codigoNumero del PddNivel al que
	 *                        esta asociado el PddNivelAtributo
	 * @param idAtributoPadre Long que representa el identificador del atributopadre
	 *                        al que esta relacionado
	 * @return un objeto PddNivelAtributo con la informacion correspondiente
	 */
	public PddNivelAtributoDTO consultarPddNivelAtributoPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(Long numero,
			Long codigoNumero, Long idAtributoPadre) throws SpddExceptions {
		PddNivelAtributo pddNivelAtributo = pddNivelAtributoServiceRepo
				.obtenerPorNumeroYCodigoNumeroDePddNivelYPddAtributoPadre(numero, codigoNumero, idAtributoPadre);

		return pddNivelAtributo != null ? pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributo)
				: new PddNivelAtributoDTO();
	}

	/**
	 * Consultar todos de la tabla mpIndicador
	 * 
	 * @return un genericoDTO con la lista de indicadores mp
	 */
	public GenericoDTO consultarIndicadorMetaPorductoTodos() {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddMpIndicadorDTO> lista = pddMpIndicadorMapper
				.mpIndicadorEntitiesToDTO(pddMpIndicadorServiceRepo.obtenerTodos());
		PddMpIndicadorDTO auxiliar = new PddMpIndicadorDTO();
		auxiliar.setIdIndicador(-1L);
		auxiliar.setNombreIndicador("otro");
		lista.add(auxiliar);
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}
	
	/**
	 * @throws SpddExceptions 
	 * 
	 */
	public GenericoDTO consultarProblematicaPorIdCompromiso(Long idCompromiso) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		respuesta.setLstObjectsDTO(new ArrayList<>(pddProblematicaMapper.pddProblematicasEntitiesToDTO(pddProblematicaServiceRepo.consultarPorIdCompromiso(idCompromiso))));
		return respuesta;
	}
	
	/**
	 * Metodo que permite obtener el POT por estado
	 * @param estado estado del por por el cual se quiere filtar (Vigente | Finalizado)
	 * @return una lista con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPOTPorEstado(String estado) throws SpddExceptions
	{
		GenericoDTO respuesta = new GenericoDTO();
		List<PotDTO> listaPotDTO = potMapper.potEntitiesToDTO(potServiceRepo.obtenerTodosPorEstado(estado));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPotDTO));
		return respuesta;
	}
	
	public GenericoDTO consultarPddPrbPoblacionPorIdProblematica(PddPrbPoblacionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = poblacionServiceRepo.obtenerPddPrbPoblacionPorIdProblematica(peticion, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddPrbPoblacion> listaPage = new PageImpl<>((List<PddPrbPoblacion>) filtro.getResultadoConsulta(), pageRequest,
				filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPage.getContent()));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}
	
	/**
	 * Metodo que permite la comunicación entre el appData y appCore para obtener todas las competencias asociadas
	 * @return un generditoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarTodasPddCompetenciaAsociada() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddCompetenciaAsociadaDTO> listaPddCompetenciaAsociadaDTO = pddCompetenciaAsociadaMapper.pddCompetenciasAsociadasEntitiesToDTO(pddCompetenciaAsociadaServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaPddCompetenciaAsociadaDTO));
		return respuesta;
	}
	
	/**
	 * Metodo que permite obtener la lista de PrbIndicador filtrado y paginados
	 * @param pddPrbIndicadorDTO objeto que contiene la informacion para filtrar y paginar
	 * @return un filtroDTO con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPrbIndicadorFiltrado(PddPrbIndicadorDTO pddPrbIndicadorDTO) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(pddPrbIndicadorDTO.getPagina(), pddPrbIndicadorDTO.getTamanioPagina());
		FiltroDTO filtro = pddPrbIndicadorServiceRepo.obtenerTodosFiltrado(pddPrbIndicadorDTO, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<PddPrbIndicador> listaPage = new PageImpl<>((List<PddPrbIndicador>) filtro.getResultadoConsulta(),pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(pddPrbIndicadorMapper.prbIndicadorEntititesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}
	
	public GenericoDTO consultarTodosPrbProblematica() {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddProblematicaDTO> lista = pddProblematicaMapper.pddProblematicasEntitiesToDTO(pddProblematicaServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}
	
	public GenericoDTO consultarPddCompromisoEspecificoFiltrado(PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		if (pddCompromisoEspecificoDTO.getPagina() == null || pddCompromisoEspecificoDTO.getTamanioPagina() == null) {
			pddCompromisoEspecificoDTO.setPagina(0);
			pddCompromisoEspecificoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(pddCompromisoEspecificoDTO.getPagina(), pddCompromisoEspecificoDTO.getTamanioPagina());
		FiltroDTO filtro = pddCompromisoEspecificoServiceRepo.filtrarCompromisoPorCampo(pddCompromisoEspecificoDTO, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddCompromisoEspecifico> listaPage = new PageImpl<>((List<PddCompromisoEspecifico>) filtro.getResultadoConsulta(),	pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;		
	}
	
	/**
	 * Metodo que permite obtenre todos las policitcas publicas filtradas por sus campos
	 * @param pddPoliticaPublicadDTO objeto de tipo PddPoliticaPublicaDTO que contiene la informacion para filtar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO pddPoliticaPublicadDTO) throws SpddExceptions
	{
		GenericoDTO respuesta = new GenericoDTO();
		if (pddPoliticaPublicadDTO.getPagina() == null || pddPoliticaPublicadDTO.getTamanioPagina() == null) {
			pddPoliticaPublicadDTO.setPagina(0);
			pddPoliticaPublicadDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(pddPoliticaPublicadDTO.getPagina(), pddPoliticaPublicadDTO.getTamanioPagina());
		FiltroDTO filtro = pddPoliticaPublicaServiceRepo.obtenerTodosFiltrado(pddPoliticaPublicadDTO, pageRequest.getOffset(), pageRequest.getPageSize());
		Page<PddPoliticaPublica> listaPage = new PageImpl<>((List<PddPoliticaPublica>) filtro.getResultadoConsulta(),	pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(new ArrayList<>(pddPoliticaPublicaMapper.pddPoliticaPublicaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}
	
	/**
	 * Metodo que permite consultar las politicas publicas que no estan relacionadas con el proyecto de inversion
	 * @param idProyInversion Long que representa el proyecto de inversion que esta relacionado con las politicas publicas
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(Long idProyInversion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddPoliticaPublicaDTO> lista = pddPoliticaPublicaMapper.pddPoliticaPublicaEntitiesToDTO(pddPoliticaPublicaServiceRepo.obtenerTodosSinRelacionConProyectoInversion(idProyInversion));
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}
	
	/**
	 * Metodo que permite consultar todas las politicas publicas ordenadas por el campo Politca ascendentemente
	 * @return una lista de PddPoliticaPublica con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosPddPoliticaPublicaOrdenadosPorNombrePolitica() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<PddPoliticaPublicaDTO> lista = pddPoliticaPublicaMapper.pddPoliticaPublicaEntitiesToDTO(pddPoliticaPublicaServiceRepo.obtenerTodosOrdenadosPorNombrePolitica());
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		return respuesta;
	}
	
}
