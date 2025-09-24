package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMetaServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMetaRepo;
import co.gov.sdp.spdd.data.model.ip.PddMeta;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author SumSet 2019
 *
 */
@Service
public class PddMetaRepo extends OperacionesBasicasFacade<PddMeta, Long> implements IPddMetaServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPddMetaRepo pddMetaRepository;

	/**
	 * 
	 */
	@Override
	public CrudRepository<PddMeta, Long> getRepo() {
		return pddMetaRepository;
	}

	/**
	 * 
	 */
	@Override
	public List<PddMeta> consultarMetasCompromistoEstrategico(Long idEspecifico) throws SpddExceptions {
		return pddMetaRepository.consultarMetasCompromistoEstrategico(idEspecifico);
	}

	@Override
	public PddMeta validarMetaTipoYEspecifico(String meta, Long especifico, Long tipo) throws SpddExceptions {
		return pddMetaRepository.consultarPddMetaPorMetaYIdTipoMetaYIdEspecifico(meta, especifico, tipo);
	}

	@Override
	public void eliminarTodasLasMetasDeCompromisos(Long idCompromiso) throws SpddExceptions {
		List<PddMeta> result = consultarMetasCompromistoEstrategico(idCompromiso);

		if (!result.isEmpty()) {
			pddMetaRepository.deleteAll(result);
		}
	}

}
