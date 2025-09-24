package co.gov.sdp.nhspdd.common.dto.ip;

import java.util.List;
import java.util.Map;

import co.gov.sdp.nhspdd.common.dto.RespuestaDTO;
import lombok.Data;

@Data
public class ArbolCompromisoDTO extends RespuestaDTO{

	Map<String, List<Object>> mapObjetos;
}
