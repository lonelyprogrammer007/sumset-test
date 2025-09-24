package co.gov.sdp.nhspdd.common.validador;

import java.util.List;

import co.gov.sdp.nhspdd.common.dto.CampoValidacionDTO;

/**
 * Clase auxiliar para manejar las validaciones
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 26/12/2016
 *
 */
public class NHSPDDResultadoValidacion {

    // Mensajes de los campos validados
    private List<CampoValidacionDTO> msgCampoValidacion;

    /**
     *
     * Costructor de la clase NHSPDDResultadoValidacion, asigna los campos de
     * validacion recibidos
     *
     * @param msgCampoValid campos para validar
     */
    public NHSPDDResultadoValidacion(List<CampoValidacionDTO> msgCampoValidacion) {
        super();
        this.msgCampoValidacion = msgCampoValidacion;
    }

    public List<CampoValidacionDTO> getMsgCampoValidacion() {
        return msgCampoValidacion;
    }

    public void setMsgCampoValidacion(List<CampoValidacionDTO> msgCampoValidacion) {
        this.msgCampoValidacion = msgCampoValidacion;
    }

    /**
     * Metodo que verifica si se superaron las validaciones
     *
     * @return <code>true</code> en caso que la lista de validaciones este
     * vacia, <code>false</code> en caso contrario
     */
    public boolean esValido() {
        return msgCampoValidacion.isEmpty();
    }

}
