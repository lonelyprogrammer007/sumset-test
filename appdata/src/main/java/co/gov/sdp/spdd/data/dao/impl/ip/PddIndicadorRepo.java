package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddIndicadorServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddIndicadorRepo;
import co.gov.sdp.spdd.data.model.ip.PddIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * Servicio de la clase PddIndicador
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddIndicadorRepo extends OperacionesBasicasFacade<PddIndicador, Long>
		implements IPddIndicadorServiceRepo, Serializable {

	@Autowired
	IPddIndicadorRepo indicadorRepositorio;

	@Override
	public CrudRepository<PddIndicador, Long> getRepo() {
		return indicadorRepositorio;
	}

	@Override
	public List<PddIndicador> obtenerPddIndicadorMetaResultado(String denominacion, Long magnitud, String periodicidad,
			Long fuenteExterna, String fuente, Long lineaBase, Long magnitudLb, String lineaBaseDesc)
			throws SpddExceptions {
		return indicadorRepositorio.obtenerPddIndicadorMetaResultado(denominacion, magnitud, periodicidad,
				fuenteExterna, fuente, lineaBase, magnitudLb, lineaBaseDesc);
	}

}
