package co.gov.sdp.nhspdd.common.dto;

import lombok.Data;

/**
 *
 * @author Jose Alvaro Rodriguez Botero
 * @date 26/12/2016
 *
 */
@Data
public class ValidatorFieldDTO {

    private Integer coderesponse;
    private String entityname;
    private String fieldname;
    private String message;

    /**
     *
     * @param coderesponse
     * @param message
     */
    public ValidatorFieldDTO(Integer coderesponse, String message) {
        super();
        this.coderesponse = coderesponse;
        this.message = message;
    }

}
