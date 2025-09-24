package co.gov.sdp.spdd.data.serviciofacade.ip;

import org.springframework.boot.test.mock.mockito.MockBean;

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

public class SessionIPTest {
	
	/**
	 * 
	 */
	@MockBean
	SPDDLogger spddLogger;

	/*-------------------------------------------REPOS-------------------------------*/
	/**
	 * Inyeccion del Repo de la entidad CompromisoEstrategico
	 */
	@MockBean
	ICompromisoEstrategicoServiceRepo compromisoEstrategicoServiceRepo;

	/**
	 * Inyeccion del Repo de la entidad HisVPddCompromiso
	 */
	@MockBean
	IHisVPddCompromisoServiceRepo hisVPddCompromisoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddCompetenciaAsociada
	 */
	@MockBean
	IPddCompetenciaAsociadaServiceRepo pddCompetenciaAsociadaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddCompromisoEspecifico
	 */
	@MockBean
	IPddCompromisoEspecificoServiceRepo pddCompromisoEspecificoServiceRepo;

	/**
	 * Inyeccion de la repo de la entidad PddCompromiso
	 */
	@MockBean
	IPddCompromisoServiceRepo pddCompromisoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad HisVPddCompromiso
	 */
	@MockBean
	IPddMetaServiceRepo pddMetaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMetaResultado
	 */
	@MockBean
	IPddMetaResultadoServiceRepo pddMetaResultadoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdd
	 */
	@MockBean
	IPddServiceRepo pddServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddNivel
	 */
	@MockBean
	IPddNivelServiceRepo pddNivelServiceRepo;

	/**
	 * Inyeccion del repo de la entidad PddPrbValoracion
	 */
	@MockBean
	IPddPrbValoracionServiceRepo pddPrbValoracionServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddObrasConcretas
	 */
	@MockBean
	IObraConcretaServiceRepo pddObraConcretaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddProblematica
	 */
	@MockBean
	IPddProblematicaServiceRepo pddProblematicaServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdl
	 */
	@MockBean
	IPdlServiceRepo pdlServiceRepo;

	@MockBean
	IPddNivelAtributoServiceRepo pddNivelAtributoServiceRepo;

	@MockBean
	IPotServiceRepo potServiceRepo;

	@MockBean
	IPotNivelServiceRepo potNivelServiceRepo;

	@MockBean
	IPotObraServiceRepo potObraServiceRepo;

	@MockBean
	IPotObraEntidadServiceRepo potObraEntidadServiceRepo;

	@MockBean
	IPotInstrumentoServiceRepo potInstrumentoServiceRepo;

	@MockBean
	IPdlNivelAtributoServiceRepo pdlNivelAtributoServiceRepo;

	/*-------------------------------------------MAPPERS-------------------------------*/

	/**
	 * Inyeccion del repo de la entidad pddPrbPoblacion
	 */
	@MockBean
	IPddPrbPoblacionServiceRepo poblacionServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddIndicador
	 */
	@MockBean
	IPddIndicadorServiceRepo pddIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddPrbIndicador
	 */
	@MockBean
	IPddPrbIndicadorServiceRepo pddPrbIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMpAnualizar
	 */
	@MockBean
	IPddMpAnualizarRepo pddMpAnualizarServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMpIndicador
	 */
	@MockBean
	IPddMpIndicadorServiceRepo pddMpIndicadorServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pddMetaProducto
	 */
	@MockBean
	IPddMetaProductoServiceRepo pddMetaProductoServiceRepo;

	/**
	 * Inyeccion del repo de la entidad pdlNivel
	 */
	@MockBean
	IPdlNivelServiceRepo pdlNivelServiceRepo;

	/**
	 * Inyeccion del mapper de la entidad CompromisoEstrategico
	 */
	@MockBean
	CompromisoEstrategicoMapper compromisoEstrategicoMapper;

	/**
	 * Inyeccion del mapper de la entidad hisVPddCompromiso
	 */
	@MockBean
	HisVPddCompromisoMapper hisVPddCompromisoMapper;

