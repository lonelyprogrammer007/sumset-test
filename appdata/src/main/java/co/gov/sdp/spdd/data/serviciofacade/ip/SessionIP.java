package co.gov.sdp.spdd.data.serviciofacade.ip;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.dao.interfaces.ip.ICompromisoEstrategicoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IHisVPddCompromisoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IObraConcretaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompetenciaAsociadaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompromisoEspecificoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddCompromisoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddIndicadorServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMRIndicadorServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaProductoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaResultadoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpIndicadorEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpIndicadorServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddNivelAtributoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddNivelServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPoliticaPublicaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbIndicadorServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbPoblacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddPrbValoracionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddProblematicaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddRangoPonderacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlNivelAtributoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlNivelServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotInstrumentoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotNivelServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotObraEntidadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotObraServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotRamaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMpAnualizarRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddRangoPonderacionRepo;
import co.gov.sdp.spdd.data.mapper.CompromisoEstrategicoMapper;
import co.gov.sdp.spdd.data.mapper.HisVPddCompromisoMapper;
import co.gov.sdp.spdd.data.mapper.PddCompetenciaAsociadaMapper;
import co.gov.sdp.spdd.data.mapper.PddCompromisoEspecificoMapper;
import co.gov.sdp.spdd.data.mapper.PddCompromisoMapper;
import co.gov.sdp.spdd.data.mapper.PddIndicadorMapper;
import co.gov.sdp.spdd.data.mapper.PddMRIndicadorMapper;
import co.gov.sdp.spdd.data.mapper.PddMapper;
import co.gov.sdp.spdd.data.mapper.PddMetaMapper;
import co.gov.sdp.spdd.data.mapper.PddMetaProductoMapper;
import co.gov.sdp.spdd.data.mapper.PddMetaResultadoMapper;
import co.gov.sdp.spdd.data.mapper.PddMpEntidadMapper;
import co.gov.sdp.spdd.data.mapper.PddMpIndicadorEntidadMapper;
import co.gov.sdp.spdd.data.mapper.PddMpIndicadorMapper;
import co.gov.sdp.spdd.data.mapper.PddNivelAtributoMapper;
import co.gov.sdp.spdd.data.mapper.PddNivelMapper;
import co.gov.sdp.spdd.data.mapper.PddObraConcretaMapper;
import co.gov.sdp.spdd.data.mapper.PddPoliticaPublicaMapper;
import co.gov.sdp.spdd.data.mapper.PddPrbIndicadorMapper;
import co.gov.sdp.spdd.data.mapper.PddPrbPoblacionMapper;
import co.gov.sdp.spdd.data.mapper.PddPrbValoracionMapper;
import co.gov.sdp.spdd.data.mapper.PddProblematicaMapper;
import co.gov.sdp.spdd.data.mapper.PddRangoPonderacionMapper;
import co.gov.sdp.spdd.data.mapper.PdlMapper;
import co.gov.sdp.spdd.data.mapper.PdlNivelAtributoMapper;
import co.gov.sdp.spdd.data.mapper.PdlNivelMapper;
import co.gov.sdp.spdd.data.mapper.PotInstrumentoMapper;
import co.gov.sdp.spdd.data.mapper.PotMapper;
import co.gov.sdp.spdd.data.mapper.PotNivelMapper;
import co.gov.sdp.spdd.data.mapper.PotObraEntidadMapper;
import co.gov.sdp.spdd.data.mapper.PotObraMapper;
import co.gov.sdp.spdd.data.mapper.PotRamaMapper;

/**
 * Clase principal de patron Facade del modulo IP, contiene las inyecciones de
 * los diferentes Repository y Mapper que se utilizan en este modulo
 * 
 * @author DANIEL
 * @version 1.0 24/02/2020
 */
@Component
public class SessionIP implements Serializable {

	/**
	 * 
	 */
	@Autowired
	SPDDLogger spddLogger;

