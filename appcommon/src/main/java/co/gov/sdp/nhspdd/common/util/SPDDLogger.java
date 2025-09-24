/**
 * 
 */
package co.gov.sdp.nhspdd.common.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Sumset
 *
 */

@Component
public class SPDDLogger implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final SPDDLogger SPDD_LOGGER = new SPDDLogger();

	/**
	 * 
	 */
	public SPDDLogger() {
		super();
	}

	/**
	 * 
	 * @param mensaje
	 * @param severidad
	 * @param nombreClase
	 */
	public void mensajeLogger(String mensaje, String severidad, Class<?> nombreClase) {
		Logger logger = LogManager.getLogger(nombreClase);

		switch (severidad) {
		case NHSPDDConstantes.SEVERIDAD_DEBUG:
			logger.debug(mensaje);
			break;
		case NHSPDDConstantes.SEVERIDAD_ERROR:
			logger.error(mensaje);
			break;
		case NHSPDDConstantes.SEVERIDAD_FATAL:
			logger.fatal(mensaje);
			break;
		case NHSPDDConstantes.SEVERIDAD_INFO:
			logger.info(mensaje);
			break;
		case NHSPDDConstantes.SEVERIDAD_TRACE:
			logger.trace(mensaje);
			break;
		case NHSPDDConstantes.SEVERIDAD_WARN:
			logger.warn(mensaje);
			break;
		default:
			logger.debug(mensaje);
			break;
		}
	}
}
