package co.gov.sdp.nhspdd.common.util;

/**
 * Clase para manejar las constantes
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
public class NHSPDDConstantes {

	private NHSPDDConstantes() {
		super();
	}

	/**
	 * Seguridad Token
	 */
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";

	public static final String JWT_SECRETO = "#S4b4n4B0g.";
	public static final int JWT_EXPIRACION_MS_SESSION = 180000000;

	public static final String EVENTO_ACTUALIZAR = "ACTUALIZAR_USUARIO";
	public static final String TRANSACCION_ACTUALIZAR_USUARIO = "ACTUALIZAR_USUARIO";
	public static final char TIPO_CORE = 'C';
	public static final char TIPO_SEGURIDAD = 'S';
	/**
	 * Servicio Web NHSPDD
	 */

	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_APP_JSON = "application/json";
	public static final String HEADER_ACCEPT = "Accept";

	public static final String CONTEXTO_SEGURIDAD_API = "/BackSeguridad";
	public static final String PATH_SEG_API = "/api";
	public static final String PATH_SEG_API_ENTIDADES = "/entidad";
	public static final String PATH_SEG_API_SINCRONIZACION = "/sincronizacion";
	public static final String PATH_SEG_API_REST_AUT = "/autenticacion";
	public static final String PATH_SEG_API_REST_AUT_AUTENTICAR = "/autenticar";
	public static final String PATH_SEG_API_REST_AUT_RECORDAR_CONTRASENIA = "/recordarContraseniaAfs";
	public static final String PATH_SEG_API_REST_AUT_VALIDAR_CONTRASENIA = "/validarToken";
	public static final String PATH_SEG_API_REST_AUT_CAMBIAR_CONTRASENIA = "/cambiarContrasenia";
	public static final String PATH_SEG_API_REST_USUARIOS = "/usuarios";
	public static final String PATH_SEG_API_REST_USUARIOS_CONSULTARPORROL = "/consultarusuariosporrol";
	public static final String PATH_SEG_API_REST_CONSULTAR_ENTIDADES = "/consultaentidades";
	public static final String PATH_SEG_API_REST_TRAER_ROLES = "/roles";
	public static final String PATH_SEG_API_REST_AUDITORIA = "/auditoria";
	public static final String PATH_SEG_API_REST_CREAR_AUDITORIA = "/crear";

	public static final String MAPPER_RESPUESTA_SI = "Si";
	public static final String MAPPER_RESPUESTA_NO = "No";

	/**
	 * Properties
	 */
	public static final String CONFIG_FILE = "com.enviivo.common.messages.config";
	public static final String PROPERTIES_FILE = "com.enviivo.common.messages.messages";

	/**
	 * Codigo entidad usuarios internos
	 */
	public static final String CODIGO_ENTIDAD_INTERNO = "0143";

	/**
	 * Severidad del log
	 */
	public static final String SEVERIDAD_DEBUG = "debug";
	public static final String SEVERIDAD_ERROR = "error";
	public static final String SEVERIDAD_FATAL = "fatal";
	public static final String SEVERIDAD_INFO = "info";
	public static final String SEVERIDAD_TRACE = "trace";
	public static final String SEVERIDAD_WARN = "warn";

	/**
	 * Consecutivos
	 */
	public static final String CORE_CONSECUTIVO_TABLA_INICIATIVA_CIUDADANA = "Iniciativa Ciudadana";
	public static final String CORE_CONSECUTIVO_TABLA_BP_PROYECTO_INVERSION = "BpProyectoInversion";

	/**
	 * Nombres de tablas
	 */
	public static final String ARCHIVO_PROCESADO = "archivo_procesado";
	public static final String ARGUMENTO_LISTA_SIMPLE = "argumento_lista_simple";
	public static final String BANCO_DE_PROYECTOS = "banco_de_proyectos";
	public static final String BUZON_NOTIFICACIONES = "buzon_notificaciones";
	public static final String COMPONENTE_GASTO = "componente_gasto";
	public static final String COMPONENTE_GESTION = "componente_gestion";
	public static final String COMPONENTE_GESTION_USUARIO = "componente_gestion_usuario";
	public static final String CONFIGURACION_CARGUE_ARCHIVO = "configuracion_cargue_archivo";
	public static final String CONFIGURACION_NOTIFICACION = "configuracion_notificacion";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String ENTIDAD = "entidad";
	public static final String EQUIPAMENTO = "equipamento";
	public static final String ESTADO_SERVICIO = "estado_servicio";
	public static final String ESTRUCTURA_META = "estructura_meta";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD = "funcionario_clave_entidad";
	public static final String INFORMACION_PRESUPUESTAL = "informacion_presupuestal";
	public static final String LINEA_DE_INVERSION = "linea_de_inversion";
	public static final String LISTA_COMPUESTA = "lista_compuesta";
	public static final String LISTA_SIMPLE = "lista_simple";
	public static final String PARAMETRO_GENERAL = "parametro_general";
	public static final String PERMISO = "permiso";
	public static final String POT_PROYECTO_INSTRUMENTO = "pot_proyecto_instrumento";
	public static final String PROYECTO_INVERSION = "proyecto_inversion";
	public static final String PROYECTOS_INVERSION_USUARIO = "proyectos_inversion_usuario";
	public static final String ROLES = "roles";
	public static final String TERRITORIALIZACION = "territorializacion";
	public static final String TIPO_ARCHIVO = "tipo_archivo";
	public static final String USUARIOS = "usuarios";

	public static final String CORE_SEGURIDAD_ENTIDAD_PLANEACION = "Secretaria de planeación";
	/**
	 * Constantes controllers
	 */
	public static final String WEB_CONTROLLER_GET_CONSULTAR_LISTA_COMPUESTA = "/consultar_lista_compuesta";

	public static final String WEB_CONTROLLER_GET_RESTABLECER_CONTRASENA = "/restablecer";
	public static final String WEB_CONTROLLER_GET_RESTABLECER_CONTRASENA_MENSAJE = "/restablecer-mensaje";
	public static final String WEB_CONTROLLER_GET_CAMBIAR_CONTRASENA = "/cambiar-contrasena";
	public static final String WEB_CONTROLLER_GET_HOME = "/home";
	public static final String WEB_CONTROLLER_REQUEST_INDEX = "/";
	public static final String WEB_CONTROLLER_RETURN_RESTABLCER_CONTRASENA = "/autenticacion/restablecer-contrasena";
	public static final String WEB_CONTROLLER_RETURN_INDEX = "index";
	public static final String WEB_CONTROLLER_RETURN_HOME = "home";
	public static final String WEB_CONTROLLER_RETURN_RESTABLECER_CONTRASENA_MENSAJE = "/Autenticacion/restablecer-contrasena-mensaje";
	public static final String WEB_CONTROLLER_RETURN_CAMBIAR_CONTRASENA = "/autenticacion/cambiar-contrasena";

	public static final String WEB_RESOURCE_LISTA_CONSULTAR_CONTENIDO_LISTA_COMPUESTA = "afs/listas/consultar-contenido-lista-compuesta";

	public static final String CORE_CONTROLLER_GET_ENVIAR_CORREO = "/restablecer";
	public static final String CORE_CONTROLLER_POST_ASIGNAR_ENTIDAD = "";
	public static final String CORE_CONTROLLER_POST_ASIGNAR_PROYECTO_INVERSION = "";
	public static final String CORE_CONTROLLER_POST_ASIGNAR_COMPONENTE_GESTION_USUARIO = "/administracion/guardar_componente_usuario";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_COMPONENTE_GESTION = "/administracion/guardar_componente_gestion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTES_USUARIO = "/administracion/obtener_componente_gestion_usuario";
	public static final String CORE_CONTROLLER_ADMINISTRACION_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO = "/administracion/lista_componente_gestion_usuario";
	public static final String CORE_CONTROLLER_ADMINISTRACION_OBTENER_COMPONENTE_LIBRE = "/administracion/obtener_componente_disponible";
	public static final String CORE_CONTROLLER_POST_REMOVER_COMPONENTE_GESTION_USUARIO = "/administracion/remover_componente_usuario";

	// Seguridad
	public static final String CORE_CONTROLLER_GET_HISTORIAL_ADMINISTRATIVO = "/seguridad/historial_administrativo_consultar";
	public static final String CORE_CONTROLLER_POST_HISTORIAL_ADMINISTRATIVO = "/seguridad/historial_administrativo_registrar";
	public static final String CORE_CONTROLLER_GET_HISTORIAL_SECTORIAL = "/seguridad/historial_sectorial_consultar";
	public static final String CORE_CONTROLLER_POST_HISTORIAL_SECTORIAL = "/seguridad/historial_sectorial_registrar";

	// Archivo plano
	public static final String CORE_CONTROLLER_CARGA_GET_OBTENER_ARCHIVOS_PLANOS_TODOS = "/carga/obtener_archivos_planos";
	public static final String CORE_CONTROLLER_CARGA_POST_CARGAR_ARCHIVO_PLANO = "/carga/cargar_archivo_plano";
	public static final String CORE_CONTROLLER_CARGA_GET_OBTENER_CONFIGURACION_CARGA = "/carga/obtener_config_carga/{id}";
	public static final String ID_CONFIG_CARGUE = "idConfigCargue";
	// Entidad
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_ASIGNADOS = "/administracion/obtener_entidades_asignados";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_DISPONIBLES = "/administracion/obtener_entidades_disponibles";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_ENTIDADES_TODOS = "/administracion/obtener_entidades_todos";
	public static final String CORE_CONTROLLER_ADMINISTRACION_DELETE_REMOVER_ENTIDAD = "/administracion/remover_entidad";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_ENTIDAD = "/administracion/asignar_entidad";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_ENTIDAD = "/administracion/crear_entidad";

	// Listas Compuestas
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_LISTAS_COMPUESTAS = "/administracion/listacompuesta/obtener_listas_compuestas_todos";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_LISTA_COMPUESTA = "/administracion/listacompuesta/obtener_lista/{id}";

	// Proyectos de inversion
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_ASIGNADOS = "/administracion/obtener_proyectos_inversion_asignados";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_DISPONIBLES = "/administracion/obtener_proyectos_inversion_disponibles";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_PROYECTOS_INVERSION_TODOS = "/administracion/obtener_proyectos_inversion_todos";
	public static final String CORE_CONTROLLER_ADMINISTRACION_DELETE_REMOVER_PROYECTO_INVERSION = "/administracion/remover_proyecto_inversion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_ASIGNAR_PROYECTO_INVERSION = "/administracion/asignar_proyecto_inversion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_PROYECTO_INVERSION = "/administracion/crear_proyecto_inversion";

	// Componente Gestion
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_COMPONENTE_GESTION_TODOS = "/administracion/obtener_componente_gestion_todos";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_CREAR_COMPONENTE_GESTION = "/administracion/crear_componente_gestion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_REMOVER_COMPONENTE_GESTION_USUARIO = "/administracion/remover_componente_gestion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_PUT_MODIFICAR_COMPONENTE_GESTION = "/administracion/modificar_componente_gestion";

	// Componente Gasto
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_COMPONENTE_GASTO_TODOS = "/administracion/lista_compuesta/obtener_componente_gasto_todos";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_COMPONENTE_GASTO = "/administracion/lista_compuesta/crear_componente_gasto";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_CAMBIAR_ESTADO_COMPONENTE_GASTO = "/administracion/lista_compuesta/cambiar_estado_componente_gasto/{id}";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_COMPONENTE_GASTO = "/administracion/lista_compuesta/modificar_componente_gasto";

	// Equipamientos
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_EQUIPAMIENTOS_TODOS = "/administracion/lista_compuesta/obtener_equipamiento_todos";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_EQUIPAMIENTOS_TODOS_V2 = "/administracion/lista_compuesta/obtener_equipamiento_todos_V2";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_EQUIPAMIENTO = "/administracion/lista_compuesta/crear_equipamiento";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_CAMBIAR_ESTADO_EQUIPAMIENTO = "/administracion/lista_compuesta/cambiar_estado_equipamiento/{id}";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_EQUIPAMIENTO = "/administracion/lista_compuesta/modificar_equipamiento";

	// Estructura Metas
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_GET_OBTENER_ESTRUCTURA_METAS_TODOS = "/administracion/lista_compuesta/obtener_estructura_meta_todos";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_POST_CREAR_ESTRUCTURA_METAS = "/administracion/lista_compuesta/crear_estructura_meta";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_CAMBIAR_ESTADO_ESTRUCTURA_METAS = "/administracion/lista_compuesta/cambiar_estado_estructura_meta/{id}";
	public static final String CORE_CONTROLLER_LISTA_COMPUESTA_PUT_MODIFICAR_ESTRUCTURA_METAS = "/administracion/lista_compuesta/modificar_estructura_meta";

	// Informacion presupuestal
	public static final String CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_TODOS = "/presupuesto/obtener_informacion_presupuestal_todos";
	//public static final String CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_TODOS = "/presupuesto/obtener_informacion_presupuestal_todos";
	public static final String CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_DESAROLLO = "/presupuesto/obtener_informacion_presupuestal_plan_desarrollo/{codigo}";
	public static final String CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_INFORMACION_PRESUPUESTAL_ENTIDAD = "/presupuesto/obtener_informacion_presupuestal_entidad/{codigo}";
	public static final String CORE_CONTROLLER_PRESUPUESTO_POST_CREAR_INFORMACION_PRESUPUESTAL = "/presupuesto/crear_informacion_presupuestal";
	public static final String CORE_CONTROLLER_PRESUPUESTO_PUT_MODIFICAR_INFORMACION_PRESUPUESTAL = "/presupuesto/modificar_informacion_presupuestal";
	public static final String CORE_CONTROLLER_PRESUPUESTO_DELETE_ELIMINAR_INFORMACION_PRESUPUESTAL = "/presupuesto/eliminar_informacion_presupuestal/{id}";

	// Plan de desarrollo
	public static final String CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_PDD_TODOS = "/presupuesto/obtener_pdd_todos";

	// Autenticacion
	public static final String CORE_CONTROLLER_AUTENTICACION_POST_CAMBIAR_CLAVE = "/autenticacion/cambiar_clave";
	public static final String CORE_CONTROLLER_AUTENTICACION_POST_INICIAR_SESION = "/autenticacion/iniciar_sesion";
	public static final String CORE_CONTROLLER_RETURN_ENVIAR_CORREO = "redirect:/restablecer-mensaje";

	// Lista simple
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS = "/administracion_lista_simple/lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_LISTA = "/administracion_lista_simple/registrar_lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_LISTA = "/administracion_lista_simple/modificar_lista_simple";

	// Argumento lista simple
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE = "/administracion_lista_simple/registrar_argumento_lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE = "/administracion_lista_simple/modificar_argumento_lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_ARGUMENTO_LISTA_SIMPLE = "/administracion_lista_simple/obtener_argumento_lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ESTADO_ARGUMENTO_LISTA_SIMPLE = "/administracion_lista_simple/modificar_estado_argumento_lista_simple";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_LISTA_SIMPLE = "/administracion_lista_simple/obtener_por_id/{id}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_ARGUMENTO_LISTA_SIMPLE_POR_NOMBRE = "/administracion_lista_simple/obtener_argumento/{nombre}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_PUT_ARGUMENTO_LISTA_SIMPLE_MODIFICAR_ESTADO = "/administracion_lista_simple/modificar_estado_argumento/{id}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_LISTA_SIMPLE_POR_FILTRO = "/administracion_lista_simple/obtener_por_filtro";
	public static final String CORE_CONTROLLER_ADMINISTRACION_OBTENER_ARGUMENTO_LISTA_SIMPLE_POR_ID_PLAN = "/administracion_lista_simple/obtener_tematicas_por_id_plan/{id}";

	// Pot proyecto instrumento
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_POT_PROYECTO_INSTRUMENTO = "/administracion_lista_compuesta/registrar_pot_proyecto_instrumento";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_POT_PROYECTO_INSTRUMENTO = "/administracion_lista_compuesta/modificar_pot_proyecto_instrumento";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO = "/administracion_lista_compuesta/obtener_todos_pot_proyecto_instrumento";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_INSTRUMENTO = "/administracion_lista_compuesta/obtener_todos_pot_instrumento";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_POT_OBRA = "/administracion_lista_compuesta/obtener_todos_pot_obra";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_POT_PROYECTO_INSTRUMENTO_ESTADO = "/administracion_lista_compuesta/modificar_estado_proyecto_instrumento/{id}";

	// Linea de inversion
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_LINEA_INVERSION = "/administracion_lista_compuesta/registrar_linea_inversion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION = "/administracion_lista_compuesta/modificar_linea_inversion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_LINEA_INVERSION = "/administracion_lista_compuesta/obtener_todos_linea_inversion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_LINEA_INVERSION_ESTADO = "/administracion_lista_compuesta/modificar_estado_linea_inversion/{id}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_LINEA_POR_SECTOR = "/administracion_lista_compuesta/obtener_por_sector/{sector}";
	// Territorializacion
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_REGISTRAR_TERRITORIALIZACION = "/administracion_lista_compuesta/registrar_territorializacion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_TERRITORIALIZACION = "/administracion_lista_compuesta/modificar_territorializacion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION = "/administracion_lista_compuesta/obtenert_todos_territorializacion";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_MODIFICAR_ESTADO_TERRITORIALIZACION = "/administracion_lista_compuesta/modificar_estado_territorializacion/{id}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_POR_ID_TERRITROIALIZACION = "/administracion_lista_compuesta/obtener_territorializacion/{id}";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_TODOS_TERRITORIALIZACION_V2 = "/administracion_lista_compuesta/obtenert_todos_territorializacion_v2";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_POR_LOCALIDAD_TERR = "/administracion_lista_compuesta/obtener_localidad";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_COMPUESTA_OBTENER_POR_ID_LOCALIDAD_TERR = "/administracion_lista_compuesta/obtener_id_localidad/{id}";
	
	// Consecutivo
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_REGISTRAR_CONSECUTIVO = "/administracion_lista_simple/registrar_consecutivo";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_CONSECUTIVO = "/administracion_lista_simple/modificar_consecutivo";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODOS_CONSECUTIVO = "/administracion_lista_simple/obtener_todos_consecutivo";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_CONSECUTIVO_POR_PLAN = "/administracion_lista_simple/obtener_consecutivo_por_plan";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_ESTADO_CONSECUTIVO = "/administracion_lista_simple/modificar_estado/{idConsecutivo}";
	public static final String COD_ENTIDAD = "codigoEntidad";
	public static final String CODIGO_ENTIDAD = "0143";
	public static final String ID_PLAN_DESARROLLO = "idPlanDesarrollo";

	// Parametro General
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_MODIFICAR_PARAMETRO_GENERAL = "/administracion_lista_simple/modificar_parametro_general";
	public static final String CORE_CONTROLLER_ADMINISTRACION_LISTA_SIMPLE_OBTENER_TODO_PARAMETRO_GENERAL = "/administracion_lista_simple/obtener_todo_parametro_general";

	// Buzon de mensajeria
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_CREAR_MENSAJE = "/buzon_noticificacion/crear_mensaje";
	public static final String CORE_CONTROLLER_BUZON_NOTIFIACION_OBTENER_TODOS_USUARIO = "/buzon_notificacion/obtener_todos/{usuario}";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_ADMIN = "/buzon_notificacion/obtener_todos";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_FILTRO = "/buzon_notificacion/filtro";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_DAR_RESPUESTA = "/buzon_notifiicacion/dar_respuesta";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_CREAR_BUZON = "/buzon_notifiicacion/crear_buzon";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_LEER_INFORMATIVO = "/leer_informativo/{usuario}";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_NOTIFICACIONES = "/obtener_notificaciones";
	public static final String CORE_CONTROLLER_BUZON_NOTIFICACION_OBTENER_AUTOMATICOS = "/buzon_notificacion/obtener_automaticos";
	public static final String CORE_CONTROLLER_CONFIGURACION_NOTIFICACION_MODIFICAR_CONFIGURACION = "/plantilla_notificacion/modificar_notificacion";

	// Estado de servicio
	public static final String CORE_CONTROLLER_ESTADO_DE_SERVICIO_OBTENER_TODOS = "/estado_servicio/obtener_todos";

	// Funcionario Clave Entidad
	public static final String CORE_CONTROLLER_ADMINISTRACION_GET_OBTENER_POR_ID_FUNCIONARIO_CLAVE_ENTIDAD = "/administracion/funcionario_clave/obtener_lista";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_REGISTRAR_FUNCIONARIO_CLAVE_ENTIDAD = "/administracion/funcionario_clave/registrar";
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD = "/administracion/funcionario_clave/modificar";

	// Administracion
	public static final String CORE_CONTROLLER_ADMINISTRACION_POST_CONSULTAR_USUARIOS = "/administracion/consultarusuariosporrol";

	/**
	 * Entidades
	 */
	public static final String COMPONENTE_GESTION_USUARIOID = "componente_gestion_usuario.id_gestion_usuario";
	public static final String COMPONENTE_GESTIONID = "componente_gestion.id_componente_gestion";
	public static final String COMPONENTEN_GESTIONDTOID = "idComponenteGestion";
	public static final String COMPONENTE_GESTION_USUARIODTOID = "idGestionUsuario";
	public static final String ENTIDADCODIGO = "entidad.codigo_entidad";
	public static final String ENTIDADCODIGO_DTO = "codigoEntidad";

	/**
	 * paramteros controllers
	 */
	public static final String CORE_CONTROLLER_PARAMETRO_CORREO = "correo";

	/**
	 * Nombres campos tablas
	 */
	public static final String ARCHIVO_PROCESADO_ID_ARCHIVO = "ArchivoProcesado.idArchivo";
	public static final String ARCHIVO_PROCESADO_ID_CONFIG_CARGUE = "ArchivoProcesado.idConfigCargue";
	public static final String ARCHIVO_PROCESADO_DETALLE = "ArchivoProcesado.detalle";
	public static final String ARCHIVO_PROCESADO_ESTADO = "ArchivoProcesado.estado";
	public static final String ARCHIVO_PROCESADO_FECHA_CARGUE = "ArchivoProcesado.fechaCargue";
	public static final String ARCHIVO_PROCESADO_MODULO = "ArchivoProcesado.modulo";
	public static final String ARCHIVO_PROCESADO_TEMA = "ArchivoProcesado.tema";
	public static final String ARCHIVO_PROCESADO_USUARIO = "ArchivoProcesado.usuario";

	public static final String MENSAJE_CARGAR_ARCHIVO_PLANO_CORRECTO = "mensaje_cargar_archivo_plano_correcto";
	public static final String MENSAJE_OBTENER_ARCHIVOS_PLANOS_CARGADOS_CORRECTO = "mensaje_obtener_archivos_planos_correcto";

	/**
	 * Campos de las tablas en plano
	 */
	public static final String ARGUMENTO = "argumento";
	public static final String RESULTADO = "resultado";

	/**
	 * Nombres campos tablas
	 */
	public static final String COMPONENTE_GASTO_ID_COMPONENTE_GASTO = "ComponenteGasto.idComponenteGasto";
	public static final String COMPONENTE_GASTO_CODIGO_COMPONENTE = "ComponenteGasto.codigoComponente";
	public static final String COMPONENTE_GASTO_ESTADO = "ComponenteGasto.estado";
	public static final String COMPONENTE_GASTO_NOMBRE_COMPONENTE = "ComponenteGasto.nombreComponente";
	public static final String COMPONENTE_GASTO_ARGUMENTO_LISTA_SIMPLE = "ComponenteGasto.idLsTipoProyecto";

	public static final String ENTIDAD_CODIGO_ENTIDAD = "Entidad.codigoEntidad";
	public static final String ENTIDAD_GESTION_PROYECTOS = "Entidad.gestionProyectos";
	public static final String ENTIDAD_GESTION_USUARIOS = "Entidad.gestionUsuarios";
	public static final String ENTIDAD_ID_LS_CLASIFICACION = "Entidad.argumentoListaSimple1";
	public static final String ENTIDAD_ID_LS_ASOCIACION = "Entidad.argumentoListaSimple2";
	public static final String ENTIDAD_ID_BANCO_PROYECTO = "Entidad.idBancoProyecto";

	public static final String EQUIPAMIENTO_ID_EQUIPAMIENTO = "Equipamento.idEquipamiento";
	public static final String EQUIPAMIENTO_NOMBRE = "Equipamento.nombre";
	public static final String EQUIPAMIENTO_DESCRIPCION = "Equipamento.descripcion";
	public static final String EQUIPAMIENTO_ESTADO = "Equipamento.estado";
	public static final String EQUIPAMIENTO_ID_LS_SECTOR = "Equipamento.idLsSector";
	public static final String EQUIPAMIENTO_ID_LS_CATEGORIA = "Equipamento.idLsCategoria";

	public static final String ESTRUCTURA_METAS_ID_ESTRUCTURA_METAS = "EstructuraMeta.idEstructuraMetas";
	public static final String ESTRUCTURA_METAS_ESTADO = "EstructuraMeta.estado";
	public static final String ESTRUCTURA_METAS_PD = "EstructuraMeta.pd";
	public static final String ESTRUCTURA_METAS_PI = "EstructuraMeta.pi";
	public static final String ESTRUCTURA_METAS_ID_LS_UNIDAD_MEDIDA = "EstructuraMeta.idLsUnidadMedida";
	public static final String ESTRUCTURA_METAS_ID_LS_VERBO = "EstructuraMeta.idLsVerbo";

	public static final String INFORMACION_PRESUPUESTAL_ID_INFO_PRESUPUESTAL = "InformacionPresupuestal.idInfoPresupuestal";
	public static final String INFORMACION_PRESUPUESTAL_YEAR = "InformacionPresupuestal.year";
	public static final String INFORMACION_PRESUPUESTAL_MES = "InformacionPresupuestal.mes";
	public static final String INFORMACION_PRESUPUESTAL_CODIGO_DISTRITAL = "InformacionPresupuestal.codigoDistrital";
	public static final String INFORMACION_PRESUPUESTAL_CODIGO_PROYECTO = "InformacionPresupuestal.codigoProyecto";
	public static final String INFORMACION_PRESUPUESTAL_NOMBRE_PROYECTO = "InformacionPresupuestal.nombreProyecto";
	public static final String INFORMACION_PRESUPUESTAL_EJECUCION_VIGENCIA = "InformacionPresupuestal.ejecucionVigencia";
	public static final String INFORMACION_PRESUPUESTAL_GIROS_VIGENCIA = "InformacionPresupuestal.girosVigencia";
	public static final String INFORMACION_PRESUPUESTAL_RECURSOS_SUSPENDIDOS = "InformacionPresupuestal.recursosSuspendidos";
	public static final String INFORMACION_PRESUPUESTAL_CONSTITUCION_RESERVA = "InformacionPresupuestal.constitucionReserva";
	public static final String INFORMACION_PRESUPUESTAL_APROPIACION_RESERVA = "InformacionPresupuestal.apropiacionReserva";
	public static final String INFORMACION_PRESUPUESTAL_APROBACION_DEFINITIVA = "InformacionPresupuestal.aprobacionDefinitiva";
	public static final String INFORMACION_PRESUPUESTAL_EJECUCUIN_GIRO_RESERVAS = "InformacionPresupuestal.ejecucionGiroReservas";
	public static final String INFORMACION_PRESUPUESTAL_CODIGO_CLASIFICACION_PRESUPUESTAL = "InformacionPresupuestal.codigoClasificacionPresupuestal";
	public static final String INFORMACION_PRESUPUESTAL_CODIGO_INTERNO = "InformacionPresupuestal.codigoInterno";
	public static final String INFORMACION_PRESUPUESTAL_ORIGEN = "InformacionPresupuestal.origen";
	public static final String INFORMACION_PRESUPUESTAL_ID_ARCHIVO = "InformacionPresupuestal.idArchivo";
	public static final String INFORMACION_PRESUPUESTAL_ID_PLAN_DESARROLLO = "InformacionPresupuestal.idPlanDesarrollo";

	public static final String LISTA_COMPUESTA_NOMBRE = "ListaCompuesta.nombre";

	public static final String USUARIO_ENTIDAD_ID_USUARIO_ENTIDAD = "UsuarioEntidad.idUsuarioEntidad";
	public static final String USUARIO_ENTIDAD_USUARIO = "UsuarioEntidad.usuario";
	public static final String USUARIO_ENTIDAD_CODIGO_ENTIDAD = "UsuarioEntidad.entidad";
	public static final String USUARIO_CORREO = "Usuarios.correo_electronico";
	public static final String USUARIO_CLAVE = "Usuarios.clave";
	public static final String USUARIO_CLAVE_NUEVA = "Usuarios.clave_nueva";
	public static final String USUARIO_CLAVE_ANTERIOR = "Usuarios.clave_anterior";
	public static final String USUARIO_ID = "Usuarios.id";
	public static final String USUARIO_TIPO = "Usuarios.tipoUsuario";
	public static final String USUARIO_IDENTIFICACION = "Usuarios.identificacion";
	public static final String USUARIO_USUARIO = "Usuarios.usuario";

	public static final String PROYECTO_INVERSION_ID_PROYECTO_INVERSION = "ProyectoInversion.idProyectoInversion";
	public static final String PROYECTO_INVERSION_NOMBRE = "ProyectoInversion.nombre";
	public static final String PROYECTOS_INVERSION_USUARIO_ID_PROYECTO_USUARIO = "ProyectosInversionUsuario.idProyectoUsuario";
	public static final String PROYECTOS_INVERSION_USUARIO_USUARIO = "ProyectosInversionUsuario.usuario";
	public static final String PROYECTOS_INVERSION_USUARIO_PROYECTO_INVERSION = "ProyectosInversionUsuario.proyectoInversion";

	public static final String COMPONENTE_GESTION_USUARIO_ID_GESTION_USUARIO = "ComponenteGestionUsuario.idGestionUsuario";
	public static final String COMPONENTE_GESTION_USUARIO_USUARIO = "ComponenteGestionUsuario.usuario";
	public static final String COMPONENTE_GESTION_USUARIO_COMPONENTE_GESTION = "ComponenteGestionUsuario.idComponenteGestion";

	public static final String LISTA_SIMPLE_ID_LISTA_SIMPLE = "ListaSimple.idListaSimple";
	public static final String LISTA_SIMPLE_NOMBRE = "ListaSimple.nombre";
	public static final String LISTA_SIMPLE_DESCRIPCION = "ListaSimple.descripcion";

	public static final String ARGUMENTO_LISTA_SIMPLE_ID_ARGUMENTO = "ArgumentoListaSimple.idArgumento";
	public static final String ARGUMENTO_LISTA_SIMPLE_ESTADO = "ArgumentoListaSimple.estado";
	public static final String ARGUMENTO_LISTA_SIMPLE_RESULTADO = "ArgumentoListaSimple.resultado";
	public static final String ARGUMENTO_LISTA_SIMPLE_ARGUMENTO = "ArgumentoListaSimple.argumento";
	public static final String ARGUMENTO_LISTA_SIMPLE_LISTA_SIMPLE = "ArgumentoListaSimple.idListaSimple";

	public static final String POT_PROYECTO_INSTRUMENTO_ID_PROYECTO_INSTRUMENTO = "PotProyectoInstrumento.idProyectoInstrumento";
	public static final String POT_PROYECTO_INSTRUMENTO_ESTADO = "PotProyectoInstrumento.estado";
	public static final String POT_PROYECTO_INSTRUMENTO_ID_INSTRUMENTO = "PotProyectoInstrumento.idPotInstrumento";
	public static final String POT_PROYECTO_INSTRUMENTO_ID_POT_PROYECTO = "PotProyectoInstrumento.idPotProyecto";

	public static final String LINEA_INVERSION_ID_LINEA_INVERSION = "LineaInversion.idLineaInversion";
	public static final String LINEA_INVERSION_CONCEPTO = "LineaInversion.concepto";
	public static final String LINEA_INVERSION_ID_LS_SECTOR = "LineaInversion.idLsSector";
	public static final String LINEA_INVERSION_ESTABLECIDO = "LineaInversion.establecido";
	public static final String LINEA_INVERSION_DESCRIPCION = "LineaInversion.descripcion";
	public static final String LINEA_INVERSION_FECHA = "LineaInversion.fecha";
	public static final String LINEA_INVERSION_ESTADO = "LineaInversion.estado";

	public static final String TERRITORIALIZACION_ID_TERRITORIALIZACION = "Territorializacion.idTerritorializacion";
	public static final String TERRITORIALIZACION_ESTADO = "Territorializacion.estado";
	public static final String TERRITORIALIZACION_ID_LS_LOCALIDAD = "Territorializacion.idLsLocalidad";
	public static final String TERRITORIALIZACION_ID_LS_UPZ = "Territorializacion.idLsUpz";
	public static final String TERRITORIALIZACION_ID_LS_VEREDA = "Territorializacion.idLsVereda";
	public static final String TERRITORIALIZACION_ID_LS_BARRIO = "Territorializacion.idLsBarrio";
	public static final String TERRITORIALIZACION_ID_LS_UPR = "Territorializacion.idLsUpr";

	public static final String CONSECUTIVO_ID_CONSECUTIVO = "Consecutivo.idConsecutivo";
	public static final String CONSECUTIVO_DESCRIPCION = "Consecutivo.descripcion";
	public static final String CONSECUTIVO_NOMBRE = "Consecutivo.nombre";
	public static final String CONSECUTIVO_SECUENCIA = "Consecutivo.secuencia";
	public static final String CONSECUTIVO_VIGENCIA = "Consecutivo.vigencia";
	public static final String CONSECUTIVO_ID_PLAN_DESARROLLO = "Consecutivo.idPlanDesarrollo";
	public static final String CONSECUTIVO_ENTIDAD = "Consecutivo.entidad";

	public static final String PARAMETRO_GENERAL_CODIGO_PARAMETRO = "ParametroGeneral.codigoParametro";
	public static final String PARAMETRO_GENERAL_ARGUMENTO = "ParametroGeneral.argumento";
	public static final String PARAMETRO_GENERAL_DESCRIPCION = "ParametroGeneral.descripcion";
	public static final String PARAMETRO_GENERAL_FECHA_CREACION = "ParametroGeneral.fechaCreacion";
	public static final String PARAMETRO_GENERAL_FECHA_MODIFICACION = "ParametroGeneral.fechaModificacion";
	public static final String PARAMETRO_GENERAL_NOMBRE = "ParametroGeneral.nombre";
	public static final String PARAMETRO_GENERAL_USUARIO_CREACION = "ParametroGeneral.usuarioCreacion";
	public static final String PARAMETRO_GENERAL_USUARIO_MODIFICACION = "ParametroGeneral.usuarioModificacion";

	public static final String BUZON_NOTIFICACIONES_ID_NOTIFICACION = "BuzonNotificaciones.idNotificacion";
	public static final String BUZON_NOTIFICACIONES_ESTADO = "BuzonNotificaciones.estado";
	public static final String BUZON_NOTIFICACIONES_FECHA_ESCRITURA = "BuzonNotificaciones.fechaEscritura";
	public static final String BUZON_NOTIFICACIONES_FECHA_LECTURA = "BuzonNotificaciones.fechaLectura";
	public static final String BUZON_NOTIFICACIONES_FECHA_RESPUESTA = "BuzonNotificaciones.fechaRespuesta";
	public static final String BUZON_NOTIFICACIONES_ID_CONFIG_NOTIFICACION = "BuzonNotificaciones.idConfigNotificacion";
	public static final String BUZON_NOTIFICACIONES_MENSAJE = "BuzonNotificaciones.mensaje";
	public static final String BUZON_NOTIFICACIONES_RESPUESTA = "BuzonNotificaciones.respuesta";
	public static final String BUZON_NOTIFICACIONES_ROLES = "BuzonNotificaciones.roles";
	public static final String BUZON_NOTIFICACIONES_TIPO_MENSAJE = "BuzonNotificaciones.tipoMensaje";
	public static final String BUZON_NOTIFICACIONES_USUARIO_DESTINO = "BuzonNotificaciones.usuarioDestino";
	public static final String BUZON_NOTIFICACIONES_USUARIO_ORIGINA = "BuzonNotificaciones.usuarioOrigina";

	public static final String CONFIGURACION_NOTIFICACION_ASUNTO = "ConfiguracionNotificacion.asunto";
	public static final String CONFIGURACION_NOTIFICACION_ID_CONFIG_NOTIFICACION = "ConfiguracionNotificacion.idConfigNotificacion";
	public static final String CONFIGURACION_NOTIFICACION_MENSAJE = "ConfiguracionNotificacion.mensaje";
	public static final String CONFIGURACION_NOTIFICACION_OPERACION_ORIGEN = "ConfiguracionNotificacion.operacionOrigen";
	public static final String CONFIGURACION_NOTIFICACION_REQUIERE_ACCION = "ConfiguracionNotificacion.requiereAccion";
	public static final String CONFIGURACION_NOTIFICACION_ROL = "ConfiguracionNotificacion.rol";
	public static final String CONFIGURACION_NOTIFICACION_USUARIOS = "ConfiguracionNotificaciones.usuarios";

	/**
	 * Nombres campos tabla Historial Administrativo
	 */
	public static final String HISTORIAL_ADMINISTRATIVO_CODIGO_HIS_ADMIN = "SegEntidadHisAdministrativo.codigoHisAdmin";
	public static final String HISTORIAL_ADMINISTRATIVO_NOMBRE = "SegEntidadHisAdministrativo.nombre";
	public static final String HISTORIAL_ADMINISTRATIVO_SIGLA = "SegEntidadHisAdministrativo.sigla";
	public static final String HISTORIAL_ADMINISTRATIVO_FECHA_INICIO = "SegEntidadHisAdministrativo.fechaInicio";
	public static final String HISTORIAL_ADMINISTRATIVO_FECHA_FINAL = "SegEntidadHisAdministrativo.fechaFinal";
	public static final String HISTORIAL_ADMINISTRATIVO_ACTO_ADMINISTRATIVO = "SegEntidadHisAdministrativo.actoAdministrativo";
	public static final String HISTORIAL_ADMINISTRATIVO_CODIGO_ENTIDAD = "SegEntidadHisAdministrativo.codigoEntidad";

	/**
	 * Nombres campos tabla Historial Sectorial
	 */
	public static final String HISTORIAL_SECTORIAL_CODIGO_HIS_SECTORIAL = "SegEntidadHisSectorial.codigoHisSectorial";
	public static final String HISTORIAL_SECTORIAL_NOMBRE = "SegEntidadHisSectorial.nombre";
	public static final String HISTORIAL_SECTORIAL_ACTO_ADMINISTRATIVO = "SegEntidadHisSectorial.actoAdministrativo";
	public static final String HISTORIAL_SECTORIAL_ACTIVO = "SegEntidadHisSectorial.activo";
	public static final String HISTORIAL_SECTORIAL_FECHA_INICIO = "SegEntidadHisSectorial.fechaInicio";
	public static final String HISTORIAL_SECTORIAL_FECHA_FINAL = "SegEntidadHisSectorial.fechaFinal";
	public static final String HISTORIAL_SECTORIAL_CODIGO_ENTIDAD = "SegEntidadHisSectorial.codigoEntidad";

	/**
	 * Nombres campos tabla Funcionario Clave Entidad
	 */
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD = "FuncionarioClaveEntidad.idFuncionarioEntidad";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD = "FuncionarioClaveEntidad.codigoEntidad";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE = "FuncionarioClaveEntidad.nombre";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION = "FuncionarioClaveEntidad.idLsFuncion";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO = "FuncionarioClaveEntidad.idLsGenero";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_CARGO = "FuncionarioClaveEntidad.cargo";
	public static final String FUNCIONARIO_CLAVE_ENTIDAD_CORREO = "FuncionarioClaveEntidad.correo";

	/**
	 * Nombres campos tabla Usuario - Seguridad
	 */
	public static final String SEG_USUARIO_USUARIO = "Usuario.nombreUsuario";
	public static final String SEG_USUARIO_ESTADO = "Usuario.estado";
	public static final String SEG_USUARIO_CODIGO_ENTIDAD = "Usuario.codigoEntidad";
	public static final String SEG_USUARIO_CODIGO_TIPO_USUARIO = "Usuario.tipoUsuario";
	public static final String SEG_USUARIO_FECHA_CADUCIDA = "Usuario.fechaCaducida";
	public static final String SEG_USUARIO_FECHA_ULTIMO_INGRESO = "Usuario.fechaUltimoIngreso";
	public static final String SEG_USUARIO_NOMBRE = "Usuario.nombre";
	public static final String SEG_USUARIO_CORREO_ELECTRONICO = "Usuario.correoElectronico";
	public static final String SEG_USUARIO_TELEFONO = "Usuario.telefono";
	public static final String SEG_USUARIO_CAMBIAR_CONTRASENIA = "Usuario.cambiarContrasenia";
	public static final String SEG_USUARIO_NUMERO_INTENTOS = "Usuario.numeroIntentos";

	/**
	 * Nombres de campos tabla pddCompromiso
	 */
	public static final String PDD_COMPROMISO_ID_COMPROMISO = "PddCompromiso.idCompromiso";
	public static final String PDD_COMPROMISO_ID_ESTRATEGICO = "PddCompromiso.idEstrategico";
	public static final String PDD_COMPROMISO_ID_PLAN_DESARROLLO = "PddCompromiso.idPlanDesarrollo";

	/**
	 * Nombres campos tabla pddCompromisoEspecifico
	 */
	public static final String PDD_COMPROMISO_ESPEDIFICO_ID_ESPECIFICO = "PddCompromisoEspecifico.idEspecifico";
	public static final String PDD_COMPROMISO_ESPEDIFICO_ID_COMPROMISO = "PddCompromisoEspecifico.idCompromiso";
	public static final String PDD_COMPROMISO_ESPEDIFICO_DESCRIPCION = "PddCompromisoEspecifico.descripcion";

	/**
	 * Nombres campos tabla PddCompetenciaAsociada
	 */
	public static final String PDD_COMPETENCIA_ASOCIADA_ID_COMPETENCIA = "PddCompetenciaAsociada.idCompetencia";
	public static final String PDD_COMPETENCIA_ASOCIADA_ID_LS_COMPETENCIA = "PddCompetenciaAsociada.idLsCompetencia";
	public static final String PDD_COMPETENCIA_ASOCIADA_ID_LS_SECTOR = "PddCompetenciaAsociada.idLsSector";

	/**
	 * Nombres campos tabla PddPrbValoracion
	 */
	public static final String PDD_PRB_VALORACION_ID_VALORACION = "PddPrbValoracion.idValoracion";
	public static final String PDD_PRB_VALORACION_GRAVEDAD = "PddPrbValoracion.gravedad";
	public static final String PDD_PRB_VALORACION_DURACION = "PddPrbValoracion.duracion";
	public static final String PDD_PRB_VALORACION_IMPACTO = "PddPrbValoracion.impacto";
	public static final String PDD_PRB_VALORACION_DEBILIDAD = "PddPrbValoracion.debilidad";
	public static final String PDD_PRB_VALORACION_BALANCE_INICIAL = "PddPrbValoracion.balanceInicial";
	public static final String PDD_PRB_VALORACION_OBSERVACIONES = "PddPrbValoracion.observaciones";
	public static final String PDD_PRB_VALORACION_MOMENTO = "PddPrbValoracion.momento";
	public static final String PDD_PRB_VALORACION_ID_LS_SECTOR = "PddPrbValoracion.idLsSector";
	public static final String PDD_PRB_VALORACION_ID_LS_DIMENSION = "PddPrbValoracion.idLsDimension";
	public static final String PDD_PRB_VALORACION_ID_LS_COMPETENCIA_1 = "PddPrbValoracion.idLsCompetencia1";
	public static final String PDD_PRB_VALORACION_ID_LS_COMPETENCIA_2 = "PddPrbValoracion.idLsCompetencia2";
	public static final String PDD_PRB_VALORACION_ID_PROBLEMATICA = "PddPrbValoracion.idProblematica";

	/**
	 * Nombres campos tabla PddMEtaResultado
	 */
	public static final String PDD_META_RESULTADO_ID_LS_VERBO = "PddMetaResultado.idLsVerbo";
	public static final String PDD_META_RESULTADO_MAGNITUD = "PddMetaResultado.magnitud";
	public static final String PDD_META_RESULTADO_ID_LS_UNIDAD_MEDIDA = "PddMetaResultado.idLsUnidadMedida";
	public static final String PDD_META_RESULTADO_COMPLEMENTO = "PddMetaResultado.complemento";
	public static final String PDD_META_RESULTADO_ID_LS_TIPO_ANUALIZACION = "PddMetaResultado.idLsTipoAnualizacion";
	public static final String PDD_META_RESULTADO_ES_FORMULACION = "PddMetaResultado.esFormulacion";
	public static final String PDD_META_RESULTADO_ID_META_RESULTADO = "PddMetaResultado.idMetaResultado";

	/**
	 * Nombre campos tabla BpProyectoInversion
	 */
	public static final String BP_PROYECTO_INVERSION_ID_PROYECTO_INVERSION = "BpProyectoInversion.idProyInversion";
	public static final String BP_PROYECTO_INVERSION_CODIGO_PROYECTO = "BpProyectoInversion.codigo";
	public static final String BP_PROYECTO_INVERSION_NOMBRE_PROYECTO = "BpProyectoInversion.nombre";
	public static final String BP_PROYECTO_INVERSION_CODIGO_PROYECTO_BPIN = "BpProyectoInversion.codigoBpin";
	public static final String BP_PROYECTO_INVERSION_NOMBRE_PROYECTO_BPIN = "BpProyectoInversion.nombreBpin";
	public static final String BP_PROYECTO_INVERSION_ID_LS_ETAPA = "BpProyectoInversion.idLsEtapa";
	public static final String BP_PROYECTO_INVERSION_ID_LS_ESTADO = "BpProyectoInversion.idLsEstado";
	public static final String BP_PROYECTO_INVERSION_BLOQUEO = "BpProyectoInversion.bloqueo";
	public static final String BP_PROYECTO_INVERSION_NOMBRE_PROYECTO_POAI = "BpProyectoInversion.nombrePoai";
	public static final String BP_PROYECTO_INVERSION_CODIGO_ENTIDAD = "BpProyectoInversion.codigoEntidad";
	public static final String BP_PROYECTO_INVERSION_TIPOS = "BpProyectoInversion.tipos";

	/**
	 * Nombres campos tabla BpProyInvAporte
	 */
	public static final String BP_PROY_INV_APORTE_ID_PROY_INV_APORTE = "BpProyInvAporte.idProyAporte";
	public static final String BP_PROY_INV_APORTE_ID_PROY_INVERSION = "BpProyInvAporte.idProyInversion";
	public static final String BP_PROY_INV_APORTE_ID_APORTE = "BpProyInvAporte.idAporte";
	public static final String BP_PROY_INV_APORTE_IDS_APORTES = "BpProyInvAporte.idsAportes";

	/**
	 * Nombres campso tabla BpProyInvInciativa
	 */
	public static final String BP_PROY_INV_INICIATIVA_ID_PROY_INVERSION = "BpProyInvIniciativa.idProyInversion";

	/**
	 * Nombres campos tabla BpAporteCiudadano
	 */
	public static final String BP_APORTE_CIUDADANO_ID_APORTE = "BpAporteCiudadano.idAporte";
	public static final String BP_APORTE_CIUDADANO_CONSECUTIVO = "BpAporteCiudadano.consecutivo";
	public static final String BP_APORTE_CIUDADANO_ACCION = "BpAporteCiudadano.accion";
	public static final String BP_APORTE_CIUDADANO_FUENTE = "BpAporteCiudadano.fuente";
	public static final String BP_APORTE_CIUDADANO_ID_NIVEL_ATRIBUTO_PDD = "BpAporteCiudadano.idNivelAtributoPdd";
	public static final String BP_APORTE_CIUDADANO_ID_ARCHIVO = "BpAporteCiudadano.idArchivo";
	
	/**
	 * Nombres campos de tabla BpProyInvPolitica
	 */
	public static final String BP_PROY_INV_POLITICA_ID_PROY_POLITICA = "BpProyInvPolitica.idProyPolitica";
	public static final String BP_PROY_INV_POLITICA_ID_POL_PUB = "BpProyInvPolitica.idPolPub";
	public static final String BP_PROY_INV_POLITICA_ID_PROY_INVERSION = "BpProyInvPolitica.idProyInversion";
	
	/**
	 * Nombres campos de la tabla BpProyInvLinea
	 */
	public static final String BP_PROY_INV_LINEA_ID_PROY_LINEA = "BpProyInvLinea.idProyLinea";
	public static final String BP_PROY_INV_LINEA_ID_LINEA_INVERSION = "BpProyInvLinea.idLineaInversion";
	public static final String BP_PROY_INV_LINEA_ID_PROY_INVERSION = "BpProyInvLinea.idProyInversion";
	
	/**
	 * Nombres campos de la tabla BpProyInvPmr
	 */
	public static final String BP_PROY_INV_PMR_ID_PRY_PMR = "BpProyInvPmr.idProyPmr";
	public static final String BP_PROY_INV_PMR_ID_IND_MR = "BpProyInvPmr.idIndMr";
	public static final String BP_PROY_INV_PMR_ID_PROY_INVERSION = "BpProyInvPmr.idProyInversion";
	
	/**
	 * Nombre campos tabla BpIniciativaCiudadana
	 */
	public static final String BP_INICIATIVA_CIUDADANA_ID_INICIATIVA = "BpIniciativaCiudadana.idIniciativa";
	public static final String BP_INICIATIVA_CIUDADANA_CODIGO = "BpIniciativaCiudadana.codigo";
	public static final String BP_INICIATIVA_CIUDADANA_FECHA_CODIGO = "BpIniciativaCiudadana.fechaCodigo";
	public static final String BP_INICIATIVA_CIUDADANA_RADICADO = "BpIniciativaCiudadana.radicado";
	public static final String BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO = "BpIniciativaCiudadana.numeroRad";
	public static final String BP_INICIATIVA_CIUDADANA_FECHA_RADICADO = "BpIniciativaCiudadana.fechaRad";
	public static final String BP_INICIATIVA_CIUDADANA_NOMBRE = "BpIniciativaCiudadana.nombre";
	public static final String BP_INICIATIVA_CIUDADANA_APLICA_LINEA = "BpIniciativaCiudadana.aplicaLinea";
	public static final String BP_INICIATIVA_CIUDADANA_ID_PLAN_DESAROLLO = "BpIniciativaCiudadana.idPlanDesarrollo";
	public static final String BP_INICIATIVA_CIUDADANA_PROBLEMATICA = "BpIniciativaCiudadana.problematica";
	public static final String BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION = "BpIniciativaCiudadana.totalPoblacion";
	public static final String BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION = "BpIniciativaCiudadana.alternativaSolucion";
	public static final String BP_INICIATIVA_CIUDADANA_NOMBRE_PROP = "BpIniciativaCiudadana.nombreProp";
	public static final String BP_INICIATIVA_CIUDADANA_TELEFONO_PROP = "BpIniciativaCiudadana.telefonoProp";
	public static final String BP_INICIATIVA_CIUDADANA_IDENTIFICACION_PROP = "BpIniciativaCiudadana.identificacionProp";
	public static final String BP_INICIATIVA_CIUDADANA_EMAIL_PROP = "BpIniciativaCiudadana.emailProp";
	public static final String BP_INICIATIVA_CIUDADANA_OBSERVACION = "BpIniciativaCiudadana.observacion";
	public static final String BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN = "BpIniciativaCiudadana.idLsOrigen";
	public static final String BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA = "BpIniciativaCiudadana.idLsEstadoInicia";
	public static final String BP_INICIATIVA_CIUDADANA_ID_LC_LINEA_INV = "BpIniciativaCiudadana.idLcLineaInv";
	public static final String BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPR = "BpIniciativaCiudadana.idLcTerritorializacionUpr";
	public static final String BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPZ = "BpIniciativaCiudadana.idLcTerritorializacionUpz";
	public static final String BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD = "BpIniciativaCiudadana.codigoEntidad";
	public static final String BP_INICIATIVA_CIUDADANA_GRUPOS_ETARIOS = "BpIniciativaCiudadana.grupoEtario";
	public static final String BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL = "BpIniciativaCiudadana.condicionPoblacional";

	/**
	 * Nombre campos tabla PddIndicador
	 */
	public static final String PDD_INDICADOR_ID_INDICADOR = "PddIndicador.idIndicador";
	public static final String PDD_INDICADOR_NOMBRE = "PddIndicador.nombre";
	public static final String PDD_INDICADOR_TIPO = "PddIndicador.tipo";
	public static final String PDD_INDICADOR_LINEA_BASE_DESC = "PddIndicador.lineaBaseDesc";
	public static final String PDD_INDICADOR_FUENTE = "PddIndicador.fuente";
	public static final String PDD_INDICADOR_YEAR_LINEA_BASE = "PddIndicador.yearLineaBase";
	public static final String PDD_INDICADOR_INFORMACION_SOPORTE = "PddIndicador.informacionSoporte";

	/**
	 * Nombres campos tabla Compromiso Estrategico
	 */
	public static final String COMPROMISO_ESTRATEGICO_ID_COMPROMISO = "CompromisoEstrategico.idCompromiso";
	public static final String COMPROMISO_ESTRATEGICO_ESTADO = "CompromisoEstrategico.estado";
	public static final String COMPROMISO_ESTRATEGICO_IDLSESTRATEGICO = "CompromisoEstrategico.idLsEstrategico";
	public static final String COMPROMISO_ESTRATEGICO_IDLSTEMATICA = "CompromisoEstrategico.idLsTematica";

	/**
	 * Nombres de campos tabla PDD
	 */
	public static final String PDD_ID_PLAN_DESARROLLO = "Pdd.idPlanDesarrollo";
	public static final String PDD_NOMBRE_PLAN = "Pdd.nombrePlan";
	public static final String PDD_SIGLA_PLAN = "Pdd.siglaPlan";
	public static final String PDD_ACTO_ADMINISTRATIVO = "Pdd.actoAdministrativo";
	public static final String PDD_FECHA_ACTO = "Pdd.fechaActo";
	public static final String PDD_YEAR_INICIO = "Pdd.yearInicio";
	public static final String PDD_NOMBRE_ALCALDE = "Pdd.nombreAlcalde";
	public static final String PDD_CORREO_ALCALDE = "Pdd.correoAlcalde";
	public static final String PDD_PROGRAMA_GOBIERNO = "Pdd.programaGobierno";
	public static final String PDD_YEAR_FINAL = "Pdd.yearFinal";
	public static final String PDD_VERSION = "Pdd.version";
	public static final String PDD_IDLSADOPTADO = "Pdd.idLsAdoptado";
	public static final String PDD_IDLSAVANCESGR = "Pdd.idLsAvanceSgr";
	public static final String PDD_IDESTADOPLAN = "Pdd.idLsEstadoPlan";
	public static final String PDD_IDLSNIVELES = "Pdd.idLsNiveles";

	/**
	 * Nombres de campos tabla PDD_NIVEL
	 */
	public static final String PDD_NIVEL_ID_PDD_NIVEL = "PddNivel.idPddNivel";
	public static final String PDD_NIVEL_CODIGO_NIVEL = "PddNivel.codNivel";
	public static final String PDD_NIVEL_DESCRIPCION = "PddNivel.descripcion";
	public static final String PDD_NIVEL_OBLIGATORIO_PDL = "PddNivel.obligatorioPdl";
	public static final String PDD_NIVEL_ID_PLAN_DESARROLLO = "PddNivel.idPlanDesarrollo";

	/**
	 * Nombres de campos tabla PDD_NIVEL_ATRIBUTO
	 */
	public static final String PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO = "PddNivelAtributo.idAtributo";
	public static final String PDD_NIVEL_ATRIBUTO_NUMERO = "PddNivelAtributo.numero";
	public static final String PDD_NIVEL_ATRIBUTO_DENOMINACION = "PddNivelAtributo.denominacion";
	public static final String PDD_NIVEL_ATRIBUTO_PONDERACION = "PddNivelAtributo.ponderacion";
	public static final String PDD_NIVEL_ATRIBUTO_NOMBRE_GERENTE = "PddNivelAtributo.nombreGerente";
	public static final String PDD_NIVEL_ATRIBUTO_CORREO_GERENTE = "PddNivelAtributo.correoGerente";
	public static final String PDD_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE = "PddNivelAtributo.idLsGeneroGerente";
	public static final String PDD_NIVEL_ATRIBUTO_ID_PDD_NIVEL = "PddNivelAtributo.idPddNivel";
	public static final String PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE = "PddNivelAtributo.idAtributoPadre";
	public static final String PDD_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO = "PddNivelAtributo.proyectoEstrategico";

	/**
	 * Nombres de campos tabla PDL_NIVEL_ATRIBUTO
	 */
	public static final String PDL_NIVEL_ATRIBUTO_ID_ATRIBUTO = "PdlNivelAtributo.idAtributo";
	public static final String PDL_NIVEL_ATRIBUTO_NUMERO = "PdlNivelAtributo.numero";
	public static final String PDL_NIVEL_ATRIBUTO_DENOMINACION = "PdlNivelAtributo.denominacion";
	public static final String PDL_NIVEL_ATRIBUTO_PONDERACION = "PdlNivelAtributo.ponderacion";
	public static final String PDL_NIVEL_ATRIBUTO_NOMBRE_GERENTE = "PdlNivelAtributo.nombreGerente";
	public static final String PDL_NIVEL_ATRIBUTO_CORREO_GERENTE = "PdlNivelAtributo.correoGerente";
	public static final String PDL_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE = "PdlNivelAtributo.idLsGeneroGerente";
	public static final String PDL_NIVEL_ATRIBUTO_ID_PDD_NIVEL = "PdlNivelAtributo.idPddNivel";
	public static final String PDL_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE = "PdlNivelAtributo.idAtributoPadre";
	public static final String PDL_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO = "PdlNivelAtributo.proyectoEstrategico";

	/**
	 * Nombre de campos tabla PDD_META
	 */
	public static final String PDD_META_ID_META = "PddMeta.idMeta";
	public static final String PDD_META_ID_ESPECIFICO = "PddMeta.idEspecifico";
	public static final String PDD_META_ID_TIPO_META_LS = "PddMeta.idTipoMetaLs";
	public static final String PDD_META_MAGNITUD = "PddMeta.magnitud";
	public static final String PDD_META_META = "PddMeta.meta";

	/**
	 * Nombre de campos tabla PDD_PROBLEMATICA
	 */
	public static final String PDD_PROBLEMATICA_ID_PROBLEMATICA = "PddProblematica.idProblematica";
	public static final String PDD_PROBLEMATICA_ID_COMPROMISO = "PddProblematica.idCompromiso";
	public static final String PDD_PROBLEMATICA_PROBLEMATICA = "PddProblematica.problematica";
	public static final String PDD_PROBLEMATICA_DESCRIPCION = "PddProblematica.descripcion";
	public static final String PDD_PROBLEMATICA_ID_LS_LOCALIZACION = "PddProblematica.idLsLocalizacion";
	public static final String PDD_PROBLEMATICA_ID_LS_SUBLOCALIZACION = "PddProblematica.idLsSublocalizacion";
	public static final String PDD_PROBLEMATICA_ID_LZ_UPZ_UPR = "PddProblematica.idLzUpzUpr";
	public static final String PDD_PROBLEMATICA_CAUSAS = "PddProblematica.causas";
	public static final String PDD_PROBLEMATICA_CONSECUENCIAS = "PddProblematica.consecuencias";
	public static final String PDD_PROBLEMATICA_OBJETIVO = "PddProblematica.objetivo";

	/**
	 * Nombres de campos tabla PDD_OBRA_CONCRETA
	 */
	public static final String PDD_OBRA_CONCRETA_ID_CONCRETA = "PddObraConcreta.idConcreta";
	public static final String PDD_OBRA_CONCRETA_ID_META = "PddObraConcreta.idMeta";
	public static final String PDD_OBRA_CONCRETA_OBRA_CONCRETA = "PddObraConcreta.obraConcreta";

	/**
	 * Nombres de campos tabla PDD_PBR_POBLACION
	 */
	public static final String PDD_PBR_POBLACION_ID_POBLACION = "PddPbrPoblacion.idPoblacion";
	public static final String PDD_PBR_POBLACION_DESCRIPCION = "PddPbrPoblacion.descripcion";
	public static final String PDD_PBR_POBLACION_ID_PROBLEMATICA = "PddPbrPoblacion.idProblematica";

	/**
	 * Nombres de campos de tabla PDD_PRB_INDICADOR
	 */
	public static final String PDD_PRB_INDICADOR_ID_PROB_IND = "PddPrbIndicador.idProbInd";
	public static final String PDD_PRB_INDICADOR_ID_INDICADOR = "PddPrbIndicador.idIndicador";
	public static final String PDD_PRB_INDICADOR_ID_PROBLEMATICA = "PddPrbIndicador.idProblematica";

	/**
	 * Nombre de campos de la tabla SPDD_POT
	 */

	public static final String IP_POT_ID_POT = "Pot.idPot";
	public static final String IP_POT_CODIGO_POT = "Pot.codigoPot";
	public static final String IP_POT_ACTO_ADMINISTRATIVO = "Pot.actoAdministrativo";
	public static final String IP_POT_YEAR_INICIO = "Pot.yearInicio";
	public static final String IP_POT_YEAR_FIN = "Pot.yearFin";
	public static final String IP_POT_ID_LS_ADOPTADO = "Pot.idLsAdoptado";
	public static final String IP_POT_FECHA = "Pot.fecha";
	public static final String IP_POT_ESTADO = "Pot.estado";
	public static final String IP_POT_URL = "Pot.url";
	public static final String IP_POT_NOMBRE = "Pot.nombre";

	/**
	 * Nombre de campos de la tabla PotObra
	 */
	public static final String POT_OBRA_ID_OBRA_PROY_POT = "PotObra.idObraProyPot";
	public static final String POT_OBRA_CODIGO = "PotObra.codigo";
	public static final String POT_OBRA_OBRA = "PotObra.obra";
	public static final String POT_OBRA_ID_LS_PLAZO = "PotObra.idLsPlazo";
	public static final String POT_OBRA_ID_NIVEL_POT = "PotObra.idNivelPot";
	public static final String POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS = "PotObra.codigoEntidadConcatenados";
	
	/**
	 * Nombre de campos de la tabla BpProyInvPoblacion
	 */
	public static final String BP_PROY_INV_POBLACION_NUMERO = "BpProyInvPoblacion.numero";
	public static final String BP_PROY_INV_POBLACION_ID_POBLACION= "BpProyInvPoblacion.idPoblacion";
	public static final String BP_PROY_INV_POBLACION_DESCRIPCION = "BpProyInvPoblacion.descripcion";
	public static final String BP_PROY_INV_POBLACION_ID_PROYECTO_INVERSION = "BpProyInvPoblacion.idProyInversion.idProyInversion";
	public static final String BP_PROY_INV_POBLACION_ID_LS_ETARIO = "BpProyInvPoblacion.idLsEtario.idArgumento";

	/**
	 * Nombre de campos de la tabla BpProyInvEtnico
	 */
	public static final String BP_PROY_INV_ETNICO_ID = "BpProyInvEtnico.idEtnico";
	public static final String BP_PROY_INV_ETNICO_NUMERO = "BpProyInvEtnico.numero";
	public static final String BP_PROY_INV_ETNICO_DESCRIPCION = "BpProyInvEtnico.descripcion";
	public static final String BP_PROY_INV_ETNICO_ID_LS_ETNICO = "BpProyInvEtnico.idLsEtnico.idArgumento";
	public static final String BP_PROY_INV_ETNICO_ID_POBLACION = "BpProyInvEtnico.idPoblacion.idPoblacion";
	
	/**
	 * Nombre de campos de la tabla PotInstrumento
	 */
	public static final String POT_INSTRUMENTO_ID_INSTRUMENTO_POT = "PotInstrumento.idInstrumentoPot";
	public static final String POT_INSTRUMENTO_CODIGO = "PotInstrumento.codigo";
	public static final String POT_INSTRUMENTO_DENOMINACION = "PotInstrumento.denominacion";
	public static final String POT_INSTRUMENTO_ID_POT = "PotInstrumento.idPot";
	public static final String POT_INSTRUMENTO_ID_LS_NIVEL_INST = "PotIntrumento.idLsNivelInst";

	/**
	 * Nombre de campos de la tabla SPDD_POT_RAMA
	 */

	public static final String IP_RAMA_ID_RAMA_POT = "PotRama.idRamaPot";
	public static final String IP_RAMA_NUMERO_RAMA_POT = "PotRama.numeroRama";
	public static final String IP_RAMA_ID_POT = "PotRama.idPot";

	/**
	 * Nombre de campos de la tabla SPDD_POT_NIVEL
	 */

	public static final String IP_POT_NIVEL_ID_NIVEL = "PotNivel.idNivelPot";
	public static final String IP_POT_NIVEL_NUMERO_NIVEL = "PotNivel.numeroNivel";
	public static final String IP_POT_NIVEL_DESCRIPCION = "PotNivel.descripcion";
	public static final String IP_POT_NIVEL_ID_NIVEL_PADRE = "PotNivel.idNivelPadre";
	public static final String IP_POT_NIVEL_ID_RAMA_POT = "PotNivel.idRamaPot";
	/**
	 * Nombres de campos tabla PDL
	 */
	public static final String PDL_ID_PLAN_LOCAL = "Pdl.idPlanLocal";
	public static final String PDL_NOMBRE_PLAN = "Pdl.nombrePlan";
	public static final String PDL_URL_PLAN = "Pdl.urlPlan";
	public static final String PDL_IDLSADOPTADO = "Pdl.idLsAdoptado";
	public static final String PDL_ACTO_ADMINISTRATIVO = "Pdl.actoAdministrativo";
	public static final String PDL_FECHA_ACTO = "Pdl.fechaActo";
	public static final String PDL_YEAR_INICIO = "Pdl.yearInicio";
	public static final String PDL_NOMBRE_ALCALDE = "Pdl.nombreAlcaldeLocal";
	public static final String PDL_CORREO_ALCALDE = "Pdl.correoAlcaldeLocal";
	public static final String PDL_YEAR_FINAL = "Pdl.yearFinal";
	public static final String PDL_ID_PLAN_DESARROLLO = "Pdl.idPlanDesarrollo";
	public static final String PDL_CODIGO_ENTIDAD = "Pdl.codigoEntidad";

	/**
	 * Nombres de campos tabla PDL NIVEL
	 */
	public static final String PDL_NIVEL_ID_PDL_NIVEL = "PdlNivel.idPdlNivel";
	public static final String PDL_NIVEL_CODIGO_NIVEL = "PdlNivel.codNivel";
	public static final String PDL_NIVEL_DESCRIPCION = "PdlNivel.descripcion";
	public static final String PDL_NIVEL_ID_PLAN_LOCAL = "PdlNivel.idPlanLocal";

	/**
	 * Nombre de campos tabla PDD META PRODUCTO
	 */
	public static final String PDD_META_PRODUCTO_ID_META_PRODUCTO = "PddMetaProducto.idMetaProducto";
	public static final String PDD_META_PRODUCTO_ID_META_RESULTADO = "PddMetaProducto.idMetaResultado";
	public static final String PDD_META_PRODUCTO_COMPLEMENTO = "PddMetaProducto.complemento";
	public static final String PDD_META_PRODUCTO_ES_FORMULACION = "PddMetaProducto.esFormulacion";
	public static final String PDD_META_PRODUCTO_BLOQUEAR_PPI = "PddMetaProducto.bloquearPpi";
	public static final String PDD_META_PRODUCTO_FECHA_CREACION = "PddMetaProducto.fechaCreacion";
	public static final String PDD_META_PRODUCTO_ID_LS_ESTADO = "PddMetaProducto.idLsEstado";
	public static final String PDD_META_PRODUCTO_ID_LS_TIPO_ANUALIZACION = "PddMetaProducto.idLsTipoAnualizacion";
	public static final String PDD_META_PRODUCTO_ID_LS_UNIDAD_MEDIDA = "PddMetaProducto.idLsUnidadMedida";
	public static final String PDD_META_PRODUCTO_ID_LS_VERBO = "PddMetaProducto.idLsVerbo";
	public static final String PDD_META_PRODUCTO_MAGNITUD = "PddMetaProducto.magnitud";
	public static final String PDD_META_PRODUCTO_META_PDD = "PddMetaProducto.metaPdd";
	public static final String PDD_META_PRODUCTO_OBSERVACIONES = "PddMetaProducto.observaciones";
	public static final String PDD_META_PRODUCTO_PONDERACION = "PddMetaProducto.ponderacion";

	/**
	 * Nombre de campos de la tabla: PddMpEntidad
	 */
	public static final String PDD_MP_ENTIDAD_ID_MP_ENTIDAD = "PddMpEntidad.idMpEntidad";
	public static final String PDD_MP_ENTIDAD_CODIGO_ENTIDAD = "PddMpEntidad.codigoEntidad";
	public static final String PDD_MP_ENTIDAD_ID_META_PRODUCTO = "PddMpEntidad.idMetaProducto";
	public static final String PDD_MP_ENTIDAD_MAGNITUD = "PddMpEntidad.magnitud";
	public static final String PDD_MP_ENTIDAD_ESTADO = "PddMpEntidad.estado";
	/**
	 * Nombre de campos de la tabla: PddMpIndicadorEntidad
	 */
	public static final String PDD_MP_INDICADOR_ENTIDAD_ID_MP_IND_ENTIDAD = "PddMpIndicadorEntidad.idMpIndEntidad";
	public static final String PDD_MP_INDICADOR_ENTIDAD_CODIGO_ENTIDAD = "PddMpIndicadorEntidad.codigoEntidad";
	public static final String PDD_MP_INDICADOR_ENTIDAD_ID_META_PROD_IND = "PddMpIndicadorEntidad.idMetaProdInd";
	public static final String PDD_MP_INDICADOR_ENTIDAD_MAGNITUD = "PddMpIndicadorEntidad.magnitud";
	public static final String PDD_MP_INDICADOR_ENTIDAD_PONDERACION = "PddMpIndicadorEntidad.ponderacion";
	
	/**
	 * Nombre de campos de la tabla: BP_PROY_INV_FINANCIA
	 */
	public static final String BP_PROY_INV_FINANCIA_LS_FUENTE= "BpProyInvFinancia.idLsFuente";
	public static final String BP_PROY_INV_FINANCIA_ID_PROY= "BpProyInvFinancia.idProyInversion";
	
	
	/**
	 * Longitud campos
	 */
	public static final int BUZON_USUARIO_ORIGINA_MIN_LENGTH = 1;
	public static final int BUZON_USUARIO_ORIGINA_MAX_LENGTH = 100;
	public static final int BUZON_USUARIO_DESTINO_MIN_LENGTH = 1;
	public static final int BUZON_USUARIO_DESTINO_MAX_LENGTH = 100;
	public static final int BUZON_USUARIO_MENSAJE_MIN_LENGTH = 1;
	public static final int BUZON_USUARIO_MENSAJE_MAX_LENGTH = 10000;

	/**
	 * Formatos
	 */
	public static final String FORMAT_DATETIME_SHORT_WITH_DASH = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_SHORT_WITH_DASH_DMY = "dd-MM-yyyy";
	public static final String FORMAT_DATE_SHORT_WITH_DASH_YMD = "yyyy-MM-dd";
	public static final String FORMAT_DATE_SHORT_WITH_SLASH_DMY = "dd/MM/yyyy";
	public static final String FORMAT_DATETIME_SHORT_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT_DATE_SHORT_WITH_SLASH = "yyyy/MM/dd";
	public static final String FORMAT_DATE_CREDITCARD_WITH_SLASH = "yyyy/MM";
	public static final String FORMAT_TIME = "HH:mm:ss";
	public static final String FORMAT_SHORT_TIME = "HH:mm";
	public static final String FECHA_HORA_INICIAL = " 00:00:00.000";
	public static final String FECHA_HORA_FINAL = " 23:59:59.999";
	public static final String FORMATO_FECHA_FILTRO = "dd/MM/yyyy HH:mm:ss.SSS";

	/**
	 * Mensajes normales
	 */

	public static final String MENSAJE_OBTENER_CONFIGURACION_CARGUE_ARCHIVO = "obtener_configuracion_cargue_exitoso";

	public static final String MENSAJE_EXITO_CORREO = "mensaje_exito_correo";
	public static final String MENSAJE_INICIO_SESION_CORRECTO = "mensaje_inicio_sesion_correcto";
	public static final String MENSAJE_CAMBIAR_CLAVE_CORRECTO = "mensaje_cambiar_clave_correcto";
	public static final String MENSAJE_CAMBIAR_CLAVE_INCORRECTO = "mensaje_cambiar_clave_incorrecto";
	public static final String MENSAJE_RESTABLACER_CLAVE_CORRECTO = "mensaje_restablecer_clave_correcto";
	public static final String MENSAJE_CORREO_ENVIADO_CORRECTO = "mensaje_correo_enviado_correcto";
	public static final String MENSAJE_CREAR_COMPONENTE_GASTO_CORRECTO = "mensaje_crear_componente_gasto_correcto";
	public static final String MENSAJE_CAMBIAR_ESTADO_COMPONENTE_GASTO_CORRECTO = "mensaje_cambiar_estado_componente_gasto_correcto";
	public static final String MENSAJE_MODIFICAR_COMPONENTE_GASTO_CORRECTO = "mensaje_modificar_componente_gasto_correcto";
	public static final String MENSAJE_OBTENER_COMPONENTE_GASTO_TODOS_CORRECTO = "mensaje_obtener_componente_gasto_todos_correcto";
	public static final String MENSAJE_REGISTROS_ASOCIADOS = "mensaje_registros_asociados";

	public static final String MENSAJE_CREAR_EQUIPAMIENTO_CORRECTO = "mensaje_crear_equipamiento_correcto";
	public static final String MENSAJE_MODIFICAR_EQUIPAMIENTO_CORRECTO = "mensaje_modificar_equipamiento_correcto";
	public static final String MENSAJE_CAMBIAR_ESTADO_EQUIPAMIENTO_CORRECTO = "mensaje_cambiar_estado_equipamiento_correcto";
	public static final String MENSAJE_OBTENER_EQUIPAMIENTO_TODOS_CORRECTO = "mensaje_obtener_equipamientos_todos_correcto";

	public static final String MENSAJE_CREAR_ESTRUCTURA_META_CORRECTO = "mensaje_crear_estructura_meta_correcto";
	public static final String MENSAJE_MODIFICAR_ESTRUCTURA_META_CORRECTO = "mensaje_modificar_estructura_meta_correcto";
	public static final String MENSAJE_CAMBIAR_ESTADO_ESTRUCTURA_META_CORRECTO = "mensaje_cambiar_estado_estructura_meta_correcto";
	public static final String MENSAJE_OBTENER_ESTRUCTURA_METAS_TODOS_CORRECTO = "mensaje_obtener_estructura_metas_todos_correcto";

	public static final String MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_CORRECTO = "mensaje_crear_informacion_presupuestal_correcto";
	public static final String MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELES = "mensaje_crear_informacion_presupuestal_incorrecto_sin_niveles";
	public static final String MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_1 = "mensaje_crear_informacion_presupuestal_incorrecto_sin_nivel_1";
	public static final String MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_2 = "mensaje_crear_informacion_presupuestal_incorrecto_sin_nivel_2";
	public static final String MENSAJE_CREAR_INFORMACION_PRESUPUESTAL_INCORRECTO_SIN_NIVELE_3 = "mensaje_crear_informacion_presupuestal_incorrecto_sin_nivel_3";

	public static final String MENSAJE_MODIFICAR_INFORMACION_PRESUPUESTAL_CORRECTO = "mensaje_modificar_informacion_presupuestal_correcto";
	public static final String MENSAJE_ELIMINAR_INFORMACION_PRESUPUESTAL_CORRECTO = "mensaje_eliminar_informacion_presupuestal_correcto";
	public static final String MENSAJE_CAMBIAR_ESTADO_INFORMACION_PRESUPUESTAL_CORRECTO = "mensaje_cambiar_estado_informacion_presupuestal_correcto";
	public static final String MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_TODOS_CORRECTO = "mensaje_obtener_informacion_presupuestal_todos_correcto";
	public static final String MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_DESARROLLO = "mensaje_obtener_informacion_presupuestal_plan_desarrollo";
	public static final String MENSAJE_OBTENER_INFORMACION_PRESUPUESTAL_PLAN_ENTIDAD = "mensaje_obtener_informacion_presupuestal_plan_entidad";

	public static final String MENSAJE_CREAR_PROYECTO_INVERSION_CORRECTO = "mensaje_crear_proyecto_inversion_correcto";
	public static final String MENSAJE_OBTENER_PROYECTO_INVERSION_ASIGNADOS_CORRECTO = "mensaje_obtener_proyectos_inversion_asignados_correcto";
	public static final String MENSAJE_OBTENER_PROYECTO_INVERSION_DISPONIBLES_CORRECTO = "mensaje_obtener_proyectos_inversion_disponibles_correcto";
	public static final String MENSAJE_OBTENER_PROYECTO_INVERSION_TODOS_CORRECTO = "mensaje_obtener_proyectos_inversion_todos_correcto";
	public static final String MENSAJE_ASIGNAR_PROYECTOS_INVERSION_USUARIO_CORRECTO = "mensaje_asignar_proyectos_inversion_usuario_correcto";
	public static final String MENSAJE_REMOVER_PROYECTOS_INVERSION_USUARIO_CORRECTO = "mensaje_remover_proyectos_inversion_usuario_correcto";

	public static final String MENSAJE_ASIGNAR_COMPONENTE_GESTION_USUARIO_CORRECTO = "mensaje_asignar_componente_gestion_usuario_correcto";
	public static final String MENSAJE_REMOVER_COMPONENTE_GESTION_USUARIO_CORRECTO = "mensaje_remover_componente_gestion_usuario_correcto";
	public static final String MENSAJE_OBTENER_TODOS_COMPONENTES_GESTION_USUARIO_CORRECTO = "mensaje_obtener_todos_componente_gestion_correcto";
	public static final String MENSAJE_OBTENER_TODOS_COMPONENTES_LIBRES_CORRECTO = "mensaje_obtener_componentes_libres_correcto";
	public static final String MENSAJE_REGISTRAR_LISTA_SIMPLE = "mensaje_registrar_lista_simple_correcto";
	public static final String MENSAJE_OBTENER_TODOS_LISTA_SIMPLE = "mensaje_obtener_todos_lista_simple_correcto";

	public static final String MENSAJE_CREAR_ENTIDAD_CORRECTO = "mensaje_crear_entidad_correcto";
	public static final String MENSAJE_OBTENER_ENTIDAD_ASIGNADAS_CORRECTO = "mensaje_obtener_entidad_asignadas_correcto";
	public static final String MENSAJE_OBTENER_ENTIDAD_DISPONIBLES_CORRECTO = "mensaje_obtener_entidad_disponibles_correcto";
	public static final String MENSAJE_OBTENER_ENTIDAD_TODOS_CORRECTO = "mensaje_obtener_entidad_todos_correcto";
	public static final String MENSAJE_ASIGNAR_USUARIO_ENTIDAD_CORRECTO = "mensaje_asignar_usuario_entidad_correcto";
	public static final String MENSAJE_REMOVER_USUARIO_ENTIDAD_CORRECTO = "mensaje_remover_usuario_entidad_correcto";

	public static final String MENSAJE_OBTENER_LISTA_COMPUESTA_CORRECTO = "mensaje_obtener_lista_compuesta_correcto";

	public static final String MENSAJE_MODIFICAR_LISTA_SIMPLE = "mensaje_modificar_lista_simple_correcto";
	public static final String MENSAJE_REGISTRAR_ARGUMENTO_LISTA_SIMPLE = "mensaje_registrar_argumento_lista_simple_correcto";
	public static final String MENSAJE_MODIFICAR_ARGUMENTO_LISTA_SIMPLE = "mensaje_modificar_argumento_lista_simple_correcto";
	public static final String MENSAJE_OBTENER_ARGUMENTO_LISTA_SIMPLE = "mensaje_obtener_argumento_lista_simple_correcto";
	public static final String MENSAJE_REGISTRAR_POT_PROYECTO_INSTRUMENTO = "mensaje_registrar_pot_proyecto_instrumento";
	public static final String MENSAJE_MODIFICAR_POT_PROYECTO_INSTRUMENTO = "mensaje_modificar_pot_proyecto_instrumento";
	public static final String MENSAJE_OBTENER_TODOS_POT_PROYECTO_INSTRUMENTO = "mensaje_obtener_todos_pot_proyecto_instrumento";
	public static final String MENSAJE_OBTENER_TODOS_POT_INSTRUMENTO = "mensaje_obtener_todos_pot_instrumento";
	public static final String MENSAJE_OBTENER_TODOS_POT_OBRA = "mensaje_obtener_todos_pot_obra";

	public static final String MENSAJE_REGISTRAR_LINEA_INVERSION_CORRECTO = "mensaje_registrar_linea_inversion_correcto";
	public static final String MENSAJE_MODIFICAR_LINEA_INVERSION_CORRECTO = "mensaje_modificar_linea_inversion_correcto";
	public static final String MENSAJE_OBTENER_TODOS_LINEA_INVERSION_CORRECTO = "mensaje_obtener_todos_linea_inversion_correcto";
	public static final String MENSAJE_CAMBIO_DE_ESTADO_CORRECTO = "mensaje_cambio_de_estado_correcto";
	public static final String MENSAJE_REGISTRAR_TERRITORIALIZACION_CORRECTO = "mensaje_registrar_territorializacion_correcto";
	public static final String MENSAJE_MODIFICAR_TERRITORIALIZACION_CORRECTO = "mensaje_modificar_territorializacion_correcto";
	public static final String MENSAJE_OBTENER_TODOS_TERRITORIALIZACION_CORRECTO = "mensaje_obtener_todos_territorializacion_correcto";
	public static final String MENSAJE_REGISTRAR_CONSECUTIVO_CORRECTO = "mensaje_registrar_consecutivo_correcto";
	public static final String MENSAJE_MODIFICAR_CONSECUTIVO_CORRECTO = "mensaje_modificar_consecutivo_correcto";
	public static final String MENSAJE_OBTENER_TODOS_CONSECUTIVO_CORRECTO = "mensaje_obtener_todos_consecutivo_correcto";
	public static final String MENSAJE_MODIFICAR_PARAMETRO_GENERAL_CORRECTO = "mensaje_modificar_parametro_general_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PARAMETRO_GENERAL_CORRECTO = "mensaje_obtener_todos_parametro_general";

	public static final String MENSAJE_OBTENER_TODOS_POR_USUARIO_BUZON_NOTIFICACION = "mensaje_obtener_todos_por_usuario_buzon_notificacion";
	public static final String MENSAJE_OBTENER_TODOS_ADMIN_BUZON_NOTIFICACION = "mensaje_obtener_todos_admin_buzon_notificacion";
	public static final String MENSAJE_OBTENER_POR_FILTRO_BUZON_NOTIFICACION = "mensaje_obtener_por_filtro_buzon_notificacion";
	public static final String MENSAJE_MODIFICAR_ESTADO_BUZON_NOTIFICACION = "mensaje_modificar_estado_buzon_notificacion";
	public static final String MENSAJE_OBTENER_TODOS_SERVICIOS = "mensaje_obtener_todos_servicios";
	public static final String MENSAJE_OBTENER_POR_ID_LISTA_SIMPLE = "mensaje_obtener_por_id_lista_simple";
	public static final String MENSAJE_OBTENER_NOTIFICACIONES_AUTOMATICO = "mensaje_obtener_notificaciones_automatico";
	public static final String MENSAJE_MODIFICAR_CONFIGURACION_NOTIFICACION = "mensaje_modificar_configuracion_notificacion";
	public static final String MENSAJE_OBTENER_POR_ID_LISTA_COMPUESTA = "mensaje_obtener_por_id_lista_compuesta";
	public static final String MENSAJE_OBTENER_TODOS_ARGUMENTO_LISTA_SIMPLE = "mensaje_obtener_todos_argumento_lista_simple";
	public static final String MENSAJE_OBTENER_TODOS_ARGUMENTO_LISTA_SIMPLE_POR_NOMBRE = "mensaje_obtener_todos_argumento_lista_simple_por_nombre";
	public static final String MENSAJE_OBTENER_POR_ID_TERRITORIALIZACION = "mensaje_obtener_por_id_territorializacion";

	public static final String MENSAJE_OBTENER_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO = "mensaje_obtener_funcionario_clave_entidad_correcto";
	public static final String MENSAJE_OBTENER_FUNCIONARIO_CLAVE_ENTIDAD_INCORRECTO = "mensaje_obtener_funcionario_clave_entidad_incorrecto";
	public static final String MENSAJE_CREAR_PROYECTO_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO = "mensaje_crear_proyecto_funcionario_clave_entidad_correcto";
	public static final String MENSAJE_CREAR_PROYECTO_FUNCIONARIO_CLAVE_ENTIDAD_INCORRECTO = "mensaje_crear_proyecto_funcionario_clave_entidad_incorrecto";

	public static final String MENSAJE_OBTENER_TODOS_USUARIO_ADMINISTRACION = "mensaje_obtener_todos_usuario_administracion";

	public static final String MENSAJE_CREAR_BUZON_EXITOSO = "mensaje_crear_buzon_exitoso";

	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_LEER_ARCHIVO_PLANO = "mensaje_cargue_archivo_exito_leer_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_CON_ERROR_EN_LINEAS = "mensaje_cargue_archivo_exito_con_error_en_lineas";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_LINEA_ARCHIVO_PLANO = "mensaje_cargue_archivo_exito_linea_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_ARMAR_INSERT_ARCHIVO_PLANO = "mensaje_cargue_archivo_exito_armar_insert_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_ARMAR_UPDATE_ARCHIVO_PLANO = "mensaje_cargue_archivo_exito_armar_update_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO_ARMAR_QUERY_ARCHIVO_PLANO = "mensaje_cargue_archivo_exito_armar_query_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_EXITO = "mensaje_cargue_archivo_exito";

	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_NO_EXISTE_XML = "mensaje_cargue_archivo_error_no_existe_xml";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_LEER_ARCHIVO_PLANO = "mensaje_cargue_archivo_error_leer_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_LINEA_ESTRUCTURA_ARCHIVO_PLANO = "mensaje_cargue_archivo_error_linea_estructura_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_LINEA_ARCHIVO_PLANO = "mensaje_cargue_archivo_error_linea_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_ARMAR_UPDATE_VALIDACION_ARCHIVO_PLANO = "mensaje_cargue_archivo_error_armar_update_validacion_archivo_plano";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_LINEA_ARCHIVO = "mensaje_cargue_archivo_error_guardar_linea_archivo";
	public static final String MENSAJE_CARGUE_ARCHIVO_ERROR_GUARDAR_ARCHIVO_DB = "mensaje_cargue_archivo_error_guardar_archivo_db";

	public static final String CARGUE_ARCHIVO_NUMLINEA = "NUMLINEA";
	public static final String CARGUE_ARCHIVO_ERROR = "ERROR";
	public static final String CARGUE_ARCHIVO_CAMPOS = "CAMPOS";
	public static final String CARGUE_ARCHIVO_RESULTADO_CARGUE = "RESULTADO CARGUE";
	public static final String CARGUE_ARCHIVO_CON_ERROR = "Con error";
	public static final String CARGUE_ARCHIVO_ACTUALIZADO = "Actualizado";
	public static final String CARGUE_ARCHIVO_INSERTADO = "Insertado";

	/**
	 * Mensajes Listas Compuesta
	 */
	public static final String MENSAJE_CREAR_LISTA_COMPUESTA_EXITOSO = "mensaje_crear_lista_compuesta_exitoso";
	public static final String MENSAJE_ACTUALIZAR_LISTA_COMPUESTA = "mensaje_actualizar_compuesta";
	public static final String MENSAJE_ASOCIACION_EXISTENTE = "mensaje_asociacion_existente";

	/**
	 * 
	 */

	/**
	 * Modulo IP IPFORMULACION
	 */
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_HISVPDDCOMPROMISO = "/ipformulacion/obtener_todos_hisvpddcompromiso";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_COMPROMISO_ESTRATEGICO = "/ipformulacion/obtener_todos_compromiso_estrategico";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_COMPROMISO_ESTRATEGICO = "/ipformulacion/registrar_compromiso_estrategico";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_COMPROMISO_ESTRATEGICO = "/ipformulacion/modificar_compromiso_estrategico";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_ESPECIFICO_POR_ID_COMPROMISO = "/ipformulacion/obtener_pdd_compromiso_especifico_por_id_compromiso/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO_ESPECIFICO = "/ipformulacion/registrar_pdd_compromiso_especifico";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO_ESPECIFICO = "/ipformulacion/modificar_pdd_compromiso_especifico";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_METAS_COMPROMISO_ESTRATEGICO = "/ipformulacion/obtener_metas_compromiso_estrategico/{id}";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_PR_VALORACION_POR_ID_PROBLEMATICA_Y_MOMENTO = "/ipformulacion/obtener_pdd_prb_valoracion_por_id_problematica_y_momento";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_INDICADORES = "/ipformulacion/obtener_todos_indicadores";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_INDICADORES_DE_PROBLEMATICA = "/ipformulacion/obtener_indicadores_problematica/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_INDICADORES_DE_PROBLEMATICA_FILTRADO = "/ipformulacion/obtener_indicadores_problematica_filtrado";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_COMPROMISOS_ESPECIFICOS_FILTRADO = "/ipformulacion/obtener_compromisos_especificos_filtrado";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICA_TODOS = "/ipformulacion/obtener_problematica_todos";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_INDICADOR_POR_ID = "/ipformulacion/obtener_pdd_indicador_por_id/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_POR_ID = "/ipformulacion/obtener_pdd_compromiso_por_id/{id}";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_VIGENTE = "/ipformulacion/obtener_pdd_vigente";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_VIGENTE = "/ipplandistrital/obtener_pdl_vigente/{codigoEntidad}";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_TODOS_PDD_COMPROMISO = "/ipformulacion/obtener_todos_pdd_compromiso";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPROMISO_POR_ID_PLAN_DESARROLLO = "/ipformulacion/obtener_pdd_compromiso_por_id_plan/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_OBRAS_POR_METAS = "/ipformulacion/obtener_obras_concretas/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_META_COMPROMISO = "/ipformulacion/registrar_meta";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_OBRA_CONCRETA = "/ipformulacion/registrar_obra_concreta";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_COMPROMISO = "/ipformulacion/registrar_pdd_compromiso";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_COMPROMISO = "/ipformulacion/modificar_pdd_compromiso";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PRB_INDICADOR = "/ipformulacion/registrar_indicador";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_INDICADOR_Y_PRB_INDICADOR = "/ipformulacion/registrar_indicador_y_prb_inficador";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_INDICADOR_Y_PRB_INDICADOR = "/ipformulacion/modificar_indicador_y_prb_inficador";

	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PBR_POBLACION = "/ipformulacion/registrar_pbr_poblacion";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_META_RESULTADO = "/ipformulacion/registrar_pdd_meta_resultado";

	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_OBRA_CONCRETA = "/ipformulacion/modificar_obra_concreta";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_METAS = "/ipformulacion/modificar_meta";

	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_META_RESULTADO_POR_ID_PRB_INDICADOR = "/ipformulacion/obtener_pdd_meta_resultado_por_id_prb_indicador/{id}";

	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PBR_POBLACION = "/ipformulacion/modificar_pbr_poblacion";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PRB_INDICADOR = "/ipformulacion/modificar_prb_indicador";
	public static final String CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PBR_POBLACION = "/ipformulacion/eliminar_pbr_poblacion/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_ELIMINAR_OBRA_CONCRETA = "/ipformulacion/eliminar_obra_concreta/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_ELIMINAR_META = "/ipformulacion/eliminar_meta/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PDD_COMPROMISO_ESPECIFICO = "/ipformulacion/eliminar_pdd_compromiso_especifico/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_ELIMINAR_PDD_PRB_INDICADOR = "/ipformulacion/eliminar_indicador/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_META_RESULTADO = "/ipformulacion/modificar_pdd_meta_resultado";

	// MODULO BP
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_FILTRO_BP_PROYECTO_INVERSION = "/bpproyinv/obtener_todos_filtro_bp_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION = "/bpproyinv/registrar_bp_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROYECTO_INVERSION = "/bpproyinv/eliminar_bp_proyecto_inversion/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROYECTO_INVERSION = "/bpproyinv/obtener_todos_bp_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_SIN_RELACION_CON_PROYECTO_INVERSION = "/bpproyinv/obtener_todos_bp_aporte_ciudadano_sin_relacion_con_proyecto_inversion/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION = "/bpproyinv/obtener_todos_proyectos_inversion_poblacion_asociados_a_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION = "/bpproyinv/registrar_varios_proyectos_inversion_poblacion_asociados_a_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_PROYECTO_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION = "/bpproyinv/registrar_proyecto_inversion_etnico_asociados_a_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INV_POBLACION = "/bpproyinv/obtener_todos_proyectos_inversion_etnico_asociados_a_proyecto_inv_poblacion";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_POBLACION = "/bpproyinv/modificar_bp_proy_inv_poblacion";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROY_INV_ETNICO = "/bpproyinv/modificar_bp_proy_inv_etnico";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_POBLACION = "/bpproyinv/eliminar_bp_proy_inv_poblacion";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_ETNICO = "/bpproyinv/eliminar_bp_proy_inv_etnico";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_CON_RELACION_CON_PORYECTO_INVERSION = "/bpproyinv/obtener_todos_bp_aporte_ciudadano_con_relacion_con_proyecto_inversion/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_APORTE_CIUDADANO_CARGADO_POR_ARCHIVO = "/bpproyinv/obtener_todos_bp_aporte_ciudadano_cargado_por_archivo";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_BP_APORTE_CIUDADANO_CARGADOS_POR_ARCHIVO_CON_BP_PROYECTO_INVERSION = "/bpproyinv/registrar_varios_bp_aporte_ciudadano_cargados_por_archivo_con_bp_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_APORTE_POR_ID_PROYECTO_INVERSION = "/bpproyinv/obtener_todos_bp_proy_inv_aporte_por_id_proyecto_inversion";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_INICIATIVA_CIUDADANA_CON_RELACION_CON_PORYECTO_INVERSION = "/bpproyinv/obtener_todos_bp_iniativa_ciudadana_con_relacion_con_proyecto_inversion/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_FILTRO_BP_INICIATIVA_CIUDADANA_VIABLE = "/bpproyinv/obtener_todos_filtro_bp_inciativa_ciudadana_viable";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_VARIOS_BP_INICIATIVA_CIUDADANA_VIABLES_CON_BP_PROYECTO_INVERSION = "/bpproyinv/registrar_varios_bp_iniciativa_ciudadana_viables_con_bp_proyecto_inversion";

	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_BP_PROYECTO_INVERSION_POR_ID = "/bpproyinv/obtener_bp_proyecto_inversion_por_id/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_BP_APORTE_CIUDADANO_POR_ID = "/bpproyinv/obtener_bp_aporte_ciudadano_por_id/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_APORTE_CIUDADANO = "/bpproyinv/registrar_bp_aporte_ciudadano";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_APORTE_CIUDADANO = "/bpproyinv/modificar_bp_aporte_ciudadano";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_APORTE = "/bpproyinv/eliminar_bp_proy_inv_aporte/{id}";

	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_BP_PROYECTO_INVERSION = "/bpproyinv/modificar_bp_proyecto_inversion";

	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_LOCALIZA = "/bpproyinv/resgitrar_bp_proyecto_inversion_localiza";
	
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_ESPECIF = "/bpproyinv/resgitrar_bp_proyecto_inversion_especif";
	
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_META_PLAN = "/bpproyinv/resgitrar_bp_proyecto_inversion_meta_plan";
	
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_PRODUCTO = "/bpproyinv/resgitrar_bp_proyecto_inversion_producto";
	
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_BP_PROYECTO_INVERSION_ACTIVIDAD = "/bpproyinv/resgitrar_bp_proyecto_inversion_actividad";

	// Problematicas
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICA_POR_ID = "/ipformulacion/obtener_problematica/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POR_COMPROMISO = "/ipformulacion/obtener_problematica_por_compromiso";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_PDD_PROBLEMATICA = "/ipformulacion/registrar_pdd_problematica";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POR_ID_COMPROMISO="/ipformulacion/obtener_problematica_por_id_compromiso/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_MODIFICAR_PDD_PROBLEMATICA="/ipformulacion/modificar_pdd_problematica";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PROBLEMATICAS_POBLACION_POR_ID_PROBLEMATICA="/ipformulacion/obtener_problematica_poblacion_por_id_problematica";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_PDD_COMPETENCIAS_ASOCIADAS_POR_ID_SECTOR = "/ipformulacion/obtener_pdd_competencias_asociadas_por_id_sector/{id}";
	public static final String CORE_CONTROLLER_IPFORMULACION_OBTENER_TODAS_PDD_COMPETENCIAS_ASOCIADAS = "/ipformulacion/obtener_todas_pdd_competencias_asociadas";
	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_COMPETENCIA_ASOCIADA = "/ipformulacion/registrar_modificar_pdd_competencia_asociada";

	public static final String CORE_CONTROLLER_IPFORMULACION_REGISTRAR_MODIFICAR_PDD_PRB_VALORACION = "/ipformulacion/registrar_modificar_pdd_prb_valoracion";

	/**
	 * Modulo IP IPPLANDISTRITAL
	 */
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_COMPONENTES_DESBALANCEADOS_NIVELES_POR_ID_PLAN_DESARROLLO = "/ipplandistrital/obtener_componentes_desbalanceados_niveles_por_id_plan_desarrollo/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD = "/ipplandistrital/obtener_todos_pdd";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD = "/ipplandistrital/registrar_pdd";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD = "/ipplandistrital/modificar_pdd";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_POR_ID = "/ipplandistrital/obtener_pdd_por_id/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_POR_ID_PLAN_DESARROLLO = "/ipplandistrital/obtener_todos_pdd_nivel/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_META_RESULTADO_POR_PROYECTO = "/ipplandistrital/obtener_pddmr_proyecto";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL = "/ipplandistrital/registrar_pdd_nivel";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_NIVEL = "/ipplandistrital/modificar_pdd_nivel";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_META_RESULTADO = "/ipplandistrital/eliminar_meta_resultado/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_INDICADOR_META_RESULTADO = "/ipplandistrital/eliminar_meta_resultado_indicador/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_NIVEL_ATRIBUTO = "/ipplandistrital/registrar_pdd_nivel_atributo";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_NIVEL_ATRIBUTO = "/ipplandistrital/modificar_pdd_nivel_atributo";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_NIVEL_ATRIBUTO = "/ipplandistrital/eliminar_pdd_nivel_atributo/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_NIVEL = "/ipplandistrital/obtener_todos_pdd_nivel_atributo_por_id_nivel";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE = "/ipplandistrital/obtener_todos_pdd_nivel_atributo_por_id_atributo_padre";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_POR_FILTRO_PDL = "/ipplandistrital/obtener_todos_filtro_pdl";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL = "/ipplandistrital/obtener_todos_pdl";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_POR_ID = "/ipplandistrital/obtener_pdl/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDL_NIVEL_POR_ID_PLAN_LOCAL = "/ipplandistrital/obtener_pdl_nivel/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL_NIVEL_ATRIBUTO_POR_ID_NIVEL = "/ipplandistrital/obtener_todos_pdl_nivel_atributo_por_id_nivel";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDL_NIVEL_ATRIBUTO_POR_ID_ATRIBUTO_PADRE = "/ipplandistrital/obtener_todos_pdl_nivel_atributo_por_id_atributo_padre";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDL = "/ipplandistrital/registrar_pdl";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDL = "/ipplandistrital/modificar_pdl";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDL_NIVEL = "/ipplandistrital/modificar_pdl_nivel";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDL_NIVEL_ATRIBUTO = "/ipplandistrital/registrar_pdl_nivel_atributo";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_RESULTADO_INDICADOR = "/ipplandistrital/registrar_indicador_meta_resultado";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO = "/ipplandistrital/registrar_meta_producto";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_META_PRODUCTO = "/ipplandistrital/modificar_meta_producto";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_META_PRODUCTO = "/ipplandistrital/eliminar_meta_producto/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_META_PRODUCTO_INDICADOR = "/ipplandistrital/registrar_meta_producto_indicador";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_META_PRODUCTO_INDICADOR = "/ipplandistrital/modificar_meta_producto_indicador";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_META_PRODUCTO_INDICADOR = "/ipplandistrital/eliminar_meta_producto_indicador/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADOR_META_RESULTADO = "/ipplandistrital/obtener_todos_indicador_mr";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_POR_MR = "/ipplandistrital/obtener_todos_meta_producto_mr";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_INDICADOR = "/ipplandistrital/obtener_todos_meta_producto_indicador";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_META_PRODUCTO_ENTIDADES = "/ipplandistrital/obtener_todos_meta_producto_entidades";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_COPIAR_ESTRUCTURA_DE_PDD_A_PDL = "/ipplandistrital/copiar_estructura_de_pdd_a_pdl";

	/**
	 * Modulo IP Plan de ordenamiento territorial
	 */

	public static final String CORE_CONTROLLER_IP_POT_MODIFICAR_POT = "/ippot/modificar_pot";
	public static final String CORE_CONTROLLER_IP_POT_RAMA_REGISTRAR_RAMA = "/ippot/registrar_rama_pot";
	public static final String CORE_CONTROLLER_IP_POT_RAMA_OBTENER_TODOS = "/ippot/obtener_todos_pot_rama/{id}";
	public static final String CORE_CONTROLLER_IP_POT_RAMA_OBTENER_TODOS_DESC = "/ippot/obtener_todos_pot_rama_desc/{id}";
	public static final String CORE_CONTROLLER_IP_POT_RAMA_ELIMINAR_RAMA = "/ippot/eliminar_rama_pot/{id}";
	public static final String CORE_CONTROLLER_IP_POT_NIVEL_POT_RAMA_OBTENER_TODOS = "ippot/obtener_todos_nivel_pot_rama/{id}";
	public static final String CORE_CONTROLLER_IP_POT_NIVEL_POT_NIVEL_OBTENER_TODOS = "ippot/obtener_todos_nivel_pot_nivel/{id}";
	public static final String CORE_CONTROLLER_IP_POT_NIVEL_REGISTRAR_NIVEL = "/ippot/registrar_nivel_pot";
	public static final String CORE_CONTROLLER_IP_POT_NIVEL_ELIMINAR_NIVEL = "/ippot/eliminar_nivel_pot/{id}";

	public static final String CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_OBRA_POR_ID_NIVEL_POT = "/ippot/obtener_todos_pot_obra_por_id_nivel_pot";
	public static final String CORE_CONTROLLER_IP_POT_OBRA_REGISTRAR_POT_OBRA = "/ippot/registrar_pot_obra";
	public static final String CORE_CONTROLLER_IP_POT_OBRA_MODIFICAR_POT_OBRA = "/ippot/modificar_pot_obra";
	public static final String CORE_CONTROLLER_IP_POT_OBRA_ELIMINAR_POT_OBRA = "/ippot/eliminar_pot_obra/{id}";

	public static final String CORE_CONTROLLER_IP_POT_OBTENER_TODOS_POT_INSTUMENTO_POR_ID_POT = "/ippot/obtener_todos_pot_instrumento_por_id_pot";
	public static final String CORE_CONTROLLER_IP_POT_REGISTRAR_POT_INSTUMENTO = "/ippot/registrar_pot_instrumento";
	public static final String CORE_CONTROLLER_IP_POT_MODIFICAR_POT_INSTUMENTO = "/ippot/modificar_pot_instrumento";
	public static final String CORE_CONTROLLER_IP_POT_ELIMINAR_POT_INSTUMENTO = "/ippot/eliminar_pot_instrumento/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_PDD_RANGO_PONDERACION = "/ipplandistrital/obtener_todos_pdd_rango_ponderacion";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_PDD_RANGO_PONDERACION_POR_ID_PDD = "/ipplandistrital/obtener_rango_ponderacion_por_id_pdd/{id}";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_PDD_RANGO_PONDERACION = "/ipplandistrital/registrar_rango_ponderacion";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_PDD_RANGO_PONDERACION = "/ipplandistrital/modificar_rango_ponderacion";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_PDD_RANGO_PONDERACION = "/ipplandistrital/eliminar_rango_ponderacion/{id}";

	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_REGISTRAR_MP_INDICADOR_ENTIDAD = "/ipplandistrital/registrar_mp_indicador_entidad";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_MODIFICAR_MP_INDICADOR_ENTIDAD = "/ipplandistrital/modificar_mp_indicador_entidad";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_ELIMINAR_MP_INDICADOR_ENTIDAD = "/ipplandistrital/eliminar_mp_indicador_entidad/{id}";
	/**
	 * Modulo BP Iniciativa Ciudadana
	 */
	public static final String CORE_CONTROLLER_BP_INCIATIVA_CIUDADANA_OBTENER_TODOS = "/iniciativa_ciudadana/obtener_todos";
	public static final String CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_REGISTRAR = "/iniciativa_ciudadana/registrar";
	public static final String CORE_CONTROLLER_BP_INICIAITIVA_CIUDADANA_OBTENER_POR_ID = "/iniciativa_ciudadana/obtener/{id}";
	public static final String CORE_CONTROLLER_BP_INICIAITIVA_CIUDADANA_OBTENER_TODOS_BP_PROY_INV_INICIATIVA_POR_ID_INICIATIVA = "/bpiniciativaciudadana/obtener_todos_bp_proy_inv_iniciativa_por_id_iniciativa/{id}";
	public static final String CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_ELIMINAR = "/iniciativa_ciudadana/eliminar/{id}";
	public static final String CORE_CONTROLLER_BP_INICIATIVA_CIUDADANA_MODIFICAR = "/iniciativa_ciudadana/modificar";
	/**
	 * Mensajes de CRUD
	 */
	public static final String MENSAJE_CREAR_COMPROMISO_ESTRATEGICO_CORRECTO = "mensaje_crear_compromiso_estrategico_correcto";
	public static final String MENSAJE_CREAR_PDD_CORRECTO = "mensaje_crear_pdd_correcto";
	public static final String MENSAJE_CREAR_PDD_NIVEL_CORRECTO = "mensaje_crear_pdd_nivel_correcto";
	public static final String MENSAJE_CREAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO = "mensaje_crear_pdd_compromiso_especifico_correcto";
	public static final String MENSAJE_CREAR_PDD_COMPROMISO_CORRECTO = "mensaje_crear_pdd_compromiso_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_COMPROMISO_CORRECTO = "mensaje_modificar_pdd_compromiso_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_PROBLEMATICA_CORRECTO = "mensaje_modificar_pdd_problematica_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_CORRECTO = "mensaje_crear_bp_proyecto_inversion_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_incorrecto";
	public static final String MENSAJE_CREAR_BP_ASIGNAR_INICIATIVA_CIUDADANA_CORRECTO = "mensaje_asignar_bp_iniciativa_ciudadana_correcto";
	public static final String MENSAJE_CREAR_BP_REGISTRAR_INICIATIVA_CIUDADANA_CORRECTO = "mensaje_registrar_bp_iniciativa_ciudadana_correcto";
	public static final String MENSAJE_CREAR_BP_INICIATIVA_CIUDADANA_INCORRECTO = "mensaje_crear_bp_iniciativa_ciudadana_incorrecto";
	public static final String MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO = "mensaje_relacionar_bp_aporte_ciudadano_correcto";
	public static final String MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA = "mensaje_relacionar_bp_iniciativa_ciudadana_correcta";
	public static final String MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_INCORRECTO = "mensaje_relacionar_bp_aporte_ciudadano_incorrecto";
	public static final String MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_INCORRECTA = "mensaje_relacionar_bp_iniciativa_ciudadana_incorrecta";
	public static final String MENSAJE_RELACIONAR_BP_APORTE_CIUDADANO_CORRECTO_INCORRECTO = "mensaje_relacionar_bp_aporte_ciudadano_correcto_incorrecto";
	public static final String MENSAJE_RELACIONAR_BP_INICIATIVA_CIUDADANA_CORRECTA_INCORRECTA = "mensaje_relacionar_bp_iniciativa_ciudadana_correcta_incorrecta";
	public static final String MENSAJE_CREAR_BP_APORTE_CIUDADANO_CORRECTO = "mensaje_crear_bp_aporte_ciudadano_correcto";
	public static final String MENSAJE_MODIFICAR_BP_APORTE_CIUDADANO_CORRECTO = "mensaje_modificar_bp_aporte_ciudadano_correcto";
	public static final String MENSAJE_CREAR_BP_APORTE_CIUDADANO_INCORRECTO = "mensaje_crear_bp_aporte_ciudadano_incorrecto";
	public static final String MENSAJE_CREAR_PDD_NIVEL_ATRIBUTO_CORRECTO = "mensaje_crear_pdd_nivel_atributo_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_NIVEL_ATRIBUTO_CORRECTO = "mensaje_modificar_pdd_nivel_atributo_correcto";
	public static final String MENSAJE_ELIMINAR_PDD_NIVEL_ATRIBUTO_CORRECTO = "mensaje_eliminar_pdd_nivel_atributo_correcto";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_CORRECTO = "mensaje_eliminar_bp_proyecto_inversion_correcto";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_INCORRECTO = "mensaje_eliminar_bp_proyecto_inversion_incorrecto";

	public static final String MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO = "mensaje_crear_relacion_proyectoinversion_con_politica_publica_correcto";
	public static final String MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO = "mensaje_modificar_relacion_proyectoinversion_con_politica_publica_correcto";
	public static final String MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_POLITICAPUBLICA_CORRECTO = "mensaje_eliminar_relacion_proyectoinversion_con_politica_publica_correcto";

	
	public static final String MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO = "mensaje_crear_relacion_proyectoinversion_con_lineainversion_correcto";
	public static final String MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO = "mensaje_modificar_relacion_proyectoinversion_con_lineainversion_correcto";
	public static final String MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_LINEAINVERSION_CORRECTO = "mensaje_eliminar_relacion_proyectoinversion_con_lineainversion_correcto";

	public static final String MENSAJE_CREAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO = "mensaje_crear_relacion_proyectoinversion_con_pddmrindicador_correcto";
	public static final String MENSAJE_MODIFICAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO = "mensaje_modificar_relacion_proyectoinversion_con_pddmrindicador_correcto";
	public static final String MENSAJE_ELIMINAR_RELACION_PROYECTOINVERSION_CON_PDDMRINDICADOR_CORRECTO = "mensaje_eliminar_relacion_proyectoinversion_con_pddmrindicador_correcto";

	
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_CORRECTO = "mensaje_modificar_bp_proyecto_inversion_correcto";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_NO_EXISTE = "mensaje_modificar_bp_proyecto_inversion_poblacion_no_existe";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_NUMERO_NO_VALIDO = "mensaje_modificar_bp_proyecto_inversion_poblacion_numero_no_valido";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_INCORRECTO = "mensaje_modificar_bp_proyecto_inversion_incorrecto";

	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_LOCALIZA_CORRECTO = "mensaje_crear_bp_proyecto_inversion_localiza_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_LOCALIZA_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_localiza_incorrecto";
	
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_ESPECIF_CORRECTO = "mensaje_crear_bp_proyecto_inversion_especif_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_ESPECIF_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_especif_incorrecto";
	
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_CORRECTO = "mensaje_crear_bp_proyecto_inversion_meta_plan_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_meta_plan_incorrecto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_META_PLAN_ESPECIF = "mensaje_crear_bp_proyecto_inversion_meta_plan_especif";
	
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_CORRECTO = "mensaje_crear_bp_proyecto_inversion_producto_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_producto_incorrecto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_PRODUCTO_OBJETIVO = "mensaje_crear_bp_proyecto_inversion_producto_objetivo";
	
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_CORRECTO = "mensaje_crear_bp_proyecto_inversion_actividad_correcto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_actividad_incorrecto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_ACTIVIDAD_PRODUCTO = "mensaje_crear_bp_proyecto_inversion_actividad_producto";
	
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_CORRECTO = "mensaje_crear_bp_proyecto_inversion_financia";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_INCORRECTO = "mensaje_crear_bp_proyecto_inversion_financia_incorrecto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_PROYECTO = "mensaje_crear_bp_proyecto_inversion_financia_proyecto";
	public static final String MENSAJE_CREAR_BP_PROYECTO_INVERSION_FINANCIA_IDS_FUENTES = "mensaje_crear_bp_proyecto_inversion_financia_ids_fuentes";
	
	public static final String MENSAJE_OBTENER_PDL_VIGENTE = "mensaje_obtener_pdl_vigente";
	public static final String MENSAJE_PDL_VIGENTE_INEXISTENTE = "mensaje_pdl_vigente_inexistente";
	public static final String MENSAJE_PDL_ENTIDAD_NO_ALCALDIA = "mensaje_pdl_entidad_no_alcaldia";

	public static final String MENSAJE_CREAR_PDD_COMPETENCIA_ASOCIADA_CORRECTO = "mensaje_crear_pdd_competencia_asociada_correcto";
	public static final String MENSAJE_CREAR_PDD_PRB_VALORACION_CORRECTO = "mensaje_crear_pdd_prb_valoracion_correcto";

	public static final String MENSAJE_CREAR_PDD_COMPROMISO_INCORRECTO = "mensaje_crear_pdd_compromiso_incorrecto";

	public static final String MENSAJE_CREAR_MODIFICAR_PDD_NIVELES_CORRECTO = "mensaje_crear_modificar_pdd_niveles_correcto";
	public static final String MENSAJE_CREAR_MODIFICAR_PDD_NIVELES_INCORRECTO = "mensaje_crear_modificar_pdd_niveles_incorrecto";
	public static final String MENSAJE_CREAR_PDD_NIVELES_CORRECTO = "mensaje_crear_pdd_niveles_correcto";
	public static final String MENSAJE_CREAR_PDD_NIVELES_INCORRECTO = "mensaje_crear_pdd_niveles_incorrecto";
	public static final String MENSAJE_MODIFICAR_PDD_NIVELES_CORRECTO = "mensaje_modificar_pdd_niveles_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_NIVELES_INCORRECTO = "mensaje_modificar_pdd_niveles_incorrecto";
	public static final String MENSAJE_CREAR_PDL_NIVELES_CORRECTO = "mensaje_crear_pdl_niveles_correcto";
	public static final String MENSAJE_CREAR_PDL_NIVELES_INCORRECTO = "mensaje_crear_pdl_niveles_incorrecto";

	public static final String MENSAJE_MODIFICAR_COMPROMISO_ESTRATEGICO_CORRECTO = "mensaje_modificar_compromiso_estrategico_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_CORRECTO = "mensaje_modificar_pdd_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_NIVEL_CORRECTO = "mensaje_modificar_pdd_nivel_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO = "mensaje_modificar_pdd_compromiso_especifico_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_COMPETENCIA_ASOCIADA_CORRECTO = "mensaje_modificar_pdd_competencia_asociada_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_PRB_VALORACION_CORRECTO = "mensaje_modificar_pdd_prb_valoracion_correcto";

	public static final String MENSAJE_CREAR_PDDMR_INDICADOR_INCORRECTO = "mensaje_crear_pddmr_indicador_incorrecto";
	public static final String MENSAJE_CREAR_PDDMR_INDICADOR_CORRECTO = "mensaje_crear_pddmr_indicador_correcto";

	public static final String MENSAJE_REGISTRAR_PDD_META_RESULTADO_CORRECTO = "mensaje_registrar_pdd_meta_resultado_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_META_RESULTADO_INCORRECTO = "mensaje_registrar_pdd_meta_resultado_incorrecto";
	public static final String MENSAJE_MODIFICAR_PDD_META_RESULTADO_CORRECTO = "mensaje_modificar_pdd_meta_resultado_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_META_RESULTADO_INCORRECTO = "mensaje_modificar_pdd_meta_resultado_incorrecto";

	public static final String MENSAJE_REGISTRAR_PDD_INDICADOR_CORRECTO = "mensaje_registrar_pdd_indicador_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_INDICADOR_INCORRECTO = "mensaje_registrar_pdd_indicador_incorrecto";
	public static final String MENSAJE_MODIFICAR_PDD_INDICADOR_CORRECTO = "mensaje_modificar_pdd_indicador_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_INDICADOR_INCORRECTO = "mensaje_modificar_pdd_indicador_incorrecto";
	public static final String MENSAJE_OBTENER_PDD_INDICADOR_CORRECTO = "mensaje_obtener_pdd_indicador_correcto";
	public static final String MENSAJE_OBTENER_PDD_INDICADOR_INCORRECTO = "mensaje_obtener_pdd_indicador_incorrecto";

	public static final String MENSAJE_OBTENER_PDD_COMPROMISO_CORRECTO = "mensaje_obtener_pdd_compromiso_correcto";
	public static final String MENSAJE_OBTENER_PDD_COMPROMISO_INCORRECTO = "mensaje_obtener_pdd_compromiso_incorrecto";

	public static final String MENSAJE_ELIMINAR_PDD_COMPROMISO_ESPECIFICO_CORRECTO = "mensaje_eliminar_pdd_compromiso_especifico_correcto";
	public static final String MENSAJE_ELIMINAR_PDD_COMPROMISO_ESPECIFICO_INCORRECTO = "mensaje_eliminar_pdd_compromiso_especifico_incorrecto";

	public static final String MENSAJE_OBTENER_TODOS_HISVPDDCOMPROMISO_CORRECTO = "mensaje_obtener_todos_hisvpddcompromiso_correcto";
	public static final String MENSAJE_OBTENER_TODOS_COMPROMISO_ESTRATEGICO_CORRECTO = "mensaje_obtener_todos_compromisoestrategico_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_CORRECTO = "mensaje_obtener_todos_pdd_correcto";
	public static final String MENSAJE_OBTENER_METAS_COMPROMISO_ESTRATEGICO_CORRECTO = "mensaje_obtener_metas_compromiso_estrategico_correcto";
	public static final String MENSAJE_OBTENER_PDD_METAS_RESULTADO_CORRECTO = "mensaje_obtener_pdd_metas_resultado_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_NIVEL_CORRECTO = "mensaje_obtener_todos_pdd_nivel_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_POR_ID_PLAN_DESARROLLO_CORRECTO = "mensaje_obtener_todos_pdd_compromiso_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_CORRECTO = "mensaje_obtener_todos_pdd_compromiso_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_COMPROMISO_ESPECIFICO_POR_ID_COMPROMISO = "mensaje_obtener_todos_pdd_compromiso_especifico_por_id_compromiso";
	public static final String MENSAJE_OBTENER_TODOS_COMPONENTES_DESBALANCEADOS_NIVELES_POR_ID_PLAN_DESARROLLO = "mensaje_obtener_todos_componentes_desbalanceados_niveles_por_id_plan_desarrollo";
	public static final String MENSAJE_OBTENER_TODOS_BP_PROYECTO_INVERSION_CORRECTO = "mensaje_obtener_todos_bp_proyecto_inversion_correcto";
	public static final String MENSAJE_OBTENER_TODOS_BP_APORTE_CIUDADANO_CORRECTO = "mensaje_obtener_todos_bp_aporte_ciudadano_correcto";
	public static final String MENSAJE_OBTENER_BP_PROYECTO_INVERSION_CORRECTO = "mensaje_obtener_bp_proyecto_inversion_correcto";
	public static final String MENSAJE_OBTENER_BP_APORTE_CIUDADANO_CORRECTO = "mensaje_obtener_bp_aporte_ciudadano_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_NIVEL_ATRIBUTO_CORRECTO = "mensaje_obtener_todos_pdd_nivel_atributo_correcto";
	public static final String MENSAJE_OBTENER_TODOS_BP_PROY_INV_APORTE_CORRECTO = "mensaje_obtener_todos_bp_proy_inv_aporte";

	public static final String MENSAJE_OBTENER_PDD_META_RESULTADO_INDICADOR_CORRECTO = "mensaje_obtener_pdd_meta_resultado_indicador_correcto";

	public static final String MENSAJE_OBTENER_PDD_META_RESULTADO_POR_PROYECTO = "mensaje_obtener_pdd_meta_resultado_por_proyecto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_COMPETENCIA_ASOCIADA_POR_ID_SECTOR_CORRECTO = "mensaje_obtener_todos_pdd_competencia_asociada_por_id_sector";
	public static final String MENSAJE_OBTENER_TODOS_PDD_COMPETENCIA_ASOCIADA_POR_ID_SECTOR_INCORRECTO = "mensaje_obtener_todos_pdd_competencia_asociada_por_id_sector_incorrecto";
	public static final String MENSAJE_OBTENER_PDD_PRB_VALORACION_POR_ID_PROBLEMATICA_Y_MOMENTO = "mensaje_obtener_pdd_prb_valoracion";

	public static final String MENSAJE_REGISTRAR_PDD_PBR_POBLACION_CORRECTO = "mensaje_registrar_pdd_pbr_poblacion_correcto";
	public static final String MENSAJE_REGISTRO_REPETIDO_PDD_PBR_POBLACION = "mensaje_registro_repetido_pdd_pbr_poblacion";
	public static final String MENSAJE_MODIFICAR_PDD_PBR_POBLACION_CORRECTO = "mensaje_modificar_pdd_pbr_poblacion_correcto";
	public static final String MENSAJE_ELIMINAR_PDD_PBR_POBLACION_CORRECTO = "mensaje_eliminar_pdd_pbr_poblacion_correcto";

	public static final String MENSAJE_OBTENER_PDD_CORRECTO = "mensaje_obtener_pdd_correcto";

	public static final String MENSAJE_OBTENER_PDL_CORRECTO = "mensaje_obtener_pdl_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDL_CORRECTO = "mensaje_obtener_todos_pdl_correcto";

	public static final String MENSAJE_CREAR_PDL_CORRECTO = "mensaje_crear_pdl_correcto";
	public static final String MENSAJE_CREAR_PDL_INCORRECTO = "mensaje_crear_pdl_incorrecto";

	public static final String MENSAJE_CREAR_PDL_ESTADO_VIGENTE = "mensaje_crear_pdl_estado_vigente";

	public static final String MENSAJE_CREAR_PDL_SIN_PDD_ESTADO_VIGENTE = "mensaje_crear_pdl_sin_pdd_estado_vigente";

	public static final String MENSAJE_OBTENER_PDL_NIVEL_CORRECTO = "mensaje_obtener_pdl_nivel_correcto";
	public static final String MENSAJE_CREAR_PDL_NIVEL_CORRECTO = "mensaje_crear_pdl_nivel_correcto";
	public static final String MENSAJE_MODIFICAR_PDL_NIVEL_CORRECTO = "mensaje_modificar_pdl_nivel_correcto";

	public static final String MENSAJE_PDL_ESTADO_VIGENTE = "mensaje_pdl_estado_vigente";

	public static final String MENSAJE_OBTENER_PDL_NIVEL_ATRIBUTO_CORRECTO = "mensaje_obtener_pdl_nivel_atributo_correcto";
	public static final String MENSAJE_CREAR_PDL_NIVEL_ATRIBUTO_CORRECTO = "mensaje_crear_pdl_nivel_atributo_correcto";

	public static final String MENSAJE_OBTENER_METAS_COMPROMISO_ESTRATEGICO_INCORRECTO = "mensaje_obtener_metas_compromiso_estrategico_incorrecto";
	public static final String MENSAJE_OBTENER_PDD_METAS_RESULTADO_INCORRECTO = "mensaje_obtener_pdd_metas_resultado_incorrecto";
	public static final String MENSAJE_OBTENER_PDD_META_PRODUCTO_CORRECTO = "mensaje_obtener_pdd_meta_producto_correcto";
	public static final String MENSAJE_ELIMINAR_PDD_META_PRODUCTO_CORRECTO = "mensaje_eliminar_pdd_meta_producto_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_META_PRODUCTO_CORRECTO = "mensaje_registrar_pdd_meta_producto_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_META_PRODUCTO_CORRECTO = "mensaje_modificar_pdd_meta_producto_correcto";

	public static final String MENSAJE_ELIMINAR_PDD_META_RESULTADO_CORRECTO = "mensaje_eliminar_pdd_meta_resultado_correcto";
	public static final String MENSAJE_ELIMINAR_PDD_META_RESULTADO_INCORRECTO = "mensaje_eliminar_pdd_meta_resultado_incorrecto";
	public static final String MENSAJE_ELIMINAR_PDD_META_RESULTADO_CON_ASOCIACION_INCORRECTO = "mensaje_eliminar_pdd_meta_resultado_con_asociacion_incorrecto";
	public static final String MENSAJE_ELIMINAR_PDD_META_RESULTADO_INDICADOR_CORRECTO = "mensaje_eliminar_pdd_meta_resultado_correcto";
	public static final String MENSAJE_PDD_ESTADO_FORMULACION = "mensaje_pdd_estado_formulacion";
	public static final String MENSAJE_PDD_ESTADO_VIGENTE = "mensaje_pdd_estado_vigente";
	public static final String MENSAJE_PDD_ESTADO_FINALIZADO = "mensaje_pdd_estado_finalizado";
	public static final String MENSAJE_PDD_NIVEL_ESTADO_FORMULACION = "mensaje_pdd_nivel_estado_formulacion";
	public static final String MENSAJE_PDD_NIVEL_ESTADO_FINALIZADO = "mensaje_pdd_nivel_estado_finalizado";
	public static final String MENSAJE_REGISTRAR_PDD_NIVEL_ESTADO_VIGENTE = "mensaje_registrar_pdd_nivel_estado_vigente";
	public static final String MENSAJE_PDD_DEVOLVER_ESTADO = "mesanej_pdd_devolver_estado";
	public static final String MENSAJE_PDD_MODIFICAR_SIN_NIVELES = "mesanej_pdd_modificar_sin_niveles";

	public static final String MENSAJE_OBTENER_TODOS_POT_OBRA_CORRECTO = "mensaje_obtener_todos_pot_obra_correcto";
	public static final String MENSAJE_OBTENER_TODOS_POT_OBRA_INCORRECTO = "mensaje_obtener_todos_pot_obra_incorrecto";

	public static final String MENSAJE_OBTENER_TODOS_POT_INTRUMENTO_CORRECTO = "mensaje_obtener_todos_pot_intrumento_correcto";
	public static final String MENSAJE_CREAR_POT_INSTRUMENTO_EXITOSO = "mensaje_crear_pot_instrumento_exitoso";
	public static final String MENSAJE_MODIFICAR_POT_INSTRUMENTO_EXITOSO = "mensaje_modificar_pot_instrumento_exitoso";
	public static final String MENSAJE_ELIMINAR_POT_INSTRUMENTO_EXITOSO = "mensaje_eliminar_pot_instrumento_exitoso";

	public static final String MENSAJE_CREAR_POT_OBRA_EXITOSO = "mensaje_crear_pot_obra_exitoso";
	public static final String MENSAJE_CREAR_POT_OBRA_EXITOSO_SIN_ENTIDADES = "mensaje_crear_pot_obra_exitoso_sin_entidades";
	public static final String MENSAJE_MODIFICAR_POT_OBRA_EXITOSO = "mensaje_modificar_pot_obra_exitoso";
	public static final String MENSAJE_ELIMINAR_POT_OBRA_EXITOSO = "mensaje_eliminar_pot_obra_exitoso";

	public static final String MENSAJE_OBTENER_TODOS_POT_CORRECTO = "mensaje_obtener_todos_pot_correcto";
	public static final String MENSAJE_CREAR_POT_EXITOSO = "mensaje_crear_pot_exitoso";
	public static final String MENSAJE_CREAR_POT_INCORRECTO = "mensaje_crear_pot_incorrecto";
	public static final String MENSAJE_CREAR_POT_INCORRECTO_VIGENTE = "mensaje_crear_pot_incorrecto_vigente";
	public static final String MENSAJE_MODIFICAR_POT_CORRECTO = "mensaje_editar_pot_correcto";

	public static final String MENSAJE_CREAR_POT_RAMA_CORRECTO = "mensaje_crear_pot_rama_correcto";
	public static final String MENSAJE_OBTENER_TODOS_POT_RAMA_CORRECTO = "mensaje_obtener_todos_pot_rama_correcto";
	public static final String MENSAJE_ELIMINAR_RAMA_POT_CORRECTO = "mensaje_eliminar_rama_pot_correcto";
	public static final String MENSAJE_ELIMINAR_RAMA_POT_INCORRECTO = "mensaje_eliminar_rama_pot_incorrecto";

	public static final String MENSAJE_OBTENER_TODOS_POT_NIVEL_CORRECTO = "mensaje_obtener_todos_pot_nivel_correcto";
	public static final String MENSAJE_CREAR_POT_NIVEL_CORRECTO = "mensaje_crear_pot_nivel_correcto";
	public static final String MENSAJE_ELIMINAR_NIVEL_POT_CORRECTO = "mensaje_eliminar_nivel_pot_correcto";
	public static final String MENSAJE_ELIMINAR_NIVEL_POT_INCORRECTO = "mensaje_eliminar_nivel_pot_incorrecto";

	public static final String MENSAJE_OBTENER_TODOS_PDD_RANGO_PONDERACION = "mensaje_obtener_todos_pdd_rango_ponderacion";
	public static final String MENSAJE_ELIMINAR_PDD_RANGO_PONDERACION_CORRECTO = "mensaje_eliminar_pdd_rango_ponderacion_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_CORRECTO = "mensaje_registrar_pdd_rango_ponderacion_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_RANGO_PONDERACION_INCORRECTO = "mensaje_registrar_pdd_rango_ponderacion_incorrecto";
	public static final String MENSAJE_MODIFICAR_PDD_RANGO_PONDERACION_CORRECTO = "mensaje_modificar_pdd_rango_ponderacion_correcto";

	/**
	 * Mensajes de error
	 */
	public static final String MENSAJE_NO_ENCONTRADO = "No se encontro el mensaje seleccionado";
	public static final String MENSAJE_ARCHIVO_PROCESADO_NO_ENCONTRADO = "mensaje_archivo_procesado_no_encontrado";
	public static final String MENSAJE_BANCO_DE_PROYECTOS_NO_ENCONTRADO = "mensaje_banco_de_proyectos_no_encontrado";
	public static final String MENSAJE_COMPONENTE_GASTO_NO_ENCONTRADO = "mensaje_componente_gasto_no_encontrado";
	public static final String MENSAJE_CONFIGURACION_CARGUE_ARCHIVO_NO_ENCONTRADO = "mensaje_configuracion_archivo_no_encontrado";
	public static final String MENSAJE_ENTIDAD_NO_ENCONTRADO = "mensaje_entidad_no_encontrado";
	public static final String MENSAJE_ENTIDAD_NO_EXISTENTE_BD_SEGPLAN = "mensaje_entidad_no_existente_bd_segplan";
	public static final String MENSAJE_ENTIDAD_NO_EXISTENTE_BD_SEGURIDAD = "mensaje_entidad_no_existente_bd_seguridad";

	public static final String MENSAJE_REGISTRO_YA_EXISTENTE = "mensaje_registro_ya_existente";
	public static final String MENSAJE_EQUIPAMIENTO_NO_ENCONTRADO = "mensaje_equipamiento_no_encontrado";
	public static final String MENSAJE_ESTRUCTURA_META_NO_ENCONTRADO = "mensaje_estructura_meta_no_encontrado";
	public static final String MENSAJE_PLAN_DESARROLLO_NO_ENCONTRADO = "mensaje_id_plan_desarrollo_no_encontrado";
	public static final String MENSAJE_CONFIG_NOTIFICACION_NO_ENCONTRADO = "mensaje_id_config_notificacion_no_encontrado";
	public static final String MENSAJE_INFORMACION_PRESUPUESTAL_NO_ENCONTRADO = "mensaje_informacion_presupuestal_no_encontrado";
	public static final String MENSAJE_LISTA_ASOCIACION_NO_ENCONTRADO = "mensaje_lista_asociacion_no_encontrado";
	public static final String MENSAJE_LISTA_CATEGORIA_NO_ENCONTRADO = "mensaje_lista_categoria_no_encontrado";
	public static final String MENSAJE_LISTA_CLASIFICACION_NO_ENCONTRADO = "mensaje_lista_clasificacion_no_encontrado";
	public static final String MENSAJE_LISTA_TIPO_PROYECTO_NO_ENCONTRADO = "mensaje_lista_tipo_proyecto_no_encontrado";
	public static final String MENSAJE_LISTA_SECTOR_NO_ENCONTRADO = "mensaje_lista_sector_no_encontrado";
	public static final String MENSAJE_LISTA_UNIDAD_MEDIDA_NO_ENCONTRADO = "mensaje_lista_unidad_medida_no_encontrado";
	public static final String MENSAJE_LISTA_VERBO_NO_ENCONTRADO = "mensaje_lista_verbo_no_encontrado";
	public static final String MENSAJE_PROYECTO_DE_INVERSION_NO_ENCONTRADO = "mensaje_proyecto_de_inversion_no_encontrado";
	public static final String MENSAJE_TIPO_ARCHIVO_NO_ENCONTRADO = "mensaje_tipo_archivo_no_encontrado";
	public static final String MENSAJE_USUARIO_ENTIDAD_NO_ENCONTRADO = "mensaje_usuario_entidad_no_encontrado";

	public static final String MENSAJE_ERROR_ASIGNAR_COMPONENTE = "mensaje_error_asignar_componente";
	public static final String MENSAJE_ERROR_INICIO_SESION_CREDENCIALES_INVALIDAS = "mensaje_error_inicio_sesion_credenciales_invalidas";
	public static final String MENSAJE_ERROR_VALIDACION = "mensaje_error_validacion";
	public static final String MENSAJE_LONGITUD_DE_CAMPOS_INCORRECTO = "mensaje_error_longitud_campos";
	public static final String MENSAJE_ERROR_OBLIGATORIEDAD = "mensaje_error_validacion_obligatoriedad";
	public static final String MENSAJE_ERROR_SELECCION = "mensaje_error_validacion_seleccion";
	public static final String MENSAJE_ERROR_SELECCION_COMPROMISO = "mensaje_error_validacion_seleccion_compromiso";
	public static final String MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD = "mensaje_no_se_encuentra_la_entidad";
	public static final String MENSAJE_ERROR_VALIDACION_DILIGENCIAR = "mensaje_error_validacion_diligenciar";
	public static final String MENSAJE_ERROR_SELECCION_APORTES = "mensaje_error_validacion_seleccion_aportes";
	public static final String MENSAJE_ERROR_BP_APORTE_CIUDADANO_RELACIONADO_BP_PROYECTO_INVERSION = "mensaje_error_bp_aporte_ciudadano_relacionado_bp_proyecto_inversion";
	public static final String MENSAJE_ERROR_BP_INICIATIVA_CIUDADANA_RELACIONADA_BP_PROYECTO_INVERSION = "mensaje_error_bp_iniciativa_ciudadana_relacionada_bp_proyecto_inversion";

	/**
	 * Mensajes de validacion
	 */
	public static final String MENSAJE_VALIDACION_APROPIACION_RESERVA_VACIO = "mensaje_validacion_apropiacion_reserva_vacio";
	public static final String MENSAJE_VALIDACION_APROPIACION_DEFINITIVA_VACIO = "mensaje_validacion_apropiacion_definitiva_vacio";
	public static final String MENSAJE_VALIDACION_ARGUMENTO_VACIO = "mensaje_validacion_argumento_vacio";
	public static final String MENSAJE_VALIDACION_ARGUMENTO_LISTA_SIMPLE_1_VACIO = "mensaje_validacion_argumento_lista_simple_1_vacio";
	public static final String MENSAJE_VALIDACION_ARGUMENTO_LISTA_SIMPLE_2_VACIO = "mensaje_validacion_argumento_lista_simple_2_vacio";
	public static final String MENSAJE_VALIDACION_CLAVE_VACIO = "mensaje_validacion_clave_vacio";
	public static final String MENSAJE_VALIDACION_CLAVE_ANTERIOR_VACIO = "mensaje_validacion_clave_anterior_vacio";
	public static final String MENSAJE_VALIDACION_CLAVE_NUEVA_VACIO = "mensaje_validacion_clave_nuevo_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_CLASIFICACION_PRESUPUESTAL_VACIO = "mensaje_validacion_codigo_componente_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_COMPONENTE_VACIO = "mensaje_validacion_codigo_componente_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_DISTRITAL_VACIO = "mensaje_validacion_codigo_distrital_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_ENTIDAD_VACIO = "mensaje_validacion_codigo_entidad_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_INTERNO_VACIO = "mensaje_validacion_codigo_interno_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_PARAMETRO_VACIO = "mensaje_validacion_codigo_parametro_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_PROYECTO_VACIO = "mensaje_validacion_codigo_proyecto_vacio";
	public static final String MENSAJE_VALIDACION_COMPONENTE_GESTION = "mensaje_validacion_componente_gestion_vacio";
	public static final String MENSAJE_VALIDACION_COMPONENTE_GESTION_USUARIO_USUARIO = "mensaje_validacion_componente_gestion_usuario_usuario_vacio";
	public static final String MENSAJE_VALIDACION_CONCEPTO_VACIO = "mensaje_validacion_concepto_vacio";
	public static final String MENSAJE_VALIDACION_CONFIGURACION_VACIO = "mensaje_validacion_configuracion_vacio";
	public static final String MENSAJE_VALIDACION_CONSTITUCION_RESERVA_VACIO = "mensaje_validacion_constitucion_reserva_vacio";
	public static final String MENSAJE_VALIDACION_CORREO_VACIO = "mensaje_validacion_correo_vacio";
	public static final String MENSAJE_VALIDACION_DETALLE_VACIO = "mensaje_validacion_detalle_vacio";
	public static final String MENSAJE_VALIDACION_EJECUCION_GIRO_RESERVAS_VACIO = "mensaje_validacion_ejecucion_giro_reservas_vacio";
	public static final String MENSAJE_VALIDACION_EJECUCION_VIGENCIA_VACIO = "mensaje_validacion_ejecucion_vigencia_vacio";
	public static final String MENSAJE_VALIDACION_ENTIDAD_VACIO = "mensaje_validacion_entidad_vacio";
	public static final String MENSAJE_VALIDACION_ESTABLECIDO_VACIO = "mensaje_validacion_establecido_vacio";
	public static final String MENSAJE_VALIDACION_ESTADO_VACIO = "mensaje_validacion_estado_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_VACIO = "mensaje_validacion_fecha_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_CARGUE_VACIO = "mensaje_validacion_fecha_cargue_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_CREACION_VACIO = "mensaje_validacion_fecha_creacion_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_MODIFICACION_VACIO = "mensaje_validacion_fecha_modificacion_vacio";
	public static final String MENSAJE_VALIDACION_GIROS_VIGENCIA_VACIO = "mensaje_validacion_giros_vigencia_vacio";
	public static final String MENSAJE_VALIDACION_GESTION_PROYECTOS_VACIO = "mensaje_validacion_gestion_proyectos_vacio";
	public static final String MENSAJE_VALIDACION_GESTION_USUARIOS_VACIO = "mensaje_validacion_gestion_usuarios_vacio";
	public static final String MENSAJE_VALIDACION_ID_ARCHIVO_VACIO = "mensaje_validacion_id_archivo_vacio";
	public static final String MENSAJE_VALIDACION_ID_ARGUMENTO_VACIO = "mensaje_validacion_id_argumento_vacio";
	public static final String MENSAJE_VALIDACION_ID_BANCO_PROYECTO_VACIO = "mensaje_validacion_id_banco_proyecto_vacio";
	public static final String MENSAJE_VALIDACION_ID_COMPONENTE_GESTION_USUARIO_VACIO = "mensaje_validacion_id_componente_gestion_usuario_vacio";
	public static final String MENSAJE_VALIDACION_ID_COMPONENTE_GASTO_VACIO = "mensaje_validacion_id_componente_gasto_vacio";
	public static final String MENSAJE_VALIDACION_ID_CONFIG_CARGUE_VACIO = "mensaje_validacion_id_config_cargue_vacio";
	public static final String MENSAJE_VALIDACION_ID_CONSECUTIVO_VACIO = "mensaje_validacion_id_consecutivo_vacio";
	public static final String MENSAJE_VALIDACION_ID_EQUIPAMIENTO = "mensaje_validacion_id_equipamiento_vacio";
	public static final String MENSAJE_VALIDACION_ID_ESTRUCTURA_META_VACIO = "mensaje_validacion_id_estructura_meta_vacio";
	public static final String MENSAJE_VALIDACION_ID_INFO_PRESUPUESTAL_VACIO = "mensaje_validacion_id_info_presupuestal_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_VEREDA_VACIO = "mensaje_validacion_id_ls_vereda_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_LOCALIDAD_VACIO = "mensaje_validacion_ls_localidad_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_ASOCIACION_VACIO = "mensaje_validacion_id_ls_asociacion_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_BARRIO_VACIO = "mensaje_validacion_id_ls_barrio_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_CATEGORIA_VACIO = "mensaje_validacion_id_ls_categoria_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_CLASIFICACION_VACIO = "mensaje_validacion_id_ls_clasificacion_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_LINEA_INVERSION_VACIO = "mensaje_validacion_id_ls_inversion_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_SECTOR_VACIO = "mensaje_validacion_id_ls_sector_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_UNIDAD_MEDIDA_VACIO = "mensaje_validacion_id_ls_unidad_medida_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_UPR_VACIO = "mensaje_validacion_id_ls_upd_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_UPZ_VACIO = "mensaje_validacion_id_ls_upz_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_VERBO_VACIO = "mensaje_validacion_id_ls_verbo_vacio";
	public static final String MENSAJE_VALIDACION_ID_LINEA_INVERSION_VACIO = "mensaje_validacion_id_linea_inversion_vacio";

	public static final String MENSAJE_VALIDACION_ID_ESPECIFICO_VACIO = "mensaje_validacion_id_especifico_vacio";
	public static final String MENSAJE_VALIDACION_ID_COMPROMISO_VACIO = "mensaje_validacion_id_compromiso_vacio";
	public static final String MENSAJE_VALIDACION_DESCRIPCION_VACIO = "mensaje_validacion_descripcion_vacio";

	public static final String MENSAJE_VALIDACION_ID_META_RESULTADO_VACIO = "mensaje_validacion_id_meta_resultado_vacio";
	public static final String MENSAJE_VALIDACION_COMPLEMENTO_VACIO = "mensaje_validacion_complemento_vacio";
	public static final String MENSAJE_VALIDACION_ES_FORMULACION_VACIO = "mensaje_validacion_es_formulacion_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_TIPO_ANUALIZACION_VACIO = "mensaje_validacion_id_ls_tipo_anualizacion_vacio";
	public static final String MENSAJE_VALIDACION_MAGNITUD_VACIO = "mensaje_validacion_magnitud_vacio";

	public static final String MENSAJE_VALIDACION_ID_INDICADOR_VACIO = "mensaje_validacion_id_indicador_vacio";
	public static final String MENSAJE_VALIDACION_LINEA_BASE_DES_VACIO = "mensaje_validacion_linea_base_des_vacio";
	public static final String MENSAJE_VALIDACION_FUENTE_VACIO = "mensaje_validacion_fuente_vacio";
	public static final String MENSAJE_VALIDACION_YEAR_LINEA_BASE_VACIO = "mensaje_validacion_year_linea_base_vacio";
	public static final String MENSAJE_VALIDACION_INFORMACION_SOPORTE_VACIO = "mensaje_validacion_informacio_soporte_vacio";

	public static final String MENSAJE_VALIDACION_ID_META_PRODUCTO_VACIO = "mensaje_validacion_id_meta_producto_vacio";
	public static final String MENSAJE_VALIDACION_BLOQUEAR_PPI_VACIO = "mensaje_validacion_bloquear_ppi_vacio";
	public static final String MENSAJE_VALIDACION_META_PDD_VACIO = "mensaje_validacion_meta_pdd_vacio";
	public static final String MENSAJE_VALIDACION_OBSERVACION_VACIO = "mensaje_validacion_observacio_vacio";
	public static final String MENSAJE_VALIDACION_PONDERACION_VACIO = "mensaje_validacion_ponderacion_vacio";
	// Mensajes de validacion de bpProyectoInversion
	public static final String MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_VACIO = "mensaje_validacion_id_proyecto_inversion_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_PROYECTO_BPIN_VACIO = "mensaje_validacion_codigo_proyecto_bpin_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_PROYECTO_BPIN_VACIO = "mensaje_validacion_nombre_proyecto_bpin_vacio";
	public static final String MENSAJE_VALIDACION_TIPO_PROYECTO_VACIO = "mensaje_validacion_tipo_proyecto_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_ETAPA_VACIO = "mensaje_validacion_id_ls_etapa_vacio";
	public static final String MENSAJE_VALIDACION_BLOQUEO_VACIO = "mensaje_validacion_bloqueo_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_ESTADO_VACIO = "mensaje_validacion_id_ls_estado_vacio";
	public static final String MENSAJE_VALIDACION_TIPOS_PROYECTOS_VACIO = "mensaje_validacion_tipos_proyectos_vacio";
