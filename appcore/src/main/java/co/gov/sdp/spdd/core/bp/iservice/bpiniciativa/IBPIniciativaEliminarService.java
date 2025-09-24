package co.gov.sdp.spdd.core.bp.iservice.bpiniciativa;

import co.gov.sdp.nhspdd.common.dto.bp.BpIniciativaCiudadanaDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;

public interface IBPIniciativaEliminarService {

	public BpIniciativaCiudadanaDTO eliminarIniciativaCiudadana(Long idIniciativa) throws SpddExceptions;
}
