package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotRamaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotRamaRepo;

import co.gov.sdp.spdd.data.model.ip.PotRama;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class PotRamaRepo extends OperacionesBasicasFacade<PotRama, Long> 
							implements Serializable,IPotRamaServiceRepo{

	@Autowired
	IPotRamaRepo potRamaRepo;
	
	
	
	@Override
	public CrudRepository<PotRama, Long> getRepo() {
		
		return potRamaRepo;
	}

	@Override
	public List<PotRama> obtenerRamaPotPorIdPot(Long idPot) throws SpddExceptions {
		
		return potRamaRepo.obtenerPotRamaPorIdPot(idPot);
	}

	@Override
	public List<PotRama> obtenerPotRamaPorIdPotNumeroRamaDesc(Long idPot) throws SpddExceptions {
		
		return potRamaRepo.obtenerPotRamaPorIdPotNumeroRamaDesc(idPot);
	}

}
