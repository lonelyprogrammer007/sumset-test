package co.gov.sdp.spdd.core.bp.service.bpproyinv;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.sdp.nhspdd.common.dto.ArgumentoListaSimpleDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.dto.HistorialSectorialDTO;
import co.gov.sdp.nhspdd.common.dto.PddNivelAtributoDTO;
import co.gov.sdp.nhspdd.common.dto.RespuestaApiDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpAporteCiudadanoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAnualizaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvAporteDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvFinanciaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvEtnicoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvLineaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPmrDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoblacionDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvPoliticaDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyInvTipoDTO;
import co.gov.sdp.nhspdd.common.dto.bp.BpProyectoInversionDTO;
import co.gov.sdp.nhspdd.common.dto.ip.PddPoliticaPublicaDTO;
import co.gov.sdp.nhspdd.common.dto.seguridad.EntidadSeguridadDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.bp.iservice.bpproyinv.IBPProyInvConsultarService;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeBP;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeIP;
import co.gov.sdp.spdd.util.MetodosRest;

@Service
public class BPProyInvConsultarService implements IBPProyInvConsultarService {

	/**
	 * Inyeccion del SessionFacade del modulo IP
	 */
	@Autowired
	ISessionFacadeBP sessionFacadeBP;
	
	@Autowired
	ISessionFacadeIP sessionFacadeIP;
	
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	@Autowired
	SPDDLogger spddLogger;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MetodosRest<RespuestaApiDTO<HistorialSectorialDTO>> historialSectorial;

	@Autowired
	HttpServletRequest request;

