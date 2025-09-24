package co.gov.sdp.nhspdd.common.dto.bp;

import java.io.Serializable;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class BpIniciativaCiudadanaDTO extends RespuestaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idIniciativa;

	private Long codigoIn;

	private String fechaCodigo;

	private String nombrePoblaciones;

	private String nombreGruposEtarios;

	private Long radicado;

	private Long numeroRad;

	private String fechaRad;

	private String nombre;

	private Long aplicaLinea;

	private Long idPlanDesarrollo;

	private String problematica;

	private String condicionPoblacional;

	private String gruposEtarios;

	private String codicionesPoblacionales;

	private Long totalPoblacion;

	private String alternativaSolucion;

	private String nombreProp;

	private String telefonoProp;

	private String identificacionProp;

	private String emailProp;

	private String observacion;

	private Long idLsOrigen;

	private Long idLsEstadoInicia;

	private Long idLcLineaInv;

	private Long idLcTerritorializacionUpr;

	private Long idLcTerritorializacionUpz;

	private Long idLsBarrio;

	private Long idLsUpz;

	private Long idLsUpr;

	private Long idLsVereda;

	private Long idLsLocalidad;

	private String codigoEntidad;

	private String nombrePlanDesarrollo;

	private String nombreLineaInversion;

	private String nombreSector;

	private String nombreTerritorializacionUpr;

	private String nombreTerritorializacionUpz;

	private String nombreEstado;

	private String nombreOrigen;

	private String nombreEntidad;

	private String nombreCodigoProyecto;

	private String nombreUpz;

	private String nombreUpr;

	private String nombreVereda;

	private String nombreBarrio;

	private String localidad;
	
	private Long codigoPi;
	
	private Long idProyInversion;

	private String stringProyInversion;
	
	private Long codigoProyectoInversion;

}
