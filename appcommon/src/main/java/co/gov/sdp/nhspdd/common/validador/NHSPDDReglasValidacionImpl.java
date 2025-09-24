package co.gov.sdp.nhspdd.common.validador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGastoDTO;
import co.gov.sdp.nhspdd.common.dto.ComponenteGestionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.ConfiguracionNotificacionDTO;
import co.gov.sdp.nhspdd.common.dto.ConsecutivoDTO;
import co.gov.sdp.nhspdd.common.dto.EntidadDTO;
import co.gov.sdp.nhspdd.common.dto.EquipamientoDTO;
import co.gov.sdp.nhspdd.common.dto.EstructuraMetaDTO;
import co.gov.sdp.nhspdd.common.dto.FuncionarioClaveEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialAdministrativoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.InformacionPresupuestalDTO;
import co.gov.sdp.nhspdd.common.dto.LineaDeInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.ParametroGeneralDTO;
import co.gov.sdp.nhspdd.common.dto.PddDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelDTO;
import co.gov.sdp.nhspdd.common.dto.PotInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.PotObraDTO;
import co.gov.sdp.nhspdd.common.dto.PotProyectoInstrumentoDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ProyectosInversionUsuarioDTO;
import co.gov.sdp.nhspdd.common.dto.TerritorializacionDTO;
import co.gov.sdp.nhspdd.common.dto.UsuarioEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.UsuariosDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvIniciativaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.CompromisoEstrategicoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompetenciaAsociadaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddCompromisoEspecificoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaProductoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMetaResultadoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorEntidadDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddObraConcretaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbIndicadorDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPrbValoracionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddProblematicaDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PdlNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotNivelDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PotRamaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;

