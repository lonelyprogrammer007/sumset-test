package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPotNivelServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPotNivelRepo;
import co.gov.sdp.spdd.data.model.ip.PotNivel;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class PotNivelRepo extends OperacionesBasicasFacade<PotNivel, Long> implements Serializable,IPotNivelServiceRepo{
	
	@Autowired
	IPotNivelRepo nivelRepo;
	
	@Override
	public CrudRepository<PotNivel, Long> getRepo() {
		
		return nivelRepo;
	}

	@Override
	public List<PotNivel> obtenerPotNivelPorIdRamaPot(Long idRamaPot) throws SpddExceptions {
		
		return nivelRepo.obtenerPotNivelPorIdRamaPot(idRamaPot);
	}

	@Override
	public List<PotNivel> obtenerPotNivelPorIdRamaPotDesc(Long idRamaPot) throws SpddExceptions {
	
		return nivelRepo.obtenerPotNivelPorIdRamaPotDesc(idRamaPot);
	}

	@Override
	public List<PotNivel> obtenerPotNivelPorIdNivelPadre(Long idNivelPot) throws SpddExceptions {
		return nivelRepo.obtenerPotNivelPorIdNivelPadre(idNivelPot);
	}

	@Override
	public List<PotNivel> obtenerPotNivelPorIdNivelPadreDesc(Long idNivelPot) throws SpddExceptions {
		return nivelRepo.obtenerPotNivelPorIdNivelPadreDesc(idNivelPot);
	}
							
}
