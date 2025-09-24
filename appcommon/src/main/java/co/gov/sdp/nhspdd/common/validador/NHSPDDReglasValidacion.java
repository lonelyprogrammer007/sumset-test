package co.gov.sdp.nhspdd.common.validador;

import java.util.Map;

import co.gov.sdp.nhspdd.common.dto.ArchivoProcesadoDTO;
import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
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

/**
 * Clase fachada para los metodos de validacion
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
public class NHSPDDReglasValidacion {

	private NHSPDDReglasValidacion() {
		super();
	}

	/**
	 * Metodo para validar el DTO de usuario
	 *
	 * @param usuariosDTO    Objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarUsuario(UsuariosDTO usuariosDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasUsuario(usuariosDTO, camposAValidar));
	}

	/**
	 * Metodo para validadr el DTO de componenteGestionUsuario
	 *
	 * @param componenteGesionUsuarioDTO Objeto DTO a validar
	 * @param camposAValidar             Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarComponenteGestionUsuario(
			ComponenteGestionUsuarioDTO componenteGesionUsuarioDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasComponenteGestionUsuario(componenteGesionUsuarioDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de lista simple
	 *
	 * @param listaSimpleDTOObjeto DTO a validar
	 * @param camposAValidar       Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarListaSimple(ListaSimpleDTO listaSimpleDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasListaSimple(listaSimpleDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de proyectos de inversion
	 *
	 * @param proyectosInversionDTO Objeto DTO a validar
	 * @param camposAValidar        Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarProyectoInversion(ProyectoInversionDTO proyectoInversionDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasProyectoInversion(proyectoInversionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de proyectos de inversion de usuario
	 *
	 * @param proyectosInversionUsuarioDTO Objeto DTO a validar
	 * @param camposAValidar               Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarProyectosInversionUsuario(
			ProyectosInversionUsuarioDTO proyectosInversionUsuarioDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasProyectosInversionUsuario(proyectosInversionUsuarioDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de entidad
	 *
	 * @param entidadDTO     Objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarEntidad(EntidadDTO entidadDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasEntidad(entidadDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de estructura meta
	 *
	 * @param estructuraMetaDTO Objeto DTO a validar
	 * @param camposAValidar    Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarEstrucuraMeta(EstructuraMetaDTO estructuraMetaDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasEstructuraMeta(estructuraMetaDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de usuario entidad
	 *
	 * @param usuarioEntidadDTO Objeto DTO a validar
	 * @param camposAValidar    Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidadDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasUsuarioEntidad(usuarioEntidadDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de argumento lista simple
	 *
	 * @param argumentoListaSimpleDTO Objeto DTO a validar
	 * @param camposAValidar          Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarArgumentoListaSimple(ArgumentoListaSimpleDTO argumentoListaSimpleDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasArgumentoListaSimple(argumentoListaSimpleDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de componente de gasto
	 *
	 * @param componenteGastoDTO Objeto DTO a validar
	 * @param camposAValidar     Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarComponenteGasto(ComponenteGastoDTO componenteGastoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasComponenteGasto(componenteGastoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de potProyectoInstrumento
	 *
	 * @param potProyectoInstrumentoDTO objeto DTO a validar
	 * @param camposAValidar            Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPotProyectoInstrumento(
			PotProyectoInstrumentoDTO potProyectoInstrumentoDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasPotProyectoInstrumento(potProyectoInstrumentoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de linea de inversion
	 *
	 * @param lineaDeInversionDTO objeto DTO a validar
	 * @param camposAValidar      Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarLineaInversion(LineaDeInversionDTO lineaDeInversionDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasLineaInversion(lineaDeInversionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de valoracion
	 *
	 * @param pddPrbValoracionDTO objeto DTO a validar
	 * @param camposAValidar      Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddPrbValoracion(PddPrbValoracionDTO pddPrbValoracionDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddPrbValoracion(pddPrbValoracionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de competencia asociada
	 *
	 * @param pddCompetenciaAsociadaDTO objeto DTO a validar
	 * @param camposAValidar            Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddCompetenciaAsociada(
			PddCompetenciaAsociadaDTO pddCompetenciaAsociadaDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasPddCompetenciaAsociada(pddCompetenciaAsociadaDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de compromiso estrategico
	 *
	 * @param compromisoEstrategicoDTO objeto DTO a validar
	 * @param camposAValidar           Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarCompromisoEstrategico(
			CompromisoEstrategicoDTO compromisoEstrategicoDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasCompromisoEstrategico(compromisoEstrategicoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de pddCompromisoEspecifico
	 *
	 * @param pddCompromisoEspecifico objeto DTO a validar
	 * @param camposAValidar          Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddCompromisoEspecifico(
			PddCompromisoEspecificoDTO pddCompromisoEspecifico, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasPddCompromisoEspecifico(pddCompromisoEspecifico, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de pddCompromiso
	 *
	 * @param pddCompromiso  objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddCompromiso(PddCompromisoDTO pddCompromiso,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddCompromiso(pddCompromiso, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo distrital
	 *
	 * @param pddDTO         objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPdd(PddDTO pddDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasPdd(pddDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo distrital
	 *
	 * @param pddDTO         objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddNivel(PddNivelDTO pddNivelDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddNivel(pddNivelDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo distrital
	 *
	 * @param pddNivelAtributoDTO objeto DTO a validar
	 * @param camposAValidar      Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddNivelAtributo(PddNivelAtributoDTO pddNivelAtributoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddNivelAtributo(pddNivelAtributoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo local
	 *
	 * @param pdlNivelAtributoDTO objeto DTO a validar
	 * @param camposAValidar      Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPdlNivelAtributo(PdlNivelAtributoDTO pdlNivelAtributoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPdlNivelAtributo(pdlNivelAtributoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de territorializacion
	 *
	 * @param territorializacionDTO objeto DTO a validar
	 * @param camposAValidar        Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarTerritorializacion(TerritorializacionDTO territorializacionDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasTerritorializacion(territorializacionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de consecutivo
	 *
	 * @param consecutivoDTO objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarConsecutivo(ConsecutivoDTO consecutivoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasConsecutivo(consecutivoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de consecutivo
	 *
	 * @param consecutivoDTO objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarParametroGeneral(ParametroGeneralDTO parametroGeneralDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasParametroGeneral(parametroGeneralDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de buzon notificaciones
	 *
	 * @param buzonNotificacionesDTO objeto DTO a validar
	 * @param camposAValidar         camposAValidar Mapa con los campos a validar
	 *
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBuzonNotificacion(BuzonNotificacionesDTO buzonNotificacionesDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBuzonNotificacion(buzonNotificacionesDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de configuracion notificacion
	 *
	 * @param configuracionNotificacionDTO objeto DTO a validar
	 * @param camposAValidar               camposAValidar Mapa con los campos a
	 *                                     validar
	 *
	 *
	 */
	public static NHSPDDResultadoValidacion validarConfiguracionNotificacion(
			ConfiguracionNotificacionDTO configuracionNotificacionDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasConfiguracionNotificacion(configuracionNotificacionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de equipamiento
	 *
	 * @param equipamientoDTO objeto DTO a validar
	 * @param camposAValidar  Mapa con los campos a validar
	 *
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarEquipamiento(EquipamientoDTO equipamientoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasEquipamiento(equipamientoDTO, camposAValidar));

	}

	/**
	 * Metodo para validar el DTO de equipamiento
	 *
	 * @param informacionPresupuestalDTO objeto DTO a validar
	 * @param camposAValidar             Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarInformacionPresupuestal(
			InformacionPresupuestalDTO informacionPresupuestalDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasInformacionPresupuestal(informacionPresupuestalDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de archivo plano
	 *
	 * @param archivoProcesadoDTO objeto DTO a validar
	 * @param camposAValidar      Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarArchivoProcesado(ArchivoProcesadoDTO archivoProcesadoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerArchivoProcesado(archivoProcesadoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de historial Administrativo
	 *
	 * @param historialAdministrativoDTO objeto DTO a validar
	 * @param camposAValidar             Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarCrearHistorialAdministrativo(
			HistorialAdministrativoDTO historialAdministrativoDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasHistorialAdministrativo(historialAdministrativoDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de historial Sectorial
	 *
	 * @param historialSectorialDTO objeto DTO a validar
	 * @param camposAValidar        Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarCrearHistorialSectorial(HistorialSectorialDTO historialSectorialDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasHistorialSectorial(historialSectorialDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de funcionario clave entidad
	 *
	 * @param funcionarioClaveEntidadDTO objeto DTO a validar
	 * @param camposAValidar             Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarCrearFuncionarioClaveEntidad(
			FuncionarioClaveEntidadDTO funcionarioClaveEntidadDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasCrearFuncionarioClaveEntidad(funcionarioClaveEntidadDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de usuario
	 * 
	 * @param usuariosDTO    objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion consultarUsuario(UsuariosDTO usuariosDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasConsultarUsuario(usuariosDTO, camposAValidar));

	}

	/**
	 * Metodo para validar PddMetas
	 * 
	 * @param pddMetaDTO     objeto dto a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddMeta(PddMetaDTO pddMetaDTO, Map<String, Boolean> camposAValidar) {

		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddMeta(pddMetaDTO, camposAValidar));
	}

	/**
	 * metodo para validar obras concretas
	 * 
	 * @param pddObraConcretaDTO objeto dto a validar
	 * @param camposAValidar     Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddObraConcreta(PddObraConcretaDTO pddObraConcretaDTO,
			Map<String, Boolean> camposAValidar) {

		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddObraConcreta(pddObraConcretaDTO, camposAValidar));
	}

	public static NHSPDDResultadoValidacion validarPddPbrPoblacion(PddPrbPoblacionDTO pddPrbPoblacionDTO,
			Map<String, Boolean> camposAValidar) {

		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddPbrPoblacion(pddPrbPoblacionDTO, camposAValidar));
	}

	/**
	 * Método para validar problematicas
	 * 
	 * @param pddProblematicaDTO
	 * @param camposAValidar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddProblematica(PddProblematicaDTO pddProblematicaDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddProblematica(pddProblematicaDTO, camposAValidar));
	}

	/**
	 * 
	 * @param pddPrbIndicadorDTO
	 * @param camposAValidar
	 * @return
	 */
	public static NHSPDDResultadoValidacion validarPddPrbIndicador(PddPrbIndicadorDTO pddPrbIndicadorDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddPrbIndicador(pddPrbIndicadorDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de pddCompromiso
	 *
	 * @param pddCompromiso  objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddMetaResultado(PddMetaResultadoDTO pddMetaResultadoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddMetaResultado(pddMetaResultadoDTO, camposAValidar));
	}

	/**
	 * Método para validar problematicas
	 * 
	 * @param pddProblematicaDTO
	 * @param camposAValidar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddIndicadorYPrbIndicador(PddPrbIndicadorDTO pddIndicadorDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddIndicadorYPrbIndicador(pddIndicadorDTO, camposAValidar));
	}

	/**
	 * Método para validar problematicas
	 * 
	 * @param pddProblematicaDTO
	 * @param camposAValidar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPddIndicador(PddIndicadorDTO pddIndicadorDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPddIndicador(pddIndicadorDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo local
	 *
	 * @param pdlDTO         objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPdl(PdlDTO pdlDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasPdl(pdlDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de plan de desarrollo distrital
	 *
	 * @param pddDTO         objeto DTO a validar
	 * @param camposAValidar Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPdlNivel(PdlNivelDTO pdlNivelDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPdlNivel(pdlNivelDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de pddCompromisoEspecifico
	 *
	 * @param pddCompromisoEspecifico objeto DTO a validar
	 * @param camposAValidar          Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarProyectoInversionTABIndentificacionProyecto(
			BpProyectoInversionDTO bpProyectoInversionDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl
				.obtenerReglasProyectoInversionTABIndentificacionProyecto(bpProyectoInversionDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el dto de iniciativa ciudadana
	 * 
	 * @param bpIniciativaCiudadanaDTO objeto dto a validar
	 * @param camposAValidar
	 * @return
	 */
	public static NHSPDDResultadoValidacion validarIniciativaCiudadana(
			BpIniciativaCiudadanaDTO bpIniciativaCiudadanaDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasIniciativaCiudadana(bpIniciativaCiudadanaDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de BpProyInvAporte
	 *
	 * @param bpProyInvAporteDTO objeto DTO a validar
	 * @param camposAValidar     Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvAporte(BpProyInvAporteDTO bpProyInvAporteDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvAporte(bpProyInvAporteDTO, camposAValidar));
	}
	
	/**
	 * Metodo para validar el DTO de BpProyInvIniciativaDTO
	 *
	 * @param bpProyInvIniciativaDTO objeto DTO a validar
	 * @param camposAValidar     Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvIniciativa(BpProyInvIniciativaDTO bpProyInvIniciativaDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvIniciativa(bpProyInvIniciativaDTO, camposAValidar));
	}

	/**
	 * Metodo para validar el DTO de POT
	 * 
	 * @param potDTO         objeto a validar
	 * @param camposAValidar mapa con los campos a validar
	 * @return si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion,sino, la lista contendra los respectivos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarIpPot(PotDTO potDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasIpPot(potDTO, camposAValidar));

	}

	/**
	 * Metodo para validar el DTO de POT_OBRA
	 * 
	 * @param potObraDTO     objeto a validar
	 * @param camposAValidar mapa con los campos a validar
	 * @return si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion,sino, la lista contendra los respectivos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPotObra(PotObraDTO potObraDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPotObra(potObraDTO, camposAValidar));

	}

	/**
	 * Metodo para validar el DTO de POT_OBRA
	 * 
	 * @param potObraDTO     objeto a validar
	 * @param camposAValidar mapa con los campos a validar
	 * @return si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion,sino, la lista contendra los respectivos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPotIntrumento(PotInstrumentoDTO potIntrumentoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasPotInstrumento(potIntrumentoDTO, camposAValidar));

	}

	/**
	 * Metodo para validar el dto de PotRama
	 * @param potRamaDTO objeto a validar
	 * @param camposAValidar mapa con los campos a validar
	 * @return si el objeto es valido retorna un objeto con la 
	 * lista vacia de mensajes de validacion,sino, la lista contendra los
	 * respectivos errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarPotRama(PotRamaDTO potRamaDTO,Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasRamaPot(potRamaDTO, camposAValidar));
	}
	
	public static NHSPDDResultadoValidacion validarPotNivel(PotNivelDTO potNivelDTO,Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasNivelPot(potNivelDTO, camposAValidar));
	}
	/**
	 * Metodo para validar el DTO de BpAporteCiudadano
	 *
	 * @param bpAporteCiudadanoDTO objeto DTO a validar
	 * @param camposAValidar       Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpAporteCiudadano(BpAporteCiudadanoDTO bpAporteCiudadanoDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpAporteCiudadano(bpAporteCiudadanoDTO, camposAValidar));

	}
	
	/**
	 * Metodo para validar el DTO de BpAporteCiudadano
	 *
	 * @param bpProyInvPoliticaDTO objeto DTO a validar
	 * @param camposAValidar       Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvPolitica(BpProyInvPoliticaDTO bpProyInvPoliticaDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvPolitica(bpProyInvPoliticaDTO, camposAValidar));

	}
	
	/**
	 * Metodo para validar el DTO de BpAporteCiudadano
	 *
	 * @param bpProyInvLineaDTO objeto DTO a validar
	 * @param camposAValidar       Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvLinea(BpProyInvLineaDTO bpProyInvLineaDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvLinea(bpProyInvLineaDTO, camposAValidar));

	}
	
	/**
	 * Metodo para validar el DTO de bpProyInvPmr
	 *
	 * @param bpProyInvPmrDTO objeto DTO a validar
	 * @param camposAValidar       Mapa con los campos a validar
	 * @return Si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion, sino, la lista contendra los respectvos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvPmr(BpProyInvPmrDTO bpProyInvPmrDTO,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvPmr(bpProyInvPmrDTO, camposAValidar));

	}

	/**
	 * 
	 * @param peticion
	 * @param camposAValidar
	 * @return
	 */
	public static NHSPDDResultadoValidacion validarpddMetaProducto(PddMetaProductoDTO peticion,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerPddMetaProducto(peticion, camposAValidar));
	}

	/**
	 * 
	 * @param peticion
	 * @param camposAValidar
	 * @return
	 */
	public static NHSPDDResultadoValidacion validarPddMpEntidad(PddMpEntidadDTO peticion,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerPddMpEntidad(peticion, camposAValidar));
	}

	public static NHSPDDResultadoValidacion validarPddMpIndicadorEntidad(PddMpIndicadorEntidadDTO peticion,
			Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerPddMpIndicadorEntidad(peticion,camposAValidar));
	}
	

	
	/**
	 * Metodo que permite validar campos de un registro para un BpProyInvFinancia
	 * @param peticion objeto de tipo BpProyInvFinanciaDTO que contiene la informacion a registrar
	 * @param camposAValidar
	 * @return
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvFinancia(BpProyInvFinanciaDTO peticion,
			Map<String, Boolean> camposAValidar) {
		
		return new NHSPDDResultadoValidacion(NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvFinancia(peticion, camposAValidar));
		
	}
	

	/**
	 * Metodo para validar el DTO de POT_OBRA
	 * 
	 * @param potObraDTO     objeto a validar
	 * @param camposAValidar mapa con los campos a validar
	 * @return si el objeto es valido retorna un objeto con la lista vacia de
	 *         mensajes de validacion,sino, la lista contendra los respectivos
	 *         errores asociados a las validaciones
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvPoblacion(BpProyInvPoblacionDTO bpProyInvPoblacionDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvPoblacion(bpProyInvPoblacionDTO, camposAValidar));

	}
	
	
	/*
	 * Metodo de validacion de campos de BpProyInvEtnico
	 */
	public static NHSPDDResultadoValidacion validarBpProyInvEtnico(BpProyInvEtnicoDTO bpProyInvEtnicoDTO, Map<String, Boolean> camposAValidar) {
		return new NHSPDDResultadoValidacion(
				NHSPDDReglasValidacionImpl.obtenerReglasBpProyInvEtnico(bpProyInvEtnicoDTO, camposAValidar));

	}

}
