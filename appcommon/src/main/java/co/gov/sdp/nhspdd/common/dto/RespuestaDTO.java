package co.gov.sdp.nhspdd.common.dto;

import java.util.List;

import lombok.Data;

/**
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/12/2019
 *
 */
@Data
public class RespuestaDTO {

    private Integer codigoRespuesta;
    
    private String msgRespuesta;
    
    private String lenguaje;
    
    private Integer pagina;
    
    private Integer tamanioPagina;
    
    private List<CampoValidacionDTO> lstCampoValidacion;
    
    private String token;
    
    private Boolean exito;
    
    private int codigo;
    
    private List<?> objetos;
    
    private String tipoOrden;
    
    private String columnaOrdenar;
    
  

    /**
     * Metodo constructor
     */
    public RespuestaDTO() {
        super();
    }

    /**
     * 
     * @param codigoRespuesta
     * @param msgRespuesta
     */
    public RespuestaDTO(Integer codigoRespuesta, String msgRespuesta) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.msgRespuesta = msgRespuesta;
    }

}
