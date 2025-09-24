package co.gov.sdp.nhspdd.common.validador;

import java.util.HashMap;
import java.util.Map;

import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;

/**
 * Clase para obtener los campos a validar por endpoint
 *
 * @author Raul Londono Murillo
 *
 */
public class NHSPDDCamposValidar {

	private NHSPDDCamposValidar() {
		super();
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * cambiarClave del modulo de autenticacion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cambiarClaveObtenerCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_ID, true);
		resultado.put(NHSPDDConstantes.USUARIO_CLAVE_ANTERIOR, true);
		resultado.put(NHSPDDConstantes.USUARIO_CLAVE_NUEVA, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint asignar
	 * componente del modulo administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> asignarComponenteUsuario() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_USUARIO, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_COMPONENTE_GESTION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint remover
	 * componente del modulo administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> removerComponenteUsuario() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPONENTE_GESTION_USUARIO_ID_GESTION_USUARIO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * iniciarSesion del modulo de autenticacion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> iniciarSesionObtenerCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_CORREO, true);
		resultado.put(NHSPDDConstantes.USUARIO_CLAVE, true);
		resultado.put(NHSPDDConstantes.USUARIO_TIPO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endopoint
	 * restablecerCorreo del modulo de autenticacion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> restablcerCorreoCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_CORREO, true);
		resultado.put(NHSPDDConstantes.USUARIO_USUARIO, true);
		resultado.put(NHSPDDConstantes.USUARIO_IDENTIFICACION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * crearProyectoInversion del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearProyectoInversionCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PROYECTO_INVERSION_NOMBRE, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * asignarProyectoInversion del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> asignarProyectoInversionUsuarioCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_PROYECTO_INVERSION, true);
		resultado.put(NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_USUARIO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * removerProyectoInversionCamposValidacion del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> removerProyectoInversionCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PROYECTOS_INVERSION_USUARIO_ID_PROYECTO_USUARIO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * obtener usuario por componente del modulo administracion
	 *
	 * @return mapa con campos a validar
	 */
	public static Map<String, Boolean> obtenerUsuarioComponente() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_USUARIO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * crearEntidad del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearEntidadCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ENTIDAD_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.ENTIDAD_ID_LS_CLASIFICACION, true);
		resultado.put(NHSPDDConstantes.ENTIDAD_ID_LS_ASOCIACION, true);
		resultado.put(NHSPDDConstantes.ENTIDAD_ID_BANCO_PROYECTO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * asignarUsuarioEntidad del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> asignarEntidadUsuarioCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_ENTIDAD_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.USUARIO_ENTIDAD_USUARIO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * removerUsuarioEntidad del modulo de administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> removerUsuarioEntidadCamposValidacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.USUARIO_ENTIDAD_ID_USUARIO_ENTIDAD, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * crear lista simple del modulo administrar listas simples
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearListaSimple() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.LISTA_SIMPLE_NOMBRE, true);
		resultado.put(NHSPDDConstantes.LISTA_SIMPLE_DESCRIPCION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint de
	 * modificar lista simple del modulo administrar listas simples
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarListaSimple() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.LISTA_SIMPLE_ID_LISTA_SIMPLE, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar argumento lista simple del modulo administrar listas simples
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarArgumentoListaSimple() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ARGUMENTO, true);
		resultado.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ESTADO, true);
		resultado.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_LISTA_SIMPLE, true);
		resultado.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_RESULTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar argumento lista simple del modulo administrar listas simples
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarArgumentoListaSimple() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ARGUMENTO_LISTA_SIMPLE_ID_ARGUMENTO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * crearComponenteGasto del modulo administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearComponenteGasto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_CODIGO_COMPONENTE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_NOMBRE_COMPONENTE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ARGUMENTO_LISTA_SIMPLE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificarComponenteGasto del modulo administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarComponenteGasto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ID_COMPONENTE_GASTO, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_CODIGO_COMPONENTE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_NOMBRE_COMPONENTE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ARGUMENTO_LISTA_SIMPLE, true);
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * desactivarComponenteGasto del modulo administracion
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cambiarEstadoComponenteGasto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPONENTE_GASTO_ID_COMPONENTE_GASTO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pot proyecto instrumento del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPotProyectoInstrumento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_INSTRUMENTO, true);
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_POT_PROYECTO, true);
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar pot proyecto instrumento del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPotProyectoInstrumento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_PROYECTO_INSTRUMENTO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar el estado del pot proyecto instrumento del modulo administrar
	 * listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEstadoPotProyectoInstrumento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ID_PROYECTO_INSTRUMENTO, true);
		resultado.put(NHSPDDConstantes.POT_PROYECTO_INSTRUMENTO_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar linea de inversion del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarLineaInverision() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_CONCEPTO, true);
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ESTABLECIDO, true);
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_FECHA, true);
//		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ID_LS_SECTOR, true);
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar linea de inversion del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarLineaInversion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ID_LINEA_INVERSION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar el estado de una linea de inversion del modulo administrar listas
	 * compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEstadoLineaInversion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ID_LINEA_INVERSION, true);
		resultado.put(NHSPDDConstantes.LINEA_INVERSION_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pddPrbValoracion del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddPrbValoracion() {
		Map<String, Boolean> resultado = new HashMap<>();
		//resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_GRAVEDAD, true);
		//resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_DURACION, true);
		//resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_IMPACTO, true);
		//resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_DEBILIDAD, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_BALANCE_INICIAL, true);
		// resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_OBSERVACIONES, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_MOMENTO, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_SECTOR, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_DIMENSION, true);
		// resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_1, true);
		// resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_LS_COMPETENCIA_2, true);
		// resultado.put(NHSPDDConstantes.PDD_PRB_VALORACION_ID_PROBLEMATICA, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar competencia asociada del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddCompetenciaAsociada() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_COMPETENCIA_ASOCIADA_ID_LS_COMPETENCIA, true);
		resultado.put(NHSPDDConstantes.PDD_COMPETENCIA_ASOCIADA_ID_LS_SECTOR, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar compromiso estrategico del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarCompromisoEstrategico() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_ESTADO, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSTEMATICA, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar CompromisoEstrategico del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarCompromisoEstrategico() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_ID_COMPROMISO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pdd compromiso especifico del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddCompromisoEspecifico() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_COMPROMISO, true);
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_DESCRIPCION, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar pdd compromiso especifico del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddCompromisoEspecifico() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_COMPROMISO, true);
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_DESCRIPCION, true);

		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ESPEDIFICO_ID_ESPECIFICO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pdd compromiso del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddCompromiso() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ID_PLAN_DESARROLLO, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSTEMATICA, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar pdd compromiso del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddCompromiso() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ID_COMPROMISO, true);
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ID_PLAN_DESARROLLO, true);
		resultado.put(NHSPDDConstantes.PDD_COMPROMISO_ID_ESTRATEGICO, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSTEMATICA, true);
		resultado.put(NHSPDDConstantes.COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar plan de desarrollo distrital del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPdd() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_NOMBRE_PLAN, true);
		resultado.put(NHSPDDConstantes.PDD_SIGLA_PLAN, true);
		resultado.put(NHSPDDConstantes.PDD_YEAR_INICIO, true);
		resultado.put(NHSPDDConstantes.PDD_YEAR_FINAL, true);
		resultado.put(NHSPDDConstantes.PDD_PROGRAMA_GOBIERNO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar pdd del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPdd() {
		Map<String, Boolean> resultado = registrarPdd();
		resultado.put(NHSPDDConstantes.PDD_ID_PLAN_DESARROLLO, true);

		resultado.put(NHSPDDConstantes.PDD_NOMBRE_PLAN, true);
		resultado.put(NHSPDDConstantes.PDD_SIGLA_PLAN, true);
		resultado.put(NHSPDDConstantes.PDD_YEAR_INICIO, true);
		resultado.put(NHSPDDConstantes.PDD_YEAR_FINAL, true);
		resultado.put(NHSPDDConstantes.PDD_PROGRAMA_GOBIERNO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar nivel de plan de desarrollo distrital del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddNivel() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ID_PLAN_DESARROLLO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar plan de desarrollo local del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPdl() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDL_NOMBRE_PLAN, true);
		resultado.put(NHSPDDConstantes.PDL_IDLSADOPTADO, true);
		resultado.put(NHSPDDConstantes.PDL_ACTO_ADMINISTRATIVO, true);
		resultado.put(NHSPDDConstantes.PDL_FECHA_ACTO, true);
		resultado.put(NHSPDDConstantes.PDL_YEAR_INICIO, true);
		resultado.put(NHSPDDConstantes.PDL_YEAR_FINAL, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar nivel de plan de desarrollo local del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPdlNivel() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ID_PLAN_LOCAL, true);
		return resultado;
	}

	/**
	 * registrar nivel atributo del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddNivelAtributo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_NUMERO, true);
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_DENOMINACION, true);
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_PONDERACION, true);
		
		return resultado;
	}

	/**
	 * registrar nivel atributo del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPdlNivelAtributo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NUMERO, true);
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_DENOMINACION, true);
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_NOMBRE_GERENTE, true);
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_CORREO_GERENTE, true);
		resultado.put(NHSPDDConstantes.PDL_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar nivel atributo del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddNivelAtributo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO, true);
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_NUMERO, true);
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_DENOMINACION, true);
		resultado.put(NHSPDDConstantes.PDD_NIVEL_ATRIBUTO_PONDERACION, true);
		
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar territorializacion del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarTerritorializacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.TERRITORIALIZACION_ID_LS_LOCALIDAD, true);
		resultado.put(NHSPDDConstantes.TERRITORIALIZACION_ESTADO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar territorializacion del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarTerritorializacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.TERRITORIALIZACION_ID_TERRITORIALIZACION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar el estado de una terriotrializacion del modulo administrar listas
	 * compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEstadoTerritorializacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.TERRITORIALIZACION_ID_TERRITORIALIZACION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar consecutivo del modulo listas simples,parametros y consecutivos
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarConsecutivo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.CONSECUTIVO_SECUENCIA, true);
		resultado.put(NHSPDDConstantes.CONSECUTIVO_ID_PLAN_DESARROLLO, true);
		resultado.put(NHSPDDConstantes.CONSECUTIVO_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.CONSECUTIVO_NOMBRE, true);
		resultado.put(NHSPDDConstantes.CONSECUTIVO_VIGENCIA, true);
		resultado.put(NHSPDDConstantes.CONSECUTIVO_ENTIDAD, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * una estructura meta del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearEstructuraMeta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ESTADO, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_PD, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_PI, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_VERBO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar consecutivo del modulo listas simples,parametros y consecutivos
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarConsecutivo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.CONSECUTIVO_ID_CONSECUTIVO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar estado del consecutivo del modulo listas simples,parametros y
	 * consecutivos
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEstadoConsecutivo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.CONSECUTIVO_ID_CONSECUTIVO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar parametro general del modulo listas simples,parametros y
	 * consecutivos
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarParametroGeneral() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PARAMETRO_GENERAL_CODIGO_PARAMETRO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar una estructura meta del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEstructuraMeta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_ESTRUCTURA_METAS, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ESTADO, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_PD, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_PI, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_LS_VERBO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * respuesta del modulo Buzon de mensajeria interno
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> darRepuestaBuzon() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_ID_NOTIFICACION, true);
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_RESPUESTA, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * mensaje del modulo Buzon de mensajeria interno
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearMensaje() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ASUNTO, true);
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_MENSAJE, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * mensaje del modulo Buzon de mensajeria interno
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarMensaje() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ID_CONFIG_NOTIFICACION, true);
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_ASUNTO, true);
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_MENSAJE, true);
		resultado.put(NHSPDDConstantes.CONFIGURACION_NOTIFICACION_REQUIERE_ACCION, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint cambiar
	 * estado de una estructura meta del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cambiarEstadoEstructuraMeta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ESTRUCTURA_METAS_ID_ESTRUCTURA_METAS, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * un equipamiento del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearEquipamiento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ESTADO, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_NOMBRE, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_LS_CATEGORIA, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_LS_SECTOR, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar un equipamiento del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarEquipamiento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_EQUIPAMIENTO, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ESTADO, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_NOMBRE, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_LS_CATEGORIA, true);
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_LS_SECTOR, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint cambiar
	 * estado de un equipamiento del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cambiarEstadoEquipamiento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.EQUIPAMIENTO_ID_EQUIPAMIENTO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint cambiar
	 * estado de una informacion presupuestal del modulo administrar listas
	 * compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cambiarEstadoInformacionPresupuestal() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_INFO_PRESUPUESTAL, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * una informacion presupuestal del modulo administrar listas compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearInformacionPresupuestal() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_YEAR, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_MES, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_CLASIFICACION_PRESUPUESTAL, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_DISTRITAL, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_GIROS_VIGENCIA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_PROYECTO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_RECURSOS_SUSPENDIDOS, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_INTERNO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CONSTITUCION_RESERVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_NOMBRE_PROYECTO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROPIACION_RESERVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROBACION_DEFINITIVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCUIN_GIRO_RESERVAS, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCION_VIGENCIA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_PLAN_DESARROLLO, true);

		// resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ORIGEN, true);
		// resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_ARCHIVO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar una informacion presupuestal del modulo administrar listas
	 * compuestas
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarInformacionPresupuestal() {
		Map<String, Boolean> resultado = crearInformacionPresupuestal();
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_INFO_PRESUPUESTAL, true);

		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_YEAR, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_MES, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_CLASIFICACION_PRESUPUESTAL, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_DISTRITAL, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_GIROS_VIGENCIA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_PROYECTO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_RECURSOS_SUSPENDIDOS, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CODIGO_INTERNO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_CONSTITUCION_RESERVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_NOMBRE_PROYECTO, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROPIACION_RESERVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_APROBACION_DEFINITIVA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCUIN_GIRO_RESERVAS, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_EJECUCION_VIGENCIA, true);
		resultado.put(NHSPDDConstantes.INFORMACION_PRESUPUESTAL_ID_PLAN_DESARROLLO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint cargar
	 * archivo plano del modulo carga archivo
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> cargarArchivoPlano() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_USUARIO, true);
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_TEMA, true);
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_MODULO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint obtener
	 * archivo plano del modulo carga archivo
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> obtenerArchivoPlano() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_TEMA, true);
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_MODULO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint obtener
	 * archivo plano por id del modulo carga archivo
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> obtenerArchivoPlanoPorId() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.ARCHIVO_PROCESADO_ID_ARCHIVO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * historial administrativo
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearHistorialAdministrativo() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_CODIGO_HIS_ADMIN, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_NOMBRE, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_SIGLA, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_FECHA_INICIO, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_FECHA_FINAL, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_ACTO_ADMINISTRATIVO, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_ADMINISTRATIVO_CODIGO_ENTIDAD, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * historial sectorial
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearHistorialSectorial() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_CODIGO_HIS_SECTORIAL, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_NOMBRE, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_ACTO_ADMINISTRATIVO, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_ACTIVO, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_FECHA_INICIO, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_FECHA_FINAL, true);
		resultado.put(NHSPDDConstantes.HISTORIAL_SECTORIAL_CODIGO_ENTIDAD, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * funcionario clave entidad
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> crearFuncionarioClaveEntidad() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CARGO, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CORREO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint crear
	 * funcionario clave entidad
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarFuncionarioClaveEntidad() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CARGO, true);
		resultado.put(NHSPDDConstantes.FUNCIONARIO_CLAVE_ENTIDAD_CORREO, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * consultar usuario
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> consultarUsuarios() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.SEG_USUARIO_USUARIO, true);
		resultado.put(NHSPDDConstantes.SEG_USUARIO_CORREO_ELECTRONICO, true);
		resultado.put(NHSPDDConstantes.SEG_USUARIO_NOMBRE, true);
		resultado.put(NHSPDDConstantes.SEG_USUARIO_CODIGO_TIPO_USUARIO, true);
		resultado.put(NHSPDDConstantes.SEG_USUARIO_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.SEG_USUARIO_ESTADO, true);
		return resultado;

	}

	/**
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBuzonNotificacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_USUARIO_ORIGINA, true);
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_MENSAJE, true);
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_ROLES, true);
		resultado.put(NHSPDDConstantes.BUZON_NOTIFICACIONES_MENSAJE, true);
		return resultado;
	}

	/**
	 * 
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddMeta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_ID_ESPECIFICO, true);
		resultado.put(NHSPDDConstantes.PDD_META_ID_TIPO_META_LS, true);
		resultado.put(NHSPDDConstantes.PDD_META_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_META_META, true);

		return resultado;
	}

	public static Map<String, Boolean> registrarPddObraConcreta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_META, true);
		resultado.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_OBRA_CONCRETA, true);

		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> modificarObraConcreta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_CONCRETA, true);
		resultado.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_ID_META, true);
		resultado.put(NHSPDDConstantes.PDD_OBRA_CONCRETA_OBRA_CONCRETA, true);

		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> modificarPddMeta() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_ID_META, true);
		resultado.put(NHSPDDConstantes.PDD_META_ID_ESPECIFICO, true);
		resultado.put(NHSPDDConstantes.PDD_META_ID_TIPO_META_LS, true);
		resultado.put(NHSPDDConstantes.PDD_META_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_META_META, true);

		return resultado;
	}

	/**
	 * Metodo que retorna un mapa con los campos obligatorios para registar una
	 * problematica.
	 * 
	 * @return el mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddProblematica() {
		Map<String, Boolean> resultado = new HashMap<>();
		
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_PROBLEMATICA, true);
		
	
		return resultado;
	}

	/**
	 * Metodo que retorna un mapa con los campos obligatorios para modificar una
	 * problematica.
	 * 
	 * @return el mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddProblematica() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_PROBLEMATICA, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_COMPROMISO, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_LS_LOCALIZACION, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_LS_SUBLOCALIZACION, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_ID_LZ_UPZ_UPR, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_PROBLEMATICA, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_OBJETIVO, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_CAUSAS, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_CONSECUENCIAS, true);
		resultado.put(NHSPDDConstantes.PDD_PROBLEMATICA_DESCRIPCION, true);
		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> registrarPddPbrPoblacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_PBR_POBLACION_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.PDD_PBR_POBLACION_ID_PROBLEMATICA, true);

		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> modificarPddPbrPoblacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_PBR_POBLACION_ID_POBLACION, true);
		resultado.put(NHSPDDConstantes.PDD_PBR_POBLACION_DESCRIPCION, true);
		resultado.put(NHSPDDConstantes.PDD_PBR_POBLACION_ID_PROBLEMATICA, true);

		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> registrarPddPrbIndicador() {
		Map<String, Boolean> resultado = new HashMap<>();

		resultado.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_INDICADOR, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROBLEMATICA, true);

		return resultado;
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Boolean> modificarPddPrbIndicador() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROB_IND, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_INDICADOR, true);
		resultado.put(NHSPDDConstantes.PDD_PRB_INDICADOR_ID_PROBLEMATICA, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pdd meta resultado del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddMetaResultado() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_COMPLEMENTO, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ES_FORMULACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_TIPO_ANUALIZACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_VERBO, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_MAGNITUD, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar pdd meta resultado del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddMetaResultado() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_META_RESULTADO, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_COMPLEMENTO, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ES_FORMULACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_TIPO_ANUALIZACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_ID_LS_VERBO, true);
		resultado.put(NHSPDDConstantes.PDD_META_RESULTADO_MAGNITUD, true);

		return resultado;
	}

	/**
	 * Metodo que retorna un mapa con los campos obligatorios para registar un
	 * indicador
	 * 
	 * @return el mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPddIndicador() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_NOMBRE, true);
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_LINEA_BASE_DESC, true);
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_FUENTE, true);

		return resultado;
	}

	/**
	 * Metodo que retorna un mapa con los campos obligatorios para modificar
	 * indicador.
	 * 
	 * @return el mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPddIndicador() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_ID_INDICADOR, true);
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_NOMBRE, true);
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_LINEA_BASE_DESC, true);
		resultado.put(NHSPDDConstantes.PDD_INDICADOR_FUENTE, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar pdd compromiso especifico del modulo ip
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarProyectoInversionTABIndentificacionProyecto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_PROYECTO, true);
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_NOMBRE_PROYECTO, true);
		//resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_PROYECTO_BPIN, true);
		//resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_NOMBRE_PROYECTO_BPIN, true);
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_LS_ETAPA, true);
		//resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_ID_LS_ESTADO, true);
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_BLOQUEO, true);
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_TIPOS, true);
		// resultado.put(NHSPDDConstantes.BP_PROYECTO_INVERSION_NOMBRE_PROYECTO_POAI, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validación para el endpoint de
	 * registrar iniciativa ciudadana para el modulo de bp
	 * 
	 * @return
	 */
	public static Map<String, Boolean> registrarIniciativaCiudadana() {
		Map<String, Boolean> resultado = new HashMap<>();

		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_RADICADO, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_FECHA_RADICADO, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_APLICA_LINEA, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_PROBLEMATICA, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_GRUPOS_ETARIOS, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_NOMBRE_PROP, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL, true);
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD, true);
		return resultado;
	}

	/**
	 * Metodo que crea con los campos validación del endpoint de editar iniciativa
	 * ciudadana para el modulo de bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarIniciativaCiudadana() {
		Map<String, Boolean> resultado = registrarIniciativaCiudadana();
		resultado.put(NHSPDDConstantes.BP_INICIATIVA_CIUDADANA_ID_INICIATIVA, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvAporte del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpProyInvAporte() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_APORTE_ID_PROY_INVERSION, true);
		//resultado.put(NHSPDDConstantes.BP_PROY_INV_APORTE_IDS_APORTES, true);
		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvIniciativa del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpProyInvIniciativa() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_INICIATIVA_ID_PROY_INVERSION, true);
		
		return resultado;
	}
	
	public static Map<String, Boolean> registrarBpProyInvFinancia(){
		Map<String, Boolean> resultado = new HashMap<>();
		//resultado.put(NHSPDDConstantes.BP_PROY_INV_FINANCIA_LS_FUENTE, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_FINANCIA_ID_PROY, true);
		
		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar PotObra del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarPotObra() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_OBRA_CODIGO, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_OBRA, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_ID_LS_PLAZO, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_ID_NIVEL_POT, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS, true);

		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar PotObra del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPotObra() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_OBRA_ID_OBRA_PROY_POT, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_CODIGO, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_OBRA, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_ID_LS_PLAZO, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_ID_NIVEL_POT, true);
		resultado.put(NHSPDDConstantes.POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS, true);

		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint obtener
	 * PotIntrumento del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> obtenerPotIntrumentoPorIdPot() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_POT, true);

		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar PotIntrumento del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> registraPotInstrumento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_CODIGO, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_DENOMINACION, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_POT, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_LS_NIVEL_INST, true);

		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar PotIntrumento del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarPotInstrumento() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_INSTRUMENTO_POT, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_CODIGO, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_DENOMINACION, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_POT, true);
		resultado.put(NHSPDDConstantes.POT_INSTRUMENTO_ID_LS_NIVEL_INST, true);
		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar IpPot del modulo ip
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarIpPot() {
		Map<String, Boolean> resultado = new HashMap<>();

		resultado.put(NHSPDDConstantes.IP_POT_CODIGO_POT, true);
		resultado.put(NHSPDDConstantes.IP_POT_ACTO_ADMINISTRATIVO, true);
		resultado.put(NHSPDDConstantes.IP_POT_FECHA, true);
		resultado.put(NHSPDDConstantes.IP_POT_ID_LS_ADOPTADO, true);
		resultado.put(NHSPDDConstantes.IP_POT_YEAR_FIN, true);
		resultado.put(NHSPDDConstantes.IP_POT_YEAR_INICIO, true);

		resultado.put(NHSPDDConstantes.IP_POT_ESTADO, true);
		return resultado;
	}

	public static Map<String, Boolean> modificarIpPot() {
		Map<String, Boolean> resultado = new HashMap<>();

		resultado.put(NHSPDDConstantes.IP_POT_ID_POT, true);
		resultado.put(NHSPDDConstantes.IP_POT_CODIGO_POT, true);
		resultado.put(NHSPDDConstantes.IP_POT_ACTO_ADMINISTRATIVO, true);
		resultado.put(NHSPDDConstantes.IP_POT_FECHA, true);
		resultado.put(NHSPDDConstantes.IP_POT_ID_LS_ADOPTADO, true);
		resultado.put(NHSPDDConstantes.IP_POT_YEAR_FIN, true);
		resultado.put(NHSPDDConstantes.IP_POT_YEAR_INICIO, true);

		resultado.put(NHSPDDConstantes.IP_POT_ESTADO, true);
		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validación para el endpoint
	 * registrar PotRama del modulo ip
	 * 
	 * @return
	 */
	public static Map<String, Boolean> registrarRamaPot() {
		Map<String, Boolean> resultado = new HashMap<>();

		resultado.put(NHSPDDConstantes.IP_RAMA_ID_POT, true);
		return resultado;
	}

	/**
	 * metodo que crea un mapa con los campos de validación para el endpoit
	 * registrar PotNivel para el modulo de ip
	 * 
	 * @return
	 */
	public static Map<String, Boolean> registrarPotNivel() {
		Map<String, Boolean> resultado = new HashMap<String, Boolean>();
		resultado.put(NHSPDDConstantes.IP_POT_NIVEL_ID_RAMA_POT, true);
		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpAporteCiudadano del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpAporteCiudadano() {
		Map<String, Boolean> resultado = new HashMap<>();
		// resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_APORTE, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_CONSECUTIVO, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ACCION, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_FUENTE, true);
		//resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_NIVEL_ATRIBUTO_PDD, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_ARCHIVO, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvPolitica del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpProyInvPolitica() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_POL_PUB, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar BpProyInvPolitica del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarBpProyInvPolitica() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_PROY_POLITICA, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_POL_PUB, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POLITICA_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvLinea del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpProyInvLinea() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_LINEA_ID_LINEA_INVERSION, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_LINEA_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar BpProyInvLinea del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarBpProyInvLinea() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_LINEA_ID_PROY_LINEA, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_LINEA_ID_LINEA_INVERSION, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_LINEA_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvPmr del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarBpProyInvPmr() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_PMR_ID_IND_MR, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_PMR_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar BpProyInvPmr del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarBpProyInvPmr() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_PMR_ID_PRY_PMR, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_PMR_ID_IND_MR, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_PMR_ID_PROY_INVERSION, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpAporteCiudadano del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarBpAporteCiudadano() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_APORTE, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_CONSECUTIVO, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ACCION, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_FUENTE, true);
		//resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_NIVEL_ATRIBUTO_PDD, true);
		resultado.put(NHSPDDConstantes.BP_APORTE_CIUDADANO_ID_ARCHIVO, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos a validar del enpoint de registrar
	 * meta producto
	 * 
	 * @return retorna un mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarMetaProducto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_META_RESULTADO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_VERBO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_COMPLEMENTO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_TIPO_ANUALIZACION, true);
		//resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_PONDERACION, true);
		//resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_ESTADO, true);
		//resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_OBSERVACIONES, true);

		return resultado;
	}

	/**
	 * Metodoq ue crea un mapa con las validaciones de modificar meta producto
	 * 
	 * @return retorna un mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarMetaProducto() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_META_PRODUCTO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_META_RESULTADO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_VERBO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_COMPLEMENTO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_TIPO_ANUALIZACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_UNIDAD_MEDIDA, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_PONDERACION, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_ID_LS_ESTADO, true);
		resultado.put(NHSPDDConstantes.PDD_META_PRODUCTO_OBSERVACIONES, true);

		return resultado;
	}

	/**
	 * Metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpAporteCiudadano del modulo BP
	 *
	 * @return Mapa con los campos a validar
	 */
	public static Map<String, Boolean> copiarEstructuraPddToPdl() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDL_ID_PLAN_DESARROLLO, true);
		resultado.put(NHSPDDConstantes.PDL_CODIGO_ENTIDAD, true);

		return resultado;
	}

	/**
	 * Metodo que valida el registrar de una entidad meta producto
	 * 
	 * @return un mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarMpEntidad() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_MP_ENTIDAD_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.PDD_MP_ENTIDAD_ID_META_PRODUCTO, true);
		resultado.put(NHSPDDConstantes.PDD_MP_ENTIDAD_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_MP_ENTIDAD_ESTADO, true);

		return resultado;
	}

	/**
	 * Metodo que valida el modificar de una entidad meta producto
	 * 
	 * @return un mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarMpEntidad() {
		Map<String, Boolean> resultado = registrarMpEntidad();
		resultado.put(NHSPDDConstantes.PDD_MP_ENTIDAD_ID_MP_ENTIDAD, true);
		return resultado;
	}

	public static Map<String, Boolean> registrarMpIndicadorEntidad() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_CODIGO_ENTIDAD, true);
		resultado.put(NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_ID_META_PROD_IND, true);
		resultado.put(NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_MAGNITUD, true);
		resultado.put(NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_PONDERACION, true);

		return resultado;
	}

	public static Map<String, Boolean> modificarMpIndicadorEntidad() {
		Map<String, Boolean> resultado = registrarMpIndicadorEntidad();
		resultado.put(NHSPDDConstantes.PDD_MP_INDICADOR_ENTIDAD_ID_MP_IND_ENTIDAD, true);
		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvPoblacion del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> registrarProyInvPoblacionAsociadoAProyInv() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_NUMERO, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_PROYECTO_INVERSION, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_LS_ETARIO, true);

		return resultado;
	}
	
	/**
	 * Metodo que crea un mapa con los campos de validacion para consultar los BpProyInvEtnico
	 * Asociados a un BpProyInvPoblacion
	 * @return
	 */
	public static Map<String, Boolean> listarTodosBpProyInvEtnicoAsociadosABpProyInvPoblacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_POBLACION, true);

		return resultado;
	}
	
	/**
	 * Metodo para validar los campos asociados a un BpProyInvEtnico
	 * @return
	 */
	public static Map<String, Boolean> registrarProyInvEtnico() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_ID_LS_ETNICO, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_NUMERO, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_ID_POBLACION, true);
		

		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar BpProyInvPoblacion del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarProyInvPoblacionAsociadoAProyInv() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_NUMERO, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_POBLACION, true);

		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * modificar BpProyInvEtnico del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> modificarProyInvEtnicoAsociadoABpProyInvPoblacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_NUMERO, true);
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_ID, true);

		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * eliminar BpProyInvPoblacion del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> eliminarProyInvPoblacionAsociadoAProyInv() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_POBLACION_ID_POBLACION, true);

		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * eliminar BpProyInvEtnico del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
	public static Map<String, Boolean> eliminarProyInvEtnicoAsociadoAProyInvPoblacion() {
		Map<String, Boolean> resultado = new HashMap<>();
		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_ID, true);

		return resultado;
	}
	
	/**
	 * metodo que crea un mapa con los campos de validacion para el endpoint
	 * registrar BpProyInvEtnico del modulo bp
	 * 
	 * @return mapa con los campos a validar
	 */
//	public static Map<String, Boolean> registrarProyInvEtnicoAsociadoAProyInv() {
//		Map<String, Boolean> resultado = new HashMap<>();
//		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_NUMERO, true);
//		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_DESCRIPCION, true);
//		resultado.put(NHSPDDConstantes.BP_PROY_INV_ETNICO_ID_POBLACION, true);
//
//		return resultado;
//	}

}
