package co.gov.sdp.spdd.core.controller.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.nhspdd.common.util.NHSPDDConstantes;
import co.gov.sdp.spdd.core.icontroller.presupuesto.IPddController;
import co.gov.sdp.spdd.core.iservice.presupuesto.IPddConsultar;

/**
 * 
 * @author sumset
 *
 */
@RestController
public class PddController implements IPddController {

	/**
	 * 
	 */
	@Autowired
	IPddConsultar consultar;
	
	/**
	 * 
	 */
	@GetMapping(NHSPDDConstantes.CORE_CONTROLLER_PRESUPUESTO_GET_OBTENER_PDD_TODOS)
	@Override
	public GenericoDTO consultarPdd() throws SpddExceptions {
		return consultar.consultarPdd();
	}

}
