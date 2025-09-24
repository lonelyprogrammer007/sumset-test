package co.gov.sdp.spdd.data.serviciofacade.bp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.FiltroDTO;
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
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.bp.BpAporteCiudadano;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAnualiza;
import co.gov.sdp.spdd.data.model.bp.BpProyInvAporte;
import co.gov.sdp.spdd.data.model.bp.BpProyInvEtnico;
import co.gov.sdp.spdd.data.model.bp.BpProyInvFinancia;
import co.gov.sdp.spdd.data.model.bp.BpProyInvIniciativa;
import co.gov.sdp.spdd.data.model.bp.BpProyInvLinea;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPmr;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPoblacion;
import co.gov.sdp.spdd.data.model.bp.BpProyInvPolitica;
import co.gov.sdp.spdd.data.model.bp.BpProyInvTipo;
import co.gov.sdp.spdd.data.model.bp.BpProyectoInversion;

/**
 * Clase que representa el Facade para el servicio Consultar del modulo BP
 * 
 * @author DANIEL, SEBASTIAN
 * @version 1.0 31/03/2020
 */
@Component
public class SessionConsultaBP extends SessionBP implements Serializable {

	/**
	 * Constructor
	 */
	public SessionConsultaBP() {
		super();
	}

	/**
	 * Metodo que permite consultar un BpProyectoInversion segun el filtro aplicado
	 * para este
	 * 
	 * @param peticion objeto de tipo BpProyectoInversionDTO que contiene la
	 *                 informacion a filtrar
	 * @return un objeto de tipo GenericoDTO con la informacion filtrada.
	 * @throws SpddExceptions excepcion propia que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = bpProyectoInversionServiceRepo.filtrarPorCampo(peticion, pageRequest.getOffset(),
				pageRequest.getPageSize());
		Page<BpProyectoInversion> listaPage = new PageImpl<>((List<BpProyectoInversion>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());

		respuesta.setLstObjectsDTO(
				new ArrayList<>(bpProyectoInversionMapper.bpProyectosInversionEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite consutar una lista de BpProyInvLine por medio del
	 * identificador del proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto genericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBpProyectoInversionTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		
		//List<BpProyectoInversionDTO> listaProyectosInversion = vistaBpProyectoInversionMapper.bpProyectosInversionEntitiesToDTO(vistaBpProyectoInversionServiceRepo.obtenerTodos());
		List<BpProyectoInversionDTO> listaProyectosInversion = bpProyectoInversionMapper.bpProyectosInversionEntitiesToDTO(bpProyectoInversionServiceRepo.obtenerTodos());
		
		respuesta.setLstObjectsDTO(new ArrayList<>(listaProyectosInversion));
		return respuesta;
	}

	/**
	 * Metodo que permite consutar una lista de BpProyInvLine por medio del
	 * identificador del proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBpProyInvLineaPorIdProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<BpProyInvLineaDTO> listaBpProyInvLineaDTO = bpProyInvLineaMapper
				.bpProyInvLineasEntitiesToDTO(bpProyInvLineaServiceRepo.obtenerPorIdProyectoInversion(idProyecto));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaBpProyInvLineaDTO));
		return genericoDTO;
	}

	/**
	 * Metodo que permite consutar una lista de BpProyInvTipo por medio del
	 * identificador del proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo GenericoDTO con la informacion correspondiente
	 * @throws SpddExceptions
	 */
	public GenericoDTO consultarBpProyInvTipoPorIdProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO genericoDTO = new GenericoDTO();
		List<BpProyInvTipoDTO> listaBpProyInvTipoDTO = bpProyInvTipoMapper
				.bpProyInvTiposEntitiesToDTO(bpProyInvTipoServiceRepo.obtenerPorIdProyectoInversion(idProyecto));
		genericoDTO.setLstObjectsDTO(new ArrayList<>(listaBpProyInvTipoDTO));
		return genericoDTO;
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public GenericoDTO filtroInciativaCiudadana(BpIniciativaCiudadanaDTO peticion) throws SpddExceptions {

		GenericoDTO respuesta = new GenericoDTO();
		Pageable pageRequest = PageRequest.of(peticion.getPagina(), peticion.getTamanioPagina());
		FiltroDTO filtro = iniciativaCiudadanaServiceRepo.obtenerTodosIniciativaPorFiltro(peticion,
				pageRequest.getOffset(), pageRequest.getPageSize());
		Page listaPage = new PageImpl<>(filtro.getResultadoConsulta(), pageRequest, filtro.getContador());
		respuesta.setLstObjectsDTO(
				new ArrayList<>(vistaBpIniciativaCiudadanaMapper.vistaIniciativaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite buscar un BpProyInvTipo que corresponda a los
	 * identificadores del tipo y proyecto que se pasan como parametro
	 * 
	 * @param idLsTipo        Long que representa el identificador del argumento de
	 *                        lista simple que representa el tipo
	 * @param idProyInversion Long que respresenta el idnetificador del proyecto de
	 *                        inversion
	 * @return un objeto de tipo BpProyInvTipoDTO con la ifnromacin correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvTipoDTO consultarBpProyInvTipoPorIdLsTipoYIdProyInv(Long idLsTipo, Long idProyInversion)
			throws SpddExceptions {
		BpProyInvTipo bpProyInvTipo = bpProyInvTipoServiceRepo.obtenerPorIdLsTipoYIdProyInversion(idLsTipo,
				idProyInversion);

		return bpProyInvTipo != null ? bpProyInvTipoMapper.bpProyInvTipoEntityToDTO(bpProyInvTipo)
				: new BpProyInvTipoDTO();

	}

	/**
	 * Metodo que se encarga de consultar todos los registros que hay en BD de la
	 * tabla BpAporteCiudadano.
	 * 
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO consultarBpAporteCiudadanoTodos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpAporteCiudadanoDTO> listaAportes = bpAporteCiudadanoMapper
				.bpAportesCiudadanoEntitiesToDTO(bpAporteCiudadanoServiceRepo.obtenerTodos());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaAportes));
		return respuesta;
	}

	/**
	 * Metodo que se encarga de consultar todos los registros que hay en BD de la
	 * tabla BpAporteCiudadano pero que aun no tienen una relacion con el proyecto
	 * BpProyectoInversion indicado en el parametro
	 * 
	 * @param idProyecto Long que representa el identificador del
	 *                   BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpAporteCiudadanoDTO> listaAportes = bpAporteCiudadanoMapper.bpAportesCiudadanoEntitiesToDTO(
				bpAporteCiudadanoServiceRepo.obtenerTodosSinRelacionConProyectoInversion(idProyecto));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaAportes));
		return respuesta;
	}

	/**
	 * Metodo que permite obtener un BpProyInvAporte por el indentificador del
	 * aporte y el identificador del proyecto
	 * 
	 * @param idAporte        Long que representa el identificador del aporte
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public BpProyInvAporteDTO consultarBpProyInvAportePorIdAporteYIdProyInversion(Long idAporte, Long idProyInversion)
			throws SpddExceptions {
		BpProyInvAporte bpProyInvAporte = bpProyInvAporteServiceRepo.obtenerPorIdAporteYIdProyInversion(idAporte,
				idProyInversion);

		return bpProyInvAporte != null ? bpProyInvAporteMapper.bpProyInvAporteEntityToDTO(bpProyInvAporte)
				: new BpProyInvAporteDTO();
	}

	/**
	 * Metodo que permite buscar un BpProyInv por medio del identificador del
	 * proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se quiere buscar
	 * @return un objeto de tipo BpProyInversionDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO consultarProyInvPorId(Long idProyecto) {
		return bpProyectoInversionMapper
				.bpProyectoInversionEntityToDTO(bpProyectoInversionServiceRepo.obtener(idProyecto));
	}

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BpIniciativaUbicacionDTO> obtenerUbicacionPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return bpIniciativaUbicacionMapper.inciativaUbicacionEntitiesToDTO(
				bpIniciativaUbicacionServiceRepo.obtenerUbicacionPorIniciativa(idIniciativa));
	}

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BpIniciativaEtarioDTO> obtenerGruposEtariosPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return bpIniciativaEtarioMapper
				.etarioEntitiesToDTO(bpIniciativaEtarioServiceRepo.obtenerGrupoPorIniciativa(idIniciativa));
	}

	/**
	 * Metodo que permite obtener todos BpProyInvAporte por el identificador del
	 * proyecto paginados
	 * 
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador       objeto de tipo pageable que contiene la informacion
	 *                        para pagianr
	 * @return un objeto de tipo BpProyInvAporte con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO bpProyInvAporteDTO)
			throws SpddExceptions {
<<<<<<< HEAD
=======
		GenericoDTO respuesta = new GenericoDTO();
		
>>>>>>> developer
		if (bpProyInvAporteDTO.getTamanioPagina() == null || bpProyInvAporteDTO.getPagina() == null) {
			bpProyInvAporteDTO.setPagina(0);
			bpProyInvAporteDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(bpProyInvAporteDTO.getPagina(), bpProyInvAporteDTO.getTamanioPagina());
		Page<BpProyInvAporte> pageBpProyInvAporte = bpProyInvAporteServiceRepo
				.obtenerTodosPorIdProyInversionPaginado(bpProyInvAporteDTO.getIdProyInversion(), pageRequest);
<<<<<<< HEAD

		GenericoDTO respuesta = new GenericoDTO();
=======
		
		List<BpAporteCiudadano> listaAportesCiudadanos = bpAporteCiudadanoServiceRepo.obtenerTodosOrdenadorPorConsecutivoASC();
		if (listaAportesCiudadanos != null && !listaAportesCiudadanos.isEmpty()) {
			Long numeroConsecutivoSiguiente = listaAportesCiudadanos.get(listaAportesCiudadanos.size() - 1).getConsecutivo() + 1L;
			respuesta.setNumeroConsecutivo(numeroConsecutivoSiguiente);
		} else {
			respuesta.setNumeroConsecutivo(1L);
		}
		
		
>>>>>>> developer
		List<BpProyInvAporteDTO> listaBpProyInvAporteDTO = bpProyInvAporteMapper
				.bpProyInvAportesEntitiesToDTO(pageBpProyInvAporte.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvAporteDTO));
		respuesta.setTotal(pageBpProyInvAporte.getTotalElements());

		return respuesta;
	}

	/**
	 * Metodo que permite buscar un BpAporteCiudadano por medio del identificador
	 * 
	 * @param idAporte Long que representa el identificador del Aporte ciudadano
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte) throws SpddExceptions {
		return bpAporteCiudadanoMapper.bpAporteCiudadanoEntityToDTO(bpAporteCiudadanoServiceRepo.obtener(idAporte));
	}

	/**
	 * Metodo que permite buscar un BpProyInvAporte por medio del identificador
	 * 
	 * @param idProyInvAporte Long que representa el identificador BpProyInvAporte
	 * @return un objeto de tipo BpProyInvAporteDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO consultarProyInvAportePorId(Long idProyInvAporte) throws SpddExceptions {
		return bpProyInvAporteMapper.bpProyInvAporteEntityToDTO(bpProyInvAporteServiceRepo.obtener(idProyInvAporte));
	}

	/**
	 * Metodo que permite obtener un BpAporteCiudadano mediante la combinacion de
	 * campos pasados como parametros
	 * 
	 * @param accion             String que representa la accion por la cual se va a
	 *                           buscar
	 * @param fuente             String que representa la fuente por la cual se va a
	 *                           buscar
	 * @param idPddNivelAtributo Long que representa el identificador del nivels
	 *                           atributo por el cual se va a buscar
	 * @return Un objeto BpAporteCiudadano con la informacion correspondientes.
	 */
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorAccionYFuenteYIdNivelPdd(String accion, String fuente,
			Long idPddNivelAtributo) throws SpddExceptions {
		BpAporteCiudadano bpAporteCiudadano = bpAporteCiudadanoServiceRepo.obtenrePorAccionYFuenteYIdNivelPdd(accion,
				fuente, idPddNivelAtributo);

		return bpAporteCiudadano != null ? bpAporteCiudadanoMapper.bpAporteCiudadanoEntityToDTO(bpAporteCiudadano)
				: new BpAporteCiudadanoDTO();
	}

	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la
	 * tabla BpAporteCiudadano correspondiente a los aportes cargados desde un
	 * archivo plano
	 * 
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpAporteCiudadanoDTO> listaBpAporteCiudadanoDTO = bpAporteCiudadanoMapper
				.bpAportesCiudadanoEntitiesToDTO(bpAporteCiudadanoServiceRepo.obtenerTodosCargadosPorArchivos());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpAporteCiudadanoDTO));
		return respuesta;
	}