	/*-------------------------------------------REPOS-------------------------------*/
	/**
	 * Inyeccion del Repo de la entidad CompromisoEstrategico
	 */
	@Autowired
	ICompromisoEstrategicoServiceRepo compromisoEstrategicoServiceRepo;

	/**
	 * Inyeccion del Repo de la entidad HisVPddCompromiso
	 */
	@Autowired
	IHisVPddCompromisoServiceRepo hisVPddCompromisoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddCompetenciaAsociada
	 */
	@Autowired
	IPddCompetenciaAsociadaServiceRepo pddCompetenciaAsociadaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddCompromisoEspecifico
	 */
	@Autowired
	IPddCompromisoEspecificoServiceRepo pddCompromisoEspecificoServiceRepo;

	/**
	 * Inyeccion de la repo de la entidad PddCompromiso
	 */
	@Autowired
	IPddCompromisoServiceRepo pddCompromisoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad HisVPddCompromiso
	 */
	@Autowired
	IPddMetaServiceRepo pddMetaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMetaResultado
	 */
	@Autowired
	IPddMetaResultadoServiceRepo pddMetaResultadoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdd
	 */
	@Autowired
	IPddServiceRepo pddServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddNivel
	 */
	@Autowired
	IPddNivelServiceRepo pddNivelServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddPrbValoracion
	 */
	@Autowired
	IPddPrbValoracionServiceRepo pddPrbValoracionServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddObrasConcretas
	 */
	@Autowired
	IObraConcretaServiceRepo pddObraConcretaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddProblematica
	 */
	@Autowired
	IPddProblematicaServiceRepo pddProblematicaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdl
	 */
	@Autowired
	IPdlServiceRepo pdlServiceRepo;

	@Autowired
	IPddNivelAtributoServiceRepo pddNivelAtributoServiceRepo;

	@Autowired
	IPotServiceRepo potServiceRepo;

	@Autowired
	IPotNivelServiceRepo potNivelServiceRepo;

	@Autowired
	IPotObraServiceRepo potObraServiceRepo;

	@Autowired
	IPotObraEntidadServiceRepo potObraEntidadServiceRepo;

	@Autowired
	IPotInstrumentoServiceRepo potInstrumentoServiceRepo;

	@Autowired
	IPdlNivelAtributoServiceRepo pdlNivelAtributoServiceRepo;

	/*-------------------------------------------MAPPERS-------------------------------*/

	/**
	 * Inyeccion del repo de la entidad pddPrbPoblacion
	 */
	@Autowired
	IPddPrbPoblacionServiceRepo poblacionServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddIndicador
	 */
	@Autowired
	IPddIndicadorServiceRepo pddIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddPrbIndicador
	 */
	@Autowired
	IPddPrbIndicadorServiceRepo pddPrbIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMpAnualizar
	 */
	@Autowired
	IPddMpAnualizarRepo pddMpAnualizarServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMpIndicador
	 */
	@Autowired
	IPddMpIndicadorServiceRepo pddMpIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMetaProducto
	 */
	@Autowired
	IPddMetaProductoServiceRepo pddMetaProductoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdlNivel
	 */
	@Autowired
	IPdlNivelServiceRepo pdlNivelServiceRepo;

	/**
	 * Inyeccion del mapper de la entidad CompromisoEstrategico
	 */
	@Autowired
	CompromisoEstrategicoMapper compromisoEstrategicoMapper;

	/**
	 * Inyeccion del mapper de la entidad hisVPddCompromiso
	 */
	@Autowired
	HisVPddCompromisoMapper hisVPddCompromisoMapper;

	/**
	 * Inyeccion del mapper de la entidad PddCompetenciaAsociada
	 */
	@Autowired
	PddCompetenciaAsociadaMapper pddCompetenciaAsociadaMapper;

	/**
	 * Inyeccion del mapper de la entidad PddCompromiso
	 */
	@Autowired
	PddCompromisoMapper pddCompromisoMapper;

