package co.gov.sdp.spdd.data.dao.impl.ip;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.gov.sdp.spdd.data.dao.interfaces.ip.IPdlNivelAtributoServiceRepo;
import co.gov.sdp.spdd.data.irepositorio.ip.IPdlNivelAtributoRepo;
import co.gov.sdp.spdd.data.model.ip.PddNivel;
import co.gov.sdp.spdd.data.model.ip.PddNivelAtributo;
import co.gov.sdp.spdd.data.model.ip.PdlNivelAtributo;
import co.gov.sdp.spdd.data.operacionesbasicas.impl.OperacionesBasicasFacade;

@Service
public class PdlNivelAtributoRepo extends OperacionesBasicasFacade<PdlNivelAtributo, Long> implements IPdlNivelAtributoServiceRepo, Serializable {

	/**
	 * 
	 */
	@Autowired
	IPdlNivelAtributoRepo pdlNivelAtributoRepository;
	
	@Override
	public CrudRepository<PdlNivelAtributo, Long> getRepo() {
		return pdlNivelAtributoRepository;
	}

	@Override
	public Page<PdlNivelAtributo> obtenerTodosPorIdPdlNivelAtributoPaginado(Long idPdlNivel, Pageable paginador) {
		return pdlNivelAtributoRepository.obtenerTodosPorIdPdlNivelAtributoPaginado(idPdlNivel, paginador);
	}

	@Override
	public Page<PdlNivelAtributo> obtenerTodosPorIdAtributoPadrePaginado(Long idAtributoPadre, Pageable paginador) {
		return pdlNivelAtributoRepository.obtenerTodosPorIdAtributoPadrePaginado(idAtributoPadre, paginador);
	}
	
	@Override
	public PdlNivelAtributo obtenerPorDenominacionYIdPdlNivel(String denominacion, Long idPdlNivel) {
		return pdlNivelAtributoRepository.obtenerPorDenominacionYIdPdlNivel(denominacion, idPdlNivel);
	}
}