	/**
	 * Metodo que se encarga de obtener todos los registros que hay en BD de la
	 * tabla BpAporteCiudadano correspondiente a los aportes cargados desde un
	 * archivo plano y que estan relacionados con el proyecto de inversion pasado
	 * como parametro
	 * 
	 * @param idProyecto Long que representa el identificador del
	 *                   BpProyectoInversion para restringir la consultar
	 * @return Una lista de BpAporteCiudadano con todos los registros
	 *         correspondientes
	 */
	public GenericoDTO obtenerTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpAporteCiudadanoDTO> listaBpAporteCiudadanoDTO = bpAporteCiudadanoMapper
				.bpAportesCiudadanoEntitiesToDTO(bpAporteCiudadanoServiceRepo
						.obtenerTodosCargadosPorArchivosConRelacionConProyectoInversion(idProyecto));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpAporteCiudadanoDTO));
		return respuesta;
	}

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public List<BpIniciativaCondicionDTO> obtenerCondicionesPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return bpIniciativaCondicionMapper.iniciativaCondicionEntitiesToDTO(
				bpIniciativaCondicionServiceRepo.obtenerCondicionesPorIniciativa(idIniciativa));
	}

	/**
	 * 
	 * @param idIniciativa
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO consultarBpIniciativaCiudadanaPorId(Long idIniciativa) throws SpddExceptions {
		return iniciativaCiudadanaMapper
				.bpIniciativaCiudadanaEntityToDTO(iniciativaCiudadanaServiceRepo.obtener(idIniciativa));
	}

	/**
	 * Metodo que permite obtener todas las BpIniciativasCiudadanas que estan
	 * relacionadas con el proyecto de inversion especificado
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto por el
	 *                   cual se van a filtar o buscar las iniciativas
	 * @return unas lista de objetos BpIniciativasCiudadanas
	 */
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto)
			throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpIniciativaCiudadanaDTO> listaBpIniciativaCiudadanaDTO = iniciativaCiudadanaMapper
				.bpIniciativaCiudadanaEntitiesToDTO(
						iniciativaCiudadanaServiceRepo.obtenerTodosRelacionadasConProyectoInversion(idProyecto));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpIniciativaCiudadanaDTO));
		return respuesta;
	}

	/**
	 * Metodo que permite obtener todas las relaciones entre iniciativa ciudadana y
	 * proyecto de inversion segun el identificador del proyecto de inversion pasado
	 * como parametro
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto de
	 *                   inversion por el cual se va a filtra
	 * @return un GenericoDTO con la lista de BpProyInvIniciativa
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdProyInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		List<BpProyInvIniciativaDTO> listaBpProyInvIniciativaDTO = bpProyInvIniciativaMapper
				.bpProyInvIniciativaEntitiesToDTO(
						bpProyInvIniciativaServiceRepo.obtenerTodosPorIdProyInversion(idProyecto));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvIniciativaDTO));
		return respuesta;
	}

	/**
	 * Metodo que permite obtener todas una relacione entre iniciativa ciudadana y
	 * proyecto de inversion segun el identificador del proyecto de inversion y el
	 * identificador de la iniciativa pasadas como parametro
	 * 
	 * @param idProyecto  Long que representa el identificador del proyecto de
	 *                    inversion por el cual se va a filtra
	 * @param idInciativa Long que representa el identificador de la iniciativa
	 *                    ciudadana por el cual se va a filtar
	 * @return un objeto de tipo sBpProyInvIniciativa
	 */
	public BpProyInvIniciativaDTO consultarBpProyInvIniciativaPorIdIniciativaYIdProyInversion(Long idIniciativa,
			Long idProyecto) throws SpddExceptions {
		BpProyInvIniciativa bpProyInvIniciativa = bpProyInvIniciativaServiceRepo
				.obtenerPorIdIniciativaYIdProyInversion(idIniciativa, idProyecto);
		return bpProyInvIniciativa != null
				? bpProyInvIniciativaMapper.bpProyInvIniciativaEntityToDTO(bpProyInvIniciativa)
				: new BpProyInvIniciativaDTO();
	}

	/**
	 * Metodo que permite buscar un BpProyInvEspecif por medio del identificador
	 * 
	 * @param idProyObjEspecif Long que representa el identificador BpProyInvEspecif
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO consultarBpProyInvEspecifPorId(Long idProyObjEspecif) throws SpddExceptions {
		return bpProyInvEspecifMapper
				.bpProyInvEspecifEntityToDTO(bpProyInvEspecifServiceRepo.obtener(idProyObjEspecif));
	}

	/**
	 * Metodo que permite buscar un BpProyInvProducto por medio del identificador
	 * 
	 * @param idProducto Long que representa el identificador BpProyInvProducto
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO consultarBpProyInvProductoPorId(Long idProducto) throws SpddExceptions {
		return bpProyInvProductoMapper.bpProyInvProductoEntityToDTO(bpProyInvProductoServiceRepo.obtener(idProducto));
	}

	/**
	 * Metodo que permite buscar un BpProyInvActividad por medio del identificador
	 * 
	 * @param idActividad Long que representa el identificador BpProyInvActividad
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO consultarBpProyInvActividadPorId(Long idActividad) throws SpddExceptions {
		return bpProyInvActividadMapper
				.bpProyInvActividadEntityToDTO(bpProyInvActividadServiceRepo.obtener(idActividad));
	}

	/**
	 * Metodo que permite buscar un BpProyInvMetaPlan por medio del identificador
	 * 
	 * @param idProyObjEspecif Long que representa el identificador
	 *                         BpProyInvMetaPlan
	 * @return un objeto de tipo BpProyInvMetaPlanDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO obtenerPorIdProyInvEspecif(Long idProyObjEspecif) {
		return bpProyInvMetaPlanMapper.bpProyInvMetaPlanEntityToDTO(
				bpProyInvMetaPlanServiceRepo.obtenerPorIdProyInvEspecif(idProyObjEspecif));
	}

	/**
	 * Metodo que permite buscar un BpProyInvProducto por medio del identificador
	 * 
	 * @param idProyMetaPlan Long que representa el identificador BpProyInvProducto
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO consultarBpProyInvProductoPorIdProyInvMetaPlan(Long idProyMetaPlan) {
		return bpProyInvProductoMapper
				.bpProyInvProductoEntityToDTO(bpProyInvProductoServiceRepo.obtenerPorIdProyInvMetaPlan(idProyMetaPlan));
	}

	/**
	 * Metodo que permite buscar un BpProyInvActividad por medio del identificador
	 * 
	 * @param idProyMetaPlan Long que representa el identificador BpProyInvActividad
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion
	 *         correspondiente
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO consultarBpProyInvActividadPorIdProducto(Long idProducto) {
		return bpProyInvActividadMapper
				.bpProyInvActividadEntityToDTO(bpProyInvActividadServiceRepo.obtenerPorIdProducto(idProducto));
	}

<<<<<<< HEAD
=======
	/**
	 * Metodo que permite obtener todos BpProyInvFinancia por el identificador del
	 * proyecto paginados
	 * 
	 * @param idProyInversion Long que representa el identificador del proyecto
	 * @param paginador       objeto de tipo pageable que contiene la informacion
	 *                        para pagianr
	 * @return un objeto de tipo BpProyInvFinancia con la informacion
	 *         correspondiente
	 */
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO bpProyInvFinanciaDTO)
			throws SpddExceptions {
		if (bpProyInvFinanciaDTO.getTamanioPagina() == null || bpProyInvFinanciaDTO.getPagina() == null) {
			bpProyInvFinanciaDTO.setPagina(0);
			bpProyInvFinanciaDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(bpProyInvFinanciaDTO.getPagina(),
				bpProyInvFinanciaDTO.getTamanioPagina());
		Page<BpProyInvFinancia> pageBpProyInvFinancia = bpProyInvFinanciaServiceRepo
				.obtenerPorIdTodosProyInvFiancia(bpProyInvFinanciaDTO.getIdProyInversion(), pageRequest);

		GenericoDTO respuesta = new GenericoDTO();
		List<BpProyInvFinanciaDTO> listaBpProyInvFinanciaDTO = bpProyInvFinanciaMapper
				.bpProyInvFinanciaEntitiesToDTO(pageBpProyInvFinancia.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvFinanciaDTO));
		respuesta.setTotal(pageBpProyInvFinancia.getTotalElements());

		return respuesta;
	}

	/**
	 * Metodo que permite obtener todos ProyInvAnualiza por el identificador de la
	 * fuente
	 * 
	 * @param idFuente  Long que representa el identificador de la fuente
	 * @param paginador objeto de tipo pageable que contiene la informacion para
	 *                  pagianr
	 * @return un objeto de tipo BpProyInvAnualiza con la informacion
	 *         correspondiente
	 */
	public GenericoDTO consultarTodosProyInvAnualizaPorIdFuentePaginado(BpProyInvAnualizaDTO bpProyInvAnualizaDTO)
			throws SpddExceptions {
		if (bpProyInvAnualizaDTO.getTamanioPagina() == null || bpProyInvAnualizaDTO.getPagina() == null) {
			bpProyInvAnualizaDTO.setPagina(0);
			bpProyInvAnualizaDTO.setTamanioPagina(Integer.MAX_VALUE);
		}
		Pageable pageRequest = PageRequest.of(bpProyInvAnualizaDTO.getPagina(), bpProyInvAnualizaDTO.getTamanioPagina(),
				Sort.by("vigencia").ascending());
		Page<BpProyInvAnualiza> pageBpProyInvAnualiza = bpProyInvAnualizaServiceRepo
				.obtenerPorIdTodosProyInvAnualiza(bpProyInvAnualizaDTO.getIdFuente(), pageRequest);

		GenericoDTO respuesta = new GenericoDTO();
		List<BpProyInvAnualizaDTO> listaBpProyInvAnualizaDTO = bpProyInvAnualizaMapper
				.bpProyInvAnualizaEntitiesToDTO(pageBpProyInvAnualiza.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvAnualizaDTO));
		respuesta.setTotal(pageBpProyInvAnualiza.getTotalElements());

		return respuesta;
	}

	/**
	 * Metodo que permite consultar ProyInvFinancia por IdLsFuente y pot IdProyInv
	 * 
	 * @param idLsFUente    id de el argumento Ls
	 * @param idProyectoInv id del proyecto de inversion
	 * @return
	 */
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorIdLSFuYIdProy(Long idLsFUente, Long idProyectoInv) {

		BpProyInvFinancia AuxbpProyInvFinancia = bpProyInvFinanciaServiceRepo
				.consultarProyInvFinanciaPorIdLSFuYIdProy(idLsFUente, idProyectoInv);

		return AuxbpProyInvFinancia != null ? bpProyInvFinanciaMapper.bpProyInvFinanciaEntityToDTO(AuxbpProyInvFinancia)
				: new BpProyInvFinanciaDTO();
	}

	/**
	 * Metodo que permite consultar ProyInvFinancia por IdFuente
	 * @param idFuente id de la fuente respectiva
	 * @return
	 */
	public BpProyInvFinanciaDTO consultarProyInvFinanciaPorId(Long idFuente) {

		BpProyInvFinancia bpProyInvFinancia = bpProyInvFinanciaServiceRepo.consultarProyInvFinanciaPorId(idFuente);

		return bpProyInvFinancia != null ? bpProyInvFinanciaMapper.bpProyInvFinanciaEntityToDTO(bpProyInvFinancia)
				: new BpProyInvFinanciaDTO();
	}

