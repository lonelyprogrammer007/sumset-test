package co.gov.sdp.spdd.data.serviciofacade.ip;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEspecifDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
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

import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddRangoPonderacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;

import co.gov.sdp.nhspdd.common.dto.ip.PotObraEntidadDTO;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;

import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.model.ip.PddCompromiso;
import co.gov.sdp.spdd.data.model.ip.PddPrbValoracion;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;

/**
 * Clase que representa el Facade para el servicio Registrar del modulo IP
 * 
 * @author DANIEL, SEBASTIAN
 * @version 1.0 24/01/2020
 */
@Component
public class SessionRegistroIP extends SessionIP implements Serializable {

	/**
	 * Metodo que permite guardar un compromisoEstrategico en BD
	 * 
	 * @param compromisoEstrategicoDTO objeto de tipo CompromisoEstrategicoDTO que
	 *                                 contiene la informacion para guardar
	 * @return un objeto de tipo CompromisoEstrategicoDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public CompromisoEstrategicoDTO guardarCompromisoEstrategico(CompromisoEstrategicoDTO compromisoEstrategicoDTO) {

		return compromisoEstrategicoMapper.compromisoEstrategicoEntityToDTO(compromisoEstrategicoServiceRepo
				.guardar(compromisoEstrategicoMapper.compromisoEstrategicoDTOToEntity(compromisoEstrategicoDTO)));
	}

	/**
	 * Metodo que permite guardar un pdd en BD
	 * 
	 * @param pddDTO objeto de tipo PddDTO que contiene la informacion para guardar
	 * @return un objeto de tipo PddDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddDTO guardarPdd(PddDTO pddDTO) throws SpddExceptions {

		return pddMapper.pddEntityToDTO(pddServiceRepo.guardar(pddMapper.pddDTOToEntity(pddDTO)));
	}

	/**
	 * Metodo que permite guarda un pdd nivel en BD
	 * 
	 * @param pddNivelDTO objeto de tipo PddNivelDTO que contiene la informacion
	 *                    para guardar
	 * @return n objeto de tipo PddNivelDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddNivelDTO guardarPddNivel(PddNivelDTO pddNivelDTO) throws SpddExceptions {
		return pddNivelMapper
				.pddNivelEntityToDTO(pddNivelServiceRepo.guardar(pddNivelMapper.pddNivelDTOToEntity(pddNivelDTO)));
	}

	/**
	 * Metodo que permite guardar un PddCompetenciaAsociada en BD
	 * 
	 * @param pddCompetenciaDTO objeto de tipo PddCompetenciaAsociadaDTO que
	 *                          contiene la informacion para guardar
	 * @return un objeto de tipo PddCompetenciaAsociadaDTO con la informacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public PddCompetenciaAsociadaDTO guardarPddCompetenciaAsociada(PddCompetenciaAsociadaDTO pddCompetenciaDTO)
			throws SpddExceptions {
		return pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaEntityToDTO(pddCompetenciaAsociadaServiceRepo
				.guardar(pddCompetenciaAsociadaMapper.pddCompetenciaAsociadaDTOToEntity(pddCompetenciaDTO)));
	}

	/**
	 * Metodo que permite guardar un pddCompromisoEspecifico en BD
	 * 
	 * @param pddCompromisoEspecificoDTO objeto de tipo PddCompromisoEspecificoDTO
	 *                                   que contiene la informacion para guardar
	 * @return un objeto de tipo PddCompromisoEspecificoDTO con la informacion que
	 *         se guardo
	 * @throws SpddExceptions
	 */
	public PddCompromisoEspecificoDTO guardarPddCompromisoEspecifico(
			PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO) throws SpddExceptions {
		return pddCompromisoEspecifocMapper.pddCompromisoEspecificoEntityToDTO(pddCompromisoEspecificoServiceRepo
				.guardar(pddCompromisoEspecifocMapper.pddCompromisoEspecificoDTOToEntity(pddCompromisoEspecificoDTO)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaDTO guardarPddMeta(PddMetaDTO peticion) throws SpddExceptions {
		return pddMetaMapper.pddMetaEntityToDTO(pddMetaServiceRepo.guardar(pddMetaMapper.pddMetaDTOToEntity(peticion)));

	}

	/**
	 * Metodo que permite guarda una PddMetaResultado en BD
	 * 
	 * @param peticion objeto de tipo PddMetaResultadoDTO que contiene la
	 *                 inforamcion para guardar
	 * @return un objeto de tipo PddMetaResultadoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddMetaResultadoDTO guardarPddMetaResultado(PddMetaResultadoDTO peticion) throws SpddExceptions {
		return pddMetaResultadoMapper.pddMetaResultadoEntityToDTO(
				pddMetaResultadoServiceRepo.guardar(pddMetaResultadoMapper.pddMetaResultadoDTOToEntity(peticion)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 */
	public PddPrbPoblacionDTO guardarPrbPoblacion(PddPrbPoblacionDTO peticion) {
		return poblacionMapper
				.poblacionEntityToDTO(poblacionServiceRepo.guardar(poblacionMapper.poblacionDTOToEntity(peticion)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddObraConcretaDTO guardarObraConcreta(PddObraConcretaDTO peticion) throws SpddExceptions {
		return pddObraConcretamapper.pddObraConcretaEntityToDTO(
				pddObraConcretaServiceRepo.guardar(pddObraConcretamapper.pddObraConcretaDTOToEntity(peticion)));

	}

	/**
	 * Metodo que permite guardar un PddCompromiso en BD
	 * 
	 * @param pddCompromisoDTO objeto de tipo PddCompromisoDTO que contiene la
	 *                         informacion para guardar
	 * @return un objeto de tipo PddCompromisoDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PddCompromisoDTO guardadPddCompromiso(PddCompromisoDTO pddCompromisoDTO) throws SpddExceptions {
		PddCompromiso pddCompromiso = pddCompromisoMapper.pddCompromisoDTOToEntity(pddCompromisoDTO);
		return pddCompromisoMapper.pddCompromisoEntityToDTO(pddCompromisoServiceRepo.guardar(pddCompromiso));
	}

	/**
	 * Método que permite guardar un PDDProblematica en DB.
	 * 
	 * @param pddProblematicaDTO Objeto de tipo PddProblematica con la información a
	 *                           guardar.
	 * @return un objeto de tipo PddProblematicaDTO con la información que se
	 *         guardo.
	 * @throws SpddExceptions
	 */
	public PddProblematicaDTO guardarPddProblematica(PddProblematicaDTO pddProblematicaDTO) throws SpddExceptions {
		PddProblematica pddProblematica = pddProblematicaMapper.pddProblematicaDTOToEntity(pddProblematicaDTO);
		return pddProblematicaMapper.pddProblematicaEntityToDTO(pddProblematicaServiceRepo.guardar(pddProblematica));
	}

	/**
	 * Metodo que permite guardar un PddPrbValoracion en BD
	 * 
	 * @param pddPrbValoracion objeto de tipo PddPrbValoracionDTO que contiene la
	 *                         informacion para guardar.
	 * @return un objeto de tipo PddPrbValoracionDTO con la indormacion que se
	 *         guardo
	 * @throws SpddExceptions
	 */
	public PddPrbValoracionDTO guardarPddPrbValoracion(PddPrbValoracionDTO pddPrbValoracionDTO) throws SpddExceptions {
		PddPrbValoracion pddPrbValoracion = pddPrbValoracionMapper.pddPrbValoracionDTOToEntity(pddPrbValoracionDTO);
		PddPrbValoracion pddPrbValoracionGuardadaa = pddPrbValoracionServiceRepo.guardar(pddPrbValoracion);
		return pddPrbValoracionMapper.pddPrbValoracionEntityToDTO(pddPrbValoracionGuardadaa);
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddPrbIndicadorDTO guardarPddPrbIndicador(PddPrbIndicadorDTO peticion) throws SpddExceptions {
		return pddPrbIndicadorMapper.prbIndicadorEntityToDTO(
				pddPrbIndicadorServiceRepo.guardar(pddPrbIndicadorMapper.prbIndicadorDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar un PddIndicador en BD
	 * 
	 * @param peticion objeto de tipo PddIndicadorDTO que contiene la informacion
	 *                 para guardar
	 * @return un objeto de tipo PddIndicadorDTO con la informacion que se guardo.
	 * @throws SpddExceptions
	 */
	public PddIndicadorDTO guardarPddIndicador(PddIndicadorDTO peticion) throws SpddExceptions {
		return pddIndicadorMapper.pddIndicadorEntityToDTO(
				pddIndicadorServiceRepo.guardar(pddIndicadorMapper.pddIndicadorDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar un pot en la bd
	 * 
	 * @param peticion objeto de tipo PotDTO que contiene la información paraguardar
	 * @return un objeto de tipo PotDTO con la información que se guardo
	 */
	public PotDTO guardarPot(PotDTO peticion) {
		return potMapper.potEntityToDTO(potServiceRepo.guardar(potMapper.potDTOToEntity(peticion)));
	}

	/**
	 * metodo que permite guarda una rama del pot en la bd
	 * 
	 * @param peticion objeto de tipo PotRamaDTO que contiene la información para
	 *                 guardar
	 * @return un objeto de tipo PotRamaDTO con la información que se guardo
	 * @throws SpddExceptions
	 */
	public PotRamaDTO guardarRamaPot(PotRamaDTO peticion) throws SpddExceptions {
		return potRamaMapper.potRamaEntityToDTO(potRamaServiceRepo.guardar(potRamaMapper.potRamaDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar un pdl en BD
	 * 
	 * @param pdlDTO objeto de tipo PdlDTO que contiene la informacion para guardar
	 * @return un objeto de tipo PdlDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PdlDTO guardarPdl(PdlDTO pdlDTO) throws SpddExceptions {
		return pdlMapper.pdlEntityToDTO(pdlServiceRepo.guardar(pdlMapper.pdlDTOToEntity(pdlDTO)));
	}

	/**
	 * Metodo que permite guarda un pdl nivel en BD
	 * 
	 * @param pdlNivelDTO objeto de tipo PdlNivelDTO que contiene la informacion
	 *                    para guardar
	 * @return un objeto de tipo PdlNivelDTO con la informacion que se guardo
	 * @throws SpddExceptions
	 */
	public PdlNivelDTO guardarPdlNivel(PdlNivelDTO pdlNivelDTO) throws SpddExceptions {
		return pdlNivelMapper
				.pdlNivelEntityToDTO(pdlNivelServiceRepo.guardar(pdlNivelMapper.pdlNivelDTOToEntity(pdlNivelDTO)));
	}

	/**
	 * Metodo que permite guardar un PddNivelAtributo en BD
	 * 
	 * @param pddNivelAtributoDTO objeto de tipo PddNivelAtributoDTO que contiene la
	 *                            informacion a guardar
	 * @return un objeto de tipo PddNivelAtributoDTO con la informacion que se
	 *         guardo
	 */
	public PddNivelAtributoDTO guardarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO) {
		return pddNivelAtributoMapper.pddNivelAtributoEntityToDTO(pddNivelAtributoServiceRepo
				.guardar(pddNivelAtributoMapper.pddNivelAtributoDTOToEntity(pddNivelAtributoDTO)));

	}

	/**
	 * Metodo que guardar un meta indicador
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMRIndicadorDTO guardarIndicadorMetaResultado(PddMRIndicadorDTO peticion) throws SpddExceptions {
		return pddMRIndicadorMapper.mRIndicadorEntityToDTO(
				pddMRIndicadorServiceRepo.guardar(pddMRIndicadorMapper.mRIndicadorDTOToEntity(peticion)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMetaProductoDTO guardarMetaProducto(PddMetaProductoDTO peticion) throws SpddExceptions {
		return pddMetaProductoMapper.metaProductoEntityToDTO(
				pddMetaProductoServiceRepo.guardar(pddMetaProductoMapper.metaProductoDTOToEntity(peticion)));
	}

	/**
	 * metodo que permite guardar un nivel de un pot
	 * 
	 * @param peticion objeto de tipo NivelPotDTO con la información necesaria para
	 *                 guardar un nivel
	 * @return objeto de tipo NivelPotDTo con la información que se registro
	 * @throws SpddExceptions
	 */
	public PotNivelDTO guardarNivelDTO(PotNivelDTO peticion) throws SpddExceptions {
		return potNivelMapper
				.PotNivelEntityToDTO(potNivelServiceRepo.guardar(potNivelMapper.PotNivelDTOToEntity(peticion)));

	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorDTO guardarIndicadorMetaProducto(PddMpIndicadorDTO peticion) throws SpddExceptions {

		return pddMpIndicadorMapper.mpIndicadorEntityToDTO(
				pddMpIndicadorServiceRepo.guardar(pddMpIndicadorMapper.mpIndicadorDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar una PotObra en BD
	 * 
	 * @param potObraDTO objeto de tipo PotObraDTO con la informacion a guardar
	 * @return un objeto de tipo PotObraDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraDTO guardarPotObra(PotObraDTO potObraDTO) throws SpddExceptions {
		return potObraMapper
				.potObraEntityToDTO(potObraServiceRepo.guardar(potObraMapper.potObraDTOToEntity(potObraDTO)));
	}

	/**
	 * Metodo que permite guardar una PotObraEntidad en BD
	 * 
	 * @param potObraEntidadDTO objetio de tipo PotObraEntidadDTO con la informacion
	 *                          a guardar.
	 * @return un objeto de tipo PotOBraEntidadDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotObraEntidadDTO guardarPotObraEntidad(PotObraEntidadDTO potObraEntidadDTO) throws SpddExceptions {
		return potObraEntidadMapper.potObraEntidadEntityToDTO(
				potObraEntidadServiceRepo.guardar(potObraEntidadMapper.potObraEntidadDTOToEntity(potObraEntidadDTO)));
	}

	/**
	 * Metodo que permite guardar una potInstrumento en BD
	 * 
	 * @param potInstrumentoDTO objeto de tipo PotInstrumentoDTO con la informacion
	 *                          a guardar.
	 * @return un objeto de tipo PotInstrumentoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PotInstrumentoDTO guardarPotInstrumento(PotInstrumentoDTO potInstrumentoDTO) throws SpddExceptions {
		return potInstrumentoMapper.potInstrumentoEntityToDTO(
				potInstrumentoServiceRepo.guardar(potInstrumentoMapper.potInstrumentoDTOToEntity(potInstrumentoDTO)));
	}

	/**
	 * Metodo que permite guardar una PdlNivelAtributo en BD
	 * 
	 * @param potObraDTO objeto de tipo PdlNivelAtributoDTO con la informacion a
	 *                   guardar
	 * @return un objeto de tipo PdlNivelAtributoDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PdlNivelAtributoDTO guardarPdlNivelAtributo(PdlNivelAtributoDTO pdlNivelAtributoDTO) throws SpddExceptions {
		return pdlNivelAtributoMapper.pdlNivelAtributoEntityToDTO(pdlNivelAtributoServiceRepo
				.guardar(pdlNivelAtributoMapper.pdlNivelAtributoDTOToEntity(pdlNivelAtributoDTO)));

	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpEntidadDTO guardarMpEntidad(PddMpEntidadDTO peticion) throws SpddExceptions {
		return pddMpEntidadMapper.mPEntidadEntityToDTO(
				pddMpEntidadServiceRepo.guardar(pddMpEntidadMapper.mPEntidadDTOToEntity(peticion)));
	}

	/**
	 * 
	 * @param peticion
	 * @return
	 * @throws SpddExceptions
	 */
	public PddMpIndicadorEntidadDTO registrarIndicadorEntidadMetaProducto(PddMpIndicadorEntidadDTO peticion)
			throws SpddExceptions {
		return pddMpIndicadorEntidadMapper.indicadorEntidadEntityToDTO(pddMpIndicadorEntidadServiceRepo
				.guardar(pddMpIndicadorEntidadMapper.indicadorEntidadDTOToEntity(peticion)));
	}

	/**
	 * Metodo que permite guardar una PddRangoPonderacion en BD
	 * 
	 * @param peticion objeto de tipo PddRangoPonderacionDTO con la informacion a
	 *                 guardar
	 * @return un objeto de tipo PddRangoPonderacionDTO con la informacion guardada
	 * @throws SpddExceptions
	 */
	public PddRangoPonderacionDTO guardarPddRangoPonderacion(PddRangoPonderacionDTO peticion) throws SpddExceptions {
		return pddRangoPonderacionMapper.ppdRangoPonderacionEntityToDTO(pddRangoPonderacionServiceRepo
				.guardar(pddRangoPonderacionMapper.ppdRangoPonderacionDTOToEntity(peticion)));
	}
	
	public PddProblematicaDTO modificarPddProblematica(PddProblematicaDTO pddProblematicaDTO) throws SpddExceptions{
		return pddProblematicaMapper.pddProblematicaEntityToDTO(pddProblematicaServiceRepo.guardar(pddProblematicaMapper.pddProblematicaDTOToEntity(pddProblematicaDTO)));
	}
}
