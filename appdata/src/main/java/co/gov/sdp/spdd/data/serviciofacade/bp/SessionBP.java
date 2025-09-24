package co.gov.sdp.spdd.data.serviciofacade.bp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpAporteCiudadanoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCiudadanaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCondicionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaEtarioServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaUbicacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvActividadServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAnualizaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvAporteServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEspecifServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvEtnicoServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvFinanciaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvIniciativaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLineaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvLocalizaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvMetaPlanServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPmrServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoblacionServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpProyInvPoliticaServiceRepo;
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
import co.gov.sdp.spdd.data.mapper.BpProyInvEtnicoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvFinanciaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvIniciativaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvLineaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvLocalizaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvMetaPlanMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvPmrMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvPoblacionMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvPoliticaMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvProductoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyInvTipoMapper;
import co.gov.sdp.spdd.data.mapper.BpProyectoInversionMapper;
import co.gov.sdp.spdd.data.mapper.VistaBpIniciativaMapper;
import co.gov.sdp.spdd.data.mapper.VistaBpProyectoInversionMapper;

/**
 * Clase principal de patron Facade del modulo BP, contiene las inyecciones de
 * los diferentes Repository y Mapper que se utilizan en este modulo
 * 
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Component
public class SessionBP implements Serializable {

	@Autowired
	IBpProyectoInversionServiceRepo bpProyectoInversionServiceRepo;

	@Autowired
	IBpProyInvLineaServiceRepo bpProyInvLineaServiceRepo;

	@Autowired
	IBpProyInvTipoServiceRepo bpProyInvTipoServiceRepo;
	
	@Autowired
	IBpProyInvIniciativaServiceRepo bpProyInvIniciativaServiceRepo;

	@Autowired
	IVistaBpProyectoInversionServiceRepo vistaBpProyectoInversionServiceRepo;

	@Autowired
	IBpAporteCiudadanoServiceRepo bpAporteCiudadanoServiceRepo;

	@Autowired
	IBpProyInvAporteServiceRepo bpProyInvAporteServiceRepo;

	@Autowired
	BpProyectoInversionMapper bpProyectoInversionMapper;

	@Autowired
	BpProyInvTipoMapper bpProyInvTipoMapper;

	@Autowired
	BpProyInvLineaMapper bpProyInvLineaMapper;

	@Autowired
	VistaBpIniciativaMapper vistaBpIniciativaCiudadanaMapper;

	@Autowired
	IBpIniciativaCiudadanaServiceRepo iniciativaCiudadanaServiceRepo;

	@Autowired
	BpIniciativaCiudadanaMapper iniciativaCiudadanaMapper;

	@Autowired
	IBpIniciativaEtarioServiceRepo bpIniciativaEtarioServiceRepo;

	@Autowired
	IBpIniciativaUbicacionServiceRepo bpIniciativaUbicacionServiceRepo;

	@Autowired
	BpIniciativaEtarioMapper bpIniciativaEtarioMapper;

	@Autowired
	BpIniciativaUbicacionMapper bpIniciativaUbicacionMapper;

	@Autowired
	VistaBpProyectoInversionMapper vistaBpProyectoInversionMapper;

	@Autowired
	BpAporteCiudadanoMapper bpAporteCiudadanoMapper;

	@Autowired
	BpProyInvAporteMapper bpProyInvAporteMapper;

	@Autowired
	IBpIniciativaCondicionServiceRepo bpIniciativaCondicionServiceRepo;

	@Autowired
	BpIniciativaCondicionMapper bpIniciativaCondicionMapper;
	
	@Autowired
	IBpProyInvLocalizaServiceRepo bpProyInvLocalizaServiceRepo;
	
	@Autowired
	BpProyInvLocalizaMapper bpProyInvLocalizaMapper;
	
	@Autowired
	BpProyInvIniciativaMapper bpProyInvIniciativaMapper;
	
	@Autowired
	IBpProyInvEspecifServiceRepo bpProyInvEspecifServiceRepo;
	
	@Autowired
	BpProyInvEspecifMapper bpProyInvEspecifMapper;
	
	@Autowired
	IBpProyInvMetaPlanServiceRepo bpProyInvMetaPlanServiceRepo;
	
	@Autowired
	BpProyInvMetaPlanMapper bpProyInvMetaPlanMapper;
	
	@Autowired
	IBpProyInvProductoServiceRepo bpProyInvProductoServiceRepo;
	
	@Autowired
	BpProyInvProductoMapper bpProyInvProductoMapper;
	
	@Autowired
	IBpProyInvActividadServiceRepo bpProyInvActividadServiceRepo;
	
	@Autowired
	BpProyInvActividadMapper bpProyInvActividadMapper;
		// Jefferson Arenas
	
	@Autowired
	IBpProyInvAnualizaServiceRepo bpProyInvAnualizaServiceRepo;
	
	@Autowired
	BpProyInvAnualizaMapper bpProyInvAnualizaMapper;
	
	@Autowired
	IBpProyInvFinanciaServiceRepo bpProyInvFinanciaServiceRepo;
	
	@Autowired
	BpProyInvFinanciaMapper bpProyInvFinanciaMapper;
	
	
	// Jefferson Arenas

	@Autowired
	BpProyInvPoblacionMapper bpProyInvPoblacionMapper;
	
	@Autowired
	IBpProyInvPoblacionServiceRepo bpProyInvPoblacionServiceRepo;
	
	@Autowired
	IBpProyInvEtnicoServiceRepo bpProyInvEtnicoServiceRepo;
	
	@Autowired
	BpProyInvEtnicoMapper bpProyInvEtnicoMapper;
	
	@Autowired
	IBpProyInvPoliticaServiceRepo bpProyInvPoliticaServiceRepo;
	
	@Autowired
	BpProyInvPoliticaMapper bpProyInvPoliticaMapper;
	
	@Autowired
	IBpProyInvPmrServiceRepo bpProyInvPmrServiceRepo;
	
	@Autowired
	BpProyInvPmrMapper bpProyInvPmrMapper;
}