>>>>>>> developer
	/**
	 * Metodo que se encarga de consultar todos los registros que hay en BD de la
	 * tabla BpProyInvPoblacion que estan asociados a un proyecto de inversion
	 * 
	 * @param bpProyInvPoblacionDTO BpProyInvPoblacionDTO, representa el DTO
	 *                              correspondiente con la informacion requerida en
	 *                              la consulta
	 * @return Una lista de bpProyInvPoblacionDTO con todos los registros
	 *         correspondientes a un proyecto de inversion, y la paginacion
	 */
<<<<<<< HEAD
	public GenericoDTO consultarGruposEtarios(
=======
	public GenericoDTO consultarTodosProyInvPoblacionAsociadosAProyectoInversion(
>>>>>>> developer
			BpProyInvPoblacionDTO bpProyInvPoblacionDTO) throws SpddExceptions {
		if (bpProyInvPoblacionDTO.getTamanioPagina() == null || bpProyInvPoblacionDTO.getPagina() == null) {
			bpProyInvPoblacionDTO.setPagina(0);
			bpProyInvPoblacionDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(bpProyInvPoblacionDTO.getPagina(),
				bpProyInvPoblacionDTO.getTamanioPagina());

		GenericoDTO respuesta = new GenericoDTO();
		Page<BpProyInvPoblacion> listaBpProyInvPoblacion = bpProyInvPoblacionServiceRepo
<<<<<<< HEAD
				.obtenerTodosGruposEtarios(bpProyInvPoblacionDTO.getIdProyInversion(),
=======
				.obtenerTodosProyInvPoblacionAsociadosAProyectoInversion(bpProyInvPoblacionDTO.getIdProyInversion(),
>>>>>>> developer
						pageRequest);
		List<BpProyInvPoblacionDTO> listaBpProyInvPoblacionDTO = bpProyInvPoblacionMapper
				.bpProyInvPoblacionEntitiesToDTO(listaBpProyInvPoblacion.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvPoblacionDTO));
		respuesta.setTotal(listaBpProyInvPoblacion.getTotalElements());
		return respuesta;
	}

	/**
	 * Metodo que permite obtener todas las realaciones filtradas por iniciativa ciudadana
	 * @param idIniciativa Long que representa el identificador de la iniciativa por la cual se quiere filtrar
	 * @return una lista de BpProyInvIniciativa con la informacion correspondiente
	 */
	public GenericoDTO consultarTodosBpProyInvIniciativaPorIdIniciativa(Long idIniciativa) throws SpddExceptions
	{
		GenericoDTO respuesta = new GenericoDTO();
		List<BpProyInvIniciativaDTO> listaBpProyInvIniciativaDTO = bpProyInvIniciativaMapper.bpProyInvIniciativaEntitiesToDTO(bpProyInvIniciativaServiceRepo.obtenerTodosPorIdIniciativa(idIniciativa));
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvIniciativaDTO));
		return respuesta;
	}
	 
	/** Metodo que se encarga de consultar todos los registros que hay en BD de la
	 * tabla BpProyInvEtnico que estan asociados a un proyecto de inversion
	 * 
	 * @param bpProyInvEtnicoDTO BpProyInvEtnicoDTO, representa el DTO
	 *                           correspondiente con la informacion requerida en la
	 *                           consulta
	 * @return Una lista de bpProyInvEtnicoDTO con todos los registros
	 *         correspondientes a un proyecto de inversion, y la paginacion
	 */
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO bpProyInvEtnicoDTO)
			throws SpddExceptions {
		if (bpProyInvEtnicoDTO.getTamanioPagina() == null || bpProyInvEtnicoDTO.getPagina() == null) {
			bpProyInvEtnicoDTO.setPagina(0);
			bpProyInvEtnicoDTO.setTamanioPagina(Integer.MAX_VALUE);
		}

		Pageable pageRequest = PageRequest.of(bpProyInvEtnicoDTO.getPagina(), bpProyInvEtnicoDTO.getTamanioPagina());

		GenericoDTO respuesta = new GenericoDTO();
		Page<BpProyInvEtnico> listaBpProyInvEtnico = bpProyInvEtnicoServiceRepo
<<<<<<< HEAD
				.obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(bpProyInvEtnicoDTO.getIdPoblacion(), pageRequest);
=======
				.obtenerTodosProyInvEtnicoAsociadosAProyectoInversion(bpProyInvEtnicoDTO.getIdProyInversion(),
						pageRequest);
>>>>>>> developer
		List<BpProyInvEtnicoDTO> listaBpProyInvEtnicoDTO = bpProyInvEtnicoMapper
				.bpProyInvEtnicoEntitiesToDTO(listaBpProyInvEtnico.getContent());
		respuesta.setLstObjectsDTO(new ArrayList<>(listaBpProyInvEtnicoDTO));
		respuesta.setTotal(listaBpProyInvEtnico.getTotalElements());
		return respuesta;
	}

	public BpProyInvPoblacionDTO consultarBpProyInvPoblacionPorIdLsEtarioYIdProyInv(Long idLsEtario, Long idProyInv) {

		BpProyInvPoblacion bpProyInvPoblacion = bpProyInvPoblacionServiceRepo.obtenerPorIdLsEtarioYIdProyInv(idLsEtario,
				idProyInv);
		return bpProyInvPoblacion != null ? bpProyInvPoblacionMapper.bpProyInvPoblacionEntityToDTO(bpProyInvPoblacion)
				: new BpProyInvPoblacionDTO();
	}

