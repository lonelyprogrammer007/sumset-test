package co.gov.sdp.spdd.data.dao.impl.ip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.nhspdd.common.dto.ip.PddMpIndicadorDTO;
import co.gov.sdp.nhspdd.common.exception.SpddExceptions;
import co.gov.sdp.spdd.data.dao.interfaces.ip.IPddMpIndicadorServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPddMpIndicadorRepo;
import co.gov.sdp.spdd.data.model.ip.PddMpIndicador;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

/**
 * 
 * @author Bryan Muñoz
 *
 */
@Service
public class PddMpIndicadorRepo extends OperacionesBasicasFacade<PddMpIndicador, Long>
		implements IPddMpIndicadorServiceRepo {

	@Autowired
	IPddMpIndicadorRepo indicadorRepositorio;

	@Override
	public CrudRepository<PddMpIndicador, Long> getRepo() {
		return indicadorRepositorio;
	}

	@Override
	public Page<PddMpIndicador> obtenerMpIndicadorPorMetaProducto(Long idMetaProducto, Pageable pageable)
			throws SpddExceptions {
		return indicadorRepositorio.obtenerMpIndicadorPorMetaProducto(idMetaProducto, pageable);
	}

	@Override
	public PddMpIndicador validarMetaProductoIndicador(Long idMetaProducto, Long idIndicador) throws SpddExceptions {
		return indicadorRepositorio.validarMpIndicador(idMetaProducto, idIndicador);
	}

	@Override
	public List<PddMpIndicador> obtenerTodosDesbalanceados(Long idPlanDesarrollo) {
		return indicadorRepositorio.obtenerTodosDesbalanceados(idPlanDesarrollo);
	}

	@Override
	public Long sumarPonderacionesMpIndicador(Long idMetaProducto) {
		return indicadorRepositorio.sumarPonderacionIndicadoreMetaProducto(idMetaProducto);
	}

	@Override
	public List<PddMpIndicador> obtenerTodosOrdenadosASC() {
		return indicadorRepositorio.obtenerTodosOrdenadosASC();
	}
	
	

}