	/**
	 * Inyeccion del mapper de la entidad PddCompetenciaAsociada
	 */
	@MockBean
	PddCompetenciaAsociadaMapper pddCompetenciaAsociadaMapper;

	/**
	 * Inyeccion del mapper de la entidad PddCompromiso
	 */
	@MockBean
	PddCompromisoMapper pddCompromisoMapper;

	/**
	 * Inyeccion del mapper de la entidad pddCompromisoEspecifico
	 */
	@MockBean
	PddCompromisoEspecificoMapper pddCompromisoEspecifocMapper;

	/**
	 * Inyeccion del mapper de la entidad pdd
	 */
	@MockBean
	PddMapper pddMapper;

	/**
	 * Inyeccion del mapper de la entidad pddNivel
	 */
	@MockBean
	PddNivelMapper pddNivelMapper;

	/**
	 * Inyeccion del mapper de la entidad pddMeta
	 */
	@MockBean
	PddMetaMapper pddMetaMapper;

	/**
	 * Inyeccion del mapper de la entidad PddMetaRasultado
	 */
	@MockBean
	PddMetaResultadoMapper pddMetaResultadoMapper;

	/**
	 * Inyeccion del mapper de la entidad pddObraConcreta
	 */
	@MockBean
	PddObraConcretaMapper pddObraConcretamapper;

	/**
	 * Inyeccion del mapper de la entidad pddPrbValoracion
	 */
	@MockBean
	PddPrbValoracionMapper pddPrbValoracionMapper;

	/**
	 * Inyeccion del mapper de la entidad pddProblematica
	 */
	@MockBean
	PddProblematicaMapper pddProblematicaMapper;

	/**
	 * Inyeccion del mapper de la entidad pdl
	 */
	@MockBean
	PdlMapper pdlMapper;

	/**
	 * Inyeccion del mapper de la entidad pdlNivel
	 */
	@MockBean
	PdlNivelMapper pdlNivelMapper;

	/**
	 * Inyeccion del mapper de la entidad poblacion
	 */
	@MockBean
	PddPrbPoblacionMapper poblacionMapper;

	/**
	 * Inyeccion del mapper de la entidad pddPrbIndicador
	 */
	@MockBean
	PddPrbIndicadorMapper pddPrbIndicadorMapper;

	/**
	 * Inyeccion del mapper de la entidad pddIndicador
	 */
	@MockBean
	PddIndicadorMapper pddIndicadorMapper;

	@MockBean
	PotMapper potMapper;

	@MockBean
	PddMpIndicadorMapper pddMpIndicadorMapper;

	@MockBean
	PotRamaMapper potRamaMapper;

	@MockBean
	IPotRamaServiceRepo potRamaServiceRepo;

	@MockBean
	PddNivelAtributoMapper pddNivelAtributoMapper;

	@MockBean
	PotObraMapper potObraMapper;

	@MockBean
	PddMRIndicadorMapper pddMRIndicadorMapper;

	@MockBean
	IPddMRIndicadorServiceRepo pddMRIndicadorServiceRepo;

	@MockBean
	PddMetaProductoMapper pddMetaProductoMapper;

	@MockBean
	PotNivelMapper potNivelMapper;
	
	@MockBean
	PotObraEntidadMapper potObraEntidadMapper;

	@MockBean
	PotInstrumentoMapper potInstrumentoMapper;

	@MockBean
	PdlNivelAtributoMapper pdlNivelAtributoMapper;
	
	@MockBean
	PddMpEntidadMapper pddMpEntidadMapper;

	@MockBean
	IPddMpEntidadServiceRepo pddMpEntidadServiceRepo;
	
	@MockBean
	IPddRangoPonderacionServiceRepo pddRangoPonderacionServiceRepo;
	
	@MockBean
	PddRangoPonderacionMapper pddRangoPonderacionMapper;
	
	@MockBean
	IPddMpIndicadorEntidadServiceRepo pddMpIndicadorEntidadServiceRepo;
	
	@MockBean
	PddMpIndicadorEntidadMapper pddMpIndicadorEntidad;


}