/**
 * Clase que implementa las reglas de validacion
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
public class NHSPDDReglasValidacionImpl {

	private static final int TIPO_TEXTO_VACIO = 1;

	private NHSPDDReglasValidacionImpl() {
		super();
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param proyectosInversionDTO Objecto DTO a validar
	 * @param camposAValidar        Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasEntidad(EntidadDTO entidadDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_CODIGO_ENTIDAD, entidadDTO.getCodigoEntidad(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_GESTION_PROYECTOS, entidadDTO.getGestionProyectos(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_GESTION_PROYECTOS_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_GESTION_USUARIOS, entidadDTO.getGestionUsuarios(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_GESTION_USUARIOS_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_ID_LS_CLASIFICACION,
				entidadDTO.getIdLsClasificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_CLASIFICACION_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_ID_LS_ASOCIACION, entidadDTO.getIdLsAsociacion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_ASOCIACION_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ENTIDAD_ID_BANCO_PROYECTO, entidadDTO.getIdBancoProyecto(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_BANCO_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, entidadDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param proyectosInversionDTO Objecto DTO a validar
	 * @param camposAValidar        Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasProyectoInversion(ProyectoInversionDTO proyectosInversionDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PROYECTO_INVERSION_ID_PROYECTO_INVERSION,
				proyectosInversionDTO.getIdProyectoInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, proyectosInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PROYECTO_INVERSION_NOMBRE, proyectosInversionDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, proyectosInversionDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacions que contiene el objeto
	 * componenteGestionUsuario segun los campos requeridos
	 *
	 * @param componenteGestionUsuarioDTO Objeto DTO a validar
	 * @param camposAValidar              Campos a validar
	 * @return Lista de errores de validacion la cual esta vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasComponenteGestionUsuario(
			ComponenteGestionUsuarioDTO componenteGestionUsuarioDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_ID_GESTION_USUARIO,
				componenteGestionUsuarioDTO.getIdGestionUsuario(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_ID_COMPONENTE_GESTION_USUARIO_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGestionUsuarioDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_USUARIO,
				componenteGestionUsuarioDTO.getCodigoUsuario(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_COMPONENTE_GESTION_USUARIO_USUARIO,
							PaqueteMensajeEnum.ERRORES, componenteGestionUsuarioDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_COMPONENTE_GESTION,
				componenteGestionUsuarioDTO.getIdComponenteGestion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_COMPONENTE_GESTION,
							PaqueteMensajeEnum.ERRORES, componenteGestionUsuarioDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param proyectosInversionUsuarioDTO Objecto DTO a validar
	 * @param camposAValidar               Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasProyectosInversionUsuario(
			ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_ID_PROYECTO_USUARIO,
				proyectosInversionUsuarioDTO.getIdProyectoUsuario(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_USUARIO_VACIO,
							PaqueteMensajeEnum.ERRORES, proyectosInversionUsuarioDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_PROYECTO_INVERSION,
				proyectosInversionUsuarioDTO.getIdProyectoInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, proyectosInversionUsuarioDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_USUARIO,
				proyectosInversionUsuarioDTO.getCodigoUsuario(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_USUARIO_VACIO,
							PaqueteMensajeEnum.ERRORES, proyectosInversionUsuarioDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param usuariosDTO    Objecto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasUsuario(UsuariosDTO usuariosDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_CORREO, usuariosDTO.getCorreo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CORREO_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_USUARIO, usuariosDTO.getNombreUsuario(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_USUARIOS_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_CLAVE, usuariosDTO.getClave(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CLAVE_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_CLAVE_ANTERIOR, usuariosDTO.getContraseniaActual(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CLAVE_ANTERIOR_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_CLAVE_NUEVA, usuariosDTO.getContraseniaNueva(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CLAVE_NUEVA_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_TIPO, usuariosDTO.getTipo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_TIPO_VACIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param proyectosInversionDTO Objecto DTO a validar
	 * @param camposAValidar        Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasUsuarioEntidad(UsuarioEntidadDTO usuarioEntidadDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_ENTIDAD_ID_USUARIO_ENTIDAD,
				usuarioEntidadDTO.getIdUsuarioEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_USUARIO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, usuarioEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_ENTIDAD_CODIGO_ENTIDAD,
				usuarioEntidadDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, usuarioEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.USUARIO_ENTIDAD_USUARIO, usuarioEntidadDTO.getCodigoUsuario(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_USUARIO_VACIO,
							PaqueteMensajeEnum.ERRORES, usuarioEntidadDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param listaSimpleDTO Objecto DTO a validar
	 * @param camposAValidar Campos a valida
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasListaSimple(ListaSimpleDTO listaSimpleDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.LISTA_SIMPLE_ID_LISTA_SIMPLE,
				listaSimpleDTO.getIdListaSimple(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LISTA_SIMPLE_VACIO,
							PaqueteMensajeEnum.ERRORES, listaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LISTA_SIMPLE_NOMBRE, listaSimpleDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, listaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LISTA_SIMPLE_DESCRIPCION, listaSimpleDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, listaSimpleDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param argumentoListaSimpleDTO Objeto DTO a validar
	 * @param camposAValidar          Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasArgumentoListaSimple(
			ArgumentoListaSimpleDTO argumentoListaSimpleDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ID_ARGUMENTO,
				argumentoListaSimpleDTO.getIdArgumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ARGUMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, argumentoListaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ARGUMENTO,
				argumentoListaSimpleDTO.getArgumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ARGUMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, argumentoListaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ESTADO,
				argumentoListaSimpleDTO.getEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, argumentoListaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_RESULTADO,
				argumentoListaSimpleDTO.getResultado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_RESULTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, argumentoListaSimpleDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_LISTA_SIMPLE,
				argumentoListaSimpleDTO.getIdListaSimple(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_LISTA_SIMPLE_VACIO,
							PaqueteMensajeEnum.ERRORES, argumentoListaSimpleDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param potProyectoInstrumentoDTO Objeto DTO a validar
	 * @param camposAValidar            Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPotProyectoInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_PROYECTO_INSTRUMENTO,
				potProyectoInstrumentoDTO.getIdProyectoInstrumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_POT_PROYECTO_INSTRUMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, potProyectoInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_INSTRUMENTO,
				potProyectoInstrumentoDTO.getIdPotInstrumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ARGUMENTO_LISTA_SIMPLE_1_VACIO,
							PaqueteMensajeEnum.ERRORES, potProyectoInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_POT_PROYECTO,
				potProyectoInstrumentoDTO.getIdPotProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ARGUMENTO_LISTA_SIMPLE_2_VACIO,
							PaqueteMensajeEnum.ERRORES, potProyectoInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ESTADO,
				potProyectoInstrumentoDTO.getEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, potProyectoInstrumentoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param componenteGastoDTO Objeto DTO a validar
	 * @param camposAValidar     Campos a valida
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasComponenteGasto(ComponenteGastoDTO componenteGastoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GASTO_ID_COMPONENTE_GASTO,
				componenteGastoDTO.getIdComponenteGasto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_COMPONENTE_GASTO_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGastoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GASTO_CODIGO_COMPONENTE,
				componenteGastoDTO.getCodigoComponente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_COMPONENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGastoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GASTO_NOMBRE_COMPONENTE,
				componenteGastoDTO.getNombreComponente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGastoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GASTO_ARGUMENTO_LISTA_SIMPLE,
				componenteGastoDTO.getIdLsTipoProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ARGUMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGastoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPONENTE_GASTO_ESTADO, componenteGastoDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, componenteGastoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param lineaDeInversionDTO objeto DTO a validar
	 * @param camposAValidar      Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasLineaInversion(LineaDeInversionDTO lineaDeInversionDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_ID_LINEA_INVERSION,
				lineaDeInversionDTO.getIdLineaInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LINEA_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_ESTABLECIDO,
				lineaDeInversionDTO.getEstablecido(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTABLECIDO_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_CONCEPTO, lineaDeInversionDTO.getConcepto(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CONCEPTO_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_DESCRIPCION,
				lineaDeInversionDTO.getDescripcion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_FECHA, lineaDeInversionDTO.getFecha(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_ID_LS_SECTOR,
				lineaDeInversionDTO.getIdLsSector(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_SECTOR_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.LINEA_INVERSION_ESTADO, lineaDeInversionDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, lineaDeInversionDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddPrbValoracionDTO objeto DTO a validar
	 * @param camposAValidar      Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddPrbValoracion(PddPrbValoracionDTO pddPrbValoracionDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_GRAVEDAD,
				pddPrbValoracionDTO.getGravedad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_GRAVEDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_DURACION,
				pddPrbValoracionDTO.getDuracion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DURACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_IMPACTO, pddPrbValoracionDTO.getImpacto(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IMPACTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_BALANCE_INICIAL,
				pddPrbValoracionDTO.getBalanceInicial(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BALANCE_INICIAL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_OBSERVACIONES,
				pddPrbValoracionDTO.getObservaciones(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_OBSERVACIONES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_MOMENTO, pddPrbValoracionDTO.getMomento(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MOMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_SECTOR,
				pddPrbValoracionDTO.getIdLsSector(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_SECTOR_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_DIMENSION,
				pddPrbValoracionDTO.getIdLsDimension(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_DIMENSION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_1,
				pddPrbValoracionDTO.getIdCompetenciaAsociada1(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_COMPETENCIA_1__VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_2,
				pddPrbValoracionDTO.getIdCompetenciaAsociada2(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_COMPETENCIA_2_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_ID_PROBLEMATICA,
				pddPrbValoracionDTO.getIdProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROBLEMATICA_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_VALORACION_DEBILIDAD,
				pddPrbValoracionDTO.getDebilidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DEBILIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, pddPrbValoracionDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param compromisoEstrategicoDTO objeto DTO a validar
	 * @param camposAValidar           Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddCompetenciaAsociada(
			PddCompetenciaAsociadaDTO ppCompetenciaAsociadaDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPETENCIA_ASOCIADA_ID_LS_COMPETENCIA,
				ppCompetenciaAsociadaDTO.getIdLsCompetencia(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_COMPETENCIA_VACIO,
							PaqueteMensajeEnum.ERRORES, ppCompetenciaAsociadaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPETENCIA_ASOCIADA_ID_LS_SECTOR,
				ppCompetenciaAsociadaDTO.getIdLsSector(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_SECTOR_VACIO,
							PaqueteMensajeEnum.ERRORES, ppCompetenciaAsociadaDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param compromisoEstrategicoDTO objeto DTO a validar
	 * @param camposAValidar           Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasCompromisoEstrategico(
			CompromisoEstrategicoDTO compromisoEstrategicoDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_ID_COMPROMISO,
				compromisoEstrategicoDTO.getIdCompromiso(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDCOMPROMISO_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, compromisoEstrategicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_ESTADO,
				compromisoEstrategicoDTO.getEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, compromisoEstrategicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO,
				compromisoEstrategicoDTO.getIdCompromisoEstrategico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_COMPROMISO_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, compromisoEstrategicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSTEMATICA,
				compromisoEstrategicoDTO.getIdTematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_TEMATICA_VACIO,
							PaqueteMensajeEnum.ERRORES, compromisoEstrategicoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param compromisoEstrategicoDTO objeto DTO a validar
	 * @param camposAValidar           Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddCompromisoEspecifico(
			PddCompromisoEspecificoDTO pddCompromisoEspecificoDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_ESPECIFICO,
				pddCompromisoEspecificoDTO.getIdEspecifico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ESPECIFICO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoEspecificoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_COMPROMISO,
				pddCompromisoEspecificoDTO.getIdCompromiso(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_COMPROMISO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoEspecificoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_DESCRIPCION,
				pddCompromisoEspecificoDTO.getDescripcion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoEspecificoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddCompromisoDTO objeto DTO a validar
	 * @param camposAValidar   Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddCompromiso(PddCompromisoDTO pddCompromisoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ID_COMPROMISO,
				pddCompromisoDTO.getIdCompromiso(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_COMPROMISO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ID_ESTRATEGICO,
				pddCompromisoDTO.getIdEstrategico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_COMPROMISO_ID_PLAN_DESARROLLO,
				pddCompromisoDTO.getIdPlanDesarrollo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSTEMATICA,
				pddCompromisoDTO.getIdTematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_TEMATICA_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO,
				pddCompromisoDTO.getIdLsEstrategico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddCompromisoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddDTO         objeto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPdd(PddDTO pddDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NOMBRE_PLAN, pddDTO.getNombrePlan(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_PLAN_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_SIGLA_PLAN, pddDTO.getSiglaPlan(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SIGLA_PLAN_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_YEAR_INICIO, pddDTO.getYearInicio(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_INICIO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_YEAR_FINAL, pddDTO.getYearFinal(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_FINAL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROGRAMA_GOBIERNO, pddDTO.getProgramaGobierno(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PROGRAMA_GOBIERNO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_ACTO_ADMINISTRATIVO, pddDTO.getActoAdministrativo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ACTO_ADMINISTRATIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_FECHA_ACTO, pddDTO.getFechaActo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_ACTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NOMBRE_ALCALDE, pddDTO.getNombreAlcalde(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_ALCALDE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_CORREO_ALCALDE, pddDTO.getCorreoAlcalde(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CORREO_ALCALDE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_IDLSADOPTADO, pddDTO.getIdLsAdoptado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDLSADOPTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_IDLSAVANCESGR, pddDTO.getIdLsAvanceSgr(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDLSAVANCESGR_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_IDESTADOPLAN, pddDTO.getIdLsEstadoPlan(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDESTADOPLAN_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_IDLSNIVELES, pddDTO.getIdLsNiveles(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDLSNIVELES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_ID_PLAN_DESARROLLO, pddDTO.getIdPlanDesarrollo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddNivelDTO    objeto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddNivel(PddNivelDTO pddNivelDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ID_PDD_NIVEL, pddNivelDTO.getIdPddNivel(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PDD_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_CODIGO_NIVEL, pddNivelDTO.getCodNivel(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_DESCRIPCION, pddNivelDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ID_PLAN_DESARROLLO,
				pddNivelDTO.getIdPlanDesarrollo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_OBLIGATORIO_PDL, pddNivelDTO.getObligatorioPdl(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_OBLIGATORIO_PDL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddNivelDTO    objeto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO,
				pddNivelAtributoDTO.getIdAtributo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_NUMERO, pddNivelAtributoDTO.getNumero(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NUMERO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_DENOMINACION,
				pddNivelAtributoDTO.getDenominacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_DENOMINACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_PONDERACION,
				pddNivelAtributoDTO.getPonderacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PONDERACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_NOMBRE_GERENTE,
				pddNivelAtributoDTO.getNombreGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NOMBRE_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_CORREO_GERENTE,
				pddNivelAtributoDTO.getCorreoGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_CORREO_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE,
				pddNivelAtributoDTO.getIdLsGeneroGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_ID_PDD_NIVEL,
				pddNivelAtributoDTO.getIdPddNivel(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_PDD_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE,
				pddNivelAtributoDTO.getIdAtributoPadre(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO,
				pddNivelAtributoDTO.getProyectoEstrategico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddNivelAtributoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddNivelDTO    objeto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPdlNivelAtributo(PdlNivelAtributoDTO pdlNivelAtributoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NUMERO, pdlNivelAtributoDTO.getNumero(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NUMERO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_DENOMINACION,
				pdlNivelAtributoDTO.getDenominacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_DENOMINACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_PONDERACION,
				pdlNivelAtributoDTO.getPonderacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PONDERACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NOMBRE_GERENTE,
				pdlNivelAtributoDTO.getNombreGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NOMBRE_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_CORREO_GERENTE,
				pdlNivelAtributoDTO.getCorreoGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_CORREO_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE,
				pdlNivelAtributoDTO.getIdLsGeneroGerente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_ID_PDD_NIVEL,
				pdlNivelAtributoDTO.getIdPdlNivel(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_PDD_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE,
				pdlNivelAtributoDTO.getIdAtributoPadre(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO,
				pdlNivelAtributoDTO.getProyectoEstrategico(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelAtributoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param territorializacionDTO objeto DTO a validar
	 * @param camposAValidar        campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasTerritorializacion(TerritorializacionDTO territorializacionDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_TERRITORIALIZACION,
				territorializacionDTO.getIdTerritorializacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_TERRITORIALIZACION_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ESTADO, territorializacionDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_LS_BARRIO,
				territorializacionDTO.getIdLsBarrio(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_BARRIO_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_LS_LOCALIDAD,
				territorializacionDTO.getIdLsLocalidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_BARRIO_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_LS_UPR,
				territorializacionDTO.getIdLsUpr(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_UPR_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_LS_UPZ,
				territorializacionDTO.getIdLsUpz(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_UPZ_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.TERRITORIALIZACION_ID_LS_VEREDA,
				territorializacionDTO.getIdLsVereda(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_VEREDA_VACIO,
							PaqueteMensajeEnum.ERRORES, territorializacionDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param consecutivoDTO objeto dto a validar
	 * @param camposAValidar campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasConsecutivo(ConsecutivoDTO consecutivoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_ID_PLAN_DESARROLLO,
				consecutivoDTO.getIdPlanDesarrollo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_NOMBRE, consecutivoDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_DESCRIPCION, consecutivoDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_SECUENCIA, consecutivoDTO.getSecuencia(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SECUENCIA_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_VIGENCIA, consecutivoDTO.getVigencia(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_VIGENCIA_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_ENTIDAD, consecutivoDTO.getCodigoEntidad(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONSECUTIVO_ID_CONSECUTIVO, consecutivoDTO.getIdConsecutivo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, consecutivoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param parametroGeneralDTO objeto DTO a validar
	 * @param camposAValidar      campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasParametroGeneral(ParametroGeneralDTO parametroGeneralDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_CODIGO_PARAMETRO,
				parametroGeneralDTO.getCodigoParametro(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_PARAMETRO_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_NOMBRE, parametroGeneralDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_ARGUMENTO,
				parametroGeneralDTO.getArgumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ARGUMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_DESCRIPCION,
				parametroGeneralDTO.getDescripcion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_FECHA_CREACION,
				parametroGeneralDTO.getFechaCreacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_CREACION_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_FECHA_MODIFICACION,
				parametroGeneralDTO.getFechaModificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_MODIFICACION_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_USUARIO_CREACION,
				parametroGeneralDTO.getUsuarioCreacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_USUARIO_CREACION_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PARAMETRO_GENERAL_USUARIO_MODIFICACION,
				parametroGeneralDTO.getUsuarioModificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_USUARIO_MODIFICACION_VACIO,
							PaqueteMensajeEnum.ERRORES, parametroGeneralDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param EstructuraMetaDTO Objecto DTO a validar
	 * @param camposAValidar    Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasEstructuraMeta(EstructuraMetaDTO estructuraMetaDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.ESTRUCTURA_METAS_ID_ESTRUCTURA_METAS,
				estructuraMetaDTO.getIdEstructuraMetas(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ESTRUCTURA_META_VACIO,
							PaqueteMensajeEnum.ERRORES, estructuraMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ESTRUCTURA_METAS_ESTADO, estructuraMetaDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, estructuraMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_UNIDAD_MEDIDA,
				estructuraMetaDTO.getIdLsUnidadMedida(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_UNIDAD_MEDIDA_VACIO,
							PaqueteMensajeEnum.ERRORES, estructuraMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_VERBO,
				estructuraMetaDTO.getIdLsVerbo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_VERBO_VACIO,
							PaqueteMensajeEnum.ERRORES, estructuraMetaDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param buzonNotificacionesDTO objeto DTO a validar
	 * @param camposAValidar         campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasBuzonNotificacion(BuzonNotificacionesDTO buzonNotificacionesDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_ID_NOTIFICACION,
				buzonNotificacionesDTO.getIdNotificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_NOTIFICACION_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_ESTADO,
				buzonNotificacionesDTO.getEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_ESCRITURA,
				buzonNotificacionesDTO.getFechaEscritura(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_ESCRITURA_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_LECTURA,
				buzonNotificacionesDTO.getFechaLectura(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_LECTURA_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_FECHA_RESPUESTA,
				buzonNotificacionesDTO.getFechaRespuesta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_RESPUESTA_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_ID_CONFIG_NOTIFICACION,
				buzonNotificacionesDTO.getIdConfigNotificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_CONFIG_NOTIFICACION,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}

		List<String> mensajes = new ArrayList<>();
		mensajes.add(NHSPDDConstantes.MENSAJE_VALIDACION_MENSAJE_VACIO);
		mensajes.add(NHSPDDConstantes.MENSAJE_VALIDACION_MENSAJE_TAMANO_INVALIDO);

		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_MENSAJE,
				buzonNotificacionesDTO.getMensaje(), TIPO_TEXTO_VACIO)) {
			resultado.addAll(validarCampoDeTexto(mensajes, buzonNotificacionesDTO.getLenguaje(),
					buzonNotificacionesDTO.getMensaje(), NHSPDDConstantes.BUZON_USUARIO_MENSAJE_MIN_LENGTH,
					NHSPDDConstantes.BUZON_USUARIO_MENSAJE_MAX_LENGTH));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_RESPUESTA,
				buzonNotificacionesDTO.getRespuesta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_RESPUESTA_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_TIPO_MENSAJE,
				buzonNotificacionesDTO.getTipoMensaje(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_TIPO_MENSAJE_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_USUARIO_DESTINO,
				buzonNotificacionesDTO.getCodigoUsuarioDestino(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_USUARIO_DESTINO_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BUZON_NOTIFICACIONES_USUARIO_ORIGINA,
				buzonNotificacionesDTO.getCodigoUsuarioOrigina(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_USUARIO_ORIGINA_VACIO,
							PaqueteMensajeEnum.ERRORES, buzonNotificacionesDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param configuracionNotificacionDTO objeto DTO a validar
	 * @param camposAValidar               campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasConfiguracionNotificacion(
			ConfiguracionNotificacionDTO configuracionNotificacionDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ID_CONFIG_NOTIFICACION,
				configuracionNotificacionDTO.getIdConfigNotificacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_CONFIG_NOTIFICACION,
							PaqueteMensajeEnum.ERRORES, configuracionNotificacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ASUNTO,
				configuracionNotificacionDTO.getAsunto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ASUNTO_VACIO,
							PaqueteMensajeEnum.ERRORES, configuracionNotificacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONFIGURACION_NOTIFICACION_MENSAJE,
				configuracionNotificacionDTO.getMensaje(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MENSAJE_VACIO,
							PaqueteMensajeEnum.ERRORES, configuracionNotificacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONFIGURACION_NOTIFICACION_OPERACION_ORIGEN,
				configuracionNotificacionDTO.getOperacionOrigen(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_OPERACION_ORIGEN_VACIO,
							PaqueteMensajeEnum.ERRORES, configuracionNotificacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.CONFIGURACION_NOTIFICACION_REQUIERE_ACCION,
				configuracionNotificacionDTO.getRequiereAccion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_REQUIERE_ACCION_VACIO,
							PaqueteMensajeEnum.ERRORES, configuracionNotificacionDTO.getLenguaje())));

		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param equipamientoDTO Objecto DTO a validar
	 * @param camposAValidar  Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasEquipamiento(EquipamientoDTO equipamientoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_ID_EQUIPAMIENTO,
				equipamientoDTO.getIdEquipamento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_EQUIPAMIENTO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_NOMBRE, equipamientoDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_DESCRIPCION, equipamientoDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DESCRIPCION_VACIO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_ESTADO, equipamientoDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_ID_LS_CATEGORIA,
				equipamientoDTO.getIdLsCategoria(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_CATEGORIA_VACIO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.EQUIPAMIENTO_ID_LS_SECTOR, equipamientoDTO.getIdLsSector(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_SECTOR_VACIO,
							PaqueteMensajeEnum.ERRORES, equipamientoDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param informacionPresupuestalDTO Objecto DTO a validar
	 * @param camposAValidar             Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasInformacionPresupuestal(
			InformacionPresupuestalDTO informacionPresupuestalDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_INFO_PRESUPUESTAL,
				informacionPresupuestalDTO.getIdInfoPresupuestal(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_INFO_PRESUPUESTAL_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_YEAR,
				informacionPresupuestalDTO.getYear(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_MES,
				informacionPresupuestalDTO.getMes(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MES_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_DISTRITAL,
				informacionPresupuestalDTO.getCodigoDistrital(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_DISTRITAL_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_PROYECTO,
				informacionPresupuestalDTO.getCodigoProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_NOMBRE_PROYECTO,
				informacionPresupuestalDTO.getNombreProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCION_VIGENCIA,
				informacionPresupuestalDTO.getEjecucionVigencia(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_EJECUCION_VIGENCIA_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_GIROS_VIGENCIA,
				informacionPresupuestalDTO.getGirosVigencia(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_GIROS_VIGENCIA_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_RECURSOS_SUSPENDIDOS,
				informacionPresupuestalDTO.getRecursosSuspendidos(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_RECURSOS_SUSPENDIDOS_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CONSTITUCION_RESERVA,
				informacionPresupuestalDTO.getConstitucionReserva(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CONSTITUCION_RESERVA_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROPIACION_RESERVA,
				informacionPresupuestalDTO.getApropiacionReserva(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_APROPIACION_RESERVA_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROBACION_DEFINITIVA,
				informacionPresupuestalDTO.getApropiacionDefinitiva(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_APROPIACION_DEFINITIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCUIN_GIRO_RESERVAS,
				informacionPresupuestalDTO.getEjecucionGiroReservas(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_EJECUCION_GIRO_RESERVAS_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_CLASIFICACION_PRESUPUESTAL,
				informacionPresupuestalDTO.getCodigoClasificacionPresupuestal(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_CLASIFICACION_PRESUPUESTAL_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_INTERNO,
				informacionPresupuestalDTO.getCodigoInterno(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_INTERNO_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ORIGEN,
				informacionPresupuestalDTO.getOrigen(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ORIGEN_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_ARCHIVO,
				informacionPresupuestalDTO.getIdArchivo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ARCHIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_PLAN_DESARROLLO,
				informacionPresupuestalDTO.getIdPlanDesarrollo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, informacionPresupuestalDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param historialAdministrativoDTO Objecto DTO a validar
	 * @param camposAValidar             Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasHistorialAdministrativo(
			HistorialAdministrativoDTO historialAdministrativoDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_CODIGO_HIS_ADMIN,
				historialAdministrativoDTO.getCodigoHisAdmin(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_HISTORIAL_ADMINISTRATIVO,
							PaqueteMensajeEnum.ERRORES, historialAdministrativoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_NOMBRE,
				historialAdministrativoDTO.getNombre(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_HISTORIAL_ADMINISTRATIVO,
							PaqueteMensajeEnum.ERRORES, historialAdministrativoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_SIGLA,
				historialAdministrativoDTO.getSigla(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SIGLA_HISTORIAL_ADMINISTRATIVO,
							PaqueteMensajeEnum.ERRORES, historialAdministrativoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_FECHA_INICIO,
				historialAdministrativoDTO.getFechaInicio(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_INICIO_HISTORIAL_ADMINISTRATIVO,
							PaqueteMensajeEnum.ERRORES, historialAdministrativoDTO.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasHistorialSectorial(HistorialSectorialDTO historialSectorialDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_SECTORIAL_CODIGO_HIS_SECTORIAL,
				historialSectorialDTO.getCodigoHisSectorial(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_HISTORIAL_SECTORIAL,
							PaqueteMensajeEnum.ERRORES, historialSectorialDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_SECTORIAL_NOMBRE, historialSectorialDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_HISTORIAL_SECTORIAL,
							PaqueteMensajeEnum.ERRORES, historialSectorialDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_SECTORIAL_ACTIVO, historialSectorialDTO.getActivo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ACTIVO_HISTORIAL_SECTORIAL,
							PaqueteMensajeEnum.ERRORES, historialSectorialDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.HISTORIAL_SECTORIAL_FECHA_INICIO,
				historialSectorialDTO.getFechaInicio(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_INICIO_HISTORIAL_SECTORIAL,
							PaqueteMensajeEnum.ERRORES, historialSectorialDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param informacionPresupuestalDTO Objecto DTO a validar
	 * @param camposAValidar             Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARCHIVO_PROCESADO_ID_ARCHIVO,
				archivoProcesadoDTO.getIdArchivo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_ARCHIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, archivoProcesadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARCHIVO_PROCESADO_DETALLE, archivoProcesadoDTO.getDetalle(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_DETALLE_VACIO,
							PaqueteMensajeEnum.ERRORES, archivoProcesadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARCHIVO_PROCESADO_ESTADO, archivoProcesadoDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, archivoProcesadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.ARCHIVO_PROCESADO_FECHA_CARGUE,
				archivoProcesadoDTO.getFechaCargue(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_CARGUE_VACIO,
							PaqueteMensajeEnum.ERRORES, archivoProcesadoDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * 
	 * @param funcionarioClaveEntidadDTO
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerReglasCrearFuncionarioClaveEntidad(
			FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO, Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD,
				funcionarioClaveEntidadDTO.getIdFuncionarioEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD,
				funcionarioClaveEntidadDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE,
				funcionarioClaveEntidadDTO.getNombre(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION,
				funcionarioClaveEntidadDTO.getIdLsFuncion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO,
				funcionarioClaveEntidadDTO.getIdLsGenero(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CARGO,
				funcionarioClaveEntidadDTO.getCargo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CARGO,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CORREO,
				funcionarioClaveEntidadDTO.getCorreo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CORREO,
							PaqueteMensajeEnum.ERRORES, funcionarioClaveEntidadDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra todos los errores de validacion que contiene un objeto
	 * segun los campos requeridos
	 *
	 * @param usuariosDTO    Objecto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasConsultarUsuario(UsuariosDTO usuariosDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_USUARIO, usuariosDTO.getNombreUsuario(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_USUARIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, usuariosDTO.getCorreo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_CORREO_ELECTRONICO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_NOMBRE, usuariosDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_NOMBRE,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_CODIGO_TIPO_USUARIO,
				usuariosDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_CODIGO_TIPO_USUARIO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_CODIGO_ENTIDAD, usuariosDTO.getCodigoEntidad(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_CODIGO_ENTIDAD,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.SEG_USUARIO_ESTADO, usuariosDTO.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_SEG_USUARIO_ESTADO,
							PaqueteMensajeEnum.ERRORES, usuariosDTO.getLenguaje())));
		}

		return resultado;

	}

	public static List<CampoValidacionDTO> obtenerReglasPddMeta(PddMetaDTO pddMetaDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_ID_META, pddMetaDTO.getIdMeta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_META_ID_META,
							PaqueteMensajeEnum.ERRORES, pddMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_ID_ESPECIFICO, pddMetaDTO.getIdEspecifico(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_META_ID_ESPECIFICO,
							PaqueteMensajeEnum.ERRORES, pddMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_ID_TIPO_META_LS, pddMetaDTO.getIdTipoMetaLs(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_META_ID_TIPO_META_LS,
							PaqueteMensajeEnum.ERRORES, pddMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_MAGNITUD, pddMetaDTO.getMagnitud(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_META_MAGNITUD,
							PaqueteMensajeEnum.ERRORES, pddMetaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_META, pddMetaDTO.getMeta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_META_META,
							PaqueteMensajeEnum.ERRORES, pddMetaDTO.getLenguaje())));
		}

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasPddObraConcreta(PddObraConcretaDTO pddObraConcretaDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_CONCRETA,
				pddObraConcretaDTO.getIdConcreta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_ID_CONCRETA,
							PaqueteMensajeEnum.ERRORES, pddObraConcretaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_META, pddObraConcretaDTO.getIdMeta(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_ID_META,
							PaqueteMensajeEnum.ERRORES, pddObraConcretaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_OBRA_CONCRETA_OBRA_CONCRETA,
				pddObraConcretaDTO.getObraConcreta(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_OBRA_CONCRETA,
							PaqueteMensajeEnum.ERRORES, pddObraConcretaDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * 
	 * @param pddProblematicaDTO
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddProblematica(PddProblematicaDTO pddProblematicaDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_ID_PROBLEMATICA,
				pddProblematicaDTO.getIdProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_PROBLEMATICA,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_ID_COMPROMISO,
				pddProblematicaDTO.getIdCompromiso(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_COMPROMISO,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_ID_LS_LOCALIZACION,
				pddProblematicaDTO.getIdLsLocalizacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LS_LOCALIZACION,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_ID_LS_SUBLOCALIZACION,
				pddProblematicaDTO.getIdLsSublocalizacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LS_SUBLOCALIZACION,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_ID_LZ_UPZ_UPR,
				pddProblematicaDTO.getIdLzUpzUpr(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LZ_UPZUPR,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_PROBLEMATICA,
				pddProblematicaDTO.getProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_PROBLEMATICA,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_CAUSAS, pddProblematicaDTO.getCausas(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_CAUSA,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_CONSECUENCIAS,
				pddProblematicaDTO.getConsecuencias(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_CONSECUENCIA,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_DESCRIPCION,
				pddProblematicaDTO.getDescripcion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_DESCRIPCION,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PROBLEMATICA_OBJETIVO, pddProblematicaDTO.getObjetivo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PROBLEMATICA_OBJETIVO,
							PaqueteMensajeEnum.ERRORES, pddProblematicaDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * 
	 * @param camposAValidar
	 * @param campo
	 * @param valor
	 * @param tipo
	 * @return
	 */
	public static boolean validarCampo(Map<String, Boolean> camposAValidar, String campo, Object valor, int tipo) {
		boolean flag = tipo == TIPO_TEXTO_VACIO ? NHSPDDValidadores.esTextoVacio(valor) : false;
		return camposAValidar.containsKey(campo) ? flag : false;
	}

	/**
	 * 
	 * @param campo
	 * @param mensaje
	 * @param lenguaje
	 * @param valor
	 * @param tamano
	 * @return
	 */
	public static List<CampoValidacionDTO> validarCampoDeTexto(List<String> mensaje, String lenguaje, Object valor,
			Integer... tamano) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (NHSPDDValidadores.esTextoVacio(valor)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(mensaje.get(0), PaqueteMensajeEnum.ERRORES, lenguaje)));

		} else if (NHSPDDValidadores.esValidoLongitud(valor.toString(), tamano[0], tamano[1])) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(mensaje.get(1), PaqueteMensajeEnum.ERRORES, lenguaje)));
		}
		return resultado;
	}

	/**
	 * 
	 * @param pddPrbPoblacionDTO
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddPbrPoblacion(PddPrbPoblacionDTO pddPrbPoblacionDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PBR_POBLACION_ID_POBLACION,
				pddPrbPoblacionDTO.getIdPoblacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PBR_ID_POBLACION,
							PaqueteMensajeEnum.ERRORES, pddPrbPoblacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PBR_POBLACION_DESCRIPCION,
				pddPrbPoblacionDTO.getDescripcion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PBR_DESCRIPCION,
							PaqueteMensajeEnum.ERRORES, pddPrbPoblacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PBR_POBLACION_ID_PROBLEMATICA,
				pddPrbPoblacionDTO.getIdProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PBR_ID_PROBLEMATICA,
							PaqueteMensajeEnum.ERRORES, pddPrbPoblacionDTO.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasPddPrbIndicador(PddPrbIndicadorDTO pddPrbIndicadorDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROB_IND,
				pddPrbIndicadorDTO.getIdProbInd(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_PROBIND,
							PaqueteMensajeEnum.ERRORES, pddPrbIndicadorDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_INDICADOR_ID_INDICADOR,
				pddPrbIndicadorDTO.getIdIndicador(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_PROBIND,
							PaqueteMensajeEnum.ERRORES, pddPrbIndicadorDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROBLEMATICA,
				pddPrbIndicadorDTO.getIdProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_PROBIND,
							PaqueteMensajeEnum.ERRORES, pddPrbIndicadorDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pddCompromisoDTO objeto DTO a validar
	 * @param camposAValidar   Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddMetaResultado(PddMetaResultadoDTO pddMetaResultadoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_ID_META_RESULTADO,
				pddMetaResultadoDTO.getIdMetaResultado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_META_RESULTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_COMPLEMENTO,
				pddMetaResultadoDTO.getComplemento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_COMPLEMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_ES_FORMULACION,
				pddMetaResultadoDTO.getEsFormulacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ES_FORMULACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_TIPO_ANUALIZACION,
				pddMetaResultadoDTO.getIdLsTipoAnualizacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_TIPO_ANUALIZACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_UNIDAD_MEDIDA,
				pddMetaResultadoDTO.getIdLsUnidadMedida(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_UNIDAD_MEDIDA_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_VERBO,
				pddMetaResultadoDTO.getIdLsVerbo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_VERBO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_RESULTADO_MAGNITUD,
				pddMetaResultadoDTO.getMagnitud(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MAGNITUD_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaResultadoDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * 
	 * @param pddIndicadorDTO
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddIndicadorYPrbIndicador(PddPrbIndicadorDTO pddIndicadorDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROBLEMATICA,
				pddIndicadorDTO.getIdProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROBLEMATICA_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_ID_INDICADOR, pddIndicadorDTO.getIdIndicador(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_INDICADOR_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_NOMBRE, pddIndicadorDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_TIPO, pddIndicadorDTO.getTipo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_TIPO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_LINEA_BASE_DESC,
				pddIndicadorDTO.getLineaBaseDesc(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_LINEA_BASE_DES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_FUENTE, pddIndicadorDTO.getFuente(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FUENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_YEAR_LINEA_BASE,
				pddIndicadorDTO.getYearLineaBase(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_LINEA_BASE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_INFORMACION_SOPORTE,
				pddIndicadorDTO.getInformacionSoporte(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_INFORMACION_SOPORTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * 
	 * @param pddIndicadorDTO
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerReglasPddIndicador(PddIndicadorDTO pddIndicadorDTO,
			Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_ID_INDICADOR, pddIndicadorDTO.getIdIndicador(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_INDICADOR_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_NOMBRE, pddIndicadorDTO.getNombre(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_TIPO, pddIndicadorDTO.getTipo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_TIPO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_LINEA_BASE_DESC,
				pddIndicadorDTO.getLineaBaseDesc(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_LINEA_BASE_DES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_FUENTE, pddIndicadorDTO.getFuente(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FUENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_YEAR_LINEA_BASE,
				pddIndicadorDTO.getYearLineaBase(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_LINEA_BASE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_INDICADOR_INFORMACION_SOPORTE,
				pddIndicadorDTO.getInformacionSoporte(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_INFORMACION_SOPORTE_VACIO,
							PaqueteMensajeEnum.ERRORES, pddIndicadorDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param pdlDTO         objeto DTO a validar
	 * @param camposAValidar Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasPdl(PdlDTO pdlDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_NOMBRE_PLAN, pdlDTO.getNombrePlan(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_PLAN_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_IDLSADOPTADO, pdlDTO.getIdLsAdoptado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDLSADOPTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_ACTO_ADMINISTRATIVO, pdlDTO.getActoAdministrativo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ACTO_ADMINISTRATIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_FECHA_ACTO, pdlDTO.getFechaActo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_FECHA_ACTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_YEAR_INICIO, pdlDTO.getYearInicio(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_INICIO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_YEAR_FINAL, pdlDTO.getYearFinal(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_YEAR_FINAL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_ID_PLAN_DESARROLLO, pdlDTO.getIdPlanDesarrollo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.COD_ENTIDAD, pdlDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlDTO.getLenguaje())));
		}
		return resultado;
	}

	/**
	 * Metodo que encuentra los errores de validacion que contiene un objeto segun
	 * los campos requeridos
	 *
	 * @param bpProyectoInversionDTO objeto DTO a validar
	 * @param camposAValidar         Campos a validar
	 * @return Lista de errores de validacion la cual estara vacia si el objeto pasa
	 *         todas las validaciones
	 */
	public static List<CampoValidacionDTO> obtenerReglasProyectoInversionTABIndentificacionProyecto(
			BpProyectoInversionDTO bpProyectoInversionDTO, Map<String, Boolean> camposAValidar) {

		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_PROYECTO_INVERSION,
				bpProyectoInversionDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_PROYECTO,
				bpProyectoInversionDTO.getCodigoProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_NOMBRE_PROYECTO,
				bpProyectoInversionDTO.getNombreProyecto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_PROYECTO_BPIN,
				bpProyectoInversionDTO.getCodigoBpin(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_PROYECTO_BPIN_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_NOMBRE_PROYECTO_BPIN,
				bpProyectoInversionDTO.getNombreBpin(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_NOMBRE_PROYECTO_BPIN_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_LS_ETAPA,
				bpProyectoInversionDTO.getIdLsEtapa(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_ETAPA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_ENTIDAD,
				bpProyectoInversionDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_BLOQUEO,
				bpProyectoInversionDTO.getBloqueo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BLOQUEO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_LS_ESTADO,
				bpProyectoInversionDTO.getIdLsEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROYECTO_INVERSION_TIPOS,
				bpProyectoInversionDTO.getIdsTipoProy(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_TIPOS_PROYECTOS_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyectoInversionDTO.getLenguaje())));
		}

		return resultado;

	}

	public static List<CampoValidacionDTO> obtenerReglasIniciativaCiudadana(
			BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_INICIATIVA,
				bpIniciativaCiudadanaDTO.getIdIniciativa(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CODIGO,
				bpIniciativaCiudadanaDTO.getCodigoIn(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CODIGO_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION,
				bpIniciativaCiudadanaDTO.getAlternativaSolucion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_APLICA_LINEA,
				bpIniciativaCiudadanaDTO.getAplicaLinea(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_APLICA_LINEA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL,
				bpIniciativaCiudadanaDTO.getCodicionesPoblacionales(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_EMAIL_PROP,
				bpIniciativaCiudadanaDTO.getEmailProp(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_EMAIL_PROP_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_FECHA_RADICADO,
				bpIniciativaCiudadanaDTO.getFechaRad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_FECHA_RADICADO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_FECHA_CODIGO,
				bpIniciativaCiudadanaDTO.getFechaCodigo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_FECHA_CODIGO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_GRUPOS_ETARIOS,
				bpIniciativaCiudadanaDTO.getGruposEtarios(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_FECHA_RADICADO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LC_LINEA_INV,
				bpIniciativaCiudadanaDTO.getIdLcLineaInv(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_LINEA_INV_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPR,
				bpIniciativaCiudadanaDTO.getIdLcTerritorializacionUpr(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPR_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPZ,
				bpIniciativaCiudadanaDTO.getIdLcTerritorializacionUpz(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPZ_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA,
				bpIniciativaCiudadanaDTO.getIdLsEstadoInicia(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN,
				bpIniciativaCiudadanaDTO.getIdLsOrigen(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_IDENTIFICACION_PROP,
				bpIniciativaCiudadanaDTO.getIdentificacionProp(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_IDENTIFICACION_PROP_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE_PROP,
				bpIniciativaCiudadanaDTO.getNombreProp(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE,
				bpIniciativaCiudadanaDTO.getNombre(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NOMBRE_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO,
				bpIniciativaCiudadanaDTO.getNumeroRad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_OBSERVACION,
				bpIniciativaCiudadanaDTO.getObservacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_OBSERVACION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_PROBLEMATICA,
				bpIniciativaCiudadanaDTO.getProblematica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_PROBLEMATICA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_RADICADO,
				bpIniciativaCiudadanaDTO.getRadicado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_RADICADO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_TELEFONO_PROP,
				bpIniciativaCiudadanaDTO.getTelefonoProp(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_TEL_PROP_INICIATIVA_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION,
				bpIniciativaCiudadanaDTO.getTotalPoblacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD,
				bpIniciativaCiudadanaDTO.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, bpIniciativaCiudadanaDTO.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasBpProyInvAporte(BpProyInvAporteDTO BpProyInvAporteDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_APORTE_ID_PROY_INV_APORTE,
				BpProyInvAporteDTO.getIdProyAporte(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_PROY_INV_APORTE_VACIO,
							PaqueteMensajeEnum.ERRORES, BpProyInvAporteDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_APORTE_ID_APORTE,
				BpProyInvAporteDTO.getIdAporte(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_APORTE_VACIO,
							PaqueteMensajeEnum.ERRORES, BpProyInvAporteDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_APORTE_ID_PROY_INVERSION,
				BpProyInvAporteDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_PROY_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, BpProyInvAporteDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_APORTE_IDS_APORTES,
				BpProyInvAporteDTO.getIdsAportes(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_IDS_APORTES_VACIO,
							PaqueteMensajeEnum.ERRORES, BpProyInvAporteDTO.getLenguaje())));
		}

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasBpProyInvIniciativa(
			BpProyInvIniciativaDTO bpProyInvIniciativaDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_INICIATIVA_ID_PROY_INVERSION,
				bpProyInvIniciativaDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_PROY_INVERSION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvIniciativaDTO.getLenguaje())));
		}

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasBpAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_APORTE,
				bpAporteCiudadanoDTO.getIdAporte(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_APORTE_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_CONSECUTIVO,
				bpAporteCiudadanoDTO.getConsecutivo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_CONSECUTIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_ACCION, bpAporteCiudadanoDTO.getAccion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ACCION_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_FUENTE, bpAporteCiudadanoDTO.getFuente(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_FUENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_NIVEL_ATRIBUTO_PDD,
				bpAporteCiudadanoDTO.getIdNivelAtributoPdd(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_NIVEL_PDD_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_ARCHIVO,
				bpAporteCiudadanoDTO.getIdArchivo(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_ARCHIVO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpAporteCiudadanoDTO.getLenguaje())));
		}

		return resultado;
	}
	
	public static List<CampoValidacionDTO> obtenerReglasBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_PROY_POLITICA,
				bpProyInvPoliticaDTO.getIdProyPolitica(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoliticaDTO.getLenguaje())));
		}
		
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_POL_PUB,
				bpProyInvPoliticaDTO.getIdPolPub(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoliticaDTO.getLenguaje())));
		}	
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_PROY_INVERSION,
				bpProyInvPoliticaDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoliticaDTO.getLenguaje())));
		}	

		return resultado;
	}
	
	public static List<CampoValidacionDTO> obtenerReglasBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_LINEA_ID_PROY_LINEA,
				bpProyInvLineaDTO.getIdProyLinea(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvLineaDTO.getLenguaje())));
		}
		
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_LINEA_ID_LINEA_INVERSION,
				bpProyInvLineaDTO.getIdLineaInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvLineaDTO.getLenguaje())));
		}
		
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_LINEA_ID_PROY_INVERSION,
				bpProyInvLineaDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvLineaDTO.getLenguaje())));
		}			

		return resultado;
	}
	
	public static List<CampoValidacionDTO> obtenerReglasBpProyInvPmr(BpProyInvPmrDTO bpProyInvPmrDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_PMR_ID_PRY_PMR,
				bpProyInvPmrDTO.getIdProyPmr(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPmrDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_PMR_ID_IND_MR,
				bpProyInvPmrDTO.getIdIndMr(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPmrDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_PMR_ID_PROY_INVERSION,
				bpProyInvPmrDTO.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_CAMPO_VACIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPmrDTO.getLenguaje())));
		}		

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasPdlNivel(PdlNivelDTO pdlNivelDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ID_PDL_NIVEL, pdlNivelDTO.getIdPdlNivel(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PDL_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_CODIGO_NIVEL, pdlNivelDTO.getCodNivel(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDL_CODIGO_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_DESCRIPCION, pdlNivelDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDL_DESCRIPCION_NIVEL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDL_NIVEL_ID_PLAN_LOCAL, pdlNivelDTO.getIdPlanLocal(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION__PDL_ID_PDL_VACIO,
							PaqueteMensajeEnum.ERRORES, pdlNivelDTO.getLenguaje())));
		}

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasIpPot(PotDTO potDTO, Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_ID_POT, potDTO.getIdPot(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_ID_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_CODIGO_POT, potDTO.getCodigoPot(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_CODIGO_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_ACTO_ADMINISTRATIVO, potDTO.getActoAdministrativo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_ACTO_ADMINISTRATIVO_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_ID_LS_ADOPTADO, potDTO.getIdLsAdoptado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_ID_LS_ADOPTADO_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_YEAR_INICIO, potDTO.getYearInicio(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_YEAR_INICIO_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_YEAR_FIN, potDTO.getYearFin(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_YEAR_FIN_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_FECHA, potDTO.getFecha(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_FECHA_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_URL, potDTO.getUrl(), TIPO_TEXTO_VACIO)) {

			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_URL_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potDTO.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasRamaPot(PotRamaDTO potRamaDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_RAMA_ID_RAMA_POT, potRamaDTO.getIdPotRama(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_RAMA_ID_RAMA,
							PaqueteMensajeEnum.ERRORES, potRamaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_RAMA_NUMERO_RAMA_POT, potRamaDTO.getNumeroRama(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_RAMA_NUMERO_RAMA,
							PaqueteMensajeEnum.ERRORES, potRamaDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_RAMA_ID_POT, potRamaDTO.getIdPot(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_RAMA_ID_POT,
							PaqueteMensajeEnum.ERRORES, potRamaDTO.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasNivelPot(PotNivelDTO potNivelDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_NIVEL_ID_NIVEL, potNivelDTO.getIdNivelPot(),
				TIPO_TEXTO_VACIO)) {
			NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_NIVEL_ID_NIVEL,
					PaqueteMensajeEnum.ERRORES, potNivelDTO.getLenguaje());
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_NIVEL_DESCRIPCION, potNivelDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_NIVEL_DESCRIPICION,
					PaqueteMensajeEnum.ERRORES, potNivelDTO.getLenguaje());
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_NIVEL_ID_NIVEL_PADRE, potNivelDTO.getIdNivelPadre(),
				TIPO_TEXTO_VACIO)) {
			NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_NIVEL_ID_NIVEL_PADRE,
					PaqueteMensajeEnum.ERRORES, potNivelDTO.getLenguaje());
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_NIVEL_ID_RAMA_POT, potNivelDTO.getIdRamaPot(),
				TIPO_TEXTO_VACIO)) {
			NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_NIVEL_ID_RAMA_POT,
					PaqueteMensajeEnum.ERRORES, potNivelDTO.getLenguaje());
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.IP_POT_NIVEL_NUMERO_NIVEL, potNivelDTO.getNumeroNivel(),
				TIPO_TEXTO_VACIO)) {
			NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IP_POT_NIVEL_NUMERO_NIVEL,
					PaqueteMensajeEnum.ERRORES, potNivelDTO.getLenguaje());
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasPotObra(PotObraDTO potObraDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_ID_OBRA_PROY_POT, potObraDTO.getIdObraProyPot(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_ID_OBRA_PROY_POT,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_OBRA, potObraDTO.getObra(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_OBRA,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_CODIGO, potObraDTO.getCodigoPotObra(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_CODIGO,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_ID_LS_PLAZO, potObraDTO.getIdLsPlazo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_ID_LS_PLAZO,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_ID_NIVEL_POT, potObraDTO.getIdNivelPot(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_ID_NIVEL_POT,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS,
				potObraDTO.getCodigoEntidadConcatenados(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS,
							PaqueteMensajeEnum.ERRORES, potObraDTO.getLenguaje())));
		}

		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerReglasPotInstrumento(PotInstrumentoDTO potInstrumentoDTO,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_INSTRUMENTO_ID_INSTRUMENTO_POT,
				potInstrumentoDTO.getIdInstrumentoPot(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_INSTRUMENTO_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_INSTRUMENTO_CODIGO,
				potInstrumentoDTO.getCodigoPotInstrumento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_INSTRUMENTO_CODIGO_VACIO,
							PaqueteMensajeEnum.ERRORES, potInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_INSTRUMENTO_DENOMINACION,
				potInstrumentoDTO.getIdLsDenominacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_POT_INSTRUMENTO_DENOMINACION_VACIO,
							PaqueteMensajeEnum.ERRORES, potInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_INSTRUMENTO_ID_POT, potInstrumentoDTO.getIdPot(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_POT_VACIO,
							PaqueteMensajeEnum.ERRORES, potInstrumentoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.POT_INSTRUMENTO_ID_LS_NIVEL_INST,
				potInstrumentoDTO.getIdLsNivelInst(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(
							NHSPDDConstantes.MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_LS_NIVEL_INST_VACIO,
							PaqueteMensajeEnum.ERRORES, potInstrumentoDTO.getLenguaje())));
		}

		return resultado;
	}

	/**
	 * 
	 * @param pddMetaProducto
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerPddMetaProducto(PddMetaProductoDTO pddMetaProducto,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_META_PRODUCTO,
				pddMetaProducto.getIdMetaProducto(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_META_PRODUCTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_META_RESULTADO,
				pddMetaProducto.getIdMetaResultado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_META_RESULTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_BLOQUEAR_PPI,
				pddMetaProducto.getBloqueaPpi(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BLOQUEAR_PPI_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_COMPLEMENTO,
				pddMetaProducto.getComplemento(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_COMPLEMENTO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ES_FORMULACION,
				pddMetaProducto.getEsFormulacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ES_FORMULACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_ESTADO,
				pddMetaProducto.getIdLsEstado(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}

		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_TIPO_ANUALIZACION,
				pddMetaProducto.getIdLsTipoAnualizacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_TIPO_ANUALIZACION_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_UNIDAD_MEDIDA,
				pddMetaProducto.getIdLsUnidadMedida(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_UNIDAD_MEDIDA_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_VERBO, pddMetaProducto.getIdLsVerbo(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_LS_VERBO_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_MAGNITUD, pddMetaProducto.getMagnitud(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MAGNITUD_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_META_PDD, pddMetaProducto.getMetaPdd(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_META_PDD_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_OBSERVACIONES,
				pddMetaProducto.getObservaciones(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_OBSERVACIONES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_META_PRODUCTO_PONDERACION,
				pddMetaProducto.getPonderacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_OBSERVACIONES_VACIO,
							PaqueteMensajeEnum.ERRORES, pddMetaProducto.getLenguaje())));
		}
		return resultado;

	}

	/**
	 * 
	 * @param peticion
	 * @param camposAValidar
	 * @return
	 */
	public static List<CampoValidacionDTO> obtenerPddMpEntidad(PddMpEntidadDTO peticion,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_ENTIDAD_ID_MP_ENTIDAD, peticion.getIdMpEntidad(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PDD_MP_ENTIDAD_ID_MP_ENTIDAD,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_ENTIDAD_ID_META_PRODUCTO, peticion.getIdMetaProducto(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_META_PRODUCTO_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_ENTIDAD_CODIGO_ENTIDAD, peticion.getCodigoEntidad(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_ENTIDAD_MAGNITUD, peticion.getMagnitud(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MAGNITUD_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_ENTIDAD_ESTADO, peticion.getEstado(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ESTADO_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		return resultado;
	}

	public static List<CampoValidacionDTO> obtenerPddMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion,
			Map<String, Boolean> camposAValidar) {
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_ID_MP_IND_ENTIDAD,
				peticion.getIdMpIndEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_MP_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_ID_META_PROD_IND,
				peticion.getIdMetaProdInd(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_META_PRODUCTO_INDICADOR,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_CODIGO_ENTIDAD,
				peticion.getCodigoEntidad(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_MP_ENTIDAD_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_MAGNITUD, peticion.getMagnitud(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_MAGNITUD_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_PONDERACION,
				peticion.getPonderacion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_PONDERACION_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		return resultado;
	}


	public static List<CampoValidacionDTO> obtenerReglasBpProyInvFinancia(BpProyInvFinanciaDTO peticion,
			Map<String, Boolean> camposAValidar) {
		
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if(validarCampo(camposAValidar,NHSPDDConstantes.BP_PROY_INV_FINANCIA_LS_FUENTE , peticion.getIdLsFuente(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_IDLS_FUENTE_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		
		if(validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_FINANCIA_ID_PROY, peticion.getIdProyInversion(), TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_ID_PROYECTO_VACIO,
							PaqueteMensajeEnum.ERRORES, peticion.getLenguaje())));
		}
		
		return resultado;

	}


	
	public static List<CampoValidacionDTO> obtenerReglasBpProyInvPoblacion(BpProyInvPoblacionDTO bpProyInvPoblacionDTO,Map<String, Boolean> camposAValidar){
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POBLACION_NUMERO, bpProyInvPoblacionDTO.getNumero(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_NUMERO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoblacionDTO.getLenguaje())));
		}
//		
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_PROYECTO_INVERSION, bpProyInvPoblacionDTO.getIdProyInversion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_PROYECTO_INVERSION,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoblacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_LS_ETARIO, bpProyInvPoblacionDTO.getIdLsEtario(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_LS_ETARIO,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoblacionDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_POBLACION, bpProyInvPoblacionDTO.getIdPoblacion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_POBLACION,
							PaqueteMensajeEnum.ERRORES, bpProyInvPoblacionDTO.getLenguaje())));
		}
		

		return resultado;
	}
	
	public static List<CampoValidacionDTO> obtenerReglasBpProyInvEtnico(BpProyInvEtnicoDTO bpProyInvEtnicoDTO,Map<String, Boolean> camposAValidar){
		List<CampoValidacionDTO> resultado = new ArrayList<>();
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_ETNICO_ID, bpProyInvEtnicoDTO.getIdEtnico(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_ETNICO,
							PaqueteMensajeEnum.ERRORES, bpProyInvEtnicoDTO.getLenguaje())));
		}
//		
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_ETNICO_ID_LS_ETNICO, bpProyInvEtnicoDTO.getIdLsEtnico(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_LS_ETNICO,
							PaqueteMensajeEnum.ERRORES, bpProyInvEtnicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_ETNICO_NUMERO, bpProyInvEtnicoDTO.getNumero(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_NUMERO,
							PaqueteMensajeEnum.ERRORES, bpProyInvEtnicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_ETNICO_ID_POBLACION, bpProyInvEtnicoDTO.getIdPoblacion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_POBLACION,
							PaqueteMensajeEnum.ERRORES, bpProyInvEtnicoDTO.getLenguaje())));
		}
		if (validarCampo(camposAValidar, NHSPDDConstantes.BP_PROY_INV_ETNICO_DESCRIPCION, bpProyInvEtnicoDTO.getDescripcion(),
				TIPO_TEXTO_VACIO)) {
			resultado.add(new CampoValidacionDTO(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO,
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_DESCRIPCION,
							PaqueteMensajeEnum.ERRORES, bpProyInvEtnicoDTO.getLenguaje())));
		}
		

		return resultado;
	}
<<<<<<< HEAD
	
=======
>>>>>>> developer

}