<<<<<<< HEAD
	public static final String MENSAJE_PROY_INV_NO_EXISTE = "mensaje_proy_inv_no_existe";

=======
	// mENSAJES DE VALIDACION DE  BpProyInvFinancia
	public static final String MENSAJE_VALIDACION_IDLS_FUENTE_VACIO = "mensaje_validacion_idls_vacio";
	public static final String MENSAJE_VALIDACION_ID_PROYECTO_VACIO = "mensaje_validacion_id_proecto_vacio";
	
	
>>>>>>> developer
	// Mensajes Validacion de bp Iniciativas Ciudadanas
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_INICIATIVA_VACIO = "mensaje_validacion_id_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_RADICADO_VACIO = "mensaje_validacion_radicado_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_FECHA_RADICADO_VACIO = "mensaje_validacion_fecha_radicado_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NUMERO_RADICADO_VACIO = "mensaje_validacion_numero_radicado_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NOMBRE_VACIO = "mensaje_validacion_nombre_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LS_ORIGEN_VACIO = "mensaje_validacion_id_ls_origen_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_APLICA_LINEA_VACIO = "mensaje_validacion_aplica_linea_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_PROBLEMATICA_VACIO = "mensaje_validacion_problematica_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPZ_VACIO = "mensaje_validacion_id_lc_territorializacion_upz_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_TERRITORIALIZACION_UPR_VACIO = "mensaje_validacion_id_lc_territorializacion_upz_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_GRUPOS_ETARIOS_VACIO = "mensaje_validacion_grupos_etarios_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_TOTAL_POBLACION_VACIO = "mensaje_validacion_total_poblacion_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ALTERNATIVA_SOLUCION_VACIO = "mensaje_validacion_total_alternativa_solucion_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_NOMBRE_PROP_VACIO = "mensaje_validacion_total_nombre_prop_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LS_ESTADO_INICIA_VACIO = "mensaje_validacion_id_ls_estado_inicia_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CONDICION_POBLACIONAL_VACIO = "mensaje_validacion_condicion_poblacional_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CODIGO_INICIATIVA_VACIO = "mensaje_validacion_codigo_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_EMAIL_PROP_INICIATIVA_VACIO = "mensaje_validacion_email_prop_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_TEL_PROP_INICIATIVA_VACIO = "mensaje_validacion_tel_prop_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_IDENTIFICACION_PROP_INICIATIVA_VACIO = "mensaje_validacion_identificacion_prop_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_ID_LC_LINEA_INV_INICIATIVA_VACIO = "mensaje_validacion_id_lc_linea_inv_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_FECHA_CODIGO_VACIO = "mensaje_validacion_fecha_codigo_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_OBSERVACION_VACIO = "mensaje_validacion_observacion_iniciativa_ciudadana_vacio";
	public static final String MENSAJE_VALIDACION_BP_INICIATIVA_CIUDADANA_CODIGO_ENTIDAD_VACIO = "mensaje_validacion_codigo_entidad_iniciativa_ciudadana_vacio";

	/*
	 * Mensaje validacion de BpNivelAtributo
	 */
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_VACIO = "mesaje_validacion_bp_nivel_atributo_id_atributo_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NUMERO_VACIO = "mesaje_validacion_bp_nivel_atributo_numero_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_DENOMINACION_VACIO = "mesaje_validacion_bp_nivel_atributo_denominacion_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PONDERACION_VACIO = "mesaje_validacion_bp_nivel_atributo_ponderacion_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_NOMBRE_GERENTE_VACIO = "mesaje_validacion_bp_nivel_atributo_nombre_gerente_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_CORREO_GERENTE_VACIO = "mesaje_validacion_bp_nivel_atributo_correo_gerente_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_LS_GENERO_GERENTE_VACIO = "mesaje_validacion_bp_nivel_atributo_id_ls_genero_gerente_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_PDD_NIVEL_VACIO = "mesaje_validacion_bp_nivel_atributo_id_pdd_nivel_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_ID_ATRIBUTO_PADRE_VACIO = "mesaje_validacion_bp_nivel_atributo_id_atributo_padre_vacio";
	public static final String MENSAJE_VALIDACION_PDD_NIVEL_ATRIBUTO_PROYECTO_ESTRATEGICO_VACIO = "mesaje_validacion_bp_nivel_atributo_proyecto_estrategico_vacio";

	public static final String MENSAJE_VALIDACION_ID_ESTRATEGICO_VACIO = "mesaje_validacion_id_estrategico_vacio";

	public static final String MENSAJE_VALIDACION_ID_LS_COMPETENCIA_VACIO = "mensaje_validacion_id_ls_competencia_vacio";

	public static final String MENSAJE_VALIDACION_ID_VALORACION_VACIO = "mensaje_validacion_id_valoracion_vacio";
	public static final String MENSAJE_VALIDACION_GRAVEDAD_VACIO = "mensaje_validacion_gravedad_vacio";
	public static final String MENSAJE_VALIDACION_DURACION_VACIO = "mensaje_validacion_duracion_vacio";
	public static final String MENSAJE_VALIDACION_IMPACTO_VACIO = "mensaje_validacion_impacto_vacio";
	public static final String MENSAJE_VALIDACION_DEBILIDAD_VACIO = "mensaje_validacion_debilidad_vacio";
	public static final String MENSAJE_VALIDACION_BALANCE_INICIAL_VACIO = "mensaje_validacion_balance_inicial_vacio";
	public static final String MENSAJE_VALIDACION_OBSERVACIONES_VACIO = "mensaje_validacion_observaciones_vacio";
	public static final String MENSAJE_VALIDACION_MOMENTO_VACIO = "mensaje_validacion_momento_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_DIMENSION_VACIO = "mensaje_validacion_id_ls_dimension_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_COMPETENCIA_1__VACIO = "mensaje_validacion_id_ls_competencia_1_vacio";
	public static final String MENSAJE_VALIDACION_ID_LS_COMPETENCIA_2_VACIO = "mensaje_validacion_id_ls_competencia_2_vacio";
	public static final String MENSAJE_VALIDACION_ID_PROBLEMATICA_VACIO = "mensaje_validacion_id_problematica_vacio";

	public static final String MENSAJE_VALIDACION_IDCOMPROMISO_ESTRATEGICO_VACIO = "mensaje_validacion_idcompromiso_estrategico_vacio";
	public static final String MENSAJE_VALIDACION_ID_COMPROMISO_ESTRATEGICO_VACIO = "mensaje_validacion_id_compromiso_estrategico_vacio";
	public static final String MENSAJE_VALIDACION_ID_TEMATICA_VACIO = "mensaje_validacion_id_tematica_vacio";
	public static final String MENSAJE_VALIDACION_ID_LISTA_SIMPLE_VACIO = "mensaje_validacion_id_lista_simple_vacio";
	public static final String MENSAJE_VALIDACION_ID_PLAN_DESARROLLO_VACIO = "mensaje_validacion_id_plan_desarrollo_vacio";
	public static final String MENSAJE_VALIDACION_ID_POT_PROYECTO_INSTRUMENTO_VACIO = "mensaje_validacion_id_pot_proyecto_instrumento_vacio";
	public static final String MENSAJE_VALIDACION_ID_PROYECTO_INVERSION_USUARIO_VACIO = "mensaje_validacion_id_proyecto_inversion_usuario_vacio";
	public static final String MENSAJE_VALIDACION_ID_TERRITORIALIZACION_VACIO = "mensaje_validacion_id_territorializacion_vacio";
	public static final String MENSAJE_VALIDACION_ID_TIPO_ARCHIVO_VACIO = "mensaje_validacion_id_tipo_archivo_vacio";
	public static final String MENSAJE_VALIDACION_ID_USUARIO_VACIO = "mensaje_validacion_id_usuario_vacio";
	public static final String MENSAJE_VALIDACION_ID_USUARIO_ENTIDAD_VACIO = "mensaje_validacion_id_usuario_entidad_vacio";
	public static final String MENSAJE_VALIDACION_ID_VACIO = "mensaje_validacion_id_vacio";
	public static final String MENSAJE_VALIDACION_LISTA_SIMPLE_VACIO = "mensaje_validacion_lista_simple_vacio";
	public static final String MENSAJE_VALIDACION_MES_VACIO = "mensaje_validacion_mes_vacio";
	public static final String MENSAJE_VALIDACION_MODULO_VACIO = "mensaje_validacion_modulo_vacio";

	public static final String MENSAJE_VALIDACION_NOMBRE_PLAN_VACIO = "mensaje_nombre_plan_vacio";
	public static final String MENSAJE_VALIDACION_SIGLA_PLAN_VACIO = "mensaje_sigla_plan_vacio";
	public static final String MENSAJE_VALIDACION_YEAR_INICIO_VACIO = "mensaje_year_inicio_vacio";
	public static final String MENSAJE_VALIDACION_YEAR_FINAL_VACIO = "mensaje_year_final_vacio";

	public static final String MENSAJE_VALIDACION_URL_PLAN_VACIO = "mensaje_url_plan_vacio";

	public static final String MENSAJE_VALIDACION_PROGRAMA_GOBIERNO_VACIO = "mensaje_programa_gobierno_vacio";

	public static final String MENSAJE_VALIDACION_ID_PLAN_DESARROLLO = "mensaje_validacion_idplandesarrollo_vacio";
	public static final String MENSAJE_VALIDACION_ACTO_ADMINISTRATIVO_VACIO = "mensaje_validacion_acto_administrativo_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_ACTO_VACIO = "mensaje_validacion_fecha_acto_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_ALCALDE_VACIO = "mensaje_validacion_nombre_alcalde_vacio";
	public static final String MENSAJE_VALIDACION_CORREO_ALCALDE_VACIO = "mensaje_validacion_correo_alcalde_vacio";
	public static final String MENSAJE_VALIDACION_IDLSADOPTADO_VACIO = "mensaje_validacion_idlsadoptado_vacio";
	public static final String MENSAJE_VALIDACION_IDLSAVANCESGR_VACIO = "mensaje_validacion_idlsavancesgr_vacio";
	public static final String MENSAJE_VALIDACION_IDESTADOPLAN_VACIO = "mensaje_validacion_idestadoplan_vacio";
	public static final String MENSAJE_VALIDACION_IDLSNIVELES_VACIO = "mensaje_validacion_idlsniveles_vacio";

	public static final String MENSAJE_VALIDACION_ID_PDD_NIVEL_VACIO = "mensaje_validacion_id_pdd_nivel_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_NIVEL_VACIO = "mensaje_validacion_codigo_nivel_vacio";
	public static final String MENSAJE_VALIDACION_DESCRIPCION_NIVEL_VACIO = "mensaje_validacion_descripcion_nivel_vacio";
	public static final String MENSAJE_VALIDACION_ID_PDD_VACIO = "mensaje_validacion_id_pdd_vacio";
	public static final String MENSAJE_VALIDACION_OBLIGATORIO_PDL_VACIO = "mensaje_validacion_obligatorio_pdl_vacio";

	public static final String MENSAJE_VALIDACION_ID_PDL_NIVEL_VACIO = "mensaje_validacion_pld_id_nivel_vacio";
	public static final String MENSAJE_VALIDACION_PDL_CODIGO_NIVEL_VACIO = "mensaje_validacion_pdl_codigo_nivel_vacio";
	public static final String MENSAJE_VALIDACION_PDL_DESCRIPCION_NIVEL_VACIO = "mensaje_validacion_pdl_descripcion_nivel_vacio";
	public static final String MENSAJE_VALIDACION__PDL_ID_PDL_VACIO = "mensaje_validacion_pdl_id_pdl_vacio";

	public static final String MENSAJE_VALIDACION_ID_MP_ENTIDAD_VACIO = "mensaje_validacion_id_mp_entidad_vacio";
	public static final String MENSAJE_VALIDACION_ID_META_PRODUCTO_INDICADOR = "mensaje_validacion_id_meta_prducto_indicador";

	public static final String MENSAJE_VALIDACION_NOMBRE_VACIO = "mensaje_validacion_nombre_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_PROYECTO_VACIO = "mensaje_validacion_nombre_proyecto_vacio";
	public static final String MENSAJE_VALIDACION_ORIGEN_VACIO = "mensaje_validacion_origen_vacio";
	public static final String MENSAJE_VALIDACION_PD_VACIO = "mensaje_validacion_pd_vacio";
	public static final String MENSAJE_VALIDACION_PI_VACIO = "mensaje_validacion_pi_vacio";
	public static final String MENSAJE_VALIDACION_RECURSOS_SUSPENDIDOS_VACIO = "mensaje_validacion_recursos_suspendidos_vacio";
	public static final String MENSAJE_VALIDACION_RESULTADO_VACIO = "mensaje_validacion_resultado_vacio";
	public static final String MENSAJE_VALIDACION_SECUENCIA_VACIO = "mensaje_validacion_secuencia_vacio";
	public static final String MENSAJE_VALIDACION_TEMA_VACIO = "mensaje_validacion_tema_vacio";
	public static final String MENSAJE_VALIDACION_TIPO_VACIO = "mensaje_validacion_tipo_vacio";
	public static final String MENSAJE_VALIDACION_USUARIO_CREACION_VACIO = "mensaje_validacion_usuario_creacion_vacio";
	public static final String MENSAJE_VALIDACION_USUARIO_MODIFICACION_VACIO = "mensaje_validacion_usuario_modificacion_vacio";
	public static final String MENSAJE_VALIDACION_VIGENCIA_VACIO = "mensaje_validacion_vigencia_vacio";
	public static final String MENSAJE_VALIDACION_MENSAJE_VACIO = "mensaje_validacion_mensaje_vacio";
	public static final String MENSAJE_VALIDACION_ROL_VACIO = "mensaje_validacion_rol_vacio";
	public static final String MENSAJE_VALIDACION_ID_CONFIG_NOTIFICACION = "mensaje_validacion_id_config_notificacion";
	public static final String MENSAJE_VALIDACION_USUARIOS_VACIO = "mensaje_validacion_usuarios_vacio";
	public static final String MENSAJE_VALIDACION_ASUNTO_VACIO = "mensaje_validacion_asunto_vacio";
	public static final String MENSAJE_VALIDACION_REQUIERE_ACCION_VACIO = "mensaje_validacion_requiere_accion_vacio";
	public static final String MENSAJE_VALIDACION_OPERACION_ORIGEN_VACIO = "mensaje_validacion_operacion_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_ESCRITURA_VACIO = "mensaje_validacion_fecha_escritura_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_LECTURA_VACIO = "mensaje_validacion_fecha_lectura_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_RESPUESTA_VACIO = "mensaje_validacion_fecha_respuesta_vacio";
	public static final String MENSAJE_VALIDACION_ID_NOTIFICACION_VACIO = "mensaje_validacion_id_notificacion_vacio";
	public static final String MENSAJE_VALIDACION_RESPUESTA_VACIO = "mensaje_validacion_respuesta_vacio";
	public static final String MENSAJE_VALIDACION_TIPO_MENSAJE_VACIO = "mensaje_validacion_tipo_mensaje_vacio";
	public static final String MENSAJE_VALIDACION_USUARIO_DESTINO_VACIO = "mensaje_validacion_usuario_destino_vacio";
	public static final String MENSAJE_VALIDACION_USUARIO_ORIGINA_VACIO = "mensaje_validacion_usuario_origina_vacio";
	public static final String MENSAJE_VALIDACION_ESTADO_SOLICITUD_VACIO = "mensaje_validacion_estado_solicitud_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_SOLICITUD_VACIO = "mensaje_validacion_fecha_solicitud_vacio";
	public static final String MENSAJE_VALIDACION_ID_ESTADO_SOLICITUD_VACIO = "mensaje_validacion_id_estado_solicitud_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_SERVICIO_VACIO = "mensaje_validacion_nombre_servicio_vacio";
	public static final String MENSAJE_VALIDACION_TAREA_VACIO = "mensaje_validacion_tarea_vacio";
	public static final String MENSAJE_VALIDACION_YEAR_VACIO = "mensaje_validacion_year_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_codigo_his_admin_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_nombre_vacio";
	public static final String MENSAJE_VALIDACION_SIGLA_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_sigla_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_INICIO_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_fecha_inicio_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_FINAL_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_fecha_final_vacio";
	public static final String MENSAJE_VALIDACION_ACTO_ADMINISTRATIVO_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_acto_administrativo_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_ENTIDAD_HISTORIAL_SECTORIAL = "mensaje_validacion_codigo_entidad_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_HISTORIAL_SECTORIAL = "mensaje_validacion_codigo_his_sectorial_vacio";
	public static final String MENSAJE_VALIDACION_NOMBRE_HISTORIAL_SECTORIAL = "mensaje_validacion_nombre_vacio";
	public static final String MENSAJE_VALIDACION_ACTIVO_HISTORIAL_SECTORIAL = "mensaje_validacion_activo_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_INICIO_HISTORIAL_SECTORIAL = "mensaje_validacion_fecha_inicio_vacio";
	public static final String MENSAJE_VALIDACION_FECHA_FINAL_HISTORIAL_SECTORIAL = "mensaje_validacion_fecha_final_vacio";
	public static final String MENSAJE_VALIDACION_ACTO_ADMINISTRATIVO_HISTORIAL_SECTORIAL = "mensaje_validacion_acto_administrativo_vacio";
	public static final String MENSAJE_VALIDACION_CODIGO_ENTIDAD_HISTORIAL_ADMINISTRATIVO = "mensaje_validacion_codigo_entidad_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_FUNCIONARIO_CLAVE_ENTIDAD = "mensaje_validacion_id_funcionario_clave_entidad_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CODIDO_ENTIDAD = "mensaje_validacion_codigo_entidad_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_NOMBRE = "mensaje_validacion_nombre_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_FUNCION = "mensaje_validacion_id_ls_funcion_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_ID_LS_GENERO = "mensaje_validacion_id_ls_genero_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CARGO = "mensaje_validacion_cargo_vacio";
	public static final String MENSAJE_VALIDACION_FUNCIONARIO_CLAVE_ENTIDAD_CORREO = "mensaje_validacion_correo_vacio";
	public static final String MENSAJE_VALIDACION_MENSAJE_TAMANO_INVALIDO = "mensaje_validacion_mensaje_tamano_invalido";

	/**
	 * Mensajes de validacion Usuario - Seguridad
	 */
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_USUARIO = "mensaje_validacion_seg_usuario_usuario";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_ESTADO = "mensaje_validacion_seg_usuario_estado";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_CODIGO_ENTIDAD = "mensaje_validacion_seg_usuario_codigo_entidad";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_CODIGO_TIPO_USUARIO = "mensaje_validacion_seg_usuario_codigo_tipo_usuario";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_FECHA_CADUCIDA = "mensaje_validacion_seg_usuario_fecha_caducida";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_FECHA_ULTIMO_INGRESO = "mensaje_validacion_seg_usuario_fecha_ultimo_ingreso";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_NOMBRE = "mensaje_validacion_seg_usuario_nombre";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_CORREO_ELECTRONICO = "mensaje_validacion_seg_usuario_correo_electronico";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_TELEFONO = "mensaje_validacion_seg_usuario_telefono";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_CAMBIAR_CONTRASENIA = "mensaje_validacion_seg_usuario_cambiar_contrasenia";
	public static final String MENSAJE_VALIDACION_SEG_USUARIO_NUMERO_INTENTOS = "mensaje_validacion_seg_usuario_numero_intentos";

	/**
	 * Mensajes de validacion pddMeta
	 */
	public static final String MENSAJE_VALIDACION_PDD_META_ID_META = "mensaje_validacion_pdd_meta_id_meta";
	public static final String MENSAJE_VALIDACION_PDD_META_ID_ESPECIFICO = "mensaje_validacion_pdd_meta_id_especifico";
	public static final String MENSAJE_VALIDACION_PDD_META_ID_TIPO_META_LS = "mensaje_validacion_pdd_meta_id_tipo_meta_ls";
	public static final String MENSAJE_VALIDACION_PDD_META_MAGNITUD = "mensaje_validacion_pdd_meta_magnitud";
	public static final String MENSAJE_VALIDACION_PDD_META_META = "mensaje_validacion_pdd_meta_meta";

	/**
	 * Mensajes validacion pddMpEntidad
	 */

	public static final String MENSAJE_VALIDACION_PDD_MP_ENTIDAD_ID_MP_ENTIDAD = "mensaje_validacion_pdd_mp_entidad_id_mp_entidad";
	public static final String MENSAJE_VALIDACION_PDD_MP_ENTIDAD_CODIGO_ENTIDAD = "mensaje_validacion_pdd_mo_entidad_codigo_entidad";
	public static final String MENSAJE_VALIDACION_PDD_MP_ENTIDAD_MAGNITUD = "mensaje_validacion_pdd_mp_entidad_magnitud";
	public static final String MENSAJE_VALIDACION_PDD_MP_ENTIDAD_ESTADO = "mensaje_validacion_pdd_mp_entidad_estado";
	public static final String MENSAJE_VALIDACION_PDD_MP_ENTIDAD_ID_META_PRODCUTO = "mensaje_validacion_pdd_mp_entidad_id_meta_producto";
	/**
	 * Mensajes de validacion obraConcreta
	 */
	public static final String MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_ID_CONCRETA = "mensaje_validacion_pdd_obra_conreta_id_concreta";
	public static final String MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_ID_META = "mensaje_validacion_pdd_obra_concreta_id_meta";
	public static final String MENSAJE_VALIDACION_PDD_OBRA_CONCRETA_OBRA_CONCRETA = "mensaje_validacion_pdd_obra_obra_concreta";

	/**
	 * Mensajes de validacion PddProblematica
	 */
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_PROBLEMATICA = "mensaje_validacion_pdd_problematica_id_problematica";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_COMPROMISO = "mensaje_validacion_pdd_problematica_id_compromiso";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LS_LOCALIZACION = "mensaje_validacion_pdd_problematica_id_localizacion";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LS_SUBLOCALIZACION = "mensaje_validacion_pdd_problematica_id_sublocalizacion";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ID_LZ_UPZUPR = "mensaje_validacion_pdd_problematica_id_upzupr";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_PROBLEMATICA = "mensaje_validacion_pdd_problematica_problematica";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_OBJETIVO = "mensaje_validacion_pdd_problematica_objetivo";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_CAUSA = "mensaje_validacion_pdd_problematica_causa";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_CONSECUENCIA = "mensaje_validacion_pdd_problematica_consecuencia";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_DESCRIPCION = "mensaje_validacion_pdd_problematica_descripcion";
	public static final String MENSAJE_VALIDACION_PDD_PROBLEMATICA_ERROR_CAMPOS_VACIOS = "mensaje_validacion_pdd_problematica_error_campos_vacios";

	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_ID_EXITO = "mensaje_obtener_pdd_problematica_por_id_exito";
	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_ID_SIN_RESGISTROS = "mensaje_obtener_pdd_problematica_por_id_sin_registros";
	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_ID_ERROR = "mensaje_obtener_pdd_problematica_por_id_error";

	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_EXITO = "mensaje_obtener_pdd_problematica_por_compromiso_exito";
	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_SIN_RESGISTROS = "mensaje_obtener_pdd_problematica_por_compromiso_sin_registros";
	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POR_COMPROMISO_ERROR = "mensaje_obtener_pdd_problematica_por_compromiso_error";
	public static final String MENSAJE_OBTENER_PDD_PROBLEMATICA_POBLACION_EXITO = "mensaje_obtener_pdd_problematica_problematica_exito";
	public static final String MENSAJE_REGISTRAR_PDD_PROBLEMATICA_CORRECTO = "mensaje_registrar_pdd_problematica_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_PROBLEMATICA_REPETIDO = "mensaje_registrar_pdd_problematica_repetido";
	public static final String MENSAJE_REGISTRAR_PDD_PROBLEMATICA_ERROR_FK = "mensaje_registrar_pdd_problematica_error_fk";
	
	/**
	 * Mensajes de validacion PrbIndicador
	 */
	public static final String MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_PROBIND = "mensaje_validacion_pdd_prb_indicador_id_probInd";
	public static final String MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_INDICADOR = "mensaje_validacion_pdd_prb_indicador_id_indicador";
	public static final String MENSAJE_VALIDACION_PDD_PRB_INDICADOR_ID_PROBLEMATICA = "mensaje_validacion_pdd_prb_indicador_id_problematica";
	
	/**
	 * Mesajes validacion BpProyInvPolitica
	 */
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POLITICA_IDS_POL_PUB_COCATENADOS_VACIO = "mensaje_validacion_bp_proy_inv_politica_ids_pol_pul_concatenados_vacio";
	
	
	
	/*
	 * Mensaje de validacion BpProyInvAporte
	 */
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_PROY_INVERSION_VACIO = "mensaje_validacion_bp_proy_inv_aporte_id_proy_inversion_vacio";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_APORTE_VACIO = "mensaje_validacion_bp_proy_inv_aporte_id_aporte_vacio";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_APORTE_IDS_APORTES_VACIO = "mensaje_validacion_bp_proy_inv_aporte_ids_aportes_vacio";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_APORTE_ID_PROY_INV_APORTE_VACIO = "mensaje_validacion_bp_proy_inv_aporte_id_proy_inv_aporte_vacio";

	/*
	 * Mensaje de validacino de BpProyInvIniciativa
	 */
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_INICIATIVA_IDS_INICIATIVAS_CONCATENADAS_VACIO = "mensaje_validacion_bp_proy_inv_iniciativa_ids_iniciativas_concatenadas_vacio";
	

	/**
	 * Mensaje de validación IPpot
	 */

	public static final String MENSAJE_VALIDACION_IP_POT_ID_POT_VACIO = "mensaje_validacion_ip_pot_id_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_CODIGO_POT_VACIO = "mensaje_validacion_ip_pot_codigo_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_YEAR_INICIO_POT_VACIO = "mensaje_validacion_ip_pot_year_inicio_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_YEAR_FIN_POT_VACIO = "mensaje_validacion_ip_pot_year_fin_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_ID_LS_ADOPTADO_POT_VACIO = "mensaje_validacion_ip_pot_id_ls_adoptado_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_ACTO_ADMINISTRATIVO_POT_VACIO = "mensaje_validacion_ip_pot_acto_administrativo_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_FECHA_POT_VACIO = "mensaje_validacion_ip_pot_fecha_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_URL_POT_VACIO = "mensaje_validacion_ip_pot_url_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_NOMBRE_POT_VACIO = "mensaje_validacion_ip_pot_nombre_pot_vacio";
	public static final String MENSAJE_VALIDACION_IP_POT_CAMPOS_VACIOS = "mensaje_validacion_ip_pot_campos_vacios";

	/**
	 * Mensaje de validación potRama
	 */
	public static final String MENSAJE_VALIDACION_IP_POT_RAMA_ID_RAMA = "mensaje_validacion_ip_pot_rama_id_rama";
	public static final String MENSAJE_VALIDACION_IP_POT_RAMA_NUMERO_RAMA = "mensaje_validacion_ip_pot_rama_numero_rama";
	public static final String MENSAJE_VALIDACION_IP_POT_RAMA_ID_POT = "mensaje_validacion_ip_pot_rama_id_pot";
	public static final String MENSAJE_VALIDACION_IP_POT_RAMA_CAMPOS_VACIOS = "mensaje_validacion_ip_pot_rama_campos_vacios";

	/**
	 * Mensaje de validación potNivel
	 */

	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_ID_NIVEL = "mensaje_validacion_ip_pot_nivel_id_nivel";
	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_NUMERO_NIVEL = "mensaje_validacion_ip_pot_nivel_numero_nivel";
	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_DESCRIPICION = "mensaje_validacion_ip_pot_nivel_descripcion";
	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_ID_NIVEL_PADRE = "mensaje_validacion_ip_pot_nivel_id_nivel_padre";
	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_ID_RAMA_POT = "mensaje_validacion_ip_pot_nivel_id_rama_pot";
	public static final String MENSAJE_VALIDACION_IP_POT_NIVEL_CAMPOS_VACIOS = "mensaje_validacion_ip_pot_nivel_campos_vacios";
	/*
	 * 
	 * /** Mensaje de validacion PotObra
	 */
	public static final String MENSAJE_VALIDACION_POT_OBRA_ID_OBRA_PROY_POT = "mensaje_validacion_pot_obra_id_obra_pry_por";
	public static final String MENSAJE_VALIDACION_POT_OBRA_CODIGO = "mensaje_validacion_pot_obra_codigo";
	public static final String MENSAJE_VALIDACION_POT_OBRA_OBRA = "mensaje_validacion_pot_obra_obra";
	public static final String MENSAJE_VALIDACION_POT_OBRA_ID_LS_PLAZO = "mensaje_validacion_pot_obra_id_ls_plazo";
	public static final String MENSAJE_VALIDACION_POT_OBRA_ID_NIVEL_POT = "mensaje_validacion_pot_obra_id_nivel_pot";
	public static final String MENSAJE_VALIDACION_POT_OBRA_CODIGO_ENTIDAD_CONCATENADOS = "mensaje_validacion_pot_obra_codigo_entidad_concatenadas";
	
	/*
	 * 
	 * /** Mensaje de validacion BpProyInvPoblacion
	 */
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_POBLACION = "mensaje_validacion_bp_proy_inv_poblacion_id_poblacion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_NUMERO = "mensaje_validacion_bp_proy_inv_poblacion_id_numero";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_DESCRIPCION = "mensaje_validacion_bp_proy_inv_poblacion_descripcion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_LS_ETARIO = "mensaje_validacion_bp_proy_inv_poblacion_id_ls_etario";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_ID_PROYECTO_INVERSION = "mensaje_validacion_bp_proy_inv_poblacion_id_proyecto_inversion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_POBLACION_IDS_LS_ETARIO_CONCATENADOS_VACIO = "mensaje_validacion_bp_proy_inv_poblacion_ids_ls_etario_concatenados_vacio";
	public static final String MENSAJE_CREACION_GRUPO_ETARIO_EXITOSO = "mensaje_creacion_grupo_etario_exitoso";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_EXITOSO = "mensaje_modificar_bp_proyecto_inversion_poblacion_exitoso";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_POBLACION_ERROR = "mensaje_modificar_bp_proyecto_inversion_poblacion_error";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_NO_EXISTENTE = "mensaje_eliminar_bp_proyecto_inversion_poblacion_no_existente";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_REGISTROS_ASOCIADOS = "mensaje_eliminar_bp_proyecto_inversion_poblacion_registros_asociados";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_POBLACION_EXITOSO = "mensaje_eliminar_bp_proyecto_inversion_poblacion_exitoso";
	
	
	
	/*
	 * 
	 * /** Mensaje de validacion BpProyInvEtnico
	 */
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_POBLACION = "mensaje_validacion_bp_proy_inv_etnico_id_poblacion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_NUMERO = "mensaje_validacion_bp_proy_inv_etnico_id_numero";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_DESCRIPCION = "mensaje_validacion_bp_proy_inv_etnico_descripcion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_LS_ETARIO = "mensaje_validacion_bp_proy_inv_etnico_id_ls_etario";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_PROYECTO_INVERSION = "mensaje_validacion_bp_proy_inv_etnico_id_proyecto_inversion";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_ETNICO= "mensaje_validacion_bp_proy_inv_etnico_id_etnico";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_ID_LS_ETNICO= "mensaje_validacion_bp_proy_inv_etnico_id_ls_etnico";
	public static final String MENSAJE_VALIDACION_BP_PROY_INV_ETNICO_IDS_LS_ETARIO_CONCATENADOS_VACIO = "mensaje_validacion_bp_proy_inv_etnico_ids_ls_etario_concatenados_vacio";
	public static final String MENSAJE_CREACION_GRUPO_ETNICO_EXITOSO = "mensaje_creacion_grupo_etnico_exitoso";
	public static final String MENSAJE_CREACION_GRUPO_ETNICO_PROY_INV_POBLACION_NO_EXISTE = "mensaje_creacion_grupo_etnico_proy_inv_poblacion_no_existe";
	public static final String MENSAJE_CREACION_GRUPO_ETNICO_PROY_INV_POBLACION_NUMERO_NO_VALIDO = "mensaje_creacion_grupo_etnico_proy_inv_poblacion_numero_no_valido";
	public static final String MENSAJE_AGREGAR_BP_PROY_INV_ETNICO_EXITOSO = "mensaje_agregar_bp_proy_inv_etnico_exitoso";
	public static final String MENSAJE_AGREGAR_BP_PROY_INV_ETNICO_FALLIDO = "mensaje_agregar_bp_proy_inv_etnico_fallido";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_NO_EXISTE = "mensaje_modificar_bp_proyecto_inversion_etnico_no_existe";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_BP_PROY_INV_POBLACION_NO_EXISTE = "mensaje_modificar_bp_proyecto_inversion_etnico_bp_proy_inv_poblacion_no_existe";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_NUMERO_NO_VALIDO = "mensaje_modificar_bp_proyecto_inversion_etnico_numero_no_valido";
	public static final String MENSAJE_MODIFICAR_BP_PROYECTO_INVERSION_ETNICO_EXITOSO = "mensaje_modificar_bp_proyecto_inversion_etnico_exitoso";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_ETNICO_NO_EXISTENTE = "mensaje_eliminar_bp_proyecto_inversion_etnico_no_existente";
	public static final String MENSAJE_ELIMINAR_BP_PROYECTO_INVERSION_ETNICO_EXITOSO = "mensaje_eliminar_bp_proyecto_inversion_etnico_exitoso";
	
	
	

	/**
	 * Mensaje de validacion PotInstrumento
	 */
	public static final String MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_INSTRUMENTO_POT_VACIO = "mensaje_validacion_pot_instrumento_id_instrumento_pot_vacio";
	public static final String MENSAJE_VALIDACION_POT_INSTRUMENTO_CODIGO_VACIO = "mensaje_validacion_pot_instrumento_codigo_vacio";
	public static final String MENSAJE_VALIDACION_POT_INSTRUMENTO_DENOMINACION_VACIO = "mensaje_validacion_pot_instrumento_denominacion_vacio";
	public static final String MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_POT_VACIO = "mensaje_validacion_pot_instrumento_id_pot_vacio";
	public static final String MENSAJE_VALIDACION_POT_INSTRUMENTO_ID_LS_NIVEL_INST_VACIO = "mensaje_validacion_pot_instrumento_id_ls_nivel_inst_vacio";

	/**
	 * 
	 * Mensaje de validacion de BbAporteCiudadano
	 */
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_APORTE_VACIO = "mensaje_validacion_bp_aporte_ciudadano_id_aporte_vacio";
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_CONSECUTIVO_VACIO = "mensaje_validacion_bp_aporte_ciudadano_consecutivo_vacio";
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ACCION_VACIO = "mensaje_validacion_bp_aporte_ciudadano_accion_vacio";
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_FUENTE_VACIO = "mensaje_validacion_bp_aporte_ciudadano_fuente_vacio";
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_NIVEL_PDD_VACIO = "mensaje_validacion_bp_aporte_ciudadano_id_nivel_pdd_vacio";
	public static final String MENSAJE_VALIDACION_BP_APORTE_CIUDADANO_ID_ARCHIVO_VACIO = "mensaje_validacion_bp_aporte_ciudadano_id_archivo_vacio";

	/*
	 * VALIDACION CARGA DE ARCHIVOS
	 */
	public static final String MENSAJE_VALIDACION_NO_COINCIDE_LONGITUD_CAMPO = "mensaje_validacion_no_coincide_longitud_campo";
	public static final String MENSAJE_VALIDACION_NO_COINCIDE_EL_TIPO_DE_DATO = "mensaje_validacion_no_coincide_el_tipo_de_dato";
	public static final String MENSAJE_VALIDACION_NO_COINCIDE_EL_NUMERO_DE_CAMPOS = "mensaje_validacion_no_coincide_el_numero_de_campos";
	public static final String MENSAJE_VALIDACION_CAMPO_VACIO = "mensaje_validacion_campo_vacio";
	public static final String MENSAJE_ERRORES_CAMPO_FILA = "mensaje_errores_campo_fila";
	public static final String MENSAJE_TODOS_LOS_CAMPOS = "mensaje_todos_los_campos";

	/**
	 * Validacion PbrPoblacion
	 */

	public static final String MENSAJE_VALIDACION_PDD_PBR_ID_POBLACION = "mensaje_validacion_pdd_pbr_id_poblacion";
	public static final String MENSAJE_VALIDACION_PDD_PBR_DESCRIPCION = "mensaje_validacion_pdd_pbr_descripcion";
	public static final String MENSAJE_VALIDACION_PDD_PBR_ID_PROBLEMATICA = "mensaje_validacion_pdd_pbr_id_problematica";
	public static final String TIPO_DATO_CHAR = "CHAR";
	public static final String TIPO_DATO_INTEGER = "INTEGER";
	public static final String TIPO_DATO_DOUBLE = "DECIMAL";

	public static final String DESCRIPCION_NA = "NA";
	public static final String CARACTER_CONCATENAR = "|";
	public static final String CARACTER_CONCATENAR_AMPERSAND = "&";
	public static final String CARACTER_SPLIT_COMA = ",";
	public static final String CARACTER_SPLIT_PUNTOYCOMA = ";";
	public static final String CARACTER_SPLIT_ESPACIO = " ";

	/**
	 * Constantes para identificar los nombres de los campos en el archivo y hacer
	 * match con los campos de la base de datos.
	 */
	public static final String INF_PRESPUESTAL_CASE_YEAR = "YEAR";
	public static final String INF_PRESPUESTAL_CODIGO_ENTIDAD = "A.CODIGO_ENTIDAD";
	public static final String INF_PRESPUESTAL_CASE_UNIDAD_EJECUTORA = "B.UNIDAD_EJECUTORA";
	public static final String INF_PRESPUESTAL_CASE_CODIGO_PROYECTO = "CODIGO_PROYECTO";
	public static final String INF_PRESPUESTAL_CASE_CODIGO_INTERNO = "CODIGO_INTERNO";
	public static final String INF_PRESPUESTAL_CASE_MES = "MES";
	public static final String INF_PRESPUESTAL_CASE_APROPIACION_DEFINITIVA = "APROPIACION_DEFINITIVA";
	public static final String INF_PRESPUESTAL_CASE_RECURSOS_SUSPENDIDOS = "RECURSOS_SUSPENDIDOS";
	public static final String INF_PRESPUESTAL_CASE_EJECUCION_VIGENCIA = "EJECUCION_VIGENCIA";
	public static final String INF_PRESPUESTAL_CASE_GIROS_VIGENCIA = "GIROS_VIGENCIA";
	public static final String INF_PRESPUESTAL_CASE_APROPIACION_RESERVA = "APROPIACION_RESERVA";
	public static final String INF_PRESPUESTAL_CASE_EJECUCION_GIRO_RESERVA = "EJECUCION_GIRO_RESERVA";
	public static final String INF_PRESPUESTAL_CASE_CODIGO_CLASIF_PRESUPUESTAL = "CODIGO_CLASIF_PRESUPUESTAL";
	public static final String INF_PRESPUESTAL_CASE_ID_PLAN_DESARROLLO = "ID_PLAN_DESARROLLO";

	/**
	 * 
	 */
	public static final int ESTADO_ACTIVO = 1;
	public static final int ESTADO_INACTIVO = 0;
	public static final int ESTADO_MENSAJE_LEIDO = 1;
	public static final int ESTADO_MENSAJE_NO_LEIDO = 0;

	public static final String VERSION_INICIAL = "0";

	/**
	 * Nombre de Listas Simples
	 */
	public static final String LS_ESTADOS_PDD = "Estados PDD";
	public static final String LS_ESTADOS_PDL = "Estados PDL";
	public static final String LS_ESTADOS_PROYECTO_INVERSION = "EstadosProyectoInversion";
	public static final String LS_NIVELES_PDD = "Niveles PDD";
	public static final String LS_CLASIFICACION_ENTIDADES = "Clasificacion";

	/**
	 * Lista de estados de Pdd
	 */
	public static final String PDD_ESTADO_FORMULACION = "Formulacion";
	public static final String PDD_ESTADO_VIGENTE = "Vigente";
	public static final String PDD_ESTADO_FINALIZADO = "Finalizado";
	
	/**
	 * Lista de estados de Pdl
	 */
	public static final String PDL_ESTADO_VIGENTE = "Vigente";
	public static final String PDL_ESTADO_FINALIZADO = "Finalizado";
	
	/**
	 * Lista de estados de POT
	 */
	public static final String POT_ESTADO_VIGENTE = "Vigente";
	public static final String POT_ESTADO_FINALIZADO = "Finalizado";

	/**
	 * Lista de estado de iniciativa ciudadana
	 */
	public static final String PDD_ESTADO_VIABLE = "Viable";
	public static final String PDD_ESTADO_NO_VIABLE = "No Viable";

	/**
	 * Lista de estados de proyecto de inversion
	 */
	public static final String BP_PROYECTO_INV_ESTADO_SIN_INSCRIPCION = "Sin inscripcion";
	public static final String BP_PROYECTO_INV_ESTADO_ELIMINADO = "Eliminado";

	/**
	 * Numero Niveles de Pdd
	 */
	public static final String PDD_NUMERO_NIVELES_2 = "2";

	/**
	 * Lista de clasificacion de entidades
	 */
	public static final String ENTIDAD_ALCALDIA = "Alcaldía";
	public static final String ENTIDAD_EIC = "EIC";

	/**
	 * 
	 */
	public static final String ESTADO_STRING_ACTIVO = "Activo";
	public static final String ESTADO_STRING_INACTIVO = "Inactivo";

	/**
	 * Momentos PddPrbValoracion
	 */
	public static final String MOMENTO_STRING_ANTES = "Antes";
	public static final String MOMENTO_STRING_DURANTE = "Durante";
	public static final String MOMENTO_STRING_DESPUES = "Despues";
	
	/**
	 * Constantes
	 */
	public static final String STRING_DE_ENTIDAD_NO_APLICA = "Entidad No Aplica";
	public static final String CODIGO_ENTIDAD_DE_ENTIDAD_NO_APLICA = "0";

	/**
	 * 
	 */
	public static final String TIPO_PD = "PD";
	public static final String TIPO_PI = "PI";

	/**
	 * Respuestas HTTP
	 */
	public static final int RESPUESTA_EXITOSA = 200;
	public static final int RESPUESTA_ERROR_EXTERNO = 400;
	public static final int RESPUESTA_TIEMPO_EXCEDIDO = 408;
	public static final int RESPUESTA_EXCEPCION = 500;

	/**
	 * Constantes email.
	 */
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/*
	 * Mensajes expcepciones
	 */
	public static final String MENSAJE_EXCEPCION_GENERICA = "mensaje_excepcion_generica";
	public static final String MENSAJE_EXCEPCION_GENERICA_IO_FILE = "mensaje_excepcion_generica_io_file";
	public static final String MENSAJE_EXCEPCION_GENERICA_SQL = "mensaje_excepcion_generica_sql";
	/*
	 * Mensajes Seguridad api
	 */

	public static final int SEGURIDADAPI_CODIGO_OK = 0;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_CONTRASENIA_EN_BLANCO = -1;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_CONTRASENIA_INVALIDAS = -2;
	public static final int SEGURIDADAPI_CODIGO_TIPO_USUARIO_INVALIDO = -3;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_ERROR_NO_EXISTE = -4;
	public static final int SEGURIDADAPI_CODIGO_LDAP_ERROR = -5;
	public static final int SEGURIDADAPI_CODIGO_ERROR_SERVICIO_NO_SE_ENCUENTRA = -6;
	public static final int SEGURIDADAPI_CODIGO_TOKEN_INVALIDO = -7;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_USUARIO_INHABILITADO = -8;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_CAMBIAR_CONTRASENIA = -9;
	public static final int SEGURIDADAPI_CODIGO_ROL_NO_EXISTE = -10;
	public static final int SEGURIDADAPI_CODIGO_USUARIO_NO_TIENE_PERMISO_APLICACION = -11;
	public static final int SEGURIDADAPI_CODIGO_ERROR = -99;
	public static final int SEGURIDADAPI_CODIGO_ADVERTENCIA = 2;

	public static final String MENSAJE_SEGURIDADAPI_OK = "mensaje_seguridadapi_ok";
	public static final String MENSAJE_SEGURIDADAPI_ADVERTENCIA = "mensaje_seguridadapi_advertencia";
	public static final String MENSAJE_SEGURIDADAPI_ERROR = "mensaje_seguridadapi_error";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_ERROR_EXISTE = "mensaje_seguridadapi_usuario_error_existe";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_ERROR_NO_EXISTE = "mensaje_seguridadapi_usuario_error_no_existe";
	public static final String MENSAJE_SEGURIDADAPI_TOKEN_INVALIDO = "mensaje_seguridadapi_token_invalido";
	public static final String MENSAJE_SEGURIDADAPI_TOKEN_VALIDO = "mensaje_seguridadapi_token_valido";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_CONTRASENIA_EN_BLANCO = "mensaje_seguridadapi_usuario_contrasenia_en_blanco";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_CONTRASENIA_INVALIDAS = "mensaje_seguridadapi_usuario_contrasenia_invalidas";
	public static final String MENSAJE_SEGURIDADAPI_ERROR_SERVICIO_NO_SE_ENCUENTRA = "mensaje_seguridadapi_error_servicio_no_se_encuentra";
	public static final String MENSAJE_SEGURIDADAPI_TIPO_USUARIO_INVALIDO = "mensaje_seguridadapi_tipo_usuario_invalido";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_USUARIO_INHABILITADO = "mensaje_seguridadapi_usuario_usuario_inhabilitado";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_CAMBIAR_CONTRASENIA = "mensaje_seguridadapi_usuario_cambiar_contrasenia";
	public static final String MENSAJE_SEGURIDADAPI_USUARIO_NO_TIENE_PERMISO_APLICACION = "mensaje_seguridadapi_usuario_no_tiene_permiso_aplicacion";
	public static final String MENSAJE_SEGURIDADAPI_ROL_NO_EXISTE = "mensaje_seguridadapi_rol_no_existe";
	public static final String MENSAJE_SEGURIDADAPI_LDAP_ERROR = "mensaje_seguridadapi_ldap_error";
	public static final String MENSAJE_SEGURIDADAPI_MENSAJE_CRITERIOS_CONTRASENA = "mensaje_seguridadapi_mensaje_criterios_contrasena";
	public static final String MENSAJE_SEGURIDADAPI_TIPO_USUARIO_INVALIDA = "mensaje_seguridadapi_tipo_usuario_invalida";
	public static final String MENSAJE_REGISTRO_REPETIDO = "mensaje_de_registro_repetido";
	public static final String MENSAJE_ROLES_VACIO = "mensaje_roles_vacio";

	/**
	 * Mensajes IP
	 */
	public static final String MENSAJE_OBTENER_OBRAS_CONCRETAS_POR_METAS_CORRECTO = "mensaje_obtener_obras_concretas_por_metas";
	public static final String MENSAJE_OBTENER_OBRAS_CONCRETAS_POR_METAS_INCORRECTO = "mensaje_obtener_obras_concretas_incorrecto";
	public static final String MENSAJE_ERROR_SERVICIO_INTERNO = "mensaje_error_servicio_interno";
	public static final String MENSAJE_ELIMINAR_PDD_META_CORRECTO = "mensaje_eliminar_pdd_meta";
	public static final String MENSAJE_ELIMINAR_OBRA_CONCRETA_CORRECTO = "mensaje_eliminar_obra_concreta_correcto";
	public static final String MENSAJE_CREAR_PDD_META_CORRECTO = "mensaje_crear_pdd_meta_correcto";
	public static final String MENSAJE_CREAR_PDD_META_INCORRECTO = "mensaje_crear_pdd_meta_incorrecto";
	public static final String MENSAJE_CREAR_PDD_OBRA_CONCRETA_CORRECTO = "mensaje_crear_pdd_obra_concreta_correcto";
	public static final String MENSAJE_CREAR_PDD_OBRA_CONCRETA_INCORRECTA = "mensaje_crear_pdd_obra_concreta_incorrecta";
	public static final String MENSAJE_MODIFICAR_PDD_OBRA_CONCRETA_CORRECTO = "mensaje_modificar_pdd_obra_concreta_correcta";
	public static final String MENSAJE_MODIFICAR_PDD_META_CORRECTO = "mensaje_modificar_pdd_meta_correcto";
	public static final String MENSAJE_MODIFICAR_PDD_META_INCORRECTO = "mensaje_modificar_pdd_meta_incorrecto";
	public static final String MENSAJE_MODIFICAR_PDD_OBRA_CONCRETA_IN69CORRECTO = "mensaje_modificar_pdd_obra_concreta_incorrecta";
	public static final String MENSAJE_ELIMINAR_PDD_META_INCORRECTO = "mensaje_eliminar_pdd_meta_incorrecto";
	public static final String MENSAJE_ELIMINAR_OBRA_CONCRETA_INCORRECTO = "mensaje_eliminar_obra_concreta_incorrecto";
	public static final String MENSAJE_FATAL_JSON = "mensaje_fatal_json_error";
	public static final String MENSAJE_REGISTRAR_PDD_PRB_INDICADOR_CORRECTO = "mensaje_pdd_prb_indicador_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_PRB_INDICADOR_INCORRECTO = "mensaje_pdd_prb_indicador_incorrecto";
	public static final String MENSAJE_REGISTRO_REPETIDO_PDD_PRB_INDICADOR = "mensaje_registro_repetido_pdd_prb_indicador";
	public static final String MENSAJE_MODIFICAR_PDD_PRB_INDICADOR_CORRECTO = "mensaje_modificar_pdd_prb_indicador_correcto";
	public static final String MENSAJE_ELMINAR_PDD_PRB_INDICADOR_CORRECTO = "mensaje_eliminar_pdd_prb_indicador_correcto";
	public static final String MENSAJE_OBTENENER_TODOS_PRB_INDICADOR_CORRECTO = "mensaje_obtener_todos_prb_indicador_correcto";
	public static final String MENSAJE_OBTENENER_TODOS_PRB_INDICADOR_INCORRECTO = "mensaje_obtener_todos_prb_indicador_incorrecto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_INDICADOR_CORRECTO = "mensaje_obtener_todos_pdd_indicador_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_INDICADOR_INCORRECTO = "mensaje_obtener_todos_pdd_indicador_incorrecto";

	// Llaves de mapa de ArbolDTO

	public static final String MENSAJE_OBTENER_TODOS_MP_ANUALIZAR_CORRECTO = "mensaje_obtener_todos_mp_anualizar_correcto";
	public static final String MENSAJE_OBTENER_TODOS_META_RESULTADO_CORRECTO = "mensaje_obtener_todos_meta_producto_correcto";
	public static final String MENSAJE_OBTENER_TODOS_MP_INDICADOR_CORRECTO = "mensaje_obtener_todos_mp_indicador_correcto";

	public static final String MAPA_LLAVE_COMPROMISO = "compromiso";
	public static final String MAPA_LLAVE_META = "meta";
	public static final String MAPA_LLAVE_OBRA = "obra";
	// public static final String MAPA_LLAVE_PDD_NIVEL = "pddNivel"
	public static final String MENSAJE_OBTENER_TODOS__PROY_INV_FINANCIA_POR_ID_PROYECTO = "mensaje_obtener_todos_proy_inversion_fiannciera_identificador_proyecto";
	public static final String MENSAJE_OBTENER_TODOS_PRESENTACION_INICIATIVA_CIUDADANA = "mensaje_obtener_todos_presentacion_iniciativa_ciudadana";
	public static final String MENSAJE_OBTENER_PDD_META_PRODUCTO_INDICADOR_CORRECTO = "mensaje_obtener_pdd_meta_producto_indicador_correcto";
	public static final String MENSAJE_CREAR_PDDMP_INDICADOR_CORRECTO = "mensaje_crear_pddmp_indicador_correcto";
	public static final String MENSAJE_CREAR_PDDMP_INDICADOR_INCORRECTO = "mensaje_crear_pddmp_indicador_incorrecto";
	public static final String MENSAJE_ELIMINAR_PDD_INDICADOR_META_PRODUCTO_CORRECTO = "mensaje_eliminar_pdd_indicador_meta_producto_correcto";
	public static final String MENSAJE_OBTENER_TODOS_PDD_MP_ENTIDADES_CORRECTO = "mensaje_obtener_todos_pdd_mp_entidades_correcto";
	public static final String MENSAJE_ELIMINAR_MP_ENTIDAD_CORRECTO = "mensaje_eliminar_mp_entidad_correcto";
	public static final String MENSAJE_REGISTRAR_PDD_MP_ENTIDAD_CORRECTO = "mensaje_registrar_pdd_mp_entidad_correcto";
	public static final String CORE_CONTROLLER_IP_POT_OBTENER_TODOS = "/ippot/obtener_todos_pot";
	public static final String CORE_CONTROLLER_IP_POT_AGREGAR_POT = "/ippot/registrar_pot";
	public static final String CORE_CONTROLLER_IP_POT_MODIFICAR_POT_NIVEL = "/ippot/modificar_pot_nivel";
	public static final String CORE_CONTROLLER_IP_POT_OBTENER_POT_NIVEL = "/ippot/obtener_niveles_pot";
	public static final String CORE_CONTROLLER_IP_POT_MODIFICAR_ESTADO_POT_NIVEL = "/ippot/modificar_niveles/{idnivel}/{cerrado}";
	public static final String CORE_CONTROLLER_IP_POT_MODIFICAR_ESTADO_RAMA = "/ippot/modificar_rama/{idrama}/{cerrado}";
	public static final String MENSAJE_MODIFICAR_POT_RAMA_CORRECTO = "mensaje_modificar_pot_correcto";
	public static final String MENSAJE_REGISTRAR_MP_INDICADOR_ENTIDAD_CORRECTO = "mensaje_registrar_mp_indicador_entidad_correcto";
	public static final String MENSAJE_MODIFICAR_MP_INDICADOR_ENTIDAD_CORRECTO = "mensaje_modificar_mp_indicador_entidad_correcto";
	public static final String MENSAJE_REGISTRAR_BP_INCIATIVA_CIUDADANA_CORRECTO = "mensaje_registrar_bp_iniciativa_ciudadana_correcto";
	public static final String MENSAJE_ELIMINAR_INICIATIVA_CIUDADANA_CORRECTO = "mensaje_eliminar_iniciativa_ciudadana_correcto";
	public static final String MENSAJE_MODIFICAR_BP_INCIATIVA_CIUDADANA_CORRECTO = "mensaje_modificar_bp_iniciativa_ciudadana_correcto";
	public static final String MENSAJE_OBTENER_PDD_VIGENTE = "mensaje_obtener_pdd_vigente";
	public static final String MENSAJE_PDD_VIGENTE_INEXISTENTE = "mensaje_pdd_vigente_inexistente";
	public static final String MENSAJE_MODIFICAR_FUNCIONARIO_CLAVE_ENTIDAD_CORRECTO = "mensaje_modificar_funcionario_clave_entidad";
	public static final String MENSAJE_REGISTRO_REPETIDO_FUNCIONARIO_CLAVE_ENTIDAD = "mensaje_registro_repetido_funcionario_clave_entidad";
	public static final String CORE_CONTROLLER_IPPLANDISTRITAL_OBTENER_TODOS_INDICADORES_META_PRODUCTO = "/ipplandistrital/obtener_ind_meta_productos";

	public static final String CORE_CONTROLLER_BP_MODIFICAR_FUENTE_FINANCIACION = "/bpproyinv/modificar_fuente_financiacion";
	public static final String CORE_CONTROLLER_BP_OBTENER_TODAS_FUENTES_FINANCIACION_PYOYECT = "/bpproyinv/obtener_todas_funetes_financia_proyect";
	public static final String CORE_CONTROLLER_BP_REGISTRAR_FUENTE_FINANCIACION_PYOYECT= "/bpproyinv/registrar_proy_inv_financia";

	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_BP_PROY_INV_FINANCIA = "/bpproyinv/eliminar_bp_proy_inv_financia";
	public static final String MENSAJE_FUENTE_FINANCIACION_NO_ENCONTRADA = "mensaje_fuente_financiacion_no_encontrada";
	public static final String MENSAJE_FUENTE_FINANCIACION_ELIMINADA = "mensaje_fuente_financiacion_eliminada";
	public static final String MENSAJE_MODIFICAR_BP_PROY_INV_ANUALIZA = "mensaje_modificar_bp_proy_inv_anualiza";
	public static final String MENSAJE_MODIFICAR_ERROR_BP_PROY_INV_ANUALIZA = "mensaje_modificar_error_bp_proy_inv_anualiza";
	public static final String MENSAJE_MODIFICAR_CORRECTO_BP_PROY_INV_ANUALIZA = "mensaje_modificar_correcto_bp_proy_inv_anualiza";


	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_POLITICA_FILTRADO = "/bpproyinv/obtener_todos_bp_proy_inv_politica_filtrado";
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PDD_POLITICA_PUBLICA_FILTRADO = "/bpproyinv/obtener_todos_pdd_politica_publica_filtrado";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION = "/bpproyinv/registrar_relacion_pddpoliticapublic_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION = "/bpproyinv/modificar_relacion_pddpoliticapublic_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_PDDPOLITICAPUBLICA_CON_BPPROYECTOINVERSION = "/bpproyinv/eliminar_relacion_pddpoliticapublic_con_bpproyectoInversion/{id}";
	
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_BP_PROY_INV_LINEA = "/bpproyinv/obtener_todos_bp_proy_inv_linea/{id}";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION = "/bpproyinv/registrar_relacion_lineainversion_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION = "/bpproyinv/modificar_relacion_lineainversion_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_LINEAINVERSION_CON_BPPROYECTOINVERSION = "/bpproyinv/eliminar_relacion_lineainversion_con_bpproyectoinversion/{id}";
	
	public static final String CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION = "/bpproyinv/obtener_todos_bp_proy_inv_pmr";
	public static final String CORE_CONTROLLER_BPPROYINV_REGISTRAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION = "/bpproyinv/registrar_relacion_mrindicador_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_MODIFICAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION = "/bpproyinv/modificar_relacion_mrindicador_con_bpproyectoinversion";
	public static final String CORE_CONTROLLER_BPPROYINV_ELIMINAR_RELACION_MRINDICADOR_CON_BPPROYECTOINVERSION = "/bpproyinv/eliminar_relacion_mrindicador_con_bpproyectoinversion/{id}";

	
	public static final String 	CORE_CONTROLLER_BPPROYINV_OBTENER_TODOS_PDD_POLITICA_PUBLICA = "/bpproyinv/obtener_todos_pdd_politica_publica_sin_relacion_con_proyecto_inversion/{id}";


	
	public static final String MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION = "mensaje_obtener_todos_proyectos_inversion_poblacion_asociados_a_proyecto_inversion";
	public static final String MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION_LISTA_VACIA = "mensaje_obtener_todos_proyectos_inversion_poblacion_asociados_a_proyecto_inversion_lista_vacia";
	public static final String MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION_LISTA_VACIA = "mensaje_obtener_todos_proyectos_inversion_etnico_asociados_a_proyecto_inversion_lista_vacia";
	public static final String MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION = "mensaje_obtener_todos_proyectos_inversion_etnico_asociados_a_proyecto_inversion";
	
	public static final String CORE_CONTROLLER_ADMINISTRACION_VALIDAR_Y_REGISTRAR_ENTIDAD = "/administracion/validaryregistrarEntidad/{codigo}";

	public static final String MENSAJE_OBTENER_TODOS_CORRECTAMENTE = "mensaje_obtener_todos_correctamente";


}
