package co.gov.sdp.nhspdd.common.dto.bp;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

/**
 * Clase que representa el DTO de la entidad BPProyectoInveersion
 * @author DANIEL
 * @version 1.0 31/03/2020
 */
@Data
public class BpProyectoInversionDTO extends RespuestaDTO {
	
    private Long idProyInversion;
    
    private Long codigoProyecto;
    
    private String nombreProyecto;
    
    private String codigoBpin;

    private String nombreBpin;
    
    private String nombrePoai;
    
    private String antecedente;
    
    private String situacion;
    
    private String descripcionUniverso;
    
    private Long cuantificacion;
    
    private String descripcion;
    
    private String objetivo;
    
    private String observacion;
    
    private String nombreGerente;
    
    private String correo;
    
    private String telefono;
    
    private String cargo;
    
    private String area;
    
    private String fechaResponsable;
    
    private String obseracionReg;
    
    private Long version;
    
    private Long aporteCiudadano;
    
    private Long bloqueo;    
    
    private String fechaEstado;
    
    private String fechaVersion;
    
    //TODO: validar si es una foranea a otra tabla
    private String idArchUcm;
    
    private Long peso;
    
    private Long idLsEstado;
    
    private String stringLsEstado;
    
    private Long idLsEtapa;
    
    private String stringLsEtapa;
    
    private Long idLsUnidad;
    
    private String stringLsUnidad;
    
    private String codigoEntidad;
    
    private String nombreEntidad;
    
    private Long idLsClasificacionEntidad;
    
    private String stringEntidad;
    
    private String stringSectorEntidad;
    
    private Long idBancoProyecto;
    
    private String stringBancoProyecto;
    
    private Long idAtributoPdd;
    
    private String stringAtributoPdd;
    
    private Long idPlanDesarrolloDistrital;
    
    private String stringPlanDesarrolloDistrital;
    
    private Long idAtributoPdl;
    
    private String stringAtributoPdl;
    
    private Long idPlanDesarrolloLocal;
    
    private String stringPlanDesarrolloLocal;
    
    private Long idLsSectorAl;
    
    private String stringLsSectorAl;
    
    
    //este atributo se utiliza para el mapeo con la entidad y guardar la concatenacion  de los ids de los tipos de proyectos seleccionados en la vista para guardar en BD
    private String idsTipoProy;
  //este atributo se utiliza para el mapeo con la entidad y guardar la concatenacion  de los string de los tipos de proyectos seleccionados en la vista para guardar en BD
    private String stringstipoProy;
    
    
    //Este atributo se utiliza para el mapeo con la vista y guardar el identificador del tipo de la vista para pasar los datos de BD a vista
    private Long idLsTipo;
    //Este atributo se utiliza para el mapeo con la vista y guardar el string del identificador del tipo de la vista
    private String stringLsTipo;
    
    private String stringSector;
    
    private String enfoqueGenero;
    
    //Territorializacion
	private Long idTerritorializacion;

	private Integer estado;

	private Long idLsLocalidad;

	private Long idLsUpz;

	private Long idLsVereda;

	private Long idLsBarrio;

	private Long idLsUpr;

	private String nombreLocalidad;

	private String nombreUpz;

	private String nombreVereda;

	private String nombreBarrio;

	private String nombreUpr;

	private String stringEstado;

}