	/**
	 * Inyeccion del mapper de la entidad pddCompromisoEspecifico
	 */
	@Autowired
	PddCompromisoEspecificoMapper pddCompromisoEspecifocMapper;

	/**
	 * Inyeccion del mapper de la entidad pdd
	 */
	@Autowired
	PddMapper pddMapper;

	/**
	 * Inyeccion del mapper de la entidad pddNivel
	 */
	@Autowired
	PddNivelMapper pddNivelMapper;

	/**
	 * Inyeccion del mapper de la entidad pddMeta
	 */
	@Autowired
	PddMetaMapper pddMetaMapper;

	/**
	 * Inyeccion del mapper de la entidad PddMetaRasultado
	 */
	@Autowired
	PddMetaResultadoMapper pddMetaResultadoMapper;

	/**
	 * Inyeccion del mapper de la entidad pddObraConcreta
	 */
	@Autowired
	PddObraConcretaMapper pddObraConcretamapper;

	/**
	 * Inyeccion del mapper de la entidad pddPrbValoracion
	 */
	@Autowired
	PddPrbValoracionMapper pddPrbValoracionMapper;

	/**
	 * Inyeccion del mapper de la entidad pddProblematica
	 */
	@Autowired
	PddProblematicaMapper pddProblematicaMapper;

	/**
	 * Inyeccion del mapper de la entidad pdl
	 */
	@Autowired
	PdlMapper pdlMapper;

	/**
	 * Inyeccion del mapper de la entidad pdlNivel
	 */
	@Autowired
	PdlNivelMapper pdlNivelMapper;

	/**
	 * Inyeccion del mapper de la entidad poblacion
	 */
	@Autowired
	PddPrbPoblacionMapper poblacionMapper;

	/**
	 * Inyeccion del mapper de la entidad pddPrbIndicador
	 */
	@Autowired
	PddPrbIndicadorMapper pddPrbIndicadorMapper;

	/**
	 * Inyeccion del mapper de la entidad pddIndicador
	 */
	@Autowired
	PddIndicadorMapper pddIndicadorMapper;

	@Autowired
	PotMapper potMapper;

	@Autowired
	PddMpIndicadorMapper pddMpIndicadorMapper;

	@Autowired
	PotRamaMapper potRamaMapper;

	@Autowired
	IPotRamaServiceRepo potRamaServiceRepo;

	@Autowired
	PddNivelAtributoMapper pddNivelAtributoMapper;

	@Autowired
	PotObraMapper potObraMapper;

	@Autowired
	PddMRIndicadorMapper pddMRIndicadorMapper;

	@Autowired
	IPddMRIndicadorServiceRepo pddMRIndicadorServiceRepo;

	@Autowired
	PddMetaProductoMapper pddMetaProductoMapper;

	@Autowired
	PotNivelMapper potNivelMapper;

	@Autowired
	PotObraEntidadMapper potObraEntidadMapper;

	@Autowired
	PotInstrumentoMapper potInstrumentoMapper;

	@Autowired
	PdlNivelAtributoMapper pdlNivelAtributoMapper;

	@Autowired
	PddMpEntidadMapper pddMpEntidadMapper;

	@Autowired
	IPddMpEntidadServiceRepo pddMpEntidadServiceRepo;
	
	@Autowired
	PddRangoPonderacionMapper pddRangoPonderacionMapper;
	
	@Autowired
	IPddRangoPonderacionServiceRepo pddRangoPonderacionServiceRepo;

	@Autowired
	IPddMpIndicadorEntidadServiceRepo pddMpIndicadorEntidadServiceRepo;

	@Autowired
	PddMpIndicadorEntidadMapper pddMpIndicadorEntidadMapper;
	
	@Autowired
	IPddPoliticaPublicaServiceRepo pddPoliticaPublicaServiceRepo;
	
	@Autowired
	PddPoliticaPublicaMapper pddPoliticaPublicaMapper;
	

}