	@Override
	public GenericoDTO consultarBpProyectoInversionPorFiltro(BpProyectoInversionDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarBpProyectoInversionPorFiltro(peticion);
		setSectorYTipo(respuesta);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_PROYECTO_INVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarProyectoInversionTodos(BpProyectoInversionDTO peticion) throws SpddExceptions {

		GenericoDTO respuesta = sessionFacadeBP.consultarBpProyectoInversionTodos();
		setSectorABpProyectoInversion(respuesta);
		
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_PROYECTO_INVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	@Override
	public GenericoDTO consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpAporteCiudadanoSinRelacionConProyectoInversion(idProyecto);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_APORTE_CIUDADANO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	
	@Override
	public BpProyectoInversionDTO consultarBpProyectoInversionPorId(Long idProyecto) throws SpddExceptions {
		BpProyectoInversionDTO respuesta = sessionFacadeBP.consultarProyInvPorId(idProyecto);
		
		ArgumentoListaSimpleDTO argumentoEntidadClasificacionAlcaldia = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.ENTIDAD_ALCALDIA,NHSPDDConstantes.LS_CLASIFICACION_ENTIDADES);
		String token = request.getHeader("Authorization");
		historialSectorial.agregarToken(token.substring(6, token.length()));
		RespuestaApiDTO<HistorialSectorialDTO> entidades = historialSectorial.get(
				NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/historial_sectorial/consultar_por_codigo_distrital/"
						+ respuesta.getCodigoEntidad(),
				new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
				});
		
		String sectorEntidad = entidades.getObjetos().get(0).getDescripcion();	
		String nombreEntidad = entidades.getObjetos().get(0).getCodigoEntidad();
		
		if(respuesta.getIdLsClasificacionEntidad().equals(argumentoEntidadClasificacionAlcaldia.getIdArgumento()))
		{
			respuesta.setStringSector(respuesta.getStringLsSectorAl());
		}
		else
		{				
			respuesta.setStringSector(sectorEntidad);				
		}
		
		respuesta.setStringSectorEntidad(sectorEntidad);
		respuesta.setNombreEntidad(nombreEntidad);
		
		// Concateno los tipos del proyecto
		String idstiposProyectos = getStringTiposProyectoInversion(respuesta.getIdProyInversion())[0];
		String stringstiposProyectos = getStringTiposProyectoInversion(respuesta.getIdProyInversion())[1];
		respuesta.setIdsTipoProy(idstiposProyectos);
		respuesta.setStringstipoProy(stringstiposProyectos);		
		
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_BP_PROYECTO_INVERSION_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	
	/**
	 * Metodo que permite setiar los sectores que estan en la entidad en la base de datos de seguridad a los proyectos
	 * de inversion.
	 * @param respuesta objeto de tipo genericoDTO que contiene la lista de proyectos de inversion
	 * @throws SpddExceptions 
	 */
	private void setSectorABpProyectoInversion(GenericoDTO respuesta) throws SpddExceptions
	{
		ArgumentoListaSimpleDTO argumentoEntidadClasificacionAlcaldia = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.ENTIDAD_ALCALDIA,NHSPDDConstantes.LS_CLASIFICACION_ENTIDADES);
		
		String token = request.getHeader("Authorization");
		historialSectorial.agregarToken(token.substring(6, token.length()));
		for (int i = 0; i < respuesta.getLstObjectsDTO().size(); i++) {
			
			RespuestaApiDTO<HistorialSectorialDTO> entidades = historialSectorial.get(
					NHSPDDConstantes.CONTEXTO_SEGURIDAD_API + "/api/historial_sectorial/consultar_por_codigo_distrital/"
							+ ((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).getCodigoEntidad(),
					new ParameterizedTypeReference<RespuestaApiDTO<HistorialSectorialDTO>>() {
					});
			
			String nombreEntidad = entidades.getObjetos().get(0).getCodigoEntidad();
			String sectorEntidad = entidades.getObjetos().get(0).getDescripcion();
			
			if(((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).getIdLsClasificacionEntidad().equals(argumentoEntidadClasificacionAlcaldia.getIdArgumento()))
			{
				((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setStringSector(((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).getStringLsSectorAl());
			}
			else
			{								
				((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setStringSector(sectorEntidad);				
			}
			
			((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setStringSectorEntidad(sectorEntidad);
			((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setNombreEntidad(nombreEntidad);
			
			// Concateno los tipos de cada uno de los proyectos
			String idstiposProyectos = getStringTiposProyectoInversion(((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).getIdProyInversion())[0];
			String stringstiposProyectos = getStringTiposProyectoInversion(((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).getIdProyInversion())[1];
			((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setIdsTipoProy(idstiposProyectos);
			((BpProyectoInversionDTO)respuesta.getLstObjectsDTO().get(i)).setStringstipoProy(stringstiposProyectos);
		}
		
	}

	/**
	 * Metodo que permite settiar los valores a los campos de sector y TipoProy de
	 * cada uno de los registros de ProyectoInversion
	 * 
	 * @param objeto objeto de tipo GenericoDTO que contiene toda la informacion de
	 *               los proyectos de inversion
	 * @throws SpddExceptions
	 */
	private void setSectorYTipo(GenericoDTO objeto) throws SpddExceptions {
		String tipos = "";
		String sectores = "";

		for (int i = 0; i < objeto.getLstObjectsDTO().size(); i++) {
			tipos = getStringTiposProyectoInversion(
					((BpProyectoInversionDTO) objeto.getLstObjectsDTO().get(i)).getIdProyInversion())[1];
			sectores = getStringSectoresProyectoInversion(
					((BpProyectoInversionDTO) objeto.getLstObjectsDTO().get(i)).getIdProyInversion());
			((BpProyectoInversionDTO) objeto.getLstObjectsDTO().get(i)).setStringstipoProy(tipos);
			((BpProyectoInversionDTO) objeto.getLstObjectsDTO().get(i)).setStringSector(sectores);
		}
	}

	/**
	 * Metodo que se encarga de obtener la cadena que representa la concatenacion de
	 * todos los tipos que tiene un proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto al cual
	 *                   se le van a buscar los tipos para ser concatenados
	 * @return un arreglo de String que representa la concatenacion de los tipos de un proyecto
	 *         de inversion, en la posicion 0 estan los ids concatenados y en la posicion 2 estan los strings
	 *         de los ids concatenados
	 * @throws SpddExceptions
	 */
	private String[] getStringTiposProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO tiposProyecto = sessionFacadeBP.consultarBpProyInvTipoPorIdProyectoInversion(idProyecto);
		BpProyInvTipoDTO tipoDTOTemp = null;
		StringBuilder retornoStringsTipos = new StringBuilder("");
		StringBuilder retornoIdsTipos = new StringBuilder("");

		String concatenador = "";
		String tipo = "";
		Long id;  
		if (tiposProyecto != null) {
			for (Object objectoTemp : tiposProyecto.getLstObjectsDTO()) {
				tipoDTOTemp = (BpProyInvTipoDTO) objectoTemp;
				tipo = tipoDTOTemp.getStringLsTipo();
				id = tipoDTOTemp.getIdLsTipo();
				if (!retornoStringsTipos.toString().contains(tipo)) {
					retornoStringsTipos.append(String.format("%s%s", concatenador, tipo));					
					
					retornoIdsTipos.append(String.format("%s%d", concatenador, id));
					
					concatenador = ";";
				}
			}
		}
		//String[] retorno = {retornoIdsTipos.toString() , retornoIdsTipos.toString()};
		return new String[]{retornoIdsTipos.toString() , retornoStringsTipos.toString()};
	}

	/**
	 * Metodo que se encarga de obtener la cadena que representa la concatenacion de
	 * todos los sectores que tiene un proyecto de inversion
	 * 
	 * @param idProyecto Long que representa el identificador del proyecto al cual
	 *                   se le van a buscar los tipos para ser concatenados
	 * @return un String que representa la concatenacion de los sectores de un
	 *         proyecto de inversion
	 * @throws SpddExceptions
	 */
	private String getStringSectoresProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO sectores = sessionFacadeBP.consultarTodosBpProyInvLineaPorIdProyectoInversion(idProyecto);
		BpProyInvLineaDTO sectorDTOTemp = null;
		StringBuilder retorno = new StringBuilder();
    	String concatenador = "";
    	String stringSector = "";
    	if(sectores != null)
    	{
    		for (Object objectTemp : sectores.getLstObjectsDTO()) {
    			sectorDTOTemp = (BpProyInvLineaDTO) objectTemp;
    			stringSector = sectorDTOTemp.getStringLsSectorLineaInversion();
    			if(!retorno.toString().contains(stringSector))
    			{
    				retorno.append(String.format("%s %s", concatenador,stringSector));
    				concatenador = ";";
    			}
        		
    		}
    	}
    	return retorno.toString();
	}

	
	@Override
	public GenericoDTO consultarTodosBpProyInvAportePorIdProyInversionPaginado(BpProyInvAporteDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpProyInvAportePorIdProyInversionPaginado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_PROY_INV_APORTE_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public BpAporteCiudadanoDTO consultarBpAporteCiudadanoPorId(Long idAporte) throws SpddExceptions {
		BpAporteCiudadanoDTO respuesta = sessionFacadeBP.consultarBpAporteCiudadanoPorId(idAporte);
		if(respuesta != null && respuesta.getIdAporte() > 0)
		{
			PddNivelAtributoDTO pddNivelAtributoAux3;
			PddNivelAtributoDTO pddNivelAtributoAux2;
			PddNivelAtributoDTO pddNivelAtributoAux1;
			if(respuesta.getNumeroPddNivel().equals(3L))
			{
				pddNivelAtributoAux3 = sessionFacadeIP.consultarPddNivelAtributoPorId(respuesta.getIdNivelAtributoPdd());
				respuesta.setIdNivelAtributoPddOpcion3(pddNivelAtributoAux3.getIdAtributo());
				respuesta.setStringNivelAtributoPddOpcion3(pddNivelAtributoAux3.getDenominacion());
				
				pddNivelAtributoAux2 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux3.getIdAtributoPadre());
				respuesta.setIdNivelAtributoPddOpcion2(pddNivelAtributoAux2.getIdAtributo());
				respuesta.setStringNivelAtributoPddOpcion2(pddNivelAtributoAux2.getDenominacion());
				
				pddNivelAtributoAux1 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux2.getIdAtributoPadre());
				respuesta.setIdNivelAtributoPddOpcion1(pddNivelAtributoAux1.getIdAtributo());
				respuesta.setStringNivelAtributoPddOpcion1(pddNivelAtributoAux1.getDenominacion());						
			}
			else if (respuesta.getNumeroPddNivel().equals(2L))
			{
				pddNivelAtributoAux2 = sessionFacadeIP.consultarPddNivelAtributoPorId(respuesta.getIdNivelAtributoPdd());
				respuesta.setIdNivelAtributoPddOpcion2(pddNivelAtributoAux2.getIdAtributo());
				respuesta.setStringNivelAtributoPddOpcion2(pddNivelAtributoAux2.getDenominacion());
				
				pddNivelAtributoAux1 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux2.getIdAtributoPadre());
				respuesta.setIdNivelAtributoPddOpcion1(pddNivelAtributoAux1.getIdAtributo());
				respuesta.setStringNivelAtributoPddOpcion1(pddNivelAtributoAux1.getDenominacion());
			}
			
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_BP_APORTE_CIUDADANO_CORRECTO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		else
		{
			respuesta = new BpAporteCiudadanoDTO();
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ENTIDAD_NO_ENCONTRADO,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		
		return respuesta;
	}

	@Override
	public GenericoDTO colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.colsultarTodosBpAporteCiudadanoCargadosPorArchivosConRelacionConProyectoInversion(idProyecto);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_APORTE_CIUDADANO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpAportesCiudadanosCargadosPorArchivos() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpAportesCiudadanosCargadosPorArchivos();
		
		if(!respuesta.getLstObjectsDTO().isEmpty())
		{
			TypeReference<List<BpAporteCiudadanoDTO>> type = new TypeReference<List<BpAporteCiudadanoDTO>>() {
			};
			List<BpAporteCiudadanoDTO> listaBpAportesCiudadanosDTO = objectMapper.convertValue(respuesta.getLstObjectsDTO(), type);
			
			for(BpAporteCiudadanoDTO aporteDTO : listaBpAportesCiudadanosDTO)
			{
				PddNivelAtributoDTO pddNivelAtributoAux3;
				PddNivelAtributoDTO pddNivelAtributoAux2;
				PddNivelAtributoDTO pddNivelAtributoAux1;
				if(aporteDTO.getNumeroPddNivel().equals(3L))
				{
					pddNivelAtributoAux3 = sessionFacadeIP.consultarPddNivelAtributoPorId(aporteDTO.getIdNivelAtributoPdd());
					aporteDTO.setIdNivelAtributoPddOpcion3(pddNivelAtributoAux3.getIdAtributo());
					aporteDTO.setStringNivelAtributoPddOpcion3(pddNivelAtributoAux3.getDenominacion());
					
					pddNivelAtributoAux2 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux3.getIdAtributoPadre());
					aporteDTO.setIdNivelAtributoPddOpcion2(pddNivelAtributoAux2.getIdAtributo());
					aporteDTO.setStringNivelAtributoPddOpcion2(pddNivelAtributoAux2.getDenominacion());
					
					pddNivelAtributoAux1 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux2.getIdAtributoPadre());
					aporteDTO.setIdNivelAtributoPddOpcion1(pddNivelAtributoAux1.getIdAtributo());
					aporteDTO.setStringNivelAtributoPddOpcion1(pddNivelAtributoAux1.getDenominacion());						
				}
				else if (aporteDTO.getNumeroPddNivel().equals(2L))
				{
					pddNivelAtributoAux2 = sessionFacadeIP.consultarPddNivelAtributoPorId(aporteDTO.getIdNivelAtributoPdd());
					aporteDTO.setIdNivelAtributoPddOpcion2(pddNivelAtributoAux2.getIdAtributo());
					aporteDTO.setStringNivelAtributoPddOpcion2(pddNivelAtributoAux2.getDenominacion());
					
					pddNivelAtributoAux1 = sessionFacadeIP.consultarPddNivelAtributoPorId(pddNivelAtributoAux2.getIdAtributoPadre());
					aporteDTO.setIdNivelAtributoPddOpcion1(pddNivelAtributoAux1.getIdAtributo());
					aporteDTO.setStringNivelAtributoPddOpcion1(pddNivelAtributoAux1.getDenominacion());
				}
									
			}
			respuesta.setLstObjectsDTO(new ArrayList<>(listaBpAportesCiudadanosDTO));
		}
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_BP_APORTE_CIUDADANO_CORRECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpIniciativaCiudadanaViablesFiltradas(	BpIniciativaCiudadanaDTO peticion) throws SpddExceptions {
		ArgumentoListaSimpleDTO argumetoEstadoViableIniciativa = sessionFacadeAFS.consultarArgumentoListaSimplePorResultadoYNombreListaSimple(NHSPDDConstantes.PDD_ESTADO_VIABLE, NHSPDDConstantes.LS_ESTADOS_PDD);
		if(argumetoEstadoViableIniciativa != null && argumetoEstadoViableIniciativa.getIdArgumento() != null)
		{
			peticion.setIdLsEstadoInicia(argumetoEstadoViableIniciativa.getIdArgumento());
		}
		
		GenericoDTO respuesta = sessionFacadeBP.filtrarIniciativaCiudadana(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PRESENTACION_INICIATIVA_CIUDADANA,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(Long idProyecto)	throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpIniciativaCiudadanaRelacionadasConProyectoInversion(idProyecto);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PRESENTACION_INICIATIVA_CIUDADANA,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarGruposEtarios(BpProyInvPoblacionDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarGruposEtarios(peticion);
		
		
		if(respuesta.getLstObjectsDTO().size() == 0) {
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION_LISTA_VACIA,
							PaqueteMensajeEnum.MENSAJES, null));
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
		}else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_POBLACION_ASOCIADOS_A_PROYECTO_INVERSION,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosProyInvEtnicoAsociadosAProyectoInversion(BpProyInvEtnicoDTO peticion)
			throws SpddExceptions {
		
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosProyInvEtnicoAsociadosAProyectoInversion(peticion);
		if(respuesta.getLstObjectsDTO().size() == 0) {
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION_LISTA_VACIA,
							PaqueteMensajeEnum.MENSAJES, null));
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
		}else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_PROYECTOS_INVERSION_ETNICO_ASOCIADOS_A_PROYECTO_INVERSION,
							PaqueteMensajeEnum.MENSAJES, null));
		}
		
		return respuesta;
	}

<<<<<<< HEAD
=======
	
	

	@Override
	public GenericoDTO consultarTodosProyInvFianciaPorIdProyInversionPaginado(BpProyInvFinanciaDTO peticion)
			throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosProyInvFianciaPorIdProyInversionPaginado(peticion);
		
		
		TypeReference<List<BpProyInvFinanciaDTO>> type = new TypeReference<List<BpProyInvFinanciaDTO>>() { };
		List<BpProyInvFinanciaDTO> lista = objectMapper.convertValue(respuesta.getLstObjectsDTO(), type);
       
		BpProyInvAnualizaDTO bpproyAnualizaDTO= new BpProyInvAnualizaDTO();
		
		TypeReference<List<BpProyInvAnualizaDTO>> type2 = new TypeReference<List<BpProyInvAnualizaDTO>>() { };
		
		for (BpProyInvFinanciaDTO prof : lista) {
			
			bpproyAnualizaDTO.setIdFuente(prof.getIdFuente());
			GenericoDTO respuesta2 = sessionFacadeBP.consultarTodosProyInvAnualizaPorIdFuentePaginado(bpproyAnualizaDTO);
			
			List<BpProyInvAnualizaDTO> lista2 = objectMapper.convertValue(respuesta2.getLstObjectsDTO(), type2);
				
				if(lista2.size()==1) {
					
					prof.setMontoAnio1(lista2.get(0).getMonto());
					prof.setVigencia1(lista2.get(0).getVigencia());
					prof.setMontoAnio2(0.00);
					prof.setMontoAnio3(0.00);
					prof.setMontoAnio4(0.00);
					prof.setMontoAnio5(0.00);
				}
				if(lista2.size()==2) {
					
					prof.setMontoAnio1(lista2.get(0).getMonto());
					prof.setVigencia1(lista2.get(0).getVigencia());
					prof.setMontoAnio2(lista2.get(1).getMonto());
					prof.setVigencia2(lista2.get(1).getVigencia());
					prof.setMontoAnio3(0.00);
					prof.setMontoAnio4(0.00);
					prof.setMontoAnio5(0.00);
				}
				if(lista2.size()==3) {
					
					prof.setMontoAnio1(lista2.get(0).getMonto());
					prof.setVigencia1(lista2.get(0).getVigencia());
					prof.setMontoAnio2(lista2.get(1).getMonto());
					prof.setVigencia2(lista2.get(1).getVigencia());
					prof.setMontoAnio3(lista2.get(2).getMonto());
					prof.setVigencia3(lista2.get(2).getVigencia());
					prof.setMontoAnio4(0.00);
					prof.setMontoAnio5(0.00);
				}
				if(lista2.size()==4) {
					
					prof.setMontoAnio1(lista2.get(0).getMonto());
					prof.setVigencia1(lista2.get(0).getVigencia());
					prof.setMontoAnio2(lista2.get(1).getMonto());
					prof.setVigencia2(lista2.get(1).getVigencia());
					prof.setMontoAnio3(lista2.get(2).getMonto());
					prof.setVigencia3(lista2.get(2).getVigencia());
					prof.setMontoAnio4(lista2.get(3).getMonto());
					prof.setVigencia4(lista2.get(3).getVigencia());
					prof.setMontoAnio5(0.00);
				}
				if(lista2.size()==5) {
					
					prof.setMontoAnio1(lista2.get(0).getMonto());
					prof.setVigencia1(lista2.get(0).getVigencia());
					prof.setMontoAnio2(lista2.get(1).getMonto());
					prof.setVigencia2(lista2.get(1).getVigencia());
					prof.setMontoAnio3(lista2.get(2).getMonto());
					prof.setVigencia3(lista2.get(2).getVigencia());
					prof.setMontoAnio4(lista2.get(3).getMonto());
					prof.setVigencia4(lista2.get(3).getVigencia());
					prof.setMontoAnio5(lista2.get(4).getMonto());
					prof.setVigencia5(lista2.get(4).getVigencia());
				}
				
			
		}
        
		respuesta.setLstObjectsDTO(new ArrayList<>(lista));
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS__PROY_INV_FINANCIA_POR_ID_PROYECTO,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvPoliticaFiltrado(BpProyInvPoliticaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpProyInvPoliticaFiltrado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaFiltrado(PddPoliticaPublicaDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddPoliticaPublicaFiltrado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(Long idProyInversion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddPoliticaPublicaSinRelacionConProyectoInversion(idProyInversion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvLineaPorIdProyectoInversion(Long idProyecto) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpProyInvLineaPorIdProyectoInversion(idProyecto);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosPddPoliticaPublica() throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeIP.consultarTodosPddPoliticaPublicaOrdenadosPorNombrePolitica();
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}

	@Override
	public GenericoDTO consultarTodosBpProyInvPmrDTOFiltrado(BpProyInvPmrDTO peticion) throws SpddExceptions {
		GenericoDTO respuesta = sessionFacadeBP.consultarTodosBpProyInvPmrDTOFiltrado(peticion);
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
		respuesta.setMsgRespuesta(
				NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_OBTENER_TODOS_CORRECTAMENTE,
						PaqueteMensajeEnum.MENSAJES, null));
		return respuesta;
	}
	
	
	

>>>>>>> developer
}
