package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpAnualizarServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMpAnualizarRepo;
import co.gov.sdp.spdd.data.model.ip.PddMpAnualizar;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMpAnualizarRepo extends OperacionesBasicasFacade<PddMpAnualizar, Long>
		implements IPddMpAnualizarServiceRepo, Serializable {

	@Autowired
	IPddMpAnualizarRepo anualizarRepositorio;

	@Override
	public CrudRepository<PddMpAnualizar, Long> getRepo() {
		return anualizarRepositorio;
	}

	@Override
	public List<PddMpAnualizar> obtenerMpAnualizarPorMetaProducto(Long idMetaProducto) {
		return anualizarRepositorio.obtenerMpAnualizarPorMetaProducto(idMetaProducto);
	}

}
