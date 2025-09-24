package co.gov.sdp.spdd.data.serviciofacade.bp;

import java.io.Serializable;

import org.springframework.stereotype.Component;

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
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLocalizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvMetaPlanDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvProductoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

/**
 * Clase que representa el Facade para el servicio Registrar del modulo BP
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Component
public class SessionRegistroBP extends SessionBP implements Serializable {

	/**
	 * Metodo que permite guardar la informacion basica de un proyecto de inversion.
	 * Este guardar corresponde a la informacion que existe en el TAB-Identificacion
	 * del proyecto.
	 * 
	 * @param bpProyectoInversionDTO objeto de tipo BpProyectoInversionDTO que
	 *                               contiene la informacion para guardar
	 * @return un objeto de tipo BpProyectoInversionDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public BpProyectoInversionDTO guardarProyectoInversionTABIndentificacionProyecto(
			BpProyectoInversionDTO bpProyectoInversionDTO) throws SpddExceptions {

		return bpProyectoInversionMapper.bpProyectoInversionEntityToDTO(bpProyectoInversionServiceRepo
				.guardar(bpProyectoInversionMapper.bpProyectoInversionDTOToEntity(bpProyectoInversionDTO)));
	}

	/**
	 * Metodo que permite guardar la informacion basica de un tipo de proyecto de
	 * inversion. Este guardar corresponde a la informacion que existe en el
	 * TAB-Identificacion del proyecto.
	 * 
	 * @param bpProyInvTipoDTO objeto de tipo BpProyInvTipoDTO que contiene la
	 *                         informacion para guardar
	 * @return un objeto de tipo BpProyInvTipoDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyInvTipoDTO guardarProyInvTipo(BpProyInvTipoDTO bpProyInvTipoDTO) throws SpddExceptions {
		return bpProyInvTipoMapper.bpProyInvTipoEntityToDTO(
				bpProyInvTipoServiceRepo.guardar(bpProyInvTipoMapper.bpProyInvTipoDTOToEntity(bpProyInvTipoDTO)));
	}

	/**
	 * Metodo que agrega un grupo etario segun la iniciativa ciudadana
	 * 
	 * @param peticion un dto con los datos a almacenar
	 * @return el dto con un id generado por la BD
	 * @throws SpddExceptions
	 */
	public BpIniciativaEtarioDTO guardarGrupoEtario(BpIniciativaEtarioDTO peticion) throws SpddExceptions {
		return bpIniciativaEtarioMapper.etarioEntityToDTO(
				bpIniciativaEtarioServiceRepo.guardar(bpIniciativaEtarioMapper.etarioDTOToEntity(peticion)));
	}

	/**
	 * Metodo que agrega una ubicacion segun la iniciativa ciudadana
	 * 
	 * @param peticion un dto con los datos a almacenar
	 * @return el dto con un id generado por la BD
	 * @throws SpddExceptions
	 */
	public BpIniciativaUbicacionDTO guardarUbicacion(BpIniciativaUbicacionDTO peticion) throws SpddExceptions {
		return bpIniciativaUbicacionMapper.iniciativaUbicacionEntityToDTO(bpIniciativaUbicacionServiceRepo
				.guardar(bpIniciativaUbicacionMapper.iniciativaUbicacionDTOToEntity(peticion)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpIniciativaCondicionDTO guardarCondicionIniciativa(BpIniciativaCondicionDTO peticion)
			throws SpddExceptions {
		return bpIniciativaCondicionMapper.iniciativaCondicionEntityToDTO(bpIniciativaCondicionServiceRepo
				.guardar(bpIniciativaCondicionMapper.iniciativaCondicionDTOToEntity(peticion)));
	}

	/**
	 * Metodo que almacena una iniciativa ciudadana
	 * 
	 * @param peticion
	 * @return una iniciativa ciudadana con un id generado por la BD
	 * @throws SpddExceptions
	 */
	public BpIniciativaCiudadanaDTO guardarIniciativaCiudadana(BpIniciativaCiudadanaDTO peticion)
			throws SpddExceptions {
		return iniciativaCiudadanaMapper.bpIniciativaCiudadanaEntityToDTO(iniciativaCiudadanaServiceRepo
				.guardar(iniciativaCiudadanaMapper.bpIniciativaCiudadanaDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar la informacion de un BpProyInvAporte la cual
	 * indica la realacion entre un BpProyectoInversion y BpAporteCiudadano
	 * 
	 * @param bpProyInvAporte Objeto de tipo BpProyInvAporteDTO que contiene la
	 *                        informacion para guardar
	 * @return un objeto de tipo BpProyInvAporteDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public BpProyInvAporteDTO guardarBpProyInvAporte(BpProyInvAporteDTO bpProyInvAporteDTO) throws SpddExceptions {
		return bpProyInvAporteMapper.bpProyInvAporteEntityToDTO(bpProyInvAporteServiceRepo
				.guardar(bpProyInvAporteMapper.bpProyInvAporteDTOToEntity(bpProyInvAporteDTO)));
	}

	/**
	 * Metodo que permite guardar la informacion de un BpAporteCiudadano en BD
	 * 
	 * @param bpAporteCiudadanoDTO objeto de tipo BpAporteCiudadanoDTO con la
	 *                             informacion para guardar en BD
	 * @return un objeto de tipo BpAporteCiudadanoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpAporteCiudadanoDTO guardarBPAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO)
			throws SpddExceptions {
		return bpAporteCiudadanoMapper.bpAporteCiudadanoEntityToDTO(bpAporteCiudadanoServiceRepo
				.guardar(bpAporteCiudadanoMapper.bpAporteCiudadanoDTOToEntity(bpAporteCiudadanoDTO)));
	}

	/**
	 * Metodo que permite guardar la informacion de un BpProyInvIniciativa en BD
	 * 
	 * @param bpProyInvIniciativaDTO objeto de tipo BpProyInvIniciativaDTO con la
	 *                               informacin para guardar en BD
	 * @return un objeto de tipo BpProyInvIniciativaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvIniciativaDTO guardarBpProyInvIniciativa(BpProyInvIniciativaDTO bpProyInvIniciativaDTO)
			throws SpddExceptions {
		return bpProyInvIniciativaMapper.bpProyInvIniciativaEntityToDTO(bpProyInvIniciativaServiceRepo
				.guardar(bpProyInvIniciativaMapper.bpProyInvIniciativaDTOToEntity(bpProyInvIniciativaDTO)));
	}

	/**
	 * Metodo que permite guardar la informacion de un BpProyInvLocaliza en BD
	 * 
	 * @param BpProyInvLocalizaDTO objeto de tipo BpProyInvLocalizaDTO con la
	 *                             informacion para guardar en BD
	 * @return un objeto de tipo BpProyInvLocalizaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLocalizaDTO guardarBPProyectoInvLocaliza(BpProyInvLocalizaDTO BpProyInvLocalizaDTO)
			throws SpddExceptions {
		return bpProyInvLocalizaMapper.BpProyInvLocalizaEntityToDTO(bpProyInvLocalizaServiceRepo
				.guardar(bpProyInvLocalizaMapper.BpProyInvLocalizaDTOToEntity(BpProyInvLocalizaDTO)));
	}

	/**
	 * Metodo que permite guardar una BpProyInvEspecif en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvEspecifDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvEspecifDTO guardarBpProyInvEspecif(BpProyInvEspecifDTO peticion) throws SpddExceptions {
		return bpProyInvEspecifMapper.bpProyInvEspecifEntityToDTO(
				bpProyInvEspecifServiceRepo.guardar(bpProyInvEspecifMapper.bpProyInvEspecifDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar una BpProyInvMetaPlan en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvMetaPlanDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvEspecifDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvMetaPlanDTO guardarBpProyInvMetaPlan(BpProyInvMetaPlanDTO peticion) throws SpddExceptions {
		return bpProyInvMetaPlanMapper.bpProyInvMetaPlanEntityToDTO(
				bpProyInvMetaPlanServiceRepo.guardar(bpProyInvMetaPlanMapper.bpProyInvMetaPlanDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar una BpProyInvProducto en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvProductoDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvProductoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvProductoDTO guardarBpProyInvProducto(BpProyInvProductoDTO peticion) throws SpddExceptions {
		return bpProyInvProductoMapper.bpProyInvProductoEntityToDTO(
				bpProyInvProductoServiceRepo.guardar(bpProyInvProductoMapper.bpProyInvProductoDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar una BpProyInvActividad en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvActividadDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvActividadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvActividadDTO guardarBpProyInvActividad(BpProyInvActividadDTO peticion) throws SpddExceptions {
		return bpProyInvActividadMapper.bpProyInvActividadEntityToDTO(bpProyInvActividadServiceRepo
				.guardar(bpProyInvActividadMapper.bpProyInvActividadDTOToEntity(peticion)));

	}

	/**
	 * Metodo que permite guardar un BpProyInvFinancia en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvFinanciaDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvFinanciaDTO con la informacion a guardar
	 * @throws SpddExceptions
	 */
	public BpProyInvFinanciaDTO registrarBpProyInvFinancia(BpProyInvFinanciaDTO peticion) throws SpddExceptions {
		return bpProyInvFinanciaMapper.bpProyInvFinanciaEntityToDTO(
				bpProyInvFinanciaServiceRepo.guardar(bpProyInvFinanciaMapper.bpProyInvFinanciaDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar un BpProyInvAnualiza en BD
	 * 
	 * @param peticion objeto de tipo BpProyInvAnualizaDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo BpProyInvAnualizaDTO con la informacion a guardar
	 * @throws SpddExceptions
	 */
	public BpProyInvAnualizaDTO registrarBpProyInvAnualiza(BpProyInvAnualizaDTO peticion) throws SpddExceptions {
		return bpProyInvAnualizaMapper.bpProyInvAnualizEntityToDTO(
				bpProyInvAnualizaServiceRepo.guardar(bpProyInvAnualizaMapper.bpProyInvAnualizaDTOToEntity(peticion)));

	}

		
	/**
	 * Metodo que permite guardar un BpProyInvPoblacion en la Bd
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvPoblacionDTO guardarBpProyInvPoblacion(BpProyInvPoblacionDTO peticion) throws SpddExceptions{
		return bpProyInvPoblacionMapper.bpProyInvPoblacionEntityToDTO(bpProyInvPoblacionServiceRepo.guardar(bpProyInvPoblacionMapper.bpProyInvPoblacionDTOToEntity(peticion)));
	}
	
	/**
	 * Metodo que permite guardar un BpProyInvEtnico en la Bd
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public BpProyInvEtnicoDTO guardarBpProyInvEtnico(BpProyInvEtnicoDTO peticion) throws SpddExceptions{
		return bpProyInvEtnicoMapper.bpProyInvEtnicoEntityToDTO(bpProyInvEtnicoServiceRepo.guardar(bpProyInvEtnicoMapper.bpProyInvEtnicoDTOToEntity(peticion)));
	}
	
	/**
	 * Metodo que permite guardar un BpProyInvPolitica en la BD
	 * @param bpProyInvPoliticaDTO objeto de tipo BpProyInvPoliticaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPoliticaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPoliticaDTO guardarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO) throws SpddExceptions{
		return bpProyInvPoliticaMapper.bpProyInvPoliticaEntityToDTO(bpProyInvPoliticaServiceRepo.guardar(bpProyInvPoliticaMapper.bpProyInvPoliticaDTOToEntity(bpProyInvPoliticaDTO)));
	}
	
	/**
	 * Metodo que permite guardar un BpProyInvLinea en la BD
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo bpProyInvLineaDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvLineaDTO guardarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO) throws SpddExceptions {
		return bpProyInvLineaMapper.bpProyInvLineaEntityToDTO(bpProyInvLineaServiceRepo.guardar(bpProyInvLineaMapper.bpProyInvLineaDTOToEntity(bpProyInvLineaDTO)));
	}
	
	/**
	 * Metodo que permite guardar un BpProyInvPrm en la BD
	 * @param bpProyInvLineaDTO objeto de tipo BpProyInvLineaDTO con la informacion a guardar
	 * @return un objeto de tipo BpProyInvPmrDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public BpProyInvPmrDTO guardarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPrmDTO) throws SpddExceptions {
		return bpProyInvPmrMapper.bpProyInvPmrEntityToDTO(bpProyInvPmrServiceRepo.guardar(bpProyInvPmrMapper.bpProyInvPmrDTOToEntity(bpProyInvPrmDTO)));
	}
}
