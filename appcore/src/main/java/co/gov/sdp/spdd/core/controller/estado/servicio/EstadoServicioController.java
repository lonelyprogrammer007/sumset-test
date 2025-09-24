package co.gov.sdp.spdd.core.controller.estado.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.EstadoServicioDTO;
import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.nhspdd.common.util.NHSPDDMensajes;
import co.gov.sdp.nhspdd.common.util.PaqueteMensajeEnum;
import co.gov.sdp.nhspdd.common.util.SPDDLogger;
import co.gov.sdp.spdd.core.icontroller.estado.servicio.IEstadoServicioController;
import co.gov.sdp.spdd.core.iservice.estado.servicio.IEstadoServicioConsultar;


@RestController
public class EstadoServicioController implements IEstadoServicioController {

    @Autowired
    IEstadoServicioConsultar consultar;
    
    /**
	 * Logger
	 */
	@Autowired
	SPDDLogger spddLogger;

    /**
     * Implementacion del metodo obtenerTodos
     *
     * @see
     */
    @PostMapping(NHSPDDConstantes.CORE_CONTROLLER_ESTADO_DE_SERVICIO_OBTENER_TODOS)
    @Override
    public GenericoDTO obtenerTodos(@RequestBody EstadoServicioDTO peticion) {
    	GenericoDTO respuesta = new GenericoDTO();
        try {
			respuesta =  consultar.obtenerTodos(peticion);
		} catch (SpddExceptions e) {
			respuesta.setCodigoRespuesta(NHSPDDConstantes.RESPUESTA_ERROR_EXTERNO);
			respuesta.setMsgRespuesta(NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_ERROR_VALIDACION,
					PaqueteMensajeEnum.ERRORES, null));
			spddLogger.mensajeLogger(
					NHSPDDMensajes.obtenerMensaje(NHSPDDConstantes.MENSAJE_EXCEPCION_GENERICA, respuesta.getLenguaje()),
					NHSPDDConstantes.SEVERIDAD_FATAL, EstadoServicioController.class);
		}
        return respuesta;
    }
}
