package co.gov.sdp.spdd.core.service.buzon.notificacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.BuzonNotificacionesDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.spdd.core.iservice.buzon.notificacion.IBuzonNotificacionModificar;
import co.gov.sdp.spdd.data.dao.interfaces.afs.IBuzonNotificacioneServiceRepo;
import co.gov.sdp.spdd.data.mapper.BuzonNotificacionesMapper;
import co.gov.sdp.spdd.data.model.afs.BuzonNotificaciones;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que implementa los metodos de modificar
 *
 * @author Bryan Munoz
 *
 */
@Service
public class BuzonNotificacionModificar implements IBuzonNotificacionModificar {

	// Servicio de base de datos
	@Autowired
	IBuzonNotificacioneServiceRepo buzonNotificacionServiceRepo;

	// Servicio que convierte un dto a entidad y viceversa
	@Autowired
	BuzonNotificacionesMapper mapper;

	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;

	/**
	 * Implementacion de darRespuesta
	 */
	@Override
	public BuzonNotificacionesDTO darRespuesta(BuzonNotificacionesDTO peticion) throws SpddExceptions {
		BuzonNotificaciones entidad = buzonNotificacionServiceRepo.obtener(peticion.getIdNotificacion());
		BuzonNotificacionesDTO respuesta = new BuzonNotificacionesDTO();
		if (entidad != null && entidad.getEstado() == 0) {
			entidad.setEstado((short) 1);
			Date fechaActual = new Date();
			entidad.setFechaLectura(fechaActual);
			entidad.setRespuesta(peticion.getRespuesta());
			buzonNotificacionServiceRepo.guardar(entidad);
			respuesta = mapper.buzonNotificacionesEntityToDTO(entidad);
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);
			respuesta.setMsgRespuesta(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_MODIFICAR_ESTADO_BUZON_NOTIFICACION,
							PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));

		} else {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_TIEMPO_EXCEDIDO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_NO_SE_ENCUENTRA_LA_ENTIDAD,
					PaqueteMensajeEnum.MENSAJES, peticion.getLenguaje()));
		}

		return respuesta;
	}

	@Override
	public BuzonNotificacionesDTO leerInformativos(String usuario) throws SpddExceptions {
		BuzonNotificacionesDTO respuesta = new BuzonNotificacionesDTO();
		List<BuzonNotificacionesDTO> lista = sessionFacadeAFS.leerInformativos(usuario);
		if (!lista.isEmpty()) {
			for (BuzonNotificacionesDTO buzon : lista) {
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				buzon.setFechaLectura((date.format(new Date())));
				buzon.setFechaRespuesta((date.format(new Date())));
				buzon.setEstado(1);
				sessionFacadeAFS.guardarBuzonNotificaciones(buzon);
			}

		}
		respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_EXITOSA);

		return respuesta;
	}

}
