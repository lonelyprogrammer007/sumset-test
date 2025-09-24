package co.gov.sdp.spdd.controller.handler;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;

//@ControllerAdvice
public class RequestHandlerController {
	
	@Autowired
	SPDDLogger spddLogger;

	@ResponseBody
	@ExceptionHandler(GenericJDBCException.class)
	public RespuestaDTO errorBaseDeDatos(GenericJDBCException ex) {
		RespuestaDTO respuesta = new RespuestaDTO();
		respuesta.setCodigoRespuesta(500);
		spddLogger.mensajeLogger(ex.getMessage(), NHSPDDConstantes.SEVERIDAD_ERROR, RequestHandlerController.class);
		respuesta.setMsgRespuesta("Compruebe que este ingresando los campos correctamente");
		return respuesta;

	}

}
