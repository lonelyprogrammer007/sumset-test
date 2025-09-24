package co.gov.sdp.spdd.data.dao.impl.bp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.bp.IBpIniciativaCondicionServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.bp.IBpIniciativaCondicionRepo;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCiudadana;
import co.gov.sdp.spdd.data.model.bp.BpIniciativaCondicion;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class BpIniciativaCondicionRepo extends OperacionesBasicasFacade<BpIniciativaCondicion, Long>
		implements IBpIniciativaCondicionServiceRepo {

	@Autowired
	IBpIniciativaCondicionRepo bpInciativaCondicionRepositorio;

	@Override
	public CrudRepository<BpIniciativaCondicion, Long> getRepo() {
		return bpInciativaCondicionRepositorio;
	}

	@Override
	public List<BpIniciativaCondicion> obtenerCondicionesPorIniciativa(Long idIniciativa) throws SpddExceptions {
		return bpInciativaCondicionRepositorio.obtenerCondicionPorIniciativa(idIniciativa);
	}

	@Override
	public void eliminarTodasLasCondicionesInciativas(List<BpIniciativaCondicion> listaCondiciones) {
		bpInciativaCondicionRepositorio.deleteAll(listaCondiciones);
	}

}
