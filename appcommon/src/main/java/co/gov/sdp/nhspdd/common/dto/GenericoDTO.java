package co.gov.sdp.nhspdd.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * DTO para administrador todas las peticiones
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 23/10/2019
 *
 */
@Data
public class GenericoDTO extends RespuestaDTO {

    private Long total;
    
    private Long totalPonderacion;
    
    private Long numeroConsecutivo;
    
    // Lista de multiples objetos para transacciones
    private List<Object> lstObjectsDTO;
    
    

    /**
     * Constructor de la vacio clase
     */
    public GenericoDTO() {
        super();
        lstObjectsDTO = new ArrayList<>();
    }

}
