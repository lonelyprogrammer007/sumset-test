package co.gov.sdp.spdd.data.serviciofacade.bp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpAporteCiudadanoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCiudadanaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCondicionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaEtarioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaUbicacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvActividadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAnualizaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAporteServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEspecifServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvFinanciaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEtnicoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvIniciativaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLineaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLocalizaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvMetaPlanServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoblacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvProductoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvTipoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IVistaBpProyectoInversionServiceRepo;
import co.gov.sdp.spdd.data.mapper.BpAporteCiudadanoMapper;
import co.gov.sdp.spdd.data.mapper.BpIniciativaCiudadanaMapper;
import co.gov.sdp.spdd.data.mapper.BpIniciativaCondicionMapper;
import co.gov.sdp.spdd.data.mapper.BpIniciativaEtarioMapper;
import co.gov.sdp.spdd.data.mapper.BpIniciativaUbicacionMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvActividadMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvAnualizaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvAporteMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvEspecifMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvFinanciaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvEtnicoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvIniciativaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvLineaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvLocalizaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvMetaPlanMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvPoblacionMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvProductoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvTipoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.VistaBpIniciativaMapper;
import co.gov.sdp.spdd.data.mapper.VistaBpProyectoInversionMapper;

public class SessionBPTest {

	@MockBean
	IBpProyectoInversionServiceRepo bpProyectoInversionServiceRepo;

	@MockBean
	IBpProyInvLineaServiceRepo bpProyInvLineaServiceRepo;

	@MockBean
	IBpProyInvTipoServiceRepo bpProyInvTipoServiceRepo;
	
	@MockBean
	IBpProyInvIniciativaServiceRepo bpProyInvIniciativaServiceRepo;

	@MockBean
	IVistaBpProyectoInversionServiceRepo vistaBpProyectoInversionServiceRepo;

	@MockBean
	IBpAporteCiudadanoServiceRepo bpAporteCiudadanoServiceRepo;

	@MockBean
	IBpProyInvAporteServiceRepo bpProyInvAporteServiceRepo;

	@MockBean
	BpProyectoInversionMapper bpProyectoInversionMapper;

	@MockBean
	BpProyInvTipoMapper bpProyInvTipoMapper;

	@MockBean
	BpProyInvLineaMapper bpProyInvLineaMapper;

	@MockBean
	VistaBpIniciativaMapper vistaBpIniciativaCiudadanaMapper;

	@MockBean
	IBpIniciativaCiudadanaServiceRepo iniciativaCiudadanaServiceRepo;

	@MockBean
	BpIniciativaCiudadanaMapper iniciativaCiudadanaMapper;

	@MockBean
	IBpIniciativaEtarioServiceRepo bpIniciativaEtarioServiceRepo;

	@MockBean
	IBpIniciativaUbicacionServiceRepo bpIniciativaUbicacionServiceRepo;

	@MockBean
	BpIniciativaEtarioMapper bpIniciativaEtarioMapper;

	@MockBean
	BpIniciativaUbicacionMapper bpIniciativaUbicacionMapper;

	@MockBean
	VistaBpProyectoInversionMapper vistaBpProyectoInversionMapper;

	@MockBean
	BpAporteCiudadanoMapper bpAporteCiudadanoMapper;

	@MockBean
	BpProyInvAporteMapper bpProyInvAporteMapper;

	@MockBean
	IBpIniciativaCondicionServiceRepo bpIniciativaCondicionServiceRepo;

	@MockBean
	BpIniciativaCondicionMapper bpIniciativaCondicionMapper;
	
	@MockBean
	IBpProyInvLocalizaServiceRepo bpProyInvLocalizaServiceRepo;
	
	@MockBean
	BpProyInvLocalizaMapper bpProyInvLocalizaMapper;
	
	@MockBean
	BpProyInvIniciativaMapper bpProyInvIniciativaMapper;
	
	@MockBean
	IBpProyInvEspecifServiceRepo bpProyInvEspecifServiceRepo;
	
	@MockBean
	BpProyInvEspecifMapper bpProyInvEspecifMapper;
	
	@MockBean
	IBpProyInvMetaPlanServiceRepo bpProyInvMetaPlanServiceRepo;
	
	@MockBean
	BpProyInvMetaPlanMapper bpProyInvMetaPlanMapper;
	
	@MockBean
	IBpProyInvProductoServiceRepo bpProyInvProductoServiceRepo;
	
	@MockBean
	BpProyInvProductoMapper bpProyInvProductoMapper;
	
	@MockBean
	IBpProyInvActividadServiceRepo bpProyInvActividadServiceRepo;
	
	@MockBean
	BpProyInvActividadMapper bpProyInvActividadMapper;
	

	// Jefferson Arenas
	
	@MockBean
	IBpProyInvAnualizaServiceRepo bpProyInvAnualizaServiceRepo;
	
	@MockBean
	BpProyInvAnualizaMapper bpProyInvAnualizaMapper;
	
	@MockBean
	IBpProyInvFinanciaServiceRepo bpProyInvFinanciaServiceRepo;
	
	@MockBean
	BpProyInvFinanciaMapper bpProyInvFinanciaMapper;
	
	
	// Jefferson Arenas

	@MockBean
	BpProyInvPoblacionMapper bpProyInvPoblacionMapper;
	
	@MockBean
	IBpProyInvPoblacionServiceRepo bpProyInvPoblacionServiceRepo;
	
	@MockBean
	IBpProyInvEtnicoServiceRepo bpProyInvEtnicoServiceRepo;
	
	@MockBean
	BpProyInvEtnicoMapper bpProyInvEtnicoMapper;


}
