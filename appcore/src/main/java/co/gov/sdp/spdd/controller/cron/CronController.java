package co.gov.sdp.spdd.controller.cron;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

/**
 * Clase que se encarga de encapsular los metodos que necesitan de cron para su automatizacion
 * @author DANIEL
 * @version 1.0 03/04/2020
 */
@Configuration
@EnableScheduling
public class CronController {
	
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;
	
	/**
	 * Permite hacer una insercion en BD todos los dias a las 6:00 Am.
	 */
	@Scheduled(cron = "0 0 6 * * ?")	
	public void incersionEstadoServicio()
	{
		EstadoServicioDTO respuesta = new EstadoServicioDTO();
		try {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fechaActual = Calendar.getInstance().getTime();
		String fecha = formatter.format(fechaActual);
		EstadoServicioDTO estadoDTO = new EstadoServicioDTO(null, "1", fecha, fecha, "Servicio de Hacienda", "Consultar Informacion Presupuestal");
		
		sessionFacadeAFS.guardarEstadoServicio(estadoDTO);
		} catch (SpddExceptions e) {
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, CronController.class);
			
		}
	}
}
