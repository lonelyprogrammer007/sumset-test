package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IObraConcretaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IObraConcretaRepo;
import co.gov.sdp.spdd.data.model.ip.PddObraConcreta;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author SumSet 2019
 *
 */
@Service
public class ObraConcretaRepo extends OperacionesBasicasFacade<PddObraConcreta, Long>
		implements IObraConcretaServiceRepo, Serializable {

	@Autowired
	IObraConcretaRepo obraConcretaRepository;

	@Override
	public CrudRepository<PddObraConcreta, Long> getRepo() {
		return obraConcretaRepository;
	}

	@Override
	public List<PddObraConcreta> buscarPorMeta(Long id)  {

		return obraConcretaRepository.findIdMeta(id);
	}

	@Override
	public PddObraConcreta validarCampos(Long idMeta, String obraConcreta) throws SpddExceptions {
		return obraConcretaRepository.validarIdMetaYObraConcreta(idMeta, obraConcreta);
	}



}