<<<<<<< HEAD
	public BpProyInvEtnicoDTO consultarBpProyInvEtnicoPorIdLsEtnicoYIdPoblacion(Long idLsEtnico, Long idPoblacion) {

		BpProyInvEtnico bpProyInvEtnico = bpProyInvEtnicoServiceRepo.obtenerPorIdLsEtnicoYIdPoblacion(idLsEtnico,
				idPoblacion);
=======
	public BpProyInvEtnicoDTO consultarBpProyInvEtnicoPorIdLsEtnicoYIdProyInv(Long idLsEtnico, Long idProyInv) {

		BpProyInvEtnico bpProyInvEtnico = bpProyInvEtnicoServiceRepo.obtenerPorIdLsEtnicoYIdProyInv(idLsEtnico,
				idProyInv);
>>>>>>> developer
		return bpProyInvEtnico != null ? bpProyInvEtnicoMapper.bpProyInvEtnicoEntityToDTO(bpProyInvEtnico)
				: new BpProyInvEtnicoDTO();
	}
	
<<<<<<< HEAD
	public BpProyInvPoblacionDTO consultarBpProyInvPoblacionPorIdPoblacion(Long idPoblacion) {

		BpProyInvPoblacion bpProyInvPoblacion = bpProyInvPoblacionServiceRepo.obtener(idPoblacion);
		return bpProyInvPoblacion != null ? bpProyInvPoblacionMapper.bpProyInvPoblacionEntityToDTO(bpProyInvPoblacion)
				: new BpProyInvPoblacionDTO();
	}
	
	public BpProyectoInversionDTO consultarBpProyectoInversion(Long idProyectoInversion) {

		BpProyectoInversion bpProyectoInversion = bpProyectoInversionServiceRepo.obtener(idProyectoInversion);
		return bpProyectoInversion != null ? bpProyectoInversionMapper.bpProyectoInversionEntityToDTO(bpProyectoInversion)
				: new BpProyectoInversionDTO();
	}
	
	
	public BpProyInvEtnicoDTO consultarBpProyInvEtnicoPorId(Long idEtnico) {

		BpProyInvEtnico bpProyInvEtnico = bpProyInvEtnicoServiceRepo.obtener(idEtnico);
		return bpProyInvEtnico != null ? bpProyInvEtnicoMapper.bpProyInvEtnicoEntityToDTO(bpProyInvEtnico)
				: new BpProyInvEtnicoDTO();
	}
=======
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y politica publica filtrados por sus campos
	 * @param bpProyInvPoliticaDTO objeto de tipo BpIniciativaCiudadanaDTO que contiene la informacion para filtrar
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions {
		GenericoDTO respuesta = new GenericoDTO();
		if (bpProyInvPoliticaDTO.getTamanioPagina() == null || bpProyInvPoliticaDTO.getPagina() == null) {
			bpProyInvPoliticaDTO.setPagina(0);
			bpProyInvPoliticaDTO.setTamanioPagina(Integer.MAX_VALUE);
        }		
		
		Pageable pageRequest = PageRequest.of(bpProyInvPoliticaDTO.getPagina(), bpProyInvPoliticaDTO.getTamanioPagina());
		FiltroDTO filtro = bpProyInvPoliticaServiceRepo.obtenerTodosFiltrado(bpProyInvPoliticaDTO, pageRequest.getOffset(),	pageRequest.getPageSize());
		Page<BpProyInvPolitica> listaPage = new PageImpl<>((List<BpProyInvPolitica>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());

		respuesta.setLstObjectsDTO(
				new ArrayList<>(bpProyInvPoliticaMapper.bpProyInvPoliticaEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvPolitica por medio de la llave de unicidad entre el identificador de la politica
	 * y el identificador del proyecto de inversion
	 * @param idPolPub Long que representa el identificador de la politica publica porl a que se va filtrar
	 * @param idProyInversion Long que represneta el identiricador del proyecto de inversion por el cual se va filtrar
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorIdPolPubYIdProyInversion(Long idPolPub, Long idProyInversion) throws SpddExceptions
	{
		BpProyInvPolitica bpProyInvPolitica = bpProyInvPoliticaServiceRepo.obtenerPorIdPolPubYIdProyInversion(idPolPub, idProyInversion);
		
		return bpProyInvPolitica != null ? bpProyInvPoliticaMapper.bpProyInvPoliticaEntityToDTO(bpProyInvPolitica) : new BpProyInvPoliticaDTO();
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvPolitica por medio de su identificador
	 * @param idProyPol Long que representa el identificador del BpProyInvPolitica
	 * @return Un objeto de tipo BpProyInvPolitica que contiene la informacion correspondiente
	 */
	public BpProyInvPoliticaDTO consultarBpProyInvPoliticaPorId(Long idProyPol) throws SpddExceptions
	{
		return bpProyInvPoliticaMapper.bpProyInvPoliticaEntityToDTO(bpProyInvPoliticaServiceRepo.obtener(idProyPol));
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por medio de la unicidad entre el identificador de la linea de inversion y 
	 * el identificador del proyecto de inversion
	 * @param idLineaInversion Long que representa el identificador de la linea de inversion
	 * @param idProyecto Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	public BpProyInvLineaDTO consultarBpProyInvLineaPorIdLineaInversionYIdProyInversion(Long idLineaInversion, Long idProyecto) throws SpddExceptions {
		BpProyInvLinea bpProyInvLinea = bpProyInvLineaServiceRepo.obtenerPorIdLineaInversionYIdProyInversion(idLineaInversion, idProyecto);
		
		return bpProyInvLinea != null ? bpProyInvLineaMapper.bpProyInvLineaEntityToDTO(bpProyInvLinea) : new BpProyInvLineaDTO(); 
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvLinea por el identificador
	 * @param idProyLinea Long que representa el identificador de la relacion entre proyecto de inversion y la linea de inversion
	 * @return un objeto de tipo BpProyInvLinea con la informacion correspondiente
	 */
	public BpProyInvLineaDTO consultarBpProyInvLineaPorId(Long idProyLinea) throws SpddExceptions {
		return bpProyInvLineaMapper.bpProyInvLineaEntityToDTO(bpProyInvLineaServiceRepo.obtener(idProyLinea));
	}
	
	/**
	 * Metodo que permite obtener todas las relaciones entre proyecto de inversion y MRIndicador filtrados por sus campos
	 * @param BpProyInvPmrDTO objeto de tipo BpProyInvPmrDTO que contiene la informacion para filtrar
	 * @param inicio
	 * @param limite
	 * @return un GenericoDTO con toda la informacion filtrada
	 */
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO bpProyInvPmrDTO) throws SpddExceptions{
		GenericoDTO respuesta = new GenericoDTO();
		if (bpProyInvPmrDTO.getTamanioPagina() == null || bpProyInvPmrDTO.getPagina() == null) {
			bpProyInvPmrDTO.setPagina(0);
			bpProyInvPmrDTO.setTamanioPagina(Integer.MAX_VALUE);
        }		
		
		Pageable pageRequest = PageRequest.of(bpProyInvPmrDTO.getPagina(), bpProyInvPmrDTO.getTamanioPagina());
		FiltroDTO filtro = bpProyInvPmrServiceRepo.obtenerTodosFiltrado(bpProyInvPmrDTO, pageRequest.getOffset(),	pageRequest.getPageSize());
		Page<BpProyInvPmr> listaPage = new PageImpl<>((List<BpProyInvPmr>) filtro.getResultadoConsulta(),
				pageRequest, filtro.getContador());

		respuesta.setLstObjectsDTO(
				new ArrayList<>(bpProyInvPmrMapper.bpProyInvPmrEntitiesToDTO(listaPage.getContent())));
		respuesta.setTotal(listaPage.getTotalElements());
		return respuesta;
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvPmr por medio de los campos de unicidad de idIndMr e idProyInversion
	 * @param idIndMr Long que representa el identificador de MrIndicador
	 * @param idProyInversion Long que representa el identificador del proyecto de inversion
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	public BpProyInvPmrDTO consultarBpProyInvPmrPorIdIndMrYIdProyInversion(Long idIndMr, Long idProyInversion) throws SpddExceptions {
		BpProyInvPmr bpProyInvPmr = bpProyInvPmrServiceRepo.obtenerPorIdIndMrYIdProyInversion(idIndMr, idProyInversion);
		
		return bpProyInvPmr != null ? bpProyInvPmrMapper.bpProyInvPmrEntityToDTO(bpProyInvPmr) : new BpProyInvPmrDTO();
	}
	
	/**
	 * Metodo que permite obtener un BpProyInvPmr por medio del identificador
	 * @param idProyPmr Long que representa el identificador de BpProyInvPmr
	 * @return un objeto de tipo BpProyInvPmr con la informacion correspondiente.
	 */
	public BpProyInvPmrDTO consultarBpProyInvPmrPorId(Long idProyPmr) throws SpddExceptions {
		return bpProyInvPmrMapper.bpProyInvPmrEntityToDTO(bpProyInvPmrServiceRepo.obtener(idProyPmr));
	}

>>>>>>> developer

}
