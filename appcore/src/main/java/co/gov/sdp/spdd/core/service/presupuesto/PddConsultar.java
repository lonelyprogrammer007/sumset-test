package co.gov.sdp.spdd.core.service.presupuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.GenericoDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.core.iservice.presupuesto.IPddConsultar;
import co.gov.sdp.spdd.data.sesionfacade.interfaces.ISessionFacadeAFS;

@Service
public class PddConsultar implements IPddConsultar {

	/**
	 * 
	 */
	@Autowired
	ISessionFacadeAFS sessionFacadeAFS;
	
	/**
	 * 
	 */
	@Override
	public GenericoDTO consultarPdd() throws SpddExceptions {
		return sessionFacadeAFS.consultarPdd();
	}

}
