package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddProblematicaServiceRepo;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddRangoPonderacionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddRangoPonderacionRepo;
import co.gov.sdp.spdd.data.model.ip.PddProblematica;
import co.gov.sdp.spdd.data.model.ip.PddRangoPonderacion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Transactional
@Service
public class PddRangoPonderacionRepo extends OperacionesBasicasFacade<PddRangoPonderacion, Long>
implements IPddRangoPonderacionServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPddRangoPonderacionRepo ppdRangoPonderacionRepository;
	
	/**
	 * 
	 */
	@Override
	public CrudRepository<PddRangoPonderacion, Long> getRepo() {
		return ppdRangoPonderacionRepository;
	}

	/**
	 * 
	 */
	@Override
	public List<PddRangoPonderacion> obtenerPddRangoPonderacionPorIdPdd(Long idPdd) {
		return ppdRangoPonderacionRepository.obtenerPddRangoPonderacionPorIdPdd(idPdd);
	}

}
